package com.google.common.util.concurrent;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.ForOverride;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class AbstractTransformFuture<I, O, F, T> extends FluentFuture.TrustedFuture<O> implements Runnable {
    public F function;
    public ListenableFuture<? extends I> inputFuture;

    private static final class AsyncTransformFuture<I, O> extends AbstractTransformFuture<I, O, AsyncFunction<? super I, ? extends O>, ListenableFuture<? extends O>> {
        public AsyncTransformFuture(ListenableFuture<? extends I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction) {
            super(listenableFuture, asyncFunction);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public ListenableFuture<? extends O> doTransform(AsyncFunction<? super I, ? extends O> asyncFunction, I i) {
            ListenableFuture<? extends O> listenableFutureApply = asyncFunction.apply(i);
            Preconditions.checkNotNull(listenableFutureApply, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", asyncFunction);
            return listenableFutureApply;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public void setResult(ListenableFuture<? extends O> listenableFuture) {
            setFuture(listenableFuture);
        }
    }

    private static final class TransformFuture<I, O> extends AbstractTransformFuture<I, O, Function<? super I, ? extends O>, O> {
        public TransformFuture(ListenableFuture<? extends I> listenableFuture, Function<? super I, ? extends O> function) {
            super(listenableFuture, function);
        }

        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public void setResult(O o) {
            set(o);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public O doTransform(Function<? super I, ? extends O> function, I i) {
            return function.apply(i);
        }
    }

    public AbstractTransformFuture(ListenableFuture<? extends I> listenableFuture, F f2) {
        if (listenableFuture == null) {
            throw new NullPointerException();
        }
        this.inputFuture = listenableFuture;
        if (f2 == null) {
            throw new NullPointerException();
        }
        this.function = f2;
    }

    public static <I, O> ListenableFuture<O> create(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction, Executor executor) {
        if (executor == null) {
            throw new NullPointerException();
        }
        AsyncTransformFuture asyncTransformFuture = new AsyncTransformFuture(listenableFuture, asyncFunction);
        listenableFuture.addListener(asyncTransformFuture, MoreExecutors.rejectionPropagatingExecutor(executor, asyncTransformFuture));
        return asyncTransformFuture;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.function = null;
    }

    @ForOverride
    public abstract T doTransform(F f2, I i);

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        String strA;
        ListenableFuture<? extends I> listenableFuture = this.inputFuture;
        F f2 = this.function;
        String strPendingToString = super.pendingToString();
        if (listenableFuture != null) {
            String strValueOf = String.valueOf(listenableFuture);
            strA = a.a(strValueOf.length() + 16, "inputFuture=[", strValueOf, "], ");
        } else {
            strA = "";
        }
        if (f2 != null) {
            String strValueOf2 = String.valueOf(f2);
            return a.b(strValueOf2.length() + a.a((Object) strA, 11), strA, "function=[", strValueOf2, "]");
        }
        if (strPendingToString == null) {
            return null;
        }
        String strValueOf3 = String.valueOf(strA);
        return strPendingToString.length() != 0 ? strValueOf3.concat(strPendingToString) : new String(strValueOf3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture<? extends I> listenableFuture = this.inputFuture;
        F f2 = this.function;
        if ((isCancelled() | (listenableFuture == null)) || (f2 == null)) {
            return;
        }
        this.inputFuture = null;
        if (listenableFuture.isCancelled()) {
            setFuture(listenableFuture);
            return;
        }
        try {
            try {
                try {
                    Object objDoTransform = doTransform(f2, Futures.getDone(listenableFuture));
                    this.function = null;
                    setResult(objDoTransform);
                } catch (Throwable th) {
                    setException(th);
                    this.function = null;
                }
            } catch (Throwable th2) {
                this.function = null;
                throw th2;
            }
        } catch (Error e2) {
            setException(e2);
        } catch (CancellationException unused) {
            cancel(false);
        } catch (RuntimeException e3) {
            setException(e3);
        } catch (ExecutionException e4) {
            setException(e4.getCause());
        }
    }

    @ForOverride
    public abstract void setResult(T t);

    public static <I, O> ListenableFuture<O> create(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function, Executor executor) {
        if (function != null) {
            TransformFuture transformFuture = new TransformFuture(listenableFuture, function);
            listenableFuture.addListener(transformFuture, MoreExecutors.rejectionPropagatingExecutor(executor, transformFuture));
            return transformFuture;
        }
        throw new NullPointerException();
    }
}
