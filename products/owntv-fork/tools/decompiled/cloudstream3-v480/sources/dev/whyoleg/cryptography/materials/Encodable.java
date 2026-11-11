package dev.whyoleg.cryptography.materials;

import dev.whyoleg.cryptography.materials.EncodingFormat;
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

/* JADX INFO: compiled from: Encodable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J\u0017\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H\u0096À\u0004¢\u0006\u0002\u0010\u0007J\u0017\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H¦\u0080\u0004¢\u0006\u0002\u0010\tJ\u0017\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00028\u0000H\u0096À\u0004¢\u0006\u0002\u0010\u0007J\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/materials/Encodable;", "F", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "", "encodeToByteArray", "", "format", "(Ldev/whyoleg/cryptography/materials/EncodingFormat;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "encodeToByteArrayBlocking", "(Ldev/whyoleg/cryptography/materials/EncodingFormat;)[B", "encodeToByteString", "Lkotlinx/io/bytestring/ByteString;", "encodeToByteStringBlocking", "(Ldev/whyoleg/cryptography/materials/EncodingFormat;)Lkotlinx/io/bytestring/ByteString;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface Encodable<F extends EncodingFormat> {

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.materials.Encodable$encodeToByteString$1, reason: invalid class name */
    /* JADX INFO: compiled from: Encodable.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "dev.whyoleg.cryptography.materials.Encodable", f = "Encodable.kt", i = {0, 0}, l = {40}, m = "encodeToByteString$suspendImpl", n = {"$this", "format"}, nl = {53}, s = {"L$0", "L$1"}, v = 2)
    static final class AnonymousClass1<F extends EncodingFormat> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ Encodable<F> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Encodable<F> encodable, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = encodable;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CC.encodeToByteString$suspendImpl(this.this$0, null, this);
        }
    }

    Object encodeToByteArray(F f, Continuation<? super byte[]> continuation);

    byte[] encodeToByteArrayBlocking(F format);

    Object encodeToByteString(F f, Continuation<? super ByteString> continuation);

    ByteString encodeToByteStringBlocking(F format);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.materials.Encodable$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Encodable.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ <F extends EncodingFormat> Object encodeToByteArray$suspendImpl(Encodable<F> encodable, F f, Continuation<? super byte[]> continuation) {
            return encodable.encodeToByteArrayBlocking(f);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public static /* synthetic */ <F extends EncodingFormat> Object encodeToByteString$suspendImpl(Encodable<F> encodable, F f, Continuation<? super ByteString> continuation) {
            AnonymousClass1 anonymousClass1;
            if (continuation instanceof AnonymousClass1) {
                anonymousClass1 = (AnonymousClass1) continuation;
                if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                    anonymousClass1.label -= Integer.MIN_VALUE;
                } else {
                    anonymousClass1 = new AnonymousClass1(encodable, continuation);
                }
            } else {
                anonymousClass1 = new AnonymousClass1(encodable, continuation);
            }
            Object objEncodeToByteArray = anonymousClass1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = anonymousClass1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objEncodeToByteArray);
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(encodable);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(f);
                anonymousClass1.label = 1;
                objEncodeToByteArray = encodable.encodeToByteArray(f, anonymousClass1);
                if (objEncodeToByteArray == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objEncodeToByteArray);
            }
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe((byte[]) objEncodeToByteArray);
        }

        public static ByteString $default$encodeToByteStringBlocking(Encodable _this, EncodingFormat format) {
            Intrinsics.checkNotNullParameter(format, "format");
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(_this.encodeToByteArrayBlocking(format));
        }
    }
}
