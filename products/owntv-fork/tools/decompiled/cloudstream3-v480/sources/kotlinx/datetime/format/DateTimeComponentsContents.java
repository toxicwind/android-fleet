package kotlinx.datetime.format;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.DecimalFraction;
import kotlinx.datetime.internal.format.parser.Copyable;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DateTimeComponents.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b)\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\b\u0012\u0004\u0012\u00020\u00000\u0005B3\b\u0000\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u001a\u001a\u00020\u0000H\u0016J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0096\u0002J\b\u0010\u001f\u001a\u00020 H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010!\u001a\u0004\u0018\u00010\"X\u0096\u000f¢\u0006\f\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\b-\u0010)\"\u0004\b.\u0010+R\u001a\u0010/\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\b0\u0010)\"\u0004\b1\u0010+R(\u00104\u001a\u0004\u0018\u0001032\b\u00102\u001a\u0004\u0018\u0001038V@VX\u0096\u000f¢\u0006\f\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\b:\u0010)\"\u0004\b;\u0010+R\u001a\u0010<\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\b=\u0010)\"\u0004\b>\u0010+R\u001a\u0010?\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\b@\u0010)\"\u0004\bA\u0010+R\u001a\u0010B\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\bC\u0010)\"\u0004\bD\u0010+R\u001a\u0010E\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\bF\u0010)\"\u0004\bG\u0010+R\u001a\u0010H\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\bI\u0010)\"\u0004\bJ\u0010+R\u001a\u0010K\u001a\u0004\u0018\u00010\u001cX\u0096\u000f¢\u0006\f\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001a\u0010P\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\bQ\u0010)\"\u0004\bR\u0010+R\u001a\u0010S\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\bT\u0010)\"\u0004\bU\u0010+R\u001a\u0010V\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\bW\u0010)\"\u0004\bX\u0010+R\u001a\u0010Y\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\bZ\u0010)\"\u0004\b[\u0010+¨\u0006\\"}, d2 = {"Lkotlinx/datetime/format/DateTimeComponentsContents;", "Lkotlinx/datetime/format/DateFieldContainer;", "Lkotlinx/datetime/format/TimeFieldContainer;", "Lkotlinx/datetime/format/UtcOffsetFieldContainer;", "Lkotlinx/datetime/format/DateTimeFieldContainer;", "Lkotlinx/datetime/internal/format/parser/Copyable;", "date", "Lkotlinx/datetime/format/IncompleteLocalDate;", "time", "Lkotlinx/datetime/format/IncompleteLocalTime;", TypedValues.CycleType.S_WAVE_OFFSET, "Lkotlinx/datetime/format/IncompleteUtcOffset;", "timeZoneId", "", "<init>", "(Lkotlinx/datetime/format/IncompleteLocalDate;Lkotlinx/datetime/format/IncompleteLocalTime;Lkotlinx/datetime/format/IncompleteUtcOffset;Ljava/lang/String;)V", "getDate", "()Lkotlinx/datetime/format/IncompleteLocalDate;", "getTime", "()Lkotlinx/datetime/format/IncompleteLocalTime;", "getOffset", "()Lkotlinx/datetime/format/IncompleteUtcOffset;", "getTimeZoneId", "()Ljava/lang/String;", "setTimeZoneId", "(Ljava/lang/String;)V", "copy", "equals", "", "other", "", "hashCode", "", "amPm", "Lkotlinx/datetime/format/AmPmMarker;", "getAmPm", "()Lkotlinx/datetime/format/AmPmMarker;", "setAmPm", "(Lkotlinx/datetime/format/AmPmMarker;)V", "day", "getDay", "()Ljava/lang/Integer;", "setDay", "(Ljava/lang/Integer;)V", "dayOfWeek", "getDayOfWeek", "setDayOfWeek", "dayOfYear", "getDayOfYear", "setDayOfYear", ES6Iterator.VALUE_PROPERTY, "Lkotlinx/datetime/internal/DecimalFraction;", "fractionOfSecond", "getFractionOfSecond", "()Lkotlinx/datetime/internal/DecimalFraction;", "setFractionOfSecond", "(Lkotlinx/datetime/internal/DecimalFraction;)V", "hour", "getHour", "setHour", "hourOfAmPm", "getHourOfAmPm", "setHourOfAmPm", "minute", "getMinute", "setMinute", "monthNumber", "getMonthNumber", "setMonthNumber", "nanosecond", "getNanosecond", "setNanosecond", "offsetHours", "getOffsetHours", "setOffsetHours", "offsetIsNegative", "getOffsetIsNegative", "()Ljava/lang/Boolean;", "setOffsetIsNegative", "(Ljava/lang/Boolean;)V", "offsetMinutesOfHour", "getOffsetMinutesOfHour", "setOffsetMinutesOfHour", "offsetSecondsOfMinute", "getOffsetSecondsOfMinute", "setOffsetSecondsOfMinute", "second", "getSecond", "setSecond", "year", "getYear", "setYear", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DateTimeComponentsContents implements DateFieldContainer, TimeFieldContainer, UtcOffsetFieldContainer, DateTimeFieldContainer, Copyable<DateTimeComponentsContents> {
    private final IncompleteLocalDate date;
    private final IncompleteUtcOffset offset;
    private final IncompleteLocalTime time;
    private String timeZoneId;

    public DateTimeComponentsContents() {
        this(null, null, null, null, 15, null);
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

    @Override // kotlinx.datetime.format.UtcOffsetFieldContainer
    public Integer getOffsetHours() {
        return this.offset.getOffsetHours();
    }

    @Override // kotlinx.datetime.format.UtcOffsetFieldContainer
    public Boolean getOffsetIsNegative() {
        return this.offset.getOffsetIsNegative();
    }

    @Override // kotlinx.datetime.format.UtcOffsetFieldContainer
    public Integer getOffsetMinutesOfHour() {
        return this.offset.getOffsetMinutesOfHour();
    }

    @Override // kotlinx.datetime.format.UtcOffsetFieldContainer
    public Integer getOffsetSecondsOfMinute() {
        return this.offset.getOffsetSecondsOfMinute();
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

    @Override // kotlinx.datetime.format.UtcOffsetFieldContainer
    public void setOffsetHours(Integer num) {
        this.offset.setOffsetHours(num);
    }

    @Override // kotlinx.datetime.format.UtcOffsetFieldContainer
    public void setOffsetIsNegative(Boolean bool) {
        this.offset.setOffsetIsNegative(bool);
    }

    @Override // kotlinx.datetime.format.UtcOffsetFieldContainer
    public void setOffsetMinutesOfHour(Integer num) {
        this.offset.setOffsetMinutesOfHour(num);
    }

    @Override // kotlinx.datetime.format.UtcOffsetFieldContainer
    public void setOffsetSecondsOfMinute(Integer num) {
        this.offset.setOffsetSecondsOfMinute(num);
    }

    @Override // kotlinx.datetime.format.TimeFieldContainer
    public void setSecond(Integer num) {
        this.time.setSecond(num);
    }

    @Override // kotlinx.datetime.format.YearMonthFieldContainer
    public void setYear(Integer num) {
        this.date.setYear(num);
    }

    public DateTimeComponentsContents(IncompleteLocalDate date, IncompleteLocalTime time, IncompleteUtcOffset offset, String str) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(offset, "offset");
        this.date = date;
        this.time = time;
        this.offset = offset;
        this.timeZoneId = str;
    }

    public /* synthetic */ DateTimeComponentsContents(IncompleteLocalDate incompleteLocalDate, IncompleteLocalTime incompleteLocalTime, IncompleteUtcOffset incompleteUtcOffset, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new IncompleteLocalDate(null, null, null, null, 15, null) : incompleteLocalDate, (i & 2) != 0 ? new IncompleteLocalTime(null, null, null, null, null, null, 63, null) : incompleteLocalTime, (i & 4) != 0 ? new IncompleteUtcOffset(null, null, null, null, 15, null) : incompleteUtcOffset, (i & 8) != 0 ? null : str);
    }

    public final IncompleteLocalDate getDate() {
        return this.date;
    }

    public final IncompleteLocalTime getTime() {
        return this.time;
    }

    public final IncompleteUtcOffset getOffset() {
        return this.offset;
    }

    public final String getTimeZoneId() {
        return this.timeZoneId;
    }

    public final void setTimeZoneId(String str) {
        this.timeZoneId = str;
    }

    @Override // kotlinx.datetime.internal.format.parser.Copyable
    public DateTimeComponentsContents copy() {
        return new DateTimeComponentsContents(this.date.copy(), this.time.copy(), this.offset.copy(), this.timeZoneId);
    }

    public boolean equals(Object other) {
        if (!(other instanceof DateTimeComponentsContents)) {
            return false;
        }
        DateTimeComponentsContents dateTimeComponentsContents = (DateTimeComponentsContents) other;
        return Intrinsics.areEqual(dateTimeComponentsContents.date, this.date) && Intrinsics.areEqual(dateTimeComponentsContents.time, this.time) && Intrinsics.areEqual(dateTimeComponentsContents.offset, this.offset) && Intrinsics.areEqual(dateTimeComponentsContents.timeZoneId, this.timeZoneId);
    }

    public int hashCode() {
        int iHashCode = (this.date.hashCode() ^ this.time.hashCode()) ^ this.offset.hashCode();
        String str = this.timeZoneId;
        return (str != null ? str.hashCode() : 0) ^ iHashCode;
    }
}
