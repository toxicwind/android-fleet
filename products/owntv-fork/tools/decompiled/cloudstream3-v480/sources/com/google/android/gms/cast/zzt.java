package com.google.android.gms.cast;

import java.util.Collection;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzt {
    private String zza = "com.google.android.gms.cast.CATEGORY_CAST";
    private String zzb;
    private Collection zzc;

    private zzt() {
    }

    /* synthetic */ zzt(zzv zzvVar) {
    }

    static /* synthetic */ zzt zza(zzt zztVar, String str) {
        zztVar.zzb = str;
        return zztVar;
    }

    static /* synthetic */ zzt zzb(zzt zztVar, String str) {
        zztVar.zza = "com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK";
        return zztVar;
    }

    static /* synthetic */ zzt zzc(zzt zztVar, Collection collection) {
        zztVar.zzc = collection;
        return zztVar;
    }

    static /* bridge */ /* synthetic */ zzu zzd(zzt zztVar) {
        return new zzu(zztVar.zza, zztVar.zzb, zztVar.zzc, false, true, null);
    }
}
