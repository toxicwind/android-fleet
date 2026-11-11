package dev.whyoleg.cryptography.operations;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: Signature.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H¦\u0080\u0004J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0096\u0080\u0004J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H¦\u0080\u0004J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0096\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/VerifyFunction;", "Ldev/whyoleg/cryptography/operations/UpdateFunction;", "tryVerify", "", "signature", "", "startIndex", "", "endIndex", "Lkotlinx/io/bytestring/ByteString;", "verify", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface VerifyFunction extends UpdateFunction {
    boolean tryVerify(ByteString signature, int startIndex, int endIndex);

    boolean tryVerify(byte[] signature, int startIndex, int endIndex);

    void verify(ByteString signature, int startIndex, int endIndex);

    void verify(byte[] signature, int startIndex, int endIndex);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.VerifyFunction$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Signature.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ boolean tryVerify$default(VerifyFunction verifyFunction, byte[] bArr, int i, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryVerify");
            }
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = bArr.length;
            }
            return verifyFunction.tryVerify(bArr, i, i2);
        }

        public static /* synthetic */ boolean tryVerify$default(VerifyFunction verifyFunction, ByteString byteString, int i, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryVerify");
            }
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = byteString.getSize();
            }
            return verifyFunction.tryVerify(byteString, i, i2);
        }

        public static /* synthetic */ void verify$default(VerifyFunction verifyFunction, byte[] bArr, int i, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: verify");
            }
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = bArr.length;
            }
            verifyFunction.verify(bArr, i, i2);
        }

        public static /* synthetic */ void verify$default(VerifyFunction verifyFunction, ByteString byteString, int i, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: verify");
            }
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = byteString.getSize();
            }
            verifyFunction.verify(byteString, i, i2);
        }

        public static boolean $default$tryVerify(VerifyFunction _this, ByteString signature, int i, int i2) {
            Intrinsics.checkNotNullParameter(signature, "signature");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return _this.tryVerify(signature.getData(), i, i2);
        }

        public static void $default$verify(VerifyFunction _this, ByteString signature, int i, int i2) {
            Intrinsics.checkNotNullParameter(signature, "signature");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            _this.verify(signature.getData(), i, i2);
        }
    }
}
