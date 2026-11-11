package dev.whyoleg.cryptography.operations;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: Signature.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004J\u0017\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0096À\u0004¢\u0006\u0002\u0010\u0007J\u0017\u0010\u0004\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\bH\u0096À\u0004¢\u0006\u0002\u0010\tJ\u0017\u0010\u0004\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\nH\u0096À\u0004¢\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0096\u0080\u0004J\u0012\u0010\f\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\bH\u0096\u0080\u0004J\u0012\u0010\f\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\nH\u0096\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "", "createSignFunction", "Ldev/whyoleg/cryptography/operations/SignFunction;", "generateSignature", "", "data", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/bytestring/ByteString;", "(Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/RawSource;", "(Lkotlinx/io/RawSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateSignatureBlocking", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface SignatureGenerator {

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.SignatureGenerator$generateSignature$2, reason: invalid class name */
    /* JADX INFO: compiled from: Signature.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "dev.whyoleg.cryptography.operations.SignatureGenerator", f = "Signature.kt", i = {0, 0}, l = {43}, m = "generateSignature$suspendImpl", n = {"$this", "data"}, nl = {341}, s = {"L$0", "L$1"}, v = 2)
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
            return CC.generateSignature$suspendImpl(SignatureGenerator.this, (ByteString) null, this);
        }
    }

    SignFunction createSignFunction();

    Object generateSignature(RawSource rawSource, Continuation<? super ByteString> continuation);

    Object generateSignature(ByteString byteString, Continuation<? super ByteString> continuation);

    Object generateSignature(byte[] bArr, Continuation<? super byte[]> continuation);

    ByteString generateSignatureBlocking(RawSource data);

    ByteString generateSignatureBlocking(ByteString data);

    byte[] generateSignatureBlocking(byte[] data);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.SignatureGenerator$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Signature.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ Object generateSignature$suspendImpl(SignatureGenerator signatureGenerator, byte[] bArr, Continuation<? super byte[]> continuation) {
            return signatureGenerator.generateSignatureBlocking(bArr);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public static /* synthetic */ Object generateSignature$suspendImpl(SignatureGenerator signatureGenerator, ByteString byteString, Continuation<? super ByteString> continuation) {
            AnonymousClass2 anonymousClass2;
            if (continuation instanceof AnonymousClass2) {
                anonymousClass2 = (AnonymousClass2) continuation;
                if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                    anonymousClass2.label -= Integer.MIN_VALUE;
                } else {
                    anonymousClass2 = signatureGenerator.new AnonymousClass2(continuation);
                }
            } else {
                anonymousClass2 = signatureGenerator.new AnonymousClass2(continuation);
            }
            Object objGenerateSignature = anonymousClass2.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = anonymousClass2.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objGenerateSignature);
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                byte[] data = byteString.getData();
                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(signatureGenerator);
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(byteString);
                anonymousClass2.label = 1;
                objGenerateSignature = signatureGenerator.generateSignature(data, anonymousClass2);
                if (objGenerateSignature == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objGenerateSignature);
            }
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe((byte[]) objGenerateSignature);
        }

        public static /* synthetic */ Object generateSignature$suspendImpl(SignatureGenerator signatureGenerator, RawSource rawSource, Continuation<? super ByteString> continuation) {
            return signatureGenerator.generateSignatureBlocking(rawSource);
        }

        public static byte[] $default$generateSignatureBlocking(SignatureGenerator _this, byte[] data) {
            Intrinsics.checkNotNullParameter(data, "data");
            SignFunction signFunctionCreateSignFunction = _this.createSignFunction();
            try {
                SignFunction signFunction = signFunctionCreateSignFunction;
                UpdateFunction.CC.update$default(signFunction, data, 0, 0, 6, (Object) null);
                byte[] bArrSignToByteArray = signFunction.signToByteArray();
                AutoCloseableKt.closeFinally(signFunctionCreateSignFunction, null);
                return bArrSignToByteArray;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(signFunctionCreateSignFunction, th);
                    throw th2;
                }
            }
        }

        public static ByteString $default$generateSignatureBlocking(SignatureGenerator _this, RawSource data) {
            Intrinsics.checkNotNullParameter(data, "data");
            SignFunction signFunctionCreateSignFunction = _this.createSignFunction();
            try {
                SignFunction signFunction = signFunctionCreateSignFunction;
                signFunction.update(data);
                ByteString byteStringSign = signFunction.sign();
                AutoCloseableKt.closeFinally(signFunctionCreateSignFunction, null);
                return byteStringSign;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(signFunctionCreateSignFunction, th);
                    throw th2;
                }
            }
        }

        public static ByteString $default$generateSignatureBlocking(SignatureGenerator _this, ByteString data) {
            Intrinsics.checkNotNullParameter(data, "data");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(_this.generateSignatureBlocking(data.getData()));
        }
    }
}
