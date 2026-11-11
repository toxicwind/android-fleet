package com.google.android.gms.flags;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: compiled from: com.google.android.gms:play-services-flags@@17.0.1 */
/* JADX INFO: loaded from: classes5.dex */
public class FlagRegistry {
    private final Collection<Flag> zza = new ArrayList();
    private final Collection<Flag.StringFlag> zzb = new ArrayList();
    private final Collection<Flag.StringFlag> zzc = new ArrayList();

    public static void initialize(Context context) {
        Singletons.zza().zzb(context);
    }

    public final void zza(Flag flag) {
        this.zza.add(flag);
    }
}
