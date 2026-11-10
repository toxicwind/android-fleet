package com.google.android.gms.dynamic;

/* JADX INFO: loaded from: classes.dex */
public final class zag implements DeferredLifecycleHelper.zaa {
    public final /* synthetic */ DeferredLifecycleHelper zart;

    public zag(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zart = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final int getState() {
        return 4;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        this.zart.zaru.onStart();
    }
}
