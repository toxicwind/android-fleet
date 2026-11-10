package d;

/* JADX INFO: renamed from: d.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0245d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f2097a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    static {
        byte[] bArr = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    }

    public static byte[] a(String str) {
        int i;
        char cCharAt;
        int length = str.length();
        while (length > 0 && ((cCharAt = str.charAt(length - 1)) == '=' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == ' ' || cCharAt == '\t')) {
            length--;
        }
        byte[] bArr = new byte[(int) ((((long) length) * 6) / 8)];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            char cCharAt2 = str.charAt(i5);
            if (cCharAt2 >= 'A' && cCharAt2 <= 'Z') {
                i = cCharAt2 - 'A';
            } else if (cCharAt2 >= 'a' && cCharAt2 <= 'z') {
                i = cCharAt2 - 'G';
            } else if (cCharAt2 >= '0' && cCharAt2 <= '9') {
                i = cCharAt2 + 4;
            } else if (cCharAt2 == '+' || cCharAt2 == '-') {
                i = 62;
            } else {
                if (cCharAt2 == '/' || cCharAt2 == '_') {
                    i = 63;
                } else if (cCharAt2 != '\n' && cCharAt2 != '\r' && cCharAt2 != ' ' && cCharAt2 != '\t') {
                    return null;
                }
            }
            i3 = (i3 << 6) | ((byte) i);
            i2++;
            if (i2 % 4 == 0) {
                int i6 = i4 + 1;
                bArr[i4] = (byte) (i3 >> 16);
                int i7 = i6 + 1;
                bArr[i6] = (byte) (i3 >> 8);
                bArr[i7] = (byte) i3;
                i4 = i7 + 1;
            }
        }
        int i8 = i2 % 4;
        if (i8 == 1) {
            return null;
        }
        if (i8 == 2) {
            bArr[i4] = (byte) ((i3 << 12) >> 16);
            i4++;
        } else if (i8 == 3) {
            int i9 = i3 << 6;
            int i10 = i4 + 1;
            bArr[i4] = (byte) (i9 >> 16);
            i4 = i10 + 1;
            bArr[i10] = (byte) (i9 >> 8);
        }
        if (i4 == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, 0, bArr2, 0, i4);
        return bArr2;
    }
}
