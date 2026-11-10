package com.google.android.gms.common.internal;

import android.util.Log;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzc<TListener> {

    @Nullable
    public TListener zza;
    public boolean zzb = false;
    public final /* synthetic */ BaseGmsClient zzd;

    public zzc(BaseGmsClient baseGmsClient, TListener tlistener) {
        this.zzd = baseGmsClient;
        this.zza = tlistener;
    }

    public abstract void zza(TListener tlistener);

    public abstract void zzc();

    public final void zze() {
        TListener tlistener;
        synchronized (this) {
            tlistener = this.zza;
            if (this.zzb) {
                String string = toString();
                StringBuilder sb = new StringBuilder(string.length() + 47);
                sb.append("Callback proxy ");
                sb.append(string);
                sb.append(" being reused. This is not safe.");
                Log.w("GmsClient", sb.toString());
            }
        }
        if (tlistener != null) {
            try {
                zza(tlistener);
            } catch (RuntimeException e2) {
                throw e2;
            }
        }
        synchronized (this) {
            this.zzb = true;
        }
        zzg();
    }

    public final void zzf() {
        synchronized (this) {
            this.zza = null;
        }
    }

    public final void zzg() {
        zzf();
        synchronized (this.zzd.zzt) {
            this.zzd.zzt.remove(this);
        }
    }
}
