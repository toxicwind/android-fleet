package com.google.protobuf;

/* JADX INFO: loaded from: classes2.dex */
public final class ProtobufLists {
    public static Internal.BooleanList emptyBooleanList() {
        return BooleanArrayList.EMPTY_LIST;
    }

    public static Internal.DoubleList emptyDoubleList() {
        return DoubleArrayList.EMPTY_LIST;
    }

    public static Internal.FloatList emptyFloatList() {
        return FloatArrayList.EMPTY_LIST;
    }

    public static Internal.IntList emptyIntList() {
        return IntArrayList.EMPTY_LIST;
    }

    public static Internal.LongList emptyLongList() {
        return LongArrayList.EMPTY_LIST;
    }

    public static <E> Internal.ProtobufList<E> emptyProtobufList() {
        return ProtobufArrayList.EMPTY_LIST;
    }

    public static <E> Internal.ProtobufList<E> mutableCopy(Internal.ProtobufList<E> protobufList) {
        int size = protobufList.size();
        return protobufList.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
    }

    public static Internal.BooleanList newBooleanList() {
        return new BooleanArrayList();
    }

    public static Internal.DoubleList newDoubleList() {
        return new DoubleArrayList();
    }

    public static Internal.FloatList newFloatList() {
        return new FloatArrayList();
    }

    public static Internal.IntList newIntList() {
        return new IntArrayList();
    }

    public static Internal.LongList newLongList() {
        return new LongArrayList();
    }
}
