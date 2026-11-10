package com.google.firebase.firestore.model.mutation;

import androidx.annotation.Nullable;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.Value;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ArrayTransformOperation implements TransformOperation {
    public final List<Value> elements;

    public static class Remove extends ArrayTransformOperation {
        public Remove(List<Value> list) {
            super(list);
        }

        @Override // com.google.firebase.firestore.model.mutation.ArrayTransformOperation
        public Value apply(@Nullable Value value) {
            ArrayValue.Builder builderCoercedFieldValuesArray = ArrayTransformOperation.coercedFieldValuesArray(value);
            for (Value value2 : getElements()) {
                int i = 0;
                while (i < builderCoercedFieldValuesArray.getValuesCount()) {
                    if (Values.equals(builderCoercedFieldValuesArray.getValues(i), value2)) {
                        builderCoercedFieldValuesArray.removeValues(i);
                    } else {
                        i++;
                    }
                }
            }
            return Value.newBuilder().setArrayValue(builderCoercedFieldValuesArray).build();
        }
    }

    public static class Union extends ArrayTransformOperation {
        public Union(List<Value> list) {
            super(list);
        }

        @Override // com.google.firebase.firestore.model.mutation.ArrayTransformOperation
        public Value apply(@Nullable Value value) {
            ArrayValue.Builder builderCoercedFieldValuesArray = ArrayTransformOperation.coercedFieldValuesArray(value);
            for (Value value2 : getElements()) {
                if (!Values.contains(builderCoercedFieldValuesArray, value2)) {
                    builderCoercedFieldValuesArray.addValues(value2);
                }
            }
            return Value.newBuilder().setArrayValue(builderCoercedFieldValuesArray).build();
        }
    }

    public ArrayTransformOperation(List<Value> list) {
        this.elements = Collections.unmodifiableList(list);
    }

    public static ArrayValue.Builder coercedFieldValuesArray(@Nullable Value value) {
        return Values.isArray(value) ? value.getArrayValue().toBuilder() : ArrayValue.newBuilder();
    }

    public abstract Value apply(@Nullable Value value);

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToLocalView(@Nullable Value value, Timestamp timestamp) {
        return apply(value);
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToRemoteDocument(@Nullable Value value, Value value2) {
        return apply(value);
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    @Nullable
    public Value computeBaseValue(@Nullable Value value) {
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.elements.equals(((ArrayTransformOperation) obj).elements);
    }

    public List<Value> getElements() {
        return this.elements;
    }

    public int hashCode() {
        return this.elements.hashCode() + (getClass().hashCode() * 31);
    }
}
