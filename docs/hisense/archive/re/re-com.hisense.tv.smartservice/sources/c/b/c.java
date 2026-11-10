package c.b;

import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f1949a;

    static {
        Throwable th;
        Class<?> cls;
        Level level = Level.WARNING;
        a aVar = null;
        try {
            cls = Class.forName("io.perfmark.impl.SecretPerfMarkImpl$PerfMarkImpl");
            th = null;
        } catch (ClassNotFoundException e2) {
            th = e2;
            level = Level.FINE;
            cls = null;
        } catch (Throwable th2) {
            th = th2;
            cls = null;
        }
        if (cls != null) {
            try {
                aVar = (a) cls.asSubclass(a.class).getConstructor(d.class).newInstance(a.f1947a);
            } catch (Throwable th3) {
                th = th3;
            }
        }
        if (aVar != null) {
            f1949a = aVar;
        } else {
            f1949a = new a(a.f1947a);
        }
        if (th != null) {
            Logger.getLogger(c.class.getName()).log(level, "Error during PerfMark.<clinit>", th);
        }
    }

    public static b a() {
        return f1949a.a();
    }
}
