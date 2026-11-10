package com.google.firebase.remoteconfig;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseRemoteConfigServerException extends FirebaseRemoteConfigException {
    public final int httpStatusCode;

    public FirebaseRemoteConfigServerException(int i, @NonNull String str) {
        super(str);
        this.httpStatusCode = i;
    }

    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }

    public FirebaseRemoteConfigServerException(int i, @NonNull String str, @Nullable Throwable th) {
        super(str, th);
        this.httpStatusCode = i;
    }
}
