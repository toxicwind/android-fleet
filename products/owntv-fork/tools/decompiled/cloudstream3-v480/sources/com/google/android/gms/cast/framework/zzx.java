package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzbr;
import com.google.android.gms.cast.zzbs;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzx extends zzaf {
    final /* synthetic */ CastSession zza;

    /* synthetic */ zzx(CastSession castSession, zzaa zzaaVar) {
        this.zza = castSession;
    }

    @Override // com.google.android.gms.cast.framework.zzag
    public final void zzb(int i) {
        CastSession.zzh(this.zza, i);
    }

    @Override // com.google.android.gms.cast.framework.zzag
    public final void zzc(final String str, final String str2) {
        CastSession castSession = this.zza;
        if (castSession.zzi == null || !castSession.zzi.zzl()) {
            return;
        }
        final zzbr zzbrVar = (zzbr) castSession.zzi;
        final zzbs zzbsVar = null;
        zzbrVar.doWrite(TaskApiCall.builder().run(new RemoteCall(str, str2, zzbsVar) { // from class: com.google.android.gms.cast.zzbb
            public final /* synthetic */ String zzb;
            public final /* synthetic */ String zzc;

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zzbr.zzB(this.zza, this.zzb, this.zzc, null, (com.google.android.gms.cast.internal.zzy) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(8407).build()).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.gms.cast.framework.zzw
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                CastSession.zzi(this.zza.zza, "joinApplication", task);
            }
        });
    }

    @Override // com.google.android.gms.cast.framework.zzag
    public final void zzd(final String str, final LaunchOptions launchOptions) {
        CastSession castSession = this.zza;
        if (castSession.zzi == null || !castSession.zzi.zzl()) {
            return;
        }
        final zzbr zzbrVar = (zzbr) castSession.zzi;
        zzbrVar.doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzaw
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zzbr.zzv(zzbrVar, str, launchOptions, (com.google.android.gms.cast.internal.zzy) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(8406).build()).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.gms.cast.framework.zzv
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                CastSession.zzi(this.zza.zza, "launchApplication", task);
            }
        });
    }

    @Override // com.google.android.gms.cast.framework.zzag
    public final void zze(final String str) {
        CastSession castSession = this.zza;
        if (castSession.zzi == null || !castSession.zzi.zzl()) {
            return;
        }
        final zzbr zzbrVar = (zzbr) castSession.zzi;
        zzbrVar.doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzbh
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zzbr.zzx(zzbrVar, str, (com.google.android.gms.cast.internal.zzy) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(8409).build());
    }
}
