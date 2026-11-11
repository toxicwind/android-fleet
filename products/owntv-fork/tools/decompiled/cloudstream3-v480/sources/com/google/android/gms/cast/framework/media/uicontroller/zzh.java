package com.google.android.gms.cast.framework.media.uicontroller;

import com.google.android.gms.cast.framework.media.widget.CastSeekBar;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzh extends com.google.android.gms.cast.framework.media.widget.zzd {
    final /* synthetic */ UIMediaController zza;

    zzh(UIMediaController uIMediaController) {
        this.zza = uIMediaController;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.zzd
    public final void zza(CastSeekBar castSeekBar, int i, boolean z) {
        this.zza.zzc(castSeekBar, i, z);
    }

    @Override // com.google.android.gms.cast.framework.media.widget.zzd
    public final void zzb(CastSeekBar castSeekBar) {
        this.zza.zzd(castSeekBar);
    }

    @Override // com.google.android.gms.cast.framework.media.widget.zzd
    public final void zzc(CastSeekBar castSeekBar) {
        this.zza.zze(castSeekBar);
    }
}
