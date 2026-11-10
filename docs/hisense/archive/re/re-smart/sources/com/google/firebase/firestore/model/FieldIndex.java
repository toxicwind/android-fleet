package com.google.firebase.firestore.model;

import androidx.annotation.Nullable;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class FieldIndex {
    public static final int INITIAL_SEQUENCE_NUMBER = 0;
    public static IndexState INITIAL_STATE = IndexState.create(0, SnapshotVersion.NONE, DocumentKey.empty());
    public static final Comparator<FieldIndex> SEMANTIC_COMPARATOR = new Comparator() { // from class: b.e.b.d.e.d
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return FieldIndex.a((FieldIndex) obj, (FieldIndex) obj2);
        }
    };
    public static final int UNKNOWN_ID = -1;

    public static abstract class IndexOffset implements Comparable<IndexOffset> {
        public static final Comparator<MutableDocument> DOCUMENT_COMPARATOR = new Comparator() { // from class: b.e.b.d.e.c
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return FieldIndex.IndexOffset.fromDocument((MutableDocument) obj).compareTo(FieldIndex.IndexOffset.fromDocument((MutableDocument) obj2));
            }
        };
        public static final IndexOffset NONE = new AutoValue_FieldIndex_IndexOffset(SnapshotVersion.NONE, DocumentKey.empty());

        public static IndexOffset create(SnapshotVersion snapshotVersion, DocumentKey documentKey) {
            return new AutoValue_FieldIndex_IndexOffset(snapshotVersion, documentKey);
        }

        public static IndexOffset fromDocument(Document document) {
            return new AutoValue_FieldIndex_IndexOffset(document.getReadTime(), document.getKey());
        }

        public abstract DocumentKey getDocumentKey();

        public abstract SnapshotVersion getReadTime();

        public static IndexOffset create(SnapshotVersion snapshotVersion) {
            long seconds = snapshotVersion.getTimestamp().getSeconds();
            int nanoseconds = snapshotVersion.getTimestamp().getNanoseconds() + 1;
            return new AutoValue_FieldIndex_IndexOffset(new SnapshotVersion(((double) nanoseconds) == 1.0E9d ? new Timestamp(seconds + 1, 0) : new Timestamp(seconds, nanoseconds)), DocumentKey.empty());
        }

        @Override // java.lang.Comparable
        public int compareTo(IndexOffset indexOffset) {
            int iCompareTo = getReadTime().compareTo(indexOffset.getReadTime());
            return iCompareTo != 0 ? iCompareTo : getDocumentKey().compareTo(indexOffset.getDocumentKey());
        }
    }

    public static abstract class IndexState {
        public static IndexState create(long j, IndexOffset indexOffset) {
            return new AutoValue_FieldIndex_IndexState(j, indexOffset);
        }

        public abstract IndexOffset getOffset();

        public abstract long getSequenceNumber();

        public static IndexState create(long j, SnapshotVersion snapshotVersion, DocumentKey documentKey) {
            return new AutoValue_FieldIndex_IndexState(j, IndexOffset.create(snapshotVersion, documentKey));
        }
    }

    public static abstract class Segment implements Comparable<Segment> {

        public enum Kind {
            ASCENDING,
            DESCENDING,
            CONTAINS
        }

        public static Segment create(FieldPath fieldPath, Kind kind) {
            return new AutoValue_FieldIndex_Segment(fieldPath, kind);
        }

        public abstract FieldPath getFieldPath();

        public abstract Kind getKind();

        @Override // java.lang.Comparable
        public int compareTo(Segment segment) {
            int iCompareTo = getFieldPath().compareTo(segment.getFieldPath());
            return iCompareTo != 0 ? iCompareTo : getKind().compareTo(segment.getKind());
        }
    }

    public static /* synthetic */ int a(FieldIndex fieldIndex, FieldIndex fieldIndex2) {
        int iCompareTo = fieldIndex.getCollectionGroup().compareTo(fieldIndex2.getCollectionGroup());
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        Iterator<Segment> it = fieldIndex.getSegments().iterator();
        Iterator<Segment> it2 = fieldIndex2.getSegments().iterator();
        while (it.hasNext() && it2.hasNext()) {
            int iCompareTo2 = it.next().compareTo(it2.next());
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return Boolean.compare(it.hasNext(), it2.hasNext());
    }

    public static FieldIndex create(int i, String str, List<Segment> list, IndexState indexState) {
        return new AutoValue_FieldIndex(i, str, list, indexState);
    }

    @Nullable
    public Segment getArraySegment() {
        for (Segment segment : getSegments()) {
            if (segment.getKind().equals(Segment.Kind.CONTAINS)) {
                return segment;
            }
        }
        return null;
    }

    public abstract String getCollectionGroup();

    public List<Segment> getDirectionalSegments() {
        ArrayList arrayList = new ArrayList();
        for (Segment segment : getSegments()) {
            if (!segment.getKind().equals(Segment.Kind.CONTAINS)) {
                arrayList.add(segment);
            }
        }
        return arrayList;
    }

    public abstract int getIndexId();

    public abstract IndexState getIndexState();

    public abstract List<Segment> getSegments();
}
