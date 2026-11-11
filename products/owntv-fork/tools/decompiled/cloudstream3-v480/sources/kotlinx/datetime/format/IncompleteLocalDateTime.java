package kotlinx.datetime.format;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.LocalDateTime;
import kotlinx.datetime.internal.DecimalFraction;
import kotlinx.datetime.internal.format.parser.Copyable;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: LocalDateTimeFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u00020\u00000\u0004B\u001b\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010J\b\u0010\u0014\u001a\u00020\u0000H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0096\u000f¢\u0006\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0096\u000f¢\u0006\f\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u0004\u0018\u00010\u001cX\u0096\u000f¢\u0006\f\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u001a\u0010$\u001a\u0004\u0018\u00010\u001cX\u0096\u000f¢\u0006\f\u001a\u0004\b%\u0010\u001e\"\u0004\b&\u0010 R(\u0010)\u001a\u0004\u0018\u00010(2\b\u0010'\u001a\u0004\u0018\u00010(8V@VX\u0096\u000f¢\u0006\f\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u0004\u0018\u00010\u001cX\u0096\u000f¢\u0006\f\u001a\u0004\b/\u0010\u001e\"\u0004\b0\u0010 R\u001a\u00101\u001a\u0004\u0018\u00010\u001cX\u0096\u000f¢\u0006\f\u001a\u0004\b2\u0010\u001e\"\u0004\b3\u0010 R\u001a\u00104\u001a\u0004\u0018\u00010\u001cX\u0096\u000f¢\u0006\f\u001a\u0004\b5\u0010\u001e\"\u0004\b6\u0010 R\u001a\u00107\u001a\u0004\u0018\u00010\u001cX\u0096\u000f¢\u0006\f\u001a\u0004\b8\u0010\u001e\"\u0004\b9\u0010 R\u001a\u0010:\u001a\u0004\u0018\u00010\u001cX\u0096\u000f¢\u0006\f\u001a\u0004\b;\u0010\u001e\"\u0004\b<\u0010 R\u001a\u0010=\u001a\u0004\u0018\u00010\u001cX\u0096\u000f¢\u0006\f\u001a\u0004\b>\u0010\u001e\"\u0004\b?\u0010 R\u001a\u0010@\u001a\u0004\u0018\u00010\u001cX\u0096\u000f¢\u0006\f\u001a\u0004\bA\u0010\u001e\"\u0004\bB\u0010 ¨\u0006C"}, d2 = {"Lkotlinx/datetime/format/IncompleteLocalDateTime;", "Lkotlinx/datetime/format/DateTimeFieldContainer;", "Lkotlinx/datetime/format/DateFieldContainer;", "Lkotlinx/datetime/format/TimeFieldContainer;", "Lkotlinx/datetime/internal/format/parser/Copyable;", "date", "Lkotlinx/datetime/format/IncompleteLocalDate;", "time", "Lkotlinx/datetime/format/IncompleteLocalTime;", "<init>", "(Lkotlinx/datetime/format/IncompleteLocalDate;Lkotlinx/datetime/format/IncompleteLocalTime;)V", "getDate", "()Lkotlinx/datetime/format/IncompleteLocalDate;", "getTime", "()Lkotlinx/datetime/format/IncompleteLocalTime;", "toLocalDateTime", "Lkotlinx/datetime/LocalDateTime;", "populateFrom", "", "dateTime", "copy", "amPm", "Lkotlinx/datetime/format/AmPmMarker;", "getAmPm", "()Lkotlinx/datetime/format/AmPmMarker;", "setAmPm", "(Lkotlinx/datetime/format/AmPmMarker;)V", "day", "", "getDay", "()Ljava/lang/Integer;", "setDay", "(Ljava/lang/Integer;)V", "dayOfWeek", "getDayOfWeek", "setDayOfWeek", "dayOfYear", "getDayOfYear", "setDayOfYear", ES6Iterator.VALUE_PROPERTY, "Lkotlinx/datetime/internal/DecimalFraction;", "fractionOfSecond", "getFractionOfSecond", "()Lkotlinx/datetime/internal/DecimalFraction;", "setFractionOfSecond", "(Lkotlinx/datetime/internal/DecimalFraction;)V", "hour", "getHour", "setHour", "hourOfAmPm", "getHourOfAmPm", "setHourOfAmPm", "minute", "getMinute", "setMinute", "monthNumber", "getMonthNumber", "setMonthNumber", "nanosecond", "getNanosecond", "setNanosecond", "second", "getSecond", "setSecond", "year", "getYear", "setYear", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class IncompleteLocalDateTime implements DateTimeFieldContainer, DateFieldContainer, TimeFieldContainer, Copyable<IncompleteLocalDateTime> {
    private final IncompleteLocalDate date;
    private final IncompleteLocalTime time;

    /* JADX WARN: Multi-variable type inference failed */
    public IncompleteLocalDateTime() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public AmPmMarker getAmPm() {
        return this.time.getAmPm();
    }

    @Override // kotlinx.datetime.format.DateFieldContainer
    public Integer getDay() {
        return this.date.getDay();
    }

    @Override // kotlinx.datetime.format.DateFieldContainer
    public Integer getDayOfWeek() {
        return this.date.getDayOfWeek();
    }

    @Override // kotlinx.datetime.format.DateFieldContainer
    public Integer getDayOfYear() {
        return this.date.getDayOfYear();
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public DecimalFraction getFractionOfSecond() {
        return this.time.getFractionOfSecond();
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public Integer getHour() {
        return this.time.getHour();
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public Integer getHourOfAmPm() {
        return this.time.getHourOfAmPm();
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public Integer getMinute() {
        return this.time.getMinute();
    }

    @Override // kotlinx.datetime.format.YearMonthFieldContainer
    public Integer getMonthNumber() {
        return this.date.getMonthNumber();
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public Integer getNanosecond() {
        return this.time.getNanosecond();
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public Integer getSecond() {
        return this.time.getSecond();
    }

    @Override // kotlinx.datetime.format.YearMonthFieldContainer
    public Integer getYear() {
        return this.date.getYear();
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public void setAmPm(AmPmMarker amPmMarker) {
        this.time.setAmPm(amPmMarker);
    }

    @Override // kotlinx.datetime.format.DateFieldContainer
    public void setDay(Integer num) {
        this.date.setDay(num);
    }

    @Override // kotlinx.datetime.format.DateFieldContainer
    public void setDayOfWeek(Integer num) {
        this.date.setDayOfWeek(num);
    }

    @Override // kotlinx.datetime.format.DateFieldContainer
    public void setDayOfYear(Integer num) {
        this.date.setDayOfYear(num);
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public void setFractionOfSecond(DecimalFraction decimalFraction) {
        this.time.setFractionOfSecond(decimalFraction);
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public void setHour(Integer num) {
        this.time.setHour(num);
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public void setHourOfAmPm(Integer num) {
        this.time.setHourOfAmPm(num);
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public void setMinute(Integer num) {
        this.time.setMinute(num);
    }

    @Override // kotlinx.datetime.format.YearMonthFieldContainer
    public void setMonthNumber(Integer num) {
        this.date.setMonthNumber(num);
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public void setNanosecond(Integer num) {
        this.time.setNanosecond(num);
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public void setSecond(Integer num) {
        this.time.setSecond(num);
    }

    @Override // kotlinx.datetime.format.YearMonthFieldContainer
    public void setYear(Integer num) {
        this.date.setYear(num);
    }

    public IncompleteLocalDateTime(IncompleteLocalDate date, IncompleteLocalTime time) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(time, "time");
        this.date = date;
        this.time = time;
    }

    public /* synthetic */ IncompleteLocalDateTime(IncompleteLocalDate incompleteLocalDate, IncompleteLocalTime incompleteLocalTime, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new IncompleteLocalDate(null, null, null, null, 15, null) : incompleteLocalDate, (i & 2) != 0 ? new IncompleteLocalTime(null, null, null, null, null, null, 63, null) : incompleteLocalTime);
    }

    public final IncompleteLocalDate getDate() {
        return this.date;
    }

    public final IncompleteLocalTime getTime() {
        return this.time;
    }

    public final LocalDateTime toLocalDateTime() {
        return new LocalDateTime(this.date.toLocalDate(), this.time.toLocalTime());
    }

    public final void populateFrom(LocalDateTime dateTime) {
        Intrinsics.checkNotNullParameter(dateTime, "dateTime");
        this.date.populateFrom(dateTime.getDate());
        this.time.populateFrom(dateTime.getTime());
    }

    @Override // kotlinx.datetime.internal.format.parser.Copyable
    public IncompleteLocalDateTime copy() {
        return new IncompleteLocalDateTime(this.date.copy(), this.time.copy());
    }
}
