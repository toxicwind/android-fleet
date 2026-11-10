package com.google.android.gms.internal.measurement;

import b.a.a.a.a;
import com.google.protobuf.MessageSchema;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public final class zzlf<T> implements zzln<T> {
    public static final int[] zza = new int[0];
    public static final Unsafe zzb = zzml.zzg();
    public final int[] zzc;
    public final Object[] zzd;
    public final int zze;
    public final int zzf;
    public final zzlc zzg;
    public final boolean zzh;
    public final boolean zzi;
    public final int[] zzj;
    public final int zzk;
    public final int zzl;
    public final zzkq zzm;
    public final zzmb<?, ?> zzn;
    public final zzjk<?> zzo;
    public final zzlh zzp;
    public final zzkx zzq;

    /* JADX WARN: Multi-variable type inference failed */
    public zzlf(int[] iArr, int[] iArr2, Object[] objArr, int i, int i2, zzlc zzlcVar, boolean z, boolean z2, int[] iArr3, int i3, int i4, zzlh zzlhVar, zzkq zzkqVar, zzmb<?, ?> zzmbVar, zzjk<?> zzjkVar, zzkx zzkxVar) {
        this.zzc = iArr;
        this.zzd = iArr2;
        this.zze = objArr;
        this.zzf = i;
        this.zzi = zzlcVar;
        boolean z3 = false;
        if (zzmbVar != 0 && zzmbVar.zzc((zzlc) i2)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzj = z2;
        this.zzk = iArr3;
        this.zzl = i3;
        this.zzp = i4;
        this.zzm = zzlhVar;
        this.zzn = zzkqVar;
        this.zzo = zzmbVar;
        this.zzg = i2;
        this.zzq = zzjkVar;
    }

    public static int zzA(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzB(int i) {
        return this.zzc[i + 1];
    }

    public static <T> long zzC(T t, long j) {
        return ((Long) zzml.zzf(t, j)).longValue();
    }

    private final zzkb zzD(int i) {
        int i2 = i / 3;
        return (zzkb) this.zzd[i2 + i2 + 1];
    }

    private final zzln zzE(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        Object[] objArr = this.zzd;
        zzln zzlnVar = (zzln) objArr[i3];
        if (zzlnVar != null) {
            return zzlnVar;
        }
        zzln<T> zzlnVarZzb = zzlk.zza.zzb((Class) objArr[i3 + 1]);
        this.zzd[i3] = zzlnVarZzb;
        return zzlnVarZzb;
    }

    private final Object zzF(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    public static Field zzG(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String string = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + name.length() + String.valueOf(string).length());
            a.a(sb, "Field ", str, " for ", name);
            throw new RuntimeException(a.a(sb, " not found. Known fields are ", string));
        }
    }

    private final void zzH(T t, T t2, int i) {
        long j = this.zzc[i + 1] & 1048575;
        if (zzM(t2, i)) {
            Object objZzf = zzml.zzf(t, j);
            Object objZzf2 = zzml.zzf(t2, j);
            if (objZzf != null && objZzf2 != null) {
                zzml.zzg.zzp(t, j, zzkf.zzg(objZzf, objZzf2));
                zzJ(t, i);
            } else if (objZzf2 != null) {
                zzml.zzg.zzp(t, j, objZzf2);
                zzJ(t, i);
            }
        }
    }

    private final void zzI(T t, T t2, int i) {
        int[] iArr = this.zzc;
        int i2 = iArr[i + 1];
        int i3 = iArr[i];
        long j = i2 & 1048575;
        if (zzP(t2, i3, i)) {
            Object objZzf = zzP(t, i3, i) ? zzml.zzf(t, j) : null;
            Object objZzf2 = zzml.zzf(t2, j);
            if (objZzf != null && objZzf2 != null) {
                zzml.zzg.zzp(t, j, zzkf.zzg(objZzf, objZzf2));
                zzK(t, i3, i);
            } else if (objZzf2 != null) {
                zzml.zzg.zzp(t, j, objZzf2);
                zzK(t, i3, i);
            }
        }
    }

    private final void zzJ(T t, int i) {
        int i2 = this.zzc[i + 2];
        long j = 1048575 & i2;
        if (j == 1048575) {
            return;
        }
        zzml.zzg.zzn(t, j, (1 << (i2 >>> 20)) | zzml.zzc(t, j));
    }

    private final void zzK(T t, int i, int i2) {
        zzml.zzg.zzn(t, this.zzc[i2 + 2] & 1048575, i);
    }

    private final boolean zzL(T t, T t2, int i) {
        return zzM(t, i) == zzM(t2, i);
    }

    private final boolean zzM(T t, int i) {
        int[] iArr = this.zzc;
        int i2 = iArr[i + 2];
        long j = i2 & 1048575;
        if (j != 1048575) {
            return (zzml.zzc(t, j) & (1 << (i2 >>> 20))) != 0;
        }
        int i3 = iArr[i + 1];
        long j2 = i3 & 1048575;
        switch ((i3 >>> 20) & 255) {
            case 0:
                return Double.doubleToRawLongBits(zzml.zza(t, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzml.zzb(t, j2)) != 0;
            case 2:
                return zzml.zzd(t, j2) != 0;
            case 3:
                return zzml.zzd(t, j2) != 0;
            case 4:
                return zzml.zzc(t, j2) != 0;
            case 5:
                return zzml.zzd(t, j2) != 0;
            case 6:
                return zzml.zzc(t, j2) != 0;
            case 7:
                return zzml.zzw(t, j2);
            case 8:
                Object objZzf = zzml.zzf(t, j2);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zzix) {
                    return !zzix.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzml.zzf(t, j2) != null;
            case 10:
                return !zzix.zzb.equals(zzml.zzf(t, j2));
            case 11:
                return zzml.zzc(t, j2) != 0;
            case 12:
                return zzml.zzc(t, j2) != 0;
            case 13:
                return zzml.zzc(t, j2) != 0;
            case 14:
                return zzml.zzd(t, j2) != 0;
            case 15:
                return zzml.zzc(t, j2) != 0;
            case 16:
                return zzml.zzd(t, j2) != 0;
            case 17:
                return zzml.zzf(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzN(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzM(t, i);
        }
        return (i3 & i4) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean zzO(Object obj, int i, zzln zzlnVar) {
        return zzlnVar.zzj(zzml.zzf(obj, i & 1048575));
    }

    private final boolean zzP(T t, int i, int i2) {
        return zzml.zzc(t, (long) (this.zzc[i2 + 2] & 1048575)) == i;
    }

    public static <T> boolean zzQ(T t, long j) {
        return ((Boolean) zzml.zzf(t, j)).booleanValue();
    }

    private final void zzR(T t, zzjf zzjfVar) {
        int i;
        int i2;
        if (this.zzh) {
            this.zzo.zza(t);
            throw null;
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length) {
            int iZzB = zzB(i4);
            int[] iArr = this.zzc;
            int i6 = iArr[i4];
            int i7 = (iZzB >>> 20) & 255;
            if (i7 <= 17) {
                int i8 = iArr[i4 + 2];
                i = i8 & 1048575;
                if (i != i3) {
                    i5 = unsafe.getInt(t, i);
                } else {
                    i = i3;
                }
                i2 = 1 << (i8 >>> 20);
            } else {
                i = i3;
                i2 = 0;
            }
            long j = iZzB & 1048575;
            switch (i7) {
                case 0:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzf(i6, zzml.zza(t, j));
                    }
                    break;
                case 1:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzo(i6, zzml.zzb(t, j));
                    }
                    break;
                case 2:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzt(i6, unsafe.getLong(t, j));
                    }
                    break;
                case 3:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzJ(i6, unsafe.getLong(t, j));
                    }
                    break;
                case 4:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzr(i6, unsafe.getInt(t, j));
                    }
                    break;
                case 5:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzm(i6, unsafe.getLong(t, j));
                    }
                    break;
                case 6:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzk(i6, unsafe.getInt(t, j));
                    }
                    break;
                case 7:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzb(i6, zzml.zzw(t, j));
                    }
                    break;
                case 8:
                    if ((i2 & i5) != 0) {
                        zzT(i6, unsafe.getObject(t, j), zzjfVar);
                    }
                    break;
                case 9:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzv(i6, unsafe.getObject(t, j), zzE(i4));
                    }
                    break;
                case 10:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzd(i6, (zzix) unsafe.getObject(t, j));
                    }
                    break;
                case 11:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzH(i6, unsafe.getInt(t, j));
                    }
                    break;
                case 12:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzi(i6, unsafe.getInt(t, j));
                    }
                    break;
                case 13:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzw(i6, unsafe.getInt(t, j));
                    }
                    break;
                case 14:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzy(i6, unsafe.getLong(t, j));
                    }
                    break;
                case 15:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzA(i6, unsafe.getInt(t, j));
                    }
                    break;
                case 16:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzC(i6, unsafe.getLong(t, j));
                    }
                    break;
                case 17:
                    if ((i2 & i5) != 0) {
                        zzjfVar.zzq(i6, unsafe.getObject(t, j), zzE(i4));
                    }
                    break;
                case 18:
                    zzlp.zzL(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 19:
                    zzlp.zzP(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 20:
                    zzlp.zzS(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 21:
                    zzlp.zzaa(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 22:
                    zzlp.zzR(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 23:
                    zzlp.zzO(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 24:
                    zzlp.zzN(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 25:
                    zzlp.zzJ(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 26:
                    zzlp.zzY(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar);
                    break;
                case 27:
                    zzlp.zzT(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, zzE(i4));
                    break;
                case 28:
                    zzlp.zzK(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar);
                    break;
                case 29:
                    zzlp.zzZ(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 30:
                    zzlp.zzM(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 31:
                    zzlp.zzU(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 32:
                    zzlp.zzV(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 33:
                    zzlp.zzW(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 34:
                    zzlp.zzX(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, false);
                    break;
                case 35:
                    zzlp.zzL(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 36:
                    zzlp.zzP(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 37:
                    zzlp.zzS(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 38:
                    zzlp.zzaa(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 39:
                    zzlp.zzR(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 40:
                    zzlp.zzO(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 41:
                    zzlp.zzN(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 42:
                    zzlp.zzJ(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 43:
                    zzlp.zzZ(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 44:
                    zzlp.zzM(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 45:
                    zzlp.zzU(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 46:
                    zzlp.zzV(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 47:
                    zzlp.zzW(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 48:
                    zzlp.zzX(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, true);
                    break;
                case 49:
                    zzlp.zzQ(this.zzc[i4], (List) unsafe.getObject(t, j), zzjfVar, zzE(i4));
                    break;
                case 50:
                    zzS(zzjfVar, i6, unsafe.getObject(t, j), i4);
                    break;
                case 51:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzf(i6, zzn(t, j));
                    }
                    break;
                case 52:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzo(i6, zzo(t, j));
                    }
                    break;
                case 53:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzt(i6, zzC(t, j));
                    }
                    break;
                case 54:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzJ(i6, zzC(t, j));
                    }
                    break;
                case 55:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzr(i6, zzr(t, j));
                    }
                    break;
                case 56:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzm(i6, zzC(t, j));
                    }
                    break;
                case 57:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzk(i6, zzr(t, j));
                    }
                    break;
                case 58:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzb(i6, zzQ(t, j));
                    }
                    break;
                case 59:
                    if (zzP(t, i6, i4)) {
                        zzT(i6, unsafe.getObject(t, j), zzjfVar);
                    }
                    break;
                case 60:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzv(i6, unsafe.getObject(t, j), zzE(i4));
                    }
                    break;
                case 61:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzd(i6, (zzix) unsafe.getObject(t, j));
                    }
                    break;
                case 62:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzH(i6, zzr(t, j));
                    }
                    break;
                case 63:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzi(i6, zzr(t, j));
                    }
                    break;
                case 64:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzw(i6, zzr(t, j));
                    }
                    break;
                case 65:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzy(i6, zzC(t, j));
                    }
                    break;
                case 66:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzA(i6, zzr(t, j));
                    }
                    break;
                case 67:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzC(i6, zzC(t, j));
                    }
                    break;
                case 68:
                    if (zzP(t, i6, i4)) {
                        zzjfVar.zzq(i6, unsafe.getObject(t, j), zzE(i4));
                    }
                    break;
            }
            i4 += 3;
            i3 = i;
        }
        zzmb<?, ?> zzmbVar = this.zzn;
        zzmbVar.zzi(zzmbVar.zzc(t), zzjfVar);
    }

    private final <K, V> void zzS(zzjf zzjfVar, int i, Object obj, int i2) {
        if (obj == null) {
            return;
        }
        throw null;
    }

    public static final void zzT(int i, Object obj, zzjf zzjfVar) {
        if (obj instanceof String) {
            zzjfVar.zzF(i, (String) obj);
        } else {
            zzjfVar.zzd(i, (zzix) obj);
        }
    }

    public static zzmc zzd(Object obj) {
        zzjx zzjxVar = (zzjx) obj;
        zzmc zzmcVar = zzjxVar.zzc;
        if (zzmcVar != zzmc.zza) {
            return zzmcVar;
        }
        zzmc zzmcVarZze = zzmc.zze();
        zzjxVar.zzc = zzmcVarZze;
        return zzmcVarZze;
    }

    public static <T> zzlf<T> zzk(Class<T> cls, zzkz zzkzVar, zzlh zzlhVar, zzkq zzkqVar, zzmb<?, ?> zzmbVar, zzjk<?> zzjkVar, zzkx zzkxVar) {
        if (zzkzVar instanceof zzlm) {
            return zzl((zzlm) zzkzVar, zzlhVar, zzkqVar, zzmbVar, zzjkVar, zzkxVar);
        }
        throw null;
    }

    /* JADX WARN: Code duplicated, block: B:159:0x0307  */
    /* JADX WARN: Code duplicated, block: B:180:0x0382  */
    public static <T> zzlf<T> zzl(zzlm zzlmVar, zzlh zzlhVar, zzkq zzkqVar, zzmb<?, ?> zzmbVar, zzjk<?> zzjkVar, zzkx zzkxVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int iCharAt;
        int i6;
        int i7;
        int i8;
        int[] iArr;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        char cCharAt;
        int i16;
        char cCharAt2;
        char cCharAt3;
        char cCharAt4;
        char cCharAt5;
        char cCharAt6;
        char cCharAt7;
        int i17;
        char cCharAt8;
        int i18;
        int i19;
        int i20;
        int i21;
        int iObjectFieldOffset;
        int i22;
        int i23;
        int iObjectFieldOffset2;
        int i24;
        Field fieldZzG;
        char cCharAt9;
        int i25;
        Field fieldZzG2;
        Field fieldZzG3;
        int i26;
        char cCharAt10;
        int i27;
        char cCharAt11;
        int i28;
        char cCharAt12;
        int i29;
        char cCharAt13;
        boolean z = zzlmVar.zzc() == 2;
        String strZzd = zzlmVar.zzd();
        int length = strZzd.length();
        char c2 = 55296;
        if (strZzd.charAt(0) >= 55296) {
            int i30 = 1;
            while (true) {
                i = i30 + 1;
                if (strZzd.charAt(i30) < 55296) {
                    break;
                }
                i30 = i;
            }
        } else {
            i = 1;
        }
        int i31 = i + 1;
        int iCharAt2 = strZzd.charAt(i);
        if (iCharAt2 >= 55296) {
            int i32 = iCharAt2 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
            int i33 = 13;
            while (true) {
                i29 = i31 + 1;
                cCharAt13 = strZzd.charAt(i31);
                if (cCharAt13 < 55296) {
                    break;
                }
                i32 |= (cCharAt13 & 8191) << i33;
                i33 += 13;
                i31 = i29;
            }
            iCharAt2 = i32 | (cCharAt13 << i33);
            i31 = i29;
        }
        if (iCharAt2 == 0) {
            i12 = 0;
            iCharAt = 0;
            i14 = 0;
            i10 = 0;
            i11 = 0;
            i8 = i31;
            iArr = zza;
            i13 = 0;
            i9 = 0;
        } else {
            int i34 = i31 + 1;
            int iCharAt3 = strZzd.charAt(i31);
            if (iCharAt3 >= 55296) {
                int i35 = iCharAt3 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i36 = 13;
                while (true) {
                    i17 = i34 + 1;
                    cCharAt8 = strZzd.charAt(i34);
                    if (cCharAt8 < 55296) {
                        break;
                    }
                    i35 |= (cCharAt8 & 8191) << i36;
                    i36 += 13;
                    i34 = i17;
                }
                iCharAt3 = i35 | (cCharAt8 << i36);
                i34 = i17;
            }
            int i37 = i34 + 1;
            int iCharAt4 = strZzd.charAt(i34);
            if (iCharAt4 >= 55296) {
                int i38 = iCharAt4 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i39 = 13;
                while (true) {
                    i2 = i37 + 1;
                    cCharAt7 = strZzd.charAt(i37);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i38 |= (cCharAt7 & 8191) << i39;
                    i39 += 13;
                    i37 = i2;
                }
                iCharAt4 = i38 | (cCharAt7 << i39);
            } else {
                i2 = i37;
            }
            int i40 = i2 + 1;
            char cCharAt14 = strZzd.charAt(i2);
            if (cCharAt14 >= 55296) {
                int i41 = cCharAt14 & 8191;
                int i42 = 13;
                while (true) {
                    i3 = i40 + 1;
                    cCharAt6 = strZzd.charAt(i40);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i41 |= (cCharAt6 & 8191) << i42;
                    i42 += 13;
                    i40 = i3;
                }
                i4 = (cCharAt6 << i42) | i41;
            } else {
                i3 = i40;
                i4 = cCharAt14;
            }
            int i43 = i3 + 1;
            int iCharAt5 = strZzd.charAt(i3);
            if (iCharAt5 >= 55296) {
                int i44 = iCharAt5 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i45 = 13;
                while (true) {
                    i5 = i43 + 1;
                    cCharAt5 = strZzd.charAt(i43);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i44 |= (cCharAt5 & 8191) << i45;
                    i45 += 13;
                    i43 = i5;
                }
                iCharAt5 = (cCharAt5 << i45) | i44;
            } else {
                i5 = i43;
            }
            int i46 = i5 + 1;
            iCharAt = strZzd.charAt(i5);
            if (iCharAt >= 55296) {
                int i47 = iCharAt & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i48 = 13;
                while (true) {
                    i6 = i46 + 1;
                    cCharAt4 = strZzd.charAt(i46);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i47 |= (cCharAt4 & 8191) << i48;
                    i48 += 13;
                    i46 = i6;
                }
                iCharAt = (cCharAt4 << i48) | i47;
            } else {
                i6 = i46;
            }
            int i49 = i6 + 1;
            int iCharAt6 = strZzd.charAt(i6);
            if (iCharAt6 >= 55296) {
                int i50 = iCharAt6 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i51 = 13;
                while (true) {
                    i7 = i49 + 1;
                    cCharAt3 = strZzd.charAt(i49);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i50 |= (cCharAt3 & 8191) << i51;
                    i51 += 13;
                    i49 = i7;
                }
                iCharAt6 = (cCharAt3 << i51) | i50;
            } else {
                i7 = i49;
            }
            int i52 = i7 + 1;
            int iCharAt7 = strZzd.charAt(i7);
            if (iCharAt7 >= 55296) {
                int i53 = iCharAt7 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i54 = 13;
                while (true) {
                    i16 = i52 + 1;
                    cCharAt2 = strZzd.charAt(i52);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i53 |= (cCharAt2 & 8191) << i54;
                    i54 += 13;
                    i52 = i16;
                }
                iCharAt7 = i53 | (cCharAt2 << i54);
                i52 = i16;
            }
            i8 = i52 + 1;
            int iCharAt8 = strZzd.charAt(i52);
            if (iCharAt8 >= 55296) {
                int i55 = iCharAt8 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i56 = 13;
                while (true) {
                    i15 = i8 + 1;
                    cCharAt = strZzd.charAt(i8);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i55 |= (cCharAt & 8191) << i56;
                    i56 += 13;
                    i8 = i15;
                }
                iCharAt8 = i55 | (cCharAt << i56);
                i8 = i15;
            }
            iArr = new int[iCharAt8 + iCharAt6 + iCharAt7];
            i9 = iCharAt3 + iCharAt3 + iCharAt4;
            i10 = i4;
            i11 = iCharAt5;
            i12 = iCharAt3;
            i13 = iCharAt6;
            i14 = iCharAt8;
        }
        Unsafe unsafe = zzb;
        Object[] objArrZze = zzlmVar.zze();
        Class<?> cls = zzlmVar.zza().getClass();
        int[] iArr2 = new int[iCharAt * 3];
        Object[] objArr = new Object[iCharAt + iCharAt];
        int i57 = i14 + i13;
        int i58 = i9;
        int i59 = i14;
        int i60 = i57;
        int i61 = 0;
        int i62 = 0;
        while (i8 < length) {
            int i63 = i8 + 1;
            char cCharAt15 = strZzd.charAt(i8);
            if (cCharAt15 >= c2) {
                int i64 = 13;
                int i65 = cCharAt15 & 8191;
                int i66 = i63;
                while (true) {
                    i28 = i66 + 1;
                    cCharAt12 = strZzd.charAt(i66);
                    if (cCharAt12 < c2) {
                        break;
                    }
                    i65 |= (cCharAt12 & 8191) << i64;
                    i64 += 13;
                    i66 = i28;
                }
                i18 = i65 | (cCharAt12 << i64);
                i19 = i28;
            } else {
                i18 = cCharAt15;
                i19 = i63;
            }
            int i67 = i19 + 1;
            int iCharAt9 = strZzd.charAt(i19);
            if (iCharAt9 >= c2) {
                int i68 = 13;
                int i69 = iCharAt9 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i70 = i67;
                while (true) {
                    i27 = i70 + 1;
                    cCharAt11 = strZzd.charAt(i70);
                    if (cCharAt11 < c2) {
                        break;
                    }
                    i69 |= (cCharAt11 & 8191) << i68;
                    i68 += 13;
                    i70 = i27;
                }
                iCharAt9 = i69 | (cCharAt11 << i68);
                i20 = i27;
            } else {
                i20 = i67;
            }
            int i71 = iCharAt9 & 255;
            int i72 = length;
            if ((iCharAt9 & 1024) != 0) {
                iArr[i62] = i61;
                i62++;
            }
            if (i71 >= 51) {
                int i73 = i20 + 1;
                int iCharAt10 = strZzd.charAt(i20);
                int i74 = i73;
                char c3 = 55296;
                if (iCharAt10 >= 55296) {
                    int i75 = 13;
                    int i76 = iCharAt10 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                    int i77 = i74;
                    while (true) {
                        i26 = i77 + 1;
                        cCharAt10 = strZzd.charAt(i77);
                        if (cCharAt10 < c3) {
                            break;
                        }
                        i76 |= (cCharAt10 & 8191) << i75;
                        i75 += 13;
                        i77 = i26;
                        c3 = 55296;
                    }
                    iCharAt10 = i76 | (cCharAt10 << i75);
                    i74 = i26;
                }
                int i78 = i71 - 51;
                if (i78 == 9 || i78 == 17) {
                    int i79 = i61 / 3;
                    i25 = i58 + 1;
                    objArr[i79 + i79 + 1] = objArrZze[i58];
                } else if (i78 != 12 || z) {
                    i25 = i58;
                } else {
                    int i80 = i61 / 3;
                    i25 = i58 + 1;
                    objArr[i80 + i80 + 1] = objArrZze[i58];
                }
                int i81 = iCharAt10 + iCharAt10;
                Object obj = objArrZze[i81];
                i58 = i25;
                if (obj instanceof Field) {
                    fieldZzG2 = (Field) obj;
                } else {
                    fieldZzG2 = zzG(cls, (String) obj);
                    objArrZze[i81] = fieldZzG2;
                }
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzG2);
                int i82 = i81 + 1;
                Object obj2 = objArrZze[i82];
                if (obj2 instanceof Field) {
                    fieldZzG3 = (Field) obj2;
                } else {
                    fieldZzG3 = zzG(cls, (String) obj2);
                    objArrZze[i82] = fieldZzG3;
                }
                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZzG3);
                i22 = i74;
                i23 = 0;
            } else {
                int i83 = i58 + 1;
                Field fieldZzG4 = zzG(cls, (String) objArrZze[i58]);
                if (i71 == 9 || i71 == 17) {
                    int i84 = i61 / 3;
                    objArr[i84 + i84 + 1] = fieldZzG4.getType();
                } else {
                    if (i71 == 27 || i71 == 49) {
                        int i85 = i61 / 3;
                        i21 = i83 + 1;
                        objArr[i85 + i85 + 1] = objArrZze[i83];
                    } else if (i71 == 12 || i71 == 30 || i71 == 44) {
                        if (!z) {
                            int i86 = i61 / 3;
                            i21 = i83 + 1;
                            objArr[i86 + i86 + 1] = objArrZze[i83];
                        }
                    } else if (i71 == 50) {
                        int i87 = i59 + 1;
                        iArr[i59] = i61;
                        int i88 = i61 / 3;
                        int i89 = i88 + i88;
                        i21 = i83 + 1;
                        objArr[i89] = objArrZze[i83];
                        if ((iCharAt9 & 2048) != 0) {
                            objArr[i89 + 1] = objArrZze[i21];
                            i21++;
                        }
                        i59 = i87;
                    }
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzG4);
                    if ((iCharAt9 & 4096) == 4096 || i71 > 17) {
                        i22 = i20;
                        i23 = 0;
                        iObjectFieldOffset2 = 1048575;
                    } else {
                        int i90 = i20 + 1;
                        int iCharAt11 = strZzd.charAt(i20);
                        if (iCharAt11 >= 55296) {
                            int i91 = iCharAt11 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                            int i92 = 13;
                            while (true) {
                                i24 = i90 + 1;
                                cCharAt9 = strZzd.charAt(i90);
                                if (cCharAt9 < 55296) {
                                    break;
                                }
                                i91 |= (cCharAt9 & 8191) << i92;
                                i92 += 13;
                                i90 = i24;
                            }
                            iCharAt11 = i91 | (cCharAt9 << i92);
                        } else {
                            i24 = i90;
                        }
                        int i93 = (iCharAt11 / 32) + i12 + i12;
                        Object obj3 = objArrZze[i93];
                        if (obj3 instanceof Field) {
                            fieldZzG = (Field) obj3;
                        } else {
                            fieldZzG = zzG(cls, (String) obj3);
                            objArrZze[i93] = fieldZzG;
                        }
                        i23 = iCharAt11 % 32;
                        int i94 = i24;
                        iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZzG);
                        i22 = i94;
                    }
                    if (i71 >= 18 && i71 <= 49) {
                        iArr[i60] = iObjectFieldOffset;
                        i60++;
                    }
                    i58 = i21;
                }
                i21 = i83;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzG4);
                if ((iCharAt9 & 4096) == 4096) {
                    i22 = i20;
                    i23 = 0;
                    iObjectFieldOffset2 = 1048575;
                } else {
                    i22 = i20;
                    i23 = 0;
                    iObjectFieldOffset2 = 1048575;
                }
                if (i71 >= 18) {
                    iArr[i60] = iObjectFieldOffset;
                    i60++;
                }
                i58 = i21;
            }
            int i95 = i61 + 1;
            iArr2[i61] = i18;
            int i96 = i95 + 1;
            String str = strZzd;
            iArr2[i95] = ((iCharAt9 & 512) != 0 ? MessageSchema.ENFORCE_UTF8_MASK : 0) | ((iCharAt9 & 256) != 0 ? MessageSchema.REQUIRED_MASK : 0) | (i71 << 20) | iObjectFieldOffset;
            iArr2[i96] = (i23 << 20) | iObjectFieldOffset2;
            i61 = i96 + 1;
            i8 = i22;
            strZzd = str;
            length = i72;
            i62 = i62;
            iArr2 = iArr2;
            c2 = 55296;
        }
        return new zzlf<>(iArr2, objArr, i10, i11, zzlmVar.zza(), z, false, iArr, i14, i57, zzlhVar, zzkqVar, zzmbVar, zzjkVar, zzkxVar, null);
    }

    public static <T> double zzn(T t, long j) {
        return ((Double) zzml.zzf(t, j)).doubleValue();
    }

    public static <T> float zzo(T t, long j) {
        return ((Float) zzml.zzf(t, j)).floatValue();
    }

    private final int zzp(T t) {
        int i;
        int iZzA;
        int iZzA2;
        int iZzA3;
        int iZzB;
        int iZzA4;
        int iZzA5;
        int iZzv;
        int iZzA6;
        int iZzd;
        int iZzA7;
        int iZzu;
        int iZzi;
        int iZzz;
        int iZzA8;
        int i2;
        int iZzA9;
        int iZzA10;
        int iZzA11;
        int iZzB2;
        int iZzA12;
        int iZzv2;
        int iZzA13;
        int iZzd2;
        int iZzA14;
        Unsafe unsafe = zzb;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 1048575;
        while (i4 < this.zzc.length) {
            int iZzB3 = zzB(i4);
            int[] iArr = this.zzc;
            int i8 = iArr[i4];
            int i9 = (iZzB3 >>> 20) & 255;
            if (i9 <= 17) {
                int i10 = iArr[i4 + 2];
                int i11 = i10 & i3;
                i = 1 << (i10 >>> 20);
                if (i11 != i7) {
                    i6 = unsafe.getInt(t, i11);
                    i7 = i11;
                }
            } else {
                i = 0;
            }
            long j = i3 & iZzB3;
            switch (i9) {
                case 0:
                    if ((i6 & i) != 0) {
                        iZzA = zzje.zzA(i8 << 3);
                        iZzA4 = iZzA + 8;
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 1:
                    if ((i6 & i) != 0) {
                        iZzA2 = zzje.zzA(i8 << 3);
                        iZzA4 = iZzA2 + 4;
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 2:
                    if ((i6 & i) != 0) {
                        long j2 = unsafe.getLong(t, j);
                        iZzA3 = zzje.zzA(i8 << 3);
                        iZzB = zzje.zzB(j2);
                        iZzA4 = iZzA3 + iZzB;
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 3:
                    if ((i6 & i) != 0) {
                        long j3 = unsafe.getLong(t, j);
                        iZzA3 = zzje.zzA(i8 << 3);
                        iZzB = zzje.zzB(j3);
                        iZzA4 = iZzA3 + iZzB;
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 4:
                    if ((i6 & i) != 0) {
                        int i12 = unsafe.getInt(t, j);
                        iZzA5 = zzje.zzA(i8 << 3);
                        iZzv = zzje.zzv(i12);
                        iZzA4 = iZzv + iZzA5;
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 5:
                    if ((i6 & i) != 0) {
                        iZzA = zzje.zzA(i8 << 3);
                        iZzA4 = iZzA + 8;
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 6:
                    if ((i6 & i) != 0) {
                        iZzA2 = zzje.zzA(i8 << 3);
                        iZzA4 = iZzA2 + 4;
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 7:
                    if ((i6 & i) != 0) {
                        iZzA4 = zzje.zzA(i8 << 3) + 1;
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 8:
                    if ((i6 & i) != 0) {
                        Object object = unsafe.getObject(t, j);
                        if (!(object instanceof zzix)) {
                            iZzA5 = zzje.zzA(i8 << 3);
                            iZzv = zzje.zzy((String) object);
                            iZzA4 = iZzv + iZzA5;
                            i5 = iZzA4 + i5;
                        } else {
                            iZzA6 = zzje.zzA(i8 << 3);
                            iZzd = ((zzix) object).zzd();
                            iZzA7 = zzje.zzA(iZzd);
                            i5 = iZzA7 + iZzd + iZzA6 + i5;
                        }
                    }
                    break;
                case 9:
                    if ((i6 & i) != 0) {
                        iZzA4 = zzlp.zzo(i8, unsafe.getObject(t, j), zzE(i4));
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 10:
                    if ((i6 & i) != 0) {
                        zzix zzixVar = (zzix) unsafe.getObject(t, j);
                        iZzA6 = zzje.zzA(i8 << 3);
                        iZzd = zzixVar.zzd();
                        iZzA7 = zzje.zzA(iZzd);
                        i5 = iZzA7 + iZzd + iZzA6 + i5;
                    }
                    break;
                case 11:
                    if ((i6 & i) != 0) {
                        int i13 = unsafe.getInt(t, j);
                        iZzA5 = zzje.zzA(i8 << 3);
                        iZzv = zzje.zzA(i13);
                        iZzA4 = iZzv + iZzA5;
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 12:
                    if ((i6 & i) != 0) {
                        int i14 = unsafe.getInt(t, j);
                        iZzA5 = zzje.zzA(i8 << 3);
                        iZzv = zzje.zzv(i14);
                        iZzA4 = iZzv + iZzA5;
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 13:
                    if ((i6 & i) != 0) {
                        iZzA2 = zzje.zzA(i8 << 3);
                        iZzA4 = iZzA2 + 4;
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 14:
                    if ((i6 & i) != 0) {
                        iZzA = zzje.zzA(i8 << 3);
                        iZzA4 = iZzA + 8;
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 15:
                    if ((i6 & i) != 0) {
                        int i15 = unsafe.getInt(t, j);
                        iZzA5 = zzje.zzA(i8 << 3);
                        iZzv = zzje.zzA((i15 >> 31) ^ (i15 + i15));
                        iZzA4 = iZzv + iZzA5;
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 16:
                    if ((i & i6) != 0) {
                        long j4 = unsafe.getLong(t, j);
                        iZzA4 = zzje.zzB((j4 >> 63) ^ (j4 + j4)) + zzje.zzA(i8 << 3);
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 17:
                    if ((i6 & i) != 0) {
                        iZzA4 = zzje.zzu(i8, (zzlc) unsafe.getObject(t, j), zzE(i4));
                        i5 = iZzA4 + i5;
                    }
                    break;
                case 18:
                    iZzA4 = zzlp.zzh(i8, (List) unsafe.getObject(t, j), false);
                    i5 = iZzA4 + i5;
                    break;
                case 19:
                    iZzA4 = zzlp.zzf(i8, (List) unsafe.getObject(t, j), false);
                    i5 = iZzA4 + i5;
                    break;
                case 20:
                    iZzA4 = zzlp.zzm(i8, (List) unsafe.getObject(t, j), false);
                    i5 = iZzA4 + i5;
                    break;
                case 21:
                    iZzA4 = zzlp.zzx(i8, (List) unsafe.getObject(t, j), false);
                    i5 = iZzA4 + i5;
                    break;
                case 22:
                    iZzA4 = zzlp.zzk(i8, (List) unsafe.getObject(t, j), false);
                    i5 = iZzA4 + i5;
                    break;
                case 23:
                    iZzA4 = zzlp.zzh(i8, (List) unsafe.getObject(t, j), false);
                    i5 = iZzA4 + i5;
                    break;
                case 24:
                    iZzA4 = zzlp.zzf(i8, (List) unsafe.getObject(t, j), false);
                    i5 = iZzA4 + i5;
                    break;
                case 25:
                    iZzA4 = zzlp.zza(i8, (List) unsafe.getObject(t, j), false);
                    i5 = iZzA4 + i5;
                    break;
                case 26:
                    iZzu = zzlp.zzu(i8, (List) unsafe.getObject(t, j));
                    i5 += iZzu;
                    break;
                case 27:
                    iZzu = zzlp.zzp(i8, (List) unsafe.getObject(t, j), zzE(i4));
                    i5 += iZzu;
                    break;
                case 28:
                    iZzu = zzlp.zzc(i8, (List) unsafe.getObject(t, j));
                    i5 += iZzu;
                    break;
                case 29:
                    iZzu = zzlp.zzv(i8, (List) unsafe.getObject(t, j), false);
                    i5 += iZzu;
                    break;
                case 30:
                    iZzA4 = zzlp.zzd(i8, (List) unsafe.getObject(t, j), false);
                    i5 = iZzA4 + i5;
                    break;
                case 31:
                    iZzA4 = zzlp.zzf(i8, (List) unsafe.getObject(t, j), false);
                    i5 = iZzA4 + i5;
                    break;
                case 32:
                    iZzA4 = zzlp.zzh(i8, (List) unsafe.getObject(t, j), false);
                    i5 = iZzA4 + i5;
                    break;
                case 33:
                    iZzA4 = zzlp.zzq(i8, (List) unsafe.getObject(t, j), false);
                    i5 = iZzA4 + i5;
                    break;
                case 34:
                    iZzA4 = zzlp.zzs(i8, (List) unsafe.getObject(t, j), false);
                    i5 = iZzA4 + i5;
                    break;
                case 35:
                    iZzi = zzlp.zzi((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 36:
                    iZzi = zzlp.zzg((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 37:
                    iZzi = zzlp.zzn((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 38:
                    iZzi = zzlp.zzy((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 39:
                    iZzi = zzlp.zzl((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 40:
                    iZzi = zzlp.zzi((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 41:
                    iZzi = zzlp.zzg((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 42:
                    iZzi = zzlp.zzb((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 43:
                    iZzi = zzlp.zzw((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 44:
                    iZzi = zzlp.zze((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 45:
                    iZzi = zzlp.zzg((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 46:
                    iZzi = zzlp.zzi((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 47:
                    iZzi = zzlp.zzr((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 48:
                    iZzi = zzlp.zzt((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i8);
                        iZzA8 = zzje.zzA(iZzi);
                        i2 = iZzA8 + iZzz + iZzi;
                        i5 += i2;
                    }
                    break;
                case 49:
                    iZzu = zzlp.zzj(i8, (List) unsafe.getObject(t, j), zzE(i4));
                    i5 += iZzu;
                    break;
                case 50:
                    zzkx.zza(i8, unsafe.getObject(t, j), zzF(i4));
                    break;
                case 51:
                    if (zzP(t, i8, i4)) {
                        iZzA9 = zzje.zzA(i8 << 3);
                        iZzu = iZzA9 + 8;
                        i5 += iZzu;
                    }
                    break;
                case 52:
                    if (zzP(t, i8, i4)) {
                        iZzA10 = zzje.zzA(i8 << 3);
                        iZzu = iZzA10 + 4;
                        i5 += iZzu;
                    }
                    break;
                case 53:
                    if (zzP(t, i8, i4)) {
                        long jZzC = zzC(t, j);
                        iZzA11 = zzje.zzA(i8 << 3);
                        iZzB2 = zzje.zzB(jZzC);
                        iZzu = iZzA11 + iZzB2;
                        i5 += iZzu;
                    }
                    break;
                case 54:
                    if (zzP(t, i8, i4)) {
                        long jZzC2 = zzC(t, j);
                        iZzA11 = zzje.zzA(i8 << 3);
                        iZzB2 = zzje.zzB(jZzC2);
                        iZzu = iZzA11 + iZzB2;
                        i5 += iZzu;
                    }
                    break;
                case 55:
                    if (zzP(t, i8, i4)) {
                        int iZzr = zzr(t, j);
                        iZzA12 = zzje.zzA(i8 << 3);
                        iZzv2 = zzje.zzv(iZzr);
                        iZzu = iZzv2 + iZzA12;
                        i5 += iZzu;
                    }
                    break;
                case 56:
                    if (zzP(t, i8, i4)) {
                        iZzA9 = zzje.zzA(i8 << 3);
                        iZzu = iZzA9 + 8;
                        i5 += iZzu;
                    }
                    break;
                case 57:
                    if (zzP(t, i8, i4)) {
                        iZzA10 = zzje.zzA(i8 << 3);
                        iZzu = iZzA10 + 4;
                        i5 += iZzu;
                    }
                    break;
                case 58:
                    if (zzP(t, i8, i4)) {
                        iZzu = zzje.zzA(i8 << 3) + 1;
                        i5 += iZzu;
                    }
                    break;
                case 59:
                    if (zzP(t, i8, i4)) {
                        Object object2 = unsafe.getObject(t, j);
                        if (!(object2 instanceof zzix)) {
                            iZzA12 = zzje.zzA(i8 << 3);
                            iZzv2 = zzje.zzy((String) object2);
                            iZzu = iZzv2 + iZzA12;
                            i5 += iZzu;
                        } else {
                            iZzA13 = zzje.zzA(i8 << 3);
                            iZzd2 = ((zzix) object2).zzd();
                            iZzA14 = zzje.zzA(iZzd2);
                            i2 = iZzA14 + iZzd2 + iZzA13;
                            i5 += i2;
                        }
                    }
                    break;
                case 60:
                    if (zzP(t, i8, i4)) {
                        iZzu = zzlp.zzo(i8, unsafe.getObject(t, j), zzE(i4));
                        i5 += iZzu;
                    }
                    break;
                case 61:
                    if (zzP(t, i8, i4)) {
                        zzix zzixVar2 = (zzix) unsafe.getObject(t, j);
                        iZzA13 = zzje.zzA(i8 << 3);
                        iZzd2 = zzixVar2.zzd();
                        iZzA14 = zzje.zzA(iZzd2);
                        i2 = iZzA14 + iZzd2 + iZzA13;
                        i5 += i2;
                    }
                    break;
                case 62:
                    if (zzP(t, i8, i4)) {
                        int iZzr2 = zzr(t, j);
                        iZzA12 = zzje.zzA(i8 << 3);
                        iZzv2 = zzje.zzA(iZzr2);
                        iZzu = iZzv2 + iZzA12;
                        i5 += iZzu;
                    }
                    break;
                case 63:
                    if (zzP(t, i8, i4)) {
                        int iZzr3 = zzr(t, j);
                        iZzA12 = zzje.zzA(i8 << 3);
                        iZzv2 = zzje.zzv(iZzr3);
                        iZzu = iZzv2 + iZzA12;
                        i5 += iZzu;
                    }
                    break;
                case 64:
                    if (zzP(t, i8, i4)) {
                        iZzA10 = zzje.zzA(i8 << 3);
                        iZzu = iZzA10 + 4;
                        i5 += iZzu;
                    }
                    break;
                case 65:
                    if (zzP(t, i8, i4)) {
                        iZzA9 = zzje.zzA(i8 << 3);
                        iZzu = iZzA9 + 8;
                        i5 += iZzu;
                    }
                    break;
                case 66:
                    if (zzP(t, i8, i4)) {
                        int iZzr4 = zzr(t, j);
                        iZzA12 = zzje.zzA(i8 << 3);
                        iZzv2 = zzje.zzA((iZzr4 >> 31) ^ (iZzr4 + iZzr4));
                        iZzu = iZzv2 + iZzA12;
                        i5 += iZzu;
                    }
                    break;
                case 67:
                    if (zzP(t, i8, i4)) {
                        long jZzC3 = zzC(t, j);
                        iZzu = zzje.zzB((jZzC3 >> 63) ^ (jZzC3 + jZzC3)) + zzje.zzA(i8 << 3);
                        i5 += iZzu;
                    }
                    break;
                case 68:
                    if (zzP(t, i8, i4)) {
                        iZzu = zzje.zzu(i8, (zzlc) unsafe.getObject(t, j), zzE(i4));
                        i5 += iZzu;
                    }
                    break;
            }
            i4 += 3;
            i3 = 1048575;
        }
        zzmb<?, ?> zzmbVar = this.zzn;
        int iZza = zzmbVar.zza(zzmbVar.zzc(t)) + i5;
        if (!this.zzh) {
            return iZza;
        }
        this.zzo.zza(t);
        throw null;
    }

    private final int zzq(T t) {
        int iZzA;
        int iZzA2;
        int iZzA3;
        int iZzB;
        int iZzA4;
        int iZzv;
        int iZzA5;
        int iZzA6;
        int iZzd;
        int iZzA7;
        int iZzo;
        int iZzi;
        int iZzz;
        int iZzA8;
        int i;
        Unsafe unsafe = zzb;
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzc.length; i3 += 3) {
            int iZzB2 = zzB(i3);
            int i4 = (iZzB2 >>> 20) & 255;
            int i5 = this.zzc[i3];
            long j = iZzB2 & 1048575;
            if (i4 >= zzjp.DOUBLE_LIST_PACKED.zza() && i4 <= zzjp.SINT64_LIST_PACKED.zza()) {
                int i6 = this.zzc[i3 + 2];
            }
            switch (i4) {
                case 0:
                    if (zzM(t, i3)) {
                        iZzA = zzje.zzA(i5 << 3);
                        iZzo = iZzA + 8;
                        i2 = iZzo + i2;
                    }
                    break;
                case 1:
                    if (zzM(t, i3)) {
                        iZzA2 = zzje.zzA(i5 << 3);
                        iZzo = iZzA2 + 4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 2:
                    if (zzM(t, i3)) {
                        long jZzd = zzml.zzd(t, j);
                        iZzA3 = zzje.zzA(i5 << 3);
                        iZzB = zzje.zzB(jZzd);
                        iZzo = iZzB + iZzA3;
                        i2 = iZzo + i2;
                    }
                    break;
                case 3:
                    if (zzM(t, i3)) {
                        long jZzd2 = zzml.zzd(t, j);
                        iZzA3 = zzje.zzA(i5 << 3);
                        iZzB = zzje.zzB(jZzd2);
                        iZzo = iZzB + iZzA3;
                        i2 = iZzo + i2;
                    }
                    break;
                case 4:
                    if (zzM(t, i3)) {
                        int iZzc = zzml.zzc(t, j);
                        iZzA4 = zzje.zzA(i5 << 3);
                        iZzv = zzje.zzv(iZzc);
                        iZzo = iZzv + iZzA4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 5:
                    if (zzM(t, i3)) {
                        iZzA = zzje.zzA(i5 << 3);
                        iZzo = iZzA + 8;
                        i2 = iZzo + i2;
                    }
                    break;
                case 6:
                    if (zzM(t, i3)) {
                        iZzA2 = zzje.zzA(i5 << 3);
                        iZzo = iZzA2 + 4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 7:
                    if (zzM(t, i3)) {
                        iZzA5 = zzje.zzA(i5 << 3);
                        iZzo = iZzA5 + 1;
                        i2 = iZzo + i2;
                    }
                    break;
                case 8:
                    if (zzM(t, i3)) {
                        Object objZzf = zzml.zzf(t, j);
                        if (objZzf instanceof zzix) {
                            iZzA6 = zzje.zzA(i5 << 3);
                            iZzd = ((zzix) objZzf).zzd();
                            iZzA7 = zzje.zzA(iZzd);
                            i = iZzA7 + iZzd + iZzA6;
                            i2 = i + i2;
                        } else {
                            iZzA4 = zzje.zzA(i5 << 3);
                            iZzv = zzje.zzy((String) objZzf);
                            iZzo = iZzv + iZzA4;
                            i2 = iZzo + i2;
                        }
                    }
                    break;
                case 9:
                    if (zzM(t, i3)) {
                        iZzo = zzlp.zzo(i5, zzml.zzf(t, j), zzE(i3));
                        i2 = iZzo + i2;
                    }
                    break;
                case 10:
                    if (zzM(t, i3)) {
                        zzix zzixVar = (zzix) zzml.zzf(t, j);
                        iZzA6 = zzje.zzA(i5 << 3);
                        iZzd = zzixVar.zzd();
                        iZzA7 = zzje.zzA(iZzd);
                        i = iZzA7 + iZzd + iZzA6;
                        i2 = i + i2;
                    }
                    break;
                case 11:
                    if (zzM(t, i3)) {
                        int iZzc2 = zzml.zzc(t, j);
                        iZzA4 = zzje.zzA(i5 << 3);
                        iZzv = zzje.zzA(iZzc2);
                        iZzo = iZzv + iZzA4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 12:
                    if (zzM(t, i3)) {
                        int iZzc3 = zzml.zzc(t, j);
                        iZzA4 = zzje.zzA(i5 << 3);
                        iZzv = zzje.zzv(iZzc3);
                        iZzo = iZzv + iZzA4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 13:
                    if (zzM(t, i3)) {
                        iZzA2 = zzje.zzA(i5 << 3);
                        iZzo = iZzA2 + 4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 14:
                    if (zzM(t, i3)) {
                        iZzA = zzje.zzA(i5 << 3);
                        iZzo = iZzA + 8;
                        i2 = iZzo + i2;
                    }
                    break;
                case 15:
                    if (zzM(t, i3)) {
                        int iZzc4 = zzml.zzc(t, j);
                        iZzA4 = zzje.zzA(i5 << 3);
                        iZzv = zzje.zzA((iZzc4 >> 31) ^ (iZzc4 + iZzc4));
                        iZzo = iZzv + iZzA4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 16:
                    if (zzM(t, i3)) {
                        long jZzd3 = zzml.zzd(t, j);
                        iZzA4 = zzje.zzA(i5 << 3);
                        iZzv = zzje.zzB((jZzd3 >> 63) ^ (jZzd3 + jZzd3));
                        iZzo = iZzv + iZzA4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 17:
                    if (zzM(t, i3)) {
                        iZzo = zzje.zzu(i5, (zzlc) zzml.zzf(t, j), zzE(i3));
                        i2 = iZzo + i2;
                    }
                    break;
                case 18:
                    iZzo = zzlp.zzh(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 19:
                    iZzo = zzlp.zzf(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 20:
                    iZzo = zzlp.zzm(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 21:
                    iZzo = zzlp.zzx(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 22:
                    iZzo = zzlp.zzk(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 23:
                    iZzo = zzlp.zzh(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 24:
                    iZzo = zzlp.zzf(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 25:
                    iZzo = zzlp.zza(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 26:
                    iZzo = zzlp.zzu(i5, (List) zzml.zzf(t, j));
                    i2 = iZzo + i2;
                    break;
                case 27:
                    iZzo = zzlp.zzp(i5, (List) zzml.zzf(t, j), zzE(i3));
                    i2 = iZzo + i2;
                    break;
                case 28:
                    iZzo = zzlp.zzc(i5, (List) zzml.zzf(t, j));
                    i2 = iZzo + i2;
                    break;
                case 29:
                    iZzo = zzlp.zzv(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 30:
                    iZzo = zzlp.zzd(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 31:
                    iZzo = zzlp.zzf(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 32:
                    iZzo = zzlp.zzh(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 33:
                    iZzo = zzlp.zzq(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 34:
                    iZzo = zzlp.zzs(i5, (List) zzml.zzf(t, j), false);
                    i2 = iZzo + i2;
                    break;
                case 35:
                    iZzi = zzlp.zzi((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 36:
                    iZzi = zzlp.zzg((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 37:
                    iZzi = zzlp.zzn((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 38:
                    iZzi = zzlp.zzy((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 39:
                    iZzi = zzlp.zzl((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 40:
                    iZzi = zzlp.zzi((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 41:
                    iZzi = zzlp.zzg((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 42:
                    iZzi = zzlp.zzb((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 43:
                    iZzi = zzlp.zzw((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 44:
                    iZzi = zzlp.zze((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 45:
                    iZzi = zzlp.zzg((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 46:
                    iZzi = zzlp.zzi((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 47:
                    iZzi = zzlp.zzr((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 48:
                    iZzi = zzlp.zzt((List) unsafe.getObject(t, j));
                    if (iZzi > 0) {
                        iZzz = zzje.zzz(i5);
                        iZzA8 = zzje.zzA(iZzi);
                        i = iZzA8 + iZzz + iZzi;
                        i2 = i + i2;
                    }
                    break;
                case 49:
                    iZzo = zzlp.zzj(i5, (List) zzml.zzf(t, j), zzE(i3));
                    i2 = iZzo + i2;
                    break;
                case 50:
                    zzkx.zza(i5, zzml.zzf(t, j), zzF(i3));
                    break;
                case 51:
                    if (zzP(t, i5, i3)) {
                        iZzA = zzje.zzA(i5 << 3);
                        iZzo = iZzA + 8;
                        i2 = iZzo + i2;
                    }
                    break;
                case 52:
                    if (zzP(t, i5, i3)) {
                        iZzA2 = zzje.zzA(i5 << 3);
                        iZzo = iZzA2 + 4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 53:
                    if (zzP(t, i5, i3)) {
                        long jZzC = zzC(t, j);
                        iZzA3 = zzje.zzA(i5 << 3);
                        iZzB = zzje.zzB(jZzC);
                        iZzo = iZzB + iZzA3;
                        i2 = iZzo + i2;
                    }
                    break;
                case 54:
                    if (zzP(t, i5, i3)) {
                        long jZzC2 = zzC(t, j);
                        iZzA3 = zzje.zzA(i5 << 3);
                        iZzB = zzje.zzB(jZzC2);
                        iZzo = iZzB + iZzA3;
                        i2 = iZzo + i2;
                    }
                    break;
                case 55:
                    if (zzP(t, i5, i3)) {
                        int iZzr = zzr(t, j);
                        iZzA4 = zzje.zzA(i5 << 3);
                        iZzv = zzje.zzv(iZzr);
                        iZzo = iZzv + iZzA4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 56:
                    if (zzP(t, i5, i3)) {
                        iZzA = zzje.zzA(i5 << 3);
                        iZzo = iZzA + 8;
                        i2 = iZzo + i2;
                    }
                    break;
                case 57:
                    if (zzP(t, i5, i3)) {
                        iZzA2 = zzje.zzA(i5 << 3);
                        iZzo = iZzA2 + 4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 58:
                    if (zzP(t, i5, i3)) {
                        iZzA5 = zzje.zzA(i5 << 3);
                        iZzo = iZzA5 + 1;
                        i2 = iZzo + i2;
                    }
                    break;
                case 59:
                    if (zzP(t, i5, i3)) {
                        Object objZzf2 = zzml.zzf(t, j);
                        if (objZzf2 instanceof zzix) {
                            iZzA6 = zzje.zzA(i5 << 3);
                            iZzd = ((zzix) objZzf2).zzd();
                            iZzA7 = zzje.zzA(iZzd);
                            i = iZzA7 + iZzd + iZzA6;
                            i2 = i + i2;
                        } else {
                            iZzA4 = zzje.zzA(i5 << 3);
                            iZzv = zzje.zzy((String) objZzf2);
                            iZzo = iZzv + iZzA4;
                            i2 = iZzo + i2;
                        }
                    }
                    break;
                case 60:
                    if (zzP(t, i5, i3)) {
                        iZzo = zzlp.zzo(i5, zzml.zzf(t, j), zzE(i3));
                        i2 = iZzo + i2;
                    }
                    break;
                case 61:
                    if (zzP(t, i5, i3)) {
                        zzix zzixVar2 = (zzix) zzml.zzf(t, j);
                        iZzA6 = zzje.zzA(i5 << 3);
                        iZzd = zzixVar2.zzd();
                        iZzA7 = zzje.zzA(iZzd);
                        i = iZzA7 + iZzd + iZzA6;
                        i2 = i + i2;
                    }
                    break;
                case 62:
                    if (zzP(t, i5, i3)) {
                        int iZzr2 = zzr(t, j);
                        iZzA4 = zzje.zzA(i5 << 3);
                        iZzv = zzje.zzA(iZzr2);
                        iZzo = iZzv + iZzA4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 63:
                    if (zzP(t, i5, i3)) {
                        int iZzr3 = zzr(t, j);
                        iZzA4 = zzje.zzA(i5 << 3);
                        iZzv = zzje.zzv(iZzr3);
                        iZzo = iZzv + iZzA4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 64:
                    if (zzP(t, i5, i3)) {
                        iZzA2 = zzje.zzA(i5 << 3);
                        iZzo = iZzA2 + 4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 65:
                    if (zzP(t, i5, i3)) {
                        iZzA = zzje.zzA(i5 << 3);
                        iZzo = iZzA + 8;
                        i2 = iZzo + i2;
                    }
                    break;
                case 66:
                    if (zzP(t, i5, i3)) {
                        int iZzr4 = zzr(t, j);
                        iZzA4 = zzje.zzA(i5 << 3);
                        iZzv = zzje.zzA((iZzr4 >> 31) ^ (iZzr4 + iZzr4));
                        iZzo = iZzv + iZzA4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 67:
                    if (zzP(t, i5, i3)) {
                        long jZzC3 = zzC(t, j);
                        iZzA4 = zzje.zzA(i5 << 3);
                        iZzv = zzje.zzB((jZzC3 >> 63) ^ (jZzC3 + jZzC3));
                        iZzo = iZzv + iZzA4;
                        i2 = iZzo + i2;
                    }
                    break;
                case 68:
                    if (zzP(t, i5, i3)) {
                        iZzo = zzje.zzu(i5, (zzlc) zzml.zzf(t, j), zzE(i3));
                        i2 = iZzo + i2;
                    }
                    break;
            }
        }
        zzmb<?, ?> zzmbVar = this.zzn;
        return zzmbVar.zza(zzmbVar.zzc(t)) + i2;
    }

    public static <T> int zzr(T t, long j) {
        return ((Integer) zzml.zzf(t, j)).intValue();
    }

    private final <K, V> int zzs(T t, byte[] bArr, int i, int i2, int i3, long j, zzik zzikVar) {
        Unsafe unsafe = zzb;
        Object objZzF = zzF(i3);
        Object object = unsafe.getObject(t, j);
        if (!((zzkw) object).zze()) {
            zzkw<K, V> zzkwVarZzb = zzkw.zza.zzb();
            zzkx.zzb(zzkwVarZzb, object);
            unsafe.putObject(t, j, zzkwVarZzb);
        }
        throw null;
    }

    private final int zzt(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzik zzikVar) throws zzkh {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Double.valueOf(Double.longBitsToDouble(zzil.zzn(bArr, i))));
                unsafe.putInt(t, j2, i4);
                return i + 8;
            case 52:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Float.valueOf(Float.intBitsToFloat(zzil.zzb(bArr, i))));
                unsafe.putInt(t, j2, i4);
                return i + 4;
            case 53:
            case 54:
                if (i5 != 0) {
                    return i;
                }
                int iZzm = zzil.zzm(bArr, i, zzikVar);
                unsafe.putObject(t, j, Long.valueOf(zzikVar.zzb));
                unsafe.putInt(t, j2, i4);
                return iZzm;
            case 55:
            case 62:
                if (i5 != 0) {
                    return i;
                }
                int iZzj = zzil.zzj(bArr, i, zzikVar);
                unsafe.putObject(t, j, Integer.valueOf(zzikVar.zza));
                unsafe.putInt(t, j2, i4);
                return iZzj;
            case 56:
            case 65:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Long.valueOf(zzil.zzn(bArr, i)));
                unsafe.putInt(t, j2, i4);
                return i + 8;
            case 57:
            case 64:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Integer.valueOf(zzil.zzb(bArr, i)));
                unsafe.putInt(t, j2, i4);
                return i + 4;
            case 58:
                if (i5 != 0) {
                    return i;
                }
                int iZzm2 = zzil.zzm(bArr, i, zzikVar);
                unsafe.putObject(t, j, Boolean.valueOf(zzikVar.zzb != 0));
                unsafe.putInt(t, j2, i4);
                return iZzm2;
            case 59:
                if (i5 != 2) {
                    return i;
                }
                int iZzj2 = zzil.zzj(bArr, i, zzikVar);
                int i9 = zzikVar.zza;
                if (i9 == 0) {
                    unsafe.putObject(t, j, "");
                } else {
                    if ((i6 & MessageSchema.ENFORCE_UTF8_MASK) != 0 && !zzmq.zzf(bArr, iZzj2, iZzj2 + i9)) {
                        throw zzkh.zzc();
                    }
                    unsafe.putObject(t, j, new String(bArr, iZzj2, i9, zzkf.zzb));
                    iZzj2 += i9;
                }
                unsafe.putInt(t, j2, i4);
                return iZzj2;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                int iZzd = zzil.zzd(zzE(i8), bArr, i, i2, zzikVar);
                Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object == null) {
                    unsafe.putObject(t, j, zzikVar.zzc);
                } else {
                    unsafe.putObject(t, j, zzkf.zzg(object, zzikVar.zzc));
                }
                unsafe.putInt(t, j2, i4);
                return iZzd;
            case 61:
                if (i5 != 2) {
                    return i;
                }
                int iZza = zzil.zza(bArr, i, zzikVar);
                unsafe.putObject(t, j, zzikVar.zzc);
                unsafe.putInt(t, j2, i4);
                return iZza;
            case 63:
                if (i5 != 0) {
                    return i;
                }
                int iZzj3 = zzil.zzj(bArr, i, zzikVar);
                int i10 = zzikVar.zza;
                zzkb zzkbVarZzD = zzD(i8);
                if (zzkbVarZzD == null || zzkbVarZzD.zza(i10)) {
                    unsafe.putObject(t, j, Integer.valueOf(i10));
                    unsafe.putInt(t, j2, i4);
                } else {
                    zzd(t).zzh(i3, Long.valueOf(i10));
                }
                return iZzj3;
            case 66:
                if (i5 != 0) {
                    return i;
                }
                int iZzj4 = zzil.zzj(bArr, i, zzikVar);
                unsafe.putObject(t, j, Integer.valueOf(zzja.zzb(zzikVar.zza)));
                unsafe.putInt(t, j2, i4);
                return iZzj4;
            case 67:
                if (i5 != 0) {
                    return i;
                }
                int iZzm3 = zzil.zzm(bArr, i, zzikVar);
                unsafe.putObject(t, j, Long.valueOf(zzja.zzc(zzikVar.zzb)));
                unsafe.putInt(t, j2, i4);
                return iZzm3;
            case 68:
                if (i5 != 3) {
                    return i;
                }
                int iZzc = zzil.zzc(zzE(i8), bArr, i, i2, (i3 & (-8)) | 4, zzikVar);
                Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object2 == null) {
                    unsafe.putObject(t, j, zzikVar.zzc);
                } else {
                    unsafe.putObject(t, j, zzkf.zzg(object2, zzikVar.zzc));
                }
                unsafe.putInt(t, j2, i4);
                return iZzc;
            default:
                return i;
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x02de A[PHI: r0 r19 r23 r26 r27 r28
      0x02de: PHI (r0v20 int) = (r0v15 int), (r0v22 int) binds: [B:112:0x0348, B:100:0x02dc] A[DONT_GENERATE, DONT_INLINE]
      0x02de: PHI (r19v6 int) = (r19v4 int), (r19v7 int) binds: [B:112:0x0348, B:100:0x02dc] A[DONT_GENERATE, DONT_INLINE]
      0x02de: PHI (r23v5 int) = (r23v3 int), (r23v6 int) binds: [B:112:0x0348, B:100:0x02dc] A[DONT_GENERATE, DONT_INLINE]
      0x02de: PHI (r26v3 int) = (r26v1 int), (r26v4 int) binds: [B:112:0x0348, B:100:0x02dc] A[DONT_GENERATE, DONT_INLINE]
      0x02de: PHI (r27v2 int) = (r27v0 int), (r27v3 int) binds: [B:112:0x0348, B:100:0x02dc] A[DONT_GENERATE, DONT_INLINE]
      0x02de: PHI (r28v6 sun.misc.Unsafe) = (r28v4 sun.misc.Unsafe), (r28v7 sun.misc.Unsafe) binds: [B:112:0x0348, B:100:0x02dc] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:102:0x02f4 A[PHI: r0 r19 r23 r26 r27 r28
      0x02f4: PHI (r0v19 int) = (r0v15 int), (r0v22 int) binds: [B:112:0x0348, B:100:0x02dc] A[DONT_GENERATE, DONT_INLINE]
      0x02f4: PHI (r19v5 int) = (r19v4 int), (r19v7 int) binds: [B:112:0x0348, B:100:0x02dc] A[DONT_GENERATE, DONT_INLINE]
      0x02f4: PHI (r23v4 int) = (r23v3 int), (r23v6 int) binds: [B:112:0x0348, B:100:0x02dc] A[DONT_GENERATE, DONT_INLINE]
      0x02f4: PHI (r26v2 int) = (r26v1 int), (r26v4 int) binds: [B:112:0x0348, B:100:0x02dc] A[DONT_GENERATE, DONT_INLINE]
      0x02f4: PHI (r27v1 int) = (r27v0 int), (r27v3 int) binds: [B:112:0x0348, B:100:0x02dc] A[DONT_GENERATE, DONT_INLINE]
      0x02f4: PHI (r28v5 sun.misc.Unsafe) = (r28v4 sun.misc.Unsafe), (r28v7 sun.misc.Unsafe) binds: [B:112:0x0348, B:100:0x02dc] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Failed to find 'out' block for switch in B:27:0x0091. Please report as an issue. */
    private final int zzu(T t, byte[] bArr, int i, int i2, zzik zzikVar) throws zzkh {
        int i3;
        int iZzk;
        int i4;
        int i5;
        Unsafe unsafe;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        Unsafe unsafe2;
        Unsafe unsafe3;
        int i12;
        int i13;
        int i14;
        Unsafe unsafe4;
        this = this;
        t = t;
        bArr = bArr;
        i2 = i2;
        zzikVar = zzikVar;
        Unsafe unsafe5 = zzb;
        int i15 = -1;
        int i16 = 1048575;
        int iZzi = i;
        int i17 = 1048575;
        int i18 = -1;
        int i19 = 0;
        int i20 = 0;
        while (iZzi < i2) {
            int i21 = iZzi + 1;
            byte b2 = bArr[iZzi];
            if (b2 < 0) {
                iZzk = zzil.zzk(b2, bArr, i21, zzikVar);
                i3 = zzikVar.zza;
            } else {
                i3 = b2;
                iZzk = i21;
            }
            int i22 = i3 >>> 3;
            int i23 = i3 & 7;
            int iZzx = i22 > i18 ? this.zzx(i22, i19 / 3) : this.zzw(i22);
            if (iZzx == i15) {
                i4 = iZzk;
                i5 = i22;
                unsafe = unsafe5;
                i6 = i15;
                i7 = 0;
            } else {
                int[] iArr = this.zzc;
                int i24 = iArr[iZzx + 1];
                int i25 = (i24 >>> 20) & 255;
                Unsafe unsafe6 = unsafe5;
                long j = i24 & i16;
                if (i25 <= 17) {
                    int i26 = iArr[iZzx + 2];
                    int i27 = 1 << (i26 >>> 20);
                    int i28 = i26 & 1048575;
                    if (i28 != i17) {
                        if (i17 != 1048575) {
                            long j2 = i17;
                            unsafe4 = unsafe6;
                            unsafe4.putInt(t, j2, i20);
                        } else {
                            unsafe4 = unsafe6;
                        }
                        if (i28 != 1048575) {
                            i20 = unsafe4.getInt(t, i28);
                        }
                        unsafe2 = unsafe4;
                        i17 = i28;
                    } else {
                        iZzx = iZzx;
                        unsafe2 = unsafe6;
                    }
                    switch (i25) {
                        case 0:
                            unsafe3 = unsafe2;
                            i8 = 1048575;
                            i12 = i17;
                            i13 = iZzx;
                            i5 = i22;
                            i14 = iZzk;
                            if (i23 != 1) {
                                i4 = i14;
                                unsafe = unsafe3;
                                i6 = -1;
                                int i29 = i12;
                                i7 = i13;
                                i17 = i29;
                            } else {
                                zzml.zzo(t, j, Double.longBitsToDouble(zzil.zzn(bArr, i14)));
                                iZzi = i14 + 8;
                                i20 |= i27;
                                i19 = i13;
                                unsafe5 = unsafe3;
                                i17 = i12;
                                i18 = i5;
                                i16 = i8;
                                i15 = -1;
                            }
                            break;
                        case 1:
                            unsafe3 = unsafe2;
                            i8 = 1048575;
                            i12 = i17;
                            i13 = iZzx;
                            i5 = i22;
                            i14 = iZzk;
                            if (i23 != 5) {
                                i4 = i14;
                                unsafe = unsafe3;
                                i6 = -1;
                                int i210 = i12;
                                i7 = i13;
                                i17 = i210;
                            } else {
                                zzml.zzg.zzf(t, j, Float.intBitsToFloat(zzil.zzb(bArr, i14)));
                                iZzi = i14 + 4;
                                i20 |= i27;
                                i19 = i13;
                                unsafe5 = unsafe3;
                                i17 = i12;
                                i18 = i5;
                                i16 = i8;
                                i15 = -1;
                            }
                            break;
                        case 2:
                        case 3:
                            unsafe3 = unsafe2;
                            i8 = 1048575;
                            i12 = i17;
                            i13 = iZzx;
                            i5 = i22;
                            i14 = iZzk;
                            if (i23 != 0) {
                                i4 = i14;
                                unsafe = unsafe3;
                                i6 = -1;
                                int i211 = i12;
                                i7 = i13;
                                i17 = i211;
                            } else {
                                int iZzm = zzil.zzm(bArr, i14, zzikVar);
                                unsafe3.putLong(t, j, zzikVar.zzb);
                                i20 |= i27;
                                i19 = i13;
                                unsafe5 = unsafe3;
                                iZzi = iZzm;
                                i17 = i12;
                                i18 = i5;
                                i16 = i8;
                                i15 = -1;
                            }
                            break;
                        case 4:
                        case 11:
                            unsafe3 = unsafe2;
                            i8 = 1048575;
                            i12 = i17;
                            i13 = iZzx;
                            i5 = i22;
                            i14 = iZzk;
                            if (i23 != 0) {
                                i4 = i14;
                                unsafe = unsafe3;
                                i6 = -1;
                                int i212 = i12;
                                i7 = i13;
                                i17 = i212;
                            } else {
                                iZzi = zzil.zzj(bArr, i14, zzikVar);
                                unsafe3.putInt(t, j, zzikVar.zza);
                                i20 |= i27;
                                i19 = i13;
                                unsafe5 = unsafe3;
                                i17 = i12;
                                i18 = i5;
                                i16 = i8;
                                i15 = -1;
                            }
                            break;
                        case 5:
                        case 14:
                            unsafe3 = unsafe2;
                            i8 = 1048575;
                            i12 = i17;
                            i13 = iZzx;
                            i5 = i22;
                            if (i23 != 1) {
                                i14 = iZzk;
                                i4 = i14;
                                unsafe = unsafe3;
                                i6 = -1;
                                int i213 = i12;
                                i7 = i13;
                                i17 = i213;
                            } else {
                                unsafe3.putLong(t, j, zzil.zzn(bArr, iZzk));
                                iZzi = iZzk + 8;
                                i20 |= i27;
                                i19 = i13;
                                unsafe5 = unsafe3;
                                i17 = i12;
                                i18 = i5;
                                i16 = i8;
                                i15 = -1;
                            }
                            break;
                        case 6:
                        case 13:
                            unsafe3 = unsafe2;
                            i8 = 1048575;
                            i12 = i17;
                            i13 = iZzx;
                            i5 = i22;
                            if (i23 != 5) {
                                i14 = iZzk;
                                i4 = i14;
                                unsafe = unsafe3;
                                i6 = -1;
                                int i214 = i12;
                                i7 = i13;
                                i17 = i214;
                            } else {
                                unsafe3.putInt(t, j, zzil.zzb(bArr, iZzk));
                                iZzi = iZzk + 4;
                                i20 |= i27;
                                i19 = i13;
                                unsafe5 = unsafe3;
                                i17 = i12;
                                i18 = i5;
                                i16 = i8;
                                i15 = -1;
                            }
                            break;
                        case 7:
                            unsafe3 = unsafe2;
                            i8 = 1048575;
                            i12 = i17;
                            i13 = iZzx;
                            i5 = i22;
                            if (i23 != 0) {
                                i14 = iZzk;
                                i4 = i14;
                                unsafe = unsafe3;
                                i6 = -1;
                                int i215 = i12;
                                i7 = i13;
                                i17 = i215;
                            } else {
                                iZzi = zzil.zzm(bArr, iZzk, zzikVar);
                                zzml.zzg.zzc(t, j, zzikVar.zzb != 0);
                                i20 |= i27;
                                i19 = i13;
                                unsafe5 = unsafe3;
                                i17 = i12;
                                i18 = i5;
                                i16 = i8;
                                i15 = -1;
                            }
                            break;
                        case 8:
                            unsafe3 = unsafe2;
                            i8 = 1048575;
                            i12 = i17;
                            i13 = iZzx;
                            i5 = i22;
                            if (i23 != 2) {
                                i14 = iZzk;
                                i4 = i14;
                                unsafe = unsafe3;
                                i6 = -1;
                                int i216 = i12;
                                i7 = i13;
                                i17 = i216;
                            } else {
                                iZzi = (i24 & MessageSchema.ENFORCE_UTF8_MASK) == 0 ? zzil.zzg(bArr, iZzk, zzikVar) : zzil.zzh(bArr, iZzk, zzikVar);
                                unsafe3.putObject(t, j, zzikVar.zzc);
                                i20 |= i27;
                                i19 = i13;
                                unsafe5 = unsafe3;
                                i17 = i12;
                                i18 = i5;
                                i16 = i8;
                                i15 = -1;
                            }
                            break;
                        case 9:
                            unsafe3 = unsafe2;
                            i8 = 1048575;
                            i12 = i17;
                            i13 = iZzx;
                            i5 = i22;
                            if (i23 != 2) {
                                i14 = iZzk;
                                i4 = i14;
                                unsafe = unsafe3;
                                i6 = -1;
                                int i217 = i12;
                                i7 = i13;
                                i17 = i217;
                            } else {
                                iZzi = zzil.zzd(this.zzE(i13), bArr, iZzk, i2, zzikVar);
                                Object object = unsafe3.getObject(t, j);
                                if (object == null) {
                                    unsafe3.putObject(t, j, zzikVar.zzc);
                                } else {
                                    unsafe3.putObject(t, j, zzkf.zzg(object, zzikVar.zzc));
                                }
                                i20 |= i27;
                                i19 = i13;
                                unsafe5 = unsafe3;
                                i17 = i12;
                                i18 = i5;
                                i16 = i8;
                                i15 = -1;
                            }
                            break;
                        case 10:
                            unsafe3 = unsafe2;
                            i8 = 1048575;
                            i12 = i17;
                            i13 = iZzx;
                            i5 = i22;
                            if (i23 != 2) {
                                i14 = iZzk;
                                i4 = i14;
                                unsafe = unsafe3;
                                i6 = -1;
                                int i218 = i12;
                                i7 = i13;
                                i17 = i218;
                            } else {
                                iZzi = zzil.zza(bArr, iZzk, zzikVar);
                                unsafe3.putObject(t, j, zzikVar.zzc);
                                i20 |= i27;
                                i19 = i13;
                                unsafe5 = unsafe3;
                                i17 = i12;
                                i18 = i5;
                                i16 = i8;
                                i15 = -1;
                            }
                            break;
                        case 12:
                            unsafe3 = unsafe2;
                            i8 = 1048575;
                            i12 = i17;
                            i13 = iZzx;
                            i5 = i22;
                            if (i23 != 0) {
                                i14 = iZzk;
                                i4 = i14;
                                unsafe = unsafe3;
                                i6 = -1;
                                int i219 = i12;
                                i7 = i13;
                                i17 = i219;
                            } else {
                                iZzi = zzil.zzj(bArr, iZzk, zzikVar);
                                unsafe3.putInt(t, j, zzikVar.zza);
                                i20 |= i27;
                                i19 = i13;
                                unsafe5 = unsafe3;
                                i17 = i12;
                                i18 = i5;
                                i16 = i8;
                                i15 = -1;
                            }
                            break;
                        case 15:
                            unsafe3 = unsafe2;
                            i8 = 1048575;
                            i12 = i17;
                            i13 = iZzx;
                            i5 = i22;
                            if (i23 != 0) {
                                i14 = iZzk;
                                i4 = i14;
                                unsafe = unsafe3;
                                i6 = -1;
                                int i2110 = i12;
                                i7 = i13;
                                i17 = i2110;
                            } else {
                                iZzi = zzil.zzj(bArr, iZzk, zzikVar);
                                unsafe3.putInt(t, j, zzja.zzb(zzikVar.zza));
                                i20 |= i27;
                                i19 = i13;
                                unsafe5 = unsafe3;
                                i17 = i12;
                                i18 = i5;
                                i16 = i8;
                                i15 = -1;
                            }
                            break;
                        case 16:
                            if (i23 != 0) {
                                i12 = i17;
                                i13 = iZzx;
                                i5 = i22;
                                unsafe3 = unsafe2;
                                i14 = iZzk;
                                i4 = i14;
                                unsafe = unsafe3;
                                i6 = -1;
                                int i2111 = i12;
                                i7 = i13;
                                i17 = i2111;
                            } else {
                                int iZzm2 = zzil.zzm(bArr, iZzk, zzikVar);
                                Unsafe unsafe7 = unsafe2;
                                i12 = i17;
                                int i30 = iZzx;
                                i5 = i22;
                                i8 = 1048575;
                                unsafe2.putLong(t, j, zzja.zzc(zzikVar.zzb));
                                i20 |= i27;
                                i19 = i30;
                                unsafe5 = unsafe7;
                                iZzi = iZzm2;
                                i17 = i12;
                                i18 = i5;
                                i16 = i8;
                                i15 = -1;
                            }
                            break;
                        default:
                            unsafe3 = unsafe2;
                            i12 = i17;
                            i13 = iZzx;
                            i5 = i22;
                            i14 = iZzk;
                            i4 = i14;
                            unsafe = unsafe3;
                            i6 = -1;
                            int i2112 = i12;
                            i7 = i13;
                            i17 = i2112;
                            break;
                    }
                } else {
                    i5 = i22;
                    int i31 = iZzk;
                    i8 = 1048575;
                    int i32 = i17;
                    if (i25 != 27) {
                        if (i25 <= 49) {
                            i10 = i20;
                            i11 = i32;
                            i7 = iZzx;
                            unsafe = unsafe6;
                            i6 = -1;
                            iZzi = zzv(t, bArr, i31, i2, i3, i5, i23, i7, i24, i25, j, zzikVar);
                            if (iZzi != i31) {
                                i19 = i7;
                                i18 = i5;
                                i15 = i6;
                                i20 = i10;
                                i17 = i11;
                            } else {
                                i4 = iZzi;
                                i20 = i10;
                                i17 = i11;
                            }
                        } else {
                            i9 = i31;
                            i10 = i20;
                            i11 = i32;
                            unsafe = unsafe6;
                            i6 = -1;
                            i7 = iZzx;
                            if (i25 != 50) {
                                iZzi = zzt(t, bArr, i9, i2, i3, i5, i23, i24, i25, j, i7, zzikVar);
                                if (iZzi != i9) {
                                    i19 = i7;
                                    i18 = i5;
                                    i15 = i6;
                                    i20 = i10;
                                    i17 = i11;
                                } else {
                                    i4 = iZzi;
                                    i20 = i10;
                                    i17 = i11;
                                }
                            } else if (i23 == 2) {
                                zzs(t, bArr, i9, i2, i7, j, zzikVar);
                                throw null;
                            }
                        }
                        unsafe5 = unsafe;
                        i16 = 1048575;
                    } else if (i23 == 2) {
                        zzke zzkeVarZzd = (zzke) unsafe6.getObject(t, j);
                        if (!zzkeVarZzd.zzc()) {
                            int size = zzkeVarZzd.size();
                            zzkeVarZzd = zzkeVarZzd.zzd(size == 0 ? 10 : size + size);
                            unsafe6.putObject(t, j, zzkeVarZzd);
                        }
                        iZzi = zzil.zze(this.zzE(iZzx), i3, bArr, i31, i2, zzkeVarZzd, zzikVar);
                        i19 = iZzx;
                        i20 = i20;
                        i17 = i32;
                        i18 = i5;
                        unsafe5 = unsafe6;
                        i16 = i8;
                        i15 = -1;
                    } else {
                        unsafe = unsafe6;
                        i9 = i31;
                        i10 = i20;
                        i11 = i32;
                        i6 = -1;
                        i7 = iZzx;
                    }
                    i4 = i9;
                    i20 = i10;
                    i17 = i11;
                }
            }
            iZzi = zzil.zzi(i3, bArr, i4, i2, zzd(t), zzikVar);
            i19 = i7;
            i18 = i5;
            i15 = i6;
            unsafe5 = unsafe;
            i16 = 1048575;
        }
        int i33 = i20;
        Unsafe unsafe8 = unsafe5;
        if (i17 != i16) {
            unsafe8.putInt(t, i17, i33);
        }
        if (iZzi == i2) {
            return iZzi;
        }
        throw zzkh.zze();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int zzv(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzik zzikVar) throws zzkh {
        int i8;
        int i9;
        int i10;
        int i11;
        int iZzj;
        int iZzj2 = i;
        zzke zzkeVarZzd = (zzke) zzb.getObject(t, j2);
        if (!zzkeVarZzd.zzc()) {
            int size = zzkeVarZzd.size();
            zzkeVarZzd = zzkeVarZzd.zzd(size == 0 ? 10 : size + size);
            zzb.putObject(t, j2, zzkeVarZzd);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzjg zzjgVar = (zzjg) zzkeVarZzd;
                    int iZzj3 = zzil.zzj(bArr, iZzj2, zzikVar);
                    int i12 = zzikVar.zza + iZzj3;
                    while (iZzj3 < i12) {
                        zzjgVar.zze(Double.longBitsToDouble(zzil.zzn(bArr, iZzj3)));
                        iZzj3 += 8;
                    }
                    if (iZzj3 == i12) {
                        return iZzj3;
                    }
                    throw zzkh.zzf();
                }
                if (i5 == 1) {
                    zzjg zzjgVar2 = (zzjg) zzkeVarZzd;
                    zzjgVar2.zze(Double.longBitsToDouble(zzil.zzn(bArr, i)));
                    while (true) {
                        i8 = iZzj2 + 8;
                        if (i8 < i2) {
                            iZzj2 = zzil.zzj(bArr, i8, zzikVar);
                            if (i3 == zzikVar.zza) {
                                zzjgVar2.zze(Double.longBitsToDouble(zzil.zzn(bArr, iZzj2)));
                            }
                        }
                    }
                    return i8;
                }
                return iZzj2;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzjq zzjqVar = (zzjq) zzkeVarZzd;
                    int iZzj4 = zzil.zzj(bArr, iZzj2, zzikVar);
                    int i13 = zzikVar.zza + iZzj4;
                    while (iZzj4 < i13) {
                        zzjqVar.zze(Float.intBitsToFloat(zzil.zzb(bArr, iZzj4)));
                        iZzj4 += 4;
                    }
                    if (iZzj4 == i13) {
                        return iZzj4;
                    }
                    throw zzkh.zzf();
                }
                if (i5 == 5) {
                    zzjq zzjqVar2 = (zzjq) zzkeVarZzd;
                    zzjqVar2.zze(Float.intBitsToFloat(zzil.zzb(bArr, i)));
                    while (true) {
                        i9 = iZzj2 + 4;
                        if (i9 < i2) {
                            iZzj2 = zzil.zzj(bArr, i9, zzikVar);
                            if (i3 == zzikVar.zza) {
                                zzjqVar2.zze(Float.intBitsToFloat(zzil.zzb(bArr, iZzj2)));
                            }
                        }
                    }
                    return i9;
                }
                return iZzj2;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzkr zzkrVar = (zzkr) zzkeVarZzd;
                    int iZzj5 = zzil.zzj(bArr, iZzj2, zzikVar);
                    int i14 = zzikVar.zza + iZzj5;
                    while (iZzj5 < i14) {
                        iZzj5 = zzil.zzm(bArr, iZzj5, zzikVar);
                        zzkrVar.zzg(zzikVar.zzb);
                    }
                    if (iZzj5 == i14) {
                        return iZzj5;
                    }
                    throw zzkh.zzf();
                }
                if (i5 == 0) {
                    zzkr zzkrVar2 = (zzkr) zzkeVarZzd;
                    int iZzm = zzil.zzm(bArr, iZzj2, zzikVar);
                    zzkrVar2.zzg(zzikVar.zzb);
                    while (iZzm < i2) {
                        int iZzj6 = zzil.zzj(bArr, iZzm, zzikVar);
                        if (i3 != zzikVar.zza) {
                            return iZzm;
                        }
                        iZzm = zzil.zzm(bArr, iZzj6, zzikVar);
                        zzkrVar2.zzg(zzikVar.zzb);
                    }
                    return iZzm;
                }
                return iZzj2;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzil.zzf(bArr, iZzj2, zzkeVarZzd, zzikVar);
                }
                if (i5 == 0) {
                    return zzil.zzl(i3, bArr, i, i2, zzkeVarZzd, zzikVar);
                }
                return iZzj2;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzkr zzkrVar3 = (zzkr) zzkeVarZzd;
                    int iZzj7 = zzil.zzj(bArr, iZzj2, zzikVar);
                    int i15 = zzikVar.zza + iZzj7;
                    while (iZzj7 < i15) {
                        zzkrVar3.zzg(zzil.zzn(bArr, iZzj7));
                        iZzj7 += 8;
                    }
                    if (iZzj7 == i15) {
                        return iZzj7;
                    }
                    throw zzkh.zzf();
                }
                if (i5 == 1) {
                    zzkr zzkrVar4 = (zzkr) zzkeVarZzd;
                    zzkrVar4.zzg(zzil.zzn(bArr, i));
                    while (true) {
                        i10 = iZzj2 + 8;
                        if (i10 < i2) {
                            iZzj2 = zzil.zzj(bArr, i10, zzikVar);
                            if (i3 == zzikVar.zza) {
                                zzkrVar4.zzg(zzil.zzn(bArr, iZzj2));
                            }
                        }
                    }
                    return i10;
                }
                return iZzj2;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzjy zzjyVar = (zzjy) zzkeVarZzd;
                    int iZzj8 = zzil.zzj(bArr, iZzj2, zzikVar);
                    int i16 = zzikVar.zza + iZzj8;
                    while (iZzj8 < i16) {
                        zzjyVar.zzh(zzil.zzb(bArr, iZzj8));
                        iZzj8 += 4;
                    }
                    if (iZzj8 == i16) {
                        return iZzj8;
                    }
                    throw zzkh.zzf();
                }
                if (i5 == 5) {
                    zzjy zzjyVar2 = (zzjy) zzkeVarZzd;
                    zzjyVar2.zzh(zzil.zzb(bArr, i));
                    while (true) {
                        i11 = iZzj2 + 4;
                        if (i11 < i2) {
                            iZzj2 = zzil.zzj(bArr, i11, zzikVar);
                            if (i3 == zzikVar.zza) {
                                zzjyVar2.zzh(zzil.zzb(bArr, iZzj2));
                            }
                        }
                    }
                    return i11;
                }
                return iZzj2;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzim zzimVar = (zzim) zzkeVarZzd;
                    iZzj = zzil.zzj(bArr, iZzj2, zzikVar);
                    int i17 = zzikVar.zza + iZzj;
                    while (iZzj < i17) {
                        iZzj = zzil.zzm(bArr, iZzj, zzikVar);
                        zzimVar.zze(zzikVar.zzb != 0);
                    }
                    if (iZzj != i17) {
                        throw zzkh.zzf();
                    }
                    return iZzj;
                }
                if (i5 == 0) {
                    zzim zzimVar2 = (zzim) zzkeVarZzd;
                    int iZzm2 = zzil.zzm(bArr, iZzj2, zzikVar);
                    zzimVar2.zze(zzikVar.zzb != 0);
                    while (iZzm2 < i2) {
                        int iZzj9 = zzil.zzj(bArr, iZzm2, zzikVar);
                        if (i3 != zzikVar.zza) {
                            return iZzm2;
                        }
                        iZzm2 = zzil.zzm(bArr, iZzj9, zzikVar);
                        zzimVar2.zze(zzikVar.zzb != 0);
                    }
                    return iZzm2;
                }
                return iZzj2;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        int iZzj10 = zzil.zzj(bArr, iZzj2, zzikVar);
                        int i18 = zzikVar.zza;
                        if (i18 < 0) {
                            throw zzkh.zzd();
                        }
                        if (i18 == 0) {
                            zzkeVarZzd.add("");
                        } else {
                            zzkeVarZzd.add(new String(bArr, iZzj10, i18, zzkf.zzb));
                            iZzj10 += i18;
                        }
                        while (iZzj10 < i2) {
                            int iZzj11 = zzil.zzj(bArr, iZzj10, zzikVar);
                            if (i3 != zzikVar.zza) {
                                return iZzj10;
                            }
                            iZzj10 = zzil.zzj(bArr, iZzj11, zzikVar);
                            int i19 = zzikVar.zza;
                            if (i19 < 0) {
                                throw zzkh.zzd();
                            }
                            if (i19 == 0) {
                                zzkeVarZzd.add("");
                            } else {
                                zzkeVarZzd.add(new String(bArr, iZzj10, i19, zzkf.zzb));
                                iZzj10 += i19;
                            }
                        }
                        return iZzj10;
                    }
                    int iZzj12 = zzil.zzj(bArr, iZzj2, zzikVar);
                    int i20 = zzikVar.zza;
                    if (i20 < 0) {
                        throw zzkh.zzd();
                    }
                    if (i20 == 0) {
                        zzkeVarZzd.add("");
                    } else {
                        int i21 = iZzj12 + i20;
                        if (!zzmq.zzf(bArr, iZzj12, i21)) {
                            throw zzkh.zzc();
                        }
                        zzkeVarZzd.add(new String(bArr, iZzj12, i20, zzkf.zzb));
                        iZzj12 = i21;
                    }
                    while (iZzj12 < i2) {
                        int iZzj13 = zzil.zzj(bArr, iZzj12, zzikVar);
                        if (i3 != zzikVar.zza) {
                            return iZzj12;
                        }
                        iZzj12 = zzil.zzj(bArr, iZzj13, zzikVar);
                        int i22 = zzikVar.zza;
                        if (i22 < 0) {
                            throw zzkh.zzd();
                        }
                        if (i22 == 0) {
                            zzkeVarZzd.add("");
                        } else {
                            int i23 = iZzj12 + i22;
                            if (!zzmq.zzf(bArr, iZzj12, i23)) {
                                throw zzkh.zzc();
                            }
                            zzkeVarZzd.add(new String(bArr, iZzj12, i22, zzkf.zzb));
                            iZzj12 = i23;
                        }
                    }
                    return iZzj12;
                }
                return iZzj2;
            case 27:
                if (i5 == 2) {
                    return zzil.zze(zzE(i6), i3, bArr, i, i2, zzkeVarZzd, zzikVar);
                }
                return iZzj2;
            case 28:
                if (i5 == 2) {
                    int iZzj14 = zzil.zzj(bArr, iZzj2, zzikVar);
                    int i24 = zzikVar.zza;
                    if (i24 < 0) {
                        throw zzkh.zzd();
                    }
                    if (i24 > bArr.length - iZzj14) {
                        throw zzkh.zzf();
                    }
                    if (i24 == 0) {
                        zzkeVarZzd.add(zzix.zzb);
                    } else {
                        zzkeVarZzd.add(zzix.zzl(bArr, iZzj14, i24));
                        iZzj14 += i24;
                    }
                    while (iZzj14 < i2) {
                        int iZzj15 = zzil.zzj(bArr, iZzj14, zzikVar);
                        if (i3 != zzikVar.zza) {
                            return iZzj14;
                        }
                        iZzj14 = zzil.zzj(bArr, iZzj15, zzikVar);
                        int i25 = zzikVar.zza;
                        if (i25 < 0) {
                            throw zzkh.zzd();
                        }
                        if (i25 > bArr.length - iZzj14) {
                            throw zzkh.zzf();
                        }
                        if (i25 == 0) {
                            zzkeVarZzd.add(zzix.zzb);
                        } else {
                            zzkeVarZzd.add(zzix.zzl(bArr, iZzj14, i25));
                            iZzj14 += i25;
                        }
                    }
                    return iZzj14;
                }
                return iZzj2;
            case 30:
            case 44:
                if (i5 != 2) {
                    if (i5 == 0) {
                        iZzj = zzil.zzl(i3, bArr, i, i2, zzkeVarZzd, zzikVar);
                    }
                    return iZzj2;
                }
                iZzj = zzil.zzf(bArr, iZzj2, zzkeVarZzd, zzikVar);
                zzjx zzjxVar = (zzjx) t;
                zzmc zzmcVar = zzjxVar.zzc;
                if (zzmcVar == zzmc.zza) {
                    zzmcVar = null;
                }
                Object objZzC = zzlp.zzC(i4, zzkeVarZzd, zzD(i6), zzmcVar, this.zzn);
                if (objZzC != null) {
                    zzjxVar.zzc = (zzmc) objZzC;
                    return iZzj;
                }
                return iZzj;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzjy zzjyVar3 = (zzjy) zzkeVarZzd;
                    int iZzj16 = zzil.zzj(bArr, iZzj2, zzikVar);
                    int i26 = zzikVar.zza + iZzj16;
                    while (iZzj16 < i26) {
                        iZzj16 = zzil.zzj(bArr, iZzj16, zzikVar);
                        zzjyVar3.zzh(zzja.zzb(zzikVar.zza));
                    }
                    if (iZzj16 == i26) {
                        return iZzj16;
                    }
                    throw zzkh.zzf();
                }
                if (i5 == 0) {
                    zzjy zzjyVar4 = (zzjy) zzkeVarZzd;
                    int iZzj17 = zzil.zzj(bArr, iZzj2, zzikVar);
                    zzjyVar4.zzh(zzja.zzb(zzikVar.zza));
                    while (iZzj17 < i2) {
                        int iZzj18 = zzil.zzj(bArr, iZzj17, zzikVar);
                        if (i3 != zzikVar.zza) {
                            return iZzj17;
                        }
                        iZzj17 = zzil.zzj(bArr, iZzj18, zzikVar);
                        zzjyVar4.zzh(zzja.zzb(zzikVar.zza));
                    }
                    return iZzj17;
                }
                return iZzj2;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzkr zzkrVar5 = (zzkr) zzkeVarZzd;
                    int iZzj19 = zzil.zzj(bArr, iZzj2, zzikVar);
                    int i27 = zzikVar.zza + iZzj19;
                    while (iZzj19 < i27) {
                        iZzj19 = zzil.zzm(bArr, iZzj19, zzikVar);
                        zzkrVar5.zzg(zzja.zzc(zzikVar.zzb));
                    }
                    if (iZzj19 == i27) {
                        return iZzj19;
                    }
                    throw zzkh.zzf();
                }
                if (i5 == 0) {
                    zzkr zzkrVar6 = (zzkr) zzkeVarZzd;
                    int iZzm3 = zzil.zzm(bArr, iZzj2, zzikVar);
                    zzkrVar6.zzg(zzja.zzc(zzikVar.zzb));
                    while (iZzm3 < i2) {
                        int iZzj20 = zzil.zzj(bArr, iZzm3, zzikVar);
                        if (i3 != zzikVar.zza) {
                            return iZzm3;
                        }
                        iZzm3 = zzil.zzm(bArr, iZzj20, zzikVar);
                        zzkrVar6.zzg(zzja.zzc(zzikVar.zzb));
                    }
                    return iZzm3;
                }
                return iZzj2;
            default:
                if (i5 == 3) {
                    zzln zzlnVarZzE = zzE(i6);
                    int i28 = (i3 & (-8)) | 4;
                    int iZzc = zzil.zzc(zzlnVarZzE, bArr, i, i2, i28, zzikVar);
                    zzkeVarZzd.add(zzikVar.zzc);
                    while (iZzc < i2) {
                        int iZzj21 = zzil.zzj(bArr, iZzc, zzikVar);
                        if (i3 != zzikVar.zza) {
                            return iZzc;
                        }
                        iZzc = zzil.zzc(zzlnVarZzE, bArr, iZzj21, i2, i28, zzikVar);
                        zzkeVarZzd.add(zzikVar.zzc);
                    }
                    return iZzc;
                }
                return iZzj2;
        }
    }

    private final int zzw(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, 0);
    }

    private final int zzx(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, i2);
    }

    private final int zzy(int i) {
        return this.zzc[i + 2];
    }

    private final int zzz(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final int zza(T t) {
        return this.zzi ? zzq(t) : zzp(t);
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final int zzb(T t) {
        int i;
        int iZzc;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int iZzB = zzB(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & iZzB;
            int iHashCode = 37;
            switch ((iZzB >>> 20) & 255) {
                case 0:
                    i = i2 * 53;
                    iZzc = zzkf.zzc(Double.doubleToLongBits(zzml.zza(t, j)));
                    i2 = iZzc + i;
                    break;
                case 1:
                    i = i2 * 53;
                    iZzc = Float.floatToIntBits(zzml.zzb(t, j));
                    i2 = iZzc + i;
                    break;
                case 2:
                    i = i2 * 53;
                    iZzc = zzkf.zzc(zzml.zzd(t, j));
                    i2 = iZzc + i;
                    break;
                case 3:
                    i = i2 * 53;
                    iZzc = zzkf.zzc(zzml.zzd(t, j));
                    i2 = iZzc + i;
                    break;
                case 4:
                    i = i2 * 53;
                    iZzc = zzml.zzc(t, j);
                    i2 = iZzc + i;
                    break;
                case 5:
                    i = i2 * 53;
                    iZzc = zzkf.zzc(zzml.zzd(t, j));
                    i2 = iZzc + i;
                    break;
                case 6:
                    i = i2 * 53;
                    iZzc = zzml.zzc(t, j);
                    i2 = iZzc + i;
                    break;
                case 7:
                    i = i2 * 53;
                    iZzc = zzkf.zza(zzml.zzw(t, j));
                    i2 = iZzc + i;
                    break;
                case 8:
                    i = i2 * 53;
                    iZzc = ((String) zzml.zzf(t, j)).hashCode();
                    i2 = iZzc + i;
                    break;
                case 9:
                    Object objZzf = zzml.zzf(t, j);
                    if (objZzf != null) {
                        iHashCode = objZzf.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 10:
                    i = i2 * 53;
                    iZzc = zzml.zzf(t, j).hashCode();
                    i2 = iZzc + i;
                    break;
                case 11:
                    i = i2 * 53;
                    iZzc = zzml.zzc(t, j);
                    i2 = iZzc + i;
                    break;
                case 12:
                    i = i2 * 53;
                    iZzc = zzml.zzc(t, j);
                    i2 = iZzc + i;
                    break;
                case 13:
                    i = i2 * 53;
                    iZzc = zzml.zzc(t, j);
                    i2 = iZzc + i;
                    break;
                case 14:
                    i = i2 * 53;
                    iZzc = zzkf.zzc(zzml.zzd(t, j));
                    i2 = iZzc + i;
                    break;
                case 15:
                    i = i2 * 53;
                    iZzc = zzml.zzc(t, j);
                    i2 = iZzc + i;
                    break;
                case 16:
                    i = i2 * 53;
                    iZzc = zzkf.zzc(zzml.zzd(t, j));
                    i2 = iZzc + i;
                    break;
                case 17:
                    Object objZzf2 = zzml.zzf(t, j);
                    if (objZzf2 != null) {
                        iHashCode = objZzf2.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    iZzc = zzml.zzf(t, j).hashCode();
                    i2 = iZzc + i;
                    break;
                case 50:
                    i = i2 * 53;
                    iZzc = zzml.zzf(t, j).hashCode();
                    i2 = iZzc + i;
                    break;
                case 51:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkf.zzc(Double.doubleToLongBits(zzn(t, j)));
                        i2 = iZzc + i;
                    }
                    break;
                case 52:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = Float.floatToIntBits(zzo(t, j));
                        i2 = iZzc + i;
                    }
                    break;
                case 53:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkf.zzc(zzC(t, j));
                        i2 = iZzc + i;
                    }
                    break;
                case 54:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkf.zzc(zzC(t, j));
                        i2 = iZzc + i;
                    }
                    break;
                case 55:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(t, j);
                        i2 = iZzc + i;
                    }
                    break;
                case 56:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkf.zzc(zzC(t, j));
                        i2 = iZzc + i;
                    }
                    break;
                case 57:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(t, j);
                        i2 = iZzc + i;
                    }
                    break;
                case 58:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkf.zza(zzQ(t, j));
                        i2 = iZzc + i;
                    }
                    break;
                case 59:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = ((String) zzml.zzf(t, j)).hashCode();
                        i2 = iZzc + i;
                    }
                    break;
                case 60:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzml.zzf(t, j).hashCode();
                        i2 = iZzc + i;
                    }
                    break;
                case 61:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzml.zzf(t, j).hashCode();
                        i2 = iZzc + i;
                    }
                    break;
                case 62:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(t, j);
                        i2 = iZzc + i;
                    }
                    break;
                case 63:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(t, j);
                        i2 = iZzc + i;
                    }
                    break;
                case 64:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(t, j);
                        i2 = iZzc + i;
                    }
                    break;
                case 65:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkf.zzc(zzC(t, j));
                        i2 = iZzc + i;
                    }
                    break;
                case 66:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(t, j);
                        i2 = iZzc + i;
                    }
                    break;
                case 67:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkf.zzc(zzC(t, j));
                        i2 = iZzc + i;
                    }
                    break;
                case 68:
                    if (zzP(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzml.zzf(t, j).hashCode();
                        i2 = iZzc + i;
                    }
                    break;
            }
        }
        int iHashCode2 = this.zzn.zzc(t).hashCode() + (i2 * 53);
        if (!this.zzh) {
            return iHashCode2;
        }
        this.zzo.zza(t);
        throw null;
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 12621. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    public final int zzc(T r29, byte[] r30, int r31, int r32, int r33, com.google.android.gms.internal.measurement.zzik r34) throws com.google.android.gms.internal.measurement.zzkh {
        /*
            Method dump skipped, instruction units count: 1262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlf.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zzik):int");
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final T zze() {
        return (T) ((zzjx) this.zzg).zzl(4, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final void zzf(T t) {
        int i;
        int i2 = this.zzk;
        while (true) {
            i = this.zzl;
            if (i2 >= i) {
                break;
            }
            long jZzB = zzB(this.zzj[i2]) & 1048575;
            Object objZzf = zzml.zzf(t, jZzB);
            if (objZzf != null) {
                ((zzkw) objZzf).zzc();
                zzml.zzg.zzp(t, jZzB, objZzf);
            }
            i2++;
        }
        int length = this.zzj.length;
        while (i < length) {
            this.zzm.zza(t, this.zzj[i]);
            i++;
        }
        this.zzn.zzg(t);
        if (this.zzh) {
            this.zzo.zzb(t);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final void zzg(T t, T t2) {
        if (t2 == null) {
            throw null;
        }
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzB = zzB(i);
            long j = 1048575 & iZzB;
            int i2 = this.zzc[i];
            switch ((iZzB >>> 20) & 255) {
                case 0:
                    if (zzM(t2, i)) {
                        zzml.zzo(t, j, zzml.zza(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 1:
                    if (zzM(t2, i)) {
                        zzml.zzg.zzf(t, j, zzml.zzb(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 2:
                    if (zzM(t2, i)) {
                        zzml.zzr(t, j, zzml.zzd(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 3:
                    if (zzM(t2, i)) {
                        zzml.zzr(t, j, zzml.zzd(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 4:
                    if (zzM(t2, i)) {
                        zzml.zzg.zzn(t, j, zzml.zzc(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 5:
                    if (zzM(t2, i)) {
                        zzml.zzr(t, j, zzml.zzd(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 6:
                    if (zzM(t2, i)) {
                        zzml.zzg.zzn(t, j, zzml.zzc(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 7:
                    if (zzM(t2, i)) {
                        zzml.zzg.zzc(t, j, zzml.zzw(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 8:
                    if (zzM(t2, i)) {
                        zzml.zzg.zzp(t, j, zzml.zzf(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 9:
                    zzH(t, t2, i);
                    break;
                case 10:
                    if (zzM(t2, i)) {
                        zzml.zzg.zzp(t, j, zzml.zzf(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 11:
                    if (zzM(t2, i)) {
                        zzml.zzg.zzn(t, j, zzml.zzc(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 12:
                    if (zzM(t2, i)) {
                        zzml.zzg.zzn(t, j, zzml.zzc(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 13:
                    if (zzM(t2, i)) {
                        zzml.zzg.zzn(t, j, zzml.zzc(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 14:
                    if (zzM(t2, i)) {
                        zzml.zzr(t, j, zzml.zzd(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 15:
                    if (zzM(t2, i)) {
                        zzml.zzg.zzn(t, j, zzml.zzc(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 16:
                    if (zzM(t2, i)) {
                        zzml.zzr(t, j, zzml.zzd(t2, j));
                        zzJ(t, i);
                    }
                    break;
                case 17:
                    zzH(t, t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzm.zzb(t, t2, j);
                    break;
                case 50:
                    zzlp.zzI(this.zzq, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzP(t2, i2, i)) {
                        zzml.zzg.zzp(t, j, zzml.zzf(t2, j));
                        zzK(t, i2, i);
                    }
                    break;
                case 60:
                    zzI(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzP(t2, i2, i)) {
                        zzml.zzg.zzp(t, j, zzml.zzf(t2, j));
                        zzK(t, i2, i);
                    }
                    break;
                case 68:
                    zzI(t, t2, i);
                    break;
            }
        }
        zzlp.zzF(this.zzn, t, t2);
        if (this.zzh) {
            zzlp.zzE(this.zzo, t, t2);
            throw null;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final void zzh(T t, byte[] bArr, int i, int i2, zzik zzikVar) throws zzkh {
        if (this.zzi) {
            zzu(t, bArr, i, i2, zzikVar);
        } else {
            zzc(t, bArr, i, i2, 0, zzikVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final boolean zzi(T t, T t2) {
        boolean zZzH;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int iZzB = zzB(i);
            long j = iZzB & 1048575;
            switch ((iZzB >>> 20) & 255) {
                case 0:
                    if (!zzL(t, t2, i) || Double.doubleToLongBits(zzml.zza(t, j)) != Double.doubleToLongBits(zzml.zza(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 1:
                    if (!zzL(t, t2, i) || Float.floatToIntBits(zzml.zzb(t, j)) != Float.floatToIntBits(zzml.zzb(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 2:
                    if (!zzL(t, t2, i) || zzml.zzd(t, j) != zzml.zzd(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 3:
                    if (!zzL(t, t2, i) || zzml.zzd(t, j) != zzml.zzd(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 4:
                    if (!zzL(t, t2, i) || zzml.zzc(t, j) != zzml.zzc(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 5:
                    if (!zzL(t, t2, i) || zzml.zzd(t, j) != zzml.zzd(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 6:
                    if (!zzL(t, t2, i) || zzml.zzc(t, j) != zzml.zzc(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 7:
                    if (!zzL(t, t2, i) || zzml.zzw(t, j) != zzml.zzw(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 8:
                    if (!zzL(t, t2, i) || !zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 9:
                    if (!zzL(t, t2, i) || !zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 10:
                    if (!zzL(t, t2, i) || !zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 11:
                    if (!zzL(t, t2, i) || zzml.zzc(t, j) != zzml.zzc(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 12:
                    if (!zzL(t, t2, i) || zzml.zzc(t, j) != zzml.zzc(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 13:
                    if (!zzL(t, t2, i) || zzml.zzc(t, j) != zzml.zzc(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 14:
                    if (!zzL(t, t2, i) || zzml.zzd(t, j) != zzml.zzd(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 15:
                    if (!zzL(t, t2, i) || zzml.zzc(t, j) != zzml.zzc(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 16:
                    if (!zzL(t, t2, i) || zzml.zzd(t, j) != zzml.zzd(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 17:
                    if (!zzL(t, t2, i) || !zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    zZzH = zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j));
                    break;
                case 50:
                    zZzH = zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long jZzy = zzy(i) & 1048575;
                    if (zzml.zzc(t, jZzy) != zzml.zzc(t2, jZzy) || !zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                default:
                    continue;
                    break;
            }
            if (!zZzH) {
                return false;
            }
        }
        if (!this.zzn.zzc(t).equals(this.zzn.zzc(t2))) {
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzo.zza(t);
        this.zzo.zza(t2);
        throw null;
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:45:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:48:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:51:0x00c6 A[LOOP:1: B:46:0x00b5->B:51:0x00c6, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x00c5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:71:0x00e4 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzln
    public final boolean zzj(T t) {
        int i;
        int i2;
        List list;
        zzln zzlnVarZzE;
        int i3;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        while (i6 < this.zzk) {
            int i7 = this.zzj[i6];
            int i8 = this.zzc[i7];
            int iZzB = zzB(i7);
            int i9 = this.zzc[i7 + 2];
            int i10 = i9 & 1048575;
            int i11 = 1 << (i9 >>> 20);
            if (i10 != i4) {
                i2 = i10 != 1048575 ? zzb.getInt(t, i10) : i5;
                i = i10;
            } else {
                i = i4;
                i2 = i5;
            }
            if ((268435456 & iZzB) != 0 && !zzN(t, i7, i, i2, i11)) {
                return false;
            }
            int i12 = (iZzB >>> 20) & 255;
            if (i12 == 9 || i12 == 17) {
                if (zzN(t, i7, i, i2, i11) && !zzO(t, iZzB, zzE(i7))) {
                    return false;
                }
            } else if (i12 == 27) {
                list = (List) zzml.zzf(t, iZzB & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzlnVarZzE = zzE(i7);
                    for (i3 = 0; i3 < list.size(); i3++) {
                        if (!zzlnVarZzE.zzj(list.get(i3))) {
                            return false;
                        }
                    }
                }
            } else if (i12 == 60 || i12 == 68) {
                if (zzP(t, i8, i7) && !zzO(t, iZzB, zzE(i7))) {
                    return false;
                }
            } else if (i12 == 49) {
                list = (List) zzml.zzf(t, iZzB & 1048575);
                if (list.isEmpty()) {
                    zzlnVarZzE = zzE(i7);
                    while (i3 < list.size()) {
                        if (!zzlnVarZzE.zzj(list.get(i3))) {
                            return false;
                        }
                    }
                } else {
                    continue;
                }
            } else if (i12 == 50 && !((zzkw) zzml.zzf(t, iZzB & 1048575)).isEmpty()) {
                throw null;
            }
            i6++;
            i4 = i;
            i5 = i2;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzo.zza(t);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final void zzm(T t, zzjf zzjfVar) {
        if (!this.zzi) {
            zzR(t, zzjfVar);
            return;
        }
        if (this.zzh) {
            this.zzo.zza(t);
            throw null;
        }
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int iZzB = zzB(i);
            int[] iArr = this.zzc;
            int i2 = iArr[i];
            switch ((iZzB >>> 20) & 255) {
                case 0:
                    if (zzM(t, i)) {
                        zzjfVar.zzf(i2, zzml.zza(t, iZzB & 1048575));
                    }
                    break;
                case 1:
                    if (zzM(t, i)) {
                        zzjfVar.zzo(i2, zzml.zzb(t, iZzB & 1048575));
                    }
                    break;
                case 2:
                    if (zzM(t, i)) {
                        zzjfVar.zzt(i2, zzml.zzd(t, iZzB & 1048575));
                    }
                    break;
                case 3:
                    if (zzM(t, i)) {
                        zzjfVar.zzJ(i2, zzml.zzd(t, iZzB & 1048575));
                    }
                    break;
                case 4:
                    if (zzM(t, i)) {
                        zzjfVar.zzr(i2, zzml.zzc(t, iZzB & 1048575));
                    }
                    break;
                case 5:
                    if (zzM(t, i)) {
                        zzjfVar.zzm(i2, zzml.zzd(t, iZzB & 1048575));
                    }
                    break;
                case 6:
                    if (zzM(t, i)) {
                        zzjfVar.zzk(i2, zzml.zzc(t, iZzB & 1048575));
                    }
                    break;
                case 7:
                    if (zzM(t, i)) {
                        zzjfVar.zzb(i2, zzml.zzw(t, iZzB & 1048575));
                    }
                    break;
                case 8:
                    if (zzM(t, i)) {
                        zzT(i2, zzml.zzf(t, iZzB & 1048575), zzjfVar);
                    }
                    break;
                case 9:
                    if (zzM(t, i)) {
                        zzjfVar.zzv(i2, zzml.zzf(t, iZzB & 1048575), zzE(i));
                    }
                    break;
                case 10:
                    if (zzM(t, i)) {
                        zzjfVar.zzd(i2, (zzix) zzml.zzf(t, iZzB & 1048575));
                    }
                    break;
                case 11:
                    if (zzM(t, i)) {
                        zzjfVar.zzH(i2, zzml.zzc(t, iZzB & 1048575));
                    }
                    break;
                case 12:
                    if (zzM(t, i)) {
                        zzjfVar.zzi(i2, zzml.zzc(t, iZzB & 1048575));
                    }
                    break;
                case 13:
                    if (zzM(t, i)) {
                        zzjfVar.zzw(i2, zzml.zzc(t, iZzB & 1048575));
                    }
                    break;
                case 14:
                    if (zzM(t, i)) {
                        zzjfVar.zzy(i2, zzml.zzd(t, iZzB & 1048575));
                    }
                    break;
                case 15:
                    if (zzM(t, i)) {
                        zzjfVar.zzA(i2, zzml.zzc(t, iZzB & 1048575));
                    }
                    break;
                case 16:
                    if (zzM(t, i)) {
                        zzjfVar.zzC(i2, zzml.zzd(t, iZzB & 1048575));
                    }
                    break;
                case 17:
                    if (zzM(t, i)) {
                        zzjfVar.zzq(i2, zzml.zzf(t, iZzB & 1048575), zzE(i));
                    }
                    break;
                case 18:
                    zzlp.zzL(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 19:
                    zzlp.zzP(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 20:
                    zzlp.zzS(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 21:
                    zzlp.zzaa(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 22:
                    zzlp.zzR(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 23:
                    zzlp.zzO(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 24:
                    zzlp.zzN(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 25:
                    zzlp.zzJ(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 26:
                    zzlp.zzY(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar);
                    break;
                case 27:
                    zzlp.zzT(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, zzE(i));
                    break;
                case 28:
                    zzlp.zzK(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar);
                    break;
                case 29:
                    zzlp.zzZ(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 30:
                    zzlp.zzM(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 31:
                    zzlp.zzU(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 32:
                    zzlp.zzV(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 33:
                    zzlp.zzW(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 34:
                    zzlp.zzX(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, false);
                    break;
                case 35:
                    zzlp.zzL(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 36:
                    zzlp.zzP(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 37:
                    zzlp.zzS(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 38:
                    zzlp.zzaa(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 39:
                    zzlp.zzR(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 40:
                    zzlp.zzO(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 41:
                    zzlp.zzN(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 42:
                    zzlp.zzJ(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 43:
                    zzlp.zzZ(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 44:
                    zzlp.zzM(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 45:
                    zzlp.zzU(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 46:
                    zzlp.zzV(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 47:
                    zzlp.zzW(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 48:
                    zzlp.zzX(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, true);
                    break;
                case 49:
                    zzlp.zzQ(iArr[i], (List) zzml.zzf(t, iZzB & 1048575), zzjfVar, zzE(i));
                    break;
                case 50:
                    zzS(zzjfVar, i2, zzml.zzf(t, iZzB & 1048575), i);
                    break;
                case 51:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzf(i2, zzn(t, iZzB & 1048575));
                    }
                    break;
                case 52:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzo(i2, zzo(t, iZzB & 1048575));
                    }
                    break;
                case 53:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzt(i2, zzC(t, iZzB & 1048575));
                    }
                    break;
                case 54:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzJ(i2, zzC(t, iZzB & 1048575));
                    }
                    break;
                case 55:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzr(i2, zzr(t, iZzB & 1048575));
                    }
                    break;
                case 56:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzm(i2, zzC(t, iZzB & 1048575));
                    }
                    break;
                case 57:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzk(i2, zzr(t, iZzB & 1048575));
                    }
                    break;
                case 58:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzb(i2, zzQ(t, iZzB & 1048575));
                    }
                    break;
                case 59:
                    if (zzP(t, i2, i)) {
                        zzT(i2, zzml.zzf(t, iZzB & 1048575), zzjfVar);
                    }
                    break;
                case 60:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzv(i2, zzml.zzf(t, iZzB & 1048575), zzE(i));
                    }
                    break;
                case 61:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzd(i2, (zzix) zzml.zzf(t, iZzB & 1048575));
                    }
                    break;
                case 62:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzH(i2, zzr(t, iZzB & 1048575));
                    }
                    break;
                case 63:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzi(i2, zzr(t, iZzB & 1048575));
                    }
                    break;
                case 64:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzw(i2, zzr(t, iZzB & 1048575));
                    }
                    break;
                case 65:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzy(i2, zzC(t, iZzB & 1048575));
                    }
                    break;
                case 66:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzA(i2, zzr(t, iZzB & 1048575));
                    }
                    break;
                case 67:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzC(i2, zzC(t, iZzB & 1048575));
                    }
                    break;
                case 68:
                    if (zzP(t, i2, i)) {
                        zzjfVar.zzq(i2, zzml.zzf(t, iZzB & 1048575), zzE(i));
                    }
                    break;
            }
        }
        zzmb<?, ?> zzmbVar = this.zzn;
        zzmbVar.zzi(zzmbVar.zzc(t), zzjfVar);
    }
}
