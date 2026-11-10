package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.SparseIntArray;
import androidx.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;

/* JADX INFO: loaded from: classes.dex */
public class GoogleApiAvailabilityCache {
    public final SparseIntArray zapd;
    public GoogleApiAvailabilityLight zape;

    public GoogleApiAvailabilityCache() {
        this(GoogleApiAvailability.zaao);
    }

    public void flush() {
        this.zapd.clear();
    }

    public int getClientAvailability(@NonNull Context context, @NonNull Api.Client client) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(client);
        if (!client.requiresGooglePlayServices()) {
            return 0;
        }
        int minApkVersion = client.getMinApkVersion();
        int iIsGooglePlayServicesAvailable = this.zapd.get(minApkVersion, -1);
        if (iIsGooglePlayServicesAvailable != -1) {
            return iIsGooglePlayServicesAvailable;
        }
        for (int i = 0; i < this.zapd.size(); i++) {
            int iKeyAt = this.zapd.keyAt(i);
            if (iKeyAt > minApkVersion && this.zapd.get(iKeyAt) == 0) {
                iIsGooglePlayServicesAvailable = 0;
                break;
            }
        }
        if (iIsGooglePlayServicesAvailable == -1) {
            iIsGooglePlayServicesAvailable = this.zape.isGooglePlayServicesAvailable(context, minApkVersion);
        }
        this.zapd.put(minApkVersion, iIsGooglePlayServicesAvailable);
        return iIsGooglePlayServicesAvailable;
    }

    public GoogleApiAvailabilityCache(@NonNull GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        this.zapd = new SparseIntArray();
        Preconditions.checkNotNull(googleApiAvailabilityLight);
        this.zape = googleApiAvailabilityLight;
    }
}
