package com.google.android.gms.stats;

import android.content.Context;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.internal.stats.zzh;
import com.google.android.gms.internal.stats.zzi;
import com.google.common.primitives.UnsignedLong;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
@ShowFirstParty
@KeepForSdk
public class WakeLock {
    public static final long zzb = TimeUnit.DAYS.toMillis(366);
    public static volatile ScheduledExecutorService zzc = null;
    public static final Object zzd = new Object();
    public static volatile zzd zze = new zzb();

    @GuardedBy("acquireReleaseLock")
    public com.google.android.gms.internal.stats.zzb zza;
    public final Object zzf;

    @GuardedBy("acquireReleaseLock")
    public final PowerManager.WakeLock zzg;

    @GuardedBy("acquireReleaseLock")
    public int zzh;

    @GuardedBy("acquireReleaseLock")
    public Future<?> zzi;

    @GuardedBy("acquireReleaseLock")
    public long zzj;

    @GuardedBy("acquireReleaseLock")
    public final Set<zze> zzk;

    @GuardedBy("acquireReleaseLock")
    public boolean zzl;

    @GuardedBy("acquireReleaseLock")
    public int zzm;
    public Clock zzn;
    public WorkSource zzo;
    public final String zzp;
    public final String zzq;
    public final Context zzr;

    @GuardedBy("acquireReleaseLock")
    public final Map<String, zzc> zzs;
    public AtomicInteger zzt;
    public final ScheduledExecutorService zzu;

    @KeepForSdk
    public WakeLock(@NonNull Context context, int i, @NonNull String str) {
        String packageName = context.getPackageName();
        this.zzf = new Object();
        this.zzh = 0;
        this.zzk = new HashSet();
        this.zzl = true;
        this.zzn = DefaultClock.zza;
        this.zzs = new HashMap();
        this.zzt = new AtomicInteger(0);
        Preconditions.checkNotNull(context, "WakeLock: context must not be null");
        Preconditions.checkNotEmpty(str, "WakeLock: wakeLockName must not be empty");
        this.zzr = context.getApplicationContext();
        this.zzq = str;
        this.zza = null;
        if ("com.google.android.gms".equals(context.getPackageName())) {
            zzd zzdVar = zze;
            this.zzp = str;
        } else {
            String strValueOf = String.valueOf(str);
            this.zzp = strValueOf.length() != 0 ? "*gcore*:".concat(strValueOf) : new String("*gcore*:");
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        Object[] objArr = new Object[0];
        if (powerManager == null) {
            StringBuilder sb = new StringBuilder(29);
            sb.append((CharSequence) "expected a non-null reference", 0, 29);
            throw new zzi(sb.toString());
        }
        this.zzg = powerManager.newWakeLock(i, str);
        if (WorkSourceUtil.hasWorkSourcePermission(context)) {
            packageName = Strings.isEmptyOrWhitespace(packageName) ? context.getPackageName() : packageName;
            zzd zzdVar2 = zze;
            zzd zzdVar3 = zze;
            this.zzo = WorkSourceUtil.fromPackage(context, packageName);
            WorkSource workSource = this.zzo;
            if (workSource != null) {
                zze(this.zzg, workSource);
            }
        }
        ScheduledExecutorService scheduledExecutorServiceUnconfigurableScheduledExecutorService = zzc;
        if (scheduledExecutorServiceUnconfigurableScheduledExecutorService == null) {
            synchronized (zzd) {
                try {
                    scheduledExecutorServiceUnconfigurableScheduledExecutorService = zzc;
                    if (scheduledExecutorServiceUnconfigurableScheduledExecutorService == null) {
                        com.google.android.gms.internal.stats.zze zzeVar = zzh.zzb;
                        scheduledExecutorServiceUnconfigurableScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1));
                        zzc = scheduledExecutorServiceUnconfigurableScheduledExecutorService;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        this.zzu = scheduledExecutorServiceUnconfigurableScheduledExecutorService;
    }

    public static /* synthetic */ void zza(@NonNull WakeLock wakeLock) {
        synchronized (wakeLock.zzf) {
            if (wakeLock.isHeld()) {
                Log.e("WakeLock", String.valueOf(wakeLock.zzp).concat(" ** IS FORCE-RELEASED ON TIMEOUT **"));
                wakeLock.zzc();
                if (wakeLock.isHeld()) {
                    wakeLock.zzh = 1;
                    wakeLock.zzd(0);
                }
            }
        }
    }

    @GuardedBy("acquireReleaseLock")
    private final String zzb(String str) {
        if (!this.zzl || !TextUtils.isEmpty(null)) {
        }
        return null;
    }

    @GuardedBy("acquireReleaseLock")
    private final void zzc() {
        if (this.zzk.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.zzk);
        this.zzk.clear();
        if (arrayList.size() <= 0) {
            return;
        }
        throw null;
    }

    private final void zzd(int i) {
        synchronized (this.zzf) {
            if (isHeld()) {
                if (this.zzl) {
                    int i2 = this.zzh - 1;
                    this.zzh = i2;
                    if (i2 > 0) {
                        return;
                    }
                } else {
                    this.zzh = 0;
                }
                zzc();
                Iterator<zzc> it = this.zzs.values().iterator();
                while (it.hasNext()) {
                    it.next().zza = 0;
                }
                this.zzs.clear();
                Future<?> future = this.zzi;
                if (future != null) {
                    future.cancel(false);
                    this.zzi = null;
                    this.zzj = 0L;
                }
                zzd zzdVar = zze;
                this.zzm = 0;
                if (this.zzg.isHeld()) {
                    try {
                        try {
                            this.zzg.release();
                            if (this.zza != null) {
                                this.zza = null;
                            }
                        } catch (RuntimeException e2) {
                            if (!e2.getClass().equals(RuntimeException.class)) {
                                throw e2;
                            }
                            Log.e("WakeLock", String.valueOf(this.zzp).concat(" failed to release!"), e2);
                            if (this.zza != null) {
                                this.zza = null;
                            }
                        }
                    } catch (Throwable th) {
                        if (this.zza != null) {
                            this.zza = null;
                        }
                        throw th;
                    }
                } else {
                    Log.e("WakeLock", String.valueOf(this.zzp).concat(" should be held!"));
                }
            }
        }
    }

    public static void zze(PowerManager.WakeLock wakeLock, WorkSource workSource) {
        try {
            wakeLock.setWorkSource(workSource);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e2) {
            Log.wtf("WakeLock", e2.toString());
        }
    }

    @KeepForSdk
    public void acquire(long j) {
        this.zzt.incrementAndGet();
        zzd zzdVar = zze;
        long j2 = zzb;
        long j3 = UnsignedLong.UNSIGNED_MASK;
        long jMax = Math.max(Math.min(UnsignedLong.UNSIGNED_MASK, j2), 1L);
        if (j > 0) {
            jMax = Math.min(j, jMax);
        }
        synchronized (this.zzf) {
            try {
                if (!isHeld()) {
                    zzd zzdVar2 = zze;
                    zzd zzdVar3 = zze;
                    this.zza = com.google.android.gms.internal.stats.zzb.zza;
                    this.zzg.acquire();
                    this.zzn.elapsedRealtime();
                }
                this.zzh++;
                this.zzm++;
                zzb(null);
                zzc zzcVar = this.zzs.get(null);
                if (zzcVar == null) {
                    zzcVar = new zzc(null);
                    this.zzs.put(null, zzcVar);
                }
                zzd zzdVar4 = zze;
                zzcVar.zza++;
                long jElapsedRealtime = this.zzn.elapsedRealtime();
                if (UnsignedLong.UNSIGNED_MASK - jElapsedRealtime > jMax) {
                    j3 = jElapsedRealtime + jMax;
                }
                if (j3 > this.zzj) {
                    this.zzj = j3;
                    Future<?> future = this.zzi;
                    if (future != null) {
                        future.cancel(false);
                    }
                    this.zzi = this.zzu.schedule(new Runnable() { // from class: com.google.android.gms.stats.zza
                        @Override // java.lang.Runnable
                        public final void run() {
                            WakeLock.zza(this.zza);
                        }
                    }, jMax, TimeUnit.MILLISECONDS);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public boolean isHeld() {
        boolean z;
        synchronized (this.zzf) {
            z = this.zzh > 0;
        }
        return z;
    }

    @KeepForSdk
    public void release() {
        if (this.zzt.decrementAndGet() < 0) {
            Log.e("WakeLock", String.valueOf(this.zzp).concat(" release without a matched acquire!"));
        }
        synchronized (this.zzf) {
            zzb(null);
            if (this.zzs.containsKey(null)) {
                zzc zzcVar = this.zzs.get(null);
                if (zzcVar != null) {
                    int i = zzcVar.zza - 1;
                    zzcVar.zza = i;
                    if (i == 0) {
                        this.zzs.remove(null);
                    }
                }
            } else {
                Log.w("WakeLock", String.valueOf(this.zzp).concat(" counter does not exist"));
            }
            zzd(0);
        }
    }

    @KeepForSdk
    public void setReferenceCounted(boolean z) {
        synchronized (this.zzf) {
            this.zzl = z;
        }
    }
}
