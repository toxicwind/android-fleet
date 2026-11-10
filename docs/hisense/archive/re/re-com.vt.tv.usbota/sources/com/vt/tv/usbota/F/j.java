package com.vt.tv.usbota.F;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.vt.tv.usbota.R;

/* JADX INFO: loaded from: classes.dex */
public class j extends b implements View.OnClickListener {
    private Button c;
    private Button d;
    private TextView e;
    private TextView f;
    private TextView g;
    private String h;
    private String i;
    private String j;
    private boolean k;

    public j(Context context) {
        super(context);
    }

    private void g() {
        Button button = (Button) findViewById(R.id.id_version_dialog_btn_ok);
        this.c = button;
        if (button != null) {
            button.setFocusable(true);
            this.c.setFocusableInTouchMode(true);
            this.c.setOnClickListener(this);
        }
        Button button2 = (Button) findViewById(R.id.id_version_dialog_btn_cancel);
        this.d = button2;
        if (button2 != null) {
            button2.setOnClickListener(this);
        }
        this.e = (TextView) findViewById(R.id.id_version_dialog_info_tv);
        this.f = (TextView) findViewById(R.id.id_version_cur_version);
        this.g = (TextView) findViewById(R.id.id_version_new_version);
    }

    @Override // com.vt.tv.usbota.F.b
    protected void d() {
        String strC = c(R.string.checking_upgrade);
        String strC2 = c(R.string.new_version_detected);
        String strC3 = c(R.string.btn_text_upgrade);
        String strC4 = c(R.string.cancel);
        ((TextView) findViewById(R.id.id_version_dialog_tv_title)).setText(strC);
        ((TextView) findViewById(R.id.id_version_dialog_tv_title)).setTextColor(-1);
        ((TextView) findViewById(R.id.id_version_dialog_tv_tip)).setText(strC2);
        ((TextView) findViewById(R.id.id_version_dialog_tv_tip)).setTextColor(-1);
        ((Button) findViewById(R.id.id_version_dialog_btn_ok)).setText(strC3);
        ((Button) findViewById(R.id.id_version_dialog_btn_ok)).setTextColor(-1);
        ((Button) findViewById(R.id.id_version_dialog_btn_cancel)).setText(strC4);
        ((Button) findViewById(R.id.id_version_dialog_btn_cancel)).setTextColor(-1);
    }

    public void h(String str) {
        this.i = str;
    }

    public void i(boolean z) {
        this.k = z;
    }

    public void j(String str) {
        this.j = str;
    }

    public void k(String str) {
        this.h = str;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        dismiss();
        if (this.a != null) {
            switch (view.getId()) {
                case R.id.id_version_dialog_btn_cancel /* 2131034131 */:
                    this.a.c();
                    break;
                case R.id.id_version_dialog_btn_ok /* 2131034132 */:
                    this.a.d();
                    break;
            }
        }
    }

    @Override // com.vt.tv.usbota.F.b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_newversion_dialog);
        g();
    }

    @Override // com.vt.tv.usbota.F.b, android.app.Dialog
    protected void onStart() {
        Button button;
        int i;
        super.onStart();
        this.c.setEnabled(true);
        Context context = getContext();
        TextView textView = this.e;
        if (textView != null && this.h != null) {
            textView.setText(context.getString(R.string.update_content) + this.h);
            this.e.setTextColor(-1);
        }
        TextView textView2 = this.f;
        if (textView2 != null && this.i != null) {
            textView2.setText(context.getString(R.string.current_version) + this.i);
            this.f.setTextColor(-1);
        }
        TextView textView3 = this.g;
        if (textView3 != null && this.j != null) {
            textView3.setText(context.getString(R.string.lastest_version) + this.j);
            this.g.setTextColor(-1);
        }
        if (this.k) {
            button = this.d;
            i = 8;
        } else {
            button = this.d;
            i = 0;
        }
        button.setVisibility(i);
        this.c.post(new i(this));
    }
}
