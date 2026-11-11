package kotlinx.datetime.format;

import kotlin.Metadata;

/* JADX INFO: compiled from: UtcOffsetFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\b`\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00010\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u0004\u0018\u00010\tX¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001a\u0010\u0011\u001a\u0004\u0018\u00010\tX¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\r¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lkotlinx/datetime/format/UtcOffsetFieldContainer;", "", "offsetIsNegative", "", "getOffsetIsNegative", "()Ljava/lang/Boolean;", "setOffsetIsNegative", "(Ljava/lang/Boolean;)V", "offsetHours", "", "getOffsetHours", "()Ljava/lang/Integer;", "setOffsetHours", "(Ljava/lang/Integer;)V", "offsetMinutesOfHour", "getOffsetMinutesOfHour", "setOffsetMinutesOfHour", "offsetSecondsOfMinute", "getOffsetSecondsOfMinute", "setOffsetSecondsOfMinute", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface UtcOffsetFieldContainer {
    Integer getOffsetHours();

    Boolean getOffsetIsNegative();

    Integer getOffsetMinutesOfHour();

    Integer getOffsetSecondsOfMinute();

    void setOffsetHours(Integer num);

    void setOffsetIsNegative(Boolean bool);

    void setOffsetMinutesOfHour(Integer num);

    void setOffsetSecondsOfMinute(Integer num);
}
