package com.hisense.tv.ota.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.hisense.tv.ota.R;

/* JADX INFO: loaded from: classes.dex */
public class F extends AbstractDialogC0022b implements View.OnClickListener {
    private Button c;
    private Handler d;

    public F(Context context) {
        super(context);
        HandlerThread handlerThread = new HandlerThread("DownloadDialog");
        handlerThread.start();
        this.d = new Handler(handlerThread.getLooper());
    }

    private void f() {
        Button button = (Button) findViewById(R.id.id_checking_dialog_btn_cancel);
        this.c = button;
        if (button != null) {
            button.setOnClickListener(this);
        }
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b
    protected void d() {
        String strC = c(R.string.checking_upgrade);
        String strC2 = c(R.string.cancel);
        ((TextView) findViewById(R.id.id_version_check_title)).setText(strC);
        ((TextView) findViewById(R.id.id_version_check_title)).setTextColor(-1);
        ((Button) findViewById(R.id.id_checking_dialog_btn_cancel)).setText(strC2);
        ((Button) findViewById(R.id.id_checking_dialog_btn_cancel)).setTextColor(-1);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        dismiss();
        if (this.f55a == null || view.getId() != R.id.id_checking_dialog_btn_cancel) {
            return;
        }
        this.d.post(new E(this));
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_version_checking_dialog);
        f();
    }
}
