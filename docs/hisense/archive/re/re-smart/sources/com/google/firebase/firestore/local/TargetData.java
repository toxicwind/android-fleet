package com.google.firebase.firestore.local;

import b.a.a.a.a;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.remote.WatchStream;
import com.google.protobuf.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class TargetData {
    public final SnapshotVersion lastLimboFreeSnapshotVersion;
    public final QueryPurpose purpose;
    public final ByteString resumeToken;
    public final long sequenceNumber;
    public final SnapshotVersion snapshotVersion;
    public final Target target;
    public final int targetId;

    public TargetData(Target target, int i, long j, QueryPurpose queryPurpose, SnapshotVersion snapshotVersion, SnapshotVersion snapshotVersion2, ByteString byteString) {
        if (target == null) {
            throw new NullPointerException();
        }
        this.target = target;
        this.targetId = i;
        this.sequenceNumber = j;
        this.lastLimboFreeSnapshotVersion = snapshotVersion2;
        this.purpose = queryPurpose;
        if (snapshotVersion == null) {
            throw new NullPointerException();
        }
        this.snapshotVersion = snapshotVersion;
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.resumeToken = byteString;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TargetData.class != obj.getClass()) {
            return false;
        }
        TargetData targetData = (TargetData) obj;
        return this.target.equals(targetData.target) && this.targetId == targetData.targetId && this.sequenceNumber == targetData.sequenceNumber && this.purpose.equals(targetData.purpose) && this.snapshotVersion.equals(targetData.snapshotVersion) && this.lastLimboFreeSnapshotVersion.equals(targetData.lastLimboFreeSnapshotVersion) && this.resumeToken.equals(targetData.resumeToken);
    }

    public SnapshotVersion getLastLimboFreeSnapshotVersion() {
        return this.lastLimboFreeSnapshotVersion;
    }

    public QueryPurpose getPurpose() {
        return this.purpose;
    }

    public ByteString getResumeToken() {
        return this.resumeToken;
    }

    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public SnapshotVersion getSnapshotVersion() {
        return this.snapshotVersion;
    }

    public Target getTarget() {
        return this.target;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public int hashCode() {
        return this.resumeToken.hashCode() + ((this.lastLimboFreeSnapshotVersion.hashCode() + ((this.snapshotVersion.hashCode() + ((this.purpose.hashCode() + (((((this.target.hashCode() * 31) + this.targetId) * 31) + ((int) this.sequenceNumber)) * 31)) * 31)) * 31)) * 31);
    }

    public String toString() {
        StringBuilder sbA = a.a("TargetData{target=");
        sbA.append(this.target);
        sbA.append(", targetId=");
        sbA.append(this.targetId);
        sbA.append(", sequenceNumber=");
        sbA.append(this.sequenceNumber);
        sbA.append(", purpose=");
        sbA.append(this.purpose);
        sbA.append(", snapshotVersion=");
        sbA.append(this.snapshotVersion);
        sbA.append(", lastLimboFreeSnapshotVersion=");
        sbA.append(this.lastLimboFreeSnapshotVersion);
        sbA.append(", resumeToken=");
        return a.a(sbA, (Object) this.resumeToken, '}');
    }

    public TargetData withLastLimboFreeSnapshotVersion(SnapshotVersion snapshotVersion) {
        return new TargetData(this.target, this.targetId, this.sequenceNumber, this.purpose, this.snapshotVersion, snapshotVersion, this.resumeToken);
    }

    public TargetData withResumeToken(ByteString byteString, SnapshotVersion snapshotVersion) {
        return new TargetData(this.target, this.targetId, this.sequenceNumber, this.purpose, snapshotVersion, this.lastLimboFreeSnapshotVersion, byteString);
    }

    public TargetData withSequenceNumber(long j) {
        return new TargetData(this.target, this.targetId, j, this.purpose, this.snapshotVersion, this.lastLimboFreeSnapshotVersion, this.resumeToken);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TargetData(Target target, int i, long j, QueryPurpose queryPurpose) {
        SnapshotVersion snapshotVersion = SnapshotVersion.NONE;
        this(target, i, j, queryPurpose, snapshotVersion, snapshotVersion, WatchStream.EMPTY_RESUME_TOKEN);
    }
}
