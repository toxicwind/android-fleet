package com.hisense.tv.ota.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.hisense.tv.ota.R;

/* JADX INFO: renamed from: com.hisense.tv.ota.ui.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class ViewOnClickListenerC0025e extends AbstractDialogC0022b implements View.OnClickListener {
    private Button c;
    private ProgressBar d;
    private Handler e;
    private TextView f;

    public ViewOnClickListenerC0025e(Context context) {
        super(context);
        HandlerThread handlerThread = new HandlerThread("DownloadDialog");
        handlerThread.start();
        this.e = new Handler(handlerThread.getLooper());
    }

    private void h() {
        Button button = (Button) findViewById(R.id.id_download_dialog_btn_background);
        this.c = button;
        if (button != null) {
            button.setOnClickListener(this);
        }
        this.d = (ProgressBar) findViewById(R.id.id_download_progress_bar);
        TextView textView = (TextView) findViewById(R.id.id_download_progress_tv);
        this.f = textView;
        textView.setTextColor(-1);
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, com.hisense.tv.ota.ui.A
    public void a(int i) {
        if (!isShowing() || this.d == null) {
            return;
        }
        this.b.post(new RunnableC0023c(this, i));
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b
    protected void d() {
        String strC = c(R.string.title_downloading);
        String strC2 = c(R.string.background);
        ((TextView) findViewById(R.id.id_download_tv_1)).setText(strC);
        ((TextView) findViewById(R.id.id_download_tv_1)).setTextColor(-1);
        ((Button) findViewById(R.id.id_download_dialog_btn_background)).setText(strC2);
        ((Button) findViewById(R.id.id_download_dialog_btn_background)).setTextColor(-1);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        dismiss();
        if (this.f55a == null || view.getId() != R.id.id_download_dialog_btn_background) {
            return;
        }
        this.e.post(new RunnableC0024d(this));
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_download_dialog);
        h();
    }
}
