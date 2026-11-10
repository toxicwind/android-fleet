package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class QuerySnapshot implements Iterable<QueryDocumentSnapshot> {
    public List<DocumentChange> cachedChanges;
    public MetadataChanges cachedChangesMetadataState;
    public final FirebaseFirestore firestore;
    public final SnapshotMetadata metadata;
    public final Query originalQuery;
    public final ViewSnapshot snapshot;

    private class QuerySnapshotIterator implements Iterator<QueryDocumentSnapshot> {
        public final Iterator<Document> it;

        public QuerySnapshotIterator(Iterator<Document> it) {
            this.it = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.it.hasNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("QuerySnapshot does not support remove().");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public QueryDocumentSnapshot next() {
            return QuerySnapshot.this.convertDocument(this.it.next());
        }
    }

    public QuerySnapshot(Query query, ViewSnapshot viewSnapshot, FirebaseFirestore firebaseFirestore) {
        if (query == null) {
            throw new NullPointerException();
        }
        this.originalQuery = query;
        if (viewSnapshot == null) {
            throw new NullPointerException();
        }
        this.snapshot = viewSnapshot;
        if (firebaseFirestore == null) {
            throw new NullPointerException();
        }
        this.firestore = firebaseFirestore;
        this.metadata = new SnapshotMetadata(viewSnapshot.hasPendingWrites(), viewSnapshot.isFromCache());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public QueryDocumentSnapshot convertDocument(Document document) {
        return QueryDocumentSnapshot.fromDocument(this.firestore, document, this.snapshot.isFromCache(), this.snapshot.getMutatedKeys().contains(document.getKey()));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QuerySnapshot)) {
            return false;
        }
        QuerySnapshot querySnapshot = (QuerySnapshot) obj;
        return this.firestore.equals(querySnapshot.firestore) && this.originalQuery.equals(querySnapshot.originalQuery) && this.snapshot.equals(querySnapshot.snapshot) && this.metadata.equals(querySnapshot.metadata);
    }

    @NonNull
    public List<DocumentChange> getDocumentChanges() {
        return getDocumentChanges(MetadataChanges.EXCLUDE);
    }

    @NonNull
    public List<DocumentSnapshot> getDocuments() {
        ArrayList arrayList = new ArrayList(this.snapshot.getDocuments().size());
        Iterator<Document> it = this.snapshot.getDocuments().iterator();
        while (it.hasNext()) {
            arrayList.add(convertDocument(it.next()));
        }
        return arrayList;
    }

    @NonNull
    public SnapshotMetadata getMetadata() {
        return this.metadata;
    }

    @NonNull
    public Query getQuery() {
        return this.originalQuery;
    }

    public int hashCode() {
        return this.metadata.hashCode() + ((this.snapshot.hashCode() + ((this.originalQuery.hashCode() + (this.firestore.hashCode() * 31)) * 31)) * 31);
    }

    public boolean isEmpty() {
        return this.snapshot.getDocuments().isEmpty();
    }

    @Override // java.lang.Iterable
    @NonNull
    public Iterator<QueryDocumentSnapshot> iterator() {
        return new QuerySnapshotIterator(this.snapshot.getDocuments().iterator());
    }

    public int size() {
        return this.snapshot.getDocuments().size();
    }

    @NonNull
    public <T> List<T> toObjects(@NonNull Class<T> cls) {
        return toObjects(cls, DocumentSnapshot.ServerTimestampBehavior.DEFAULT);
    }

    @NonNull
    public List<DocumentChange> getDocumentChanges(@NonNull MetadataChanges metadataChanges) {
        if (MetadataChanges.INCLUDE.equals(metadataChanges) && this.snapshot.excludesMetadataChanges()) {
            throw new IllegalArgumentException("To include metadata changes with your document changes, you must also pass MetadataChanges.INCLUDE to addSnapshotListener().");
        }
        if (this.cachedChanges == null || this.cachedChangesMetadataState != metadataChanges) {
            this.cachedChanges = Collections.unmodifiableList(DocumentChange.changesFromSnapshot(this.firestore, metadataChanges, this.snapshot));
            this.cachedChangesMetadataState = metadataChanges;
        }
        return this.cachedChanges;
    }

    @NonNull
    public <T> List<T> toObjects(@NonNull Class<T> cls, @NonNull DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(cls, "Provided POJO type must not be null.");
        ArrayList arrayList = new ArrayList();
        Iterator<QueryDocumentSnapshot> it = iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toObject(cls, serverTimestampBehavior));
        }
        return arrayList;
    }
}
