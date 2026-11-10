package b.f.b.b.a.b;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
class e implements c.b {
    @Override // b.f.b.b.a.b.c.b
    public void a(Throwable th) {
        if (th == null || !Log.isLoggable(c.f679a, 6)) {
            return;
        }
        Log.e(c.f679a, "Request threw uncaught throwable", th);
    }
}
