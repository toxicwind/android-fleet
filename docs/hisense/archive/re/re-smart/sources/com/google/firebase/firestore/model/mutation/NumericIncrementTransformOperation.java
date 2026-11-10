package com.google.firebase.firestore.model.mutation;

import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.common.primitives.UnsignedLong;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;

/* JADX INFO: loaded from: classes.dex */
public class NumericIncrementTransformOperation implements TransformOperation {
    public Value operand;

    public NumericIncrementTransformOperation(Value value) {
        Assert.hardAssert(Values.isNumber(value), "NumericIncrementTransformOperation expects a NumberValue operand", new Object[0]);
        this.operand = value;
    }

    private double operandAsDouble() {
        if (Values.isDouble(this.operand)) {
            return this.operand.getDoubleValue();
        }
        if (Values.isInteger(this.operand)) {
            return this.operand.getIntegerValue();
        }
        StringBuilder sbA = a.a("Expected 'operand' to be of Number type, but was ");
        sbA.append(this.operand.getClass().getCanonicalName());
        Assert.fail(sbA.toString(), new Object[0]);
        throw null;
    }

    private long operandAsLong() {
        if (Values.isDouble(this.operand)) {
            return (long) this.operand.getDoubleValue();
        }
        if (Values.isInteger(this.operand)) {
            return this.operand.getIntegerValue();
        }
        StringBuilder sbA = a.a("Expected 'operand' to be of Number type, but was ");
        sbA.append(this.operand.getClass().getCanonicalName());
        Assert.fail(sbA.toString(), new Object[0]);
        throw null;
    }

    private long safeIncrement(long j, long j2) {
        long j3 = j + j2;
        if (((j ^ j3) & (j2 ^ j3)) >= 0) {
            return j3;
        }
        if (j3 >= 0) {
            return Long.MIN_VALUE;
        }
        return UnsignedLong.UNSIGNED_MASK;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToLocalView(@Nullable Value value, Timestamp timestamp) {
        Value valueComputeBaseValue = computeBaseValue(value);
        if (Values.isInteger(valueComputeBaseValue) && Values.isInteger(this.operand)) {
            return Value.newBuilder().setIntegerValue(safeIncrement(valueComputeBaseValue.getIntegerValue(), operandAsLong())).build();
        }
        if (Values.isInteger(valueComputeBaseValue)) {
            return Value.newBuilder().setDoubleValue(valueComputeBaseValue.getIntegerValue() + operandAsDouble()).build();
        }
        Assert.hardAssert(Values.isDouble(valueComputeBaseValue), "Expected NumberValue to be of type DoubleValue, but was ", value.getClass().getCanonicalName());
        return Value.newBuilder().setDoubleValue(valueComputeBaseValue.getDoubleValue() + operandAsDouble()).build();
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToRemoteDocument(@Nullable Value value, Value value2) {
        return value2;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value computeBaseValue(@Nullable Value value) {
        return Values.isNumber(value) ? value : Value.newBuilder().setIntegerValue(0L).build();
    }

    public Value getOperand() {
        return this.operand;
    }
}
