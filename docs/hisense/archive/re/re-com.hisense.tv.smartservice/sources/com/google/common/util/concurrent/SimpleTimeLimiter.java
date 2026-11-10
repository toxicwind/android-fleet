package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ObjectArrays;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class SimpleTimeLimiter implements TimeLimiter {
    public final ExecutorService executor;

    public SimpleTimeLimiter(ExecutorService executorService) {
        if (executorService == null) {
            throw new NullPointerException();
        }
        this.executor = executorService;
    }

    public static /* synthetic */ Exception access$000(Exception exc, boolean z) throws Exception {
        throwCause(exc, z);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T callWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit, boolean z) throws Exception {
        if (callable == null) {
            throw new NullPointerException();
        }
        if (timeUnit == null) {
            throw new NullPointerException();
        }
        checkPositiveTimeout(j);
        Future<T> futureSubmit = this.executor.submit(callable);
        try {
            if (!z) {
                return (T) Uninterruptibles.getUninterruptibly(futureSubmit, j, timeUnit);
            }
            try {
                return futureSubmit.get(j, timeUnit);
            } catch (InterruptedException e2) {
                futureSubmit.cancel(true);
                throw e2;
            }
        } catch (ExecutionException e3) {
            throwCause(e3, true);
            throw null;
        } catch (TimeoutException e4) {
            futureSubmit.cancel(true);
            throw new UncheckedTimeoutException(e4);
        }
    }

    public static void checkPositiveTimeout(long j) {
        Preconditions.checkArgument(j > 0, "timeout must be positive: %s", j);
    }

    public static SimpleTimeLimiter create(ExecutorService executorService) {
        return new SimpleTimeLimiter(executorService);
    }

    public static boolean declaresInterruptedEx(Method method) {
        for (Class<?> cls : method.getExceptionTypes()) {
            if (cls == InterruptedException.class) {
                return true;
            }
        }
        return false;
    }

    public static Set<Method> findInterruptibleMethods(Class<?> cls) {
        HashSet hashSet = new HashSet();
        for (Method method : cls.getMethods()) {
            if (declaresInterruptedEx(method)) {
                hashSet.add(method);
            }
        }
        return hashSet;
    }

    public static Exception throwCause(Exception exc, boolean z) throws Exception {
        Throwable cause = exc.getCause();
        if (cause == null) {
            throw exc;
        }
        if (z) {
            cause.setStackTrace((StackTraceElement[]) ObjectArrays.concat(cause.getStackTrace(), exc.getStackTrace(), StackTraceElement.class));
        }
        if (cause instanceof Exception) {
            throw ((Exception) cause);
        }
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        throw exc;
    }

    private void wrapAndThrowExecutionExceptionOrError(Throwable th) throws ExecutionException {
        if (th instanceof Error) {
            throw new ExecutionError((Error) th);
        }
        if (!(th instanceof RuntimeException)) {
            throw new ExecutionException(th);
        }
        throw new UncheckedExecutionException(th);
    }

    private void wrapAndThrowRuntimeExecutionExceptionOrError(Throwable th) {
        if (!(th instanceof Error)) {
            throw new UncheckedExecutionException(th);
        }
        throw new ExecutionError((Error) th);
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    @CanIgnoreReturnValue
    public <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit) throws ExecutionException, TimeoutException {
        if (callable == null) {
            throw new NullPointerException();
        }
        if (timeUnit == null) {
            throw new NullPointerException();
        }
        checkPositiveTimeout(j);
        Future<T> futureSubmit = this.executor.submit(callable);
        try {
            return (T) Uninterruptibles.getUninterruptibly(futureSubmit, j, timeUnit);
        } catch (ExecutionException e2) {
            wrapAndThrowExecutionExceptionOrError(e2.getCause());
            throw null;
        } catch (TimeoutException e3) {
            futureSubmit.cancel(true);
            throw e3;
        }
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T newProxy(final T t, Class<T> cls, final long j, final TimeUnit timeUnit) {
        if (t == null) {
            throw new NullPointerException();
        }
        if (cls == null) {
            throw new NullPointerException();
        }
        if (timeUnit == null) {
            throw new NullPointerException();
        }
        checkPositiveTimeout(j);
        Preconditions.checkArgument(cls.isInterface(), "interfaceType must be an interface type");
        final Set<Method> setFindInterruptibleMethods = findInterruptibleMethods(cls);
        return (T) newProxy(cls, new InvocationHandler() { // from class: com.google.common.util.concurrent.SimpleTimeLimiter.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, final Method method, final Object[] objArr) {
                return SimpleTimeLimiter.this.callWithTimeout(new Callable<Object>() { // from class: com.google.common.util.concurrent.SimpleTimeLimiter.1.1
                    @Override // java.util.concurrent.Callable
                    public Object call() throws Exception {
                        try {
                            return method.invoke(t, objArr);
                        } catch (InvocationTargetException e2) {
                            SimpleTimeLimiter.throwCause(e2, false);
                            throw null;
                        }
                    }
                }, j, timeUnit, setFindInterruptibleMethods.contains(method));
            }
        });
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runUninterruptiblyWithTimeout(Runnable runnable, long j, TimeUnit timeUnit) throws TimeoutException {
        if (runnable == null) {
            throw new NullPointerException();
        }
        if (timeUnit == null) {
            throw new NullPointerException();
        }
        checkPositiveTimeout(j);
        Future<?> futureSubmit = this.executor.submit(runnable);
        try {
            Uninterruptibles.getUninterruptibly(futureSubmit, j, timeUnit);
        } catch (ExecutionException e2) {
            wrapAndThrowRuntimeExecutionExceptionOrError(e2.getCause());
            throw null;
        } catch (TimeoutException e3) {
            futureSubmit.cancel(true);
            throw e3;
        }
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runWithTimeout(Runnable runnable, long j, TimeUnit timeUnit) throws Throwable {
        if (runnable == null) {
            throw new NullPointerException();
        }
        if (timeUnit == null) {
            throw new NullPointerException();
        }
        checkPositiveTimeout(j);
        Future<?> futureSubmit = this.executor.submit(runnable);
        try {
            futureSubmit.get(j, timeUnit);
        } catch (InterruptedException e2) {
            e = e2;
            futureSubmit.cancel(true);
            throw e;
        } catch (ExecutionException e3) {
            wrapAndThrowRuntimeExecutionExceptionOrError(e3.getCause());
            throw null;
        } catch (TimeoutException e4) {
            e = e4;
            futureSubmit.cancel(true);
            throw e;
        }
    }

    public static <T> T newProxy(Class<T> cls, InvocationHandler invocationHandler) {
        return cls.cast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler));
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    @CanIgnoreReturnValue
    public <T> T callWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit) throws Throwable {
        if (callable == null) {
            throw new NullPointerException();
        }
        if (timeUnit != null) {
            checkPositiveTimeout(j);
            Future<T> futureSubmit = this.executor.submit(callable);
            try {
                return futureSubmit.get(j, timeUnit);
            } catch (InterruptedException e2) {
                e = e2;
                futureSubmit.cancel(true);
                throw e;
            } catch (ExecutionException e3) {
                wrapAndThrowExecutionExceptionOrError(e3.getCause());
                throw null;
            } catch (TimeoutException e4) {
                e = e4;
                futureSubmit.cancel(true);
                throw e;
            }
        }
        throw new NullPointerException();
    }
}
