package com.lagradost.cloudstream3.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.DialogFragment;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.viewbinding.ViewBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ&\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u001aH\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u0004\u0018\u00018\u0000X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/ui/BaseDialogFragment;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/viewbinding/ViewBinding;", "Landroidx/fragment/app/DialogFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragmentHelper;", "bindingCreator", "Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator;", "<init>", "(Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator;)V", "getBindingCreator", "()Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator;", "_binding", "get_binding", "()Landroidx/viewbinding/ViewBinding;", "set_binding", "(Landroidx/viewbinding/ViewBinding;)V", "Landroidx/viewbinding/ViewBinding;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onDestroyView", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class BaseDialogFragment<T extends ViewBinding> extends DialogFragment implements BaseFragmentHelper<T> {
    private T _binding;
    private final BaseFragment.BindingCreator<T> bindingCreator;

    public BaseDialogFragment(BaseFragment.BindingCreator<T> bindingCreator) {
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
    public BaseFragment.BindingCreator<T> getBindingCreator() {
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

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        set_binding(null);
    }
}
