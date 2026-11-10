package com.google.common.net;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.UnicodeEscaper;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
public final class PercentEscaper extends UnicodeEscaper {
    public static final char[] PLUS_SIGN = {'+'};
    public static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    public final boolean plusForSpace;
    public final boolean[] safeOctets;

    public PercentEscaper(String str, boolean z) {
        if (str == null) {
            throw new NullPointerException();
        }
        if (str.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        String strConcat = str.concat("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        if (z && strConcat.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        this.plusForSpace = z;
        this.safeOctets = createSafeOctets(strConcat);
    }

    public static boolean[] createSafeOctets(String str) {
        char[] charArray = str.toCharArray();
        int iMax = -1;
        for (char c2 : charArray) {
            iMax = Math.max((int) c2, iMax);
        }
        boolean[] zArr = new boolean[iMax + 1];
        for (char c3 : charArray) {
            zArr[c3] = true;
        }
        return zArr;
    }

    @Override // com.google.common.escape.UnicodeEscaper, com.google.common.escape.Escaper
    public String escape(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            boolean[] zArr = this.safeOctets;
            if (cCharAt >= zArr.length || !zArr[cCharAt]) {
                return escapeSlow(str, i);
            }
        }
        return str;
    }

    @Override // com.google.common.escape.UnicodeEscaper
    public int nextEscapeIndex(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            throw new NullPointerException();
        }
        while (i < i2) {
            char cCharAt = charSequence.charAt(i);
            boolean[] zArr = this.safeOctets;
            if (cCharAt >= zArr.length || !zArr[cCharAt]) {
                break;
            }
            i++;
        }
        return i;
    }

    @Override // com.google.common.escape.UnicodeEscaper
    public char[] escape(int i) {
        boolean[] zArr = this.safeOctets;
        if (i < zArr.length && zArr[i]) {
            return null;
        }
        if (i == 32 && this.plusForSpace) {
            return PLUS_SIGN;
        }
        if (i <= 127) {
            char[] cArr = UPPER_HEX_DIGITS;
            return new char[]{'%', cArr[i >>> 4], cArr[i & 15]};
        }
        if (i <= 2047) {
            char[] cArr2 = UPPER_HEX_DIGITS;
            char[] cArr3 = {'%', cArr2[(i >>> 4) | 12], cArr2[i & 15], '%', cArr2[(i & 3) | 8], cArr2[i & 15]};
            int i2 = i >>> 4;
            int i3 = i2 >>> 2;
            return cArr3;
        }
        if (i <= 65535) {
            char[] cArr4 = UPPER_HEX_DIGITS;
            char[] cArr5 = {'%', 'E', cArr4[i >>> 2], '%', cArr4[(i & 3) | 8], cArr4[i & 15], '%', cArr4[(i & 3) | 8], cArr4[i & 15]};
            int i4 = i >>> 4;
            int i5 = i4 >>> 2;
            int i6 = i5 >>> 4;
            return cArr5;
        }
        if (i <= 1114111) {
            char[] cArr6 = UPPER_HEX_DIGITS;
            char[] cArr7 = {'%', 'F', cArr6[(i >>> 2) & 7], '%', cArr6[(i & 3) | 8], cArr6[i & 15], '%', cArr6[(i & 3) | 8], cArr6[i & 15], '%', cArr6[(i & 3) | 8], cArr6[i & 15]};
            int i7 = i >>> 4;
            int i8 = i7 >>> 2;
            int i9 = i8 >>> 4;
            int i10 = i9 >>> 2;
            int i11 = i10 >>> 4;
            return cArr7;
        }
        throw new IllegalArgumentException(a.a(43, "Invalid unicode character value ", i));
    }
}
