package kotlinx.atomicfu.locks;

import kotlin.Metadata;

/* JADX INFO: compiled from: ThreadParker.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/atomicfu/locks/Unparked;", "Lkotlinx/atomicfu/locks/ParkingState;", "<init>", "()V", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class Unparked implements ParkingState {
    public static final Unparked INSTANCE = new Unparked();

    private Unparked() {
    }
}
