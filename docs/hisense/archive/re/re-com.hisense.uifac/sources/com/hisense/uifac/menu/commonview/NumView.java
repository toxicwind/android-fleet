package com.hisense.uifac.menu.commonview;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;

/* JADX INFO: loaded from: classes.dex */
public class NumView extends ListViewItemView implements RespondedKeyEvent {
    private Context context;
    private SetConfigListViewAdapter.DataItem mDataItem;
    private TextView mNameTextView;
    private TextView mShowView;
    private OnValueChangedListener mValueChangedListener;

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void onKeyEnter() {
    }

    public void setmId(String str) {
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void showValue(int i) {
    }

    public NumView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void onKeyLeft() {
        switchValuePrevious();
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void onKeyRight() {
        switchValueNext();
    }

    public void setAdapter(SetConfigListViewAdapter.DataItem dataItem) {
        this.mDataItem = dataItem;
        setViewName(dataItem.getmName());
        this.mShowView.setText(dataItem.mInitValue + ".000");
    }

    public void setValueChangedListener(OnValueChangedListener onValueChangedListener) {
        this.mValueChangedListener = onValueChangedListener;
    }

    public void setViewName(String str) {
        this.mNameTextView.setText(str);
    }

    protected void switchValueNext() {
        SetConfigListViewAdapter.DataItem dataItem = this.mDataItem;
        dataItem.mInitValue += 5;
        int i = dataItem.getmEndValue();
        SetConfigListViewAdapter.DataItem dataItem2 = this.mDataItem;
        if (dataItem2.mInitValue > i) {
            dataItem2.mInitValue = i;
        }
        this.mShowView.setText(this.mDataItem.mInitValue + ".00");
        OnValueChangedListener onValueChangedListener = this.mValueChangedListener;
        if (onValueChangedListener != null) {
            onValueChangedListener.onValueChanged(this, this.mDataItem.mInitValue);
        }
    }

    protected void switchValuePrevious() {
        int i = this.mDataItem.getmStartValue();
        SetConfigListViewAdapter.DataItem dataItem = this.mDataItem;
        int i2 = dataItem.mInitValue - 5;
        dataItem.mInitValue = i2;
        if (i2 < i) {
            dataItem.mInitValue = i;
        }
        this.mShowView.setText(this.mDataItem.mInitValue + ".00");
        OnValueChangedListener onValueChangedListener = this.mValueChangedListener;
        if (onValueChangedListener != null) {
            onValueChangedListener.onValueChanged(this, this.mDataItem.mInitValue);
        }
    }

    private void init() {
        addView(RelativeLayout.inflate(this.context, R.layout.menu_num_view, null));
        this.mNameTextView = (TextView) findViewById(R.id.common_tv_itemname);
        this.mShowView = (TextView) findViewById(R.id.common_tv_itemshow);
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mNameTextView.setTextColor(-7829368);
            this.mShowView.setTextColor(-7829368);
        }
    }
}
