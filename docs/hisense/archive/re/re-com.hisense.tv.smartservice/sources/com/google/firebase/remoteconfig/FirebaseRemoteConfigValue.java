package com.google.firebase.remoteconfig;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface FirebaseRemoteConfigValue {
    boolean asBoolean();

    @NonNull
    byte[] asByteArray();

    double asDouble();

    long asLong();

    @NonNull
    String asString();

    int getSource();
}
