package org.jdeferred2.impl;

import org.jdeferred2.Deferred;
import org.jdeferred2.Promise;

/* JADX INFO: loaded from: classes.dex */
public class DeferredObject<D, F, P> extends AbstractPromise<D, F, P> implements Deferred<D, F, P> {
    @Override // org.jdeferred2.Deferred
    public Deferred<D, F, P> resolve(D d) {
        synchronized (this) {
            if (!isPending()) {
                throw new IllegalStateException("Deferred object already finished, cannot resolve again");
            }
            this.state = Promise.State.RESOLVED;
            this.resolveResult = d;
            try {
                triggerDone(d);
                triggerAlways(this.state, d, null);
            } catch (Throwable th) {
                triggerAlways(this.state, d, null);
                throw th;
            }
        }
        return this;
    }

    @Override // org.jdeferred2.Deferred
    public Deferred<D, F, P> reject(F f) {
        synchronized (this) {
            if (!isPending()) {
                throw new IllegalStateException("Deferred object already finished, cannot reject again");
            }
            this.state = Promise.State.REJECTED;
            this.rejectResult = f;
            try {
                triggerFail(f);
                triggerAlways(this.state, null, f);
            } catch (Throwable th) {
                triggerAlways(this.state, null, f);
                throw th;
            }
        }
        return this;
    }
}
