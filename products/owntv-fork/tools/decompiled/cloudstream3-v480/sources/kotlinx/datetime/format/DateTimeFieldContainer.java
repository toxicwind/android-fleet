package kotlinx.datetime.format;

import kotlin.Metadata;
import kotlinx.datetime.internal.DecimalFraction;

/* JADX INFO: compiled from: LocalDateTimeFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u00012\u00020\u0002¨\u0006\u0003À\u0006\u0003"}, d2 = {"Lkotlinx/datetime/format/DateTimeFieldContainer;", "Lkotlinx/datetime/format/DateFieldContainer;", "Lkotlinx/datetime/format/TimeFieldContainer;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface DateTimeFieldContainer extends DateFieldContainer, TimeFieldContainer {

    /* JADX INFO: renamed from: kotlinx.datetime.format.DateTimeFieldContainer$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: LocalDateTimeFormat.kt */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: LocalDateTimeFormat.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static DecimalFraction getFractionOfSecond(DateTimeFieldContainer dateTimeFieldContainer) {
            return TimeFieldContainer.CC.$default$getFractionOfSecond(dateTimeFieldContainer);
        }

        @Deprecated
        public static void setFractionOfSecond(DateTimeFieldContainer dateTimeFieldContainer, DecimalFraction decimalFraction) {
            TimeFieldContainer.CC.$default$setFractionOfSecond(dateTimeFieldContainer, decimalFraction);
        }
    }
}
