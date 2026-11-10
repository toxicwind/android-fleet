package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class zae implements DeferredLifecycleHelper.zaa {
    public final /* synthetic */ ViewGroup val$container;
    public final /* synthetic */ DeferredLifecycleHelper zart;
    public final /* synthetic */ Bundle zary;
    public final /* synthetic */ FrameLayout zasb;
    public final /* synthetic */ LayoutInflater zasc;

    public zae(DeferredLifecycleHelper deferredLifecycleHelper, FrameLayout frameLayout, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.zart = deferredLifecycleHelper;
        this.zasb = frameLayout;
        this.zasc = layoutInflater;
        this.val$container = viewGroup;
        this.zary = bundle;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final int getState() {
        return 2;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        this.zasb.removeAllViews();
        this.zasb.addView(this.zart.zaru.onCreateView(this.zasc, this.val$container, this.zary));
    }
}
