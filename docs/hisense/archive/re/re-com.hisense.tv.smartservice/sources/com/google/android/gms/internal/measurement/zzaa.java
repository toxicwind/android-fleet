package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzaa {
    public String zza;
    public final long zzb;
    public final Map<String, Object> zzc = new HashMap();

    public zzaa(String str, long j, Map<String, Object> map) {
        this.zza = str;
        this.zzb = j;
        if (map != null) {
            this.zzc.putAll(map);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaa)) {
            return false;
        }
        zzaa zzaaVar = (zzaa) obj;
        if (this.zzb == zzaaVar.zzb && this.zza.equals(zzaaVar.zza)) {
            return this.zzc.equals(zzaaVar.zzc);
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = this.zza.hashCode();
        long j = this.zzb;
        return this.zzc.hashCode() + (((iHashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31);
    }

    public final String toString() {
        String str = this.zza;
        long j = this.zzb;
        String string = this.zzc.toString();
        StringBuilder sb = new StringBuilder(string.length() + String.valueOf(str).length() + 55);
        sb.append("Event{name='");
        sb.append(str);
        sb.append("', timestamp=");
        sb.append(j);
        sb.append(", params=");
        sb.append(string);
        sb.append('}');
        return sb.toString();
    }

    public final long zza() {
        return this.zzb;
    }

    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzaa clone() {
        return new zzaa(this.zza, this.zzb, new HashMap(this.zzc));
    }

    public final Object zzc(String str) {
        if (this.zzc.containsKey(str)) {
            return this.zzc.get(str);
        }
        return null;
    }

    public final String zzd() {
        return this.zza;
    }

    public final Map<String, Object> zze() {
        return this.zzc;
    }

    public final void zzf(String str) {
        this.zza = str;
    }

    public final void zzg(String str, Object obj) {
        if (obj == null) {
            this.zzc.remove(str);
        } else {
            this.zzc.put(str, obj);
        }
    }
}
