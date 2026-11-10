package com.hisense.uifac.menu.commonview;

import android.content.Context;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;

/* JADX INFO: loaded from: classes.dex */
public class EffectOptionView extends OptionView {
    public EffectOptionView(Context context) {
        super(context);
    }

    @Override // com.hisense.uifac.menu.commonview.OptionView
    public void setAdapter(SetConfigListViewAdapter.DataItem dataItem) {
        super.setAdapter(dataItem);
        dataItem.getmEffectGroup();
        dataItem.getmHashMap();
    }

    @Override // com.hisense.uifac.menu.commonview.OptionView
    protected void switchValueNext() {
        super.switchValueNext();
    }

    @Override // com.hisense.uifac.menu.commonview.OptionView
    protected void switchValuePrevious() {
        super.switchValuePrevious();
    }
}
