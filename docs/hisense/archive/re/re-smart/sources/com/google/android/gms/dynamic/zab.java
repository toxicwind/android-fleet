package com.google.android.gms.dynamic;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class zab implements DeferredLifecycleHelper.zaa {
    public final /* synthetic */ DeferredLifecycleHelper zart;
    public final /* synthetic */ Bundle zary;

    public zab(DeferredLifecycleHelper deferredLifecycleHelper, Bundle bundle) {
        this.zart = deferredLifecycleHelper;
        this.zary = bundle;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final int getState() {
        return 1;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        this.zart.zaru.onCreate(this.zary);
    }
}
