package kotlinx.io;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SegmentPool.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \r2\u00020\u0001:\u0001\rB\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\n\u0010\f\u001a\u00020\u0007H\u0096\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0006\u001a\u00020\u00078VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlinx/io/RefCountingCopyTracker;", "Lkotlinx/io/SegmentCopyTracker;", "<init>", "()V", "copyCount", "", "shared", "", "getShared", "()Z", "addCopy", "", "removeCopy", "Companion", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RefCountingCopyTracker extends SegmentCopyTracker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AtomicIntegerFieldUpdater<RefCountingCopyTracker> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(RefCountingCopyTracker.class, "copyCount");
    private volatile int copyCount;

    /* JADX INFO: compiled from: SegmentPool.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R;\u0010\u0004\u001a&\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006 \u0007*\u0012\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\u00050\u00058\u0002X\u0083\u0084\b¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003¨\u0006\t"}, d2 = {"Lkotlinx/io/RefCountingCopyTracker$Companion;", "", "<init>", "()V", "fieldUpdater", "Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;", "Lkotlinx/io/RefCountingCopyTracker;", "kotlin.jvm.PlatformType", "getFieldUpdater$annotations", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        private static /* synthetic */ void getFieldUpdater$annotations() {
        }

        private Companion() {
        }
    }

    @Override // kotlinx.io.SegmentCopyTracker
    public boolean getShared() {
        return this.copyCount > 0;
    }

    @Override // kotlinx.io.SegmentCopyTracker
    public void addCopy() {
        fieldUpdater.incrementAndGet(this);
    }

    @Override // kotlinx.io.SegmentCopyTracker
    public boolean removeCopy() {
        if (this.copyCount == 0) {
            return false;
        }
        int iDecrementAndGet = fieldUpdater.decrementAndGet(this);
        if (iDecrementAndGet >= 0) {
            return true;
        }
        if (iDecrementAndGet != -1) {
            throw new IllegalStateException(("Shared copies count is negative: " + (iDecrementAndGet + 1)).toString());
        }
        this.copyCount = 0;
        return false;
    }
}
