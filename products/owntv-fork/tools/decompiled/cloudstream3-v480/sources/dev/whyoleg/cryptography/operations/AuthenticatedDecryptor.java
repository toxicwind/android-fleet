package dev.whyoleg.cryptography.operations;

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

/* JADX INFO: compiled from: AuthenticatedCipher.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0096À\u0004¢\u0006\u0002\u0010\u0005J\u0017\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0006H\u0096À\u0004¢\u0006\u0002\u0010\u0007J!\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0003H\u0096À\u0004¢\u0006\u0002\u0010\tJ!\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0096À\u0004¢\u0006\u0002\u0010\nJ\u0012\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0096\u0080\u0004J\u0012\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0006H\u0096\u0080\u0004J\u001c\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0003H¦\u0080\u0004J\u001c\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0096\u0080\u0004J\u0012\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\rH\u0096\u0080\u0004J\u001c\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\u0003H¦\u0080\u0004J\u001c\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0096\u0080\u0004J\u0012\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0096\u0080\u0004J\u001c\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\u0003H¦\u0080\u0004J\u001c\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0096\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/AuthenticatedDecryptor;", "Ldev/whyoleg/cryptography/operations/Decryptor;", "decrypt", "", "ciphertext", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/bytestring/ByteString;", "(Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associatedData", "([B[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/io/bytestring/ByteString;Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decryptBlocking", "decryptingSource", "Lkotlinx/io/RawSource;", "decryptingSink", "Lkotlinx/io/RawSink;", "plaintext", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface AuthenticatedDecryptor extends Decryptor {

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.AuthenticatedDecryptor$decrypt$4, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticatedCipher.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "dev.whyoleg.cryptography.operations.AuthenticatedDecryptor", f = "AuthenticatedCipher.kt", i = {0, 0, 0}, l = {225}, m = "decrypt$suspendImpl", n = {"$this", "ciphertext", "associatedData"}, nl = {336}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class AnonymousClass4 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CC.decrypt$suspendImpl(AuthenticatedDecryptor.this, (ByteString) null, (ByteString) null, this);
        }
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    Object decrypt(ByteString byteString, Continuation<? super ByteString> continuation);

    Object decrypt(ByteString byteString, ByteString byteString2, Continuation<? super ByteString> continuation);

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    Object decrypt(byte[] bArr, Continuation<? super byte[]> continuation);

    Object decrypt(byte[] bArr, byte[] bArr2, Continuation<? super byte[]> continuation);

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    ByteString decryptBlocking(ByteString ciphertext);

    ByteString decryptBlocking(ByteString ciphertext, ByteString associatedData);

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    byte[] decryptBlocking(byte[] ciphertext);

    byte[] decryptBlocking(byte[] ciphertext, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    RawSink decryptingSink(RawSink plaintext);

    RawSink decryptingSink(RawSink plaintext, ByteString associatedData);

    RawSink decryptingSink(RawSink plaintext, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    RawSource decryptingSource(RawSource ciphertext);

    RawSource decryptingSource(RawSource ciphertext, ByteString associatedData);

    RawSource decryptingSource(RawSource ciphertext, byte[] associatedData);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.AuthenticatedDecryptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticatedCipher.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ Object decrypt$suspendImpl(AuthenticatedDecryptor authenticatedDecryptor, byte[] bArr, Continuation<? super byte[]> continuation) {
            return authenticatedDecryptor.decrypt(bArr, (byte[]) null, continuation);
        }

        public static /* synthetic */ Object decrypt$suspendImpl(AuthenticatedDecryptor authenticatedDecryptor, ByteString byteString, Continuation<? super ByteString> continuation) {
            return authenticatedDecryptor.decrypt(byteString, (ByteString) null, continuation);
        }

        public static /* synthetic */ Object decrypt$suspendImpl(AuthenticatedDecryptor authenticatedDecryptor, byte[] bArr, byte[] bArr2, Continuation<? super byte[]> continuation) {
            return authenticatedDecryptor.decryptBlocking(bArr, bArr2);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public static /* synthetic */ Object decrypt$suspendImpl(AuthenticatedDecryptor authenticatedDecryptor, ByteString byteString, ByteString byteString2, Continuation<? super ByteString> continuation) {
            AnonymousClass4 anonymousClass4;
            byte[] data;
            if (continuation instanceof AnonymousClass4) {
                anonymousClass4 = (AnonymousClass4) continuation;
                if ((anonymousClass4.label & Integer.MIN_VALUE) != 0) {
                    anonymousClass4.label -= Integer.MIN_VALUE;
                } else {
                    anonymousClass4 = authenticatedDecryptor.new AnonymousClass4(continuation);
                }
            } else {
                anonymousClass4 = authenticatedDecryptor.new AnonymousClass4(continuation);
            }
            Object objDecrypt = anonymousClass4.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = anonymousClass4.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objDecrypt);
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                byte[] data2 = byteString.getData();
                if (byteString2 != null) {
                    UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
                    data = byteString2.getData();
                } else {
                    data = null;
                }
                anonymousClass4.L$0 = SpillingKt.nullOutSpilledVariable(authenticatedDecryptor);
                anonymousClass4.L$1 = SpillingKt.nullOutSpilledVariable(byteString);
                anonymousClass4.L$2 = SpillingKt.nullOutSpilledVariable(byteString2);
                anonymousClass4.label = 1;
                objDecrypt = authenticatedDecryptor.decrypt(data2, data, anonymousClass4);
                if (objDecrypt == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objDecrypt);
            }
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe((byte[]) objDecrypt);
        }

        public static byte[] $default$decryptBlocking(AuthenticatedDecryptor _this, byte[] ciphertext) {
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return _this.decryptBlocking(ciphertext, (byte[]) null);
        }

        public static ByteString $default$decryptBlocking(AuthenticatedDecryptor _this, ByteString ciphertext) {
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return _this.decryptBlocking(ciphertext, (ByteString) null);
        }

        public static RawSource $default$decryptingSource(AuthenticatedDecryptor _this, RawSource ciphertext) {
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return _this.decryptingSource(ciphertext, (byte[]) null);
        }

        public static RawSink $default$decryptingSink(AuthenticatedDecryptor _this, RawSink plaintext) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return _this.decryptingSink(plaintext, (byte[]) null);
        }

        public static ByteString $default$decryptBlocking(AuthenticatedDecryptor _this, ByteString ciphertext, ByteString byteString) {
            byte[] data;
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            byte[] data2 = ciphertext.getData();
            if (byteString != null) {
                UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
                data = byteString.getData();
            } else {
                data = null;
            }
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(_this.decryptBlocking(data2, data));
        }

        public static RawSource $default$decryptingSource(AuthenticatedDecryptor _this, RawSource ciphertext, ByteString byteString) {
            byte[] data;
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            if (byteString != null) {
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                data = byteString.getData();
            } else {
                data = null;
            }
            return _this.decryptingSource(ciphertext, data);
        }

        public static RawSink $default$decryptingSink(AuthenticatedDecryptor _this, RawSink plaintext, ByteString byteString) {
            byte[] data;
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            if (byteString != null) {
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                data = byteString.getData();
            } else {
                data = null;
            }
            return _this.decryptingSink(plaintext, data);
        }
    }
}
