package com.vt.tv.usbota.G;

import android.os.RecoverySystem;

/* JADX INFO: loaded from: classes.dex */
class b implements RecoverySystem.ProgressListener {
    final /* synthetic */ d a;

    b(d dVar) {
        this.a = dVar;
    }

    @Override // android.os.RecoverySystem.ProgressListener
    public void onProgress(int i) {
        this.a.onProgress(i);
        if (i >= 100) {
            this.a.b();
        }
    }
}
