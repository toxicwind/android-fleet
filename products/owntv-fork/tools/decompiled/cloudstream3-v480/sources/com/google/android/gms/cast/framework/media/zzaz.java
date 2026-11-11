package com.google.android.gms.cast.framework.media;

import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzaz extends zzbg {
    final /* synthetic */ double zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaClient zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaz(RemoteMediaClient remoteMediaClient, double d, JSONObject jSONObject) {
        super(remoteMediaClient, false);
        this.zza = d;
        this.zzb = jSONObject;
        this.zzc = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbg
    protected final void zza() throws com.google.android.gms.cast.internal.zzap {
        this.zzc.zzd.zzE(zzb(), this.zza, this.zzb);
    }
}
