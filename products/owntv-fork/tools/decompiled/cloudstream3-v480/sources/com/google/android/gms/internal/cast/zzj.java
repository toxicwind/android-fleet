package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzj implements SessionManagerListener {
    final /* synthetic */ zzl zza;

    public zzj(zzl zzlVar) {
        this.zza = zzlVar;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionEnded(Session session, int i) {
        zzl zzlVar = this.zza;
        zzlVar.zzi = (CastSession) session;
        zzl.zzm(zzlVar, i);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* synthetic */ void onSessionEnding(Session session) {
        this.zza.zzi = (CastSession) session;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumeFailed(Session session, int i) {
        zzl zzlVar = this.zza;
        zzlVar.zzi = (CastSession) session;
        zzl.zzm(zzlVar, i);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumed(Session session, boolean z) {
        zzl.zza.d("onSessionResumed with wasSuspended = %b", Boolean.valueOf(z));
        zzl zzlVar = this.zza;
        zzlVar.zzi = (CastSession) session;
        zzlVar.zzs();
        Preconditions.checkNotNull(zzlVar.zzh);
        zzlVar.zzb.zzf(zzlVar.zzd.zzb(zzlVar.zzh, z), 227);
        zzlVar.zzh.zzc(zzlVar.zzg);
        zzlVar.zzu();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResuming(Session session, String str) {
        zzl.zza.d("onSessionResuming with sessionId = %s", str);
        zzl zzlVar = this.zza;
        zzlVar.zzi = (CastSession) session;
        zzl.zzn(zzlVar, zzlVar.zzg, str);
        Preconditions.checkNotNull(zzlVar.zzh);
        zzlVar.zzb.zzf(zzlVar.zzd.zzc(zzlVar.zzh), 226);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStartFailed(Session session, int i) {
        zzl zzlVar = this.zza;
        zzlVar.zzi = (CastSession) session;
        zzl.zzm(zzlVar, i);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarted(Session session, String str) {
        zzl.zza.d("onSessionStarted with sessionId = %s", str);
        zzl zzlVar = this.zza;
        zzlVar.zzi = (CastSession) session;
        zzlVar.zzs();
        zzlVar.zzh.zzf = str;
        zzlVar.zzb.zzf(zzlVar.zzd.zza(zzlVar.zzh), 222);
        zzlVar.zzh.zzc(zzlVar.zzg);
        zzlVar.zzu();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarting(Session session) {
        zzl.zza.d("onSessionStarting", new Object[0]);
        zzl zzlVar = this.zza;
        zzlVar.zzi = (CastSession) session;
        if (zzlVar.zzh != null) {
            zzl.zza.w("Start a session while there's already an active session. Create a new one.", new Object[0]);
        }
        zzlVar.zzt();
        zzlVar.zzb.zzf(zzlVar.zzd.zzd(zzlVar.zzh), 221);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionSuspended(Session session, int i) {
        zzl.zza.d("onSessionSuspended with reason = %d", Integer.valueOf(i));
        zzl zzlVar = this.zza;
        zzlVar.zzi = (CastSession) session;
        zzlVar.zzs();
        Preconditions.checkNotNull(zzlVar.zzh);
        zzlVar.zzb.zzf(zzlVar.zzd.zze(zzlVar.zzh, i), 225);
        zzlVar.zzh.zzc(zzlVar.zzg);
        zzlVar.zzr();
    }
}
