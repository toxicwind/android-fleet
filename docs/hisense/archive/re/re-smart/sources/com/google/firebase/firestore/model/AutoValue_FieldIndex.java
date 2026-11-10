package com.google.firebase.firestore.model;

import b.a.a.a.a;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AutoValue_FieldIndex extends FieldIndex {
    public final String collectionGroup;
    public final int indexId;
    public final FieldIndex.IndexState indexState;
    public final List<FieldIndex.Segment> segments;

    public AutoValue_FieldIndex(int i, String str, List<FieldIndex.Segment> list, FieldIndex.IndexState indexState) {
        this.indexId = i;
        if (str == null) {
            throw new NullPointerException("Null collectionGroup");
        }
        this.collectionGroup = str;
        if (list == null) {
            throw new NullPointerException("Null segments");
        }
        this.segments = list;
        if (indexState == null) {
            throw new NullPointerException("Null indexState");
        }
        this.indexState = indexState;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldIndex)) {
            return false;
        }
        FieldIndex fieldIndex = (FieldIndex) obj;
        return this.indexId == fieldIndex.getIndexId() && this.collectionGroup.equals(fieldIndex.getCollectionGroup()) && this.segments.equals(fieldIndex.getSegments()) && this.indexState.equals(fieldIndex.getIndexState());
    }

    @Override // com.google.firebase.firestore.model.FieldIndex
    public String getCollectionGroup() {
        return this.collectionGroup;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex
    public int getIndexId() {
        return this.indexId;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex
    public FieldIndex.IndexState getIndexState() {
        return this.indexState;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex
    public List<FieldIndex.Segment> getSegments() {
        return this.segments;
    }

    public int hashCode() {
        return ((((((this.indexId ^ 1000003) * 1000003) ^ this.collectionGroup.hashCode()) * 1000003) ^ this.segments.hashCode()) * 1000003) ^ this.indexState.hashCode();
    }

    public String toString() {
        StringBuilder sbA = a.a("FieldIndex{indexId=");
        sbA.append(this.indexId);
        sbA.append(", collectionGroup=");
        sbA.append(this.collectionGroup);
        sbA.append(", segments=");
        sbA.append(this.segments);
        sbA.append(", indexState=");
        return a.a(sbA, this.indexState, "}");
    }
}
