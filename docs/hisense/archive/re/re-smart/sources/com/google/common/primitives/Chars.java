package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Chars {
    public static final int BYTES = 2;

    @GwtCompatible
    private static class CharArrayAsList extends AbstractList<Character> implements RandomAccess, Serializable {
        public static final long serialVersionUID = 0;
        public final char[] array;
        public final int end;
        public final int start;

        public CharArrayAsList(char[] cArr) {
            int length = cArr.length;
            this.array = cArr;
            this.start = 0;
            this.end = length;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Character) && Chars.indexOf(this.array, ((Character) obj).charValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CharArrayAsList)) {
                return super.equals(obj);
            }
            CharArrayAsList charArrayAsList = (CharArrayAsList) obj;
            int size = size();
            if (charArrayAsList.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (this.array[this.start + i] != charArrayAsList.array[charArrayAsList.start + i]) {
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
            if (!(obj instanceof Character) || (iIndexOf = Chars.indexOf(this.array, ((Character) obj).charValue(), this.start, this.end)) < 0) {
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
            if (!(obj instanceof Character) || (iLastIndexOf = Chars.lastIndexOf(this.array, ((Character) obj).charValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Character> subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, size());
            if (i == i2) {
                return Collections.emptyList();
            }
            char[] cArr = this.array;
            int i3 = this.start;
            return new CharArrayAsList(cArr, i + i3, i3 + i2);
        }

        public char[] toCharArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 3);
            sb.append('[');
            sb.append(this.array[this.start]);
            int i = this.start;
            while (true) {
                i++;
                if (i >= this.end) {
                    sb.append(']');
                    return sb.toString();
                }
                sb.append(", ");
                sb.append(this.array[i]);
            }
        }

        @Override // java.util.AbstractList, java.util.List
        public Character get(int i) {
            Preconditions.checkElementIndex(i, size(), FirebaseAnalytics.Param.INDEX);
            return Character.valueOf(this.array[this.start + i]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Character set(int i, Character ch) {
            Preconditions.checkElementIndex(i, size(), FirebaseAnalytics.Param.INDEX);
            char[] cArr = this.array;
            int i2 = this.start;
            char c2 = cArr[i2 + i];
            int i3 = i2 + i;
            if (ch == null) {
                throw new NullPointerException();
            }
            cArr[i3] = ch.charValue();
            return Character.valueOf(c2);
        }

        public CharArrayAsList(char[] cArr, int i, int i2) {
            this.array = cArr;
            this.start = i;
            this.end = i2;
        }
    }

    private enum LexicographicalComparator implements Comparator<char[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Chars.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(char[] cArr, char[] cArr2) {
            int iMin = Math.min(cArr.length, cArr2.length);
            for (int i = 0; i < iMin; i++) {
                int i2 = cArr[i] - cArr2[i];
                if (i2 != 0) {
                    return i2;
                }
            }
            return cArr.length - cArr2.length;
        }
    }

    public static List<Character> asList(char... cArr) {
        return cArr.length == 0 ? Collections.emptyList() : new CharArrayAsList(cArr);
    }

    public static char checkedCast(long j) {
        char c2 = (char) j;
        Preconditions.checkArgument(((long) c2) == j, "Out of range: %s", j);
        return c2;
    }

    public static int compare(char c2, char c3) {
        return c2 - c3;
    }

    public static char[] concat(char[]... cArr) {
        int length = 0;
        for (char[] cArr2 : cArr) {
            length += cArr2.length;
        }
        char[] cArr3 = new char[length];
        int length2 = 0;
        for (char[] cArr4 : cArr) {
            System.arraycopy(cArr4, 0, cArr3, length2, cArr4.length);
            length2 += cArr4.length;
        }
        return cArr3;
    }

    @Beta
    public static char constrainToRange(char c2, char c3, char c4) {
        Preconditions.checkArgument(c3 <= c4, "min (%s) must be less than or equal to max (%s)", c3, c4);
        if (c2 < c3) {
            return c3;
        }
        return c2 < c4 ? c2 : c4;
    }

    public static boolean contains(char[] cArr, char c2) {
        for (char c3 : cArr) {
            if (c3 == c2) {
                return true;
            }
        }
        return false;
    }

    public static char[] ensureCapacity(char[] cArr, int i, int i2) {
        Preconditions.checkArgument(i >= 0, "Invalid minLength: %s", i);
        Preconditions.checkArgument(i2 >= 0, "Invalid padding: %s", i2);
        return cArr.length < i ? Arrays.copyOf(cArr, i + i2) : cArr;
    }

    @GwtIncompatible
    public static char fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 2, "array too small: %s < %s", bArr.length, 2);
        return fromBytes(bArr[0], bArr[1]);
    }

    @GwtIncompatible
    public static char fromBytes(byte b2, byte b3) {
        return (char) ((b2 << 8) | (b3 & 255));
    }

    public static int hashCode(char c2) {
        return c2;
    }

    public static int indexOf(char[] cArr, char c2) {
        return indexOf(cArr, c2, 0, cArr.length);
    }

    public static String join(String str, char... cArr) {
        if (str == null) {
            throw new NullPointerException();
        }
        int length = cArr.length;
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(((length - 1) * str.length()) + length);
        sb.append(cArr[0]);
        for (int i = 1; i < length; i++) {
            sb.append(str);
            sb.append(cArr[i]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(char[] cArr, char c2) {
        return lastIndexOf(cArr, c2, 0, cArr.length);
    }

    public static Comparator<char[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static char max(char... cArr) {
        Preconditions.checkArgument(cArr.length > 0);
        char c2 = cArr[0];
        for (int i = 1; i < cArr.length; i++) {
            if (cArr[i] > c2) {
                c2 = cArr[i];
            }
        }
        return c2;
    }

    public static char min(char... cArr) {
        Preconditions.checkArgument(cArr.length > 0);
        char c2 = cArr[0];
        for (int i = 1; i < cArr.length; i++) {
            if (cArr[i] < c2) {
                c2 = cArr[i];
            }
        }
        return c2;
    }

    public static void reverse(char[] cArr) {
        if (cArr == null) {
            throw new NullPointerException();
        }
        reverse(cArr, 0, cArr.length);
    }

    public static char saturatedCast(long j) {
        if (j > WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            return (char) 65535;
        }
        if (j < 0) {
            return (char) 0;
        }
        return (char) j;
    }

    public static void sortDescending(char[] cArr) {
        if (cArr == null) {
            throw new NullPointerException();
        }
        sortDescending(cArr, 0, cArr.length);
    }

    public static char[] toArray(Collection<Character> collection) {
        if (collection instanceof CharArrayAsList) {
            return ((CharArrayAsList) collection).toCharArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            Object obj = array[i];
            Preconditions.checkNotNull(obj);
            cArr[i] = ((Character) obj).charValue();
        }
        return cArr;
    }

    @GwtIncompatible
    public static byte[] toByteArray(char c2) {
        return new byte[]{(byte) (c2 >> '\b'), (byte) c2};
    }

    public static int indexOf(char[] cArr, char c2, int i, int i2) {
        while (i < i2) {
            if (cArr[i] == c2) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int lastIndexOf(char[] cArr, char c2, int i, int i2) {
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            if (cArr[i3] == c2) {
                return i3;
            }
        }
        return -1;
    }

    public static int indexOf(char[] cArr, char[] cArr2) {
        Preconditions.checkNotNull(cArr, "array");
        Preconditions.checkNotNull(cArr2, "target");
        if (cArr2.length == 0) {
            return 0;
        }
        for (int i = 0; i < (cArr.length - cArr2.length) + 1; i++) {
            for (int i2 = 0; i2 < cArr2.length; i2++) {
                if (cArr[i + i2] != cArr2[i2]) {
                }
            }
            return i;
        }
        return -1;
    }

    public static void reverse(char[] cArr, int i, int i2) {
        if (cArr != null) {
            Preconditions.checkPositionIndexes(i, i2, cArr.length);
            for (int i3 = i2 - 1; i < i3; i3--) {
                char c2 = cArr[i];
                cArr[i] = cArr[i3];
                cArr[i3] = c2;
                i++;
            }
            return;
        }
        throw new NullPointerException();
    }

    public static void sortDescending(char[] cArr, int i, int i2) {
        if (cArr != null) {
            Preconditions.checkPositionIndexes(i, i2, cArr.length);
            Arrays.sort(cArr, i, i2);
            reverse(cArr, i, i2);
            return;
        }
        throw new NullPointerException();
    }
}
