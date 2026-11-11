package kotlinx.datetime.format;

import kotlin.Metadata;

/* JADX INFO: compiled from: YearMonthFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b`\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lkotlinx/datetime/format/YearMonthFieldContainer;", "", "year", "", "getYear", "()Ljava/lang/Integer;", "setYear", "(Ljava/lang/Integer;)V", "monthNumber", "getMonthNumber", "setMonthNumber", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface YearMonthFieldContainer {
    Integer getMonthNumber();

    Integer getYear();

    void setMonthNumber(Integer num);

    void setYear(Integer num);
}
