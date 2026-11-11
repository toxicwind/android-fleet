package io.ktor.http;

import io.ktor.util.date.Month;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CookieUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0013\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0004\u0010\u0003\u001a\u0013\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0005\u0010\u0003\u001a\u0013\u0010\u0006\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0006\u0010\u0003\u001a\u0013\u0010\u0007\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0007\u0010\u0003\u001a%\u0010\u000b\u001a\u00020\t*\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0080\bø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a7\u0010\u0011\u001a\u00020\t*\u00020\r2\u001e\u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0\u000eH\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a+\u0010\u0015\u001a\u00020\t*\u00020\r2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t0\u0013H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a+\u0010\u0017\u001a\u00020\t*\u00020\r2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0\u0013H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0016\u001a+\u0010\u0018\u001a\u00020\t*\u00020\r2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0\u0013H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0016\u001a\u001b\u0010\u001b\u001a\u00020\t*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\rH\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001d"}, d2 = {"", "", "isDelimiter", "(C)Z", "isNonDelimiter", "isOctet", "isNonDigit", "isDigit", "Lkotlin/Function0;", "", "block", "otherwise", "(ZLkotlin/jvm/functions/Function0;)V", "", "Lkotlin/Function3;", "", "success", "tryParseTime", "(Ljava/lang/String;Lkotlin/jvm/functions/Function3;)V", "Lkotlin/Function1;", "Lio/ktor/util/date/Month;", "tryParseMonth", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "tryParseDayOfMonth", "tryParseYear", "Lio/ktor/http/CookieDateBuilder;", "token", "handleToken", "(Lio/ktor/http/CookieDateBuilder;Ljava/lang/String;)V", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CookieUtilsKt {
    public static final boolean isDelimiter(char c) {
        if (c == '\t') {
            return true;
        }
        if (' ' <= c && c < '0') {
            return true;
        }
        if (';' <= c && c < 'A') {
            return true;
        }
        if ('[' > c || c >= 'a') {
            return '{' <= c && c < 127;
        }
        return true;
    }

    public static final boolean isDigit(char c) {
        return '0' <= c && c < ':';
    }

    public static final boolean isNonDelimiter(char c) {
        if (c >= 0 && c < '\t') {
            return true;
        }
        if ('\n' <= c && c < ' ') {
            return true;
        }
        if (('0' <= c && c < ':') || c == ':') {
            return true;
        }
        if ('a' <= c && c < '{') {
            return true;
        }
        if ('A' > c || c >= '[') {
            return 127 <= c && c < 256;
        }
        return true;
    }

    public static final boolean isNonDigit(char c) {
        if (c < 0 || c >= '0') {
            return 'J' <= c && c < 256;
        }
        return true;
    }

    public static final boolean isOctet(char c) {
        return c >= 0 && c < 256;
    }

    public static final void otherwise(boolean z, Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (z) {
            return;
        }
        block.invoke();
    }

    public static final void tryParseTime(String str, Function3<? super Integer, ? super Integer, ? super Integer, Unit> success) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(success, "success");
        StringLexer stringLexer = new StringLexer(str);
        int index = stringLexer.getIndex();
        if (stringLexer.accept(CookieUtilsKt$tryParseTime$hour$1$1.INSTANCE)) {
            stringLexer.accept(CookieUtilsKt$tryParseTime$hour$1$3.INSTANCE);
            String strSubstring = stringLexer.getSource().substring(index, stringLexer.getIndex());
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            int i = Integer.parseInt(strSubstring);
            if (stringLexer.accept(C09821.INSTANCE)) {
                int index2 = stringLexer.getIndex();
                if (stringLexer.accept(CookieUtilsKt$tryParseTime$minute$1$1.INSTANCE)) {
                    stringLexer.accept(CookieUtilsKt$tryParseTime$minute$1$3.INSTANCE);
                    String strSubstring2 = stringLexer.getSource().substring(index2, stringLexer.getIndex());
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                    int i2 = Integer.parseInt(strSubstring2);
                    if (stringLexer.accept(AnonymousClass3.INSTANCE)) {
                        int index3 = stringLexer.getIndex();
                        if (stringLexer.accept(CookieUtilsKt$tryParseTime$second$1$1.INSTANCE)) {
                            stringLexer.accept(CookieUtilsKt$tryParseTime$second$1$3.INSTANCE);
                            String strSubstring3 = stringLexer.getSource().substring(index3, stringLexer.getIndex());
                            Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                            int i3 = Integer.parseInt(strSubstring3);
                            if (stringLexer.accept(AnonymousClass5.INSTANCE)) {
                                stringLexer.acceptWhile(AnonymousClass6.INSTANCE);
                            }
                            success.invoke(Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseTime$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CookieUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    public static final class C09821 implements Function1<Character, Boolean> {
        public static final C09821 INSTANCE = new C09821();

        public final Boolean invoke(char c) {
            return Boolean.valueOf(c == ':');
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Character ch) {
            return invoke(ch.charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseTime$3, reason: invalid class name */
    /* JADX INFO: compiled from: CookieUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    public static final class AnonymousClass3 implements Function1<Character, Boolean> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public final Boolean invoke(char c) {
            return Boolean.valueOf(c == ':');
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Character ch) {
            return invoke(ch.charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseTime$5, reason: invalid class name */
    /* JADX INFO: compiled from: CookieUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    public static final class AnonymousClass5 implements Function1<Character, Boolean> {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public final Boolean invoke(char c) {
            return Boolean.valueOf(CookieUtilsKt.isNonDigit(c));
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Character ch) {
            return invoke(ch.charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseTime$6, reason: invalid class name */
    /* JADX INFO: compiled from: CookieUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    public static final class AnonymousClass6 implements Function1<Character, Boolean> {
        public static final AnonymousClass6 INSTANCE = new AnonymousClass6();

        public final Boolean invoke(char c) {
            return Boolean.valueOf(CookieUtilsKt.isOctet(c));
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Character ch) {
            return invoke(ch.charValue());
        }
    }

    public static final void tryParseMonth(String str, Function1<? super Month, Unit> success) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(success, "success");
        if (str.length() < 3) {
            return;
        }
        for (Month month : Month.getEntries()) {
            if (StringsKt.startsWith(str, month.getValue(), true)) {
                success.invoke(month);
                return;
            }
        }
    }

    public static final void tryParseDayOfMonth(String str, Function1<? super Integer, Unit> success) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(success, "success");
        StringLexer stringLexer = new StringLexer(str);
        int index = stringLexer.getIndex();
        if (stringLexer.accept(CookieUtilsKt$tryParseDayOfMonth$day$1$1.INSTANCE)) {
            stringLexer.accept(CookieUtilsKt$tryParseDayOfMonth$day$1$3.INSTANCE);
            String strSubstring = stringLexer.getSource().substring(index, stringLexer.getIndex());
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            int i = Integer.parseInt(strSubstring);
            if (stringLexer.accept(AnonymousClass1.INSTANCE)) {
                stringLexer.acceptWhile(AnonymousClass2.INSTANCE);
            }
            success.invoke(Integer.valueOf(i));
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseDayOfMonth$1, reason: invalid class name */
    /* JADX INFO: compiled from: CookieUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    public static final class AnonymousClass1 implements Function1<Character, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public final Boolean invoke(char c) {
            return Boolean.valueOf(CookieUtilsKt.isNonDigit(c));
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Character ch) {
            return invoke(ch.charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseDayOfMonth$2, reason: invalid class name */
    /* JADX INFO: compiled from: CookieUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    public static final class AnonymousClass2 implements Function1<Character, Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public final Boolean invoke(char c) {
            return Boolean.valueOf(CookieUtilsKt.isOctet(c));
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Character ch) {
            return invoke(ch.charValue());
        }
    }

    public static final void tryParseYear(String str, Function1<? super Integer, Unit> success) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(success, "success");
        StringLexer stringLexer = new StringLexer(str);
        int index = stringLexer.getIndex();
        for (int i = 0; i < 2; i++) {
            if (!stringLexer.accept(CookieUtilsKt$tryParseYear$year$1$1$1.INSTANCE)) {
                return;
            }
        }
        for (int i2 = 0; i2 < 2; i2++) {
            stringLexer.accept(CookieUtilsKt$tryParseYear$year$1$2$1.INSTANCE);
        }
        String strSubstring = stringLexer.getSource().substring(index, stringLexer.getIndex());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        int i3 = Integer.parseInt(strSubstring);
        if (stringLexer.accept(C09831.INSTANCE)) {
            stringLexer.acceptWhile(C09842.INSTANCE);
        }
        success.invoke(Integer.valueOf(i3));
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseYear$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CookieUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    public static final class C09831 implements Function1<Character, Boolean> {
        public static final C09831 INSTANCE = new C09831();

        public final Boolean invoke(char c) {
            return Boolean.valueOf(CookieUtilsKt.isNonDigit(c));
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Character ch) {
            return invoke(ch.charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseYear$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CookieUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    public static final class C09842 implements Function1<Character, Boolean> {
        public static final C09842 INSTANCE = new C09842();

        public final Boolean invoke(char c) {
            return Boolean.valueOf(CookieUtilsKt.isOctet(c));
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Character ch) {
            return invoke(ch.charValue());
        }
    }

    public static final void handleToken(CookieDateBuilder cookieDateBuilder, String token) {
        Intrinsics.checkNotNullParameter(cookieDateBuilder, "<this>");
        Intrinsics.checkNotNullParameter(token, "token");
        if (cookieDateBuilder.getHours() == null || cookieDateBuilder.getMinutes() == null || cookieDateBuilder.getSeconds() == null) {
            StringLexer stringLexer = new StringLexer(token);
            int index = stringLexer.getIndex();
            if (stringLexer.accept(CookieUtilsKt$tryParseTime$hour$1$1.INSTANCE)) {
                stringLexer.accept(CookieUtilsKt$tryParseTime$hour$1$3.INSTANCE);
                String strSubstring = stringLexer.getSource().substring(index, stringLexer.getIndex());
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                int i = Integer.parseInt(strSubstring);
                if (stringLexer.accept(C09821.INSTANCE)) {
                    int index2 = stringLexer.getIndex();
                    if (stringLexer.accept(CookieUtilsKt$tryParseTime$minute$1$1.INSTANCE)) {
                        stringLexer.accept(CookieUtilsKt$tryParseTime$minute$1$3.INSTANCE);
                        String strSubstring2 = stringLexer.getSource().substring(index2, stringLexer.getIndex());
                        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                        int i2 = Integer.parseInt(strSubstring2);
                        if (stringLexer.accept(AnonymousClass3.INSTANCE)) {
                            int index3 = stringLexer.getIndex();
                            if (stringLexer.accept(CookieUtilsKt$tryParseTime$second$1$1.INSTANCE)) {
                                stringLexer.accept(CookieUtilsKt$tryParseTime$second$1$3.INSTANCE);
                                String strSubstring3 = stringLexer.getSource().substring(index3, stringLexer.getIndex());
                                Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                                int i3 = Integer.parseInt(strSubstring3);
                                if (stringLexer.accept(AnonymousClass5.INSTANCE)) {
                                    stringLexer.acceptWhile(AnonymousClass6.INSTANCE);
                                }
                                cookieDateBuilder.setHours(Integer.valueOf(i));
                                cookieDateBuilder.setMinutes(Integer.valueOf(i2));
                                cookieDateBuilder.setSeconds(Integer.valueOf(i3));
                                return;
                            }
                        }
                    }
                }
            }
        }
        if (cookieDateBuilder.getDayOfMonth() == null) {
            StringLexer stringLexer2 = new StringLexer(token);
            int index4 = stringLexer2.getIndex();
            if (stringLexer2.accept(CookieUtilsKt$tryParseDayOfMonth$day$1$1.INSTANCE)) {
                stringLexer2.accept(CookieUtilsKt$tryParseDayOfMonth$day$1$3.INSTANCE);
                String strSubstring4 = stringLexer2.getSource().substring(index4, stringLexer2.getIndex());
                Intrinsics.checkNotNullExpressionValue(strSubstring4, "substring(...)");
                int i4 = Integer.parseInt(strSubstring4);
                if (stringLexer2.accept(AnonymousClass1.INSTANCE)) {
                    stringLexer2.acceptWhile(AnonymousClass2.INSTANCE);
                }
                cookieDateBuilder.setDayOfMonth(Integer.valueOf(i4));
                return;
            }
        }
        if (cookieDateBuilder.getMonth() == null && token.length() >= 3) {
            for (Month month : Month.getEntries()) {
                if (StringsKt.startsWith(token, month.getValue(), true)) {
                    cookieDateBuilder.setMonth(month);
                    return;
                }
            }
        }
        if (cookieDateBuilder.getYear() == null) {
            StringLexer stringLexer3 = new StringLexer(token);
            int index5 = stringLexer3.getIndex();
            for (int i5 = 0; i5 < 2; i5++) {
                if (!stringLexer3.accept(CookieUtilsKt$tryParseYear$year$1$1$1.INSTANCE)) {
                    return;
                }
            }
            for (int i6 = 0; i6 < 2; i6++) {
                stringLexer3.accept(CookieUtilsKt$tryParseYear$year$1$2$1.INSTANCE);
            }
            String strSubstring5 = stringLexer3.getSource().substring(index5, stringLexer3.getIndex());
            Intrinsics.checkNotNullExpressionValue(strSubstring5, "substring(...)");
            int i7 = Integer.parseInt(strSubstring5);
            if (stringLexer3.accept(C09831.INSTANCE)) {
                stringLexer3.acceptWhile(C09842.INSTANCE);
            }
            cookieDateBuilder.setYear(Integer.valueOf(i7));
        }
    }
}
