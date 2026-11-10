package com.ibm.icu.text;

/* JADX INFO: loaded from: classes.dex */
abstract class CharsetRecog_2022 extends CharsetRecognizer {
    CharsetRecog_2022() {
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0040  */
    int match(byte[] bArr, int i, byte[][] bArr2) {
        int length = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (length < i) {
            if (bArr[length] == 27) {
                int i5 = 0;
                while (true) {
                    if (i5 < bArr2.length) {
                        byte[] bArr3 = bArr2[i5];
                        if (i - length >= bArr3.length) {
                            int i6 = 1;
                            while (true) {
                                if (i6 < bArr3.length) {
                                    if (bArr3[i6] == bArr[length + i6]) {
                                        i6++;
                                    }
                                } else {
                                    i2++;
                                    length += bArr3.length - 1;
                                }
                            }
                        }
                        i5++;
                    } else {
                        i3++;
                        if (bArr[length] != 14 || bArr[length] == 15) {
                            i4++;
                        }
                    }
                }
            } else if (bArr[length] != 14) {
                i4++;
            } else {
                i4++;
            }
            length++;
        }
        if (i2 == 0) {
            return 0;
        }
        int i7 = ((i2 * 100) - (i3 * 100)) / (i3 + i2);
        int i8 = i2 + i4;
        if (i8 < 5) {
            i7 -= (5 - i8) * 10;
        }
        if (i7 < 0) {
            return 0;
        }
        return i7;
    }

    static class CharsetRecog_2022JP extends CharsetRecog_2022 {
        private byte[][] escapeSequences = {new byte[]{27, 36, 40, 67}, new byte[]{27, 36, 40, 68}, new byte[]{27, 36, 64}, new byte[]{27, 36, 65}, new byte[]{27, 36, 66}, new byte[]{27, 38, 64}, new byte[]{27, 40, 66}, new byte[]{27, 40, 72}, new byte[]{27, 40, 73}, new byte[]{27, 40, 74}, new byte[]{27, 46, 65}, new byte[]{27, 46, 70}};

        @Override // com.ibm.icu.text.CharsetRecognizer
        String getName() {
            return "ISO-2022-JP";
        }

        CharsetRecog_2022JP() {
        }

        @Override // com.ibm.icu.text.CharsetRecognizer
        CharsetMatch match(CharsetDetector charsetDetector) {
            int iMatch = match(charsetDetector.fInputBytes, charsetDetector.fInputLen, this.escapeSequences);
            if (iMatch == 0) {
                return null;
            }
            return new CharsetMatch(charsetDetector, this, iMatch);
        }
    }

    static class CharsetRecog_2022KR extends CharsetRecog_2022 {
        private byte[][] escapeSequences = {new byte[]{27, 36, 41, 67}};

        @Override // com.ibm.icu.text.CharsetRecognizer
        String getName() {
            return "ISO-2022-KR";
        }

        CharsetRecog_2022KR() {
        }

        @Override // com.ibm.icu.text.CharsetRecognizer
        CharsetMatch match(CharsetDetector charsetDetector) {
            int iMatch = match(charsetDetector.fInputBytes, charsetDetector.fInputLen, this.escapeSequences);
            if (iMatch == 0) {
                return null;
            }
            return new CharsetMatch(charsetDetector, this, iMatch);
        }
    }

    static class CharsetRecog_2022CN extends CharsetRecog_2022 {
        private byte[][] escapeSequences = {new byte[]{27, 36, 41, 65}, new byte[]{27, 36, 41, 71}, new byte[]{27, 36, 42, 72}, new byte[]{27, 36, 41, 69}, new byte[]{27, 36, 43, 73}, new byte[]{27, 36, 43, 74}, new byte[]{27, 36, 43, 75}, new byte[]{27, 36, 43, 76}, new byte[]{27, 36, 43, 77}, new byte[]{27, 78}, new byte[]{27, 79}};

        @Override // com.ibm.icu.text.CharsetRecognizer
        String getName() {
            return "ISO-2022-CN";
        }

        CharsetRecog_2022CN() {
        }

        @Override // com.ibm.icu.text.CharsetRecognizer
        CharsetMatch match(CharsetDetector charsetDetector) {
            int iMatch = match(charsetDetector.fInputBytes, charsetDetector.fInputLen, this.escapeSequences);
            if (iMatch == 0) {
                return null;
            }
            return new CharsetMatch(charsetDetector, this, iMatch);
        }
    }
}
