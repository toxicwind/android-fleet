package io.ktor.http;

import io.ktor.util.date.GMTDate;
import io.ktor.util.date.GMTDateParser;
import io.ktor.util.date.InvalidDateStringException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: DateUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0011\u0010\u0004\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0003\u001a\u0011\u0010\u0005\u001a\u00020\u0000*\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001b\u0010\t\u001a\u00020\u0000*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\t\u0010\n\"\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"", "Lio/ktor/util/date/GMTDate;", "fromHttpToGmtDate", "(Ljava/lang/String;)Lio/ktor/util/date/GMTDate;", "fromCookieToGmtDate", "toHttpDate", "(Lio/ktor/util/date/GMTDate;)Ljava/lang/String;", "", "length", "padZero", "(II)Ljava/lang/String;", "", "HTTP_DATE_FORMATS", "Ljava/util/List;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DateUtilsKt {
    private static final List<String> HTTP_DATE_FORMATS = CollectionsKt.listOf((Object[]) new String[]{"***, dd MMM YYYY hh:mm:ss zzz", "****, dd-MMM-YYYY hh:mm:ss zzz", "*** MMM d hh:mm:ss YYYY", "***, dd-MMM-YYYY hh:mm:ss zzz", "***, dd-MMM-YYYY hh-mm-ss zzz", "***, dd MMM YYYY hh:mm:ss zzz", "*** dd-MMM-YYYY hh:mm:ss zzz", "*** dd MMM YYYY hh:mm:ss zzz", "*** dd-MMM-YYYY hh-mm-ss zzz", "***,dd-MMM-YYYY hh:mm:ss zzz", "*** MMM d YYYY hh:mm:ss zzz"});

    public static final GMTDate fromHttpToGmtDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String string = StringsKt.trim((CharSequence) str).toString();
        Iterator<String> it = HTTP_DATE_FORMATS.iterator();
        while (it.hasNext()) {
            try {
                return new GMTDateParser(it.next()).parse(str);
            } catch (InvalidDateStringException unused) {
            }
        }
        throw new IllegalStateException(("Failed to parse date: " + string).toString());
    }

    public static final GMTDate fromCookieToGmtDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String string = StringsKt.trim((CharSequence) str).toString();
        try {
            return new CookieDateParser().parse(string);
        } catch (InvalidCookieDateException unused) {
            return fromHttpToGmtDate(string);
        }
    }

    public static final String toHttpDate(GMTDate gMTDate) {
        Intrinsics.checkNotNullParameter(gMTDate, "<this>");
        StringBuilder sb = new StringBuilder();
        sb.append(gMTDate.getDayOfWeek().getValue() + ", ");
        sb.append(padZero(gMTDate.getDayOfMonth(), 2) + ' ');
        sb.append(gMTDate.getMonth().getValue() + ' ');
        sb.append(padZero(gMTDate.getYear(), 4));
        sb.append(Stream.ID_UNKNOWN + padZero(gMTDate.getHours(), 2) + AbstractJsonLexerKt.COLON + padZero(gMTDate.getMinutes(), 2) + AbstractJsonLexerKt.COLON + padZero(gMTDate.getSeconds(), 2) + ' ');
        sb.append("GMT");
        return sb.toString();
    }

    private static final String padZero(int i, int i2) {
        return StringsKt.padStart(String.valueOf(i), i2, '0');
    }
}
