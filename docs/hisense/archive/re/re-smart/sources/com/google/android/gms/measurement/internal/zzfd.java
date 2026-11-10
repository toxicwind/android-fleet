package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;

/* JADX INFO: loaded from: classes.dex */
public final class zzfd {
    public final zzfv zza;

    public zzfd(zzks zzksVar) {
        this.zza = zzksVar.zzq();
    }

    @VisibleForTesting
    public final boolean zza() {
        try {
            PackageManagerWrapper packageManagerWrapperPackageManager = Wrappers.packageManager(this.zza.zzau());
            if (packageManagerWrapperPackageManager != null) {
                return packageManagerWrapperPackageManager.getPackageInfo("com.android.vending", 128).versionCode >= 80837300;
            }
            this.zza.zzay().zzj().zza("Failed to get PackageManager for Install Referrer Play Store compatibility check");
            return false;
        } catch (Exception e2) {
            this.zza.zzay().zzj().zzb("Failed to retrieve Play Store version for Install Referrer", e2);
            return false;
        }
    }
}
