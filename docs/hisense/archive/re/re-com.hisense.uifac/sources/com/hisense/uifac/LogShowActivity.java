package com.hisense.uifac;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.menu.util.MenuConfigManager;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;

/* JADX INFO: loaded from: classes.dex */
public class LogShowActivity extends Activity {
    private String mMicTestLog;
    TextView mTextView;
    private String mZDMicTestLog;
    private MenuConfigManager mcf;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.factory_log_show_activity);
        getWindow().addFlags(128);
        this.mTextView = (TextView) findViewById(R.id.factory_log_show);
        MenuConfigManager menuConfigManager = MenuConfigManager.getInstance(this);
        this.mcf = menuConfigManager;
        this.mTextView.setText(menuConfigManager.readLogFromTvcertificate());
        this.mTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        int facLogStatus = this.mcf.getFacLogStatus();
        updateMacView(facLogStatus);
        updateHDCP14View(facLogStatus);
        updateHDCP22View(facLogStatus);
        updateMGKView(facLogStatus);
        updateWVView(facLogStatus);
        updateDMPView(facLogStatus);
        updateTVView(facLogStatus);
        updateAVView(facLogStatus);
        updateHDMI1View(facLogStatus);
        updateHDMI2View(facLogStatus);
        updateHDMI3View(facLogStatus);
        updateHDMI4View(facLogStatus);
        updateWIFIView(facLogStatus);
        updateBTView(facLogStatus);
        updateVCOMView(facLogStatus);
        updateGAMMAView(facLogStatus);
        updateWBView(facLogStatus);
        updateQDAView(facLogStatus);
        if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
            this.mZDMicTestLog = getIntent().getStringExtra("mZDMicTestLog");
            this.mMicTestLog = getIntent().getStringExtra("mMicTestLog");
            updateMicTestView(facLogStatus);
            updateZDMicTestView(facLogStatus);
        }
    }

    private void updateMacView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_mac);
        String string = textView.getText().toString();
        if ((i & 1) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateHDCP14View(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_hdcp14);
        String string = textView.getText().toString();
        if ((i & 2) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateHDCP22View(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_hdcp22);
        String string = textView.getText().toString();
        if ((i & 4) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateMGKView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_mgk);
        String string = textView.getText().toString();
        if ((i & 8) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateWVView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_widevine);
        String string = textView.getText().toString();
        if ((i & 16) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateDMPView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_dmp);
        String string = textView.getText().toString();
        if ((i & 32) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateTVView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_tv);
        String string = textView.getText().toString();
        if ((i & 64) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateAVView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_av);
        String string = textView.getText().toString();
        if ((i & 128) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateHDMI1View(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_hdmi1);
        String string = textView.getText().toString();
        if ((i & 256) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateHDMI2View(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_hdmi2);
        String string = textView.getText().toString();
        if ((i & 512) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateHDMI3View(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_hdmi3);
        String string = textView.getText().toString();
        if ((i & 1024) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateHDMI4View(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_hdmi4);
        String string = textView.getText().toString();
        if ((i & 2048) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateWIFIView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_wifi);
        String string = textView.getText().toString();
        if ((i & 4096) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateBTView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_bt);
        String string = textView.getText().toString();
        if ((i & 8192) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateVCOMView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_vcom);
        String string = textView.getText().toString();
        if ((i & 16384) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateGAMMAView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_gamma);
        String string = textView.getText().toString();
        if ((i & 32768) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateWBView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_wb);
        String string = textView.getText().toString();
        if ((i & MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateQDAView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_qda);
        String string = textView.getText().toString();
        if ((i & 2097152) > 0) {
            str = string + "OK";
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateMicTestView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_mictest);
        String string = textView.getText().toString();
        if ((i & 4194304) > 0) {
            str = string + " " + this.mMicTestLog;
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }

    private void updateZDMicTestView(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.factory_logo_zdmictest);
        String string = textView.getText().toString();
        if ((i & 8388608) > 0) {
            str = string + " " + this.mZDMicTestLog;
        } else {
            str = string + "NG";
        }
        textView.setText(str);
    }
}
