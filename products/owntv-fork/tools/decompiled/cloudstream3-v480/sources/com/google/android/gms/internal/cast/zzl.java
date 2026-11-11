package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzl {
    private static final Logger zza = new Logger("ApplicationAnalytics");
    private final zzh zzb;
    private final zzaf zzc;
    private final zzn zzd;
    private final SharedPreferences zzg;
    private zzm zzh;
    private CastSession zzi;
    private boolean zzj;
    private final Handler zzf = new zzet(Looper.getMainLooper());
    private final Runnable zze = new Runnable() { // from class: com.google.android.gms.internal.cast.zzi
        @Override // java.lang.Runnable
        public final void run() {
            zzl.zzf(this.zza);
        }
    };

    public zzl(SharedPreferences sharedPreferences, zzh zzhVar, zzaf zzafVar, Bundle bundle, String str) {
        this.zzg = sharedPreferences;
        this.zzb = zzhVar;
        this.zzc = zzafVar;
        this.zzd = new zzn(bundle, str);
    }

    public static /* synthetic */ void zzf(zzl zzlVar) {
        zzm zzmVar = zzlVar.zzh;
        if (zzmVar != null) {
            zzlVar.zzb.zzf(zzlVar.zzd.zza(zzmVar), 223);
        }
        zzlVar.zzu();
    }

    static /* bridge */ /* synthetic */ void zzm(zzl zzlVar, int i) {
        zza.d("log session ended with error = %d", Integer.valueOf(i));
        zzlVar.zzs();
        zzlVar.zzb.zzf(zzlVar.zzd.zze(zzlVar.zzh, i), 228);
        zzlVar.zzr();
        if (zzlVar.zzj) {
            return;
        }
        zzlVar.zzh = null;
    }

    static /* bridge */ /* synthetic */ void zzn(zzl zzlVar, SharedPreferences sharedPreferences, String str) {
        boolean z = false;
        if (zzlVar.zzx(str)) {
            zza.d("Use the existing ApplicationAnalyticsSession if it is available and valid.", new Object[0]);
            Preconditions.checkNotNull(zzlVar.zzh);
            return;
        }
        zzaf zzafVar = zzlVar.zzc;
        zzlVar.zzh = zzm.zzb(sharedPreferences, zzafVar);
        if (zzlVar.zzx(str)) {
            zza.d("Use the restored ApplicationAnalyticsSession if it is valid.", new Object[0]);
            Preconditions.checkNotNull(zzlVar.zzh);
            zzm.zza = zzlVar.zzh.zzd + 1;
            return;
        }
        zza.d("The restored ApplicationAnalyticsSession is not valid, create a new one.", new Object[0]);
        zzm zzmVarZza = zzm.zza(zzafVar);
        zzlVar.zzh = zzmVarZza;
        zzm zzmVar = (zzm) Preconditions.checkNotNull(zzmVarZza);
        CastSession castSession = zzlVar.zzi;
        if (castSession != null && castSession.zzl()) {
            z = true;
        }
        zzmVar.zzo = z;
        ((zzm) Preconditions.checkNotNull(zzlVar.zzh)).zzb = zzq();
        ((zzm) Preconditions.checkNotNull(zzlVar.zzh)).zzf = str;
    }

    @Pure
    private static String zzq() {
        return ((CastContext) Preconditions.checkNotNull(CastContext.getSharedInstance())).getCastOptions().getReceiverApplicationId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzr() {
        this.zzf.removeCallbacks(this.zze);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @EnsuresNonNull({"analyticsSession"})
    public final void zzs() {
        if (!zzw()) {
            zza.w("The analyticsSession should not be null for logging. Create a dummy one.", new Object[0]);
            zzt();
            return;
        }
        CastSession castSession = this.zzi;
        CastDevice castDevice = castSession != null ? castSession.getCastDevice() : null;
        if (castDevice != null && !TextUtils.equals(this.zzh.zzc, castDevice.zzd())) {
            zzv(castDevice);
        }
        Preconditions.checkNotNull(this.zzh);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @EnsuresNonNull({"analyticsSession"})
    public final void zzt() {
        zza.d("Create a new ApplicationAnalyticsSession based on CastSession", new Object[0]);
        zzm zzmVarZza = zzm.zza(this.zzc);
        this.zzh = zzmVarZza;
        zzm zzmVar = (zzm) Preconditions.checkNotNull(zzmVarZza);
        CastSession castSession = this.zzi;
        zzmVar.zzo = castSession != null && castSession.zzl();
        ((zzm) Preconditions.checkNotNull(this.zzh)).zzb = zzq();
        CastSession castSession2 = this.zzi;
        CastDevice castDevice = castSession2 == null ? null : castSession2.getCastDevice();
        if (castDevice != null) {
            zzv(castDevice);
        }
        zzm zzmVar2 = (zzm) Preconditions.checkNotNull(this.zzh);
        CastSession castSession3 = this.zzi;
        zzmVar2.zzp = castSession3 != null ? castSession3.zzm() : 0;
        Preconditions.checkNotNull(this.zzh);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzu() {
        ((Handler) Preconditions.checkNotNull(this.zzf)).postDelayed((Runnable) Preconditions.checkNotNull(this.zze), 300000L);
    }

    private final void zzv(CastDevice castDevice) {
        zzm zzmVar = this.zzh;
        if (zzmVar == null) {
            return;
        }
        zzmVar.zzc = castDevice.zzd();
        zzmVar.zzg = castDevice.zza();
        zzmVar.zzh = castDevice.getModelName();
        zzmVar.zzn = castDevice.zzb();
        com.google.android.gms.cast.internal.zzaa zzaaVarZzc = castDevice.zzc();
        if (zzaaVarZzc != null) {
            String strZzc = zzaaVarZzc.zzc();
            if (strZzc != null) {
                zzmVar.zzi = strZzc;
            }
            String strZzd = zzaaVarZzc.zzd();
            if (strZzd != null) {
                zzmVar.zzj = strZzd;
            }
            String strZza = zzaaVarZzc.zza();
            if (strZza != null) {
                zzmVar.zzk = strZza;
            }
            String strZzb = zzaaVarZzc.zzb();
            if (strZzb != null) {
                zzmVar.zzl = strZzb;
            }
            String strZze = zzaaVarZzc.zze();
            if (strZze != null) {
                zzmVar.zzm = strZze;
            }
        }
    }

    @EnsuresNonNullIf(expression = {"analyticsSession"}, result = true)
    private final boolean zzw() {
        String str;
        if (this.zzh == null) {
            zza.d("The analytics session is null when matching with application ID.", new Object[0]);
            return false;
        }
        String strZzq = zzq();
        if (strZzq == null || (str = this.zzh.zzb) == null || !TextUtils.equals(str, strZzq)) {
            zza.d("The analytics session doesn't match the application ID %s", strZzq);
            return false;
        }
        Preconditions.checkNotNull(this.zzh);
        return true;
    }

    private final boolean zzx(String str) {
        String str2;
        if (!zzw()) {
            return false;
        }
        Preconditions.checkNotNull(this.zzh);
        if (str != null && (str2 = this.zzh.zzf) != null && TextUtils.equals(str2, str)) {
            return true;
        }
        zza.d("The analytics session doesn't match the receiver session ID %s.", str);
        return false;
    }
}
