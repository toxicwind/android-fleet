package com.hisense.uifac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.hisense.serialInfoTest.util.Logger;
import com.hisense.tvcm.FacFunction_Adapter;

/* JADX INFO: loaded from: classes.dex */
public class GapAdjustActivity extends Activity implements View.OnFocusChangeListener {
    private static boolean isGAValueNull = false;
    ProgressBar mBar;
    Button mButton;
    private FacFunction_Adapter mFacFunction_Adapter;
    LinearLayout mLayout;
    TextView mText;
    TextView mValue;
    int stepValue = 0;
    int valueForBar = 0;
    private boolean f3KeyIsPressed = false;
    private final Handler mhHandler = new Handler() { // from class: com.hisense.uifac.GapAdjustActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            Logger.i("***msg=" + message.what);
            if (message.what != 0) {
                return;
            }
            GapAdjustActivity.this.f3KeyIsPressed = false;
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("Fac_GapAdjustActivity", "******Enter GapAdjust activity*****");
        setContentView(R.layout.gap_adjust);
        getWindow().addFlags(128);
        this.mFacFunction_Adapter = FacFunction_Adapter.getInstance(this);
        TextView textView = (TextView) findViewById(R.id.gap_adjust_text);
        this.mText = textView;
        textView.setText("Sliding Cover Gap Adjusting ...");
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.gap_adjust_layout);
        this.mLayout = linearLayout;
        linearLayout.setBackgroundResource(R.drawable.menu_tab_button_select_bg);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.gap_adjust_progressbar);
        this.mBar = progressBar;
        progressBar.setFocusable(true);
        this.mBar.setOnFocusChangeListener(this);
        this.mValue = (TextView) findViewById(R.id.gap_adjust_value);
        Button button = (Button) findViewById(R.id.gap_adjust_reset_button);
        this.mButton = button;
        button.setOnFocusChangeListener(this);
        this.mButton.setOnClickListener(new View.OnClickListener() { // from class: com.hisense.uifac.GapAdjustActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GapAdjustActivity.this.mFacFunction_Adapter.resetLaserMovableCoverStep();
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                GapAdjustActivity.this.mValue.setText(GapAdjustActivity.this.mFacFunction_Adapter.getLaserMovableCoverStep() + "/30");
                GapAdjustActivity gapAdjustActivity = GapAdjustActivity.this;
                gapAdjustActivity.mBar.setProgress(gapAdjustActivity.mFacFunction_Adapter.getLaserMovableCoverStep());
            }
        });
        initUI();
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        Log.d("Fac_GapAdjustActivity", "in GapAdjustActivity =====onFocusChange===id=====" + view.getId());
        switch (view.getId()) {
            case R.id.gap_adjust_progressbar /* 2131296653 */:
                this.mLayout.setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                this.mButton.setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                break;
            case R.id.gap_adjust_reset_button /* 2131296654 */:
                this.mButton.setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                this.mLayout.setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                break;
        }
    }

    private void initUI() {
        if (getGapAdjustValue()) {
            updateValue();
            return;
        }
        isGAValueNull = true;
        this.mValue.setText("NG");
        this.mBar.setProgress(0);
    }

    private boolean getGapAdjustValue() {
        int laserMovableCoverStep = this.mFacFunction_Adapter.getLaserMovableCoverStep();
        this.stepValue = laserMovableCoverStep;
        if (-1 == laserMovableCoverStep) {
            return false;
        }
        Log.i("Fac_GapAdjustActivity", "getGapAdjustValue is : " + this.stepValue);
        return true;
    }

    private void updateValue() {
        this.mValue.setText(this.stepValue + "/30");
        this.mBar.setProgress(this.stepValue);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Log.i("Fac_GapAdjustActivity", "in GapAdjustActivity onKeyDown keycode = " + i + " keyEvent = " + keyEvent.toString());
        if (i == 4) {
            this.mFacFunction_Adapter.saveLaserMovableCoverStep();
            startActivity(new Intent(this, (Class<?>) FacRootMenu.class));
        } else if (i != 22) {
            if (i != 4208) {
                if (i != 8) {
                    if (i != 9) {
                        if (i == 19 || i == 20) {
                            if (this.mBar.requestFocus()) {
                                this.mBar.setFocusable(false);
                                this.mButton.setFocusable(true);
                            } else {
                                this.mBar.setFocusable(true);
                                this.mButton.setFocusable(false);
                            }
                        }
                    } else if (this.f3KeyIsPressed) {
                        this.mFacFunction_Adapter.setLaserSlidingCoverMode(1);
                    }
                } else if (this.f3KeyIsPressed) {
                    this.mFacFunction_Adapter.setLaserSlidingCoverMode(0);
                }
            } else if (isGAValueNull) {
                Log.i("Fac_GapAdjustActivity", "GetGAValue is null, no response to KEYCODE_HISIR_F3");
            } else {
                this.f3KeyIsPressed = true;
                sendMessageDelayed(0, 1000);
            }
        } else if (isGAValueNull) {
            Log.i("Fac_GapAdjustActivity", "GetGAValue is null, no response to KEYCODE_DPAD_RIGHT");
        } else if (this.mBar.requestFocus()) {
            int laserMovableCoverStep = this.mFacFunction_Adapter.getLaserMovableCoverStep();
            this.valueForBar = laserMovableCoverStep;
            int i2 = laserMovableCoverStep + 1;
            this.valueForBar = i2;
            if (i2 <= 30) {
                this.mValue.setText(this.valueForBar + "/30");
                this.mBar.setProgress(this.valueForBar);
                Log.i("Fac_GapAdjustActivity", " LEFT getGapAdjustValue is : " + this.valueForBar);
                this.mFacFunction_Adapter.setLaserMovableCoverStep(this.valueForBar);
            }
        }
        return true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Log.i("Fac_GapAdjustActivity", "in GapAdjustActivity onKeyUp keycode = " + i + "keyEvent = " + keyEvent.toString());
        return super.onKeyUp(i, keyEvent);
    }

    private void sendMessageDelayed(int i, int i2) {
        Message message = new Message();
        message.what = i;
        this.mhHandler.removeMessages(i);
        this.mhHandler.sendMessageDelayed(message, i2);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
    }
}
