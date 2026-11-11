package com.google.android.gms.cast;

import android.util.Log;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzdl implements com.google.android.gms.cast.internal.zzat {
    final /* synthetic */ zzdn zza;

    zzdl(zzdn zzdnVar) {
        this.zza = zzdnVar;
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zza(String str, long j, int i, Object obj, long j2, long j3) {
        com.google.android.gms.cast.internal.zzaq zzaqVar = obj instanceof com.google.android.gms.cast.internal.zzaq ? (com.google.android.gms.cast.internal.zzaq) obj : null;
        try {
            this.zza.setResult(new zzdo(new Status(i), zzaqVar != null ? zzaqVar.zza : null));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestCompleted", e);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zzb(String str, long j, long j2, long j3) {
        try {
            zzdn zzdnVar = this.zza;
            zzdnVar.setResult(new zzdm(zzdnVar, new Status(2103)));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestReplaced", e);
        }
    }
}
