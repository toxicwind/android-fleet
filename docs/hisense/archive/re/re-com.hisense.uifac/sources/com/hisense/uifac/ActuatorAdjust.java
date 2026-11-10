package com.hisense.uifac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ProgressBar;
import com.hisense.uifac.menu.MenuMain;
import com.hisense.uifac.menu.util.MenuConfigManager;

/* JADX INFO: loaded from: classes.dex */
public class ActuatorAdjust extends Activity {
    private static MenuConfigManager mMenuConfigManager;
    ProgressBar bar;
    int value = 0;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("Fac_ActuatorAdjust", "******Enter ActuatorAdjust activity*****");
        setContentView(R.layout.actuator_adjust);
        getWindow().addFlags(128);
        mMenuConfigManager = MenuConfigManager.getInstance(this);
        this.bar = (ProgressBar) findViewById(R.id.mprogressbar);
        initUI();
    }

    private void initUI() {
        if (isValueValid()) {
            updateValue();
        } else {
            this.bar.setProgress(0);
        }
    }

    private boolean isValueValid() {
        int i = Integer.parseInt(MenuConfigManager.getMirrorValue() + "", 10);
        this.value = i;
        if (i < 0 || i > 19) {
            Log.i("Fac_ActuatorAdjust", " Attention please !!! The ActuatorAdjust value is illegal");
            return false;
        }
        Log.i("Fac_ActuatorAdjust", "************getValue the result is : " + this.value);
        return true;
    }

    private void updateValue() {
        this.bar.setProgress(this.value);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Log.i("Fac_ActuatorAdjust", "in ActuatorAdjust onKeyDown keycode = " + i + " keyEvent = " + keyEvent.toString());
        if (i != 4) {
            if (i == 19) {
                int i2 = Integer.parseInt(MenuConfigManager.getMirrorValue() + "", 10);
                this.value = i2;
                int i3 = i2 + 1;
                this.value = i3;
                if (i3 > 19) {
                    Log.d("Fac_ActuatorAdjust", "The value is 19 !! it is top value!!");
                } else {
                    mMenuConfigManager.setMirrorValue(i3);
                    initUI();
                }
            } else if (i == 20) {
                int i4 = Integer.parseInt(MenuConfigManager.getMirrorValue() + "", 10);
                this.value = i4;
                int i5 = i4 - 1;
                this.value = i5;
                if (i5 < 0) {
                    Log.d("Fac_ActuatorAdjust", "The value is 0 already!! it is bottom value!!");
                } else {
                    mMenuConfigManager.setMirrorValue(i5);
                    initUI();
                }
            }
        } else if (mMenuConfigManager.getDefault("HIFAC_STATUS") == 1) {
            Intent intent = new Intent();
            intent.setClass(this, MenuMain.class);
            setResult(0, intent);
            mMenuConfigManager.setOpenMirror(false);
            finish();
        }
        return true;
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
