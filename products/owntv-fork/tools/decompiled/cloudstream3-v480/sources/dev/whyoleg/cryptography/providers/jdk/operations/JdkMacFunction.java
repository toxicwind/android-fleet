package dev.whyoleg.cryptography.providers.jdk.operations;

import dev.whyoleg.cryptography.operations.SignFunction;
import dev.whyoleg.cryptography.operations.UpdateFunction;
import dev.whyoleg.cryptography.operations.VerifyFunction;
import dev.whyoleg.cryptography.providers.base.BytesKt;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.ShortBufferException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: JdkMacSignature.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\bF\u0012\u0010\u0010\u0003\u001a\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0004¢\u0006\u0004\b\u0007\u0010\bJ\"\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0096\u0080\u0004J\u001a\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u000eH\u0096\u0080\u0004J\n\u0010\u0013\u001a\u00020\fH\u0096\u0080\u0004J\"\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0096\u0080\u0004J\"\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0096\u0080\u0004J\n\u0010\u0018\u001a\u00020\nH\u0096\u0080\u0004J\b\u0010\u0019\u001a\u00020\nH\u0016R\u0019\u0010\u0003\u001a\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/operations/JdkMacFunction;", "Ldev/whyoleg/cryptography/operations/SignFunction;", "Ldev/whyoleg/cryptography/operations/VerifyFunction;", "mac", "Ldev/whyoleg/cryptography/providers/jdk/Pooled$Resource;", "Ljavax/crypto/Mac;", "Ldev/whyoleg/cryptography/providers/jdk/JMac;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/Pooled$Resource;)V", "update", "", "source", "", "startIndex", "", "endIndex", "signIntoByteArray", "destination", "destinationOffset", "signToByteArray", "tryVerify", "", "signature", "verify", "reset", "close", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class JdkMacFunction implements SignFunction, VerifyFunction {
    private final Pooled.Resource<Mac> mac;

    @Override // dev.whyoleg.cryptography.operations.SignFunction
    public /* synthetic */ ByteString sign() {
        return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(signToByteArray());
    }

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

    public JdkMacFunction(Pooled.Resource<Mac> mac) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        this.mac = mac;
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public void update(byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        BytesKt.checkBounds(source.length, startIndex, endIndex);
        this.mac.access().update(source, startIndex, endIndex - startIndex);
    }

    @Override // dev.whyoleg.cryptography.operations.SignFunction
    public int signIntoByteArray(byte[] destination, int destinationOffset) throws ShortBufferException {
        Intrinsics.checkNotNullParameter(destination, "destination");
        Mac macAccess = this.mac.access();
        BytesKt.checkBounds(destination.length, destinationOffset, macAccess.getMacLength() + destinationOffset);
        macAccess.doFinal(destination, destinationOffset);
        return macAccess.getMacLength();
    }

    @Override // dev.whyoleg.cryptography.operations.SignFunction
    public byte[] signToByteArray() {
        byte[] bArrDoFinal = this.mac.access().doFinal();
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
        return bArrDoFinal;
    }

    @Override // dev.whyoleg.cryptography.operations.VerifyFunction
    public boolean tryVerify(byte[] signature, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        BytesKt.checkBounds(signature.length, startIndex, endIndex);
        return Arrays.equals(signToByteArray(), ArraysKt.copyOfRange(signature, startIndex, endIndex));
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
        this.mac.access().reset();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.mac.close();
    }
}
