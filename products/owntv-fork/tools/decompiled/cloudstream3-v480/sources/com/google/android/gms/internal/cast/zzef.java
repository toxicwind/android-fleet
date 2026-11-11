package com.google.android.gms.internal.cast;

import android.hardware.display.VirtualDisplay;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.cast.CastRemoteDisplayApi;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public final class zzef implements CastRemoteDisplayApi {
    private static final Logger zza = new Logger("CastRemoteDisplayApiImpl");
    private final Api zzb;
    private VirtualDisplay zzc;
    private final zzen zzd = new zzdx(this);

    public zzef(Api api) {
        this.zzb = api;
    }

    static /* bridge */ /* synthetic */ void zzf(zzef zzefVar) {
        VirtualDisplay virtualDisplay = zzefVar.zzc;
        if (virtualDisplay != null) {
            if (virtualDisplay.getDisplay() != null) {
                zza.d("releasing virtual display: " + virtualDisplay.getDisplay().getDisplayId(), new Object[0]);
            }
            virtualDisplay.release();
        }
        zzefVar.zzc = null;
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplayApi
    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> startRemoteDisplay(GoogleApiClient googleApiClient, String str) {
        zza.d("startRemoteDisplay", new Object[0]);
        return googleApiClient.execute(new zzdy(this, googleApiClient, str));
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplayApi
    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> stopRemoteDisplay(GoogleApiClient googleApiClient) {
        zza.d("stopRemoteDisplay", new Object[0]);
        return googleApiClient.execute(new zzdz(this, googleApiClient));
    }
}
