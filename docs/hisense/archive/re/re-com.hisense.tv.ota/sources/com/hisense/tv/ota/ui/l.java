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
public class l extends AbstractDialogC0022b implements View.OnClickListener {
    private ProgressBar c;
    private TextView d;

    public l(Context context) {
        super(context);
        HandlerThread handlerThread = new HandlerThread("FteDownloadDialog");
        handlerThread.start();
        new Handler(handlerThread.getLooper());
    }

    private void h() {
        this.c = (ProgressBar) findViewById(R.id.id_download_progress_bar);
        TextView textView = (TextView) findViewById(R.id.id_download_progress_tv);
        this.d = textView;
        textView.setTextColor(-1);
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, com.hisense.tv.ota.ui.A
    public void a(int i) {
        if (!isShowing() || this.c == null) {
            return;
        }
        this.b.post(new k(this, i));
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b
    protected void d() {
        ((TextView) findViewById(R.id.id_download_tv_1)).setText(c(R.string.title_downloading));
        ((TextView) findViewById(R.id.id_download_tv_1)).setTextColor(-1);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_ftedownload_dialog);
        h();
    }
}
