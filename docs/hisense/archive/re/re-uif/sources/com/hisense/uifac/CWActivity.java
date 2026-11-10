package com.hisense.uifac;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.hisense.uifac.menu.util.MenuConfigManager;

/* JADX INFO: loaded from: classes.dex */
public class CWActivity extends Activity implements View.OnFocusChangeListener {
    private static boolean isCWValuesNull = false;
    private static MenuConfigManager mMenuConfigManager;
    ProgressBar bar1;
    ProgressBar bar2;
    int cwValue1 = 0;
    int cwValue2 = 0;
    LinearLayout layout1;
    LinearLayout layout2;
    TextView value1;
    TextView value2;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("Fac_CWActivity", "******Enter CW activity*****");
        setContentView(R.layout.color_wheel);
        getWindow().addFlags(128);
        mMenuConfigManager = MenuConfigManager.getInstance(this);
        this.layout1 = (LinearLayout) findViewById(R.id.layout_bar1);
        this.layout2 = (LinearLayout) findViewById(R.id.layout_bar2);
        this.bar1 = (ProgressBar) findViewById(R.id.progressbar1);
        this.bar2 = (ProgressBar) findViewById(R.id.progressbar2);
        this.value1 = (TextView) findViewById(R.id.value1);
        this.value2 = (TextView) findViewById(R.id.value2);
        initUI();
        this.bar1.setFocusable(true);
        this.layout1.setBackgroundResource(R.drawable.menu_tab_button_select_bg);
        this.bar1.setOnFocusChangeListener(this);
        this.bar2.setOnFocusChangeListener(this);
    }

    private void initUI() {
        if (getColorWheelValues()) {
            updateValues();
            return;
        }
        isCWValuesNull = true;
        this.value1.setText("NG");
        this.value2.setText("NG");
        this.bar1.setProgress(0);
        this.bar2.setProgress(0);
    }

    private boolean getColorWheelValues() {
        this.cwValue1 = Integer.parseInt(mMenuConfigManager.getCWValue1() + "", 10);
        int i = Integer.parseInt(mMenuConfigManager.getCWValue2() + "", 10);
        this.cwValue2 = i;
        if (-1 == this.cwValue1 || -1 == i) {
            return false;
        }
        Log.i("Fac_CWActivity", "getColorWheelValues first one is : " + this.cwValue1 + " the second one is : " + this.cwValue2);
        return true;
    }

    private void updateValues() {
        this.value1.setText(String.valueOf(this.cwValue1));
        this.value2.setText(String.valueOf(this.cwValue2));
        this.bar1.setProgress(this.cwValue1);
        this.bar2.setProgress(this.cwValue2);
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

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        int i2;
        int i3;
        Log.i("Fac_CWActivity", "in CWActivity onKeyDown keycode = " + i + " keyEvent = " + keyEvent.toString());
        if (i == 4 || i == 4209) {
            if (mMenuConfigManager.getDefault("HIFAC_STATUS") == 1) {
                finish();
            }
        } else {
            switch (i) {
                case 19:
                    if (this.bar1.requestFocus()) {
                        this.bar1.setFocusable(false);
                        this.bar2.setFocusable(true);
                    } else {
                        this.bar1.setFocusable(true);
                        this.bar2.setFocusable(false);
                    }
                    break;
                case 20:
                    if (this.bar1.requestFocus()) {
                        this.bar1.setFocusable(false);
                        this.bar2.setFocusable(true);
                    } else {
                        this.bar1.setFocusable(true);
                        this.bar2.setFocusable(false);
                    }
                    break;
                case 21:
                    if (isCWValuesNull) {
                        Log.i("Fac_CWActivity", "Left to getCWValues is null");
                    } else if (this.bar1.requestFocus()) {
                        int i4 = Integer.parseInt(mMenuConfigManager.getCWValue1() + "", 10) - 1;
                        i2 = i4 > -1 ? i4 : 720;
                        i3 = i2 < 721 ? i2 : 0;
                        this.value1.setText(String.valueOf(i3));
                        this.bar1.setProgress(i3);
                        Log.i("Fac_CWActivity", " LEFT getColorWheelValues first one is : " + i3);
                        mMenuConfigManager.setCWValue1(i3);
                    } else {
                        int i5 = Integer.parseInt(mMenuConfigManager.getCWValue2() + "", 10) - 1;
                        i2 = i5 > -1 ? i5 : 720;
                        i3 = i2 < 721 ? i2 : 0;
                        this.value2.setText(String.valueOf(i3));
                        this.bar2.setProgress(i3);
                        Log.i("Fac_CWActivity", " LEFT getColorWheelValues the second one is : " + i3);
                        mMenuConfigManager.setCWValue2(i3);
                    }
                    break;
                case 22:
                    if (isCWValuesNull) {
                        Log.i("Fac_CWActivity", " Right to getCWValues is null");
                    } else if (this.bar1.requestFocus()) {
                        int i6 = Integer.parseInt(mMenuConfigManager.getCWValue1() + "", 10) + 1;
                        i2 = i6 > -1 ? i6 : 720;
                        i3 = i2 < 721 ? i2 : 0;
                        this.value1.setText(String.valueOf(i3));
                        this.bar1.setProgress(i3);
                        Log.i("Fac_CWActivity", " LEFT getColorWheelValues first one is : " + i3);
                        mMenuConfigManager.setCWValue1(i3);
                    } else {
                        int i7 = Integer.parseInt(mMenuConfigManager.getCWValue2() + "", 10) + 1;
                        i2 = i7 > -1 ? i7 : 720;
                        i3 = i2 < 721 ? i2 : 0;
                        this.value2.setText(String.valueOf(i3));
                        this.bar2.setProgress(i3);
                        Log.i("Fac_CWActivity", " LEFT getColorWheelValues the second one is : " + i3);
                        mMenuConfigManager.setCWValue2(i3);
                    }
                    break;
            }
        }
        return true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Log.i("Fac_CWActivity", "in CWActivity onKeyUp keycode = " + i + "keyEvent = " + keyEvent.toString());
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        Log.d("Fac_CWActivity", "in CWActivity =====onFocusChange===id=====" + view.getId());
        switch (view.getId()) {
            case R.id.progressbar1 /* 2131296848 */:
                this.layout1.setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                this.layout2.setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                break;
            case R.id.progressbar2 /* 2131296849 */:
                this.layout2.setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                this.layout1.setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                break;
        }
    }
}
