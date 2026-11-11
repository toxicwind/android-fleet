package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: compiled from: Composer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000b¨\u0006\u0010"}, d2 = {"Landroidx/compose/runtime/GroupInfo;", "", "slotIndex", "", "nodeIndex", "nodeCount", "<init>", "(III)V", "getSlotIndex", "()I", "setSlotIndex", "(I)V", "getNodeIndex", "setNodeIndex", "getNodeCount", "setNodeCount", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class GroupInfo {
    private int nodeCount;
    private int nodeIndex;
    private int slotIndex;

    public GroupInfo(int i, int i2, int i3) {
        this.slotIndex = i;
        this.nodeIndex = i2;
        this.nodeCount = i3;
    }

    public final int getSlotIndex() {
        return this.slotIndex;
    }

    public final void setSlotIndex(int i) {
        this.slotIndex = i;
    }

    public final int getNodeIndex() {
        return this.nodeIndex;
    }

    public final void setNodeIndex(int i) {
        this.nodeIndex = i;
    }

    public final int getNodeCount() {
        return this.nodeCount;
    }

    public final void setNodeCount(int i) {
        this.nodeCount = i;
    }
}
