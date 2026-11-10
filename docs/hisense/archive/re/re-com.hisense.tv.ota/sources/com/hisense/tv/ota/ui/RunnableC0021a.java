package com.hisense.tv.ota.ui;

/* JADX INFO: renamed from: com.hisense.tv.ota.ui.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0021a implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AbstractDialogC0022b f54a;

    RunnableC0021a(AbstractDialogC0022b abstractDialogC0022b) {
        this.f54a = abstractDialogC0022b;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f54a.isShowing()) {
            this.f54a.dismiss();
        }
    }
}
