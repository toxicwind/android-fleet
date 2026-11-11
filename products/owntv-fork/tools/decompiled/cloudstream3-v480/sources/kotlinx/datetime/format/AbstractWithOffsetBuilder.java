package kotlinx.datetime.format;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.UtcOffset;
import kotlinx.datetime.internal.format.BasicFormatStructure;
import kotlinx.datetime.internal.format.FormatStructure;
import kotlinx.datetime.internal.format.SignedFormatStructure;

/* JADX INFO: compiled from: UtcOffsetFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016¨\u0006\u0010À\u0006\u0003"}, d2 = {"Lkotlinx/datetime/format/AbstractWithOffsetBuilder;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithUtcOffset;", "addFormatStructureForOffset", "", "structure", "Lkotlinx/datetime/internal/format/FormatStructure;", "Lkotlinx/datetime/format/UtcOffsetFieldContainer;", "offsetHours", "padding", "Lkotlinx/datetime/format/Padding;", "offsetMinutesOfHour", "offsetSecondsOfMinute", TypedValues.CycleType.S_WAVE_OFFSET, "format", "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/UtcOffset;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface AbstractWithOffsetBuilder extends DateTimeFormatBuilder.WithUtcOffset {
    void addFormatStructureForOffset(FormatStructure<? super UtcOffsetFieldContainer> structure);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithUtcOffset
    void offset(DateTimeFormat<UtcOffset> format);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithUtcOffset
    void offsetHours(Padding padding);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithUtcOffset
    void offsetMinutesOfHour(Padding padding);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithUtcOffset
    void offsetSecondsOfMinute(Padding padding);

    /* JADX INFO: renamed from: kotlinx.datetime.format.AbstractWithOffsetBuilder$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: UtcOffsetFormat.kt */
    public final /* synthetic */ class CC {
        public static void $default$offsetHours(AbstractWithOffsetBuilder _this, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            _this.addFormatStructureForOffset(new SignedFormatStructure(new BasicFormatStructure(new UtcOffsetWholeHoursDirective(padding)), true));
        }

        public static void $default$offsetMinutesOfHour(AbstractWithOffsetBuilder _this, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            _this.addFormatStructureForOffset(new BasicFormatStructure(new UtcOffsetMinuteOfHourDirective(padding)));
        }

        public static void $default$offsetSecondsOfMinute(AbstractWithOffsetBuilder _this, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            _this.addFormatStructureForOffset(new BasicFormatStructure(new UtcOffsetSecondOfMinuteDirective(padding)));
        }

        public static void $default$offset(AbstractWithOffsetBuilder _this, DateTimeFormat format) {
            Intrinsics.checkNotNullParameter(format, "format");
            _this.addFormatStructureForOffset(((UtcOffsetFormat) format).getActualFormat());
        }
    }

    /* JADX INFO: compiled from: UtcOffsetFormat.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void offsetHours(AbstractWithOffsetBuilder abstractWithOffsetBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            CC.$default$offsetHours(abstractWithOffsetBuilder, padding);
        }

        @Deprecated
        public static void offsetMinutesOfHour(AbstractWithOffsetBuilder abstractWithOffsetBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            CC.$default$offsetMinutesOfHour(abstractWithOffsetBuilder, padding);
        }

        @Deprecated
        public static void offsetSecondsOfMinute(AbstractWithOffsetBuilder abstractWithOffsetBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            CC.$default$offsetSecondsOfMinute(abstractWithOffsetBuilder, padding);
        }

        @Deprecated
        public static void offset(AbstractWithOffsetBuilder abstractWithOffsetBuilder, DateTimeFormat<UtcOffset> format) {
            Intrinsics.checkNotNullParameter(format, "format");
            CC.$default$offset(abstractWithOffsetBuilder, format);
        }
    }
}
