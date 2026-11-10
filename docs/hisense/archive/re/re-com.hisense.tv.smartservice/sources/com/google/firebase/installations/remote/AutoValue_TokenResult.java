package com.google.firebase.installations.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class AutoValue_TokenResult extends TokenResult {
    public final TokenResult.ResponseCode responseCode;
    public final String token;
    public final long tokenExpirationTimestamp;

    static final class Builder extends TokenResult.Builder {
        public TokenResult.ResponseCode responseCode;
        public String token;
        public Long tokenExpirationTimestamp;

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult build() {
            String strA = this.tokenExpirationTimestamp == null ? a.a("", " tokenExpirationTimestamp") : "";
            if (strA.isEmpty()) {
                return new AutoValue_TokenResult(this.token, this.tokenExpirationTimestamp.longValue(), this.responseCode, null);
            }
            throw new IllegalStateException(a.a("Missing required properties:", strA));
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setResponseCode(TokenResult.ResponseCode responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setToken(String str) {
            this.token = str;
            return this;
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setTokenExpirationTimestamp(long j) {
            this.tokenExpirationTimestamp = Long.valueOf(j);
            return this;
        }

        public Builder() {
        }

        public Builder(TokenResult tokenResult) {
            this.token = tokenResult.getToken();
            this.tokenExpirationTimestamp = Long.valueOf(tokenResult.getTokenExpirationTimestamp());
            this.responseCode = tokenResult.getResponseCode();
        }
    }

    public AutoValue_TokenResult(@Nullable String str, long j, @Nullable TokenResult.ResponseCode responseCode) {
        this.token = str;
        this.tokenExpirationTimestamp = j;
        this.responseCode = responseCode;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TokenResult)) {
            return false;
        }
        TokenResult tokenResult = (TokenResult) obj;
        String str = this.token;
        if (str != null ? str.equals(tokenResult.getToken()) : tokenResult.getToken() == null) {
            if (this.tokenExpirationTimestamp == tokenResult.getTokenExpirationTimestamp()) {
                TokenResult.ResponseCode responseCode = this.responseCode;
                if (responseCode == null) {
                    if (tokenResult.getResponseCode() == null) {
                        return true;
                    }
                } else if (responseCode.equals(tokenResult.getResponseCode())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    @Nullable
    public TokenResult.ResponseCode getResponseCode() {
        return this.responseCode;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    @Nullable
    public String getToken() {
        return this.token;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    @NonNull
    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    public int hashCode() {
        String str = this.token;
        int iHashCode = str == null ? 0 : str.hashCode();
        long j = this.tokenExpirationTimestamp;
        int i = (((iHashCode ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        TokenResult.ResponseCode responseCode = this.responseCode;
        return i ^ (responseCode != null ? responseCode.hashCode() : 0);
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    public TokenResult.Builder toBuilder() {
        return new Builder(this);
    }

    public String toString() {
        StringBuilder sbA = a.a("TokenResult{token=");
        sbA.append(this.token);
        sbA.append(", tokenExpirationTimestamp=");
        sbA.append(this.tokenExpirationTimestamp);
        sbA.append(", responseCode=");
        return a.a(sbA, this.responseCode, "}");
    }

    public /* synthetic */ AutoValue_TokenResult(String str, long j, TokenResult.ResponseCode responseCode, AnonymousClass1 anonymousClass1) {
        this.token = str;
        this.tokenExpirationTimestamp = j;
        this.responseCode = responseCode;
    }
}
