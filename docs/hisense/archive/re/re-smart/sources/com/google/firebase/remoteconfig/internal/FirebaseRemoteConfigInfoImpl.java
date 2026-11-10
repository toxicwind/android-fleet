package com.google.firebase.remoteconfig.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseRemoteConfigInfoImpl implements FirebaseRemoteConfigInfo {
    public final FirebaseRemoteConfigSettings configSettings;
    public final int lastFetchStatus;
    public final long lastSuccessfulFetchTimeInMillis;

    public static class Builder {
        public FirebaseRemoteConfigSettings builderConfigSettings;
        public int builderLastFetchStatus;
        public long builderLastSuccessfulFetchTimeInMillis;

        public Builder() {
        }

        public FirebaseRemoteConfigInfoImpl build() {
            return new FirebaseRemoteConfigInfoImpl(this.builderLastSuccessfulFetchTimeInMillis, this.builderLastFetchStatus, this.builderConfigSettings, null);
        }

        public Builder withConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
            this.builderConfigSettings = firebaseRemoteConfigSettings;
            return this;
        }

        public Builder withLastFetchStatus(int i) {
            this.builderLastFetchStatus = i;
            return this;
        }

        public Builder withLastSuccessfulFetchTimeInMillis(long j) {
            this.builderLastSuccessfulFetchTimeInMillis = j;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
        }
    }

    public FirebaseRemoteConfigInfoImpl(long j, int i, FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.lastSuccessfulFetchTimeInMillis = j;
        this.lastFetchStatus = i;
        this.configSettings = firebaseRemoteConfigSettings;
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
    public FirebaseRemoteConfigSettings getConfigSettings() {
        return this.configSettings;
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
    public long getFetchTimeMillis() {
        return this.lastSuccessfulFetchTimeInMillis;
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
    public int getLastFetchStatus() {
        return this.lastFetchStatus;
    }

    public /* synthetic */ FirebaseRemoteConfigInfoImpl(long j, int i, FirebaseRemoteConfigSettings firebaseRemoteConfigSettings, AnonymousClass1 anonymousClass1) {
        this.lastSuccessfulFetchTimeInMillis = j;
        this.lastFetchStatus = i;
        this.configSettings = firebaseRemoteConfigSettings;
    }
}
