package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzg {
    public long zzA;
    public long zzB;

    @Nullable
    public String zzC;
    public boolean zzD;
    public long zzE;
    public long zzF;
    public final zzfv zza;
    public final String zzb;

    @Nullable
    public String zzc;

    @Nullable
    public String zzd;

    @Nullable
    public String zze;

    @Nullable
    public String zzf;
    public long zzg;
    public long zzh;
    public long zzi;

    @Nullable
    public String zzj;
    public long zzk;

    @Nullable
    public String zzl;
    public long zzm;
    public long zzn;
    public boolean zzo;
    public long zzp;
    public boolean zzq;

    @Nullable
    public String zzr;

    @Nullable
    public Boolean zzs;
    public long zzt;

    @Nullable
    public List<String> zzu;

    @Nullable
    public String zzv;
    public long zzw;
    public long zzx;
    public long zzy;
    public long zzz;

    @WorkerThread
    public zzg(zzfv zzfvVar, String str) {
        Preconditions.checkNotNull(zzfvVar);
        Preconditions.checkNotEmpty(str);
        this.zza = zzfvVar;
        this.zzb = str;
        this.zza.zzaz().zzg();
    }

    @Nullable
    @WorkerThread
    public final String zzA() {
        this.zza.zzaz().zzg();
        return this.zzC;
    }

    @Nullable
    @WorkerThread
    public final String zzB() {
        this.zza.zzaz().zzg();
        return this.zze;
    }

    @Nullable
    @WorkerThread
    public final List<String> zzC() {
        this.zza.zzaz().zzg();
        return this.zzu;
    }

    @WorkerThread
    public final void zzD() {
        this.zza.zzaz().zzg();
        this.zzD = false;
    }

    @WorkerThread
    public final void zzE() {
        this.zza.zzaz().zzg();
        long j = this.zzg + 1;
        if (j > 2147483647L) {
            this.zza.zzay().zzk().zzb("Bundle index overflow. appId", zzel.zzn(this.zzb));
            j = 0;
        }
        this.zzD = true;
        this.zzg = j;
    }

    @WorkerThread
    public final void zzF(@Nullable String str) {
        this.zza.zzaz().zzg();
        if (true == TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzD |= true ^ zzkz.zzak(this.zzr, str);
        this.zzr = str;
    }

    @WorkerThread
    public final void zzG(boolean z) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzq != z;
        this.zzq = z;
    }

    @WorkerThread
    public final void zzH(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzp != j;
        this.zzp = j;
    }

    @WorkerThread
    public final void zzI(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzkz.zzak(this.zzc, str);
        this.zzc = str;
    }

    @WorkerThread
    public final void zzJ(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzkz.zzak(this.zzl, str);
        this.zzl = str;
    }

    @WorkerThread
    public final void zzK(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzkz.zzak(this.zzj, str);
        this.zzj = str;
    }

    @WorkerThread
    public final void zzL(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzk != j;
        this.zzk = j;
    }

    @WorkerThread
    public final void zzM(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzE != j;
        this.zzE = j;
    }

    @WorkerThread
    public final void zzN(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzz != j;
        this.zzz = j;
    }

    @WorkerThread
    public final void zzO(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzA != j;
        this.zzA = j;
    }

    @WorkerThread
    public final void zzP(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzy != j;
        this.zzy = j;
    }

    @WorkerThread
    public final void zzQ(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzx != j;
        this.zzx = j;
    }

    @WorkerThread
    public final void zzR(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzB != j;
        this.zzB = j;
    }

    @WorkerThread
    public final void zzS(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzw != j;
        this.zzw = j;
    }

    @WorkerThread
    public final void zzT(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzn != j;
        this.zzn = j;
    }

    @WorkerThread
    public final void zzU(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzt != j;
        this.zzt = j;
    }

    @WorkerThread
    public final void zzV(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzF != j;
        this.zzF = j;
    }

    @WorkerThread
    public final void zzW(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzkz.zzak(this.zzf, str);
        this.zzf = str;
    }

    @WorkerThread
    public final void zzX(@Nullable String str) {
        this.zza.zzaz().zzg();
        if (true == TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzD |= true ^ zzkz.zzak(this.zzv, str);
        this.zzv = str;
    }

    @WorkerThread
    public final void zzY(@Nullable String str) {
        this.zza.zzaz().zzg();
        if (true == TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzD |= true ^ zzkz.zzak(this.zzd, str);
        this.zzd = str;
    }

    @WorkerThread
    public final void zzZ(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzm != j;
        this.zzm = j;
    }

    @WorkerThread
    public final long zza() {
        this.zza.zzaz().zzg();
        return this.zzp;
    }

    @WorkerThread
    public final void zzaa(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzkz.zzak(this.zzC, str);
        this.zzC = str;
    }

    @WorkerThread
    public final void zzab(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzi != j;
        this.zzi = j;
    }

    @WorkerThread
    public final void zzac(long j) {
        Preconditions.checkArgument(j >= 0);
        this.zza.zzaz().zzg();
        this.zzD |= this.zzg != j;
        this.zzg = j;
    }

    @WorkerThread
    public final void zzad(long j) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzh != j;
        this.zzh = j;
    }

    @WorkerThread
    public final void zzae(boolean z) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzo != z;
        this.zzo = z;
    }

    @WorkerThread
    public final void zzaf(@Nullable Boolean bool) {
        boolean zEquals;
        this.zza.zzaz().zzg();
        boolean z = this.zzD;
        Boolean bool2 = this.zzs;
        int i = zzkz.zza;
        if (bool2 == null && bool == null) {
            zEquals = true;
        } else {
            zEquals = bool2 == null ? false : bool2.equals(bool);
        }
        this.zzD = z | (!zEquals);
        this.zzs = bool;
    }

    @WorkerThread
    public final void zzag(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzkz.zzak(this.zze, str);
        this.zze = str;
    }

    @WorkerThread
    public final void zzah(@Nullable List<String> list) {
        this.zza.zzaz().zzg();
        List<String> list2 = this.zzu;
        int i = zzkz.zza;
        if (list2 == null && list == null) {
            return;
        }
        if (list2 != null && list2.equals(list)) {
            return;
        }
        this.zzD = true;
        this.zzu = list != null ? new ArrayList(list) : null;
    }

    @WorkerThread
    public final boolean zzai() {
        this.zza.zzaz().zzg();
        return this.zzq;
    }

    @WorkerThread
    public final boolean zzaj() {
        this.zza.zzaz().zzg();
        return this.zzo;
    }

    @WorkerThread
    public final boolean zzak() {
        this.zza.zzaz().zzg();
        return this.zzD;
    }

    @WorkerThread
    public final long zzb() {
        this.zza.zzaz().zzg();
        return this.zzk;
    }

    @WorkerThread
    public final long zzc() {
        this.zza.zzaz().zzg();
        return this.zzE;
    }

    @WorkerThread
    public final long zzd() {
        this.zza.zzaz().zzg();
        return this.zzz;
    }

    @WorkerThread
    public final long zze() {
        this.zza.zzaz().zzg();
        return this.zzA;
    }

    @WorkerThread
    public final long zzf() {
        this.zza.zzaz().zzg();
        return this.zzy;
    }

    @WorkerThread
    public final long zzg() {
        this.zza.zzaz().zzg();
        return this.zzx;
    }

    @WorkerThread
    public final long zzh() {
        this.zza.zzaz().zzg();
        return this.zzB;
    }

    @WorkerThread
    public final long zzi() {
        this.zza.zzaz().zzg();
        return this.zzw;
    }

    @WorkerThread
    public final long zzj() {
        this.zza.zzaz().zzg();
        return this.zzn;
    }

    @WorkerThread
    public final long zzk() {
        this.zza.zzaz().zzg();
        return this.zzt;
    }

    @WorkerThread
    public final long zzl() {
        this.zza.zzaz().zzg();
        return this.zzF;
    }

    @WorkerThread
    public final long zzm() {
        this.zza.zzaz().zzg();
        return this.zzm;
    }

    @WorkerThread
    public final long zzn() {
        this.zza.zzaz().zzg();
        return this.zzi;
    }

    @WorkerThread
    public final long zzo() {
        this.zza.zzaz().zzg();
        return this.zzg;
    }

    @WorkerThread
    public final long zzp() {
        this.zza.zzaz().zzg();
        return this.zzh;
    }

    @Nullable
    @WorkerThread
    public final Boolean zzq() {
        this.zza.zzaz().zzg();
        return this.zzs;
    }

    @Nullable
    @WorkerThread
    public final String zzr() {
        this.zza.zzaz().zzg();
        return this.zzr;
    }

    @Nullable
    @WorkerThread
    public final String zzs() {
        this.zza.zzaz().zzg();
        String str = this.zzC;
        zzaa(null);
        return str;
    }

    @WorkerThread
    public final String zzt() {
        this.zza.zzaz().zzg();
        return this.zzb;
    }

    @Nullable
    @WorkerThread
    public final String zzu() {
        this.zza.zzaz().zzg();
        return this.zzc;
    }

    @Nullable
    @WorkerThread
    public final String zzv() {
        this.zza.zzaz().zzg();
        return this.zzl;
    }

    @Nullable
    @WorkerThread
    public final String zzw() {
        this.zza.zzaz().zzg();
        return this.zzj;
    }

    @Nullable
    @WorkerThread
    public final String zzx() {
        this.zza.zzaz().zzg();
        return this.zzf;
    }

    @Nullable
    @WorkerThread
    public final String zzy() {
        this.zza.zzaz().zzg();
        return this.zzv;
    }

    @Nullable
    @WorkerThread
    public final String zzz() {
        this.zza.zzaz().zzg();
        return this.zzd;
    }
}
