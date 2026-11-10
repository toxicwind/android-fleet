package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class zzhx implements Runnable {
    public final /* synthetic */ boolean zza;
    public final /* synthetic */ Uri zzb;
    public final /* synthetic */ String zzc;
    public final /* synthetic */ String zzd;
    public final /* synthetic */ zzhz zze;

    public zzhx(zzhz zzhzVar, boolean z, Uri uri, String str, String str2) {
        this.zze = zzhzVar;
        this.zza = z;
        this.zzb = uri;
        this.zzc = str;
        this.zzd = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundleZzs;
        Bundle bundleZzs2;
        zzhz zzhzVar = this.zze;
        boolean z = this.zza;
        Uri uri = this.zzb;
        String str = this.zzc;
        String str2 = this.zzd;
        zzhzVar.zza.zzg();
        try {
            zzkz zzkzVarZzv = zzhzVar.zza.zzs.zzv();
            if (TextUtils.isEmpty(str2)) {
                bundleZzs = null;
            } else if (str2.contains("gclid") || str2.contains("utm_campaign") || str2.contains("utm_source") || str2.contains("utm_medium")) {
                bundleZzs = zzkzVarZzv.zzs(Uri.parse(str2.length() != 0 ? "https://google.com/search?".concat(str2) : new String("https://google.com/search?")));
                if (bundleZzs != null) {
                    bundleZzs.putString("_cis", "referrer");
                }
            } else {
                zzkzVarZzv.zzs.zzay().zzc().zza("Activity created with data 'referrer' without required params");
                bundleZzs = null;
            }
            if (z && (bundleZzs2 = zzhzVar.zza.zzs.zzv().zzs(uri)) != null) {
                bundleZzs2.putString("_cis", "intent");
                if (!bundleZzs2.containsKey("gclid") && bundleZzs != null && bundleZzs.containsKey("gclid")) {
                    bundleZzs2.putString("_cer", String.format("gclid=%s", bundleZzs.getString("gclid")));
                }
                zzhzVar.zza.zzG(str, "_cmp", bundleZzs2);
                zzhzVar.zza.zzb.zza(str, bundleZzs2);
            }
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            zzhzVar.zza.zzs.zzay().zzc().zzb("Activity created with referrer", str2);
            if (zzhzVar.zza.zzs.zzf().zzs(null, zzdy.zzaa)) {
                if (bundleZzs != null) {
                    zzhzVar.zza.zzG(str, "_cmp", bundleZzs);
                    zzhzVar.zza.zzb.zza(str, bundleZzs);
                } else {
                    zzhzVar.zza.zzs.zzay().zzc().zzb("Referrer does not contain valid parameters", str2);
                }
                zzhzVar.zza.zzX("auto", "_ldl", null, true);
                return;
            }
            if (!str2.contains("gclid") || (!str2.contains("utm_campaign") && !str2.contains("utm_source") && !str2.contains("utm_medium") && !str2.contains("utm_term") && !str2.contains("utm_content"))) {
                zzhzVar.zza.zzs.zzay().zzc().zza("Activity created with data 'referrer' without required params");
            } else {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                zzhzVar.zza.zzX("auto", "_ldl", str2, true);
            }
        } catch (RuntimeException e2) {
            a.a(zzhzVar.zza.zzs, "Throwable caught in handleReferrerForOnActivityCreated", e2);
        }
    }
}
