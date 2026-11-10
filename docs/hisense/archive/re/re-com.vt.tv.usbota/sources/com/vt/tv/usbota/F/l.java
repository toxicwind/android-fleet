package com.vt.tv.usbota.F;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.vt.tv.usbota.R;

/* JADX INFO: loaded from: classes.dex */
public class l extends b implements View.OnClickListener {
    private Button c;
    private Handler d;

    public l(Context context) {
        super(context);
        HandlerThread handlerThread = new HandlerThread("RebootDialog");
        handlerThread.start();
        this.d = new Handler(handlerThread.getLooper());
    }

    private void f() {
        Button button = (Button) findViewById(R.id.id_reboot_dialog_btn_ok);
        this.c = button;
        if (button != null) {
            button.setOnClickListener(this);
        }
    }

    @Override // com.vt.tv.usbota.F.b
    protected void d() {
        String strC = c(R.string.install_package);
        String strC2 = c(R.string.reboot);
        String strC3 = c(R.string.ok);
        ((TextView) findViewById(R.id.id_reboot_tv_title)).setText(strC);
        ((TextView) findViewById(R.id.id_reboot_tv_title)).setTextColor(-1);
        ((TextView) findViewById(R.id.id_reboot_tv_tip)).setText(strC2);
        ((TextView) findViewById(R.id.id_reboot_tv_tip)).setTextColor(-1);
        ((Button) findViewById(R.id.id_reboot_dialog_btn_ok)).setText(strC3);
        ((Button) findViewById(R.id.id_reboot_dialog_btn_ok)).setTextColor(-1);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        dismiss();
        if (this.a == null || view.getId() != R.id.id_reboot_dialog_btn_ok) {
            return;
        }
        this.d.post(new k(this));
    }

    @Override // com.vt.tv.usbota.F.b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_reboot_dialog);
        f();
    }
}
