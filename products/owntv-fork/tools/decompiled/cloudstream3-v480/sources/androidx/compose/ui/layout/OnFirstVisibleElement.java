package androidx.compose.ui.layout;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OnFirstVisibleModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0016\u001a\u00020\u0002H\u0016J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0002H\u0016J\f\u0010\u0019\u001a\u00020\u000b*\u00020\u001aH\u0016J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0096\u0002J\b\u0010\u001f\u001a\u00020 H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Landroidx/compose/ui/layout/OnFirstVisibleElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/layout/OnFirstVisibleNode;", "minDurationMs", "", "minFractionVisible", "", "viewportBounds", "Landroidx/compose/ui/layout/LayoutBoundsHolder;", "callback", "Lkotlin/Function0;", "", "<init>", "(JFLandroidx/compose/ui/layout/LayoutBoundsHolder;Lkotlin/jvm/functions/Function0;)V", "getMinDurationMs", "()J", "getMinFractionVisible", "()F", "getViewportBounds", "()Landroidx/compose/ui/layout/LayoutBoundsHolder;", "getCallback", "()Lkotlin/jvm/functions/Function0;", "create", "update", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "equals", "", "other", "", "hashCode", "", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class OnFirstVisibleElement extends ModifierNodeElement<OnFirstVisibleNode> {
    private final Function0<Unit> callback;
    private final long minDurationMs;
    private final float minFractionVisible;
    private final LayoutBoundsHolder viewportBounds;

    public final long getMinDurationMs() {
        return this.minDurationMs;
    }

    public final float getMinFractionVisible() {
        return this.minFractionVisible;
    }

    public final LayoutBoundsHolder getViewportBounds() {
        return this.viewportBounds;
    }

    public final Function0<Unit> getCallback() {
        return this.callback;
    }

    public OnFirstVisibleElement(long j, float f, LayoutBoundsHolder layoutBoundsHolder, Function0<Unit> function0) {
        this.minDurationMs = j;
        this.minFractionVisible = f;
        this.viewportBounds = layoutBoundsHolder;
        this.callback = function0;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public OnFirstVisibleNode getNode() {
        return new OnFirstVisibleNode(this.minDurationMs, this.minFractionVisible, this.viewportBounds, this.callback);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(OnFirstVisibleNode node) {
        node.setMinDurationMs(this.minDurationMs);
        node.setMinFractionVisible(this.minFractionVisible);
        node.setCallback(this.callback);
        node.setViewportBounds(this.viewportBounds);
        node.forceUpdate();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("onFirstVisible");
        inspectorInfo.getProperties().set("minDurationMs", Long.valueOf(this.minDurationMs));
        inspectorInfo.getProperties().set("minFractionVisible", Float.valueOf(this.minFractionVisible));
        inspectorInfo.getProperties().set("viewportBounds", this.viewportBounds);
        inspectorInfo.getProperties().set("callback", this.callback);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && getClass() == other.getClass()) {
            OnFirstVisibleElement onFirstVisibleElement = (OnFirstVisibleElement) other;
            return this.minDurationMs == onFirstVisibleElement.minDurationMs && this.minFractionVisible == onFirstVisibleElement.minFractionVisible && Intrinsics.areEqual(this.viewportBounds, onFirstVisibleElement.viewportBounds) && this.callback == onFirstVisibleElement.callback;
        }
        return false;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        int iM = ((UByte$$ExternalSyntheticBackport0.m(this.minDurationMs) * 31) + Float.floatToIntBits(this.minFractionVisible)) * 31;
        LayoutBoundsHolder layoutBoundsHolder = this.viewportBounds;
        return ((iM + (layoutBoundsHolder != null ? layoutBoundsHolder.hashCode() : 0)) * 31) + this.callback.hashCode();
    }
}
