package com.google.firebase.firestore.core;

import b.a.a.a.a;
import com.google.firebase.firestore.model.DatabaseId;

/* JADX INFO: loaded from: classes.dex */
public final class DatabaseInfo {
    public final DatabaseId databaseId;
    public final String host;
    public final String persistenceKey;
    public final boolean sslEnabled;

    public DatabaseInfo(DatabaseId databaseId, String str, String str2, boolean z) {
        this.databaseId = databaseId;
        this.persistenceKey = str;
        this.host = str2;
        this.sslEnabled = z;
    }

    public DatabaseId getDatabaseId() {
        return this.databaseId;
    }

    public String getHost() {
        return this.host;
    }

    public String getPersistenceKey() {
        return this.persistenceKey;
    }

    public boolean isSslEnabled() {
        return this.sslEnabled;
    }

    public String toString() {
        StringBuilder sbA = a.a("DatabaseInfo(databaseId:");
        sbA.append(this.databaseId);
        sbA.append(" host:");
        return a.a(sbA, this.host, ")");
    }
}
