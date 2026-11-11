package com.lagradost.cloudstream3.mvvm;

import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.utils.AppDebug;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Lifecycle.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0007\u001a4\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0014\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0004\u0012\u00020\u00010\u0007\u001aB\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\t*\u00020\n*\b\u0012\u0004\u0012\u0002H\t0\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0007\u001aD\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\t*\u00020\n*\b\u0012\u0004\u0012\u0002H\t0\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0014\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0004\u0012\u00020\u00010\u0007\u001a2\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\f2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0007\u001a4\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\f2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0014\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0004\u0012\u00020\u00010\u0007¨\u0006\r"}, d2 = {"observe", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/activity/ComponentActivity;", "liveData", "Landroidx/lifecycle/LiveData;", "action", "Lkotlin/Function1;", "observeNullable", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/viewbinding/ViewBinding;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Landroid/view/View;", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LifecycleKt {
    public static final <T> void observe(ComponentActivity componentActivity, LiveData<T> liveData, final Function1<? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        Intrinsics.checkNotNullParameter(liveData, "liveData");
        Intrinsics.checkNotNullParameter(action, "action");
        observeNullable(componentActivity, liveData, new Function1() { // from class: com.lagradost.cloudstream3.mvvm.LifecycleKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LifecycleKt.observe$lambda$0(action, obj);
            }
        });
    }

    static final Unit observe$lambda$0(Function1 function1, Object obj) {
        if (obj != null) {
            function1.invoke(obj);
        }
        return Unit.INSTANCE;
    }

    public static final <T> void observeNullable(ComponentActivity componentActivity, LiveData<T> liveData, Function1<? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        Intrinsics.checkNotNullParameter(liveData, "liveData");
        Intrinsics.checkNotNullParameter(action, "action");
        ComponentActivity componentActivity2 = componentActivity;
        liveData.removeObservers(componentActivity2);
        liveData.observe(componentActivity2, new LifecycleKt$sam$androidx_lifecycle_Observer$0(action));
    }

    public static final <T, V extends ViewBinding> void observe(BaseFragment<V> baseFragment, LiveData<T> liveData, final Function1<? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(baseFragment, "<this>");
        Intrinsics.checkNotNullParameter(liveData, "liveData");
        Intrinsics.checkNotNullParameter(action, "action");
        observeNullable(baseFragment, liveData, new Function1() { // from class: com.lagradost.cloudstream3.mvvm.LifecycleKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LifecycleKt.observe$lambda$1(action, obj);
            }
        });
    }

    static final Unit observe$lambda$1(Function1 function1, Object obj) {
        if (obj != null) {
            function1.invoke(obj);
        }
        return Unit.INSTANCE;
    }

    public static final <T, V extends ViewBinding> void observeNullable(final BaseFragment<V> baseFragment, final LiveData<T> liveData, final Function1<? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(baseFragment, "<this>");
        Intrinsics.checkNotNullParameter(liveData, "liveData");
        Intrinsics.checkNotNullParameter(action, "action");
        ViewBinding binding = baseFragment.getBinding();
        final View root = binding != null ? binding.getRoot() : null;
        if (root == null) {
            BaseFragment<V> baseFragment2 = baseFragment;
            liveData.removeObservers(baseFragment2);
            liveData.observe(baseFragment2, new LifecycleKt$sam$androidx_lifecycle_Observer$0(action));
        } else {
            if (root.isAttachedToWindow()) {
                BaseFragment<V> baseFragment3 = ViewTreeLifecycleOwner.get(root);
                if (baseFragment3 == null) {
                    baseFragment3 = baseFragment;
                }
                liveData.removeObservers(baseFragment3);
                liveData.observe(baseFragment3, new LifecycleKt$sam$androidx_lifecycle_Observer$0(action));
                return;
            }
            root.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.lagradost.cloudstream3.mvvm.LifecycleKt$observeNullable$$inlined$doOnAttach$1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view) {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view) {
                    root.removeOnAttachStateChangeListener(this);
                    BaseFragment baseFragment4 = ViewTreeLifecycleOwner.get(view);
                    if (baseFragment4 == null) {
                        baseFragment4 = baseFragment;
                    }
                    liveData.removeObservers(baseFragment4);
                    liveData.observe(baseFragment4, new LifecycleKt$sam$androidx_lifecycle_Observer$0(action));
                }
            });
        }
    }

    public static final <T> void observe(View view, LiveData<T> liveData, final Function1<? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(liveData, "liveData");
        Intrinsics.checkNotNullParameter(action, "action");
        observeNullable(view, liveData, new Function1() { // from class: com.lagradost.cloudstream3.mvvm.LifecycleKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LifecycleKt.observe$lambda$2(action, obj);
            }
        });
    }

    static final Unit observe$lambda$2(Function1 function1, Object obj) {
        if (obj != null) {
            function1.invoke(obj);
        }
        return Unit.INSTANCE;
    }

    public static final <T> void observeNullable(final View view, final LiveData<T> liveData, final Function1<? super T, Unit> action) throws DebugException {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(liveData, "liveData");
        Intrinsics.checkNotNullParameter(action, "action");
        if (view.isAttachedToWindow()) {
            LifecycleOwner lifecycleOwner = ViewTreeLifecycleOwner.get(view);
            if (lifecycleOwner != null) {
                liveData.removeObservers(lifecycleOwner);
                liveData.observe(lifecycleOwner, new LifecycleKt$sam$androidx_lifecycle_Observer$0(action));
                return;
            } else {
                if (AppDebug.INSTANCE.isDebug()) {
                    throw new DebugException("Expected non-null findViewTreeLifecycleOwner");
                }
                return;
            }
        }
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.lagradost.cloudstream3.mvvm.LifecycleKt$observeNullable$$inlined$doOnAttach$2
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view2) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view2) throws DebugException {
                view.removeOnAttachStateChangeListener(this);
                LifecycleOwner lifecycleOwner2 = ViewTreeLifecycleOwner.get(view2);
                if (lifecycleOwner2 == null) {
                    if (AppDebug.INSTANCE.isDebug()) {
                        throw new DebugException("Expected non-null findViewTreeLifecycleOwner");
                    }
                } else {
                    liveData.removeObservers(lifecycleOwner2);
                    liveData.observe(lifecycleOwner2, new LifecycleKt$sam$androidx_lifecycle_Observer$0(action));
                }
            }
        });
    }
}
