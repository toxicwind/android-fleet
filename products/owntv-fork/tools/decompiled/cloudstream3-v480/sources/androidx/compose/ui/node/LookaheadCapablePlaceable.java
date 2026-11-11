package androidx.compose.ui.node;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.PlaceableKt;
import androidx.compose.ui.layout.Ruler;
import androidx.compose.ui.layout.RulerScope;
import androidx.compose.ui.layout.VerticalAlignmentLine;
import androidx.compose.ui.layout.VerticalRuler;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.FontScaling;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: LookaheadDelegate.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\b!\u0018\u0000 p2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002opB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020\u0010H\u0016J\u0011\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0086\u0002J\u0010\u00100\u001a\u00020-2\u0006\u0010.\u001a\u00020/H&J\r\u00108\u001a\u00020 H ¢\u0006\u0002\b9J\f\u0010E\u001a\u00020 *\u00020FH\u0004J\u0016\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020L2\u0006\u0010R\u001a\u00020PJ\u0018\u0010S\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020LH\u0002J\u0010\u0010T\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020LH\u0002J\u0014\u0010U\u001a\u00020\u0010*\u00020\u00142\u0006\u0010V\u001a\u00020\u0014H\u0002J\u0015\u0010W\u001a\u00020 2\u0006\u0010Q\u001a\u00020LH\u0000¢\u0006\u0002\bXJ`\u0010Y\u001a\u0002052\u0006\u0010Z\u001a\u00020-2\u0006\u0010[\u001a\u00020-2\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020-0]2\u0019\u0010^\u001a\u0015\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u001e¢\u0006\u0002\b!2\u0017\u0010_\u001a\u0013\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020 0\u001e¢\u0006\u0002\b!H\u0016J\u0017\u0010`\u001a\u00020 2\b\u0010a\u001a\u0004\u0018\u000105H\u0000¢\u0006\u0002\bbJ+\u0010c\u001a\u00020 2\u0006\u0010d\u001a\u00020#2\b\b\u0002\u0010e\u001a\u00020\u00072\b\b\u0002\u0010f\u001a\u00020gH\u0002¢\u0006\u0004\bh\u0010iJ\u0010\u0010`\u001a\u00020 2\u0006\u0010d\u001a\u00020#H\u0002J\u001c\u0010j\u001a\u00020 2\u0012\u0010k\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140N0MH\u0002J\u0016\u0010l\u001a\u00020 2\u0006\u0010Q\u001a\u00020L2\u0006\u0010m\u001a\u00020PJ\u0016\u0010n\u001a\u00020 2\u0006\u0010Q\u001a\u00020L2\u0006\u0010m\u001a\u00020PR\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u0004\u0018\u00010\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0018\u00010\u001cR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\u001d\u001a\u0015\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u001e¢\u0006\u0002\b!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u00020\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0012\"\u0004\b%\u0010&R\u0018\u0010)\u001a\u00060\u001cR\u00020\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u001a\u00101\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0012\"\u0004\b3\u0010&R\u0012\u00104\u001a\u000205X \u0004¢\u0006\u0006\u001a\u0004\b6\u00107R\u0012\u0010:\u001a\u00020;X¦\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R\u001a\u0010>\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u0012\"\u0004\b@\u0010&R\u0011\u0010A\u001a\u00020B¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0014\u0010G\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010\u0012R\u0010\u0010H\u001a\u0004\u0018\u00010IX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010J\u001a\u001c\u0012\u0004\u0012\u00020L\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140N0M\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006q"}, d2 = {"Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "Landroidx/compose/ui/layout/Placeable;", "Landroidx/compose/ui/node/MeasureScopeWithLayoutNode;", "Landroidx/compose/ui/node/MotionReferencePlacementDelegate;", "<init>", "()V", "position", "Landroidx/compose/ui/unit/IntOffset;", "getPosition-nOcc-ac", "()J", "child", "getChild", "()Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "parent", "getParent", "hasMeasureResult", "", "getHasMeasureResult", "()Z", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "_rulerScope", "Landroidx/compose/ui/node/LookaheadCapablePlaceable$ResettableRulerScope;", "rulersLambda", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/RulerScope;", "", "Lkotlin/ExtensionFunctionType;", "cachedRulerPlaceableResult", "Landroidx/compose/ui/node/PlaceableResult;", "isPlacedUnderMotionFrameOfReference", "setPlacedUnderMotionFrameOfReference", "(Z)V", "updatePlacedUnderMotionFrameOfReference", "newMFR", "rulerScope", "getRulerScope", "()Landroidx/compose/ui/node/LookaheadCapablePlaceable$ResettableRulerScope;", "get", "", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "calculateAlignmentLine", "isShallowPlacing", "isShallowPlacing$ui_release", "setShallowPlacing$ui_release", "measureResult", "Landroidx/compose/ui/layout/MeasureResult;", "getMeasureResult$ui_release", "()Landroidx/compose/ui/layout/MeasureResult;", "replace", "replace$ui_release", "alignmentLinesOwner", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "getAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "isPlacingForAlignment", "isPlacingForAlignment$ui_release", "setPlacingForAlignment$ui_release", "placementScope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "getPlacementScope", "()Landroidx/compose/ui/layout/Placeable$PlacementScope;", "invalidateAlignmentLinesFromPositionChange", "Landroidx/compose/ui/node/NodeCoordinator;", "isLookingAhead", "rulerValues", "Landroidx/compose/ui/node/RulerTrackingMap;", "rulerReaders", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/ui/layout/Ruler;", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/ui/node/WeakReference;", "findRulerValue", "", "ruler", "defaultValue", "addRulerReader", "findAncestorRulerDefiner", "isLayoutNodeAncestor", "ancestor", "invalidateChildrenOfDefiningRuler", "invalidateChildrenOfDefiningRuler$ui_release", TtmlNode.TAG_LAYOUT, "width", "height", "alignmentLines", "", "rulers", "placementBlock", "captureRulersIfNeeded", "result", "captureRulersIfNeeded$ui_release", "captureRulers", "placeableResult", "positionOnScreen", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/unit/IntSize;", "captureRulers-OSxE8f4", "(Landroidx/compose/ui/node/PlaceableResult;JJ)V", "notifyRulerValueChange", "layoutNodes", "provideRulerValue", ES6Iterator.VALUE_PROPERTY, "provideRelativeRulerValue", "ResettableRulerScope", "Companion", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class LookaheadCapablePlaceable extends Placeable implements MeasureScopeWithLayoutNode, MotionReferencePlacementDelegate {
    public static final int $stable = 0;
    private static final Function1<PlaceableResult, Unit> onCommitAffectingRuler = new Function1<PlaceableResult, Unit>() { // from class: androidx.compose.ui.node.LookaheadCapablePlaceable$Companion$onCommitAffectingRuler$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PlaceableResult placeableResult) {
            invoke2(placeableResult);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(PlaceableResult placeableResult) {
            if (placeableResult.isValidOwnerScope()) {
                placeableResult.getPlaceable().captureRulersIfNeeded(placeableResult);
            }
        }
    };
    private ResettableRulerScope _rulerScope;
    private PlaceableResult cachedRulerPlaceableResult;
    private boolean isPlacedUnderMotionFrameOfReference;
    private boolean isPlacingForAlignment;
    private boolean isShallowPlacing;
    private final Placeable.PlacementScope placementScope = PlaceableKt.PlacementScope(this);
    private MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> rulerReaders;
    private RulerTrackingMap rulerValues;
    private Function1<? super RulerScope, Unit> rulersLambda;

    public abstract int calculateAlignmentLine(AlignmentLine alignmentLine);

    public abstract AlignmentLinesOwner getAlignmentLinesOwner();

    public abstract LookaheadCapablePlaceable getChild();

    public abstract LayoutCoordinates getCoordinates();

    public abstract boolean getHasMeasureResult();

    @Override // androidx.compose.ui.node.MeasureScopeWithLayoutNode
    public abstract LayoutNode getLayoutNode();

    public abstract MeasureResult getMeasureResult$ui_release();

    public abstract LookaheadCapablePlaceable getParent();

    /* JADX INFO: renamed from: getPosition-nOcc-ac, reason: not valid java name */
    public abstract long getPosition();

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public boolean isLookingAhead() {
        return false;
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public /* synthetic */ MeasureResult layout(int i, int i2, Map map, Function1 function1) {
        return MeasureScope.CC.$default$layout(this, i, i2, map, function1);
    }

    public abstract void replace$ui_release();

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    public /* synthetic */ int mo399roundToPxR2X_6o(long j) {
        return Density.CC.m6854$default$roundToPxR2X_6o(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public /* synthetic */ int mo400roundToPx0680j_4(float f) {
        return Density.CC.m6855$default$roundToPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public /* synthetic */ float mo401toDpGaN1DYA(long j) {
        return FontScaling.CC.m6999$default$toDpGaN1DYA(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public /* synthetic */ float mo402toDpu2uoSUM(float f) {
        return Density.CC.m6856$default$toDpu2uoSUM(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public /* synthetic */ float mo403toDpu2uoSUM(int i) {
        return Density.CC.m6857$default$toDpu2uoSUM((Density) this, i);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public /* synthetic */ long mo404toDpSizekrfVVM(long j) {
        return Density.CC.m6858$default$toDpSizekrfVVM(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public /* synthetic */ float mo405toPxR2X_6o(long j) {
        return Density.CC.m6859$default$toPxR2X_6o(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public /* synthetic */ float mo406toPx0680j_4(float f) {
        return Density.CC.m6860$default$toPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    public /* synthetic */ Rect toRect(DpRect dpRect) {
        return Density.CC.$default$toRect(this, dpRect);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public /* synthetic */ long mo407toSizeXkaWNTQ(long j) {
        return Density.CC.m6861$default$toSizeXkaWNTQ(this, j);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public /* synthetic */ long mo408toSp0xMU5do(float f) {
        return FontScaling.CC.m7000$default$toSp0xMU5do(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public /* synthetic */ long mo409toSpkPz2Gy4(float f) {
        return Density.CC.m6862$default$toSpkPz2Gy4(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public /* synthetic */ long mo410toSpkPz2Gy4(int i) {
        return Density.CC.m6863$default$toSpkPz2Gy4((Density) this, i);
    }

    @Override // androidx.compose.ui.node.MotionReferencePlacementDelegate
    /* JADX INFO: renamed from: isPlacedUnderMotionFrameOfReference, reason: from getter */
    public boolean getIsPlacedUnderMotionFrameOfReference() {
        return this.isPlacedUnderMotionFrameOfReference;
    }

    public void setPlacedUnderMotionFrameOfReference(boolean z) {
        this.isPlacedUnderMotionFrameOfReference = z;
    }

    @Override // androidx.compose.ui.node.MotionReferencePlacementDelegate
    public void updatePlacedUnderMotionFrameOfReference(boolean newMFR) {
        LookaheadCapablePlaceable parent = getParent();
        LayoutNode layoutNode = parent != null ? parent.getLayoutNode() : null;
        if (Intrinsics.areEqual(layoutNode, getLayoutNode())) {
            setPlacedUnderMotionFrameOfReference(newMFR);
            return;
        }
        if ((layoutNode != null ? layoutNode.getLayoutState$ui_release() : null) != LayoutNode.LayoutState.LayingOut) {
            if ((layoutNode != null ? layoutNode.getLayoutState$ui_release() : null) != LayoutNode.LayoutState.LookaheadLayingOut) {
                return;
            }
        }
        setPlacedUnderMotionFrameOfReference(newMFR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ResettableRulerScope getRulerScope() {
        ResettableRulerScope resettableRulerScope = this._rulerScope;
        if (resettableRulerScope != null) {
            return resettableRulerScope;
        }
        ResettableRulerScope resettableRulerScope2 = new ResettableRulerScope();
        this._rulerScope = resettableRulerScope2;
        return resettableRulerScope2;
    }

    @Override // androidx.compose.ui.layout.Measured
    public final int get(AlignmentLine alignmentLine) {
        int iCalculateAlignmentLine;
        int iM7023getYimpl;
        if (!getHasMeasureResult() || (iCalculateAlignmentLine = calculateAlignmentLine(alignmentLine)) == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (alignmentLine instanceof VerticalAlignmentLine) {
            iM7023getYimpl = IntOffset.m7022getXimpl(getApparentToRealOffset());
        } else {
            iM7023getYimpl = IntOffset.m7023getYimpl(getApparentToRealOffset());
        }
        return iCalculateAlignmentLine + iM7023getYimpl;
    }

    /* JADX INFO: renamed from: isShallowPlacing$ui_release, reason: from getter */
    public final boolean getIsShallowPlacing() {
        return this.isShallowPlacing;
    }

    public final void setShallowPlacing$ui_release(boolean z) {
        this.isShallowPlacing = z;
    }

    /* JADX INFO: renamed from: isPlacingForAlignment$ui_release, reason: from getter */
    public final boolean getIsPlacingForAlignment() {
        return this.isPlacingForAlignment;
    }

    public final void setPlacingForAlignment$ui_release(boolean z) {
        this.isPlacingForAlignment = z;
    }

    public final Placeable.PlacementScope getPlacementScope() {
        return this.placementScope;
    }

    protected final void invalidateAlignmentLinesFromPositionChange(NodeCoordinator nodeCoordinator) {
        AlignmentLines alignmentLines;
        NodeCoordinator wrapped = nodeCoordinator.getWrapped();
        if (!Intrinsics.areEqual(wrapped != null ? wrapped.getLayoutNode() : null, nodeCoordinator.getLayoutNode())) {
            nodeCoordinator.getAlignmentLinesOwner().getAlignmentLines().onAlignmentsChanged();
            return;
        }
        AlignmentLinesOwner parentAlignmentLinesOwner = nodeCoordinator.getAlignmentLinesOwner().getParentAlignmentLinesOwner();
        if (parentAlignmentLinesOwner == null || (alignmentLines = parentAlignmentLinesOwner.getAlignmentLines()) == null) {
            return;
        }
        alignmentLines.onAlignmentsChanged();
    }

    public final float findRulerValue(Ruler ruler, float defaultValue) {
        if (this.isPlacingForAlignment) {
            return defaultValue;
        }
        LookaheadCapablePlaceable lookaheadCapablePlaceable = this;
        while (true) {
            RulerTrackingMap rulerTrackingMap = lookaheadCapablePlaceable.rulerValues;
            float orDefault = rulerTrackingMap != null ? rulerTrackingMap.getOrDefault(ruler, Float.NaN) : Float.NaN;
            if (!Float.isNaN(orDefault)) {
                lookaheadCapablePlaceable.addRulerReader(getLayoutNode(), ruler);
                return ruler.calculateCoordinate$ui_release(orDefault, lookaheadCapablePlaceable.getCoordinates(), getCoordinates());
            }
            LookaheadCapablePlaceable parent = lookaheadCapablePlaceable.getParent();
            if (parent == null) {
                lookaheadCapablePlaceable.addRulerReader(getLayoutNode(), ruler);
                return defaultValue;
            }
            lookaheadCapablePlaceable = parent;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x009a  */
    /* JADX WARN: Code duplicated, block: B:50:0x0102  */
    /* JADX WARN: Code duplicated, block: B:90:0x00a2 A[SYNTHETIC] */
    private final void addRulerReader(LayoutNode layoutNode, Ruler ruler) {
        char c;
        long j;
        long j2;
        long j3;
        int i;
        int i2;
        long[] jArr;
        long[] jArr2;
        long j4;
        int i3;
        char c2;
        long j5;
        int i4;
        int i5;
        int i6;
        boolean z;
        MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> mutableScatterMap = this.rulerReaders;
        char c3 = 7;
        long j6 = -9187201950435737472L;
        int i7 = 8;
        if (mutableScatterMap != null) {
            MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> mutableScatterMap2 = mutableScatterMap;
            Object[] objArr = mutableScatterMap2.values;
            long[] jArr3 = mutableScatterMap2.metadata;
            int length = jArr3.length - 2;
            if (length >= 0) {
                int i8 = 0;
                j2 = 128;
                while (true) {
                    long j7 = jArr3[i8];
                    j3 = 255;
                    if ((((~j7) << c3) & j7 & j6) != j6) {
                        int i9 = 8 - ((~(i8 - length)) >>> 31);
                        int i10 = 0;
                        while (i10 < i9) {
                            if ((j7 & 255) < 128) {
                                c2 = c3;
                                MutableScatterSet mutableScatterSet = (MutableScatterSet) objArr[(i8 << 3) + i10];
                                j5 = j6;
                                Object[] objArr2 = mutableScatterSet.elements;
                                long[] jArr4 = mutableScatterSet.metadata;
                                int length2 = jArr4.length - 2;
                                if (length2 >= 0) {
                                    int i11 = i7;
                                    int i12 = 0;
                                    while (true) {
                                        int i13 = length2;
                                        long j8 = jArr4[i12];
                                        jArr2 = jArr3;
                                        j4 = j7;
                                        if ((((~j8) << c2) & j8 & j5) != j5) {
                                            int i14 = 8 - ((~(i12 - i13)) >>> 31);
                                            int i15 = 0;
                                            while (i15 < i14) {
                                                if ((j8 & 255) < 128) {
                                                    int i16 = (i12 << 3) + i15;
                                                    LayoutNode layoutNode2 = (LayoutNode) ((WeakReference) objArr2[i16]).get();
                                                    i5 = i15;
                                                    if (layoutNode2 != null) {
                                                        boolean zIsAttached = layoutNode2.isAttached();
                                                        i6 = i10;
                                                        z = zIsAttached;
                                                        if (!z) {
                                                            mutableScatterSet.removeElementAt(i16);
                                                        }
                                                    } else {
                                                        i6 = i10;
                                                    }
                                                    if (!z) {
                                                        mutableScatterSet.removeElementAt(i16);
                                                    }
                                                } else {
                                                    i5 = i15;
                                                    i6 = i10;
                                                }
                                                j8 >>= i11;
                                                i15 = i5 + 1;
                                                i10 = i6;
                                            }
                                            i3 = i10;
                                            if (i14 != i11) {
                                                break;
                                            }
                                        } else {
                                            i3 = i10;
                                        }
                                        length2 = i13;
                                        if (i12 == length2) {
                                            break;
                                        }
                                        i12++;
                                        jArr3 = jArr2;
                                        j7 = j4;
                                        i10 = i3;
                                        i11 = 8;
                                    }
                                } else {
                                    jArr2 = jArr3;
                                    j4 = j7;
                                    i3 = i10;
                                }
                                i4 = 8;
                            } else {
                                jArr2 = jArr3;
                                j4 = j7;
                                i3 = i10;
                                c2 = c3;
                                j5 = j6;
                                i4 = i7;
                            }
                            i7 = i4;
                            j7 = j4 >> i4;
                            c3 = c2;
                            j6 = j5;
                            i10 = i3 + 1;
                            jArr3 = jArr2;
                        }
                        jArr = jArr3;
                        c = c3;
                        j = j6;
                        if (i9 != i7) {
                            break;
                        }
                    } else {
                        jArr = jArr3;
                        c = c3;
                        j = j6;
                    }
                    if (i8 == length) {
                        break;
                    }
                    i8++;
                    c3 = c;
                    j6 = j;
                    jArr3 = jArr;
                    i7 = 8;
                }
            } else {
                c = 7;
                j = -9187201950435737472L;
                j2 = 128;
                j3 = 255;
            }
        } else {
            c = 7;
            j = -9187201950435737472L;
            j2 = 128;
            j3 = 255;
        }
        MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> mutableScatterMap3 = this.rulerReaders;
        if (mutableScatterMap3 != null) {
            long[] jArr5 = mutableScatterMap3.metadata;
            int length3 = jArr5.length - 2;
            if (length3 >= 0) {
                int i17 = 0;
                while (true) {
                    long j9 = jArr5[i17];
                    if ((((~j9) << c) & j9 & j) != j) {
                        int i18 = 8 - ((~(i17 - length3)) >>> 31);
                        for (int i19 = 0; i19 < i18; i19++) {
                            if ((j9 & j3) < j2) {
                                int i20 = (i17 << 3) + i19;
                                if (((MutableScatterSet) mutableScatterMap3.values[i20]).isEmpty()) {
                                    mutableScatterMap3.removeValueAt(i20);
                                }
                            }
                            j9 >>= 8;
                        }
                        if (i18 != 8) {
                            break;
                        }
                    }
                    if (i17 == length3) {
                        break;
                    } else {
                        i17++;
                    }
                }
            }
        }
        MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> mutableScatterMap4 = this.rulerReaders;
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (mutableScatterMap4 == null) {
            i = 0;
            i2 = 1;
            mutableScatterMap4 = new MutableScatterMap<>(i, i2, defaultConstructorMarker);
            this.rulerReaders = mutableScatterMap4;
        } else {
            i = 0;
            i2 = 1;
        }
        MutableScatterSet<WeakReference<LayoutNode>> mutableScatterSet2 = mutableScatterMap4.get(ruler);
        if (mutableScatterSet2 == null) {
            mutableScatterSet2 = new MutableScatterSet<>(i, i2, null);
            mutableScatterMap4.set(ruler, mutableScatterSet2);
        }
        mutableScatterSet2.plusAssign(new WeakReference<>(layoutNode));
    }

    private final LookaheadCapablePlaceable findAncestorRulerDefiner(Ruler ruler) {
        LookaheadCapablePlaceable parent;
        while (true) {
            RulerTrackingMap rulerTrackingMap = this.rulerValues;
            if ((rulerTrackingMap != null && rulerTrackingMap.contains(ruler)) || (parent = this.getParent()) == null) {
                return this;
            }
            this = parent;
        }
    }

    private final boolean isLayoutNodeAncestor(LayoutNode layoutNode, LayoutNode layoutNode2) {
        if (layoutNode == layoutNode2) {
            return true;
        }
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        if (parent$ui_release != null) {
            return isLayoutNodeAncestor(parent$ui_release, layoutNode2);
        }
        return false;
    }

    public final void invalidateChildrenOfDefiningRuler$ui_release(Ruler ruler) {
        MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> mutableScatterMap = findAncestorRulerDefiner(ruler).rulerReaders;
        MutableScatterSet<WeakReference<LayoutNode>> mutableScatterSetRemove = mutableScatterMap != null ? mutableScatterMap.remove(ruler) : null;
        if (mutableScatterSetRemove != null) {
            notifyRulerValueChange(mutableScatterSetRemove);
        }
    }

    /* JADX WARN: Code duplicated, block: B:66:0x011f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:67:0x0121 A[LOOP:2: B:57:0x00f4->B:67:0x0121, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:80:0x0124 A[EDGE_INSN: B:80:0x0124->B:68:0x0124 BREAK  A[LOOP:2: B:57:0x00f4->B:67:0x0121], SYNTHETIC] */
    public final void captureRulersIfNeeded$ui_release(MeasureResult result) {
        char c;
        MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> mutableScatterMap = this.rulerReaders;
        char c2 = 7;
        if (result != null) {
            if (this.isPlacingForAlignment) {
                return;
            }
            Function1<RulerScope, Unit> rulers = result.getRulers();
            if (rulers != null) {
                boolean z = this.rulersLambda != rulers;
                long jM7032getMaxnOccac = IntOffset.INSTANCE.m7032getMaxnOccac();
                long jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                if (!z && getRulerScope().getCoordinatesAccessed()) {
                    LayoutCoordinates coordinates = getCoordinates();
                    jM7032getMaxnOccac = IntOffsetKt.m7039roundk4lQ0M(LayoutCoordinatesKt.positionOnScreen(coordinates));
                    jM7070getZeroYbymL2g = coordinates.mo5583getSizeYbymL2g();
                    z = (IntOffset.m7021equalsimpl0(jM7032getMaxnOccac, getRulerScope().getPositionOnScreen()) && IntSize.m7063equalsimpl0(jM7070getZeroYbymL2g, getRulerScope().getSize())) ? false : true;
                }
                long j = jM7032getMaxnOccac;
                long j2 = jM7070getZeroYbymL2g;
                if (z) {
                    PlaceableResult placeableResult = this.cachedRulerPlaceableResult;
                    if (placeableResult != null) {
                        placeableResult.setResult(result);
                    } else {
                        placeableResult = new PlaceableResult(result, this);
                        this.cachedRulerPlaceableResult = placeableResult;
                    }
                    m5808captureRulersOSxE8f4(placeableResult, j, j2);
                    this.rulersLambda = result.getRulers();
                    return;
                }
                return;
            }
            if (mutableScatterMap != null) {
                MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> mutableScatterMap2 = mutableScatterMap;
                Object[] objArr = mutableScatterMap2.values;
                long[] jArr = mutableScatterMap2.metadata;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i = 0;
                    while (true) {
                        long j3 = jArr[i];
                        if ((((~j3) << c2) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i2 = 8 - ((~(i - length)) >>> 31);
                            int i3 = 0;
                            while (i3 < i2) {
                                if ((j3 & 255) < 128) {
                                    notifyRulerValueChange((MutableScatterSet) objArr[(i << 3) + i3]);
                                }
                                j3 >>= 8;
                                i3++;
                                c2 = c2;
                            }
                            c = c2;
                            if (i2 != 8) {
                                break;
                            }
                        } else {
                            c = c2;
                        }
                        if (i == length) {
                            break;
                        }
                        i++;
                        c2 = c;
                    }
                }
                mutableScatterMap.clear();
                return;
            }
            return;
        }
        if (mutableScatterMap != null) {
            MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> mutableScatterMap3 = mutableScatterMap;
            Object[] objArr2 = mutableScatterMap3.values;
            long[] jArr2 = mutableScatterMap3.metadata;
            int length2 = jArr2.length - 2;
            if (length2 >= 0) {
                int i4 = 0;
                while (true) {
                    long j4 = jArr2[i4];
                    if ((((~j4) << 7) & j4 & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i4 != length2) {
                            break;
                            break;
                        }
                        i4++;
                    } else {
                        int i5 = 8 - ((~(i4 - length2)) >>> 31);
                        for (int i6 = 0; i6 < i5; i6++) {
                            if ((j4 & 255) < 128) {
                                notifyRulerValueChange((MutableScatterSet) objArr2[(i4 << 3) + i6]);
                            }
                            j4 >>= 8;
                        }
                        if (i5 != 8) {
                            break;
                        } else if (i4 != length2) {
                            break;
                        } else {
                            i4++;
                        }
                    }
                }
            }
        }
        if (mutableScatterMap != null) {
            mutableScatterMap.clear();
        }
        RulerTrackingMap rulerTrackingMap = this.rulerValues;
        if (rulerTrackingMap != null) {
            rulerTrackingMap.clear();
        }
    }

    /* JADX INFO: renamed from: captureRulers-OSxE8f4$default, reason: not valid java name */
    static /* synthetic */ void m5809captureRulersOSxE8f4$default(LookaheadCapablePlaceable lookaheadCapablePlaceable, PlaceableResult placeableResult, long j, long j2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: captureRulers-OSxE8f4");
        }
        if ((i & 2) != 0) {
            j = IntOffset.INSTANCE.m7032getMaxnOccac();
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = IntSize.INSTANCE.m7070getZeroYbymL2g();
        }
        lookaheadCapablePlaceable.m5808captureRulersOSxE8f4(placeableResult, j3, j2);
    }

    /* JADX INFO: renamed from: captureRulers-OSxE8f4, reason: not valid java name */
    private final void m5808captureRulersOSxE8f4(final PlaceableResult placeableResult, final long positionOnScreen, final long size) {
        OwnerSnapshotObserver snapshotObserver;
        MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> mutableScatterMap = this.rulerReaders;
        RulerTrackingMap rulerTrackingMap = this.rulerValues;
        if (rulerTrackingMap == null) {
            rulerTrackingMap = new RulerTrackingMap();
            this.rulerValues = rulerTrackingMap;
        }
        Owner owner = getLayoutNode().getOwner();
        if (owner != null && (snapshotObserver = owner.getSnapshotObserver()) != null) {
            snapshotObserver.observeReads$ui_release(placeableResult, onCommitAffectingRuler, new Function0<Unit>() { // from class: androidx.compose.ui.node.LookaheadCapablePlaceable$captureRulers$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.getRulerScope().setCoordinatesAccessed(false);
                    this.this$0.getRulerScope().m5813setPositionOnScreengyyYBs(positionOnScreen);
                    this.this$0.getRulerScope().m5814setSizeozmzZPI(size);
                    Function1<RulerScope, Unit> rulers = placeableResult.getResult().getRulers();
                    if (rulers != null) {
                        rulers.invoke(this.this$0.getRulerScope());
                    }
                }
            });
        }
        rulerTrackingMap.notifyChanged(isLookingAhead(), this, mutableScatterMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:20:0x0056 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:21:0x0058 A[LOOP:0: B:11:0x0021->B:21:0x0058, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:27:0x005b A[EDGE_INSN: B:27:0x005b->B:22:0x005b BREAK  A[LOOP:0: B:11:0x0021->B:21:0x0058], SYNTHETIC] */
    public final void captureRulersIfNeeded(PlaceableResult placeableResult) {
        if (this.isPlacingForAlignment) {
            return;
        }
        Function1<RulerScope, Unit> rulers = placeableResult.getResult().getRulers();
        MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> mutableScatterMap = this.rulerReaders;
        if (rulers != null) {
            m5809captureRulersOSxE8f4$default(this, placeableResult, 0L, 0L, 6, null);
            this.rulersLambda = rulers;
            return;
        }
        if (mutableScatterMap != null) {
            MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> mutableScatterMap2 = mutableScatterMap;
            Object[] objArr = mutableScatterMap2.values;
            long[] jArr = mutableScatterMap2.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    long j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i != length) {
                            break;
                            break;
                        }
                        i++;
                    } else {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        for (int i3 = 0; i3 < i2; i3++) {
                            if ((255 & j) < 128) {
                                notifyRulerValueChange((MutableScatterSet) objArr[(i << 3) + i3]);
                            }
                            j >>= 8;
                        }
                        if (i2 != 8) {
                            break;
                        } else if (i != length) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            mutableScatterMap.clear();
        }
    }

    private final void notifyRulerValueChange(MutableScatterSet<WeakReference<LayoutNode>> layoutNodes) {
        LayoutNode layoutNode;
        MutableScatterSet<WeakReference<LayoutNode>> mutableScatterSet = layoutNodes;
        Object[] objArr = mutableScatterSet.elements;
        long[] jArr = mutableScatterSet.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128 && (layoutNode = (LayoutNode) ((WeakReference) objArr[(i << 3) + i3]).get()) != null) {
                        if (isLookingAhead()) {
                            layoutNode.requestLookaheadRelayout$ui_release(false);
                        } else {
                            layoutNode.requestRelayout$ui_release(false);
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void provideRulerValue(Ruler ruler, float value) {
        RulerTrackingMap rulerTrackingMap = this.rulerValues;
        if (rulerTrackingMap == null) {
            rulerTrackingMap = new RulerTrackingMap();
            this.rulerValues = rulerTrackingMap;
        }
        rulerTrackingMap.set(ruler, value);
    }

    public final void provideRelativeRulerValue(Ruler ruler, float value) {
        RulerTrackingMap rulerTrackingMap = this.rulerValues;
        if (rulerTrackingMap == null) {
            rulerTrackingMap = new RulerTrackingMap();
            this.rulerValues = rulerTrackingMap;
        }
        if (getLayoutDirection() != LayoutDirection.Ltr) {
            value = getWidth() - value;
        }
        rulerTrackingMap.set(ruler, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: LookaheadDelegate.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0096\u0004J\u0015\u0010\u001e\u001a\u00020\u001a*\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0096\u0004R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0013\u0010\r\"\u0004\b\u0014\u0010\u000fR\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010 \u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\"¨\u0006%"}, d2 = {"Landroidx/compose/ui/node/LookaheadCapablePlaceable$ResettableRulerScope;", "Landroidx/compose/ui/layout/RulerScope;", "<init>", "(Landroidx/compose/ui/node/LookaheadCapablePlaceable;)V", "coordinatesAccessed", "", "getCoordinatesAccessed", "()Z", "setCoordinatesAccessed", "(Z)V", "positionOnScreen", "Landroidx/compose/ui/unit/IntOffset;", "getPositionOnScreen-nOcc-ac", "()J", "setPositionOnScreen--gyyYBs", "(J)V", "J", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "setSize-ozmzZPI", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "provides", "", "Landroidx/compose/ui/layout/Ruler;", ES6Iterator.VALUE_PROPERTY, "", "providesRelative", "Landroidx/compose/ui/layout/VerticalRuler;", "density", "getDensity", "()F", "fontScale", "getFontScale", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    final class ResettableRulerScope implements RulerScope {
        private boolean coordinatesAccessed;
        private long positionOnScreen = IntOffset.INSTANCE.m7032getMaxnOccac();
        private long size = IntSize.INSTANCE.m7070getZeroYbymL2g();

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx--R2X_6o */
        public /* synthetic */ int mo399roundToPxR2X_6o(long j) {
            return Density.CC.m6854$default$roundToPxR2X_6o(this, j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx-0680j_4 */
        public /* synthetic */ int mo400roundToPx0680j_4(float f) {
            return Density.CC.m6855$default$roundToPx0680j_4(this, f);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toDp-GaN1DYA */
        public /* synthetic */ float mo401toDpGaN1DYA(long j) {
            return FontScaling.CC.m6999$default$toDpGaN1DYA(this, j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public /* synthetic */ float mo402toDpu2uoSUM(float f) {
            return Density.CC.m6856$default$toDpu2uoSUM(this, f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public /* synthetic */ float mo403toDpu2uoSUM(int i) {
            return Density.CC.m6857$default$toDpu2uoSUM((Density) this, i);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDpSize-k-rfVVM */
        public /* synthetic */ long mo404toDpSizekrfVVM(long j) {
            return Density.CC.m6858$default$toDpSizekrfVVM(this, j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx--R2X_6o */
        public /* synthetic */ float mo405toPxR2X_6o(long j) {
            return Density.CC.m6859$default$toPxR2X_6o(this, j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx-0680j_4 */
        public /* synthetic */ float mo406toPx0680j_4(float f) {
            return Density.CC.m6860$default$toPx0680j_4(this, f);
        }

        @Override // androidx.compose.ui.unit.Density
        public /* synthetic */ Rect toRect(DpRect dpRect) {
            return Density.CC.$default$toRect(this, dpRect);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSize-XkaWNTQ */
        public /* synthetic */ long mo407toSizeXkaWNTQ(long j) {
            return Density.CC.m6861$default$toSizeXkaWNTQ(this, j);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toSp-0xMU5do */
        public /* synthetic */ long mo408toSp0xMU5do(float f) {
            return FontScaling.CC.m7000$default$toSp0xMU5do(this, f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public /* synthetic */ long mo409toSpkPz2Gy4(float f) {
            return Density.CC.m6862$default$toSpkPz2Gy4(this, f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public /* synthetic */ long mo410toSpkPz2Gy4(int i) {
            return Density.CC.m6863$default$toSpkPz2Gy4((Density) this, i);
        }

        public ResettableRulerScope() {
        }

        public final boolean getCoordinatesAccessed() {
            return this.coordinatesAccessed;
        }

        public final void setCoordinatesAccessed(boolean z) {
            this.coordinatesAccessed = z;
        }

        /* JADX INFO: renamed from: getPositionOnScreen-nOcc-ac, reason: not valid java name and from getter */
        public final long getPositionOnScreen() {
            return this.positionOnScreen;
        }

        /* JADX INFO: renamed from: setPositionOnScreen--gyyYBs, reason: not valid java name */
        public final void m5813setPositionOnScreengyyYBs(long j) {
            this.positionOnScreen = j;
        }

        /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name and from getter */
        public final long getSize() {
            return this.size;
        }

        /* JADX INFO: renamed from: setSize-ozmzZPI, reason: not valid java name */
        public final void m5814setSizeozmzZPI(long j) {
            this.size = j;
        }

        @Override // androidx.compose.ui.layout.RulerScope
        public LayoutCoordinates getCoordinates() {
            this.coordinatesAccessed = true;
            LayoutCoordinates coordinates = LookaheadCapablePlaceable.this.getCoordinates();
            if (IntOffset.m7021equalsimpl0(this.positionOnScreen, IntOffset.INSTANCE.m7032getMaxnOccac())) {
                this.positionOnScreen = IntOffsetKt.m7039roundk4lQ0M(LayoutCoordinatesKt.positionOnScreen(coordinates));
                this.size = coordinates.mo5583getSizeYbymL2g();
            }
            LookaheadCapablePlaceable.this.getLayoutNode().getLayoutDelegate().onCoordinatesUsed();
            return coordinates;
        }

        @Override // androidx.compose.ui.layout.RulerScope
        public void provides(Ruler ruler, float f) {
            LookaheadCapablePlaceable.this.provideRulerValue(ruler, f);
        }

        @Override // androidx.compose.ui.layout.RulerScope
        public void providesRelative(VerticalRuler verticalRuler, float f) {
            LookaheadCapablePlaceable.this.provideRelativeRulerValue(verticalRuler, f);
        }

        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            return LookaheadCapablePlaceable.this.getDensity();
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public float getFontScale() {
            return LookaheadCapablePlaceable.this.getFontScale();
        }
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public MeasureResult layout(final int width, final int height, final Map<AlignmentLine, Integer> alignmentLines, final Function1<? super RulerScope, Unit> rulers, final Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
        if (!((width & (-16777216)) == 0 && ((-16777216) & height) == 0)) {
            InlineClassHelperKt.throwIllegalStateException("Size(" + width + " x " + height + ") is out of range. Each dimension must be between 0 and 16777215.");
        }
        return new MeasureResult() { // from class: androidx.compose.ui.node.LookaheadCapablePlaceable.layout.1
            @Override // androidx.compose.ui.layout.MeasureResult
            /* JADX INFO: renamed from: getWidth, reason: from getter */
            public int get$width() {
                return width;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            /* JADX INFO: renamed from: getHeight, reason: from getter */
            public int get$height() {
                return height;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Map<AlignmentLine, Integer> getAlignmentLines() {
                return alignmentLines;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Function1<RulerScope, Unit> getRulers() {
                return rulers;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public void placeChildren() {
                placementBlock.invoke(this.getPlacementScope());
            }
        };
    }
}
