package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.util.Preconditions;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class SetOptions {

    @Nullable
    public final FieldMask fieldMask;
    public final boolean merge;
    public static final SetOptions OVERWRITE = new SetOptions(false, null);
    public static final SetOptions MERGE_ALL_FIELDS = new SetOptions(true, null);

    public SetOptions(boolean z, @Nullable FieldMask fieldMask) {
        Preconditions.checkArgument(fieldMask == null || z, "Cannot specify a fieldMask for non-merge sets()", new Object[0]);
        this.merge = z;
        this.fieldMask = fieldMask;
    }

    @NonNull
    public static SetOptions merge() {
        return MERGE_ALL_FIELDS;
    }

    @NonNull
    public static SetOptions mergeFieldPaths(@NonNull List<FieldPath> list) {
        HashSet hashSet = new HashSet();
        Iterator<FieldPath> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getInternalPath());
        }
        return new SetOptions(true, new FieldMask(hashSet));
    }

    @NonNull
    public static SetOptions mergeFields(@NonNull List<String> list) {
        HashSet hashSet = new HashSet();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(FieldPath.fromDotSeparatedPath(it.next()).getInternalPath());
        }
        return new SetOptions(true, new FieldMask(hashSet));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SetOptions.class != obj.getClass()) {
            return false;
        }
        SetOptions setOptions = (SetOptions) obj;
        if (this.merge != setOptions.merge) {
            return false;
        }
        FieldMask fieldMask = this.fieldMask;
        if (fieldMask != null) {
            return fieldMask.equals(setOptions.fieldMask);
        }
        return setOptions.fieldMask == null;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public FieldMask getFieldMask() {
        return this.fieldMask;
    }

    public int hashCode() {
        int i = (this.merge ? 1 : 0) * 31;
        FieldMask fieldMask = this.fieldMask;
        return i + (fieldMask != null ? fieldMask.hashCode() : 0);
    }

    public boolean isMerge() {
        return this.merge;
    }

    @NonNull
    public static SetOptions mergeFields(String... strArr) {
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            hashSet.add(FieldPath.fromDotSeparatedPath(str).getInternalPath());
        }
        return new SetOptions(true, new FieldMask(hashSet));
    }
}
