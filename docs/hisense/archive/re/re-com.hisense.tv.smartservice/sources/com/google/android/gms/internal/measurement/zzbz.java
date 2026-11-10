package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.util.Log;
import com.hisense.support.tv.HisenseCountryLanInfo;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class zzbz extends zzce {
    public final AtomicReference<Bundle> zza = new AtomicReference<>();
    public boolean zzb;

    public static final <T> T zze(Bundle bundle, Class<T> cls) {
        Object obj;
        if (bundle == null || (obj = bundle.get("r")) == null) {
            return null;
        }
        try {
            return cls.cast(obj);
        } catch (ClassCastException e2) {
            Log.w(HisenseCountryLanInfo.LOCALE_ARM_COUNTRY, String.format("Unexpected object type. Expected, Received: %s, %s", cls.getCanonicalName(), obj.getClass().getCanonicalName()), e2);
            throw e2;
        }
    }

    public final Bundle zzb(long j) {
        Bundle bundle;
        synchronized (this.zza) {
            if (!this.zzb) {
                try {
                    this.zza.wait(j);
                } catch (InterruptedException unused) {
                    return null;
                }
            }
            bundle = this.zza.get();
        }
        return bundle;
    }

    public final String zzc(long j) {
        return (String) zze(zzb(j), String.class);
    }

    @Override // com.google.android.gms.internal.measurement.zzcf
    public final void zzd(Bundle bundle) {
        synchronized (this.zza) {
            try {
                this.zza.set(bundle);
                this.zzb = true;
                this.zza.notify();
            } catch (Throwable th) {
                this.zza.notify();
                throw th;
            }
        }
    }
}
