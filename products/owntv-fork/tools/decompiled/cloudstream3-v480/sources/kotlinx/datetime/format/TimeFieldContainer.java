package kotlinx.datetime.format;

import kotlin.Metadata;
import kotlinx.datetime.internal.DecimalFraction;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: LocalTimeFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b`\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\u0005\"\u0004\b\r\u0010\u0007R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0005\"\u0004\b\u0010\u0010\u0007R\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0005\"\u0004\b\u0013\u0010\u0007R\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u0015X¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R(\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006!À\u0006\u0003"}, d2 = {"Lkotlinx/datetime/format/TimeFieldContainer;", "", "minute", "", "getMinute", "()Ljava/lang/Integer;", "setMinute", "(Ljava/lang/Integer;)V", "second", "getSecond", "setSecond", "nanosecond", "getNanosecond", "setNanosecond", "hour", "getHour", "setHour", "hourOfAmPm", "getHourOfAmPm", "setHourOfAmPm", "amPm", "Lkotlinx/datetime/format/AmPmMarker;", "getAmPm", "()Lkotlinx/datetime/format/AmPmMarker;", "setAmPm", "(Lkotlinx/datetime/format/AmPmMarker;)V", ES6Iterator.VALUE_PROPERTY, "Lkotlinx/datetime/internal/DecimalFraction;", "fractionOfSecond", "getFractionOfSecond", "()Lkotlinx/datetime/internal/DecimalFraction;", "setFractionOfSecond", "(Lkotlinx/datetime/internal/DecimalFraction;)V", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface TimeFieldContainer {
    AmPmMarker getAmPm();

    DecimalFraction getFractionOfSecond();

    Integer getHour();

    Integer getHourOfAmPm();

    Integer getMinute();

    Integer getNanosecond();

    Integer getSecond();

    void setAmPm(AmPmMarker amPmMarker);

    void setFractionOfSecond(DecimalFraction decimalFraction);

    void setHour(Integer num);

    void setHourOfAmPm(Integer num);

    void setMinute(Integer num);

    void setNanosecond(Integer num);

    void setSecond(Integer num);

    /* JADX INFO: renamed from: kotlinx.datetime.format.TimeFieldContainer$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: LocalTimeFormat.kt */
    public final /* synthetic */ class CC {
        public static DecimalFraction $default$getFractionOfSecond(TimeFieldContainer _this) {
            Integer nanosecond = _this.getNanosecond();
            if (nanosecond != null) {
                return new DecimalFraction(nanosecond.intValue(), 9);
            }
            return null;
        }

        public static void $default$setFractionOfSecond(TimeFieldContainer _this, DecimalFraction decimalFraction) {
            _this.setNanosecond(decimalFraction != null ? Integer.valueOf(decimalFraction.fractionalPartWithNDigits(9)) : null);
        }
    }

    /* JADX INFO: compiled from: LocalTimeFormat.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static DecimalFraction getFractionOfSecond(TimeFieldContainer timeFieldContainer) {
            return CC.$default$getFractionOfSecond(timeFieldContainer);
        }

        @Deprecated
        public static void setFractionOfSecond(TimeFieldContainer timeFieldContainer, DecimalFraction decimalFraction) {
            CC.$default$setFractionOfSecond(timeFieldContainer, decimalFraction);
        }
    }
}
