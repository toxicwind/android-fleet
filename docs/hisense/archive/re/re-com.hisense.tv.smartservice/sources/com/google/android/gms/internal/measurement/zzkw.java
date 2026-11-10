package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class zzkw<K, V> extends LinkedHashMap<K, V> {
    public static final zzkw zza = new zzkw();
    public boolean zzb;

    static {
        zza.zzb = false;
    }

    public zzkw() {
        this.zzb = true;
    }

    public static <K, V> zzkw<K, V> zza() {
        return zza;
    }

    public static int zzf(Object obj) {
        if (obj instanceof byte[]) {
            return zzkf.zzb((byte[]) obj);
        }
        if (obj instanceof zzjz) {
            throw new UnsupportedOperationException();
        }
        return obj.hashCode();
    }

    private final void zzg() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzg();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this == map) {
            return true;
        }
        if (size() != map.size()) {
            return false;
        }
        for (Map.Entry<K, V> entry : entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            V value = entry.getValue();
            Object obj2 = map.get(entry.getKey());
            if (!(((value instanceof byte[]) && (obj2 instanceof byte[])) ? Arrays.equals((byte[]) value, (byte[]) obj2) : value.equals(obj2))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int iZzf = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            iZzf += zzf(entry.getValue()) ^ zzf(entry.getKey());
        }
        return iZzf;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        zzg();
        zzkf.zze(k);
        zzkf.zze(v);
        return (V) super.put(k, v);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        zzg();
        for (K k : map.keySet()) {
            zzkf.zze(k);
            zzkf.zze(map.get(k));
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        zzg();
        return (V) super.remove(obj);
    }

    public final zzkw<K, V> zzb() {
        return isEmpty() ? new zzkw<>() : new zzkw<>(this);
    }

    public final void zzc() {
        this.zzb = false;
    }

    public final void zzd(zzkw<K, V> zzkwVar) {
        zzg();
        if (zzkwVar.isEmpty()) {
            return;
        }
        putAll(zzkwVar);
    }

    public final boolean zze() {
        return this.zzb;
    }

    public zzkw(Map<K, V> map) {
        super(map);
        this.zzb = true;
    }
}
