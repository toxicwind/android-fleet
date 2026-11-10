package com.google.firebase.heartbeatinfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class HeartBeatResult {
    public static HeartBeatResult create(String str, long j, HeartBeatInfo.HeartBeat heartBeat) {
        return new AutoValue_HeartBeatResult(str, j, heartBeat);
    }

    public abstract HeartBeatInfo.HeartBeat getHeartBeat();

    public abstract long getMillis();

    public abstract String getSdkName();
}
