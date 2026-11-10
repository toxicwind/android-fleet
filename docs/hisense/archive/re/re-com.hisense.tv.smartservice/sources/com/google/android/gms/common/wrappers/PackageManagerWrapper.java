package com.google.android.gms.common.wrappers;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Build;
import android.os.Process;
import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class PackageManagerWrapper {

    @NonNull
    public final Context zza;

    public PackageManagerWrapper(@NonNull Context context) {
        this.zza = context;
    }

    @KeepForSdk
    public int checkCallingOrSelfPermission(@NonNull String str) {
        return this.zza.checkCallingOrSelfPermission(str);
    }

    @KeepForSdk
    public int checkPermission(@NonNull String str, @NonNull String str2) {
        return this.zza.getPackageManager().checkPermission(str, str2);
    }

    @NonNull
    @KeepForSdk
    public ApplicationInfo getApplicationInfo(@NonNull String str, int i) {
        return this.zza.getPackageManager().getApplicationInfo(str, i);
    }

    @NonNull
    @KeepForSdk
    public CharSequence getApplicationLabel(@NonNull String str) {
        return this.zza.getPackageManager().getApplicationLabel(this.zza.getPackageManager().getApplicationInfo(str, 0));
    }

    @NonNull
    @KeepForSdk
    public Pair<CharSequence, Drawable> getApplicationLabelAndIcon(@NonNull String str) throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo = this.zza.getPackageManager().getApplicationInfo(str, 0);
        return new Pair<>(this.zza.getPackageManager().getApplicationLabel(applicationInfo), this.zza.getPackageManager().getApplicationIcon(applicationInfo));
    }

    @NonNull
    @KeepForSdk
    public PackageInfo getPackageInfo(@NonNull String str, int i) {
        return this.zza.getPackageManager().getPackageInfo(str, i);
    }

    @KeepForSdk
    public boolean isCallerInstantApp() {
        if (Binder.getCallingUid() == Process.myUid()) {
            return InstantApps.isInstantApp(this.zza);
        }
        int i = Build.VERSION.SDK_INT;
        String nameForUid = this.zza.getPackageManager().getNameForUid(Binder.getCallingUid());
        if (nameForUid != null) {
            return this.zza.getPackageManager().isInstantApp(nameForUid);
        }
        return false;
    }

    @TargetApi(19)
    public final boolean zza(int i, @NonNull String str) {
        int i2 = Build.VERSION.SDK_INT;
        try {
            AppOpsManager appOpsManager = (AppOpsManager) this.zza.getSystemService("appops");
            if (appOpsManager == null) {
                throw new NullPointerException("context.getSystemService(Context.APP_OPS_SERVICE) is null");
            }
            appOpsManager.checkPackage(i, str);
            return true;
        } catch (SecurityException unused) {
            return false;
        }
    }
}
