package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.MainThread;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzfe {
    public final zza zza;

    public interface zza {
        void doStartService(Context context, Intent intent);
    }

    public zzfe(zza zzaVar) {
        Preconditions.checkNotNull(zzaVar);
        this.zza = zzaVar;
    }

    @MainThread
    public final void zza(Context context, Intent intent) {
        zzfv zzfvVarZzp = zzfv.zzp(context, null, null);
        zzel zzelVarZzay = zzfvVarZzp.zzay();
        if (intent == null) {
            zzelVarZzay.zzk().zza("Receiver called with null intent");
            return;
        }
        zzfvVarZzp.zzaw();
        String action = intent.getAction();
        zzelVarZzay.zzj().zzb("Local receiver got", action);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(action)) {
            if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
                zzelVarZzay.zzk().zza("Install Referrer Broadcasts are deprecated");
            }
        } else {
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            zzelVarZzay.zzj().zza("Starting wakeful intent.");
            this.zza.doStartService(context, className);
        }
    }
}
