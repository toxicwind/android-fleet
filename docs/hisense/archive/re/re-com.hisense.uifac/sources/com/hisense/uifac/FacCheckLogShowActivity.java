package com.hisense.uifac;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.uifac.menu.util.MenuConfigManager;

/* JADX INFO: loaded from: classes.dex */
public class FacCheckLogShowActivity extends Activity {
    String TAG = "Fac_FacCheckLogShowActivity";
    private TextView mAVTestView;
    private TextView mBTListTestView;
    private TextView mDMPTestView;
    private FacFunction_Adapter mFacFunction_Adapter;
    private TextView mHDMI1View;
    private TextView mHDMI2View;
    private TextView mHDMI3View;
    private TextView mHDMI4View;
    private TextView mLocalKeypadTestView;
    private TextView mMacKeysTestView;
    private TextView mMainBordTestView;
    private TextView mSoftwareVersionTestView;
    private TextView mTVTestView;
    private TextView mWifiListTestView;
    private MenuConfigManager mcf;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fac_check_log_show);
        getWindow().addFlags(128);
        this.mcf = MenuConfigManager.getInstance(this);
        this.mFacFunction_Adapter = FacFunction_Adapter.getInstance(this);
        this.mMainBordTestView = (TextView) findViewById(R.id.factory_main_board_test);
        this.mSoftwareVersionTestView = (TextView) findViewById(R.id.factory_software_version);
        this.mMacKeysTestView = (TextView) findViewById(R.id.factory_mac_keys);
        this.mTVTestView = (TextView) findViewById(R.id.factory_tv);
        this.mAVTestView = (TextView) findViewById(R.id.factory_av);
        this.mDMPTestView = (TextView) findViewById(R.id.factory_dmp);
        this.mWifiListTestView = (TextView) findViewById(R.id.factory_wifi);
        this.mBTListTestView = (TextView) findViewById(R.id.factory_bt);
        this.mLocalKeypadTestView = (TextView) findViewById(R.id.factory_local_keypad);
        this.mHDMI1View = (TextView) findViewById(R.id.factory_hdmi1);
        this.mHDMI2View = (TextView) findViewById(R.id.factory_hdmi2);
        this.mHDMI3View = (TextView) findViewById(R.id.factory_hdmi3);
        this.mHDMI4View = (TextView) findViewById(R.id.factory_hdmi4);
        int facCheckLogStatus = this.mcf.getFacCheckLogStatus();
        updateMainBoardTestView(facCheckLogStatus);
        updateSoftWareVersionView(facCheckLogStatus);
        updateMacKeysView(facCheckLogStatus);
        updateTVView(facCheckLogStatus);
        updateAVView(facCheckLogStatus);
        updateDmpView(facCheckLogStatus);
        updateWifiView(facCheckLogStatus);
        updateBTView(facCheckLogStatus);
        updateLocalKeypadView(facCheckLogStatus);
        updateHDMI1View(facCheckLogStatus);
        updateHDMI2View(facCheckLogStatus);
        updateHDMI3View(facCheckLogStatus);
        updateHDMI4View(facCheckLogStatus);
        updateUI();
    }

    private void updateUI() {
        int hDMINumber = this.mFacFunction_Adapter.getHDMINumber();
        Log.d(this.TAG, "getHDMINumber" + hDMINumber);
        if (hDMINumber == 1) {
            this.mHDMI2View.setVisibility(8);
            this.mHDMI3View.setVisibility(8);
            this.mHDMI4View.setVisibility(8);
        } else if (hDMINumber == 2) {
            this.mHDMI3View.setVisibility(8);
            this.mHDMI4View.setVisibility(8);
        } else {
            if (hDMINumber != 3) {
                return;
            }
            this.mHDMI4View.setVisibility(8);
        }
    }

    private void updateMainBoardTestView(int i) {
        String str;
        String string = this.mMainBordTestView.getText().toString();
        if ((i & 1) > 0) {
            str = string + "OK";
            this.mMainBordTestView.setTextColor(-16711936);
        } else {
            str = string + "NG";
            this.mMainBordTestView.setTextColor(-65536);
        }
        this.mMainBordTestView.setText(str);
    }

    private void updateSoftWareVersionView(int i) {
        String str;
        String string = this.mSoftwareVersionTestView.getText().toString();
        if ((i & 2) > 0) {
            str = string + "OK";
            this.mSoftwareVersionTestView.setTextColor(-16711936);
        } else {
            str = string + "NG";
            this.mSoftwareVersionTestView.setTextColor(-65536);
        }
        this.mSoftwareVersionTestView.setText(str);
    }

    private void updateMacKeysView(int i) {
        String str;
        String string = this.mMacKeysTestView.getText().toString();
        if ((i & 4) > 0) {
            str = string + "OK";
            this.mMacKeysTestView.setTextColor(-16711936);
        } else {
            str = string + "NG";
            this.mMacKeysTestView.setTextColor(-65536);
        }
        this.mMacKeysTestView.setText(str);
    }

    private void updateTVView(int i) {
        String str;
        String string = this.mTVTestView.getText().toString();
        if ((i & 8) > 0) {
            str = string + "OK";
            this.mTVTestView.setTextColor(-16711936);
        } else {
            str = string + "NG";
            this.mTVTestView.setTextColor(-65536);
        }
        this.mTVTestView.setText(str);
    }

    private void updateAVView(int i) {
        String str;
        String string = this.mAVTestView.getText().toString();
        if ((i & 16) > 0) {
            str = string + "OK";
            this.mAVTestView.setTextColor(-16711936);
        } else {
            str = string + "NG";
            this.mAVTestView.setTextColor(-65536);
        }
        this.mAVTestView.setText(str);
    }

    private void updateDmpView(int i) {
        String str;
        String string = this.mDMPTestView.getText().toString();
        if ((i & 32) > 0) {
            str = string + "OK";
            this.mDMPTestView.setTextColor(-16711936);
        } else {
            str = string + "NG";
            this.mDMPTestView.setTextColor(-65536);
        }
        this.mDMPTestView.setText(str);
    }

    private void updateWifiView(int i) {
        String str;
        String string = this.mWifiListTestView.getText().toString();
        if ((i & 64) > 0) {
            str = string + "OK";
            this.mWifiListTestView.setTextColor(-16711936);
        } else {
            str = string + "NG";
            this.mWifiListTestView.setTextColor(-65536);
        }
        this.mWifiListTestView.setText(str);
    }

    private void updateBTView(int i) {
        String str;
        String string = this.mBTListTestView.getText().toString();
        if ((i & 128) > 0) {
            str = string + "OK";
            this.mBTListTestView.setTextColor(-16711936);
        } else {
            str = string + "NG";
            this.mBTListTestView.setTextColor(-65536);
        }
        this.mBTListTestView.setText(str);
    }

    private void updateLocalKeypadView(int i) {
        String str;
        String string = this.mLocalKeypadTestView.getText().toString();
        if ((i & 256) > 0) {
            str = string + "OK";
            this.mLocalKeypadTestView.setTextColor(-16711936);
        } else {
            str = string + "NG";
            this.mLocalKeypadTestView.setTextColor(-65536);
        }
        this.mLocalKeypadTestView.setText(str);
    }

    private void updateHDMI1View(int i) {
        String str;
        String string = this.mHDMI1View.getText().toString();
        if ((i & 512) > 0) {
            str = string + "OK";
            this.mHDMI1View.setTextColor(-16711936);
        } else {
            str = string + "NG";
            this.mHDMI1View.setTextColor(-65536);
        }
        this.mHDMI1View.setText(str);
    }

    private void updateHDMI2View(int i) {
        String str;
        String string = this.mHDMI2View.getText().toString();
        if ((i & 1024) > 0) {
            str = string + "OK";
            this.mHDMI2View.setTextColor(-16711936);
        } else {
            str = string + "NG";
            this.mHDMI2View.setTextColor(-65536);
        }
        this.mHDMI2View.setText(str);
    }

    private void updateHDMI3View(int i) {
        String str;
        String string = this.mHDMI3View.getText().toString();
        if ((i & 2048) > 0) {
            str = string + "OK";
            this.mHDMI3View.setTextColor(-16711936);
        } else {
            str = string + "NG";
            this.mHDMI3View.setTextColor(-65536);
        }
        this.mHDMI3View.setText(str);
    }

    private void updateHDMI4View(int i) {
        String str;
        String string = this.mHDMI4View.getText().toString();
        if ((i & 4096) > 0) {
            str = string + "OK";
            this.mHDMI4View.setTextColor(-16711936);
        } else {
            str = string + "NG";
            this.mHDMI4View.setTextColor(-65536);
        }
        this.mHDMI4View.setText(str);
    }
}
