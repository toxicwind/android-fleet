package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzu implements SessionManagerListener {
    final /* synthetic */ zzx zza;

    public zzu(zzx zzxVar) {
        this.zza = zzxVar;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionEnded(Session session, int i) {
        zzca zzcaVar = new zzca(9);
        zzcaVar.zzb(Integer.valueOf(i));
        zzx zzxVar = this.zza;
        zzcaVar.zza(Boolean.valueOf(zzxVar.zzb.zzf()));
        zzx.zzf(zzxVar, new zzcb(zzcaVar));
        zzxVar.zzh();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionEnding(Session session) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumeFailed(Session session, int i) {
        zzca zzcaVar = new zzca(8);
        zzcaVar.zzb(Integer.valueOf(i));
        zzcb zzcbVar = new zzcb(zzcaVar);
        zzx zzxVar = this.zza;
        zzx.zzf(zzxVar, zzcbVar);
        zzxVar.zzh();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumed(Session session, boolean z) {
        zzcb zzcbVar = new zzcb(new zzca(4));
        zzx zzxVar = this.zza;
        zzx.zzf(zzxVar, zzcbVar);
        ((zzz) Preconditions.checkNotNull(zzxVar.zzd)).zzg((CastSession) session);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResuming(Session session, String str) {
        zzcb zzcbVar = new zzcb(new zzca(7));
        zzx zzxVar = this.zza;
        zzx.zzf(zzxVar, zzcbVar);
        ((zzz) Preconditions.checkNotNull(zzxVar.zzd)).zzg((CastSession) session);
        ((zzz) Preconditions.checkNotNull(zzxVar.zzd)).zzh(str);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStartFailed(Session session, int i) {
        zzca zzcaVar = new zzca(5);
        zzcaVar.zzb(Integer.valueOf(i));
        zzcb zzcbVar = new zzcb(zzcaVar);
        zzx zzxVar = this.zza;
        zzx.zzf(zzxVar, zzcbVar);
        zzxVar.zzh();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarted(Session session, String str) {
        zzcb zzcbVar = new zzcb(new zzca(4));
        zzx zzxVar = this.zza;
        zzx.zzf(zzxVar, zzcbVar);
        ((zzz) Preconditions.checkNotNull(zzxVar.zzd)).zzg((CastSession) session);
        ((zzz) Preconditions.checkNotNull(zzxVar.zzd)).zzh(str);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarting(Session session) {
        CastSession castSession = (CastSession) session;
        zzca zzcaVar = new zzca(2);
        zzx zzxVar = this.zza;
        zzcaVar.zza(Boolean.valueOf(zzxVar.zzb.zzf()));
        zzx.zzf(zzxVar, new zzcb(zzcaVar));
        ((zzz) Preconditions.checkNotNull(zzxVar.zzd)).zzg(castSession);
        castSession.zzj(zzxVar.zze);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionSuspended(Session session, int i) {
        zzca zzcaVar = new zzca(6);
        zzcaVar.zzb(Integer.valueOf(i));
        zzcb zzcbVar = new zzcb(zzcaVar);
        zzx zzxVar = this.zza;
        zzx.zzf(zzxVar, zzcbVar);
        ((zzz) Preconditions.checkNotNull(zzxVar.zzd)).zzg((CastSession) session);
    }
}
