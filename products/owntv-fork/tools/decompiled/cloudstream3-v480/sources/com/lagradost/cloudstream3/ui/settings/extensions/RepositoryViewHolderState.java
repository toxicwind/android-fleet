package com.lagradost.cloudstream3.ui.settings.extensions;

import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PluginAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryViewHolderState;", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "view", "Landroidx/viewbinding/ViewBinding;", "<init>", "(Landroidx/viewbinding/ViewBinding;)V", "recycleCount", "", "getRecycleCount", "()I", "setRecycleCount", "(I)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RepositoryViewHolderState extends ViewHolderState<Object> {
    private int recycleCount;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RepositoryViewHolderState(ViewBinding view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public final int getRecycleCount() {
        return this.recycleCount;
    }

    public final void setRecycleCount(int i) {
        this.recycleCount = i;
    }
}
