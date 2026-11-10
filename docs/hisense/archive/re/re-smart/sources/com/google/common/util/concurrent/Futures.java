package com.google.common.util.concurrent;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Futures extends GwtFuturesCatchingSpecialization {

    private static final class CallbackListener<V> implements Runnable {
        public final FutureCallback<? super V> callback;
        public final Future<V> future;

        public CallbackListener(Future<V> future, FutureCallback<? super V> futureCallback) {
            this.future = future;
            this.callback = futureCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable thTryInternalFastPathGetFailure;
            Future<V> future = this.future;
            if ((future instanceof InternalFutureFailureAccess) && (thTryInternalFastPathGetFailure = ((InternalFutureFailureAccess) future).tryInternalFastPathGetFailure()) != null) {
                this.callback.onFailure(thTryInternalFastPathGetFailure);
                return;
            }
            try {
                this.callback.onSuccess(Futures.getDone(this.future));
            } catch (Error e2) {
                e = e2;
                this.callback.onFailure(e);
            } catch (RuntimeException e3) {
                e = e3;
                this.callback.onFailure(e);
            } catch (ExecutionException e4) {
                this.callback.onFailure(e4.getCause());
            }
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).addValue(this.callback).toString();
        }
    }

    private static final class InCompletionOrderState<T> {
        public volatile int delegateIndex;
        public final AtomicInteger incompleteOutputCount;
        public final ListenableFuture<? extends T>[] inputFutures;
        public boolean shouldInterrupt;
        public boolean wasCancelled;

        public static /* synthetic */ void access$400(InCompletionOrderState inCompletionOrderState, boolean z) {
            inCompletionOrderState.wasCancelled = true;
            if (!z) {
                inCompletionOrderState.shouldInterrupt = false;
            }
            inCompletionOrderState.recordCompletion();
        }

        private void recordCompletion() {
            if (this.incompleteOutputCount.decrementAndGet() == 0 && this.wasCancelled) {
                for (ListenableFuture<? extends T> listenableFuture : this.inputFutures) {
                    if (listenableFuture != null) {
                        listenableFuture.cancel(this.shouldInterrupt);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void recordInputCompletion(ImmutableList<AbstractFuture<T>> immutableList, int i) {
            ListenableFuture<? extends T>[] listenableFutureArr = this.inputFutures;
            ListenableFuture<? extends T> listenableFuture = listenableFutureArr[i];
            listenableFutureArr[i] = null;
            for (int i2 = this.delegateIndex; i2 < immutableList.size(); i2++) {
                if (immutableList.get(i2).setFuture(listenableFuture)) {
                    recordCompletion();
                    this.delegateIndex = i2 + 1;
                    return;
                }
            }
            this.delegateIndex = immutableList.size();
        }

        private void recordOutputCancellation(boolean z) {
            this.wasCancelled = true;
            if (!z) {
                this.shouldInterrupt = false;
            }
            recordCompletion();
        }

        public InCompletionOrderState(ListenableFuture<? extends T>[] listenableFutureArr) {
            this.wasCancelled = false;
            this.shouldInterrupt = true;
            this.delegateIndex = 0;
            this.inputFutures = listenableFutureArr;
            this.incompleteOutputCount = new AtomicInteger(listenableFutureArr.length);
        }
    }

    private static final class NonCancellationPropagatingFuture<V> extends AbstractFuture.TrustedFuture<V> implements Runnable {
        public ListenableFuture<V> delegate;

        public NonCancellationPropagatingFuture(ListenableFuture<V> listenableFuture) {
            this.delegate = listenableFuture;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public void afterDone() {
            this.delegate = null;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public String pendingToString() {
            ListenableFuture<V> listenableFuture = this.delegate;
            if (listenableFuture == null) {
                return null;
            }
            String strValueOf = String.valueOf(listenableFuture);
            return a.a(strValueOf.length() + 11, "delegate=[", strValueOf, "]");
        }

        @Override // java.lang.Runnable
        public void run() {
            ListenableFuture<V> listenableFuture = this.delegate;
            if (listenableFuture != null) {
                setFuture(listenableFuture);
            }
        }
    }

    public static <V> void addCallback(ListenableFuture<V> listenableFuture, FutureCallback<? super V> futureCallback, Executor executor) {
        if (futureCallback == null) {
            throw new NullPointerException();
        }
        listenableFuture.addListener(new CallbackListener(listenableFuture, futureCallback), executor);
    }

    @SafeVarargs
    @Beta
    public static <V> ListenableFuture<List<V>> allAsList(ListenableFuture<? extends V>... listenableFutureArr) {
        return new CollectionFuture.ListFuture(ImmutableList.copyOf(listenableFutureArr), true);
    }

    @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    @Beta
    public static <V, X extends Throwable> ListenableFuture<V> catching(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function, Executor executor) {
        return AbstractCatchingFuture.create(listenableFuture, cls, function, executor);
    }

    @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    @Beta
    public static <V, X extends Throwable> ListenableFuture<V> catchingAsync(ListenableFuture<? extends V> listenableFuture, Class<X> cls, AsyncFunction<? super X, ? extends V> asyncFunction, Executor executor) {
        return AbstractCatchingFuture.create(listenableFuture, cls, asyncFunction, executor);
    }

    @CanIgnoreReturnValue
    @Beta
    @GwtIncompatible
    public static <V, X extends Exception> V getChecked(Future<V> future, Class<X> cls) {
        return (V) FuturesGetChecked.getChecked(future, cls);
    }

    @CanIgnoreReturnValue
    public static <V> V getDone(Future<V> future) {
        Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", future);
        return (V) Uninterruptibles.getUninterruptibly(future);
    }

    @CanIgnoreReturnValue
    public static <V> V getUnchecked(Future<V> future) {
        if (future == null) {
            throw new NullPointerException();
        }
        try {
            return (V) Uninterruptibles.getUninterruptibly(future);
        } catch (ExecutionException e2) {
            wrapAndThrowUnchecked(e2.getCause());
            throw null;
        }
    }

    public static <V> ListenableFuture<V> immediateCancelledFuture() {
        return new ImmediateFuture.ImmediateCancelledFuture();
    }

    public static <V> ListenableFuture<V> immediateFailedFuture(Throwable th) {
        if (th != null) {
            return new ImmediateFuture.ImmediateFailedFuture(th);
        }
        throw new NullPointerException();
    }

    public static <V> ListenableFuture<V> immediateFuture(V v) {
        return v == null ? (ListenableFuture<V>) ImmediateFuture.NULL : new ImmediateFuture(v);
    }

    public static ListenableFuture<Void> immediateVoidFuture() {
        return ImmediateFuture.NULL;
    }

    @Beta
    public static <T> ImmutableList<ListenableFuture<T>> inCompletionOrder(Iterable<? extends ListenableFuture<? extends T>> iterable) {
        Collection collectionCopyOf = iterable instanceof Collection ? (Collection) iterable : ImmutableList.copyOf(iterable);
        ListenableFuture[] listenableFutureArr = (ListenableFuture[]) collectionCopyOf.toArray(new ListenableFuture[collectionCopyOf.size()]);
        final InCompletionOrderState inCompletionOrderState = new InCompletionOrderState(listenableFutureArr);
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < listenableFutureArr.length; i++) {
            builder.add(new InCompletionOrderFuture(inCompletionOrderState, null));
        }
        final ImmutableList<ListenableFuture<T>> immutableListBuild = builder.build();
        for (final int i2 = 0; i2 < listenableFutureArr.length; i2++) {
            listenableFutureArr[i2].addListener(new Runnable() { // from class: com.google.common.util.concurrent.Futures.3
                @Override // java.lang.Runnable
                public void run() {
                    inCompletionOrderState.recordInputCompletion(immutableListBuild, i2);
                }
            }, DirectExecutor.INSTANCE);
        }
        return immutableListBuild;
    }

    @Beta
    @GwtIncompatible
    public static <I, O> Future<O> lazyTransform(final Future<I> future, final Function<? super I, ? extends O> function) {
        if (future == null) {
            throw new NullPointerException();
        }
        if (function != null) {
            return new Future<O>() { // from class: com.google.common.util.concurrent.Futures.2
                private O applyTransformation(I i) throws ExecutionException {
                    try {
                        return (O) function.apply(i);
                    } catch (Throwable th) {
                        throw new ExecutionException(th);
                    }
                }

                @Override // java.util.concurrent.Future
                public boolean cancel(boolean z) {
                    return future.cancel(z);
                }

                @Override // java.util.concurrent.Future
                public O get() {
                    return applyTransformation(future.get());
                }

                @Override // java.util.concurrent.Future
                public boolean isCancelled() {
                    return future.isCancelled();
                }

                @Override // java.util.concurrent.Future
                public boolean isDone() {
                    return future.isDone();
                }

                @Override // java.util.concurrent.Future
                public O get(long j, TimeUnit timeUnit) {
                    return applyTransformation(future.get(j, timeUnit));
                }
            };
        }
        throw new NullPointerException();
    }

    @Beta
    public static <V> ListenableFuture<V> nonCancellationPropagating(ListenableFuture<V> listenableFuture) {
        if (listenableFuture.isDone()) {
            return listenableFuture;
        }
        NonCancellationPropagatingFuture nonCancellationPropagatingFuture = new NonCancellationPropagatingFuture(listenableFuture);
        listenableFuture.addListener(nonCancellationPropagatingFuture, DirectExecutor.INSTANCE);
        return nonCancellationPropagatingFuture;
    }

    @Beta
    @GwtIncompatible
    public static <O> ListenableFuture<O> scheduleAsync(AsyncCallable<O> asyncCallable, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        TrustedListenableFutureTask trustedListenableFutureTask = new TrustedListenableFutureTask(asyncCallable);
        final ScheduledFuture<?> scheduledFutureSchedule = scheduledExecutorService.schedule(trustedListenableFutureTask, j, timeUnit);
        trustedListenableFutureTask.addListener(new Runnable() { // from class: com.google.common.util.concurrent.Futures.1
            @Override // java.lang.Runnable
            public void run() {
                scheduledFutureSchedule.cancel(false);
            }
        }, DirectExecutor.INSTANCE);
        return trustedListenableFutureTask;
    }

    @Beta
    public static <O> ListenableFuture<O> submit(Callable<O> callable, Executor executor) {
        TrustedListenableFutureTask trustedListenableFutureTask = new TrustedListenableFutureTask(callable);
        executor.execute(trustedListenableFutureTask);
        return trustedListenableFutureTask;
    }

    @Beta
    public static <O> ListenableFuture<O> submitAsync(AsyncCallable<O> asyncCallable, Executor executor) {
        TrustedListenableFutureTask trustedListenableFutureTask = new TrustedListenableFutureTask(asyncCallable);
        executor.execute(trustedListenableFutureTask);
        return trustedListenableFutureTask;
    }

    @SafeVarargs
    @Beta
    public static <V> ListenableFuture<List<V>> successfulAsList(ListenableFuture<? extends V>... listenableFutureArr) {
        return new CollectionFuture.ListFuture(ImmutableList.copyOf(listenableFutureArr), false);
    }

    @Beta
    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function, Executor executor) {
        return AbstractTransformFuture.create(listenableFuture, function, executor);
    }

    @Beta
    public static <I, O> ListenableFuture<O> transformAsync(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction, Executor executor) {
        return AbstractTransformFuture.create(listenableFuture, asyncFunction, executor);
    }

    @SafeVarargs
    @Beta
    public static <V> FutureCombiner<V> whenAllComplete(ListenableFuture<? extends V>... listenableFutureArr) {
        return new FutureCombiner<>(false, ImmutableList.copyOf(listenableFutureArr), null);
    }

    @SafeVarargs
    @Beta
    public static <V> FutureCombiner<V> whenAllSucceed(ListenableFuture<? extends V>... listenableFutureArr) {
        return new FutureCombiner<>(true, ImmutableList.copyOf(listenableFutureArr), null);
    }

    @Beta
    @GwtIncompatible
    public static <V> ListenableFuture<V> withTimeout(ListenableFuture<V> listenableFuture, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        return listenableFuture.isDone() ? listenableFuture : TimeoutFuture.create(listenableFuture, j, timeUnit, scheduledExecutorService);
    }

    public static void wrapAndThrowUnchecked(Throwable th) {
        if (!(th instanceof Error)) {
            throw new UncheckedExecutionException(th);
        }
        throw new ExecutionError((Error) th);
    }

    private static final class InCompletionOrderFuture<T> extends AbstractFuture<T> {
        public InCompletionOrderState<T> state;

        public InCompletionOrderFuture(InCompletionOrderState<T> inCompletionOrderState) {
            this.state = inCompletionOrderState;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public void afterDone() {
            this.state = null;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public boolean cancel(boolean z) {
            InCompletionOrderState<T> inCompletionOrderState = this.state;
            if (!super.cancel(z)) {
                return false;
            }
            InCompletionOrderState.access$400(inCompletionOrderState, z);
            return true;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public String pendingToString() {
            InCompletionOrderState<T> inCompletionOrderState = this.state;
            if (inCompletionOrderState == null) {
                return null;
            }
            int length = inCompletionOrderState.inputFutures.length;
            int i = inCompletionOrderState.incompleteOutputCount.get();
            StringBuilder sb = new StringBuilder(49);
            sb.append("inputCount=[");
            sb.append(length);
            sb.append("], remaining=[");
            sb.append(i);
            sb.append("]");
            return sb.toString();
        }

        public /* synthetic */ InCompletionOrderFuture(InCompletionOrderState inCompletionOrderState, AnonymousClass1 anonymousClass1) {
            this.state = inCompletionOrderState;
        }
    }

    @Beta
    public static <V> ListenableFuture<List<V>> allAsList(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return new CollectionFuture.ListFuture(ImmutableList.copyOf(iterable), true);
    }

    @CanIgnoreReturnValue
    @Beta
    @GwtIncompatible
    public static <V, X extends Exception> V getChecked(Future<V> future, Class<X> cls, long j, TimeUnit timeUnit) {
        return (V) FuturesGetChecked.getChecked(future, cls, j, timeUnit);
    }

    @Beta
    public static <V> ListenableFuture<List<V>> successfulAsList(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return new CollectionFuture.ListFuture(ImmutableList.copyOf(iterable), false);
    }

    @Beta
    public static <V> FutureCombiner<V> whenAllComplete(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return new FutureCombiner<>(false, ImmutableList.copyOf(iterable), null);
    }

    @Beta
    public static <V> FutureCombiner<V> whenAllSucceed(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return new FutureCombiner<>(true, ImmutableList.copyOf(iterable), null);
    }

    @Beta
    @GwtCompatible
    @CanIgnoreReturnValue
    public static final class FutureCombiner<V> {
        public final boolean allMustSucceed;
        public final ImmutableList<ListenableFuture<? extends V>> futures;

        public FutureCombiner(boolean z, ImmutableList<ListenableFuture<? extends V>> immutableList) {
            this.allMustSucceed = z;
            this.futures = immutableList;
        }

        @CanIgnoreReturnValue
        public <C> ListenableFuture<C> call(Callable<C> callable, Executor executor) {
            return new CombinedFuture(this.futures, this.allMustSucceed, executor, callable);
        }

        public <C> ListenableFuture<C> callAsync(AsyncCallable<C> asyncCallable, Executor executor) {
            return new CombinedFuture(this.futures, this.allMustSucceed, executor, asyncCallable);
        }

        public ListenableFuture<?> run(final Runnable runnable, Executor executor) {
            return call(new Callable<Void>(this) { // from class: com.google.common.util.concurrent.Futures.FutureCombiner.1
                @Override // java.util.concurrent.Callable
                public Void call() {
                    runnable.run();
                    return null;
                }
            }, executor);
        }

        public /* synthetic */ FutureCombiner(boolean z, ImmutableList immutableList, AnonymousClass1 anonymousClass1) {
            this.allMustSucceed = z;
            this.futures = immutableList;
        }
    }

    @Beta
    public static ListenableFuture<Void> submit(Runnable runnable, Executor executor) {
        TrustedListenableFutureTask trustedListenableFutureTaskCreate = TrustedListenableFutureTask.create(runnable, null);
        executor.execute(trustedListenableFutureTaskCreate);
        return trustedListenableFutureTaskCreate;
    }
}
