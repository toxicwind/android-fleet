package com.google.android.gms.cast.framework.media.internal;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzk implements zza {
    final /* synthetic */ zzm zza;
    final /* synthetic */ zzn zzb;

    zzk(zzn zznVar, zzm zzmVar) {
        this.zza = zzmVar;
        this.zzb = zznVar;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(Bitmap bitmap) {
        zzm zzmVar = this.zza;
        zzmVar.zzb = bitmap;
        zzn zznVar = this.zzb;
        zznVar.zzp = zzmVar;
        zznVar.zzg();
    }
}
