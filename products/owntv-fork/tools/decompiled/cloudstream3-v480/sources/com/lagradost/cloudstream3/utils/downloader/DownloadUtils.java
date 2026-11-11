package com.lagradost.cloudstream3.utils.downloader;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.exifinterface.media.ExifInterface;
import coil3.Extras;
import coil3.ImageLoader;
import coil3.SingletonImageLoader;
import coil3.request.ImageRequest;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.material.internal.ViewUtils;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.result.ExtractorSubtitleLink;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UiText;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: DownloadUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00052\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000bJ/\u0010\u0014\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u0012\u0010\u0013J1\u0010\u001c\u001a\u00020\u00192\b\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ'\u0010\u001c\u001a\u00020\u00192\b\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0016\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b\u001c\u0010 JE\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010!*\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u00072\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u0007H\u0000¢\u0006\u0004\b#\u0010$J\u0019\u0010*\u001a\u00020\u0019*\b\u0012\u0004\u0012\u00020'0&H\u0000¢\u0006\u0004\b(\u0010)J\u001a\u0010-\u001a\u00020\u0019*\b\u0012\u0004\u0012\u00020'0&H\u0080@¢\u0006\u0004\b+\u0010,R \u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100¨\u00061"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadUtils;", "", "<init>", "()V", "Landroid/content/Context;", "", ImagesContract.URL, "", "headers", "Landroid/graphics/Bitmap;", "getImageBitmapFromUrl$app", "(Landroid/content/Context;Ljava/lang/String;Ljava/util/Map;)Landroid/graphics/Bitmap;", "getImageBitmapFromUrl", "context", "", "bytesPerSecond", "progress", "total", "getEstimatedTimeLeft$app", "(Landroid/content/Context;JJJ)Ljava/lang/String;", "getEstimatedTimeLeft", "Lcom/lagradost/cloudstream3/ui/result/ExtractorSubtitleLink;", "link", "fileName", "folder", "", "downloadSubtitle$app", "(Landroid/content/Context;Lcom/lagradost/cloudstream3/ui/result/ExtractorSubtitleLink;Ljava/lang/String;Ljava/lang/String;)V", "downloadSubtitle", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeMetadata;", "meta", "(Landroid/content/Context;Lcom/lagradost/cloudstream3/ui/player/SubtitleData;Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeMetadata;)V", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "rhs", "appendAndDontOverride$app", "(Ljava/util/Map;Ljava/util/Map;)Ljava/util/Map;", "appendAndDontOverride", "", "Lkotlinx/coroutines/Job;", "cancel$app", "(Ljava/util/List;)V", "cancel", "join$app", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "join", "j$/util/concurrent/ConcurrentHashMap", "cachedBitmaps", "Lj$/util/concurrent/ConcurrentHashMap;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadUtils {
    public static final DownloadUtils INSTANCE = new DownloadUtils();
    private static final ConcurrentHashMap<String, Bitmap> cachedBitmaps = new ConcurrentHashMap<>();

    private DownloadUtils() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Bitmap getImageBitmapFromUrl$app$default(DownloadUtils downloadUtils, Context context, String str, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            map = null;
        }
        return downloadUtils.getImageBitmapFromUrl$app(context, str, map);
    }

    public final Bitmap getImageBitmapFromUrl$app(final Context context, final String url, final Map<String, String> map) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        return (Bitmap) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.utils.downloader.DownloadUtils$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DownloadUtils.getImageBitmapFromUrl$lambda$0(url, context, map);
            }
        });
    }

    static final Bitmap getImageBitmapFromUrl$lambda$0(String str, Context context, Map map) {
        Bitmap bitmap = cachedBitmaps.get(str);
        if (bitmap != null) {
            return bitmap;
        }
        ImageLoader imageLoader = SingletonImageLoader.get(context);
        ImageRequest.Builder builderData = new ImageRequest.Builder(context).data(str);
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                String str2 = (String) entry.getKey();
                builderData.getExtras().set(new Extras.Key(str2), (String) entry.getValue());
            }
        }
        Bitmap bitmap2 = (Bitmap) BuildersKt__BuildersKt.runBlocking$default(null, new DownloadUtils$getImageBitmapFromUrl$1$bitmap$1(imageLoader, builderData.build(), context, null), 1, null);
        if (bitmap2 != null) {
            cachedBitmaps.putIfAbsent(str, bitmap2);
        }
        return bitmap2;
    }

    public final String getEstimatedTimeLeft$app(Context context, long bytesPerSecond, long progress, long total) {
        UiText uiTextTxt;
        String strAsString;
        Intrinsics.checkNotNullParameter(context, "context");
        if (bytesPerSecond <= 0) {
            return "";
        }
        long j = (total - progress) / bytesPerSecond;
        long j2 = j / 3600;
        long j3 = (j % 3600) / 60;
        long j4 = j % 60;
        if (j2 > 0) {
            uiTextTxt = TextUtilKt.txt(R.string.download_time_left_hour_min_sec_format, Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4));
        } else if (j3 > 0) {
            uiTextTxt = TextUtilKt.txt(R.string.download_time_left_min_sec_format, Long.valueOf(j3), Long.valueOf(j4));
        } else {
            uiTextTxt = j4 > 0 ? TextUtilKt.txt(R.string.download_time_left_sec_format, Long.valueOf(j4)) : null;
        }
        return (uiTextTxt == null || (strAsString = uiTextTxt.asString(context)) == null) ? "" : strAsString;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.downloader.DownloadUtils$downloadSubtitle$1, reason: invalid class name */
    /* JADX INFO: compiled from: DownloadUtils.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadUtils;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.DownloadUtils$downloadSubtitle$1", f = "DownloadUtils.kt", i = {}, l = {103}, m = "invokeSuspend", n = {}, nl = {112}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, DownloadUtils, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ String $fileName;
        final /* synthetic */ String $folder;
        final /* synthetic */ ExtractorSubtitleLink $link;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, ExtractorSubtitleLink extractorSubtitleLink, String str, String str2, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$context = context;
            this.$link = extractorSubtitleLink;
            this.$fileName = str;
            this.$folder = str2;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, DownloadUtils downloadUtils, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(this.$context, this.$link, this.$fileName, this.$folder, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                VideoDownloadManager videoDownloadManager = VideoDownloadManager.INSTANCE;
                Context context = this.$context;
                if (context == null) {
                    return Unit.INSTANCE;
                }
                ExtractorSubtitleLink extractorSubtitleLink = this.$link;
                String str = this.$fileName + ' ' + this.$link.getName();
                String str2 = this.$folder;
                String str3 = StringsKt.contains$default((CharSequence) this.$link.getUrl(), (CharSequence) ".srt", false, 2, (Object) null) ? "srt" : "vtt";
                this.label = 1;
                if (videoDownloadManager.downloadThing(context, extractorSubtitleLink, str, str2, str3, false, null, new Function1() { // from class: com.lagradost.cloudstream3.utils.downloader.DownloadUtils$downloadSubtitle$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return Unit.INSTANCE;
                    }
                }, (ViewUtils.EDGE_TO_EDGE_FLAGS & 256) != 0 ? 3 : 0, (ViewUtils.EDGE_TO_EDGE_FLAGS & 512) != 0 ? 100L : 0L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void downloadSubtitle$app(Context context, ExtractorSubtitleLink link, String fileName, String folder) {
        Intrinsics.checkNotNullParameter(link, "link");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(folder, "folder");
        Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(context, link, fileName, folder, null));
    }

    public final void downloadSubtitle(Context context, SubtitleData link, DownloadObjects.DownloadEpisodeMetadata meta) {
        Intrinsics.checkNotNullParameter(link, "link");
        Intrinsics.checkNotNullParameter(meta, "meta");
        if (context != null) {
            String fileName$app = DownloadFileManagement.INSTANCE.getFileName$app(context, meta);
            DownloadFileManagement downloadFileManagement = DownloadFileManagement.INSTANCE;
            TvType type = meta.getType();
            if (type == null) {
                return;
            }
            INSTANCE.downloadSubtitle$app(context, new ExtractorSubtitleLink(link.getName(), link.getUrl(), "", link.getHeaders()), fileName$app, downloadFileManagement.getFolder$app(type, meta.getMainName()));
        }
    }

    public final <V> Map<String, V> appendAndDontOverride$app(Map<String, ? extends V> map, Map<String, ? extends V> rhs) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        Map<String, V> mutableMap = MapsKt.toMutableMap(map);
        Set<String> setKeySet = map.keySet();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setKeySet, 10));
        Iterator<T> it = setKeySet.iterator();
        while (it.hasNext()) {
            String lowerCase = ((String) it.next()).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            arrayList.add(lowerCase);
        }
        ArrayList arrayList2 = arrayList;
        for (Map.Entry<String, ? extends V> entry : rhs.entrySet()) {
            String key = entry.getKey();
            V value = entry.getValue();
            String lowerCase2 = key.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            if (!arrayList2.contains(lowerCase2)) {
                mutableMap.put(key, value);
            }
        }
        return mutableMap;
    }

    public final void cancel$app(List<? extends Job> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            try {
                Job.CC.cancel$default((Job) it.next(), (CancellationException) null, 1, (Object) null);
            } catch (Throwable th) {
                ArchComponentExtKt.logError(th);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x005d  */
    /* JADX WARN: Code duplicated, block: B:24:0x008a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x008b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x008b -> B:29:0x0096). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object join$app(java.util.List<? extends kotlinx.coroutines.Job> r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.lagradost.cloudstream3.utils.downloader.DownloadUtils$join$1
            if (r0 == 0) goto L14
            r0 = r11
            com.lagradost.cloudstream3.utils.downloader.DownloadUtils$join$1 r0 = (com.lagradost.cloudstream3.utils.downloader.DownloadUtils$join$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            com.lagradost.cloudstream3.utils.downloader.DownloadUtils$join$1 r0 = new com.lagradost.cloudstream3.utils.downloader.DownloadUtils$join$1
            r0.<init>(r9, r11)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r11 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L4b
            if (r1 != r3) goto L43
            int r10 = r0.I$1
            int r10 = r0.I$0
            java.lang.Object r1 = r0.L$4
            kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
            java.lang.Object r1 = r0.L$3
            java.lang.Object r1 = r0.L$2
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r4 = r0.L$1
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.lang.Object r5 = r0.L$0
            java.util.List r5 = (java.util.List) r5
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L41
            goto L96
        L41:
            r9 = move-exception
            goto L93
        L43:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L4b:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r10
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.Iterator r1 = r9.iterator()
            r4 = r9
            r9 = r2
        L57:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L99
            java.lang.Object r5 = r1.next()
            r6 = r5
            kotlinx.coroutines.Job r6 = (kotlinx.coroutines.Job) r6
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch: java.lang.Throwable -> L8e
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L8e
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch: java.lang.Throwable -> L8e
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L8e
            r0.L$2 = r1     // Catch: java.lang.Throwable -> L8e
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch: java.lang.Throwable -> L8e
            r0.L$3 = r5     // Catch: java.lang.Throwable -> L8e
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)     // Catch: java.lang.Throwable -> L8e
            r0.L$4 = r5     // Catch: java.lang.Throwable -> L8e
            r0.I$0 = r9     // Catch: java.lang.Throwable -> L8e
            r0.I$1 = r2     // Catch: java.lang.Throwable -> L8e
            r0.label = r3     // Catch: java.lang.Throwable -> L8e
            java.lang.Object r5 = r6.join(r0)     // Catch: java.lang.Throwable -> L8e
            if (r5 != r11) goto L8b
            return r11
        L8b:
            r5 = r10
            r10 = r9
            goto L96
        L8e:
            r5 = move-exception
            r8 = r10
            r10 = r9
            r9 = r5
            r5 = r8
        L93:
            com.lagradost.cloudstream3.mvvm.ArchComponentExtKt.logError(r9)
        L96:
            r9 = r10
            r10 = r5
            goto L57
        L99:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.downloader.DownloadUtils.join$app(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
