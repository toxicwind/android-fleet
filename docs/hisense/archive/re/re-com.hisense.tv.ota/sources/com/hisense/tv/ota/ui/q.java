package com.hisense.tv.ota.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.TextView;
import com.hisense.tv.ota.R;

/* JADX INFO: loaded from: classes.dex */
public class q extends AbstractDialogC0022b implements View.OnClickListener {
    public q(Context context) {
        super(context);
        HandlerThread handlerThread = new HandlerThread("DownloadDialog");
        handlerThread.start();
        new Handler(handlerThread.getLooper());
    }

    private void f() {
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b
    protected void d() {
        String strC = c(R.string.checking_for_upgrade);
        c(R.string.cancel);
        ((TextView) findViewById(R.id.id_version_check_title)).setText(strC);
        ((TextView) findViewById(R.id.id_version_check_title)).setTextColor(-1);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_fteversion_checing_dialog);
        f();
    }
}
