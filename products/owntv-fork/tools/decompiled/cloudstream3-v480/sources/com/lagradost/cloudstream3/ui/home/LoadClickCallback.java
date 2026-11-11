package com.lagradost.cloudstream3.ui.home;

import android.view.View;
import com.lagradost.cloudstream3.LoadResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeParentItemAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/LoadClickCallback;", "", "action", "", "view", "Landroid/view/View;", "position", "response", "Lcom/lagradost/cloudstream3/LoadResponse;", "<init>", "(ILandroid/view/View;ILcom/lagradost/cloudstream3/LoadResponse;)V", "getAction", "()I", "getView", "()Landroid/view/View;", "getPosition", "getResponse", "()Lcom/lagradost/cloudstream3/LoadResponse;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LoadClickCallback {
    private final int action;
    private final int position;
    private final LoadResponse response;
    private final View view;

    public LoadClickCallback(int i, View view, int i2, LoadResponse response) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(response, "response");
        this.action = i;
        this.view = view;
        this.position = i2;
        this.response = response;
    }

    public /* synthetic */ LoadClickCallback(int i, View view, int i2, LoadResponse loadResponse, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, view, i2, loadResponse);
    }

    public final int getAction() {
        return this.action;
    }

    public final View getView() {
        return this.view;
    }

    public final int getPosition() {
        return this.position;
    }

    public final LoadResponse getResponse() {
        return this.response;
    }
}
