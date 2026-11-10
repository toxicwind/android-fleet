package com.ju.lib.datacommunication.network.http.debug;

/* JADX INFO: loaded from: classes.dex */
class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ i f107a;

    e(f fVar, i iVar) {
        this.f107a = iVar;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        f.g().d(DebugCheckType.RespBody, this.f107a);
        f.g().d(DebugCheckType.RespCode, this.f107a);
    }
}
