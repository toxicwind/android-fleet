package kotlinx.io;

import kotlin.Metadata;

/* JADX INFO: compiled from: Segment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\n\u0010\n\u001a\u00020\u0005H\u0096\u0080\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lkotlinx/io/AlwaysSharedCopyTracker;", "Lkotlinx/io/SegmentCopyTracker;", "<init>", "()V", "shared", "", "getShared", "()Z", "addCopy", "", "removeCopy", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AlwaysSharedCopyTracker extends SegmentCopyTracker {
    public static final AlwaysSharedCopyTracker INSTANCE = new AlwaysSharedCopyTracker();

    @Override // kotlinx.io.SegmentCopyTracker
    public void addCopy() {
    }

    @Override // kotlinx.io.SegmentCopyTracker
    public boolean getShared() {
        return true;
    }

    @Override // kotlinx.io.SegmentCopyTracker
    public boolean removeCopy() {
        return true;
    }

    private AlwaysSharedCopyTracker() {
    }
}
