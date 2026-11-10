package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.firebase.firestore.util.Preconditions;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class FieldPath {
    public final com.google.firebase.firestore.model.FieldPath internalPath;
    public static final Pattern RESERVED = Pattern.compile("[~*/\\[\\]]");
    public static final FieldPath DOCUMENT_ID_INSTANCE = new FieldPath(com.google.firebase.firestore.model.FieldPath.KEY_PATH);

    public FieldPath(List<String> list) {
        this.internalPath = com.google.firebase.firestore.model.FieldPath.fromSegments(list);
    }

    @NonNull
    public static FieldPath documentId() {
        return DOCUMENT_ID_INSTANCE;
    }

    public static FieldPath fromDotSeparatedPath(@NonNull String str) {
        Preconditions.checkNotNull(str, "Provided field path must not be null.");
        Preconditions.checkArgument(!RESERVED.matcher(str).find(), "Use FieldPath.of() for field names containing '~*/[]'.", new Object[0]);
        try {
            return of(str.split("\\.", -1));
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException(a.a("Invalid field path (", str, "). Paths must not be empty, begin with '.', end with '.', or contain '..'"));
        }
    }

    @NonNull
    public static FieldPath of(String... strArr) {
        Preconditions.checkArgument(strArr.length > 0, "Invalid field path. Provided path must not be empty.", new Object[0]);
        int i = 0;
        while (i < strArr.length) {
            boolean z = (strArr[i] == null || strArr[i].isEmpty()) ? false : true;
            StringBuilder sbA = a.a("Invalid field name at argument ");
            i++;
            sbA.append(i);
            sbA.append(". Field names must not be null or empty.");
            Preconditions.checkArgument(z, sbA.toString(), new Object[0]);
        }
        return new FieldPath((List<String>) Arrays.asList(strArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FieldPath.class != obj.getClass()) {
            return false;
        }
        return this.internalPath.equals(((FieldPath) obj).internalPath);
    }

    public com.google.firebase.firestore.model.FieldPath getInternalPath() {
        return this.internalPath;
    }

    public int hashCode() {
        return this.internalPath.hashCode();
    }

    public String toString() {
        return this.internalPath.toString();
    }

    public FieldPath(com.google.firebase.firestore.model.FieldPath fieldPath) {
        this.internalPath = fieldPath;
    }
}
