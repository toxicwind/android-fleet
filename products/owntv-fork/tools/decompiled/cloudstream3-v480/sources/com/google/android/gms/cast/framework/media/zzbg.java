package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
abstract class zzbg extends BasePendingResult {
    private com.google.android.gms.cast.internal.zzat zza;
    private final boolean zzb;
    final /* synthetic */ RemoteMediaClient zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbg(RemoteMediaClient remoteMediaClient, boolean z) {
        super((GoogleApiClient) null);
        this.zzg = remoteMediaClient;
        this.zzb = z;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzbf(this, status);
    }

    abstract void zza() throws com.google.android.gms.cast.internal.zzap;

    final com.google.android.gms.cast.internal.zzat zzb() {
        if (this.zza == null) {
            this.zza = new zzbe(this);
        }
        return this.zza;
    }

    public final void zzc() {
        if (!this.zzb) {
            RemoteMediaClient remoteMediaClient = this.zzg;
            Iterator it = remoteMediaClient.zzi.iterator();
            while (it.hasNext()) {
                ((RemoteMediaClient.Listener) it.next()).onSendingRemoteMediaRequest();
            }
            Iterator it2 = remoteMediaClient.zzj.iterator();
            while (it2.hasNext()) {
                ((RemoteMediaClient.Callback) it2.next()).onSendingRemoteMediaRequest();
            }
        }
        try {
            synchronized (this.zzg.zzb) {
                zza();
            }
        } catch (com.google.android.gms.cast.internal.zzap unused) {
            setResult(new zzbf(this, new Status(2100)));
        }
    }
}
