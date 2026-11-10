package com.google.firebase.firestore.model;

import b.a.a.a.a;
import com.google.firebase.Timestamp;

/* JADX INFO: loaded from: classes.dex */
public final class SnapshotVersion implements Comparable<SnapshotVersion> {
    public static final SnapshotVersion NONE = new SnapshotVersion(new Timestamp(0, 0));
    public final Timestamp timestamp;

    public SnapshotVersion(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SnapshotVersion) && compareTo((SnapshotVersion) obj) == 0;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return getTimestamp().hashCode();
    }

    public String toString() {
        StringBuilder sbA = a.a("SnapshotVersion(seconds=");
        sbA.append(this.timestamp.getSeconds());
        sbA.append(", nanos=");
        sbA.append(this.timestamp.getNanoseconds());
        sbA.append(")");
        return sbA.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(SnapshotVersion snapshotVersion) {
        return this.timestamp.compareTo(snapshotVersion.timestamp);
    }
}
