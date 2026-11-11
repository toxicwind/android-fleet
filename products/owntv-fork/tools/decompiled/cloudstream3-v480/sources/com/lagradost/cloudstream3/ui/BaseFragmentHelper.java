package com.lagradost.cloudstream3.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\bb\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J&\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u001f\u0010\u001a\u001a\u00020\u00182\u0006\u0010\r\u001a\u00028\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0002\u0010\u001bJ\u0015\u0010\u001a\u001a\u00020\u00182\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\fJ\u000f\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0017¢\u0006\u0002\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0010H&R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00018\u0000X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u0004\u0018\u00018\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\n¨\u0006 À\u0006\u0003"}, d2 = {"Lcom/lagradost/cloudstream3/ui/BaseFragmentHelper;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/viewbinding/ViewBinding;", "", "bindingCreator", "Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator;", "getBindingCreator", "()Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator;", "_binding", "get_binding", "()Landroidx/viewbinding/ViewBinding;", "set_binding", "(Landroidx/viewbinding/ViewBinding;)V", "binding", "getBinding", "createBinding", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewReady", "", "view", "onBindingCreated", "(Landroidx/viewbinding/ViewBinding;Landroid/os/Bundle;)V", "pickLayout", "", "()Ljava/lang/Integer;", "fixLayout", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
interface BaseFragmentHelper<T extends ViewBinding> {
    View createBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    void fixLayout(View view);

    T getBinding();

    BaseFragment.BindingCreator<T> getBindingCreator();

    T get_binding();

    void onBindingCreated(T binding);

    void onBindingCreated(T binding, Bundle savedInstanceState);

    void onViewReady(View view, Bundle savedInstanceState);

    Integer pickLayout();

    void set_binding(T t);

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.BaseFragmentHelper$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: BaseFragment.kt */
    public final /* synthetic */ class CC {
        public static Integer $default$pickLayout(BaseFragmentHelper _this) {
            return null;
        }

        public static View $default$createBinding(BaseFragmentHelper _this, LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
            View root;
            T tInvoke;
            Intrinsics.checkNotNullParameter(inflater, "inflater");
            Integer numPickLayout = _this.pickLayout();
            T t = null;
            View viewInflate = numPickLayout != null ? inflater.inflate(numPickLayout.intValue(), viewGroup, false) : null;
            try {
                BaseFragment.BindingCreator<T> bindingCreator = _this.getBindingCreator();
                if (bindingCreator instanceof BaseFragment.BindingCreator.Inflate) {
                    tInvoke = ((BaseFragment.BindingCreator.Inflate) bindingCreator).getFn().invoke(inflater, viewGroup, false);
                } else {
                    if (!(bindingCreator instanceof BaseFragment.BindingCreator.Bind)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    if (viewInflate == null) {
                        throw new IllegalStateException("Root view is null for bind()");
                    }
                    tInvoke = ((BaseFragment.BindingCreator.Bind) bindingCreator).getFn().invoke(viewInflate);
                }
                t = tInvoke;
            } catch (Throwable th) {
                CommonActivity commonActivity = CommonActivity.INSTANCE;
                int i = R.string.unable_to_inflate;
                String message = th.getMessage();
                if (message == null) {
                    message = "";
                }
                commonActivity.showToast(TextUtilKt.txt(i, message), (Integer) 1);
                ArchComponentExtKt.logError(th);
            }
            _this.set_binding(t);
            ViewBinding viewBinding = _this.get_binding();
            return (viewBinding == null || (root = viewBinding.getRoot()) == null) ? viewInflate : root;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static void $default$onViewReady(BaseFragmentHelper baseFragmentHelper, View view, Bundle bundle) {
            Intrinsics.checkNotNullParameter(view, "view");
            baseFragmentHelper.fixLayout(view);
            ViewBinding binding = baseFragmentHelper.getBinding();
            if (binding != null) {
                baseFragmentHelper.onBindingCreated(binding, bundle);
            }
        }

        public static void $default$onBindingCreated(BaseFragmentHelper _this, ViewBinding binding, Bundle bundle) {
            Intrinsics.checkNotNullParameter(binding, "binding");
            _this.onBindingCreated(binding);
        }
    }

    /* JADX INFO: compiled from: BaseFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <T extends ViewBinding> T getBinding(BaseFragmentHelper<T> baseFragmentHelper) {
            return (T) baseFragmentHelper.get_binding();
        }

        @Deprecated
        public static <T extends ViewBinding> View createBinding(BaseFragmentHelper<T> baseFragmentHelper, LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
            Intrinsics.checkNotNullParameter(inflater, "inflater");
            return CC.$default$createBinding(baseFragmentHelper, inflater, viewGroup, bundle);
        }

        @Deprecated
        public static <T extends ViewBinding> void onViewReady(BaseFragmentHelper<T> baseFragmentHelper, View view, Bundle bundle) {
            Intrinsics.checkNotNullParameter(view, "view");
            CC.$default$onViewReady(baseFragmentHelper, view, bundle);
        }

        @Deprecated
        public static <T extends ViewBinding> void onBindingCreated(BaseFragmentHelper<T> baseFragmentHelper, T binding, Bundle bundle) {
            Intrinsics.checkNotNullParameter(binding, "binding");
            CC.$default$onBindingCreated(baseFragmentHelper, binding, bundle);
        }

        @Deprecated
        public static <T extends ViewBinding> void onBindingCreated(BaseFragmentHelper<T> baseFragmentHelper, T binding) {
            Intrinsics.checkNotNullParameter(binding, "binding");
            Intrinsics.checkNotNullParameter(binding, "binding");
        }

        @Deprecated
        public static <T extends ViewBinding> Integer pickLayout(BaseFragmentHelper<T> baseFragmentHelper) {
            return CC.$default$pickLayout(baseFragmentHelper);
        }
    }
}
