package com.google.android.gms.measurement.internal;

import android.os.Process;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public final class zzfr extends Thread {
    public final /* synthetic */ zzfs zza;
    public final Object zzb;
    public final BlockingQueue<zzfq<?>> zzc;

    @GuardedBy("threadLifeCycleLock")
    public boolean zzd = false;

    public zzfr(zzfs zzfsVar, String str, BlockingQueue<zzfq<?>> blockingQueue) {
        this.zza = zzfsVar;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zzb = new Object();
        this.zzc = blockingQueue;
        setName(str);
    }

    private final void zzb() {
        synchronized (this.zza.zzh) {
            if (!this.zzd) {
                this.zza.zzi.release();
                this.zza.zzh.notifyAll();
                if (this == this.zza.zzb) {
                    this.zza.zzb = null;
                } else if (this == this.zza.zzc) {
                    this.zza.zzc = null;
                } else {
                    this.zza.zzs.zzay().zzd().zza("Current scheduler thread is neither worker nor network");
                }
                this.zzd = true;
            }
        }
    }

    private final void zzc(InterruptedException interruptedException) {
        this.zza.zzs.zzay().zzk().zzb(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        boolean z = false;
        while (!z) {
            try {
                this.zza.zzi.acquire();
                z = true;
            } catch (InterruptedException e2) {
                zzc(e2);
            }
        }
        try {
            int threadPriority = Process.getThreadPriority(Process.myTid());
            while (true) {
                zzfq<?> zzfqVarPoll = this.zzc.poll();
                if (zzfqVarPoll == null) {
                    synchronized (this.zzb) {
                        if (this.zzc.peek() == null) {
                            zzfs.zzr(this.zza);
                            try {
                                this.zzb.wait(30000L);
                            } catch (InterruptedException e3) {
                                zzc(e3);
                            }
                        }
                    }
                    synchronized (this.zza.zzh) {
                        if (this.zzc.peek() == null) {
                            break;
                        }
                    }
                } else {
                    Process.setThreadPriority(true != zzfqVarPoll.zza ? 10 : threadPriority);
                    zzfqVarPoll.run();
                }
            }
            if (this.zza.zzs.zzf().zzs(null, zzdy.zzaj)) {
                zzb();
            }
            zzb();
        } catch (Throwable th) {
            zzb();
            throw th;
        }
    }

    public final void zza() {
        synchronized (this.zzb) {
            this.zzb.notifyAll();
        }
    }
}
