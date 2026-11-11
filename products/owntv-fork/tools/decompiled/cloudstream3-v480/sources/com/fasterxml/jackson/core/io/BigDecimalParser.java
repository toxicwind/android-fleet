package com.fasterxml.jackson.core.io;

import androidx.collection.SieveCacheKt;
import java.math.BigDecimal;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class BigDecimalParser {
    private final char[] chars;

    BigDecimalParser(char[] cArr) {
        this.chars = cArr;
    }

    public static BigDecimal parse(String str) {
        return parse(str.toCharArray());
    }

    public static BigDecimal parse(char[] cArr, int i, int i2) {
        if (i > 0 || i2 != cArr.length) {
            cArr = Arrays.copyOfRange(cArr, i, i2 + i);
        }
        return parse(cArr);
    }

    public static BigDecimal parse(char[] cArr) {
        int length = cArr.length;
        try {
            if (length < 500) {
                return new BigDecimal(cArr);
            }
            return new BigDecimalParser(cArr).parseBigDecimal(length / 10);
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            if (message == null) {
                message = "Not a valid number representation";
            }
            throw new NumberFormatException("Value \"" + new String(cArr) + "\" can not be represented as `java.math.BigDecimal`, reason: " + message);
        }
    }

    private BigDecimal parseBigDecimal(int i) {
        int i2;
        BigDecimal bigDecimalRec;
        int length = this.chars.length;
        int i3 = -1;
        int i4 = -1;
        int iAdjustScale = 0;
        boolean z = false;
        boolean z2 = false;
        int i5 = 0;
        boolean z3 = false;
        for (int i6 = 0; i6 < length; i6++) {
            char c = this.chars[i6];
            if (c != '+') {
                if (c == 'E' || c == 'e') {
                    if (i3 >= 0) {
                        throw new NumberFormatException("Multiple exponent markers");
                    }
                    i3 = i6;
                } else if (c != '-') {
                    if (c == '.') {
                        if (i4 >= 0) {
                            throw new NumberFormatException("Multiple decimal points");
                        }
                        i4 = i6;
                    } else if (i4 >= 0 && i3 == -1) {
                        iAdjustScale++;
                    }
                } else if (i3 >= 0) {
                    if (z2) {
                        throw new NumberFormatException("Multiple signs in exponent");
                    }
                    z2 = true;
                } else {
                    if (z) {
                        throw new NumberFormatException("Multiple signs in number");
                    }
                    i5 = i6 + 1;
                    z = true;
                    z3 = true;
                }
            } else if (i3 >= 0) {
                if (z2) {
                    throw new NumberFormatException("Multiple signs in exponent");
                }
                z2 = true;
            } else {
                if (z) {
                    throw new NumberFormatException("Multiple signs in number");
                }
                i5 = i6 + 1;
                z = true;
            }
        }
        if (i3 >= 0) {
            i2 = Integer.parseInt(new String(this.chars, i3 + 1, (length - i3) - 1));
            iAdjustScale = adjustScale(iAdjustScale, i2);
            length = i3;
        } else {
            i2 = 0;
        }
        if (i4 >= 0) {
            int i7 = (length - i4) - 1;
            bigDecimalRec = toBigDecimalRec(i5, i4 - i5, i2, i).add(toBigDecimalRec(i4 + 1, i7, i2 - i7, i));
        } else {
            bigDecimalRec = toBigDecimalRec(i5, length - i5, i2, i);
        }
        if (iAdjustScale != 0) {
            bigDecimalRec = bigDecimalRec.setScale(iAdjustScale);
        }
        return z3 ? bigDecimalRec.negate() : bigDecimalRec;
    }

    private int adjustScale(int i, long j) {
        long j2 = ((long) i) - j;
        if (j2 <= SieveCacheKt.NodeLinkMask && j2 >= SieveCacheKt.NodeMetaAndPreviousMask) {
            return (int) j2;
        }
        throw new NumberFormatException("Scale out of range: " + j2 + " while adjusting scale " + i + " to exponent " + j);
    }

    private BigDecimal toBigDecimalRec(int i, int i2, int i3, int i4) {
        if (i2 <= i4) {
            return i2 == 0 ? BigDecimal.ZERO : new BigDecimal(this.chars, i, i2).movePointRight(i3);
        }
        int i5 = i2 / 2;
        return toBigDecimalRec(i, i5, (i3 + i2) - i5, i4).add(toBigDecimalRec(i + i5, i2 - i5, i3, i4));
    }
}
