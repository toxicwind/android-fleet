package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;

/* JADX INFO: loaded from: classes.dex */
public final class zzhk implements Runnable {
    public final /* synthetic */ Bundle zza;
    public final /* synthetic */ zzia zzb;

    public zzhk(zzia zziaVar, Bundle bundle) {
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
        Preconditions.checkNotEmpty(string);
        if (!zziaVar.zzs.zzJ()) {
            a.b(zziaVar.zzs, "Conditional property not cleared since app measurement is disabled");
            return;
        }
        try {
            zziaVar.zzs.zzt().zzE(new zzab(bundle.getString("app_id"), "", new zzkv(string, 0L, null, ""), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), bundle.getBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zziaVar.zzs.zzv().zzz(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), "", bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, true)));
        } catch (IllegalArgumentException unused) {
        }
    }
}
