package com.google.android.gms.cast.framework.media;

import android.content.DialogInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzbm implements DialogInterface.OnClickListener {
    final /* synthetic */ TracksChooserDialogFragment zza;

    zzbm(TracksChooserDialogFragment tracksChooserDialogFragment) {
        this.zza = tracksChooserDialogFragment;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        TracksChooserDialogFragment tracksChooserDialogFragment = this.zza;
        if (tracksChooserDialogFragment.zze != null) {
            tracksChooserDialogFragment.zze.cancel();
            tracksChooserDialogFragment.zze = null;
        }
    }
}
