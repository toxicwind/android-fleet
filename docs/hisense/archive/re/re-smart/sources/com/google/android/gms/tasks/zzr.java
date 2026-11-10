package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
public final class zzr<TResult> {
    public final Object zza = new Object();
    public Queue<zzq<TResult>> zzb;
    public boolean zzc;

    public final void zza(@NonNull zzq<TResult> zzqVar) {
        synchronized (this.zza) {
            if (this.zzb == null) {
                this.zzb = new ArrayDeque();
            }
            this.zzb.add(zzqVar);
        }
    }

    public final void zzb(@NonNull Task<TResult> task) {
        zzq<TResult> zzqVarPoll;
        synchronized (this.zza) {
            if (this.zzb != null && !this.zzc) {
                this.zzc = true;
                while (true) {
                    synchronized (this.zza) {
                        zzqVarPoll = this.zzb.poll();
                        if (zzqVarPoll == null) {
                            this.zzc = false;
                            return;
                        }
                    }
                    zzqVarPoll.zzd(task);
                }
            }
        }
    }
}
