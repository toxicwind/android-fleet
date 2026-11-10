package com.hisense.serialInfoTest.testPattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import com.hisense.serialInfoTest.model.KeySwitcher;
import com.hisense.serialInfoTest.util.Logger;
import com.hisense.uifac.R;

/* JADX INFO: loaded from: classes.dex */
public class WhitePatternActivity extends Activity implements PatternPanelController.Listerner {
    private RelativeLayout mMenuMainRelativeLayout;
    private PatternPanelController mPatternPanelController;

    public WhitePatternActivity() {
        KeySwitcher.getInstance();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Logger.d("enter white onCreate");
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.serial_test_pattern);
        getWindow().addFlags(128);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.testpattern_relativeLayout);
        this.mMenuMainRelativeLayout = relativeLayout;
        relativeLayout.setBackgroundResource(R.drawable.color_white);
        PatternPanelController patternPanelController = PatternPanelController.getInstance(this);
        this.mPatternPanelController = patternPanelController;
        patternPanelController.register(this);
    }

    @Override // android.app.Activity
    protected void onPause() {
        Logger.d("enter WhitePatternActivity onPause");
        super.onPause();
        finish();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        Logger.d("enter WhitePatternActivity onNewIntent");
        super.onNewIntent(intent);
    }

    @Override // android.app.Activity
    protected void onStart() {
        Logger.d("whitePanel onStart");
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        Logger.d("whitePanel onRestart");
        super.onRestart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        Logger.d("whitePanel onStop");
        this.mPatternPanelController.unregister(this);
        super.onStop();
    }

    @Override // com.hisense.serialInfoTest.testPattern.PatternPanelController.Listerner
    public void onHidePanel() {
        Logger.d("white onHidePanel");
        finish();
    }
}
