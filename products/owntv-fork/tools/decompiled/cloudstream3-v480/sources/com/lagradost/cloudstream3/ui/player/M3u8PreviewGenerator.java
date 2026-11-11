package com.lagradost.cloudstream3.ui.player;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.M3u8Helper;
import com.lagradost.cloudstream3.utils.M3u8Helper2;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: PreviewGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\b\u0002\u0018\u0000 ,2\u00020\u0001:\u0001,B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0016J\"\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020)0+R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u0018\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020 X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/M3u8PreviewGenerator;", "Lcom/lagradost/cloudstream3/ui/player/IPreviewGenerator;", "params", "Lcom/lagradost/cloudstream3/ui/player/ImageParams;", "<init>", "(Lcom/lagradost/cloudstream3/ui/player/ImageParams;)V", "getParams", "()Lcom/lagradost/cloudstream3/ui/player/ImageParams;", "setParams", "images", "", "Landroid/graphics/Bitmap;", "[Landroid/graphics/Bitmap;", "prefixSum", "", "[Ljava/lang/Double;", "loadedImages", "", "getLoadedImages", "()I", "setLoadedImages", "(I)V", "totalImages", "hasPreview", "", "getPreviewImage", "fraction", "", "clear", "", "release", "durationMs", "", "getDurationMs", "()J", "setDurationMs", "(J)V", "currentJob", "Lkotlinx/coroutines/Job;", "load", ImagesContract.URL, "", "headers", "", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class M3u8PreviewGenerator implements IPreviewGenerator {
    private static final String TAG = "PreviewImgM3u8";
    private Job currentJob;
    private long durationMs;
    private Bitmap[] images;
    private int loadedImages;
    private ImageParams params;
    private Double[] prefixSum;
    private int totalImages;

    public M3u8PreviewGenerator(ImageParams params) {
        Intrinsics.checkNotNullParameter(params, "params");
        this.params = params;
        this.images = new Bitmap[0];
        this.prefixSum = new Double[0];
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
        return this.totalImages > 0 && getLoadedImages() >= Math.min(this.totalImages, 4);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public Bitmap getPreviewImage(float fraction) {
        Bitmap bitmap;
        synchronized (this.images) {
            int length = this.images.length;
            int i = -1;
            double d = Double.MAX_VALUE;
            for (int i2 = 0; i2 < length; i2++) {
                double dAbs = Math.abs(this.prefixSum[i2].doubleValue() - ((double) fraction));
                if (dAbs > d) {
                    break;
                }
                if (this.images[i2] != null) {
                    i = i2;
                    d = dAbs;
                }
            }
            bitmap = (Bitmap) ArraysKt.getOrNull(this.images, i);
        }
        return bitmap;
    }

    private final void clear() {
        synchronized (this.images) {
            Job job = this.currentJob;
            if (job != null) {
                Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.images = new Bitmap[0];
            this.prefixSum = new Double[0];
            setLoadedImages(0);
            this.totalImages = 0;
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void release() {
        clear();
        this.images = new Bitmap[0];
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public long getDurationMs() {
        return this.durationMs;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void setDurationMs(long j) {
        this.durationMs = j;
    }

    public final void load(String url, Map<String, String> headers) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        clear();
        Job job = this.currentJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.currentJob = Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(url, headers, this, null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.M3u8PreviewGenerator$load$1, reason: invalid class name */
    /* JADX INFO: compiled from: PreviewGenerator.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/player/M3u8PreviewGenerator;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.M3u8PreviewGenerator$load$1", f = "PreviewGenerator.kt", i = {}, l = {MediaError.DetailedErrorCode.DASH_NO_INIT}, m = "invokeSuspend", n = {}, nl = {405}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, M3u8PreviewGenerator, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $headers;
        final /* synthetic */ String $url;
        int label;
        final /* synthetic */ M3u8PreviewGenerator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Map<String, String> map, M3u8PreviewGenerator m3u8PreviewGenerator, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$url = str;
            this.$headers = map;
            this.this$0 = m3u8PreviewGenerator;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, M3u8PreviewGenerator m3u8PreviewGenerator, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(this.$url, this.$headers, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.M3u8PreviewGenerator$load$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: PreviewGenerator.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.M3u8PreviewGenerator$load$1$1", f = "PreviewGenerator.kt", i = {0, 0}, l = {329}, m = "invokeSuspend", n = {"$this$withContext", "retriever"}, nl = {339}, s = {"L$0", "L$1"}, v = 2)
        static final class C01731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Map<String, String> $headers;
            final /* synthetic */ String $url;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ M3u8PreviewGenerator this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01731(String str, Map<String, String> map, M3u8PreviewGenerator m3u8PreviewGenerator, Continuation<? super C01731> continuation) {
                super(2, continuation);
                this.$url = str;
                this.$headers = map;
                this.this$0 = m3u8PreviewGenerator;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C01731 c01731 = new C01731(this.$url, this.$headers, this.this$0, continuation);
                c01731.L$0 = obj;
                return c01731;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                MediaMetadataRetriever mediaMetadataRetriever;
                Object objHslLazy$default;
                Bitmap bitmap;
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Log.i(M3u8PreviewGenerator.TAG, "Loading with url = " + this.$url + " headers = " + this.$headers);
                    mediaMetadataRetriever = new MediaMetadataRetriever();
                    this.L$0 = coroutineScope;
                    this.L$1 = mediaMetadataRetriever;
                    this.label = 1;
                    objHslLazy$default = M3u8Helper2.hslLazy$default(M3u8Helper2.INSTANCE, new M3u8Helper.M3u8Stream(this.$url, null, this.$headers, 2, null), false, false, 0, this, 8, null);
                    if (objHslLazy$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    MediaMetadataRetriever mediaMetadataRetriever2 = (MediaMetadataRetriever) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    objHslLazy$default = obj;
                    mediaMetadataRetriever = mediaMetadataRetriever2;
                }
                M3u8Helper2.LazyHlsDownloadData lazyHlsDownloadData = (M3u8Helper2.LazyHlsDownloadData) objHslLazy$default;
                if (lazyHlsDownloadData.isEncrypted()) {
                    Log.i(M3u8PreviewGenerator.TAG, "m3u8 is encrypted");
                    this.this$0.totalImages = 0;
                    return Unit.INSTANCE;
                }
                Iterator<T> it = lazyHlsDownloadData.getAllTsLinks().iterator();
                double dDoubleValue = 0.0d;
                while (it.hasNext()) {
                    Double time = ((M3u8Helper2.TsLink) it.next()).getTime();
                    dDoubleValue += time != null ? time.doubleValue() : 0.0d;
                }
                this.this$0.setDurationMs((long) (1000.0d * dDoubleValue));
                double d = 1.0d / dDoubleValue;
                M3u8PreviewGenerator m3u8PreviewGenerator = this.this$0;
                if (dDoubleValue <= 10.0d) {
                    m3u8PreviewGenerator.totalImages = 0;
                    return Unit.INSTANCE;
                }
                m3u8PreviewGenerator.totalImages = lazyHlsDownloadData.getAllTsLinks().size();
                M3u8PreviewGenerator m3u8PreviewGenerator2 = this.this$0;
                int size = lazyHlsDownloadData.getAllTsLinks().size() + 1;
                Double[] dArr = new Double[size];
                for (int i2 = 0; i2 < size; i2++) {
                    dArr[i2] = Boxing.boxDouble(0.0d);
                }
                m3u8PreviewGenerator2.prefixSum = dArr;
                int size2 = lazyHlsDownloadData.getAllTsLinks().size();
                int i3 = 0;
                double dDoubleValue2 = 0.0d;
                while (i3 < size2) {
                    Double time2 = lazyHlsDownloadData.getAllTsLinks().get(i3).getTime();
                    dDoubleValue2 += time2 != null ? time2.doubleValue() : 0.0d;
                    i3++;
                    this.this$0.prefixSum[i3] = Boxing.boxDouble(dDoubleValue2 * d);
                }
                Bitmap[] bitmapArr = this.this$0.images;
                M3u8PreviewGenerator m3u8PreviewGenerator3 = this.this$0;
                synchronized (bitmapArr) {
                    int size3 = lazyHlsDownloadData.getSize();
                    Bitmap[] bitmapArr2 = new Bitmap[size3];
                    for (int i4 = 0; i4 < size3; i4++) {
                        bitmapArr2[i4] = null;
                    }
                    m3u8PreviewGenerator3.images = bitmapArr2;
                    m3u8PreviewGenerator3.setLoadedImages(0);
                    Unit unit = Unit.INSTANCE;
                }
                int iCoerceIn = RangesKt.coerceIn((int) Math.ceil(MathKt.log2(dDoubleValue)), 3, 6);
                int size4 = lazyHlsDownloadData.getAllTsLinks().size();
                if (1 <= iCoerceIn) {
                    int i5 = 1;
                    while (true) {
                        int i6 = 1 << (i5 - 1);
                        for (int i7 = 0; i7 < i6; i7++) {
                            int iCoerceIn2 = RangesKt.coerceIn((size4 / (1 << i5)) + ((i7 * size4) / i6), 0, lazyHlsDownloadData.getSize());
                            Bitmap[] bitmapArr3 = this.this$0.images;
                            M3u8PreviewGenerator m3u8PreviewGenerator4 = this.this$0;
                            synchronized (bitmapArr3) {
                                bitmap = m3u8PreviewGenerator4.images[iCoerceIn2];
                            }
                            if (bitmap == null) {
                                Log.i(M3u8PreviewGenerator.TAG, "Generating preview for " + iCoerceIn2);
                                try {
                                    mediaMetadataRetriever.setDataSource(lazyHlsDownloadData.getAllTsLinks().get(iCoerceIn2).getUrl(), lazyHlsDownloadData.getHeaders());
                                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                                        Bitmap bitmapImage = PreviewGeneratorKt.image(mediaMetadataRetriever, 0L, this.this$0.getParams());
                                        if (!CoroutineScopeKt.isActive(coroutineScope)) {
                                            return Unit.INSTANCE;
                                        }
                                        if (bitmapImage != null && bitmapImage.getWidth() > 1 && bitmapImage.getHeight() > 1) {
                                            Bitmap[] bitmapArr4 = this.this$0.images;
                                            M3u8PreviewGenerator m3u8PreviewGenerator5 = this.this$0;
                                            synchronized (bitmapArr4) {
                                                m3u8PreviewGenerator5.images[iCoerceIn2] = bitmapImage;
                                                m3u8PreviewGenerator5.setLoadedImages(m3u8PreviewGenerator5.getLoadedImages() + 1);
                                                Unit unit2 = Unit.INSTANCE;
                                            }
                                        }
                                    } else {
                                        return Unit.INSTANCE;
                                    }
                                } catch (Throwable th) {
                                    ArchComponentExtKt.logError(th);
                                }
                            }
                        }
                        if (i5 == iCoerceIn) {
                            break;
                        }
                        i5++;
                    }
                }
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getIO(), new C01731(this.$url, this.$headers, this.this$0, null), this) == coroutine_suspended) {
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
}
