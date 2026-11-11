package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.internal.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzbl implements SessionManagerListener {
    final /* synthetic */ zzbn zza;

    /* synthetic */ zzbl(zzbn zzbnVar, zzbm zzbmVar) {
        this.zza = zzbnVar;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionEnded(Session session, int i) {
        zzbn.zza.d("onSessionEnded with error = %d", Integer.valueOf(i));
        zzbn zzbnVar = this.zza;
        zzbn.zzg(zzbnVar);
        if (zzbnVar.zzf == 2) {
            return;
        }
        zzbnVar.zzs();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionEnding(Session session) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumeFailed(Session session, int i) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumed(Session session, boolean z) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResuming(Session session, String str) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStartFailed(Session session, int i) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarted(Session session, String str) {
        Logger logger = zzbn.zza;
        zzbn zzbnVar = this.zza;
        logger.d("onSessionStarted with transferType = %d", Integer.valueOf(zzbnVar.zzf));
        if (zzbnVar.zzp() && zzbnVar.zzf == 2) {
            zzbn.zzi(zzbnVar);
        }
        zzbnVar.zzs();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarting(Session session) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionSuspended(Session session, int i) {
    }
}
