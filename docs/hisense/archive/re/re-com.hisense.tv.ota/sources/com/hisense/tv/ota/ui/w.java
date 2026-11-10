package com.hisense.tv.ota.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.hisense.tv.ota.R;

/* JADX INFO: loaded from: classes.dex */
public class w extends AbstractDialogC0022b implements View.OnClickListener {
    private Button c;
    private Button d;
    private Button e;
    private View f;
    private CheckBox g;
    private TextView h;
    private TextView i;
    private TextView j;
    private String k;
    private String l;
    private String m;
    private String n;
    private boolean o;

    public w(Context context) {
        super(context);
    }

    private void j() {
        Button button = (Button) findViewById(R.id.id_version_dialog_btn_ok);
        this.c = button;
        if (button != null) {
            button.setFocusable(true);
            this.c.setOnClickListener(this);
        }
        Button button2 = (Button) findViewById(R.id.id_version_dialog_btn_cancel);
        this.d = button2;
        if (button2 != null) {
            button2.setOnClickListener(this);
        }
        Button button3 = (Button) findViewById(R.id.id_version_dialog_btn_not_remind);
        this.e = button3;
        if (button3 != null) {
            button3.setOnClickListener(this);
        }
        View viewFindViewById = findViewById(R.id.id_version_dialog_btn_review);
        this.f = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(this);
        }
        CheckBox checkBox = (CheckBox) findViewById(R.id.id_version_dialog_check_box);
        this.g = checkBox;
        checkBox.setOnCheckedChangeListener(new u(this));
        this.h = (TextView) findViewById(R.id.id_version_dialog_info_tv);
        this.i = (TextView) findViewById(R.id.id_version_cur_version);
        this.j = (TextView) findViewById(R.id.id_version_new_version);
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b
    protected void d() {
        String strC = c(R.string.checking_upgrade);
        String strC2 = c(R.string.new_version_detected);
        String strC3 = c(R.string.agree_disclaimer);
        String strC4 = c(R.string.review_all_disclaimer);
        String strC5 = c(R.string.btn_text_upgrade);
        String strC6 = c(R.string.btn_text_next_time);
        String strC7 = c(R.string.btn_text_never_remind);
        ((TextView) findViewById(R.id.id_version_dialog_tv_title)).setText(strC);
        ((TextView) findViewById(R.id.id_version_dialog_tv_title)).setTextColor(-1);
        ((TextView) findViewById(R.id.id_version_dialog_tv_tip)).setText(strC2);
        ((TextView) findViewById(R.id.id_version_dialog_tv_tip)).setTextColor(-1);
        ((TextView) findViewById(R.id.id_version_dialog_check_box)).setText(strC3);
        ((TextView) findViewById(R.id.id_version_dialog_check_box)).setTextColor(-1);
        ((TextView) findViewById(R.id.id_version_dialog_btn_review)).setText(strC4);
        ((TextView) findViewById(R.id.id_version_dialog_btn_review)).setTextColor(-1);
        ((Button) findViewById(R.id.id_version_dialog_btn_ok)).setText(strC5);
        ((Button) findViewById(R.id.id_version_dialog_btn_ok)).setTextColor(-1);
        ((Button) findViewById(R.id.id_version_dialog_btn_cancel)).setText(strC6);
        ((Button) findViewById(R.id.id_version_dialog_btn_cancel)).setTextColor(-1);
        ((Button) findViewById(R.id.id_version_dialog_btn_not_remind)).setText(strC7);
        ((Button) findViewById(R.id.id_version_dialog_btn_not_remind)).setTextColor(-1);
    }

    public void k(String str) {
        this.m = str;
    }

    public void l(String str) {
        Log.i("NewVersionDialog", "setEulaPath " + str);
        this.k = str;
    }

    public void m(boolean z) {
        this.o = z;
    }

    public void n(String str) {
        this.n = str;
    }

    public void o(String str) {
        this.l = str;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Dialog dialog;
        if (view.getId() == R.id.id_version_dialog_btn_review) {
            if (SystemProperties.get("ro.vt.market").equals("EM") && SystemProperties.get("ro.product.board").equals("MT9980")) {
                i iVar = new i(getContext());
                iVar.g(this.k);
                dialog = iVar;
            } else {
                Log.i("NewVersionDialog", " will show  EulaZip dialog");
                j jVar = new j(getContext());
                jVar.h(this.k);
                dialog = jVar;
            }
            dialog.show();
            return;
        }
        if (this.f55a != null) {
            switch (view.getId()) {
                case R.id.id_version_dialog_btn_cancel /* 2131034137 */:
                    this.f55a.e();
                    break;
                case R.id.id_version_dialog_btn_not_remind /* 2131034138 */:
                    this.f55a.d();
                    break;
                case R.id.id_version_dialog_btn_ok /* 2131034139 */:
                    this.f55a.b();
                    return;
                default:
                    return;
            }
            dismiss();
        }
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_newversion_dialog);
        j();
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, android.app.Dialog
    protected void onStart() {
        super.onStart();
        if (TextUtils.isEmpty(this.k)) {
            this.f.setVisibility(8);
            this.g.setVisibility(8);
            this.c.setEnabled(true);
        } else {
            this.f.setVisibility(0);
            this.g.setVisibility(0);
        }
        Context context = getContext();
        TextView textView = this.h;
        if (textView != null && this.l != null) {
            textView.setText(context.getString(R.string.update_content) + "\n" + this.l);
            this.h.setTextColor(-1);
        }
        TextView textView2 = this.i;
        if (textView2 != null && this.m != null) {
            textView2.setText(context.getString(R.string.current_version) + " " + this.m);
            this.i.setTextColor(-1);
        }
        TextView textView3 = this.j;
        if (textView3 != null && this.n != null) {
            textView3.setText(context.getString(R.string.lastest_version) + " " + this.n);
            this.j.setTextColor(-1);
        }
        if (this.o) {
            this.d.setVisibility(8);
            this.e.setVisibility(8);
        } else {
            this.d.setVisibility(0);
            this.e.setVisibility(0);
        }
        this.c.post(new v(this));
    }
}
