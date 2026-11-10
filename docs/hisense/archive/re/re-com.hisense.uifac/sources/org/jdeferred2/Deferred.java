package org.jdeferred2;

/* JADX INFO: loaded from: classes.dex */
public interface Deferred<D, F, P> extends Promise<D, F, P> {
    Deferred<D, F, P> reject(F f);

    Deferred<D, F, P> resolve(D d);
}
