package kotlinx.datetime;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MonthJvm.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0010\b\n\u0000\n\u0002\b\b\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\"\u001e\u0010\u0001\u001a\u00020\u0000*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"", "number", "j$/time/Month", "Month", "(I)Lj$/time/Month;", "getNumber", "(Lj$/time/Month;)I", "getNumber$annotations", "(Lj$/time/Month;)V", "kotlinx-datetime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "kotlinx/datetime/MonthKt")
final /* synthetic */ class MonthKt__MonthJvmKt {
    @Deprecated(message = "Use kotlinx.datetime.Month", replaceWith = @ReplaceWith(expression = "toKotlinMonth().number", imports = {}))
    public static /* synthetic */ void getNumber$annotations(j$.time.Month month) {
    }

    public static final int getNumber(j$.time.Month month) {
        Intrinsics.checkNotNullParameter(month, "<this>");
        return MonthKt.getNumber(ConvertersKt.toKotlinMonth(month));
    }
}
