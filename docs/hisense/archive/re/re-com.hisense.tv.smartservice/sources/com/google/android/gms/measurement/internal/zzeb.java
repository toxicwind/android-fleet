package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IInterface;
import androidx.annotation.Nullable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface zzeb extends IInterface {
    @Nullable
    String zzd(zzp zzpVar);

    @Nullable
    List<zzkv> zze(zzp zzpVar, boolean z);

    List<zzab> zzf(@Nullable String str, @Nullable String str2, zzp zzpVar);

    List<zzab> zzg(String str, @Nullable String str2, @Nullable String str3);

    List<zzkv> zzh(@Nullable String str, @Nullable String str2, boolean z, zzp zzpVar);

    List<zzkv> zzi(String str, @Nullable String str2, @Nullable String str3, boolean z);

    void zzj(zzp zzpVar);

    void zzk(zzat zzatVar, zzp zzpVar);

    void zzl(zzat zzatVar, String str, @Nullable String str2);

    void zzm(zzp zzpVar);

    void zzn(zzab zzabVar, zzp zzpVar);

    void zzo(zzab zzabVar);

    void zzp(zzp zzpVar);

    void zzq(long j, @Nullable String str, @Nullable String str2, String str3);

    void zzr(Bundle bundle, zzp zzpVar);

    void zzs(zzp zzpVar);

    void zzt(zzkv zzkvVar, zzp zzpVar);

    @Nullable
    byte[] zzu(zzat zzatVar, String str);
}
