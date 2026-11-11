package dev.whyoleg.cryptography.operations;

import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: SecretDerivation.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0096À\u0004¢\u0006\u0002\u0010\u0005J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0006H\u0096À\u0004¢\u0006\u0002\u0010\u0007J\u0017\u0010\b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0003H\u0096À\u0004¢\u0006\u0002\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0006H\u0096À\u0004¢\u0006\u0002\u0010\u0007J\u0012\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H¦\u0080\u0004J\u0012\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0006H\u0096\u0080\u0004J\u0012\u0010\n\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0003H\u0096\u0080\u0004J\u0012\u0010\n\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0006H\u0096\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/SecretDerivation;", "", "deriveSecretToByteArray", "", TvContractCompat.PARAM_INPUT, "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/bytestring/ByteString;", "(Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deriveSecret", "deriveSecretToByteArrayBlocking", "deriveSecretBlocking", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface SecretDerivation {

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.SecretDerivation$deriveSecret$1, reason: invalid class name */
    /* JADX INFO: compiled from: SecretDerivation.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "dev.whyoleg.cryptography.operations.SecretDerivation", f = "SecretDerivation.kt", i = {0, 0}, l = {44}, m = "deriveSecret$suspendImpl", n = {"$this", TvContractCompat.PARAM_INPUT}, nl = {96}, s = {"L$0", "L$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CC.deriveSecret$suspendImpl(SecretDerivation.this, (byte[]) null, this);
        }
    }

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.SecretDerivation$deriveSecret$2, reason: invalid class name */
    /* JADX INFO: compiled from: SecretDerivation.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "dev.whyoleg.cryptography.operations.SecretDerivation", f = "SecretDerivation.kt", i = {0, 0}, l = {54}, m = "deriveSecret$suspendImpl", n = {"$this", TvContractCompat.PARAM_INPUT}, nl = {98}, s = {"L$0", "L$1"}, v = 2)
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
            return CC.deriveSecret$suspendImpl(SecretDerivation.this, (ByteString) null, this);
        }
    }

    Object deriveSecret(ByteString byteString, Continuation<? super ByteString> continuation);

    Object deriveSecret(byte[] bArr, Continuation<? super ByteString> continuation);

    ByteString deriveSecretBlocking(ByteString input);

    ByteString deriveSecretBlocking(byte[] input);

    Object deriveSecretToByteArray(ByteString byteString, Continuation<? super byte[]> continuation);

    Object deriveSecretToByteArray(byte[] bArr, Continuation<? super byte[]> continuation);

    byte[] deriveSecretToByteArrayBlocking(ByteString input);

    byte[] deriveSecretToByteArrayBlocking(byte[] input);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.SecretDerivation$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SecretDerivation.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ Object deriveSecretToByteArray$suspendImpl(SecretDerivation secretDerivation, byte[] bArr, Continuation<? super byte[]> continuation) {
            return secretDerivation.deriveSecretToByteArrayBlocking(bArr);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public static /* synthetic */ Object deriveSecret$suspendImpl(SecretDerivation secretDerivation, byte[] bArr, Continuation<? super ByteString> continuation) {
            AnonymousClass1 anonymousClass1;
            if (continuation instanceof AnonymousClass1) {
                anonymousClass1 = (AnonymousClass1) continuation;
                if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                    anonymousClass1.label -= Integer.MIN_VALUE;
                } else {
                    anonymousClass1 = secretDerivation.new AnonymousClass1(continuation);
                }
            } else {
                anonymousClass1 = secretDerivation.new AnonymousClass1(continuation);
            }
            Object objDeriveSecretToByteArray = anonymousClass1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = anonymousClass1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objDeriveSecretToByteArray);
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(secretDerivation);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(bArr);
                anonymousClass1.label = 1;
                objDeriveSecretToByteArray = secretDerivation.deriveSecretToByteArray(bArr, anonymousClass1);
                if (objDeriveSecretToByteArray == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objDeriveSecretToByteArray);
            }
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe((byte[]) objDeriveSecretToByteArray);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public static /* synthetic */ Object deriveSecret$suspendImpl(SecretDerivation secretDerivation, ByteString byteString, Continuation<? super ByteString> continuation) {
            AnonymousClass2 anonymousClass2;
            if (continuation instanceof AnonymousClass2) {
                anonymousClass2 = (AnonymousClass2) continuation;
                if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                    anonymousClass2.label -= Integer.MIN_VALUE;
                } else {
                    anonymousClass2 = secretDerivation.new AnonymousClass2(continuation);
                }
            } else {
                anonymousClass2 = secretDerivation.new AnonymousClass2(continuation);
            }
            Object objDeriveSecretToByteArray = anonymousClass2.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = anonymousClass2.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objDeriveSecretToByteArray);
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                byte[] data = byteString.getData();
                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(secretDerivation);
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(byteString);
                anonymousClass2.label = 1;
                objDeriveSecretToByteArray = secretDerivation.deriveSecretToByteArray(data, anonymousClass2);
                if (objDeriveSecretToByteArray == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objDeriveSecretToByteArray);
            }
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe((byte[]) objDeriveSecretToByteArray);
        }

        public static ByteString $default$deriveSecretBlocking(SecretDerivation _this, byte[] input) {
            Intrinsics.checkNotNullParameter(input, "input");
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(_this.deriveSecretToByteArrayBlocking(input));
        }

        public static /* synthetic */ Object deriveSecretToByteArray$suspendImpl(SecretDerivation secretDerivation, ByteString byteString, Continuation<? super byte[]> continuation) {
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return secretDerivation.deriveSecretToByteArray(byteString.getData(), continuation);
        }

        public static byte[] $default$deriveSecretToByteArrayBlocking(SecretDerivation _this, ByteString input) {
            Intrinsics.checkNotNullParameter(input, "input");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return _this.deriveSecretToByteArrayBlocking(input.getData());
        }

        public static ByteString $default$deriveSecretBlocking(SecretDerivation _this, ByteString input) {
            Intrinsics.checkNotNullParameter(input, "input");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(_this.deriveSecretToByteArrayBlocking(input.getData()));
        }
    }
}
