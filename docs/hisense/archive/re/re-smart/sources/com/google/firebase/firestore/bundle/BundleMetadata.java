package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.SnapshotVersion;

/* JADX INFO: loaded from: classes.dex */
public class BundleMetadata implements BundleElement {
    public final String bundleId;
    public final SnapshotVersion createTime;
    public final int schemaVersion;
    public final long totalBytes;
    public final int totalDocuments;

    public BundleMetadata(String str, int i, SnapshotVersion snapshotVersion, int i2, long j) {
        this.bundleId = str;
        this.schemaVersion = i;
        this.createTime = snapshotVersion;
        this.totalDocuments = i2;
        this.totalBytes = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BundleMetadata.class != obj.getClass()) {
            return false;
        }
        BundleMetadata bundleMetadata = (BundleMetadata) obj;
        if (this.schemaVersion == bundleMetadata.schemaVersion && this.totalDocuments == bundleMetadata.totalDocuments && this.totalBytes == bundleMetadata.totalBytes && this.bundleId.equals(bundleMetadata.bundleId)) {
            return this.createTime.equals(bundleMetadata.createTime);
        }
        return false;
    }

    public String getBundleId() {
        return this.bundleId;
    }

    public SnapshotVersion getCreateTime() {
        return this.createTime;
    }

    public int getSchemaVersion() {
        return this.schemaVersion;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public int getTotalDocuments() {
        return this.totalDocuments;
    }

    public int hashCode() {
        int iHashCode = ((((this.bundleId.hashCode() * 31) + this.schemaVersion) * 31) + this.totalDocuments) * 31;
        long j = this.totalBytes;
        return this.createTime.hashCode() + ((iHashCode + ((int) (j ^ (j >>> 32)))) * 31);
    }
}
