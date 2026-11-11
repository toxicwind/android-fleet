package androidx.compose.ui.layout;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import androidx.collection.IntObjectMap;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.core.graphics.Insets;
import androidx.core.view.DisplayCutoutCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WindowInsetsRulers.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0016J\u0018\u0010*\u001a\u00020+2\u0006\u0010(\u001a\u00020)2\u0006\u0010,\u001a\u00020+H\u0016J\u0018\u0010-\u001a\u00020'2\u0006\u0010.\u001a\u00020\u00142\u0006\u0010(\u001a\u00020)H\u0002J\u001e\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\u00102\f\u00101\u001a\b\u0012\u0004\u0012\u00020)02H\u0016J\u0010\u00103\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u00104\u001a\u00020'2\u0006\u0010.\u001a\u00020\u0014H\u0002J\u0018\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u0002072\u0006\u00100\u001a\u00020\u0010H\u0016J\u0010\u00108\u001a\u00020'2\u0006\u00100\u001a\u00020\u0010H\u0002J\b\u00109\u001a\u00020'H\u0016J\u0010\u0010:\u001a\u00020'2\u0006\u00106\u001a\u000207H\u0016J\u0010\u0010;\u001a\u00020'2\u0006\u00106\u001a\u000207H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u0006<"}, d2 = {"Landroidx/compose/ui/layout/InsetsListener;", "Landroidx/core/view/WindowInsetsAnimationCompat$Callback;", "Ljava/lang/Runnable;", "Landroidx/core/view/OnApplyWindowInsetsListener;", "Landroid/view/View$OnAttachStateChangeListener;", "composeView", "Landroidx/compose/ui/platform/AndroidComposeView;", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeView;)V", "getComposeView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "prepared", "", "runningAnimationMask", "", "savedInsets", "Landroidx/core/view/WindowInsetsCompat;", "insetsValues", "Landroidx/collection/ScatterMap;", "", "Landroidx/compose/ui/layout/WindowWindowInsetsAnimationValues;", "getInsetsValues", "()Landroidx/collection/ScatterMap;", "generation", "Landroidx/compose/runtime/MutableIntState;", "getGeneration", "()Landroidx/compose/runtime/MutableIntState;", "displayCutouts", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/runtime/MutableState;", "Landroid/graphics/Rect;", "getDisplayCutouts", "()Landroidx/collection/MutableObjectList;", "displayCutoutRulers", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Landroidx/compose/ui/layout/RectRulers;", "getDisplayCutoutRulers", "()Landroidx/compose/runtime/snapshots/SnapshotStateList;", "onPrepare", "", "animation", "Landroidx/core/view/WindowInsetsAnimationCompat;", "onStart", "Landroidx/core/view/WindowInsetsAnimationCompat$BoundsCompat;", "bounds", "updateInsetAnimationInfo", "insetsValue", "onProgress", "insets", "runningAnimations", "", "onEnd", "stopAnimationForRuler", "onApplyWindowInsets", "view", "Landroid/view/View;", "updateInsets", "run", "onViewAttachedToWindow", "onViewDetachedFromWindow", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class InsetsListener extends WindowInsetsAnimationCompat.Callback implements Runnable, OnApplyWindowInsetsListener, View.OnAttachStateChangeListener {
    public static final int $stable = 8;
    private final AndroidComposeView composeView;
    private final SnapshotStateList<RectRulers> displayCutoutRulers;
    private final MutableObjectList<MutableState<Rect>> displayCutouts;
    private final MutableIntState generation;
    private final ScatterMap<Object, WindowWindowInsetsAnimationValues> insetsValues;
    private boolean prepared;
    private int runningAnimationMask;
    private WindowInsetsCompat savedInsets;

    public final AndroidComposeView getComposeView() {
        return this.composeView;
    }

    public InsetsListener(AndroidComposeView androidComposeView) {
        super(1);
        this.composeView = androidComposeView;
        MutableScatterMap mutableScatterMap = new MutableScatterMap(9);
        mutableScatterMap.set(WindowInsetsRulers.INSTANCE.getCaptionBar(), new WindowWindowInsetsAnimationValues("caption bar"));
        mutableScatterMap.set(WindowInsetsRulers.INSTANCE.getDisplayCutout(), new WindowWindowInsetsAnimationValues("display cutout"));
        mutableScatterMap.set(WindowInsetsRulers.INSTANCE.getIme(), new WindowWindowInsetsAnimationValues("ime"));
        mutableScatterMap.set(WindowInsetsRulers.INSTANCE.getMandatorySystemGestures(), new WindowWindowInsetsAnimationValues("mandatory system gestures"));
        mutableScatterMap.set(WindowInsetsRulers.INSTANCE.getNavigationBars(), new WindowWindowInsetsAnimationValues("navigation bars"));
        mutableScatterMap.set(WindowInsetsRulers.INSTANCE.getStatusBars(), new WindowWindowInsetsAnimationValues("status bars"));
        mutableScatterMap.set(WindowInsetsRulers.INSTANCE.getSystemGestures(), new WindowWindowInsetsAnimationValues("system gestures"));
        mutableScatterMap.set(WindowInsetsRulers.INSTANCE.getTappableElement(), new WindowWindowInsetsAnimationValues("tappable element"));
        mutableScatterMap.set(WindowInsetsRulers.INSTANCE.getWaterfall(), new WindowWindowInsetsAnimationValues("waterfall"));
        this.insetsValues = mutableScatterMap;
        this.generation = SnapshotIntStateKt.mutableIntStateOf(0);
        this.displayCutouts = new MutableObjectList<>(4);
        this.displayCutoutRulers = SnapshotStateKt.mutableStateListOf();
    }

    public final ScatterMap<Object, WindowWindowInsetsAnimationValues> getInsetsValues() {
        return this.insetsValues;
    }

    public final MutableIntState getGeneration() {
        return this.generation;
    }

    public final MutableObjectList<MutableState<Rect>> getDisplayCutouts() {
        return this.displayCutouts;
    }

    public final SnapshotStateList<RectRulers> getDisplayCutoutRulers() {
        return this.displayCutoutRulers;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public void onPrepare(WindowInsetsAnimationCompat animation) {
        this.prepared = true;
        super.onPrepare(animation);
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat animation, WindowInsetsAnimationCompat.BoundsCompat bounds) {
        WindowInsetsCompat windowInsetsCompat = this.savedInsets;
        this.prepared = false;
        this.savedInsets = null;
        if (animation.getDurationMillis() > 0 && windowInsetsCompat != null) {
            int typeMask = animation.getTypeMask();
            this.runningAnimationMask |= typeMask;
            WindowInsetsRulers windowInsetsRulers = (WindowInsetsRulers) WindowInsetsRulers_androidKt.AnimatableRulers.get(typeMask);
            if (windowInsetsRulers != null) {
                WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues = this.insetsValues.get(windowInsetsRulers);
                Intrinsics.checkNotNull(windowWindowInsetsAnimationValues);
                WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues2 = windowWindowInsetsAnimationValues;
                Insets insets = windowInsetsCompat.getInsets(typeMask);
                long jM5697constructorimpl = ValueInsets.m5697constructorimpl(((long) insets.bottom) | (((long) insets.left) << 48) | (((long) insets.top) << 32) | (((long) insets.right) << 16));
                long current = windowWindowInsetsAnimationValues2.getCurrent();
                if (!ValueInsets.m5699equalsimpl0(jM5697constructorimpl, current)) {
                    windowWindowInsetsAnimationValues2.m5715setSourceValueInsetsYnlvx88(current);
                    windowWindowInsetsAnimationValues2.m5716setTargetValueInsetsYnlvx88(jM5697constructorimpl);
                    windowWindowInsetsAnimationValues2.setAnimating(true);
                    updateInsetAnimationInfo(windowWindowInsetsAnimationValues2, animation);
                    MutableIntState mutableIntState = this.generation;
                    mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
                    Snapshot.INSTANCE.sendApplyNotifications();
                }
            }
        }
        return super.onStart(animation, bounds);
    }

    private final void updateInsetAnimationInfo(WindowWindowInsetsAnimationValues insetsValue, WindowInsetsAnimationCompat animation) {
        insetsValue.setFraction(animation.getInterpolatedFraction());
        insetsValue.setAlpha(animation.getAlpha());
        insetsValue.setDurationMillis(animation.getDurationMillis());
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public void onEnd(WindowInsetsAnimationCompat animation) {
        this.prepared = false;
        int typeMask = animation.getTypeMask();
        this.runningAnimationMask &= ~typeMask;
        this.savedInsets = null;
        WindowInsetsRulers windowInsetsRulers = (WindowInsetsRulers) WindowInsetsRulers_androidKt.AnimatableRulers.get(typeMask);
        if (windowInsetsRulers != null) {
            WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues = this.insetsValues.get(windowInsetsRulers);
            Intrinsics.checkNotNull(windowWindowInsetsAnimationValues);
            WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues2 = windowWindowInsetsAnimationValues;
            windowWindowInsetsAnimationValues2.setFraction(0.0f);
            windowWindowInsetsAnimationValues2.setAlpha(1.0f);
            windowWindowInsetsAnimationValues2.setDurationMillis(0L);
            windowWindowInsetsAnimationValues2.setFraction(0.0f);
            stopAnimationForRuler(windowWindowInsetsAnimationValues2);
            MutableIntState mutableIntState = this.generation;
            mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
            Snapshot.INSTANCE.sendApplyNotifications();
        }
        super.onEnd(animation);
    }

    private final void stopAnimationForRuler(WindowWindowInsetsAnimationValues insetsValue) {
        insetsValue.setAnimating(false);
        insetsValue.m5715setSourceValueInsetsYnlvx88(ValueInsets_androidKt.getUnsetValueInsets());
        insetsValue.m5716setTargetValueInsetsYnlvx88(ValueInsets_androidKt.getUnsetValueInsets());
    }

    @Override // androidx.core.view.OnApplyWindowInsetsListener
    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat insets) {
        if (this.prepared) {
            this.savedInsets = insets;
            if (Build.VERSION.SDK_INT == 30) {
                view.post(this);
                return insets;
            }
        } else if (this.runningAnimationMask == 0) {
            updateInsets(insets);
        }
        return insets;
    }

    private final void updateInsets(WindowInsetsCompat insets) {
        long j;
        boolean z;
        long j2;
        char c;
        char c2;
        char c3;
        char c4;
        int i;
        boolean z2;
        boolean z3;
        long jM5697constructorimpl;
        long jM5697constructorimpl2;
        long[] jArr;
        int[] iArr;
        long[] jArr2;
        int[] iArr2;
        long[] jArr3;
        int[] iArr3;
        long[] jArr4;
        int[] iArr4;
        int i2;
        long j3;
        int i3;
        IntObjectMap intObjectMap = WindowInsetsRulers_androidKt.WindowInsetsTypeMap;
        int[] iArr5 = intObjectMap.keys;
        Object[] objArr = intObjectMap.values;
        long[] jArr5 = intObjectMap.metadata;
        int i4 = 2;
        int length = jArr5.length - 2;
        long j4 = -9187201950435737472L;
        int i5 = 8;
        if (length >= 0) {
            int i6 = 0;
            z2 = false;
            z3 = false;
            z = true;
            j2 = 255;
            while (true) {
                long j5 = jArr5[i6];
                c = 7;
                c2 = 16;
                if ((((~j5) << 7) & j5 & j4) != j4) {
                    int i7 = 8 - ((~(i6 - length)) >>> 31);
                    int i8 = 0;
                    while (i8 < i7) {
                        if ((j5 & 255) < 128) {
                            int i9 = (i6 << 3) + i8;
                            int i10 = iArr5[i9];
                            WindowInsetsRulers windowInsetsRulers = (WindowInsetsRulers) objArr[i9];
                            Insets insets2 = insets.getInsets(i10);
                            j3 = j4;
                            i3 = i4;
                            i2 = i5;
                            jArr4 = jArr5;
                            iArr4 = iArr5;
                            long jM5697constructorimpl3 = ValueInsets.m5697constructorimpl((((long) insets2.top) << 32) | (((long) insets2.left) << 48) | (((long) insets2.right) << 16) | ((long) insets2.bottom));
                            WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues = this.insetsValues.get(windowInsetsRulers);
                            Intrinsics.checkNotNull(windowWindowInsetsAnimationValues);
                            WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues2 = windowWindowInsetsAnimationValues;
                            if (!ValueInsets.m5699equalsimpl0(jM5697constructorimpl3, windowWindowInsetsAnimationValues2.getCurrent())) {
                                windowWindowInsetsAnimationValues2.m5713setCurrentYnlvx88(jM5697constructorimpl3);
                                z2 = true;
                                if (!ValueInsets.m5699equalsimpl0(jM5697constructorimpl3, ValueInsets_androidKt.getZeroValueInsets())) {
                                    z3 = true;
                                }
                            }
                        } else {
                            jArr4 = jArr5;
                            iArr4 = iArr5;
                            i2 = i5;
                            j3 = j4;
                            i3 = i4;
                        }
                        j5 >>= i2;
                        i8++;
                        i4 = i3;
                        i5 = i2;
                        j4 = j3;
                        iArr5 = iArr4;
                        jArr5 = jArr4;
                    }
                    jArr3 = jArr5;
                    iArr3 = iArr5;
                    j = j4;
                    c3 = ' ';
                    c4 = '0';
                    i = i4;
                    if (i7 != i5) {
                        break;
                    }
                } else {
                    jArr3 = jArr5;
                    iArr3 = iArr5;
                    j = j4;
                    c3 = ' ';
                    c4 = '0';
                    i = i4;
                }
                if (i6 == length) {
                    break;
                }
                i6++;
                i4 = i;
                j4 = j;
                iArr5 = iArr3;
                jArr5 = jArr3;
                i5 = 8;
            }
        } else {
            j = -9187201950435737472L;
            z = true;
            j2 = 255;
            c = 7;
            c2 = 16;
            c3 = ' ';
            c4 = '0';
            i = 2;
            z2 = false;
            z3 = false;
        }
        MutableIntObjectMap mutableIntObjectMap = WindowInsetsRulers_androidKt.AnimatableRulers;
        int[] iArr6 = mutableIntObjectMap.keys;
        Object[] objArr2 = mutableIntObjectMap.values;
        long[] jArr6 = mutableIntObjectMap.metadata;
        int length2 = jArr6.length - 2;
        if (length2 >= 0) {
            int i11 = 0;
            while (true) {
                long j6 = jArr6[i11];
                if ((((~j6) << c) & j6 & j) != j) {
                    int i12 = 8 - ((~(i11 - length2)) >>> 31);
                    int i13 = 0;
                    while (i13 < i12) {
                        if ((j6 & j2) < 128) {
                            int i14 = (i11 << 3) + i13;
                            int i15 = iArr6[i14];
                            WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues3 = this.insetsValues.get((WindowInsetsRulers) objArr2[i14]);
                            Intrinsics.checkNotNull(windowWindowInsetsAnimationValues3);
                            WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues4 = windowWindowInsetsAnimationValues3;
                            if (i15 != WindowInsetsCompat.Type.ime()) {
                                Insets insetsIgnoringVisibility = insets.getInsetsIgnoringVisibility(i15);
                                jArr2 = jArr6;
                                iArr2 = iArr6;
                                long jM5697constructorimpl4 = ValueInsets.m5697constructorimpl((((long) insetsIgnoringVisibility.top) << c3) | (((long) insetsIgnoringVisibility.left) << c4) | (((long) insetsIgnoringVisibility.right) << c2) | ((long) insetsIgnoringVisibility.bottom));
                                if (!ValueInsets.m5699equalsimpl0(windowWindowInsetsAnimationValues4.getMaximum(), jM5697constructorimpl4)) {
                                    windowWindowInsetsAnimationValues4.m5714setMaximumYnlvx88(jM5697constructorimpl4);
                                    z2 = z;
                                    if (!ValueInsets.m5699equalsimpl0(jM5697constructorimpl4, ValueInsets_androidKt.getZeroValueInsets())) {
                                        z3 = z2;
                                    }
                                }
                            } else {
                                jArr2 = jArr6;
                                iArr2 = iArr6;
                            }
                            windowWindowInsetsAnimationValues4.setVisible(insets.isVisible(i15));
                        } else {
                            jArr2 = jArr6;
                            iArr2 = iArr6;
                        }
                        j6 >>= 8;
                        i13++;
                        jArr6 = jArr2;
                        iArr6 = iArr2;
                    }
                    jArr = jArr6;
                    iArr = iArr6;
                    if (i12 != 8) {
                        break;
                    }
                } else {
                    jArr = jArr6;
                    iArr = iArr6;
                }
                if (i11 == length2) {
                    break;
                }
                i11++;
                jArr6 = jArr;
                iArr6 = iArr;
            }
        }
        DisplayCutoutCompat displayCutout = insets.getDisplayCutout();
        if (displayCutout == null) {
            jM5697constructorimpl = ValueInsets_androidKt.getZeroValueInsets();
        } else {
            Insets waterfallInsets = displayCutout.getWaterfallInsets();
            jM5697constructorimpl = ValueInsets.m5697constructorimpl((((long) waterfallInsets.left) << c4) | (((long) waterfallInsets.top) << c3) | (((long) waterfallInsets.right) << c2) | ((long) waterfallInsets.bottom));
        }
        WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues5 = this.insetsValues.get(WindowInsetsRulers.INSTANCE.getWaterfall());
        Intrinsics.checkNotNull(windowWindowInsetsAnimationValues5);
        WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues6 = windowWindowInsetsAnimationValues5;
        if (!ValueInsets.m5699equalsimpl0(windowWindowInsetsAnimationValues6.getCurrent(), jM5697constructorimpl)) {
            windowWindowInsetsAnimationValues6.m5713setCurrentYnlvx88(jM5697constructorimpl);
            windowWindowInsetsAnimationValues6.m5714setMaximumYnlvx88(jM5697constructorimpl);
            z2 = z;
            if (!ValueInsets.m5699equalsimpl0(jM5697constructorimpl, ValueInsets_androidKt.getZeroValueInsets())) {
                z3 = z2;
            }
        }
        if (displayCutout == null) {
            jM5697constructorimpl2 = ValueInsets_androidKt.getZeroValueInsets();
        } else {
            jM5697constructorimpl2 = ValueInsets.m5697constructorimpl((((long) displayCutout.getSafeInsetTop()) << c3) | (((long) displayCutout.getSafeInsetLeft()) << c4) | (((long) displayCutout.getSafeInsetRight()) << c2) | ((long) displayCutout.getSafeInsetBottom()));
        }
        WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues7 = this.insetsValues.get(WindowInsetsRulers.INSTANCE.getDisplayCutout());
        Intrinsics.checkNotNull(windowWindowInsetsAnimationValues7);
        WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues8 = windowWindowInsetsAnimationValues7;
        if (!ValueInsets.m5699equalsimpl0(jM5697constructorimpl2, windowWindowInsetsAnimationValues8.getCurrent())) {
            windowWindowInsetsAnimationValues8.m5713setCurrentYnlvx88(jM5697constructorimpl2);
            windowWindowInsetsAnimationValues8.m5714setMaximumYnlvx88(jM5697constructorimpl2);
            z2 = z;
            if (!ValueInsets.m5699equalsimpl0(jM5697constructorimpl2, ValueInsets_androidKt.getZeroValueInsets())) {
                z3 = z2;
            }
        }
        if (displayCutout == null) {
            if (this.displayCutouts.getSize() > 0) {
                this.displayCutouts.clear();
                this.displayCutoutRulers.clear();
                z2 = z;
            }
        } else {
            List<Rect> boundingRects = displayCutout.getBoundingRects();
            if (boundingRects.size() < this.displayCutouts.getSize()) {
                this.displayCutouts.removeRange(boundingRects.size(), this.displayCutouts.getSize());
                this.displayCutoutRulers.removeRange(boundingRects.size(), this.displayCutoutRulers.size());
                z2 = z;
            } else {
                int size = boundingRects.size() - this.displayCutouts.getSize();
                int i16 = 0;
                while (i16 < size) {
                    MutableObjectList<MutableState<Rect>> mutableObjectList = this.displayCutouts;
                    mutableObjectList.add(SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(boundingRects.get(mutableObjectList.getSize()), null, i, null));
                    this.displayCutoutRulers.add(RectRulersKt.RectRulers("display cutout rect " + this.displayCutouts.getSize()));
                    i16++;
                    z2 = z;
                }
            }
            List<Rect> list = boundingRects;
            int size2 = list.size();
            for (int i17 = 0; i17 < size2; i17++) {
                Rect rect = boundingRects.get(i17);
                MutableState<Rect> mutableState = this.displayCutouts.get(i17);
                if (!Intrinsics.areEqual(mutableState.getValue(), rect)) {
                    mutableState.setValue(rect);
                    z2 = z;
                }
            }
            if (!list.isEmpty()) {
                z3 = z;
            }
        }
        if ((z3 || this.generation.getIntValue() != 0) && z2) {
            MutableIntState mutableIntState = this.generation;
            mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
            Snapshot.INSTANCE.sendApplyNotifications();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.prepared) {
            this.runningAnimationMask = 0;
            this.prepared = false;
            WindowInsetsCompat windowInsetsCompat = this.savedInsets;
            if (windowInsetsCompat != null) {
                updateInsets(windowInsetsCompat);
                this.savedInsets = null;
            }
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        Object parent = view.getParent();
        View view2 = parent instanceof View ? (View) parent : null;
        if (view2 != null) {
            view = view2;
        }
        ViewCompat.setOnApplyWindowInsetsListener(view, this);
        ViewCompat.setWindowInsetsAnimationCallback(view, this);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        Object parent = view.getParent();
        View view2 = parent instanceof View ? (View) parent : null;
        if (view2 != null) {
            view = view2;
        }
        ViewCompat.setOnApplyWindowInsetsListener(view, null);
        ViewCompat.setWindowInsetsAnimationCallback(view, null);
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public WindowInsetsCompat onProgress(WindowInsetsCompat insets, List<WindowInsetsAnimationCompat> runningAnimations) {
        int size = runningAnimations.size();
        for (int i = 0; i < size; i++) {
            WindowInsetsAnimationCompat windowInsetsAnimationCompat = runningAnimations.get(i);
            WindowInsetsRulers windowInsetsRulers = (WindowInsetsRulers) WindowInsetsRulers_androidKt.AnimatableRulers.get(windowInsetsAnimationCompat.getTypeMask());
            if (windowInsetsRulers != null) {
                WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues = this.insetsValues.get(windowInsetsRulers);
                Intrinsics.checkNotNull(windowWindowInsetsAnimationValues);
                WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues2 = windowWindowInsetsAnimationValues;
                if (windowWindowInsetsAnimationValues2.isAnimating()) {
                    updateInsetAnimationInfo(windowWindowInsetsAnimationValues2, windowInsetsAnimationCompat);
                }
            }
        }
        updateInsets(insets);
        return insets;
    }
}
