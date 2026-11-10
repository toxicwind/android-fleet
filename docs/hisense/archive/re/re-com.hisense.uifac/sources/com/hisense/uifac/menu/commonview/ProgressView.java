package com.hisense.uifac.menu.commonview;

import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;

/* JADX INFO: loaded from: classes.dex */
public class ProgressView extends ListViewItemView implements RespondedKeyEvent {
    private Context context;
    private SetConfigListViewAdapter.DataItem mDataItem;
    private String mId;
    private TextView mNameView;
    private int mOffset;
    private int mPostion;
    private ProgressBar mProgressView;
    private int mStepValue;
    private OnValueChangedListener mValueChangedListener;
    private TextView mValueView;

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void onKeyEnter() {
    }

    public ProgressView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public String getmId() {
        return this.mId;
    }

    public TextView getNameView() {
        return this.mNameView;
    }

    public ProgressBar getProgressView() {
        return this.mProgressView;
    }

    public TextView getValueView() {
        return this.mValueView;
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
        this.mId = dataItem.getmItemID();
        this.mOffset = -dataItem.getmStartValue();
        this.mProgressView.setMax(dataItem.getmEndValue() - dataItem.getmStartValue());
        this.mPostion = dataItem.getmInitValue() + this.mOffset;
        this.mStepValue = dataItem.getmStepValue();
        this.mProgressView.setProgress(this.mPostion);
        showValue(dataItem.getmInitValue());
        setViewName(dataItem.getmName());
    }

    public void setmId(String str) {
        this.mId = str;
    }

    public void setValueChangedListener(OnValueChangedListener onValueChangedListener) {
        this.mValueChangedListener = onValueChangedListener;
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void showValue(int i) {
        this.mValueView.setText("" + i);
        this.mDataItem.mInitValue = i;
        int i2 = i + this.mOffset;
        this.mPostion = i2;
        this.mProgressView.setProgress(i2);
    }

    private void init() {
        addView(RelativeLayout.inflate(this.context, R.layout.menu_progress_view, null));
        this.mNameView = (TextView) findViewById(R.id.common_tv_itemname);
        this.mProgressView = (ProgressBar) findViewById(R.id.common_ps_itemshow);
        this.mValueView = (TextView) findViewById(R.id.common_tv_itemvalue);
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mNameView.setTextColor(-7829368);
            this.mValueView.setTextColor(-7829368);
        }
    }

    private void setViewName(String str) {
        this.mNameView.setText(str);
    }

    private void switchValueNext() {
        if (this.mPostion <= this.mProgressView.getMax()) {
            if (this.mPostion != this.mProgressView.getMax()) {
                this.mPostion += this.mStepValue;
            }
            SetConfigListViewAdapter.DataItem dataItem = this.mDataItem;
            int i = this.mPostion - this.mOffset;
            dataItem.mInitValue = i;
            showValue(i);
            OnValueChangedListener onValueChangedListener = this.mValueChangedListener;
            if (onValueChangedListener != null) {
                onValueChangedListener.onValueChanged(this, this.mDataItem.mInitValue);
            }
        }
    }

    private void switchValuePrevious() {
        Log.i("Fac_ProgressView", "mPostion=" + this.mPostion);
        int i = this.mPostion;
        if (i >= 0) {
            if (i != 0) {
                this.mPostion = i - this.mStepValue;
            }
            SetConfigListViewAdapter.DataItem dataItem = this.mDataItem;
            int i2 = this.mPostion - this.mOffset;
            dataItem.mInitValue = i2;
            showValue(i2);
            Log.i("Fac_ProgressView", "init=" + this.mDataItem.mInitValue);
            if (this.mValueChangedListener != null) {
                Log.i("Fac_ProgressView", "***change value");
                this.mValueChangedListener.onValueChanged(this, this.mDataItem.mInitValue);
            }
        }
    }
}
