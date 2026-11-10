package com.google.firebase.firestore.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class AutoValue_FieldIndex_Segment extends FieldIndex.Segment {
    public final FieldPath fieldPath;
    public final FieldIndex.Segment.Kind kind;

    public AutoValue_FieldIndex_Segment(FieldPath fieldPath, FieldIndex.Segment.Kind kind) {
        if (fieldPath == null) {
            throw new NullPointerException("Null fieldPath");
        }
        this.fieldPath = fieldPath;
        if (kind == null) {
            throw new NullPointerException("Null kind");
        }
        this.kind = kind;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldIndex.Segment)) {
            return false;
        }
        FieldIndex.Segment segment = (FieldIndex.Segment) obj;
        return this.fieldPath.equals(segment.getFieldPath()) && this.kind.equals(segment.getKind());
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.Segment
    public FieldPath getFieldPath() {
        return this.fieldPath;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.Segment
    public FieldIndex.Segment.Kind getKind() {
        return this.kind;
    }

    public int hashCode() {
        return ((this.fieldPath.hashCode() ^ 1000003) * 1000003) ^ this.kind.hashCode();
    }

    public String toString() {
        StringBuilder sbA = a.a("Segment{fieldPath=");
        sbA.append(this.fieldPath);
        sbA.append(", kind=");
        return a.a(sbA, this.kind, "}");
    }
}
