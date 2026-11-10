package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public final class zzhr {
    public final String zza;
    public final Uri zzb;
    public final String zzc;
    public final String zzd;
    public final boolean zze;
    public final boolean zzf;
    public final boolean zzg;
    public final boolean zzh;
    public final zzhy<Context, Boolean> zzi;

    public zzhr(Uri uri) {
        this(null, uri, "", "", false, false, false, false, null);
    }

    public zzhr(String str, Uri uri, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, zzhy<Context, Boolean> zzhyVar) {
        this.zza = null;
        this.zzb = uri;
        this.zzc = "";
        this.zzd = "";
        this.zze = z;
        this.zzf = false;
        this.zzg = false;
        this.zzh = false;
        this.zzi = null;
    }

    public final zzhr zza() {
        if (this.zzc.isEmpty()) {
            return new zzhr(null, this.zzb, this.zzc, this.zzd, true, false, false, false, null);
        }
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    public final zzhu<Double> zzb(String str, double d2) {
        return new zzhp(this, "measurement.test.double_flag", Double.valueOf(-3.0d), true);
    }

    public final zzhu<Long> zzc(String str, long j) {
        return new zzhn(this, str, Long.valueOf(j), true);
    }

    public final zzhu<String> zzd(String str, String str2) {
        return new zzhq(this, str, str2, true);
    }

    public final zzhu<Boolean> zze(String str, boolean z) {
        return new zzho(this, str, Boolean.valueOf(z), true);
    }
}
