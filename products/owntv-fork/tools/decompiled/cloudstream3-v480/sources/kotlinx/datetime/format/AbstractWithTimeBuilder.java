package kotlinx.datetime.format;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.LocalTime;
import kotlinx.datetime.internal.format.BasicFormatStructure;
import kotlinx.datetime.internal.format.FormatStructure;

/* JADX INFO: compiled from: LocalTimeFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0016\u0010\u0015\u001a\u00020\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016¨\u0006\u0019À\u0006\u0003"}, d2 = {"Lkotlinx/datetime/format/AbstractWithTimeBuilder;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithTime;", "addFormatStructureForTime", "", "structure", "Lkotlinx/datetime/internal/format/FormatStructure;", "Lkotlinx/datetime/format/TimeFieldContainer;", "hour", "padding", "Lkotlinx/datetime/format/Padding;", "amPmHour", "amPmMarker", "am", "", "pm", "minute", "second", "secondFraction", "minLength", "", "maxLength", "time", "format", "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/LocalTime;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface AbstractWithTimeBuilder extends DateTimeFormatBuilder.WithTime {
    void addFormatStructureForTime(FormatStructure<? super TimeFieldContainer> structure);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
    void amPmHour(Padding padding);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
    void amPmMarker(String am, String pm);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
    void hour(Padding padding);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
    void minute(Padding padding);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
    void second(Padding padding);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
    void secondFraction(int minLength, int maxLength);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
    void time(DateTimeFormat<LocalTime> format);

    /* JADX INFO: renamed from: kotlinx.datetime.format.AbstractWithTimeBuilder$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: LocalTimeFormat.kt */
    public final /* synthetic */ class CC {
        public static void $default$hour(AbstractWithTimeBuilder _this, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            _this.addFormatStructureForTime(new BasicFormatStructure(new HourDirective(padding)));
        }

        public static void $default$amPmHour(AbstractWithTimeBuilder _this, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            _this.addFormatStructureForTime(new BasicFormatStructure(new AmPmHourDirective(padding)));
        }

        public static void $default$amPmMarker(AbstractWithTimeBuilder _this, String am, String pm) {
            Intrinsics.checkNotNullParameter(am, "am");
            Intrinsics.checkNotNullParameter(pm, "pm");
            _this.addFormatStructureForTime(new BasicFormatStructure(new AmPmMarkerDirective(am, pm)));
        }

        public static void $default$minute(AbstractWithTimeBuilder _this, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            _this.addFormatStructureForTime(new BasicFormatStructure(new MinuteDirective(padding)));
        }

        public static void $default$second(AbstractWithTimeBuilder _this, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            _this.addFormatStructureForTime(new BasicFormatStructure(new SecondDirective(padding)));
        }

        public static void $default$secondFraction(AbstractWithTimeBuilder _this, int i, int i2) {
            _this.addFormatStructureForTime(new BasicFormatStructure(new FractionalSecondDirective(i, i2, null, 4, null)));
        }

        public static void $default$time(AbstractWithTimeBuilder _this, DateTimeFormat format) {
            Intrinsics.checkNotNullParameter(format, "format");
            _this.addFormatStructureForTime(((LocalTimeFormat) format).getActualFormat());
        }
    }

    /* JADX INFO: compiled from: LocalTimeFormat.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void secondFraction(AbstractWithTimeBuilder abstractWithTimeBuilder, int i) {
            abstractWithTimeBuilder.secondFraction(i, i);
        }

        @Deprecated
        public static void hour(AbstractWithTimeBuilder abstractWithTimeBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            CC.$default$hour(abstractWithTimeBuilder, padding);
        }

        @Deprecated
        public static void amPmHour(AbstractWithTimeBuilder abstractWithTimeBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            CC.$default$amPmHour(abstractWithTimeBuilder, padding);
        }

        @Deprecated
        public static void amPmMarker(AbstractWithTimeBuilder abstractWithTimeBuilder, String am, String pm) {
            Intrinsics.checkNotNullParameter(am, "am");
            Intrinsics.checkNotNullParameter(pm, "pm");
            CC.$default$amPmMarker(abstractWithTimeBuilder, am, pm);
        }

        @Deprecated
        public static void minute(AbstractWithTimeBuilder abstractWithTimeBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            CC.$default$minute(abstractWithTimeBuilder, padding);
        }

        @Deprecated
        public static void second(AbstractWithTimeBuilder abstractWithTimeBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            CC.$default$second(abstractWithTimeBuilder, padding);
        }

        @Deprecated
        public static void secondFraction(AbstractWithTimeBuilder abstractWithTimeBuilder, int i, int i2) {
            CC.$default$secondFraction(abstractWithTimeBuilder, i, i2);
        }

        @Deprecated
        public static void time(AbstractWithTimeBuilder abstractWithTimeBuilder, DateTimeFormat<LocalTime> format) {
            Intrinsics.checkNotNullParameter(format, "format");
            CC.$default$time(abstractWithTimeBuilder, format);
        }
    }
}
