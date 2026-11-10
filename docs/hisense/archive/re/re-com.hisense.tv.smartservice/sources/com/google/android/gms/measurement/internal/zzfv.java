package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zznu;
import com.google.android.gms.internal.measurement.zznx;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import e.b.b;
import e.b.d;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class zzfv implements zzgq {
    public static volatile zzfv zzd;
    public zzec zzA;
    public Boolean zzC;
    public long zzD;
    public volatile Boolean zzE;
    public volatile boolean zzF;
    public int zzG;

    @VisibleForTesting
    public Boolean zza;

    @VisibleForTesting
    public Boolean zzb;

    @VisibleForTesting
    public final long zzc;
    public final Context zze;
    public final String zzf;
    public final String zzg;
    public final String zzh;
    public final boolean zzi;
    public final zzaa zzj;
    public final zzaf zzk;
    public final zzfa zzl;
    public final zzel zzm;
    public final zzfs zzn;
    public final zzkd zzo;
    public final zzkz zzp;
    public final zzeg zzq;
    public final Clock zzr;
    public final zzio zzs;
    public final zzia zzt;
    public final zzd zzu;
    public final zzie zzv;
    public final String zzw;
    public zzee zzx;
    public zzjo zzy;
    public zzan zzz;
    public boolean zzB = false;
    public final AtomicInteger zzH = new AtomicInteger(0);

    public zzfv(zzgy zzgyVar) {
        Bundle bundle;
        Preconditions.checkNotNull(zzgyVar);
        this.zzj = new zzaa(zzgyVar.zza);
        zzdv.zza = this.zzj;
        this.zze = zzgyVar.zza;
        this.zzf = zzgyVar.zzb;
        this.zzg = zzgyVar.zzc;
        this.zzh = zzgyVar.zzd;
        this.zzi = zzgyVar.zzh;
        this.zzE = zzgyVar.zze;
        this.zzw = zzgyVar.zzj;
        this.zzF = true;
        com.google.android.gms.internal.measurement.zzcl zzclVar = zzgyVar.zzg;
        if (zzclVar != null && (bundle = zzclVar.zzg) != null) {
            Object obj = bundle.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zza = (Boolean) obj;
            }
            Object obj2 = zzclVar.zzg.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzb = (Boolean) obj2;
            }
        }
        com.google.android.gms.internal.measurement.zzhu.zzd(this.zze);
        this.zzr = DefaultClock.zza;
        Long l = zzgyVar.zzi;
        this.zzc = l != null ? l.longValue() : this.zzr.currentTimeMillis();
        this.zzk = new zzaf(this);
        zzfa zzfaVar = new zzfa(this);
        zzfaVar.zzv();
        this.zzl = zzfaVar;
        zzel zzelVar = new zzel(this);
        zzelVar.zzv();
        this.zzm = zzelVar;
        zzkz zzkzVar = new zzkz(this);
        zzkzVar.zzv();
        this.zzp = zzkzVar;
        this.zzq = new zzeg(new zzgx(zzgyVar, this));
        this.zzu = new zzd(this);
        zzio zzioVar = new zzio(this);
        zzioVar.zzb();
        this.zzs = zzioVar;
        zzia zziaVar = new zzia(this);
        zziaVar.zzb();
        this.zzt = zziaVar;
        zzkd zzkdVar = new zzkd(this);
        zzkdVar.zzb();
        this.zzo = zzkdVar;
        zzie zzieVar = new zzie(this);
        zzieVar.zzv();
        this.zzv = zzieVar;
        zzfs zzfsVar = new zzfs(this);
        zzfsVar.zzv();
        this.zzn = zzfsVar;
        com.google.android.gms.internal.measurement.zzcl zzclVar2 = zzgyVar.zzg;
        boolean z = zzclVar2 == null || zzclVar2.zzb == 0;
        if (this.zze.getApplicationContext() instanceof Application) {
            zzia zziaVarZzq = zzq();
            if (zziaVarZzq.zzs.zze.getApplicationContext() instanceof Application) {
                Application application = (Application) zziaVarZzq.zzs.zze.getApplicationContext();
                if (zziaVarZzq.zza == null) {
                    zziaVarZzq.zza = new zzhz(zziaVarZzq, null);
                }
                if (z) {
                    application.unregisterActivityLifecycleCallbacks(zziaVarZzq.zza);
                    application.registerActivityLifecycleCallbacks(zziaVarZzq.zza);
                    a.b(zziaVarZzq.zzs, "Registered activity lifecycle callback");
                }
            }
        } else {
            a.c(this, "Application context is not an Application");
        }
        this.zzn.zzp(new zzfu(this, zzgyVar));
    }

    public static /* bridge */ /* synthetic */ void zzA(zzfv zzfvVar, zzgy zzgyVar) {
        zzfvVar.zzaz().zzg();
        zzfvVar.zzk.zzn();
        zzan zzanVar = new zzan(zzfvVar);
        zzanVar.zzv();
        zzfvVar.zzz = zzanVar;
        zzec zzecVar = new zzec(zzfvVar, zzgyVar.zzf);
        zzecVar.zzb();
        zzfvVar.zzA = zzecVar;
        zzee zzeeVar = new zzee(zzfvVar);
        zzeeVar.zzb();
        zzfvVar.zzx = zzeeVar;
        zzjo zzjoVar = new zzjo(zzfvVar);
        zzjoVar.zzb();
        zzfvVar.zzy = zzjoVar;
        zzfvVar.zzp.zzw();
        zzfvVar.zzl.zzw();
        zzfvVar.zzA.zzc();
        zzej zzejVarZzi = zzfvVar.zzay().zzi();
        zzfvVar.zzk.zzh();
        zzejVarZzi.zzb("App measurement initialized, version", 46000L);
        zzfvVar.zzay().zzi().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String strZzl = zzecVar.zzl();
        if (TextUtils.isEmpty(zzfvVar.zzf)) {
            if (zzfvVar.zzv().zzad(strZzl)) {
                zzfvVar.zzay().zzi().zza("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
            } else {
                zzej zzejVarZzi2 = zzfvVar.zzay().zzi();
                String strValueOf = String.valueOf(strZzl);
                zzejVarZzi2.zza(strValueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(strValueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app "));
            }
        }
        zzfvVar.zzay().zzc().zza("Debug-level message logging enabled");
        if (zzfvVar.zzG != zzfvVar.zzH.get()) {
            zzfvVar.zzay().zzd().zzc("Not all components initialized", Integer.valueOf(zzfvVar.zzG), Integer.valueOf(zzfvVar.zzH.get()));
        }
        zzfvVar.zzB = true;
    }

    public static final void zzO() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    public static final void zzP(zzgo zzgoVar) {
        if (zzgoVar == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    public static final void zzQ(zzf zzfVar) {
        if (zzfVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (zzfVar.zze()) {
            return;
        }
        String strValueOf = String.valueOf(zzfVar.getClass());
        strValueOf.length();
        throw new IllegalStateException("Component not initialized: ".concat(strValueOf));
    }

    public static final void zzR(zzgp zzgpVar) {
        if (zzgpVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (zzgpVar.zzx()) {
            return;
        }
        String strValueOf = String.valueOf(zzgpVar.getClass());
        strValueOf.length();
        throw new IllegalStateException("Component not initialized: ".concat(strValueOf));
    }

    public static zzfv zzp(Context context, com.google.android.gms.internal.measurement.zzcl zzclVar, Long l) {
        Bundle bundle;
        if (zzclVar != null && (zzclVar.zze == null || zzclVar.zzf == null)) {
            zzclVar = new com.google.android.gms.internal.measurement.zzcl(zzclVar.zza, zzclVar.zzb, zzclVar.zzc, zzclVar.zzd, null, null, zzclVar.zzg, null);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzd == null) {
            synchronized (zzfv.class) {
                if (zzd == null) {
                    zzd = new zzfv(new zzgy(context, zzclVar, l));
                }
            }
        } else if (zzclVar != null && (bundle = zzclVar.zzg) != null && bundle.containsKey("dataCollectionDefaultEnabled")) {
            Preconditions.checkNotNull(zzd);
            zzd.zzE = Boolean.valueOf(zzclVar.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        Preconditions.checkNotNull(zzd);
        return zzd;
    }

    public final void zzB() {
        this.zzH.incrementAndGet();
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0017  */
    public final /* synthetic */ void zzC(String str, int i, Throwable th, byte[] bArr, Map map) {
        List<ResolveInfo> listQueryIntentActivities;
        if (i == 200 || i == 204) {
            if (th == null) {
                zzm().zzm.zza(true);
                if (bArr != null || bArr.length == 0) {
                    zzay().zzc().zza("Deferred Deep Link response empty.");
                    return;
                }
                try {
                    d dVar = new d(new String(bArr));
                    String strA = dVar.a("deeplink", "");
                    String strA2 = dVar.a("gclid", "");
                    double dA = dVar.a(NotificationCompat.CarExtender.KEY_TIMESTAMP, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    if (TextUtils.isEmpty(strA)) {
                        zzay().zzc().zza("Deferred Deep Link is empty.");
                        return;
                    }
                    zzkz zzkzVarZzv = zzv();
                    zzfv zzfvVar = zzkzVarZzv.zzs;
                    if (!TextUtils.isEmpty(strA) && (listQueryIntentActivities = zzkzVarZzv.zzs.zze.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(strA)), 0)) != null && !listQueryIntentActivities.isEmpty()) {
                        Bundle bundle = new Bundle();
                        bundle.putString("gclid", strA2);
                        bundle.putString("_cis", "ddp");
                        this.zzt.zzG("auto", "_cmp", bundle);
                        zzkz zzkzVarZzv2 = zzv();
                        if (TextUtils.isEmpty(strA)) {
                            return;
                        }
                        try {
                            SharedPreferences.Editor editorEdit = zzkzVarZzv2.zzs.zze.getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
                            editorEdit.putString("deeplink", strA);
                            editorEdit.putLong(NotificationCompat.CarExtender.KEY_TIMESTAMP, Double.doubleToRawLongBits(dA));
                            if (editorEdit.commit()) {
                                zzkzVarZzv2.zzs.zze.sendBroadcast(new Intent("android.google.analytics.action.DEEPLINK_ACTION"));
                                return;
                            }
                            return;
                        } catch (RuntimeException e2) {
                            zzkzVarZzv2.zzs.zzay().zzd().zzb("Failed to persist Deferred Deep Link. exception", e2);
                            return;
                        }
                    }
                    zzay().zzk().zzc("Deferred Deep Link validation failed. gclid, deep link", strA2, strA);
                    return;
                } catch (b e3) {
                    a.a(this, "Failed to parse the Deferred Deep Link response. exception", e3);
                    return;
                }
            }
        } else if (i == 304) {
            i = 304;
            if (th == null) {
                zzm().zzm.zza(true);
                if (bArr != null) {
                }
                zzay().zzc().zza("Deferred Deep Link response empty.");
                return;
            }
        }
        zzay().zzk().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
    }

    public final void zzD() {
        this.zzG++;
    }

    @WorkerThread
    public final void zzE() {
        zzaz().zzg();
        zzR(zzr());
        String strZzl = zzh().zzl();
        Pair<String, Boolean> pairZzb = zzm().zzb(strZzl);
        if (!this.zzk.zzr() || ((Boolean) pairZzb.second).booleanValue() || TextUtils.isEmpty((CharSequence) pairZzb.first)) {
            zzay().zzc().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
            return;
        }
        zzie zzieVarZzr = zzr();
        zzieVarZzr.zzu();
        ConnectivityManager connectivityManager = (ConnectivityManager) zzieVarZzr.zzs.zze.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            try {
                activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            } catch (SecurityException unused) {
            }
        }
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            a.c(this, "Network is not available for Deferred Deep Link request. Skipping");
            return;
        }
        zzkz zzkzVarZzv = zzv();
        zzh().zzs.zzk.zzh();
        URL urlZzD = zzkzVarZzv.zzD(46000L, strZzl, (String) pairZzb.first, zzm().zzn.zza() - 1);
        if (urlZzD != null) {
            zzie zzieVarZzr2 = zzr();
            zzft zzftVar = new zzft(this);
            zzieVarZzr2.zzg();
            zzieVarZzr2.zzu();
            Preconditions.checkNotNull(urlZzD);
            Preconditions.checkNotNull(zzftVar);
            zzieVarZzr2.zzs.zzaz().zzo(new zzid(zzieVarZzr2, strZzl, urlZzD, null, null, zzftVar, null));
        }
    }

    @WorkerThread
    public final void zzF(boolean z) {
        this.zzE = Boolean.valueOf(z);
    }

    @WorkerThread
    public final void zzG(boolean z) {
        zzaz().zzg();
        this.zzF = z;
    }

    @WorkerThread
    public final void zzH(com.google.android.gms.internal.measurement.zzcl zzclVar) {
        zzag zzagVar;
        zzaz().zzg();
        zzag zzagVarZzc = zzm().zzc();
        zzfa zzfaVarZzm = zzm();
        zzfv zzfvVar = zzfaVarZzm.zzs;
        zzfaVarZzm.zzg();
        int i = 100;
        int i2 = zzfaVarZzm.zza().getInt("consent_source", 100);
        zzaf zzafVar = this.zzk;
        zzfv zzfvVar2 = zzafVar.zzs;
        Boolean boolZzk = zzafVar.zzk("google_analytics_default_allow_ad_storage");
        zzaf zzafVar2 = this.zzk;
        zzfv zzfvVar3 = zzafVar2.zzs;
        Boolean boolZzk2 = zzafVar2.zzk("google_analytics_default_allow_analytics_storage");
        if (!(boolZzk == null && boolZzk2 == null) && zzm().zzl(-10)) {
            zzagVar = new zzag(boolZzk, boolZzk2);
            i = -10;
        } else {
            if (TextUtils.isEmpty(zzh().zzn()) || !(i2 == 0 || i2 == 30 || i2 == 10 || i2 == 30 || i2 == 30 || i2 == 40)) {
                zznx.zzc();
                if ((!this.zzk.zzs(null, zzdy.zzar) || TextUtils.isEmpty(zzh().zzn())) && zzclVar != null && zzclVar.zzg != null && zzm().zzl(30)) {
                    zzagVar = zzag.zza(zzclVar.zzg);
                    if (!zzagVar.equals(zzag.zza)) {
                        i = 30;
                    }
                }
            } else {
                zzq().zzS(zzag.zza, -10, this.zzc);
            }
            zzagVar = null;
        }
        if (zzagVar != null) {
            zzq().zzS(zzagVar, i, this.zzc);
        } else {
            zzagVar = zzagVarZzc;
        }
        zzq().zzW(zzagVar);
        if (zzm().zzc.zza() == 0) {
            zzay().zzj().zzb("Persisting first open", Long.valueOf(this.zzc));
            zzm().zzc.zzb(this.zzc);
        }
        zzq().zzb.zzc();
        if (zzM()) {
            if (!TextUtils.isEmpty(zzh().zzn()) || !TextUtils.isEmpty(zzh().zzk())) {
                zzkz zzkzVarZzv = zzv();
                String strZzn = zzh().zzn();
                zzfa zzfaVarZzm2 = zzm();
                zzfaVarZzm2.zzg();
                String string = zzfaVarZzm2.zza().getString("gmp_app_id", null);
                String strZzk = zzh().zzk();
                zzfa zzfaVarZzm3 = zzm();
                zzfaVarZzm3.zzg();
                if (zzkzVarZzv.zzam(strZzn, string, strZzk, zzfaVarZzm3.zza().getString("admob_app_id", null))) {
                    zzay().zzi().zza("Rechecking which service to use due to a GMP App Id change");
                    zzfa zzfaVarZzm4 = zzm();
                    zzfaVarZzm4.zzg();
                    Boolean boolZzd = zzfaVarZzm4.zzd();
                    SharedPreferences.Editor editorEdit = zzfaVarZzm4.zza().edit();
                    editorEdit.clear();
                    editorEdit.apply();
                    if (boolZzd != null) {
                        zzfaVarZzm4.zzh(boolZzd);
                    }
                    zzi().zzj();
                    this.zzy.zzs();
                    this.zzy.zzr();
                    zzm().zzc.zzb(this.zzc);
                    zzm().zze.zzb(null);
                }
                zzfa zzfaVarZzm5 = zzm();
                String strZzn2 = zzh().zzn();
                zzfaVarZzm5.zzg();
                SharedPreferences.Editor editorEdit2 = zzfaVarZzm5.zza().edit();
                editorEdit2.putString("gmp_app_id", strZzn2);
                editorEdit2.apply();
                zzfa zzfaVarZzm6 = zzm();
                String strZzk2 = zzh().zzk();
                zzfaVarZzm6.zzg();
                SharedPreferences.Editor editorEdit3 = zzfaVarZzm6.zza().edit();
                editorEdit3.putString("admob_app_id", strZzk2);
                editorEdit3.apply();
            }
            if (!zzm().zzc().zzk()) {
                zzm().zze.zzb(null);
            }
            zzq().zzO(zzm().zze.zza());
            zznu.zzc();
            if (this.zzk.zzs(null, zzdy.zzah)) {
                try {
                    zzv().zzs.zze.getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
                } catch (ClassNotFoundException unused) {
                    if (!TextUtils.isEmpty(zzm().zzo.zza())) {
                        zzay().zzk().zza("Remote config removed with active feature rollouts");
                        zzm().zzo.zzb(null);
                    }
                }
            }
            if (!TextUtils.isEmpty(zzh().zzn()) || !TextUtils.isEmpty(zzh().zzk())) {
                boolean zZzJ = zzJ();
                if (!zzm().zzj() && !this.zzk.zzv()) {
                    zzm().zzi(!zZzJ);
                }
                if (zZzJ) {
                    zzq().zzy();
                }
                zzu().zza.zza();
                zzt().zzu(new AtomicReference<>());
                zzt().zzH(zzm().zzr.zza());
            }
        } else if (zzJ()) {
            if (!zzv().zzac("android.permission.INTERNET")) {
                a.a(this, "App is missing INTERNET permission");
            }
            if (!zzv().zzac("android.permission.ACCESS_NETWORK_STATE")) {
                a.a(this, "App is missing ACCESS_NETWORK_STATE permission");
            }
            if (!Wrappers.packageManager(this.zze).isCallerInstantApp() && !this.zzk.zzx()) {
                if (!zzkz.zzai(this.zze)) {
                    a.a(this, "AppMeasurementReceiver not registered/enabled");
                }
                if (!zzkz.zzaj(this.zze, false)) {
                    a.a(this, "AppMeasurementService not registered/enabled");
                }
            }
            a.a(this, "Uploading is not possible. App measurement disabled");
        }
        zzm().zzi.zza(true);
    }

    @WorkerThread
    public final boolean zzI() {
        return this.zzE != null && this.zzE.booleanValue();
    }

    @WorkerThread
    public final boolean zzJ() {
        return zza() == 0;
    }

    @WorkerThread
    public final boolean zzK() {
        zzaz().zzg();
        return this.zzF;
    }

    public final boolean zzL() {
        return TextUtils.isEmpty(this.zzf);
    }

    @WorkerThread
    public final boolean zzM() {
        if (!this.zzB) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
        zzaz().zzg();
        Boolean bool = this.zzC;
        if (bool == null || this.zzD == 0 || (!bool.booleanValue() && Math.abs(this.zzr.elapsedRealtime() - this.zzD) > 1000)) {
            this.zzD = this.zzr.elapsedRealtime();
            boolean z = true;
            this.zzC = Boolean.valueOf(zzv().zzac("android.permission.INTERNET") && zzv().zzac("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zze).isCallerInstantApp() || this.zzk.zzx() || (zzkz.zzai(this.zze) && zzkz.zzaj(this.zze, false))));
            if (this.zzC.booleanValue()) {
                if (!zzv().zzW(zzh().zzn(), zzh().zzk(), zzh().zzm()) && TextUtils.isEmpty(zzh().zzk())) {
                    z = false;
                }
                this.zzC = Boolean.valueOf(z);
            }
        }
        return this.zzC.booleanValue();
    }

    public final boolean zzN() {
        return this.zzi;
    }

    @WorkerThread
    public final int zza() {
        zzaz().zzg();
        if (this.zzk.zzv()) {
            return 1;
        }
        Boolean bool = this.zzb;
        if (bool != null && bool.booleanValue()) {
            return 2;
        }
        zzaz().zzg();
        if (!this.zzF) {
            return 8;
        }
        Boolean boolZzd = zzm().zzd();
        if (boolZzd != null) {
            return boolZzd.booleanValue() ? 0 : 3;
        }
        zzaf zzafVar = this.zzk;
        zzaa zzaaVar = zzafVar.zzs.zzj;
        Boolean boolZzk = zzafVar.zzk("firebase_analytics_collection_enabled");
        if (boolZzk != null) {
            return boolZzk.booleanValue() ? 0 : 4;
        }
        Boolean bool2 = this.zza;
        if (bool2 != null) {
            return bool2.booleanValue() ? 0 : 5;
        }
        return (!this.zzk.zzs(null, zzdy.zzS) || this.zzE == null || this.zzE.booleanValue()) ? 0 : 7;
    }

    @Override // com.google.android.gms.measurement.internal.zzgq
    public final Context zzau() {
        return this.zze;
    }

    @Override // com.google.android.gms.measurement.internal.zzgq
    public final Clock zzav() {
        return this.zzr;
    }

    @Override // com.google.android.gms.measurement.internal.zzgq
    public final zzaa zzaw() {
        return this.zzj;
    }

    @Override // com.google.android.gms.measurement.internal.zzgq
    public final zzel zzay() {
        zzR(this.zzm);
        return this.zzm;
    }

    @Override // com.google.android.gms.measurement.internal.zzgq
    public final zzfs zzaz() {
        zzR(this.zzn);
        return this.zzn;
    }

    public final zzd zzd() {
        zzd zzdVar = this.zzu;
        if (zzdVar != null) {
            return zzdVar;
        }
        throw new IllegalStateException("Component not created");
    }

    public final zzaf zzf() {
        return this.zzk;
    }

    public final zzan zzg() {
        zzR(this.zzz);
        return this.zzz;
    }

    public final zzec zzh() {
        zzQ(this.zzA);
        return this.zzA;
    }

    public final zzee zzi() {
        zzQ(this.zzx);
        return this.zzx;
    }

    public final zzeg zzj() {
        return this.zzq;
    }

    public final zzel zzl() {
        zzel zzelVar = this.zzm;
        if (zzelVar == null || !zzelVar.zzx()) {
            return null;
        }
        return this.zzm;
    }

    public final zzfa zzm() {
        zzP(this.zzl);
        return this.zzl;
    }

    public final zzfs zzo() {
        return this.zzn;
    }

    public final zzia zzq() {
        zzQ(this.zzt);
        return this.zzt;
    }

    public final zzie zzr() {
        zzR(this.zzv);
        return this.zzv;
    }

    public final zzio zzs() {
        zzQ(this.zzs);
        return this.zzs;
    }

    public final zzjo zzt() {
        zzQ(this.zzy);
        return this.zzy;
    }

    public final zzkd zzu() {
        zzQ(this.zzo);
        return this.zzo;
    }

    public final zzkz zzv() {
        zzP(this.zzp);
        return this.zzp;
    }

    public final String zzw() {
        return this.zzf;
    }

    public final String zzx() {
        return this.zzg;
    }

    public final String zzy() {
        return this.zzh;
    }

    public final String zzz() {
        return this.zzw;
    }
}
