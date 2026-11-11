package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.Status;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzbe implements com.google.android.gms.cast.internal.zzat {
    final /* synthetic */ zzbg zza;

    zzbe(zzbg zzbgVar) {
        this.zza = zzbgVar;
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zza(String str, long j, int i, Object obj, long j2, long j3) {
        int i2;
        com.google.android.gms.cast.internal.zzaq zzaqVar = obj instanceof com.google.android.gms.cast.internal.zzaq ? (com.google.android.gms.cast.internal.zzaq) obj : null;
        try {
            i2 = i;
            try {
                this.zza.setResult(new zzbh(new Status(i2), zzaqVar != null ? zzaqVar.zza : null, zzaqVar != null ? zzaqVar.zzb : null));
            } catch (IllegalStateException e) {
                e = e;
                RemoteMediaClient.zza.e(e, "Result already set when calling onRequestCompleted", new Object[0]);
            }
        } catch (IllegalStateException e2) {
            e = e2;
            i2 = i;
        }
        Iterator it = this.zza.zzg.zzj.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zza(str, j, i2, j2, j3);
            i2 = i;
        }
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zzb(String str, long j, long j2, long j3) {
        try {
            zzbg zzbgVar = this.zza;
            zzbgVar.setResult(new zzbf(zzbgVar, new Status(2103)));
        } catch (IllegalStateException e) {
            RemoteMediaClient.zza.e(e, "Result already set when calling onRequestReplaced", new Object[0]);
        }
        Iterator it = this.zza.zzg.zzj.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zza(str, j, 2103, j2, j3);
        }
    }
}
