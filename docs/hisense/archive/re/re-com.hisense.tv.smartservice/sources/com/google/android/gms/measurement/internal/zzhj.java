package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;

/* JADX INFO: loaded from: classes.dex */
public final class zzhj implements Runnable {
    public final /* synthetic */ Bundle zza;
    public final /* synthetic */ zzia zzb;

    public zzhj(zzia zziaVar, Bundle bundle) {
        this.zzb = zziaVar;
        this.zza = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzia zziaVar = this.zzb;
        Bundle bundle = this.zza;
        zziaVar.zzg();
        zziaVar.zza();
        Preconditions.checkNotNull(bundle);
        String string = bundle.getString("name");
        String string2 = bundle.getString("origin");
        Preconditions.checkNotEmpty(string);
        Preconditions.checkNotEmpty(string2);
        Preconditions.checkNotNull(bundle.get("value"));
        if (!zziaVar.zzs.zzJ()) {
            a.b(zziaVar.zzs, "Conditional property not set since app measurement is disabled");
            return;
        }
        zzkv zzkvVar = new zzkv(string, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP), bundle.get("value"), string2);
        try {
            zzat zzatVarZzz = zziaVar.zzs.zzv().zzz(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS), string2, 0L, true, true);
            zziaVar.zzs.zzt().zzE(new zzab(bundle.getString("app_id"), string2, zzkvVar, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), false, bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), zziaVar.zzs.zzv().zzz(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS), string2, 0L, true, true), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), zzatVarZzz, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zziaVar.zzs.zzv().zzz(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), string2, 0L, true, true)));
        } catch (IllegalArgumentException unused) {
        }
    }
}
