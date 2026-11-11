package kotlinx.datetime.format;

import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlinx.datetime.internal.format.Accessor;
import kotlinx.datetime.internal.format.FieldSign;
import kotlinx.datetime.internal.format.PropertyAccessor;
import kotlinx.datetime.internal.format.UnsignedFieldSpec;

/* JADX INFO: compiled from: UtcOffsetFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/OffsetFields;", "", "<init>", "()V", MediaTrack.ROLE_SIGN, "kotlinx/datetime/format/OffsetFields$sign$1", "Lkotlinx/datetime/format/OffsetFields$sign$1;", "totalHoursAbs", "Lkotlinx/datetime/internal/format/UnsignedFieldSpec;", "Lkotlinx/datetime/format/UtcOffsetFieldContainer;", "getTotalHoursAbs", "()Lkotlinx/datetime/internal/format/UnsignedFieldSpec;", "minutesOfHour", "getMinutesOfHour", "secondsOfMinute", "getSecondsOfMinute", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class OffsetFields {
    public static final OffsetFields INSTANCE = new OffsetFields();
    private static final UnsignedFieldSpec<UtcOffsetFieldContainer> minutesOfHour;
    private static final UnsignedFieldSpec<UtcOffsetFieldContainer> secondsOfMinute;
    private static final OffsetFields$sign$1 sign;
    private static final UnsignedFieldSpec<UtcOffsetFieldContainer> totalHoursAbs;

    private OffsetFields() {
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlinx.datetime.format.OffsetFields$sign$1] */
    static {
        ?? r0 = new FieldSign<UtcOffsetFieldContainer>() { // from class: kotlinx.datetime.format.OffsetFields$sign$1
            private final PropertyAccessor<UtcOffsetFieldContainer, Boolean> isNegative = new PropertyAccessor<>(new MutablePropertyReference1Impl() { // from class: kotlinx.datetime.format.OffsetFields$sign$1$isNegative$1
                @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KProperty1
                public Object get(Object obj) {
                    return ((UtcOffsetFieldContainer) obj).getOffsetIsNegative();
                }

                @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KMutableProperty1
                public void set(Object obj, Object obj2) {
                    ((UtcOffsetFieldContainer) obj).setOffsetIsNegative((Boolean) obj2);
                }
            }, null, 2, null);

            @Override // kotlinx.datetime.internal.format.FieldSign
            public Accessor<UtcOffsetFieldContainer, Boolean> isNegative() {
                return this.isNegative;
            }

            @Override // kotlinx.datetime.internal.format.FieldSign
            public boolean isZero(UtcOffsetFieldContainer obj) {
                Intrinsics.checkNotNullParameter(obj, "obj");
                Integer offsetHours = obj.getOffsetHours();
                if ((offsetHours != null ? offsetHours.intValue() : 0) == 0) {
                    Integer offsetMinutesOfHour = obj.getOffsetMinutesOfHour();
                    if ((offsetMinutesOfHour != null ? offsetMinutesOfHour.intValue() : 0) == 0) {
                        Integer offsetSecondsOfMinute = obj.getOffsetSecondsOfMinute();
                        if ((offsetSecondsOfMinute != null ? offsetSecondsOfMinute.intValue() : 0) == 0) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };
        sign = r0;
        totalHoursAbs = new UnsignedFieldSpec<>(new PropertyAccessor(new MutablePropertyReference1Impl() { // from class: kotlinx.datetime.format.OffsetFields$totalHoursAbs$1
            @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UtcOffsetFieldContainer) obj).getOffsetHours();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KMutableProperty1
            public void set(Object obj, Object obj2) {
                ((UtcOffsetFieldContainer) obj).setOffsetHours((Integer) obj2);
            }
        }, null, 2, null), 0, 18, null, 0, (FieldSign) r0, 8, null);
        minutesOfHour = new UnsignedFieldSpec<>(new PropertyAccessor(new MutablePropertyReference1Impl() { // from class: kotlinx.datetime.format.OffsetFields$minutesOfHour$1
            @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UtcOffsetFieldContainer) obj).getOffsetMinutesOfHour();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KMutableProperty1
            public void set(Object obj, Object obj2) {
                ((UtcOffsetFieldContainer) obj).setOffsetMinutesOfHour((Integer) obj2);
            }
        }, null, 2, null), 0, 59, null, 0, (FieldSign) r0, 8, null);
        secondsOfMinute = new UnsignedFieldSpec<>(new PropertyAccessor(new MutablePropertyReference1Impl() { // from class: kotlinx.datetime.format.OffsetFields$secondsOfMinute$1
            @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UtcOffsetFieldContainer) obj).getOffsetSecondsOfMinute();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KMutableProperty1
            public void set(Object obj, Object obj2) {
                ((UtcOffsetFieldContainer) obj).setOffsetSecondsOfMinute((Integer) obj2);
            }
        }, null, 2, null), 0, 59, null, 0, (FieldSign) r0, 8, null);
    }

    public final UnsignedFieldSpec<UtcOffsetFieldContainer> getTotalHoursAbs() {
        return totalHoursAbs;
    }

    public final UnsignedFieldSpec<UtcOffsetFieldContainer> getMinutesOfHour() {
        return minutesOfHour;
    }

    public final UnsignedFieldSpec<UtcOffsetFieldContainer> getSecondsOfMinute() {
        return secondsOfMinute;
    }
}
