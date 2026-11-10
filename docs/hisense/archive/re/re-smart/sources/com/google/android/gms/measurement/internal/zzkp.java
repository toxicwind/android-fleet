package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class zzkp implements zzky {
    public final /* synthetic */ zzks zza;

    public zzkp(zzks zzksVar) {
        this.zza = zzksVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzky
    public final void zza(String str, String str2, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.zza.zzaz().zzp(new zzko(this, str, "_err", bundle));
            return;
        }
        zzfv zzfvVar = this.zza.zzn;
        if (zzfvVar != null) {
            a.a(zzfvVar, "AppId not known when logging event", "_err");
        }
    }
}
