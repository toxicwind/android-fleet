package com.lagradost.cloudstream3.ui.home;

import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.settings.Globals;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeChildItemAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\f\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u001a\u0010\u0007\u001a\u00020\u0002X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/HomeScrollViewHolderState;", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "view", "Landroidx/viewbinding/ViewBinding;", "<init>", "(Landroidx/viewbinding/ViewBinding;)V", "wasFocused", "getWasFocused", "()Z", "setWasFocused", "(Z)V", "save", "()Ljava/lang/Boolean;", "restore", "", "state", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HomeScrollViewHolderState extends ViewHolderState<Boolean> {
    private boolean wasFocused;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScrollViewHolderState(ViewBinding view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
    }

    @Override // com.lagradost.cloudstream3.ui.ViewHolderState
    public /* bridge */ /* synthetic */ void restore(Boolean bool) {
        restore(bool.booleanValue());
    }

    public final boolean getWasFocused() {
        return this.wasFocused;
    }

    public final void setWasFocused(boolean z) {
        this.wasFocused = z;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lagradost.cloudstream3.ui.ViewHolderState
    public Boolean save() {
        return Boolean.valueOf(this.wasFocused);
    }

    public void restore(boolean state) {
        if (state) {
            this.wasFocused = false;
            if (Globals.INSTANCE.isLayout(2)) {
                this.itemView.requestFocus();
            }
        }
    }
}
