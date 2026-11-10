package com.google.firebase.heartbeatinfo;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class AutoValue_SdkHeartBeatResult extends SdkHeartBeatResult {
    public final long millis;
    public final String sdkName;

    public AutoValue_SdkHeartBeatResult(String str, long j) {
        if (str == null) {
            throw new NullPointerException("Null sdkName");
        }
        this.sdkName = str;
        this.millis = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SdkHeartBeatResult)) {
            return false;
        }
        SdkHeartBeatResult sdkHeartBeatResult = (SdkHeartBeatResult) obj;
        return this.sdkName.equals(sdkHeartBeatResult.getSdkName()) && this.millis == sdkHeartBeatResult.getMillis();
    }

    @Override // com.google.firebase.heartbeatinfo.SdkHeartBeatResult
    public long getMillis() {
        return this.millis;
    }

    @Override // com.google.firebase.heartbeatinfo.SdkHeartBeatResult
    public String getSdkName() {
        return this.sdkName;
    }

    public int hashCode() {
        int iHashCode = (this.sdkName.hashCode() ^ 1000003) * 1000003;
        long j = this.millis;
        return iHashCode ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder sbA = a.a("SdkHeartBeatResult{sdkName=");
        sbA.append(this.sdkName);
        sbA.append(", millis=");
        sbA.append(this.millis);
        sbA.append("}");
        return sbA.toString();
    }
}
