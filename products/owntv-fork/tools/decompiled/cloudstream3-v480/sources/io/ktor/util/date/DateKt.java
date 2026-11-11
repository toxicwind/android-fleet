package io.ktor.util.date;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;

/* JADX INFO: compiled from: Date.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001c\u0010\u0003\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001c\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\u0002¢\u0006\u0004\b\u0005\u0010\u0004\u001a\u001c\u0010\u0003\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0086\u0002¢\u0006\u0004\b\b\u0010\u0004\u001a\u001c\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0086\u0002¢\u0006\u0004\b\t\u0010\u0004\u001a\u0011\u0010\n\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/util/date/GMTDate;", "", "milliseconds", "plus", "(Lio/ktor/util/date/GMTDate;J)Lio/ktor/util/date/GMTDate;", "minus", "Lkotlin/time/Duration;", TypedValues.TransitionType.S_DURATION, "plus-HG0u8IE", "minus-HG0u8IE", "truncateToSeconds", "(Lio/ktor/util/date/GMTDate;)Lio/ktor/util/date/GMTDate;", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DateKt {
    public static final GMTDate plus(GMTDate gMTDate, long j) {
        Intrinsics.checkNotNullParameter(gMTDate, "<this>");
        return DateJvmKt.GMTDate(Long.valueOf(gMTDate.getTimestamp() + j));
    }

    public static final GMTDate minus(GMTDate gMTDate, long j) {
        Intrinsics.checkNotNullParameter(gMTDate, "<this>");
        return DateJvmKt.GMTDate(Long.valueOf(gMTDate.getTimestamp() - j));
    }

    /* JADX INFO: renamed from: plus-HG0u8IE, reason: not valid java name */
    public static final GMTDate m8859plusHG0u8IE(GMTDate plus, long j) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        return DateJvmKt.GMTDate(Long.valueOf(plus.getTimestamp() + Duration.m10329getInWholeMillisecondsimpl(j)));
    }

    /* JADX INFO: renamed from: minus-HG0u8IE, reason: not valid java name */
    public static final GMTDate m8858minusHG0u8IE(GMTDate minus, long j) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        return DateJvmKt.GMTDate(Long.valueOf(minus.getTimestamp() - Duration.m10329getInWholeMillisecondsimpl(j)));
    }

    public static final GMTDate truncateToSeconds(GMTDate gMTDate) {
        Intrinsics.checkNotNullParameter(gMTDate, "<this>");
        return DateJvmKt.GMTDate(gMTDate.getSeconds(), gMTDate.getMinutes(), gMTDate.getHours(), gMTDate.getDayOfMonth(), gMTDate.getMonth(), gMTDate.getYear());
    }
}
