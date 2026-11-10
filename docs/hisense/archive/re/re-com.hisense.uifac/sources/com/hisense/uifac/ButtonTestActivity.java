package com.hisense.uifac;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import com.hisense.tvcm.FacPropertyDemo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ButtonTestActivity extends Activity {
    private Button button_channel_down;
    private Button button_channel_up;
    private Button button_mixbar;
    private Button button_volume_down;
    private Button button_volume_up;
    private Handler mHandler;
    public int mKeyInit;
    private Context mContext = null;
    private String TAG = "ButtonTest";
    private Map<Integer, Button> map = new HashMap();

    public ButtonTestActivity() {
        new HashMap();
        this.mKeyInit = 4393;
        this.mHandler = new Handler() { // from class: com.hisense.uifac.ButtonTestActivity.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 0) {
                    return;
                }
                ButtonTestActivity.this.openFacRootMenu();
            }
        };
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Log.i(this.TAG, "dialogone onCreate");
        setContentView(R.layout.buttontest_dialog);
        getWindow().addFlags(128);
        this.mContext = this;
        this.button_volume_up = (Button) findViewById(R.id.button_volume_up);
        this.button_volume_down = (Button) findViewById(R.id.button_volume_down);
        this.button_channel_up = (Button) findViewById(R.id.button_channel_up);
        this.button_channel_down = (Button) findViewById(R.id.button_channel_down);
        this.button_mixbar = (Button) findViewById(R.id.button_mixbar);
        Log.i(this.TAG, "onCreate num = " + FacPropertyDemo.HIFAC_KEYBOARD_NUM);
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.button_volume_up.setBackgroundResource(R.color.white_oled);
            this.button_volume_down.setBackgroundResource(R.color.white_oled);
            this.button_channel_up.setBackgroundResource(R.color.white_oled);
            this.button_channel_down.setBackgroundResource(R.color.white_oled);
            this.button_mixbar.setBackgroundResource(R.color.white_oled);
        }
        int i = FacPropertyDemo.HIFAC_KEYBOARD_NUM;
        if (i == 5) {
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                this.button_channel_down.setText("MUTE");
                this.button_channel_up.setText("SWITCH");
            }
            this.button_mixbar.setText("POWER");
            this.map.put(4392, this.button_volume_up);
            this.map.put(4393, this.button_volume_down);
            this.map.put(4394, this.button_channel_up);
            this.map.put(4395, this.button_channel_down);
            this.map.put(4396, this.button_mixbar);
        } else if (i == 1) {
            this.map.put(330, this.button_mixbar);
            this.button_volume_up.setVisibility(8);
            this.button_volume_down.setVisibility(8);
            this.button_channel_up.setVisibility(8);
            this.button_channel_down.setVisibility(8);
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0), 2000L);
            this.mKeyInit = 330;
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.i(this.TAG, "bundleOne not null");
            if (FacPropertyDemo.HIFAC_KEYBOARD_NUM != 1) {
                this.mKeyInit = extras.getInt("KeyInitValue");
            }
        }
        Log.i(this.TAG, "onCreate mKeyInit = " + this.mKeyInit);
        Map<Integer, Button> map = this.map;
        if (map != null && map.get(Integer.valueOf(this.mKeyInit)) != null) {
            this.map.get(Integer.valueOf(this.mKeyInit)).setBackgroundColor(this.mContext.getResources().getColor(R.drawable.yellow));
        }
        SystemProperties.set("sys.vt.factory.enter_button_test", "1");
        super.onCreate(bundle);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Log.i(this.TAG, "dialogOne OnkeyDown" + i);
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Log.i(this.TAG, "ButtonTest OnkeyUp" + i);
        if (FacPropertyDemo.HIFAC_KEYBOARD_NUM == 1 && (i != 4396 || i != 330)) {
            return super.onKeyUp(i, keyEvent);
        }
        if (i != 4 && i != 4211) {
            switch (i) {
                case 4392:
                case 4393:
                case 4394:
                case 4395:
                case 4396:
                    this.map.get(Integer.valueOf(i)).setBackgroundColor(this.mContext.getResources().getColor(R.drawable.yellow));
                    Iterator<Map.Entry<Integer, Button>> it = this.map.entrySet().iterator();
                    int i2 = 0;
                    while (it.hasNext()) {
                        Button value = it.next().getValue();
                        Log.i(this.TAG, "button is " + ((Object) value.getText()));
                        if (Integer.toHexString(((ColorDrawable) value.getBackground()).getColor()).equals("77ffff00")) {
                            Log.i(this.TAG, "button is yellow for while");
                            i2++;
                            Log.i(this.TAG, "button keynum = " + i2);
                            if (i2 < 5) {
                                Log.i(this.TAG, "continue");
                            } else {
                                Log.i(this.TAG, "dismiss");
                                startActivity(new Intent(this, (Class<?>) FacRootMenu.class));
                            }
                        } else {
                            Log.i(this.TAG, "button is white,break");
                            break;
                        }
                    }
                    break;
            }
        } else {
            Log.i(this.TAG, "keycode back pressed");
            openFacRootMenu();
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        Log.i(this.TAG, "dialogone  onDestroy");
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        Log.i(this.TAG, "dialogone  onPause");
        SystemProperties.set("sys.vt.factory.enter_button_test", "0");
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        Log.i(this.TAG, "dialogone  onResume");
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onStart() {
        Log.i(this.TAG, "dialogone  onStart");
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        Log.i(this.TAG, "dialogone  onStop");
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openFacRootMenu() {
        startActivity(new Intent(this, (Class<?>) FacRootMenu.class));
    }
}
