package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.SessionState;
import com.google.android.gms.cast.framework.SessionTransferCallback;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzk extends SessionTransferCallback {
    final /* synthetic */ zzl zza;

    public zzk(zzl zzlVar) {
        this.zza = zzlVar;
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferFailed(int i, int i2) {
        zzl.zza.d("onTransferFailed with type = %d and reason = %d", Integer.valueOf(i), Integer.valueOf(i2));
        zzl zzlVar = this.zza;
        zzlVar.zzs();
        zzlVar.zzb.zzf(zzlVar.zzd.zzf(zzlVar.zzh, i, i2), 232);
        zzlVar.zzj = false;
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferred(int i, SessionState sessionState) {
        zzl.zza.d("onTransferred with type = %d", Integer.valueOf(i));
        zzl zzlVar = this.zza;
        zzlVar.zzs();
        zzlVar.zzb.zzf(zzlVar.zzd.zzg(zzlVar.zzh, i), 231);
        zzlVar.zzj = false;
        zzlVar.zzh = null;
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferring(int i) {
        zzl.zza.d("onTransferring with type = %d", Integer.valueOf(i));
        zzl zzlVar = this.zza;
        zzlVar.zzj = true;
        zzlVar.zzs();
        zzlVar.zzb.zzf(zzlVar.zzd.zzg(zzlVar.zzh, i), 230);
    }
}
