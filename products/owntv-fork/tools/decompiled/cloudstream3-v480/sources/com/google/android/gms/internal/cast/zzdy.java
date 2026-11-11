package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzdy extends zzed {
    final /* synthetic */ String zza;
    final /* synthetic */ zzef zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdy(zzef zzefVar, GoogleApiClient googleApiClient, String str) {
        super(zzefVar, googleApiClient);
        this.zza = str;
        this.zzb = zzefVar;
    }

    @Override // com.google.android.gms.internal.cast.zzed, com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzei) anyClient);
    }

    @Override // com.google.android.gms.internal.cast.zzed
    /* JADX INFO: renamed from: zza */
    public final void doExecute(zzei zzeiVar) throws RemoteException {
        zzeiVar.zzr(new zzeb(this, zzeiVar), this.zzb.zzd, this.zza);
    }
}
