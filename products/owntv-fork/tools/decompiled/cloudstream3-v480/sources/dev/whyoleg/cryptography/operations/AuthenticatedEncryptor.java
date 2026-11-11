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
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0096À\u0004¢\u0006\u0002\u0010\u0005J\u0017\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0006H\u0096À\u0004¢\u0006\u0002\u0010\u0007J!\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0003H\u0096À\u0004¢\u0006\u0002\u0010\tJ!\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0096À\u0004¢\u0006\u0002\u0010\nJ\u0012\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0096\u0080\u0004J\u0012\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0006H\u0096\u0080\u0004J\u001c\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0003H¦\u0080\u0004J\u001c\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0096\u0080\u0004J\u0012\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\rH\u0096\u0080\u0004J\u001c\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\u0003H¦\u0080\u0004J\u001c\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0096\u0080\u0004J\u0012\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0096\u0080\u0004J\u001c\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\u0003H¦\u0080\u0004J\u001c\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0096\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/AuthenticatedEncryptor;", "Ldev/whyoleg/cryptography/operations/Encryptor;", "encrypt", "", "plaintext", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/bytestring/ByteString;", "(Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associatedData", "([B[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/io/bytestring/ByteString;Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "encryptBlocking", "encryptingSource", "Lkotlinx/io/RawSource;", "encryptingSink", "Lkotlinx/io/RawSink;", "ciphertext", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface AuthenticatedEncryptor extends Encryptor {

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.AuthenticatedEncryptor$encrypt$4, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticatedCipher.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "dev.whyoleg.cryptography.operations.AuthenticatedEncryptor", f = "AuthenticatedCipher.kt", i = {0, 0, 0}, l = {69}, m = "encrypt$suspendImpl", n = {"$this", "plaintext", "associatedData"}, nl = {336}, s = {"L$0", "L$1", "L$2"}, v = 2)
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
            return CC.encrypt$suspendImpl(AuthenticatedEncryptor.this, (ByteString) null, (ByteString) null, this);
        }
    }

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    Object encrypt(ByteString byteString, Continuation<? super ByteString> continuation);

    Object encrypt(ByteString byteString, ByteString byteString2, Continuation<? super ByteString> continuation);

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    Object encrypt(byte[] bArr, Continuation<? super byte[]> continuation);

    Object encrypt(byte[] bArr, byte[] bArr2, Continuation<? super byte[]> continuation);

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    ByteString encryptBlocking(ByteString plaintext);

    ByteString encryptBlocking(ByteString plaintext, ByteString associatedData);

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    byte[] encryptBlocking(byte[] plaintext);

    byte[] encryptBlocking(byte[] plaintext, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    RawSink encryptingSink(RawSink ciphertext);

    RawSink encryptingSink(RawSink ciphertext, ByteString associatedData);

    RawSink encryptingSink(RawSink ciphertext, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    RawSource encryptingSource(RawSource plaintext);

    RawSource encryptingSource(RawSource plaintext, ByteString associatedData);

    RawSource encryptingSource(RawSource plaintext, byte[] associatedData);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.AuthenticatedEncryptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticatedCipher.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ Object encrypt$suspendImpl(AuthenticatedEncryptor authenticatedEncryptor, byte[] bArr, Continuation<? super byte[]> continuation) {
            return authenticatedEncryptor.encrypt(bArr, (byte[]) null, continuation);
        }

        public static /* synthetic */ Object encrypt$suspendImpl(AuthenticatedEncryptor authenticatedEncryptor, ByteString byteString, Continuation<? super ByteString> continuation) {
            return authenticatedEncryptor.encrypt(byteString, (ByteString) null, continuation);
        }

        public static /* synthetic */ Object encrypt$suspendImpl(AuthenticatedEncryptor authenticatedEncryptor, byte[] bArr, byte[] bArr2, Continuation<? super byte[]> continuation) {
            return authenticatedEncryptor.encryptBlocking(bArr, bArr2);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public static /* synthetic */ Object encrypt$suspendImpl(AuthenticatedEncryptor authenticatedEncryptor, ByteString byteString, ByteString byteString2, Continuation<? super ByteString> continuation) {
            AnonymousClass4 anonymousClass4;
            byte[] data;
            if (continuation instanceof AnonymousClass4) {
                anonymousClass4 = (AnonymousClass4) continuation;
                if ((anonymousClass4.label & Integer.MIN_VALUE) != 0) {
                    anonymousClass4.label -= Integer.MIN_VALUE;
                } else {
                    anonymousClass4 = authenticatedEncryptor.new AnonymousClass4(continuation);
                }
            } else {
                anonymousClass4 = authenticatedEncryptor.new AnonymousClass4(continuation);
            }
            Object objEncrypt = anonymousClass4.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = anonymousClass4.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objEncrypt);
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                byte[] data2 = byteString.getData();
                if (byteString2 != null) {
                    UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
                    data = byteString2.getData();
                } else {
                    data = null;
                }
                anonymousClass4.L$0 = SpillingKt.nullOutSpilledVariable(authenticatedEncryptor);
                anonymousClass4.L$1 = SpillingKt.nullOutSpilledVariable(byteString);
                anonymousClass4.L$2 = SpillingKt.nullOutSpilledVariable(byteString2);
                anonymousClass4.label = 1;
                objEncrypt = authenticatedEncryptor.encrypt(data2, data, anonymousClass4);
                if (objEncrypt == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objEncrypt);
            }
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe((byte[]) objEncrypt);
        }

        public static byte[] $default$encryptBlocking(AuthenticatedEncryptor _this, byte[] plaintext) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return _this.encryptBlocking(plaintext, (byte[]) null);
        }

        public static ByteString $default$encryptBlocking(AuthenticatedEncryptor _this, ByteString plaintext) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return _this.encryptBlocking(plaintext, (ByteString) null);
        }

        public static RawSource $default$encryptingSource(AuthenticatedEncryptor _this, RawSource plaintext) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return _this.encryptingSource(plaintext, (byte[]) null);
        }

        public static RawSink $default$encryptingSink(AuthenticatedEncryptor _this, RawSink ciphertext) {
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return _this.encryptingSink(ciphertext, (byte[]) null);
        }

        public static ByteString $default$encryptBlocking(AuthenticatedEncryptor _this, ByteString plaintext, ByteString byteString) {
            byte[] data;
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            byte[] data2 = plaintext.getData();
            if (byteString != null) {
                UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
                data = byteString.getData();
            } else {
                data = null;
            }
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(_this.encryptBlocking(data2, data));
        }

        public static RawSource $default$encryptingSource(AuthenticatedEncryptor _this, RawSource plaintext, ByteString byteString) {
            byte[] data;
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            if (byteString != null) {
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                data = byteString.getData();
            } else {
                data = null;
            }
            return _this.encryptingSource(plaintext, data);
        }

        public static RawSink $default$encryptingSink(AuthenticatedEncryptor _this, RawSink ciphertext, ByteString byteString) {
            byte[] data;
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            if (byteString != null) {
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                data = byteString.getData();
            } else {
                data = null;
            }
            return _this.encryptingSink(ciphertext, data);
        }
    }
}
