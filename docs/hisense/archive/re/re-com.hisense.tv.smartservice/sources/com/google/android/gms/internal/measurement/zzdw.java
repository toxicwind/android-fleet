package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: loaded from: classes.dex */
public final class zzdw extends zzdt {
    public final /* synthetic */ Bundle zza;
    public final /* synthetic */ Activity zzb;
    public final /* synthetic */ zzed zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdw(zzed zzedVar, Bundle bundle, Activity activity) {
        super(zzedVar.zza, true);
        this.zzc = zzedVar;
        this.zza = bundle;
        this.zzb = activity;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    public final void zza() {
        Bundle bundle;
        if (this.zza != null) {
            bundle = new Bundle();
            if (this.zza.containsKey("com.google.app_measurement.screen_service")) {
                Object obj = this.zza.get("com.google.app_measurement.screen_service");
                if (obj instanceof Bundle) {
                    bundle.putBundle("com.google.app_measurement.screen_service", (Bundle) obj);
                }
            }
        } else {
            bundle = null;
        }
        zzcc zzccVar = this.zzc.zza.zzj;
        Preconditions.checkNotNull(zzccVar);
        zzccVar.onActivityCreated(new ObjectWrapper(this.zzb), bundle, this.zzi);
    }
}
