package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
public abstract class UnicodeEscaper extends Escaper {
    public static final int DEST_PAD = 32;

    public static int codePointAt(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            throw new NullPointerException();
        }
        if (i >= i2) {
            throw new IndexOutOfBoundsException("Index exceeds specified range");
        }
        int i3 = i + 1;
        char cCharAt = charSequence.charAt(i);
        if (cCharAt < 55296 || cCharAt > 57343) {
            return cCharAt;
        }
        if (cCharAt > 56319) {
            String strValueOf = String.valueOf(charSequence);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 88);
            sb.append("Unexpected low surrogate character '");
            sb.append(cCharAt);
            sb.append("' with value ");
            sb.append((int) cCharAt);
            sb.append(" at index ");
            sb.append(i3 - 1);
            sb.append(" in '");
            sb.append(strValueOf);
            sb.append("'");
            throw new IllegalArgumentException(sb.toString());
        }
        if (i3 == i2) {
            return -cCharAt;
        }
        char cCharAt2 = charSequence.charAt(i3);
        if (Character.isLowSurrogate(cCharAt2)) {
            return Character.toCodePoint(cCharAt, cCharAt2);
        }
        String strValueOf2 = String.valueOf(charSequence);
        StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 89);
        sb2.append("Expected low surrogate but got char '");
        sb2.append(cCharAt2);
        sb2.append("' with value ");
        sb2.append((int) cCharAt2);
        sb2.append(" at index ");
        sb2.append(i3);
        sb2.append(" in '");
        sb2.append(strValueOf2);
        sb2.append("'");
        throw new IllegalArgumentException(sb2.toString());
    }

    public static char[] growBuffer(char[] cArr, int i, int i2) {
        if (i2 < 0) {
            throw new AssertionError("Cannot increase internal buffer any further");
        }
        char[] cArr2 = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i);
        }
        return cArr2;
    }

    @Override // com.google.common.escape.Escaper
    public String escape(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        int length = str.length();
        int iNextEscapeIndex = nextEscapeIndex(str, 0, length);
        return iNextEscapeIndex == length ? str : escapeSlow(str, iNextEscapeIndex);
    }

    public abstract char[] escape(int i);

    public final String escapeSlow(String str, int i) {
        int i2;
        int length = str.length();
        char[] cArrCharBufferFromThreadLocal = Platform.charBufferFromThreadLocal();
        int i3 = 0;
        int length2 = 0;
        while (i < length) {
            int iCodePointAt = codePointAt(str, i, length);
            if (iCodePointAt < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            char[] cArrEscape = escape(iCodePointAt);
            int i4 = (Character.isSupplementaryCodePoint(iCodePointAt) ? 2 : 1) + i;
            if (cArrEscape != null) {
                int i5 = i - i3;
                int i6 = length2 + i5;
                int length3 = cArrEscape.length + i6;
                if (cArrCharBufferFromThreadLocal.length < length3) {
                    cArrCharBufferFromThreadLocal = growBuffer(cArrCharBufferFromThreadLocal, length2, (length - i) + length3 + 32);
                }
                if (i5 > 0) {
                    str.getChars(i3, i, cArrCharBufferFromThreadLocal, length2);
                    length2 = i6;
                }
                if (cArrEscape.length > 0) {
                    System.arraycopy(cArrEscape, 0, cArrCharBufferFromThreadLocal, length2, cArrEscape.length);
                    length2 += cArrEscape.length;
                }
                i3 = i4;
            }
            i = nextEscapeIndex(str, i4, length);
        }
        int i7 = length - i3;
        if (i7 > 0) {
            i2 = i7 + length2;
            if (cArrCharBufferFromThreadLocal.length < i2) {
                cArrCharBufferFromThreadLocal = growBuffer(cArrCharBufferFromThreadLocal, length2, i2);
            }
            str.getChars(i3, length, cArrCharBufferFromThreadLocal, length2);
        } else {
            i2 = length2;
        }
        return new String(cArrCharBufferFromThreadLocal, 0, i2);
    }

    public int nextEscapeIndex(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            int iCodePointAt = codePointAt(charSequence, i, i2);
            if (iCodePointAt < 0 || escape(iCodePointAt) != null) {
                break;
            }
            i += Character.isSupplementaryCodePoint(iCodePointAt) ? 2 : 1;
        }
        return i;
    }
}
