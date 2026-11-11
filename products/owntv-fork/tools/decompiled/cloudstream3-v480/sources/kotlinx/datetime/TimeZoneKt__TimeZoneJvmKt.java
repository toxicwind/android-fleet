package kotlinx.datetime;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import j$.time.DateTimeException;
import j$.time.ZoneId;
import j$.time.ZonedDateTime;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Instant;
import kotlin.time.jdk8.InstantConversionsJDK8Kt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: TimeZoneJvm.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0019\u0010\b\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001b\u0010\b\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\n\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\b\u0010\u000b\u001a#\u0010\u000e\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000f\u001a#\u0010\u000e\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u0010\u001a#\u0010\u0012\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0012\u0010\u0013\u001a+\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00002\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u0000¢\u0006\u0004\b\u0016\u0010\u0017\"\u0018\u0010\u001c\u001a\u00020\u0019*\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, d2 = {"Lkotlinx/datetime/TimeZone;", "Lkotlin/time/Instant;", "instant", "Lkotlinx/datetime/UtcOffset;", "offsetAt", "(Lkotlinx/datetime/TimeZone;Lkotlin/time/Instant;)Lkotlinx/datetime/UtcOffset;", "timeZone", "Lkotlinx/datetime/LocalDateTime;", "toLocalDateTime", "(Lkotlin/time/Instant;Lkotlinx/datetime/TimeZone;)Lkotlinx/datetime/LocalDateTime;", TypedValues.CycleType.S_WAVE_OFFSET, "(Lkotlin/time/Instant;Lkotlinx/datetime/UtcOffset;)Lkotlinx/datetime/LocalDateTime;", "Lkotlinx/datetime/OverloadMarker;", "youShallNotPass", "toInstant", "(Lkotlinx/datetime/LocalDateTime;Lkotlinx/datetime/TimeZone;Lkotlinx/datetime/OverloadMarker;)Lkotlin/time/Instant;", "(Lkotlinx/datetime/LocalDateTime;Lkotlinx/datetime/UtcOffset;Lkotlinx/datetime/OverloadMarker;)Lkotlin/time/Instant;", "Lkotlinx/datetime/LocalDate;", "atStartOfDayIn", "(Lkotlinx/datetime/LocalDate;Lkotlinx/datetime/TimeZone;Lkotlinx/datetime/OverloadMarker;)Lkotlin/time/Instant;", "dateTime", "preferred", "localDateTimeToInstant", "(Lkotlinx/datetime/LocalDateTime;Lkotlinx/datetime/TimeZone;Lkotlinx/datetime/UtcOffset;)Lkotlin/time/Instant;", "j$/time/ZoneId", "", "isFixedOffset$TimeZoneKt__TimeZoneJvmKt", "(Lj$/time/ZoneId;)Z", "isFixedOffset", "kotlinx-datetime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "kotlinx/datetime/TimeZoneKt")
public final /* synthetic */ class TimeZoneKt__TimeZoneJvmKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isFixedOffset$TimeZoneKt__TimeZoneJvmKt(ZoneId zoneId) {
        try {
            return zoneId.getRules().isFixedOffset();
        } catch (ArrayIndexOutOfBoundsException unused) {
            return false;
        }
    }

    public static final UtcOffset offsetAt(TimeZone timeZone, Instant instant) {
        Intrinsics.checkNotNullParameter(timeZone, "<this>");
        Intrinsics.checkNotNullParameter(instant, "instant");
        return new UtcOffset(timeZone.getZoneId().getRules().getOffset(InstantConversionsJDK8Kt.toJavaInstant(instant)));
    }

    public static final LocalDateTime toLocalDateTime(Instant instant, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        try {
            return new LocalDateTime(j$.time.LocalDateTime.ofInstant(InstantConversionsJDK8Kt.toJavaInstant(instant), timeZone.getZoneId()));
        } catch (DateTimeException e) {
            throw new DateTimeArithmeticException(e);
        }
    }

    public static final LocalDateTime toLocalDateTime(Instant instant, UtcOffset offset) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(offset, "offset");
        try {
            return new LocalDateTime(j$.time.LocalDateTime.ofInstant(InstantConversionsJDK8Kt.toJavaInstant(instant), offset.getZoneOffset()));
        } catch (DateTimeException e) {
            throw new DateTimeArithmeticException(e);
        }
    }

    public static final Instant toInstant(LocalDateTime localDateTime, TimeZone timeZone, OverloadMarker youShallNotPass) {
        Intrinsics.checkNotNullParameter(localDateTime, "<this>");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        Intrinsics.checkNotNullParameter(youShallNotPass, "youShallNotPass");
        j$.time.Instant instant = localDateTime.getValue().atZone(timeZone.getZoneId()).toInstant();
        Intrinsics.checkNotNullExpressionValue(instant, "toInstant(...)");
        return InstantConversionsJDK8Kt.toKotlinInstant(instant);
    }

    public static final Instant toInstant(LocalDateTime localDateTime, UtcOffset offset, OverloadMarker youShallNotPass) {
        Intrinsics.checkNotNullParameter(localDateTime, "<this>");
        Intrinsics.checkNotNullParameter(offset, "offset");
        Intrinsics.checkNotNullParameter(youShallNotPass, "youShallNotPass");
        j$.time.Instant instant = localDateTime.getValue().toInstant(offset.getZoneOffset());
        Intrinsics.checkNotNullExpressionValue(instant, "toInstant(...)");
        return InstantConversionsJDK8Kt.toKotlinInstant(instant);
    }

    public static final Instant atStartOfDayIn(LocalDate localDate, TimeZone timeZone, OverloadMarker youShallNotPass) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        Intrinsics.checkNotNullParameter(youShallNotPass, "youShallNotPass");
        j$.time.Instant instant = localDate.getValue().atStartOfDay(timeZone.getZoneId()).toInstant();
        Intrinsics.checkNotNullExpressionValue(instant, "toInstant(...)");
        return InstantConversionsJDK8Kt.toKotlinInstant(instant);
    }

    public static /* synthetic */ Instant localDateTimeToInstant$default(LocalDateTime localDateTime, TimeZone timeZone, UtcOffset utcOffset, int i, Object obj) {
        if ((i & 4) != 0) {
            utcOffset = null;
        }
        return TimeZoneKt.localDateTimeToInstant(localDateTime, timeZone, utcOffset);
    }

    public static final Instant localDateTimeToInstant(LocalDateTime dateTime, TimeZone timeZone, UtcOffset utcOffset) {
        Intrinsics.checkNotNullParameter(dateTime, "dateTime");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        j$.time.Instant instant = ZonedDateTime.ofLocal(dateTime.getValue(), timeZone.getZoneId(), utcOffset != null ? utcOffset.getZoneOffset() : null).toInstant();
        Intrinsics.checkNotNullExpressionValue(instant, "toInstant(...)");
        return InstantConversionsJDK8Kt.toKotlinInstant(instant);
    }

    public static /* synthetic */ Instant atStartOfDayIn$default(LocalDate localDate, TimeZone timeZone, OverloadMarker overloadMarker, int i, Object obj) {
        if ((i & 2) != 0) {
            overloadMarker = OverloadMarker.INSTANCE.getINSTANCE$kotlinx_datetime();
        }
        return TimeZoneKt.atStartOfDayIn(localDate, timeZone, overloadMarker);
    }

    public static /* synthetic */ Instant toInstant$default(LocalDateTime localDateTime, TimeZone timeZone, OverloadMarker overloadMarker, int i, Object obj) {
        if ((i & 2) != 0) {
            overloadMarker = OverloadMarker.INSTANCE.getINSTANCE$kotlinx_datetime();
        }
        return TimeZoneKt.toInstant(localDateTime, timeZone, overloadMarker);
    }

    public static /* synthetic */ Instant toInstant$default(LocalDateTime localDateTime, UtcOffset utcOffset, OverloadMarker overloadMarker, int i, Object obj) {
        if ((i & 2) != 0) {
            overloadMarker = OverloadMarker.INSTANCE.getINSTANCE$kotlinx_datetime();
        }
        return TimeZoneKt.toInstant(localDateTime, utcOffset, overloadMarker);
    }
}
