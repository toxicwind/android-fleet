package com.hisense.uifac.network.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import com.hisense.uifac.menu.commonview.MenuSetUpListView;
import com.hisense.uifac.menu.commonview.RespondedKeyEvent;

/* JADX INFO: loaded from: classes.dex */
class NetworkListView extends MenuSetUpListView {
    public NetworkListView(Context context) {
        super(context);
    }

    public NetworkListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NetworkListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.hisense.uifac.menu.commonview.CustListView, android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean zOnKeyDown = super.onKeyDown(i, keyEvent);
        KeyEvent.Callback selectedView = getSelectedView();
        if (selectedView instanceof RespondedKeyEvent) {
            if (i == 21) {
                ((RespondedKeyEvent) selectedView).onKeyLeft();
            } else if (i == 22) {
                ((RespondedKeyEvent) selectedView).onKeyRight();
            } else if (i == 4390) {
                return true;
            }
        }
        return zOnKeyDown;
    }
}
