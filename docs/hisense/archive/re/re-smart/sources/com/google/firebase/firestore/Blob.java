package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import b.a.a.a.a;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;

/* JADX INFO: loaded from: classes.dex */
public class Blob implements Comparable<Blob> {
    public final ByteString bytes;

    public Blob(ByteString byteString) {
        this.bytes = byteString;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Blob fromByteString(@NonNull ByteString byteString) {
        Preconditions.checkNotNull(byteString, "Provided ByteString must not be null.");
        return new Blob(byteString);
    }

    @NonNull
    public static Blob fromBytes(@NonNull byte[] bArr) {
        Preconditions.checkNotNull(bArr, "Provided bytes array must not be null.");
        return new Blob(ByteString.copyFrom(bArr));
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof Blob) && this.bytes.equals(((Blob) obj).bytes);
    }

    public int hashCode() {
        return this.bytes.hashCode();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ByteString toByteString() {
        return this.bytes;
    }

    @NonNull
    public byte[] toBytes() {
        return this.bytes.toByteArray();
    }

    @NonNull
    public String toString() {
        StringBuilder sbA = a.a("Blob { bytes=");
        sbA.append(Util.toDebugString(this.bytes));
        sbA.append(" }");
        return sbA.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull Blob blob) {
        return Util.compareByteStrings(this.bytes, blob.bytes);
    }
}
