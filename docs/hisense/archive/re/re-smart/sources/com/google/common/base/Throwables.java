package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Throwables {

    @GwtIncompatible
    public static final String JAVA_LANG_ACCESS_CLASSNAME = "sun.misc.JavaLangAccess";

    @VisibleForTesting
    @GwtIncompatible
    public static final String SHARED_SECRETS_CLASSNAME = "sun.misc.SharedSecrets";

    @GwtIncompatible
    public static final Method getStackTraceDepthMethod;

    @GwtIncompatible
    public static final Method getStackTraceElementMethod;

    @GwtIncompatible
    public static final Object jla = getJLA();

    /* JADX INFO: renamed from: com.google.common.base.Throwables$1, reason: invalid class name */
    public class AnonymousClass1 extends AbstractList<StackTraceElement> {
        public final /* synthetic */ Throwable val$t;

        public AnonymousClass1(Throwable th) {
            this.val$t = th;
        }

        @Override // java.util.AbstractList, java.util.List
        public StackTraceElement get(int i) {
            return (StackTraceElement) Throwables.invokeAccessibleNonThrowingMethod(Throwables.getStackTraceElementMethod, Throwables.jla, this.val$t, Integer.valueOf(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return ((Integer) Throwables.invokeAccessibleNonThrowingMethod(Throwables.getStackTraceDepthMethod, Throwables.jla, this.val$t)).intValue();
        }
    }

    static {
        getStackTraceElementMethod = jla == null ? null : getGetMethod();
        getStackTraceDepthMethod = jla != null ? getSizeMethod() : null;
    }

    @Beta
    public static List<Throwable> getCausalChain(Throwable th) {
        if (th == null) {
            throw new NullPointerException();
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(th);
        boolean z = false;
        Throwable cause = th;
        while (true) {
            th = th.getCause();
            if (th == null) {
                return Collections.unmodifiableList(arrayList);
            }
            arrayList.add(th);
            if (th == cause) {
                throw new IllegalArgumentException("Loop in causal chain detected.", th);
            }
            if (z) {
                cause = cause.getCause();
            }
            z = !z;
        }
    }

    @Beta
    @GwtIncompatible
    public static <X extends Throwable> X getCauseAs(Throwable th, Class<X> cls) {
        try {
            return cls.cast(th.getCause());
        } catch (ClassCastException e2) {
            e2.initCause(th);
            throw e2;
        }
    }

    @GwtIncompatible
    public static Method getGetMethod() {
        return getJlaMethod("getStackTraceElement", Throwable.class, Integer.TYPE);
    }

    @GwtIncompatible
    public static Object getJLA() {
        try {
            return Class.forName(SHARED_SECRETS_CLASSNAME, false, null).getMethod("getJavaLangAccess", new Class[0]).invoke(null, new Object[0]);
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable unused) {
            return null;
        }
    }

    @GwtIncompatible
    public static Method getJlaMethod(String str, Class<?>... clsArr) {
        try {
            return Class.forName(JAVA_LANG_ACCESS_CLASSNAME, false, null).getMethod(str, clsArr);
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Throwable getRootCause(Throwable th) {
        boolean z = false;
        Throwable cause = th;
        while (true) {
            Throwable cause2 = th.getCause();
            if (cause2 == null) {
                return th;
            }
            if (cause2 == cause) {
                throw new IllegalArgumentException("Loop in causal chain detected.", cause2);
            }
            if (z) {
                cause = cause.getCause();
            }
            z = !z;
            th = cause2;
        }
    }

    @GwtIncompatible
    public static Method getSizeMethod() {
        try {
            Method jlaMethod = getJlaMethod("getStackTraceDepth", Throwable.class);
            if (jlaMethod == null) {
                return null;
            }
            jlaMethod.invoke(getJLA(), new Throwable());
            return jlaMethod;
        } catch (IllegalAccessException | UnsupportedOperationException | InvocationTargetException unused) {
            return null;
        }
    }

    @GwtIncompatible
    public static String getStackTraceAsString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    @GwtIncompatible
    public static Object invokeAccessibleNonThrowingMethod(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            propagate(e3.getCause());
            throw null;
        }
    }

    @GwtIncompatible
    public static List<StackTraceElement> jlaStackTrace(Throwable th) {
        if (th != null) {
            return new AnonymousClass1(th);
        }
        throw new NullPointerException();
    }

    @Beta
    @GwtIncompatible
    public static List<StackTraceElement> lazyStackTrace(Throwable th) {
        if (!lazyStackTraceIsLazy()) {
            return Collections.unmodifiableList(Arrays.asList(th.getStackTrace()));
        }
        if (th != null) {
            return new AnonymousClass1(th);
        }
        throw new NullPointerException();
    }

    @Beta
    @GwtIncompatible
    public static boolean lazyStackTraceIsLazy() {
        return (getStackTraceElementMethod == null || getStackTraceDepthMethod == null) ? false : true;
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    @Deprecated
    public static RuntimeException propagate(Throwable th) {
        throwIfUnchecked(th);
        throw new RuntimeException(th);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @GwtIncompatible
    @Deprecated
    public static <X extends Throwable> void propagateIfInstanceOf(Throwable th, Class<X> cls) throws X {
        if (th != null) {
            throwIfInstanceOf(th, cls);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @GwtIncompatible
    public static <X extends Throwable> void propagateIfPossible(Throwable th, Class<X> cls) throws X {
        if (th != null) {
            throwIfInstanceOf(th, cls);
        }
        if (th != null) {
            throwIfUnchecked(th);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    @GwtIncompatible
    public static <X extends Throwable> void throwIfInstanceOf(Throwable th, Class<X> cls) throws X {
        if (th == null) {
            throw new NullPointerException();
        }
        if (cls.isInstance(th)) {
            throw cls.cast(th);
        }
    }

    public static void throwIfUnchecked(Throwable th) {
        if (th == null) {
            throw new NullPointerException();
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @GwtIncompatible
    public static <X1 extends Throwable, X2 extends Throwable> void propagateIfPossible(Throwable th, Class<X1> cls, Class<X2> cls2) throws X {
        if (cls2 != null) {
            if (th != null) {
                throwIfInstanceOf(th, cls);
            }
            propagateIfPossible(th, cls2);
            return;
        }
        throw new NullPointerException();
    }

    @GwtIncompatible
    @Deprecated
    public static void propagateIfPossible(Throwable th) {
        if (th != null) {
            throwIfUnchecked(th);
        }
    }
}
