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
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0097À\u0004¢\u0006\u0002\u0010\u0006J\u001f\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0007H\u0097À\u0004¢\u0006\u0002\u0010\bJ\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H§\u0080\u0004J\u001a\u0010\t\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0007H\u0097\u0080\u0004J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u000bH§\u0080\u0004J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u000bH\u0097\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\rH§\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\rH\u0097\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/IvDecryptor;", "Ldev/whyoleg/cryptography/operations/Decryptor;", "decryptWithIv", "", "iv", "ciphertext", "([B[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/bytestring/ByteString;", "(Lkotlinx/io/bytestring/ByteString;Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decryptWithIvBlocking", "decryptingSourceWithIv", "Lkotlinx/io/RawSource;", "decryptingSinkWithIv", "Lkotlinx/io/RawSink;", "plaintext", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface IvDecryptor extends Decryptor {

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.IvDecryptor$decryptWithIv$2, reason: invalid class name */
    /* JADX INFO: compiled from: IvCipher.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "dev.whyoleg.cryptography.operations.IvDecryptor", f = "IvCipher.kt", i = {0, 0, 0}, l = {147}, m = "decryptWithIv$suspendImpl", n = {"$this", "iv", "ciphertext"}, nl = {215}, s = {"L$0", "L$1", "L$2"}, v = 2)
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
            return CC.decryptWithIv$suspendImpl(IvDecryptor.this, (ByteString) null, (ByteString) null, this);
        }
    }

    @DelicateCryptographyApi
    Object decryptWithIv(ByteString byteString, ByteString byteString2, Continuation<? super ByteString> continuation);

    @DelicateCryptographyApi
    Object decryptWithIv(byte[] bArr, byte[] bArr2, Continuation<? super byte[]> continuation);

    @DelicateCryptographyApi
    ByteString decryptWithIvBlocking(ByteString iv, ByteString ciphertext);

    @DelicateCryptographyApi
    byte[] decryptWithIvBlocking(byte[] iv, byte[] ciphertext);

    @DelicateCryptographyApi
    RawSink decryptingSinkWithIv(ByteString iv, RawSink plaintext);

    @DelicateCryptographyApi
    RawSink decryptingSinkWithIv(byte[] iv, RawSink plaintext);

    @DelicateCryptographyApi
    RawSource decryptingSourceWithIv(ByteString iv, RawSource ciphertext);

    @DelicateCryptographyApi
    RawSource decryptingSourceWithIv(byte[] iv, RawSource ciphertext);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.IvDecryptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: IvCipher.kt */
    public final /* synthetic */ class CC {
        @DelicateCryptographyApi
        public static /* synthetic */ Object decryptWithIv$suspendImpl(IvDecryptor ivDecryptor, byte[] bArr, byte[] bArr2, Continuation<? super byte[]> continuation) {
            return ivDecryptor.decryptWithIvBlocking(bArr, bArr2);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @DelicateCryptographyApi
        public static /* synthetic */ Object decryptWithIv$suspendImpl(IvDecryptor ivDecryptor, ByteString byteString, ByteString byteString2, Continuation<? super ByteString> continuation) {
            AnonymousClass2 anonymousClass2;
            if (continuation instanceof AnonymousClass2) {
                anonymousClass2 = (AnonymousClass2) continuation;
                if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                    anonymousClass2.label -= Integer.MIN_VALUE;
                } else {
                    anonymousClass2 = ivDecryptor.new AnonymousClass2(continuation);
                }
            } else {
                anonymousClass2 = ivDecryptor.new AnonymousClass2(continuation);
            }
            Object objDecryptWithIv = anonymousClass2.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = anonymousClass2.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objDecryptWithIv);
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                byte[] data = byteString.getData();
                UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
                byte[] data2 = byteString2.getData();
                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(ivDecryptor);
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(byteString);
                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(byteString2);
                anonymousClass2.label = 1;
                objDecryptWithIv = ivDecryptor.decryptWithIv(data, data2, anonymousClass2);
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
        public static ByteString $default$decryptWithIvBlocking(IvDecryptor _this, ByteString iv, ByteString ciphertext) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            byte[] data = iv.getData();
            UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(_this.decryptWithIvBlocking(data, ciphertext.getData()));
        }

        @DelicateCryptographyApi
        public static RawSource $default$decryptingSourceWithIv(IvDecryptor _this, ByteString iv, RawSource ciphertext) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return _this.decryptingSourceWithIv(iv.getData(), ciphertext);
        }

        @DelicateCryptographyApi
        public static RawSink $default$decryptingSinkWithIv(IvDecryptor _this, ByteString iv, RawSink plaintext) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return _this.decryptingSinkWithIv(iv.getData(), plaintext);
        }
    }
}
