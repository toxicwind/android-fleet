package com.google.android.gms.common.api;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import b.a.a.a.a;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AvailabilityException extends Exception {
    public final ArrayMap<ApiKey<?>, ConnectionResult> zaba;

    public AvailabilityException(ArrayMap<ApiKey<?>, ConnectionResult> arrayMap) {
        this.zaba = arrayMap;
    }

    public ConnectionResult getConnectionResult(GoogleApi<? extends Api.ApiOptions> googleApi) {
        Object apiKey = googleApi.getApiKey();
        Preconditions.checkArgument(this.zaba.get(apiKey) != null, "The given API was not part of the availability request.");
        return this.zaba.get(apiKey);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (ApiKey<?> apiKey : this.zaba.keySet()) {
            ConnectionResult connectionResult = this.zaba.get(apiKey);
            if (connectionResult.isSuccess()) {
                z = false;
            }
            String apiName = apiKey.getApiName();
            String strValueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(strValueOf.length() + a.a((Object) apiName, 2));
            sb.append(apiName);
            sb.append(": ");
            sb.append(strValueOf);
            arrayList.add(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        if (z) {
            sb2.append("None of the queried APIs are available. ");
        } else {
            sb2.append("Some of the queried APIs are unavailable. ");
        }
        sb2.append(TextUtils.join("; ", arrayList));
        return sb2.toString();
    }

    public final ArrayMap<ApiKey<?>, ConnectionResult> zaj() {
        return this.zaba;
    }

    public ConnectionResult getConnectionResult(HasApiKey<? extends Api.ApiOptions> hasApiKey) {
        Object apiKey = hasApiKey.getApiKey();
        Preconditions.checkArgument(this.zaba.get(apiKey) != null, "The given API was not part of the availability request.");
        return this.zaba.get(apiKey);
    }
}
