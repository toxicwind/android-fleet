package com.google.firebase.remoteconfig;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseRemoteConfigFetchThrottledException extends FirebaseRemoteConfigException {
    public final long throttleEndTimeMillis;

    public FirebaseRemoteConfigFetchThrottledException(long j) {
        super("Fetch was throttled.");
        this.throttleEndTimeMillis = j;
    }

    public long getThrottleEndTimeMillis() {
        return this.throttleEndTimeMillis;
    }

    public FirebaseRemoteConfigFetchThrottledException(String str, long j) {
        super(str);
        this.throttleEndTimeMillis = j;
    }
}
