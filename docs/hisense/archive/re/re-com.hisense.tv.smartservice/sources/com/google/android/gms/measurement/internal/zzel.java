package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;

/* JADX INFO: loaded from: classes.dex */
public final class zzel extends zzgp {
    public char zza;
    public long zzb;

    @GuardedBy("this")
    public String zzc;
    public final zzej zzd;
    public final zzej zze;
    public final zzej zzf;
    public final zzej zzg;
    public final zzej zzh;
    public final zzej zzi;
    public final zzej zzj;
    public final zzej zzk;
    public final zzej zzl;

    public zzel(zzfv zzfvVar) {
        super(zzfvVar);
        this.zza = (char) 0;
        this.zzb = -1L;
        this.zzd = new zzej(this, 6, false, false);
        this.zze = new zzej(this, 6, true, false);
        this.zzf = new zzej(this, 6, false, true);
        this.zzg = new zzej(this, 5, false, false);
        this.zzh = new zzej(this, 5, true, false);
        this.zzi = new zzej(this, 5, false, true);
        this.zzj = new zzej(this, 4, false, false);
        this.zzk = new zzej(this, 3, false, false);
        this.zzl = new zzej(this, 2, false, false);
    }

    public static Object zzn(String str) {
        if (str == null) {
            return null;
        }
        return new zzek(str);
    }

    public static String zzo(boolean z, String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = "";
        }
        String strZzp = zzp(z, obj);
        String strZzp2 = zzp(z, obj2);
        String strZzp3 = zzp(z, obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(strZzp)) {
            sb.append(str2);
            sb.append(strZzp);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(strZzp2)) {
            sb.append(str2);
            sb.append(strZzp2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(strZzp3)) {
            sb.append(str2);
            sb.append(strZzp3);
        }
        return sb.toString();
    }

    @VisibleForTesting
    public static String zzp(boolean z, Object obj) {
        String className;
        if (obj == null) {
            return "";
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf(((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            if (!z) {
                return obj.toString();
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return obj.toString();
            }
            String str = obj.toString().charAt(0) == '-' ? MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING : "";
            String strValueOf = String.valueOf(Math.abs(l.longValue()));
            long jRound = Math.round(Math.pow(10.0d, strValueOf.length() - 1));
            long jRound2 = Math.round(Math.pow(10.0d, strValueOf.length()) - 1.0d);
            StringBuilder sb = new StringBuilder(str.length() + str.length() + 43);
            sb.append(str);
            sb.append(jRound);
            sb.append("...");
            sb.append(str);
            sb.append(jRound2);
            return sb.toString();
        }
        if (obj instanceof Boolean) {
            return obj.toString();
        }
        if (!(obj instanceof Throwable)) {
            if (obj instanceof zzek) {
                return ((zzek) obj).zza;
            }
            return z ? MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING : obj.toString();
        }
        Throwable th = (Throwable) obj;
        StringBuilder sb2 = new StringBuilder(z ? th.getClass().getName() : th.toString());
        String strZzy = zzy(zzfv.class.getCanonicalName());
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null && zzy(className).equals(strZzy)) {
                sb2.append(": ");
                sb2.append(stackTraceElement);
                break;
            }
        }
        return sb2.toString();
    }

    public static String zzy(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf == -1 ? str : str.substring(0, iLastIndexOf);
    }

    public final zzej zzc() {
        return this.zzk;
    }

    public final zzej zzd() {
        return this.zzd;
    }

    public final zzej zze() {
        return this.zzf;
    }

    @Override // com.google.android.gms.measurement.internal.zzgp
    public final boolean zzf() {
        return false;
    }

    public final zzej zzh() {
        return this.zze;
    }

    public final zzej zzi() {
        return this.zzj;
    }

    public final zzej zzj() {
        return this.zzl;
    }

    public final zzej zzk() {
        return this.zzg;
    }

    public final zzej zzl() {
        return this.zzi;
    }

    public final zzej zzm() {
        return this.zzh;
    }

    @VisibleForTesting
    public final String zzq() {
        String str;
        synchronized (this) {
            if (this.zzc == null) {
                if (this.zzs.zzy() != null) {
                    this.zzc = this.zzs.zzy();
                } else {
                    this.zzc = this.zzs.zzf().zzn();
                }
            }
            Preconditions.checkNotNull(this.zzc);
            str = this.zzc;
        }
        return str;
    }

    public final void zzt(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && Log.isLoggable(zzq(), i)) {
            Log.println(i, zzq(), zzo(false, str, obj, obj2, obj3));
        }
        if (z2 || i < 5) {
            return;
        }
        Preconditions.checkNotNull(str);
        zzfs zzfsVarZzo = this.zzs.zzo();
        if (zzfsVarZzo == null) {
            Log.println(6, zzq(), "Scheduler not set. Not logging error/warn");
        } else {
            if (!zzfsVarZzo.zzx()) {
                Log.println(6, zzq(), "Scheduler not initialized. Not logging error/warn");
                return;
            }
            if (i >= 9) {
                i = 8;
            }
            zzfsVarZzo.zzp(new zzei(this, i, str, obj, obj2, obj3));
        }
    }
}
