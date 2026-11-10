package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamite.DynamiteModule;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class ProviderInstaller {

    @NonNull
    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    public static final GoogleApiAvailabilityLight zza = GoogleApiAvailabilityLight.zza;
    public static final Object zzb = new Object();

    @GuardedBy("ProviderInstaller.lock")
    public static Method zzc = null;

    @GuardedBy("ProviderInstaller.lock")
    public static Method zzd = null;

    public interface ProviderInstallListener {
        void onProviderInstallFailed(int i, @Nullable Intent intent);

        void onProviderInstalled();
    }

    public static void installIfNeeded(@NonNull Context context) {
        Context moduleContext;
        Preconditions.checkNotNull(context, "Context must not be null");
        zza.verifyGooglePlayServicesIsAvailable(context, 11925000);
        synchronized (zzb) {
            try {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                try {
                    moduleContext = DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.providerinstaller.dynamite").getModuleContext();
                } catch (DynamiteModule.LoadingException e2) {
                    String strValueOf = String.valueOf(e2.getMessage());
                    Log.w("ProviderInstaller", strValueOf.length() != 0 ? "Failed to load providerinstaller module: ".concat(strValueOf) : new String("Failed to load providerinstaller module: "));
                    moduleContext = null;
                }
                if (moduleContext != null) {
                    zzc(moduleContext, context, "com.google.android.gms.providerinstaller.ProviderInstallerImpl");
                    return;
                }
                long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
                if (remoteContext != null) {
                    try {
                        if (zzd == null) {
                            zzd = remoteContext.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("reportRequestStats", Context.class, Long.TYPE, Long.TYPE);
                        }
                        zzd.invoke(null, context, Long.valueOf(jElapsedRealtime), Long.valueOf(jElapsedRealtime2));
                    } catch (Exception e3) {
                        String strValueOf2 = String.valueOf(e3.getMessage());
                        Log.w("ProviderInstaller", strValueOf2.length() != 0 ? "Failed to report request stats: ".concat(strValueOf2) : new String("Failed to report request stats: "));
                    }
                }
                if (remoteContext != null) {
                    zzc(remoteContext, context, "com.google.android.gms.common.security.ProviderInstallerImpl");
                } else {
                    Log.e("ProviderInstaller", "Failed to get remote context");
                    throw new GooglePlayServicesNotAvailableException(8);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void installIfNeededAsync(@NonNull Context context, @NonNull ProviderInstallListener providerInstallListener) {
        Preconditions.checkNotNull(context, "Context must not be null");
        Preconditions.checkNotNull(providerInstallListener, "Listener must not be null");
        Preconditions.checkMainThread("Must be called on the UI thread");
        new zza(context, providerInstallListener).execute(new Void[0]);
    }

    public static Method zzb(Context context, String str, String str2, Class[] clsArr) {
        return context.getClassLoader().loadClass(str).getMethod(str2, clsArr);
    }

    @GuardedBy("ProviderInstaller.lock")
    public static void zzc(Context context, Context context2, String str) throws GooglePlayServicesNotAvailableException {
        try {
            if (zzc == null) {
                zzc = context.getClassLoader().loadClass(str).getMethod("insertProvider", Context.class);
            }
            zzc.invoke(null, context);
        } catch (Exception e2) {
            Throwable cause = e2.getCause();
            if (Log.isLoggable("ProviderInstaller", 6)) {
                String strValueOf = String.valueOf(cause == null ? e2.getMessage() : cause.getMessage());
                Log.e("ProviderInstaller", strValueOf.length() != 0 ? "Failed to install provider: ".concat(strValueOf) : new String("Failed to install provider: "));
            }
            throw new GooglePlayServicesNotAvailableException(8);
        }
    }
}
