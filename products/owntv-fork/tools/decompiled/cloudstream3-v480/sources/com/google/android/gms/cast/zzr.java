package com.google.android.gms.cast;

import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public interface zzr extends HasApiKey {
    double zza();

    int zzb();

    int zzc();

    ApplicationMetadata zzd();

    Task zze();

    Task zzf();

    Task zzg(String str);

    Task zzh(String str, String str2);

    Task zzi(String str, Cast.MessageReceivedCallback messageReceivedCallback);

    String zzj();

    void zzk(zzq zzqVar);

    boolean zzl();

    boolean zzm();
}
