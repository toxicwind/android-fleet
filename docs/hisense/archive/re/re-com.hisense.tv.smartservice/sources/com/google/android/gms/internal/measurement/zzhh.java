package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Binder;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.core.content.PermissionChecker;

/* JADX INFO: loaded from: classes.dex */
public final class zzhh implements zzhe {

    @GuardedBy("GservicesLoader.class")
    public static zzhh zza;
    public final Context zzb;
    public final ContentObserver zzc;

    public zzhh() {
        this.zzb = null;
        this.zzc = null;
    }

    public zzhh(Context context) {
        this.zzb = context;
        this.zzc = new zzhg(this, null);
        context.getContentResolver().registerContentObserver(zzgv.zza, true, this.zzc);
    }

    public static zzhh zza(Context context) {
        zzhh zzhhVar;
        synchronized (zzhh.class) {
            if (zza == null) {
                zza = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzhh(context) : new zzhh();
            }
            zzhhVar = zza;
        }
        return zzhhVar;
    }

    public static synchronized void zze() {
        if (zza != null && zza.zzb != null && zza.zzc != null) {
            zza.zzb.getContentResolver().unregisterContentObserver(zza.zzc);
        }
        zza = null;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x002e  */
    /* JADX WARN: Code duplicated, block: B:22:0x0033  */
    @Override // com.google.android.gms.internal.measurement.zzhe
    /* JADX INFO: renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final String zzb(String str) {
        String strValueOf;
        String str2;
        if (this.zzb == null) {
            return null;
        }
        try {
            try {
                try {
                    return zzd(str);
                } catch (SecurityException e2) {
                    e = e2;
                    strValueOf = String.valueOf(str);
                    if (strValueOf.length() != 0) {
                        str2 = "Unable to read GServices for: ".concat(strValueOf);
                    } else {
                        str2 = new String("Unable to read GServices for: ");
                    }
                    Log.e("GservicesLoader", str2, e);
                    return null;
                }
            } catch (SecurityException unused) {
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    return zzd(str);
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            }
        } catch (IllegalStateException e3) {
            e = e3;
            strValueOf = String.valueOf(str);
            if (strValueOf.length() != 0) {
                str2 = "Unable to read GServices for: ".concat(strValueOf);
            } else {
                str2 = new String("Unable to read GServices for: ");
            }
            Log.e("GservicesLoader", str2, e);
            return null;
        } catch (NullPointerException e4) {
            e = e4;
            strValueOf = String.valueOf(str);
            if (strValueOf.length() != 0) {
                str2 = "Unable to read GServices for: ".concat(strValueOf);
            } else {
                str2 = new String("Unable to read GServices for: ");
            }
            Log.e("GservicesLoader", str2, e);
            return null;
        }
    }

    public final /* synthetic */ String zzd(String str) {
        return zzgv.zza(this.zzb.getContentResolver(), str, null);
    }
}
