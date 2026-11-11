package com.google.android.gms.cast.internal;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public class Logger {
    protected final String zza;
    private final boolean zzb;
    private final String zzc;
    private boolean zzd;

    public Logger(String str) {
        this(str, null);
    }

    private static final boolean zzd() {
        return !Build.TYPE.equals("user");
    }

    public void d(String str, Object... objArr) {
        if (zzc()) {
            Log.d(this.zza, zza(str, objArr));
        }
    }

    public void e(String str, Object... objArr) {
        Log.e(this.zza, zza(str, objArr));
    }

    public void i(String str, Object... objArr) {
        Log.i(this.zza, zza(str, objArr));
    }

    public void v(String str, Object... objArr) {
        if (zzd() && this.zzb) {
            String str2 = this.zza;
            if (Log.isLoggable(str2, 2)) {
                Log.v(str2, zza(str, objArr));
            }
        }
    }

    public void w(String str, Object... objArr) {
        Log.w(this.zza, zza(str, objArr));
    }

    protected final String zza(String str, Object... objArr) {
        if (objArr.length != 0) {
            str = String.format(Locale.ROOT, str, objArr);
        }
        String str2 = this.zzc;
        String str3 = TextUtils.isEmpty(str2) ? "" : String.format("[%s] ", str2);
        return !TextUtils.isEmpty(str3) ? String.valueOf(str3).concat(String.valueOf(str)) : str;
    }

    public final void zzb(boolean z) {
        this.zzd = true;
    }

    public final boolean zzc() {
        if (!zzd()) {
            return false;
        }
        if (this.zzd) {
            return true;
        }
        return this.zzb && Log.isLoggable(this.zza, 3);
    }

    protected Logger(String str, String str2) {
        Preconditions.checkNotEmpty(str, "The log tag cannot be null or empty.");
        this.zza = str;
        this.zzc = str2;
        this.zzb = str.length() <= 23;
        this.zzd = false;
    }

    public void e(Throwable th, String str, Object... objArr) {
        Log.e(this.zza, zza(str, objArr), th);
    }

    public void i(Throwable th, String str, Object... objArr) {
        Log.i(this.zza, zza(str, objArr), th);
    }

    public void w(Throwable th, String str, Object... objArr) {
        Log.w(this.zza, zza(str, objArr), th);
    }

    public void d(Throwable th, String str, Object... objArr) {
        if (zzc()) {
            Log.d(this.zza, zza(str, objArr), th);
        }
    }
}
