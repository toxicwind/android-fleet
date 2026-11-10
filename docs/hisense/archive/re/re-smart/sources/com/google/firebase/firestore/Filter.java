package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.firebase.firestore.core.FieldFilter;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class Filter {
    public final FieldPath field;
    public final FieldFilter.Operator operator;
    public final Object value;

    public Filter(@NonNull FieldPath fieldPath, FieldFilter.Operator operator, Object obj) {
        this.field = fieldPath;
        this.operator = operator;
        this.value = obj;
    }

    @NonNull
    public static Filter arrayContains(@NonNull String str, @Nullable Object obj) {
        return arrayContains(FieldPath.fromDotSeparatedPath(str), obj);
    }

    @NonNull
    public static Filter arrayContainsAny(@NonNull String str, @Nullable Object obj) {
        return arrayContainsAny(FieldPath.fromDotSeparatedPath(str), obj);
    }

    @NonNull
    public static Filter equalTo(@NonNull String str, @Nullable Object obj) {
        return equalTo(FieldPath.fromDotSeparatedPath(str), obj);
    }

    @NonNull
    public static Filter greaterThan(@NonNull String str, @Nullable Object obj) {
        return greaterThan(FieldPath.fromDotSeparatedPath(str), obj);
    }

    @NonNull
    public static Filter greaterThanOrEqualTo(@NonNull String str, @Nullable Object obj) {
        return greaterThanOrEqualTo(FieldPath.fromDotSeparatedPath(str), obj);
    }

    @NonNull
    public static Filter inArray(@NonNull String str, @Nullable Object obj) {
        return inArray(FieldPath.fromDotSeparatedPath(str), obj);
    }

    @NonNull
    public static Filter lessThan(@NonNull String str, @Nullable Object obj) {
        return lessThan(FieldPath.fromDotSeparatedPath(str), obj);
    }

    @NonNull
    public static Filter lessThanOrEqualTo(@NonNull String str, @Nullable Object obj) {
        return lessThanOrEqualTo(FieldPath.fromDotSeparatedPath(str), obj);
    }

    @NonNull
    public static Filter notEqualTo(@NonNull String str, @Nullable Object obj) {
        return notEqualTo(FieldPath.fromDotSeparatedPath(str), obj);
    }

    @NonNull
    public static Filter notInArray(@NonNull String str, @Nullable Object obj) {
        return notInArray(FieldPath.fromDotSeparatedPath(str), obj);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public FieldPath getField() {
        return this.field;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public FieldFilter.Operator getOperator() {
        return this.operator;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Object getValue() {
        return this.value;
    }

    @NonNull
    public static Filter arrayContains(@NonNull FieldPath fieldPath, @Nullable Object obj) {
        return new Filter(fieldPath, FieldFilter.Operator.ARRAY_CONTAINS, obj);
    }

    @NonNull
    public static Filter arrayContainsAny(@NonNull FieldPath fieldPath, @Nullable Object obj) {
        return new Filter(fieldPath, FieldFilter.Operator.ARRAY_CONTAINS_ANY, obj);
    }

    @NonNull
    public static Filter equalTo(@NonNull FieldPath fieldPath, @Nullable Object obj) {
        return new Filter(fieldPath, FieldFilter.Operator.EQUAL, obj);
    }

    @NonNull
    public static Filter greaterThan(@NonNull FieldPath fieldPath, @Nullable Object obj) {
        return new Filter(fieldPath, FieldFilter.Operator.GREATER_THAN, obj);
    }

    @NonNull
    public static Filter greaterThanOrEqualTo(@NonNull FieldPath fieldPath, @Nullable Object obj) {
        return new Filter(fieldPath, FieldFilter.Operator.GREATER_THAN_OR_EQUAL, obj);
    }

    @NonNull
    public static Filter inArray(@NonNull FieldPath fieldPath, @Nullable Object obj) {
        return new Filter(fieldPath, FieldFilter.Operator.IN, obj);
    }

    @NonNull
    public static Filter lessThan(@NonNull FieldPath fieldPath, @Nullable Object obj) {
        return new Filter(fieldPath, FieldFilter.Operator.LESS_THAN, obj);
    }

    @NonNull
    public static Filter lessThanOrEqualTo(@NonNull FieldPath fieldPath, @Nullable Object obj) {
        return new Filter(fieldPath, FieldFilter.Operator.LESS_THAN_OR_EQUAL, obj);
    }

    @NonNull
    public static Filter notEqualTo(@NonNull FieldPath fieldPath, @Nullable Object obj) {
        return new Filter(fieldPath, FieldFilter.Operator.NOT_EQUAL, obj);
    }

    @NonNull
    public static Filter notInArray(@NonNull FieldPath fieldPath, @Nullable Object obj) {
        return new Filter(fieldPath, FieldFilter.Operator.NOT_IN, obj);
    }
}
