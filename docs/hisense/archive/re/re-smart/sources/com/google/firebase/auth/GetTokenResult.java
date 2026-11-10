package com.google.firebase.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class GetTokenResult {
    public String zza;
    public Map<String, Object> zzb;

    @KeepForSdk
    public GetTokenResult(@Nullable String str, @NonNull Map<String, Object> map) {
        this.zza = str;
        this.zzb = map;
    }

    private final long zza(String str) {
        Integer num = (Integer) this.zzb.get(str);
        if (num == null) {
            return 0L;
        }
        return num.longValue();
    }

    public long getAuthTimestamp() {
        return zza("auth_time");
    }

    @NonNull
    public Map<String, Object> getClaims() {
        return this.zzb;
    }

    public long getExpirationTimestamp() {
        return zza("exp");
    }

    public long getIssuedAtTimestamp() {
        return zza("iat");
    }

    @Nullable
    public String getSignInProvider() {
        Map map = (Map) this.zzb.get(RemoteConfigComponent.DEFAULT_NAMESPACE);
        if (map != null) {
            return (String) map.get("sign_in_provider");
        }
        return null;
    }

    @Nullable
    @KeepForSdk
    public String getSignInSecondFactor() {
        Map map = (Map) this.zzb.get(RemoteConfigComponent.DEFAULT_NAMESPACE);
        if (map != null) {
            return (String) map.get("sign_in_second_factor");
        }
        return null;
    }

    @Nullable
    public String getToken() {
        return this.zza;
    }
}
