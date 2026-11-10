package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.installations.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public final class zzjo extends zzf {
    public final zzjn zza;
    public zzeb zzb;
    public volatile Boolean zzc;
    public final zzam zzd;
    public final zzke zze;
    public final List<Runnable> zzf;
    public final zzam zzg;

    public zzjo(zzfv zzfvVar) {
        super(zzfvVar);
        this.zzf = new ArrayList();
        this.zze = new zzke(zzfvVar.zzav());
        this.zza = new zzjn(this);
        this.zzd = new zziy(this, zzfvVar);
        this.zzg = new zzja(this, zzfvVar);
    }

    @WorkerThread
    private final zzp zzO(boolean z) {
        Pair<String, Long> pairZza;
        this.zzs.zzaw();
        zzec zzecVarZzh = this.zzs.zzh();
        String strA = null;
        if (z) {
            zzel zzelVarZzay = this.zzs.zzay();
            if (zzelVarZzay.zzs.zzm().zzb != null && (pairZza = zzelVarZzay.zzs.zzm().zzb.zza()) != null && pairZza != zzfa.zza) {
                String strValueOf = String.valueOf(pairZza.second);
                String str = (String) pairZza.first;
                strA = a.a(new StringBuilder(strValueOf.length() + 1 + String.valueOf(str).length()), strValueOf, Utils.APP_ID_IDENTIFICATION_SUBSTRING, str);
            }
        }
        return zzecVarZzh.zzj(strA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzP() {
        zzg();
        this.zzs.zzay().zzj().zzb("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        Iterator<Runnable> it = this.zzf.iterator();
        while (it.hasNext()) {
            try {
                it.next().run();
            } catch (RuntimeException e2) {
                a.a(this.zzs, "Task exception while flushing queue", e2);
            }
        }
        this.zzf.clear();
        this.zzg.zzb();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzQ() {
        zzg();
        this.zze.zzb();
        zzam zzamVar = this.zzd;
        this.zzs.zzf();
        zzamVar.zzd(zzdy.zzI.zza(null).longValue());
    }

    @WorkerThread
    private final void zzR(Runnable runnable) {
        zzg();
        if (zzL()) {
            runnable.run();
            return;
        }
        int size = this.zzf.size();
        this.zzs.zzf();
        if (size >= 1000) {
            a.a(this.zzs, "Discarding data. Max runnable queue size reached");
            return;
        }
        this.zzf.add(runnable);
        this.zzg.zzd(60000L);
        zzr();
    }

    private final boolean zzS() {
        this.zzs.zzaw();
        return true;
    }

    public static /* bridge */ /* synthetic */ void zzo(zzjo zzjoVar, ComponentName componentName) {
        zzjoVar.zzg();
        if (zzjoVar.zzb != null) {
            zzjoVar.zzb = null;
            zzjoVar.zzs.zzay().zzj().zzb("Disconnected from device MeasurementService", componentName);
            zzjoVar.zzg();
            zzjoVar.zzr();
        }
    }

    @WorkerThread
    public final void zzA(zzat zzatVar, String str) {
        Preconditions.checkNotNull(zzatVar);
        zzg();
        zza();
        zzS();
        zzR(new zzjd(this, true, zzO(true), this.zzs.zzi().zzo(zzatVar), zzatVar, str));
    }

    @WorkerThread
    public final void zzB(com.google.android.gms.internal.measurement.zzcf zzcfVar, zzat zzatVar, String str) {
        zzg();
        zza();
        if (this.zzs.zzv().zzo(12451000) == 0) {
            zzR(new zziz(this, zzatVar, str, zzcfVar));
        } else {
            a.c(this.zzs, "Not bundling data. Service unavailable or out of date");
            this.zzs.zzv().zzR(zzcfVar, new byte[0]);
        }
    }

    @WorkerThread
    public final void zzC() {
        zzg();
        zza();
        zzp zzpVarZzO = zzO(false);
        zzS();
        this.zzs.zzi().zzj();
        zzR(new zzis(this, zzpVarZzO));
    }

    @VisibleForTesting
    @WorkerThread
    public final void zzD(zzeb zzebVar, AbstractSafeParcelable abstractSafeParcelable, zzp zzpVar) {
        int size;
        zzg();
        zza();
        zzS();
        this.zzs.zzf();
        int i = 0;
        int i2 = 100;
        while (i < 1001 && i2 == 100) {
            ArrayList arrayList = new ArrayList();
            List<AbstractSafeParcelable> listZzi = this.zzs.zzi().zzi(100);
            if (listZzi != null) {
                arrayList.addAll(listZzi);
                size = listZzi.size();
            } else {
                size = 0;
            }
            if (abstractSafeParcelable != null && size < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            int size2 = arrayList.size();
            for (int i3 = 0; i3 < size2; i3++) {
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) arrayList.get(i3);
                if (abstractSafeParcelable2 instanceof zzat) {
                    try {
                        zzebVar.zzk((zzat) abstractSafeParcelable2, zzpVar);
                    } catch (RemoteException e2) {
                        a.a(this.zzs, "Failed to send event to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzkv) {
                    try {
                        zzebVar.zzt((zzkv) abstractSafeParcelable2, zzpVar);
                    } catch (RemoteException e3) {
                        a.a(this.zzs, "Failed to send user property to the service", e3);
                    }
                } else if (abstractSafeParcelable2 instanceof zzab) {
                    try {
                        zzebVar.zzn((zzab) abstractSafeParcelable2, zzpVar);
                    } catch (RemoteException e4) {
                        a.a(this.zzs, "Failed to send conditional user property to the service", e4);
                    }
                } else {
                    a.a(this.zzs, "Discarding data. Unrecognized parcel type.");
                }
            }
            i++;
            i2 = size;
        }
    }

    @WorkerThread
    public final void zzE(zzab zzabVar) {
        Preconditions.checkNotNull(zzabVar);
        zzg();
        zza();
        this.zzs.zzaw();
        zzR(new zzje(this, true, zzO(true), this.zzs.zzi().zzn(zzabVar), new zzab(zzabVar), zzabVar));
    }

    @WorkerThread
    public final void zzF(boolean z) {
        zzg();
        zza();
        if (z) {
            zzS();
            this.zzs.zzi().zzj();
        }
        if (zzM()) {
            zzR(new zzjc(this, zzO(false)));
        }
    }

    @WorkerThread
    public final void zzG(zzih zzihVar) {
        zzg();
        zza();
        zzR(new zziw(this, zzihVar));
    }

    @WorkerThread
    public final void zzH(Bundle bundle) {
        zzg();
        zza();
        zzR(new zzix(this, zzO(false), bundle));
    }

    @WorkerThread
    public final void zzI() {
        zzg();
        zza();
        zzR(new zzjb(this, zzO(true)));
    }

    @VisibleForTesting
    @WorkerThread
    public final void zzJ(zzeb zzebVar) {
        zzg();
        Preconditions.checkNotNull(zzebVar);
        this.zzb = zzebVar;
        zzQ();
        zzP();
    }

    @WorkerThread
    public final void zzK(zzkv zzkvVar) {
        zzg();
        zza();
        zzS();
        zzR(new zziq(this, zzO(true), this.zzs.zzi().zzp(zzkvVar), zzkvVar));
    }

    @WorkerThread
    public final boolean zzL() {
        zzg();
        zza();
        return this.zzb != null;
    }

    @WorkerThread
    public final boolean zzM() {
        zzg();
        zza();
        return !zzN() || this.zzs.zzv().zzm() >= zzdy.zzan.zza(null).intValue();
    }

    @WorkerThread
    public final boolean zzN() {
        zzg();
        zza();
        if (this.zzc == null) {
            zzg();
            zza();
            zzfa zzfaVarZzm = this.zzs.zzm();
            zzfaVarZzm.zzg();
            boolean z = false;
            Boolean boolValueOf = !zzfaVarZzm.zza().contains("use_service") ? null : Boolean.valueOf(zzfaVarZzm.zza().getBoolean("use_service", false));
            boolean z2 = true;
            if (boolValueOf == null || !boolValueOf.booleanValue()) {
                this.zzs.zzaw();
                if (this.zzs.zzh().zzh() == 1) {
                    z = true;
                } else {
                    a.b(this.zzs, "Checking service availability");
                    int iZzo = this.zzs.zzv().zzo(12451000);
                    if (iZzo != 0) {
                        if (iZzo != 1) {
                            if (iZzo == 2) {
                                this.zzs.zzay().zzc().zza("Service container out of date");
                                if (this.zzs.zzv().zzm() >= 17443) {
                                    if (boolValueOf != null) {
                                    }
                                }
                            } else if (iZzo == 3) {
                                a.c(this.zzs, "Service disabled");
                            } else if (iZzo == 9) {
                                a.c(this.zzs, "Service invalid");
                            } else if (iZzo != 18) {
                                this.zzs.zzay().zzk().zzb("Unexpected service status", Integer.valueOf(iZzo));
                            } else {
                                a.c(this.zzs, "Service updating");
                            }
                            z2 = false;
                        } else {
                            a.b(this.zzs, "Service missing");
                        }
                        z = true;
                        z2 = false;
                    } else {
                        a.b(this.zzs, "Service available");
                    }
                    z = true;
                }
                if (!z2 && this.zzs.zzf().zzx()) {
                    a.a(this.zzs, "No way to upload. Consider using the full version of Analytics");
                } else if (z) {
                    zzfa zzfaVarZzm2 = this.zzs.zzm();
                    zzfaVarZzm2.zzg();
                    SharedPreferences.Editor editorEdit = zzfaVarZzm2.zza().edit();
                    editorEdit.putBoolean("use_service", z2);
                    editorEdit.apply();
                }
            }
            this.zzc = Boolean.valueOf(z2);
        }
        return this.zzc.booleanValue();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final boolean zzf() {
        return false;
    }

    public final Boolean zzj() {
        return this.zzc;
    }

    @WorkerThread
    public final void zzq() {
        zzg();
        zza();
        zzp zzpVarZzO = zzO(true);
        this.zzs.zzi().zzk();
        zzR(new zziv(this, zzpVarZzO));
    }

    @WorkerThread
    public final void zzr() {
        zzg();
        zza();
        if (zzL()) {
            return;
        }
        if (zzN()) {
            this.zza.zzc();
            return;
        }
        if (this.zzs.zzf().zzx()) {
            return;
        }
        this.zzs.zzaw();
        List<ResolveInfo> listQueryIntentServices = this.zzs.zzau().getPackageManager().queryIntentServices(new Intent().setClassName(this.zzs.zzau(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
        if (listQueryIntentServices == null || listQueryIntentServices.size() <= 0) {
            a.a(this.zzs, "Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            return;
        }
        Intent intent = new Intent("com.google.android.gms.measurement.START");
        Context contextZzau = this.zzs.zzau();
        this.zzs.zzaw();
        intent.setComponent(new ComponentName(contextZzau, "com.google.android.gms.measurement.AppMeasurementService"));
        this.zza.zzb(intent);
    }

    @WorkerThread
    public final void zzs() {
        zzg();
        zza();
        this.zza.zzd();
        try {
            ConnectionTracker.getInstance().unbindService(this.zzs.zzau(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzb = null;
    }

    @WorkerThread
    public final void zzt(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzg();
        zza();
        zzR(new zziu(this, zzO(false), zzcfVar));
    }

    @WorkerThread
    public final void zzu(AtomicReference<String> atomicReference) {
        zzg();
        zza();
        zzR(new zzit(this, atomicReference, zzO(false)));
    }

    @WorkerThread
    public final void zzv(com.google.android.gms.internal.measurement.zzcf zzcfVar, String str, String str2) {
        zzg();
        zza();
        zzR(new zzjg(this, str, str2, zzO(false), zzcfVar));
    }

    @WorkerThread
    public final void zzw(AtomicReference<List<zzab>> atomicReference, String str, String str2, String str3) {
        zzg();
        zza();
        zzR(new zzjf(this, atomicReference, null, str2, str3, zzO(false)));
    }

    @WorkerThread
    public final void zzx(AtomicReference<List<zzkv>> atomicReference, boolean z) {
        zzg();
        zza();
        zzR(new zzir(this, atomicReference, zzO(false), z));
    }

    @WorkerThread
    public final void zzy(com.google.android.gms.internal.measurement.zzcf zzcfVar, String str, String str2, boolean z) {
        zzg();
        zza();
        zzR(new zzip(this, str, str2, zzO(false), z, zzcfVar));
    }

    @WorkerThread
    public final void zzz(AtomicReference<List<zzkv>> atomicReference, String str, String str2, String str3, boolean z) {
        zzg();
        zza();
        zzR(new zzjh(this, atomicReference, null, str2, str3, zzO(false), z));
    }
}
