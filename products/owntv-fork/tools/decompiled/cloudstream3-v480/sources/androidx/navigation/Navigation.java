package androidx.navigation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import androidx.core.app.ActivityCompat;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: Navigation.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0007J$\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\t2\u0010\b\u0002\u0010\u000f\u001a\n\u0018\u00010\u0010j\u0004\u0018\u0001`\u0011H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u0007J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\u0019"}, d2 = {"Landroidx/navigation/Navigation;", "", "<init>", "()V", "findNavController", "Landroidx/navigation/NavController;", "activity", "Landroid/app/Activity;", "viewId", "", "view", "Landroid/view/View;", "createNavigateOnClickListener", "Landroid/view/View$OnClickListener;", "resId", "args", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "directions", "Landroidx/navigation/NavDirections;", "setViewNavController", "", "controller", "findViewNavController", "getViewNavController", "navigation-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Navigation {
    public static final Navigation INSTANCE = new Navigation();

    @JvmStatic
    public static final View.OnClickListener createNavigateOnClickListener(int i) {
        return createNavigateOnClickListener$default(i, null, 2, null);
    }

    private Navigation() {
    }

    @JvmStatic
    public static final NavController findNavController(Activity activity, int viewId) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        View viewRequireViewById = ActivityCompat.requireViewById(activity, viewId);
        Intrinsics.checkNotNullExpressionValue(viewRequireViewById, "requireViewById(...)");
        NavController navControllerFindViewNavController = INSTANCE.findViewNavController(viewRequireViewById);
        if (navControllerFindViewNavController != null) {
            return navControllerFindViewNavController;
        }
        throw new IllegalStateException("Activity " + activity + " does not have a NavController set on " + viewId);
    }

    @JvmStatic
    public static final NavController findNavController(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        NavController navControllerFindViewNavController = INSTANCE.findViewNavController(view);
        if (navControllerFindViewNavController != null) {
            return navControllerFindViewNavController;
        }
        throw new IllegalStateException("View " + view + " does not have a NavController set");
    }

    public static /* synthetic */ View.OnClickListener createNavigateOnClickListener$default(int i, Bundle bundle, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bundle = null;
        }
        return createNavigateOnClickListener(i, bundle);
    }

    @JvmStatic
    public static final View.OnClickListener createNavigateOnClickListener(final int resId, final Bundle args) {
        return new View.OnClickListener() { // from class: androidx.navigation.Navigation$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Navigation.createNavigateOnClickListener$lambda$0(resId, args, view);
            }
        };
    }

    static final void createNavigateOnClickListener$lambda$0(int i, Bundle bundle, View view) {
        Intrinsics.checkNotNull(view);
        findNavController(view).navigate(i, bundle);
    }

    @JvmStatic
    public static final View.OnClickListener createNavigateOnClickListener(final NavDirections directions) {
        Intrinsics.checkNotNullParameter(directions, "directions");
        return new View.OnClickListener() { // from class: androidx.navigation.Navigation$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Navigation.createNavigateOnClickListener$lambda$1(directions, view);
            }
        };
    }

    static final void createNavigateOnClickListener$lambda$1(NavDirections navDirections, View view) {
        Intrinsics.checkNotNull(view);
        findNavController(view).navigate(navDirections);
    }

    @JvmStatic
    public static final void setViewNavController(View view, NavController controller) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTag(R.id.nav_controller_view_tag, controller);
    }

    private final NavController findViewNavController(View view) {
        return (NavController) SequencesKt.firstOrNull(SequencesKt.mapNotNull(SequencesKt.generateSequence(view, (Function1<? super View, ? extends View>) new Function1() { // from class: androidx.navigation.Navigation$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Navigation.findViewNavController$lambda$2((View) obj);
            }
        }), new Function1() { // from class: androidx.navigation.Navigation$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Navigation.findViewNavController$lambda$3((View) obj);
            }
        }));
    }

    static final View findViewNavController$lambda$2(View it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Object parent = it.getParent();
        if (parent instanceof View) {
            return (View) parent;
        }
        return null;
    }

    static final NavController findViewNavController$lambda$3(View it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.getViewNavController(it);
    }

    private final NavController getViewNavController(View view) {
        Object tag = view.getTag(R.id.nav_controller_view_tag);
        if (tag instanceof WeakReference) {
            return (NavController) ((WeakReference) tag).get();
        }
        if (tag instanceof NavController) {
            return (NavController) tag;
        }
        return null;
    }
}
