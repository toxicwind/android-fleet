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

/* JADX INFO: compiled from: Hash.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004J\u0017\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0096À\u0004¢\u0006\u0002\u0010\u0007J\u0017\u0010\u0004\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\bH\u0096À\u0004¢\u0006\u0002\u0010\tJ\u0017\u0010\u0004\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\nH\u0096À\u0004¢\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0096\u0080\u0004J\u0012\u0010\f\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\bH\u0096\u0080\u0004J\u0012\u0010\f\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\nH\u0096\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/Hasher;", "", "createHashFunction", "Ldev/whyoleg/cryptography/operations/HashFunction;", "hash", "", "data", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/bytestring/ByteString;", "(Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/RawSource;", "(Lkotlinx/io/RawSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hashBlocking", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface Hasher {

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.Hasher$hash$2, reason: invalid class name */
    /* JADX INFO: compiled from: Hash.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "dev.whyoleg.cryptography.operations.Hasher", f = "Hash.kt", i = {0, 0}, l = {41}, m = "hash$suspendImpl", n = {"$this", "data"}, nl = {120}, s = {"L$0", "L$1"}, v = 2)
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
            return CC.hash$suspendImpl(Hasher.this, (ByteString) null, this);
        }
    }

    HashFunction createHashFunction();

    Object hash(RawSource rawSource, Continuation<? super ByteString> continuation);

    Object hash(ByteString byteString, Continuation<? super ByteString> continuation);

    Object hash(byte[] bArr, Continuation<? super byte[]> continuation);

    ByteString hashBlocking(RawSource data);

    ByteString hashBlocking(ByteString data);

    byte[] hashBlocking(byte[] data);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.Hasher$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Hash.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ Object hash$suspendImpl(Hasher hasher, byte[] bArr, Continuation<? super byte[]> continuation) {
            return hasher.hashBlocking(bArr);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public static /* synthetic */ Object hash$suspendImpl(Hasher hasher, ByteString byteString, Continuation<? super ByteString> continuation) {
            AnonymousClass2 anonymousClass2;
            if (continuation instanceof AnonymousClass2) {
                anonymousClass2 = (AnonymousClass2) continuation;
                if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                    anonymousClass2.label -= Integer.MIN_VALUE;
                } else {
                    anonymousClass2 = hasher.new AnonymousClass2(continuation);
                }
            } else {
                anonymousClass2 = hasher.new AnonymousClass2(continuation);
            }
            Object objHash = anonymousClass2.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = anonymousClass2.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objHash);
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                byte[] backingArrayReference = byteString.getData();
                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(hasher);
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(byteString);
                anonymousClass2.label = 1;
                objHash = hasher.hash(backingArrayReference, anonymousClass2);
                if (objHash == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objHash);
            }
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe((byte[]) objHash);
        }

        public static /* synthetic */ Object hash$suspendImpl(Hasher hasher, RawSource rawSource, Continuation<? super ByteString> continuation) {
            return hasher.hashBlocking(rawSource);
        }

        public static byte[] $default$hashBlocking(Hasher _this, byte[] data) {
            Intrinsics.checkNotNullParameter(data, "data");
            HashFunction hashFunctionCreateHashFunction = _this.createHashFunction();
            try {
                HashFunction hashFunction = hashFunctionCreateHashFunction;
                UpdateFunction.CC.update$default(hashFunction, data, 0, 0, 6, (Object) null);
                byte[] bArrHashToByteArray = hashFunction.hashToByteArray();
                AutoCloseableKt.closeFinally(hashFunctionCreateHashFunction, null);
                return bArrHashToByteArray;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(hashFunctionCreateHashFunction, th);
                    throw th2;
                }
            }
        }

        public static ByteString $default$hashBlocking(Hasher _this, RawSource data) {
            Intrinsics.checkNotNullParameter(data, "data");
            HashFunction hashFunctionCreateHashFunction = _this.createHashFunction();
            try {
                HashFunction hashFunction = hashFunctionCreateHashFunction;
                hashFunction.update(data);
                ByteString byteStringHash = hashFunction.hash();
                AutoCloseableKt.closeFinally(hashFunctionCreateHashFunction, null);
                return byteStringHash;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(hashFunctionCreateHashFunction, th);
                    throw th2;
                }
            }
        }

        public static ByteString $default$hashBlocking(Hasher _this, ByteString data) {
            Intrinsics.checkNotNullParameter(data, "data");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(_this.hashBlocking(data.getData()));
        }
    }
}
