package com.google.protobuf;

import b.a.a.a.a;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes2.dex */
public final class ProtobufArrayList<E> extends AbstractProtobufList<E> implements RandomAccess {
    public static final ProtobufArrayList<Object> EMPTY_LIST = new ProtobufArrayList<>(new Object[0], 0);
    public E[] array;
    public int size;

    static {
        EMPTY_LIST.makeImmutable();
    }

    public ProtobufArrayList() {
        this.array = (E[]) new Object[10];
        this.size = 0;
    }

    public static <E> E[] createArray(int i) {
        return (E[]) new Object[i];
    }

    public static <E> ProtobufArrayList<E> emptyList() {
        return (ProtobufArrayList<E>) EMPTY_LIST;
    }

    private void ensureIndexInRange(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        }
    }

    private String makeOutOfBoundsExceptionMessage(int i) {
        StringBuilder sbA = a.a("Index:", i, ", Size:");
        sbA.append(this.size);
        return sbA.toString();
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e2) {
        ensureIsMutable();
        int i = this.size;
        E[] eArr = this.array;
        if (i == eArr.length) {
            this.array = (E[]) Arrays.copyOf(eArr, ((i * 3) / 2) + 1);
        }
        E[] eArr2 = this.array;
        int i2 = this.size;
        this.size = i2 + 1;
        eArr2[i2] = e2;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        ensureIndexInRange(i);
        return this.array[i];
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public E remove(int i) {
        ensureIsMutable();
        ensureIndexInRange(i);
        E[] eArr = this.array;
        E e2 = eArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(eArr, i + 1, eArr, i, (i2 - i) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return e2;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public E set(int i, E e2) {
        ensureIsMutable();
        ensureIndexInRange(i);
        E[] eArr = this.array;
        E e3 = eArr[i];
        eArr[i] = e2;
        ((AbstractList) this).modCount++;
        return e3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    public ProtobufArrayList<E> mutableCopyWithCapacity(int i) {
        if (i >= this.size) {
            return new ProtobufArrayList<>(Arrays.copyOf(this.array, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public ProtobufArrayList(E[] eArr, int i) {
        this.array = eArr;
        this.size = i;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public void add(int i, E e2) {
        int i2;
        ensureIsMutable();
        if (i >= 0 && i <= (i2 = this.size)) {
            E[] eArr = this.array;
            if (i2 < eArr.length) {
                System.arraycopy(eArr, i, eArr, i + 1, i2 - i);
            } else {
                E[] eArr2 = (E[]) new Object[a.a(i2, 3, 2, 1)];
                System.arraycopy(eArr, 0, eArr2, 0, i);
                System.arraycopy(this.array, i, eArr2, i + 1, this.size - i);
                this.array = eArr2;
            }
            this.array[i] = e2;
            this.size++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
    }
}
