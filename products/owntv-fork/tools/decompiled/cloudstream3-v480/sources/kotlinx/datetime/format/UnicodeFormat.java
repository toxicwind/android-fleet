package kotlinx.datetime.format;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.util.date.GMTDateParser;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: Unicode.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u0000 \u00022\u00020\u0001:\u0005\u0002\u0003\u0004\u0005\u0006\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat;", "", "Companion", "OptionalGroup", "Sequence", "StringLiteral", "Directive", "Lkotlinx/datetime/format/UnicodeFormat$Directive;", "Lkotlinx/datetime/format/UnicodeFormat$OptionalGroup;", "Lkotlinx/datetime/format/UnicodeFormat$Sequence;", "Lkotlinx/datetime/format/UnicodeFormat$StringLiteral;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface UnicodeFormat {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: Unicode.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Companion;", "", "<init>", "()V", "parse", "Lkotlinx/datetime/format/UnicodeFormat;", "pattern", "", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final UnicodeFormat parse(String pattern) {
            List list;
            List list2;
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            List listMutableListOf = CollectionsKt.mutableListOf(new ArrayList());
            int length = pattern.length();
            int i = 0;
            boolean z = false;
            String str = "";
            Character chValueOf = null;
            for (int i2 = 0; i2 < length; i2++) {
                char cCharAt = pattern.charAt(i2);
                if (chValueOf != null && cCharAt == chValueOf.charValue()) {
                    i++;
                } else if (!z) {
                    if (i > 0) {
                        List list3 = (List) CollectionsKt.last(listMutableListOf);
                        if (list3 != null) {
                            Intrinsics.checkNotNull(chValueOf);
                            list3.add(UnicodeKt.unicodeDirective(chValueOf.charValue(), i));
                        }
                        i = 0;
                        chValueOf = null;
                    }
                    if (UnicodeKt.nonPlainCharacters.contains(Character.valueOf(cCharAt))) {
                        if (!Intrinsics.areEqual(str, "")) {
                            List list4 = (List) CollectionsKt.last(listMutableListOf);
                            if (list4 != null) {
                                list4.add(new StringLiteral(str));
                            }
                            str = "";
                        }
                        if (cCharAt == '\'') {
                            z = true;
                            str = "";
                        } else if (cCharAt == '[') {
                            listMutableListOf.add(new ArrayList());
                        } else if (cCharAt == ']') {
                            List list5 = (List) CollectionsKt.removeLast(listMutableListOf);
                            if (list5 == null) {
                                throw new IllegalArgumentException("Unmatched closing bracket");
                            }
                            List list6 = (List) CollectionsKt.last(listMutableListOf);
                            if (list6 != null) {
                                list6.add(new OptionalGroup(new Sequence(list5)));
                            }
                        } else {
                            chValueOf = Character.valueOf(cCharAt);
                            i = 1;
                        }
                    } else {
                        str = str + cCharAt;
                    }
                } else if (cCharAt == '\'') {
                    List list7 = (List) CollectionsKt.last(listMutableListOf);
                    if (list7 != null) {
                        String str2 = str;
                        if (str2.length() == 0) {
                            str2 = "'";
                        }
                        list7.add(new StringLiteral(str2));
                    }
                    z = false;
                    str = "";
                } else {
                    str = str + cCharAt;
                }
            }
            if (i > 0 && (list2 = (List) CollectionsKt.last(listMutableListOf)) != null) {
                Intrinsics.checkNotNull(chValueOf);
                list2.add(UnicodeKt.unicodeDirective(chValueOf.charValue(), i));
            }
            if (!Intrinsics.areEqual(str, "") && (list = (List) CollectionsKt.last(listMutableListOf)) != null) {
                list.add(new StringLiteral(str));
            }
            List list8 = (List) CollectionsKt.removeLast(listMutableListOf);
            if (list8 != null) {
                return new Sequence(list8);
            }
            throw new IllegalArgumentException("Unmatched opening bracket");
        }
    }

    /* JADX INFO: compiled from: Unicode.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\t\u0010\t\u001a\u00020\u0001HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$OptionalGroup;", "Lkotlinx/datetime/format/UnicodeFormat;", "format", "<init>", "(Lkotlinx/datetime/format/UnicodeFormat;)V", "getFormat", "()Lkotlinx/datetime/format/UnicodeFormat;", "toString", "", "component1", "copy", "equals", "", "other", "", "hashCode", "", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final /* data */ class OptionalGroup implements UnicodeFormat {
        private final UnicodeFormat format;

        public static /* synthetic */ OptionalGroup copy$default(OptionalGroup optionalGroup, UnicodeFormat unicodeFormat, int i, Object obj) {
            if ((i & 1) != 0) {
                unicodeFormat = optionalGroup.format;
            }
            return optionalGroup.copy(unicodeFormat);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final UnicodeFormat getFormat() {
            return this.format;
        }

        public final OptionalGroup copy(UnicodeFormat format) {
            Intrinsics.checkNotNullParameter(format, "format");
            return new OptionalGroup(format);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof OptionalGroup) && Intrinsics.areEqual(this.format, ((OptionalGroup) other).format);
        }

        public int hashCode() {
            return this.format.hashCode();
        }

        public OptionalGroup(UnicodeFormat format) {
            Intrinsics.checkNotNullParameter(format, "format");
            this.format = format;
        }

        public final UnicodeFormat getFormat() {
            return this.format;
        }

        public String toString() {
            return "[" + this.format + AbstractJsonLexerKt.END_LIST;
        }
    }

    /* JADX INFO: compiled from: Unicode.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u0019\u0010\u000b\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Sequence;", "Lkotlinx/datetime/format/UnicodeFormat;", "formats", "", "<init>", "(Ljava/util/List;)V", "getFormats", "()Ljava/util/List;", "toString", "", "component1", "copy", "equals", "", "other", "", "hashCode", "", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final /* data */ class Sequence implements UnicodeFormat {
        private final List<UnicodeFormat> formats;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Sequence copy$default(Sequence sequence, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = sequence.formats;
            }
            return sequence.copy(list);
        }

        public final List<UnicodeFormat> component1() {
            return this.formats;
        }

        public final Sequence copy(List<? extends UnicodeFormat> formats) {
            Intrinsics.checkNotNullParameter(formats, "formats");
            return new Sequence(formats);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Sequence) && Intrinsics.areEqual(this.formats, ((Sequence) other).formats);
        }

        public int hashCode() {
            return this.formats.hashCode();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Sequence(List<? extends UnicodeFormat> formats) {
            Intrinsics.checkNotNullParameter(formats, "formats");
            this.formats = formats;
        }

        public final List<UnicodeFormat> getFormats() {
            return this.formats;
        }

        public String toString() {
            return CollectionsKt.joinToString$default(this.formats, "", null, null, 0, null, null, 62, null);
        }
    }

    /* JADX INFO: compiled from: Unicode.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\u0003H\u0016J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$StringLiteral;", "Lkotlinx/datetime/format/UnicodeFormat;", "literal", "", "<init>", "(Ljava/lang/String;)V", "getLiteral", "()Ljava/lang/String;", "toString", "component1", "copy", "equals", "", "other", "", "hashCode", "", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final /* data */ class StringLiteral implements UnicodeFormat {
        private final String literal;

        public static /* synthetic */ StringLiteral copy$default(StringLiteral stringLiteral, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = stringLiteral.literal;
            }
            return stringLiteral.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLiteral() {
            return this.literal;
        }

        public final StringLiteral copy(String literal) {
            Intrinsics.checkNotNullParameter(literal, "literal");
            return new StringLiteral(literal);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof StringLiteral) && Intrinsics.areEqual(this.literal, ((StringLiteral) other).literal);
        }

        public int hashCode() {
            return this.literal.hashCode();
        }

        public StringLiteral(String literal) {
            Intrinsics.checkNotNullParameter(literal, "literal");
            this.literal = literal;
        }

        public final String getLiteral() {
            return this.literal;
        }

        public String toString() {
            if (Intrinsics.areEqual(this.literal, "'")) {
                return "''";
            }
            String str = this.literal;
            for (int i = 0; i < str.length(); i++) {
                if (Character.isLetter(str.charAt(i))) {
                    return "'" + this.literal + '\'';
                }
            }
            String str2 = this.literal;
            if (str2.length() == 0) {
                str2 = "";
            }
            return str2;
        }
    }

    /* JADX INFO: compiled from: Unicode.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0013\u0014\u0015\u0016\u0017B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0016J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0005H\u0016R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0082\u0001\u0005\u0018\u0019\u001a\u001b\u001c¨\u0006\u001d"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive;", "Lkotlinx/datetime/format/UnicodeFormat;", "<init>", "()V", "formatLength", "", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "toString", "", "equals", "", "other", "", "hashCode", "YearMonthBased", "DateBased", "TimeBased", "ZoneBased", "OffsetBased", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$ZoneBased;", "Lkotlinx/datetime/format/UnknownUnicodeDirective;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class Directive implements UnicodeFormat {
        public /* synthetic */ Directive(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract int getFormatLength();

        public abstract char getFormatLetter();

        private Directive() {
        }

        public String toString() {
            return StringsKt.repeat(String.valueOf(getFormatLetter()), getFormatLength());
        }

        public boolean equals(Object other) {
            if (!(other instanceof Directive)) {
                return false;
            }
            Directive directive = (Directive) other;
            return getFormatLetter() == directive.getFormatLetter() && getFormatLength() == directive.getFormatLength();
        }

        public int hashCode() {
            return (getFormatLetter() * 31) + getFormatLength();
        }

        /* JADX INFO: compiled from: Unicode.kt */
        @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\t\t\n\u000b\f\r\u000e\u000f\u0010\u0011B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\bH\u0016\u0082\u0001\t\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a¨\u0006\u001b"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased;", "<init>", "()V", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithYearMonth;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "Era", "Year", "YearOfEra", "CyclicYearName", "RelatedGregorianYear", "MonthOfYear", "StandaloneMonthOfYear", "QuarterOfYear", "StandaloneQuarterOfYear", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$CyclicYearName;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$Era;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$MonthOfYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$QuarterOfYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$RelatedGregorianYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$StandaloneMonthOfYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$StandaloneQuarterOfYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$Year;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$YearOfEra;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static abstract class YearMonthBased extends DateBased {
            public /* synthetic */ YearMonthBased(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* JADX INFO: renamed from: addToFormat */
            public abstract void mo10574addToFormat(DateTimeFormatBuilder.WithYearMonth builder);

            private YearMonthBased() {
                super(null);
            }

            @Override // kotlinx.datetime.format.UnicodeFormat.Directive.DateBased
            /* JADX INFO: renamed from: addToFormat */
            public void mo10571addToFormat(DateTimeFormatBuilder.WithDate builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                mo10574addToFormat((DateTimeFormatBuilder.WithYearMonth) builder);
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$Era;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithYearMonth;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class Era extends YearMonthBased {
                private final int formatLength;
                private final char formatLetter;

                public Era(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'G';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.YearMonthBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10574addToFormat(DateTimeFormatBuilder.WithYearMonth builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.localizedDirective$default(this, null, 1, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$Year;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithYearMonth;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class Year extends YearMonthBased {
                private final int formatLength;
                private final char formatLetter;

                public Year(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = AbstractJsonLexerKt.UNICODE_ESC;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.YearMonthBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10574addToFormat(DateTimeFormatBuilder.WithYearMonth builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    int formatLength = getFormatLength();
                    if (formatLength == 1) {
                        builder.year(Padding.NONE);
                        return;
                    }
                    if (formatLength == 2) {
                        builder.yearTwoDigits(2000);
                        return;
                    }
                    if (formatLength == 3) {
                        UnicodeKt.unsupportedPadding(this, getFormatLength());
                        throw new KotlinNothingValueException();
                    }
                    if (formatLength != 4) {
                        UnicodeKt.unsupportedPadding(this, getFormatLength());
                        throw new KotlinNothingValueException();
                    }
                    builder.year(Padding.ZERO);
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$YearOfEra;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithYearMonth;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class YearOfEra extends YearMonthBased {
                private final int formatLength;
                private final char formatLetter;

                public YearOfEra(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'y';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.YearMonthBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10574addToFormat(DateTimeFormatBuilder.WithYearMonth builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    int formatLength = getFormatLength();
                    if (formatLength == 1) {
                        YearMonthFormatKt.yearOfEra(builder, Padding.NONE);
                        return;
                    }
                    if (formatLength == 2) {
                        YearMonthFormatKt.yearOfEraTwoDigits(builder, 2000);
                        return;
                    }
                    if (formatLength == 3) {
                        UnicodeKt.unsupportedPadding(this, getFormatLength());
                        throw new KotlinNothingValueException();
                    }
                    if (formatLength != 4) {
                        UnicodeKt.unsupportedPadding(this, getFormatLength());
                        throw new KotlinNothingValueException();
                    }
                    YearMonthFormatKt.yearOfEra(builder, Padding.ZERO);
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$CyclicYearName;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithYearMonth;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class CyclicYearName extends YearMonthBased {
                private final int formatLength;
                private final char formatLetter;

                public CyclicYearName(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'U';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.YearMonthBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10574addToFormat(DateTimeFormatBuilder.WithYearMonth builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.unsupportedDirective$default("cyclic-year", null, 2, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$RelatedGregorianYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithYearMonth;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class RelatedGregorianYear extends YearMonthBased {
                private final int formatLength;
                private final char formatLetter;

                public RelatedGregorianYear(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'r';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.YearMonthBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10574addToFormat(DateTimeFormatBuilder.WithYearMonth builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.unsupportedDirective$default("related-gregorian-year", null, 2, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$MonthOfYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithYearMonth;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class MonthOfYear extends YearMonthBased {
                private final int formatLength;
                private final char formatLetter;

                public MonthOfYear(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = GMTDateParser.MONTH;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.YearMonthBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10574addToFormat(DateTimeFormatBuilder.WithYearMonth builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    int formatLength = getFormatLength();
                    if (formatLength == 1) {
                        builder.monthNumber(Padding.NONE);
                        return;
                    }
                    if (formatLength == 2) {
                        builder.monthNumber(Padding.ZERO);
                    } else {
                        if (formatLength != 3 && formatLength != 4 && formatLength != 5) {
                            UnicodeKt.unknownLength(this);
                            throw new KotlinNothingValueException();
                        }
                        UnicodeKt.localizedDirective$default(this, null, 1, null);
                        throw new KotlinNothingValueException();
                    }
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$StandaloneMonthOfYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithYearMonth;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class StandaloneMonthOfYear extends YearMonthBased {
                private final int formatLength;
                private final char formatLetter;

                public StandaloneMonthOfYear(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'L';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.YearMonthBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10574addToFormat(DateTimeFormatBuilder.WithYearMonth builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    int formatLength = getFormatLength();
                    if (formatLength == 1) {
                        builder.monthNumber(Padding.NONE);
                        return;
                    }
                    if (formatLength == 2) {
                        builder.monthNumber(Padding.ZERO);
                    } else {
                        if (formatLength != 3 && formatLength != 4 && formatLength != 5) {
                            UnicodeKt.unknownLength(this);
                            throw new KotlinNothingValueException();
                        }
                        UnicodeKt.localizedDirective$default(this, null, 1, null);
                        throw new KotlinNothingValueException();
                    }
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$QuarterOfYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithYearMonth;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class QuarterOfYear extends YearMonthBased {
                private final int formatLength;
                private final char formatLetter;

                public QuarterOfYear(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'Q';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.YearMonthBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10574addToFormat(DateTimeFormatBuilder.WithYearMonth builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    int formatLength = getFormatLength();
                    if (formatLength == 1 || formatLength == 2) {
                        UnicodeKt.unsupportedDirective$default("quarter-of-year", null, 2, null);
                        throw new KotlinNothingValueException();
                    }
                    if (formatLength != 3 && formatLength != 4 && formatLength != 5) {
                        UnicodeKt.unknownLength(this);
                        throw new KotlinNothingValueException();
                    }
                    UnicodeKt.localizedDirective$default(this, null, 1, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased$StandaloneQuarterOfYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithYearMonth;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class StandaloneQuarterOfYear extends YearMonthBased {
                private final int formatLength;
                private final char formatLetter;

                public StandaloneQuarterOfYear(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'q';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.YearMonthBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10574addToFormat(DateTimeFormatBuilder.WithYearMonth builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    int formatLength = getFormatLength();
                    if (formatLength == 1 || formatLength == 2) {
                        UnicodeKt.unsupportedDirective$default("standalone-quarter-of-year", null, 2, null);
                        throw new KotlinNothingValueException();
                    }
                    if (formatLength != 3 && formatLength != 4 && formatLength != 5) {
                        UnicodeKt.unknownLength(this);
                        throw new KotlinNothingValueException();
                    }
                    UnicodeKt.localizedDirective$default(this, null, 1, null);
                    throw new KotlinNothingValueException();
                }
            }
        }

        /* JADX INFO: compiled from: Unicode.kt */
        @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\n\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u0082\u0001\u000b\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c¨\u0006\u001d"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased;", "Lkotlinx/datetime/format/UnicodeFormat$Directive;", "<init>", "()V", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "DayOfYear", "DayOfMonth", "ModifiedJulianDay", "WeekBasedYear", "WeekOfWeekBasedYear", "WeekOfMonth", "DayOfWeek", "LocalizedDayOfWeek", "StandaloneLocalizedDayOfWeek", "DayOfWeekInMonth", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$DayOfMonth;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$DayOfWeek;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$DayOfWeekInMonth;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$DayOfYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$LocalizedDayOfWeek;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$ModifiedJulianDay;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$StandaloneLocalizedDayOfWeek;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$WeekBasedYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$WeekOfMonth;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$WeekOfWeekBasedYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$YearMonthBased;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static abstract class DateBased extends Directive {
            public /* synthetic */ DateBased(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* JADX INFO: renamed from: addToFormat */
            public abstract void mo10571addToFormat(DateTimeFormatBuilder.WithDate builder);

            private DateBased() {
                super(null);
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$DayOfYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class DayOfYear extends DateBased {
                private final int formatLength;
                private final char formatLetter;

                public DayOfYear(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'D';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.DateBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10571addToFormat(DateTimeFormatBuilder.WithDate builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    int formatLength = getFormatLength();
                    if (formatLength == 1) {
                        builder.dayOfYear(Padding.NONE);
                    } else {
                        if (formatLength != 3) {
                            UnicodeKt.unknownLength(this);
                            throw new KotlinNothingValueException();
                        }
                        builder.dayOfYear(Padding.ZERO);
                    }
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$DayOfMonth;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class DayOfMonth extends DateBased {
                private final int formatLength;
                private final char formatLetter;

                public DayOfMonth(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = GMTDateParser.DAY_OF_MONTH;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.DateBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10571addToFormat(DateTimeFormatBuilder.WithDate builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    int formatLength = getFormatLength();
                    if (formatLength == 1) {
                        builder.day(Padding.NONE);
                    } else {
                        if (formatLength != 2) {
                            UnicodeKt.unknownLength(this);
                            throw new KotlinNothingValueException();
                        }
                        builder.day(Padding.ZERO);
                    }
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$ModifiedJulianDay;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class ModifiedJulianDay extends DateBased {
                private final int formatLength;
                private final char formatLetter;

                public ModifiedJulianDay(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'g';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.DateBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10571addToFormat(DateTimeFormatBuilder.WithDate builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.unsupportedDirective$default("modified-julian-day", null, 2, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$WeekBasedYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class WeekBasedYear extends DateBased {
                private final int formatLength;
                private final char formatLetter;

                public WeekBasedYear(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = GMTDateParser.YEAR;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.DateBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10571addToFormat(DateTimeFormatBuilder.WithDate builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.unsupportedDirective$default("week-based-year", null, 2, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$WeekOfWeekBasedYear;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class WeekOfWeekBasedYear extends DateBased {
                private final int formatLength;
                private final char formatLetter;

                public WeekOfWeekBasedYear(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'w';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.DateBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10571addToFormat(DateTimeFormatBuilder.WithDate builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.unsupportedDirective$default("week-of-week-based-year", null, 2, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$WeekOfMonth;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class WeekOfMonth extends DateBased {
                private final int formatLength;
                private final char formatLetter;

                public WeekOfMonth(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'W';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.DateBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10571addToFormat(DateTimeFormatBuilder.WithDate builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.unsupportedDirective$default("week-of-month", null, 2, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$DayOfWeek;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class DayOfWeek extends DateBased {
                private final int formatLength;
                private final char formatLetter;

                public DayOfWeek(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'E';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.DateBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10571addToFormat(DateTimeFormatBuilder.WithDate builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.localizedDirective$default(this, null, 1, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$LocalizedDayOfWeek;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class LocalizedDayOfWeek extends DateBased {
                private final int formatLength;
                private final char formatLetter;

                public LocalizedDayOfWeek(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'e';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.DateBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10571addToFormat(DateTimeFormatBuilder.WithDate builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.localizedDirective$default(this, null, 1, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$StandaloneLocalizedDayOfWeek;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class StandaloneLocalizedDayOfWeek extends DateBased {
                private final int formatLength;
                private final char formatLetter;

                public StandaloneLocalizedDayOfWeek(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'c';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.DateBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10571addToFormat(DateTimeFormatBuilder.WithDate builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.localizedDirective$default(this, null, 1, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased$DayOfWeekInMonth;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$DateBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class DayOfWeekInMonth extends DateBased {
                private final int formatLength;
                private final char formatLetter;

                public DayOfWeekInMonth(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'F';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.DateBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10571addToFormat(DateTimeFormatBuilder.WithDate builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.unsupportedDirective$default("day-of-week-in-month", null, 2, null);
                    throw new KotlinNothingValueException();
                }
            }
        }

        /* JADX INFO: compiled from: Unicode.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\b\t\n\u000b\f\rB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u0082\u0001\u0005\u000e\u000f\u0010\u0011\u0012¨\u0006\u0013"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased;", "Lkotlinx/datetime/format/UnicodeFormat$Directive;", "<init>", "()V", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithTime;", "AmPmMarker", "AmPmHourOfDay", "HourOfDay", "MinuteOfHour", "WithSecondPrecision", "WithSubsecondPrecision", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$AmPmHourOfDay;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$AmPmMarker;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$HourOfDay;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$MinuteOfHour;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSecondPrecision;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static abstract class TimeBased extends Directive {
            public /* synthetic */ TimeBased(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* JADX INFO: renamed from: addToFormat */
            public abstract void mo10573addToFormat(DateTimeFormatBuilder.WithTime builder);

            private TimeBased() {
                super(null);
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$AmPmMarker;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithTime;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class AmPmMarker extends TimeBased {
                private final int formatLength;
                private final char formatLetter;

                public AmPmMarker(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'a';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.TimeBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10573addToFormat(DateTimeFormatBuilder.WithTime builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.localizedDirective$default(this, null, 1, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$AmPmHourOfDay;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithTime;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class AmPmHourOfDay extends TimeBased {
                private final int formatLength;
                private final char formatLetter;

                public AmPmHourOfDay(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = GMTDateParser.HOURS;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.TimeBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10573addToFormat(DateTimeFormatBuilder.WithTime builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.localizedDirective$default(this, null, 1, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$HourOfDay;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithTime;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class HourOfDay extends TimeBased {
                private final int formatLength;
                private final char formatLetter;

                public HourOfDay(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'H';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.TimeBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10573addToFormat(DateTimeFormatBuilder.WithTime builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    int formatLength = getFormatLength();
                    if (formatLength == 1) {
                        builder.hour(Padding.NONE);
                    } else {
                        if (formatLength != 2) {
                            UnicodeKt.unknownLength(this);
                            throw new KotlinNothingValueException();
                        }
                        builder.hour(Padding.ZERO);
                    }
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$MinuteOfHour;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithTime;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class MinuteOfHour extends TimeBased {
                private final int formatLength;
                private final char formatLetter;

                public MinuteOfHour(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = GMTDateParser.MINUTES;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.TimeBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10573addToFormat(DateTimeFormatBuilder.WithTime builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    int formatLength = getFormatLength();
                    if (formatLength == 1) {
                        builder.minute(Padding.NONE);
                    } else {
                        if (formatLength != 2) {
                            UnicodeKt.unknownLength(this);
                            throw new KotlinNothingValueException();
                        }
                        builder.minute(Padding.ZERO);
                    }
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSecondPrecision;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased;", "<init>", "()V", "SecondOfMinute", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSecondPrecision$SecondOfMinute;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static abstract class WithSecondPrecision extends TimeBased {
                public /* synthetic */ WithSecondPrecision(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                /* JADX INFO: compiled from: Unicode.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSecondPrecision$SecondOfMinute;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSecondPrecision;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithTime;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
                public static final class SecondOfMinute extends WithSecondPrecision {
                    private final int formatLength;
                    private final char formatLetter;

                    public SecondOfMinute(int i) {
                        super(null);
                        this.formatLength = i;
                        this.formatLetter = GMTDateParser.SECONDS;
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                    public int getFormatLength() {
                        return this.formatLength;
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                    public char getFormatLetter() {
                        return this.formatLetter;
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive.TimeBased
                    /* JADX INFO: renamed from: addToFormat */
                    public void mo10573addToFormat(DateTimeFormatBuilder.WithTime builder) {
                        Intrinsics.checkNotNullParameter(builder, "builder");
                        int formatLength = getFormatLength();
                        if (formatLength == 1) {
                            builder.second(Padding.NONE);
                        } else {
                            if (formatLength != 2) {
                                UnicodeKt.unknownLength(this);
                                throw new KotlinNothingValueException();
                            }
                            builder.second(Padding.ZERO);
                        }
                    }
                }

                private WithSecondPrecision() {
                    super(null);
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSecondPrecision;", "<init>", "()V", "FractionOfSecond", "MilliOfDay", "NanoOfSecond", "NanoOfDay", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision$FractionOfSecond;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision$MilliOfDay;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision$NanoOfDay;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision$NanoOfSecond;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static abstract class WithSubsecondPrecision extends WithSecondPrecision {
                public /* synthetic */ WithSubsecondPrecision(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                /* JADX INFO: compiled from: Unicode.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision$FractionOfSecond;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithTime;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
                public static final class FractionOfSecond extends WithSubsecondPrecision {
                    private final int formatLength;
                    private final char formatLetter;

                    public FractionOfSecond(int i) {
                        super(null);
                        this.formatLength = i;
                        this.formatLetter = 'S';
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                    public int getFormatLength() {
                        return this.formatLength;
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                    public char getFormatLetter() {
                        return this.formatLetter;
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive.TimeBased
                    /* JADX INFO: renamed from: addToFormat */
                    public void mo10573addToFormat(DateTimeFormatBuilder.WithTime builder) {
                        Intrinsics.checkNotNullParameter(builder, "builder");
                        builder.secondFraction(getFormatLength());
                    }
                }

                private WithSubsecondPrecision() {
                    super(null);
                }

                /* JADX INFO: compiled from: Unicode.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision$MilliOfDay;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithTime;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
                public static final class MilliOfDay extends WithSubsecondPrecision {
                    private final int formatLength;
                    private final char formatLetter;

                    public MilliOfDay(int i) {
                        super(null);
                        this.formatLength = i;
                        this.formatLetter = 'A';
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                    public int getFormatLength() {
                        return this.formatLength;
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                    public char getFormatLetter() {
                        return this.formatLetter;
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive.TimeBased
                    /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                    public Void mo10573addToFormat(DateTimeFormatBuilder.WithTime builder) {
                        Intrinsics.checkNotNullParameter(builder, "builder");
                        UnicodeKt.unsupportedDirective$default("millisecond-of-day", null, 2, null);
                        throw new KotlinNothingValueException();
                    }
                }

                /* JADX INFO: compiled from: Unicode.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision$NanoOfSecond;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithTime;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
                public static final class NanoOfSecond extends WithSubsecondPrecision {
                    private final int formatLength;
                    private final char formatLetter;

                    public NanoOfSecond(int i) {
                        super(null);
                        this.formatLength = i;
                        this.formatLetter = 'n';
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                    public int getFormatLength() {
                        return this.formatLength;
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                    public char getFormatLetter() {
                        return this.formatLetter;
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive.TimeBased
                    /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                    public Void mo10573addToFormat(DateTimeFormatBuilder.WithTime builder) {
                        Intrinsics.checkNotNullParameter(builder, "builder");
                        UnicodeKt.unsupportedDirective("nano-of-second", "Maybe you meant 'S' instead of 'n'?");
                        throw new KotlinNothingValueException();
                    }
                }

                /* JADX INFO: compiled from: Unicode.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision$NanoOfDay;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$TimeBased$WithSubsecondPrecision;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithTime;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
                public static final class NanoOfDay extends WithSubsecondPrecision {
                    private final int formatLength;
                    private final char formatLetter;

                    public NanoOfDay(int i) {
                        super(null);
                        this.formatLength = i;
                        this.formatLetter = 'N';
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                    public int getFormatLength() {
                        return this.formatLength;
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                    public char getFormatLetter() {
                        return this.formatLetter;
                    }

                    @Override // kotlinx.datetime.format.UnicodeFormat.Directive.TimeBased
                    /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                    public Void mo10573addToFormat(DateTimeFormatBuilder.WithTime builder) {
                        Intrinsics.checkNotNullParameter(builder, "builder");
                        UnicodeKt.unsupportedDirective$default("nanosecond-of-day", null, 2, null);
                        throw new KotlinNothingValueException();
                    }
                }
            }
        }

        /* JADX INFO: compiled from: Unicode.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\b\t\nB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u0082\u0001\u0003\u000b\f\r¨\u0006\u000e"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$ZoneBased;", "Lkotlinx/datetime/format/UnicodeFormat$Directive;", "<init>", "()V", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDateTimeComponents;", "TimeZoneId", "GenericTimeZoneName", "TimeZoneName", "Lkotlinx/datetime/format/UnicodeFormat$Directive$ZoneBased$GenericTimeZoneName;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$ZoneBased$TimeZoneId;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$ZoneBased$TimeZoneName;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static abstract class ZoneBased extends Directive {
            public /* synthetic */ ZoneBased(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* JADX INFO: renamed from: addToFormat */
            public abstract void mo10575addToFormat(DateTimeFormatBuilder.WithDateTimeComponents builder);

            private ZoneBased() {
                super(null);
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$ZoneBased$TimeZoneId;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$ZoneBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDateTimeComponents;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class TimeZoneId extends ZoneBased {
                private final int formatLength;
                private final char formatLetter;

                public TimeZoneId(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'V';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.ZoneBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10575addToFormat(DateTimeFormatBuilder.WithDateTimeComponents builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    if (getFormatLength() != 2) {
                        UnicodeKt.unknownLength(this);
                        throw new KotlinNothingValueException();
                    }
                    builder.timeZoneId();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$ZoneBased$GenericTimeZoneName;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$ZoneBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDateTimeComponents;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class GenericTimeZoneName extends ZoneBased {
                private final int formatLength;
                private final char formatLetter;

                public GenericTimeZoneName(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'v';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.ZoneBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10575addToFormat(DateTimeFormatBuilder.WithDateTimeComponents builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.localizedDirective$default(this, null, 1, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$ZoneBased$TimeZoneName;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$ZoneBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDateTimeComponents;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class TimeZoneName extends ZoneBased {
                private final int formatLength;
                private final char formatLetter;

                public TimeZoneName(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = GMTDateParser.ZONE;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.ZoneBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10575addToFormat(DateTimeFormatBuilder.WithDateTimeComponents builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.localizedDirective(this, "Format 'V' can be used to format time zone IDs in a locale-invariant manner.");
                    throw new KotlinNothingValueException();
                }
            }
        }

        /* JADX INFO: compiled from: Unicode.kt */
        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u000f\u0010\u0011\u0012B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\tH&J\u001a\u0010\u000b\u001a\u00020\u0005*\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r\u0082\u0001\u0004\u0013\u0014\u0015\u0016¨\u0006\u0017"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased;", "Lkotlinx/datetime/format/UnicodeFormat$Directive;", "<init>", "()V", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithUtcOffset;", "outputMinutes", "Lkotlinx/datetime/format/WhenToOutput;", "outputSeconds", TypedValues.CycleType.S_WAVE_OFFSET, "zOnZero", "", "useSeparator", "LocalizedZoneOffset", "ZoneOffset1", "ZoneOffset2", "ZoneOffset3", "Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased$LocalizedZoneOffset;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased$ZoneOffset1;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased$ZoneOffset2;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased$ZoneOffset3;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static abstract class OffsetBased extends Directive {
            public /* synthetic */ OffsetBased(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* JADX INFO: renamed from: addToFormat */
            public abstract void mo10572addToFormat(DateTimeFormatBuilder.WithUtcOffset builder);

            public abstract WhenToOutput outputMinutes();

            public abstract WhenToOutput outputSeconds();

            private OffsetBased() {
                super(null);
            }

            public final void offset(DateTimeFormatBuilder.WithUtcOffset withUtcOffset, boolean z, boolean z2) {
                Intrinsics.checkNotNullParameter(withUtcOffset, "<this>");
                UtcOffsetFormatKt.isoOffset(withUtcOffset, z, z2, outputMinutes(), outputSeconds());
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased$LocalizedZoneOffset;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithUtcOffset;", "outputMinutes", "Lkotlinx/datetime/format/WhenToOutput;", "outputSeconds", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class LocalizedZoneOffset extends OffsetBased {
                private final int formatLength;
                private final char formatLetter;

                public LocalizedZoneOffset(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'O';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.OffsetBased
                /* JADX INFO: renamed from: addToFormat, reason: merged with bridge method [inline-methods] */
                public Void mo10572addToFormat(DateTimeFormatBuilder.WithUtcOffset builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    UnicodeKt.localizedDirective$default(this, null, 1, null);
                    throw new KotlinNothingValueException();
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.OffsetBased
                public WhenToOutput outputMinutes() {
                    UnicodeKt.localizedDirective$default(this, null, 1, null);
                    throw new KotlinNothingValueException();
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.OffsetBased
                public WhenToOutput outputSeconds() {
                    UnicodeKt.localizedDirective$default(this, null, 1, null);
                    throw new KotlinNothingValueException();
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased$ZoneOffset1;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithUtcOffset;", "outputMinutes", "Lkotlinx/datetime/format/WhenToOutput;", "outputSeconds", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class ZoneOffset1 extends OffsetBased {
                private final int formatLength;
                private final char formatLetter;

                public ZoneOffset1(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'X';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.OffsetBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10572addToFormat(DateTimeFormatBuilder.WithUtcOffset builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    int formatLength = getFormatLength();
                    if (formatLength == 1) {
                        offset(builder, true, false);
                        return;
                    }
                    if (formatLength == 2) {
                        offset(builder, true, false);
                        return;
                    }
                    if (formatLength == 3) {
                        offset(builder, true, true);
                        return;
                    }
                    if (formatLength == 4) {
                        offset(builder, true, false);
                    } else {
                        if (formatLength != 5) {
                            UnicodeKt.unknownLength(this);
                            throw new KotlinNothingValueException();
                        }
                        offset(builder, true, true);
                    }
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.OffsetBased
                public WhenToOutput outputMinutes() {
                    return getFormatLength() == 1 ? WhenToOutput.IF_NONZERO : WhenToOutput.ALWAYS;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.OffsetBased
                public WhenToOutput outputSeconds() {
                    return getFormatLength() <= 3 ? WhenToOutput.NEVER : WhenToOutput.IF_NONZERO;
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased$ZoneOffset2;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithUtcOffset;", "outputMinutes", "Lkotlinx/datetime/format/WhenToOutput;", "outputSeconds", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class ZoneOffset2 extends OffsetBased {
                private final int formatLength;
                private final char formatLetter;

                public ZoneOffset2(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'x';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.OffsetBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10572addToFormat(DateTimeFormatBuilder.WithUtcOffset builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    int formatLength = getFormatLength();
                    if (formatLength == 1) {
                        offset(builder, false, false);
                        return;
                    }
                    if (formatLength == 2) {
                        offset(builder, false, false);
                        return;
                    }
                    if (formatLength == 3) {
                        offset(builder, false, true);
                        return;
                    }
                    if (formatLength == 4) {
                        offset(builder, false, false);
                    } else {
                        if (formatLength != 5) {
                            UnicodeKt.unknownLength(this);
                            throw new KotlinNothingValueException();
                        }
                        offset(builder, false, true);
                    }
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.OffsetBased
                public WhenToOutput outputMinutes() {
                    return getFormatLength() == 1 ? WhenToOutput.IF_NONZERO : WhenToOutput.ALWAYS;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.OffsetBased
                public WhenToOutput outputSeconds() {
                    return getFormatLength() <= 3 ? WhenToOutput.NEVER : WhenToOutput.IF_NONZERO;
                }
            }

            /* JADX INFO: compiled from: Unicode.kt */
            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased$ZoneOffset3;", "Lkotlinx/datetime/format/UnicodeFormat$Directive$OffsetBased;", "formatLength", "", "<init>", "(I)V", "getFormatLength", "()I", "formatLetter", "", "getFormatLetter", "()C", "addToFormat", "", "builder", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithUtcOffset;", "outputMinutes", "Lkotlinx/datetime/format/WhenToOutput;", "outputSeconds", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class ZoneOffset3 extends OffsetBased {
                private final int formatLength;
                private final char formatLetter;

                public ZoneOffset3(int i) {
                    super(null);
                    this.formatLength = i;
                    this.formatLetter = 'Z';
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public int getFormatLength() {
                    return this.formatLength;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive
                public char getFormatLetter() {
                    return this.formatLetter;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.OffsetBased
                /* JADX INFO: renamed from: addToFormat */
                public void mo10572addToFormat(DateTimeFormatBuilder.WithUtcOffset builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    int formatLength = getFormatLength();
                    if (formatLength == 1 || formatLength == 2 || formatLength == 3) {
                        offset(builder, false, false);
                        return;
                    }
                    if (formatLength == 4) {
                        new LocalizedZoneOffset(4).mo10572addToFormat(builder);
                        throw new KotlinNothingValueException();
                    }
                    if (formatLength != 5) {
                        UnicodeKt.unknownLength(this);
                        throw new KotlinNothingValueException();
                    }
                    offset(builder, false, true);
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.OffsetBased
                public WhenToOutput outputMinutes() {
                    return WhenToOutput.ALWAYS;
                }

                @Override // kotlinx.datetime.format.UnicodeFormat.Directive.OffsetBased
                public WhenToOutput outputSeconds() {
                    return getFormatLength() <= 3 ? WhenToOutput.NEVER : WhenToOutput.IF_NONZERO;
                }
            }
        }
    }
}
