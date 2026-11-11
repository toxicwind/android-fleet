package com.lagradost.cloudstream3.ui.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.utils.Coroutines;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: PreviewGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 32\u00020\u0001:\u00013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0015H\u0002J\"\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\"0$J\u001e\u0010 \u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(J\b\u0010)\u001a\u00020\u001cH\u0016J\u0010\u00100\u001a\u00020\u001c2\u0006\u00101\u001a\u000202H\u0003R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\u00020+X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/¨\u00064"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/Mp4PreviewGenerator;", "Lcom/lagradost/cloudstream3/ui/player/IPreviewGenerator;", "params", "Lcom/lagradost/cloudstream3/ui/player/ImageParams;", "<init>", "(Lcom/lagradost/cloudstream3/ui/player/ImageParams;)V", "getParams", "()Lcom/lagradost/cloudstream3/ui/player/ImageParams;", "setParams", "loadedLod", "", "loadedImages", "getLoadedImages", "()I", "setLoadedImages", "(I)V", "images", "", "Landroid/graphics/Bitmap;", "[Landroid/graphics/Bitmap;", "hasPreview", "", "getPreviewImage", "fraction", "", "retriever", "Landroid/media/MediaMetadataRetriever;", "clear", "", "keepCache", "currentJob", "Lkotlinx/coroutines/Job;", "load", ImagesContract.URL, "", "headers", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "release", "durationMs", "", "getDurationMs", "()J", "setDurationMs", "(J)V", "start", "scope", "Lkotlinx/coroutines/CoroutineScope;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class Mp4PreviewGenerator implements IPreviewGenerator {
    private static final String TAG = "PreviewImgMp4";
    private Job currentJob;
    private long durationMs;
    private Bitmap[] images;
    private int loadedImages;
    private int loadedLod;
    private ImageParams params;
    private final MediaMetadataRetriever retriever;

    public Mp4PreviewGenerator(ImageParams params) {
        Intrinsics.checkNotNullParameter(params, "params");
        this.params = params;
        Bitmap[] bitmapArr = new Bitmap[63];
        for (int i = 0; i < 63; i++) {
            bitmapArr[i] = null;
        }
        this.images = bitmapArr;
        this.retriever = new MediaMetadataRetriever();
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public ImageParams getParams() {
        return this.params;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void setParams(ImageParams imageParams) {
        Intrinsics.checkNotNullParameter(imageParams, "<set-?>");
        this.params = imageParams;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public int getLoadedImages() {
        return this.loadedImages;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void setLoadedImages(int i) {
        this.loadedImages = i;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public boolean hasPreview() {
        boolean z;
        synchronized (this.images) {
            z = this.loadedLod >= 3;
        }
        return z;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public Bitmap getPreviewImage(float fraction) {
        synchronized (this.images) {
            if (this.loadedLod < 3) {
                Log.i(TAG, "Requesting preview for " + fraction + " but " + this.loadedLod + " < 3");
                return null;
            }
            Log.i(TAG, "Requesting preview for " + fraction);
            float fAbs = Math.abs(0.5f - fraction);
            int i = this.loadedLod + 1;
            int i2 = 0;
            if (1 <= i) {
                int i3 = 1;
                int i4 = 0;
                while (true) {
                    int i5 = 1 << (i3 - 1);
                    for (int i6 = 0; i6 < i5; i6++) {
                        int i7 = (i5 - 1) + i6;
                        if (i7 > getLoadedImages()) {
                            break;
                        }
                        if (this.images[i7] != null) {
                            float fAbs2 = Math.abs(((1.0f / (1 << i3)) + (i6 * (1.0f / i5))) - fraction);
                            if (fAbs2 < fAbs) {
                                i4 = i7;
                                fAbs = fAbs2;
                            }
                        }
                    }
                    if (i3 == i) {
                        break;
                    }
                    i3++;
                }
                i2 = i4;
            }
            Log.i(TAG, "Best diff found at " + (fAbs * 100.0f) + "% diff (" + i2 + ')');
            return this.images[i2];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clear(boolean keepCache) {
        if (keepCache) {
            return;
        }
        synchronized (this.images) {
            this.loadedLod = 0;
            setLoadedImages(0);
            ArraysKt.fill$default(this.images, (Object) null, 0, 0, 6, (Object) null);
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.Mp4PreviewGenerator$load$1, reason: invalid class name */
    /* JADX INFO: compiled from: PreviewGenerator.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/player/Mp4PreviewGenerator;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.Mp4PreviewGenerator$load$1", f = "PreviewGenerator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Mp4PreviewGenerator, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $headers;
        final /* synthetic */ String $url;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ Mp4PreviewGenerator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Map<String, String> map, Mp4PreviewGenerator mp4PreviewGenerator, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$url = str;
            this.$headers = map;
            this.this$0 = mp4PreviewGenerator;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, Mp4PreviewGenerator mp4PreviewGenerator, Continuation<? super Unit> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$url, this.$headers, this.this$0, continuation);
            anonymousClass1.L$0 = coroutineScope;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Log.i(Mp4PreviewGenerator.TAG, "Loading with url = " + this.$url + " headers = " + this.$headers);
            this.this$0.clear(true);
            this.this$0.retriever.setDataSource(this.$url, this.$headers);
            this.this$0.start(coroutineScope);
            return Unit.INSTANCE;
        }
    }

    public final void load(String url, Map<String, String> headers) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Job job = this.currentJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.currentJob = Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(url, headers, this, null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.Mp4PreviewGenerator$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: PreviewGenerator.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/player/Mp4PreviewGenerator;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.Mp4PreviewGenerator$load$2", f = "PreviewGenerator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function3<CoroutineScope, Mp4PreviewGenerator, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ boolean $keepCache;
        final /* synthetic */ Uri $uri;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ Mp4PreviewGenerator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Uri uri, Mp4PreviewGenerator mp4PreviewGenerator, boolean z, Context context, Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
            this.$uri = uri;
            this.this$0 = mp4PreviewGenerator;
            this.$keepCache = z;
            this.$context = context;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, Mp4PreviewGenerator mp4PreviewGenerator, Continuation<? super Unit> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$uri, this.this$0, this.$keepCache, this.$context, continuation);
            anonymousClass2.L$0 = coroutineScope;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Log.i(Mp4PreviewGenerator.TAG, "Loading with uri = " + this.$uri);
            this.this$0.clear(this.$keepCache);
            this.this$0.retriever.setDataSource(this.$context, this.$uri);
            this.this$0.start(coroutineScope);
            return Unit.INSTANCE;
        }
    }

    public final void load(boolean keepCache, Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Job job = this.currentJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.currentJob = Coroutines.INSTANCE.ioSafe(this, new AnonymousClass2(uri, this, keepCache, context, null));
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void release() {
        Job job = this.currentJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        clear(false);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public long getDurationMs() {
        return this.durationMs;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void setDurationMs(long j) {
        this.durationMs = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void start(CoroutineScope scope) {
        Log.i(TAG, "Started loading preview");
        String strExtractMetadata = this.retriever.extractMetadata(9);
        if (strExtractMetadata == null) {
            throw new IllegalArgumentException("Bad video duration");
        }
        long j = Long.parseLong(strExtractMetadata);
        setDurationMs(j);
        float f = 1000 * j;
        int iCoerceIn = RangesKt.coerceIn((int) Math.ceil(MathKt.log2(j / 10000)), 3, 6);
        if (1 > iCoerceIn) {
            return;
        }
        int i = 1;
        while (true) {
            int i2 = 1 << (i - 1);
            for (int i3 = 0; i3 < i2; i3++) {
                int i4 = (i2 - 1) + i3;
                float f2 = (1.0f / (1 << i)) + (i3 * (1.0f / i2));
                Log.i(TAG, "Generating preview for " + (100.0f * f2) + '%');
                Bitmap bitmapImage = PreviewGeneratorKt.image(this.retriever, (long) (f2 * f), getParams());
                if (!CoroutineScopeKt.isActive(scope)) {
                    return;
                }
                if (bitmapImage != null && bitmapImage.getWidth() > 1 && bitmapImage.getHeight() > 1) {
                    synchronized (this.images) {
                        this.images[i4] = bitmapImage;
                        setLoadedImages(Math.max(getLoadedImages(), i4));
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            synchronized (this.images) {
                this.loadedLod = Math.max(this.loadedLod, i);
                Unit unit2 = Unit.INSTANCE;
            }
            if (i == iCoerceIn) {
                return;
            } else {
                i++;
            }
        }
    }
}
