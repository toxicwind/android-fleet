package com.vt.tv.usbota.F;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.vt.tv.usbota.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends Dialog implements n {
    protected m a;
    protected Handler b;

    public b(Context context) {
        super(context, R.style.AppDialog);
        this.b = new Handler();
    }

    @Override // com.vt.tv.usbota.F.n
    public void a(int i) {
    }

    @Override // com.vt.tv.usbota.F.n
    public void b() {
        this.b.post(new a(this));
    }

    protected String c(int i) {
        return getContext().getResources().getString(i);
    }

    protected abstract void d();

    public void e(m mVar) {
        this.a = mVar;
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
