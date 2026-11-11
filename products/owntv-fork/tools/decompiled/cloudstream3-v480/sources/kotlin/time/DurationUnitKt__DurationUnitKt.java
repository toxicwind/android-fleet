package kotlin.time;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DurationUnit.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0080\u0080\u0004\u001a\u001b\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b\u0007\u001a\u000e\u0010\u000b\u001a\u00020\f*\u00020\u0004H\u0081\u0080\u0004\"\u0019\u0010\b\u001a\u00020\u0001*\u00020\u00048BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"convertDurationUnitToMilliseconds", "", ES6Iterator.VALUE_PROPERTY, "unit", "Lkotlin/time/DurationUnit;", "multiplyNonNegativeWithoutOverflow", "other", "multiplyNonNegativeWithoutOverflow$DurationUnitKt__DurationUnitKt", "millisMultiplier", "getMillisMultiplier$DurationUnitKt__DurationUnitKt", "(Lkotlin/time/DurationUnit;)J", "shortName", "", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/time/DurationUnitKt")
class DurationUnitKt__DurationUnitKt extends DurationUnitKt__DurationUnitJvmKt {

    /* JADX INFO: compiled from: DurationUnit.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DurationUnit.values().length];
            try {
                iArr[DurationUnit.DAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DurationUnit.HOURS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DurationUnit.MINUTES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DurationUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DurationUnit.MILLISECONDS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[DurationUnit.NANOSECONDS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[DurationUnit.MICROSECONDS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final long convertDurationUnitToMilliseconds(long j, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return multiplyNonNegativeWithoutOverflow$DurationUnitKt__DurationUnitKt(j, getMillisMultiplier$DurationUnitKt__DurationUnitKt(unit));
    }

    private static final long multiplyNonNegativeWithoutOverflow$DurationUnitKt__DurationUnitKt(long j, long j2) {
        if (j == 0) {
            return 0L;
        }
        if (j == 1) {
            return RangesKt.coerceAtMost(j2, 4611686018427387903L);
        }
        if (j2 == 1) {
            return RangesKt.coerceAtMost(j, 4611686018427387903L);
        }
        int iNumberOfLeadingZeros = (128 - Long.numberOfLeadingZeros(j)) - Long.numberOfLeadingZeros(j2);
        if (iNumberOfLeadingZeros < 63) {
            return j * j2;
        }
        if (iNumberOfLeadingZeros > 63) {
            return 4611686018427387903L;
        }
        return RangesKt.coerceAtMost(j * j2, 4611686018427387903L);
    }

    private static final long getMillisMultiplier$DurationUnitKt__DurationUnitKt(DurationUnit durationUnit) {
        int i = WhenMappings.$EnumSwitchMapping$0[durationUnit.ordinal()];
        if (i == 1) {
            return 86400000L;
        }
        if (i == 2) {
            return DurationKt.MILLIS_IN_HOUR;
        }
        if (i == 3) {
            return 60000L;
        }
        if (i == 4) {
            return 1000L;
        }
        if (i == 5) {
            return 1L;
        }
        throw new IllegalStateException(("Wrong unit for millisMultiplier: " + durationUnit).toString());
    }

    public static final String shortName(DurationUnit durationUnit) {
        Intrinsics.checkNotNullParameter(durationUnit, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[durationUnit.ordinal()]) {
            case 1:
                return "d";
            case 2:
                return CmcdData.STREAMING_FORMAT_HLS;
            case 3:
                return CmcdData.OBJECT_TYPE_MANIFEST;
            case 4:
                return CmcdData.STREAMING_FORMAT_SS;
            case 5:
                return "ms";
            case 6:
                return "ns";
            case 7:
                return "us";
            default:
                throw new IllegalStateException(("Unknown unit: " + durationUnit).toString());
        }
    }
}
