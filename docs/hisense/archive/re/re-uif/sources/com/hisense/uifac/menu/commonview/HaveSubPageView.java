package com.hisense.uifac.menu.commonview;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;

/* JADX INFO: loaded from: classes.dex */
public class HaveSubPageView extends ListViewItemView implements RespondedKeyEvent {
    private Context context;
    private TextView mNameView;
    private TextView mStatusView;

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void onKeyEnter() {
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void onKeyLeft() {
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void onKeyRight() {
    }

    public void setmId(String str) {
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void showValue(int i) {
    }

    public HaveSubPageView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TextView getNameView() {
        return this.mNameView;
    }

    public void setAdapter(SetConfigListViewAdapter.DataItem dataItem) {
        setViewName(dataItem.getmName());
        setStatusName(dataItem.getmStatusName());
    }

    private void init() {
        addView(RelativeLayout.inflate(this.context, R.layout.menu_hava_subpage_view, null));
        this.mNameView = (TextView) findViewById(R.id.common_itemname);
        this.mStatusView = (TextView) findViewById(R.id.common_tv_itemshow);
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mNameView.setTextColor(-7829368);
            this.mStatusView.setTextColor(-7829368);
        }
    }

    private void setViewName(String str) {
        this.mNameView.setText(str);
    }

    private void setStatusName(String str) {
        this.mStatusView.setText(str);
    }
}
