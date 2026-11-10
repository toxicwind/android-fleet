package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ByteString implements Serializable, Comparable {
    static final char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final ByteString d = l(new byte[0]);
    private static final long serialVersionUID = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    transient int f260a;
    transient String b;
    final byte[] data;

    ByteString(byte[] bArr) {
        this.data = bArr;
    }

    static int b(String str, int i) {
        int length = str.length();
        int iCharCount = 0;
        int i2 = 0;
        while (iCharCount < length) {
            if (i2 == i) {
                return iCharCount;
            }
            int iCodePointAt = str.codePointAt(iCharCount);
            if ((Character.isISOControl(iCodePointAt) && iCodePointAt != 10 && iCodePointAt != 13) || iCodePointAt == 65533) {
                return -1;
            }
            i2++;
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str.length();
    }

    public static ByteString d(String str) {
        if (str == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        byte[] bArrA = C0070e.a(str);
        if (bArrA != null) {
            return new ByteString(bArrA);
        }
        return null;
    }

    public static ByteString e(String str) {
        if (str == null) {
            throw new IllegalArgumentException("hex == null");
        }
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + str);
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((f(str.charAt(i2)) << 4) + f(str.charAt(i2 + 1)));
        }
        return l(bArr);
    }

    private static int f(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (c2 < 'a' || c2 > 'f') {
            c3 = 'A';
            if (c2 < 'A' || c2 > 'F') {
                throw new IllegalArgumentException("Unexpected hex digit: " + c2);
            }
        }
        return (c2 - c3) + 10;
    }

    private ByteString g(String str) {
        try {
            return l(MessageDigest.getInstance(str).digest(this.data));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public static ByteString h(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        ByteString byteString = new ByteString(str.getBytes(E.f262a));
        byteString.b = str;
        return byteString;
    }

    public static ByteString l(byte... bArr) {
        if (bArr != null) {
            return new ByteString((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static ByteString o(InputStream inputStream, int i) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + i);
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = inputStream.read(bArr, i2, i - i2);
            if (i3 == -1) {
                throw new EOFException();
            }
            i2 += i3;
        }
        return new ByteString(bArr);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        ByteString byteStringO = o(objectInputStream, objectInputStream.readInt());
        try {
            Field declaredField = ByteString.class.getDeclaredField("data");
            declaredField.setAccessible(true);
            declaredField.set(this, byteStringO.data);
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        } catch (NoSuchFieldException unused2) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }

    public String a() {
        return C0070e.b(this.data);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public int compareTo(ByteString byteString) {
        int iR = r();
        int iR2 = byteString.r();
        int iMin = Math.min(iR, iR2);
        for (int i = 0; i < iMin; i++) {
            int i2 = i(i) & 255;
            int i3 = byteString.i(i) & 255;
            if (i2 != i3) {
                return i2 < i3 ? -1 : 1;
            }
        }
        if (iR == iR2) {
            return 0;
        }
        return iR < iR2 ? -1 : 1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            int iR = byteString.r();
            byte[] bArr = this.data;
            if (iR == bArr.length && byteString.n(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.f260a;
        if (i != 0) {
            return i;
        }
        int iHashCode = Arrays.hashCode(this.data);
        this.f260a = iHashCode;
        return iHashCode;
    }

    public byte i(int i) {
        return this.data[i];
    }

    public String j() {
        byte[] bArr = this.data;
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            char[] cArr2 = c;
            cArr[i] = cArr2[(b >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public ByteString k() {
        return g("MD5");
    }

    public boolean m(int i, ByteString byteString, int i2, int i3) {
        return byteString.n(i2, this.data, i, i3);
    }

    public boolean n(int i, byte[] bArr, int i2, int i3) {
        if (i >= 0) {
            byte[] bArr2 = this.data;
            if (i <= bArr2.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && E.a(bArr2, i, bArr, i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public ByteString p() {
        return g("SHA-1");
    }

    public ByteString q() {
        return g("SHA-256");
    }

    public int r() {
        return this.data.length;
    }

    public final boolean s(ByteString byteString) {
        return m(0, byteString, 0, byteString.r());
    }

    public ByteString t(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        byte[] bArr = this.data;
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.data.length + ")");
        }
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (i == 0 && i2 == bArr.length) {
            return this;
        }
        byte[] bArr2 = new byte[i3];
        System.arraycopy(this.data, i, bArr2, 0, i3);
        return new ByteString(bArr2);
    }

    public String toString() {
        StringBuilder sb;
        if (this.data.length == 0) {
            return "[size=0]";
        }
        String strW = w();
        int iB = b(strW, 64);
        if (iB == -1) {
            if (this.data.length <= 64) {
                sb = new StringBuilder();
                sb.append("[hex=");
                sb.append(j());
                sb.append("]");
            } else {
                sb = new StringBuilder();
                sb.append("[size=");
                sb.append(this.data.length);
                sb.append(" hex=");
                sb.append(t(0, 64).j());
                sb.append("…]");
            }
            return sb.toString();
        }
        String strReplace = strW.substring(0, iB).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
        if (iB >= strW.length()) {
            return "[text=" + strReplace + "]";
        }
        return "[size=" + this.data.length + " text=" + strReplace + "…]";
    }

    public ByteString u() {
        int i = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i >= bArr.length) {
                return this;
            }
            byte b = bArr[i];
            if (b >= 65 && b <= 90) {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < bArr2.length; i2++) {
                    byte b2 = bArr2[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        bArr2[i2] = (byte) (b2 + 32);
                    }
                }
                return new ByteString(bArr2);
            }
            i++;
        }
    }

    public byte[] v() {
        return (byte[]) this.data.clone();
    }

    public String w() {
        String str = this.b;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.data, E.f262a);
        this.b = str2;
        return str2;
    }

    void x(h hVar) {
        byte[] bArr = this.data;
        hVar.i0(bArr, 0, bArr.length);
    }
}
