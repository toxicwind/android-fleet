package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import androidx.core.os.EnvironmentCompat;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.InstantApps;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zznx;
import com.google.android.gms.internal.measurement.zzom;
import com.google.android.gms.internal.measurement.zzpt;
import com.google.firebase.FirebaseOptions;
import com.hisense.support.tv.HisenseCountryLanInfo;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzec extends zzf {
    public String zza;
    public String zzb;
    public int zzc;
    public String zzd;
    public String zze;
    public long zzf;
    public final long zzg;
    public List<String> zzh;
    public int zzi;
    public String zzj;
    public String zzk;
    public String zzl;

    public zzec(zzfv zzfvVar, long j) {
        super(zzfvVar);
        this.zzg = j;
    }

    /* JADX WARN: Code duplicated, block: B:108:0x0277 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:31:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:34:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:35:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:36:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:37:0x0100  */
    /* JADX WARN: Code duplicated, block: B:38:0x0108  */
    /* JADX WARN: Code duplicated, block: B:39:0x0118  */
    /* JADX WARN: Code duplicated, block: B:40:0x0128  */
    /* JADX WARN: Code duplicated, block: B:41:0x0130  */
    /* JADX WARN: Code duplicated, block: B:42:0x0140  */
    /* JADX WARN: Code duplicated, block: B:45:0x0154  */
    /* JADX WARN: Code duplicated, block: B:49:0x0175  */
    /* JADX WARN: Code duplicated, block: B:50:0x0177  */
    /* JADX WARN: Code duplicated, block: B:54:0x018d A[Catch: IllegalStateException -> 0x0218, TRY_ENTER, TryCatch #2 {IllegalStateException -> 0x0218, blocks: (B:47:0x015d, B:51:0x0178, B:54:0x018d, B:58:0x01ab, B:61:0x01b8, B:63:0x01c0, B:74:0x01f9, B:76:0x020f, B:78:0x0214, B:77:0x0212, B:65:0x01c6, B:57:0x01a7, B:66:0x01cd, B:68:0x01d3, B:72:0x01f1, B:71:0x01ed), top: B:104:0x015d }] */
    /* JADX WARN: Code duplicated, block: B:56:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:57:0x01a7 A[Catch: IllegalStateException -> 0x0218, TryCatch #2 {IllegalStateException -> 0x0218, blocks: (B:47:0x015d, B:51:0x0178, B:54:0x018d, B:58:0x01ab, B:61:0x01b8, B:63:0x01c0, B:74:0x01f9, B:76:0x020f, B:78:0x0214, B:77:0x0212, B:65:0x01c6, B:57:0x01a7, B:66:0x01cd, B:68:0x01d3, B:72:0x01f1, B:71:0x01ed), top: B:104:0x015d }] */
    /* JADX WARN: Code duplicated, block: B:60:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:65:0x01c6 A[Catch: IllegalStateException -> 0x0218, TryCatch #2 {IllegalStateException -> 0x0218, blocks: (B:47:0x015d, B:51:0x0178, B:54:0x018d, B:58:0x01ab, B:61:0x01b8, B:63:0x01c0, B:74:0x01f9, B:76:0x020f, B:78:0x0214, B:77:0x0212, B:65:0x01c6, B:57:0x01a7, B:66:0x01cd, B:68:0x01d3, B:72:0x01f1, B:71:0x01ed), top: B:104:0x015d }] */
    /* JADX WARN: Code duplicated, block: B:66:0x01cd A[Catch: IllegalStateException -> 0x0218, TryCatch #2 {IllegalStateException -> 0x0218, blocks: (B:47:0x015d, B:51:0x0178, B:54:0x018d, B:58:0x01ab, B:61:0x01b8, B:63:0x01c0, B:74:0x01f9, B:76:0x020f, B:78:0x0214, B:77:0x0212, B:65:0x01c6, B:57:0x01a7, B:66:0x01cd, B:68:0x01d3, B:72:0x01f1, B:71:0x01ed), top: B:104:0x015d }] */
    /* JADX WARN: Code duplicated, block: B:68:0x01d3 A[Catch: IllegalStateException -> 0x0218, TryCatch #2 {IllegalStateException -> 0x0218, blocks: (B:47:0x015d, B:51:0x0178, B:54:0x018d, B:58:0x01ab, B:61:0x01b8, B:63:0x01c0, B:74:0x01f9, B:76:0x020f, B:78:0x0214, B:77:0x0212, B:65:0x01c6, B:57:0x01a7, B:66:0x01cd, B:68:0x01d3, B:72:0x01f1, B:71:0x01ed), top: B:104:0x015d }] */
    /* JADX WARN: Code duplicated, block: B:70:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:71:0x01ed A[Catch: IllegalStateException -> 0x0218, TryCatch #2 {IllegalStateException -> 0x0218, blocks: (B:47:0x015d, B:51:0x0178, B:54:0x018d, B:58:0x01ab, B:61:0x01b8, B:63:0x01c0, B:74:0x01f9, B:76:0x020f, B:78:0x0214, B:77:0x0212, B:65:0x01c6, B:57:0x01a7, B:66:0x01cd, B:68:0x01d3, B:72:0x01f1, B:71:0x01ed), top: B:104:0x015d }] */
    /* JADX WARN: Code duplicated, block: B:74:0x01f9 A[Catch: IllegalStateException -> 0x0218, TryCatch #2 {IllegalStateException -> 0x0218, blocks: (B:47:0x015d, B:51:0x0178, B:54:0x018d, B:58:0x01ab, B:61:0x01b8, B:63:0x01c0, B:74:0x01f9, B:76:0x020f, B:78:0x0214, B:77:0x0212, B:65:0x01c6, B:57:0x01a7, B:66:0x01cd, B:68:0x01d3, B:72:0x01f1, B:71:0x01ed), top: B:104:0x015d }] */
    /* JADX WARN: Code duplicated, block: B:76:0x020f A[Catch: IllegalStateException -> 0x0218, TryCatch #2 {IllegalStateException -> 0x0218, blocks: (B:47:0x015d, B:51:0x0178, B:54:0x018d, B:58:0x01ab, B:61:0x01b8, B:63:0x01c0, B:74:0x01f9, B:76:0x020f, B:78:0x0214, B:77:0x0212, B:65:0x01c6, B:57:0x01a7, B:66:0x01cd, B:68:0x01d3, B:72:0x01f1, B:71:0x01ed), top: B:104:0x015d }] */
    /* JADX WARN: Code duplicated, block: B:77:0x0212 A[Catch: IllegalStateException -> 0x0218, TryCatch #2 {IllegalStateException -> 0x0218, blocks: (B:47:0x015d, B:51:0x0178, B:54:0x018d, B:58:0x01ab, B:61:0x01b8, B:63:0x01c0, B:74:0x01f9, B:76:0x020f, B:78:0x0214, B:77:0x0212, B:65:0x01c6, B:57:0x01a7, B:66:0x01cd, B:68:0x01d3, B:72:0x01f1, B:71:0x01ed), top: B:104:0x015d }] */
    /* JADX WARN: Code duplicated, block: B:85:0x0242  */
    /* JADX WARN: Code duplicated, block: B:87:0x0248  */
    /* JADX WARN: Code duplicated, block: B:88:0x0258  */
    /* JADX WARN: Code duplicated, block: B:91:0x0262  */
    /* JADX WARN: Code duplicated, block: B:94:0x0277  */
    /* JADX WARN: Code duplicated, block: B:96:0x027b  */
    /* JADX WARN: Code duplicated, block: B:98:0x0288  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.measurement.internal.zzf
    @WorkerThread
    public final void zzd() {
        String str;
        String string;
        Object[] objArr;
        int iZza;
        List<String> listZzp;
        Iterator<String> it;
        String strZzc;
        String str2;
        Context contextZzau;
        String strZzz;
        String str3;
        Context contextZzau2;
        String strZzz2;
        Resources resources;
        String packageName = this.zzs.zzau().getPackageName();
        PackageManager packageManager = this.zzs.zzau().getPackageManager();
        String str4 = "Unknown";
        int i = Integer.MIN_VALUE;
        String installerPackageName = EnvironmentCompat.MEDIA_UNKNOWN;
        try {
            if (packageManager != null) {
                try {
                    installerPackageName = packageManager.getInstallerPackageName(packageName);
                } catch (IllegalArgumentException unused) {
                    this.zzs.zzay().zzd().zzb("Error retrieving app installer package name. appId", zzel.zzn(packageName));
                }
                if (installerPackageName == null) {
                    installerPackageName = "manual_install";
                } else if ("com.android.vending".equals(installerPackageName)) {
                    installerPackageName = "";
                }
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(this.zzs.zzau().getPackageName(), 0);
                    if (packageInfo != null) {
                        CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                        string = !TextUtils.isEmpty(applicationLabel) ? applicationLabel.toString() : "Unknown";
                        try {
                            str4 = packageInfo.versionName;
                            i = packageInfo.versionCode;
                        } catch (PackageManager.NameNotFoundException unused2) {
                            str = str4;
                            str4 = string;
                            this.zzs.zzay().zzd().zzc("Error retrieving package info. appId, appName", zzel.zzn(packageName), str4);
                            string = str4;
                            str4 = str;
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused3) {
                    str = "Unknown";
                }
                this.zza = packageName;
                this.zzd = installerPackageName;
                this.zzb = str4;
                this.zzc = i;
                this.zze = string;
                this.zzf = 0L;
                if (TextUtils.isEmpty(this.zzs.zzw()) && HisenseCountryLanInfo.LOCALE_AMH_CODE.equals(this.zzs.zzx())) {
                    objArr = true;
                } else {
                    objArr = false;
                }
                iZza = this.zzs.zza();
                switch (iZza) {
                    case 0:
                        a.b(this.zzs, "App measurement collection enabled");
                        break;
                    case 1:
                        this.zzs.zzay().zzi().zza("App measurement deactivated via the manifest");
                        break;
                    case 2:
                        a.b(this.zzs, "App measurement deactivated via the init parameters");
                        break;
                    case 3:
                        this.zzs.zzay().zzi().zza("App measurement disabled by setAnalyticsCollectionEnabled(false)");
                        break;
                    case 4:
                        this.zzs.zzay().zzi().zza("App measurement disabled via the manifest");
                        break;
                    case 5:
                        a.b(this.zzs, "App measurement disabled via the init parameters");
                        break;
                    case 6:
                        this.zzs.zzay().zzl().zza("App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics");
                        break;
                    case 7:
                        this.zzs.zzay().zzi().zza("App measurement disabled via the global data collection setting");
                        break;
                    default:
                        this.zzs.zzay().zzi().zza("App measurement disabled due to denied storage consent");
                        break;
                }
                this.zzj = "";
                this.zzk = "";
                this.zzl = "";
                this.zzs.zzaw();
                if (objArr != false) {
                    this.zzk = this.zzs.zzw();
                }
                strZzc = zzig.zzc(this.zzs.zzau(), FirebaseOptions.APP_ID_RESOURCE_NAME, this.zzs.zzz());
                if (true != TextUtils.isEmpty(strZzc)) {
                    str2 = strZzc;
                } else {
                    str2 = "";
                }
                this.zzj = str2;
                zzom.zzc();
                if (this.zzs.zzf().zzs(null, zzdy.zzac)) {
                    contextZzau2 = this.zzs.zzau();
                    strZzz2 = this.zzs.zzz();
                    Preconditions.checkNotNull(contextZzau2);
                    resources = contextZzau2.getResources();
                    if (!TextUtils.isEmpty(strZzz2)) {
                        strZzz2 = zzfn.zza(contextZzau2);
                    }
                    String strZzb = zzfn.zzb("ga_app_id", resources, strZzz2);
                    this.zzl = true != TextUtils.isEmpty(strZzb) ? strZzb : "";
                    if (TextUtils.isEmpty(strZzc) || !TextUtils.isEmpty(strZzb)) {
                        this.zzk = zzfn.zzb("admob_app_id", resources, strZzz2);
                    }
                } else if (!TextUtils.isEmpty(strZzc)) {
                    contextZzau = this.zzs.zzau();
                    strZzz = this.zzs.zzz();
                    Preconditions.checkNotNull(contextZzau);
                    Resources resources2 = contextZzau.getResources();
                    if (!TextUtils.isEmpty(strZzz)) {
                        strZzz = zzfn.zza(contextZzau);
                    }
                    this.zzk = zzfn.zzb("admob_app_id", resources2, strZzz);
                }
                if (iZza == 0) {
                    zzej zzejVarZzj = this.zzs.zzay().zzj();
                    String str5 = this.zza;
                    if (TextUtils.isEmpty(this.zzj)) {
                        str3 = this.zzk;
                    } else {
                        str3 = this.zzj;
                    }
                    zzejVarZzj.zzc("App measurement enabled for app package, google app id", str5, str3);
                }
                this.zzh = null;
                this.zzs.zzaw();
                listZzp = this.zzs.zzf().zzp("analytics.safelisted_events");
                if (listZzp != null) {
                    this.zzh = listZzp;
                } else if (listZzp.size() == 0) {
                    this.zzs.zzay().zzl().zza("Safelisted event list is empty. Ignoring");
                } else {
                    it = listZzp.iterator();
                    do {
                        if (it.hasNext()) {
                            this.zzh = listZzp;
                        }
                    } while (this.zzs.zzv().zzaa("safelisted event", it.next()));
                }
                if (packageManager != null) {
                    this.zzi = InstantApps.isInstantApp(this.zzs.zzau()) ? 1 : 0;
                } else {
                    this.zzi = 0;
                }
            }
            this.zzs.zzay().zzd().zzb("PackageManager is null, app identity information might be inaccurate. appId", zzel.zzn(packageName));
            strZzc = zzig.zzc(this.zzs.zzau(), FirebaseOptions.APP_ID_RESOURCE_NAME, this.zzs.zzz());
            if (true != TextUtils.isEmpty(strZzc)) {
                str2 = strZzc;
            } else {
                str2 = "";
            }
            this.zzj = str2;
            zzom.zzc();
            if (this.zzs.zzf().zzs(null, zzdy.zzac)) {
                contextZzau2 = this.zzs.zzau();
                strZzz2 = this.zzs.zzz();
                Preconditions.checkNotNull(contextZzau2);
                resources = contextZzau2.getResources();
                if (!TextUtils.isEmpty(strZzz2)) {
                    strZzz2 = zzfn.zza(contextZzau2);
                }
                String strZzb2 = zzfn.zzb("ga_app_id", resources, strZzz2);
                this.zzl = true != TextUtils.isEmpty(strZzb2) ? strZzb2 : "";
                if (TextUtils.isEmpty(strZzc)) {
                    this.zzk = zzfn.zzb("admob_app_id", resources, strZzz2);
                } else {
                    this.zzk = zzfn.zzb("admob_app_id", resources, strZzz2);
                }
            } else if (!TextUtils.isEmpty(strZzc)) {
                contextZzau = this.zzs.zzau();
                strZzz = this.zzs.zzz();
                Preconditions.checkNotNull(contextZzau);
                Resources resources3 = contextZzau.getResources();
                if (!TextUtils.isEmpty(strZzz)) {
                    strZzz = zzfn.zza(contextZzau);
                }
                this.zzk = zzfn.zzb("admob_app_id", resources3, strZzz);
            }
            if (iZza == 0) {
                zzej zzejVarZzj2 = this.zzs.zzay().zzj();
                String str6 = this.zza;
                if (TextUtils.isEmpty(this.zzj)) {
                    str3 = this.zzk;
                } else {
                    str3 = this.zzj;
                }
                zzejVarZzj2.zzc("App measurement enabled for app package, google app id", str6, str3);
            }
        } catch (IllegalStateException e2) {
            this.zzs.zzay().zzd().zzc("Fetching Google App Id failed with exception. appId", zzel.zzn(packageName), e2);
        }
        string = "Unknown";
        this.zza = packageName;
        this.zzd = installerPackageName;
        this.zzb = str4;
        this.zzc = i;
        this.zze = string;
        this.zzf = 0L;
        if (TextUtils.isEmpty(this.zzs.zzw())) {
            objArr = false;
        } else {
            objArr = false;
        }
        iZza = this.zzs.zza();
        switch (iZza) {
            case 0:
                a.b(this.zzs, "App measurement collection enabled");
                break;
            case 1:
                this.zzs.zzay().zzi().zza("App measurement deactivated via the manifest");
                break;
            case 2:
                a.b(this.zzs, "App measurement deactivated via the init parameters");
                break;
            case 3:
                this.zzs.zzay().zzi().zza("App measurement disabled by setAnalyticsCollectionEnabled(false)");
                break;
            case 4:
                this.zzs.zzay().zzi().zza("App measurement disabled via the manifest");
                break;
            case 5:
                a.b(this.zzs, "App measurement disabled via the init parameters");
                break;
            case 6:
                this.zzs.zzay().zzl().zza("App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics");
                break;
            case 7:
                this.zzs.zzay().zzi().zza("App measurement disabled via the global data collection setting");
                break;
            default:
                this.zzs.zzay().zzi().zza("App measurement disabled due to denied storage consent");
                break;
        }
        this.zzj = "";
        this.zzk = "";
        this.zzl = "";
        this.zzs.zzaw();
        if (objArr != false) {
            this.zzk = this.zzs.zzw();
        }
        this.zzh = null;
        this.zzs.zzaw();
        listZzp = this.zzs.zzf().zzp("analytics.safelisted_events");
        if (listZzp != null) {
            this.zzh = listZzp;
        } else if (listZzp.size() == 0) {
            this.zzs.zzay().zzl().zza("Safelisted event list is empty. Ignoring");
        } else {
            it = listZzp.iterator();
            do {
                if (it.hasNext()) {
                    this.zzh = listZzp;
                }
            } while (this.zzs.zzv().zzaa("safelisted event", it.next()));
        }
        if (packageManager != null) {
            this.zzi = InstantApps.isInstantApp(this.zzs.zzau()) ? 1 : 0;
        } else {
            this.zzi = 0;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final boolean zzf() {
        return true;
    }

    @WorkerThread
    public final int zzh() {
        zza();
        return this.zzi;
    }

    @WorkerThread
    public final int zzi() {
        zza();
        return this.zzc;
    }

    @WorkerThread
    public final zzp zzj(String str) {
        String str2;
        String str3;
        zzg();
        String strZzl = zzl();
        String strZzn = zzn();
        zza();
        String str4 = this.zzb;
        zza();
        long j = this.zzc;
        zza();
        Preconditions.checkNotNull(this.zzd);
        String str5 = this.zzd;
        this.zzs.zzf().zzh();
        zza();
        zzg();
        long j2 = this.zzf;
        long jZzp = 0;
        if (j2 == 0) {
            zzkz zzkzVarZzv = this.zzs.zzv();
            Context contextZzau = this.zzs.zzau();
            String packageName = this.zzs.zzau().getPackageName();
            zzkzVarZzv.zzg();
            Preconditions.checkNotNull(contextZzau);
            Preconditions.checkNotEmpty(packageName);
            PackageManager packageManager = contextZzau.getPackageManager();
            MessageDigest messageDigestZzE = zzkz.zzE();
            if (messageDigestZzE == null) {
                a.a(zzkzVarZzv.zzs, "Could not get MD5 instance");
            } else {
                if (packageManager != null) {
                    try {
                        if (!zzkzVarZzv.zzaf(contextZzau, packageName)) {
                            PackageInfo packageInfo = Wrappers.packageManager(contextZzau).getPackageInfo(zzkzVarZzv.zzs.zzau().getPackageName(), 64);
                            if (packageInfo.signatures != null) {
                                Signature[] signatureArr = packageInfo.signatures;
                                if (signatureArr.length > 0) {
                                    jZzp = zzkz.zzp(messageDigestZzE.digest(signatureArr[0].toByteArray()));
                                }
                            }
                            zzkzVarZzv.zzs.zzay().zzk().zza("Could not get signatures");
                        }
                    } catch (PackageManager.NameNotFoundException e2) {
                        a.a(zzkzVarZzv.zzs, "Package name not found", e2);
                    }
                }
                this.zzf = jZzp;
            }
            jZzp = -1;
            this.zzf = jZzp;
        } else {
            jZzp = j2;
        }
        boolean zZzJ = this.zzs.zzJ();
        boolean z = !this.zzs.zzm().zzk;
        zzg();
        if (this.zzs.zzJ()) {
            zzpt.zzc();
            if (this.zzs.zzf().zzs(null, zzdy.zzae)) {
                a.b(this.zzs, "Disabled IID for tests.");
            } else {
                try {
                    Class<?> clsLoadClass = this.zzs.zzau().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                    if (clsLoadClass != null) {
                        try {
                            Object objInvoke = clsLoadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, this.zzs.zzau());
                            if (objInvoke == null) {
                                str3 = null;
                            } else {
                                try {
                                    str3 = (String) clsLoadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(objInvoke, new Object[0]);
                                } catch (Exception unused) {
                                    this.zzs.zzay().zzl().zza("Failed to retrieve Firebase Instance Id");
                                    str3 = null;
                                }
                            }
                        } catch (Exception unused2) {
                            this.zzs.zzay().zzm().zza("Failed to obtain Firebase Analytics instance");
                        }
                        str2 = str3;
                    }
                } catch (ClassNotFoundException unused3) {
                }
            }
            str2 = null;
        } else {
            str2 = null;
        }
        zzfv zzfvVar = this.zzs;
        long jZza = zzfvVar.zzm().zzc.zza();
        long jMin = jZza == 0 ? zzfvVar.zzc : Math.min(zzfvVar.zzc, jZza);
        zza();
        int i = this.zzi;
        boolean zZzr = this.zzs.zzf().zzr();
        zzfa zzfaVarZzm = this.zzs.zzm();
        zzfaVarZzm.zzg();
        boolean z2 = zzfaVarZzm.zza().getBoolean("deferred_analytics_collection", false);
        zza();
        String str6 = this.zzk;
        Boolean boolZzk = this.zzs.zzf().zzk("google_analytics_default_allow_ad_personalization_signals");
        Boolean boolValueOf = boolZzk == null ? null : Boolean.valueOf(!boolZzk.booleanValue());
        long j3 = this.zzg;
        List<String> list = this.zzh;
        zzom.zzc();
        return new zzp(strZzl, strZzn, str4, j, str5, 46000L, jZzp, str, zZzJ, z, str2, 0L, jMin, i, zZzr, z2, str6, boolValueOf, j3, list, this.zzs.zzf().zzs(null, zzdy.zzac) ? zzm() : null, this.zzs.zzm().zzc().zzi());
    }

    @WorkerThread
    public final String zzk() {
        zza();
        return this.zzk;
    }

    @WorkerThread
    public final String zzl() {
        zza();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    @WorkerThread
    public final String zzm() {
        zza();
        Preconditions.checkNotNull(this.zzl);
        return this.zzl;
    }

    @WorkerThread
    public final String zzn() {
        zznx.zzc();
        if (this.zzs.zzf().zzs(null, zzdy.zzas)) {
            zzg();
        }
        zza();
        Preconditions.checkNotNull(this.zzj);
        return this.zzj;
    }

    @WorkerThread
    public final List<String> zzo() {
        return this.zzh;
    }
}
