package dev.whyoleg.cryptography.operations;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: Signature.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004J\u001f\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0096À\u0004¢\u0006\u0002\u0010\tJ\u001f\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\nH\u0096À\u0004¢\u0006\u0002\u0010\u000bJ\u001f\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\nH\u0096À\u0004¢\u0006\u0002\u0010\rJ\u001a\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0096\u0080\u0004J\u001a\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\nH\u0096\u0080\u0004J\u001a\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\nH\u0096\u0080\u0004J\u001f\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0096À\u0004¢\u0006\u0002\u0010\tJ\u001f\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\nH\u0096À\u0004¢\u0006\u0002\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\nH\u0096À\u0004¢\u0006\u0002\u0010\rJ\u001a\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0096\u0080\u0004J\u001a\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\nH\u0096\u0080\u0004J\u001a\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\nH\u0096\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "", "createVerifyFunction", "Ldev/whyoleg/cryptography/operations/VerifyFunction;", "tryVerifySignature", "", "data", "", "signature", "([B[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/bytestring/ByteString;", "(Lkotlinx/io/bytestring/ByteString;Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/RawSource;", "(Lkotlinx/io/RawSource;Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryVerifySignatureBlocking", "verifySignature", "", "verifySignatureBlocking", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface SignatureVerifier {
    VerifyFunction createVerifyFunction();

    Object tryVerifySignature(RawSource rawSource, ByteString byteString, Continuation<? super Boolean> continuation);

    Object tryVerifySignature(ByteString byteString, ByteString byteString2, Continuation<? super Boolean> continuation);

    Object tryVerifySignature(byte[] bArr, byte[] bArr2, Continuation<? super Boolean> continuation);

    boolean tryVerifySignatureBlocking(RawSource data, ByteString signature);

    boolean tryVerifySignatureBlocking(ByteString data, ByteString signature);

    boolean tryVerifySignatureBlocking(byte[] data, byte[] signature);

    Object verifySignature(RawSource rawSource, ByteString byteString, Continuation<? super Unit> continuation);

    Object verifySignature(ByteString byteString, ByteString byteString2, Continuation<? super Unit> continuation);

    Object verifySignature(byte[] bArr, byte[] bArr2, Continuation<? super Unit> continuation);

    void verifySignatureBlocking(RawSource data, ByteString signature);

    void verifySignatureBlocking(ByteString data, ByteString signature);

    void verifySignatureBlocking(byte[] data, byte[] signature);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.SignatureVerifier$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Signature.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ Object tryVerifySignature$suspendImpl(SignatureVerifier signatureVerifier, byte[] bArr, byte[] bArr2, Continuation<? super Boolean> continuation) {
            return Boxing.boxBoolean(signatureVerifier.tryVerifySignatureBlocking(bArr, bArr2));
        }

        public static /* synthetic */ Object tryVerifySignature$suspendImpl(SignatureVerifier signatureVerifier, RawSource rawSource, ByteString byteString, Continuation<? super Boolean> continuation) {
            return Boxing.boxBoolean(signatureVerifier.tryVerifySignatureBlocking(rawSource, byteString));
        }

        public static boolean $default$tryVerifySignatureBlocking(SignatureVerifier _this, byte[] data, byte[] signature) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(signature, "signature");
            VerifyFunction verifyFunctionCreateVerifyFunction = _this.createVerifyFunction();
            try {
                VerifyFunction verifyFunction = verifyFunctionCreateVerifyFunction;
                UpdateFunction.CC.update$default(verifyFunction, data, 0, 0, 6, (Object) null);
                boolean zTryVerify$default = VerifyFunction.CC.tryVerify$default(verifyFunction, signature, 0, 0, 6, (Object) null);
                AutoCloseableKt.closeFinally(verifyFunctionCreateVerifyFunction, null);
                return zTryVerify$default;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(verifyFunctionCreateVerifyFunction, th);
                    throw th2;
                }
            }
        }

        public static boolean $default$tryVerifySignatureBlocking(SignatureVerifier _this, RawSource data, ByteString signature) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(signature, "signature");
            VerifyFunction verifyFunctionCreateVerifyFunction = _this.createVerifyFunction();
            try {
                VerifyFunction verifyFunction = verifyFunctionCreateVerifyFunction;
                verifyFunction.update(data);
                boolean zTryVerify$default = VerifyFunction.CC.tryVerify$default(verifyFunction, signature, 0, 0, 6, (Object) null);
                AutoCloseableKt.closeFinally(verifyFunctionCreateVerifyFunction, null);
                return zTryVerify$default;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(verifyFunctionCreateVerifyFunction, th);
                    throw th2;
                }
            }
        }

        public static /* synthetic */ Object verifySignature$suspendImpl(SignatureVerifier signatureVerifier, byte[] bArr, byte[] bArr2, Continuation<? super Unit> continuation) {
            signatureVerifier.verifySignatureBlocking(bArr, bArr2);
            return Unit.INSTANCE;
        }

        public static /* synthetic */ Object verifySignature$suspendImpl(SignatureVerifier signatureVerifier, RawSource rawSource, ByteString byteString, Continuation<? super Unit> continuation) {
            signatureVerifier.verifySignatureBlocking(rawSource, byteString);
            return Unit.INSTANCE;
        }

        public static void $default$verifySignatureBlocking(SignatureVerifier _this, byte[] data, byte[] signature) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(signature, "signature");
            VerifyFunction verifyFunctionCreateVerifyFunction = _this.createVerifyFunction();
            try {
                VerifyFunction verifyFunction = verifyFunctionCreateVerifyFunction;
                UpdateFunction.CC.update$default(verifyFunction, data, 0, 0, 6, (Object) null);
                VerifyFunction.CC.verify$default(verifyFunction, signature, 0, 0, 6, (Object) null);
                Unit unit = Unit.INSTANCE;
                AutoCloseableKt.closeFinally(verifyFunctionCreateVerifyFunction, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(verifyFunctionCreateVerifyFunction, th);
                    throw th2;
                }
            }
        }

        public static void $default$verifySignatureBlocking(SignatureVerifier _this, RawSource data, ByteString signature) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(signature, "signature");
            VerifyFunction verifyFunctionCreateVerifyFunction = _this.createVerifyFunction();
            try {
                VerifyFunction verifyFunction = verifyFunctionCreateVerifyFunction;
                verifyFunction.update(data);
                VerifyFunction.CC.verify$default(verifyFunction, signature, 0, 0, 6, (Object) null);
                Unit unit = Unit.INSTANCE;
                AutoCloseableKt.closeFinally(verifyFunctionCreateVerifyFunction, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(verifyFunctionCreateVerifyFunction, th);
                    throw th2;
                }
            }
        }

        public static /* synthetic */ Object tryVerifySignature$suspendImpl(SignatureVerifier signatureVerifier, ByteString byteString, ByteString byteString2, Continuation<? super Boolean> continuation) {
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            byte[] data = byteString.getData();
            UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
            return signatureVerifier.tryVerifySignature(data, byteString2.getData(), continuation);
        }

        public static boolean $default$tryVerifySignatureBlocking(SignatureVerifier _this, ByteString data, ByteString signature) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(signature, "signature");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            byte[] data2 = data.getData();
            UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
            return _this.tryVerifySignatureBlocking(data2, signature.getData());
        }

        public static /* synthetic */ Object verifySignature$suspendImpl(SignatureVerifier signatureVerifier, ByteString byteString, ByteString byteString2, Continuation<? super Unit> continuation) {
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            byte[] data = byteString.getData();
            UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
            Object objVerifySignature = signatureVerifier.verifySignature(data, byteString2.getData(), continuation);
            return objVerifySignature == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objVerifySignature : Unit.INSTANCE;
        }

        public static void $default$verifySignatureBlocking(SignatureVerifier _this, ByteString data, ByteString signature) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(signature, "signature");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            byte[] data2 = data.getData();
            UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
            _this.verifySignatureBlocking(data2, signature.getData());
        }
    }
}
