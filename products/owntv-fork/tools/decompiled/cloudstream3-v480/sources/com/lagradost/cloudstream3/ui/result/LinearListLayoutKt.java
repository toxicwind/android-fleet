package com.lagradost.cloudstream3.ui.result;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;

/* JADX INFO: compiled from: LinearListLayout.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a>\u0010\u0003\u001a\u00020\u0004*\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"FOCUS_SELF", "", "FOCUS_INHERIT", "setLinearListLayout", "", "Landroidx/recyclerview/widget/RecyclerView;", "isHorizontal", "", "nextLeft", "nextRight", "nextUp", "nextDown", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LinearListLayoutKt {
    public static final int FOCUS_INHERIT = -3;
    public static final int FOCUS_SELF = -2;

    public static /* synthetic */ void setLinearListLayout$default(RecyclerView recyclerView, boolean z, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            z = true;
        }
        if ((i5 & 2) != 0) {
            i = -3;
        }
        if ((i5 & 4) != 0) {
            i2 = -3;
        }
        if ((i5 & 8) != 0) {
            i3 = -3;
        }
        setLinearListLayout(recyclerView, z, i, i2, i3, (i5 & 16) != 0 ? -3 : i4);
    }

    public static final void setLinearListLayout(RecyclerView recyclerView, boolean z, int i, int i2, int i3, int i4) {
        Context context;
        if (recyclerView == null || (context = recyclerView.getContext()) == null) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        LinearListLayout linearListLayout = layoutManager instanceof LinearListLayout ? (LinearListLayout) layoutManager : null;
        if (linearListLayout == null) {
            linearListLayout = new LinearListLayout(context);
        }
        if (z) {
            linearListLayout.setHorizontal();
        } else {
            linearListLayout.setVertical();
        }
        if (i == -3) {
            i = recyclerView.getNextFocusLeftId();
        }
        linearListLayout.setNextFocusLeft(i);
        if (i2 == -3) {
            i2 = recyclerView.getNextFocusRightId();
        }
        linearListLayout.setNextFocusRight(i2);
        if (i3 == -3) {
            i3 = recyclerView.getNextFocusUpId();
        }
        linearListLayout.setNextFocusUp(i3);
        if (i4 == -3) {
            i4 = recyclerView.getNextFocusDownId();
        }
        linearListLayout.setNextFocusDown(i4);
        recyclerView.setLayoutManager(linearListLayout);
    }
}
