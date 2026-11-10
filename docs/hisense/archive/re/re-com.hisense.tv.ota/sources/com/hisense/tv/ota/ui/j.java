package com.hisense.tv.ota.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import com.hisense.tv.ota.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes.dex */
public class j extends AbstractDialogC0022b {
    private String c;
    private TextView d;

    public j(Context context) {
        super(context);
    }

    private void f() {
        TextView textView = (TextView) findViewById(R.id.id_eula_view);
        this.d = textView;
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    private static String g(String str, Context context) throws Throwable {
        String line;
        StringBuilder sb = new StringBuilder();
        ?? r2 = 0;
        r2 = 0;
        r2 = 0;
        try {
            try {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str))));
                    boolean z = true;
                    while (true) {
                        try {
                            line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            if (z) {
                                z = false;
                            } else {
                                sb.append('\n');
                            }
                            sb.append(line);
                        } catch (IOException e) {
                            e = e;
                            r2 = bufferedReader;
                            e.printStackTrace();
                            Log.e("EulaZipDialog", "Error open file fialed");
                            if (r2 != 0) {
                                r2.close();
                                r2 = r2;
                            }
                            return sb.toString();
                        } catch (Throwable th) {
                            th = th;
                            r2 = bufferedReader;
                            if (r2 != 0) {
                                try {
                                    r2.close();
                                } catch (IOException unused) {
                                    Log.e("EulaZipDialog", "Error closing assert ");
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader.close();
                    r2 = line;
                } catch (IOException unused2) {
                    Log.e("EulaZipDialog", "Error closing assert ");
                }
            } catch (IOException e2) {
                e = e2;
            }
            return sb.toString();
        } catch (Throwable th2) {
            th = th2;
        }
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

    public void h(String str) {
        this.c = str;
        Log.i("EulaZipDialog", "setEulaPath " + this.c);
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_eulazip_dialog);
        f();
    }

    @Override // com.hisense.tv.ota.ui.AbstractDialogC0022b, android.app.Dialog
    protected void onStart() throws Throwable {
        super.onStart();
        Context context = getContext();
        setCancelable(true);
        if (TextUtils.isEmpty(this.c)) {
            return;
        }
        this.d.setText(g(this.c, context));
        this.d.setTextColor(-1);
    }
}
