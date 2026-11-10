package com.hisense.tv.ota.ui;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
class v implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ w f66a;

    v(w wVar) {
        this.f66a = wVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!TextUtils.isEmpty(this.f66a.k)) {
            this.f66a.f.setVisibility(0);
            this.f66a.g.setVisibility(0);
            this.f66a.g.setChecked(true);
        }
        this.f66a.c.requestFocus();
    }
}
