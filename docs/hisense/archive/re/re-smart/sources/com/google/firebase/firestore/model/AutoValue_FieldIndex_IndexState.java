package com.google.firebase.firestore.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class AutoValue_FieldIndex_IndexState extends FieldIndex.IndexState {
    public final FieldIndex.IndexOffset offset;
    public final long sequenceNumber;

    public AutoValue_FieldIndex_IndexState(long j, FieldIndex.IndexOffset indexOffset) {
        this.sequenceNumber = j;
        if (indexOffset == null) {
            throw new NullPointerException("Null offset");
        }
        this.offset = indexOffset;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldIndex.IndexState)) {
            return false;
        }
        FieldIndex.IndexState indexState = (FieldIndex.IndexState) obj;
        return this.sequenceNumber == indexState.getSequenceNumber() && this.offset.equals(indexState.getOffset());
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.IndexState
    public FieldIndex.IndexOffset getOffset() {
        return this.offset;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.IndexState
    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public int hashCode() {
        long j = this.sequenceNumber;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.offset.hashCode();
    }

    public String toString() {
        StringBuilder sbA = a.a("IndexState{sequenceNumber=");
        sbA.append(this.sequenceNumber);
        sbA.append(", offset=");
        return a.a(sbA, this.offset, "}");
    }
}
