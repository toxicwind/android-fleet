package com.google.android.gms.internal.cast;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public class zzvh extends zzvi {
    private final ListenableFuture zza;

    protected zzvh(ListenableFuture listenableFuture) {
        this.zza = listenableFuture;
    }

    @Override // com.google.android.gms.internal.cast.zzvg, com.google.android.gms.internal.cast.zzgy
    protected final /* synthetic */ Object zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.cast.zzvi, com.google.android.gms.internal.cast.zzvg
    protected final /* synthetic */ Future zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.cast.zzvi
    protected final ListenableFuture zzc() {
        return this.zza;
    }
}
