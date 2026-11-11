package dev.whyoleg.cryptography.providers.base.operations;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import java.io.EOFException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Buffer;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.Sink;

/* JADX INFO: compiled from: BaseImplicitIvFunctions.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0011\u0012B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\"\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0096\u0080\u0004J\u0012\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\rH\u0096\u0080\u0004J\u0012\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0004\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseImplicitIvEncryptFunction;", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "iv", "", "cipherFunction", "<init>", "([BLdev/whyoleg/cryptography/providers/base/operations/CipherFunction;)V", "transform", "source", "startIndex", "", "endIndex", "transformedSource", "Lkotlinx/io/RawSource;", "transformedSink", "Lkotlinx/io/RawSink;", "sink", "ImplicitIvSource", "ImplicitIvSink", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class BaseImplicitIvEncryptFunction implements CipherFunction {
    private final CipherFunction cipherFunction;
    private final byte[] iv;

    public BaseImplicitIvEncryptFunction(byte[] iv, CipherFunction cipherFunction) {
        Intrinsics.checkNotNullParameter(iv, "iv");
        Intrinsics.checkNotNullParameter(cipherFunction, "cipherFunction");
        this.iv = iv;
        this.cipherFunction = cipherFunction;
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.CipherFunction
    public byte[] transform(byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        return ArraysKt.plus(this.iv, this.cipherFunction.transform(source, startIndex, endIndex));
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.CipherFunction
    public RawSource transformedSource(RawSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new ImplicitIvSource(this.iv, this.cipherFunction.transformedSource(source));
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.CipherFunction
    public RawSink transformedSink(RawSink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        return this.cipherFunction.transformedSink(new ImplicitIvSink(this.iv, sink));
    }

    /* JADX INFO: compiled from: BaseImplicitIvFunctions.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\tH\u0096\u0080\u0004J\b\u0010\f\u001a\u00020\rH\u0016R\u000f\u0010\u0004\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0002\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseImplicitIvEncryptFunction$ImplicitIvSource;", "Lkotlinx/io/RawSource;", "iv", "", "transformedSource", "<init>", "([BLkotlinx/io/RawSource;)V", "Lkotlinx/io/Buffer;", "readAtMostTo", "", "sink", "byteCount", "close", "", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class ImplicitIvSource implements RawSource {
        private final Buffer iv;
        private final RawSource transformedSource;

        public ImplicitIvSource(byte[] iv, RawSource transformedSource) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(transformedSource, "transformedSource");
            this.transformedSource = transformedSource;
            Buffer buffer = new Buffer();
            Sink.CC.write$default(buffer, iv, 0, 0, 6, null);
            this.iv = buffer;
        }

        @Override // kotlinx.io.RawSource
        public long readAtMostTo(Buffer sink, long byteCount) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (byteCount < 0) {
                throw new IllegalArgumentException(("byteCount < 0: " + byteCount).toString());
            }
            if (byteCount == 0) {
                return 0L;
            }
            if (this.iv.getSizeMut() != 0) {
                long atMostTo = this.iv.readAtMostTo(sink, byteCount);
                if (this.iv.getSizeMut() != 0) {
                    return atMostTo;
                }
                byteCount -= atMostTo;
            }
            return this.transformedSource.readAtMostTo(sink, byteCount);
        }

        @Override // kotlinx.io.RawSource, java.lang.AutoCloseable
        public void close() {
            this.transformedSource.close();
        }
    }

    /* JADX INFO: compiled from: BaseImplicitIvFunctions.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004J\n\u0010\r\u001a\u00020\tH\u0096\u0080\u0004J\n\u0010\u000e\u001a\u00020\tH\u0096\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0002\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseImplicitIvEncryptFunction$ImplicitIvSink;", "Lkotlinx/io/RawSink;", "iv", "", "originalSink", "<init>", "([BLkotlinx/io/RawSink;)V", "Lkotlinx/io/Buffer;", "write", "", "source", "byteCount", "", "flush", "close", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class ImplicitIvSink implements RawSink {
        private final Buffer iv;
        private final RawSink originalSink;

        public ImplicitIvSink(byte[] iv, RawSink originalSink) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(originalSink, "originalSink");
            this.originalSink = originalSink;
            Buffer buffer = new Buffer();
            Sink.CC.write$default(buffer, iv, 0, 0, 6, null);
            this.iv = buffer;
        }

        @Override // kotlinx.io.RawSink
        public void write(Buffer source, long byteCount) throws EOFException {
            Intrinsics.checkNotNullParameter(source, "source");
            source.require(byteCount);
            if (byteCount == 0) {
                return;
            }
            if (this.iv.getSizeMut() != 0) {
                RawSink rawSink = this.originalSink;
                Buffer buffer = this.iv;
                rawSink.write(buffer, buffer.getSizeMut());
            }
            this.originalSink.write(source, byteCount);
        }

        @Override // kotlinx.io.RawSink, java.io.Flushable
        public void flush() {
            this.originalSink.flush();
        }

        @Override // kotlinx.io.RawSink, java.lang.AutoCloseable
        public void close() throws Throwable {
            if (this.iv.getSizeMut() != 0) {
                RawSink rawSink = this.originalSink;
                Buffer buffer = this.iv;
                rawSink.write(buffer, buffer.getSizeMut());
            }
            th = null;
            try {
                this.originalSink.close();
            } catch (Throwable th) {
                if (th == null) {
                    th = th;
                } else {
                    ExceptionsKt.addSuppressed(th, th);
                }
            }
            if (th != null) {
                throw th;
            }
        }
    }
}
