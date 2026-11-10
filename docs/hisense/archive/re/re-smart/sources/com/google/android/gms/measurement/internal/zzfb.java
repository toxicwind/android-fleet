package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class zzfb implements Runnable {
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzbr zza;
    public final /* synthetic */ ServiceConnection zzb;
    public final /* synthetic */ zzfc zzc;

    public zzfb(zzfc zzfcVar, com.google.android.gms.internal.measurement.zzbr zzbrVar, ServiceConnection serviceConnection) {
        this.zzc = zzfcVar;
        this.zza = zzbrVar;
        this.zzb = serviceConnection;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfc zzfcVar = this.zzc;
        zzfd zzfdVar = zzfcVar.zza;
        String str = zzfcVar.zzb;
        com.google.android.gms.internal.measurement.zzbr zzbrVar = this.zza;
        ServiceConnection serviceConnection = this.zzb;
        zzfdVar.zza.zzaz().zzg();
        Bundle bundle = new Bundle();
        bundle.putString("package_name", str);
        try {
            if (zzbrVar.zzd(bundle) == null) {
                zzfdVar.zza.zzay().zzd().zza("Install Referrer Service returned a null response");
            }
        } catch (Exception e2) {
            zzfdVar.zza.zzay().zzd().zzb("Exception occurred while retrieving the Install Referrer", e2.getMessage());
        }
        zzfdVar.zza.zzaz().zzg();
        zzfv.zzO();
        throw null;
    }
}
