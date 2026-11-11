package dev.whyoleg.cryptography.operations;

import dev.whyoleg.cryptography.DelicateCryptographyApi;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: IvAuthenticatedCipher.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u00012\u00020\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0097À\u0004¢\u0006\u0002\u0010\u0007J\u001f\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\bH\u0097À\u0004¢\u0006\u0002\u0010\tJ)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0097À\u0004¢\u0006\u0002\u0010\u000bJ)\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\bH\u0097À\u0004¢\u0006\u0002\u0010\fJ\u001a\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0097\u0080\u0004J\u001a\u0010\r\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\bH\u0097\u0080\u0004J$\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0004H§\u0080\u0004J$\u0010\r\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\bH\u0097\u0080\u0004J\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u000fH\u0097\u0080\u0004J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\u0004H§\u0080\u0004J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\bH\u0097\u0080\u0004J\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0011H\u0097\u0080\u0004J$\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\n\u001a\u0004\u0018\u00010\u0004H§\u0080\u0004J$\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\n\u001a\u0004\u0018\u00010\bH\u0097\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/IvAuthenticatedDecryptor;", "Ldev/whyoleg/cryptography/operations/IvDecryptor;", "Ldev/whyoleg/cryptography/operations/AuthenticatedDecryptor;", "decryptWithIv", "", "iv", "ciphertext", "([B[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/bytestring/ByteString;", "(Lkotlinx/io/bytestring/ByteString;Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associatedData", "([B[B[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/io/bytestring/ByteString;Lkotlinx/io/bytestring/ByteString;Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decryptWithIvBlocking", "decryptingSourceWithIv", "Lkotlinx/io/RawSource;", "decryptingSinkWithIv", "Lkotlinx/io/RawSink;", "plaintext", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface IvAuthenticatedDecryptor extends IvDecryptor, AuthenticatedDecryptor {

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor$decryptWithIv$4, reason: invalid class name */
    /* JADX INFO: compiled from: IvAuthenticatedCipher.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor", f = "IvAuthenticatedCipher.kt", i = {0, 0, 0, 0}, l = {248}, m = "decryptWithIv$suspendImpl", n = {"$this", "iv", "ciphertext", "associatedData"}, nl = {368}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class AnonymousClass4 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CC.decryptWithIv$suspendImpl(IvAuthenticatedDecryptor.this, (ByteString) null, (ByteString) null, (ByteString) null, this);
        }
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    @DelicateCryptographyApi
    Object decryptWithIv(ByteString byteString, ByteString byteString2, Continuation<? super ByteString> continuation);

    @DelicateCryptographyApi
    Object decryptWithIv(ByteString byteString, ByteString byteString2, ByteString byteString3, Continuation<? super ByteString> continuation);

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    @DelicateCryptographyApi
    Object decryptWithIv(byte[] bArr, byte[] bArr2, Continuation<? super byte[]> continuation);

    @DelicateCryptographyApi
    Object decryptWithIv(byte[] bArr, byte[] bArr2, byte[] bArr3, Continuation<? super byte[]> continuation);

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    @DelicateCryptographyApi
    ByteString decryptWithIvBlocking(ByteString iv, ByteString ciphertext);

    @DelicateCryptographyApi
    ByteString decryptWithIvBlocking(ByteString iv, ByteString ciphertext, ByteString associatedData);

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    @DelicateCryptographyApi
    byte[] decryptWithIvBlocking(byte[] iv, byte[] ciphertext);

    @DelicateCryptographyApi
    byte[] decryptWithIvBlocking(byte[] iv, byte[] ciphertext, byte[] associatedData);

    @DelicateCryptographyApi
    RawSink decryptingSinkWithIv(ByteString iv, RawSink plaintext, ByteString associatedData);

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    @DelicateCryptographyApi
    RawSink decryptingSinkWithIv(byte[] iv, RawSink plaintext);

    @DelicateCryptographyApi
    RawSink decryptingSinkWithIv(byte[] iv, RawSink plaintext, byte[] associatedData);

    @DelicateCryptographyApi
    RawSource decryptingSourceWithIv(ByteString iv, RawSource ciphertext, ByteString associatedData);

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    @DelicateCryptographyApi
    RawSource decryptingSourceWithIv(byte[] iv, RawSource ciphertext);

    @DelicateCryptographyApi
    RawSource decryptingSourceWithIv(byte[] iv, RawSource ciphertext, byte[] associatedData);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: IvAuthenticatedCipher.kt */
    public final /* synthetic */ class CC {
        @DelicateCryptographyApi
        public static /* synthetic */ Object decryptWithIv$suspendImpl(IvAuthenticatedDecryptor ivAuthenticatedDecryptor, byte[] bArr, byte[] bArr2, Continuation<? super byte[]> continuation) {
            return ivAuthenticatedDecryptor.decryptWithIv(bArr, bArr2, (byte[]) null, continuation);
        }

        @DelicateCryptographyApi
        public static /* synthetic */ Object decryptWithIv$suspendImpl(IvAuthenticatedDecryptor ivAuthenticatedDecryptor, ByteString byteString, ByteString byteString2, Continuation<? super ByteString> continuation) {
            return ivAuthenticatedDecryptor.decryptWithIv(byteString, byteString2, (ByteString) null, continuation);
        }

        @DelicateCryptographyApi
        public static /* synthetic */ Object decryptWithIv$suspendImpl(IvAuthenticatedDecryptor ivAuthenticatedDecryptor, byte[] bArr, byte[] bArr2, byte[] bArr3, Continuation<? super byte[]> continuation) {
            return ivAuthenticatedDecryptor.decryptWithIvBlocking(bArr, bArr2, bArr3);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @DelicateCryptographyApi
        public static /* synthetic */ Object decryptWithIv$suspendImpl(IvAuthenticatedDecryptor ivAuthenticatedDecryptor, ByteString byteString, ByteString byteString2, ByteString byteString3, Continuation<? super ByteString> continuation) {
            AnonymousClass4 anonymousClass4;
            if (continuation instanceof AnonymousClass4) {
                anonymousClass4 = (AnonymousClass4) continuation;
                if ((anonymousClass4.label & Integer.MIN_VALUE) != 0) {
                    anonymousClass4.label -= Integer.MIN_VALUE;
                } else {
                    anonymousClass4 = ivAuthenticatedDecryptor.new AnonymousClass4(continuation);
                }
            } else {
                anonymousClass4 = ivAuthenticatedDecryptor.new AnonymousClass4(continuation);
            }
            Object objDecryptWithIv = anonymousClass4.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = anonymousClass4.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objDecryptWithIv);
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                byte[] data = byteString.getData();
                UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
                byte[] data2 = byteString2.getData();
                byte[] byteArray$default = byteString3 != null ? ByteString.toByteArray$default(byteString3, 0, 0, 3, null) : null;
                anonymousClass4.L$0 = SpillingKt.nullOutSpilledVariable(ivAuthenticatedDecryptor);
                anonymousClass4.L$1 = SpillingKt.nullOutSpilledVariable(byteString);
                anonymousClass4.L$2 = SpillingKt.nullOutSpilledVariable(byteString2);
                anonymousClass4.L$3 = SpillingKt.nullOutSpilledVariable(byteString3);
                anonymousClass4.label = 1;
                objDecryptWithIv = ivAuthenticatedDecryptor.decryptWithIv(data, data2, byteArray$default, anonymousClass4);
                if (objDecryptWithIv == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objDecryptWithIv);
            }
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe((byte[]) objDecryptWithIv);
        }

        @DelicateCryptographyApi
        public static byte[] $default$decryptWithIvBlocking(IvAuthenticatedDecryptor _this, byte[] iv, byte[] ciphertext) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return _this.decryptWithIvBlocking(iv, ciphertext, (byte[]) null);
        }

        @DelicateCryptographyApi
        public static ByteString $default$decryptWithIvBlocking(IvAuthenticatedDecryptor _this, ByteString iv, ByteString ciphertext) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return _this.decryptWithIvBlocking(iv, ciphertext, (ByteString) null);
        }

        @DelicateCryptographyApi
        public static RawSource $default$decryptingSourceWithIv(IvAuthenticatedDecryptor _this, byte[] iv, RawSource ciphertext) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return _this.decryptingSourceWithIv(iv, ciphertext, (byte[]) null);
        }

        @DelicateCryptographyApi
        public static RawSink $default$decryptingSinkWithIv(IvAuthenticatedDecryptor _this, byte[] iv, RawSink plaintext) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return _this.decryptingSinkWithIv(iv, plaintext, (byte[]) null);
        }

        @DelicateCryptographyApi
        public static ByteString $default$decryptWithIvBlocking(IvAuthenticatedDecryptor _this, ByteString iv, ByteString ciphertext, ByteString byteString) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            byte[] data = iv.getData();
            UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(_this.decryptWithIvBlocking(data, ciphertext.getData(), byteString != null ? ByteString.toByteArray$default(byteString, 0, 0, 3, null) : null));
        }

        @DelicateCryptographyApi
        public static RawSource $default$decryptingSourceWithIv(IvAuthenticatedDecryptor _this, ByteString iv, RawSource ciphertext, ByteString byteString) {
            byte[] data;
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            byte[] data2 = iv.getData();
            if (byteString != null) {
                UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
                data = byteString.getData();
            } else {
                data = null;
            }
            return _this.decryptingSourceWithIv(data2, ciphertext, data);
        }

        @DelicateCryptographyApi
        public static RawSink $default$decryptingSinkWithIv(IvAuthenticatedDecryptor _this, ByteString iv, RawSink plaintext, ByteString byteString) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return _this.decryptingSinkWithIv(iv.getData(), plaintext, byteString != null ? ByteString.toByteArray$default(byteString, 0, 0, 3, null) : null);
        }
    }
}
