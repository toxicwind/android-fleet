package com.hisense.tv.ota.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.hisense.tv.ota.R;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class i extends AbstractDialogC0022b {
    private String c;
    private CustomWebView d;

    public i(Context context) {
        super(context);
    }

    private void f() {
        CustomWebView customWebView = (CustomWebView) findViewById(R.id.id_web_view);
        this.d = customWebView;
        customWebView.setBackgroundColor(getContext().getResources().getColor(R.color.web_bg));
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b
    protected void d() {
        String strC = c(R.string.checking_upgrade);
        String strC2 = c(R.string.disclaimer);
        ((TextView) findViewById(R.id.id_eula_tv_title)).setText(strC);
        ((TextView) findViewById(R.id.id_eula_tv_title)).setTextColor(-1);
        ((TextView) findViewById(R.id.id_eula_tv_dis)).setText(strC2);
        ((TextView) findViewById(R.id.id_eula_tv_dis)).setTextColor(-1);
    }

    public void g(String str) {
        this.c = str;
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CustomWebView.b();
        setContentView(R.layout.layout_eula_dialog);
        f();
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, android.app.Dialog
    protected void onStart() {
        super.onStart();
        setCancelable(true);
        if (TextUtils.isEmpty(this.c)) {
            return;
        }
        this.d.loadUrl(Uri.fromFile(new File(this.c)).toString());
    }
}
