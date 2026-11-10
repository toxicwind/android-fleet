package androidx.core.text;

import android.text.SpannableStringBuilder;
import com.google.common.base.Ascii;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class BidiFormatter {
    public static final int DEFAULT_FLAGS = 2;
    public static final int DIR_LTR = -1;
    public static final int DIR_RTL = 1;
    public static final int DIR_UNKNOWN = 0;
    public static final String EMPTY_STRING = "";
    public static final int FLAG_STEREO_RESET = 2;
    public static final char LRE = 8234;
    public static final char PDF = 8236;
    public static final char RLE = 8235;
    public final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
    public final int mFlags;
    public final boolean mIsRtlContext;
    public static final TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
    public static final char LRM = 8206;
    public static final String LRM_STRING = Character.toString(LRM);
    public static final char RLM = 8207;
    public static final String RLM_STRING = Character.toString(RLM);
    public static final BidiFormatter DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
    public static final BidiFormatter DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);

    private static class DirectionalityEstimator {
        public int charIndex;
        public final boolean isHtml;
        public char lastChar;
        public final int length;
        public final CharSequence text;
        public static final int DIR_TYPE_CACHE_SIZE = 1792;
        public static final byte[] DIR_TYPE_CACHE = new byte[DIR_TYPE_CACHE_SIZE];

        static {
            for (int i = 0; i < 1792; i++) {
                DIR_TYPE_CACHE[i] = Character.getDirectionality(i);
            }
        }

        public DirectionalityEstimator(CharSequence text, boolean isHtml) {
            this.text = text;
            this.isHtml = isHtml;
            this.length = text.length();
        }

        public static byte getCachedDirectionality(char c2) {
            return c2 < 1792 ? DIR_TYPE_CACHE[c2] : Character.getDirectionality(c2);
        }

        private byte skipEntityBackward() {
            char c2;
            int i = this.charIndex;
            do {
                int i2 = this.charIndex;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.text;
                int i3 = i2 - 1;
                this.charIndex = i3;
                this.lastChar = charSequence.charAt(i3);
                c2 = this.lastChar;
                if (c2 == '&') {
                    return Ascii.FF;
                }
            } while (c2 != ';');
            this.charIndex = i;
            this.lastChar = ';';
            return Ascii.CR;
        }

        private byte skipEntityForward() {
            char cCharAt;
            do {
                int i = this.charIndex;
                if (i >= this.length) {
                    return Ascii.FF;
                }
                CharSequence charSequence = this.text;
                this.charIndex = i + 1;
                cCharAt = charSequence.charAt(i);
                this.lastChar = cCharAt;
            } while (cCharAt != ';');
            return Ascii.FF;
        }

        private byte skipTagBackward() {
            char cCharAt;
            int i = this.charIndex;
            while (true) {
                int i2 = this.charIndex;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.text;
                int i3 = i2 - 1;
                this.charIndex = i3;
                this.lastChar = charSequence.charAt(i3);
                char c2 = this.lastChar;
                if (c2 == '<') {
                    return Ascii.FF;
                }
                if (c2 == '>') {
                    break;
                }
                if (c2 == '\"' || c2 == '\'') {
                    char c3 = this.lastChar;
                    do {
                        int i4 = this.charIndex;
                        if (i4 <= 0) {
                            break;
                        }
                        CharSequence charSequence2 = this.text;
                        int i5 = i4 - 1;
                        this.charIndex = i5;
                        cCharAt = charSequence2.charAt(i5);
                        this.lastChar = cCharAt;
                    } while (cCharAt != c3);
                }
            }
            this.charIndex = i;
            this.lastChar = '>';
            return Ascii.CR;
        }

        private byte skipTagForward() {
            char cCharAt;
            int i = this.charIndex;
            while (true) {
                int i2 = this.charIndex;
                if (i2 >= this.length) {
                    this.charIndex = i;
                    this.lastChar = '<';
                    return Ascii.CR;
                }
                CharSequence charSequence = this.text;
                this.charIndex = i2 + 1;
                this.lastChar = charSequence.charAt(i2);
                char c2 = this.lastChar;
                if (c2 == '>') {
                    return Ascii.FF;
                }
                if (c2 == '\"' || c2 == '\'') {
                    char c3 = this.lastChar;
                    do {
                        int i3 = this.charIndex;
                        if (i3 >= this.length) {
                            break;
                        }
                        CharSequence charSequence2 = this.text;
                        this.charIndex = i3 + 1;
                        cCharAt = charSequence2.charAt(i3);
                        this.lastChar = cCharAt;
                    } while (cCharAt != c3);
                }
            }
        }

        public byte dirTypeBackward() {
            this.lastChar = this.text.charAt(this.charIndex - 1);
            if (Character.isLowSurrogate(this.lastChar)) {
                int iCodePointBefore = Character.codePointBefore(this.text, this.charIndex);
                this.charIndex -= Character.charCount(iCodePointBefore);
                return Character.getDirectionality(iCodePointBefore);
            }
            this.charIndex--;
            byte cachedDirectionality = getCachedDirectionality(this.lastChar);
            if (!this.isHtml) {
                return cachedDirectionality;
            }
            char c2 = this.lastChar;
            if (c2 == '>') {
                return skipTagBackward();
            }
            return c2 == ';' ? skipEntityBackward() : cachedDirectionality;
        }

        public byte dirTypeForward() {
            this.lastChar = this.text.charAt(this.charIndex);
            if (Character.isHighSurrogate(this.lastChar)) {
                int iCodePointAt = Character.codePointAt(this.text, this.charIndex);
                this.charIndex = Character.charCount(iCodePointAt) + this.charIndex;
                return Character.getDirectionality(iCodePointAt);
            }
            this.charIndex++;
            byte cachedDirectionality = getCachedDirectionality(this.lastChar);
            if (!this.isHtml) {
                return cachedDirectionality;
            }
            char c2 = this.lastChar;
            if (c2 == '<') {
                return skipTagForward();
            }
            if (c2 != '&') {
                return cachedDirectionality;
            }
            skipEntityForward();
            return Ascii.FF;
        }

        public int getEntryDir() {
            this.charIndex = 0;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (this.charIndex < this.length && i == 0) {
                byte bDirTypeForward = dirTypeForward();
                if (bDirTypeForward != 0) {
                    if (bDirTypeForward == 1 || bDirTypeForward == 2) {
                        if (i3 == 0) {
                            return 1;
                        }
                    } else if (bDirTypeForward != 9) {
                        switch (bDirTypeForward) {
                            case 14:
                            case 15:
                                i3++;
                                i2 = -1;
                                continue;
                            case 16:
                            case 17:
                                i3++;
                                i2 = 1;
                                continue;
                            case 18:
                                i3--;
                                i2 = 0;
                                continue;
                        }
                    }
                } else if (i3 == 0) {
                    return -1;
                }
                i = i3;
            }
            if (i == 0) {
                return 0;
            }
            if (i2 != 0) {
                return i2;
            }
            while (this.charIndex > 0) {
                switch (dirTypeBackward()) {
                    case 14:
                    case 15:
                        if (i == i3) {
                            return -1;
                        }
                        break;
                    case 16:
                    case 17:
                        if (i == i3) {
                            return 1;
                        }
                        break;
                    case 18:
                        i3++;
                        continue;
                    default:
                        continue;
                }
                i3--;
            }
            return 0;
        }

        public int getExitDir() {
            this.charIndex = this.length;
            int i = 0;
            int i2 = 0;
            while (this.charIndex > 0) {
                byte bDirTypeBackward = dirTypeBackward();
                if (bDirTypeBackward == 0) {
                    if (i2 == 0) {
                        return -1;
                    }
                    if (i == 0) {
                        i = i2;
                    }
                } else if (bDirTypeBackward == 1 || bDirTypeBackward == 2) {
                    if (i2 == 0) {
                        return 1;
                    }
                    if (i == 0) {
                        i = i2;
                    }
                } else if (bDirTypeBackward != 9) {
                    switch (bDirTypeBackward) {
                        case 14:
                        case 15:
                            if (i == i2) {
                                return -1;
                            }
                            i2--;
                            break;
                        case 16:
                        case 17:
                            if (i == i2) {
                                return 1;
                            }
                            i2--;
                            break;
                        case 18:
                            i2++;
                            break;
                        default:
                            if (i == 0) {
                                i = i2;
                            }
                            break;
                    }
                } else {
                    continue;
                }
            }
            return 0;
        }
    }

    public BidiFormatter(boolean isRtlContext, int flags, TextDirectionHeuristicCompat heuristic) {
        this.mIsRtlContext = isRtlContext;
        this.mFlags = flags;
        this.mDefaultTextDirectionHeuristicCompat = heuristic;
    }

    public static int getEntryDir(CharSequence str) {
        return new DirectionalityEstimator(str, false).getEntryDir();
    }

    public static int getExitDir(CharSequence str) {
        return new DirectionalityEstimator(str, false).getExitDir();
    }

    public static BidiFormatter getInstance(boolean rtlContext) {
        TextDirectionHeuristicCompat textDirectionHeuristicCompat = DEFAULT_TEXT_DIRECTION_HEURISTIC;
        return textDirectionHeuristicCompat == DEFAULT_TEXT_DIRECTION_HEURISTIC ? Builder.getDefaultInstanceFromContext(rtlContext) : new BidiFormatter(rtlContext, 2, textDirectionHeuristicCompat);
    }

    public static boolean isRtlLocale(Locale locale) {
        return TextUtilsCompat.getLayoutDirectionFromLocale(locale) == 1;
    }

    private String markAfter(CharSequence str, TextDirectionHeuristicCompat heuristic) {
        boolean zIsRtl = heuristic.isRtl(str, 0, str.length());
        if (!this.mIsRtlContext && (zIsRtl || getExitDir(str) == 1)) {
            return LRM_STRING;
        }
        if (this.mIsRtlContext) {
            return (!zIsRtl || getExitDir(str) == -1) ? RLM_STRING : "";
        }
        return "";
    }

    private String markBefore(CharSequence str, TextDirectionHeuristicCompat heuristic) {
        boolean zIsRtl = heuristic.isRtl(str, 0, str.length());
        if (!this.mIsRtlContext && (zIsRtl || getEntryDir(str) == 1)) {
            return LRM_STRING;
        }
        if (this.mIsRtlContext) {
            return (!zIsRtl || getEntryDir(str) == -1) ? RLM_STRING : "";
        }
        return "";
    }

    public boolean getStereoReset() {
        return (this.mFlags & 2) != 0;
    }

    public boolean isRtl(String str) {
        return isRtl((CharSequence) str);
    }

    public boolean isRtlContext() {
        return this.mIsRtlContext;
    }

    public String unicodeWrap(String str, TextDirectionHeuristicCompat heuristic, boolean isolate) {
        if (str == null) {
            return null;
        }
        return unicodeWrap((CharSequence) str, heuristic, isolate).toString();
    }

    public static final class Builder {
        public int mFlags;
        public boolean mIsRtlContext;
        public TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;

        public Builder() {
            initialize(BidiFormatter.isRtlLocale(Locale.getDefault()));
        }

        public static BidiFormatter getDefaultInstanceFromContext(boolean isRtlContext) {
            return isRtlContext ? BidiFormatter.DEFAULT_RTL_INSTANCE : BidiFormatter.DEFAULT_LTR_INSTANCE;
        }

        private void initialize(boolean isRtlContext) {
            this.mIsRtlContext = isRtlContext;
            this.mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
            this.mFlags = 2;
        }

        public BidiFormatter build() {
            return (this.mFlags == 2 && this.mTextDirectionHeuristicCompat == BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC) ? getDefaultInstanceFromContext(this.mIsRtlContext) : new BidiFormatter(this.mIsRtlContext, this.mFlags, this.mTextDirectionHeuristicCompat);
        }

        public Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat heuristic) {
            this.mTextDirectionHeuristicCompat = heuristic;
            return this;
        }

        public Builder stereoReset(boolean stereoReset) {
            if (stereoReset) {
                this.mFlags |= 2;
            } else {
                this.mFlags &= -3;
            }
            return this;
        }

        public Builder(boolean rtlContext) {
            this.mIsRtlContext = rtlContext;
            this.mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
            this.mFlags = 2;
        }

        public Builder(Locale locale) {
            initialize(BidiFormatter.isRtlLocale(locale));
        }
    }

    public boolean isRtl(CharSequence str) {
        return this.mDefaultTextDirectionHeuristicCompat.isRtl(str, 0, str.length());
    }

    public CharSequence unicodeWrap(CharSequence str, TextDirectionHeuristicCompat heuristic, boolean isolate) {
        if (str == null) {
            return null;
        }
        boolean zIsRtl = heuristic.isRtl(str, 0, str.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (getStereoReset() && isolate) {
            spannableStringBuilder.append((CharSequence) markBefore(str, zIsRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        if (zIsRtl != this.mIsRtlContext) {
            spannableStringBuilder.append(zIsRtl ? RLE : LRE);
            spannableStringBuilder.append(str);
            spannableStringBuilder.append(PDF);
        } else {
            spannableStringBuilder.append(str);
        }
        if (isolate) {
            spannableStringBuilder.append((CharSequence) markAfter(str, zIsRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        return spannableStringBuilder;
    }

    public static BidiFormatter getInstance() {
        return new Builder().build();
    }

    public static BidiFormatter getInstance(Locale locale) {
        return new Builder(locale).build();
    }

    public String unicodeWrap(String str, TextDirectionHeuristicCompat heuristic) {
        return unicodeWrap(str, heuristic, true);
    }

    public CharSequence unicodeWrap(CharSequence str, TextDirectionHeuristicCompat heuristic) {
        return unicodeWrap(str, heuristic, true);
    }

    public String unicodeWrap(String str, boolean isolate) {
        return unicodeWrap(str, this.mDefaultTextDirectionHeuristicCompat, isolate);
    }

    public CharSequence unicodeWrap(CharSequence str, boolean isolate) {
        return unicodeWrap(str, this.mDefaultTextDirectionHeuristicCompat, isolate);
    }

    public String unicodeWrap(String str) {
        return unicodeWrap(str, this.mDefaultTextDirectionHeuristicCompat, true);
    }

    public CharSequence unicodeWrap(CharSequence str) {
        return unicodeWrap(str, this.mDefaultTextDirectionHeuristicCompat, true);
    }
}
