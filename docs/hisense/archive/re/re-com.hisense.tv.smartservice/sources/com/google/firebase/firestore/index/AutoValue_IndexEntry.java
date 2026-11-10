package com.google.firebase.firestore.index;

import b.a.a.a.a;
import com.google.firebase.firestore.model.DocumentKey;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class AutoValue_IndexEntry extends IndexEntry {
    public final byte[] arrayValue;
    public final byte[] directionalValue;
    public final DocumentKey documentKey;
    public final int indexId;

    public AutoValue_IndexEntry(int i, DocumentKey documentKey, byte[] bArr, byte[] bArr2) {
        this.indexId = i;
        if (documentKey == null) {
            throw new NullPointerException("Null documentKey");
        }
        this.documentKey = documentKey;
        if (bArr == null) {
            throw new NullPointerException("Null arrayValue");
        }
        this.arrayValue = bArr;
        if (bArr2 == null) {
            throw new NullPointerException("Null directionalValue");
        }
        this.directionalValue = bArr2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IndexEntry)) {
            return false;
        }
        IndexEntry indexEntry = (IndexEntry) obj;
        if (this.indexId == indexEntry.getIndexId() && this.documentKey.equals(indexEntry.getDocumentKey())) {
            boolean z = indexEntry instanceof AutoValue_IndexEntry;
            if (Arrays.equals(this.arrayValue, z ? ((AutoValue_IndexEntry) indexEntry).arrayValue : indexEntry.getArrayValue())) {
                if (Arrays.equals(this.directionalValue, z ? ((AutoValue_IndexEntry) indexEntry).directionalValue : indexEntry.getDirectionalValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.firebase.firestore.index.IndexEntry
    public byte[] getArrayValue() {
        return this.arrayValue;
    }

    @Override // com.google.firebase.firestore.index.IndexEntry
    public byte[] getDirectionalValue() {
        return this.directionalValue;
    }

    @Override // com.google.firebase.firestore.index.IndexEntry
    public DocumentKey getDocumentKey() {
        return this.documentKey;
    }

    @Override // com.google.firebase.firestore.index.IndexEntry
    public int getIndexId() {
        return this.indexId;
    }

    public int hashCode() {
        return ((((((this.indexId ^ 1000003) * 1000003) ^ this.documentKey.hashCode()) * 1000003) ^ Arrays.hashCode(this.arrayValue)) * 1000003) ^ Arrays.hashCode(this.directionalValue);
    }

    public String toString() {
        StringBuilder sbA = a.a("IndexEntry{indexId=");
        sbA.append(this.indexId);
        sbA.append(", documentKey=");
        sbA.append(this.documentKey);
        sbA.append(", arrayValue=");
        sbA.append(Arrays.toString(this.arrayValue));
        sbA.append(", directionalValue=");
        sbA.append(Arrays.toString(this.directionalValue));
        sbA.append("}");
        return sbA.toString();
    }
}
