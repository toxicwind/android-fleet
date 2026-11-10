package com.google.android.gms.internal.measurement;

import b.a.a.a.a;
import com.google.common.base.Ascii;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class zzat implements Iterable<zzap>, zzap {
    public final String zza;

    public zzat(String str) {
        if (str == null) {
            throw new IllegalArgumentException("StringValue cannot be null.");
        }
        this.zza = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzat) {
            return this.zza.equals(((zzat) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator<zzap> iterator() {
        return new zzas(this);
    }

    public final String toString() {
        String str = this.zza;
        StringBuilder sb = new StringBuilder(str.length() + 2);
        sb.append('\"');
        sb.append(str);
        sb.append('\"');
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:131:0x02c5 A[PHI: r14
      0x02c5: PHI (r14v5 boolean) = (r14v11 boolean), (r14v12 boolean), (r14v15 boolean) binds: [B:127:0x02b1, B:128:0x02b3, B:130:0x02c3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:93:0x0157  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbK(String str, zzg zzgVar, List<zzap> list) {
        byte b2;
        zzap zzahVar;
        int i;
        zzap zzahVar2;
        int i2;
        int i3;
        boolean zEquals;
        z = false;
        boolean z = false;
        if (!"charAt".equals(str) && !"concat".equals(str) && !"hasOwnProperty".equals(str) && !"indexOf".equals(str) && !"lastIndexOf".equals(str) && !"match".equals(str) && !"replace".equals(str) && !FirebaseAnalytics.Event.SEARCH.equals(str) && !"slice".equals(str) && !"split".equals(str) && !"substring".equals(str) && !"toLowerCase".equals(str) && !"toLocaleLowerCase".equals(str) && !"toString".equals(str) && !"toUpperCase".equals(str) && !"toLocaleUpperCase".equals(str) && !"trim".equals(str)) {
            throw new IllegalArgumentException(String.format("%s is not a String function", str));
        }
        switch (str) {
            case "hasOwnProperty":
                b2 = 2;
                break;
            case "toString":
                b2 = Ascii.SO;
                break;
            case "toLocaleLowerCase":
                b2 = Ascii.FF;
                break;
            case "charAt":
                b2 = 0;
                break;
            case "concat":
                b2 = 1;
                break;
            case "toLowerCase":
                b2 = Ascii.CR;
                break;
            case "search":
                b2 = 7;
                break;
            case "toLocaleUpperCase":
                b2 = Ascii.VT;
                break;
            case "lastIndexOf":
                b2 = 4;
                break;
            case "toUpperCase":
                b2 = Ascii.SI;
                break;
            case "trim":
                b2 = Ascii.DLE;
                break;
            case "match":
                b2 = 5;
                break;
            case "slice":
                b2 = 8;
                break;
            case "split":
                b2 = 9;
                break;
            case "substring":
                b2 = 10;
                break;
            case "replace":
                b2 = 6;
                break;
            case "indexOf":
                b2 = 3;
                break;
            default:
                b2 = -1;
                break;
        }
        String strZzi = "undefined";
        double dDoubleValue = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        switch (b2) {
            case 0:
                zzh.zzj("charAt", 1, list);
                int iZza = list.size() > 0 ? (int) zzh.zza(zzgVar.zzb(list.get(0)).zzh().doubleValue()) : 0;
                String str2 = this.zza;
                if (iZza >= 0 && iZza < str2.length()) {
                    return new zzat(String.valueOf(str2.charAt(iZza)));
                }
                zzahVar = zzap.zzm;
                return zzahVar;
            case 1:
                if (list.size() != 0) {
                    StringBuilder sb = new StringBuilder(this.zza);
                    for (int i4 = 0; i4 < list.size(); i4++) {
                        sb.append(zzgVar.zzb(list.get(i4)).zzi());
                    }
                    return new zzat(sb.toString());
                }
                return this;
            case 2:
                zzh.zzh("hasOwnProperty", 1, list);
                String str3 = this.zza;
                zzap zzapVarZzb = zzgVar.zzb(list.get(0));
                if ("length".equals(zzapVarZzb.zzi())) {
                    zzahVar = zzap.zzk;
                } else {
                    double dDoubleValue2 = zzapVarZzb.zzh().doubleValue();
                    zzahVar = (dDoubleValue2 != Math.floor(dDoubleValue2) || (i = (int) dDoubleValue2) < 0 || i >= str3.length()) ? zzap.zzl : zzap.zzk;
                }
                return zzahVar;
            case 3:
                zzh.zzj("indexOf", 2, list);
                String str4 = this.zza;
                String strZzi2 = list.size() > 0 ? zzgVar.zzb(list.get(0)).zzi() : "undefined";
                if (list.size() >= 2) {
                    dDoubleValue = zzgVar.zzb(list.get(1)).zzh().doubleValue();
                }
                zzahVar2 = new zzah(Double.valueOf(str4.indexOf(strZzi2, (int) zzh.zza(dDoubleValue))));
                return zzahVar2;
            case 4:
                zzh.zzj("lastIndexOf", 2, list);
                String str5 = this.zza;
                String strZzi3 = list.size() > 0 ? zzgVar.zzb(list.get(0)).zzi() : "undefined";
                double dDoubleValue3 = list.size() < 2 ? Double.NaN : zzgVar.zzb(list.get(1)).zzh().doubleValue();
                return new zzah(Double.valueOf(str5.lastIndexOf(strZzi3, (int) (Double.isNaN(dDoubleValue3) ? Double.POSITIVE_INFINITY : zzh.zza(dDoubleValue3)))));
            case 5:
                zzh.zzj("match", 1, list);
                Matcher matcher = Pattern.compile(list.size() <= 0 ? "" : zzgVar.zzb(list.get(0)).zzi()).matcher(this.zza);
                if (matcher.find()) {
                    return new zzae(Arrays.asList(new zzat(matcher.group())));
                }
                zzahVar = zzap.zzg;
                return zzahVar;
            case 6:
                zzh.zzj("replace", 2, list);
                zzap zzapVarZza = zzap.zzf;
                if (list.size() > 0) {
                    strZzi = zzgVar.zzb(list.get(0)).zzi();
                    if (list.size() > 1) {
                        zzapVarZza = zzgVar.zzb(list.get(1));
                    }
                }
                String str6 = strZzi;
                String str7 = this.zza;
                int iIndexOf = str7.indexOf(str6);
                if (iIndexOf >= 0) {
                    if (zzapVarZza instanceof zzai) {
                        zzapVarZza = ((zzai) zzapVarZza).zza(zzgVar, Arrays.asList(new zzat(str6), new zzah(Double.valueOf(iIndexOf)), this));
                    }
                    String strSubstring = str7.substring(0, iIndexOf);
                    String strZzi4 = zzapVarZza.zzi();
                    String strSubstring2 = str7.substring(str6.length() + iIndexOf);
                    return new zzat(a.a(new StringBuilder(String.valueOf(strSubstring).length() + String.valueOf(strZzi4).length() + String.valueOf(strSubstring2).length()), strSubstring, strZzi4, strSubstring2));
                }
                return this;
            case 7:
                zzh.zzj(FirebaseAnalytics.Event.SEARCH, 1, list);
                Matcher matcher2 = Pattern.compile(list.size() > 0 ? zzgVar.zzb(list.get(0)).zzi() : "undefined").matcher(this.zza);
                if (matcher2.find()) {
                    return new zzah(Double.valueOf(matcher2.start()));
                }
                zzahVar = new zzah(Double.valueOf(-1.0d));
                return zzahVar;
            case 8:
                zzh.zzj("slice", 2, list);
                String str8 = this.zza;
                double dZza = zzh.zza(list.size() > 0 ? zzgVar.zzb(list.get(0)).zzh().doubleValue() : 0.0d);
                int iMax = (int) (dZza < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? Math.max(((double) str8.length()) + dZza, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) : Math.min(dZza, str8.length()));
                double dZza2 = zzh.zza(list.size() > 1 ? zzgVar.zzb(list.get(1)).zzh().doubleValue() : str8.length());
                return new zzat(str8.substring(iMax, Math.max(0, ((int) (dZza2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? Math.max(((double) str8.length()) + dZza2, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) : Math.min(dZza2, str8.length()))) - iMax) + iMax));
            case 9:
                zzh.zzj("split", 2, list);
                String str9 = this.zza;
                if (str9.length() == 0) {
                    zzahVar = new zzae(Arrays.asList(this));
                } else {
                    ArrayList arrayList = new ArrayList();
                    if (list.size() == 0) {
                        arrayList.add(this);
                    } else {
                        String strZzi5 = zzgVar.zzb(list.get(0)).zzi();
                        long jZzd = list.size() > 1 ? zzh.zzd(zzgVar.zzb(list.get(1)).zzh().doubleValue()) : 2147483647L;
                        if (jZzd == 0) {
                            zzahVar = new zzae();
                        } else {
                            String[] strArrSplit = str9.split(Pattern.quote(strZzi5), ((int) jZzd) + 1);
                            int length = strArrSplit.length;
                            if (!strZzi5.equals("") || length <= 0) {
                                i3 = zEquals;
                                z = zEquals;
                                i2 = length;
                                i3 = z;
                            } else {
                                zEquals = strArrSplit[0].equals("");
                                i2 = length - 1;
                                if (!strArrSplit[i2].equals("")) {
                                    i3 = zEquals;
                                    z = zEquals;
                                    i2 = length;
                                    i3 = z;
                                }
                            }
                            i3 = zEquals;
                            z = zEquals;
                            if (length > jZzd) {
                                i2--;
                            }
                            while (i3 < i2) {
                                arrayList.add(new zzat(strArrSplit[i3]));
                                i3++;
                            }
                        }
                    }
                    zzahVar = new zzae(arrayList);
                }
                return zzahVar;
            case 10:
                zzh.zzj("substring", 2, list);
                String str10 = this.zza;
                int iZza2 = list.size() > 0 ? (int) zzh.zza(zzgVar.zzb(list.get(0)).zzh().doubleValue()) : 0;
                int iZza3 = list.size() > 1 ? (int) zzh.zza(zzgVar.zzb(list.get(1)).zzh().doubleValue()) : str10.length();
                int iMin = Math.min(Math.max(iZza2, 0), str10.length());
                int iMin2 = Math.min(Math.max(iZza3, 0), str10.length());
                zzahVar2 = new zzat(str10.substring(Math.min(iMin, iMin2), Math.max(iMin, iMin2)));
                return zzahVar2;
            case 11:
                zzh.zzh("toLocaleUpperCase", 0, list);
                return new zzat(this.zza.toUpperCase());
            case 12:
                zzh.zzh("toLocaleLowerCase", 0, list);
                return new zzat(this.zza.toLowerCase());
            case 13:
                zzh.zzh("toLowerCase", 0, list);
                return new zzat(this.zza.toLowerCase(Locale.ENGLISH));
            case 14:
                zzh.zzh("toString", 0, list);
                return this;
            case 15:
                zzh.zzh("toUpperCase", 0, list);
                return new zzat(this.zza.toUpperCase(Locale.ENGLISH));
            case 16:
                zzh.zzh("toUpperCase", 0, list);
                return new zzat(this.zza.trim());
            default:
                throw new IllegalArgumentException("Command not supported");
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return new zzat(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        if (this.zza.isEmpty()) {
            return Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
        try {
            return Double.valueOf(this.zza);
        } catch (NumberFormatException unused) {
            return Double.valueOf(Double.NaN);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator<zzap> zzl() {
        return new zzar(this);
    }
}
