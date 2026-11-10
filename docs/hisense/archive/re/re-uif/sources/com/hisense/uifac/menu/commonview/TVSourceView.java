package com.hisense.uifac.menu.commonview;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;

/* JADX INFO: loaded from: classes.dex */
public class TVSourceView extends ListViewItemView implements RespondedKeyEvent {
    private Context context;
    private String[] mData;
    private TextView num;
    private TextView sourceName;

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

    public TVSourceView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public void init() {
        addView(RelativeLayout.inflate(this.context, R.layout.menu_parental_tvsource_view, null));
        this.num = (TextView) findViewById(R.id.common_tvsourceview_text_num);
        this.sourceName = (TextView) findViewById(R.id.common_ctvsourceview_text_name);
    }

    public void setAdapter(SetConfigListViewAdapter.DataItem dataItem) {
        this.mData = dataItem.getmOptionValue();
        setInputSourceData();
        dataItem.getmItemID();
        dataItem.getmSubChildGroup();
    }

    public void setInputSourceData() {
        this.num.setText(this.mData[0]);
        this.sourceName.setText(this.mData[1]);
    }
}
