package kotlinx.datetime;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import j$.time.ZoneId;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.serializers.FixedOffsetTimeZoneSerializer;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: TimeZoneJvm.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0019\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0011\u001a\u00020\f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lkotlinx/datetime/FixedOffsetTimeZone;", "Lkotlinx/datetime/TimeZone;", "Lkotlinx/datetime/UtcOffset;", TypedValues.CycleType.S_WAVE_OFFSET, "j$/time/ZoneId", "zoneId", "<init>", "(Lkotlinx/datetime/UtcOffset;Lj$/time/ZoneId;)V", "(Lkotlinx/datetime/UtcOffset;)V", "Lkotlinx/datetime/UtcOffset;", "getOffset", "()Lkotlinx/datetime/UtcOffset;", "", "getTotalSeconds", "()I", "getTotalSeconds$annotations", "()V", "totalSeconds", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FixedOffsetTimeZone extends TimeZone {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final UtcOffset offset;

    @Deprecated(message = "Use offset.totalSeconds", replaceWith = @ReplaceWith(expression = "offset.totalSeconds", imports = {}))
    public static /* synthetic */ void getTotalSeconds$annotations() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FixedOffsetTimeZone(UtcOffset offset, ZoneId zoneId) {
        super(zoneId);
        Intrinsics.checkNotNullParameter(offset, "offset");
        Intrinsics.checkNotNullParameter(zoneId, "zoneId");
        this.offset = offset;
    }

    public final UtcOffset getOffset() {
        return this.offset;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FixedOffsetTimeZone(UtcOffset offset) {
        this(offset, offset.getZoneOffset());
        Intrinsics.checkNotNullParameter(offset, "offset");
    }

    public final int getTotalSeconds() {
        return this.offset.getTotalSeconds();
    }

    /* JADX INFO: compiled from: TimeZoneJvm.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¨\u0006\u0007"}, d2 = {"Lkotlinx/datetime/FixedOffsetTimeZone$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/datetime/FixedOffsetTimeZone;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Serializing FixedOffsetTimeZone is discouraged, as deserialization can fail or return a non-fixed-offset zone depending on the configuration. Please serialize the string id instead.")
        public final KSerializer<FixedOffsetTimeZone> serializer() {
            return FixedOffsetTimeZoneSerializer.INSTANCE;
        }
    }
}
