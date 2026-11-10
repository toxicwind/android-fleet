package b.f.b.b.a.b;

import android.os.Process;

/* JADX INFO: loaded from: classes2.dex */
public class b extends Thread {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a f678a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c.a aVar, Runnable runnable, String str) {
        super(runnable, str);
        this.f678a = aVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(9);
        try {
            super.run();
        } catch (Throwable th) {
            this.f678a.f684a.a(th);
        }
    }
}
