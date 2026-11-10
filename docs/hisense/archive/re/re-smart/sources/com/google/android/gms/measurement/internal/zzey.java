package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.common.primitives.UnsignedLong;

/* JADX INFO: loaded from: classes.dex */
public final class zzey {

    @VisibleForTesting
    public final String zza;
    public final /* synthetic */ zzfa zzb;
    public final String zzc;
    public final String zzd;
    public final long zze;

    public /* synthetic */ zzey(zzfa zzfaVar, String str, long j, zzex zzexVar) {
        this.zzb = zzfaVar;
        Preconditions.checkNotEmpty("health_monitor");
        Preconditions.checkArgument(j > 0);
        this.zza = "health_monitor:start";
        this.zzc = "health_monitor:count";
        this.zzd = "health_monitor:value";
        this.zze = j;
    }

    @WorkerThread
    private final long zzc() {
        return this.zzb.zza().getLong(this.zza, 0L);
    }

    @WorkerThread
    private final void zzd() {
        this.zzb.zzg();
        long jCurrentTimeMillis = this.zzb.zzs.zzav().currentTimeMillis();
        SharedPreferences.Editor editorEdit = this.zzb.zza().edit();
        editorEdit.remove(this.zzc);
        editorEdit.remove(this.zzd);
        editorEdit.putLong(this.zza, jCurrentTimeMillis);
        editorEdit.apply();
    }

    @WorkerThread
    public final Pair<String, Long> zza() {
        long jAbs;
        this.zzb.zzg();
        this.zzb.zzg();
        long jZzc = zzc();
        if (jZzc == 0) {
            zzd();
            jAbs = 0;
        } else {
            jAbs = Math.abs(jZzc - this.zzb.zzs.zzav().currentTimeMillis());
        }
        long j = this.zze;
        if (jAbs < j) {
            return null;
        }
        if (jAbs > j + j) {
            zzd();
            return null;
        }
        String string = this.zzb.zza().getString(this.zzd, null);
        long j2 = this.zzb.zza().getLong(this.zzc, 0L);
        zzd();
        return (string == null || j2 <= 0) ? zzfa.zza : new Pair<>(string, Long.valueOf(j2));
    }

    @WorkerThread
    public final void zzb(String str, long j) {
        this.zzb.zzg();
        if (zzc() == 0) {
            zzd();
        }
        if (str == null) {
            str = "";
        }
        long j2 = this.zzb.zza().getLong(this.zzc, 0L);
        if (j2 <= 0) {
            SharedPreferences.Editor editorEdit = this.zzb.zza().edit();
            editorEdit.putString(this.zzd, str);
            editorEdit.putLong(this.zzc, 1L);
            editorEdit.apply();
            return;
        }
        long jNextLong = this.zzb.zzs.zzv().zzF().nextLong();
        long j3 = j2 + 1;
        long j4 = UnsignedLong.UNSIGNED_MASK / j3;
        SharedPreferences.Editor editorEdit2 = this.zzb.zza().edit();
        if ((UnsignedLong.UNSIGNED_MASK & jNextLong) < j4) {
            editorEdit2.putString(this.zzd, str);
        }
        editorEdit2.putLong(this.zzc, j3);
        editorEdit2.apply();
    }
}
