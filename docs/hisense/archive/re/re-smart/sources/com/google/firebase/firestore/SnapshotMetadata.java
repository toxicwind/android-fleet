package com.google.firebase.firestore;

import androidx.annotation.Nullable;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class SnapshotMetadata {
    public final boolean hasPendingWrites;
    public final boolean isFromCache;

    public SnapshotMetadata(boolean z, boolean z2) {
        this.hasPendingWrites = z;
        this.isFromCache = z2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SnapshotMetadata)) {
            return false;
        }
        SnapshotMetadata snapshotMetadata = (SnapshotMetadata) obj;
        return this.hasPendingWrites == snapshotMetadata.hasPendingWrites && this.isFromCache == snapshotMetadata.isFromCache;
    }

    public boolean hasPendingWrites() {
        return this.hasPendingWrites;
    }

    public int hashCode() {
        return ((this.hasPendingWrites ? 1 : 0) * 31) + (this.isFromCache ? 1 : 0);
    }

    public boolean isFromCache() {
        return this.isFromCache;
    }

    public String toString() {
        StringBuilder sbA = a.a("SnapshotMetadata{hasPendingWrites=");
        sbA.append(this.hasPendingWrites);
        sbA.append(", isFromCache=");
        sbA.append(this.isFromCache);
        sbA.append('}');
        return sbA.toString();
    }
}
