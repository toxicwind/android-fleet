package com.ibm.icu.text;

import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class CharsetMatch implements Comparable<CharsetMatch> {
    private String fCharsetName;
    private int fConfidence;
    private InputStream fInputStream;

    public String getName() {
        return this.fCharsetName;
    }

    @Override // java.lang.Comparable
    public int compareTo(CharsetMatch charsetMatch) {
        int i = this.fConfidence;
        int i2 = charsetMatch.fConfidence;
        if (i > i2) {
            return 1;
        }
        return i < i2 ? -1 : 0;
    }

    CharsetMatch(CharsetDetector charsetDetector, CharsetRecognizer charsetRecognizer, int i) {
        this.fConfidence = i;
        if (charsetDetector.fInputStream == null) {
            byte[] bArr = charsetDetector.fRawInput;
            int i2 = charsetDetector.fRawLength;
        }
        this.fInputStream = charsetDetector.fInputStream;
        this.fCharsetName = charsetRecognizer.getName();
        charsetRecognizer.getLanguage();
    }

    CharsetMatch(CharsetDetector charsetDetector, CharsetRecognizer charsetRecognizer, int i, String str, String str2) {
        this.fConfidence = i;
        if (charsetDetector.fInputStream == null) {
            byte[] bArr = charsetDetector.fRawInput;
            int i2 = charsetDetector.fRawLength;
        }
        this.fInputStream = charsetDetector.fInputStream;
        this.fCharsetName = str;
    }
}
