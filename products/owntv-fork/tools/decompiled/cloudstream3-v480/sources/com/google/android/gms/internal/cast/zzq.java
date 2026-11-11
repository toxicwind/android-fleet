package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.MediaStatus;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzq {
    private final int zza;

    public zzq(MediaStatus mediaStatus) {
        this.zza = mediaStatus.getPlayerState();
    }
}
