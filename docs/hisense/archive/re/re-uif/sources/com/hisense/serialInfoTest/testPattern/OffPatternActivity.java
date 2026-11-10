package com.hisense.serialInfoTest.testPattern;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.hisense.serialInfoTest.model.KeySwitcher;
import com.hisense.serialInfoTest.util.Logger;

/* JADX INFO: loaded from: classes.dex */
public class OffPatternActivity extends Activity {
    private PatternPanelController mPatternPanelController;
    private final Handler mhHandler;

    public OffPatternActivity() {
        KeySwitcher.getInstance();
        this.mhHandler = new Handler() { // from class: com.hisense.serialInfoTest.testPattern.OffPatternActivity.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (!Thread.currentThread().isInterrupted()) {
                    Logger.i("***msg=" + message.what);
                    if (message.what == 0) {
                        OffPatternActivity.this.finish();
                    }
                }
                super.handleMessage(message);
            }
        };
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Logger.d("enter off onCreate");
        super.onCreate(bundle);
        this.mPatternPanelController = PatternPanelController.getInstance(this);
        getWindow().addFlags(128);
    }

    @Override // android.app.Activity
    protected void onStart() {
        Logger.d("enter OffPatternActivity onStart");
        this.mPatternPanelController.onHidePanels();
        this.mhHandler.removeMessages(0);
        sendMessageDelayed(0, 2000);
        super.onStart();
    }

    private void sendMessageDelayed(int i, int i2) {
        Message message = new Message();
        message.what = i;
        this.mhHandler.removeMessages(i);
        this.mhHandler.sendMessageDelayed(message, i2);
    }

    @Override // android.app.Activity
    protected void onPause() {
        Logger.d("enter OffPatternActivity onPause");
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        Logger.d("enter OffPatternActivity onRestart");
        super.onRestart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        Logger.d("enter OffPatternActivity onStop");
        super.onStop();
        finish();
    }
}
