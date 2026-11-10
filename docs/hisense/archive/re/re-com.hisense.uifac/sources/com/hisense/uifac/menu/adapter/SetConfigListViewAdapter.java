package com.hisense.uifac.menu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.commonview.EffectOptionView;
import com.hisense.uifac.menu.commonview.HaveSubPageView;
import com.hisense.uifac.menu.commonview.NumAdjustView;
import com.hisense.uifac.menu.commonview.NumView;
import com.hisense.uifac.menu.commonview.OnValueChangedListener;
import com.hisense.uifac.menu.commonview.OptionView;
import com.hisense.uifac.menu.commonview.ProgressView;
import com.hisense.uifac.menu.commonview.ProgressView2;
import com.hisense.uifac.menu.commonview.SwitchOptionView;
import com.hisense.uifac.menu.commonview.TVSourceView;
import com.hisense.uifac.menu.commonview.TextCommView;
import com.hisense.uifac.menu.util.MenuConfigManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SetConfigListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<DataItem> mGroup;

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    public static class DataItem {
        public boolean isEnable = true;
        private volatile DataType mDataType;
        public List<DataItem> mEffectGroup;
        public int mEndValue;
        public HashMap<Integer, int[]> mHashMap;
        public int mInitValue;
        public String mItemID;
        public String mName;
        public String[] mOptionValue;
        public DataItem mParent;
        public List<DataItem> mParentGroup;
        public int mPosition;
        public String mShowValue;
        public int mStartValue;
        public String mStatusName;
        public int mStepValue;
        public List<DataItem> mSubChildGroup;
        public int userDefined;

        public enum DataType {
            OPTIONVIEW,
            PROGRESSBAR,
            PROGRESSBAR2,
            HAVESUBCHILD,
            INPUTBOX,
            EFFECTOPTIONVIEW,
            SWICHOPTIONVIEW,
            CHANNELLISTVIEW,
            TEXTCOMMVIEW,
            NUMVIEW,
            NUMADJUSTVIEW,
            TVSOURCEVIEW,
            PASSWORDVIEW
        }

        public DataItem(String str, String str2, int i, int i2, int i3, String[] strArr, int i4, DataType dataType) {
            this.mDataType = DataType.OPTIONVIEW;
            this.mItemID = str;
            this.mName = str2;
            this.mDataType = dataType;
            if (dataType == DataType.PROGRESSBAR || dataType == DataType.PROGRESSBAR2 || dataType == DataType.NUMVIEW || dataType == DataType.NUMADJUSTVIEW) {
                this.mStartValue = i;
                this.mEndValue = i2;
                this.mInitValue = i3;
                this.mStepValue = i4;
            }
            if (dataType == DataType.OPTIONVIEW || dataType == DataType.EFFECTOPTIONVIEW || dataType == DataType.SWICHOPTIONVIEW || dataType == DataType.NUMVIEW || dataType == DataType.CHANNELLISTVIEW || dataType == DataType.TVSOURCEVIEW) {
                this.mOptionValue = strArr;
                if (i != 10004) {
                    this.mStartValue = i;
                }
                String[] strArr2 = this.mOptionValue;
                if (strArr2 != null && (i3 < 0 || i3 > strArr2.length - 1)) {
                    Log.e("Fac_SetConfigListViewAdapter", "!!!!!!!!!!!!!!!!!!!!!!!!!!!This value is not valid, should check!");
                    i3 = 0;
                }
                this.mInitValue = i3;
            }
            if (dataType == DataType.INPUTBOX) {
                this.mOptionValue = strArr;
            }
        }

        public DataItem(String str, String str2, int i, int i2, int i3, String[] strArr, int i4, DataType dataType, String str3) {
            this.mDataType = DataType.OPTIONVIEW;
            this.mItemID = str;
            this.mName = str2;
            this.mDataType = dataType;
            this.mShowValue = str3;
        }

        public DataItem(String str, String str2, int i, int i2, String str3, String[] strArr, int i3, DataType dataType) {
            this.mDataType = DataType.OPTIONVIEW;
            this.mItemID = str;
            this.mName = str2;
            this.mDataType = dataType;
            this.mStartValue = i;
            this.mEndValue = i2;
            this.mStatusName = str3;
            this.mStepValue = i3;
        }

        public DataType getDataType() {
            return this.mDataType;
        }

        public List<DataItem> getmEffectGroup() {
            return this.mEffectGroup;
        }

        public int getmEndValue() {
            return this.mEndValue;
        }

        public String getmStatusName() {
            return this.mStatusName;
        }

        public HashMap<Integer, int[]> getmHashMap() {
            return this.mHashMap;
        }

        public int getmInitValue() {
            return this.mInitValue;
        }

        public String getmItemID() {
            return this.mItemID;
        }

        public String getmName() {
            return this.mName;
        }

        public String[] getmOptionValue() {
            return this.mOptionValue;
        }

        public List<DataItem> getmParentGroup() {
            return this.mParentGroup;
        }

        public int getmStartValue() {
            return this.mStartValue;
        }

        public int getmStepValue() {
            return this.mStepValue;
        }

        public List<DataItem> getmSubChildGroup() {
            return this.mSubChildGroup;
        }

        public int getPosition() {
            return this.mPosition;
        }

        public void setEnable(boolean z) {
            this.isEnable = z;
        }

        public void setmEndValue(int i) {
            this.mEndValue = i;
        }

        public void setPosition(int i) {
            this.mPosition = i;
        }

        public void setUserDefined(int i) {
            this.userDefined = i;
        }

        public String toString() {
            return "DataItem [mDataType=" + this.mDataType + ", mEndValue=" + this.mEndValue + ", mInitValue=" + this.mInitValue + ", mStepValue=" + this.mStepValue + ", mItemID=" + this.mItemID + ", mName=" + this.mName + ", mOptionValue=" + Arrays.toString(this.mOptionValue) + ", mStartValue=" + this.mStartValue + ", mSubChildGroup=" + this.mSubChildGroup + ", mShowValue=" + this.mShowValue + "]";
        }
    }

    private class ViewHolder {
        EffectOptionView mEffectOptionView;
        HaveSubPageView mHaveSubPageView;
        NumAdjustView mNumAdjustView;
        NumView mNumView;
        OptionView mOptionView;
        ProgressView mProgressView;
        ProgressView2 mProgressView2;
        SwitchOptionView mSwitchOptionView;
        TextCommView textCommView;
        TVSourceView tvSourceView;

        private ViewHolder(SetConfigListViewAdapter setConfigListViewAdapter) {
        }
    }

    public SetConfigListViewAdapter(Context context) {
        this.mContext = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<DataItem> list = this.mGroup;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int getFirstEnableItemPosition() {
        for (int i = 0; i <= getCount() - 1; i++) {
            if (isEnabled(i)) {
                return i;
            }
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        List<DataItem> list = this.mGroup;
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        if (this.mGroup == null) {
            return 0L;
        }
        return i;
    }

    public int getLastEnableItemPosition() {
        int count = getCount() - 1;
        for (int count2 = getCount() - 1; count2 >= 0; count2--) {
            if (isEnabled(count2)) {
                return count2;
            }
        }
        return count;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        final MenuConfigManager menuConfigManager = MenuConfigManager.getInstance(this.mContext);
        ViewHolder viewHolder = new ViewHolder();
        List<DataItem> list = this.mGroup;
        if (list != null && list.size() > 0) {
            DataItem dataItem = this.mGroup.get(i);
            dataItem.setPosition(i);
            if (dataItem.getDataType() == DataItem.DataType.OPTIONVIEW) {
                if (viewHolder.mOptionView == null) {
                    viewHolder.mOptionView = new OptionView(this.mContext);
                }
                if (!isEnabled(i)) {
                    viewHolder.mOptionView.getNameView().setTextColor(-7829368);
                    viewHolder.mOptionView.getValueView().setTextColor(-7829368);
                }
                viewHolder.mOptionView.setAdapter(dataItem);
                viewHolder.mOptionView.setmId(dataItem.mItemID);
                viewHolder.mOptionView.setValueChangedListener(new OnValueChangedListener(this) { // from class: com.hisense.uifac.menu.adapter.SetConfigListViewAdapter.1
                    @Override // com.hisense.uifac.menu.commonview.OnValueChangedListener
                    public void onValueChanged(View view2, int i2) {
                        String str = ((OptionView) view2).getmId();
                        MenuConfigManager menuConfigManager2 = menuConfigManager;
                        menuConfigManager2.setValue(str, i2 + menuConfigManager2.getMin(str));
                    }
                });
                return viewHolder.mOptionView;
            }
            if (dataItem.getDataType() == DataItem.DataType.NUMVIEW) {
                if (viewHolder.mNumView == null) {
                    viewHolder.mNumView = new NumView(this.mContext);
                }
                viewHolder.mNumView.setAdapter(dataItem);
                viewHolder.mNumView.setmId(dataItem.mItemID);
                viewHolder.mNumView.setValueChangedListener(new OnValueChangedListener(this) { // from class: com.hisense.uifac.menu.adapter.SetConfigListViewAdapter.2
                    @Override // com.hisense.uifac.menu.commonview.OnValueChangedListener
                    public void onValueChanged(View view2, int i2) {
                    }
                });
                return viewHolder.mNumView;
            }
            if (dataItem.getDataType() == DataItem.DataType.NUMADJUSTVIEW) {
                if (viewHolder.mNumAdjustView == null) {
                    viewHolder.mNumAdjustView = new NumAdjustView(this.mContext);
                }
                viewHolder.mNumAdjustView.setAdapter(dataItem);
                viewHolder.mNumAdjustView.setmId(dataItem.mItemID);
                viewHolder.mNumAdjustView.setValueChangedListener(new OnValueChangedListener(this) { // from class: com.hisense.uifac.menu.adapter.SetConfigListViewAdapter.3
                    @Override // com.hisense.uifac.menu.commonview.OnValueChangedListener
                    public void onValueChanged(View view2, int i2) {
                        menuConfigManager.setValue(((NumAdjustView) view2).getmId(), i2);
                    }
                });
                return viewHolder.mNumAdjustView;
            }
            if (dataItem.getDataType() == DataItem.DataType.EFFECTOPTIONVIEW) {
                if (viewHolder.mEffectOptionView == null) {
                    Log.i("Fac_SetConfigListViewAdapter", "effect null****");
                    viewHolder.mEffectOptionView = new EffectOptionView(this.mContext);
                }
                viewHolder.mEffectOptionView.setAdapter(dataItem);
                viewHolder.mEffectOptionView.setmId(dataItem.mItemID);
                viewHolder.mEffectOptionView.setValueChangedListener(new OnValueChangedListener(this) { // from class: com.hisense.uifac.menu.adapter.SetConfigListViewAdapter.4
                    @Override // com.hisense.uifac.menu.commonview.OnValueChangedListener
                    public void onValueChanged(View view2, int i2) {
                        String str = ((EffectOptionView) view2).getmId();
                        Log.i("Fac_SetConfigListViewAdapter", "set selId" + str + "val=" + i2);
                        MenuConfigManager menuConfigManager2 = menuConfigManager;
                        menuConfigManager2.setValue(str, i2 + menuConfigManager2.getMin(str));
                    }
                });
                return viewHolder.mEffectOptionView;
            }
            if (dataItem.getDataType() == DataItem.DataType.SWICHOPTIONVIEW) {
                if (viewHolder.mSwitchOptionView == null) {
                    viewHolder.mSwitchOptionView = new SwitchOptionView(this.mContext);
                }
                if (!isEnabled(i)) {
                    viewHolder.mSwitchOptionView.getNameView().setTextColor(-7829368);
                    viewHolder.mSwitchOptionView.getValueView().setTextColor(-7829368);
                }
                viewHolder.mSwitchOptionView.setAdapter(dataItem);
                viewHolder.mSwitchOptionView.setmId(dataItem.mItemID);
                viewHolder.mSwitchOptionView.setValueChangedListener(new OnValueChangedListener(this) { // from class: com.hisense.uifac.menu.adapter.SetConfigListViewAdapter.5
                    @Override // com.hisense.uifac.menu.commonview.OnValueChangedListener
                    public void onValueChanged(View view2, int i2) {
                        menuConfigManager.setValue(((SwitchOptionView) view2).getmId(), i2);
                    }
                });
                return viewHolder.mSwitchOptionView;
            }
            if (dataItem.getDataType() == DataItem.DataType.PROGRESSBAR) {
                if (viewHolder.mProgressView == null) {
                    viewHolder.mProgressView = new ProgressView(this.mContext);
                }
                if (!isEnabled(i)) {
                    Log.i("Fac_SetConfigListViewAdapter", "*****111");
                    viewHolder.mProgressView.getProgressView().setProgressDrawable(this.mContext.getResources().getDrawable(R.drawable.menu_probargray));
                    viewHolder.mProgressView.getNameView().setTextColor(-12303292);
                    viewHolder.mProgressView.getValueView().setTextColor(-12303292);
                }
                viewHolder.mProgressView.setAdapter(dataItem);
                viewHolder.mProgressView.setmId(dataItem.mItemID);
                viewHolder.mProgressView.setValueChangedListener(new OnValueChangedListener(this) { // from class: com.hisense.uifac.menu.adapter.SetConfigListViewAdapter.6
                    @Override // com.hisense.uifac.menu.commonview.OnValueChangedListener
                    public void onValueChanged(View view2, int i2) {
                        menuConfigManager.setValue(((ProgressView) view2).getmId(), i2);
                    }
                });
                return viewHolder.mProgressView;
            }
            if (dataItem.getDataType() == DataItem.DataType.PROGRESSBAR2) {
                if (viewHolder.mProgressView2 == null) {
                    viewHolder.mProgressView2 = new ProgressView2(this.mContext);
                }
                if (!isEnabled(i)) {
                    viewHolder.mProgressView2.getProgressView().setProgressDrawable(this.mContext.getResources().getDrawable(R.drawable.menu_probargray));
                    viewHolder.mProgressView2.getNameView().setTextColor(-12303292);
                    viewHolder.mProgressView2.getValueView().setTextColor(-12303292);
                }
                viewHolder.mProgressView2.setAdapter(dataItem);
                viewHolder.mProgressView2.setmId(dataItem.mItemID);
                viewHolder.mProgressView2.setValueChangedListener(new OnValueChangedListener(this) { // from class: com.hisense.uifac.menu.adapter.SetConfigListViewAdapter.7
                    @Override // com.hisense.uifac.menu.commonview.OnValueChangedListener
                    public void onValueChanged(View view2, int i2) {
                        Log.i("Fac_SetConfigListViewAdapter", "PROGRESSBAR2 setValue*************" + i2);
                        menuConfigManager.setValue(((ProgressView2) view2).getmId(), i2);
                    }
                });
                return viewHolder.mProgressView2;
            }
            if (dataItem.getDataType() == DataItem.DataType.HAVESUBCHILD) {
                if (viewHolder.mHaveSubPageView == null) {
                    viewHolder.mHaveSubPageView = new HaveSubPageView(this.mContext);
                }
                viewHolder.mHaveSubPageView.setAdapter(dataItem);
                viewHolder.mHaveSubPageView.setmId(dataItem.mItemID);
                if (!isEnabled(i)) {
                    viewHolder.mHaveSubPageView.getNameView().setTextColor(-7829368);
                }
                return viewHolder.mHaveSubPageView;
            }
            if (dataItem.getDataType() == DataItem.DataType.TEXTCOMMVIEW) {
                if (viewHolder.textCommView == null) {
                    viewHolder.textCommView = new TextCommView(this.mContext);
                }
                viewHolder.textCommView.setAdapter(dataItem);
                viewHolder.textCommView.setmId(dataItem.mItemID);
                return viewHolder.textCommView;
            }
            if (dataItem.getDataType() == DataItem.DataType.TVSOURCEVIEW) {
                if (viewHolder.tvSourceView == null) {
                    viewHolder.tvSourceView = new TVSourceView(this.mContext);
                }
                viewHolder.tvSourceView.setAdapter(dataItem);
                viewHolder.tvSourceView.setmId(dataItem.mItemID);
                return viewHolder.tvSourceView;
            }
            if (dataItem.getDataType() == DataItem.DataType.NUMVIEW) {
                if (viewHolder.mNumView == null) {
                    viewHolder.mNumView = new NumView(this.mContext);
                }
                viewHolder.mNumView.setAdapter(dataItem);
                viewHolder.mNumView.setmId(dataItem.mItemID);
                viewHolder.mNumView.setValueChangedListener(new OnValueChangedListener(this) { // from class: com.hisense.uifac.menu.adapter.SetConfigListViewAdapter.8
                    @Override // com.hisense.uifac.menu.commonview.OnValueChangedListener
                    public void onValueChanged(View view2, int i2) {
                    }
                });
                return viewHolder.mNumView;
            }
        }
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return this.mGroup.get(i).isEnable;
    }

    public void setmGroup(List<DataItem> list) {
        this.mGroup = list;
    }
}
