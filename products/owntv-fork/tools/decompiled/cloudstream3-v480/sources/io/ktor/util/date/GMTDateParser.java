package io.ktor.util.date;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GMTDateParser.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ#\u0010\u000f\u001a\u00020\u000e*\u00020\n2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0011¨\u0006\u0013"}, d2 = {"Lio/ktor/util/date/GMTDateParser;", "", "", "pattern", "<init>", "(Ljava/lang/String;)V", "dateString", "Lio/ktor/util/date/GMTDate;", "parse", "(Ljava/lang/String;)Lio/ktor/util/date/GMTDate;", "Lio/ktor/util/date/GMTDateBuilder;", "", "type", "chunk", "", "handleToken", "(Lio/ktor/util/date/GMTDateBuilder;CLjava/lang/String;)V", "Ljava/lang/String;", "Companion", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class GMTDateParser {
    public static final char ANY = '*';
    public static final char DAY_OF_MONTH = 'd';
    public static final char HOURS = 'h';
    public static final char MINUTES = 'm';
    public static final char MONTH = 'M';
    public static final char SECONDS = 's';
    public static final char YEAR = 'Y';
    public static final char ZONE = 'z';
    private final String pattern;

    public GMTDateParser(String pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        this.pattern = pattern;
        if (pattern.length() <= 0) {
            throw new IllegalStateException("Date parser pattern shouldn't be empty.".toString());
        }
    }

    public final GMTDate parse(String dateString) {
        Intrinsics.checkNotNullParameter(dateString, "dateString");
        GMTDateBuilder gMTDateBuilder = new GMTDateBuilder();
        char cCharAt = this.pattern.charAt(0);
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        while (i2 < this.pattern.length()) {
            try {
                if (this.pattern.charAt(i2) == cCharAt) {
                    i2++;
                } else {
                    int i4 = (i3 + i2) - i;
                    String strSubstring = dateString.substring(i3, i4);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    handleToken(gMTDateBuilder, cCharAt, strSubstring);
                    try {
                        cCharAt = this.pattern.charAt(i2);
                        i = i2;
                        i2++;
                        i3 = i4;
                    } catch (Throwable unused) {
                        i3 = i4;
                        throw new InvalidDateStringException(dateString, i3, this.pattern);
                    }
                }
            } catch (Throwable unused2) {
            }
        }
        if (i3 < dateString.length()) {
            String strSubstring2 = dateString.substring(i3);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            handleToken(gMTDateBuilder, cCharAt, strSubstring2);
        }
        return gMTDateBuilder.build();
    }

    private final void handleToken(GMTDateBuilder gMTDateBuilder, char c, String str) {
        if (c != '*') {
            if (c == 'M') {
                gMTDateBuilder.setMonth(Month.INSTANCE.from(str));
                return;
            }
            if (c == 'Y') {
                gMTDateBuilder.setYear(Integer.valueOf(Integer.parseInt(str)));
                return;
            }
            if (c == 'd') {
                gMTDateBuilder.setDayOfMonth(Integer.valueOf(Integer.parseInt(str)));
                return;
            }
            if (c == 'h') {
                gMTDateBuilder.setHours(Integer.valueOf(Integer.parseInt(str)));
                return;
            }
            if (c == 'm') {
                gMTDateBuilder.setMinutes(Integer.valueOf(Integer.parseInt(str)));
                return;
            }
            if (c == 's') {
                gMTDateBuilder.setSeconds(Integer.valueOf(Integer.parseInt(str)));
                return;
            }
            if (c == 'z') {
                if (!Intrinsics.areEqual(str, "GMT")) {
                    throw new IllegalStateException("Check failed.");
                }
                return;
            }
            String str2 = str;
            for (int i = 0; i < str2.length(); i++) {
                if (str2.charAt(i) != c) {
                    throw new IllegalStateException("Check failed.");
                }
            }
        }
    }
}
