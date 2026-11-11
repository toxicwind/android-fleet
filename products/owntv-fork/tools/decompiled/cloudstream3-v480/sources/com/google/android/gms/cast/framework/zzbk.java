package com.google.android.gms.cast.framework;

import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzbk extends zzbb {
    final /* synthetic */ SessionProvider zza;

    /* synthetic */ zzbk(SessionProvider sessionProvider, zzbl zzblVar) {
        this.zza = sessionProvider;
    }

    @Override // com.google.android.gms.cast.framework.zzbc
    public final IObjectWrapper zzb(String str) {
        Session sessionCreateSession = this.zza.createSession(str);
        if (sessionCreateSession == null) {
            return null;
        }
        return sessionCreateSession.zzn();
    }

    @Override // com.google.android.gms.cast.framework.zzbc
    public final String zzc() {
        return this.zza.getCategory();
    }

    @Override // com.google.android.gms.cast.framework.zzbc
    public final boolean zzd() {
        return this.zza.isSessionRecoverable();
    }
}
