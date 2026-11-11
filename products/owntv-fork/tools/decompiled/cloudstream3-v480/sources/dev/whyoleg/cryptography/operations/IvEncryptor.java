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

/* JADX INFO: compiled from: IvCipher.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0097À\u0004¢\u0006\u0002\u0010\u0006J\u001f\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0007H\u0097À\u0004¢\u0006\u0002\u0010\bJ\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H§\u0080\u0004J\u001a\u0010\t\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0007H\u0097\u0080\u0004J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u000bH§\u0080\u0004J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u000bH\u0097\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\rH§\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\rH\u0097\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/IvEncryptor;", "Ldev/whyoleg/cryptography/operations/Encryptor;", "encryptWithIv", "", "iv", "plaintext", "([B[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/bytestring/ByteString;", "(Lkotlinx/io/bytestring/ByteString;Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "encryptWithIvBlocking", "encryptingSourceWithIv", "Lkotlinx/io/RawSource;", "encryptingSinkWithIv", "Lkotlinx/io/RawSink;", "ciphertext", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface IvEncryptor extends Encryptor {

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.IvEncryptor$encryptWithIv$2, reason: invalid class name */
    /* JADX INFO: compiled from: IvCipher.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "dev.whyoleg.cryptography.operations.IvEncryptor", f = "IvCipher.kt", i = {0, 0, 0}, l = {52}, m = "encryptWithIv$suspendImpl", n = {"$this", "iv", "plaintext"}, nl = {215}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class AnonymousClass2 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CC.encryptWithIv$suspendImpl(IvEncryptor.this, (ByteString) null, (ByteString) null, this);
        }
    }

    @DelicateCryptographyApi
    Object encryptWithIv(ByteString byteString, ByteString byteString2, Continuation<? super ByteString> continuation);

    @DelicateCryptographyApi
    Object encryptWithIv(byte[] bArr, byte[] bArr2, Continuation<? super byte[]> continuation);

    @DelicateCryptographyApi
    ByteString encryptWithIvBlocking(ByteString iv, ByteString plaintext);

    @DelicateCryptographyApi
    byte[] encryptWithIvBlocking(byte[] iv, byte[] plaintext);

    @DelicateCryptographyApi
    RawSink encryptingSinkWithIv(ByteString iv, RawSink ciphertext);

    @DelicateCryptographyApi
    RawSink encryptingSinkWithIv(byte[] iv, RawSink ciphertext);

    @DelicateCryptographyApi
    RawSource encryptingSourceWithIv(ByteString iv, RawSource plaintext);

    @DelicateCryptographyApi
    RawSource encryptingSourceWithIv(byte[] iv, RawSource plaintext);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.IvEncryptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: IvCipher.kt */
    public final /* synthetic */ class CC {
        @DelicateCryptographyApi
        public static /* synthetic */ Object encryptWithIv$suspendImpl(IvEncryptor ivEncryptor, byte[] bArr, byte[] bArr2, Continuation<? super byte[]> continuation) {
            return ivEncryptor.encryptWithIvBlocking(bArr, bArr2);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @DelicateCryptographyApi
        public static /* synthetic */ Object encryptWithIv$suspendImpl(IvEncryptor ivEncryptor, ByteString byteString, ByteString byteString2, Continuation<? super ByteString> continuation) {
            AnonymousClass2 anonymousClass2;
            if (continuation instanceof AnonymousClass2) {
                anonymousClass2 = (AnonymousClass2) continuation;
                if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                    anonymousClass2.label -= Integer.MIN_VALUE;
                } else {
                    anonymousClass2 = ivEncryptor.new AnonymousClass2(continuation);
                }
            } else {
                anonymousClass2 = ivEncryptor.new AnonymousClass2(continuation);
            }
            Object objEncryptWithIv = anonymousClass2.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = anonymousClass2.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objEncryptWithIv);
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                byte[] data = byteString.getData();
                UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
                byte[] data2 = byteString2.getData();
                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(ivEncryptor);
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(byteString);
                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(byteString2);
                anonymousClass2.label = 1;
                objEncryptWithIv = ivEncryptor.encryptWithIv(data, data2, anonymousClass2);
                if (objEncryptWithIv == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objEncryptWithIv);
            }
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe((byte[]) objEncryptWithIv);
        }

        @DelicateCryptographyApi
        public static ByteString $default$encryptWithIvBlocking(IvEncryptor _this, ByteString iv, ByteString plaintext) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            byte[] data = iv.getData();
            UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(_this.encryptWithIvBlocking(data, plaintext.getData()));
        }

        @DelicateCryptographyApi
        public static RawSource $default$encryptingSourceWithIv(IvEncryptor _this, ByteString iv, RawSource plaintext) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return _this.encryptingSourceWithIv(iv.getData(), plaintext);
        }

        @DelicateCryptographyApi
        public static RawSink $default$encryptingSinkWithIv(IvEncryptor _this, ByteString iv, RawSink ciphertext) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return _this.encryptingSinkWithIv(iv.getData(), ciphertext);
        }
    }
}
