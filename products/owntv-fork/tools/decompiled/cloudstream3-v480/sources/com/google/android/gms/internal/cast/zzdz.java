package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzdz extends zzed {
    zzdz(zzef zzefVar, GoogleApiClient googleApiClient) {
        super(zzefVar, googleApiClient);
    }

    @Override // com.google.android.gms.internal.cast.zzed, com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzei) anyClient);
    }

    @Override // com.google.android.gms.internal.cast.zzed
    /* JADX INFO: renamed from: zza */
    public final void doExecute(zzei zzeiVar) throws RemoteException {
        zzeiVar.zzs(new zzec(this));
    }
}
