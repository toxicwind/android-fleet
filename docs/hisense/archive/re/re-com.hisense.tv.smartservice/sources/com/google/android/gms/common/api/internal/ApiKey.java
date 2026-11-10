package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.Objects;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class ApiKey<O extends Api.ApiOptions> {
    public final Api<O> mApi;
    public final O zabj;
    public final boolean zacx;
    public final int zacy;

    public ApiKey(Api<O> api, O o) {
        this.zacx = false;
        this.mApi = api;
        this.zabj = o;
        this.zacy = Arrays.hashCode(new Object[]{this.mApi, this.zabj});
    }

    public static <O extends Api.ApiOptions> ApiKey<O> getSharedApiKey(Api<O> api, O o) {
        return new ApiKey<>(api, o);
    }

    public static <O extends Api.ApiOptions> ApiKey<O> getUniqueApiKey(Api<O> api) {
        return new ApiKey<>(api);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApiKey)) {
            return false;
        }
        ApiKey apiKey = (ApiKey) obj;
        return !this.zacx && !apiKey.zacx && Objects.equal(this.mApi, apiKey.mApi) && Objects.equal(this.zabj, apiKey.zabj);
    }

    public final String getApiName() {
        return this.mApi.getName();
    }

    public final Api.AnyClientKey<?> getClientKey() {
        return this.mApi.getClientKey();
    }

    public final int hashCode() {
        return this.zacy;
    }

    public final boolean isUnique() {
        return this.zacx;
    }

    public ApiKey(Api<O> api) {
        this.zacx = true;
        this.mApi = api;
        this.zabj = null;
        this.zacy = System.identityHashCode(this);
    }
}
