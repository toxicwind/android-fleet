package com.google.android.gms.common.internal;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public final class GmsLogger {
    public final String zza;

    @Nullable
    public final String zzb;

    @KeepForSdk
    public GmsLogger(@NonNull String str) {
        this(str, null);
    }

    private final String zza(String str) {
        String str2 = this.zzb;
        return str2 == null ? str : str2.concat(str);
    }

    private final String zzb(String str, Object... objArr) {
        String str2 = String.format(str, objArr);
        String str3 = this.zzb;
        return str3 == null ? str2 : str3.concat(str2);
    }

    @KeepForSdk
    public boolean canLog(int i) {
        return Log.isLoggable(this.zza, i);
    }

    @KeepForSdk
    public boolean canLogPii() {
        return false;
    }

    @KeepForSdk
    public void d(@NonNull String str, @NonNull String str2) {
        if (canLog(3)) {
            String str3 = this.zzb;
            if (str3 != null) {
                str2 = str3.concat(str2);
            }
            Log.d(str, str2);
        }
    }

    @KeepForSdk
    public void e(@NonNull String str, @NonNull String str2) {
        if (canLog(6)) {
            String str3 = this.zzb;
            if (str3 != null) {
                str2 = str3.concat(str2);
            }
            Log.e(str, str2);
        }
    }

    @KeepForSdk
    public void efmt(@NonNull String str, @NonNull String str2, @NonNull Object... objArr) {
        if (canLog(6)) {
            Log.e(str, zzb(str2, objArr));
        }
    }

    @KeepForSdk
    public void i(@NonNull String str, @NonNull String str2) {
        if (canLog(4)) {
            String str3 = this.zzb;
            if (str3 != null) {
                str2 = str3.concat(str2);
            }
            Log.i(str, str2);
        }
    }

    @KeepForSdk
    public void pii(@NonNull String str, @NonNull String str2) {
    }

    @KeepForSdk
    public void pii(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
    }

    @KeepForSdk
    public void v(@NonNull String str, @NonNull String str2) {
        if (canLog(2)) {
            String str3 = this.zzb;
            if (str3 != null) {
                str2 = str3.concat(str2);
            }
            Log.v(str, str2);
        }
    }

    @KeepForSdk
    public void w(@NonNull String str, @NonNull String str2) {
        if (canLog(5)) {
            String str3 = this.zzb;
            if (str3 != null) {
                str2 = str3.concat(str2);
            }
            Log.w(str, str2);
        }
    }

    @KeepForSdk
    public void wfmt(@NonNull String str, @NonNull String str2, @NonNull Object... objArr) {
        if (canLog(5)) {
            Log.w(this.zza, zzb(str2, objArr));
        }
    }

    @KeepForSdk
    public void wtf(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (canLog(7)) {
            String str3 = this.zzb;
            Log.e(str, str3 == null ? str2 : str3.concat(str2), th);
            String str4 = this.zzb;
            if (str4 != null) {
                str2 = str4.concat(str2);
            }
            Log.wtf(str, str2, th);
        }
    }

    @KeepForSdk
    public GmsLogger(@NonNull String str, @Nullable String str2) {
        Preconditions.checkNotNull(str, "log tag cannot be null");
        Preconditions.checkArgument(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, 23);
        this.zza = str;
        if (str2 == null || str2.length() <= 0) {
            this.zzb = null;
        } else {
            this.zzb = str2;
        }
    }

    @KeepForSdk
    public void d(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (canLog(3)) {
            String str3 = this.zzb;
            if (str3 != null) {
                str2 = str3.concat(str2);
            }
            Log.d(str, str2, th);
        }
    }

    @KeepForSdk
    public void e(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (canLog(6)) {
            String str3 = this.zzb;
            if (str3 != null) {
                str2 = str3.concat(str2);
            }
            Log.e(str, str2, th);
        }
    }

    @KeepForSdk
    public void i(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (canLog(4)) {
            String str3 = this.zzb;
            if (str3 != null) {
                str2 = str3.concat(str2);
            }
            Log.i(str, str2, th);
        }
    }

    @KeepForSdk
    public void v(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (canLog(2)) {
            String str3 = this.zzb;
            if (str3 != null) {
                str2 = str3.concat(str2);
            }
            Log.v(str, str2, th);
        }
    }

    @KeepForSdk
    public void w(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (canLog(5)) {
            String str3 = this.zzb;
            if (str3 != null) {
                str2 = str3.concat(str2);
            }
            Log.w(str, str2, th);
        }
    }
}
