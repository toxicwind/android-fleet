package com.google.android.gms.internal.cast;

import j$.util.DesugarCollections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzyy extends zzzd {
    zzyy() {
        super(null);
    }

    @Override // com.google.android.gms.internal.cast.zzzd
    public final void zza() {
        if (!zzj()) {
            for (int i = 0; i < zzc(); i++) {
                Map.Entry entryZzg = zzg(i);
                if (((zzxa) ((zzyz) entryZzg).zza()).zze()) {
                    entryZzg.setValue(DesugarCollections.unmodifiableList((List) entryZzg.getValue()));
                }
            }
            for (Map.Entry entry : zzd()) {
                if (((zzxa) entry.getKey()).zze()) {
                    entry.setValue(DesugarCollections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
