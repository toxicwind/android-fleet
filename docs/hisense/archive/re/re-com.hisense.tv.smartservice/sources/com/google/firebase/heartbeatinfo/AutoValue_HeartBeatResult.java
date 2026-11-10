package com.google.firebase.heartbeatinfo;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class AutoValue_HeartBeatResult extends HeartBeatResult {
    public final HeartBeatInfo.HeartBeat heartBeat;
    public final long millis;
    public final String sdkName;

    public AutoValue_HeartBeatResult(String str, long j, HeartBeatInfo.HeartBeat heartBeat) {
        if (str == null) {
            throw new NullPointerException("Null sdkName");
        }
        this.sdkName = str;
        this.millis = j;
        if (heartBeat == null) {
            throw new NullPointerException("Null heartBeat");
        }
        this.heartBeat = heartBeat;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HeartBeatResult)) {
            return false;
        }
        HeartBeatResult heartBeatResult = (HeartBeatResult) obj;
        return this.sdkName.equals(heartBeatResult.getSdkName()) && this.millis == heartBeatResult.getMillis() && this.heartBeat.equals(heartBeatResult.getHeartBeat());
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatResult
    public HeartBeatInfo.HeartBeat getHeartBeat() {
        return this.heartBeat;
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatResult
    public long getMillis() {
        return this.millis;
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatResult
    public String getSdkName() {
        return this.sdkName;
    }

    public int hashCode() {
        int iHashCode = (this.sdkName.hashCode() ^ 1000003) * 1000003;
        long j = this.millis;
        return ((iHashCode ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.heartBeat.hashCode();
    }

    public String toString() {
        StringBuilder sbA = a.a("HeartBeatResult{sdkName=");
        sbA.append(this.sdkName);
        sbA.append(", millis=");
        sbA.append(this.millis);
        sbA.append(", heartBeat=");
        return a.a(sbA, this.heartBeat, "}");
    }
}
