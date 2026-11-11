package androidx.compose.ui.spatial;

import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ThrottledCallbacks.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u001f\b\u0001\u0018\u00002\u00020\u0001:\u0001ZB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J7\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\u00152\b\u0010*\u001a\u0004\u0018\u00010 2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,¢\u0006\u0004\b.\u0010/J\u0010\u00100\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000fH\u0002J:\u00102\u001a\u0002032\u0006\u00104\u001a\u00020,2\u0006\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u000f2\u0006\u00107\u001a\u0002082\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020<0:J:\u0010=\u001a\u0002032\u0006\u00104\u001a\u00020,2\u0006\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u000f2\u0006\u00107\u001a\u0002082\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020<0:J&\u0010>\u001a\u00020<2\u0006\u00104\u001a\u00020,2\u0006\u0010?\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u000f2\u0006\u0010A\u001a\u00020\u000fJ!\u0010B\u001a\u00020<2\u0016\u00109\u001a\u0012\u0012\b\u0012\u00060\u0006R\u00020\u0000\u0012\u0004\u0012\u00020<0:H\u0086\bJ\u000e\u0010C\u001a\u00020<2\u0006\u0010A\u001a\u00020\u000fJ\u000e\u0010D\u001a\u00020<2\u0006\u0010A\u001a\u00020\u000fJ\u000e\u0010E\u001a\u00020<2\u0006\u0010A\u001a\u00020\u000fJ1\u0010F\u001a\u00020<2\n\u0010G\u001a\u00060\u0006R\u00020\u00002\u0006\u0010?\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u000f2\u0006\u0010A\u001a\u00020\u000fH\u0000¢\u0006\u0002\bHJ=\u0010I\u001a\u00020<2\n\u0010G\u001a\u00060\u0006R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00152\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010A\u001a\u00020\u000fH\u0002¢\u0006\u0004\bJ\u0010KJE\u0010L\u001a\u00020\u000f2\n\u0010G\u001a\u00060\u0006R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00152\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010A\u001a\u00020\u000f2\u0006\u0010M\u001a\u00020\u000fH\u0002¢\u0006\u0004\bN\u0010OJ\u0014\u0010P\u001a\u00020<2\n\u0010G\u001a\u00060\u0006R\u00020\u0000H\u0002J\u0014\u0010Q\u001a\u00020'2\n\u0010G\u001a\u00060\u0006R\u00020\u0000H\u0002J)\u0010R\u001a\u00020<*\u00060\u0006R\u00020\u00002\u0016\u0010S\u001a\u0012\u0012\b\u0012\u00060\u0006R\u00020\u0000\u0012\u0004\u0012\u00020<0:H\u0082\bJ/\u0010T\u001a\u00020<*\f\u0012\b\u0012\u00060\u0006R\u00020\u00000\u00052\u0016\u0010S\u001a\u0012\u0012\b\u0012\u00060\u0006R\u00020\u0000\u0012\u0004\u0012\u00020<0:H\u0082\bJ7\u0010U\u001a\u00020<*\f\u0012\b\u0012\u00060\u0006R\u00020\u00000\u00052\u0006\u00104\u001a\u00020,2\u0016\u0010S\u001a\u0012\u0012\b\u0012\u00060\u0006R\u00020\u0000\u0012\u0004\u0012\u00020<0:H\u0082\bJ.\u0010V\u001a\u00060\u0006R\u00020\u0000*\f\u0012\b\u0012\u00060\u0006R\u00020\u00000\u00052\u0006\u0010W\u001a\u00020,2\n\u0010X\u001a\u00060\u0006R\u00020\u0000H\u0002J*\u0010Y\u001a\u00020'*\f\u0012\b\u0012\u00060\u0006R\u00020\u00000\u00052\u0006\u0010W\u001a\u00020,2\n\u0010X\u001a\u00060\u0006R\u00020\u0000H\u0002R\u001b\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006R\u00020\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0018\u00010\u0006R\u00020\u0000X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001c\u0010\u0019\u001a\u00020\u0015X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u001a\u0010\u0011\"\u0004\b\u001b\u0010\u0013R\u001a\u0010\u001c\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0011\"\u0004\b\u001e\u0010\u0013R\u001e\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006["}, d2 = {"Landroidx/compose/ui/spatial/ThrottledCallbacks;", "", "<init>", "()V", "rectChangedMap", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/compose/ui/spatial/ThrottledCallbacks$Entry;", "getRectChangedMap", "()Landroidx/collection/MutableIntObjectMap;", "globalChangeEntries", "getGlobalChangeEntries", "()Landroidx/compose/ui/spatial/ThrottledCallbacks$Entry;", "setGlobalChangeEntries", "(Landroidx/compose/ui/spatial/ThrottledCallbacks$Entry;)V", "minDebounceDeadline", "", "getMinDebounceDeadline", "()J", "setMinDebounceDeadline", "(J)V", "windowOffset", "Landroidx/compose/ui/unit/IntOffset;", "getWindowOffset-nOcc-ac", "setWindowOffset--gyyYBs", "J", "screenOffset", "getScreenOffset-nOcc-ac", "setScreenOffset--gyyYBs", "windowSize", "getWindowSize", "setWindowSize", "viewToWindowMatrix", "Landroidx/compose/ui/graphics/Matrix;", "getViewToWindowMatrix-3i98HWw", "()[F", "setViewToWindowMatrix-Q8lPUPs", "([F)V", "[F", "updateOffsets", "", "screen", "window", "matrix", "windowWidth", "", "windowHeight", "updateOffsets-LDcG7Xg", "(JJ[FII)Z", "roundDownToMultipleOf8", "x", "registerOnRectChanged", "Landroidx/compose/ui/node/DelegatableNode$RegistrationHandle;", "id", "throttleMillis", "debounceMillis", "node", "Landroidx/compose/ui/node/DelegatableNode;", "callback", "Lkotlin/Function1;", "Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "", "registerOnGlobalChange", "fireOnUpdatedRect", "topLeft", "bottomRight", "currentMillis", "forEachNewCallbackNeverInvoked", "fireOnRectChangedEntries", "fireGlobalChangeEntries", "triggerDebounced", "fireWithUpdatedRect", "entry", "fireWithUpdatedRect$ui_release", "fire", "fire-WY9HvpM", "(Landroidx/compose/ui/spatial/ThrottledCallbacks$Entry;JJ[FJ)V", "debounceEntry", "minDeadline", "debounceEntry-b8qMvQI", "(Landroidx/compose/ui/spatial/ThrottledCallbacks$Entry;JJ[FJJ)J", "addToGlobalEntries", "removeFromGlobalEntries", "linkedForEach", "block", "multiForEach", "runFor", "multiPut", "key", ES6Iterator.VALUE_PROPERTY, "multiRemove", "Entry", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ThrottledCallbacks {
    public static final int $stable = 8;
    private Entry globalChangeEntries;
    private float[] viewToWindowMatrix;
    private long windowSize;
    private final MutableIntObjectMap<Entry> rectChangedMap = IntObjectMapKt.mutableIntObjectMapOf();
    private long minDebounceDeadline = -1;
    private long windowOffset = IntOffset.INSTANCE.m7033getZeronOccac();
    private long screenOffset = IntOffset.INSTANCE.m7033getZeronOccac();

    private final long roundDownToMultipleOf8(long x) {
        return (x >> 3) << 3;
    }

    /* JADX INFO: compiled from: ThrottledCallbacks.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010+\u001a\u00020\fH\u0016J7\u0010,\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.2\b\u00100\u001a\u0004\u0018\u000101¢\u0006\u0004\b2\u00103R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R \u0010\u0018\u001a\b\u0018\u00010\u0000R\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0012\"\u0004\b$\u0010!R\u001a\u0010%\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0012\"\u0004\b'\u0010!R\u001a\u0010(\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0012\"\u0004\b*\u0010!¨\u00064"}, d2 = {"Landroidx/compose/ui/spatial/ThrottledCallbacks$Entry;", "Landroidx/compose/ui/node/DelegatableNode$RegistrationHandle;", "id", "", "throttleMillis", "", "debounceMillis", "node", "Landroidx/compose/ui/node/DelegatableNode;", "callback", "Lkotlin/Function1;", "Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "", "<init>", "(Landroidx/compose/ui/spatial/ThrottledCallbacks;IJJLandroidx/compose/ui/node/DelegatableNode;Lkotlin/jvm/functions/Function1;)V", "getId", "()I", "getThrottleMillis", "()J", "getDebounceMillis", "getNode", "()Landroidx/compose/ui/node/DelegatableNode;", "getCallback", "()Lkotlin/jvm/functions/Function1;", "next", "Landroidx/compose/ui/spatial/ThrottledCallbacks;", "getNext", "()Landroidx/compose/ui/spatial/ThrottledCallbacks$Entry;", "setNext", "(Landroidx/compose/ui/spatial/ThrottledCallbacks$Entry;)V", "topLeft", "getTopLeft", "setTopLeft", "(J)V", "bottomRight", "getBottomRight", "setBottomRight", "lastInvokeMillis", "getLastInvokeMillis", "setLastInvokeMillis", "lastUninvokedFireMillis", "getLastUninvokedFireMillis", "setLastUninvokedFireMillis", "unregister", "fire", "windowOffset", "Landroidx/compose/ui/unit/IntOffset;", "screenOffset", "viewToWindowMatrix", "Landroidx/compose/ui/graphics/Matrix;", "fire-9b-9wPM", "(JJJJ[F)V", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class Entry implements DelegatableNode.RegistrationHandle {
        private long bottomRight;
        private final Function1<RelativeLayoutBounds, Unit> callback;
        private final long debounceMillis;
        private final int id;
        private long lastInvokeMillis = Long.MIN_VALUE;
        private long lastUninvokedFireMillis = -1;
        private Entry next;
        private final DelegatableNode node;
        private final long throttleMillis;
        private long topLeft;

        /* JADX WARN: Multi-variable type inference failed */
        public Entry(int i, long j, long j2, DelegatableNode delegatableNode, Function1<? super RelativeLayoutBounds, Unit> function1) {
            this.id = i;
            this.throttleMillis = j;
            this.debounceMillis = j2;
            this.node = delegatableNode;
            this.callback = function1;
        }

        public final int getId() {
            return this.id;
        }

        public final long getThrottleMillis() {
            return this.throttleMillis;
        }

        public final long getDebounceMillis() {
            return this.debounceMillis;
        }

        public final DelegatableNode getNode() {
            return this.node;
        }

        public final Function1<RelativeLayoutBounds, Unit> getCallback() {
            return this.callback;
        }

        public final Entry getNext() {
            return this.next;
        }

        public final void setNext(Entry entry) {
            this.next = entry;
        }

        public final long getTopLeft() {
            return this.topLeft;
        }

        public final void setTopLeft(long j) {
            this.topLeft = j;
        }

        public final long getBottomRight() {
            return this.bottomRight;
        }

        public final void setBottomRight(long j) {
            this.bottomRight = j;
        }

        public final long getLastInvokeMillis() {
            return this.lastInvokeMillis;
        }

        public final void setLastInvokeMillis(long j) {
            this.lastInvokeMillis = j;
        }

        public final long getLastUninvokedFireMillis() {
            return this.lastUninvokedFireMillis;
        }

        public final void setLastUninvokedFireMillis(long j) {
            this.lastUninvokedFireMillis = j;
        }

        @Override // androidx.compose.ui.node.DelegatableNode.RegistrationHandle
        public void unregister() {
            ThrottledCallbacks throttledCallbacks = ThrottledCallbacks.this;
            if (throttledCallbacks.multiRemove(throttledCallbacks.getRectChangedMap(), this.id, this)) {
                return;
            }
            ThrottledCallbacks.this.removeFromGlobalEntries(this);
        }

        /* JADX INFO: renamed from: fire-9b-9wPM, reason: not valid java name */
        public final void m6156fire9b9wPM(long topLeft, long bottomRight, long windowOffset, long screenOffset, float[] viewToWindowMatrix) {
            RelativeLayoutBounds relativeLayoutBoundsM6157rectInfoForDg36KO4 = ThrottledCallbacksKt.m6157rectInfoForDg36KO4(this.node, topLeft, bottomRight, windowOffset, screenOffset, ThrottledCallbacks.this.getWindowSize(), viewToWindowMatrix);
            if (relativeLayoutBoundsM6157rectInfoForDg36KO4 == null) {
                return;
            }
            this.callback.invoke(relativeLayoutBoundsM6157rectInfoForDg36KO4);
        }
    }

    public final MutableIntObjectMap<Entry> getRectChangedMap() {
        return this.rectChangedMap;
    }

    public final Entry getGlobalChangeEntries() {
        return this.globalChangeEntries;
    }

    public final void setGlobalChangeEntries(Entry entry) {
        this.globalChangeEntries = entry;
    }

    public final long getMinDebounceDeadline() {
        return this.minDebounceDeadline;
    }

    public final void setMinDebounceDeadline(long j) {
        this.minDebounceDeadline = j;
    }

    /* JADX INFO: renamed from: getWindowOffset-nOcc-ac, reason: not valid java name and from getter */
    public final long getWindowOffset() {
        return this.windowOffset;
    }

    /* JADX INFO: renamed from: setWindowOffset--gyyYBs, reason: not valid java name */
    public final void m6154setWindowOffsetgyyYBs(long j) {
        this.windowOffset = j;
    }

    /* JADX INFO: renamed from: getScreenOffset-nOcc-ac, reason: not valid java name and from getter */
    public final long getScreenOffset() {
        return this.screenOffset;
    }

    /* JADX INFO: renamed from: setScreenOffset--gyyYBs, reason: not valid java name */
    public final void m6152setScreenOffsetgyyYBs(long j) {
        this.screenOffset = j;
    }

    public final long getWindowSize() {
        return this.windowSize;
    }

    public final void setWindowSize(long j) {
        this.windowSize = j;
    }

    /* JADX INFO: renamed from: getViewToWindowMatrix-3i98HWw, reason: not valid java name and from getter */
    public final float[] getViewToWindowMatrix() {
        return this.viewToWindowMatrix;
    }

    /* JADX INFO: renamed from: setViewToWindowMatrix-Q8lPUPs, reason: not valid java name */
    public final void m6153setViewToWindowMatrixQ8lPUPs(float[] fArr) {
        this.viewToWindowMatrix = fArr;
    }

    /* JADX INFO: renamed from: updateOffsets-LDcG7Xg, reason: not valid java name */
    public final boolean m6155updateOffsetsLDcG7Xg(long screen, long window, float[] matrix, int windowWidth, int windowHeight) {
        boolean z;
        if (IntOffset.m7021equalsimpl0(window, this.windowOffset)) {
            z = false;
        } else {
            this.windowOffset = window;
            z = true;
        }
        if (!IntOffset.m7021equalsimpl0(screen, this.screenOffset)) {
            this.screenOffset = screen;
            z = true;
        }
        if (matrix != null) {
            this.viewToWindowMatrix = matrix;
            z = true;
        }
        long j = (((long) windowWidth) << 32) | (((long) windowHeight) & 4294967295L);
        if (j == this.windowSize) {
            return z;
        }
        this.windowSize = j;
        return true;
    }

    public final DelegatableNode.RegistrationHandle registerOnRectChanged(int id, long throttleMillis, long debounceMillis, DelegatableNode node, Function1<? super RelativeLayoutBounds, Unit> callback) {
        return multiPut(this.rectChangedMap, id, new Entry(id, throttleMillis, debounceMillis == 0 ? throttleMillis : debounceMillis, node, callback));
    }

    public final DelegatableNode.RegistrationHandle registerOnGlobalChange(int id, long throttleMillis, long debounceMillis, DelegatableNode node, Function1<? super RelativeLayoutBounds, Unit> callback) {
        Entry entry = new Entry(id, throttleMillis, debounceMillis == 0 ? throttleMillis : debounceMillis, node, callback);
        addToGlobalEntries(entry);
        return entry;
    }

    public final void fireOnUpdatedRect(int id, long topLeft, long bottomRight, long currentMillis) {
        for (Entry next = this.rectChangedMap.get(id); next != null; next = next.getNext()) {
            fireWithUpdatedRect$ui_release(next, topLeft, bottomRight, currentMillis);
        }
    }

    public final void forEachNewCallbackNeverInvoked(Function1<? super Entry, Unit> callback) {
        MutableIntObjectMap<Entry> rectChangedMap = getRectChangedMap();
        Object[] objArr = rectChangedMap.values;
        long[] jArr = rectChangedMap.metadata;
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
                    if ((255 & j) < 128) {
                        Entry entry = (Entry) objArr[(i << 3) + i3];
                        for (Entry next = entry; next != null; next = next.getNext()) {
                            if (entry.getLastInvokeMillis() == Long.MIN_VALUE) {
                                callback.invoke(entry);
                            }
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

    public final void fireOnRectChangedEntries(long currentMillis) {
        ThrottledCallbacks throttledCallbacks = this;
        long j = throttledCallbacks.windowOffset;
        long j2 = throttledCallbacks.screenOffset;
        float[] fArr = throttledCallbacks.viewToWindowMatrix;
        MutableIntObjectMap<Entry> mutableIntObjectMap = throttledCallbacks.rectChangedMap;
        Object[] objArr = mutableIntObjectMap.values;
        long[] jArr = mutableIntObjectMap.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j3 = jArr[i];
            if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                long j4 = j3;
                int i3 = 0;
                while (i3 < i2) {
                    if ((j4 & 255) < 128) {
                        Entry next = (Entry) objArr[(i << 3) + i3];
                        while (next != null) {
                            int i4 = i3;
                            Entry entry = next;
                            throttledCallbacks.m6148fireWY9HvpM(entry, j, j2, fArr, currentMillis);
                            next = entry.getNext();
                            throttledCallbacks = this;
                            i3 = i4;
                        }
                    }
                    j4 >>= 8;
                    i3++;
                    throttledCallbacks = this;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            }
            i++;
            throttledCallbacks = this;
        }
    }

    public final void fireGlobalChangeEntries(long currentMillis) {
        long j = this.windowOffset;
        long j2 = this.screenOffset;
        float[] fArr = this.viewToWindowMatrix;
        Entry entry = this.globalChangeEntries;
        if (entry != null) {
            for (Entry next = entry; next != null; next = next.getNext()) {
                LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(next.getNode());
                long offsetFromRoot = layoutNodeRequireLayoutNode.getOffsetFromRoot();
                long lastSize = layoutNodeRequireLayoutNode.getLastSize();
                next.setTopLeft(offsetFromRoot);
                next.setBottomRight((((long) (IntOffset.m7022getXimpl(offsetFromRoot) + ((int) (lastSize >> 32)))) << 32) | (((long) (IntOffset.m7023getYimpl(offsetFromRoot) + ((int) (lastSize & 4294967295L)))) & 4294967295L));
                m6148fireWY9HvpM(next, j, j2, fArr, currentMillis);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x008d A[LOOP:0: B:8:0x0025->B:24:0x008d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:38:0x0097 A[EDGE_INSN: B:38:0x0097->B:26:0x0097 BREAK  A[LOOP:0: B:8:0x0025->B:24:0x008d], SYNTHETIC] */
    public final void triggerDebounced(long currentMillis) {
        long j;
        long jM6147debounceEntryb8qMvQI;
        int i;
        if (this.minDebounceDeadline > currentMillis) {
            return;
        }
        long j2 = this.windowOffset;
        long j3 = this.screenOffset;
        float[] fArr = this.viewToWindowMatrix;
        MutableIntObjectMap<Entry> mutableIntObjectMap = this.rectChangedMap;
        Object[] objArr = mutableIntObjectMap.values;
        long[] jArr = mutableIntObjectMap.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            jM6147debounceEntryb8qMvQI = Long.MAX_VALUE;
            while (true) {
                long j4 = jArr[i2];
                j = Long.MAX_VALUE;
                if ((((~j4) << 7) & j4 & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i2 != length) {
                        break;
                        break;
                    }
                    i2++;
                } else {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    long j5 = j4;
                    int i4 = 0;
                    while (i4 < i3) {
                        if ((j5 & 255) < 128) {
                            Entry next = (Entry) objArr[(i2 << 3) + i4];
                            while (next != null) {
                                int i5 = i2;
                                Entry entry = next;
                                jM6147debounceEntryb8qMvQI = m6147debounceEntryb8qMvQI(entry, j2, j3, fArr, currentMillis, jM6147debounceEntryb8qMvQI);
                                i4 = i4;
                                next = entry.getNext();
                                i2 = i5;
                            }
                            i = i4;
                        } else {
                            i = i4;
                        }
                        j5 >>= 8;
                        i4 = i + 1;
                        i2 = i2;
                    }
                    int i6 = i2;
                    if (i3 != 8) {
                        break;
                    }
                    i2 = i6;
                    if (i2 != length) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        } else {
            j = Long.MAX_VALUE;
            jM6147debounceEntryb8qMvQI = Long.MAX_VALUE;
        }
        Entry next2 = this.globalChangeEntries;
        if (next2 != null) {
            long jM6147debounceEntryb8qMvQI2 = jM6147debounceEntryb8qMvQI;
            while (next2 != null) {
                jM6147debounceEntryb8qMvQI2 = m6147debounceEntryb8qMvQI(next2, j2, j3, fArr, currentMillis, jM6147debounceEntryb8qMvQI2);
                next2 = next2.getNext();
            }
            jM6147debounceEntryb8qMvQI = jM6147debounceEntryb8qMvQI2;
        }
        if (jM6147debounceEntryb8qMvQI == j) {
            jM6147debounceEntryb8qMvQI = -1;
        }
        this.minDebounceDeadline = jM6147debounceEntryb8qMvQI;
    }

    public final void fireWithUpdatedRect$ui_release(Entry entry, long topLeft, long bottomRight, long currentMillis) {
        long lastInvokeMillis = entry.getLastInvokeMillis();
        long throttleMillis = entry.getThrottleMillis();
        long debounceMillis = entry.getDebounceMillis();
        boolean z = currentMillis - lastInvokeMillis >= throttleMillis || lastInvokeMillis == Long.MIN_VALUE;
        boolean z2 = debounceMillis == 0;
        boolean z3 = throttleMillis == 0;
        entry.setTopLeft(topLeft);
        entry.setBottomRight(bottomRight);
        boolean z4 = !(z2 || z3) || z2;
        if (z && z4) {
            entry.setLastUninvokedFireMillis(-1L);
            entry.setLastInvokeMillis(currentMillis);
            entry.m6156fire9b9wPM(topLeft, bottomRight, this.windowOffset, this.screenOffset, this.viewToWindowMatrix);
        } else {
            if (z2) {
                return;
            }
            entry.setLastUninvokedFireMillis(currentMillis);
            long j = this.minDebounceDeadline;
            long j2 = currentMillis + debounceMillis;
            if (j <= 0 || j2 >= j) {
                return;
            }
            this.minDebounceDeadline = j;
        }
    }

    /* JADX INFO: renamed from: fire-WY9HvpM, reason: not valid java name */
    private final void m6148fireWY9HvpM(Entry entry, long windowOffset, long screenOffset, float[] viewToWindowMatrix, long currentMillis) {
        long lastInvokeMillis = entry.getLastInvokeMillis();
        boolean z = currentMillis - lastInvokeMillis > entry.getThrottleMillis() || lastInvokeMillis == Long.MIN_VALUE;
        boolean z2 = entry.getDebounceMillis() == 0;
        entry.setLastUninvokedFireMillis(currentMillis);
        if (z && z2) {
            entry.setLastInvokeMillis(currentMillis);
            entry.m6156fire9b9wPM(entry.getTopLeft(), entry.getBottomRight(), windowOffset, screenOffset, viewToWindowMatrix);
        }
        if (z2) {
            return;
        }
        long j = this.minDebounceDeadline;
        long debounceMillis = entry.getDebounceMillis() + currentMillis;
        if (j <= 0 || debounceMillis >= j) {
            return;
        }
        this.minDebounceDeadline = j;
    }

    /* JADX INFO: renamed from: debounceEntry-b8qMvQI, reason: not valid java name */
    private final long m6147debounceEntryb8qMvQI(Entry entry, long windowOffset, long screenOffset, float[] viewToWindowMatrix, long currentMillis, long minDeadline) {
        if (entry.getDebounceMillis() <= 0 || entry.getLastUninvokedFireMillis() <= 0) {
            return minDeadline;
        }
        if (currentMillis - entry.getLastUninvokedFireMillis() > entry.getDebounceMillis()) {
            entry.setLastInvokeMillis(currentMillis);
            entry.setLastUninvokedFireMillis(-1L);
            entry.m6156fire9b9wPM(entry.getTopLeft(), entry.getBottomRight(), windowOffset, screenOffset, viewToWindowMatrix);
            return minDeadline;
        }
        return Math.min(minDeadline, entry.getLastUninvokedFireMillis() + entry.getDebounceMillis());
    }

    private final void addToGlobalEntries(Entry entry) {
        entry.setNext(this.globalChangeEntries);
        this.globalChangeEntries = entry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean removeFromGlobalEntries(Entry entry) {
        Entry entry2 = this.globalChangeEntries;
        if (entry2 == entry) {
            this.globalChangeEntries = entry2.getNext();
            entry.setNext(null);
            return true;
        }
        Entry next = entry2 != null ? entry2.getNext() : null;
        while (true) {
            Entry entry3 = entry2;
            entry2 = next;
            if (entry2 == null) {
                return false;
            }
            if (entry2 == entry) {
                if (entry3 != null) {
                    entry3.setNext(entry2.getNext());
                }
                entry.setNext(null);
                return true;
            }
            next = entry2.getNext();
        }
    }

    private final void linkedForEach(Entry entry, Function1<? super Entry, Unit> function1) {
        while (entry != null) {
            function1.invoke(entry);
            entry = entry.getNext();
        }
    }

    private final void multiForEach(MutableIntObjectMap<Entry> mutableIntObjectMap, Function1<? super Entry, Unit> function1) {
        MutableIntObjectMap<Entry> mutableIntObjectMap2 = mutableIntObjectMap;
        Object[] objArr = mutableIntObjectMap2.values;
        long[] jArr = mutableIntObjectMap2.metadata;
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
                    if ((255 & j) < 128) {
                        for (Entry next = (Entry) objArr[(i << 3) + i3]; next != null; next = next.getNext()) {
                            function1.invoke(next);
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

    private final void runFor(MutableIntObjectMap<Entry> mutableIntObjectMap, int i, Function1<? super Entry, Unit> function1) {
        for (Entry next = mutableIntObjectMap.get(i); next != null; next = next.getNext()) {
            function1.invoke(next);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean multiRemove(MutableIntObjectMap<Entry> mutableIntObjectMap, int i, Entry entry) {
        Entry entryRemove = mutableIntObjectMap.remove(i);
        if (entryRemove == null) {
            return false;
        }
        if (Intrinsics.areEqual(entryRemove, entry)) {
            Entry next = entry.getNext();
            entry.setNext(null);
            if (next != null) {
                mutableIntObjectMap.put(i, next);
            }
            return true;
        }
        mutableIntObjectMap.put(i, entryRemove);
        while (entryRemove != null) {
            Entry next2 = entryRemove.getNext();
            if (next2 == null) {
                return false;
            }
            if (next2 == entry) {
                entryRemove.setNext(entry.getNext());
                entry.setNext(null);
                break;
            }
            entryRemove = entryRemove.getNext();
        }
        return true;
    }

    private final Entry multiPut(MutableIntObjectMap<Entry> mutableIntObjectMap, int i, Entry entry) {
        Entry entry2 = mutableIntObjectMap.get(i);
        if (entry2 == null) {
            mutableIntObjectMap.set(i, entry);
            entry2 = entry;
        }
        Entry next = entry2;
        if (next != entry) {
            while (next.getNext() != null) {
                next = next.getNext();
                Intrinsics.checkNotNull(next);
            }
            next.setNext(entry);
        }
        return entry;
    }
}
