package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;

/* JADX INFO: loaded from: classes.dex */
public final class zzcx extends zzdt {
    public final /* synthetic */ String zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ Context zzc;
    public final /* synthetic */ Bundle zzd;
    public final /* synthetic */ zzee zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcx(zzee zzeeVar, String str, String str2, Context context, Bundle bundle) {
        super(zzeeVar, true);
        this.zze = zzeeVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = context;
        this.zzd = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    public final void zza() {
        String str;
        String str2;
        String str3;
        try {
            zzee zzeeVar = this.zze;
            if (zzee.zzV(this.zza, this.zzb)) {
                String str4 = this.zzb;
                str2 = this.zza;
                str3 = str4;
                str = this.zze.zzd;
            } else {
                str = null;
                str2 = null;
                str3 = null;
            }
            Preconditions.checkNotNull(this.zzc);
            zzee zzeeVar2 = this.zze;
            zzeeVar2.zzj = zzeeVar2.zzf(this.zzc, true);
            if (this.zze.zzj == null) {
                Log.w(this.zze.zzd, "Failed to connect to measurement client.");
                return;
            }
            int localVersion = DynamiteModule.getLocalVersion(this.zzc, ModuleDescriptor.MODULE_ID);
            int remoteVersion = DynamiteModule.getRemoteVersion(this.zzc, ModuleDescriptor.MODULE_ID);
            zzcl zzclVar = new zzcl(46000L, Math.max(localVersion, remoteVersion), remoteVersion < localVersion, str, str2, str3, this.zzd, com.google.android.gms.measurement.internal.zzfn.zza(this.zzc));
            zzcc zzccVar = this.zze.zzj;
            Preconditions.checkNotNull(zzccVar);
            zzccVar.initialize(new ObjectWrapper(this.zzc), zzclVar, this.zzh);
        } catch (Exception e2) {
            this.zze.zzS(e2, true, false);
        }
    }
}
