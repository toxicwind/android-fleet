package com.google.android.gms.internal.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class zzaa<E> extends zzab<E> {
    public Object[] zza = new Object[4];
    public int zzb = 0;
    public boolean zzc;

    public zzaa(int i) {
    }

    private final void zzb(int i) {
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (length >= i) {
            if (this.zzc) {
                this.zza = (Object[]) objArr.clone();
                this.zzc = false;
                return;
            }
            return;
        }
        int i2 = length + (length >> 1) + 1;
        if (i2 < i) {
            int iHighestOneBit = Integer.highestOneBit(i - 1);
            i2 = iHighestOneBit + iHighestOneBit;
        }
        if (i2 < 0) {
            i2 = Integer.MAX_VALUE;
        }
        this.zza = Arrays.copyOf(objArr, i2);
        this.zzc = false;
    }

    public final zzaa<E> zza(E e2) {
        if (e2 == null) {
            throw null;
        }
        zzb(this.zzb + 1);
        Object[] objArr = this.zza;
        int i = this.zzb;
        this.zzb = i + 1;
        objArr[i] = e2;
        return this;
    }
}
