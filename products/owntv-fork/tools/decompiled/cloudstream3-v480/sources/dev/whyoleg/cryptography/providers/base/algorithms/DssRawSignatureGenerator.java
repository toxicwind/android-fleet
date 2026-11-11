package dev.whyoleg.cryptography.providers.base.algorithms;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.operations.SignFunction;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.UpdateFunction;
import dev.whyoleg.cryptography.providers.base.BytesKt;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: BaseDss.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\tB\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\u0007\u001a\u00020\bH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/DssRawSignatureGenerator;", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "derGenerator", "orderSize", "", "<init>", "(Ldev/whyoleg/cryptography/operations/SignatureGenerator;I)V", "createSignFunction", "Ldev/whyoleg/cryptography/operations/SignFunction;", "RawSignFunction", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DssRawSignatureGenerator implements SignatureGenerator {
    private final SignatureGenerator derGenerator;
    private final int orderSize;

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public /* synthetic */ Object generateSignature(RawSource rawSource, Continuation continuation) {
        return SignatureGenerator.CC.generateSignature$suspendImpl(this, rawSource, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public /* synthetic */ Object generateSignature(ByteString byteString, Continuation continuation) {
        return SignatureGenerator.CC.generateSignature$suspendImpl(this, byteString, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public /* synthetic */ Object generateSignature(byte[] bArr, Continuation continuation) {
        return SignatureGenerator.CC.generateSignature$suspendImpl(this, bArr, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public /* synthetic */ ByteString generateSignatureBlocking(RawSource rawSource) {
        return SignatureGenerator.CC.$default$generateSignatureBlocking(this, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public /* synthetic */ ByteString generateSignatureBlocking(ByteString byteString) {
        return SignatureGenerator.CC.$default$generateSignatureBlocking(this, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public /* synthetic */ byte[] generateSignatureBlocking(byte[] bArr) {
        return SignatureGenerator.CC.$default$generateSignatureBlocking(this, bArr);
    }

    public DssRawSignatureGenerator(SignatureGenerator derGenerator, int i) {
        Intrinsics.checkNotNullParameter(derGenerator, "derGenerator");
        this.derGenerator = derGenerator;
        this.orderSize = i;
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public SignFunction createSignFunction() {
        return new RawSignFunction(this.derGenerator.createSignFunction(), this.orderSize);
    }

    /* JADX INFO: compiled from: BaseDss.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0096\u0080\u0004J\u001a\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0004H\u0096\u0080\u0004J\n\u0010\u0010\u001a\u00020\nH\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\bH\u0096\u0080\u0004J\b\u0010\u0012\u001a\u00020\bH\u0016R\u000f\u0010\u0002\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/DssRawSignatureGenerator$RawSignFunction;", "Ldev/whyoleg/cryptography/operations/SignFunction;", "derSignFunction", "orderSize", "", "<init>", "(Ldev/whyoleg/cryptography/operations/SignFunction;I)V", "update", "", "source", "", "startIndex", "endIndex", "signIntoByteArray", "destination", "destinationOffset", "signToByteArray", "reset", "close", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class RawSignFunction implements SignFunction {
        private final SignFunction derSignFunction;
        private final int orderSize;

        @Override // dev.whyoleg.cryptography.operations.SignFunction
        public /* synthetic */ ByteString sign() {
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(signToByteArray());
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

        public RawSignFunction(SignFunction derSignFunction, int i) {
            Intrinsics.checkNotNullParameter(derSignFunction, "derSignFunction");
            this.derSignFunction = derSignFunction;
            this.orderSize = i;
        }

        @Override // dev.whyoleg.cryptography.operations.UpdateFunction
        public void update(byte[] source, int startIndex, int endIndex) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.derSignFunction.update(source, startIndex, endIndex);
        }

        @Override // dev.whyoleg.cryptography.operations.SignFunction
        public int signIntoByteArray(byte[] destination, int destinationOffset) {
            Intrinsics.checkNotNullParameter(destination, "destination");
            byte[] bArrSignToByteArray = signToByteArray();
            BytesKt.checkBounds(destination.length, destinationOffset, bArrSignToByteArray.length + destinationOffset);
            ArraysKt.copyInto$default(bArrSignToByteArray, destination, destinationOffset, 0, 0, 12, (Object) null);
            return bArrSignToByteArray.length;
        }

        @Override // dev.whyoleg.cryptography.operations.SignFunction
        public byte[] signToByteArray() {
            return BaseDssKt.convertDssDerSignatureToRaw(this.orderSize, this.derSignFunction.signToByteArray());
        }

        @Override // dev.whyoleg.cryptography.operations.UpdateFunction
        public void reset() {
            this.derSignFunction.reset();
        }

        @Override // java.lang.AutoCloseable
        public void close() throws Exception {
            UByte$$ExternalSyntheticBackport0.m9014m((Object) this.derSignFunction);
        }
    }
}
