package androidx.compose.ui.platform;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectIntMapKt;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusInteropUtils_androidKt;
import androidx.compose.ui.focus.TwoDimensionalFocusSearchKt;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: FocusFinderCompat.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0001\u0018\u0000 $2\u00020\u0001:\u0002$%B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013J \u0010\u0014\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u001a\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\fH\u0002J\"\u0010\u0017\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J$\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0002JF\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u00132\u0016\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rH\u0002J<\u0010\u0019\u001a\u0004\u0018\u00010\f2\u0016\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0003J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J\u0018\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0005H\u0002JD\u0010\u001d\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0015\u001a\u00020\u00052\u0016\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J4\u0010\u001e\u001a\u0004\u0018\u00010\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0016\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u0006\u0010\u001f\u001a\u00020\u0013H\u0002J4\u0010 \u001a\u0004\u0018\u00010\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0016\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u0006\u0010\u001f\u001a\u00020\u0013H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/compose/ui/platform/FocusFinderCompat;", "", "<init>", "()V", "cachedFocusedRect", "Landroid/graphics/Rect;", "bestCandidateRect", "otherRect", "userSpecifiedFocusComparator", "Landroidx/compose/ui/platform/FocusFinderCompat$UserSpecifiedFocusComparator;", "tmpList", "Ljava/util/ArrayList;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "findNextFocus", "root", "Landroid/view/ViewGroup;", "focused", "direction", "", "findNextFocusFromRect", "focusedRect", "getEffectiveRoot", "findNextUserSpecifiedFocus", "focusables", "findNextFocusInRelativeDirection", "setFocusBottomRight", "", "setFocusTopLeft", "findNextFocusInAbsoluteDirection", "getNextFocusable", PeertubeParsingHelper.COUNT_KEY, "getPreviousFocusable", "isValidId", "", "id", "Companion", "UserSpecifiedFocusComparator", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class FocusFinderCompat {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final FocusFinderCompat$Companion$FocusFinderThreadLocal$1 FocusFinderThreadLocal = new ThreadLocal<FocusFinderCompat>() { // from class: androidx.compose.ui.platform.FocusFinderCompat$Companion$FocusFinderThreadLocal$1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public FocusFinderCompat initialValue() {
            return new FocusFinderCompat();
        }
    };
    private final Rect cachedFocusedRect = new Rect();
    private final Rect bestCandidateRect = new Rect();
    private final Rect otherRect = new Rect();
    private final UserSpecifiedFocusComparator userSpecifiedFocusComparator = new UserSpecifiedFocusComparator(new UserSpecifiedFocusComparator.NextFocusGetter() { // from class: androidx.compose.ui.platform.FocusFinderCompat$$ExternalSyntheticLambda0
        @Override // androidx.compose.ui.platform.FocusFinderCompat.UserSpecifiedFocusComparator.NextFocusGetter
        public final View get(View view, View view2) {
            return FocusFinderCompat.userSpecifiedFocusComparator$lambda$0(this.f$0, view, view2);
        }
    });
    private final ArrayList<View> tmpList = new ArrayList<>();

    private final boolean isValidId(int id) {
        return (id == 0 || id == -1) ? false : true;
    }

    /* JADX INFO: compiled from: FocusFinderCompat.android.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/platform/FocusFinderCompat$Companion;", "", "<init>", "()V", "FocusFinderThreadLocal", "androidx/compose/ui/platform/FocusFinderCompat$Companion$FocusFinderThreadLocal$1", "Landroidx/compose/ui/platform/FocusFinderCompat$Companion$FocusFinderThreadLocal$1;", "instance", "Landroidx/compose/ui/platform/FocusFinderCompat;", "getInstance", "()Landroidx/compose/ui/platform/FocusFinderCompat;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FocusFinderCompat getInstance() {
            FocusFinderCompat focusFinderCompat = FocusFinderCompat.FocusFinderThreadLocal.get();
            Intrinsics.checkNotNull(focusFinderCompat);
            return focusFinderCompat;
        }
    }

    static final View userSpecifiedFocusComparator$lambda$0(FocusFinderCompat focusFinderCompat, View view, View view2) {
        if (focusFinderCompat.isValidId(view2.getNextFocusForwardId())) {
            return FocusFinderCompat_androidKt.findUserSetNextFocus(view2, view, 2);
        }
        return null;
    }

    public final View findNextFocus(ViewGroup root, View focused, int direction) {
        ViewGroup effectiveRoot = getEffectiveRoot(root, focused);
        View viewFindNextUserSpecifiedFocus = findNextUserSpecifiedFocus(effectiveRoot, focused, direction);
        if (viewFindNextUserSpecifiedFocus != null) {
            return viewFindNextUserSpecifiedFocus;
        }
        ArrayList<View> arrayList = this.tmpList;
        try {
            arrayList.clear();
            FocusFinderCompat_androidKt.addFocusableViews(effectiveRoot, (ArrayList<View>) arrayList, direction);
            if (!arrayList.isEmpty()) {
                viewFindNextUserSpecifiedFocus = findNextFocus(effectiveRoot, focused, null, direction, arrayList);
            }
            return viewFindNextUserSpecifiedFocus;
        } finally {
            arrayList.clear();
        }
    }

    public final View findNextFocusFromRect(ViewGroup root, Rect focusedRect, int direction) {
        this.cachedFocusedRect.set(focusedRect);
        return findNextFocus(root, this.cachedFocusedRect, direction);
    }

    private final ViewGroup getEffectiveRoot(ViewGroup root, View focused) {
        if (focused != null && focused != root) {
            ViewParent parent = focused.getParent();
            ViewGroup viewGroup = null;
            while (parent instanceof ViewGroup) {
                if (parent == root) {
                    if (viewGroup == null) {
                        break;
                    }
                    return viewGroup;
                }
                ViewGroup viewGroup2 = (ViewGroup) parent;
                if (viewGroup2.getTouchscreenBlocksFocus() && focused.getContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen")) {
                    viewGroup = viewGroup2;
                }
                parent = viewGroup2.getParent();
            }
        }
        return root;
    }

    private final View findNextUserSpecifiedFocus(ViewGroup root, View focused, int direction) {
        ViewGroup viewGroup = root;
        View viewFindUserSetNextFocus = FocusFinderCompat_androidKt.findUserSetNextFocus(focused, viewGroup, direction);
        boolean z = true;
        View viewFindUserSetNextFocus2 = viewFindUserSetNextFocus;
        while (viewFindUserSetNextFocus != null) {
            if (viewFindUserSetNextFocus.isFocusable() && viewFindUserSetNextFocus.getVisibility() == 0 && (!viewFindUserSetNextFocus.isInTouchMode() || viewFindUserSetNextFocus.isFocusableInTouchMode())) {
                return viewFindUserSetNextFocus;
            }
            viewFindUserSetNextFocus = FocusFinderCompat_androidKt.findUserSetNextFocus(viewFindUserSetNextFocus, viewGroup, direction);
            boolean z2 = !z;
            if (!z) {
                viewFindUserSetNextFocus2 = viewFindUserSetNextFocus2 != null ? FocusFinderCompat_androidKt.findUserSetNextFocus(viewFindUserSetNextFocus2, viewGroup, direction) : null;
                if (viewFindUserSetNextFocus2 == viewFindUserSetNextFocus) {
                    break;
                }
            }
            z = z2;
        }
        return null;
    }

    private final View findNextFocus(ViewGroup root, Rect focusedRect, int direction) {
        ViewGroup effectiveRoot = getEffectiveRoot(root, null);
        ArrayList<View> arrayList = this.tmpList;
        try {
            arrayList.clear();
            FocusFinderCompat_androidKt.addFocusableViews(effectiveRoot, (ArrayList<View>) arrayList, direction);
            if (arrayList.isEmpty()) {
                return null;
            }
            return findNextFocus(effectiveRoot, null, focusedRect, direction, arrayList);
        } finally {
            arrayList.clear();
        }
    }

    private final View findNextFocus(ViewGroup root, View focused, Rect focusedRect, int direction, ArrayList<View> focusables) {
        Rect rect = this.cachedFocusedRect;
        if (focused != null) {
            focused.getFocusedRect(rect);
            root.offsetDescendantRectToMyCoords(focused, rect);
        } else if (focusedRect != null) {
            rect.set(focusedRect);
        } else if (direction != 1) {
            if (direction != 2) {
                if (direction == 17 || direction == 33) {
                    setFocusBottomRight(root, rect);
                } else if (direction == 66 || direction == 130) {
                    setFocusTopLeft(root, rect);
                }
            } else if (root.getLayoutDirection() == 1) {
                setFocusBottomRight(root, rect);
            } else {
                setFocusTopLeft(root, rect);
            }
        } else if (root.getLayoutDirection() == 1) {
            setFocusTopLeft(root, rect);
        } else {
            setFocusBottomRight(root, rect);
        }
        if (direction == 1 || direction == 2) {
            return findNextFocusInRelativeDirection(focusables, root, focused, direction);
        }
        if (direction == 17 || direction == 33 || direction == 66 || direction == 130) {
            return findNextFocusInAbsoluteDirection(root, focused, rect, focusables, direction);
        }
        throw new IllegalArgumentException("Unknown direction: " + direction);
    }

    private final View findNextFocusInRelativeDirection(ArrayList<View> focusables, ViewGroup root, View focused, int direction) {
        ArrayList<View> arrayList;
        try {
            this.userSpecifiedFocusComparator.setFocusables(focusables, root);
            Collections.sort(focusables, this.userSpecifiedFocusComparator);
            this.userSpecifiedFocusComparator.recycle();
            int size = focusables.size();
            View previousFocusable = null;
            if (size < 2) {
                return null;
            }
            if (direction == 1) {
                arrayList = focusables;
                previousFocusable = getPreviousFocusable(focused, arrayList, size);
            } else if (direction == 2) {
                arrayList = focusables;
                previousFocusable = getNextFocusable(focused, arrayList, size);
            } else if (direction == 17 || direction == 33 || direction == 66 || direction == 130) {
                arrayList = focusables;
                previousFocusable = findNextFocusInAbsoluteDirection(root, focused, this.cachedFocusedRect, arrayList, direction);
            } else {
                arrayList = focusables;
            }
            return previousFocusable == null ? arrayList.get(size - 1) : previousFocusable;
        } catch (Throwable th) {
            this.userSpecifiedFocusComparator.recycle();
            throw th;
        }
    }

    private final void setFocusBottomRight(ViewGroup root, Rect focusedRect) {
        int scrollY = root.getScrollY() + root.getHeight();
        int scrollX = root.getScrollX() + root.getWidth();
        focusedRect.set(scrollX, scrollY, scrollX, scrollY);
    }

    private final void setFocusTopLeft(ViewGroup root, Rect focusedRect) {
        int scrollY = root.getScrollY();
        int scrollX = root.getScrollX();
        focusedRect.set(scrollX, scrollY, scrollX, scrollY);
    }

    private final View findNextFocusInAbsoluteDirection(ViewGroup root, View focused, Rect focusedRect, ArrayList<View> focusables, int direction) {
        this.bestCandidateRect.set(focusedRect);
        if (direction == 17) {
            this.bestCandidateRect.offset(focusedRect.width() + 1, 0);
        } else if (direction == 33) {
            this.bestCandidateRect.offset(0, focusedRect.height() + 1);
        } else if (direction == 66) {
            this.bestCandidateRect.offset((-focusedRect.width()) - 1, 0);
        } else if (direction == 130) {
            this.bestCandidateRect.offset(0, (-focusedRect.height()) - 1);
        }
        ArrayList<View> arrayList = focusables;
        int size = arrayList.size();
        View view = null;
        for (int i = 0; i < size; i++) {
            View view2 = arrayList.get(i);
            if (!Intrinsics.areEqual(view2, focused) && !Intrinsics.areEqual(view2, root)) {
                view2.getFocusedRect(this.otherRect);
                root.offsetDescendantRectToMyCoords(view2, this.otherRect);
                androidx.compose.ui.geometry.Rect composeRect = RectHelper_androidKt.toComposeRect(this.otherRect);
                androidx.compose.ui.geometry.Rect composeRect2 = RectHelper_androidKt.toComposeRect(this.bestCandidateRect);
                androidx.compose.ui.geometry.Rect composeRect3 = RectHelper_androidKt.toComposeRect(focusedRect);
                FocusDirection focusDirection = FocusInteropUtils_androidKt.toFocusDirection(direction);
                if (TwoDimensionalFocusSearchKt.m3818isBetterCandidateI7lrPNg(composeRect, composeRect2, composeRect3, focusDirection != null ? focusDirection.getValue() : FocusDirection.INSTANCE.m3757getNextdhqQ8s())) {
                    this.bestCandidateRect.set(this.otherRect);
                    view = view2;
                }
            }
        }
        return view;
    }

    private final View getNextFocusable(View focused, ArrayList<View> focusables, int count) {
        int iLastIndexOf;
        int i;
        if (count < 2) {
            return null;
        }
        if (focused != null && (iLastIndexOf = focusables.lastIndexOf(focused)) >= 0 && (i = iLastIndexOf + 1) < count) {
            return focusables.get(i);
        }
        return focusables.get(0);
    }

    private final View getPreviousFocusable(View focused, ArrayList<View> focusables, int count) {
        int iIndexOf;
        if (count < 2) {
            return null;
        }
        if (focused != null && (iIndexOf = focusables.indexOf(focused)) > 0) {
            return focusables.get(iIndexOf - 1);
        }
        return focusables.get(count - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: FocusFinderCompat.android.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0002\u0018\u00002\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0002`\u0003:\u0001\u001cB\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0010\u001a\u00020\u0011J&\u0010\u0012\u001a\u00020\u00112\u0016\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0014j\b\u0012\u0004\u0012\u00020\u0002`\u00152\u0006\u0010\u000f\u001a\u00020\u0002J\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0002J\u001c\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u00022\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/platform/FocusFinderCompat$UserSpecifiedFocusComparator;", "Ljava/util/Comparator;", "Landroid/view/View;", "Lkotlin/Comparator;", "mNextFocusGetter", "Landroidx/compose/ui/platform/FocusFinderCompat$UserSpecifiedFocusComparator$NextFocusGetter;", "<init>", "(Landroidx/compose/ui/platform/FocusFinderCompat$UserSpecifiedFocusComparator$NextFocusGetter;)V", "nextFoci", "Landroidx/collection/MutableScatterMap;", "isConnectedTo", "Landroidx/collection/MutableScatterSet;", "headsOfChains", "originalOrdinal", "Landroidx/collection/MutableObjectIntMap;", "root", "recycle", "", "setFocusables", "focusables", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "setHeadOfChain", TtmlNode.TAG_HEAD, "compare", "", "first", "second", "NextFocusGetter", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final class UserSpecifiedFocusComparator implements Comparator<View> {
        private final NextFocusGetter mNextFocusGetter;
        private View root;
        private final MutableScatterMap<View, View> nextFoci = ScatterMapKt.mutableScatterMapOf();
        private final MutableScatterSet<View> isConnectedTo = ScatterSetKt.mutableScatterSetOf();
        private final MutableScatterMap<View, View> headsOfChains = ScatterMapKt.mutableScatterMapOf();
        private final MutableObjectIntMap<View> originalOrdinal = ObjectIntMapKt.mutableObjectIntMapOf();

        /* JADX INFO: compiled from: FocusFinderCompat.android.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/platform/FocusFinderCompat$UserSpecifiedFocusComparator$NextFocusGetter;", "", "get", "Landroid/view/View;", "root", "view", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public interface NextFocusGetter {
            View get(View root, View view);
        }

        public UserSpecifiedFocusComparator(NextFocusGetter nextFocusGetter) {
            this.mNextFocusGetter = nextFocusGetter;
        }

        public final void recycle() {
            this.root = null;
            this.headsOfChains.clear();
            this.isConnectedTo.clear();
            this.originalOrdinal.clear();
            this.nextFoci.clear();
        }

        public final void setFocusables(ArrayList<View> focusables, View root) {
            this.root = root;
            ArrayList<View> arrayList = focusables;
            ArrayList<View> arrayList2 = arrayList;
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                this.originalOrdinal.set(arrayList.get(i), i);
            }
            int size2 = arrayList2.size() - 1;
            if (size2 >= 0) {
                while (true) {
                    int i2 = size2 - 1;
                    View view = arrayList.get(size2);
                    View view2 = this.mNextFocusGetter.get(root, view);
                    if (view2 != null && this.originalOrdinal.containsKey(view2)) {
                        this.nextFoci.set(view, view2);
                        this.isConnectedTo.add(view2);
                    }
                    if (i2 < 0) {
                        break;
                    } else {
                        size2 = i2;
                    }
                }
            }
            int size3 = arrayList2.size() - 1;
            if (size3 < 0) {
                return;
            }
            while (true) {
                int i3 = size3 - 1;
                View view3 = arrayList.get(size3);
                if (this.nextFoci.get(view3) != null && !this.isConnectedTo.contains(view3)) {
                    setHeadOfChain(view3);
                }
                if (i3 < 0) {
                    return;
                } else {
                    size3 = i3;
                }
            }
        }

        public final void setHeadOfChain(View head) {
            View view = head;
            while (head != null) {
                View view2 = this.headsOfChains.get(head);
                if (view2 != null) {
                    if (view2 == view) {
                        return;
                    }
                    head = view;
                    view = view2;
                }
                this.headsOfChains.set(head, view);
                head = this.nextFoci.get(head);
            }
        }

        @Override // java.util.Comparator
        public int compare(View first, View second) {
            if (first == second) {
                return 0;
            }
            if (first == null) {
                return -1;
            }
            if (second == null) {
                return 1;
            }
            View view = this.headsOfChains.get(first);
            View view2 = this.headsOfChains.get(second);
            if (view == view2 && view != null) {
                if (first == view) {
                    return -1;
                }
                return (second == view || this.nextFoci.get(first) == null) ? 1 : -1;
            }
            if (view != null) {
                first = view;
            }
            if (view2 != null) {
                second = view2;
            }
            if (view == null && view2 == null) {
                return 0;
            }
            return this.originalOrdinal.get(first) < this.originalOrdinal.get(second) ? -1 : 1;
        }
    }
}
