package com.google.firebase.installations.remote;

import androidx.annotation.Nullable;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class AutoValue_InstallationResponse extends InstallationResponse {
    public final TokenResult authToken;
    public final String fid;
    public final String refreshToken;
    public final InstallationResponse.ResponseCode responseCode;
    public final String uri;

    static final class Builder extends InstallationResponse.Builder {
        public TokenResult authToken;
        public String fid;
        public String refreshToken;
        public InstallationResponse.ResponseCode responseCode;
        public String uri;

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse build() {
            return new AutoValue_InstallationResponse(this.uri, this.fid, this.refreshToken, this.authToken, this.responseCode, null);
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setAuthToken(TokenResult tokenResult) {
            this.authToken = tokenResult;
            return this;
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setFid(String str) {
            this.fid = str;
            return this;
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setRefreshToken(String str) {
            this.refreshToken = str;
            return this;
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setResponseCode(InstallationResponse.ResponseCode responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setUri(String str) {
            this.uri = str;
            return this;
        }

        public Builder() {
        }

        public Builder(InstallationResponse installationResponse) {
            this.uri = installationResponse.getUri();
            this.fid = installationResponse.getFid();
            this.refreshToken = installationResponse.getRefreshToken();
            this.authToken = installationResponse.getAuthToken();
            this.responseCode = installationResponse.getResponseCode();
        }
    }

    public AutoValue_InstallationResponse(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable TokenResult tokenResult, @Nullable InstallationResponse.ResponseCode responseCode) {
        this.uri = str;
        this.fid = str2;
        this.refreshToken = str3;
        this.authToken = tokenResult;
        this.responseCode = responseCode;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InstallationResponse)) {
            return false;
        }
        InstallationResponse installationResponse = (InstallationResponse) obj;
        String str = this.uri;
        if (str != null ? str.equals(installationResponse.getUri()) : installationResponse.getUri() == null) {
            String str2 = this.fid;
            if (str2 != null ? str2.equals(installationResponse.getFid()) : installationResponse.getFid() == null) {
                String str3 = this.refreshToken;
                if (str3 != null ? str3.equals(installationResponse.getRefreshToken()) : installationResponse.getRefreshToken() == null) {
                    TokenResult tokenResult = this.authToken;
                    if (tokenResult != null ? tokenResult.equals(installationResponse.getAuthToken()) : installationResponse.getAuthToken() == null) {
                        InstallationResponse.ResponseCode responseCode = this.responseCode;
                        if (responseCode == null) {
                            if (installationResponse.getResponseCode() == null) {
                                return true;
                            }
                        } else if (responseCode.equals(installationResponse.getResponseCode())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    @Nullable
    public TokenResult getAuthToken() {
        return this.authToken;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    @Nullable
    public String getFid() {
        return this.fid;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    @Nullable
    public String getRefreshToken() {
        return this.refreshToken;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    @Nullable
    public InstallationResponse.ResponseCode getResponseCode() {
        return this.responseCode;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    @Nullable
    public String getUri() {
        return this.uri;
    }

    public int hashCode() {
        String str = this.uri;
        int iHashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.fid;
        int iHashCode2 = (iHashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.refreshToken;
        int iHashCode3 = (iHashCode2 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        TokenResult tokenResult = this.authToken;
        int iHashCode4 = (iHashCode3 ^ (tokenResult == null ? 0 : tokenResult.hashCode())) * 1000003;
        InstallationResponse.ResponseCode responseCode = this.responseCode;
        return iHashCode4 ^ (responseCode != null ? responseCode.hashCode() : 0);
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    public InstallationResponse.Builder toBuilder() {
        return new Builder(this);
    }

    public String toString() {
        StringBuilder sbA = a.a("InstallationResponse{uri=");
        sbA.append(this.uri);
        sbA.append(", fid=");
        sbA.append(this.fid);
        sbA.append(", refreshToken=");
        sbA.append(this.refreshToken);
        sbA.append(", authToken=");
        sbA.append(this.authToken);
        sbA.append(", responseCode=");
        return a.a(sbA, this.responseCode, "}");
    }

    public /* synthetic */ AutoValue_InstallationResponse(String str, String str2, String str3, TokenResult tokenResult, InstallationResponse.ResponseCode responseCode, AnonymousClass1 anonymousClass1) {
        this.uri = str;
        this.fid = str2;
        this.refreshToken = str3;
        this.authToken = tokenResult;
        this.responseCode = responseCode;
    }
}
