package io.ktor.http;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import io.ktor.util.date.GMTDate;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: CookieUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\n\u001a\u00020\t\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0004\b\n\u0010\u000bJ-\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lio/ktor/http/CookieDateParser;", "", "<init>", "()V", ExifInterface.GPS_DIRECTION_TRUE, "", "source", "name", "field", "", "checkFieldNotNull", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "", "requirement", "Lkotlin/Function0;", NotificationCompat.CATEGORY_MESSAGE, "checkRequirement", "(Ljava/lang/String;ZLkotlin/jvm/functions/Function0;)V", "Lio/ktor/util/date/GMTDate;", "parse", "(Ljava/lang/String;)Lio/ktor/util/date/GMTDate;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CookieDateParser {
    private final <T> void checkFieldNotNull(String source, String name, T field) {
        if (field != null) {
            return;
        }
        throw new InvalidCookieDateException(source, "Could not find " + name);
    }

    private final void checkRequirement(String source, boolean requirement, Function0<String> msg) {
        if (!requirement) {
            throw new InvalidCookieDateException(source, msg.invoke());
        }
    }

    public final GMTDate parse(String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        StringLexer stringLexer = new StringLexer(source);
        CookieDateBuilder cookieDateBuilder = new CookieDateBuilder();
        stringLexer.acceptWhile(new Function1() { // from class: io.ktor.http.CookieDateParser$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CookieUtilsKt.isDelimiter(((Character) obj).charValue()));
            }
        });
        while (stringLexer.getHasRemaining()) {
            if (stringLexer.test(new Function1() { // from class: io.ktor.http.CookieDateParser$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(CookieUtilsKt.isNonDelimiter(((Character) obj).charValue()));
                }
            })) {
                int index = stringLexer.getIndex();
                stringLexer.acceptWhile(new Function1() { // from class: io.ktor.http.CookieDateParser$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(CookieUtilsKt.isNonDelimiter(((Character) obj).charValue()));
                    }
                });
                String strSubstring = stringLexer.getSource().substring(index, stringLexer.getIndex());
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                CookieUtilsKt.handleToken(cookieDateBuilder, strSubstring);
                stringLexer.acceptWhile(new Function1() { // from class: io.ktor.http.CookieDateParser$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(CookieUtilsKt.isDelimiter(((Character) obj).charValue()));
                    }
                });
            }
        }
        Integer year = cookieDateBuilder.getYear();
        IntRange intRange = new IntRange(70, 99);
        if (year == null || !intRange.contains(year.intValue())) {
            IntRange intRange2 = new IntRange(0, 69);
            if (year != null && intRange2.contains(year.intValue())) {
                Integer year2 = cookieDateBuilder.getYear();
                Intrinsics.checkNotNull(year2);
                cookieDateBuilder.setYear(Integer.valueOf(year2.intValue() + 2000));
            }
        } else {
            Integer year3 = cookieDateBuilder.getYear();
            Intrinsics.checkNotNull(year3);
            cookieDateBuilder.setYear(Integer.valueOf(year3.intValue() + 1900));
        }
        checkFieldNotNull(source, "day-of-month", cookieDateBuilder.getDayOfMonth());
        checkFieldNotNull(source, "month", cookieDateBuilder.getMonth());
        checkFieldNotNull(source, "year", cookieDateBuilder.getYear());
        checkFieldNotNull(source, "time", cookieDateBuilder.getHours());
        checkFieldNotNull(source, "time", cookieDateBuilder.getMinutes());
        checkFieldNotNull(source, "time", cookieDateBuilder.getSeconds());
        IntRange intRange3 = new IntRange(1, 31);
        Integer dayOfMonth = cookieDateBuilder.getDayOfMonth();
        checkRequirement(source, dayOfMonth != null && intRange3.contains(dayOfMonth.intValue()), new Function0() { // from class: io.ktor.http.CookieDateParser$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CookieDateParser.parse$lambda$4();
            }
        });
        Integer year4 = cookieDateBuilder.getYear();
        Intrinsics.checkNotNull(year4);
        checkRequirement(source, year4.intValue() >= 1601, new Function0() { // from class: io.ktor.http.CookieDateParser$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CookieDateParser.parse$lambda$5();
            }
        });
        Integer hours = cookieDateBuilder.getHours();
        Intrinsics.checkNotNull(hours);
        checkRequirement(source, hours.intValue() <= 23, new Function0() { // from class: io.ktor.http.CookieDateParser$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CookieDateParser.parse$lambda$6();
            }
        });
        Integer minutes = cookieDateBuilder.getMinutes();
        Intrinsics.checkNotNull(minutes);
        checkRequirement(source, minutes.intValue() <= 59, new Function0() { // from class: io.ktor.http.CookieDateParser$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CookieDateParser.parse$lambda$7();
            }
        });
        Integer seconds = cookieDateBuilder.getSeconds();
        Intrinsics.checkNotNull(seconds);
        checkRequirement(source, seconds.intValue() <= 59, new Function0() { // from class: io.ktor.http.CookieDateParser$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CookieDateParser.parse$lambda$8();
            }
        });
        return cookieDateBuilder.build();
    }

    static final String parse$lambda$4() {
        return "day-of-month not in [1,31]";
    }

    static final String parse$lambda$5() {
        return "year >= 1601";
    }

    static final String parse$lambda$6() {
        return "hours > 23";
    }

    static final String parse$lambda$7() {
        return "minutes > 59";
    }

    static final String parse$lambda$8() {
        return "seconds > 59";
    }
}
