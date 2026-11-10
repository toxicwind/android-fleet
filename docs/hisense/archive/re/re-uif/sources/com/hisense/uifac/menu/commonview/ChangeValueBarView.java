package com.hisense.uifac.menu.commonview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;
import com.hisense.uifac.menu.util.MenuConfigManager;

/* JADX INFO: loaded from: classes.dex */
public class ChangeValueBarView extends LinearLayout {
    private Context mContext;
    private SetConfigListViewAdapter.DataItem mData;
    private Handler mHandler;
    private ProChangeListener mListener;
    private TextView mNameTextView;
    private int mOffset;
    private int mPosition;
    private ProgressBar mShowView;
    private int mStepValue;
    private TextView mValueView;

    public class ProChangeListener implements OnValueChangedListener {
        public ProChangeListener() {
        }

        @Override // com.hisense.uifac.menu.commonview.OnValueChangedListener
        public void onValueChanged(View view, int i) {
            String str = ((ChangeValueBarView) view).mData.mItemID;
            MenuConfigManager menuConfigManager = MenuConfigManager.getInstance(ChangeValueBarView.this.mContext);
            if (str != null) {
                menuConfigManager.setValue(str, i);
                return;
            }
            Log.e("Fac_ChangeValueBarView", "Item " + str + " return null");
        }
    }

    public ChangeValueBarView(Context context) {
        super(context);
        this.mContext = context;
    }

    public ChangeValueBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 21 || i == 22) {
            this.mHandler.removeMessages(100);
            this.mHandler.sendEmptyMessageDelayed(100, 30000L);
            changeValue(i);
            return true;
        }
        if (i == 82 || i == 23 || i == 66) {
            this.mHandler.removeMessages(100);
            this.mHandler.sendEmptyMessage(101);
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void setAdapter(SetConfigListViewAdapter.DataItem dataItem) {
        this.mData = dataItem;
        initUI();
    }

    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }

    public void setViewName(String str) {
        this.mNameTextView.setText(str);
    }

    public void showValue(int i) {
        this.mValueView.setText("" + i);
    }

    private void changeValue(int i) {
        int i2;
        if (i == 21 && (i2 = this.mPosition) > 0) {
            this.mPosition = i2 - this.mStepValue;
        } else if (i == 22 && this.mPosition < this.mShowView.getMax()) {
            this.mPosition += this.mStepValue;
        }
        SetConfigListViewAdapter.DataItem dataItem = this.mData;
        int i3 = dataItem.mInitValue;
        int i4 = this.mPosition;
        int i5 = this.mOffset;
        if (i3 != i4 - i5) {
            dataItem.mInitValue = i4 - i5;
            this.mShowView.setProgress(i4);
            showValue(this.mData.mInitValue);
            ProChangeListener proChangeListener = this.mListener;
            if (proChangeListener != null) {
                proChangeListener.onValueChanged(this, this.mData.mInitValue);
            } else {
                Log.d("Fac_ChangeValueBarView", "Change Value Bar View Lisenter is null");
            }
        }
    }

    private void initUI() {
        View viewInflate = LinearLayout.inflate(this.mContext, R.layout.menu_change_value_bar, null);
        viewInflate.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        addView(viewInflate);
        this.mNameTextView = (TextView) findViewById(R.id.menu_arg_title);
        this.mValueView = (TextView) findViewById(R.id.menu_arg_value);
        ProgressBar progressBar = this.mShowView;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.menu_arg_pb);
        this.mShowView = progressBar2;
        progressBar2.setVisibility(0);
        initValue();
    }

    private void initValue() {
        this.mOffset = -this.mData.getmStartValue();
        this.mShowView.setMax(this.mData.getmEndValue() - this.mData.getmStartValue());
        this.mPosition = this.mData.getmInitValue() + this.mOffset;
        this.mStepValue = this.mData.getmStepValue();
        this.mShowView.setProgress(this.mPosition);
        showValue(this.mData.mInitValue);
        setViewName(this.mData.getmName());
        this.mShowView.setVisibility(0);
        if (this.mListener == null) {
            this.mListener = new ProChangeListener();
        }
    }
}
