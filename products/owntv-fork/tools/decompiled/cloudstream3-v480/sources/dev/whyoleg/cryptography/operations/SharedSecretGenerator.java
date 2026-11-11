package dev.whyoleg.cryptography.operations;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: SharedSecretGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0017\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H\u0096À\u0004¢\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00028\u0000H\u0096À\u0004¢\u0006\u0002\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H¦\u0080\u0004¢\u0006\u0002\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/SharedSecretGenerator;", "K", "", "generateSharedSecretToByteArray", "", "other", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateSharedSecret", "Lkotlinx/io/bytestring/ByteString;", "generateSharedSecretToByteArrayBlocking", "(Ljava/lang/Object;)[B", "generateSharedSecretBlocking", "(Ljava/lang/Object;)Lkotlinx/io/bytestring/ByteString;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface SharedSecretGenerator<K> {

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.SharedSecretGenerator$generateSharedSecret$1, reason: invalid class name */
    /* JADX INFO: compiled from: SharedSecretGenerator.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "dev.whyoleg.cryptography.operations.SharedSecretGenerator", f = "SharedSecretGenerator.kt", i = {0, 0}, l = {34}, m = "generateSharedSecret$suspendImpl", n = {"$this", "other"}, nl = {56}, s = {"L$0", "L$1"}, v = 2)
    static final class AnonymousClass1<K> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SharedSecretGenerator<K> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SharedSecretGenerator<K> sharedSecretGenerator, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = sharedSecretGenerator;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CC.generateSharedSecret$suspendImpl(this.this$0, null, this);
        }
    }

    Object generateSharedSecret(K k, Continuation<? super ByteString> continuation);

    ByteString generateSharedSecretBlocking(K other);

    Object generateSharedSecretToByteArray(K k, Continuation<? super byte[]> continuation);

    byte[] generateSharedSecretToByteArrayBlocking(K other);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.SharedSecretGenerator$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SharedSecretGenerator.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ <K> Object generateSharedSecretToByteArray$suspendImpl(SharedSecretGenerator<K> sharedSecretGenerator, K k, Continuation<? super byte[]> continuation) {
            return sharedSecretGenerator.generateSharedSecretToByteArrayBlocking(k);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public static /* synthetic */ <K> Object generateSharedSecret$suspendImpl(SharedSecretGenerator<K> sharedSecretGenerator, K k, Continuation<? super ByteString> continuation) {
            AnonymousClass1 anonymousClass1;
            if (continuation instanceof AnonymousClass1) {
                anonymousClass1 = (AnonymousClass1) continuation;
                if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                    anonymousClass1.label -= Integer.MIN_VALUE;
                } else {
                    anonymousClass1 = new AnonymousClass1(sharedSecretGenerator, continuation);
                }
            } else {
                anonymousClass1 = new AnonymousClass1(sharedSecretGenerator, continuation);
            }
            Object objGenerateSharedSecretToByteArray = anonymousClass1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = anonymousClass1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objGenerateSharedSecretToByteArray);
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(sharedSecretGenerator);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(k);
                anonymousClass1.label = 1;
                objGenerateSharedSecretToByteArray = sharedSecretGenerator.generateSharedSecretToByteArray(k, anonymousClass1);
                if (objGenerateSharedSecretToByteArray == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Object obj = anonymousClass1.L$1;
                ResultKt.throwOnFailure(objGenerateSharedSecretToByteArray);
            }
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe((byte[]) objGenerateSharedSecretToByteArray);
        }
    }
}
