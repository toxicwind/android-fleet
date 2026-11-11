package com.lagradost.cloudstream3.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedDispatcher;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004:\u0001$B\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J&\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\u0010\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0012H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u0004\u0018\u00018\u0000X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/ui/BaseFragment;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/viewbinding/ViewBinding;", "Landroidx/fragment/app/Fragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragmentHelper;", "bindingCreator", "Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator;", "<init>", "(Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator;)V", "getBindingCreator", "()Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator;", "_binding", "get_binding", "()Landroidx/viewbinding/ViewBinding;", "set_binding", "(Landroidx/viewbinding/ViewBinding;)V", "Landroidx/viewbinding/ViewBinding;", "dispatchBackPressed", "", "delayedDispatchBackPressed", "remaining", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onDestroyView", "BindingCreator", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class BaseFragment<T extends ViewBinding> extends Fragment implements BaseFragmentHelper<T> {
    private T _binding;
    private final BindingCreator<T> bindingCreator;

    public BaseFragment(BindingCreator<T> bindingCreator) {
        Intrinsics.checkNotNullParameter(bindingCreator, "bindingCreator");
        this.bindingCreator = bindingCreator;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public /* bridge */ View createBinding(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return BaseFragmentHelper.CC.$default$createBinding(this, layoutInflater, viewGroup, bundle);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public /* bridge */ T getBinding() {
        return (T) get_binding();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public /* bridge */ void onBindingCreated(T t) {
        Intrinsics.checkNotNullParameter(t, "binding");
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public /* bridge */ void onBindingCreated(T t, Bundle bundle) {
        BaseFragmentHelper.CC.$default$onBindingCreated(this, t, bundle);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public /* bridge */ void onViewReady(View view, Bundle bundle) {
        BaseFragmentHelper.CC.$default$onViewReady(this, view, bundle);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public /* bridge */ Integer pickLayout() {
        return BaseFragmentHelper.CC.$default$pickLayout(this);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public BindingCreator<T> getBindingCreator() {
        return this.bindingCreator;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public T get_binding() {
        return this._binding;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void set_binding(T t) {
        this._binding = t;
    }

    public final void dispatchBackPressed() {
        OnBackPressedDispatcher onBackPressedDispatcher;
        try {
            FragmentActivity activity = getActivity();
            if (activity == null || (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) == null) {
                return;
            }
            onBackPressedDispatcher.onBackPressed();
        } catch (IllegalStateException unused) {
            delayedDispatchBackPressed(5);
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
        }
    }

    private final void delayedDispatchBackPressed(final int remaining) {
        ViewBinding binding;
        View root;
        if (remaining <= 0 || (binding = getBinding()) == null || (root = binding.getRoot()) == null) {
            return;
        }
        root.postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.ui.BaseFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BaseFragment.delayedDispatchBackPressed$lambda$0(this.f$0, remaining);
            }
        }, 200L);
    }

    static final void delayedDispatchBackPressed$lambda$0(BaseFragment baseFragment, int i) {
        OnBackPressedDispatcher onBackPressedDispatcher;
        try {
            FragmentActivity activity = baseFragment.getActivity();
            if (activity == null || (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) == null) {
                return;
            }
            onBackPressedDispatcher.onBackPressed();
        } catch (IllegalStateException unused) {
            baseFragment.delayedDispatchBackPressed(i - 1);
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return createBinding(inflater, container, savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        onViewReady(view, savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        View view = getView();
        if (view != null) {
            fixLayout(view);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        set_binding(null);
    }

    /* JADX INFO: compiled from: BaseFragment.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u00020\u0003:\u0002\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/viewbinding/ViewBinding;", "", "<init>", "()V", "Inflate", "Bind", "Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator$Bind;", "Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator$Inflate;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class BindingCreator<T extends ViewBinding> {
        public /* synthetic */ BindingCreator(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private BindingCreator() {
        }

        /* JADX INFO: compiled from: BaseFragment.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B)\u0012 \u0010\u0004\u001a\u001c\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00020\u0005¢\u0006\u0004\b\t\u0010\nR+\u0010\u0004\u001a\u001c\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator$Inflate;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/viewbinding/ViewBinding;", "Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator;", "fn", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "<init>", "(Lkotlin/jvm/functions/Function3;)V", "getFn", "()Lkotlin/jvm/functions/Function3;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Inflate<T extends ViewBinding> extends BindingCreator<T> {
            private final Function3<LayoutInflater, ViewGroup, Boolean, T> fn;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public Inflate(Function3<? super LayoutInflater, ? super ViewGroup, ? super Boolean, ? extends T> fn) {
                super(null);
                Intrinsics.checkNotNullParameter(fn, "fn");
                this.fn = fn;
            }

            public final Function3<LayoutInflater, ViewGroup, Boolean, T> getFn() {
                return this.fn;
            }
        }

        /* JADX INFO: compiled from: BaseFragment.kt */
        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator$Bind;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/viewbinding/ViewBinding;", "Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator;", "fn", "Lkotlin/Function1;", "Landroid/view/View;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getFn", "()Lkotlin/jvm/functions/Function1;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Bind<T extends ViewBinding> extends BindingCreator<T> {
            private final Function1<View, T> fn;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public Bind(Function1<? super View, ? extends T> fn) {
                super(null);
                Intrinsics.checkNotNullParameter(fn, "fn");
                this.fn = fn;
            }

            public final Function1<View, T> getFn() {
                return this.fn;
            }
        }
    }
}
