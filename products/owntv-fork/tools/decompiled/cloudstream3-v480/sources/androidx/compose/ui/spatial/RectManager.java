package androidx.compose.ui.spatial;

import android.os.Trace;
import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Actual_androidKt;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.MatrixKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.MeasurePassDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: RectManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0019\u001a\u00020\u0010J5\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b#\u0010$J\u0006\u0010%\u001a\u00020\u0010J\u000e\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u0012J\u0016\u0010(\u001a\u0004\u0018\u00010\u00012\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ:\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020!2\u0006\u0010-\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u0002002\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\u001001J:\u00103\u001a\u00020+2\u0006\u0010,\u001a\u00020!2\u0006\u0010-\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u0002002\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\u001001J\u0010\u00104\u001a\u00020\u00102\b\u00105\u001a\u0004\u0018\u00010\u0001J\u000e\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u0004J\u001e\u00108\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00122\u0006\u0010:\u001a\u00020\u0012J\u000e\u0010;\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u0004J\u0016\u0010<\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u0012J\u0010\u0010>\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u0004H\u0002J\u0010\u0010?\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u0004H\u0002J\u0018\u0010B\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u0012H\u0002J8\u0010C\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00122\u0006\u0010D\u001a\u00020!2\u0006\u0010E\u001a\u00020!2\u0006\u0010F\u001a\u00020!2\u0006\u0010G\u001a\u00020!H\u0002J\u0014\u0010H\u001a\u00020\u0010*\u00020I2\u0006\u0010J\u001a\u00020AH\u0002J\u001b\u0010K\u001a\u00020\u001c*\u00020I2\u0006\u0010L\u001a\u00020\u001cH\u0002¢\u0006\u0004\bM\u0010NJ\u0013\u0010O\u001a\u00020\u001c*\u00020\u0004H\u0002¢\u0006\u0004\bP\u0010QJ\u000e\u0010R\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u0004J\u001d\u0010S\u001a\u00020\u00122\u0006\u0010T\u001a\u00020!2\u0006\u0010U\u001a\u00020!H\u0000¢\u0006\u0002\bVR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006W"}, d2 = {"Landroidx/compose/ui/spatial/RectManager;", "", "layoutNodes", "Landroidx/collection/IntObjectMap;", "Landroidx/compose/ui/node/LayoutNode;", "<init>", "(Landroidx/collection/IntObjectMap;)V", "rects", "Landroidx/compose/ui/spatial/RectList;", "getRects", "()Landroidx/compose/ui/spatial/RectList;", "throttledCallbacks", "Landroidx/compose/ui/spatial/ThrottledCallbacks;", "callbacks", "Landroidx/collection/MutableObjectList;", "Lkotlin/Function0;", "", "isDirty", "", "isScreenOrWindowDirty", "isFragmented", "dispatchToken", "scheduledDispatchDeadline", "", "dispatchLambda", "invalidate", "updateOffsets", "screenOffset", "Landroidx/compose/ui/unit/IntOffset;", "windowOffset", "viewToWindowMatrix", "Landroidx/compose/ui/graphics/Matrix;", "windowWidth", "", "windowHeight", "updateOffsets-gTq6Wqs", "(JJ[FII)V", "dispatchCallbacks", "scheduleDebounceCallback", "ensureSomethingScheduled", "registerOnChangedCallback", "callback", "registerOnRectChangedCallback", "Landroidx/compose/ui/node/DelegatableNode$RegistrationHandle;", "id", "throttleMillis", "debounceMillis", "node", "Landroidx/compose/ui/node/DelegatableNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "registerOnGlobalLayoutCallback", "unregisterOnChangedCallback", "token", "invalidateCallbacksFor", "layoutNode", "updateFlagsFor", "focusable", "gesturable", "onLayoutLayerPositionalPropertiesChanged", "onLayoutPositionChanged", "firstPlacement", "recalculateOffsetFromRoot", "insertOrUpdateTransformedNodeSubhierarchy", "cachedRect", "Landroidx/compose/ui/geometry/MutableRect;", "insertOrUpdateTransformedNode", "insertOrUpdate", CmcdData.STREAM_TYPE_LIVE, "t", "r", "b", "boundingRectInRoot", "Landroidx/compose/ui/node/NodeCoordinator;", "rect", "applyLayerTransformation", "position", "applyLayerTransformation-2IdBmHc", "(Landroidx/compose/ui/node/NodeCoordinator;J)J", "outerToInnerOffset", "outerToInnerOffset-Bjo55l4", "(Landroidx/compose/ui/node/LayoutNode;)J", "remove", "isTargetDrawnFirst", "targetId", "otherId", "isTargetDrawnFirst$ui_release", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RectManager {
    public static final int $stable = 8;
    private final MutableRect cachedRect;
    private final MutableObjectList<Function0<Unit>> callbacks;
    private final Function0<Unit> dispatchLambda;
    private Object dispatchToken;
    private boolean isDirty;
    private boolean isFragmented;
    private boolean isScreenOrWindowDirty;
    private final IntObjectMap<LayoutNode> layoutNodes;
    private final RectList rects;
    private long scheduledDispatchDeadline;
    private final ThrottledCallbacks throttledCallbacks;

    /* JADX WARN: Multi-variable type inference failed */
    public RectManager() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public RectManager(IntObjectMap<LayoutNode> intObjectMap) {
        this.layoutNodes = intObjectMap;
        this.rects = new RectList();
        this.throttledCallbacks = new ThrottledCallbacks();
        this.callbacks = new MutableObjectList<>(0, 1, null);
        this.scheduledDispatchDeadline = -1L;
        this.dispatchLambda = new Function0<Unit>() { // from class: androidx.compose.ui.spatial.RectManager$dispatchLambda$1
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
                this.this$0.dispatchToken = null;
                RectManager rectManager = this.this$0;
                Trace.beginSection("OnPositionedDispatch");
                try {
                    rectManager.dispatchCallbacks();
                    Unit unit = Unit.INSTANCE;
                } finally {
                    Trace.endSection();
                }
            }
        };
        this.cachedRect = new MutableRect(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public /* synthetic */ RectManager(IntObjectMap intObjectMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? IntObjectMapKt.intObjectMapOf() : intObjectMap);
    }

    public final RectList getRects() {
        return this.rects;
    }

    public final void invalidate() {
        this.isDirty = true;
    }

    /* JADX INFO: renamed from: updateOffsets-gTq6Wqs, reason: not valid java name */
    public final void m6138updateOffsetsgTq6Wqs(long screenOffset, long windowOffset, float[] viewToWindowMatrix, int windowWidth, int windowHeight) {
        int iM6141analyzeComponents58bKbWc = RectManagerKt.m6141analyzeComponents58bKbWc(viewToWindowMatrix);
        ThrottledCallbacks throttledCallbacks = this.throttledCallbacks;
        if ((iM6141analyzeComponents58bKbWc & 2) != 0) {
            viewToWindowMatrix = null;
        }
        this.isScreenOrWindowDirty = throttledCallbacks.m6155updateOffsetsLDcG7Xg(screenOffset, windowOffset, viewToWindowMatrix, windowWidth, windowHeight) || this.isScreenOrWindowDirty;
    }

    public final void dispatchCallbacks() {
        long[] jArr;
        final long jCurrentTimeMillis = Actual_androidKt.currentTimeMillis();
        boolean z = this.isDirty;
        int i = 0;
        boolean z2 = z || this.isScreenOrWindowDirty;
        if (z) {
            this.isDirty = false;
            MutableObjectList<Function0<Unit>> mutableObjectList = this.callbacks;
            Object[] objArr = mutableObjectList.content;
            int i2 = mutableObjectList._size;
            for (int i3 = 0; i3 < i2; i3++) {
                ((Function0) objArr[i3]).invoke();
            }
            RectList rectList = this.rects;
            long[] jArr2 = rectList.items;
            int i4 = rectList.itemsSize;
            for (int i5 = 0; i5 < jArr2.length - 2 && i5 < i4; i5 += 3) {
                long j = jArr2[i5 + 2];
                if ((((int) (j >> 61)) & 1) != 0) {
                    this.throttledCallbacks.fireOnUpdatedRect(67108863 & ((int) j), jArr2[i5], jArr2[i5 + 1], jCurrentTimeMillis);
                }
            }
            MutableIntObjectMap<ThrottledCallbacks.Entry> rectChangedMap = this.throttledCallbacks.getRectChangedMap();
            Object[] objArr2 = rectChangedMap.values;
            long[] jArr3 = rectChangedMap.metadata;
            int length = jArr3.length - 2;
            if (length >= 0) {
                int i6 = 0;
                while (true) {
                    long j2 = jArr3[i6];
                    if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i7 = 8;
                        int i8 = 8 - ((~(i6 - length)) >>> 31);
                        int i9 = i;
                        while (i9 < i8) {
                            if ((255 & j2) < 128) {
                                final ThrottledCallbacks.Entry entry = (ThrottledCallbacks.Entry) objArr2[(i6 << 3) + i9];
                                ThrottledCallbacks.Entry next = entry;
                                while (next != null) {
                                    if (entry.getLastInvokeMillis() == Long.MIN_VALUE) {
                                        this.rects.withTopLeftBottomRight(entry.getId(), new Function2<Long, Long, Unit>() { // from class: androidx.compose.ui.spatial.RectManager$dispatchCallbacks$3$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                                invoke(l.longValue(), l2.longValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(long j3, long j4) {
                                                this.this$0.throttledCallbacks.fireWithUpdatedRect$ui_release(entry, j3, j4, jCurrentTimeMillis);
                                            }
                                        });
                                    }
                                    next = next.getNext();
                                    i7 = i7;
                                    jArr3 = jArr3;
                                }
                            }
                            int i10 = i7;
                            j2 >>= i10;
                            i9++;
                            i7 = i10;
                            jArr3 = jArr3;
                        }
                        jArr = jArr3;
                        if (i8 != i7) {
                            break;
                        }
                    } else {
                        jArr = jArr3;
                    }
                    if (i6 == length) {
                        break;
                    }
                    i6++;
                    jArr3 = jArr;
                    i = 0;
                }
            }
            this.rects.clearUpdated();
        }
        if (this.isScreenOrWindowDirty) {
            this.isScreenOrWindowDirty = false;
            this.throttledCallbacks.fireOnRectChangedEntries(jCurrentTimeMillis);
        }
        if (z2) {
            this.throttledCallbacks.fireGlobalChangeEntries(jCurrentTimeMillis);
        }
        if (this.isFragmented) {
            this.isFragmented = false;
            this.rects.defragment();
        }
        this.throttledCallbacks.triggerDebounced(jCurrentTimeMillis);
    }

    public final void scheduleDebounceCallback(boolean ensureSomethingScheduled) {
        boolean z = (ensureSomethingScheduled && this.dispatchToken == null) ? false : true;
        long minDebounceDeadline = this.throttledCallbacks.getMinDebounceDeadline();
        if (minDebounceDeadline >= 0 || !z) {
            if (this.scheduledDispatchDeadline == minDebounceDeadline && z) {
                return;
            }
            Object obj = this.dispatchToken;
            if (obj != null) {
                Actual_androidKt.removePost(obj);
            }
            long jCurrentTimeMillis = Actual_androidKt.currentTimeMillis();
            long jMax = Math.max(minDebounceDeadline, 16 + jCurrentTimeMillis);
            this.scheduledDispatchDeadline = jMax;
            this.dispatchToken = Actual_androidKt.postDelayed(jMax - jCurrentTimeMillis, this.dispatchLambda);
        }
    }

    public final Object registerOnChangedCallback(Function0<Unit> callback) {
        this.callbacks.add(callback);
        return callback;
    }

    public final DelegatableNode.RegistrationHandle registerOnRectChangedCallback(int id, long throttleMillis, long debounceMillis, DelegatableNode node, Function1<? super RelativeLayoutBounds, Unit> callback) {
        DelegatableNode.RegistrationHandle registrationHandleRegisterOnRectChanged = this.throttledCallbacks.registerOnRectChanged(id, throttleMillis, debounceMillis, node, callback);
        invalidate();
        scheduleDebounceCallback(true);
        return registrationHandleRegisterOnRectChanged;
    }

    public final DelegatableNode.RegistrationHandle registerOnGlobalLayoutCallback(int id, long throttleMillis, long debounceMillis, DelegatableNode node, Function1<? super RelativeLayoutBounds, Unit> callback) {
        return this.throttledCallbacks.registerOnGlobalChange(id, throttleMillis, debounceMillis, node, callback);
    }

    public final void unregisterOnChangedCallback(Object token) {
        if ((TypeIntrinsics.isFunctionOfArity(token, 0) ? (Function0) token : null) == null) {
            return;
        }
        this.callbacks.remove(token);
    }

    public final void invalidateCallbacksFor(LayoutNode layoutNode) {
        this.isDirty = true;
        this.rects.markUpdated(layoutNode.getSemanticsId());
        scheduleDebounceCallback(true);
    }

    public final void updateFlagsFor(LayoutNode layoutNode, boolean focusable, boolean gesturable) {
        if (layoutNode.isAttached()) {
            this.rects.updateFlagsFor(layoutNode.getSemanticsId(), focusable, gesturable);
        }
    }

    public final void onLayoutLayerPositionalPropertiesChanged(LayoutNode layoutNode) {
        if (ComposeUiFlags.isRectTrackingEnabled) {
            long jM6137outerToInnerOffsetBjo55l4 = m6137outerToInnerOffsetBjo55l4(layoutNode);
            if (RectManagerKt.m6142isSetgyyYBs(jM6137outerToInnerOffsetBjo55l4)) {
                layoutNode.m5796setOuterToInnerOffsetgyyYBs$ui_release(jM6137outerToInnerOffsetBjo55l4);
                layoutNode.setOuterToInnerOffsetDirty$ui_release(false);
                MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui_release();
                LayoutNode[] layoutNodeArr = mutableVector.content;
                int size = mutableVector.getSize();
                for (int i = 0; i < size; i++) {
                    onLayoutPositionChanged(layoutNodeArr[i], false);
                }
                invalidateCallbacksFor(layoutNode);
                return;
            }
            insertOrUpdateTransformedNodeSubhierarchy(layoutNode);
        }
    }

    public final void onLayoutPositionChanged(LayoutNode layoutNode, boolean firstPlacement) {
        if (ComposeUiFlags.isRectTrackingEnabled) {
            MeasurePassDelegate measurePassDelegate$ui_release = layoutNode.getMeasurePassDelegate$ui_release();
            int measuredWidth = measurePassDelegate$ui_release.getMeasuredWidth();
            int measuredHeight = measurePassDelegate$ui_release.getMeasuredHeight();
            long offsetFromRoot = layoutNode.getOffsetFromRoot();
            long lastSize = layoutNode.getLastSize();
            int i = (int) (lastSize >> 32);
            int i2 = (int) (lastSize & 4294967295L);
            recalculateOffsetFromRoot(layoutNode);
            long offsetFromRoot2 = layoutNode.getOffsetFromRoot();
            if (!RectManagerKt.m6142isSetgyyYBs(offsetFromRoot2)) {
                insertOrUpdateTransformedNode(layoutNode, firstPlacement);
                return;
            }
            layoutNode.m5794setLastSizeozmzZPI$ui_release(IntSize.m7060constructorimpl((((long) measuredWidth) << 32) | (4294967295L & ((long) measuredHeight))));
            int iM7022getXimpl = IntOffset.m7022getXimpl(offsetFromRoot2);
            int iM7023getYimpl = IntOffset.m7023getYimpl(offsetFromRoot2);
            int i3 = iM7022getXimpl + measuredWidth;
            int i4 = iM7023getYimpl + measuredHeight;
            if (!firstPlacement && IntOffset.m7021equalsimpl0(offsetFromRoot2, offsetFromRoot) && i == measuredWidth && i2 == measuredHeight) {
                return;
            }
            insertOrUpdate(layoutNode, firstPlacement, iM7022getXimpl, iM7023getYimpl, i3, i4);
        }
    }

    private final void recalculateOffsetFromRoot(LayoutNode layoutNode) {
        long outerToInnerOffset;
        NodeCoordinator outerCoordinator$ui_release = layoutNode.getOuterCoordinator$ui_release();
        long jM6136applyLayerTransformation2IdBmHc = m6136applyLayerTransformation2IdBmHc(outerCoordinator$ui_release, IntOffset.INSTANCE.m7033getZeronOccac());
        if (!RectManagerKt.m6142isSetgyyYBs(jM6136applyLayerTransformation2IdBmHc)) {
            layoutNode.m5795setOffsetFromRootgyyYBs$ui_release(IntOffset.INSTANCE.m7032getMaxnOccac());
            return;
        }
        long jM7026plusqkQi6aY = IntOffset.m7026plusqkQi6aY(jM6136applyLayerTransformation2IdBmHc, outerCoordinator$ui_release.getPosition());
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        if (parent$ui_release != null) {
            if (!RectManagerKt.m6142isSetgyyYBs(parent$ui_release.getOffsetFromRoot())) {
                recalculateOffsetFromRoot(parent$ui_release);
            }
            long offsetFromRoot = parent$ui_release.getOffsetFromRoot();
            if (!RectManagerKt.m6142isSetgyyYBs(offsetFromRoot)) {
                jM7026plusqkQi6aY = IntOffset.INSTANCE.m7032getMaxnOccac();
            } else {
                if (parent$ui_release.getOuterToInnerOffsetDirty()) {
                    outerToInnerOffset = m6137outerToInnerOffsetBjo55l4(parent$ui_release);
                    parent$ui_release.m5796setOuterToInnerOffsetgyyYBs$ui_release(outerToInnerOffset);
                    parent$ui_release.setOuterToInnerOffsetDirty$ui_release(false);
                } else {
                    outerToInnerOffset = parent$ui_release.getOuterToInnerOffset();
                }
                if (!RectManagerKt.m6142isSetgyyYBs(outerToInnerOffset)) {
                    jM7026plusqkQi6aY = IntOffset.INSTANCE.m7032getMaxnOccac();
                } else {
                    jM7026plusqkQi6aY = IntOffset.m7026plusqkQi6aY(IntOffset.m7026plusqkQi6aY(offsetFromRoot, outerToInnerOffset), jM7026plusqkQi6aY);
                }
            }
        }
        layoutNode.m5795setOffsetFromRootgyyYBs$ui_release(jM7026plusqkQi6aY);
    }

    private final void insertOrUpdateTransformedNode(LayoutNode layoutNode, boolean firstPlacement) {
        NodeCoordinator outerCoordinator$ui_release = layoutNode.getOuterCoordinator$ui_release();
        MeasurePassDelegate measurePassDelegate$ui_release = layoutNode.getMeasurePassDelegate$ui_release();
        int measuredWidth = measurePassDelegate$ui_release.getMeasuredWidth();
        int measuredHeight = measurePassDelegate$ui_release.getMeasuredHeight();
        MutableRect mutableRect = this.cachedRect;
        mutableRect.set(0.0f, 0.0f, measuredWidth, measuredHeight);
        boundingRectInRoot(outerCoordinator$ui_release, mutableRect);
        int left = (int) mutableRect.getLeft();
        int top = (int) mutableRect.getTop();
        int right = (int) mutableRect.getRight();
        int bottom = (int) mutableRect.getBottom();
        int semanticsId = layoutNode.getSemanticsId();
        if (firstPlacement || !this.rects.update(semanticsId, left, top, right, bottom)) {
            LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
            this.rects.insert(semanticsId, left, top, right, bottom, parent$ui_release != null ? parent$ui_release.getSemanticsId() : -1, layoutNode.getNodes().m5842hasH91voCI$ui_release(NodeKind.m5881constructorimpl(1024)), layoutNode.getNodes().m5842hasH91voCI$ui_release(NodeKind.m5881constructorimpl(16)));
        }
        invalidate();
    }

    private final void insertOrUpdate(LayoutNode layoutNode, boolean firstPlacement, int l, int t, int r, int b) {
        int i;
        int i2;
        int i3;
        int i4;
        int semanticsId = layoutNode.getSemanticsId();
        if (!firstPlacement) {
            i = l;
            i2 = t;
            i3 = r;
            i4 = b;
            if (!this.rects.move(semanticsId, i, i2, i3, i4)) {
            }
            invalidate();
        }
        i = l;
        i2 = t;
        i3 = r;
        i4 = b;
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        this.rects.insert(semanticsId, i, i2, i3, i4, parent$ui_release != null ? parent$ui_release.getSemanticsId() : -1, layoutNode.getNodes().m5842hasH91voCI$ui_release(NodeKind.m5881constructorimpl(1024)), layoutNode.getNodes().m5842hasH91voCI$ui_release(NodeKind.m5881constructorimpl(16)));
        invalidate();
    }

    private final void boundingRectInRoot(NodeCoordinator nodeCoordinator, MutableRect mutableRect) {
        while (nodeCoordinator != null) {
            OwnedLayer layer = nodeCoordinator.getLayer();
            if (layer != null) {
                float[] fArrMo5934getUnderlyingMatrixsQKQjiQ = layer.mo5934getUnderlyingMatrixsQKQjiQ();
                if (!MatrixKt.m4427isIdentity58bKbWc(fArrMo5934getUnderlyingMatrixsQKQjiQ)) {
                    Matrix.m4410mapimpl(fArrMo5934getUnderlyingMatrixsQKQjiQ, mutableRect);
                }
            }
            long position = nodeCoordinator.getPosition();
            mutableRect.m3855translatek4lQ0M(Offset.m3862constructorimpl((((long) Float.floatToRawIntBits(IntOffset.m7022getXimpl(position))) << 32) | (((long) Float.floatToRawIntBits(IntOffset.m7023getYimpl(position))) & 4294967295L)));
            nodeCoordinator = nodeCoordinator.getWrappedBy();
        }
    }

    /* JADX INFO: renamed from: applyLayerTransformation-2IdBmHc, reason: not valid java name */
    private final long m6136applyLayerTransformation2IdBmHc(NodeCoordinator nodeCoordinator, long j) {
        OwnedLayer layer = nodeCoordinator.getLayer();
        if (layer == null) {
            return j;
        }
        float[] fArrMo5934getUnderlyingMatrixsQKQjiQ = layer.mo5934getUnderlyingMatrixsQKQjiQ();
        int iM6141analyzeComponents58bKbWc = RectManagerKt.m6141analyzeComponents58bKbWc(fArrMo5934getUnderlyingMatrixsQKQjiQ);
        if (iM6141analyzeComponents58bKbWc == 3) {
            return j;
        }
        if ((iM6141analyzeComponents58bKbWc & 2) == 0) {
            return IntOffset.INSTANCE.m7032getMaxnOccac();
        }
        float fM7022getXimpl = IntOffset.m7022getXimpl(j);
        return IntOffsetKt.m7039roundk4lQ0M(Matrix.m4408mapMKHz9U(fArrMo5934getUnderlyingMatrixsQKQjiQ, Offset.m3862constructorimpl((((long) Float.floatToRawIntBits(IntOffset.m7023getYimpl(j))) & 4294967295L) | (Float.floatToRawIntBits(fM7022getXimpl) << 32))));
    }

    /* JADX INFO: renamed from: outerToInnerOffset-Bjo55l4, reason: not valid java name */
    private final long m6137outerToInnerOffsetBjo55l4(LayoutNode layoutNode) {
        NodeCoordinator outerCoordinator$ui_release = layoutNode.getOuterCoordinator$ui_release();
        long jM7033getZeronOccac = IntOffset.INSTANCE.m7033getZeronOccac();
        for (NodeCoordinator innerCoordinator$ui_release = layoutNode.getInnerCoordinator$ui_release(); innerCoordinator$ui_release != null && innerCoordinator$ui_release != outerCoordinator$ui_release; innerCoordinator$ui_release = innerCoordinator$ui_release.getWrappedBy()) {
            long jM6136applyLayerTransformation2IdBmHc = m6136applyLayerTransformation2IdBmHc(innerCoordinator$ui_release, jM7033getZeronOccac);
            if (IntOffset.m7021equalsimpl0(jM6136applyLayerTransformation2IdBmHc, IntOffset.INSTANCE.m7032getMaxnOccac())) {
                return IntOffset.INSTANCE.m7032getMaxnOccac();
            }
            jM7033getZeronOccac = IntOffset.m7026plusqkQi6aY(jM6136applyLayerTransformation2IdBmHc, innerCoordinator$ui_release.getPosition());
        }
        return jM7033getZeronOccac;
    }

    public final void remove(LayoutNode layoutNode) {
        this.rects.remove(layoutNode.getSemanticsId());
        invalidate();
        this.isFragmented = true;
    }

    public final boolean isTargetDrawnFirst$ui_release(int targetId, int otherId) {
        LayoutNode parent$ui_release;
        LayoutNode parent$ui_release2;
        LayoutNode parent$ui_release3 = this.layoutNodes.get(targetId);
        if (parent$ui_release3 != null && (parent$ui_release = this.layoutNodes.get(otherId)) != null && parent$ui_release3.getDepth() != 0 && parent$ui_release.getDepth() != 0) {
            while (parent$ui_release3.getDepth() > parent$ui_release.getDepth()) {
                parent$ui_release3 = parent$ui_release3.getParent$ui_release();
                if (parent$ui_release3 == null) {
                    return false;
                }
            }
            if (parent$ui_release3 == parent$ui_release) {
                return false;
            }
            while (parent$ui_release.getDepth() > parent$ui_release3.getDepth()) {
                parent$ui_release = parent$ui_release.getParent$ui_release();
                if (parent$ui_release == null) {
                    return false;
                }
            }
            if (parent$ui_release3 == parent$ui_release) {
                return false;
            }
            LayoutNode layoutNode = parent$ui_release3;
            LayoutNode layoutNode2 = layoutNode;
            LayoutNode layoutNode3 = parent$ui_release;
            while (layoutNode != parent$ui_release) {
                LayoutNode parent$ui_release4 = layoutNode.getParent$ui_release();
                if (parent$ui_release4 == null || (parent$ui_release2 = parent$ui_release.getParent$ui_release()) == null) {
                    return false;
                }
                layoutNode3 = parent$ui_release;
                parent$ui_release = parent$ui_release2;
                layoutNode2 = layoutNode;
                layoutNode = parent$ui_release4;
            }
            if (layoutNode2.getMeasurePassDelegate$ui_release().getZIndex() == layoutNode3.getMeasurePassDelegate$ui_release().getZIndex()) {
                return layoutNode2.getPlaceOrder$ui_release() < layoutNode3.getPlaceOrder$ui_release();
            }
            if (layoutNode2.getMeasurePassDelegate$ui_release().getZIndex() < layoutNode3.getMeasurePassDelegate$ui_release().getZIndex()) {
                return true;
            }
        }
        return false;
    }

    private final void insertOrUpdateTransformedNodeSubhierarchy(LayoutNode layoutNode) {
        MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui_release();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode2 = layoutNodeArr[i];
            insertOrUpdateTransformedNode(layoutNode2, false);
            insertOrUpdateTransformedNodeSubhierarchy(layoutNode2);
        }
    }
}
