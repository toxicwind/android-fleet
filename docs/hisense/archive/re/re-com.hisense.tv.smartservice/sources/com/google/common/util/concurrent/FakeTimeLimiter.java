package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@CanIgnoreReturnValue
@Beta
@GwtIncompatible
public final class FakeTimeLimiter implements TimeLimiter {
    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit) {
        return (T) callWithTimeout(callable, j, timeUnit);
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T callWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit) throws ExecutionException {
        if (callable == null) {
            throw new NullPointerException();
        }
        if (timeUnit == null) {
            throw new NullPointerException();
        }
        try {
            return callable.call();
        } catch (Error e2) {
            throw new ExecutionError(e2);
        } catch (RuntimeException e3) {
            throw new UncheckedExecutionException(e3);
        } catch (Exception e4) {
            throw new ExecutionException(e4);
        } catch (Throwable th) {
            throw new ExecutionException(th);
        }
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T newProxy(T t, Class<T> cls, long j, TimeUnit timeUnit) {
        if (t == null) {
            throw new NullPointerException();
        }
        if (cls == null) {
            throw new NullPointerException();
        }
        if (timeUnit != null) {
            return t;
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runUninterruptiblyWithTimeout(Runnable runnable, long j, TimeUnit timeUnit) {
        runWithTimeout(runnable, j, timeUnit);
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runWithTimeout(Runnable runnable, long j, TimeUnit timeUnit) {
        if (runnable == null) {
            throw new NullPointerException();
        }
        if (timeUnit == null) {
            throw new NullPointerException();
        }
        try {
            runnable.run();
        } catch (Error e2) {
            throw new ExecutionError(e2);
        } catch (RuntimeException e3) {
            throw new UncheckedExecutionException(e3);
        } catch (Throwable th) {
            throw new UncheckedExecutionException(th);
        }
    }
}
