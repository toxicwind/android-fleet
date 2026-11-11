package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GuidanceJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0087@¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"runInterruptible", ExifInterface.GPS_DIRECTION_TRUE, "context", "Lkotlinx/coroutines/Job;", "block", "Lkotlin/Function0;", "(Lkotlinx/coroutines/Job;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class GuidanceJvmKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "Passing a Job to `runInterruptible` prevents it from being cancelled when the caller gets cancelled. This pattern should be avoided. This overload will be deprecated with an error in the future.")
    public static final <T> Object runInterruptible(Job job, Function0<? extends T> function0, Continuation<? super T> continuation) {
        Intrinsics.checkNotNull(job, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext");
        return InterruptibleKt.runInterruptible(job, function0, continuation);
    }
}
