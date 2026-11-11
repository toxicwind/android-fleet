package kotlinx.datetime;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;

/* JADX INFO: compiled from: UtcOffset.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003\u001a\b\u0010\u0004\u001a\u00020\u0002H\u0007\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0002\u001a\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003H\u0000¨\u0006\b"}, d2 = {"format", "", "Lkotlinx/datetime/UtcOffset;", "Lkotlinx/datetime/format/DateTimeFormat;", "UtcOffset", "asTimeZone", "Lkotlinx/datetime/FixedOffsetTimeZone;", "getIsoUtcOffsetFormat", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class UtcOffsetKt {
    public static final String format(UtcOffset utcOffset, DateTimeFormat<UtcOffset> format) {
        Intrinsics.checkNotNullParameter(utcOffset, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return format.format(utcOffset);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use UtcOffset.ZERO instead", replaceWith = @ReplaceWith(expression = "UtcOffset.ZERO", imports = {}))
    public static final UtcOffset UtcOffset() {
        return UtcOffset.INSTANCE.getZERO();
    }

    public static final FixedOffsetTimeZone asTimeZone(UtcOffset utcOffset) {
        Intrinsics.checkNotNullParameter(utcOffset, "<this>");
        return new FixedOffsetTimeZone(utcOffset);
    }

    public static final DateTimeFormat<UtcOffset> getIsoUtcOffsetFormat() {
        return UtcOffset.Formats.INSTANCE.getISO();
    }
}
