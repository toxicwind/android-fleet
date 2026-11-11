package com.lagradost.cloudstream3.ui.settings;

import android.view.View;
import com.lagradost.cloudstream3.syncproviders.AuthData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AccountAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/AccountClickCallback;", "", "action", "", "view", "Landroid/view/View;", "card", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", "<init>", "(ILandroid/view/View;Lcom/lagradost/cloudstream3/syncproviders/AuthData;)V", "getAction", "()I", "getView", "()Landroid/view/View;", "getCard", "()Lcom/lagradost/cloudstream3/syncproviders/AuthData;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AccountClickCallback {
    private final int action;
    private final AuthData card;
    private final View view;

    public AccountClickCallback(int i, View view, AuthData card) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(card, "card");
        this.action = i;
        this.view = view;
        this.card = card;
    }

    public final int getAction() {
        return this.action;
    }

    public final AuthData getCard() {
        return this.card;
    }

    public final View getView() {
        return this.view;
    }
}
