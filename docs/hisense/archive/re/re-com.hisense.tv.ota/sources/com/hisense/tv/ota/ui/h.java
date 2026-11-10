package com.hisense.tv.ota.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.hisense.tv.ota.R;

/* JADX INFO: loaded from: classes.dex */
public class h extends AbstractDialogC0022b implements View.OnClickListener {
    private static SparseIntArray i;
    private Button c;
    private Button d;
    private TextView e;
    private Handler f;
    private int g;
    private boolean h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        i = sparseIntArray;
        sparseIntArray.put(-1, R.string.space_not_enough);
        i.put(-2, R.string.no_new_version);
        i.put(-3, R.string.network_error);
        i.put(-4, R.string.downlodd_error);
        i.put(-5, R.string.some_error_occurs);
        i.put(-6, R.string.some_error_occurs);
        i.put(-9, R.string.some_error_occurs);
        i.put(-7, R.string.some_error_occurs);
        i.put(-8, R.string.some_error_occurs);
        i.put(-10, R.string.fte_download_error);
    }

    public h(Context context) {
        super(context);
        this.g = -5;
        HandlerThread handlerThread = new HandlerThread("ErrorDialog");
        handlerThread.start();
        this.f = new Handler(handlerThread.getLooper());
    }

    private void f() {
        Button button = (Button) findViewById(R.id.id_error_dialog_btn_ok);
        this.c = button;
        if (button != null) {
            button.setOnClickListener(this);
        }
        Button button2 = (Button) findViewById(R.id.id_error_dialog_btn_retry);
        this.d = button2;
        if (button2 != null) {
            button2.setOnClickListener(this);
        }
        this.e = (TextView) findViewById(R.id.id_error_tv);
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b
    protected void d() {
        String strC = c(R.string.sys_upgrade);
        String strC2 = c(R.string.some_error_occurs);
        String strC3 = c(R.string.ok);
        String strC4 = c(R.string.btn_text_retry);
        ((TextView) findViewById(R.id.id_error_tv_title)).setText(strC);
        ((TextView) findViewById(R.id.id_error_tv_title)).setTextColor(-1);
        ((TextView) findViewById(R.id.id_error_tv)).setText(strC2);
        ((TextView) findViewById(R.id.id_error_tv)).setTextColor(-1);
        ((Button) findViewById(R.id.id_error_dialog_btn_ok)).setText(strC3);
        ((Button) findViewById(R.id.id_error_dialog_btn_ok)).setTextColor(-1);
        ((Button) findViewById(R.id.id_error_dialog_btn_retry)).setText(strC4);
        ((Button) findViewById(R.id.id_error_dialog_btn_retry)).setTextColor(-1);
    }

    public void g(int i2) {
        this.g = i2;
    }

    public void h(boolean z) {
        Log.i("ErrorDialog", "setFteMode " + z);
        this.h = z;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Handler handler;
        Runnable runnableC0026f;
        dismiss();
        if (this.f55a != null) {
            switch (view.getId()) {
                case R.id.id_error_dialog_btn_ok /* 2131034117 */:
                    handler = this.f;
                    runnableC0026f = new RunnableC0026f(this);
                    break;
                case R.id.id_error_dialog_btn_retry /* 2131034118 */:
                    handler = this.f;
                    runnableC0026f = new g(this);
                    break;
                default:
                    return;
            }
            handler.post(runnableC0026f);
        }
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_error_dialog);
        f();
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, android.app.Dialog
    protected void onStart() {
        super.onStart();
        String string = getContext().getString(i.get(this.g, R.string.some_error_occurs));
        TextView textView = this.e;
        if (textView != null) {
            textView.setText(string);
        }
        if (this.g == -4) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
        if (this.h) {
            this.d.setVisibility(8);
        }
    }
}
