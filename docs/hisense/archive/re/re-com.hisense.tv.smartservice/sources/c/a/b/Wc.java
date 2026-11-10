package c.a.b;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class Wc implements Tb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1180a = Logger.getLogger(Wc.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Constructor<?> f1181b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Method f1182c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final RuntimeException f1183d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object[] f1184e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f1185f;

    static {
        Method method;
        Constructor<?> constructor;
        try {
            Class<?> cls = Class.forName("java.util.concurrent.atomic.LongAdder");
            method = cls.getMethod("add", Long.TYPE);
            try {
                cls.getMethod("sum", new Class[0]);
                Constructor<?>[] constructors = cls.getConstructors();
                int length = constructors.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        constructor = null;
                        break;
                    }
                    constructor = constructors[i];
                    if (constructor.getParameterTypes().length == 0) {
                        break;
                    } else {
                        i++;
                    }
                }
                th = null;
            } catch (Throwable th) {
                th = th;
                f1180a.log(Level.FINE, "LongAdder can not be found via reflection, this is normal for JDK7 and below", th);
                constructor = null;
            }
        } catch (Throwable th2) {
            th = th2;
            method = null;
        }
        if (th != null || constructor == null) {
            f1181b = null;
            f1182c = null;
            f1183d = new RuntimeException(th);
        } else {
            f1181b = constructor;
            f1182c = method;
            f1183d = null;
        }
        f1184e = new Object[]{1L};
    }

    public Wc() {
        RuntimeException runtimeException = f1183d;
        if (runtimeException != null) {
            throw runtimeException;
        }
        try {
            this.f1185f = f1181b.newInstance(new Object[0]);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4);
        }
    }

    @Override // c.a.b.Tb
    public void add(long j) {
        try {
            f1182c.invoke(this.f1185f, j == 1 ? f1184e : new Object[]{Long.valueOf(j)});
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }
}
