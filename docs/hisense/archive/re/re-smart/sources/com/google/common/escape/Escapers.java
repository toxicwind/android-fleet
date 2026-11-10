package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
public final class Escapers {
    public static final Escaper NULL_ESCAPER = new CharEscaper() { // from class: com.google.common.escape.Escapers.1
        @Override // com.google.common.escape.CharEscaper, com.google.common.escape.Escaper
        public String escape(String str) {
            if (str != null) {
                return str;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.escape.CharEscaper
        public char[] escape(char c2) {
            return null;
        }
    };

    /* JADX INFO: renamed from: com.google.common.escape.Escapers$2, reason: invalid class name */
    public class AnonymousClass2 extends UnicodeEscaper {
        public final /* synthetic */ CharEscaper val$escaper;

        public AnonymousClass2(CharEscaper charEscaper) {
            this.val$escaper = charEscaper;
        }

        @Override // com.google.common.escape.UnicodeEscaper
        public char[] escape(int i) {
            if (i < 65536) {
                return this.val$escaper.escape((char) i);
            }
            char[] cArr = new char[2];
            Character.toChars(i, cArr, 0);
            char[] cArrEscape = this.val$escaper.escape(cArr[0]);
            char[] cArrEscape2 = this.val$escaper.escape(cArr[1]);
            if (cArrEscape == null && cArrEscape2 == null) {
                return null;
            }
            int length = cArrEscape != null ? cArrEscape.length : 1;
            char[] cArr2 = new char[(cArrEscape2 != null ? cArrEscape2.length : 1) + length];
            if (cArrEscape != null) {
                for (int i2 = 0; i2 < cArrEscape.length; i2++) {
                    cArr2[i2] = cArrEscape[i2];
                }
            } else {
                cArr2[0] = cArr[0];
            }
            if (cArrEscape2 != null) {
                for (int i3 = 0; i3 < cArrEscape2.length; i3++) {
                    cArr2[length + i3] = cArrEscape2[i3];
                }
            } else {
                cArr2[length] = cArr[1];
            }
            return cArr2;
        }
    }

    @Beta
    public static final class Builder {
        public final Map<Character, String> replacementMap;
        public char safeMax;
        public char safeMin;
        public String unsafeReplacement;

        @CanIgnoreReturnValue
        public Builder addEscape(char c2, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.replacementMap.put(Character.valueOf(c2), str);
            return this;
        }

        public Escaper build() {
            return new ArrayBasedCharEscaper(this.replacementMap, this.safeMin, this.safeMax) { // from class: com.google.common.escape.Escapers.Builder.1
                public final char[] replacementChars;

                {
                    String str = Builder.this.unsafeReplacement;
                    this.replacementChars = str != null ? str.toCharArray() : null;
                }

                @Override // com.google.common.escape.ArrayBasedCharEscaper
                public char[] escapeUnsafe(char c2) {
                    return this.replacementChars;
                }
            };
        }

        @CanIgnoreReturnValue
        public Builder setSafeRange(char c2, char c3) {
            this.safeMin = c2;
            this.safeMax = c3;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUnsafeReplacement(String str) {
            this.unsafeReplacement = str;
            return this;
        }

        public Builder() {
            this.replacementMap = new HashMap();
            this.safeMin = (char) 0;
            this.safeMax = (char) 65535;
            this.unsafeReplacement = null;
        }
    }

    public static UnicodeEscaper asUnicodeEscaper(Escaper escaper) {
        if (escaper == null) {
            throw new NullPointerException();
        }
        if (escaper instanceof UnicodeEscaper) {
            return (UnicodeEscaper) escaper;
        }
        if (escaper instanceof CharEscaper) {
            return new AnonymousClass2((CharEscaper) escaper);
        }
        String name = escaper.getClass().getName();
        throw new IllegalArgumentException(name.length() != 0 ? "Cannot create a UnicodeEscaper from: ".concat(name) : new String("Cannot create a UnicodeEscaper from: "));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static String computeReplacement(CharEscaper charEscaper, char c2) {
        return stringOrNull(charEscaper.escape(c2));
    }

    public static Escaper nullEscaper() {
        return NULL_ESCAPER;
    }

    public static String stringOrNull(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        return new String(cArr);
    }

    public static UnicodeEscaper wrap(CharEscaper charEscaper) {
        return new AnonymousClass2(charEscaper);
    }

    public static String computeReplacement(UnicodeEscaper unicodeEscaper, int i) {
        return stringOrNull(unicodeEscaper.escape(i));
    }
}
