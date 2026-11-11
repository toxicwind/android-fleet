package com.google.android.gms.cast.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.ComplianceOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzn extends GoogleApi {
    private static final Api.ClientKey zza;
    private static final Api.AbstractClientBuilder zzb;
    private static final Api zzc;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zza = clientKey;
        zzi zziVar = new zzi();
        zzb = zziVar;
        zzc = new Api("CastApi.API", zziVar, clientKey);
    }

    public zzn(Context context) {
        super(context, (Api<Api.ApiOptions.NoOptions>) zzc, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task zza(final String[] strArr) {
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.internal.zzg
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zzo zzoVar = (zzo) obj;
                zzk zzkVar = new zzk(this.zza, (TaskCompletionSource) obj2);
                ComplianceOptions complianceOptionsBuild = ComplianceOptions.newBuilder(zzoVar.getContext()).build();
                ((zzak) zzoVar.getService()).zzf(zzkVar, strArr, ApiMetadata.fromComplianceOptions(complianceOptionsBuild));
            }
        }).setFeatures(com.google.android.gms.cast.zzav.zzd).setAutoResolveMissingFeatures(false).setMethodKey(8425).build());
    }
}
