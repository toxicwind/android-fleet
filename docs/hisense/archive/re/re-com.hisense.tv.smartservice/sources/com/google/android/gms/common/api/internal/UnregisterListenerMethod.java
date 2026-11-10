package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public abstract class UnregisterListenerMethod<A extends Api.AnyClient, L> {
    public final ListenerHolder.ListenerKey<L> zajo;

    @KeepForSdk
    public UnregisterListenerMethod(ListenerHolder.ListenerKey<L> listenerKey) {
        this.zajo = listenerKey;
    }

    @KeepForSdk
    public ListenerHolder.ListenerKey<L> getListenerKey() {
        return this.zajo;
    }

    @KeepForSdk
    public abstract void unregisterListener(A a2, TaskCompletionSource<Boolean> taskCompletionSource);
}
