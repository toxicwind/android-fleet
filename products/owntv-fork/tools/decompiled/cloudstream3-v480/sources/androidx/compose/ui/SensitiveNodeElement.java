package androidx.compose.ui;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;

/* JADX INFO: compiled from: SensitiveContent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\b\u001a\u00020\u0002H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\f\u0010\f\u001a\u00020\n*\u00020\rH\u0016J\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0007¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/SensitiveNodeElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/SensitiveContentNode;", "isContentSensitive", "", "<init>", "(Z)V", "()Z", "create", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
final /* data */ class SensitiveNodeElement extends ModifierNodeElement<SensitiveContentNode> {
    private final boolean isContentSensitive;

    public static /* synthetic */ SensitiveNodeElement copy$default(SensitiveNodeElement sensitiveNodeElement, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = sensitiveNodeElement.isContentSensitive;
        }
        return sensitiveNodeElement.copy(z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsContentSensitive() {
        return this.isContentSensitive;
    }

    public final SensitiveNodeElement copy(boolean isContentSensitive) {
        return new SensitiveNodeElement(isContentSensitive);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SensitiveNodeElement) && this.isContentSensitive == ((SensitiveNodeElement) other).isContentSensitive;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return UByte$$ExternalSyntheticBackport0.m(this.isContentSensitive);
    }

    public String toString() {
        return "SensitiveNodeElement(isContentSensitive=" + this.isContentSensitive + ')';
    }

    public final boolean isContentSensitive() {
        return this.isContentSensitive;
    }

    public SensitiveNodeElement(boolean z) {
        this.isContentSensitive = z;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public SensitiveContentNode create() {
        return new SensitiveContentNode(this.isContentSensitive);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(SensitiveContentNode node) {
        node.setContentSensitive(this.isContentSensitive);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("sensitiveContent");
        inspectorInfo.getProperties().set("isContentSensitive", Boolean.valueOf(this.isContentSensitive));
    }
}
