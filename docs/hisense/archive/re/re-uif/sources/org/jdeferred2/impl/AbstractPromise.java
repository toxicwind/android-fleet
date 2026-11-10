package org.jdeferred2.impl;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jdeferred2.AlwaysCallback;
import org.jdeferred2.CallbackExceptionHandler;
import org.jdeferred2.DoneCallback;
import org.jdeferred2.FailCallback;
import org.jdeferred2.Promise;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractPromise<D, F, P> implements Promise<D, F, P> {
    protected final List<AlwaysCallback<? super D, ? super F>> alwaysCallbacks;
    protected final List<DoneCallback<? super D>> doneCallbacks;
    protected final List<FailCallback<? super F>> failCallbacks;
    protected F rejectResult;
    protected D resolveResult;
    protected volatile Promise.State state;

    public AbstractPromise() {
        LoggerFactory.getLogger((Class<?>) AbstractPromise.class);
        this.state = Promise.State.PENDING;
        this.doneCallbacks = new CopyOnWriteArrayList();
        this.failCallbacks = new CopyOnWriteArrayList();
        new CopyOnWriteArrayList();
        this.alwaysCallbacks = new CopyOnWriteArrayList();
    }

    @Override // org.jdeferred2.Promise
    public Promise<D, F, P> done(DoneCallback<? super D> doneCallback) {
        synchronized (this) {
            if (isResolved()) {
                triggerDone(doneCallback, this.resolveResult);
            } else {
                this.doneCallbacks.add(doneCallback);
            }
        }
        return this;
    }

    @Override // org.jdeferred2.Promise
    public Promise<D, F, P> fail(FailCallback<? super F> failCallback) {
        synchronized (this) {
            if (isRejected()) {
                triggerFail(failCallback, this.rejectResult);
            } else {
                this.failCallbacks.add(failCallback);
            }
        }
        return this;
    }

    protected void triggerDone(D d) {
        Iterator<DoneCallback<? super D>> it = this.doneCallbacks.iterator();
        while (it.hasNext()) {
            triggerDone(it.next(), d);
        }
        this.doneCallbacks.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void triggerDone(DoneCallback<? super D> doneCallback, D d) {
        try {
            doneCallback.onDone(d);
        } catch (Exception e) {
            handleException(CallbackExceptionHandler.CallbackType.DONE_CALLBACK, e);
        }
    }

    protected void triggerFail(F f) {
        Iterator<FailCallback<? super F>> it = this.failCallbacks.iterator();
        while (it.hasNext()) {
            triggerFail(it.next(), f);
        }
        this.failCallbacks.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void triggerFail(FailCallback<? super F> failCallback, F f) {
        try {
            failCallback.onFail(f);
        } catch (Exception e) {
            handleException(CallbackExceptionHandler.CallbackType.FAIL_CALLBACK, e);
        }
    }

    protected void triggerAlways(Promise.State state, D d, F f) {
        Iterator<AlwaysCallback<? super D, ? super F>> it = this.alwaysCallbacks.iterator();
        while (it.hasNext()) {
            triggerAlways(it.next(), state, d, f);
        }
        this.alwaysCallbacks.clear();
        synchronized (this) {
            notifyAll();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void triggerAlways(AlwaysCallback<? super D, ? super F> alwaysCallback, Promise.State state, D d, F f) {
        try {
            alwaysCallback.onAlways(state, d, f);
        } catch (Exception e) {
            handleException(CallbackExceptionHandler.CallbackType.ALWAYS_CALLBACK, e);
        }
    }

    @Override // org.jdeferred2.Promise
    public boolean isPending() {
        return this.state == Promise.State.PENDING;
    }

    public boolean isResolved() {
        return this.state == Promise.State.RESOLVED;
    }

    public boolean isRejected() {
        return this.state == Promise.State.REJECTED;
    }

    protected void handleException(CallbackExceptionHandler.CallbackType callbackType, Exception exc) {
        GlobalConfiguration.getGlobalCallbackExceptionHandler().handleException(callbackType, exc);
    }
}
