package com.google.android.gms.measurement.internal;

import android.util.Log;
import com.google.firebase.installations.Utils;
import com.hmct.cloud.sdk.utils.Constants;

/* JADX INFO: loaded from: classes.dex */
public final class zzei implements Runnable {
    public final /* synthetic */ int zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ Object zzc;
    public final /* synthetic */ Object zzd;
    public final /* synthetic */ Object zze;
    public final /* synthetic */ zzel zzf;

    public zzei(zzel zzelVar, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzf = zzelVar;
        this.zza = i;
        this.zzb = str;
        this.zzc = obj;
        this.zzd = obj2;
        this.zze = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfa zzfaVarZzm = this.zzf.zzs.zzm();
        if (!zzfaVarZzm.zzx()) {
            Log.println(6, this.zzf.zzq(), "Persisted config not initialized. Not logging error/warn");
            return;
        }
        zzel zzelVar = this.zzf;
        if (zzelVar.zza == 0) {
            if (zzelVar.zzs.zzf().zzy()) {
                zzel zzelVar2 = this.zzf;
                zzelVar2.zzs.zzaw();
                zzelVar2.zza = 'C';
            } else {
                zzel zzelVar3 = this.zzf;
                zzelVar3.zzs.zzaw();
                zzelVar3.zza = 'c';
            }
        }
        zzel zzelVar4 = this.zzf;
        if (zzelVar4.zzb < 0) {
            zzelVar4.zzs.zzf().zzh();
            zzelVar4.zzb = 46000L;
        }
        char cCharAt = "01VDIWEA?".charAt(this.zza);
        zzel zzelVar5 = this.zzf;
        char c2 = zzelVar5.zza;
        long j = zzelVar5.zzb;
        String strZzo = zzel.zzo(true, this.zzb, this.zzc, this.zzd, this.zze);
        StringBuilder sb = new StringBuilder(strZzo.length() + 24);
        sb.append(Constants.LANGUAGE_FRENCH);
        sb.append(cCharAt);
        sb.append(c2);
        sb.append(j);
        sb.append(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
        sb.append(strZzo);
        String string = sb.toString();
        if (string.length() > 1024) {
            string = this.zzb.substring(0, 1024);
        }
        zzey zzeyVar = zzfaVarZzm.zzb;
        if (zzeyVar != null) {
            zzeyVar.zzb(string, 1L);
        }
    }
}
