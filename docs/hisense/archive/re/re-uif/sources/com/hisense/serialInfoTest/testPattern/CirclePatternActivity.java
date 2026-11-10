package com.hisense.serialInfoTest.testPattern;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import com.hisense.serialInfoTest.util.Logger;
import com.hisense.uifac.R;

/* JADX INFO: loaded from: classes.dex */
public class CirclePatternActivity extends Activity implements PatternPanelController.Listerner {
    private RelativeLayout mMenuMainRelativeLayout;
    private PatternPanelController mPatternPanelController;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Logger.d("enter circle onCreate");
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        getWindow().addFlags(128);
        setContentView(R.layout.circle_test_pattern);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.circletestpattern_relativeLayout);
        this.mMenuMainRelativeLayout = relativeLayout;
        relativeLayout.setBackgroundResource(R.drawable.test_pattern);
        PatternPanelController patternPanelController = PatternPanelController.getInstance(this);
        this.mPatternPanelController = patternPanelController;
        patternPanelController.register(this);
    }

    @Override // android.app.Activity
    protected void onPause() {
        Logger.d("enter circle onPause");
        super.onPause();
        finish();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        Logger.d("enter circle onRestart");
        super.onRestart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        Logger.d("enter circle onStop");
        super.onStop();
    }

    @Override // com.hisense.serialInfoTest.testPattern.PatternPanelController.Listerner
    public void onHidePanel() {
        Logger.d("enter circle onHidePanel");
        finish();
    }
}
