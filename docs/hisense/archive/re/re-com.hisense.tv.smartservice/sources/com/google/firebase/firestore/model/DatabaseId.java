package com.google.firebase.firestore.model;

import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.firebase.firestore.util.Assert;

/* JADX INFO: loaded from: classes.dex */
public final class DatabaseId implements Comparable<DatabaseId> {
    public static final String DEFAULT_DATABASE_ID = "(default)";
    public static final DatabaseId EMPTY = new DatabaseId("", "");
    public final String databaseId;
    public final String projectId;

    public DatabaseId(String str, String str2) {
        this.projectId = str;
        this.databaseId = str2;
    }

    public static DatabaseId forDatabase(String str, String str2) {
        return new DatabaseId(str, str2);
    }

    public static DatabaseId forProject(String str) {
        return new DatabaseId(str, DEFAULT_DATABASE_ID);
    }

    public static DatabaseId fromName(String str) {
        ResourcePath resourcePathFromString = ResourcePath.fromString(str);
        Assert.hardAssert(resourcePathFromString.length() > 3 && resourcePathFromString.getSegment(0).equals("projects") && resourcePathFromString.getSegment(2).equals("databases"), "Tried to parse an invalid resource name: %s", resourcePathFromString);
        return new DatabaseId(resourcePathFromString.getSegment(1), resourcePathFromString.getSegment(3));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DatabaseId.class != obj.getClass()) {
            return false;
        }
        DatabaseId databaseId = (DatabaseId) obj;
        return this.projectId.equals(databaseId.projectId) && this.databaseId.equals(databaseId.databaseId);
    }

    public String getDatabaseId() {
        return this.databaseId;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public int hashCode() {
        return this.databaseId.hashCode() + (this.projectId.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sbA = a.a("DatabaseId(");
        sbA.append(this.projectId);
        sbA.append(", ");
        return a.a(sbA, this.databaseId, ")");
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull DatabaseId databaseId) {
        int iCompareTo = this.projectId.compareTo(databaseId.projectId);
        return iCompareTo != 0 ? iCompareTo : this.databaseId.compareTo(databaseId.databaseId);
    }
}
