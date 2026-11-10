package com.google.firebase.firestore.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DocumentSet implements Iterable<Document> {
    public final ImmutableSortedMap<DocumentKey, Document> keyIndex;
    public final ImmutableSortedSet<Document> sortedSet;

    public DocumentSet(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap, ImmutableSortedSet<Document> immutableSortedSet) {
        this.keyIndex = immutableSortedMap;
        this.sortedSet = immutableSortedSet;
    }

    public static /* synthetic */ int a(Comparator comparator, Document document, Document document2) {
        int iCompare = comparator.compare(document, document2);
        return iCompare == 0 ? Document.KEY_COMPARATOR.compare(document, document2) : iCompare;
    }

    public static DocumentSet emptySet(final Comparator<Document> comparator) {
        return new DocumentSet(DocumentCollections.EMPTY_DOCUMENT_MAP, new ImmutableSortedSet(Collections.emptyList(), new Comparator() { // from class: b.e.b.d.e.b
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return DocumentSet.a(comparator, (Document) obj, (Document) obj2);
            }
        }));
    }

    public DocumentSet add(Document document) {
        DocumentSet documentSetRemove = remove(document.getKey());
        return new DocumentSet(documentSetRemove.keyIndex.insert(document.getKey(), document), documentSetRemove.sortedSet.insert(document));
    }

    public boolean contains(DocumentKey documentKey) {
        return this.keyIndex.containsKey(documentKey);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DocumentSet.class != obj.getClass()) {
            return false;
        }
        DocumentSet documentSet = (DocumentSet) obj;
        if (size() != documentSet.size()) {
            return false;
        }
        Iterator<Document> it = iterator();
        Iterator<Document> it2 = documentSet.iterator();
        while (it.hasNext()) {
            if (!it.next().equals(it2.next())) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    public Document getDocument(DocumentKey documentKey) {
        return this.keyIndex.get(documentKey);
    }

    @Nullable
    public Document getFirstDocument() {
        return this.sortedSet.getMinEntry();
    }

    @Nullable
    public Document getLastDocument() {
        return this.sortedSet.getMaxEntry();
    }

    @Nullable
    public Document getPredecessor(DocumentKey documentKey) {
        Document document = this.keyIndex.get(documentKey);
        if (document != null) {
            return this.sortedSet.getPredecessorEntry(document);
        }
        throw new IllegalArgumentException(a.a("Key not contained in DocumentSet: ", documentKey));
    }

    public int hashCode() {
        int iHashCode = 0;
        for (Document document : this) {
            iHashCode = document.getData().hashCode() + ((document.getKey().hashCode() + (iHashCode * 31)) * 31);
        }
        return iHashCode;
    }

    public int indexOf(DocumentKey documentKey) {
        Document document = this.keyIndex.get(documentKey);
        if (document == null) {
            return -1;
        }
        return this.sortedSet.indexOf(document);
    }

    public boolean isEmpty() {
        return this.keyIndex.isEmpty();
    }

    @Override // java.lang.Iterable
    @NonNull
    public Iterator<Document> iterator() {
        return this.sortedSet.iterator();
    }

    public DocumentSet remove(DocumentKey documentKey) {
        Document document = this.keyIndex.get(documentKey);
        return document == null ? this : new DocumentSet(this.keyIndex.remove(documentKey), this.sortedSet.remove(document));
    }

    public int size() {
        return this.keyIndex.size();
    }

    public List<Document> toList() {
        ArrayList arrayList = new ArrayList(size());
        Iterator<Document> it = iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        boolean z = true;
        for (Document document : this) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(document);
        }
        sb.append("]");
        return sb.toString();
    }
}
