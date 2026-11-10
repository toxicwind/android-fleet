package com.google.android.gms.dynamic;

import java.util.Iterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes.dex */
public final class zaa<T> implements OnDelegateCreatedListener<T> {
    public final /* synthetic */ DeferredLifecycleHelper zart;

    public zaa(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zart = deferredLifecycleHelper;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)V */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(LifecycleDelegate lifecycleDelegate) {
        DeferredLifecycleHelper deferredLifecycleHelper = this.zart;
        deferredLifecycleHelper.zaru = lifecycleDelegate;
        Iterator<DeferredLifecycleHelper.zaa> it = deferredLifecycleHelper.zarw.iterator();
        while (it.hasNext()) {
            it.next().zaa(this.zart.zaru);
        }
        this.zart.zarw.clear();
        this.zart.zarv = null;
    }
}
