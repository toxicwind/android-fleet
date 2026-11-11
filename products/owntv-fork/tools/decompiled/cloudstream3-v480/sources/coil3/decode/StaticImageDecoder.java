package coil3.decode;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.ImageDecoder$OnHeaderDecodedListener;
import android.util.Size;
import androidx.core.os.Profiling$$ExternalSyntheticApiModelOutline0;
import coil3.ImageLoader;
import coil3.Image_androidKt;
import coil3.fetch.SourceFetchResult;
import coil3.request.ImageRequestsKt;
import coil3.request.ImageRequests_androidKt;
import coil3.request.Options;
import coil3.size.Precision;
import coil3.util.BitmapsKt;
import coil3.util.IntPair;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;

/* JADX INFO: compiled from: StaticImageDecoder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0013B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\r\u001a\u00020\u000eH\u0096@¢\u0006\u0002\u0010\u000fJ\f\u0010\u0010\u001a\u00020\u0011*\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcoil3/decode/StaticImageDecoder;", "Lcoil3/decode/Decoder;", "source", "Landroid/graphics/ImageDecoder$Source;", "closeable", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "options", "Lcoil3/request/Options;", "parallelismLock", "Lkotlinx/coroutines/sync/Semaphore;", "<init>", "(Landroid/graphics/ImageDecoder$Source;Ljava/lang/AutoCloseable;Lcoil3/request/Options;Lkotlinx/coroutines/sync/Semaphore;)V", "decode", "Lcoil3/decode/DecodeResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "configureImageDecoderProperties", "", "Landroid/graphics/ImageDecoder;", "Factory", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StaticImageDecoder implements Decoder {
    private final AutoCloseable closeable;
    private final Options options;
    private final Semaphore parallelismLock;
    private final ImageDecoder.Source source;

    /* JADX INFO: renamed from: coil3.decode.StaticImageDecoder$decode$1, reason: invalid class name */
    /* JADX INFO: compiled from: StaticImageDecoder.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "coil3.decode.StaticImageDecoder", f = "StaticImageDecoder.kt", i = {0, 0}, l = {168}, m = "decode", n = {"$this$withPermit$iv", "$i$f$withPermit"}, s = {"L$0", "I$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return StaticImageDecoder.this.decode(this);
        }
    }

    static final boolean configureImageDecoderProperties$lambda$3(ImageDecoder.DecodeException decodeException) {
        return true;
    }

    public StaticImageDecoder(ImageDecoder.Source source, AutoCloseable autoCloseable, Options options, Semaphore semaphore) {
        this.source = source;
        this.closeable = autoCloseable;
        this.options = options;
        this.parallelismLock = semaphore;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // coil3.decode.Decoder
    public Object decode(Continuation<? super DecodeResult> continuation) {
        AnonymousClass1 anonymousClass1;
        Semaphore semaphore;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Semaphore semaphore2 = this.parallelismLock;
            anonymousClass1.L$0 = semaphore2;
            anonymousClass1.I$0 = 0;
            anonymousClass1.label = 1;
            if (semaphore2.acquire(anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            semaphore = semaphore2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass1.I$0;
            semaphore = (Semaphore) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
        }
        try {
            AutoCloseable autoCloseable = this.closeable;
            try {
                final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                DecodeResult decodeResult = new DecodeResult(Image_androidKt.asImage$default(ImageDecoder.decodeBitmap(this.source, Profiling$$ExternalSyntheticApiModelOutline0.m((Object) new ImageDecoder$OnHeaderDecodedListener() { // from class: coil3.decode.StaticImageDecoder$decode$lambda$2$lambda$1$$inlined$decodeBitmap$1
                    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                        Size size = imageInfo.getSize();
                        int width = size.getWidth();
                        int height = size.getHeight();
                        long jM8088computeDstSizesEdh43o = DecodeUtils.m8088computeDstSizesEdh43o(width, height, this.this$0.options.getSize(), this.this$0.options.getScale(), ImageRequestsKt.getMaxBitmapSize(this.this$0.options));
                        int iM8148getFirstimpl = IntPair.m8148getFirstimpl(jM8088computeDstSizesEdh43o);
                        int iM8149getSecondimpl = IntPair.m8149getSecondimpl(jM8088computeDstSizesEdh43o);
                        if (width > 0 && height > 0 && (width != iM8148getFirstimpl || height != iM8149getSecondimpl)) {
                            double dComputeSizeMultiplier = DecodeUtils.computeSizeMultiplier(width, height, iM8148getFirstimpl, iM8149getSecondimpl, this.this$0.options.getScale());
                            booleanRef.element = dComputeSizeMultiplier < 1.0d;
                            if (booleanRef.element || this.this$0.options.getPrecision() == Precision.EXACT) {
                                imageDecoder.setTargetSize(MathKt.roundToInt(((double) width) * dComputeSizeMultiplier), MathKt.roundToInt(dComputeSizeMultiplier * ((double) height)));
                            }
                        }
                        this.this$0.configureImageDecoderProperties(imageDecoder);
                    }
                })), false, 1, null), booleanRef.element);
                AutoCloseableKt.closeFinally(autoCloseable, null);
                semaphore.release();
                return decodeResult;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(autoCloseable, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            semaphore.release();
            throw th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void configureImageDecoderProperties(ImageDecoder imageDecoder) {
        imageDecoder.setOnPartialImageListener(new ImageDecoder.OnPartialImageListener() { // from class: coil3.decode.StaticImageDecoder$$ExternalSyntheticLambda5
            @Override // android.graphics.ImageDecoder.OnPartialImageListener
            public final boolean onPartialImage(ImageDecoder.DecodeException decodeException) {
                return StaticImageDecoder.configureImageDecoderProperties$lambda$3(decodeException);
            }
        });
        imageDecoder.setAllocator(BitmapsKt.isHardware(ImageRequests_androidKt.getBitmapConfig(this.options)) ? 3 : 1);
        imageDecoder.setMemorySizePolicy(!ImageRequests_androidKt.getAllowRgb565(this.options) ? 1 : 0);
        if (ImageRequests_androidKt.getColorSpace(this.options) != null) {
            imageDecoder.setTargetColorSpace(ImageRequests_androidKt.getColorSpace(this.options));
        }
        imageDecoder.setUnpremultipliedRequired(!ImageRequests_androidKt.getPremultipliedAlpha(this.options));
    }

    /* JADX INFO: compiled from: StaticImageDecoder.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcoil3/decode/StaticImageDecoder$Factory;", "Lcoil3/decode/Decoder$Factory;", "parallelismLock", "Lkotlinx/coroutines/sync/Semaphore;", "<init>", "(Lkotlinx/coroutines/sync/Semaphore;)V", "create", "Lcoil3/decode/Decoder;", "result", "Lcoil3/fetch/SourceFetchResult;", "options", "Lcoil3/request/Options;", "imageLoader", "Lcoil3/ImageLoader;", "isApplicable", "", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Factory implements Decoder.Factory {
        private final Semaphore parallelismLock;

        /* JADX WARN: Multi-variable type inference failed */
        public Factory() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public Factory(Semaphore semaphore) {
            this.parallelismLock = semaphore;
        }

        public /* synthetic */ Factory(Semaphore semaphore, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? SemaphoreKt.Semaphore$default(4, 0, 2, null) : semaphore);
        }

        @Override // coil3.decode.Decoder.Factory
        public Decoder create(SourceFetchResult result, Options options, ImageLoader imageLoader) {
            ImageDecoder.Source imageDecoderSourceOrNull;
            if (isApplicable(options) && (imageDecoderSourceOrNull = StaticImageDecoderKt.toImageDecoderSourceOrNull(result.getSource(), options, false)) != null) {
                return new StaticImageDecoder(imageDecoderSourceOrNull, result.getSource(), options, this.parallelismLock);
            }
            return null;
        }

        private final boolean isApplicable(Options options) {
            Bitmap.Config bitmapConfig = ImageRequests_androidKt.getBitmapConfig(options);
            return bitmapConfig == Bitmap.Config.ARGB_8888 || bitmapConfig == Bitmap.Config.HARDWARE;
        }
    }
}
