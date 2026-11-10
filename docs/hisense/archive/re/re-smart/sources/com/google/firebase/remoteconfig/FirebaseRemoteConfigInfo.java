package com.google.firebase.remoteconfig;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface FirebaseRemoteConfigInfo {
    @NonNull
    FirebaseRemoteConfigSettings getConfigSettings();

    long getFetchTimeMillis();

    int getLastFetchStatus();
}
