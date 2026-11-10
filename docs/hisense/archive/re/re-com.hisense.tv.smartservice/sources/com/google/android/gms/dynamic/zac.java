package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class zac implements DeferredLifecycleHelper.zaa {
    public final /* synthetic */ Activity val$activity;
    public final /* synthetic */ DeferredLifecycleHelper zart;
    public final /* synthetic */ Bundle zary;
    public final /* synthetic */ Bundle zarz;

    public zac(DeferredLifecycleHelper deferredLifecycleHelper, Activity activity, Bundle bundle, Bundle bundle2) {
        this.zart = deferredLifecycleHelper;
        this.val$activity = activity;
        this.zarz = bundle;
        this.zary = bundle2;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final int getState() {
        return 0;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        this.zart.zaru.onInflate(this.val$activity, this.zarz, this.zary);
    }
}
