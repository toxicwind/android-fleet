package com.google.android.gms.cast.framework.media;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzaq extends zzbg {
    final /* synthetic */ int zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ int zzc;
    final /* synthetic */ RemoteMediaClient zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaq(RemoteMediaClient remoteMediaClient, boolean z, int i, int i2, int i3) {
        super(remoteMediaClient, true);
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbg
    protected final void zza() throws com.google.android.gms.cast.internal.zzap {
        this.zzd.zzd.zzt(zzb(), this.zza, this.zzb, this.zzc);
    }
}
