package com.google.android.gms.internal.measurement;

import android.content.Context;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class zzgx extends zzhs {
    public final Context zza;
    public final zzib<zzhz<zzhi>> zzb;

    public zzgx(Context context, zzib<zzhz<zzhi>> zzibVar) {
        if (context == null) {
            throw new NullPointerException("Null context");
        }
        this.zza = context;
        this.zzb = zzibVar;
    }

    public final boolean equals(Object obj) {
        zzib<zzhz<zzhi>> zzibVar;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzhs) {
            zzhs zzhsVar = (zzhs) obj;
            if (this.zza.equals(zzhsVar.zza()) && ((zzibVar = this.zzb) != null ? zzibVar.equals(zzhsVar.zzb()) : zzhsVar.zzb() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        zzib<zzhz<zzhi>> zzibVar = this.zzb;
        return iHashCode ^ (zzibVar == null ? 0 : zzibVar.hashCode());
    }

    public final String toString() {
        String string = this.zza.toString();
        String strValueOf = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(string.length() + 46 + strValueOf.length());
        a.a(sb, "FlagsContext{context=", string, ", hermeticFileOverrides=", strValueOf);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzhs
    public final Context zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzhs
    public final zzib<zzhz<zzhi>> zzb() {
        return this.zzb;
    }
}
