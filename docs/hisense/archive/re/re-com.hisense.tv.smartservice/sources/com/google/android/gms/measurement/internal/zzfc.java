package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.MainThread;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class zzfc implements ServiceConnection {
    public final /* synthetic */ zzfd zza;
    public final String zzb;

    public zzfc(zzfd zzfdVar, String str) {
        this.zza = zzfdVar;
        this.zzb = str;
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            a.c(this.zza.zza, "Install Referrer connection returned with null binder");
            return;
        }
        try {
            com.google.android.gms.internal.measurement.zzbr zzbrVarZzb = com.google.android.gms.internal.measurement.zzbq.zzb(iBinder);
            if (zzbrVarZzb == null) {
                this.zza.zza.zzay().zzk().zza("Install Referrer Service implementation was not found");
            } else {
                this.zza.zza.zzay().zzj().zza("Install Referrer Service connected");
                this.zza.zza.zzaz().zzp(new zzfb(this, zzbrVarZzb, this));
            }
        } catch (RuntimeException e2) {
            this.zza.zza.zzay().zzk().zzb("Exception occurred while calling Install Referrer API", e2);
        }
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        a.b(this.zza.zza, "Install Referrer Service disconnected");
    }
}
