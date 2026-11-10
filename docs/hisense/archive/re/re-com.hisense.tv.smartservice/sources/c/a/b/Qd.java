package c.a.b;

import c.a.C0219ea;
import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class Qd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1124a = Logger.getLogger(Qd.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f1125b = "-bin".getBytes(Charsets.US_ASCII);

    public static byte[][] a(C0219ea c0219ea) {
        boolean z;
        byte[][] bArrB = c.a.P.b(c0219ea);
        int i = 0;
        for (int i2 = 0; i2 < bArrB.length; i2 += 2) {
            byte[] bArr = bArrB[i2];
            byte[] bArr2 = bArrB[i2 + 1];
            if (a(bArr, f1125b)) {
                bArrB[i] = bArr;
                bArrB[i + 1] = c.a.P.f841b.encode(bArr2).getBytes(Charsets.US_ASCII);
            } else {
                int length = bArr2.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        z = true;
                        break;
                    }
                    byte b2 = bArr2[i3];
                    if (b2 < 32 || b2 > 126) {
                        z = false;
                        break;
                    }
                    i3++;
                }
                if (z) {
                    bArrB[i] = bArr;
                    bArrB[i + 1] = bArr2;
                } else {
                    String str = new String(bArr, Charsets.US_ASCII);
                    Logger logger = f1124a;
                    StringBuilder sbB = b.a.a.a.a.b("Metadata key=", str, ", value=");
                    sbB.append(Arrays.toString(bArr2));
                    sbB.append(" contains invalid ASCII characters");
                    logger.warning(sbB.toString());
                }
            }
            i += 2;
        }
        return i == bArrB.length ? bArrB : (byte[][]) Arrays.copyOfRange(bArrB, 0, i);
    }

    public static byte[][] a(byte[][] bArr) {
        int i = 0;
        while (i < bArr.length) {
            byte[] bArr2 = bArr[i];
            int i2 = i + 1;
            byte[] bArr3 = bArr[i2];
            if (a(bArr2, f1125b)) {
                for (byte b2 : bArr3) {
                    if (b2 == 44) {
                        ArrayList arrayList = new ArrayList(bArr.length + 10);
                        for (int i3 = 0; i3 < i; i3++) {
                            arrayList.add(bArr[i3]);
                        }
                        while (i < bArr.length) {
                            byte[] bArr4 = bArr[i];
                            byte[] bArr5 = bArr[i + 1];
                            if (!a(bArr4, f1125b)) {
                                arrayList.add(bArr4);
                                arrayList.add(bArr5);
                            } else {
                                int i4 = 0;
                                for (int i5 = 0; i5 <= bArr5.length; i5++) {
                                    if (i5 == bArr5.length || bArr5[i5] == 44) {
                                        byte[] bArrDecode = BaseEncoding.BASE64.decode(new String(bArr5, i4, i5 - i4, Charsets.US_ASCII));
                                        arrayList.add(bArr4);
                                        arrayList.add(bArrDecode);
                                        i4 = i5 + 1;
                                    }
                                }
                            }
                            i += 2;
                        }
                        return (byte[][]) arrayList.toArray(new byte[0][]);
                    }
                }
                bArr[i2] = BaseEncoding.BASE64.decode(new String(bArr3, Charsets.US_ASCII));
            }
            i += 2;
        }
        return bArr;
    }

    public static boolean a(byte[] bArr, byte[] bArr2) {
        int length = bArr.length - bArr2.length;
        if (length < 0) {
            return false;
        }
        for (int i = length; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i - length]) {
                return false;
            }
        }
        return true;
    }
}
