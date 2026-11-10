package com.google.common.util.concurrent;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.errorprone.annotations.ForOverride;
import java.lang.Throwable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class AbstractCatchingFuture<V, X extends Throwable, F, T> extends FluentFuture.TrustedFuture<V> implements Runnable {
    public Class<X> exceptionType;
    public F fallback;
    public ListenableFuture<? extends V> inputFuture;

    private static final class AsyncCatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, AsyncFunction<? super X, ? extends V>, ListenableFuture<? extends V>> {
        public AsyncCatchingFuture(ListenableFuture<? extends V> listenableFuture, Class<X> cls, AsyncFunction<? super X, ? extends V> asyncFunction) {
            super(listenableFuture, cls, asyncFunction);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public ListenableFuture<? extends V> doFallback(AsyncFunction<? super X, ? extends V> asyncFunction, X x) {
            ListenableFuture<? extends V> listenableFutureApply = asyncFunction.apply(x);
            Preconditions.checkNotNull(listenableFutureApply, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", asyncFunction);
            return listenableFutureApply;
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public void setResult(ListenableFuture<? extends V> listenableFuture) {
            setFuture(listenableFuture);
        }
    }

    private static final class CatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, Function<? super X, ? extends V>, V> {
        public CatchingFuture(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function) {
            super(listenableFuture, cls, function);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public void setResult(V v) {
            set(v);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public V doFallback(Function<? super X, ? extends V> function, X x) {
            return function.apply(x);
        }
    }

    public AbstractCatchingFuture(ListenableFuture<? extends V> listenableFuture, Class<X> cls, F f2) {
        if (listenableFuture == null) {
            throw new NullPointerException();
        }
        this.inputFuture = listenableFuture;
        if (cls == null) {
            throw new NullPointerException();
        }
        this.exceptionType = cls;
        if (f2 == null) {
            throw new NullPointerException();
        }
        this.fallback = f2;
    }

    public static <V, X extends Throwable> ListenableFuture<V> create(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function, Executor executor) {
        CatchingFuture catchingFuture = new CatchingFuture(listenableFuture, cls, function);
        listenableFuture.addListener(catchingFuture, MoreExecutors.rejectionPropagatingExecutor(executor, catchingFuture));
        return catchingFuture;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
    }

    @ForOverride
    public abstract T doFallback(F f2, X x);

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        String strA;
        ListenableFuture<? extends V> listenableFuture = this.inputFuture;
        Class<X> cls = this.exceptionType;
        F f2 = this.fallback;
        String strPendingToString = super.pendingToString();
        if (listenableFuture != null) {
            String strValueOf = String.valueOf(listenableFuture);
            strA = a.a(strValueOf.length() + 16, "inputFuture=[", strValueOf, "], ");
        } else {
            strA = "";
        }
        if (cls != null && f2 != null) {
            String strValueOf2 = String.valueOf(cls);
            String strValueOf3 = String.valueOf(f2);
            return a.a(a.a(strValueOf3.length() + strValueOf2.length() + a.a((Object) strA, 29), strA, "exceptionType=[", strValueOf2, "], fallback=["), strValueOf3, "]");
        }
        if (strPendingToString == null) {
            return null;
        }
        String strValueOf4 = String.valueOf(strA);
        return strPendingToString.length() != 0 ? strValueOf4.concat(strPendingToString) : new String(strValueOf4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        Object done;
        ListenableFuture<? extends V> listenableFuture = this.inputFuture;
        Class<X> cls = this.exceptionType;
        F f2 = this.fallback;
        if (((f2 == null) || ((listenableFuture == null) | (cls == null))) || isCancelled()) {
            return;
        }
        this.inputFuture = null;
        try {
            th = listenableFuture instanceof InternalFutureFailureAccess ? ((InternalFutureFailureAccess) listenableFuture).tryInternalFastPathGetFailure() : null;
            done = th == null ? Futures.getDone(listenableFuture) : null;
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause == null) {
                String strValueOf = String.valueOf(listenableFuture.getClass());
                String strValueOf2 = String.valueOf(e2.getClass());
                StringBuilder sbA = a.a(strValueOf2.length() + strValueOf.length() + 35, "Future type ", strValueOf, " threw ", strValueOf2);
                sbA.append(" without a cause");
                cause = new NullPointerException(sbA.toString());
            }
            th = cause;
        } catch (Throwable th) {
            th = th;
        }
        if (th == null) {
            set(done);
            return;
        }
        try {
            if (!cls.isInstance(th)) {
                setFuture(listenableFuture);
                return;
            }
            try {
                Object objDoFallback = doFallback(f2, th);
                this.exceptionType = null;
                this.fallback = null;
                setResult(objDoFallback);
            } catch (Throwable th2) {
                setException(th2);
                this.exceptionType = null;
                this.fallback = null;
            }
        } catch (Throwable th3) {
            this.exceptionType = null;
            this.fallback = null;
            throw th3;
        }
    }

    @ForOverride
    public abstract void setResult(T t);

    public static <X extends Throwable, V> ListenableFuture<V> create(ListenableFuture<? extends V> listenableFuture, Class<X> cls, AsyncFunction<? super X, ? extends V> asyncFunction, Executor executor) {
        AsyncCatchingFuture asyncCatchingFuture = new AsyncCatchingFuture(listenableFuture, cls, asyncFunction);
        listenableFuture.addListener(asyncCatchingFuture, MoreExecutors.rejectionPropagatingExecutor(executor, asyncCatchingFuture));
        return asyncCatchingFuture;
    }
}
