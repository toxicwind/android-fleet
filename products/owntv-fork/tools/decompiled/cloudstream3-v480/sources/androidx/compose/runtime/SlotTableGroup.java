package androidx.compose.runtime;

import androidx.compose.runtime.tooling.CompositionGroup;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010(\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010$H\u0096\u0002J\b\u0010)\u001a\u00020*H\u0002J\u0012\u0010+\u001a\u0004\u0018\u00010\u00012\u0006\u0010,\u001a\u00020\u0013H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0015R\u001c\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0015R\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u001eR\u0014\u0010%\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\rR\u0014\u0010'\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\r¨\u0006-"}, d2 = {"Landroidx/compose/runtime/SlotTableGroup;", "Landroidx/compose/runtime/tooling/CompositionGroup;", "", "table", "Landroidx/compose/runtime/SlotTable;", "group", "", "version", "<init>", "(Landroidx/compose/runtime/SlotTable;II)V", "getTable", "()Landroidx/compose/runtime/SlotTable;", "getGroup", "()I", "getVersion", "isEmpty", "", "()Z", "key", "", "getKey", "()Ljava/lang/Object;", "sourceInfo", "", "getSourceInfo", "()Ljava/lang/String;", "node", "getNode", "data", "getData", "()Ljava/lang/Iterable;", "identity", "getIdentity", "compositionGroups", "getCompositionGroups", "iterator", "", "groupSize", "getGroupSize", "slotsSize", "getSlotsSize", "validateRead", "", "find", "identityToFind", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class SlotTableGroup implements CompositionGroup, Iterable<CompositionGroup>, KMappedMarker {
    private final int group;
    private final SlotTable table;
    private final int version;

    public SlotTableGroup(SlotTable slotTable, int i, int i2) {
        this.table = slotTable;
        this.group = i;
        this.version = i2;
    }

    public final SlotTable getTable() {
        return this.table;
    }

    public final int getGroup() {
        return this.group;
    }

    public /* synthetic */ SlotTableGroup(SlotTable slotTable, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(slotTable, i, (i3 & 4) != 0 ? slotTable.getVersion() : i2);
    }

    public final int getVersion() {
        return this.version;
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public boolean isEmpty() {
        return SlotTableKt.groupSize(this.table.getGroups(), this.group) == 0;
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Object getKey() {
        int i = this.table.getGroups()[(this.group * 5) + 1] & 536870912;
        SlotTable slotTable = this.table;
        if (i != 0) {
            Object obj = slotTable.getSlots()[SlotTableKt.objectKeyIndex(this.table.getGroups(), this.group)];
            Intrinsics.checkNotNull(obj);
            return obj;
        }
        return Integer.valueOf(slotTable.getGroups()[this.group * 5]);
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public String getSourceInfo() {
        GroupSourceInformation groupSourceInformationSourceInformationOf = this.table.sourceInformationOf(this.group);
        if (groupSourceInformationSourceInformationOf != null) {
            return groupSourceInformationSourceInformationOf.getSourceInformation();
        }
        return null;
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Object getNode() {
        if ((this.table.getGroups()[(this.group * 5) + 1] & 1073741824) != 0) {
            return this.table.getSlots()[this.table.getGroups()[(this.group * 5) + 4]];
        }
        return null;
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Iterable<Object> getData() {
        GroupSourceInformation groupSourceInformationSourceInformationOf = this.table.sourceInformationOf(this.group);
        if (groupSourceInformationSourceInformationOf != null) {
            return new SourceInformationGroupDataIterator(this.table, this.group, groupSourceInformationSourceInformationOf);
        }
        return new DataIterator(this.table, this.group);
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Object getIdentity() {
        validateRead();
        SlotReader slotReaderOpenReader = this.table.openReader();
        try {
            return slotReaderOpenReader.anchor(this.group);
        } finally {
            slotReaderOpenReader.close();
        }
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public Iterable<CompositionGroup> getCompositionGroups() {
        return this;
    }

    @Override // java.lang.Iterable
    public Iterator<CompositionGroup> iterator() {
        validateRead();
        GroupSourceInformation groupSourceInformationSourceInformationOf = this.table.sourceInformationOf(this.group);
        if (groupSourceInformationSourceInformationOf != null) {
            return new SourceInformationGroupIterator(this.table, this.group, groupSourceInformationSourceInformationOf, new AnchoredGroupPath(this.group));
        }
        SlotTable slotTable = this.table;
        int i = this.group;
        return new GroupIterator(slotTable, i + 1, i + SlotTableKt.groupSize(slotTable.getGroups(), this.group));
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public int getGroupSize() {
        return SlotTableKt.groupSize(this.table.getGroups(), this.group);
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public int getSlotsSize() {
        int slotsSize;
        int groupSize = this.group + getGroupSize();
        int groupsSize = this.table.getGroupsSize();
        SlotTable slotTable = this.table;
        if (groupSize >= groupsSize) {
            slotsSize = slotTable.getSlotsSize();
        } else {
            slotsSize = slotTable.getGroups()[(groupSize * 5) + 4];
        }
        return slotsSize - this.table.getGroups()[(this.group * 5) + 4];
    }

    private final void validateRead() {
        if (this.table.getVersion() != this.version) {
            SlotTableKt.throwConcurrentModificationException();
        }
    }

    private static final CompositionGroup find$findAnchoredGroup(SlotTableGroup slotTableGroup, Anchor anchor) {
        int iAnchorIndex;
        int i;
        if (!slotTableGroup.table.ownsAnchor(anchor) || (iAnchorIndex = slotTableGroup.table.anchorIndex(anchor)) < (i = slotTableGroup.group) || iAnchorIndex - i >= SlotTableKt.groupSize(slotTableGroup.table.getGroups(), slotTableGroup.group)) {
            return null;
        }
        return new SlotTableGroup(slotTableGroup.table, iAnchorIndex, slotTableGroup.version);
    }

    private static final CompositionGroup find$findRelativeGroup(CompositionGroup compositionGroup, int i) {
        return (CompositionGroup) CollectionsKt.firstOrNull(CollectionsKt.drop(compositionGroup.getCompositionGroups(), i));
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public CompositionGroup find(Object identityToFind) {
        if (identityToFind instanceof Anchor) {
            return find$findAnchoredGroup(this, (Anchor) identityToFind);
        }
        if (identityToFind instanceof SourceInformationSlotTableGroupIdentity) {
            SourceInformationSlotTableGroupIdentity sourceInformationSlotTableGroupIdentity = (SourceInformationSlotTableGroupIdentity) identityToFind;
            CompositionGroup compositionGroupFind = find(sourceInformationSlotTableGroupIdentity.getParentIdentity());
            if (compositionGroupFind != null) {
                return find$findRelativeGroup(compositionGroupFind, sourceInformationSlotTableGroupIdentity.getIndex());
            }
        }
        return null;
    }
}
