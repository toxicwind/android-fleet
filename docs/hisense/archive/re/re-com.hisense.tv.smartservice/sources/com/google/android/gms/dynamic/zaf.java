package com.google.android.gms.dynamic;

/* JADX INFO: loaded from: classes.dex */
public final class zaf implements DeferredLifecycleHelper.zaa {
    public final /* synthetic */ DeferredLifecycleHelper zart;

    public zaf(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zart = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final int getState() {
        return 5;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        this.zart.zaru.onResume();
    }
}
