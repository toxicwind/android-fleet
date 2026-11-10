package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class zzem {

    @NonNull
    public final String zza;

    @NonNull
    public final String zzb;
    public final long zzc;

    @NonNull
    public final Bundle zzd;

    public zzem(@NonNull String str, @NonNull String str2, @Nullable Bundle bundle, long j) {
        this.zza = str;
        this.zzb = str2;
        this.zzd = bundle;
        this.zzc = j;
    }

    public static zzem zzb(zzat zzatVar) {
        return new zzem(zzatVar.zza, zzatVar.zzc, zzatVar.zzb.zzc(), zzatVar.zzd);
    }

    public final String toString() {
        String str = this.zzb;
        String str2 = this.zza;
        String string = this.zzd.toString();
        StringBuilder sb = new StringBuilder(string.length() + String.valueOf(str).length() + 21 + String.valueOf(str2).length());
        a.a(sb, "origin=", str, ",name=", str2);
        return a.a(sb, ",params=", string);
    }

    public final zzat zza() {
        return new zzat(this.zza, new zzar(new Bundle(this.zzd)), this.zzb, this.zzc);
    }
}
