package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@DoNotMock("Use FakeTimeLimiter")
@Beta
@GwtIncompatible
public interface TimeLimiter {
    @CanIgnoreReturnValue
    <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit);

    @CanIgnoreReturnValue
    <T> T callWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit);

    <T> T newProxy(T t, Class<T> cls, long j, TimeUnit timeUnit);

    void runUninterruptiblyWithTimeout(Runnable runnable, long j, TimeUnit timeUnit);

    void runWithTimeout(Runnable runnable, long j, TimeUnit timeUnit);
}
