package coil3.decode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import coil3.Image;
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
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.InterruptibleKt;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: BitmapFactoryDecoder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00172\u00020\u0001:\u0003\u0015\u0016\u0017B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\f\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u000eJ\f\u0010\f\u001a\u00020\r*\u00020\u000fH\u0002J\u0014\u0010\u0010\u001a\u00020\u0011*\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0014\u0010\u0014\u001a\u00020\u0011*\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcoil3/decode/BitmapFactoryDecoder;", "Lcoil3/decode/Decoder;", "source", "Lcoil3/decode/ImageSource;", "options", "Lcoil3/request/Options;", "parallelismLock", "Lkotlinx/coroutines/sync/Semaphore;", "exifOrientationStrategy", "Lcoil3/decode/ExifOrientationStrategy;", "<init>", "(Lcoil3/decode/ImageSource;Lcoil3/request/Options;Lkotlinx/coroutines/sync/Semaphore;Lcoil3/decode/ExifOrientationStrategy;)V", "decode", "Lcoil3/decode/DecodeResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroid/graphics/BitmapFactory$Options;", "configureConfig", "", "exifData", "Lcoil3/decode/ExifData;", "configureScale", "Factory", "ExceptionCatchingSource", "Companion", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BitmapFactoryDecoder implements Decoder {
    public static final int DEFAULT_MAX_PARALLELISM = 4;
    private final ExifOrientationStrategy exifOrientationStrategy;
    private final Options options;
    private final Semaphore parallelismLock;
    private final ImageSource source;

    /* JADX INFO: renamed from: coil3.decode.BitmapFactoryDecoder$decode$1, reason: invalid class name */
    /* JADX INFO: compiled from: BitmapFactoryDecoder.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "coil3.decode.BitmapFactoryDecoder", f = "BitmapFactoryDecoder.kt", i = {0, 0, 1, 1, 1}, l = {212, 40}, m = "decode", n = {"$this$withPermit$iv", "$i$f$withPermit", "$this$withPermit$iv", "$i$f$withPermit", "$i$a$-withPermit-BitmapFactoryDecoder$decode$2"}, s = {"L$0", "I$0", "L$0", "I$0", "I$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
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
            return BitmapFactoryDecoder.this.decode(this);
        }
    }

    public BitmapFactoryDecoder(ImageSource imageSource, Options options, Semaphore semaphore, ExifOrientationStrategy exifOrientationStrategy) {
        this.source = imageSource;
        this.options = options;
        this.parallelismLock = semaphore;
        this.exifOrientationStrategy = exifOrientationStrategy;
    }

    public /* synthetic */ BitmapFactoryDecoder(ImageSource imageSource, Options options, Semaphore semaphore, ExifOrientationStrategy exifOrientationStrategy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(imageSource, options, (i & 4) != 0 ? SemaphoreKt.Semaphore$default(Integer.MAX_VALUE, 0, 2, null) : semaphore, (i & 8) != 0 ? ExifOrientationStrategy.RESPECT_PERFORMANCE : exifOrientationStrategy);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // coil3.decode.Decoder
    public Object decode(Continuation<? super DecodeResult> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Semaphore semaphore;
        int i;
        Throwable th;
        Semaphore semaphore2;
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
        int i2 = anonymousClass1.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                semaphore = this.parallelismLock;
                anonymousClass1.L$0 = semaphore;
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                if (semaphore.acquire(anonymousClass1) != coroutine_suspended) {
                    i = 0;
                }
                return coroutine_suspended;
            }
            if (i2 != 1) {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i3 = anonymousClass1.I$1;
                int i4 = anonymousClass1.I$0;
                semaphore2 = (Semaphore) anonymousClass1.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    DecodeResult decodeResult = (DecodeResult) obj;
                    semaphore2.release();
                    return decodeResult;
                } catch (Throwable th2) {
                    th = th2;
                    semaphore2.release();
                    throw th;
                }
            }
            i = anonymousClass1.I$0;
            Semaphore semaphore3 = (Semaphore) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            semaphore = semaphore3;
            Function0 function0 = new Function0() { // from class: coil3.decode.BitmapFactoryDecoder$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.f$0.decode(new BitmapFactory.Options());
                }
            };
            anonymousClass1.L$0 = semaphore;
            anonymousClass1.I$0 = i;
            anonymousClass1.I$1 = 0;
            anonymousClass1.label = 2;
            Object objRunInterruptible$default = InterruptibleKt.runInterruptible$default(null, function0, anonymousClass1, 1, null);
            if (objRunInterruptible$default != coroutine_suspended) {
                Semaphore semaphore4 = semaphore;
                obj = objRunInterruptible$default;
                semaphore2 = semaphore4;
                DecodeResult decodeResult2 = (DecodeResult) obj;
                semaphore2.release();
                return decodeResult2;
            }
            return coroutine_suspended;
        } catch (Throwable th3) {
            Semaphore semaphore5 = semaphore;
            th = th3;
            semaphore2 = semaphore5;
            semaphore2.release();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DecodeResult decode(BitmapFactory.Options options) throws Exception {
        ExceptionCatchingSource exceptionCatchingSource = new ExceptionCatchingSource(this.source.source());
        BufferedSource bufferedSourceBuffer = Okio.buffer(exceptionCatchingSource);
        boolean z = true;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(bufferedSourceBuffer.peek().inputStream(), null, options);
        Exception exception = exceptionCatchingSource.getException();
        if (exception != null) {
            throw exception;
        }
        options.inJustDecodeBounds = false;
        ExifData exifData = ExifUtils.INSTANCE.getExifData(options.outMimeType, bufferedSourceBuffer, this.exifOrientationStrategy);
        Exception exception2 = exceptionCatchingSource.getException();
        if (exception2 != null) {
            throw exception2;
        }
        options.inMutable = false;
        if (Build.VERSION.SDK_INT >= 26 && ImageRequests_androidKt.getColorSpace(this.options) != null) {
            options.inPreferredColorSpace = ImageRequests_androidKt.getColorSpace(this.options);
        }
        options.inPremultiplied = ImageRequests_androidKt.getPremultipliedAlpha(this.options);
        configureConfig(options, exifData);
        configureScale(options, exifData);
        BufferedSource bufferedSource = bufferedSourceBuffer;
        try {
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(bufferedSource.inputStream(), null, options);
            CloseableKt.closeFinally(bufferedSource, null);
            Exception exception3 = exceptionCatchingSource.getException();
            if (exception3 != null) {
                throw exception3;
            }
            if (bitmapDecodeStream == null) {
                throw new IllegalStateException("BitmapFactory returned a null bitmap. Often this means BitmapFactory could not decode the image data read from the image source (e.g. network, disk, or memory) as it's not encoded as a valid image format.".toString());
            }
            bitmapDecodeStream.setDensity(this.options.getContext().getResources().getDisplayMetrics().densityDpi);
            Image imageAsImage = Image_androidKt.asImage(new BitmapDrawable(this.options.getContext().getResources(), ExifUtils.INSTANCE.reverseTransformations(bitmapDecodeStream, exifData)));
            if (options.inSampleSize <= 1 && !options.inScaled) {
                z = false;
            }
            return new DecodeResult(imageAsImage, z);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(bufferedSource, th);
                throw th2;
            }
        }
    }

    private final void configureConfig(BitmapFactory.Options options, ExifData exifData) {
        Bitmap.Config bitmapConfig = ImageRequests_androidKt.getBitmapConfig(this.options);
        if (exifData.getIsFlipped() || ExifUtilsKt.isRotated(exifData)) {
            bitmapConfig = BitmapsKt.toSoftware(bitmapConfig);
        }
        if (ImageRequests_androidKt.getAllowRgb565(this.options) && bitmapConfig == Bitmap.Config.ARGB_8888 && Intrinsics.areEqual(options.outMimeType, "image/jpeg")) {
            bitmapConfig = Bitmap.Config.RGB_565;
        }
        if (Build.VERSION.SDK_INT >= 26 && options.outConfig == Bitmap.Config.RGBA_F16 && bitmapConfig != Bitmap.Config.HARDWARE) {
            bitmapConfig = Bitmap.Config.RGBA_F16;
        }
        options.inPreferredConfig = bitmapConfig;
    }

    private final void configureScale(BitmapFactory.Options options, ExifData exifData) {
        if (options.outWidth <= 0 || options.outHeight <= 0) {
            options.inSampleSize = 1;
            options.inScaled = false;
            return;
        }
        int i = ExifUtilsKt.isSwapped(exifData) ? options.outHeight : options.outWidth;
        int i2 = ExifUtilsKt.isSwapped(exifData) ? options.outWidth : options.outHeight;
        long jM8088computeDstSizesEdh43o = DecodeUtils.m8088computeDstSizesEdh43o(i, i2, this.options.getSize(), this.options.getScale(), ImageRequestsKt.getMaxBitmapSize(this.options));
        int iM8148getFirstimpl = IntPair.m8148getFirstimpl(jM8088computeDstSizesEdh43o);
        int iM8149getSecondimpl = IntPair.m8149getSecondimpl(jM8088computeDstSizesEdh43o);
        options.inSampleSize = DecodeUtils.calculateInSampleSize(i, i2, iM8148getFirstimpl, iM8149getSecondimpl, this.options.getScale());
        double dComputeSizeMultiplier = DecodeUtils.computeSizeMultiplier(((double) i) / ((double) options.inSampleSize), ((double) i2) / ((double) options.inSampleSize), iM8148getFirstimpl, iM8149getSecondimpl, this.options.getScale());
        if (this.options.getPrecision() == Precision.INEXACT) {
            dComputeSizeMultiplier = RangesKt.coerceAtMost(dComputeSizeMultiplier, 1.0d);
        }
        options.inScaled = !(dComputeSizeMultiplier == 1.0d);
        if (options.inScaled) {
            if (dComputeSizeMultiplier > 1.0d) {
                options.inDensity = MathKt.roundToInt(2.147483647E9d / dComputeSizeMultiplier);
                options.inTargetDensity = Integer.MAX_VALUE;
            } else {
                options.inDensity = Integer.MAX_VALUE;
                options.inTargetDensity = MathKt.roundToInt(2.147483647E9d * dComputeSizeMultiplier);
            }
        }
    }

    /* JADX INFO: compiled from: BitmapFactoryDecoder.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcoil3/decode/BitmapFactoryDecoder$Factory;", "Lcoil3/decode/Decoder$Factory;", "parallelismLock", "Lkotlinx/coroutines/sync/Semaphore;", "exifOrientationStrategy", "Lcoil3/decode/ExifOrientationStrategy;", "<init>", "(Lkotlinx/coroutines/sync/Semaphore;Lcoil3/decode/ExifOrientationStrategy;)V", "create", "Lcoil3/decode/Decoder;", "result", "Lcoil3/fetch/SourceFetchResult;", "options", "Lcoil3/request/Options;", "imageLoader", "Lcoil3/ImageLoader;", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Factory implements Decoder.Factory {
        private final ExifOrientationStrategy exifOrientationStrategy;
        private final Semaphore parallelismLock;

        /* JADX WARN: Multi-variable type inference failed */
        public Factory() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public Factory(Semaphore semaphore, ExifOrientationStrategy exifOrientationStrategy) {
            this.parallelismLock = semaphore;
            this.exifOrientationStrategy = exifOrientationStrategy;
        }

        public /* synthetic */ Factory(Semaphore semaphore, ExifOrientationStrategy exifOrientationStrategy, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? SemaphoreKt.Semaphore$default(4, 0, 2, null) : semaphore, (i & 2) != 0 ? ExifOrientationStrategy.RESPECT_PERFORMANCE : exifOrientationStrategy);
        }

        @Override // coil3.decode.Decoder.Factory
        public Decoder create(SourceFetchResult result, Options options, ImageLoader imageLoader) {
            return new BitmapFactoryDecoder(result.getSource(), options, this.parallelismLock, this.exifOrientationStrategy);
        }
    }

    /* JADX INFO: compiled from: BitmapFactoryDecoder.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0016R.\u0010\t\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b2\u000e\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Lcoil3/decode/BitmapFactoryDecoder$ExceptionCatchingSource;", "Lokio/ForwardingSource;", "delegate", "Lokio/Source;", "<init>", "(Lokio/Source;)V", ES6Iterator.VALUE_PROPERTY, "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "getException", "()Ljava/lang/Exception;", "read", "", "sink", "Lokio/Buffer;", "byteCount", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class ExceptionCatchingSource extends ForwardingSource {
        private Exception exception;

        public ExceptionCatchingSource(Source source) {
            super(source);
        }

        public final Exception getException() {
            return this.exception;
        }

        @Override // okio.ForwardingSource, okio.Source
        public long read(Buffer sink, long byteCount) throws Exception {
            try {
                return super.read(sink, byteCount);
            } catch (Exception e) {
                this.exception = e;
                throw e;
            }
        }
    }
}
