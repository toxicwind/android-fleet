package com.hisense.tv.ota;

/* JADX INFO: renamed from: com.hisense.tv.ota.r, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0017r implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ SysUpgradeNotifyService f47a;

    RunnableC0017r(SysUpgradeNotifyService sysUpgradeNotifyService) {
        this.f47a = sysUpgradeNotifyService;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f47a.a();
    }
}
