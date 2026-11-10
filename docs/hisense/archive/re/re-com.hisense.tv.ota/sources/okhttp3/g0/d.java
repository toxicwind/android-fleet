package okhttp3.g0;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
class d implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f184a;
    final /* synthetic */ boolean b;

    d(String str, boolean z) {
        this.f184a = str;
        this.b = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.f184a);
        thread.setDaemon(this.b);
        return thread;
    }
}
