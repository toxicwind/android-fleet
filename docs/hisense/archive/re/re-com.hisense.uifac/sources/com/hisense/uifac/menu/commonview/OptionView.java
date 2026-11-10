package com.hisense.uifac.menu.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;
import com.hisense.uifac.menu.util.MenuConfigManager;

/* JADX INFO: loaded from: classes.dex */
public class OptionView extends ListViewItemView implements RespondedKeyEvent {
    private Context context;
    private MenuConfigManager mConfig;
    private SetConfigListViewAdapter.DataItem mDataItem;
    private String mId;
    private ImageView mImageView;
    private TextView mNameView;
    private OnValueChangedListener mValueChangedListener;
    private TextView mValueView;

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void onKeyEnter() {
    }

    public OptionView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public OptionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        init();
    }

    public OptionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
        init();
    }

    public String getmId() {
        return this.mId;
    }

    public TextView getNameView() {
        return this.mNameView;
    }

    public TextView getValueView() {
        return this.mValueView;
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void onKeyLeft() {
        if (this.mDataItem.isEnable) {
            switchValuePrevious();
        }
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void onKeyRight() {
        if (this.mDataItem.isEnable) {
            switchValueNext();
        }
    }

    public void setAdapter(SetConfigListViewAdapter.DataItem dataItem) {
        this.mDataItem = dataItem;
        this.mId = dataItem.mItemID;
        setViewName(dataItem.getmName());
        TextView textView = this.mValueView;
        SetConfigListViewAdapter.DataItem dataItem2 = this.mDataItem;
        textView.setText(dataItem2.mOptionValue[dataItem2.mInitValue]);
        if (dataItem.mItemID.equals("HIFAC_F7_NETWORK") || dataItem.mItemID.equals("FACTORY_F7_BLUETOOTH")) {
            this.mImageView.setVisibility(4);
        }
    }

    public void setmId(String str) {
        this.mId = str;
    }

    public void setValueChangedListener(OnValueChangedListener onValueChangedListener) {
        this.mValueChangedListener = onValueChangedListener;
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void showValue(int i) {
        int i2 = this.mDataItem.mStartValue;
        if (i2 != 10004) {
            i -= i2;
        }
        String[] strArr = this.mDataItem.mOptionValue;
        if (strArr != null) {
            if (i < 0 || i > strArr.length - 1) {
                Log.e("Fac_OptionView", "!!!!!!!!!!!!!!!!!!!!!!!!!!!This value is not valid, should check!");
                i = 0;
            }
            SetConfigListViewAdapter.DataItem dataItem = this.mDataItem;
            dataItem.mInitValue = i;
            this.mValueView.setText(dataItem.mOptionValue[i]);
        }
    }

    protected void switchValueNext() {
        SetConfigListViewAdapter.DataItem dataItem = this.mDataItem;
        int i = dataItem.mInitValue;
        String[] strArr = dataItem.mOptionValue;
        if (i != strArr.length - 1) {
            TextView textView = this.mValueView;
            int i2 = i + 1;
            dataItem.mInitValue = i2;
            textView.setText(strArr[i2]);
        } else {
            dataItem.mInitValue = 0;
            this.mValueView.setText(strArr[0]);
        }
        if ("HIFAC_PICTURECURVE_MODE".equals(this.mDataItem.mItemID)) {
            this.mConfig.mPictureCurve_Mode = MenuConfigManager.PictureCurveMode.getName(this.mDataItem.mInitValue);
        }
        if (this.mValueChangedListener != null && !this.mDataItem.mItemID.equals("HIFAC_F7_NETWORK") && !this.mDataItem.mItemID.equals("FACTORY_F7_BLUETOOTH")) {
            this.mValueChangedListener.onValueChanged(this, this.mDataItem.mInitValue);
        }
        Log.v("Fac_OptionView", "***value" + this.mDataItem.mInitValue);
    }

    protected void switchValuePrevious() {
        SetConfigListViewAdapter.DataItem dataItem = this.mDataItem;
        int i = dataItem.mInitValue;
        if (i != 0) {
            TextView textView = this.mValueView;
            String[] strArr = dataItem.mOptionValue;
            int i2 = i - 1;
            dataItem.mInitValue = i2;
            textView.setText(strArr[i2]);
        } else {
            String[] strArr2 = dataItem.mOptionValue;
            int length = strArr2.length - 1;
            dataItem.mInitValue = length;
            this.mValueView.setText(strArr2[length]);
        }
        if ("HIFAC_PICTURECURVE_MODE".equals(this.mDataItem.mItemID)) {
            this.mConfig.mPictureCurve_Mode = MenuConfigManager.PictureCurveMode.getName(this.mDataItem.mInitValue);
        }
        if (this.mValueChangedListener == null || this.mDataItem.mItemID.equals("HIFAC_F7_NETWORK") || this.mDataItem.mItemID.equals("FACTORY_F7_BLUETOOTH")) {
            return;
        }
        this.mValueChangedListener.onValueChanged(this, this.mDataItem.mInitValue);
    }

    private void init() {
        addView(RelativeLayout.inflate(this.context, R.layout.menu_option_view, null));
        this.mNameView = (TextView) findViewById(R.id.common_tv_itemname);
        this.mValueView = (TextView) findViewById(R.id.common_tv_itemshow);
        this.mImageView = (ImageView) findViewById(R.id.common_iv_itemimage);
        this.mConfig = MenuConfigManager.getInstance(this.context);
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mNameView.setTextColor(-7829368);
            this.mValueView.setTextColor(-7829368);
        }
    }

    private void setViewName(String str) {
        this.mNameView.setText(str);
    }
}
