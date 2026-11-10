package com.google.common.base;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.io.Serializable;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'LOWER_UNDERSCORE' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class CaseFormat {
    public static final CaseFormat LOWER_CAMEL;
    public static final CaseFormat LOWER_UNDERSCORE;
    public static final CaseFormat UPPER_CAMEL;
    public static final CaseFormat UPPER_UNDERSCORE;
    public final CharMatcher wordBoundary;
    public final String wordSeparator;
    public static final CaseFormat LOWER_HYPHEN = new CaseFormat("LOWER_HYPHEN", 0, new CharMatcher.Is('-'), MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING) { // from class: com.google.common.base.CaseFormat.1
        {
            AnonymousClass1 anonymousClass1 = null;
        }

        @Override // com.google.common.base.CaseFormat
        public String convert(CaseFormat caseFormat, String str) {
            if (caseFormat == CaseFormat.LOWER_UNDERSCORE) {
                return str.replace('-', '_');
            }
            return caseFormat == CaseFormat.UPPER_UNDERSCORE ? Ascii.toUpperCase(str.replace('-', '_')) : super.convert(caseFormat, str);
        }

        @Override // com.google.common.base.CaseFormat
        public String normalizeWord(String str) {
            return Ascii.toLowerCase(str);
        }
    };
    public static final /* synthetic */ CaseFormat[] $VALUES = $values();

    private static final class StringConverter extends Converter<String, String> implements Serializable {
        public static final long serialVersionUID = 0;
        public final CaseFormat sourceFormat;
        public final CaseFormat targetFormat;

        public StringConverter(CaseFormat caseFormat, CaseFormat caseFormat2) {
            if (caseFormat == null) {
                throw new NullPointerException();
            }
            this.sourceFormat = caseFormat;
            if (caseFormat2 == null) {
                throw new NullPointerException();
            }
            this.targetFormat = caseFormat2;
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(Object obj) {
            if (!(obj instanceof StringConverter)) {
                return false;
            }
            StringConverter stringConverter = (StringConverter) obj;
            return this.sourceFormat.equals(stringConverter.sourceFormat) && this.targetFormat.equals(stringConverter.targetFormat);
        }

        public int hashCode() {
            return this.sourceFormat.hashCode() ^ this.targetFormat.hashCode();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.sourceFormat);
            String strValueOf2 = String.valueOf(this.targetFormat);
            return a.b(strValueOf2.length() + strValueOf.length() + 14, strValueOf, ".converterTo(", strValueOf2, ")");
        }

        @Override // com.google.common.base.Converter
        public String doBackward(String str) {
            return this.targetFormat.to(this.sourceFormat, str);
        }

        @Override // com.google.common.base.Converter
        public String doForward(String str) {
            return this.sourceFormat.to(this.targetFormat, str);
        }
    }

    public static /* synthetic */ CaseFormat[] $values() {
        return new CaseFormat[]{LOWER_HYPHEN, LOWER_UNDERSCORE, LOWER_CAMEL, UPPER_CAMEL, UPPER_UNDERSCORE};
    }

    static {
        String str = "_";
        LOWER_UNDERSCORE = new CaseFormat("LOWER_UNDERSCORE", 1, new CharMatcher.Is('_'), str) { // from class: com.google.common.base.CaseFormat.2
            {
                AnonymousClass1 anonymousClass1 = null;
            }

            @Override // com.google.common.base.CaseFormat
            public String convert(CaseFormat caseFormat, String str2) {
                if (caseFormat == CaseFormat.LOWER_HYPHEN) {
                    return str2.replace('_', '-');
                }
                return caseFormat == CaseFormat.UPPER_UNDERSCORE ? Ascii.toUpperCase(str2) : super.convert(caseFormat, str2);
            }

            @Override // com.google.common.base.CaseFormat
            public String normalizeWord(String str2) {
                return Ascii.toLowerCase(str2);
            }
        };
        String str2 = "";
        LOWER_CAMEL = new CaseFormat("LOWER_CAMEL", 2, new CharMatcher.InRange('A', 'Z'), str2) { // from class: com.google.common.base.CaseFormat.3
            {
                AnonymousClass1 anonymousClass1 = null;
            }

            @Override // com.google.common.base.CaseFormat
            public String normalizeFirstWord(String str3) {
                return Ascii.toLowerCase(str3);
            }

            @Override // com.google.common.base.CaseFormat
            public String normalizeWord(String str3) {
                return CaseFormat.firstCharOnlyToUpper(str3);
            }
        };
        UPPER_CAMEL = new CaseFormat("UPPER_CAMEL", 3, new CharMatcher.InRange('A', 'Z'), str2) { // from class: com.google.common.base.CaseFormat.4
            {
                AnonymousClass1 anonymousClass1 = null;
            }

            @Override // com.google.common.base.CaseFormat
            public String normalizeWord(String str3) {
                return CaseFormat.firstCharOnlyToUpper(str3);
            }
        };
        UPPER_UNDERSCORE = new CaseFormat("UPPER_UNDERSCORE", 4, new CharMatcher.Is('_'), str) { // from class: com.google.common.base.CaseFormat.5
            {
                AnonymousClass1 anonymousClass1 = null;
            }

            @Override // com.google.common.base.CaseFormat
            public String convert(CaseFormat caseFormat, String str3) {
                if (caseFormat == CaseFormat.LOWER_HYPHEN) {
                    return Ascii.toLowerCase(str3.replace('_', '-'));
                }
                return caseFormat == CaseFormat.LOWER_UNDERSCORE ? Ascii.toLowerCase(str3) : super.convert(caseFormat, str3);
            }

            @Override // com.google.common.base.CaseFormat
            public String normalizeWord(String str3) {
                return Ascii.toUpperCase(str3);
            }
        };
    }

    public CaseFormat(String str, int i, CharMatcher charMatcher, String str2) {
        super(str, i);
        this.wordBoundary = charMatcher;
        this.wordSeparator = str2;
    }

    public static String firstCharOnlyToUpper(String str) {
        if (str.isEmpty()) {
            return str;
        }
        char upperCase = Ascii.toUpperCase(str.charAt(0));
        String lowerCase = Ascii.toLowerCase(str.substring(1));
        StringBuilder sb = new StringBuilder(a.a((Object) lowerCase, 1));
        sb.append(upperCase);
        sb.append(lowerCase);
        return sb.toString();
    }

    public static CaseFormat valueOf(String str) {
        return (CaseFormat) Enum.valueOf(CaseFormat.class, str);
    }

    public static CaseFormat[] values() {
        return (CaseFormat[]) $VALUES.clone();
    }

    public String convert(CaseFormat caseFormat, String str) {
        int length = 0;
        StringBuilder sb = null;
        int iIndexIn = -1;
        while (true) {
            iIndexIn = this.wordBoundary.indexIn(str, iIndexIn + 1);
            if (iIndexIn == -1) {
                break;
            }
            if (length == 0) {
                sb = new StringBuilder((caseFormat.wordSeparator.length() * 4) + str.length());
                sb.append(caseFormat.normalizeFirstWord(str.substring(length, iIndexIn)));
            } else {
                sb.append(caseFormat.normalizeWord(str.substring(length, iIndexIn)));
            }
            sb.append(caseFormat.wordSeparator);
            length = this.wordSeparator.length() + iIndexIn;
        }
        if (length == 0) {
            return caseFormat.normalizeFirstWord(str);
        }
        sb.append(caseFormat.normalizeWord(str.substring(length)));
        return sb.toString();
    }

    public Converter<String, String> converterTo(CaseFormat caseFormat) {
        return new StringConverter(this, caseFormat);
    }

    public String normalizeFirstWord(String str) {
        return normalizeWord(str);
    }

    public abstract String normalizeWord(String str);

    public final String to(CaseFormat caseFormat, String str) {
        if (caseFormat == null) {
            throw new NullPointerException();
        }
        if (str != null) {
            return caseFormat == this ? str : convert(caseFormat, str);
        }
        throw new NullPointerException();
    }

    public /* synthetic */ CaseFormat(String str, int i, CharMatcher charMatcher, String str2, AnonymousClass1 anonymousClass1) {
        super(str, i);
        this.wordBoundary = charMatcher;
        this.wordSeparator = str2;
    }
}
