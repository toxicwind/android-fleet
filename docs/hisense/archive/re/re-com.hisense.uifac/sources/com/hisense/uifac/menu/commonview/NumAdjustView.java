package com.hisense.uifac.menu.commonview;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class NumAdjustView extends ListViewItemView implements RespondedKeyEvent {
    private Context context;
    DelayController delayController;
    private SetConfigListViewAdapter.DataItem mDataItem;
    private String mId;
    private TextView mNameView;
    private OnValueChangedListener mValueChangedListener;
    private TextView mValueView;

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void onKeyEnter() {
    }

    private class DelayController {
        private List<Timer> timers;

        private DelayController() {
            this.timers = new ArrayList();
        }

        public void start() {
            MyTimerTask myTimerTask = new MyTimerTask();
            Timer timer = new Timer();
            this.timers.add(timer);
            timer.schedule(myTimerTask, 2000L);
            for (int i = 0; i < this.timers.size() - 1; i++) {
                this.timers.get(i).cancel();
            }
        }
    }

    private class MyTimerTask extends TimerTask {
        private MyTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (NumAdjustView.this.mValueChangedListener != null) {
                OnValueChangedListener onValueChangedListener = NumAdjustView.this.mValueChangedListener;
                NumAdjustView numAdjustView = NumAdjustView.this;
                onValueChangedListener.onValueChanged(numAdjustView, numAdjustView.mDataItem.mInitValue);
            }
        }
    }

    public NumAdjustView(Context context) {
        super(context);
        this.delayController = new DelayController();
        this.context = context;
        init();
    }

    public String getmId() {
        return this.mId;
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void onKeyLeft() {
        this.delayController.start();
        if (this.mDataItem.isEnable) {
            switchValuePrevious();
        }
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void onKeyRight() {
        this.delayController.start();
        if (this.mDataItem.isEnable) {
            switchValueNext();
        }
    }

    public void setAdapter(SetConfigListViewAdapter.DataItem dataItem) {
        this.mDataItem = dataItem;
        this.mId = dataItem.mItemID;
        setViewName(dataItem.getmName());
        this.mValueView.setText(dataItem.mInitValue + "");
    }

    public void setmId(String str) {
        this.mId = str;
    }

    public void setValueChangedListener(OnValueChangedListener onValueChangedListener) {
        this.mValueChangedListener = onValueChangedListener;
    }

    @Override // com.hisense.uifac.menu.commonview.RespondedKeyEvent
    public void showValue(int i) {
        if (i < this.mDataItem.getmStartValue() || i > this.mDataItem.getmEndValue()) {
            throw new IllegalArgumentException("value is Illegal value");
        }
        this.mDataItem.mInitValue = i;
        this.mValueView.setText(i + "");
    }

    protected void switchValueNext() {
        SetConfigListViewAdapter.DataItem dataItem = this.mDataItem;
        dataItem.mInitValue += dataItem.getmStepValue();
        SetConfigListViewAdapter.DataItem dataItem2 = this.mDataItem;
        if (dataItem2.mInitValue >= dataItem2.getmEndValue()) {
            SetConfigListViewAdapter.DataItem dataItem3 = this.mDataItem;
            dataItem3.mInitValue = dataItem3.getmStartValue();
        }
        this.mValueView.setText(this.mDataItem.mInitValue + "");
    }

    protected void switchValuePrevious() {
        SetConfigListViewAdapter.DataItem dataItem = this.mDataItem;
        dataItem.mInitValue -= dataItem.getmStepValue();
        SetConfigListViewAdapter.DataItem dataItem2 = this.mDataItem;
        if (dataItem2.mInitValue <= dataItem2.getmStartValue()) {
            SetConfigListViewAdapter.DataItem dataItem3 = this.mDataItem;
            dataItem3.mInitValue = dataItem3.getmEndValue();
        }
        this.mValueView.setText(this.mDataItem.mInitValue + "");
    }

    private void init() {
        addView(RelativeLayout.inflate(this.context, R.layout.menu_option_view, null));
        this.mNameView = (TextView) findViewById(R.id.common_tv_itemname);
        this.mValueView = (TextView) findViewById(R.id.common_tv_itemshow);
    }

    private void setViewName(String str) {
        this.mNameView.setText(str);
    }
}
