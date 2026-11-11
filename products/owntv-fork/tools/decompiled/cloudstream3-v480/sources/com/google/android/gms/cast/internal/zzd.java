package com.google.android.gms.cast.internal;

import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public class zzd extends zzq {
    private final List zzb;

    public zzd(String str, String str2, String str3) {
        super(str, "MediaControlChannel", null);
        this.zzb = DesugarCollections.synchronizedList(new ArrayList());
    }

    protected final List zza() {
        return this.zzb;
    }

    protected final void zzb() {
        List list = this.zzb;
        synchronized (list) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((zzav) it.next()).zzc(2002);
            }
        }
    }

    protected final void zzc(zzav zzavVar) {
        this.zzb.add(zzavVar);
    }
}
