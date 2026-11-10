package com.ju.lib.datacommunication.network.http.debug;

/* JADX INFO: loaded from: classes.dex */
class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ i f106a;
    final /* synthetic */ f b;

    d(f fVar, i iVar) {
        this.b = fVar;
        this.f106a = iVar;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        this.b.d(DebugCheckType.Url, this.f106a);
        this.b.d(DebugCheckType.Param, this.f106a);
    }
}
