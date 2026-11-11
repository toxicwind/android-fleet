package io.ktor.http;

import io.ktor.util.date.GMTDateParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import org.schabi.newpipe.extractor.stream.Stream;
import org.slf4j.Marker;

/* JADX INFO: compiled from: ContentRange.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a-\u0010\u0007\u001a\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b\u001a-\u0010\u0007\u001a\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/ranges/LongRange;", "range", "", "fullLength", "Lio/ktor/http/RangeUnits;", "unit", "", "contentRangeHeaderValue", "(Lkotlin/ranges/LongRange;Ljava/lang/Long;Lio/ktor/http/RangeUnits;)Ljava/lang/String;", "(Lkotlin/ranges/LongRange;Ljava/lang/Long;Ljava/lang/String;)Ljava/lang/String;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ContentRangeKt {
    public static /* synthetic */ String contentRangeHeaderValue$default(LongRange longRange, Long l, RangeUnits rangeUnits, int i, Object obj) {
        if ((i & 2) != 0) {
            l = null;
        }
        if ((i & 4) != 0) {
            rangeUnits = RangeUnits.Bytes;
        }
        return contentRangeHeaderValue(longRange, l, rangeUnits);
    }

    public static final String contentRangeHeaderValue(LongRange longRange, Long l, RangeUnits unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return contentRangeHeaderValue(longRange, l, unit.getUnitToken());
    }

    public static /* synthetic */ String contentRangeHeaderValue$default(LongRange longRange, Long l, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            l = null;
        }
        if ((i & 4) != 0) {
            str = RangeUnits.Bytes.getUnitToken();
        }
        return contentRangeHeaderValue(longRange, l, str);
    }

    public static final String contentRangeHeaderValue(LongRange longRange, Long l, String unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        StringBuilder sb = new StringBuilder();
        sb.append(unit);
        sb.append(Stream.ID_UNKNOWN);
        if (longRange != null) {
            sb.append(longRange.getFirst());
            sb.append('-');
            sb.append(longRange.getLast());
        } else {
            sb.append(GMTDateParser.ANY);
        }
        sb.append('/');
        Object obj = l;
        if (l == null) {
            obj = Marker.ANY_MARKER;
        }
        sb.append(obj);
        return sb.toString();
    }
}
