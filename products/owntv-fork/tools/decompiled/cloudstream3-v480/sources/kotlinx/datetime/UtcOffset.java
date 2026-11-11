package kotlinx.datetime;

import androidx.exifinterface.media.ExifInterface;
import androidx.tvprovider.media.tv.TvContractCompat;
import j$.time.ZoneOffset;
import j$.time.format.DateTimeFormatter;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;
import kotlinx.datetime.format.DateTimeFormatBuilder;
import kotlinx.datetime.format.UtcOffsetFormat;
import kotlinx.datetime.format.UtcOffsetFormatKt;
import kotlinx.datetime.serializers.UtcOffsetSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: UtcOffsetJvm.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001d\u001eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u000bH\u0096\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001c\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0010¨\u0006\u001f"}, d2 = {"Lkotlinx/datetime/UtcOffset;", "Ljava/io/Serializable;", "j$/time/ZoneOffset", "zoneOffset", "<init>", "(Lj$/time/ZoneOffset;)V", "Ljava/io/ObjectInputStream;", "ois", "", "readObject", "(Ljava/io/ObjectInputStream;)V", "", "writeReplace", "()Ljava/lang/Object;", "", "hashCode", "()I", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "toString", "()Ljava/lang/String;", "Lj$/time/ZoneOffset;", "getZoneOffset$kotlinx_datetime", "()Lj$/time/ZoneOffset;", "getTotalSeconds", "totalSeconds", "Companion", "Formats", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@Serializable(with = UtcOffsetSerializer.class)
public final class UtcOffset implements java.io.Serializable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final UtcOffset ZERO;
    private static final long serialVersionUID = 0;
    private final ZoneOffset zoneOffset;

    public UtcOffset(ZoneOffset zoneOffset) {
        Intrinsics.checkNotNullParameter(zoneOffset, "zoneOffset");
        this.zoneOffset = zoneOffset;
    }

    /* JADX INFO: renamed from: getZoneOffset$kotlinx_datetime, reason: from getter */
    public final ZoneOffset getZoneOffset() {
        return this.zoneOffset;
    }

    public final int getTotalSeconds() {
        return this.zoneOffset.getTotalSeconds();
    }

    public int hashCode() {
        return this.zoneOffset.hashCode();
    }

    public boolean equals(Object other) {
        return (other instanceof UtcOffset) && Intrinsics.areEqual(this.zoneOffset, ((UtcOffset) other).zoneOffset);
    }

    public String toString() {
        String string = this.zoneOffset.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: compiled from: UtcOffsetJvm.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J%\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u0017¢\u0006\u0002\b\u001aJ\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\u001eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lkotlinx/datetime/UtcOffset$Companion;", "", "<init>", "()V", "ZERO", "Lkotlinx/datetime/UtcOffset;", "getZERO", "()Lkotlinx/datetime/UtcOffset;", "orNull", "hours", "", "minutes", "seconds", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lkotlinx/datetime/UtcOffset;", "parse", TvContractCompat.PARAM_INPUT, "", "format", "Lkotlinx/datetime/format/DateTimeFormat;", "offsetString", "", "Format", "block", "Lkotlin/Function1;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithUtcOffset;", "", "Lkotlin/ExtensionFunctionType;", "serialVersionUID", "", "serializer", "Lkotlinx/serialization/KSerializer;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<UtcOffset> serializer() {
            return UtcOffsetSerializer.INSTANCE;
        }

        public final UtcOffset getZERO() {
            return UtcOffset.ZERO;
        }

        public static /* synthetic */ UtcOffset orNull$default(Companion companion, Integer num, Integer num2, Integer num3, int i, Object obj) {
            if ((i & 1) != 0) {
                num = null;
            }
            if ((i & 2) != 0) {
                num2 = null;
            }
            if ((i & 4) != 0) {
                num3 = null;
            }
            return companion.orNull(num, num2, num3);
        }

        public final UtcOffset orNull(Integer hours, Integer minutes, Integer seconds) {
            try {
                return UtcOffsetJvmKt.UtcOffset(hours, minutes, seconds);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        public final UtcOffset parse(CharSequence input, DateTimeFormat<UtcOffset> format) {
            Intrinsics.checkNotNullParameter(input, "input");
            Intrinsics.checkNotNullParameter(format, "format");
            if (format == Formats.INSTANCE.getISO()) {
                DateTimeFormatter isoFormat = UtcOffsetJvmKt.getIsoFormat();
                Intrinsics.checkNotNullExpressionValue(isoFormat, "access$getIsoFormat(...)");
                return UtcOffsetJvmKt.parseWithFormat(input, isoFormat);
            }
            if (format == Formats.INSTANCE.getISO_BASIC()) {
                DateTimeFormatter isoBasicFormat = UtcOffsetJvmKt.getIsoBasicFormat();
                Intrinsics.checkNotNullExpressionValue(isoBasicFormat, "access$getIsoBasicFormat(...)");
                return UtcOffsetJvmKt.parseWithFormat(input, isoBasicFormat);
            }
            if (format == Formats.INSTANCE.getFOUR_DIGITS()) {
                DateTimeFormatter fourDigitsFormat = UtcOffsetJvmKt.getFourDigitsFormat();
                Intrinsics.checkNotNullExpressionValue(fourDigitsFormat, "access$getFourDigitsFormat(...)");
                return UtcOffsetJvmKt.parseWithFormat(input, fourDigitsFormat);
            }
            return format.parse(input);
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This overload is only kept for binary compatibility")
        public final /* synthetic */ UtcOffset parse(String offsetString) {
            Intrinsics.checkNotNullParameter(offsetString, "offsetString");
            return parse$default(this, offsetString, null, 2, null);
        }

        public final DateTimeFormat<UtcOffset> Format(Function1<? super DateTimeFormatBuilder.WithUtcOffset, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            return UtcOffsetFormat.INSTANCE.build(block);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ UtcOffset parse$default(Companion companion, CharSequence charSequence, DateTimeFormat dateTimeFormat, int i, Object obj) {
            if ((i & 2) != 0) {
                dateTimeFormat = UtcOffsetKt.getIsoUtcOffsetFormat();
            }
            return companion.parse(charSequence, dateTimeFormat);
        }
    }

    static {
        ZoneOffset UTC = ZoneOffset.UTC;
        Intrinsics.checkNotNullExpressionValue(UTC, "UTC");
        ZERO = new UtcOffset(UTC);
    }

    /* JADX INFO: compiled from: UtcOffsetJvm.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lkotlinx/datetime/UtcOffset$Formats;", "", "<init>", "()V", ExifInterface.TAG_RW2_ISO, "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/UtcOffset;", "getISO", "()Lkotlinx/datetime/format/DateTimeFormat;", "ISO_BASIC", "getISO_BASIC", "FOUR_DIGITS", "getFOUR_DIGITS", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Formats {
        public static final Formats INSTANCE = new Formats();

        private Formats() {
        }

        public final DateTimeFormat<UtcOffset> getISO() {
            return UtcOffsetFormatKt.getISO_OFFSET();
        }

        public final DateTimeFormat<UtcOffset> getISO_BASIC() {
            return UtcOffsetFormatKt.getISO_OFFSET_BASIC();
        }

        public final DateTimeFormat<UtcOffset> getFOUR_DIGITS() {
            return UtcOffsetFormatKt.getFOUR_DIGIT_OFFSET();
        }
    }

    private final void readObject(ObjectInputStream ois) throws InvalidObjectException {
        throw new InvalidObjectException("kotlinx.datetime.UtcOffset must be deserialized via kotlinx.datetime.Ser");
    }

    private final Object writeReplace() {
        return new Ser(10, this);
    }
}
