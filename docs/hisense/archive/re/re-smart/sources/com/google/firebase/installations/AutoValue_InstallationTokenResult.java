package com.google.firebase.installations;

import androidx.annotation.NonNull;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class AutoValue_InstallationTokenResult extends InstallationTokenResult {
    public final String token;
    public final long tokenCreationTimestamp;
    public final long tokenExpirationTimestamp;

    static final class Builder extends InstallationTokenResult.Builder {
        public String token;
        public Long tokenCreationTimestamp;
        public Long tokenExpirationTimestamp;

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult build() {
            String strA = this.token == null ? a.a("", " token") : "";
            if (this.tokenExpirationTimestamp == null) {
                strA = a.a(strA, " tokenExpirationTimestamp");
            }
            if (this.tokenCreationTimestamp == null) {
                strA = a.a(strA, " tokenCreationTimestamp");
            }
            if (strA.isEmpty()) {
                return new AutoValue_InstallationTokenResult(this.token, this.tokenExpirationTimestamp.longValue(), this.tokenCreationTimestamp.longValue(), null);
            }
            throw new IllegalStateException(a.a("Missing required properties:", strA));
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult.Builder setToken(String str) {
            if (str == null) {
                throw new NullPointerException("Null token");
            }
            this.token = str;
            return this;
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult.Builder setTokenCreationTimestamp(long j) {
            this.tokenCreationTimestamp = Long.valueOf(j);
            return this;
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult.Builder setTokenExpirationTimestamp(long j) {
            this.tokenExpirationTimestamp = Long.valueOf(j);
            return this;
        }

        public Builder() {
        }

        public Builder(InstallationTokenResult installationTokenResult) {
            this.token = installationTokenResult.getToken();
            this.tokenExpirationTimestamp = Long.valueOf(installationTokenResult.getTokenExpirationTimestamp());
            this.tokenCreationTimestamp = Long.valueOf(installationTokenResult.getTokenCreationTimestamp());
        }
    }

    public AutoValue_InstallationTokenResult(String str, long j, long j2) {
        this.token = str;
        this.tokenExpirationTimestamp = j;
        this.tokenCreationTimestamp = j2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InstallationTokenResult)) {
            return false;
        }
        InstallationTokenResult installationTokenResult = (InstallationTokenResult) obj;
        return this.token.equals(installationTokenResult.getToken()) && this.tokenExpirationTimestamp == installationTokenResult.getTokenExpirationTimestamp() && this.tokenCreationTimestamp == installationTokenResult.getTokenCreationTimestamp();
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    @NonNull
    public String getToken() {
        return this.token;
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    @NonNull
    public long getTokenCreationTimestamp() {
        return this.tokenCreationTimestamp;
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    @NonNull
    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    public int hashCode() {
        int iHashCode = (this.token.hashCode() ^ 1000003) * 1000003;
        long j = this.tokenExpirationTimestamp;
        long j2 = this.tokenCreationTimestamp;
        return ((iHashCode ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)));
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    public InstallationTokenResult.Builder toBuilder() {
        return new Builder(this);
    }

    public String toString() {
        StringBuilder sbA = a.a("InstallationTokenResult{token=");
        sbA.append(this.token);
        sbA.append(", tokenExpirationTimestamp=");
        sbA.append(this.tokenExpirationTimestamp);
        sbA.append(", tokenCreationTimestamp=");
        sbA.append(this.tokenCreationTimestamp);
        sbA.append("}");
        return sbA.toString();
    }

    public /* synthetic */ AutoValue_InstallationTokenResult(String str, long j, long j2, AnonymousClass1 anonymousClass1) {
        this.token = str;
        this.tokenExpirationTimestamp = j;
        this.tokenCreationTimestamp = j2;
    }
}
