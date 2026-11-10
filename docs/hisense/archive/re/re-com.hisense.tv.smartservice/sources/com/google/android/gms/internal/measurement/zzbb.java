package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzbb {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:65:0x00f5  */
    public static zzap zza(String str, zzae zzaeVar, zzg zzgVar, List<zzap> list) {
        byte b2;
        String strZzi;
        zzai zzaiVar;
        int i = 0;
        switch (str) {
            case "toString":
                b2 = Ascii.DC2;
                break;
            case "concat":
                b2 = 0;
                break;
            case "filter":
                b2 = 2;
                break;
            case "reduce":
                b2 = 10;
                break;
            case "splice":
                b2 = 17;
                break;
            case "forEach":
                b2 = 3;
                break;
            case "lastIndexOf":
                b2 = 6;
                break;
            case "unshift":
                b2 = 19;
                break;
            case "map":
                b2 = 7;
                break;
            case "pop":
                b2 = 8;
                break;
            case "join":
                b2 = 5;
                break;
            case "push":
                b2 = 9;
                break;
            case "some":
                b2 = Ascii.SI;
                break;
            case "sort":
                b2 = Ascii.DLE;
                break;
            case "every":
                b2 = 1;
                break;
            case "shift":
                b2 = Ascii.CR;
                break;
            case "slice":
                b2 = Ascii.SO;
                break;
            case "reduceRight":
                b2 = Ascii.VT;
                break;
            case "reverse":
                b2 = Ascii.FF;
                break;
            case "indexOf":
                b2 = 4;
                break;
            default:
                b2 = -1;
                break;
        }
        double dZzc = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        switch (b2) {
            case 0:
                zzap zzapVarZzd = zzaeVar.zzd();
                if (!list.isEmpty()) {
                    Iterator<zzap> it = list.iterator();
                    while (it.hasNext()) {
                        zzap zzapVarZzb = zzgVar.zzb(it.next());
                        if (zzapVarZzb instanceof zzag) {
                            throw new IllegalStateException("Failed evaluation of arguments");
                        }
                        zzae zzaeVar2 = (zzae) zzapVarZzd;
                        int iZzc = zzaeVar2.zzc();
                        if (zzapVarZzb instanceof zzae) {
                            zzae zzaeVar3 = (zzae) zzapVarZzb;
                            Iterator<Integer> itZzk = zzaeVar3.zzk();
                            while (itZzk.hasNext()) {
                                Integer next = itZzk.next();
                                zzaeVar2.zzq(next.intValue() + iZzc, zzaeVar3.zze(next.intValue()));
                            }
                        } else {
                            zzaeVar2.zzq(iZzc, zzapVarZzb);
                        }
                    }
                }
                return zzapVarZzd;
            case 1:
                zzh.zzh("every", 1, list);
                zzap zzapVarZzb2 = zzgVar.zzb(list.get(0));
                if (!(zzapVarZzb2 instanceof zzao)) {
                    throw new IllegalArgumentException("Callback should be a method");
                }
                if (zzaeVar.zzc() != 0 && zzb(zzaeVar, zzgVar, (zzao) zzapVarZzb2, false, true).zzc() != zzaeVar.zzc()) {
                    return zzap.zzl;
                }
                return zzap.zzk;
            case 2:
                zzh.zzh("filter", 1, list);
                zzap zzapVarZzb3 = zzgVar.zzb(list.get(0));
                if (!(zzapVarZzb3 instanceof zzao)) {
                    throw new IllegalArgumentException("Callback should be a method");
                }
                if (zzaeVar.zzb() == 0) {
                    return new zzae();
                }
                zzap zzapVarZzd2 = zzaeVar.zzd();
                zzae zzaeVarZzb = zzb(zzaeVar, zzgVar, (zzao) zzapVarZzb3, null, true);
                zzae zzaeVar4 = new zzae();
                Iterator<Integer> itZzk2 = zzaeVarZzb.zzk();
                while (itZzk2.hasNext()) {
                    zzaeVar4.zzq(zzaeVar4.zzc(), ((zzae) zzapVarZzd2).zze(itZzk2.next().intValue()));
                }
                return zzaeVar4;
            case 3:
                zzh.zzh("forEach", 1, list);
                zzap zzapVarZzb4 = zzgVar.zzb(list.get(0));
                if (!(zzapVarZzb4 instanceof zzao)) {
                    throw new IllegalArgumentException("Callback should be a method");
                }
                if (zzaeVar.zzb() == 0) {
                    return zzap.zzf;
                }
                zzb(zzaeVar, zzgVar, (zzao) zzapVarZzb4, null, null);
                return zzap.zzf;
            case 4:
                zzh.zzj("indexOf", 2, list);
                zzap zzapVarZzb5 = zzap.zzf;
                if (!list.isEmpty()) {
                    zzapVarZzb5 = zzgVar.zzb(list.get(0));
                }
                if (list.size() > 1) {
                    double dZza = zzh.zza(zzgVar.zzb(list.get(1)).zzh().doubleValue());
                    if (dZza >= zzaeVar.zzc()) {
                        return new zzah(Double.valueOf(-1.0d));
                    }
                    dZzc = dZza < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? ((double) zzaeVar.zzc()) + dZza : dZza;
                }
                Iterator<Integer> itZzk3 = zzaeVar.zzk();
                while (itZzk3.hasNext()) {
                    int iIntValue = itZzk3.next().intValue();
                    double d2 = iIntValue;
                    if (d2 >= dZzc && zzh.zzl(zzaeVar.zze(iIntValue), zzapVarZzb5)) {
                        return new zzah(Double.valueOf(d2));
                    }
                }
                return new zzah(Double.valueOf(-1.0d));
            case 5:
                zzh.zzj("join", 1, list);
                if (zzaeVar.zzc() == 0) {
                    return zzap.zzm;
                }
                if (list.size() > 0) {
                    zzap zzapVarZzb6 = zzgVar.zzb(list.get(0));
                    strZzi = ((zzapVarZzb6 instanceof zzan) || (zzapVarZzb6 instanceof zzau)) ? "" : zzapVarZzb6.zzi();
                } else {
                    strZzi = ",";
                }
                return new zzat(zzaeVar.zzj(strZzi));
            case 6:
                zzh.zzj("lastIndexOf", 2, list);
                zzap zzapVarZzb7 = zzap.zzf;
                if (!list.isEmpty()) {
                    zzapVarZzb7 = zzgVar.zzb(list.get(0));
                }
                double dZzc2 = zzaeVar.zzc() - 1;
                if (list.size() > 1) {
                    zzap zzapVarZzb8 = zzgVar.zzb(list.get(1));
                    dZzc2 = Double.isNaN(zzapVarZzb8.zzh().doubleValue()) ? zzaeVar.zzc() - 1 : zzh.zza(zzapVarZzb8.zzh().doubleValue());
                    if (dZzc2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                        dZzc2 += (double) zzaeVar.zzc();
                    }
                }
                if (dZzc2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    return new zzah(Double.valueOf(-1.0d));
                }
                for (int iMin = (int) Math.min(zzaeVar.zzc(), dZzc2); iMin >= 0; iMin--) {
                    if (zzaeVar.zzs(iMin) && zzh.zzl(zzaeVar.zze(iMin), zzapVarZzb7)) {
                        return new zzah(Double.valueOf(iMin));
                    }
                }
                return new zzah(Double.valueOf(-1.0d));
            case 7:
                zzh.zzh("map", 1, list);
                zzap zzapVarZzb9 = zzgVar.zzb(list.get(0));
                if (zzapVarZzb9 instanceof zzao) {
                    return zzaeVar.zzc() == 0 ? new zzae() : zzb(zzaeVar, zzgVar, (zzao) zzapVarZzb9, null, null);
                }
                throw new IllegalArgumentException("Callback should be a method");
            case 8:
                zzh.zzh("pop", 0, list);
                int iZzc2 = zzaeVar.zzc();
                if (iZzc2 == 0) {
                    return zzap.zzf;
                }
                int i2 = iZzc2 - 1;
                zzap zzapVarZze = zzaeVar.zze(i2);
                zzaeVar.zzp(i2);
                return zzapVarZze;
            case 9:
                if (!list.isEmpty()) {
                    Iterator<zzap> it2 = list.iterator();
                    while (it2.hasNext()) {
                        zzaeVar.zzq(zzaeVar.zzc(), zzgVar.zzb(it2.next()));
                    }
                }
                return new zzah(Double.valueOf(zzaeVar.zzc()));
            case 10:
                return zzc(zzaeVar, zzgVar, list, true);
            case 11:
                return zzc(zzaeVar, zzgVar, list, false);
            case 12:
                zzh.zzh("reverse", 0, list);
                int iZzc3 = zzaeVar.zzc();
                if (iZzc3 != 0) {
                    while (i < iZzc3 / 2) {
                        if (zzaeVar.zzs(i)) {
                            zzap zzapVarZze2 = zzaeVar.zze(i);
                            zzaeVar.zzq(i, null);
                            int i3 = (iZzc3 - 1) - i;
                            if (zzaeVar.zzs(i3)) {
                                zzaeVar.zzq(i, zzaeVar.zze(i3));
                            }
                            zzaeVar.zzq(i3, zzapVarZze2);
                        }
                        i++;
                    }
                }
                return zzaeVar;
            case 13:
                zzh.zzh("shift", 0, list);
                if (zzaeVar.zzc() == 0) {
                    return zzap.zzf;
                }
                zzap zzapVarZze3 = zzaeVar.zze(0);
                zzaeVar.zzp(0);
                return zzapVarZze3;
            case 14:
                zzh.zzj("slice", 2, list);
                if (list.isEmpty()) {
                    return zzaeVar.zzd();
                }
                double dZzc3 = zzaeVar.zzc();
                double dZza2 = zzh.zza(zzgVar.zzb(list.get(0)).zzh().doubleValue());
                double dMax = dZza2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? Math.max(dZza2 + dZzc3, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) : Math.min(dZza2, dZzc3);
                if (list.size() == 2) {
                    double dZza3 = zzh.zza(zzgVar.zzb(list.get(1)).zzh().doubleValue());
                    dZzc3 = dZza3 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? Math.max(dZzc3 + dZza3, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) : Math.min(dZzc3, dZza3);
                }
                zzae zzaeVar5 = new zzae();
                for (int i4 = (int) dMax; i4 < dZzc3; i4++) {
                    zzaeVar5.zzq(zzaeVar5.zzc(), zzaeVar.zze(i4));
                }
                return zzaeVar5;
            case 15:
                zzh.zzh("some", 1, list);
                zzap zzapVarZzb10 = zzgVar.zzb(list.get(0));
                if (!(zzapVarZzb10 instanceof zzai)) {
                    throw new IllegalArgumentException("Callback should be a method");
                }
                if (zzaeVar.zzc() == 0) {
                    return zzap.zzl;
                }
                zzai zzaiVar2 = (zzai) zzapVarZzb10;
                Iterator<Integer> itZzk4 = zzaeVar.zzk();
                while (itZzk4.hasNext()) {
                    int iIntValue2 = itZzk4.next().intValue();
                    if (zzaeVar.zzs(iIntValue2) && zzaiVar2.zza(zzgVar, Arrays.asList(zzaeVar.zze(iIntValue2), new zzah(Double.valueOf(iIntValue2)), zzaeVar)).zzg().booleanValue()) {
                        return zzap.zzk;
                    }
                }
                return zzap.zzl;
            case 16:
                zzh.zzj("sort", 1, list);
                if (zzaeVar.zzc() >= 2) {
                    List<zzap> listZzm = zzaeVar.zzm();
                    if (list.isEmpty()) {
                        zzaiVar = null;
                    } else {
                        zzap zzapVarZzb11 = zzgVar.zzb(list.get(0));
                        if (!(zzapVarZzb11 instanceof zzai)) {
                            throw new IllegalArgumentException("Comparator should be a method");
                        }
                        zzaiVar = (zzai) zzapVarZzb11;
                    }
                    Collections.sort(listZzm, new zzba(zzaiVar, zzgVar));
                    zzaeVar.zzn();
                    Iterator<zzap> it3 = listZzm.iterator();
                    while (it3.hasNext()) {
                        zzaeVar.zzq(i, it3.next());
                        i++;
                    }
                }
                return zzaeVar;
            case 17:
                if (list.isEmpty()) {
                    return new zzae();
                }
                int iZza = (int) zzh.zza(zzgVar.zzb(list.get(0)).zzh().doubleValue());
                if (iZza < 0) {
                    iZza = Math.max(0, zzaeVar.zzc() + iZza);
                } else if (iZza > zzaeVar.zzc()) {
                    iZza = zzaeVar.zzc();
                }
                int iZzc4 = zzaeVar.zzc();
                zzae zzaeVar6 = new zzae();
                if (list.size() > 1) {
                    int iMax = Math.max(0, (int) zzh.zza(zzgVar.zzb(list.get(1)).zzh().doubleValue()));
                    if (iMax > 0) {
                        for (int i5 = iZza; i5 < Math.min(iZzc4, iZza + iMax); i5++) {
                            zzaeVar6.zzq(zzaeVar6.zzc(), zzaeVar.zze(iZza));
                            zzaeVar.zzp(iZza);
                        }
                    }
                    if (list.size() > 2) {
                        for (int i6 = 2; i6 < list.size(); i6++) {
                            zzap zzapVarZzb12 = zzgVar.zzb(list.get(i6));
                            if (zzapVarZzb12 instanceof zzag) {
                                throw new IllegalArgumentException("Failed to parse elements to add");
                            }
                            zzaeVar.zzo((iZza + i6) - 2, zzapVarZzb12);
                        }
                    }
                } else {
                    while (iZza < iZzc4) {
                        zzaeVar6.zzq(zzaeVar6.zzc(), zzaeVar.zze(iZza));
                        zzaeVar.zzq(iZza, null);
                        iZza++;
                    }
                }
                return zzaeVar6;
            case 18:
                zzh.zzh("toString", 0, list);
                return new zzat(zzaeVar.zzj(","));
            case 19:
                if (!list.isEmpty()) {
                    zzae zzaeVar7 = new zzae();
                    Iterator<zzap> it4 = list.iterator();
                    while (it4.hasNext()) {
                        zzap zzapVarZzb13 = zzgVar.zzb(it4.next());
                        if (zzapVarZzb13 instanceof zzag) {
                            throw new IllegalStateException("Argument evaluation failed");
                        }
                        zzaeVar7.zzq(zzaeVar7.zzc(), zzapVarZzb13);
                    }
                    int iZzc5 = zzaeVar7.zzc();
                    Iterator<Integer> itZzk5 = zzaeVar.zzk();
                    while (itZzk5.hasNext()) {
                        Integer next2 = itZzk5.next();
                        zzaeVar7.zzq(next2.intValue() + iZzc5, zzaeVar.zze(next2.intValue()));
                    }
                    zzaeVar.zzn();
                    Iterator<Integer> itZzk6 = zzaeVar7.zzk();
                    while (itZzk6.hasNext()) {
                        Integer next3 = itZzk6.next();
                        zzaeVar.zzq(next3.intValue(), zzaeVar7.zze(next3.intValue()));
                    }
                }
                return new zzah(Double.valueOf(zzaeVar.zzc()));
            default:
                throw new IllegalArgumentException("Command not supported");
        }
    }

    public static zzae zzb(zzae zzaeVar, zzg zzgVar, zzai zzaiVar, Boolean bool, Boolean bool2) {
        zzae zzaeVar2 = new zzae();
        Iterator<Integer> itZzk = zzaeVar.zzk();
        while (itZzk.hasNext()) {
            int iIntValue = itZzk.next().intValue();
            if (zzaeVar.zzs(iIntValue)) {
                zzap zzapVarZza = zzaiVar.zza(zzgVar, Arrays.asList(zzaeVar.zze(iIntValue), new zzah(Double.valueOf(iIntValue)), zzaeVar));
                if (zzapVarZza.zzg().equals(bool)) {
                    return zzaeVar2;
                }
                if (bool2 == null || zzapVarZza.zzg().equals(bool2)) {
                    zzaeVar2.zzq(iIntValue, zzapVarZza);
                }
            }
        }
        return zzaeVar2;
    }

    public static zzap zzc(zzae zzaeVar, zzg zzgVar, List<zzap> list, boolean z) {
        zzap zzapVarZza;
        zzh.zzi("reduce", 1, list);
        zzh.zzj("reduce", 2, list);
        zzap zzapVarZzb = zzgVar.zzb(list.get(0));
        if (!(zzapVarZzb instanceof zzai)) {
            throw new IllegalArgumentException("Callback should be a method");
        }
        if (list.size() == 2) {
            zzapVarZza = zzgVar.zzb(list.get(1));
            if (zzapVarZza instanceof zzag) {
                throw new IllegalArgumentException("Failed to parse initial value");
            }
        } else {
            if (zzaeVar.zzc() == 0) {
                throw new IllegalStateException("Empty array with no initial value error");
            }
            zzapVarZza = null;
        }
        zzai zzaiVar = (zzai) zzapVarZzb;
        int iZzc = zzaeVar.zzc();
        int i = z ? 0 : iZzc - 1;
        int i2 = z ? iZzc - 1 : 0;
        int i3 = true == z ? 1 : -1;
        if (zzapVarZza == null) {
            zzapVarZza = zzaeVar.zze(i);
            i += i3;
        }
        while ((i2 - i) * i3 >= 0) {
            if (zzaeVar.zzs(i)) {
                zzapVarZza = zzaiVar.zza(zzgVar, Arrays.asList(zzapVarZza, zzaeVar.zze(i), new zzah(Double.valueOf(i)), zzaeVar));
                if (zzapVarZza instanceof zzag) {
                    throw new IllegalStateException("Reduce operation failed");
                }
                i += i3;
            } else {
                i += i3;
            }
        }
        return zzapVarZza;
    }
}
