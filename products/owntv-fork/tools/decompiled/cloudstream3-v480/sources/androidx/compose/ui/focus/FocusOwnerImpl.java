package androidx.compose.ui.focus;

import android.view.KeyEvent;
import androidx.collection.MutableLongSet;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: FocusOwnerImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\b\u001aJ!\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020!H\u0016J\u0010\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\u0015H\u0016J/\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b&\u0010'J\u001a\u0010#\u001a\u00020\u00152\b\b\u0002\u0010(\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0015H\u0002J\u0017\u0010)\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b*\u0010+J7\u0010,\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010-\u001a\u0004\u0018\u00010\u00192\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00150/H\u0016¢\u0006\u0004\b0\u00101J%\u00102\u001a\u00020\u00152\u0006\u00103\u001a\u0002042\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u001506H\u0016¢\u0006\u0004\b7\u00108J\u0017\u00109\u001a\u00020\u00152\u0006\u00103\u001a\u000204H\u0016¢\u0006\u0004\b:\u0010;J\u001e\u0010<\u001a\u00020\u00152\u0006\u0010=\u001a\u00020>2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u001506H\u0016J\u001e\u0010?\u001a\u00020\u00152\u0006\u0010=\u001a\u00020@2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u001506H\u0016J\u0010\u0010A\u001a\u00020!2\u0006\u0010B\u001a\u00020\tH\u0016J\u0010\u0010A\u001a\u00020!2\u0006\u0010B\u001a\u00020CH\u0016J\b\u0010D\u001a\u00020!H\u0016Jd\u0010E\u001a\u00020!\"\n\b\u0000\u0010F\u0018\u0001*\u00020G*\u00020G2\f\u0010H\u001a\b\u0012\u0004\u0012\u0002HF0I2\u0012\u0010J\u001a\u000e\u0012\u0004\u0012\u0002HF\u0012\u0004\u0012\u00020!0/2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020!062\u0012\u0010L\u001a\u000e\u0012\u0004\u0012\u0002HF\u0012\u0004\u0012\u00020!0/H\u0082\b¢\u0006\u0004\bM\u0010NJ0\u0010O\u001a\u0004\u0018\u0001HF\"\n\b\u0000\u0010F\u0018\u0001*\u00020P*\u00020G2\f\u0010H\u001a\b\u0012\u0004\u0012\u0002HF0IH\u0082\b¢\u0006\u0004\bQ\u0010RJ\n\u0010S\u001a\u0004\u0018\u00010\u0019H\u0016J\n\u0010T\u001a\u0004\u0018\u00010\tH\u0002J\u000e\u0010f\u001a\u0004\u0018\u00010g*\u00020GH\u0002J\u0017\u0010h\u001a\u00020\u00152\u0006\u00103\u001a\u000204H\u0002¢\u0006\u0004\bi\u0010;R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010U\u001a\u00020V8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bW\u0010XR\u001a\u0010Y\u001a\b\u0012\u0004\u0012\u00020[0ZX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010]R(\u0010_\u001a\u0004\u0018\u00010\t2\b\u0010^\u001a\u0004\u0018\u00010\t@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010\u000b\"\u0004\ba\u0010\rR$\u0010b\u001a\u00020\u00152\u0006\u0010^\u001a\u00020\u0015@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010c\"\u0004\bd\u0010e¨\u0006j"}, d2 = {"Landroidx/compose/ui/focus/FocusOwnerImpl;", "Landroidx/compose/ui/focus/FocusOwner;", "platformFocusOwner", "Landroidx/compose/ui/focus/PlatformFocusOwner;", "owner", "Landroidx/compose/ui/node/Owner;", "<init>", "(Landroidx/compose/ui/focus/PlatformFocusOwner;Landroidx/compose/ui/node/Owner;)V", "rootFocusNode", "Landroidx/compose/ui/focus/FocusTargetNode;", "getRootFocusNode$ui_release", "()Landroidx/compose/ui/focus/FocusTargetNode;", "setRootFocusNode$ui_release", "(Landroidx/compose/ui/focus/FocusTargetNode;)V", "focusInvalidationManager", "Landroidx/compose/ui/focus/FocusInvalidationManager;", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "requestOwnerFocus", "", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "previouslyFocusedRect", "Landroidx/compose/ui/geometry/Rect;", "requestOwnerFocus-7o62pno", "keysCurrentlyDown", "Landroidx/collection/MutableLongSet;", "takeFocus", "takeFocus-aToIllA", "(ILandroidx/compose/ui/geometry/Rect;)Z", "releaseFocus", "", "clearOwnerFocus", "clearFocus", "force", "refreshFocusEvents", "clearFocus-I7lrPNg", "(ZZZI)Z", "forced", "moveFocus", "moveFocus-3ESFkO8", "(I)Z", "focusSearch", "focusedRect", "onFound", "Lkotlin/Function1;", "focusSearch-ULY8qGw", "(ILandroidx/compose/ui/geometry/Rect;Lkotlin/jvm/functions/Function1;)Ljava/lang/Boolean;", "dispatchKeyEvent", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "onFocusedItem", "Lkotlin/Function0;", "dispatchKeyEvent-YhN2O0w", "(Landroid/view/KeyEvent;Lkotlin/jvm/functions/Function0;)Z", "dispatchInterceptedSoftKeyboardEvent", "dispatchInterceptedSoftKeyboardEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "dispatchRotaryEvent", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/rotary/RotaryScrollEvent;", "dispatchIndirectTouchEvent", "Landroidx/compose/ui/input/indirect/IndirectTouchEvent;", "scheduleInvalidation", "node", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "scheduleInvalidationForOwner", "traverseAncestorsIncludingSelf", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/ui/node/DelegatableNode;", "type", "Landroidx/compose/ui/node/NodeKind;", "onPreVisit", "onVisit", "onPostVisit", "traverseAncestorsIncludingSelf-QFhIj7k", "(Landroidx/compose/ui/node/DelegatableNode;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "nearestAncestorIncludingSelf", "", "nearestAncestorIncludingSelf-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Ljava/lang/Object;", "getFocusRect", "findFocusTargetNode", "rootState", "Landroidx/compose/ui/focus/FocusState;", "getRootState", "()Landroidx/compose/ui/focus/FocusState;", "listeners", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/ui/focus/FocusListener;", "getListeners", "()Landroidx/collection/MutableObjectList;", ES6Iterator.VALUE_PROPERTY, "activeFocusTargetNode", "getActiveFocusTargetNode", "setActiveFocusTargetNode", "isFocusCaptured", "()Z", "setFocusCaptured", "(Z)V", "lastLocalKeyInputNode", "Landroidx/compose/ui/Modifier$Node;", "validateKeyEvent", "validateKeyEvent-ZmokQxo", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class FocusOwnerImpl implements FocusOwner {
    public static final int $stable = 8;
    private FocusTargetNode activeFocusTargetNode;
    private final FocusInvalidationManager focusInvalidationManager;
    private boolean isFocusCaptured;
    private MutableLongSet keysCurrentlyDown;
    private final Owner owner;
    private final PlatformFocusOwner platformFocusOwner;
    private FocusTargetNode rootFocusNode = new FocusTargetNode(Focusability.INSTANCE.m3805getNeverLCbbffg(), null, null, 6, null);
    private final Modifier modifier = new ModifierNodeElement<FocusTargetNode>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$modifier$1
        @Override // androidx.compose.ui.node.ModifierNodeElement
        public boolean equals(Object other) {
            return other == this;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(FocusTargetNode node) {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        /* JADX INFO: renamed from: create */
        public FocusTargetNode getNode() {
            return this.this$0.getRootFocusNode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void inspectableProperties(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("RootFocusTarget");
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public int hashCode() {
            return this.this$0.getRootFocusNode().hashCode();
        }
    };
    private final MutableObjectList<FocusListener> listeners = new MutableObjectList<>(1);

    /* JADX INFO: compiled from: FocusOwnerImpl.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FocusOwnerImpl(PlatformFocusOwner platformFocusOwner, Owner owner) {
        this.platformFocusOwner = platformFocusOwner;
        this.owner = owner;
        this.focusInvalidationManager = new FocusInvalidationManager(this, owner);
    }

    /* JADX INFO: renamed from: getRootFocusNode$ui_release, reason: from getter */
    public final FocusTargetNode getRootFocusNode() {
        return this.rootFocusNode;
    }

    public final void setRootFocusNode$ui_release(FocusTargetNode focusTargetNode) {
        this.rootFocusNode = focusTargetNode;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public Modifier getModifier() {
        return this.modifier;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: requestOwnerFocus-7o62pno */
    public boolean mo3768requestOwnerFocus7o62pno(FocusDirection focusDirection, Rect previouslyFocusedRect) {
        return this.platformFocusOwner.mo3812requestOwnerFocus7o62pno(focusDirection, previouslyFocusedRect);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: takeFocus-aToIllA */
    public boolean mo3769takeFocusaToIllA(final int focusDirection, Rect previouslyFocusedRect) {
        Boolean boolMo3767focusSearchULY8qGw = mo3767focusSearchULY8qGw(focusDirection, previouslyFocusedRect, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$takeFocus$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                return Boolean.valueOf(focusTargetNode.mo3780requestFocus3ESFkO8(focusDirection));
            }
        });
        if (boolMo3767focusSearchULY8qGw != null) {
            return boolMo3767focusSearchULY8qGw.booleanValue();
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void releaseFocus() {
        FocusTransactionsKt.clearFocus(this.rootFocusNode, true, true);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void clearOwnerFocus() {
        this.platformFocusOwner.clearOwnerFocus();
    }

    @Override // androidx.compose.ui.focus.FocusManager
    public void clearFocus(boolean force) {
        mo3764clearFocusI7lrPNg(force, true, true, FocusDirection.INSTANCE.m3755getExitdhqQ8s());
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: clearFocus-I7lrPNg */
    public boolean mo3764clearFocusI7lrPNg(boolean force, boolean refreshFocusEvents, boolean clearOwnerFocus, int focusDirection) {
        boolean zClearFocus;
        if (!force) {
            int i = WhenMappings.$EnumSwitchMapping$0[FocusTransactionsKt.m3788performCustomClearFocusMxy_nc0(this.rootFocusNode, focusDirection).ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                zClearFocus = false;
            } else {
                if (i != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                zClearFocus = clearFocus(force, refreshFocusEvents);
            }
        } else {
            zClearFocus = clearFocus(force, refreshFocusEvents);
        }
        if (zClearFocus && clearOwnerFocus) {
            clearOwnerFocus();
        }
        return zClearFocus;
    }

    static /* synthetic */ boolean clearFocus$default(FocusOwnerImpl focusOwnerImpl, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return focusOwnerImpl.clearFocus(z, z2);
    }

    private final boolean clearFocus(boolean forced, boolean refreshFocusEvents) {
        NodeChain nodes;
        if (getActiveFocusTargetNode() == null) {
            return true;
        }
        if (getIsFocusCaptured() && !forced) {
            return false;
        }
        FocusTargetNode activeFocusTargetNode = getActiveFocusTargetNode();
        setActiveFocusTargetNode(null);
        if (refreshFocusEvents && activeFocusTargetNode != null) {
            activeFocusTargetNode.dispatchFocusCallbacks$ui_release(getIsFocusCaptured() ? FocusStateImpl.Captured : FocusStateImpl.Active, FocusStateImpl.Inactive);
            FocusTargetNode focusTargetNode = activeFocusTargetNode;
            int iM5881constructorimpl = NodeKind.m5881constructorimpl(1024);
            if (!focusTargetNode.getNode().getIsAttached()) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node parent = focusTargetNode.getNode().getParent();
            LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
            while (layoutNodeRequireLayoutNode != null) {
                if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM5881constructorimpl) != 0) {
                    while (parent != null) {
                        if ((parent.getKindSet() & iM5881constructorimpl) != 0) {
                            Modifier.Node nodePop = parent;
                            MutableVector mutableVector = null;
                            while (nodePop != null) {
                                if (nodePop instanceof FocusTargetNode) {
                                    ((FocusTargetNode) nodePop).dispatchFocusCallbacks$ui_release(FocusStateImpl.ActiveParent, FocusStateImpl.Inactive);
                                } else if ((nodePop.getKindSet() & iM5881constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int i = 0;
                                    for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                        if ((delegate.getKindSet() & iM5881constructorimpl) != 0) {
                                            i++;
                                            if (i == 1) {
                                                nodePop = delegate;
                                            } else {
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(nodePop);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector != null) {
                                                    mutableVector.add(delegate);
                                                }
                                            }
                                        }
                                    }
                                    if (i == 1) {
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector);
                            }
                        }
                        parent = parent.getParent();
                    }
                }
                layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
                parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.lang.Boolean] */
    @Override // androidx.compose.ui.focus.FocusManager
    /* JADX INFO: renamed from: moveFocus-3ESFkO8 */
    public boolean mo3763moveFocus3ESFkO8(final int focusDirection) {
        if (ComposeUiFlags.isViewFocusFixEnabled && this.platformFocusOwner.mo3811moveFocusInChildren3ESFkO8(focusDirection)) {
            return true;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = false;
        FocusTargetNode activeFocusTargetNode = getActiveFocusTargetNode();
        Boolean boolMo3767focusSearchULY8qGw = mo3767focusSearchULY8qGw(focusDirection, this.platformFocusOwner.getEmbeddedViewFocusRect(), new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$moveFocus$focusSearchSuccess$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Boolean] */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                objectRef.element = Boolean.valueOf(focusTargetNode.mo3780requestFocus3ESFkO8(focusDirection));
                return objectRef.element;
            }
        });
        if (Intrinsics.areEqual((Object) boolMo3767focusSearchULY8qGw, (Object) true) && activeFocusTargetNode != getActiveFocusTargetNode()) {
            return true;
        }
        if (boolMo3767focusSearchULY8qGw != null && objectRef.element != 0) {
            if (boolMo3767focusSearchULY8qGw.booleanValue() && ((Boolean) objectRef.element).booleanValue()) {
                return true;
            }
            if (FocusOwnerImplKt.m3774is1dFocusSearch3ESFkO8(focusDirection)) {
                return mo3764clearFocusI7lrPNg(false, true, false, focusDirection) && mo3769takeFocusaToIllA(focusDirection, null);
            }
            if (!ComposeUiFlags.isViewFocusFixEnabled && this.platformFocusOwner.mo3811moveFocusInChildren3ESFkO8(focusDirection)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: focusSearch-ULY8qGw */
    public Boolean mo3767focusSearchULY8qGw(int focusDirection, Rect focusedRect, final Function1<? super FocusTargetNode, Boolean> onFound) {
        final FocusTargetNode focusTargetNodeFindFocusTargetNode = findFocusTargetNode();
        if (focusTargetNodeFindFocusTargetNode != null) {
            FocusRequester focusRequesterM3794customFocusSearchOMvw8 = FocusTraversalKt.m3794customFocusSearchOMvw8(focusTargetNodeFindFocusTargetNode, focusDirection, this.owner.getLayoutDirection());
            if (Intrinsics.areEqual(focusRequesterM3794customFocusSearchOMvw8, FocusRequester.INSTANCE.getCancel())) {
                return null;
            }
            if (Intrinsics.areEqual(focusRequesterM3794customFocusSearchOMvw8, FocusRequester.INSTANCE.getRedirect$ui_release())) {
                FocusTargetNode focusTargetNodeFindFocusTargetNode2 = findFocusTargetNode();
                if (focusTargetNodeFindFocusTargetNode2 != null) {
                    return onFound.invoke(focusTargetNodeFindFocusTargetNode2);
                }
                return null;
            }
            if (!Intrinsics.areEqual(focusRequesterM3794customFocusSearchOMvw8, FocusRequester.INSTANCE.getDefault())) {
                return Boolean.valueOf(focusRequesterM3794customFocusSearchOMvw8.findFocusTargetNode$ui_release(onFound));
            }
        } else {
            focusTargetNodeFindFocusTargetNode = null;
        }
        return FocusTraversalKt.m3795focusSearch0X8WOeE(this.rootFocusNode, focusDirection, this.owner.getLayoutDirection(), focusedRect, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$focusSearch$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                boolean zBooleanValue;
                if (Intrinsics.areEqual(focusTargetNode, focusTargetNodeFindFocusTargetNode)) {
                    zBooleanValue = false;
                } else {
                    if (Intrinsics.areEqual(focusTargetNode, this.getRootFocusNode())) {
                        throw new IllegalStateException("Focus search landed at the root.".toString());
                    }
                    zBooleanValue = onFound.invoke(focusTargetNode).booleanValue();
                }
                return Boolean.valueOf(zBooleanValue);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:115:0x01a7 A[Catch: all -> 0x03f6, TryCatch #0 {all -> 0x03f6, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:118:0x01af, B:120:0x01bf, B:121:0x01c2, B:123:0x01d1, B:126:0x01e2, B:130:0x01ed, B:133:0x01f3, B:134:0x01fa, B:157:0x0248, B:135:0x01fe, B:137:0x0205, B:139:0x0209, B:141:0x0213, B:143:0x021a, B:145:0x021e, B:147:0x0224, B:150:0x022f, B:153:0x0239, B:154:0x0240, B:158:0x024d, B:159:0x0252, B:161:0x0258, B:163:0x025e, B:166:0x0269, B:168:0x0274, B:175:0x028b, B:176:0x028d, B:177:0x029d, B:179:0x02a1, B:181:0x02a7, B:211:0x031a, B:185:0x02b5, B:187:0x02c0, B:189:0x02c6, B:191:0x02d1, B:193:0x02d8, B:195:0x02dc, B:196:0x02e1, B:198:0x02e7, B:199:0x02ee, B:201:0x02f6, B:203:0x02fc, B:204:0x0303, B:205:0x0305, B:207:0x030b, B:208:0x0312, B:212:0x0326, B:216:0x0336, B:217:0x0346, B:219:0x034a, B:221:0x0350, B:251:0x03c3, B:225:0x035e, B:227:0x0369, B:229:0x036f, B:231:0x037a, B:233:0x0381, B:235:0x0385, B:236:0x038a, B:238:0x0390, B:239:0x0397, B:241:0x039f, B:243:0x03a5, B:244:0x03ac, B:245:0x03ae, B:247:0x03b4, B:248:0x03bb, B:253:0x03d1, B:255:0x03db, B:260:0x03ee, B:261:0x03f0, B:18:0x0041, B:20:0x0051, B:21:0x0054, B:23:0x005e, B:26:0x006f, B:30:0x007a, B:64:0x00ea, B:66:0x00ee, B:33:0x0080, B:35:0x0087, B:37:0x008b, B:39:0x0095, B:41:0x009c, B:43:0x00a0, B:45:0x00a6, B:48:0x00b1, B:51:0x00bb, B:52:0x00c2, B:55:0x00ca, B:56:0x00cf, B:57:0x00d4, B:59:0x00da, B:61:0x00e0, B:67:0x00f4, B:69:0x0106, B:70:0x0109, B:72:0x0117, B:75:0x0128, B:79:0x0133, B:113:0x01a3, B:115:0x01a7, B:82:0x0139, B:84:0x0140, B:86:0x0144, B:88:0x014e, B:90:0x0155, B:92:0x0159, B:94:0x015f, B:97:0x016a, B:100:0x0174, B:101:0x017b, B:104:0x0183, B:105:0x0188, B:106:0x018d, B:108:0x0193, B:110:0x0199), top: B:267:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:116:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:348:0x01a2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:349:0x0137 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:357:0x0188 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x00f4 A[Catch: all -> 0x03f6, TryCatch #0 {all -> 0x03f6, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:118:0x01af, B:120:0x01bf, B:121:0x01c2, B:123:0x01d1, B:126:0x01e2, B:130:0x01ed, B:133:0x01f3, B:134:0x01fa, B:157:0x0248, B:135:0x01fe, B:137:0x0205, B:139:0x0209, B:141:0x0213, B:143:0x021a, B:145:0x021e, B:147:0x0224, B:150:0x022f, B:153:0x0239, B:154:0x0240, B:158:0x024d, B:159:0x0252, B:161:0x0258, B:163:0x025e, B:166:0x0269, B:168:0x0274, B:175:0x028b, B:176:0x028d, B:177:0x029d, B:179:0x02a1, B:181:0x02a7, B:211:0x031a, B:185:0x02b5, B:187:0x02c0, B:189:0x02c6, B:191:0x02d1, B:193:0x02d8, B:195:0x02dc, B:196:0x02e1, B:198:0x02e7, B:199:0x02ee, B:201:0x02f6, B:203:0x02fc, B:204:0x0303, B:205:0x0305, B:207:0x030b, B:208:0x0312, B:212:0x0326, B:216:0x0336, B:217:0x0346, B:219:0x034a, B:221:0x0350, B:251:0x03c3, B:225:0x035e, B:227:0x0369, B:229:0x036f, B:231:0x037a, B:233:0x0381, B:235:0x0385, B:236:0x038a, B:238:0x0390, B:239:0x0397, B:241:0x039f, B:243:0x03a5, B:244:0x03ac, B:245:0x03ae, B:247:0x03b4, B:248:0x03bb, B:253:0x03d1, B:255:0x03db, B:260:0x03ee, B:261:0x03f0, B:18:0x0041, B:20:0x0051, B:21:0x0054, B:23:0x005e, B:26:0x006f, B:30:0x007a, B:64:0x00ea, B:66:0x00ee, B:33:0x0080, B:35:0x0087, B:37:0x008b, B:39:0x0095, B:41:0x009c, B:43:0x00a0, B:45:0x00a6, B:48:0x00b1, B:51:0x00bb, B:52:0x00c2, B:55:0x00ca, B:56:0x00cf, B:57:0x00d4, B:59:0x00da, B:61:0x00e0, B:67:0x00f4, B:69:0x0106, B:70:0x0109, B:72:0x0117, B:75:0x0128, B:79:0x0133, B:113:0x01a3, B:115:0x01a7, B:82:0x0139, B:84:0x0140, B:86:0x0144, B:88:0x014e, B:90:0x0155, B:92:0x0159, B:94:0x015f, B:97:0x016a, B:100:0x0174, B:101:0x017b, B:104:0x0183, B:105:0x0188, B:106:0x018d, B:108:0x0193, B:110:0x0199), top: B:267:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x0106 A[Catch: all -> 0x03f6, TryCatch #0 {all -> 0x03f6, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:118:0x01af, B:120:0x01bf, B:121:0x01c2, B:123:0x01d1, B:126:0x01e2, B:130:0x01ed, B:133:0x01f3, B:134:0x01fa, B:157:0x0248, B:135:0x01fe, B:137:0x0205, B:139:0x0209, B:141:0x0213, B:143:0x021a, B:145:0x021e, B:147:0x0224, B:150:0x022f, B:153:0x0239, B:154:0x0240, B:158:0x024d, B:159:0x0252, B:161:0x0258, B:163:0x025e, B:166:0x0269, B:168:0x0274, B:175:0x028b, B:176:0x028d, B:177:0x029d, B:179:0x02a1, B:181:0x02a7, B:211:0x031a, B:185:0x02b5, B:187:0x02c0, B:189:0x02c6, B:191:0x02d1, B:193:0x02d8, B:195:0x02dc, B:196:0x02e1, B:198:0x02e7, B:199:0x02ee, B:201:0x02f6, B:203:0x02fc, B:204:0x0303, B:205:0x0305, B:207:0x030b, B:208:0x0312, B:212:0x0326, B:216:0x0336, B:217:0x0346, B:219:0x034a, B:221:0x0350, B:251:0x03c3, B:225:0x035e, B:227:0x0369, B:229:0x036f, B:231:0x037a, B:233:0x0381, B:235:0x0385, B:236:0x038a, B:238:0x0390, B:239:0x0397, B:241:0x039f, B:243:0x03a5, B:244:0x03ac, B:245:0x03ae, B:247:0x03b4, B:248:0x03bb, B:253:0x03d1, B:255:0x03db, B:260:0x03ee, B:261:0x03f0, B:18:0x0041, B:20:0x0051, B:21:0x0054, B:23:0x005e, B:26:0x006f, B:30:0x007a, B:64:0x00ea, B:66:0x00ee, B:33:0x0080, B:35:0x0087, B:37:0x008b, B:39:0x0095, B:41:0x009c, B:43:0x00a0, B:45:0x00a6, B:48:0x00b1, B:51:0x00bb, B:52:0x00c2, B:55:0x00ca, B:56:0x00cf, B:57:0x00d4, B:59:0x00da, B:61:0x00e0, B:67:0x00f4, B:69:0x0106, B:70:0x0109, B:72:0x0117, B:75:0x0128, B:79:0x0133, B:113:0x01a3, B:115:0x01a7, B:82:0x0139, B:84:0x0140, B:86:0x0144, B:88:0x014e, B:90:0x0155, B:92:0x0159, B:94:0x015f, B:97:0x016a, B:100:0x0174, B:101:0x017b, B:104:0x0183, B:105:0x0188, B:106:0x018d, B:108:0x0193, B:110:0x0199), top: B:267:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x0117 A[Catch: all -> 0x03f6, TryCatch #0 {all -> 0x03f6, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:118:0x01af, B:120:0x01bf, B:121:0x01c2, B:123:0x01d1, B:126:0x01e2, B:130:0x01ed, B:133:0x01f3, B:134:0x01fa, B:157:0x0248, B:135:0x01fe, B:137:0x0205, B:139:0x0209, B:141:0x0213, B:143:0x021a, B:145:0x021e, B:147:0x0224, B:150:0x022f, B:153:0x0239, B:154:0x0240, B:158:0x024d, B:159:0x0252, B:161:0x0258, B:163:0x025e, B:166:0x0269, B:168:0x0274, B:175:0x028b, B:176:0x028d, B:177:0x029d, B:179:0x02a1, B:181:0x02a7, B:211:0x031a, B:185:0x02b5, B:187:0x02c0, B:189:0x02c6, B:191:0x02d1, B:193:0x02d8, B:195:0x02dc, B:196:0x02e1, B:198:0x02e7, B:199:0x02ee, B:201:0x02f6, B:203:0x02fc, B:204:0x0303, B:205:0x0305, B:207:0x030b, B:208:0x0312, B:212:0x0326, B:216:0x0336, B:217:0x0346, B:219:0x034a, B:221:0x0350, B:251:0x03c3, B:225:0x035e, B:227:0x0369, B:229:0x036f, B:231:0x037a, B:233:0x0381, B:235:0x0385, B:236:0x038a, B:238:0x0390, B:239:0x0397, B:241:0x039f, B:243:0x03a5, B:244:0x03ac, B:245:0x03ae, B:247:0x03b4, B:248:0x03bb, B:253:0x03d1, B:255:0x03db, B:260:0x03ee, B:261:0x03f0, B:18:0x0041, B:20:0x0051, B:21:0x0054, B:23:0x005e, B:26:0x006f, B:30:0x007a, B:64:0x00ea, B:66:0x00ee, B:33:0x0080, B:35:0x0087, B:37:0x008b, B:39:0x0095, B:41:0x009c, B:43:0x00a0, B:45:0x00a6, B:48:0x00b1, B:51:0x00bb, B:52:0x00c2, B:55:0x00ca, B:56:0x00cf, B:57:0x00d4, B:59:0x00da, B:61:0x00e0, B:67:0x00f4, B:69:0x0106, B:70:0x0109, B:72:0x0117, B:75:0x0128, B:79:0x0133, B:113:0x01a3, B:115:0x01a7, B:82:0x0139, B:84:0x0140, B:86:0x0144, B:88:0x014e, B:90:0x0155, B:92:0x0159, B:94:0x015f, B:97:0x016a, B:100:0x0174, B:101:0x017b, B:104:0x0183, B:105:0x0188, B:106:0x018d, B:108:0x0193, B:110:0x0199), top: B:267:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x0126 A[ADDED_TO_REGION, LOOP:15: B:74:0x0126->B:105:0x0188, LOOP_START, PHI: r9
      0x0126: PHI (r9v11 androidx.compose.ui.Modifier$Node) = (r9v4 androidx.compose.ui.Modifier$Node), (r9v12 androidx.compose.ui.Modifier$Node) binds: [B:73:0x0124, B:105:0x0188] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:75:0x0128 A[Catch: all -> 0x03f6, TryCatch #0 {all -> 0x03f6, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:118:0x01af, B:120:0x01bf, B:121:0x01c2, B:123:0x01d1, B:126:0x01e2, B:130:0x01ed, B:133:0x01f3, B:134:0x01fa, B:157:0x0248, B:135:0x01fe, B:137:0x0205, B:139:0x0209, B:141:0x0213, B:143:0x021a, B:145:0x021e, B:147:0x0224, B:150:0x022f, B:153:0x0239, B:154:0x0240, B:158:0x024d, B:159:0x0252, B:161:0x0258, B:163:0x025e, B:166:0x0269, B:168:0x0274, B:175:0x028b, B:176:0x028d, B:177:0x029d, B:179:0x02a1, B:181:0x02a7, B:211:0x031a, B:185:0x02b5, B:187:0x02c0, B:189:0x02c6, B:191:0x02d1, B:193:0x02d8, B:195:0x02dc, B:196:0x02e1, B:198:0x02e7, B:199:0x02ee, B:201:0x02f6, B:203:0x02fc, B:204:0x0303, B:205:0x0305, B:207:0x030b, B:208:0x0312, B:212:0x0326, B:216:0x0336, B:217:0x0346, B:219:0x034a, B:221:0x0350, B:251:0x03c3, B:225:0x035e, B:227:0x0369, B:229:0x036f, B:231:0x037a, B:233:0x0381, B:235:0x0385, B:236:0x038a, B:238:0x0390, B:239:0x0397, B:241:0x039f, B:243:0x03a5, B:244:0x03ac, B:245:0x03ae, B:247:0x03b4, B:248:0x03bb, B:253:0x03d1, B:255:0x03db, B:260:0x03ee, B:261:0x03f0, B:18:0x0041, B:20:0x0051, B:21:0x0054, B:23:0x005e, B:26:0x006f, B:30:0x007a, B:64:0x00ea, B:66:0x00ee, B:33:0x0080, B:35:0x0087, B:37:0x008b, B:39:0x0095, B:41:0x009c, B:43:0x00a0, B:45:0x00a6, B:48:0x00b1, B:51:0x00bb, B:52:0x00c2, B:55:0x00ca, B:56:0x00cf, B:57:0x00d4, B:59:0x00da, B:61:0x00e0, B:67:0x00f4, B:69:0x0106, B:70:0x0109, B:72:0x0117, B:75:0x0128, B:79:0x0133, B:113:0x01a3, B:115:0x01a7, B:82:0x0139, B:84:0x0140, B:86:0x0144, B:88:0x014e, B:90:0x0155, B:92:0x0159, B:94:0x015f, B:97:0x016a, B:100:0x0174, B:101:0x017b, B:104:0x0183, B:105:0x0188, B:106:0x018d, B:108:0x0193, B:110:0x0199), top: B:267:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:77:0x012f  */
    /* JADX WARN: Code duplicated, block: B:79:0x0133 A[Catch: all -> 0x03f6, TryCatch #0 {all -> 0x03f6, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:118:0x01af, B:120:0x01bf, B:121:0x01c2, B:123:0x01d1, B:126:0x01e2, B:130:0x01ed, B:133:0x01f3, B:134:0x01fa, B:157:0x0248, B:135:0x01fe, B:137:0x0205, B:139:0x0209, B:141:0x0213, B:143:0x021a, B:145:0x021e, B:147:0x0224, B:150:0x022f, B:153:0x0239, B:154:0x0240, B:158:0x024d, B:159:0x0252, B:161:0x0258, B:163:0x025e, B:166:0x0269, B:168:0x0274, B:175:0x028b, B:176:0x028d, B:177:0x029d, B:179:0x02a1, B:181:0x02a7, B:211:0x031a, B:185:0x02b5, B:187:0x02c0, B:189:0x02c6, B:191:0x02d1, B:193:0x02d8, B:195:0x02dc, B:196:0x02e1, B:198:0x02e7, B:199:0x02ee, B:201:0x02f6, B:203:0x02fc, B:204:0x0303, B:205:0x0305, B:207:0x030b, B:208:0x0312, B:212:0x0326, B:216:0x0336, B:217:0x0346, B:219:0x034a, B:221:0x0350, B:251:0x03c3, B:225:0x035e, B:227:0x0369, B:229:0x036f, B:231:0x037a, B:233:0x0381, B:235:0x0385, B:236:0x038a, B:238:0x0390, B:239:0x0397, B:241:0x039f, B:243:0x03a5, B:244:0x03ac, B:245:0x03ae, B:247:0x03b4, B:248:0x03bb, B:253:0x03d1, B:255:0x03db, B:260:0x03ee, B:261:0x03f0, B:18:0x0041, B:20:0x0051, B:21:0x0054, B:23:0x005e, B:26:0x006f, B:30:0x007a, B:64:0x00ea, B:66:0x00ee, B:33:0x0080, B:35:0x0087, B:37:0x008b, B:39:0x0095, B:41:0x009c, B:43:0x00a0, B:45:0x00a6, B:48:0x00b1, B:51:0x00bb, B:52:0x00c2, B:55:0x00ca, B:56:0x00cf, B:57:0x00d4, B:59:0x00da, B:61:0x00e0, B:67:0x00f4, B:69:0x0106, B:70:0x0109, B:72:0x0117, B:75:0x0128, B:79:0x0133, B:113:0x01a3, B:115:0x01a7, B:82:0x0139, B:84:0x0140, B:86:0x0144, B:88:0x014e, B:90:0x0155, B:92:0x0159, B:94:0x015f, B:97:0x016a, B:100:0x0174, B:101:0x017b, B:104:0x0183, B:105:0x0188, B:106:0x018d, B:108:0x0193, B:110:0x0199), top: B:267:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:82:0x0139 A[Catch: all -> 0x03f6, TryCatch #0 {all -> 0x03f6, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:118:0x01af, B:120:0x01bf, B:121:0x01c2, B:123:0x01d1, B:126:0x01e2, B:130:0x01ed, B:133:0x01f3, B:134:0x01fa, B:157:0x0248, B:135:0x01fe, B:137:0x0205, B:139:0x0209, B:141:0x0213, B:143:0x021a, B:145:0x021e, B:147:0x0224, B:150:0x022f, B:153:0x0239, B:154:0x0240, B:158:0x024d, B:159:0x0252, B:161:0x0258, B:163:0x025e, B:166:0x0269, B:168:0x0274, B:175:0x028b, B:176:0x028d, B:177:0x029d, B:179:0x02a1, B:181:0x02a7, B:211:0x031a, B:185:0x02b5, B:187:0x02c0, B:189:0x02c6, B:191:0x02d1, B:193:0x02d8, B:195:0x02dc, B:196:0x02e1, B:198:0x02e7, B:199:0x02ee, B:201:0x02f6, B:203:0x02fc, B:204:0x0303, B:205:0x0305, B:207:0x030b, B:208:0x0312, B:212:0x0326, B:216:0x0336, B:217:0x0346, B:219:0x034a, B:221:0x0350, B:251:0x03c3, B:225:0x035e, B:227:0x0369, B:229:0x036f, B:231:0x037a, B:233:0x0381, B:235:0x0385, B:236:0x038a, B:238:0x0390, B:239:0x0397, B:241:0x039f, B:243:0x03a5, B:244:0x03ac, B:245:0x03ae, B:247:0x03b4, B:248:0x03bb, B:253:0x03d1, B:255:0x03db, B:260:0x03ee, B:261:0x03f0, B:18:0x0041, B:20:0x0051, B:21:0x0054, B:23:0x005e, B:26:0x006f, B:30:0x007a, B:64:0x00ea, B:66:0x00ee, B:33:0x0080, B:35:0x0087, B:37:0x008b, B:39:0x0095, B:41:0x009c, B:43:0x00a0, B:45:0x00a6, B:48:0x00b1, B:51:0x00bb, B:52:0x00c2, B:55:0x00ca, B:56:0x00cf, B:57:0x00d4, B:59:0x00da, B:61:0x00e0, B:67:0x00f4, B:69:0x0106, B:70:0x0109, B:72:0x0117, B:75:0x0128, B:79:0x0133, B:113:0x01a3, B:115:0x01a7, B:82:0x0139, B:84:0x0140, B:86:0x0144, B:88:0x014e, B:90:0x0155, B:92:0x0159, B:94:0x015f, B:97:0x016a, B:100:0x0174, B:101:0x017b, B:104:0x0183, B:105:0x0188, B:106:0x018d, B:108:0x0193, B:110:0x0199), top: B:267:0x0009 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v15, types: [T, androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v20, types: [T, androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v32, types: [T, androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v42, types: [T, androidx.compose.ui.Modifier$Node, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v51, types: [T, androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v61, types: [T, androidx.compose.ui.Modifier$Node, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v79 */
    /* JADX WARN: Type inference failed for: r2v80 */
    /* JADX WARN: Type inference failed for: r2v81 */
    /* JADX WARN: Type inference failed for: r2v82 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because "result" is null
        	at jadx.core.dex.visitors.PrepareForCodeGen.removeInstructions(PrepareForCodeGen.java:118)
        	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:85)
        */
    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: dispatchKeyEvent-YhN2O0w */
    public boolean mo3766dispatchKeyEventYhN2O0w(android.view.KeyEvent r17, kotlin.jvm.functions.Function0<java.lang.Boolean> r18) {
        /*
            Method dump skipped, instruction units count: 1019
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.mo3766dispatchKeyEventYhN2O0w(android.view.KeyEvent, kotlin.jvm.functions.Function0):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v26, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v46 */
    /* JADX WARN: Type inference failed for: r0v47 */
    /* JADX WARN: Type inference failed for: r0v48 */
    /* JADX WARN: Type inference failed for: r0v49 */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v8, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v9, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v20, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because "result" is null
        	at jadx.core.dex.visitors.PrepareForCodeGen.removeInstructions(PrepareForCodeGen.java:118)
        	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:85)
        */
    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: dispatchInterceptedSoftKeyboardEvent-ZmokQxo */
    public boolean mo3765dispatchInterceptedSoftKeyboardEventZmokQxo(android.view.KeyEvent r14) {
        /*
            Method dump skipped, instruction units count: 612
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.mo3765dispatchInterceptedSoftKeyboardEventZmokQxo(android.view.KeyEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v23, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v43 */
    /* JADX WARN: Type inference failed for: r0v44 */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r15v11 */
    /* JADX WARN: Type inference failed for: r15v12, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r15v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r15v14 */
    /* JADX WARN: Type inference failed for: r15v15 */
    /* JADX WARN: Type inference failed for: r15v16 */
    /* JADX WARN: Type inference failed for: r15v17 */
    /* JADX WARN: Type inference failed for: r15v18 */
    /* JADX WARN: Type inference failed for: r15v19 */
    /* JADX WARN: Type inference failed for: r15v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r15v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v19, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because "result" is null
        	at jadx.core.dex.visitors.PrepareForCodeGen.removeInstructions(PrepareForCodeGen.java:118)
        	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:85)
        */
    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean dispatchRotaryEvent(androidx.compose.ui.input.rotary.RotaryScrollEvent r14, kotlin.jvm.functions.Function0<java.lang.Boolean> r15) {
        /*
            Method dump skipped, instruction units count: 623
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.dispatchRotaryEvent(androidx.compose.ui.input.rotary.RotaryScrollEvent, kotlin.jvm.functions.Function0):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v23, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v43 */
    /* JADX WARN: Type inference failed for: r0v44 */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r15v11 */
    /* JADX WARN: Type inference failed for: r15v12, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r15v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r15v14 */
    /* JADX WARN: Type inference failed for: r15v15 */
    /* JADX WARN: Type inference failed for: r15v16 */
    /* JADX WARN: Type inference failed for: r15v17 */
    /* JADX WARN: Type inference failed for: r15v18 */
    /* JADX WARN: Type inference failed for: r15v19 */
    /* JADX WARN: Type inference failed for: r15v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r15v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v19, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because "result" is null
        	at jadx.core.dex.visitors.PrepareForCodeGen.removeInstructions(PrepareForCodeGen.java:118)
        	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:85)
        */
    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean dispatchIndirectTouchEvent(androidx.compose.ui.input.indirect.IndirectTouchEvent r14, kotlin.jvm.functions.Function0<java.lang.Boolean> r15) {
        /*
            Method dump skipped, instruction units count: 623
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.dispatchIndirectTouchEvent(androidx.compose.ui.input.indirect.IndirectTouchEvent, kotlin.jvm.functions.Function0):boolean");
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusTargetNode node) {
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusEventModifierNode node) {
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidationForOwner() {
        this.focusInvalidationManager.scheduleInvalidation();
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public Rect getFocusRect() {
        FocusTargetNode focusTargetNodeFindFocusTargetNode = findFocusTargetNode();
        if (focusTargetNodeFindFocusTargetNode != null) {
            return FocusTraversalKt.focusRect(focusTargetNodeFindFocusTargetNode);
        }
        return null;
    }

    private final FocusTargetNode findFocusTargetNode() {
        return FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public FocusState getRootState() {
        return this.rootFocusNode.getFocusState();
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public MutableObjectList<FocusListener> getListeners() {
        return this.listeners;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public FocusTargetNode getActiveFocusTargetNode() {
        return this.activeFocusTargetNode;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void setActiveFocusTargetNode(FocusTargetNode focusTargetNode) {
        FocusTargetNode focusTargetNode2 = this.activeFocusTargetNode;
        this.activeFocusTargetNode = focusTargetNode;
        if (focusTargetNode == null || focusTargetNode2 != focusTargetNode) {
            setFocusCaptured(false);
        }
        if (ComposeUiFlags.isSemanticAutofillEnabled) {
            MutableObjectList<FocusListener> listeners = getListeners();
            Object[] objArr = listeners.content;
            int i = listeners._size;
            for (int i2 = 0; i2 < i; i2++) {
                ((FocusListener) objArr[i2]).onFocusChanged(focusTargetNode2, focusTargetNode);
            }
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: isFocusCaptured, reason: from getter */
    public boolean getIsFocusCaptured() {
        return this.isFocusCaptured;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void setFocusCaptured(boolean z) {
        if (!((z && getActiveFocusTargetNode() == null) ? false : true)) {
            InlineClassHelperKt.throwIllegalArgumentException("Cannot capture focus when the active focus target node is unset");
        }
        this.isFocusCaptured = z;
    }

    /* JADX INFO: renamed from: validateKeyEvent-ZmokQxo, reason: not valid java name */
    private final boolean m3773validateKeyEventZmokQxo(KeyEvent keyEvent) {
        long jM5302getKeyZmokQxo = KeyEvent_androidKt.m5302getKeyZmokQxo(keyEvent);
        int iM5303getTypeZmokQxo = KeyEvent_androidKt.m5303getTypeZmokQxo(keyEvent);
        if (KeyEventType.m5295equalsimpl0(iM5303getTypeZmokQxo, KeyEventType.INSTANCE.m5299getKeyDownCS__XNY())) {
            MutableLongSet mutableLongSet = this.keysCurrentlyDown;
            if (mutableLongSet == null) {
                mutableLongSet = new MutableLongSet(3);
                this.keysCurrentlyDown = mutableLongSet;
            }
            mutableLongSet.plusAssign(jM5302getKeyZmokQxo);
        } else if (KeyEventType.m5295equalsimpl0(iM5303getTypeZmokQxo, KeyEventType.INSTANCE.m5300getKeyUpCS__XNY())) {
            MutableLongSet mutableLongSet2 = this.keysCurrentlyDown;
            if (mutableLongSet2 == null || !mutableLongSet2.contains(jM5302getKeyZmokQxo)) {
                return false;
            }
            MutableLongSet mutableLongSet3 = this.keysCurrentlyDown;
            if (mutableLongSet3 != null) {
                mutableLongSet3.remove(jM5302getKeyZmokQxo);
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: traverseAncestorsIncludingSelf-QFhIj7k, reason: not valid java name */
    private final /* synthetic */ <T extends DelegatableNode> void m3772traverseAncestorsIncludingSelfQFhIj7k(DelegatableNode delegatableNode, int i, Function1<? super T, Unit> function1, Function0<Unit> function0, Function1<? super T, Unit> function2) {
        int i2;
        int size;
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(delegatableNode);
        ArrayList arrayList = null;
        while (true) {
            if (layoutNodeRequireLayoutNode == null) {
                break;
            }
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & i) != 0) {
                        Modifier.Node nodePop = parent;
                        MutableVector mutableVector = null;
                        while (nodePop != null) {
                            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                            if (nodePop instanceof Object) {
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                }
                                arrayList.add(nodePop);
                            } else if ((nodePop.getKindSet() & i) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i3 = 0;
                                for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                    if ((delegate.getKindSet() & i) != 0) {
                                        i3++;
                                        if (i3 == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(nodePop);
                                                }
                                                nodePop = null;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(delegate);
                                            }
                                        }
                                    }
                                }
                                if (i3 == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        if (arrayList != null && (size = arrayList.size() - 1) >= 0) {
            while (true) {
                int i4 = size - 1;
                function1.invoke((Object) arrayList.get(size));
                if (i4 < 0) {
                    break;
                } else {
                    size = i4;
                }
            }
        }
        Modifier.Node node = delegatableNode.getNode();
        MutableVector mutableVector2 = null;
        while (node != null) {
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (node instanceof Object) {
                function1.invoke(node);
            } else if ((node.getKindSet() & i) != 0 && (node instanceof DelegatingNode)) {
                int i5 = 0;
                for (Modifier.Node delegate2 = ((DelegatingNode) node).getDelegate(); delegate2 != null; delegate2 = delegate2.getChild()) {
                    if ((delegate2.getKindSet() & i) != 0) {
                        i5++;
                        if (i5 == 1) {
                            node = delegate2;
                        } else {
                            if (mutableVector2 == null) {
                                mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                            }
                            if (node != null) {
                                if (mutableVector2 != null) {
                                    mutableVector2.add(node);
                                }
                                node = null;
                            }
                            if (mutableVector2 != null) {
                                mutableVector2.add(delegate2);
                            }
                        }
                    }
                }
                if (i5 == 1) {
                }
            }
            node = DelegatableNodeKt.pop(mutableVector2);
        }
        function0.invoke();
        Modifier.Node node2 = delegatableNode.getNode();
        MutableVector mutableVector3 = null;
        while (node2 != null) {
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (node2 instanceof Object) {
                function2.invoke(node2);
            } else if ((node2.getKindSet() & i) != 0 && (node2 instanceof DelegatingNode)) {
                int i6 = 0;
                for (Modifier.Node delegate3 = ((DelegatingNode) node2).getDelegate(); delegate3 != null; delegate3 = delegate3.getChild()) {
                    if ((delegate3.getKindSet() & i) != 0) {
                        i6++;
                        if (i6 == 1) {
                            node2 = delegate3;
                        } else {
                            if (mutableVector3 == null) {
                                mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                            }
                            if (node2 != null) {
                                if (mutableVector3 != null) {
                                    mutableVector3.add(node2);
                                }
                                node2 = null;
                            }
                            if (mutableVector3 != null) {
                                mutableVector3.add(delegate3);
                            }
                        }
                    }
                }
                if (i6 == 1) {
                }
            }
            node2 = DelegatableNodeKt.pop(mutableVector3);
        }
        if (arrayList != null) {
            int size2 = arrayList.size();
            for (i2 = 0; i2 < size2; i2++) {
                function2.invoke((Object) arrayList.get(i2));
            }
        }
    }

    /* JADX INFO: renamed from: nearestAncestorIncludingSelf-64DMado, reason: not valid java name */
    private final /* synthetic */ <T> T m3771nearestAncestorIncludingSelf64DMado(DelegatableNode delegatableNode, int i) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = delegatableNode.getNode();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & i) != 0) {
                        Object obj = (T) node;
                        MutableVector mutableVector = null;
                        while (obj != null) {
                            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                            if (obj instanceof Object) {
                                return (T) obj;
                            }
                            if ((((Modifier.Node) obj).getKindSet() & i) != 0 && (obj instanceof DelegatingNode)) {
                                int i2 = 0;
                                for (Modifier.Node delegate = obj.getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                    if ((delegate.getKindSet() & i) != 0) {
                                        i2++;
                                        if (i2 == 1) {
                                            obj = (T) delegate;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (obj != null) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(obj);
                                                }
                                                obj = (T) null;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(delegate);
                                            }
                                        }
                                    }
                                }
                                if (i2 == 1) {
                                }
                            }
                            obj = (T) DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return null;
    }

    private final Modifier.Node lastLocalKeyInputNode(DelegatableNode delegatableNode) {
        int iM5881constructorimpl = NodeKind.m5881constructorimpl(1024) | NodeKind.m5881constructorimpl(8192);
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node node = delegatableNode.getNode();
        Modifier.Node node2 = null;
        if ((node.getAggregateChildKindSet() & iM5881constructorimpl) != 0) {
            for (Modifier.Node child = node.getChild(); child != null; child = child.getChild()) {
                if ((child.getKindSet() & iM5881constructorimpl) != 0) {
                    if ((NodeKind.m5881constructorimpl(1024) & child.getKindSet()) != 0) {
                        return node2;
                    }
                    node2 = child;
                }
            }
        }
        return node2;
    }
}
