package kotlinx.atomicfu.locks;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ThreadParker.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lkotlinx/atomicfu/locks/Parked;", "Lkotlinx/atomicfu/locks/ParkingState;", "data", "Ljava/lang/Thread;", "Lkotlinx/atomicfu/locks/ParkingData;", "<init>", "(Ljava/lang/Thread;)V", "getData", "()Ljava/lang/Thread;", "Ljava/lang/Thread;", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class Parked implements ParkingState {
    private final Thread data;

    public Parked(Thread data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }

    public final Thread getData() {
        return this.data;
    }
}
