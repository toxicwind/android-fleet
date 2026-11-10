package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import b.a.a.a.a;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DocumentChange {
    public final QueryDocumentSnapshot document;
    public final int newIndex;
    public final int oldIndex;
    public final Type type;

    /* JADX INFO: renamed from: com.google.firebase.firestore.DocumentChange$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type = new int[DocumentViewChange.Type.values().length];

        static {
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.METADATA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.MODIFIED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.REMOVED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public enum Type {
        ADDED,
        MODIFIED,
        REMOVED
    }

    @VisibleForTesting
    public DocumentChange(QueryDocumentSnapshot queryDocumentSnapshot, Type type, int i, int i2) {
        this.type = type;
        this.document = queryDocumentSnapshot;
        this.oldIndex = i;
        this.newIndex = i2;
    }

    public static List<DocumentChange> changesFromSnapshot(FirebaseFirestore firebaseFirestore, MetadataChanges metadataChanges, ViewSnapshot viewSnapshot) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList arrayList = new ArrayList();
        if (viewSnapshot.getOldDocuments().isEmpty()) {
            Document document = null;
            int i = 0;
            for (DocumentViewChange documentViewChange : viewSnapshot.getChanges()) {
                Document document2 = documentViewChange.getDocument();
                QueryDocumentSnapshot queryDocumentSnapshotFromDocument = QueryDocumentSnapshot.fromDocument(firebaseFirestore, document2, viewSnapshot.isFromCache(), viewSnapshot.getMutatedKeys().contains(document2.getKey()));
                Assert.hardAssert(documentViewChange.getType() == DocumentViewChange.Type.ADDED, "Invalid added event for first snapshot", new Object[0]);
                Assert.hardAssert(document == null || viewSnapshot.getQuery().comparator().compare(document, document2) < 0, "Got added events in wrong order", new Object[0]);
                arrayList.add(new DocumentChange(queryDocumentSnapshotFromDocument, Type.ADDED, -1, i));
                document = document2;
                i++;
            }
        } else {
            DocumentSet oldDocuments = viewSnapshot.getOldDocuments();
            for (DocumentViewChange documentViewChange2 : viewSnapshot.getChanges()) {
                if (metadataChanges != MetadataChanges.EXCLUDE || documentViewChange2.getType() != DocumentViewChange.Type.METADATA) {
                    Document document3 = documentViewChange2.getDocument();
                    QueryDocumentSnapshot queryDocumentSnapshotFromDocument2 = QueryDocumentSnapshot.fromDocument(firebaseFirestore, document3, viewSnapshot.isFromCache(), viewSnapshot.getMutatedKeys().contains(document3.getKey()));
                    Type type = getType(documentViewChange2);
                    if (type != Type.ADDED) {
                        iIndexOf = oldDocuments.indexOf(document3.getKey());
                        Assert.hardAssert(iIndexOf >= 0, "Index for document not found", new Object[0]);
                        oldDocuments = oldDocuments.remove(document3.getKey());
                    } else {
                        iIndexOf = -1;
                    }
                    if (type != Type.REMOVED) {
                        oldDocuments = oldDocuments.add(document3);
                        iIndexOf2 = oldDocuments.indexOf(document3.getKey());
                        Assert.hardAssert(iIndexOf2 >= 0, "Index for document not found", new Object[0]);
                    } else {
                        iIndexOf2 = -1;
                    }
                    arrayList.add(new DocumentChange(queryDocumentSnapshotFromDocument2, type, iIndexOf, iIndexOf2));
                }
            }
        }
        return arrayList;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof DocumentChange)) {
            return false;
        }
        DocumentChange documentChange = (DocumentChange) obj;
        return this.type.equals(documentChange.type) && this.document.equals(documentChange.document) && this.oldIndex == documentChange.oldIndex && this.newIndex == documentChange.newIndex;
    }

    @NonNull
    public QueryDocumentSnapshot getDocument() {
        return this.document;
    }

    public int getNewIndex() {
        return this.newIndex;
    }

    public int getOldIndex() {
        return this.oldIndex;
    }

    @NonNull
    public Type getType() {
        return this.type;
    }

    public int hashCode() {
        return ((((this.document.hashCode() + (this.type.hashCode() * 31)) * 31) + this.oldIndex) * 31) + this.newIndex;
    }

    public static Type getType(DocumentViewChange documentViewChange) {
        int iOrdinal = documentViewChange.getType().ordinal();
        if (iOrdinal == 0) {
            return Type.REMOVED;
        }
        if (iOrdinal == 1) {
            return Type.ADDED;
        }
        if (iOrdinal == 2 || iOrdinal == 3) {
            return Type.MODIFIED;
        }
        StringBuilder sbA = a.a("Unknown view change type: ");
        sbA.append(documentViewChange.getType());
        throw new IllegalArgumentException(sbA.toString());
    }
}
