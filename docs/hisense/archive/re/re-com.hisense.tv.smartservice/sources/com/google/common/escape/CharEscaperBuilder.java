package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
public final class CharEscaperBuilder {
    public int max = -1;
    public final Map<Character, String> map = new HashMap();

    @CanIgnoreReturnValue
    public CharEscaperBuilder addEscape(char c2, String str) {
        Map<Character, String> map = this.map;
        Character chValueOf = Character.valueOf(c2);
        if (str == null) {
            throw new NullPointerException();
        }
        map.put(chValueOf, str);
        if (c2 > this.max) {
            this.max = c2;
        }
        return this;
    }

    @CanIgnoreReturnValue
    public CharEscaperBuilder addEscapes(char[] cArr, String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        for (char c2 : cArr) {
            addEscape(c2, str);
        }
        return this;
    }

    public char[][] toArray() {
        char[][] cArr = new char[this.max + 1][];
        for (Map.Entry<Character, String> entry : this.map.entrySet()) {
            cArr[entry.getKey().charValue()] = entry.getValue().toCharArray();
        }
        return cArr;
    }

    public Escaper toEscaper() {
        return new CharArrayDecorator(toArray());
    }

    private static class CharArrayDecorator extends CharEscaper {
        public final int replaceLength;
        public final char[][] replacements;

        public CharArrayDecorator(char[][] cArr) {
            this.replacements = cArr;
            this.replaceLength = cArr.length;
        }

        @Override // com.google.common.escape.CharEscaper, com.google.common.escape.Escaper
        public String escape(String str) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                char[][] cArr = this.replacements;
                if (cCharAt < cArr.length && cArr[cCharAt] != null) {
                    return escapeSlow(str, i);
                }
            }
            return str;
        }

        @Override // com.google.common.escape.CharEscaper
        public char[] escape(char c2) {
            if (c2 < this.replaceLength) {
                return this.replacements[c2];
            }
            return null;
        }
    }
}
