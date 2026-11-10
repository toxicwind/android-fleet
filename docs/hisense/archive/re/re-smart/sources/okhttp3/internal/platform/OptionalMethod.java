package okhttp3.internal.platform;

import b.a.a.a.a;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class OptionalMethod<T> {
    public final String methodName;
    public final Class[] methodParams;
    public final Class<?> returnType;

    public OptionalMethod(Class<?> cls, String str, Class... clsArr) {
        this.returnType = cls;
        this.methodName = str;
        this.methodParams = clsArr;
    }

    private Method getMethod(Class<?> cls) {
        Class<?> cls2;
        String str = this.methodName;
        if (str == null) {
            return null;
        }
        Method publicMethod = getPublicMethod(cls, str, this.methodParams);
        if (publicMethod == null || (cls2 = this.returnType) == null || cls2.isAssignableFrom(publicMethod.getReturnType())) {
            return publicMethod;
        }
        return null;
    }

    public static Method getPublicMethod(Class<?> cls, String str, Class[] clsArr) {
        try {
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

    public Object invoke(T t, Object... objArr) {
        Method method = getMethod(t.getClass());
        if (method != null) {
            try {
                return method.invoke(t, objArr);
            } catch (IllegalAccessException e2) {
                AssertionError assertionError = new AssertionError(a.a("Unexpectedly could not call: ", method));
                assertionError.initCause(e2);
                throw assertionError;
            }
        }
        StringBuilder sbA = a.a("Method ");
        sbA.append(this.methodName);
        sbA.append(" not supported for object ");
        sbA.append(t);
        throw new AssertionError(sbA.toString());
    }

    public Object invokeOptional(T t, Object... objArr) {
        Method method = getMethod(t.getClass());
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(t, objArr);
        } catch (IllegalAccessException unused) {
            return null;
        }
    }

    public Object invokeOptionalWithoutCheckedException(T t, Object... objArr) {
        try {
            return invokeOptional(t, objArr);
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

    public Object invokeWithoutCheckedException(T t, Object... objArr) {
        try {
            return invoke(t, objArr);
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

    public boolean isSupported(T t) {
        return getMethod(t.getClass()) != null;
    }
}
