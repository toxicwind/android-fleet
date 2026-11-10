package com.hisense.uifac.menu.commonview;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;

/* JADX INFO: loaded from: classes.dex */
public class TextCommView extends ListViewItemView implements RespondedKeyEvent {
    private Context mContext;
    private SetConfigListViewAdapter.DataItem mDataItem;
    private TextView mNameTextView;
    private TextView textContent;

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

    public TextCommView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public void init() {
        addView(RelativeLayout.inflate(this.mContext, R.layout.menu_text_view, null));
        this.mNameTextView = (TextView) findViewById(R.id.common_text_itemname);
        this.textContent = (TextView) findViewById(R.id.common_text_content);
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mNameTextView.setTextColor(-7829368);
            this.textContent.setTextColor(-7829368);
        }
    }

    public void setAdapter(SetConfigListViewAdapter.DataItem dataItem) {
        this.mDataItem = dataItem;
        setViewName(dataItem.getmName());
        dataItem.getmItemID();
        this.textContent.setText(dataItem.mShowValue);
    }

    public void setViewName(String str) {
        this.mNameTextView.setText(str);
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void showValue(int i) {
        this.textContent.setText(this.mDataItem.mShowValue);
    }
}
