package com.ju.lib.datacommunication.network.http.debug;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ File f105a;
    final /* synthetic */ f b;

    c(f fVar, File file) {
        this.b = fVar;
        this.f105a = file;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.l(this.f105a);
    }
}
