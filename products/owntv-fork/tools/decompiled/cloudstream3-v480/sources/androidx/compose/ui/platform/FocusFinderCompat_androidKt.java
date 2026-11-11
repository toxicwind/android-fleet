package androidx.compose.ui.platform;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: FocusFinderCompat.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\"\u0010\u0005\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0004H\u0002\u001a*\u0010\b\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b0\nH\u0002\u001a,\u0010\f\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002\u001a,\u0010\u000e\u001a\u00020\u000f*\u00020\u00012\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0011j\b\u0012\u0004\u0012\u00020\u0001`\u00122\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a,\u0010\u000e\u001a\u00020\u000f*\u00020\u00012\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0011j\b\u0012\u0004\u0012\u00020\u0001`\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0002¨\u0006\u0014"}, d2 = {"findUserSetNextFocus", "Landroid/view/View;", "root", "direction", "", "findViewInsideOutShouldExist", "start", "id", "findViewByPredicateInsideOut", "predicate", "Lkotlin/Function1;", "", "findViewByPredicateTraversal", "childToSkip", "addFocusableViews", "", "views", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "inTouchMode", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class FocusFinderCompat_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final View findUserSetNextFocus(final View view, final View view2, int i) {
        int nextFocusForwardId;
        if (i == 1) {
            if (view.getId() == -1) {
                return null;
            }
            return findViewByPredicateInsideOut(view2, view, new Function1<View, Boolean>() { // from class: androidx.compose.ui.platform.FocusFinderCompat_androidKt.findUserSetNextFocus.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(View view3) {
                    return Boolean.valueOf(FocusFinderCompat_androidKt.findViewInsideOutShouldExist(view2, view3, view3.getNextFocusForwardId()) == view);
                }
            });
        }
        if (i == 2 && (nextFocusForwardId = view.getNextFocusForwardId()) != -1) {
            return findViewInsideOutShouldExist(view2, view, nextFocusForwardId);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final View findViewInsideOutShouldExist(View view, View view2, final int i) {
        return findViewByPredicateInsideOut(view, view2, new Function1<View, Boolean>() { // from class: androidx.compose.ui.platform.FocusFinderCompat_androidKt.findViewInsideOutShouldExist.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(View view3) {
                return Boolean.valueOf(view3.getId() == i);
            }
        });
    }

    private static final View findViewByPredicateInsideOut(View view, View view2, Function1<? super View, Boolean> function1) {
        View viewFindViewByPredicateTraversal;
        View view3 = null;
        while (true) {
            viewFindViewByPredicateTraversal = findViewByPredicateTraversal(view2, function1, view3);
            if (viewFindViewByPredicateTraversal != null || view2 == view) {
                break;
            }
            Object parent = view2.getParent();
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            View view4 = (View) parent;
            view3 = view2;
            view2 = view4;
        }
        return viewFindViewByPredicateTraversal;
    }

    private static final View findViewByPredicateTraversal(View view, Function1<? super View, Boolean> function1, View view2) {
        View viewFindViewByPredicateTraversal;
        if (function1.invoke(view).booleanValue()) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != view2 && (viewFindViewByPredicateTraversal = findViewByPredicateTraversal(childAt, function1, view2)) != null) {
                return viewFindViewByPredicateTraversal;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addFocusableViews(View view, ArrayList<View> arrayList, int i) {
        if (Build.VERSION.SDK_INT < 26) {
            addFocusableViews(view, arrayList, view.isInTouchMode());
        } else {
            view.addFocusables(arrayList, i, view.isInTouchMode() ? 1 : 0);
        }
    }

    private static final void addFocusableViews(View view, ArrayList<View> arrayList, boolean z) {
        boolean z2 = view.getVisibility() == 0 && view.isFocusable() && view.isEnabled() && view.getWidth() > 0 && view.getHeight() > 0 && (!z || view.isFocusableInTouchMode());
        if (!(view instanceof ViewGroup)) {
            if (z2) {
                arrayList.add(view);
                return;
            }
            return;
        }
        int size = arrayList.size();
        ViewGroup viewGroup = (ViewGroup) view;
        boolean z3 = viewGroup.getDescendantFocusability() == 131072;
        if (z2 && z3) {
            arrayList.add(view);
        }
        if (viewGroup.getDescendantFocusability() != 393216) {
            int childCount = viewGroup.getChildCount();
            View[] viewArr = new View[childCount];
            for (int i = 0; i < childCount; i++) {
                viewArr[i] = viewGroup.getChildAt(i);
            }
            FocusSorter.INSTANCE.sort(viewArr, viewGroup, viewGroup.getLayoutDirection() == 1);
            for (int i2 = 0; i2 < childCount; i2++) {
                addFocusableViews(viewArr[i2], arrayList, z);
            }
        }
        if (z2 && !z3 && size == arrayList.size()) {
            arrayList.add(view);
        }
    }
}
