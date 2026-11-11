package com.google.android.gms.cast.framework.media;

import android.content.DialogInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzbn implements DialogInterface.OnClickListener {
    final /* synthetic */ zzbq zza;
    final /* synthetic */ zzbq zzb;
    final /* synthetic */ TracksChooserDialogFragment zzc;

    zzbn(TracksChooserDialogFragment tracksChooserDialogFragment, zzbq zzbqVar, zzbq zzbqVar2) {
        this.zza = zzbqVar;
        this.zzb = zzbqVar2;
        this.zzc = tracksChooserDialogFragment;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        TracksChooserDialogFragment.zzc(this.zzc, this.zza, this.zzb);
    }
}
