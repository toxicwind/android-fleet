package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
public abstract class ArrayBasedCharEscaper extends CharEscaper {
    public final char[][] replacements;
    public final int replacementsLength;
    public final char safeMax;
    public final char safeMin;

    public ArrayBasedCharEscaper(Map<Character, String> map, char c2, char c3) {
        this(ArrayBasedEscaperMap.create(map), c2, c3);
    }

    @Override // com.google.common.escape.CharEscaper, com.google.common.escape.Escaper
    public final String escape(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt < this.replacementsLength && this.replacements[cCharAt] != null) || cCharAt > this.safeMax || cCharAt < this.safeMin) {
                return escapeSlow(str, i);
            }
        }
        return str;
    }

    public abstract char[] escapeUnsafe(char c2);

    public ArrayBasedCharEscaper(ArrayBasedEscaperMap arrayBasedEscaperMap, char c2, char c3) {
        if (arrayBasedEscaperMap == null) {
            throw new NullPointerException();
        }
        this.replacements = arrayBasedEscaperMap.getReplacementArray();
        this.replacementsLength = this.replacements.length;
        if (c3 < c2) {
            c3 = 0;
            c2 = 65535;
        }
        this.safeMin = c2;
        this.safeMax = c3;
    }

    @Override // com.google.common.escape.CharEscaper
    public final char[] escape(char c2) {
        char[] cArr;
        if (c2 < this.replacementsLength && (cArr = this.replacements[c2]) != null) {
            return cArr;
        }
        if (c2 < this.safeMin || c2 > this.safeMax) {
            return escapeUnsafe(c2);
        }
        return null;
    }
}
