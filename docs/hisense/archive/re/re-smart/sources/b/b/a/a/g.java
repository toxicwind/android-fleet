package b.b.a.a;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class g implements b.b.a.g<Throwable> {
    @Override // b.b.a.g
    public String a(Throwable th) {
        return Log.getStackTraceString(th);
    }

    @Override // b.b.a.g
    public Class<Throwable> a() {
        return Throwable.class;
    }
}
