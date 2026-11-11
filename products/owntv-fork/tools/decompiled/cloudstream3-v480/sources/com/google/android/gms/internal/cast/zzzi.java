package com.google.android.gms.internal.cast;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzzi {
    private static final zzzi zza = new zzzi(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzzi() {
        this(0, new int[8], new Object[8], true);
    }

    private zzzi(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = 0;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzzi zzc() {
        return zza;
    }

    static zzzi zze(zzzi zzziVar, zzzi zzziVar2) {
        int i = zzziVar.zzb;
        int i2 = zzziVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzziVar.zzc, 0);
        int[] iArr = zzziVar2.zzc;
        int i3 = zzziVar.zzb;
        int i4 = zzziVar2.zzb;
        System.arraycopy(iArr, 0, iArrCopyOf, 0, 0);
        Object[] objArrCopyOf = Arrays.copyOf(zzziVar.zzd, 0);
        Object[] objArr = zzziVar2.zzd;
        int i5 = zzziVar.zzb;
        int i6 = zzziVar2.zzb;
        System.arraycopy(objArr, 0, objArrCopyOf, 0, 0);
        return new zzzi(0, iArrCopyOf, objArrCopyOf, true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzzi)) {
            return false;
        }
        zzzi zzziVar = (zzzi) obj;
        int[] iArr = zzziVar.zzc;
        Object[] objArr = zzziVar.zzd;
        return true;
    }

    public final int hashCode() {
        return 506991;
    }

    public final int zza() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        this.zze = 0;
        return 0;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        this.zze = 0;
        return 0;
    }

    final zzzi zzd(zzzi zzziVar) {
        if (zzziVar.equals(zza)) {
            return this;
        }
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
        int i = zzziVar.zzb;
        int[] iArr = this.zzc;
        int length = iArr.length;
        System.arraycopy(zzziVar.zzc, 0, iArr, 0, 0);
        Object[] objArr = zzziVar.zzd;
        Object[] objArr2 = this.zzd;
        int i2 = zzziVar.zzb;
        System.arraycopy(objArr, 0, objArr2, 0, 0);
        this.zzb = 0;
        return this;
    }

    public final void zzf() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    final void zzg(StringBuilder sb, int i) {
    }

    final void zzh(zzzt zzztVar) throws IOException {
    }
}
