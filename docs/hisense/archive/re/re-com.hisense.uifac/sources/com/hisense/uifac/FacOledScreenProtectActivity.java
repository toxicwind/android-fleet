package com.hisense.uifac;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class FacOledScreenProtectActivity extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fac_oled_screen_protect_layout);
        ImageView imageView = (ImageView) findViewById(R.id.screen_portect_image);
        imageView.setBackgroundResource(R.drawable.fac_oled_screen_protect);
        ((AnimationDrawable) imageView.getBackground()).start();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        finish();
        return true;
    }
}
