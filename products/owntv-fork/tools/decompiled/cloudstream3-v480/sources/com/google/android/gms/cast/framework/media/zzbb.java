package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import java.util.concurrent.atomic.AtomicLong;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzbb implements com.google.android.gms.cast.internal.zzas {
    final /* synthetic */ RemoteMediaClient zza;
    private com.google.android.gms.cast.zzr zzb;
    private final AtomicLong zzc = new AtomicLong((CastUtils.zza() & WebSocketProtocol.PAYLOAD_SHORT_MAX) * 10000);

    public zzbb(RemoteMediaClient remoteMediaClient) {
        this.zza = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.internal.zzas
    public final long zza() {
        return this.zzc.getAndIncrement();
    }

    @Override // com.google.android.gms.cast.internal.zzas
    public final void zzb(String str, String str2, final long j, String str3) {
        com.google.android.gms.cast.zzr zzrVar = this.zzb;
        if (zzrVar == null) {
            throw new IllegalStateException("Device is not connected");
        }
        zzrVar.zzh(str, str2).addOnFailureListener(new OnFailureListener() { // from class: com.google.android.gms.cast.framework.media.zzba
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                this.zza.zza.zzd.zzP(j, exc instanceof ApiException ? ((ApiException) exc).getStatusCode() : 13);
            }
        });
    }

    public final void zzc(com.google.android.gms.cast.zzr zzrVar) {
        this.zzb = zzrVar;
    }
}
