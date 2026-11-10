package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class zzkl extends zzii<String> implements RandomAccess, zzkm {
    public static final zzkm zza;
    public static final zzkl zzb = new zzkl(10);
    public final List<Object> zzc;

    static {
        zzb.zzb();
        zza = zzb;
    }

    public zzkl() {
        this(10);
    }

    public static String zzj(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        return obj instanceof zzix ? ((zzix) obj).zzn(zzkf.zzb) : zzkf.zzh((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzii, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zzbM();
        this.zzc.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzii, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzbM();
        if (collection instanceof zzkm) {
            collection = ((zzkm) collection).zzh();
        }
        boolean zAddAll = this.zzc.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    @Override // com.google.android.gms.internal.measurement.zzii, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzbM();
        this.zzc.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzii, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zzbM();
        Object objRemove = this.zzc.remove(i);
        ((AbstractList) this).modCount++;
        return zzj(objRemove);
    }

    @Override // com.google.android.gms.internal.measurement.zzii, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zzbM();
        return zzj(this.zzc.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzke
    public final /* bridge */ /* synthetic */ zzke zzd(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzc);
        return new zzkl((ArrayList<Object>) arrayList);
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final zzkm zze() {
        return zzc() ? new zzmg(this) : this;
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final Object zzf(int i) {
        return this.zzc.get(i);
    }

    @Override // java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: zzg, reason: merged with bridge method [inline-methods] */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzix) {
            zzix zzixVar = (zzix) obj;
            String strZzn = zzixVar.zzn(zzkf.zzb);
            if (zzixVar.zzi()) {
                this.zzc.set(i, strZzn);
            }
            return strZzn;
        }
        byte[] bArr = (byte[]) obj;
        String strZzh = zzkf.zzh(bArr);
        if (zzkf.zzi(bArr)) {
            this.zzc.set(i, strZzh);
        }
        return strZzh;
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final List<?> zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final void zzi(zzix zzixVar) {
        zzbM();
        this.zzc.add(zzixVar);
        ((AbstractList) this).modCount++;
    }

    public zzkl(int i) {
        this.zzc = new ArrayList(i);
    }

    public zzkl(ArrayList<Object> arrayList) {
        this.zzc = arrayList;
    }

    @Override // com.google.android.gms.internal.measurement.zzii, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }
}
