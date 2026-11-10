package c.a.c.a;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class<?> f1641a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1642b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Class[] f1643c;

    public f(Class<?> cls, String str, Class... clsArr) {
        this.f1641a = cls;
        this.f1642b = str;
        this.f1643c = clsArr;
    }

    public boolean a(T t) {
        return a(t.getClass()) != null;
    }

    public Object b(T t, Object... objArr) {
        try {
            Method methodA = a(t.getClass());
            if (methodA == null) {
                return null;
            }
            try {
                return methodA.invoke(t, objArr);
            } catch (IllegalAccessException unused) {
                return null;
            }
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    public Object c(T t, Object... objArr) {
        try {
            return a(t, objArr);
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    public Object a(T t, Object... objArr) {
        Method methodA = a(t.getClass());
        if (methodA != null) {
            try {
                return methodA.invoke(t, objArr);
            } catch (IllegalAccessException e2) {
                AssertionError assertionError = new AssertionError(b.a.a.a.a.a("Unexpectedly could not call: ", methodA));
                assertionError.initCause(e2);
                throw assertionError;
            }
        }
        StringBuilder sbA = b.a.a.a.a.a("Method ");
        sbA.append(this.f1642b);
        sbA.append(" not supported for object ");
        sbA.append(t);
        throw new AssertionError(sbA.toString());
    }

    public final Method a(Class<?> cls) {
        Class<?> cls2;
        String str = this.f1642b;
        if (str == null) {
            return null;
        }
        Method methodA = a(cls, str, this.f1643c);
        if (methodA == null || (cls2 = this.f1641a) == null || cls2.isAssignableFrom(methodA.getReturnType())) {
            return methodA;
        }
        return null;
    }

    public static Method a(Class<?> cls, String str, Class[] clsArr) {
        if (cls == null) {
            return null;
        }
        try {
            if ((cls.getModifiers() & 1) == 0) {
                return a(cls.getSuperclass(), str, clsArr);
            }
            Method method = cls.getMethod(str, clsArr);
            try {
                if ((method.getModifiers() & 1) != 0) {
                    return method;
                }
                return null;
            } catch (NoSuchMethodException unused) {
                return method;
            }
        } catch (NoSuchMethodException unused2) {
        }
    }
}
