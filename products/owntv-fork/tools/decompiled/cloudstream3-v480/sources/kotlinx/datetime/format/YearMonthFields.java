package kotlinx.datetime.format;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlinx.datetime.internal.format.GenericFieldSpec;
import kotlinx.datetime.internal.format.PropertyAccessor;
import kotlinx.datetime.internal.format.UnsignedFieldSpec;

/* JADX INFO: compiled from: YearMonthFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lkotlinx/datetime/format/YearMonthFields;", "", "<init>", "()V", "year", "Lkotlinx/datetime/internal/format/GenericFieldSpec;", "Lkotlinx/datetime/format/YearMonthFieldContainer;", "", "getYear", "()Lkotlinx/datetime/internal/format/GenericFieldSpec;", "month", "Lkotlinx/datetime/internal/format/UnsignedFieldSpec;", "getMonth", "()Lkotlinx/datetime/internal/format/UnsignedFieldSpec;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class YearMonthFields {
    public static final YearMonthFields INSTANCE = new YearMonthFields();
    private static final GenericFieldSpec<YearMonthFieldContainer, Integer> year = new GenericFieldSpec<>(new PropertyAccessor(new MutablePropertyReference1Impl() { // from class: kotlinx.datetime.format.YearMonthFields$year$1
        @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KProperty1
        public Object get(Object obj) {
            return ((YearMonthFieldContainer) obj).getYear();
        }

        @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KMutableProperty1
        public void set(Object obj, Object obj2) {
            ((YearMonthFieldContainer) obj).setYear((Integer) obj2);
        }
    }, null, 2, null), null, null, null, 14, null);
    private static final UnsignedFieldSpec<YearMonthFieldContainer> month = new UnsignedFieldSpec<>(new PropertyAccessor(new MutablePropertyReference1Impl() { // from class: kotlinx.datetime.format.YearMonthFields$month$1
        @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KProperty1
        public Object get(Object obj) {
            return ((YearMonthFieldContainer) obj).getMonthNumber();
        }

        @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KMutableProperty1
        public void set(Object obj, Object obj2) {
            ((YearMonthFieldContainer) obj).setMonthNumber((Integer) obj2);
        }
    }, null, 2, null), 1, 12, null, null, null, 56, null);

    private YearMonthFields() {
    }

    public final GenericFieldSpec<YearMonthFieldContainer, Integer> getYear() {
        return year;
    }

    public final UnsignedFieldSpec<YearMonthFieldContainer> getMonth() {
        return month;
    }
}
