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

/* JADX INFO: compiled from: Cipher.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0096À\u0004¢\u0006\u0002\u0010\u0005J\u0017\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0006H\u0096À\u0004¢\u0006\u0002\u0010\u0007J\u0012\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H¦\u0080\u0004J\u0012\u0010\b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0006H\u0096\u0080\u0004J\u0012\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\nH¦\u0080\u0004J\u0012\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/Decryptor;", "", "decrypt", "", "ciphertext", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/bytestring/ByteString;", "(Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decryptBlocking", "decryptingSource", "Lkotlinx/io/RawSource;", "decryptingSink", "Lkotlinx/io/RawSink;", "plaintext", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface Decryptor {

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.Decryptor$decrypt$2, reason: invalid class name */
    /* JADX INFO: compiled from: Cipher.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "dev.whyoleg.cryptography.operations.Decryptor", f = "Cipher.kt", i = {0, 0}, l = {101}, m = "decrypt$suspendImpl", n = {"$this", "ciphertext"}, nl = {139}, s = {"L$0", "L$1"}, v = 2)
    static final class AnonymousClass2 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CC.decrypt$suspendImpl(Decryptor.this, (ByteString) null, this);
        }
    }

    Object decrypt(ByteString byteString, Continuation<? super ByteString> continuation);

    Object decrypt(byte[] bArr, Continuation<? super byte[]> continuation);

    ByteString decryptBlocking(ByteString ciphertext);

    byte[] decryptBlocking(byte[] ciphertext);

    RawSink decryptingSink(RawSink plaintext);

    RawSource decryptingSource(RawSource ciphertext);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.Decryptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Cipher.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ Object decrypt$suspendImpl(Decryptor decryptor, byte[] bArr, Continuation<? super byte[]> continuation) {
            return decryptor.decryptBlocking(bArr);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public static /* synthetic */ Object decrypt$suspendImpl(Decryptor decryptor, ByteString byteString, Continuation<? super ByteString> continuation) {
            AnonymousClass2 anonymousClass2;
            if (continuation instanceof AnonymousClass2) {
                anonymousClass2 = (AnonymousClass2) continuation;
                if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                    anonymousClass2.label -= Integer.MIN_VALUE;
                } else {
                    anonymousClass2 = decryptor.new AnonymousClass2(continuation);
                }
            } else {
                anonymousClass2 = decryptor.new AnonymousClass2(continuation);
            }
            Object objDecrypt = anonymousClass2.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = anonymousClass2.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objDecrypt);
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                byte[] data = byteString.getData();
                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(decryptor);
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(byteString);
                anonymousClass2.label = 1;
                objDecrypt = decryptor.decrypt(data, anonymousClass2);
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

        public static ByteString $default$decryptBlocking(Decryptor _this, ByteString ciphertext) {
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(_this.decryptBlocking(ciphertext.getData()));
        }
    }
}
