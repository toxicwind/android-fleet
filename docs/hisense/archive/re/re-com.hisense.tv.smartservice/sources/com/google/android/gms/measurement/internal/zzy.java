package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzoa;

/* JADX INFO: loaded from: classes.dex */
public final class zzy extends zzx {
    public final /* synthetic */ zzz zza;
    public final com.google.android.gms.internal.measurement.zzes zzh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzy(zzz zzzVar, String str, int i, com.google.android.gms.internal.measurement.zzes zzesVar) {
        super(str, i);
        this.zza = zzzVar;
        this.zzh = zzesVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzx
    public final int zza() {
        return this.zzh.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzx
    public final boolean zzb() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzx
    public final boolean zzc() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean zzd(Long l, Long l2, com.google.android.gms.internal.measurement.zzgh zzghVar, boolean z) {
        zzoa.zzc();
        boolean zZzs = this.zza.zzs.zzf().zzs(this.zzb, zzdy.zzW);
        boolean zZzg = this.zzh.zzg();
        boolean zZzh = this.zzh.zzh();
        boolean zZzi = this.zzh.zzi();
        Object[] objArr = zZzg || zZzh || zZzi;
        Boolean boolZzj = null;
        boolZzj = null;
        boolZzj = null;
        boolZzj = null;
        boolZzj = null;
        if (z && objArr != true) {
            this.zza.zzs.zzay().zzj().zzc("Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.zzc), this.zzh.zzj() ? Integer.valueOf(this.zzh.zza()) : null);
            return true;
        }
        com.google.android.gms.internal.measurement.zzel zzelVarZzb = this.zzh.zzb();
        boolean zZzg2 = zzelVarZzb.zzg();
        if (zzghVar.zzr()) {
            if (zzelVarZzb.zzi()) {
                boolZzj = zzx.zzj(zzx.zzh(zzghVar.zzb(), zzelVarZzb.zzc()), zZzg2);
            } else {
                this.zza.zzs.zzay().zzk().zzb("No number filter for long property. property", this.zza.zzs.zzj().zzf(zzghVar.zzf()));
            }
        } else if (zzghVar.zzq()) {
            if (zzelVarZzb.zzi()) {
                boolZzj = zzx.zzj(zzx.zzg(zzghVar.zza(), zzelVarZzb.zzc()), zZzg2);
            } else {
                this.zza.zzs.zzay().zzk().zzb("No number filter for double property. property", this.zza.zzs.zzj().zzf(zzghVar.zzf()));
            }
        } else if (!zzghVar.zzt()) {
            this.zza.zzs.zzay().zzk().zzb("User property has no value, property", this.zza.zzs.zzj().zzf(zzghVar.zzf()));
        } else if (zzelVarZzb.zzk()) {
            boolZzj = zzx.zzj(zzx.zzf(zzghVar.zzg(), zzelVarZzb.zzd(), this.zza.zzs.zzay()), zZzg2);
        } else if (!zzelVarZzb.zzi()) {
            this.zza.zzs.zzay().zzk().zzb("No string or number filter defined. property", this.zza.zzs.zzj().zzf(zzghVar.zzf()));
        } else if (zzku.zzy(zzghVar.zzg())) {
            boolZzj = zzx.zzj(zzx.zzi(zzghVar.zzg(), zzelVarZzb.zzc()), zZzg2);
        } else {
            this.zza.zzs.zzay().zzk().zzc("Invalid user property value for Numeric number filter. property, value", this.zza.zzs.zzj().zzf(zzghVar.zzf()), zzghVar.zzg());
        }
        this.zza.zzs.zzay().zzj().zzb("Property filter result", boolZzj == null ? "null" : boolZzj);
        if (boolZzj == null) {
            return false;
        }
        this.zzd = true;
        if (zZzi && !boolZzj.booleanValue()) {
            return true;
        }
        if (!z || this.zzh.zzg()) {
            this.zze = boolZzj;
        }
        if (boolZzj.booleanValue() && objArr != false && zzghVar.zzs()) {
            long jZzc = zzghVar.zzc();
            if (l != null) {
                jZzc = l.longValue();
            }
            if (zZzs && this.zzh.zzg() && !this.zzh.zzh() && l2 != null) {
                jZzc = l2.longValue();
            }
            if (this.zzh.zzh()) {
                this.zzg = Long.valueOf(jZzc);
            } else {
                this.zzf = Long.valueOf(jZzc);
            }
        }
        return true;
    }
}
