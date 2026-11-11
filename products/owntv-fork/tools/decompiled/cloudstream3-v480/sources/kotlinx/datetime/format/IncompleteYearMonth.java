package kotlinx.datetime.format;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.MonthKt;
import kotlinx.datetime.YearMonth;
import kotlinx.datetime.internal.format.parser.Copyable;

/* JADX INFO: compiled from: YearMonthFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u001f\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010J\b\u0010\u0014\u001a\u00020\u0000H\u0016J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000b¨\u0006\u001c"}, d2 = {"Lkotlinx/datetime/format/IncompleteYearMonth;", "Lkotlinx/datetime/format/YearMonthFieldContainer;", "Lkotlinx/datetime/internal/format/parser/Copyable;", "year", "", "monthNumber", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "getYear", "()Ljava/lang/Integer;", "setYear", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getMonthNumber", "setMonthNumber", "toYearMonth", "Lkotlinx/datetime/YearMonth;", "populateFrom", "", "yearMonth", "copy", "equals", "", "other", "", "hashCode", "toString", "", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class IncompleteYearMonth implements YearMonthFieldContainer, Copyable<IncompleteYearMonth> {
    private Integer monthNumber;
    private Integer year;

    /* JADX WARN: Multi-variable type inference failed */
    public IncompleteYearMonth() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public IncompleteYearMonth(Integer num, Integer num2) {
        this.year = num;
        this.monthNumber = num2;
    }

    public /* synthetic */ IncompleteYearMonth(Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2);
    }

    @Override // kotlinx.datetime.format.YearMonthFieldContainer
    public Integer getYear() {
        return this.year;
    }

    @Override // kotlinx.datetime.format.YearMonthFieldContainer
    public void setYear(Integer num) {
        this.year = num;
    }

    @Override // kotlinx.datetime.format.YearMonthFieldContainer
    public Integer getMonthNumber() {
        return this.monthNumber;
    }

    @Override // kotlinx.datetime.format.YearMonthFieldContainer
    public void setMonthNumber(Integer num) {
        this.monthNumber = num;
    }

    public final YearMonth toYearMonth() {
        return new YearMonth(((Number) YearMonthFormatKt.requireParsedField(getYear(), "year")).intValue(), ((Number) YearMonthFormatKt.requireParsedField(getMonthNumber(), "monthNumber")).intValue());
    }

    public final void populateFrom(YearMonth yearMonth) {
        Intrinsics.checkNotNullParameter(yearMonth, "yearMonth");
        setYear(Integer.valueOf(yearMonth.getYear()));
        setMonthNumber(Integer.valueOf(MonthKt.getNumber(yearMonth.getMonth())));
    }

    @Override // kotlinx.datetime.internal.format.parser.Copyable
    public IncompleteYearMonth copy() {
        return new IncompleteYearMonth(getYear(), getMonthNumber());
    }

    public boolean equals(Object other) {
        if (!(other instanceof IncompleteYearMonth)) {
            return false;
        }
        IncompleteYearMonth incompleteYearMonth = (IncompleteYearMonth) other;
        return Intrinsics.areEqual(getYear(), incompleteYearMonth.getYear()) && Intrinsics.areEqual(getMonthNumber(), incompleteYearMonth.getMonthNumber());
    }

    public int hashCode() {
        Integer year = getYear();
        int iHashCode = (year != null ? year.hashCode() : 0) * 31;
        Integer monthNumber = getMonthNumber();
        return iHashCode + (monthNumber != null ? monthNumber.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Object year = getYear();
        if (year == null) {
            year = "??";
        }
        sb.append(year);
        sb.append('-');
        Integer monthNumber = getMonthNumber();
        sb.append(monthNumber != null ? monthNumber : "??");
        return sb.toString();
    }
}
