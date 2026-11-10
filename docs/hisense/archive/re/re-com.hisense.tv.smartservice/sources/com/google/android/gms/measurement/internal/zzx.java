package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzx {
    public final String zzb;
    public final int zzc;
    public Boolean zzd;
    public Boolean zze;
    public Long zzf;
    public Long zzg;

    public zzx(String str, int i) {
        this.zzb = str;
        this.zzc = i;
    }

    public static Boolean zzd(String str, int i, boolean z, String str2, List<String> list, String str3, zzel zzelVar) {
        if (i == 7) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 2) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i - 1) {
            case 1:
                if (str3 == null) {
                    return null;
                }
                try {
                    return Boolean.valueOf(Pattern.compile(str3, true != z ? 66 : 0).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    if (zzelVar != null) {
                        zzelVar.zzk().zzb("Invalid regular expression in REGEXP audience filter. expression", str3);
                    }
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                if (list == null) {
                    return null;
                }
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    @VisibleForTesting
    public static Boolean zze(BigDecimal bigDecimal, com.google.android.gms.internal.measurement.zzeq zzeqVar, double d2) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        Preconditions.checkNotNull(zzeqVar);
        if (zzeqVar.zzg()) {
            if (zzeqVar.zzm() != 1) {
                if (zzeqVar.zzm() == 5) {
                    if (!zzeqVar.zzk() || !zzeqVar.zzj()) {
                        return null;
                    }
                } else if (!zzeqVar.zzh()) {
                    return null;
                }
                int iZzm = zzeqVar.zzm();
                if (zzeqVar.zzm() == 5) {
                    if (zzku.zzy(zzeqVar.zze()) && zzku.zzy(zzeqVar.zzd())) {
                        try {
                            BigDecimal bigDecimal5 = new BigDecimal(zzeqVar.zze());
                            bigDecimal4 = new BigDecimal(zzeqVar.zzd());
                            bigDecimal3 = bigDecimal5;
                            bigDecimal2 = null;
                        } catch (NumberFormatException unused) {
                        }
                    }
                    return null;
                }
                if (!zzku.zzy(zzeqVar.zzc())) {
                    return null;
                }
                try {
                    bigDecimal2 = new BigDecimal(zzeqVar.zzc());
                    bigDecimal3 = null;
                    bigDecimal4 = null;
                } catch (NumberFormatException unused2) {
                }
                if (iZzm == 5) {
                    if (bigDecimal3 == null) {
                        return null;
                    }
                } else if (bigDecimal2 == null) {
                    return null;
                }
                int i = iZzm - 1;
                boolean z = false;
                if (i == 1) {
                    if (bigDecimal2 == null) {
                        return null;
                    }
                    return Boolean.valueOf(bigDecimal.compareTo(bigDecimal2) < 0);
                }
                if (i == 2) {
                    if (bigDecimal2 == null) {
                        return null;
                    }
                    return Boolean.valueOf(bigDecimal.compareTo(bigDecimal2) > 0);
                }
                if (i != 3) {
                    if (i != 4 || bigDecimal3 == null) {
                        return null;
                    }
                    if (bigDecimal.compareTo(bigDecimal3) >= 0 && bigDecimal.compareTo(bigDecimal4) <= 0) {
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
                if (bigDecimal2 == null) {
                    return null;
                }
                if (d2 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    return Boolean.valueOf(bigDecimal.compareTo(bigDecimal2) == 0);
                }
                if (bigDecimal.compareTo(bigDecimal2.subtract(new BigDecimal(d2).multiply(new BigDecimal(2)))) > 0 && bigDecimal.compareTo(bigDecimal2.add(new BigDecimal(d2).multiply(new BigDecimal(2)))) < 0) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }
        return null;
    }

    @VisibleForTesting
    public static Boolean zzf(String str, com.google.android.gms.internal.measurement.zzex zzexVar, zzel zzelVar) {
        List<String> list;
        Preconditions.checkNotNull(zzexVar);
        if (str == null || !zzexVar.zzi() || zzexVar.zzj() == 1) {
            return null;
        }
        if (zzexVar.zzj() == 7) {
            if (zzexVar.zza() == 0) {
                return null;
            }
        } else if (!zzexVar.zzh()) {
            return null;
        }
        int iZzj = zzexVar.zzj();
        boolean zZzf = zzexVar.zzf();
        String strZzd = (zZzf || iZzj == 2 || iZzj == 7) ? zzexVar.zzd() : zzexVar.zzd().toUpperCase(Locale.ENGLISH);
        if (zzexVar.zza() == 0) {
            list = null;
        } else {
            List<String> listZze = zzexVar.zze();
            if (!zZzf) {
                ArrayList arrayList = new ArrayList(listZze.size());
                Iterator<String> it = listZze.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().toUpperCase(Locale.ENGLISH));
                }
                listZze = Collections.unmodifiableList(arrayList);
            }
            list = listZze;
        }
        return zzd(str, iZzj, zZzf, strZzd, list, iZzj == 2 ? strZzd : null, zzelVar);
    }

    public static Boolean zzg(double d2, com.google.android.gms.internal.measurement.zzeq zzeqVar) {
        try {
            return zze(new BigDecimal(d2), zzeqVar, Math.ulp(d2));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static Boolean zzh(long j, com.google.android.gms.internal.measurement.zzeq zzeqVar) {
        try {
            return zze(new BigDecimal(j), zzeqVar, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static Boolean zzi(String str, com.google.android.gms.internal.measurement.zzeq zzeqVar) {
        if (!zzku.zzy(str)) {
            return null;
        }
        try {
            return zze(new BigDecimal(str), zzeqVar, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @VisibleForTesting
    public static Boolean zzj(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z);
    }

    public abstract int zza();

    public abstract boolean zzb();

    public abstract boolean zzc();
}
