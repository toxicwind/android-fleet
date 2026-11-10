package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class FieldValue {
    public static final DeleteFieldValue DELETE_INSTANCE = new DeleteFieldValue();
    public static final ServerTimestampFieldValue SERVER_TIMESTAMP_INSTANCE = new ServerTimestampFieldValue();

    static class ArrayRemoveFieldValue extends FieldValue {
        public final List<Object> elements;

        public ArrayRemoveFieldValue(List<Object> list) {
            this.elements = list;
        }

        public List<Object> getElements() {
            return this.elements;
        }

        @Override // com.google.firebase.firestore.FieldValue
        public String getMethodName() {
            return "FieldValue.arrayRemove";
        }
    }

    static class ArrayUnionFieldValue extends FieldValue {
        public final List<Object> elements;

        public ArrayUnionFieldValue(List<Object> list) {
            this.elements = list;
        }

        public List<Object> getElements() {
            return this.elements;
        }

        @Override // com.google.firebase.firestore.FieldValue
        public String getMethodName() {
            return "FieldValue.arrayUnion";
        }
    }

    static class DeleteFieldValue extends FieldValue {
        @Override // com.google.firebase.firestore.FieldValue
        public String getMethodName() {
            return "FieldValue.delete";
        }
    }

    static class NumericIncrementFieldValue extends FieldValue {
        public final Number operand;

        public NumericIncrementFieldValue(Number number) {
            this.operand = number;
        }

        @Override // com.google.firebase.firestore.FieldValue
        public String getMethodName() {
            return "FieldValue.increment";
        }

        public Number getOperand() {
            return this.operand;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static class ServerTimestampFieldValue extends FieldValue {
        @Override // com.google.firebase.firestore.FieldValue
        public String getMethodName() {
            return "FieldValue.serverTimestamp";
        }
    }

    @NonNull
    public static FieldValue arrayRemove(Object... objArr) {
        return new ArrayRemoveFieldValue(Arrays.asList(objArr));
    }

    @NonNull
    public static FieldValue arrayUnion(Object... objArr) {
        return new ArrayUnionFieldValue(Arrays.asList(objArr));
    }

    @NonNull
    public static FieldValue delete() {
        return DELETE_INSTANCE;
    }

    @NonNull
    public static FieldValue increment(long j) {
        return new NumericIncrementFieldValue(Long.valueOf(j));
    }

    @NonNull
    public static FieldValue serverTimestamp() {
        return SERVER_TIMESTAMP_INSTANCE;
    }

    public abstract String getMethodName();

    @NonNull
    public static FieldValue increment(double d2) {
        return new NumericIncrementFieldValue(Double.valueOf(d2));
    }
}
