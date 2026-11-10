package org.jdeferred2;

/* JADX INFO: loaded from: classes.dex */
public interface Promise<D, F, P> {

    public enum State {
        PENDING,
        REJECTED,
        RESOLVED
    }

    Promise<D, F, P> done(DoneCallback<? super D> doneCallback);

    Promise<D, F, P> fail(FailCallback<? super F> failCallback);

    boolean isPending();
}
