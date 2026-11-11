package j$.util;

import com.google.common.base.Ascii;
import io.ktor.util.date.GMTDateParser;
import j$.sun.nio.cs.ISO_8859_1;
import j$.time.ZoneId;
import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes3.dex */
public class Base64 {
    public static Encoder getUrlEncoder() {
        return Encoder.RFC4648_URLSAFE;
    }

    public static Decoder getUrlDecoder() {
        return Decoder.RFC4648_URLSAFE;
    }

    public static class Encoder {
        private static final byte[] CRLF;
        static final Encoder RFC2045;
        static final Encoder RFC4648;
        static final Encoder RFC4648_URLSAFE;
        private static final char[] toBase64 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', GMTDateParser.MONTH, 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', GMTDateParser.YEAR, 'Z', 'a', 'b', 'c', GMTDateParser.DAY_OF_MONTH, 'e', 'f', 'g', GMTDateParser.HOURS, 'i', 'j', 'k', 'l', GMTDateParser.MINUTES, 'n', 'o', 'p', 'q', 'r', GMTDateParser.SECONDS, 't', AbstractJsonLexerKt.UNICODE_ESC, 'v', 'w', 'x', 'y', GMTDateParser.ZONE, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        private static final char[] toBase64URL = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', GMTDateParser.MONTH, 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', GMTDateParser.YEAR, 'Z', 'a', 'b', 'c', GMTDateParser.DAY_OF_MONTH, 'e', 'f', 'g', GMTDateParser.HOURS, 'i', 'j', 'k', 'l', GMTDateParser.MINUTES, 'n', 'o', 'p', 'q', 'r', GMTDateParser.SECONDS, 't', AbstractJsonLexerKt.UNICODE_ESC, 'v', 'w', 'x', 'y', GMTDateParser.ZONE, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};
        private final boolean doPadding;
        private final boolean isURL;
        private final int linemax;
        private final byte[] newline;

        private Encoder(boolean z, byte[] bArr, int i, boolean z2) {
            this.isURL = z;
            this.newline = bArr;
            this.linemax = i;
            this.doPadding = z2;
        }

        static {
            byte[] bArr = {Ascii.CR, 10};
            CRLF = bArr;
            RFC4648 = new Encoder(false, null, -1, true);
            RFC4648_URLSAFE = new Encoder(true, null, -1, true);
            RFC2045 = new Encoder(false, bArr, 76, true);
        }

        private final int outLength(int i) {
            int i2;
            if (this.doPadding) {
                i2 = ((i + 2) / 3) * 4;
            } else {
                int i3 = i % 3;
                i2 = ((i / 3) * 4) + (i3 == 0 ? 0 : i3 + 1);
            }
            int i4 = this.linemax;
            return i4 > 0 ? i2 + (((i2 - 1) / i4) * this.newline.length) : i2;
        }

        public byte[] encode(byte[] bArr) {
            int iOutLength = outLength(bArr.length);
            byte[] bArr2 = new byte[iOutLength];
            int iEncode0 = encode0(bArr, 0, bArr.length, bArr2);
            return iEncode0 != iOutLength ? Arrays.copyOf(bArr2, iEncode0) : bArr2;
        }

        public String encodeToString(byte[] bArr) {
            byte[] bArrEncode = encode(bArr);
            return new String(bArrEncode, 0, 0, bArrEncode.length);
        }

        private void encodeBlock(byte[] bArr, int i, int i2, byte[] bArr2, int i3, boolean z) {
            char[] cArr = z ? toBase64URL : toBase64;
            while (i < i2) {
                int i4 = i + 2;
                int i5 = ((bArr[i + 1] & 255) << 8) | ((bArr[i] & 255) << 16);
                i += 3;
                int i6 = i5 | (bArr[i4] & 255);
                bArr2[i3] = (byte) cArr[(i6 >>> 18) & 63];
                bArr2[i3 + 1] = (byte) cArr[(i6 >>> 12) & 63];
                int i7 = i3 + 3;
                bArr2[i3 + 2] = (byte) cArr[(i6 >>> 6) & 63];
                i3 += 4;
                bArr2[i7] = (byte) cArr[i6 & 63];
            }
        }

        private int encode0(byte[] bArr, int i, int i2, byte[] bArr2) {
            char[] cArr = this.isURL ? toBase64URL : toBase64;
            int i3 = ((i2 - i) / 3) * 3;
            int i4 = i + i3;
            int i5 = this.linemax;
            if (i5 > 0 && i3 > (i5 / 4) * 3) {
                i3 = (i5 / 4) * 3;
            }
            int i6 = i;
            int i7 = 0;
            while (i6 < i4) {
                int iMin = Math.min(i6 + i3, i4);
                Encoder encoder = this;
                byte[] bArr3 = bArr;
                byte[] bArr4 = bArr2;
                encoder.encodeBlock(bArr3, i6, iMin, bArr4, i7, this.isURL);
                int i8 = ((iMin - i6) / 3) * 4;
                i7 += i8;
                if (i8 == encoder.linemax && iMin < i2) {
                    byte[] bArr5 = encoder.newline;
                    int length = bArr5.length;
                    int i9 = 0;
                    while (i9 < length) {
                        bArr4[i7] = bArr5[i9];
                        i9++;
                        i7++;
                    }
                }
                this = encoder;
                bArr = bArr3;
                i6 = iMin;
                bArr2 = bArr4;
            }
            Encoder encoder2 = this;
            byte[] bArr6 = bArr;
            byte[] bArr7 = bArr2;
            if (i6 >= i2) {
                return i7;
            }
            int i10 = i6 + 1;
            int i11 = bArr6[i6] & 255;
            int i12 = i7 + 1;
            bArr7[i7] = (byte) cArr[i11 >> 2];
            if (i10 == i2) {
                int i13 = i7 + 2;
                bArr7[i12] = (byte) cArr[(i11 << 4) & 63];
                if (!encoder2.doPadding) {
                    return i13;
                }
                int i14 = i7 + 3;
                bArr7[i13] = kotlin.io.encoding.Base64.padSymbol;
                int i15 = i7 + 4;
                bArr7[i14] = kotlin.io.encoding.Base64.padSymbol;
                return i15;
            }
            int i16 = bArr6[i10] & 255;
            bArr7[i12] = (byte) cArr[((i11 << 4) & 63) | (i16 >> 4)];
            int i17 = i7 + 3;
            bArr7[i7 + 2] = (byte) cArr[(i16 << 2) & 63];
            if (!encoder2.doPadding) {
                return i17;
            }
            int i18 = i7 + 4;
            bArr7[i17] = kotlin.io.encoding.Base64.padSymbol;
            return i18;
        }
    }

    public static class Decoder {
        static final Decoder RFC2045;
        static final Decoder RFC4648;
        static final Decoder RFC4648_URLSAFE;
        private static final int[] fromBase64;
        private static final int[] fromBase64URL;
        private final boolean isMIME;
        private final boolean isURL;

        /* JADX INFO: renamed from: j$.util.Base64$Decoder$0, reason: invalid class name */
        public final /* synthetic */ class AnonymousClass0 {
            public static /* synthetic */ void m(String str, int i) {
                throw new IllegalArgumentException(str + i);
            }
        }

        private Decoder(boolean z, boolean z2) {
            this.isURL = z;
            this.isMIME = z2;
        }

        static {
            int[] iArr = new int[256];
            fromBase64 = iArr;
            Arrays.fill(iArr, -1);
            for (int i = 0; i < Encoder.toBase64.length; i++) {
                fromBase64[Encoder.toBase64[i]] = i;
            }
            fromBase64[61] = -2;
            int[] iArr2 = new int[256];
            fromBase64URL = iArr2;
            Arrays.fill(iArr2, -1);
            for (int i2 = 0; i2 < Encoder.toBase64URL.length; i2++) {
                fromBase64URL[Encoder.toBase64URL[i2]] = i2;
            }
            fromBase64URL[61] = -2;
            RFC4648 = new Decoder(false, false);
            RFC4648_URLSAFE = new Decoder(true, false);
            RFC2045 = new Decoder(false, true);
        }

        public byte[] decode(byte[] bArr) {
            int iOutLength = outLength(bArr, 0, bArr.length);
            byte[] bArr2 = new byte[iOutLength];
            int iDecode0 = decode0(bArr, 0, bArr.length, bArr2);
            return iDecode0 != iOutLength ? Arrays.copyOf(bArr2, iDecode0) : bArr2;
        }

        public byte[] decode(String str) {
            return decode(str.getBytes(ISO_8859_1.INSTANCE));
        }

        private int outLength(byte[] bArr, int i, int i2) {
            int i3;
            int[] iArr = this.isURL ? fromBase64URL : fromBase64;
            int i4 = i2 - i;
            int i5 = 0;
            if (i4 == 0) {
                return 0;
            }
            boolean z = this.isMIME;
            if (i4 < 2) {
                if (z && iArr[0] == -1) {
                    return 0;
                }
                ZoneId.AnonymousClass4.m("Input byte[] should at least have 2 bytes for base64 bytes");
                return 0;
            }
            if (z) {
                int i6 = 0;
                while (i < i2) {
                    int i7 = i + 1;
                    int i8 = bArr[i] & 255;
                    if (i8 == 61) {
                        i4 -= (i2 - i7) + 1;
                        break;
                    }
                    if (iArr[i8] == -1) {
                        i6++;
                    }
                    i = i7;
                }
                i4 -= i6;
            } else if (bArr[i2 - 1] == 61) {
                i5 = bArr[i2 - 2] == 61 ? 2 : 1;
            }
            if (i5 == 0 && (i3 = i4 & 3) != 0) {
                i5 = 4 - i3;
            }
            return (((i4 + 3) / 4) * 3) - i5;
        }

        /* JADX WARN: Code duplicated, block: B:20:0x0073 A[PHI: r5 r7
          0x0073: PHI (r5v10 int) = (r5v1 int), (r5v1 int), (r5v18 int) binds: [B:9:0x001a, B:11:0x001e, B:18:0x006f] A[DONT_GENERATE, DONT_INLINE]
          0x0073: PHI (r7v6 int) = (r7v1 int), (r7v1 int), (r7v9 int) binds: [B:9:0x001a, B:11:0x001e, B:18:0x006f] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:22:0x007d  */
        /* JADX WARN: Code duplicated, block: B:25:0x0082 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:26:0x0084  */
        /* JADX WARN: Code duplicated, block: B:29:0x008d  */
        /* JADX WARN: Code duplicated, block: B:34:0x0097  */
        /* JADX WARN: Code duplicated, block: B:39:0x00b8  */
        /* JADX WARN: Code duplicated, block: B:41:0x00bf  */
        /* JADX WARN: Code duplicated, block: B:42:0x00d5  */
        /* JADX WARN: Code duplicated, block: B:61:0x009e A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:64:0x0080 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:65:0x009b A[SYNTHETIC] */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x008e, code lost:
        
            if (r6 != 18) goto L43;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private int decode0(byte[] r19, int r20, int r21, byte[] r22) {
            /*
                Method dump skipped, instruction units count: 279
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.Base64.Decoder.decode0(byte[], int, int, byte[]):int");
        }
    }
}
