package okhttp3.g0.k;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Method f209a;
    private final Method b;
    private final Method c;

    d(Method method, Method method2, Method method3) {
        this.f209a = method;
        this.b = method2;
        this.c = method3;
    }

    static d b() throws NoSuchMethodException {
        Method method;
        Method method2;
        Method method3 = null;
        try {
            Class<?> cls = Class.forName("dalvik.system.CloseGuard");
            Method method4 = cls.getMethod("get", new Class[0]);
            method2 = cls.getMethod("open", String.class);
            method = cls.getMethod("warnIfOpen", new Class[0]);
            method3 = method4;
        } catch (Exception unused) {
            method = null;
            method2 = null;
        }
        return new d(method3, method2, method);
    }

    Object a(String str) {
        Method method = this.f209a;
        if (method != null) {
            try {
                Object objInvoke = method.invoke(null, new Object[0]);
                this.b.invoke(objInvoke, str);
                return objInvoke;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    boolean c(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            this.c.invoke(obj, new Object[0]);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
