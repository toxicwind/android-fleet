package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: classes.dex */
public final class UnsupportedApiCallException extends UnsupportedOperationException {
    public final Feature zza;

    @KeepForSdk
    public UnsupportedApiCallException(@NonNull Feature feature) {
        this.zza = feature;
    }

    @Override // java.lang.Throwable
    @NonNull
    public String getMessage() {
        String strValueOf = String.valueOf(this.zza);
        strValueOf.length();
        return "Missing ".concat(strValueOf);
    }
}
