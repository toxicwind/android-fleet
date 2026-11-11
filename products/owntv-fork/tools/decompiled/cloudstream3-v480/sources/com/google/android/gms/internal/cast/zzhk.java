package com.google.android.gms.internal.cast;

import j$.util.Objects;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class zzhk extends zzhc implements Set, j$.util.Set {
    private transient zzhg zza;

    zzhk() {
    }

    static int zzh(int i) {
        int iMax = Math.max(i, 2);
        if (iMax >= 751619276) {
            if (iMax < 1073741824) {
                return 1073741824;
            }
            throw new IllegalArgumentException("collection too large");
        }
        int iHighestOneBit = Integer.highestOneBit(iMax - 1);
        do {
            iHighestOneBit += iHighestOneBit;
        } while (((double) iHighestOneBit) * 0.7d < iMax);
        return iHighestOneBit;
    }

    public static zzhk zzj(Collection collection) {
        Object[] array = collection.toArray();
        return zzm(array.length, array);
    }

    public static zzhk zzk() {
        return zzht.zza;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzhk) && zzl() && ((zzhk) obj).zzl() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                return size() == set.size() && containsAll(set);
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzhu.zza(this);
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    public zzhg zzd() {
        zzhg zzhgVar = this.zza;
        if (zzhgVar != null) {
            return zzhgVar;
        }
        zzhg zzhgVarZzi = zzi();
        this.zza = zzhgVarZzi;
        return zzhgVarZzi;
    }

    @Override // com.google.android.gms.internal.cast.zzhc, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* JADX INFO: renamed from: zze */
    public abstract zzhw iterator();

    zzhg zzi() {
        Object[] array = toArray();
        int i = zzhg.zzd;
        return zzhg.zzi(array, array.length);
    }

    boolean zzl() {
        return false;
    }

    private static zzhk zzm(int i, Object... objArr) {
        if (i == 0) {
            return zzht.zza;
        }
        if (i == 1) {
            return new zzhv(Objects.requireNonNull(objArr[0]));
        }
        int iZzh = zzh(i);
        Object[] objArr2 = new Object[iZzh];
        int i2 = iZzh - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object obj = objArr[i5];
            zzhm.zza(obj, i5);
            int iHashCode = obj.hashCode();
            int iZza = zzgz.zza(iHashCode);
            while (true) {
                int i6 = iZza & i2;
                Object obj2 = objArr2[i6];
                if (obj2 == null) {
                    objArr[i4] = obj;
                    objArr2[i6] = obj;
                    i3 += iHashCode;
                    i4++;
                    break;
                }
                if (obj2.equals(obj)) {
                    break;
                }
                iZza++;
            }
        }
        Arrays.fill(objArr, i4, i, (Object) null);
        if (i4 == 1) {
            return new zzhv(Objects.requireNonNull(objArr[0]));
        }
        if (zzh(i4) < iZzh / 2) {
            return zzm(i4, objArr);
        }
        int length = objArr.length;
        if (i4 < (length >> 1) + (length >> 2)) {
            objArr = Arrays.copyOf(objArr, i4);
        }
        return new zzht(objArr, i3, objArr2, i2, i4);
    }
}
