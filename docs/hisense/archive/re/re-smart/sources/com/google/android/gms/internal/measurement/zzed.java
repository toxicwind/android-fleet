package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class zzed implements Application.ActivityLifecycleCallbacks {
    public final /* synthetic */ zzee zza;

    public zzed(zzee zzeeVar) {
        this.zza = zzeeVar;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zzee zzeeVar = this.zza;
        zzeeVar.zzb.execute(new zzdw(this, bundle, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        zzee zzeeVar = this.zza;
        zzeeVar.zzb.execute(new zzec(this, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        zzee zzeeVar = this.zza;
        zzeeVar.zzb.execute(new zzdz(this, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zzee zzeeVar = this.zza;
        zzeeVar.zzb.execute(new zzdy(this, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzbz zzbzVar = new zzbz();
        zzee zzeeVar = this.zza;
        zzeeVar.zzb.execute(new zzeb(this, activity, zzbzVar));
        Bundle bundleZzb = zzbzVar.zzb(50L);
        if (bundleZzb != null) {
            bundle.putAll(bundleZzb);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        zzee zzeeVar = this.zza;
        zzeeVar.zzb.execute(new zzdx(this, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        zzee zzeeVar = this.zza;
        zzeeVar.zzb.execute(new zzea(this, activity));
    }
}
