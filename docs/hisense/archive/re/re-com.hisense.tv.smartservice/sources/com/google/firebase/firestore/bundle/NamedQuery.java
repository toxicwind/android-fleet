package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.SnapshotVersion;

/* JADX INFO: loaded from: classes.dex */
public class NamedQuery implements BundleElement {
    public final BundledQuery bundledQuery;
    public final String name;
    public final SnapshotVersion readTime;

    public NamedQuery(String str, BundledQuery bundledQuery, SnapshotVersion snapshotVersion) {
        this.name = str;
        this.bundledQuery = bundledQuery;
        this.readTime = snapshotVersion;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NamedQuery.class != obj.getClass()) {
            return false;
        }
        NamedQuery namedQuery = (NamedQuery) obj;
        if (this.name.equals(namedQuery.name) && this.bundledQuery.equals(namedQuery.bundledQuery)) {
            return this.readTime.equals(namedQuery.readTime);
        }
        return false;
    }

    public BundledQuery getBundledQuery() {
        return this.bundledQuery;
    }

    public String getName() {
        return this.name;
    }

    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    public int hashCode() {
        return this.readTime.hashCode() + ((this.bundledQuery.hashCode() + (this.name.hashCode() * 31)) * 31);
    }
}
