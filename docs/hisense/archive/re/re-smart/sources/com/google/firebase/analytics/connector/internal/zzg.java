package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class zzg implements zza {
    public final AnalyticsConnector.AnalyticsConnectorListener zza;
    public final AppMeasurementSdk zzb;
    public final zzf zzc = new zzf(this);

    public zzg(AppMeasurementSdk appMeasurementSdk, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        this.zza = analyticsConnectorListener;
        this.zzb = appMeasurementSdk;
        this.zzb.registerOnMeasurementEventListener(this.zzc);
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final AnalyticsConnector.AnalyticsConnectorListener zza() {
        return this.zza;
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final void zzb(Set<String> set) {
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final void zzc() {
    }
}
