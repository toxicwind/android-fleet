package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.google.protobuf.MessageSchema;

/* JADX INFO: loaded from: classes.dex */
public final class zzhj {
    public static volatile zzhz<Boolean> zza = zzhx.zza;
    public static final Object zzb = new Object();

    public static boolean zza(Context context, Uri uri) {
        String authority = uri.getAuthority();
        boolean z = false;
        if (!"com.google.android.gms.phenotype".equals(authority)) {
            String.valueOf(authority).length();
            Log.e("PhenotypeClientHelper", String.valueOf(authority).concat(" is an unsupported authority. Only com.google.android.gms.phenotype authority is supported."));
            return false;
        }
        if (zza.zzb()) {
            return zza.zza().booleanValue();
        }
        synchronized (zzb) {
            if (zza.zzb()) {
                return zza.zza().booleanValue();
            }
            try {
                if (!"com.google.android.gms".equals(context.getPackageName())) {
                    ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.phenotype", Build.VERSION.SDK_INT < 29 ? 0 : MessageSchema.REQUIRED_MASK);
                    if (providerInfoResolveContentProvider != null && "com.google.android.gms".equals(providerInfoResolveContentProvider.packageName)) {
                    }
                    zza = new zzia(Boolean.valueOf(z));
                    return zza.zza().booleanValue();
                }
                if ((context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & 129) != 0) {
                    z = true;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            zza = new zzia(Boolean.valueOf(z));
            return zza.zza().booleanValue();
        }
    }
}
