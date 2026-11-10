package com.hisense.tv.ota.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.hisense.tv.ota.R;

/* JADX INFO: loaded from: classes.dex */
public class p extends AbstractDialogC0022b implements View.OnClickListener {
    private ProgressBar c;
    private TextView d;

    public p(Context context) {
        super(context);
        HandlerThread handlerThread = new HandlerThread("FteVerifyDialog");
        handlerThread.start();
        new Handler(handlerThread.getLooper());
    }

    private void h() {
        this.c = (ProgressBar) findViewById(R.id.id_verify_progress_bar);
        TextView textView = (TextView) findViewById(R.id.id_verify_progress_tv);
        this.d = textView;
        if (textView != null) {
            textView.setTextColor(-1);
            this.d.setText("0%");
        }
        ProgressBar progressBar = this.c;
        if (progressBar != null) {
            progressBar.setProgress(0);
        }
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, com.hisense.tv.ota.ui.A
    public void a(int i) {
        if (!isShowing() || this.c == null) {
            return;
        }
        this.b.post(new o(this, i));
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b
    protected void d() {
        ((TextView) findViewById(R.id.id_verify_tv_1)).setText(c(R.string.title_verifying));
        ((TextView) findViewById(R.id.id_verify_tv_1)).setTextColor(-1);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_fteverify_dialog);
        h();
    }
}
