package com.google.firebase.firestore.core;

import b.a.a.a.a;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.DocumentSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ViewSnapshot {
    public final List<DocumentViewChange> changes;
    public final boolean didSyncStateChange;
    public final DocumentSet documents;
    public boolean excludesMetadataChanges;
    public final boolean isFromCache;
    public final ImmutableSortedSet<DocumentKey> mutatedKeys;
    public final DocumentSet oldDocuments;
    public final Query query;

    public enum SyncState {
        NONE,
        LOCAL,
        SYNCED
    }

    public ViewSnapshot(Query query, DocumentSet documentSet, DocumentSet documentSet2, List<DocumentViewChange> list, boolean z, ImmutableSortedSet<DocumentKey> immutableSortedSet, boolean z2, boolean z3) {
        this.query = query;
        this.documents = documentSet;
        this.oldDocuments = documentSet2;
        this.changes = list;
        this.isFromCache = z;
        this.mutatedKeys = immutableSortedSet;
        this.didSyncStateChange = z2;
        this.excludesMetadataChanges = z3;
    }

    public static ViewSnapshot fromInitialDocuments(Query query, DocumentSet documentSet, ImmutableSortedSet<DocumentKey> immutableSortedSet, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        Iterator<Document> it = documentSet.iterator();
        while (it.hasNext()) {
            arrayList.add(new DocumentViewChange(DocumentViewChange.Type.ADDED, it.next()));
        }
        return new ViewSnapshot(query, documentSet, DocumentSet.emptySet(query.comparator()), arrayList, z, immutableSortedSet, true, z2);
    }

    public boolean didSyncStateChange() {
        return this.didSyncStateChange;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ViewSnapshot)) {
            return false;
        }
        ViewSnapshot viewSnapshot = (ViewSnapshot) obj;
        if (this.isFromCache == viewSnapshot.isFromCache && this.didSyncStateChange == viewSnapshot.didSyncStateChange && this.excludesMetadataChanges == viewSnapshot.excludesMetadataChanges && this.query.equals(viewSnapshot.query) && this.mutatedKeys.equals(viewSnapshot.mutatedKeys) && this.documents.equals(viewSnapshot.documents) && this.oldDocuments.equals(viewSnapshot.oldDocuments)) {
            return this.changes.equals(viewSnapshot.changes);
        }
        return false;
    }

    public boolean excludesMetadataChanges() {
        return this.excludesMetadataChanges;
    }

    public List<DocumentViewChange> getChanges() {
        return this.changes;
    }

    public DocumentSet getDocuments() {
        return this.documents;
    }

    public ImmutableSortedSet<DocumentKey> getMutatedKeys() {
        return this.mutatedKeys;
    }

    public DocumentSet getOldDocuments() {
        return this.oldDocuments;
    }

    public Query getQuery() {
        return this.query;
    }

    public boolean hasPendingWrites() {
        return !this.mutatedKeys.isEmpty();
    }

    public int hashCode() {
        return ((((((this.mutatedKeys.hashCode() + ((this.changes.hashCode() + ((this.oldDocuments.hashCode() + ((this.documents.hashCode() + (this.query.hashCode() * 31)) * 31)) * 31)) * 31)) * 31) + (this.isFromCache ? 1 : 0)) * 31) + (this.didSyncStateChange ? 1 : 0)) * 31) + (this.excludesMetadataChanges ? 1 : 0);
    }

    public boolean isFromCache() {
        return this.isFromCache;
    }

    public String toString() {
        StringBuilder sbA = a.a("ViewSnapshot(");
        sbA.append(this.query);
        sbA.append(", ");
        sbA.append(this.documents);
        sbA.append(", ");
        sbA.append(this.oldDocuments);
        sbA.append(", ");
        sbA.append(this.changes);
        sbA.append(", isFromCache=");
        sbA.append(this.isFromCache);
        sbA.append(", mutatedKeys=");
        sbA.append(this.mutatedKeys.size());
        sbA.append(", didSyncStateChange=");
        sbA.append(this.didSyncStateChange);
        sbA.append(", excludesMetadataChanges=");
        sbA.append(this.excludesMetadataChanges);
        sbA.append(")");
        return sbA.toString();
    }
}
