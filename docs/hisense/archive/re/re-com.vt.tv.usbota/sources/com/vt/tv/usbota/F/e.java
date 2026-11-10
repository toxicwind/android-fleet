package com.vt.tv.usbota.F;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.vt.tv.usbota.R;

/* JADX INFO: loaded from: classes.dex */
public class e extends b implements View.OnClickListener {
    private static SparseIntArray h;
    private Button c;
    private Button d;
    private TextView e;
    private Handler f;
    private int g;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        h = sparseIntArray;
        sparseIntArray.put(1, R.string.space_not_enough);
        h.put(2, R.string.no_new_version);
        h.put(3, R.string.no_usb_error);
        h.put(4, R.string.no_package_error);
        h.put(5, R.string.multi_package_error);
        h.put(6, R.string.no_package_error);
        h.put(8, R.string.verify_error);
        h.put(13, R.string.security_patch_level_error);
        h.put(11, R.string.some_error_occurs);
        h.put(9, R.string.some_error_occurs);
        h.put(10, R.string.some_error_occurs);
    }

    public e(Context context) {
        super(context);
        this.g = 7;
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

    @Override // com.vt.tv.usbota.F.b
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

    public void g(int i) {
        this.g = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Handler handler;
        Runnable cVar;
        dismiss();
        if (this.a != null) {
            switch (view.getId()) {
                case R.id.id_error_dialog_btn_ok /* 2131034114 */:
                    handler = this.f;
                    cVar = new c(this);
                    break;
                case R.id.id_error_dialog_btn_retry /* 2131034115 */:
                    handler = this.f;
                    cVar = new d(this);
                    break;
                default:
                    return;
            }
            handler.post(cVar);
        }
    }

    @Override // com.vt.tv.usbota.F.b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_error_dialog);
        f();
    }

    @Override // com.vt.tv.usbota.F.b, android.app.Dialog
    protected void onStart() {
        super.onStart();
        int i = h.get(this.g, R.string.some_error_occurs);
        Log.i("ErrorDialog", "id = " + i);
        String string = getContext().getString(i);
        TextView textView = this.e;
        if (textView != null) {
            textView.setText(string);
        }
        this.d.setVisibility(this.g == 4 ? 0 : 8);
    }
}
