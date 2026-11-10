package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class Bytes {

    @GwtCompatible
    private static class ByteArrayAsList extends AbstractList<Byte> implements RandomAccess, Serializable {
        public static final long serialVersionUID = 0;
        public final byte[] array;
        public final int end;
        public final int start;

        public ByteArrayAsList(byte[] bArr) {
            int length = bArr.length;
            this.array = bArr;
            this.start = 0;
            this.end = length;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Byte) && Bytes.indexOf(this.array, ((Byte) obj).byteValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ByteArrayAsList)) {
                return super.equals(obj);
            }
            ByteArrayAsList byteArrayAsList = (ByteArrayAsList) obj;
            int size = size();
            if (byteArrayAsList.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (this.array[this.start + i] != byteArrayAsList.array[byteArrayAsList.start + i]) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int i = 1;
            for (int i2 = this.start; i2 < this.end; i2++) {
                i = (i * 31) + this.array[i2];
            }
            return i;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            int iIndexOf;
            if (!(obj instanceof Byte) || (iIndexOf = Bytes.indexOf(this.array, ((Byte) obj).byteValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            int iLastIndexOf;
            if (!(obj instanceof Byte) || (iLastIndexOf = Bytes.lastIndexOf(this.array, ((Byte) obj).byteValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Byte> subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, size());
            if (i == i2) {
                return Collections.emptyList();
            }
            byte[] bArr = this.array;
            int i3 = this.start;
            return new ByteArrayAsList(bArr, i + i3, i3 + i2);
        }

        public byte[] toByteArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 5);
            sb.append('[');
            sb.append((int) this.array[this.start]);
            int i = this.start;
            while (true) {
                i++;
                if (i >= this.end) {
                    sb.append(']');
                    return sb.toString();
                }
                sb.append(", ");
                sb.append((int) this.array[i]);
            }
        }

        @Override // java.util.AbstractList, java.util.List
        public Byte get(int i) {
            Preconditions.checkElementIndex(i, size(), FirebaseAnalytics.Param.INDEX);
            return Byte.valueOf(this.array[this.start + i]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Byte set(int i, Byte b2) {
            Preconditions.checkElementIndex(i, size(), FirebaseAnalytics.Param.INDEX);
            byte[] bArr = this.array;
            int i2 = this.start;
            byte b3 = bArr[i2 + i];
            int i3 = i2 + i;
            if (b2 == null) {
                throw new NullPointerException();
            }
            bArr[i3] = b2.byteValue();
            return Byte.valueOf(b3);
        }

        public ByteArrayAsList(byte[] bArr, int i, int i2) {
            this.array = bArr;
            this.start = i;
            this.end = i2;
        }
    }

    public static List<Byte> asList(byte... bArr) {
        return bArr.length == 0 ? Collections.emptyList() : new ByteArrayAsList(bArr);
    }

    public static byte[] concat(byte[]... bArr) {
        int length = 0;
        for (byte[] bArr2 : bArr) {
            length += bArr2.length;
        }
        byte[] bArr3 = new byte[length];
        int length2 = 0;
        for (byte[] bArr4 : bArr) {
            System.arraycopy(bArr4, 0, bArr3, length2, bArr4.length);
            length2 += bArr4.length;
        }
        return bArr3;
    }

    public static boolean contains(byte[] bArr, byte b2) {
        for (byte b3 : bArr) {
            if (b3 == b2) {
                return true;
            }
        }
        return false;
    }

    public static byte[] ensureCapacity(byte[] bArr, int i, int i2) {
        Preconditions.checkArgument(i >= 0, "Invalid minLength: %s", i);
        Preconditions.checkArgument(i2 >= 0, "Invalid padding: %s", i2);
        return bArr.length < i ? Arrays.copyOf(bArr, i + i2) : bArr;
    }

    public static int hashCode(byte b2) {
        return b2;
    }

    public static int indexOf(byte[] bArr, byte b2) {
        return indexOf(bArr, b2, 0, bArr.length);
    }

    public static int lastIndexOf(byte[] bArr, byte b2) {
        return lastIndexOf(bArr, b2, 0, bArr.length);
    }

    public static void reverse(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        reverse(bArr, 0, bArr.length);
    }

    public static byte[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ByteArrayAsList) {
            return ((ByteArrayAsList) collection).toByteArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            Object obj = array[i];
            Preconditions.checkNotNull(obj);
            bArr[i] = ((Number) obj).byteValue();
        }
        return bArr;
    }

    public static int indexOf(byte[] bArr, byte b2, int i, int i2) {
        while (i < i2) {
            if (bArr[i] == b2) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int lastIndexOf(byte[] bArr, byte b2, int i, int i2) {
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            if (bArr[i3] == b2) {
                return i3;
            }
        }
        return -1;
    }

    public static int indexOf(byte[] bArr, byte[] bArr2) {
        Preconditions.checkNotNull(bArr, "array");
        Preconditions.checkNotNull(bArr2, "target");
        if (bArr2.length == 0) {
            return 0;
        }
        for (int i = 0; i < (bArr.length - bArr2.length) + 1; i++) {
            for (int i2 = 0; i2 < bArr2.length; i2++) {
                if (bArr[i + i2] != bArr2[i2]) {
                }
            }
            return i;
        }
        return -1;
    }

    public static void reverse(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            Preconditions.checkPositionIndexes(i, i2, bArr.length);
            for (int i3 = i2 - 1; i < i3; i3--) {
                byte b2 = bArr[i];
                bArr[i] = bArr[i3];
                bArr[i3] = b2;
                i++;
            }
            return;
        }
        throw new NullPointerException();
    }
}
