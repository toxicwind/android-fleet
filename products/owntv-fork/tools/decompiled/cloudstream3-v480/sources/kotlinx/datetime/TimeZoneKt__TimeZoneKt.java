package kotlinx.datetime;

import io.ktor.http.ContentType;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Instant;

/* JADX INFO: compiled from: TimeZone.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e*8\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B*\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u001c\b\u0005\u0012\u0018\b\u000bB\u0014\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0006\b\t\u0012\u0002\b\f¨\u0006\u000f"}, d2 = {"ZoneOffset", "Lkotlinx/datetime/FixedOffsetTimeZone;", "Lkotlin/Deprecated;", ContentType.Message.TYPE, "Use FixedOffsetTimeZone or UtcOffset instead", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "FixedOffsetTimeZone", "imports", "offsetIn", "Lkotlinx/datetime/UtcOffset;", "Lkotlin/time/Instant;", "timeZone", "Lkotlinx/datetime/TimeZone;", "kotlinx-datetime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "kotlinx/datetime/TimeZoneKt")
final /* synthetic */ class TimeZoneKt__TimeZoneKt {
    @Deprecated(message = "Use FixedOffsetTimeZone or UtcOffset instead", replaceWith = @ReplaceWith(expression = "FixedOffsetTimeZone", imports = {}))
    public static /* synthetic */ void ZoneOffset$annotations() {
    }

    public static final UtcOffset offsetIn(Instant instant, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return TimeZoneKt.offsetAt(timeZone, instant);
    }
}
