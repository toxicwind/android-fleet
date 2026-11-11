package kotlinx.datetime.format;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlinx.datetime.internal.format.PropertyAccessor;
import kotlinx.datetime.internal.format.UnsignedFieldSpec;

/* JADX INFO: compiled from: LocalDateFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lkotlinx/datetime/format/DateFields;", "", "<init>", "()V", "day", "Lkotlinx/datetime/internal/format/UnsignedFieldSpec;", "Lkotlinx/datetime/format/DateFieldContainer;", "getDay", "()Lkotlinx/datetime/internal/format/UnsignedFieldSpec;", "isoDayOfWeek", "getIsoDayOfWeek", "dayOfYear", "getDayOfYear", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class DateFields {
    public static final DateFields INSTANCE = new DateFields();
    private static final UnsignedFieldSpec<DateFieldContainer> day = new UnsignedFieldSpec<>(new PropertyAccessor(new MutablePropertyReference1Impl() { // from class: kotlinx.datetime.format.DateFields$day$1
        @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KProperty1
        public Object get(Object obj) {
            return ((DateFieldContainer) obj).getDay();
        }

        @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KMutableProperty1
        public void set(Object obj, Object obj2) {
            ((DateFieldContainer) obj).setDay((Integer) obj2);
        }
    }, null, 2, null), 1, 31, null, null, null, 56, null);
    private static final UnsignedFieldSpec<DateFieldContainer> isoDayOfWeek = new UnsignedFieldSpec<>(new PropertyAccessor(new MutablePropertyReference1Impl() { // from class: kotlinx.datetime.format.DateFields$isoDayOfWeek$1
        @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KProperty1
        public Object get(Object obj) {
            return ((DateFieldContainer) obj).getDayOfWeek();
        }

        @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KMutableProperty1
        public void set(Object obj, Object obj2) {
            ((DateFieldContainer) obj).setDayOfWeek((Integer) obj2);
        }
    }, null, 2, null), 1, 7, null, null, null, 56, null);
    private static final UnsignedFieldSpec<DateFieldContainer> dayOfYear = new UnsignedFieldSpec<>(new PropertyAccessor(new MutablePropertyReference1Impl() { // from class: kotlinx.datetime.format.DateFields$dayOfYear$1
        @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KProperty1
        public Object get(Object obj) {
            return ((DateFieldContainer) obj).getDayOfYear();
        }

        @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KMutableProperty1
        public void set(Object obj, Object obj2) {
            ((DateFieldContainer) obj).setDayOfYear((Integer) obj2);
        }
    }, null, 2, null), 1, 366, null, null, null, 56, null);

    private DateFields() {
    }

    public final UnsignedFieldSpec<DateFieldContainer> getDay() {
        return day;
    }

    public final UnsignedFieldSpec<DateFieldContainer> getIsoDayOfWeek() {
        return isoDayOfWeek;
    }

    public final UnsignedFieldSpec<DateFieldContainer> getDayOfYear() {
        return dayOfYear;
    }
}
