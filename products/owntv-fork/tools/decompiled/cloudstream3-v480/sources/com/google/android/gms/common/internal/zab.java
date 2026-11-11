package com.google.android.gms.common.internal;

import j$.util.DesugarCollections;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zab {
    public final Set zaa;

    public zab(Set set) {
        Preconditions.checkNotNull(set);
        this.zaa = DesugarCollections.unmodifiableSet(set);
    }
}
