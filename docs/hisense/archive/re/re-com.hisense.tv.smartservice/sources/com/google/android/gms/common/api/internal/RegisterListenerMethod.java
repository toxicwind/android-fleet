package com.google.android.gms.common.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public abstract class RegisterListenerMethod<A extends Api.AnyClient, L> {
    public final ListenerHolder<L> zaju;
    public final Feature[] zajv;
    public final boolean zajw;

    @KeepForSdk
    public RegisterListenerMethod(ListenerHolder<L> listenerHolder) {
        this.zaju = listenerHolder;
        this.zajv = null;
        this.zajw = false;
    }

    @KeepForSdk
    public void clearListener() {
        this.zaju.clear();
    }

    @KeepForSdk
    public ListenerHolder.ListenerKey<L> getListenerKey() {
        return this.zaju.getListenerKey();
    }

    @Nullable
    @KeepForSdk
    public Feature[] getRequiredFeatures() {
        return this.zajv;
    }

    @KeepForSdk
    public abstract void registerListener(A a2, TaskCompletionSource<Void> taskCompletionSource);

    public final boolean shouldAutoResolveMissingFeatures() {
        return this.zajw;
    }

    @KeepForSdk
    public RegisterListenerMethod(ListenerHolder<L> listenerHolder, Feature[] featureArr, boolean z) {
        this.zaju = listenerHolder;
        this.zajv = featureArr;
        this.zajw = z;
    }
}
