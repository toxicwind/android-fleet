package io.ktor.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: CoroutinesUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0019\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/Job;", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "printDebugTree", "(Lkotlinx/coroutines/Job;I)V", "parent", "Lkotlin/coroutines/CoroutineContext;", "SilentSupervisor", "(Lkotlinx/coroutines/Job;)Lkotlin/coroutines/CoroutineContext;", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CoroutinesUtilsKt {
    public static /* synthetic */ void printDebugTree$default(Job job, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        printDebugTree(job, i);
    }

    public static final void printDebugTree(Job job, int i) {
        Intrinsics.checkNotNullParameter(job, "<this>");
        System.out.println((Object) (StringsKt.repeat(Stream.ID_UNKNOWN, i) + job));
        Iterator<Job> it = job.getChildren().iterator();
        while (it.hasNext()) {
            printDebugTree(it.next(), i + 2);
        }
        if (i == 0) {
            System.out.println();
        }
    }

    public static /* synthetic */ CoroutineContext SilentSupervisor$default(Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return SilentSupervisor(job);
    }

    public static final CoroutineContext SilentSupervisor(Job job) {
        return SupervisorKt.SupervisorJob(job).plus(new CoroutinesUtilsKt$SilentSupervisor$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE));
    }
}
