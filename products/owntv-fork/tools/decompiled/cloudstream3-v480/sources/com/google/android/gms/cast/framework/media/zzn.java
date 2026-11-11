package com.google.android.gms.cast.framework.media;

import android.util.LruCache;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzn extends LruCache {
    final /* synthetic */ MediaQueue zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzn(MediaQueue mediaQueue, int i) {
        super(i);
        this.zza = mediaQueue;
    }

    @Override // android.util.LruCache
    protected final /* bridge */ /* synthetic */ void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
        Integer num = (Integer) obj;
        if (z) {
            List list = this.zza.zze;
            Preconditions.checkNotNull(list);
            list.add(num);
        }
    }
}
