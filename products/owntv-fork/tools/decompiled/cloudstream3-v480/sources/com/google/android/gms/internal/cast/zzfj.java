package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Api;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzfj {

    @Deprecated
    public static final Api zza;
    private static final Api.ClientKey zzb;
    private static final Api.AbstractClientBuilder zzc;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zzb = clientKey;
        zzfh zzfhVar = new zzfh();
        zzc = zzfhVar;
        zza = new Api("UsageReporting.API", zzfhVar, clientKey);
    }
}
