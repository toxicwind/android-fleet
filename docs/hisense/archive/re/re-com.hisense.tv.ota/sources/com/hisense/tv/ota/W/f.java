package com.hisense.tv.ota.W;

import android.os.RecoverySystem;

/* JADX INFO: loaded from: classes.dex */
class f implements RecoverySystem.ProgressListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ j f27a;

    f(j jVar) {
        this.f27a = jVar;
    }

    @Override // android.os.RecoverySystem.ProgressListener
    public void onProgress(int i) {
        this.f27a.onProgress(i);
        if (i >= 100) {
            this.f27a.b();
        }
    }
}
