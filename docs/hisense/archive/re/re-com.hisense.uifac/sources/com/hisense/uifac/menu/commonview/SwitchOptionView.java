package com.hisense.uifac.menu.commonview;

import android.content.Context;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;
import com.hisense.uifac.menu.util.MenuConfigManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SwitchOptionView extends OptionView {
    private Context context;
    private List<SetConfigListViewAdapter.DataItem> mChildGroup;
    private SetConfigListViewAdapter.DataItem mDataItem;

    public SwitchOptionView(Context context) {
        super(context);
        this.context = context;
    }

    @Override // com.hisense.uifac.menu.commonview.OptionView
    public void setAdapter(SetConfigListViewAdapter.DataItem dataItem) {
        super.setAdapter(dataItem);
        this.mChildGroup = dataItem.getmEffectGroup();
        this.mDataItem = dataItem;
    }

    @Override // com.hisense.uifac.menu.commonview.OptionView
    protected void switchValueNext() {
        super.switchValueNext();
        if ("HIFAC_3DMODE".equals(this.mDataItem.getmItemID())) {
            ArrayList<Boolean> arrayList = MenuConfigManager.getInstance(this.context).get3DConfig();
            this.mDataItem.isEnable = arrayList.get(0).booleanValue();
            Iterator<SetConfigListViewAdapter.DataItem> it = this.mChildGroup.iterator();
            while (it.hasNext()) {
                it.next().isEnable = true;
            }
            return;
        }
        Iterator<SetConfigListViewAdapter.DataItem> it2 = this.mChildGroup.iterator();
        while (it2.hasNext()) {
            it2.next().isEnable = true;
        }
    }

    @Override // com.hisense.uifac.menu.commonview.OptionView
    protected void switchValuePrevious() {
        super.switchValuePrevious();
        if ("HIFAC_3DMODE".equals(this.mDataItem.getmItemID())) {
            ArrayList<Boolean> arrayList = MenuConfigManager.getInstance(this.context).get3DConfig();
            this.mDataItem.isEnable = arrayList.get(0).booleanValue();
            Iterator<SetConfigListViewAdapter.DataItem> it = this.mChildGroup.iterator();
            while (it.hasNext()) {
                it.next().isEnable = true;
            }
            return;
        }
        Iterator<SetConfigListViewAdapter.DataItem> it2 = this.mChildGroup.iterator();
        while (it2.hasNext()) {
            it2.next().isEnable = true;
        }
    }
}
