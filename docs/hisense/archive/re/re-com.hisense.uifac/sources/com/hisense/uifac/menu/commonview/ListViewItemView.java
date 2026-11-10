package com.hisense.uifac.menu.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
public class ListViewItemView extends RelativeLayout {
    protected Context context;

    public ListViewItemView(Context context) {
        super(context);
        this.context = context;
        setLayoutParams(new AbsListView.LayoutParams(-1, getSize()));
        setFocusable(true);
        setFocusableInTouchMode(true);
        setClickable(true);
        setEnabled(true);
    }

    public ListViewItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    public ListViewItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
    }

    public int getSize() {
        return ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getHeight() / 20;
    }
}
