package com.google.firebase.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.firebase.installations.local.IidStore;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class InternalTokenResult {
    public String zza;

    @KeepForSdk
    public InternalTokenResult(@Nullable String str) {
        this.zza = str;
    }

    public boolean equals(Object obj) {
        if (obj instanceof InternalTokenResult) {
            return Objects.equal(this.zza, ((InternalTokenResult) obj).zza);
        }
        return false;
    }

    @Nullable
    @KeepForSdk
    public String getToken() {
        return this.zza;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza});
    }

    public String toString() {
        return Objects.toStringHelper(this).add(IidStore.JSON_TOKEN_KEY, this.zza).toString();
    }
}
