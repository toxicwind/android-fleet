package dev.whyoleg.cryptography.providers.base.algorithms;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import dev.whyoleg.cryptography.operations.UpdateFunction;
import dev.whyoleg.cryptography.operations.VerifyFunction;
import dev.whyoleg.cryptography.providers.base.BytesKt;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: BaseDss.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\tB\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\u0007\u001a\u00020\bH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/DssRawSignatureVerifier;", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "derVerifier", "orderSize", "", "<init>", "(Ldev/whyoleg/cryptography/operations/SignatureVerifier;I)V", "createVerifyFunction", "Ldev/whyoleg/cryptography/operations/VerifyFunction;", "RawVerifyFunction", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DssRawSignatureVerifier implements SignatureVerifier {
    private final SignatureVerifier derVerifier;
    private final int orderSize;

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ Object tryVerifySignature(RawSource rawSource, ByteString byteString, Continuation continuation) {
        return SignatureVerifier.CC.tryVerifySignature$suspendImpl(this, rawSource, byteString, (Continuation<? super Boolean>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ Object tryVerifySignature(ByteString byteString, ByteString byteString2, Continuation continuation) {
        return SignatureVerifier.CC.tryVerifySignature$suspendImpl(this, byteString, byteString2, (Continuation<? super Boolean>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ Object tryVerifySignature(byte[] bArr, byte[] bArr2, Continuation continuation) {
        return SignatureVerifier.CC.tryVerifySignature$suspendImpl(this, bArr, bArr2, (Continuation<? super Boolean>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ boolean tryVerifySignatureBlocking(RawSource rawSource, ByteString byteString) {
        return SignatureVerifier.CC.$default$tryVerifySignatureBlocking(this, rawSource, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ boolean tryVerifySignatureBlocking(ByteString byteString, ByteString byteString2) {
        return SignatureVerifier.CC.$default$tryVerifySignatureBlocking(this, byteString, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ boolean tryVerifySignatureBlocking(byte[] bArr, byte[] bArr2) {
        return SignatureVerifier.CC.$default$tryVerifySignatureBlocking(this, bArr, bArr2);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ Object verifySignature(RawSource rawSource, ByteString byteString, Continuation continuation) {
        return SignatureVerifier.CC.verifySignature$suspendImpl(this, rawSource, byteString, (Continuation<? super Unit>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ Object verifySignature(ByteString byteString, ByteString byteString2, Continuation continuation) {
        return SignatureVerifier.CC.verifySignature$suspendImpl(this, byteString, byteString2, (Continuation<? super Unit>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ Object verifySignature(byte[] bArr, byte[] bArr2, Continuation continuation) {
        return SignatureVerifier.CC.verifySignature$suspendImpl(this, bArr, bArr2, (Continuation<? super Unit>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ void verifySignatureBlocking(RawSource rawSource, ByteString byteString) {
        SignatureVerifier.CC.$default$verifySignatureBlocking(this, rawSource, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ void verifySignatureBlocking(ByteString byteString, ByteString byteString2) {
        SignatureVerifier.CC.$default$verifySignatureBlocking(this, byteString, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ void verifySignatureBlocking(byte[] bArr, byte[] bArr2) {
        SignatureVerifier.CC.$default$verifySignatureBlocking(this, bArr, bArr2);
    }

    public DssRawSignatureVerifier(SignatureVerifier derVerifier, int i) {
        Intrinsics.checkNotNullParameter(derVerifier, "derVerifier");
        this.derVerifier = derVerifier;
        this.orderSize = i;
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public VerifyFunction createVerifyFunction() {
        return new RawVerifyFunction(this.derVerifier.createVerifyFunction(), this.orderSize);
    }

    /* JADX INFO: compiled from: BaseDss.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0096\u0080\u0004J\"\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0096\u0080\u0004J\"\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\bH\u0096\u0080\u0004J\b\u0010\u0012\u001a\u00020\bH\u0016R\u000f\u0010\u0002\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/DssRawSignatureVerifier$RawVerifyFunction;", "Ldev/whyoleg/cryptography/operations/VerifyFunction;", "derVerifyFunction", "orderSize", "", "<init>", "(Ldev/whyoleg/cryptography/operations/VerifyFunction;I)V", "update", "", "source", "", "startIndex", "endIndex", "tryVerify", "", "signature", "verify", "reset", "close", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class RawVerifyFunction implements VerifyFunction {
        private final VerifyFunction derVerifyFunction;
        private final int orderSize;

        @Override // dev.whyoleg.cryptography.operations.VerifyFunction
        public /* synthetic */ boolean tryVerify(ByteString byteString, int i, int i2) {
            return VerifyFunction.CC.$default$tryVerify(this, byteString, i, i2);
        }

        @Override // dev.whyoleg.cryptography.operations.UpdateFunction
        public /* synthetic */ void update(RawSource rawSource) {
            UpdateFunction.CC.$default$update(this, rawSource);
        }

        @Override // dev.whyoleg.cryptography.operations.UpdateFunction
        public /* synthetic */ void update(ByteString byteString, int i, int i2) {
            UpdateFunction.CC.$default$update(this, byteString, i, i2);
        }

        @Override // dev.whyoleg.cryptography.operations.UpdateFunction
        public /* synthetic */ RawSink updatingSink(RawSink rawSink) {
            return UpdateFunction.CC.$default$updatingSink(this, rawSink);
        }

        @Override // dev.whyoleg.cryptography.operations.UpdateFunction
        public /* synthetic */ RawSource updatingSource(RawSource rawSource) {
            return UpdateFunction.CC.$default$updatingSource(this, rawSource);
        }

        @Override // dev.whyoleg.cryptography.operations.VerifyFunction
        public /* synthetic */ void verify(ByteString byteString, int i, int i2) {
            VerifyFunction.CC.$default$verify(this, byteString, i, i2);
        }

        public RawVerifyFunction(VerifyFunction derVerifyFunction, int i) {
            Intrinsics.checkNotNullParameter(derVerifyFunction, "derVerifyFunction");
            this.derVerifyFunction = derVerifyFunction;
            this.orderSize = i;
        }

        @Override // dev.whyoleg.cryptography.operations.UpdateFunction
        public void update(byte[] source, int startIndex, int endIndex) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.derVerifyFunction.update(source, startIndex, endIndex);
        }

        @Override // dev.whyoleg.cryptography.operations.VerifyFunction
        public boolean tryVerify(byte[] signature, int startIndex, int endIndex) {
            Intrinsics.checkNotNullParameter(signature, "signature");
            BytesKt.checkBounds(signature.length, startIndex, endIndex);
            return VerifyFunction.CC.tryVerify$default(this.derVerifyFunction, BaseDssKt.convertDssRawSignatureToDer(this.orderSize, signature, startIndex, endIndex), 0, 0, 6, (Object) null);
        }

        @Override // dev.whyoleg.cryptography.operations.VerifyFunction
        public void verify(byte[] signature, int startIndex, int endIndex) {
            Intrinsics.checkNotNullParameter(signature, "signature");
            if (!tryVerify(signature, startIndex, endIndex)) {
                throw new IllegalStateException("Invalid signature".toString());
            }
        }

        @Override // dev.whyoleg.cryptography.operations.UpdateFunction
        public void reset() {
            this.derVerifyFunction.reset();
        }

        @Override // java.lang.AutoCloseable
        public void close() throws Exception {
            UByte$$ExternalSyntheticBackport0.m9014m((Object) this.derVerifyFunction);
        }
    }
}
