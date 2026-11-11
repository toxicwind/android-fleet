package dev.whyoleg.cryptography.providers.base.operations;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.providers.base.BytesKt;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Buffer;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.SourcesKt;

/* JADX INFO: compiled from: BaseImplicitIvFunctions.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0015\u0016BI\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00126\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0004\b\u000b\u0010\fJ\"\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0096\u0080\u0004J\u0012\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u0011H\u0096\u0080\u0004J\u0012\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R?\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseImplicitIvDecryptFunction;", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "ivSize", "", "initialize", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "iv", "startIndex", "<init>", "(ILkotlin/jvm/functions/Function2;)V", "transform", "source", "endIndex", "transformedSource", "Lkotlinx/io/RawSource;", "transformedSink", "Lkotlinx/io/RawSink;", "sink", "ImplicitIvSource", "ImplicitIvSink", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class BaseImplicitIvDecryptFunction implements CipherFunction {
    private final Function2<byte[], Integer, CipherFunction> initialize;
    private final int ivSize;

    /* JADX WARN: Multi-variable type inference failed */
    public BaseImplicitIvDecryptFunction(int i, Function2<? super byte[], ? super Integer, ? extends CipherFunction> initialize) {
        Intrinsics.checkNotNullParameter(initialize, "initialize");
        this.ivSize = i;
        this.initialize = initialize;
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.CipherFunction
    public byte[] transform(byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        BytesKt.checkBounds(source.length, startIndex, endIndex);
        int i = endIndex - startIndex;
        if (i < this.ivSize) {
            throw new IllegalArgumentException(("Not enough data to read iv (expected " + this.ivSize + ", got " + i + ')').toString());
        }
        return this.initialize.invoke(source, Integer.valueOf(startIndex)).transform(source, startIndex + this.ivSize, endIndex);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.CipherFunction
    public RawSource transformedSource(RawSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new ImplicitIvSource(this, source);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.CipherFunction
    public RawSink transformedSink(RawSink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        return new ImplicitIvSink(this, sink);
    }

    /* JADX INFO: compiled from: BaseImplicitIvFunctions.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0096\u0080\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016R\u000f\u0010\u0002\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0001X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseImplicitIvDecryptFunction$ImplicitIvSource;", "Lkotlinx/io/RawSource;", "originalSource", "<init>", "(Ldev/whyoleg/cryptography/providers/base/operations/BaseImplicitIvDecryptFunction;Lkotlinx/io/RawSource;)V", "transformedSource", "readAtMostTo", "", "sink", "Lkotlinx/io/Buffer;", "byteCount", "close", "", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class ImplicitIvSource implements RawSource {
        private final RawSource originalSource;
        final /* synthetic */ BaseImplicitIvDecryptFunction this$0;
        private RawSource transformedSource;

        public ImplicitIvSource(BaseImplicitIvDecryptFunction baseImplicitIvDecryptFunction, RawSource originalSource) {
            Intrinsics.checkNotNullParameter(originalSource, "originalSource");
            this.this$0 = baseImplicitIvDecryptFunction;
            this.originalSource = originalSource;
        }

        @Override // kotlinx.io.RawSource
        public long readAtMostTo(Buffer sink, long byteCount) throws EOFException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (byteCount < 0) {
                throw new IllegalArgumentException(("byteCount < 0: " + byteCount).toString());
            }
            if (byteCount == 0) {
                return 0L;
            }
            if (this.transformedSource == null) {
                Buffer buffer = new Buffer();
                buffer.write(this.originalSource, this.this$0.ivSize);
                this.transformedSource = ((CipherFunction) this.this$0.initialize.invoke(SourcesKt.readByteArray(buffer), 0)).transformedSource(this.originalSource);
            }
            RawSource rawSource = this.transformedSource;
            Intrinsics.checkNotNull(rawSource);
            return rawSource.readAtMostTo(sink, byteCount);
        }

        @Override // kotlinx.io.RawSource, java.lang.AutoCloseable
        public void close() {
            RawSource rawSource = this.transformedSource;
            if (rawSource != null) {
                rawSource.close();
            } else {
                this.originalSource.close();
            }
        }
    }

    /* JADX INFO: compiled from: BaseImplicitIvFunctions.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004J\n\u0010\r\u001a\u00020\tH\u0096\u0080\u0004J\n\u0010\u000e\u001a\u00020\tH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0001X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseImplicitIvDecryptFunction$ImplicitIvSink;", "Lkotlinx/io/RawSink;", "originalSink", "<init>", "(Ldev/whyoleg/cryptography/providers/base/operations/BaseImplicitIvDecryptFunction;Lkotlinx/io/RawSink;)V", "transformedSink", "ivBuffer", "Lkotlinx/io/Buffer;", "write", "", "source", "byteCount", "", "flush", "close", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class ImplicitIvSink implements RawSink {
        private final Buffer ivBuffer;
        private final RawSink originalSink;
        final /* synthetic */ BaseImplicitIvDecryptFunction this$0;
        private RawSink transformedSink;

        public ImplicitIvSink(BaseImplicitIvDecryptFunction baseImplicitIvDecryptFunction, RawSink originalSink) {
            Intrinsics.checkNotNullParameter(originalSink, "originalSink");
            this.this$0 = baseImplicitIvDecryptFunction;
            this.originalSink = originalSink;
            this.ivBuffer = new Buffer();
        }

        @Override // kotlinx.io.RawSink
        public void write(Buffer source, long byteCount) throws EOFException {
            Intrinsics.checkNotNullParameter(source, "source");
            source.require(byteCount);
            if (byteCount == 0) {
                return;
            }
            if (this.transformedSink == null) {
                long atMostTo = source.readAtMostTo(this.ivBuffer, Math.min(byteCount, ((long) this.this$0.ivSize) - this.ivBuffer.getSizeMut()));
                if (this.ivBuffer.getSizeMut() != this.this$0.ivSize) {
                    return;
                }
                this.transformedSink = ((CipherFunction) this.this$0.initialize.invoke(SourcesKt.readByteArray(this.ivBuffer), 0)).transformedSink(this.originalSink);
                byteCount -= atMostTo;
            }
            RawSink rawSink = this.transformedSink;
            Intrinsics.checkNotNull(rawSink);
            rawSink.write(source, byteCount);
        }

        @Override // kotlinx.io.RawSink, java.io.Flushable
        public void flush() {
            RawSink rawSink = this.transformedSink;
            if (rawSink != null) {
                rawSink.flush();
            }
        }

        @Override // kotlinx.io.RawSink, java.lang.AutoCloseable
        public void close() {
            RawSink rawSink = this.transformedSink;
            if (rawSink != null) {
                rawSink.close();
            } else {
                this.originalSink.close();
            }
        }
    }
}
