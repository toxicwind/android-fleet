package androidx.compose.animation;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SharedTransitionScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u000b\u0010\f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J%\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\b\u0010\u000f\u001a\u00020\u0002H\u0016J\u0013\u0010\u0010\u001a\u00020\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0002H\u0016J\f\u0010\u001a\u001a\u00020\u0018*\u00020\u001bH\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\tR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001c"}, d2 = {"Landroidx/compose/animation/SkipToLookaheadElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/animation/SkipToLookaheadNode;", "scaleToBounds", "Landroidx/compose/animation/ScaleToBoundsImpl;", "isEnabled", "Lkotlin/Function0;", "", "(Landroidx/compose/animation/ScaleToBoundsImpl;Lkotlin/jvm/functions/Function0;)V", "()Lkotlin/jvm/functions/Function0;", "getScaleToBounds", "()Landroidx/compose/animation/ScaleToBoundsImpl;", "component1", "component2", "copy", "create", "equals", "other", "", "hashCode", "", "toString", "", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class SkipToLookaheadElement extends ModifierNodeElement<SkipToLookaheadNode> {
    private final Function0<Boolean> isEnabled;
    private final ScaleToBoundsImpl scaleToBounds;

    /* JADX WARN: Multi-variable type inference failed */
    public SkipToLookaheadElement() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SkipToLookaheadElement copy$default(SkipToLookaheadElement skipToLookaheadElement, ScaleToBoundsImpl scaleToBoundsImpl, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            scaleToBoundsImpl = skipToLookaheadElement.scaleToBounds;
        }
        if ((i & 2) != 0) {
            function0 = skipToLookaheadElement.isEnabled;
        }
        return skipToLookaheadElement.copy(scaleToBoundsImpl, function0);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ScaleToBoundsImpl getScaleToBounds() {
        return this.scaleToBounds;
    }

    public final Function0<Boolean> component2() {
        return this.isEnabled;
    }

    public final SkipToLookaheadElement copy(ScaleToBoundsImpl scaleToBounds, Function0<Boolean> isEnabled) {
        return new SkipToLookaheadElement(scaleToBounds, isEnabled);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SkipToLookaheadElement)) {
            return false;
        }
        SkipToLookaheadElement skipToLookaheadElement = (SkipToLookaheadElement) other;
        return Intrinsics.areEqual(this.scaleToBounds, skipToLookaheadElement.scaleToBounds) && Intrinsics.areEqual(this.isEnabled, skipToLookaheadElement.isEnabled);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        ScaleToBoundsImpl scaleToBoundsImpl = this.scaleToBounds;
        return ((scaleToBoundsImpl == null ? 0 : scaleToBoundsImpl.hashCode()) * 31) + this.isEnabled.hashCode();
    }

    public String toString() {
        return "SkipToLookaheadElement(scaleToBounds=" + this.scaleToBounds + ", isEnabled=" + this.isEnabled + ')';
    }

    public final ScaleToBoundsImpl getScaleToBounds() {
        return this.scaleToBounds;
    }

    public /* synthetic */ SkipToLookaheadElement(ScaleToBoundsImpl scaleToBoundsImpl, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : scaleToBoundsImpl, (i & 2) != 0 ? SharedTransitionScopeKt.DefaultEnabled : function0);
    }

    public final Function0<Boolean> isEnabled() {
        return this.isEnabled;
    }

    public SkipToLookaheadElement(ScaleToBoundsImpl scaleToBoundsImpl, Function0<Boolean> function0) {
        this.scaleToBounds = scaleToBoundsImpl;
        this.isEnabled = function0;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public SkipToLookaheadNode getNode() {
        return new SkipToLookaheadNode(this.scaleToBounds, this.isEnabled);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(SkipToLookaheadNode node) {
        node.setScaleToBounds(this.scaleToBounds);
        node.setEnabled(this.isEnabled);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("skipToLookahead");
        inspectorInfo.getProperties().set("scaleToBounds", this.scaleToBounds);
        inspectorInfo.getProperties().set("isEnabled", this.isEnabled);
    }
}
