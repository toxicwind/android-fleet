package com.google.android.gms.internal.cast;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzyo<T> implements zzyv<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzzo.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final zzyl zze;
    private final boolean zzf;
    private final int[] zzg;
    private final int zzh;
    private final zzzh zzi;
    private final zzwx zzj;

    private zzyo(int[] iArr, Object[] objArr, int i, int i2, zzyl zzylVar, boolean z, int[] iArr2, int i3, int i4, zzyq zzyqVar, zzxy zzxyVar, zzzh zzzhVar, zzwx zzwxVar, zzyg zzygVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        boolean z2 = false;
        if (zzwxVar != null && (zzylVar instanceof zzxg)) {
            z2 = true;
        }
        this.zzf = z2;
        this.zzg = iArr2;
        this.zzh = i3;
        this.zzi = zzzhVar;
        this.zzj = zzwxVar;
        this.zze = zzylVar;
    }

    private static boolean zzA(Object obj, int i, zzyv zzyvVar) {
        return zzyvVar.zzh(zzzo.zzf(obj, i & 1048575));
    }

    private static boolean zzB(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzxi) {
            return ((zzxi) obj).zzM();
        }
        return true;
    }

    private final boolean zzC(Object obj, int i, int i2) {
        return zzzo.zzc(obj, (long) (zzm(i2) & 1048575)) == i;
    }

    private static boolean zzD(Object obj, long j) {
        return ((Boolean) zzzo.zzf(obj, j)).booleanValue();
    }

    private static final void zzE(int i, Object obj, zzzt zzztVar) throws IOException {
        if (obj instanceof String) {
            zzztVar.zzE(i, (String) obj);
        } else {
            zzztVar.zzd(i, (zzwn) obj);
        }
    }

    /* JADX WARN: Code duplicated, block: B:126:0x026d  */
    /* JADX WARN: Code duplicated, block: B:127:0x0270  */
    /* JADX WARN: Code duplicated, block: B:130:0x028a  */
    /* JADX WARN: Code duplicated, block: B:131:0x028d  */
    /* JADX WARN: Code duplicated, block: B:170:0x034e  */
    /* JADX WARN: Code duplicated, block: B:185:0x03a3  */
    /* JADX WARN: Code duplicated, block: B:188:0x03ad  */
    static zzyo zzi(Class cls, zzyi zzyiVar, zzyq zzyqVar, zzxy zzxyVar, zzzh zzzhVar, zzwx zzwxVar, zzyg zzygVar) {
        int i;
        int iCharAt;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int[] iArr;
        int i7;
        int i8;
        char cCharAt;
        int i9;
        char cCharAt2;
        int i10;
        char cCharAt3;
        int i11;
        char cCharAt4;
        int i12;
        char cCharAt5;
        int i13;
        char cCharAt6;
        int i14;
        char cCharAt7;
        int i15;
        char cCharAt8;
        int i16;
        int i17;
        int i18;
        int iObjectFieldOffset;
        char c;
        int iObjectFieldOffset2;
        int i19;
        int i20;
        int i21;
        Field fieldZzs;
        char cCharAt9;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        Object obj;
        Field fieldZzs2;
        int i27;
        Object obj2;
        Field fieldZzs3;
        int i28;
        char cCharAt10;
        int i29;
        char cCharAt11;
        int i30;
        char cCharAt12;
        int i31;
        char cCharAt13;
        if (!(zzyiVar instanceof zzyu)) {
            throw null;
        }
        zzyu zzyuVar = (zzyu) zzyiVar;
        String strZzd = zzyuVar.zzd();
        int length = strZzd.length();
        char c2 = 55296;
        if (strZzd.charAt(0) >= 55296) {
            int i32 = 1;
            while (true) {
                i = i32 + 1;
                if (strZzd.charAt(i32) < 55296) {
                    break;
                }
                i32 = i;
            }
        } else {
            i = 1;
        }
        int i33 = i + 1;
        int iCharAt2 = strZzd.charAt(i);
        if (iCharAt2 >= 55296) {
            int i34 = iCharAt2 & 8191;
            int i35 = 13;
            while (true) {
                i31 = i33 + 1;
                cCharAt13 = strZzd.charAt(i33);
                if (cCharAt13 < 55296) {
                    break;
                }
                i34 |= (cCharAt13 & 8191) << i35;
                i35 += 13;
                i33 = i31;
            }
            iCharAt2 = i34 | (cCharAt13 << i35);
            i33 = i31;
        }
        if (iCharAt2 == 0) {
            i3 = 0;
            i6 = 0;
            iCharAt = 0;
            i2 = 0;
            i4 = 0;
            i5 = 0;
            iArr = zza;
            i7 = 0;
        } else {
            int i36 = i33 + 1;
            int iCharAt3 = strZzd.charAt(i33);
            if (iCharAt3 >= 55296) {
                int i37 = iCharAt3 & 8191;
                int i38 = 13;
                while (true) {
                    i15 = i36 + 1;
                    cCharAt8 = strZzd.charAt(i36);
                    if (cCharAt8 < 55296) {
                        break;
                    }
                    i37 |= (cCharAt8 & 8191) << i38;
                    i38 += 13;
                    i36 = i15;
                }
                iCharAt3 = i37 | (cCharAt8 << i38);
                i36 = i15;
            }
            int i39 = i36 + 1;
            int iCharAt4 = strZzd.charAt(i36);
            if (iCharAt4 >= 55296) {
                int i40 = iCharAt4 & 8191;
                int i41 = 13;
                while (true) {
                    i14 = i39 + 1;
                    cCharAt7 = strZzd.charAt(i39);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i40 |= (cCharAt7 & 8191) << i41;
                    i41 += 13;
                    i39 = i14;
                }
                iCharAt4 = i40 | (cCharAt7 << i41);
                i39 = i14;
            }
            int i42 = i39 + 1;
            int iCharAt5 = strZzd.charAt(i39);
            if (iCharAt5 >= 55296) {
                int i43 = iCharAt5 & 8191;
                int i44 = 13;
                while (true) {
                    i13 = i42 + 1;
                    cCharAt6 = strZzd.charAt(i42);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i43 |= (cCharAt6 & 8191) << i44;
                    i44 += 13;
                    i42 = i13;
                }
                iCharAt5 = i43 | (cCharAt6 << i44);
                i42 = i13;
            }
            int i45 = i42 + 1;
            int iCharAt6 = strZzd.charAt(i42);
            if (iCharAt6 >= 55296) {
                int i46 = iCharAt6 & 8191;
                int i47 = 13;
                while (true) {
                    i12 = i45 + 1;
                    cCharAt5 = strZzd.charAt(i45);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i46 |= (cCharAt5 & 8191) << i47;
                    i47 += 13;
                    i45 = i12;
                }
                iCharAt6 = i46 | (cCharAt5 << i47);
                i45 = i12;
            }
            int i48 = i45 + 1;
            iCharAt = strZzd.charAt(i45);
            if (iCharAt >= 55296) {
                int i49 = iCharAt & 8191;
                int i50 = 13;
                while (true) {
                    i11 = i48 + 1;
                    cCharAt4 = strZzd.charAt(i48);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i49 |= (cCharAt4 & 8191) << i50;
                    i50 += 13;
                    i48 = i11;
                }
                iCharAt = i49 | (cCharAt4 << i50);
                i48 = i11;
            }
            int i51 = i48 + 1;
            int iCharAt7 = strZzd.charAt(i48);
            if (iCharAt7 >= 55296) {
                int i52 = iCharAt7 & 8191;
                int i53 = 13;
                while (true) {
                    i10 = i51 + 1;
                    cCharAt3 = strZzd.charAt(i51);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i52 |= (cCharAt3 & 8191) << i53;
                    i53 += 13;
                    i51 = i10;
                }
                iCharAt7 = i52 | (cCharAt3 << i53);
                i51 = i10;
            }
            int i54 = i51 + 1;
            int iCharAt8 = strZzd.charAt(i51);
            if (iCharAt8 >= 55296) {
                int i55 = iCharAt8 & 8191;
                int i56 = 13;
                while (true) {
                    i9 = i54 + 1;
                    cCharAt2 = strZzd.charAt(i54);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i55 |= (cCharAt2 & 8191) << i56;
                    i56 += 13;
                    i54 = i9;
                }
                iCharAt8 = i55 | (cCharAt2 << i56);
                i54 = i9;
            }
            int i57 = i54 + 1;
            int iCharAt9 = strZzd.charAt(i54);
            if (iCharAt9 >= 55296) {
                int i58 = iCharAt9 & 8191;
                int i59 = 13;
                while (true) {
                    i8 = i57 + 1;
                    cCharAt = strZzd.charAt(i57);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i58 |= (cCharAt & 8191) << i59;
                    i59 += 13;
                    i57 = i8;
                }
                iCharAt9 = i58 | (cCharAt << i59);
                i57 = i8;
            }
            int i60 = iCharAt3 + iCharAt3 + iCharAt4;
            int[] iArr2 = new int[iCharAt9 + iCharAt7 + iCharAt8];
            int i61 = iCharAt7;
            i2 = iCharAt5;
            i3 = i61;
            i4 = iCharAt6;
            i5 = iCharAt9;
            i6 = i60;
            iArr = iArr2;
            i7 = iCharAt3;
            i33 = i57;
        }
        Unsafe unsafe = zzb;
        Object[] objArrZze = zzyuVar.zze();
        Class<?> cls2 = zzyuVar.zza().getClass();
        int i62 = i5 + i3;
        int i63 = iCharAt + iCharAt;
        int[] iArr3 = new int[iCharAt * 3];
        Object[] objArr = new Object[i63];
        int i64 = i5;
        int i65 = i62;
        int i66 = 0;
        int i67 = 0;
        while (i33 < length) {
            int i68 = i33 + 1;
            int iCharAt10 = strZzd.charAt(i33);
            if (iCharAt10 >= c2) {
                int i69 = iCharAt10 & 8191;
                int i70 = i68;
                int i71 = 13;
                while (true) {
                    i30 = i70 + 1;
                    cCharAt12 = strZzd.charAt(i70);
                    if (cCharAt12 < c2) {
                        break;
                    }
                    i69 |= (cCharAt12 & 8191) << i71;
                    i71 += 13;
                    i70 = i30;
                }
                iCharAt10 = i69 | (cCharAt12 << i71);
                i16 = i30;
            } else {
                i16 = i68;
            }
            int i72 = i16 + 1;
            int iCharAt11 = strZzd.charAt(i16);
            if (iCharAt11 >= c2) {
                int i73 = iCharAt11 & 8191;
                int i74 = i72;
                int i75 = 13;
                while (true) {
                    i29 = i74 + 1;
                    cCharAt11 = strZzd.charAt(i74);
                    if (cCharAt11 < c2) {
                        break;
                    }
                    i73 |= (cCharAt11 & 8191) << i75;
                    i75 += 13;
                    i74 = i29;
                }
                iCharAt11 = i73 | (cCharAt11 << i75);
                i17 = i29;
            } else {
                i17 = i72;
            }
            if ((iCharAt11 & 1024) != 0) {
                iArr[i66] = i67;
                i66++;
            }
            int i76 = iCharAt11 & 255;
            zzyu zzyuVar2 = zzyuVar;
            int i77 = iCharAt11 & 2048;
            if (i76 >= 51) {
                int i78 = i17 + 1;
                int iCharAt12 = strZzd.charAt(i17);
                char c3 = 55296;
                if (iCharAt12 >= 55296) {
                    int i79 = iCharAt12 & 8191;
                    int i80 = i78;
                    int i81 = 13;
                    while (true) {
                        i28 = i80 + 1;
                        cCharAt10 = strZzd.charAt(i80);
                        if (cCharAt10 < c3) {
                            break;
                        }
                        i79 |= (cCharAt10 & 8191) << i81;
                        i81 += 13;
                        i80 = i28;
                        c3 = 55296;
                    }
                    iCharAt12 = i79 | (cCharAt10 << i81);
                    i23 = i28;
                } else {
                    i23 = i78;
                }
                int i82 = i23;
                int i83 = i76 - 51;
                if (i83 == 9 || i83 == 17) {
                    i24 = i6 + 1;
                    int i84 = i67 / 3;
                    objArr[i84 + i84 + 1] = objArrZze[i6];
                } else {
                    if (i83 != 12) {
                        i25 = i77;
                    } else if (zzyuVar2.zzc() == 1 || i77 != 0) {
                        i24 = i6 + 1;
                        int i85 = i67 / 3;
                        objArr[i85 + i85 + 1] = objArrZze[i6];
                    } else {
                        i25 = 0;
                    }
                    i26 = iCharAt12 + iCharAt12;
                    obj = objArrZze[i26];
                    int i86 = i25;
                    if (obj instanceof Field) {
                        fieldZzs2 = (Field) obj;
                    } else {
                        fieldZzs2 = zzs(cls2, (String) obj);
                        objArrZze[i26] = fieldZzs2;
                    }
                    int i87 = i7;
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzs2);
                    i27 = i26 + 1;
                    obj2 = objArrZze[i27];
                    i18 = i87;
                    if (obj2 instanceof Field) {
                        fieldZzs3 = (Field) obj2;
                    } else {
                        fieldZzs3 = zzs(cls2, (String) obj2);
                        objArrZze[i27] = fieldZzs3;
                    }
                    iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZzs3);
                    strZzd = strZzd;
                    i20 = i86;
                    i17 = i82;
                    i19 = 0;
                    c = 55296;
                }
                i6 = i24;
                i25 = i77;
                i26 = iCharAt12 + iCharAt12;
                obj = objArrZze[i26];
                int i88 = i25;
                if (obj instanceof Field) {
                    fieldZzs2 = (Field) obj;
                } else {
                    fieldZzs2 = zzs(cls2, (String) obj);
                    objArrZze[i26] = fieldZzs2;
                }
                int i89 = i7;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzs2);
                i27 = i26 + 1;
                obj2 = objArrZze[i27];
                i18 = i89;
                if (obj2 instanceof Field) {
                    fieldZzs3 = (Field) obj2;
                } else {
                    fieldZzs3 = zzs(cls2, (String) obj2);
                    objArrZze[i27] = fieldZzs3;
                }
                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZzs3);
                strZzd = strZzd;
                i20 = i88;
                i17 = i82;
                i19 = 0;
                c = 55296;
            } else {
                i18 = i7;
                int i90 = i6 + 1;
                Field fieldZzs4 = zzs(cls2, (String) objArrZze[i6]);
                if (i76 == 9 || i76 == 17) {
                    int i91 = i67 / 3;
                    objArr[i91 + i91 + 1] = fieldZzs4.getType();
                } else {
                    if (i76 != 27) {
                        if (i76 == 49) {
                            i6 += 2;
                            i22 = 1;
                        } else if (i76 == 12 || i76 == 30 || i76 == 44) {
                            if (zzyuVar2.zzc() == 1 || i77 != 0) {
                                i6 += 2;
                                int i92 = i67 / 3;
                                objArr[i92 + i92 + 1] = objArrZze[i90];
                            } else {
                                i6 = i90;
                                i77 = 0;
                            }
                        } else if (i76 == 50) {
                            int i93 = i6 + 2;
                            int i94 = i64 + 1;
                            iArr[i64] = i67;
                            int i95 = i67 / 3;
                            int i96 = i95 + i95;
                            objArr[i96] = objArrZze[i90];
                            if (i77 != 0) {
                                objArr[i96 + 1] = objArrZze[i93];
                                i6 += 3;
                                i64 = i94;
                            } else {
                                i6 = i93;
                                i64 = i94;
                                i77 = 0;
                            }
                        }
                        iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzs4);
                        if ((iCharAt11 & 4096) != 0 || i76 > 17) {
                            c = 55296;
                            iObjectFieldOffset2 = 1048575;
                            i19 = 0;
                        } else {
                            int i97 = i17 + 1;
                            int iCharAt13 = strZzd.charAt(i17);
                            if (iCharAt13 >= 55296) {
                                int i98 = iCharAt13 & 8191;
                                int i99 = 13;
                                while (true) {
                                    i21 = i97 + 1;
                                    cCharAt9 = strZzd.charAt(i97);
                                    if (cCharAt9 < 55296) {
                                        break;
                                    }
                                    i98 |= (cCharAt9 & 8191) << i99;
                                    i99 += 13;
                                    i97 = i21;
                                }
                                iCharAt13 = i98 | (cCharAt9 << i99);
                            } else {
                                i21 = i97;
                            }
                            int i100 = i18 + i18 + (iCharAt13 / 32);
                            Object obj3 = objArrZze[i100];
                            if (obj3 instanceof Field) {
                                fieldZzs = (Field) obj3;
                            } else {
                                fieldZzs = zzs(cls2, (String) obj3);
                                objArrZze[i100] = fieldZzs;
                            }
                            int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZzs);
                            i19 = iCharAt13 % 32;
                            i17 = i21;
                            c = 55296;
                            iObjectFieldOffset2 = iObjectFieldOffset3;
                        }
                        if (i76 >= 18 && i76 <= 49) {
                            iArr[i65] = iObjectFieldOffset;
                            i65++;
                        }
                        i20 = i77;
                    } else {
                        i22 = 1;
                        i6 += 2;
                    }
                    int i101 = i67 / 3;
                    objArr[i101 + i101 + i22] = objArrZze[i90];
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzs4);
                    if ((iCharAt11 & 4096) != 0) {
                        c = 55296;
                        iObjectFieldOffset2 = 1048575;
                        i19 = 0;
                    } else {
                        c = 55296;
                        iObjectFieldOffset2 = 1048575;
                        i19 = 0;
                    }
                    if (i76 >= 18) {
                        iArr[i65] = iObjectFieldOffset;
                        i65++;
                    }
                    i20 = i77;
                }
                i6 = i90;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzs4);
                if ((iCharAt11 & 4096) != 0) {
                    c = 55296;
                    iObjectFieldOffset2 = 1048575;
                    i19 = 0;
                } else {
                    c = 55296;
                    iObjectFieldOffset2 = 1048575;
                    i19 = 0;
                }
                if (i76 >= 18) {
                    iArr[i65] = iObjectFieldOffset;
                    i65++;
                }
                i20 = i77;
            }
            int i102 = i67 + 1;
            iArr3[i67] = iCharAt10;
            int i103 = i67 + 2;
            iArr3[i102] = ((iCharAt11 & 512) != 0 ? 536870912 : 0) | ((iCharAt11 & 256) != 0 ? 268435456 : 0) | (i20 != 0 ? Integer.MIN_VALUE : 0) | (i76 << 20) | iObjectFieldOffset;
            i67 += 3;
            iArr3[i103] = (i19 << 20) | iObjectFieldOffset2;
            i33 = i17;
            strZzd = strZzd;
            c2 = c;
            zzyuVar = zzyuVar2;
            length = length;
            i7 = i18;
        }
        return new zzyo(iArr3, objArr, i2, i4, zzyuVar.zza(), false, iArr, i5, i62, zzyqVar, zzxyVar, zzzhVar, zzwxVar, zzygVar);
    }

    private static double zzj(Object obj, long j) {
        return ((Double) zzzo.zzf(obj, j)).doubleValue();
    }

    private static float zzk(Object obj, long j) {
        return ((Float) zzzo.zzf(obj, j)).floatValue();
    }

    private static int zzl(Object obj, long j) {
        return ((Integer) zzzo.zzf(obj, j)).intValue();
    }

    private final int zzm(int i) {
        return this.zzc[i + 2];
    }

    private static int zzn(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzo(int i) {
        return this.zzc[i + 1];
    }

    private static long zzp(Object obj, long j) {
        return ((Long) zzzo.zzf(obj, j)).longValue();
    }

    private final zzyv zzq(int i) {
        Object[] objArr = this.zzd;
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzyv zzyvVar = (zzyv) objArr[i3];
        if (zzyvVar != null) {
            return zzyvVar;
        }
        zzyv zzyvVarZzb = zzys.zza().zzb((Class) objArr[i3 + 1]);
        objArr[i3] = zzyvVarZzb;
        return zzyvVarZzb;
    }

    private final Object zzr(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private static Field zzs(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields), e);
        }
    }

    private final void zzt(Object obj, Object obj2, int i) {
        if (zzy(obj2, i)) {
            int iZzo = zzo(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = iZzo;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzyv zzyvVarZzq = zzq(i);
            if (!zzy(obj, i)) {
                if (zzB(object)) {
                    Object objZzc = zzyvVarZzq.zzc();
                    zzyvVarZzq.zze(objZzc, object);
                    unsafe.putObject(obj, j, objZzc);
                } else {
                    unsafe.putObject(obj, j, object);
                }
                zzv(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzB(object2)) {
                Object objZzc2 = zzyvVarZzq.zzc();
                zzyvVarZzq.zze(objZzc2, object2);
                unsafe.putObject(obj, j, objZzc2);
                object2 = objZzc2;
            }
            zzyvVarZzq.zze(object2, object);
        }
    }

    private final void zzu(Object obj, Object obj2, int i) {
        int[] iArr = this.zzc;
        int i2 = iArr[i];
        if (zzC(obj2, i2, i)) {
            int iZzo = zzo(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = iZzo;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + iArr[i] + " is present but null: " + obj2.toString());
            }
            zzyv zzyvVarZzq = zzq(i);
            if (!zzC(obj, i2, i)) {
                if (zzB(object)) {
                    Object objZzc = zzyvVarZzq.zzc();
                    zzyvVarZzq.zze(objZzc, object);
                    unsafe.putObject(obj, j, objZzc);
                } else {
                    unsafe.putObject(obj, j, object);
                }
                zzw(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzB(object2)) {
                Object objZzc2 = zzyvVarZzq.zzc();
                zzyvVarZzq.zze(objZzc2, object2);
                unsafe.putObject(obj, j, objZzc2);
                object2 = objZzc2;
            }
            zzyvVarZzq.zze(object2, object);
        }
    }

    private final void zzv(Object obj, int i) {
        int iZzm = zzm(i);
        long j = 1048575 & iZzm;
        if (j == 1048575) {
            return;
        }
        zzzo.zzq(obj, j, (1 << (iZzm >>> 20)) | zzzo.zzc(obj, j));
    }

    private final void zzw(Object obj, int i, int i2) {
        zzzo.zzq(obj, zzm(i2) & 1048575, i);
    }

    private final boolean zzx(Object obj, Object obj2, int i) {
        return zzy(obj, i) == zzy(obj2, i);
    }

    private final boolean zzy(Object obj, int i) {
        int iZzm = zzm(i);
        long j = iZzm & 1048575;
        if (j != 1048575) {
            return ((1 << (iZzm >>> 20)) & zzzo.zzc(obj, j)) != 0;
        }
        int iZzo = zzo(i);
        long j2 = iZzo & 1048575;
        switch (zzn(iZzo)) {
            case 0:
                return Double.doubleToRawLongBits(zzzo.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzzo.zzb(obj, j2)) != 0;
            case 2:
                return zzzo.zzd(obj, j2) != 0;
            case 3:
                return zzzo.zzd(obj, j2) != 0;
            case 4:
                return zzzo.zzc(obj, j2) != 0;
            case 5:
                return zzzo.zzd(obj, j2) != 0;
            case 6:
                return zzzo.zzc(obj, j2) != 0;
            case 7:
                return zzzo.zzw(obj, j2);
            case 8:
                Object objZzf = zzzo.zzf(obj, j2);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zzwn) {
                    return !zzwn.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzzo.zzf(obj, j2) != null;
            case 10:
                return !zzwn.zzb.equals(zzzo.zzf(obj, j2));
            case 11:
                return zzzo.zzc(obj, j2) != 0;
            case 12:
                return zzzo.zzc(obj, j2) != 0;
            case 13:
                return zzzo.zzc(obj, j2) != 0;
            case 14:
                return zzzo.zzd(obj, j2) != 0;
            case 15:
                return zzzo.zzc(obj, j2) != 0;
            case 16:
                return zzzo.zzd(obj, j2) != 0;
            case 17:
                return zzzo.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzz(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzy(obj, i);
        }
        return (i3 & i4) != 0;
    }

    /* JADX WARN: Code duplicated, block: B:141:0x0385  */
    /* JADX WARN: Code duplicated, block: B:211:0x0546  */
    @Override // com.google.android.gms.internal.cast.zzyv
    public final int zza(Object obj) {
        int i;
        int iZzz;
        int iZzz2;
        int iZzA;
        int iZzz3;
        int iZzz4;
        int iZzz5;
        int iZzd;
        int iZzz6;
        int iZzh;
        int iZzg;
        int size;
        int iZzl;
        int iZzz7;
        int iZzz8;
        int iZzz9;
        int iZzA2;
        int iZze;
        int iZzz10;
        int iZzz11;
        int iZzw;
        int iZzz12;
        int iZzz13;
        int iZzz14;
        int iZzd2;
        int iZzz15;
        zzyo<T> zzyoVar = this;
        Unsafe unsafe = zzb;
        int i2 = 0;
        int i3 = 0;
        int iZzz16 = 0;
        int i4 = 1048575;
        while (true) {
            int[] iArr = zzyoVar.zzc;
            if (i2 >= iArr.length) {
                int iZza = iZzz16 + ((zzxi) obj).zzc.zza();
                if (!zzyoVar.zzf) {
                    return iZza;
                }
                zzzd zzzdVar = ((zzxg) obj).zzb.zza;
                int iZzc = zzzdVar.zzc();
                int iZzb = 0;
                for (int i5 = 0; i5 < iZzc; i5++) {
                    Map.Entry entryZzg = zzzdVar.zzg(i5);
                    iZzb += zzxb.zzb((zzxa) ((zzyz) entryZzg).zza(), entryZzg.getValue());
                }
                for (Map.Entry entry : zzzdVar.zzd()) {
                    iZzb += zzxb.zzb((zzxa) entry.getKey(), entry.getValue());
                }
                return iZza + iZzb;
            }
            int iZzo = zzyoVar.zzo(i2);
            int iZzn = zzn(iZzo);
            int i6 = iArr[i2];
            int i7 = iArr[i2 + 2];
            int i8 = i7 & 1048575;
            if (iZzn <= 17) {
                if (i8 != i4) {
                    i3 = i8 == 1048575 ? 0 : unsafe.getInt(obj, i8);
                    i4 = i8;
                }
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            int i9 = iZzo & 1048575;
            if (iZzn >= zzxc.DOUBLE_LIST_PACKED.zza()) {
                zzxc.SINT64_LIST_PACKED.zza();
            }
            long j = i9;
            switch (iZzn) {
                case 0:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        iZzz16 += zzwu.zzz(i6 << 3) + 8;
                    }
                    i2 += 3;
                    break;
                case 1:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        iZzz = zzwu.zzz(i6 << 3);
                        iZzz4 = iZzz + 4;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 2:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        long j2 = unsafe.getLong(obj, j);
                        iZzz2 = zzwu.zzz(i6 << 3);
                        iZzA = zzwu.zzA(j2);
                        iZzz4 = iZzz2 + iZzA;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 3:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        long j3 = unsafe.getLong(obj, j);
                        iZzz2 = zzwu.zzz(i6 << 3);
                        iZzA = zzwu.zzA(j3);
                        iZzz4 = iZzz2 + iZzA;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 4:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        long j4 = unsafe.getInt(obj, j);
                        iZzz2 = zzwu.zzz(i6 << 3);
                        iZzA = zzwu.zzA(j4);
                        iZzz4 = iZzz2 + iZzA;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 5:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        iZzz3 = zzwu.zzz(i6 << 3);
                        iZzz4 = iZzz3 + 8;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 6:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        iZzz = zzwu.zzz(i6 << 3);
                        iZzz4 = iZzz + 4;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 7:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        iZzz4 = zzwu.zzz(i6 << 3) + 1;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 8:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        int i10 = i6 << 3;
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzwn) {
                            iZzz5 = zzwu.zzz(i10);
                            iZzd = ((zzwn) object).zzd();
                            iZzz6 = zzwu.zzz(iZzd);
                            iZzz4 = iZzz5 + iZzz6 + iZzd;
                            iZzz16 += iZzz4;
                        } else {
                            iZzz2 = zzwu.zzz(i10);
                            iZzA = zzwu.zzy((String) object);
                            iZzz4 = iZzz2 + iZzA;
                            iZzz16 += iZzz4;
                        }
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 9:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        iZzh = zzyx.zzh(i6, unsafe.getObject(obj, j), zzyoVar.zzq(i2));
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 10:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        zzwn zzwnVar = (zzwn) unsafe.getObject(obj, j);
                        iZzz5 = zzwu.zzz(i6 << 3);
                        iZzd = zzwnVar.zzd();
                        iZzz6 = zzwu.zzz(iZzd);
                        iZzz4 = iZzz5 + iZzz6 + iZzd;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 11:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        int i11 = unsafe.getInt(obj, j);
                        iZzz2 = zzwu.zzz(i6 << 3);
                        iZzA = zzwu.zzz(i11);
                        iZzz4 = iZzz2 + iZzA;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 12:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        long j5 = unsafe.getInt(obj, j);
                        iZzz2 = zzwu.zzz(i6 << 3);
                        iZzA = zzwu.zzA(j5);
                        iZzz4 = iZzz2 + iZzA;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 13:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        iZzz = zzwu.zzz(i6 << 3);
                        iZzz4 = iZzz + 4;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 14:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        iZzz3 = zzwu.zzz(i6 << 3);
                        iZzz4 = iZzz3 + 8;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 15:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        int i12 = unsafe.getInt(obj, j);
                        iZzz2 = zzwu.zzz(i6 << 3);
                        iZzA = zzwu.zzz((i12 >> 31) ^ (i12 + i12));
                        iZzz4 = iZzz2 + iZzA;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 16:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        long j6 = unsafe.getLong(obj, j);
                        iZzz2 = zzwu.zzz(i6 << 3);
                        iZzA = zzwu.zzA((j6 >> 63) ^ (j6 + j6));
                        iZzz4 = iZzz2 + iZzA;
                        iZzz16 += iZzz4;
                    }
                    zzyoVar = this;
                    i2 += 3;
                    break;
                case 17:
                    if (zzyoVar.zzz(obj, i2, i4, i3, i)) {
                        iZzh = zzwu.zzw(i6, (zzyl) unsafe.getObject(obj, j), zzyoVar.zzq(i2));
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 18:
                    iZzh = zzyx.zzd(i6, (List) unsafe.getObject(obj, j), false);
                    iZzz16 += iZzh;
                    i2 += 3;
                    break;
                case 19:
                    iZzh = zzyx.zzb(i6, (List) unsafe.getObject(obj, j), false);
                    iZzz16 += iZzh;
                    i2 += 3;
                    break;
                case 20:
                    List list = (List) unsafe.getObject(obj, j);
                    int i13 = zzyx.zza;
                    if (list.size() == 0) {
                        iZzg = 0;
                    } else {
                        iZzg = zzyx.zzg(list) + (list.size() * zzwu.zzz(i6 << 3));
                    }
                    iZzz16 += iZzg;
                    i2 += 3;
                    break;
                case 21:
                    List list2 = (List) unsafe.getObject(obj, j);
                    int i14 = zzyx.zza;
                    size = list2.size();
                    if (size == 0) {
                        iZzh = 0;
                    } else {
                        iZzl = zzyx.zzl(list2);
                        iZzz7 = zzwu.zzz(i6 << 3);
                        iZzA2 = size * iZzz7;
                        iZzh = iZzl + iZzA2;
                    }
                    iZzz16 += iZzh;
                    i2 += 3;
                    break;
                case 22:
                    List list3 = (List) unsafe.getObject(obj, j);
                    int i15 = zzyx.zza;
                    size = list3.size();
                    if (size == 0) {
                        iZzh = 0;
                    } else {
                        iZzl = zzyx.zzf(list3);
                        iZzz7 = zzwu.zzz(i6 << 3);
                        iZzA2 = size * iZzz7;
                        iZzh = iZzl + iZzA2;
                    }
                    iZzz16 += iZzh;
                    i2 += 3;
                    break;
                case 23:
                    iZzh = zzyx.zzd(i6, (List) unsafe.getObject(obj, j), false);
                    iZzz16 += iZzh;
                    i2 += 3;
                    break;
                case 24:
                    iZzh = zzyx.zzb(i6, (List) unsafe.getObject(obj, j), false);
                    iZzz16 += iZzh;
                    i2 += 3;
                    break;
                case 25:
                    List list4 = (List) unsafe.getObject(obj, j);
                    int i16 = zzyx.zza;
                    int size2 = list4.size();
                    if (size2 == 0) {
                        iZzh = 0;
                    } else {
                        iZzh = size2 * (zzwu.zzz(i6 << 3) + 1);
                    }
                    iZzz16 += iZzh;
                    i2 += 3;
                    break;
                case 26:
                    List list5 = (List) unsafe.getObject(obj, j);
                    int i17 = zzyx.zza;
                    int size3 = list5.size();
                    if (size3 == 0) {
                        iZzg = 0;
                    } else {
                        iZzg = zzwu.zzz(i6 << 3) * size3;
                        if (list5 instanceof zzxx) {
                            zzxx zzxxVar = (zzxx) list5;
                            for (int i18 = 0; i18 < size3; i18++) {
                                Object objZza = zzxxVar.zza();
                                if (objZza instanceof zzwn) {
                                    int iZzd3 = ((zzwn) objZza).zzd();
                                    iZzg += zzwu.zzz(iZzd3) + iZzd3;
                                } else {
                                    iZzg += zzwu.zzy((String) objZza);
                                }
                            }
                        } else {
                            for (int i19 = 0; i19 < size3; i19++) {
                                Object obj2 = list5.get(i19);
                                if (obj2 instanceof zzwn) {
                                    int iZzd4 = ((zzwn) obj2).zzd();
                                    iZzg += zzwu.zzz(iZzd4) + iZzd4;
                                } else {
                                    iZzg += zzwu.zzy((String) obj2);
                                }
                            }
                        }
                    }
                    iZzz16 += iZzg;
                    i2 += 3;
                    break;
                case 27:
                    List list6 = (List) unsafe.getObject(obj, j);
                    zzyv zzyvVarZzq = zzyoVar.zzq(i2);
                    int i20 = zzyx.zza;
                    int size4 = list6.size();
                    if (size4 == 0) {
                        iZzz8 = 0;
                    } else {
                        iZzz8 = zzwu.zzz(i6 << 3) * size4;
                        for (int i21 = 0; i21 < size4; i21++) {
                            Object obj3 = list6.get(i21);
                            if (obj3 instanceof zzxw) {
                                int iZza2 = ((zzxw) obj3).zza();
                                iZzz8 += zzwu.zzz(iZza2) + iZza2;
                            } else {
                                iZzz8 += zzwu.zzx((zzyl) obj3, zzyvVarZzq);
                            }
                        }
                    }
                    iZzz16 += iZzz8;
                    i2 += 3;
                    break;
                case 28:
                    List list7 = (List) unsafe.getObject(obj, j);
                    int i22 = zzyx.zza;
                    int size5 = list7.size();
                    if (size5 == 0) {
                        iZzz9 = 0;
                    } else {
                        iZzz9 = size5 * zzwu.zzz(i6 << 3);
                        for (int i23 = 0; i23 < list7.size(); i23++) {
                            int iZzd5 = ((zzwn) list7.get(i23)).zzd();
                            iZzz9 += zzwu.zzz(iZzd5) + iZzd5;
                        }
                    }
                    iZzz16 += iZzz9;
                    i2 += 3;
                    break;
                case 29:
                    List list8 = (List) unsafe.getObject(obj, j);
                    int i24 = zzyx.zza;
                    size = list8.size();
                    if (size == 0) {
                        iZzh = 0;
                    } else {
                        iZzl = zzyx.zzk(list8);
                        iZzz7 = zzwu.zzz(i6 << 3);
                        iZzA2 = size * iZzz7;
                        iZzh = iZzl + iZzA2;
                    }
                    iZzz16 += iZzh;
                    i2 += 3;
                    break;
                case 30:
                    List list9 = (List) unsafe.getObject(obj, j);
                    int i25 = zzyx.zza;
                    size = list9.size();
                    if (size == 0) {
                        iZzh = 0;
                    } else {
                        iZzl = zzyx.zza(list9);
                        iZzz7 = zzwu.zzz(i6 << 3);
                        iZzA2 = size * iZzz7;
                        iZzh = iZzl + iZzA2;
                    }
                    iZzz16 += iZzh;
                    i2 += 3;
                    break;
                case 31:
                    iZzh = zzyx.zzb(i6, (List) unsafe.getObject(obj, j), false);
                    iZzz16 += iZzh;
                    i2 += 3;
                    break;
                case 32:
                    iZzh = zzyx.zzd(i6, (List) unsafe.getObject(obj, j), false);
                    iZzz16 += iZzh;
                    i2 += 3;
                    break;
                case 33:
                    List list10 = (List) unsafe.getObject(obj, j);
                    int i26 = zzyx.zza;
                    size = list10.size();
                    if (size == 0) {
                        iZzh = 0;
                    } else {
                        iZzl = zzyx.zzi(list10);
                        iZzz7 = zzwu.zzz(i6 << 3);
                        iZzA2 = size * iZzz7;
                        iZzh = iZzl + iZzA2;
                    }
                    iZzz16 += iZzh;
                    i2 += 3;
                    break;
                case 34:
                    List list11 = (List) unsafe.getObject(obj, j);
                    int i27 = zzyx.zza;
                    size = list11.size();
                    if (size == 0) {
                        iZzh = 0;
                    } else {
                        iZzl = zzyx.zzj(list11);
                        iZzz7 = zzwu.zzz(i6 << 3);
                        iZzA2 = size * iZzz7;
                        iZzh = iZzl + iZzA2;
                    }
                    iZzz16 += iZzh;
                    i2 += 3;
                    break;
                case 35:
                    iZze = zzyx.zze((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 36:
                    iZze = zzyx.zzc((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 37:
                    iZze = zzyx.zzg((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 38:
                    iZze = zzyx.zzl((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 39:
                    iZze = zzyx.zzf((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 40:
                    iZze = zzyx.zze((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 41:
                    iZze = zzyx.zzc((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 42:
                    List list12 = (List) unsafe.getObject(obj, j);
                    int i28 = zzyx.zza;
                    iZze = list12.size();
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 43:
                    iZze = zzyx.zzk((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 44:
                    iZze = zzyx.zza((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 45:
                    iZze = zzyx.zzc((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 46:
                    iZze = zzyx.zze((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 47:
                    iZze = zzyx.zzi((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 48:
                    iZze = zzyx.zzj((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzz10 = zzwu.zzz(i6 << 3);
                        iZzz11 = zzwu.zzz(iZze);
                        iZzz9 = iZzz10 + iZzz11 + iZze;
                        iZzz16 += iZzz9;
                    }
                    i2 += 3;
                    break;
                case 49:
                    List list13 = (List) unsafe.getObject(obj, j);
                    zzyv zzyvVarZzq2 = zzyoVar.zzq(i2);
                    int i29 = zzyx.zza;
                    int size6 = list13.size();
                    if (size6 == 0) {
                        iZzw = 0;
                    } else {
                        iZzw = 0;
                        for (int i30 = 0; i30 < size6; i30++) {
                            iZzw += zzwu.zzw(i6, (zzyl) list13.get(i30), zzyvVarZzq2);
                        }
                    }
                    iZzz16 += iZzw;
                    i2 += 3;
                    break;
                case 50:
                    zzyf zzyfVar = (zzyf) unsafe.getObject(obj, j);
                    if (zzyfVar.isEmpty()) {
                        continue;
                    } else {
                        Iterator it = zzyfVar.entrySet().iterator();
                        if (it.hasNext()) {
                            Map.Entry entry2 = (Map.Entry) it.next();
                            entry2.getKey();
                            entry2.getValue();
                            throw null;
                        }
                    }
                    i2 += 3;
                case 51:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        iZzz12 = zzwu.zzz(i6 << 3);
                        iZzh = iZzz12 + 8;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 52:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        iZzz13 = zzwu.zzz(i6 << 3);
                        iZzh = iZzz13 + 4;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 53:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        long jZzp = zzp(obj, j);
                        iZzl = zzwu.zzz(i6 << 3);
                        iZzA2 = zzwu.zzA(jZzp);
                        iZzh = iZzl + iZzA2;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 54:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        long jZzp2 = zzp(obj, j);
                        iZzl = zzwu.zzz(i6 << 3);
                        iZzA2 = zzwu.zzA(jZzp2);
                        iZzh = iZzl + iZzA2;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 55:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        long jZzl = zzl(obj, j);
                        iZzl = zzwu.zzz(i6 << 3);
                        iZzA2 = zzwu.zzA(jZzl);
                        iZzh = iZzl + iZzA2;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 56:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        iZzz12 = zzwu.zzz(i6 << 3);
                        iZzh = iZzz12 + 8;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 57:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        iZzz13 = zzwu.zzz(i6 << 3);
                        iZzh = iZzz13 + 4;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 58:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        iZzh = zzwu.zzz(i6 << 3) + 1;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 59:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        int i31 = i6 << 3;
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzwn) {
                            iZzz14 = zzwu.zzz(i31);
                            iZzd2 = ((zzwn) object2).zzd();
                            iZzz15 = zzwu.zzz(iZzd2);
                            iZzh = iZzz14 + iZzz15 + iZzd2;
                            iZzz16 += iZzh;
                        } else {
                            iZzl = zzwu.zzz(i31);
                            iZzA2 = zzwu.zzy((String) object2);
                            iZzh = iZzl + iZzA2;
                            iZzz16 += iZzh;
                        }
                    }
                    i2 += 3;
                    break;
                case 60:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        iZzh = zzyx.zzh(i6, unsafe.getObject(obj, j), zzyoVar.zzq(i2));
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 61:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        zzwn zzwnVar2 = (zzwn) unsafe.getObject(obj, j);
                        iZzz14 = zzwu.zzz(i6 << 3);
                        iZzd2 = zzwnVar2.zzd();
                        iZzz15 = zzwu.zzz(iZzd2);
                        iZzh = iZzz14 + iZzz15 + iZzd2;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 62:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        int iZzl2 = zzl(obj, j);
                        iZzl = zzwu.zzz(i6 << 3);
                        iZzA2 = zzwu.zzz(iZzl2);
                        iZzh = iZzl + iZzA2;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 63:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        long jZzl2 = zzl(obj, j);
                        iZzl = zzwu.zzz(i6 << 3);
                        iZzA2 = zzwu.zzA(jZzl2);
                        iZzh = iZzl + iZzA2;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 64:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        iZzz13 = zzwu.zzz(i6 << 3);
                        iZzh = iZzz13 + 4;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 65:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        iZzz12 = zzwu.zzz(i6 << 3);
                        iZzh = iZzz12 + 8;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 66:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        int iZzl3 = zzl(obj, j);
                        iZzl = zzwu.zzz(i6 << 3);
                        iZzA2 = zzwu.zzz((iZzl3 >> 31) ^ (iZzl3 + iZzl3));
                        iZzh = iZzl + iZzA2;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 67:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        long jZzp3 = zzp(obj, j);
                        iZzl = zzwu.zzz(i6 << 3);
                        iZzA2 = zzwu.zzA((jZzp3 >> 63) ^ (jZzp3 + jZzp3));
                        iZzh = iZzl + iZzA2;
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                case 68:
                    if (zzyoVar.zzC(obj, i6, i2)) {
                        iZzh = zzwu.zzw(i6, (zzyl) unsafe.getObject(obj, j), zzyoVar.zzq(i2));
                        iZzz16 += iZzh;
                    }
                    i2 += 3;
                    break;
                default:
                    i2 += 3;
                    break;
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.zzyv
    public final int zzb(Object obj) {
        int i;
        long jDoubleToLongBits;
        int iFloatToIntBits;
        int i2;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int[] iArr = this.zzc;
            if (i3 >= iArr.length) {
                int iHashCode = (i4 * 53) + ((zzxi) obj).zzc.hashCode();
                return this.zzf ? (iHashCode * 53) + ((zzxg) obj).zzb.zza.hashCode() : iHashCode;
            }
            int iZzo = zzo(i3);
            int i5 = 1048575 & iZzo;
            int iZzn = zzn(iZzo);
            int i6 = iArr[i3];
            long j = i5;
            int iHashCode2 = 37;
            switch (iZzn) {
                case 0:
                    i = i4 * 53;
                    jDoubleToLongBits = Double.doubleToLongBits(zzzo.zza(obj, j));
                    byte[] bArr = zzxo.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i4 = i + iFloatToIntBits;
                    break;
                case 1:
                    i = i4 * 53;
                    iFloatToIntBits = Float.floatToIntBits(zzzo.zzb(obj, j));
                    i4 = i + iFloatToIntBits;
                    break;
                case 2:
                    i = i4 * 53;
                    jDoubleToLongBits = zzzo.zzd(obj, j);
                    byte[] bArr2 = zzxo.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i4 = i + iFloatToIntBits;
                    break;
                case 3:
                    i = i4 * 53;
                    jDoubleToLongBits = zzzo.zzd(obj, j);
                    byte[] bArr3 = zzxo.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i4 = i + iFloatToIntBits;
                    break;
                case 4:
                    i = i4 * 53;
                    iFloatToIntBits = zzzo.zzc(obj, j);
                    i4 = i + iFloatToIntBits;
                    break;
                case 5:
                    i = i4 * 53;
                    jDoubleToLongBits = zzzo.zzd(obj, j);
                    byte[] bArr4 = zzxo.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i4 = i + iFloatToIntBits;
                    break;
                case 6:
                    i = i4 * 53;
                    iFloatToIntBits = zzzo.zzc(obj, j);
                    i4 = i + iFloatToIntBits;
                    break;
                case 7:
                    i = i4 * 53;
                    iFloatToIntBits = zzxo.zza(zzzo.zzw(obj, j));
                    i4 = i + iFloatToIntBits;
                    break;
                case 8:
                    i = i4 * 53;
                    iFloatToIntBits = ((String) zzzo.zzf(obj, j)).hashCode();
                    i4 = i + iFloatToIntBits;
                    break;
                case 9:
                    i2 = i4 * 53;
                    Object objZzf = zzzo.zzf(obj, j);
                    if (objZzf != null) {
                        iHashCode2 = objZzf.hashCode();
                    }
                    i4 = i2 + iHashCode2;
                    break;
                case 10:
                    i = i4 * 53;
                    iFloatToIntBits = zzzo.zzf(obj, j).hashCode();
                    i4 = i + iFloatToIntBits;
                    break;
                case 11:
                    i = i4 * 53;
                    iFloatToIntBits = zzzo.zzc(obj, j);
                    i4 = i + iFloatToIntBits;
                    break;
                case 12:
                    i = i4 * 53;
                    iFloatToIntBits = zzzo.zzc(obj, j);
                    i4 = i + iFloatToIntBits;
                    break;
                case 13:
                    i = i4 * 53;
                    iFloatToIntBits = zzzo.zzc(obj, j);
                    i4 = i + iFloatToIntBits;
                    break;
                case 14:
                    i = i4 * 53;
                    jDoubleToLongBits = zzzo.zzd(obj, j);
                    byte[] bArr5 = zzxo.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i4 = i + iFloatToIntBits;
                    break;
                case 15:
                    i = i4 * 53;
                    iFloatToIntBits = zzzo.zzc(obj, j);
                    i4 = i + iFloatToIntBits;
                    break;
                case 16:
                    i = i4 * 53;
                    jDoubleToLongBits = zzzo.zzd(obj, j);
                    byte[] bArr6 = zzxo.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i4 = i + iFloatToIntBits;
                    break;
                case 17:
                    i2 = i4 * 53;
                    Object objZzf2 = zzzo.zzf(obj, j);
                    if (objZzf2 != null) {
                        iHashCode2 = objZzf2.hashCode();
                    }
                    i4 = i2 + iHashCode2;
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
                    i = i4 * 53;
                    iFloatToIntBits = zzzo.zzf(obj, j).hashCode();
                    i4 = i + iFloatToIntBits;
                    break;
                case 50:
                    i = i4 * 53;
                    iFloatToIntBits = zzzo.zzf(obj, j).hashCode();
                    i4 = i + iFloatToIntBits;
                    break;
                case 51:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        jDoubleToLongBits = Double.doubleToLongBits(zzj(obj, j));
                        byte[] bArr7 = zzxo.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 52:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        iFloatToIntBits = Float.floatToIntBits(zzk(obj, j));
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 53:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        jDoubleToLongBits = zzp(obj, j);
                        byte[] bArr8 = zzxo.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 54:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        jDoubleToLongBits = zzp(obj, j);
                        byte[] bArr9 = zzxo.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 55:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        iFloatToIntBits = zzl(obj, j);
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 56:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        jDoubleToLongBits = zzp(obj, j);
                        byte[] bArr10 = zzxo.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 57:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        iFloatToIntBits = zzl(obj, j);
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 58:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        iFloatToIntBits = zzxo.zza(zzD(obj, j));
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 59:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        iFloatToIntBits = ((String) zzzo.zzf(obj, j)).hashCode();
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 60:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        iFloatToIntBits = zzzo.zzf(obj, j).hashCode();
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 61:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        iFloatToIntBits = zzzo.zzf(obj, j).hashCode();
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 62:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        iFloatToIntBits = zzl(obj, j);
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 63:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        iFloatToIntBits = zzl(obj, j);
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 64:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        iFloatToIntBits = zzl(obj, j);
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 65:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        jDoubleToLongBits = zzp(obj, j);
                        byte[] bArr11 = zzxo.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 66:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        iFloatToIntBits = zzl(obj, j);
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 67:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        jDoubleToLongBits = zzp(obj, j);
                        byte[] bArr12 = zzxo.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i4 = i + iFloatToIntBits;
                    }
                    break;
                case 68:
                    if (zzC(obj, i6, i3)) {
                        i = i4 * 53;
                        iFloatToIntBits = zzzo.zzf(obj, j).hashCode();
                        i4 = i + iFloatToIntBits;
                    }
                    break;
            }
            i3 += 3;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzyv
    public final Object zzc() {
        return ((zzxi) this.zze).zzA();
    }

    /* JADX WARN: Code duplicated, block: B:26:0x006f  */
    /* JADX WARN: Code duplicated, block: B:28:0x0075  */
    /* JADX WARN: Code duplicated, block: B:41:0x0082 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.cast.zzyv
    public final void zzd(Object obj) {
        if (zzB(obj)) {
            if (obj instanceof zzxi) {
                zzxi zzxiVar = (zzxi) obj;
                zzxiVar.zzK(Integer.MAX_VALUE);
                zzxiVar.zza = 0;
                zzxiVar.zzI();
            }
            int[] iArr = this.zzc;
            for (int i = 0; i < iArr.length; i += 3) {
                int iZzo = zzo(i);
                int i2 = 1048575 & iZzo;
                int iZzn = zzn(iZzo);
                long j = i2;
                if (iZzn != 9) {
                    if (iZzn != 60 && iZzn != 68) {
                        switch (iZzn) {
                            case 17:
                                if (zzy(obj, i)) {
                                    zzq(i).zzd(zzb.getObject(obj, j));
                                }
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
                                ((zzxn) zzzo.zzf(obj, j)).zzb();
                                break;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j);
                                if (object != null) {
                                    ((zzyf) object).zzb();
                                    unsafe.putObject(obj, j, object);
                                }
                                break;
                        }
                    } else if (zzC(obj, iArr[i], i)) {
                        zzq(i).zzd(zzb.getObject(obj, j));
                    }
                } else if (zzy(obj, i)) {
                    zzq(i).zzd(zzb.getObject(obj, j));
                }
            }
            this.zzi.zza(obj);
            if (this.zzf) {
                this.zzj.zza(obj);
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.zzyv
    public final void zze(Object obj, Object obj2) {
        if (!zzB(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
        obj2.getClass();
        int i = 0;
        while (true) {
            int[] iArr = this.zzc;
            if (i >= iArr.length) {
                zzyx.zzo(this.zzi, obj, obj2);
                if (this.zzf) {
                    zzyx.zzn(this.zzj, obj, obj2);
                    return;
                }
                return;
            }
            int iZzo = zzo(i);
            int i2 = 1048575 & iZzo;
            int iZzn = zzn(iZzo);
            int i3 = iArr[i];
            long j = i2;
            switch (iZzn) {
                case 0:
                    if (zzy(obj2, i)) {
                        zzzo.zzo(obj, j, zzzo.zza(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 1:
                    if (zzy(obj2, i)) {
                        zzzo.zzp(obj, j, zzzo.zzb(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 2:
                    if (zzy(obj2, i)) {
                        zzzo.zzr(obj, j, zzzo.zzd(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 3:
                    if (zzy(obj2, i)) {
                        zzzo.zzr(obj, j, zzzo.zzd(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 4:
                    if (zzy(obj2, i)) {
                        zzzo.zzq(obj, j, zzzo.zzc(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 5:
                    if (zzy(obj2, i)) {
                        zzzo.zzr(obj, j, zzzo.zzd(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 6:
                    if (zzy(obj2, i)) {
                        zzzo.zzq(obj, j, zzzo.zzc(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 7:
                    if (zzy(obj2, i)) {
                        zzzo.zzm(obj, j, zzzo.zzw(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 8:
                    if (zzy(obj2, i)) {
                        zzzo.zzs(obj, j, zzzo.zzf(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 9:
                    zzt(obj, obj2, i);
                    break;
                case 10:
                    if (zzy(obj2, i)) {
                        zzzo.zzs(obj, j, zzzo.zzf(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 11:
                    if (zzy(obj2, i)) {
                        zzzo.zzq(obj, j, zzzo.zzc(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 12:
                    if (zzy(obj2, i)) {
                        zzzo.zzq(obj, j, zzzo.zzc(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 13:
                    if (zzy(obj2, i)) {
                        zzzo.zzq(obj, j, zzzo.zzc(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 14:
                    if (zzy(obj2, i)) {
                        zzzo.zzr(obj, j, zzzo.zzd(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 15:
                    if (zzy(obj2, i)) {
                        zzzo.zzq(obj, j, zzzo.zzc(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 16:
                    if (zzy(obj2, i)) {
                        zzzo.zzr(obj, j, zzzo.zzd(obj2, j));
                        zzv(obj, i);
                    }
                    break;
                case 17:
                    zzt(obj, obj2, i);
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
                    zzxn zzxnVarZzd = (zzxn) zzzo.zzf(obj, j);
                    zzxn zzxnVar = (zzxn) zzzo.zzf(obj2, j);
                    int size = zzxnVarZzd.size();
                    int size2 = zzxnVar.size();
                    if (size > 0 && size2 > 0) {
                        if (!zzxnVarZzd.zzc()) {
                            zzxnVarZzd = zzxnVarZzd.zzd(size2 + size);
                        }
                        zzxnVarZzd.addAll(zzxnVar);
                    }
                    if (size > 0) {
                        zzxnVar = zzxnVarZzd;
                    }
                    zzzo.zzs(obj, j, zzxnVar);
                    break;
                case 50:
                    int i4 = zzyx.zza;
                    zzyf zzyfVarZza = (zzyf) zzzo.zzf(obj, j);
                    zzyf zzyfVar = (zzyf) zzzo.zzf(obj2, j);
                    if (!zzyfVar.isEmpty()) {
                        if (!zzyfVarZza.zzd()) {
                            zzyfVarZza = zzyfVarZza.zza();
                        }
                        zzyfVarZza.zzc(zzyfVar);
                    }
                    zzzo.zzs(obj, j, zzyfVarZza);
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
                    if (zzC(obj2, i3, i)) {
                        zzzo.zzs(obj, j, zzzo.zzf(obj2, j));
                        zzw(obj, i3, i);
                    }
                    break;
                case 60:
                    zzu(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzC(obj2, i3, i)) {
                        zzzo.zzs(obj, j, zzzo.zzf(obj2, j));
                        zzw(obj, i3, i);
                    }
                    break;
                case 68:
                    zzu(obj, obj2, i);
                    break;
            }
            i += 3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0023  */
    @Override // com.google.android.gms.internal.cast.zzyv
    public final void zzf(Object obj, zzzt zzztVar) throws IOException {
        Map.Entry entry;
        int i;
        zzyo<T> zzyoVar = this;
        if (zzyoVar.zzf) {
            zzxb zzxbVar = ((zzxg) obj).zzb;
            if (zzxbVar.zza.isEmpty()) {
                entry = null;
            } else {
                entry = (Map.Entry) zzxbVar.zze().next();
            }
        } else {
            entry = null;
        }
        int[] iArr = zzyoVar.zzc;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i4 < iArr.length) {
            int iZzo = zzyoVar.zzo(i4);
            int iZzn = zzn(iZzo);
            int i6 = iArr[i4];
            if (iZzn <= 17) {
                int i7 = iArr[i4 + 2];
                int i8 = i7 & i2;
                if (i8 != i3) {
                    i5 = i8 == i2 ? 0 : unsafe.getInt(obj, i8);
                    i3 = i8;
                }
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            if (entry != null) {
                throw null;
            }
            long j = iZzo & i2;
            switch (iZzn) {
                case 0:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzf(i6, zzzo.zza(obj, j));
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 1:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzn(i6, zzzo.zzb(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 2:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzs(i6, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 3:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzI(i6, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 4:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzq(i6, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 5:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzl(i6, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 6:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzj(i6, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 7:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzb(i6, zzzo.zzw(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 8:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzE(i6, unsafe.getObject(obj, j), zzztVar);
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 9:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzu(i6, unsafe.getObject(obj, j), zzyoVar.zzq(i4));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 10:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzd(i6, (zzwn) unsafe.getObject(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 11:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzG(i6, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 12:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzh(i6, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 13:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzw(i6, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 14:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzy(i6, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 15:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzA(i6, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 16:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzC(i6, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 17:
                    if (zzyoVar.zzz(obj, i4, i3, i5, i)) {
                        zzztVar.zzp(i6, unsafe.getObject(obj, j), zzyoVar.zzq(i4));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 18:
                    zzyx.zzq(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 19:
                    zzyx.zzu(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 20:
                    zzyx.zzw(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 21:
                    zzyx.zzC(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 22:
                    zzyx.zzv(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 23:
                    zzyx.zzt(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 24:
                    zzyx.zzs(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 25:
                    zzyx.zzp(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 26:
                    int i9 = iArr[i4];
                    List list = (List) unsafe.getObject(obj, j);
                    int i10 = zzyx.zza;
                    if (list != null && !list.isEmpty()) {
                        zzztVar.zzF(i9, list);
                    }
                    break;
                case 27:
                    int i11 = iArr[i4];
                    List list2 = (List) unsafe.getObject(obj, j);
                    zzyv zzyvVarZzq = zzyoVar.zzq(i4);
                    int i12 = zzyx.zza;
                    if (list2 != null && !list2.isEmpty()) {
                        for (int i13 = 0; i13 < list2.size(); i13++) {
                            ((zzwv) zzztVar).zzu(i11, list2.get(i13), zzyvVarZzq);
                        }
                    }
                    break;
                case 28:
                    int i14 = iArr[i4];
                    List list3 = (List) unsafe.getObject(obj, j);
                    int i15 = zzyx.zza;
                    if (list3 != null && !list3.isEmpty()) {
                        zzztVar.zze(i14, list3);
                    }
                    break;
                case 29:
                    zzyx.zzB(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 30:
                    zzyx.zzr(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 31:
                    zzyx.zzx(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 32:
                    zzyx.zzy(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 33:
                    zzyx.zzz(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 34:
                    zzyx.zzA(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, false);
                    continue;
                    i4 += 3;
                    i2 = 1048575;
                    zzyoVar = this;
                    break;
                case 35:
                    zzyx.zzq(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 36:
                    zzyx.zzu(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 37:
                    zzyx.zzw(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 38:
                    zzyx.zzC(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 39:
                    zzyx.zzv(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 40:
                    zzyx.zzt(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 41:
                    zzyx.zzs(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 42:
                    zzyx.zzp(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 43:
                    zzyx.zzB(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 44:
                    zzyx.zzr(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 45:
                    zzyx.zzx(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 46:
                    zzyx.zzy(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 47:
                    zzyx.zzz(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 48:
                    zzyx.zzA(iArr[i4], (List) unsafe.getObject(obj, j), zzztVar, true);
                    break;
                case 49:
                    int i16 = iArr[i4];
                    List list4 = (List) unsafe.getObject(obj, j);
                    zzyv zzyvVarZzq2 = zzyoVar.zzq(i4);
                    int i17 = zzyx.zza;
                    if (list4 != null && !list4.isEmpty()) {
                        for (int i18 = 0; i18 < list4.size(); i18++) {
                            ((zzwv) zzztVar).zzp(i16, list4.get(i18), zzyvVarZzq2);
                        }
                    }
                    break;
                case 50:
                    if (unsafe.getObject(obj, j) != null) {
                        throw null;
                    }
                    break;
                case 51:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzf(i6, zzj(obj, j));
                    }
                    break;
                case 52:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzn(i6, zzk(obj, j));
                    }
                    break;
                case 53:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzs(i6, zzp(obj, j));
                    }
                    break;
                case 54:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzI(i6, zzp(obj, j));
                    }
                    break;
                case 55:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzq(i6, zzl(obj, j));
                    }
                    break;
                case 56:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzl(i6, zzp(obj, j));
                    }
                    break;
                case 57:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzj(i6, zzl(obj, j));
                    }
                    break;
                case 58:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzb(i6, zzD(obj, j));
                    }
                    break;
                case 59:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzE(i6, unsafe.getObject(obj, j), zzztVar);
                    }
                    break;
                case 60:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzu(i6, unsafe.getObject(obj, j), zzyoVar.zzq(i4));
                    }
                    break;
                case 61:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzd(i6, (zzwn) unsafe.getObject(obj, j));
                    }
                    break;
                case 62:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzG(i6, zzl(obj, j));
                    }
                    break;
                case 63:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzh(i6, zzl(obj, j));
                    }
                    break;
                case 64:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzw(i6, zzl(obj, j));
                    }
                    break;
                case 65:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzy(i6, zzp(obj, j));
                    }
                    break;
                case 66:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzA(i6, zzl(obj, j));
                    }
                    break;
                case 67:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzC(i6, zzp(obj, j));
                    }
                    break;
                case 68:
                    if (zzyoVar.zzC(obj, i6, i4)) {
                        zzztVar.zzp(i6, unsafe.getObject(obj, j), zzyoVar.zzq(i4));
                    }
                    break;
            }
            i4 += 3;
            i2 = 1048575;
            zzyoVar = this;
        }
        if (entry != null) {
            throw null;
        }
        zzzi zzziVar = ((zzxi) obj).zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzyv
    public final boolean zzg(Object obj, Object obj2) {
        boolean zZzD;
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzo = zzo(i);
            long j = iZzo & 1048575;
            switch (zzn(iZzo)) {
                case 0:
                    if (!zzx(obj, obj2, i) || Double.doubleToLongBits(zzzo.zza(obj, j)) != Double.doubleToLongBits(zzzo.zza(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 1:
                    if (!zzx(obj, obj2, i) || Float.floatToIntBits(zzzo.zzb(obj, j)) != Float.floatToIntBits(zzzo.zzb(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 2:
                    if (!zzx(obj, obj2, i) || zzzo.zzd(obj, j) != zzzo.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 3:
                    if (!zzx(obj, obj2, i) || zzzo.zzd(obj, j) != zzzo.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 4:
                    if (!zzx(obj, obj2, i) || zzzo.zzc(obj, j) != zzzo.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 5:
                    if (!zzx(obj, obj2, i) || zzzo.zzd(obj, j) != zzzo.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 6:
                    if (!zzx(obj, obj2, i) || zzzo.zzc(obj, j) != zzzo.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 7:
                    if (!zzx(obj, obj2, i) || zzzo.zzw(obj, j) != zzzo.zzw(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 8:
                    if (!zzx(obj, obj2, i) || !zzyx.zzD(zzzo.zzf(obj, j), zzzo.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 9:
                    if (!zzx(obj, obj2, i) || !zzyx.zzD(zzzo.zzf(obj, j), zzzo.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 10:
                    if (!zzx(obj, obj2, i) || !zzyx.zzD(zzzo.zzf(obj, j), zzzo.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 11:
                    if (!zzx(obj, obj2, i) || zzzo.zzc(obj, j) != zzzo.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 12:
                    if (!zzx(obj, obj2, i) || zzzo.zzc(obj, j) != zzzo.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 13:
                    if (!zzx(obj, obj2, i) || zzzo.zzc(obj, j) != zzzo.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 14:
                    if (!zzx(obj, obj2, i) || zzzo.zzd(obj, j) != zzzo.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 15:
                    if (!zzx(obj, obj2, i) || zzzo.zzc(obj, j) != zzzo.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 16:
                    if (!zzx(obj, obj2, i) || zzzo.zzd(obj, j) != zzzo.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 17:
                    if (!zzx(obj, obj2, i) || !zzyx.zzD(zzzo.zzf(obj, j), zzzo.zzf(obj2, j))) {
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
                    zZzD = zzyx.zzD(zzzo.zzf(obj, j), zzzo.zzf(obj2, j));
                    break;
                case 50:
                    zZzD = zzyx.zzD(zzzo.zzf(obj, j), zzzo.zzf(obj2, j));
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
                    long jZzm = zzm(i) & 1048575;
                    if (zzzo.zzc(obj, jZzm) != zzzo.zzc(obj2, jZzm) || !zzyx.zzD(zzzo.zzf(obj, j), zzzo.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                default:
                    continue;
                    break;
            }
            if (!zZzD) {
                return false;
            }
        }
        if (!((zzxi) obj).zzc.equals(((zzxi) obj2).zzc)) {
            return false;
        }
        if (this.zzf) {
            return ((zzxg) obj).zzb.equals(((zzxg) obj2).zzb);
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x008d  */
    /* JADX WARN: Code duplicated, block: B:44:0x009c  */
    /* JADX WARN: Code duplicated, block: B:47:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:50:0x00b2 A[LOOP:1: B:45:0x00a1->B:50:0x00b2, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x00b1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:71:0x00c8 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.cast.zzyv
    public final boolean zzh(Object obj) {
        int i;
        int i2;
        List list;
        zzyv zzyvVarZzq;
        int i3;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1048575;
        while (i5 < this.zzh) {
            int[] iArr = this.zzg;
            int[] iArr2 = this.zzc;
            int i7 = iArr[i5];
            int i8 = iArr2[i7];
            int iZzo = zzo(i7);
            int i9 = iArr2[i7 + 2];
            int i10 = i9 & 1048575;
            int i11 = 1 << (i9 >>> 20);
            if (i10 != i6) {
                if (i10 != 1048575) {
                    i4 = zzb.getInt(obj, i10);
                }
                i2 = i4;
                i = i10;
            } else {
                int i12 = i4;
                i = i6;
                i2 = i12;
            }
            if ((268435456 & iZzo) != 0 && !zzz(obj, i7, i, i2, i11)) {
                return false;
            }
            int iZzn = zzn(iZzo);
            if (iZzn == 9 || iZzn == 17) {
                if (zzz(obj, i7, i, i2, i11) && !zzA(obj, iZzo, zzq(i7))) {
                    return false;
                }
            } else if (iZzn == 27) {
                list = (List) zzzo.zzf(obj, iZzo & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzyvVarZzq = zzq(i7);
                    for (i3 = 0; i3 < list.size(); i3++) {
                        if (!zzyvVarZzq.zzh(list.get(i3))) {
                            return false;
                        }
                    }
                }
            } else if (iZzn == 60 || iZzn == 68) {
                if (zzC(obj, i8, i7) && !zzA(obj, iZzo, zzq(i7))) {
                    return false;
                }
            } else if (iZzn == 49) {
                list = (List) zzzo.zzf(obj, iZzo & 1048575);
                if (list.isEmpty()) {
                    zzyvVarZzq = zzq(i7);
                    while (i3 < list.size()) {
                        if (!zzyvVarZzq.zzh(list.get(i3))) {
                            return false;
                        }
                    }
                } else {
                    continue;
                }
            } else if (iZzn == 50 && !((zzyf) zzzo.zzf(obj, iZzo & 1048575)).isEmpty()) {
                throw null;
            }
            i5++;
            i6 = i;
            i4 = i2;
        }
        return !this.zzf || ((zzxg) obj).zzb.zzh();
    }
}
