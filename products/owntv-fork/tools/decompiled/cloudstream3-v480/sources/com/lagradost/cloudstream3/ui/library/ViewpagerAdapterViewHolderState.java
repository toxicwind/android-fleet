package com.lagradost.cloudstream3.ui.library;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.recyclerview.widget.RecyclerView;
import com.lagradost.cloudstream3.databinding.LibraryViewpagerPageBinding;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewpagerAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/ui/library/ViewpagerAdapterViewHolderState;", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "Landroid/os/Bundle;", "binding", "Lcom/lagradost/cloudstream3/databinding/LibraryViewpagerPageBinding;", "<init>", "(Lcom/lagradost/cloudstream3/databinding/LibraryViewpagerPageBinding;)V", "getBinding", "()Lcom/lagradost/cloudstream3/databinding/LibraryViewpagerPageBinding;", "save", "restore", "", "state", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ViewpagerAdapterViewHolderState extends ViewHolderState<Bundle> {
    private final LibraryViewpagerPageBinding binding;

    public final LibraryViewpagerPageBinding getBinding() {
        return this.binding;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewpagerAdapterViewHolderState(LibraryViewpagerPageBinding binding) {
        super(binding);
        Intrinsics.checkNotNullParameter(binding, "binding");
        this.binding = binding;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lagradost.cloudstream3.ui.ViewHolderState
    public Bundle save() {
        Bundle bundle = new Bundle();
        RecyclerView.LayoutManager layoutManager = this.binding.pageRecyclerview.getLayoutManager();
        bundle.putParcelable("pageRecyclerview", layoutManager != null ? layoutManager.onSaveInstanceState() : null);
        return bundle;
    }

    @Override // com.lagradost.cloudstream3.ui.ViewHolderState
    public void restore(Bundle state) {
        RecyclerView.LayoutManager layoutManager;
        Intrinsics.checkNotNullParameter(state, "state");
        Parcelable parcelable = (Parcelable) (Build.VERSION.SDK_INT < 33 ? state.getParcelable("pageRecyclerview") : state.getParcelable("pageRecyclerview", Parcelable.class));
        if (parcelable == null || (layoutManager = this.binding.pageRecyclerview.getLayoutManager()) == null) {
            return;
        }
        layoutManager.onRestoreInstanceState(parcelable);
    }
}
