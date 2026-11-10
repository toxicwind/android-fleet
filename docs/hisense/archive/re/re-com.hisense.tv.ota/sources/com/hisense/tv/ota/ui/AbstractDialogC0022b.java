package com.hisense.tv.ota.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.hisense.tv.ota.R;

/* JADX INFO: renamed from: com.hisense.tv.ota.ui.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractDialogC0022b extends Dialog implements A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected z f55a;
    protected Handler b;

    public AbstractDialogC0022b(Context context) {
        super(context, R.style.AppDialog);
        this.b = new Handler();
    }

    public void a(int i) {
    }

    @Override // com.hisense.tv.ota.ui.A
    public void b() {
        this.b.post(new RunnableC0021a(this));
    }

    protected String c(int i) {
        return getContext().getResources().getString(i);
    }

    protected abstract void d();

    public void e(z zVar) {
        this.f55a = zVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getAttributes().type = 2003;
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        requestWindowFeature(1);
    }

    @Override // android.app.Dialog
    protected void onStart() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        d();
        super.onStart();
    }
}
