package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
public final class zzet extends BroadcastReceiver {

    @VisibleForTesting
    public static final String zza = "com.google.android.gms.measurement.internal.zzet";
    public final zzks zzb;
    public boolean zzc;
    public boolean zzd;

    public zzet(zzks zzksVar) {
        Preconditions.checkNotNull(zzksVar);
        this.zzb = zzksVar;
    }

    @Override // android.content.BroadcastReceiver
    @MainThread
    public final void onReceive(Context context, Intent intent) {
        this.zzb.zzB();
        String action = intent.getAction();
        this.zzb.zzay().zzj().zzb("NetworkBroadcastReceiver received action", action);
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            this.zzb.zzay().zzk().zzb("NetworkBroadcastReceiver received unknown action", action);
            return;
        }
        boolean zZza = this.zzb.zzl().zza();
        if (this.zzd != zZza) {
            this.zzd = zZza;
            this.zzb.zzaz().zzp(new zzes(this, zZza));
        }
    }

    @WorkerThread
    public final void zzb() {
        this.zzb.zzB();
        this.zzb.zzaz().zzg();
        if (this.zzc) {
            return;
        }
        this.zzb.zzau().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.zzd = this.zzb.zzl().zza();
        this.zzb.zzay().zzj().zzb("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzd));
        this.zzc = true;
    }

    @WorkerThread
    public final void zzc() {
        this.zzb.zzB();
        this.zzb.zzaz().zzg();
        this.zzb.zzaz().zzg();
        if (this.zzc) {
            this.zzb.zzay().zzj().zza("Unregistering connectivity change receiver");
            this.zzc = false;
            this.zzd = false;
            try {
                this.zzb.zzau().unregisterReceiver(this);
            } catch (IllegalArgumentException e2) {
                this.zzb.zzay().zzd().zzb("Failed to unregister the network broadcast receiver", e2);
            }
        }
    }
}
