package com.vt.tv.usbota.F;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.vt.tv.usbota.R;

/* JADX INFO: loaded from: classes.dex */
public class h extends b implements View.OnClickListener {
    private Button c;
    private ProgressBar d;
    private Handler e;
    private TextView f;

    public h(Context context) {
        super(context);
        HandlerThread handlerThread = new HandlerThread("InstallDialog");
        handlerThread.start();
        this.e = new Handler(handlerThread.getLooper());
    }

    private void h() {
        Button button = (Button) findViewById(R.id.id_install_dialog_btn_background);
        this.c = button;
        if (button != null) {
            button.setOnClickListener(this);
        }
        this.d = (ProgressBar) findViewById(R.id.id_install_progress_bar);
        TextView textView = (TextView) findViewById(R.id.id_install_progress_tv);
        this.f = textView;
        if (textView != null) {
            textView.setTextColor(-1);
            this.f.setText("0%");
        }
        ProgressBar progressBar = this.d;
        if (progressBar != null) {
            progressBar.setProgress(0);
        }
    }

    @Override // com.vt.tv.usbota.F.b, com.vt.tv.usbota.F.n
    public void a(int i) {
        if (!isShowing() || this.d == null) {
            return;
        }
        this.b.post(new f(this, i));
    }

    @Override // com.vt.tv.usbota.F.b
    protected void d() {
        String strC = c(R.string.title_installing);
        String strC2 = c(R.string.background);
        ((TextView) findViewById(R.id.id_install_tv_1)).setText(strC);
        ((TextView) findViewById(R.id.id_install_tv_1)).setTextColor(-1);
        ((Button) findViewById(R.id.id_install_dialog_btn_background)).setText(strC2);
        ((Button) findViewById(R.id.id_install_dialog_btn_background)).setTextColor(-1);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        dismiss();
        if (this.a == null || view.getId() != R.id.id_install_dialog_btn_background) {
            return;
        }
        this.e.post(new g(this));
    }

    @Override // com.vt.tv.usbota.F.b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_install_dialog);
        getWindow().addFlags(128);
        h();
    }
}
