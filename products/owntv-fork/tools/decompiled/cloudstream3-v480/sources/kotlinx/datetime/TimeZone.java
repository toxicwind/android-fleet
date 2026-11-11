package kotlinx.datetime;

import j$.time.DateTimeException;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Instant;
import kotlinx.datetime.serializers.TimeZoneSerializer;
import kotlinx.serialization.KSerializer;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: TimeZoneJvm.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0016\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\b\u001a\u00020\u0007*\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\f\u001a\u00020\u0006*\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001c\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017¨\u0006\u001e"}, d2 = {"Lkotlinx/datetime/TimeZone;", "", "j$/time/ZoneId", "zoneId", "<init>", "(Lj$/time/ZoneId;)V", "Lkotlin/time/Instant;", "Lkotlinx/datetime/LocalDateTime;", "toLocalDateTime", "(Lkotlin/time/Instant;)Lkotlinx/datetime/LocalDateTime;", "Lkotlinx/datetime/OverloadMarker;", "youShallNotPass", "toInstant", "(Lkotlinx/datetime/LocalDateTime;Lkotlinx/datetime/OverloadMarker;)Lkotlin/time/Instant;", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lj$/time/ZoneId;", "getZoneId$kotlinx_datetime", "()Lj$/time/ZoneId;", "getId", "id", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class TimeZone {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final FixedOffsetTimeZone UTC;
    private final ZoneId zoneId;

    public TimeZone(ZoneId zoneId) {
        Intrinsics.checkNotNullParameter(zoneId, "zoneId");
        this.zoneId = zoneId;
    }

    /* JADX INFO: renamed from: getZoneId$kotlinx_datetime, reason: from getter */
    public final ZoneId getZoneId() {
        return this.zoneId;
    }

    public final String getId() {
        String id = this.zoneId.getId();
        Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
        return id;
    }

    public final LocalDateTime toLocalDateTime(Instant instant) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        return TimeZoneKt.toLocalDateTime(instant, this);
    }

    public final Instant toInstant(LocalDateTime localDateTime, OverloadMarker youShallNotPass) {
        Intrinsics.checkNotNullParameter(localDateTime, "<this>");
        Intrinsics.checkNotNullParameter(youShallNotPass, "youShallNotPass");
        return TimeZoneKt__TimeZoneJvmKt.toInstant$default(localDateTime, this, (OverloadMarker) null, 2, (Object) null);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof TimeZone) && Intrinsics.areEqual(this.zoneId, ((TimeZone) other).zoneId);
        }
        return true;
    }

    public int hashCode() {
        return this.zoneId.hashCode();
    }

    public String toString() {
        String string = this.zoneId.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: compiled from: TimeZoneJvm.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u000fH\u0007¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Lkotlinx/datetime/TimeZone$Companion;", "", "<init>", "()V", "Lkotlinx/datetime/TimeZone;", "currentSystemDefault", "()Lkotlinx/datetime/TimeZone;", "", "zoneId", "of", "(Ljava/lang/String;)Lkotlinx/datetime/TimeZone;", "j$/time/ZoneId", "ofZone$kotlinx_datetime", "(Lj$/time/ZoneId;)Lkotlinx/datetime/TimeZone;", "ofZone", "Lkotlinx/serialization/KSerializer;", "serializer", "()Lkotlinx/serialization/KSerializer;", "Lkotlinx/datetime/FixedOffsetTimeZone;", "UTC", "Lkotlinx/datetime/FixedOffsetTimeZone;", "getUTC", "()Lkotlinx/datetime/FixedOffsetTimeZone;", "", "getAvailableZoneIds", "()Ljava/util/Set;", "availableZoneIds", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TimeZone currentSystemDefault() {
            ZoneId zoneIdSystemDefault = ZoneId.systemDefault();
            Intrinsics.checkNotNullExpressionValue(zoneIdSystemDefault, "systemDefault(...)");
            return ofZone$kotlinx_datetime(zoneIdSystemDefault);
        }

        public final FixedOffsetTimeZone getUTC() {
            return TimeZone.UTC;
        }

        public final TimeZone of(String zoneId) throws Exception {
            Intrinsics.checkNotNullParameter(zoneId, "zoneId");
            try {
                if (Intrinsics.areEqual(zoneId, "z")) {
                    zoneId = "Z";
                }
                ZoneId zoneIdOf = ZoneId.of(zoneId);
                Intrinsics.checkNotNullExpressionValue(zoneIdOf, "of(...)");
                return ofZone$kotlinx_datetime(zoneIdOf);
            } catch (Exception e) {
                if (e instanceof DateTimeException) {
                    throw new IllegalTimeZoneException(e);
                }
                throw e;
            }
        }

        public final TimeZone ofZone$kotlinx_datetime(ZoneId zoneId) {
            Intrinsics.checkNotNullParameter(zoneId, "zoneId");
            if (!(zoneId instanceof ZoneOffset)) {
                if (TimeZoneKt__TimeZoneJvmKt.isFixedOffset$TimeZoneKt__TimeZoneJvmKt(zoneId)) {
                    ZoneId zoneIdNormalized = zoneId.normalized();
                    Intrinsics.checkNotNull(zoneIdNormalized, "null cannot be cast to non-null type java.time.ZoneOffset");
                    return new FixedOffsetTimeZone(new UtcOffset((ZoneOffset) zoneIdNormalized), zoneId);
                }
                return new TimeZone(zoneId);
            }
            return new FixedOffsetTimeZone(new UtcOffset((ZoneOffset) zoneId));
        }

        public final Set<String> getAvailableZoneIds() {
            Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
            Intrinsics.checkNotNullExpressionValue(availableZoneIds, "getAvailableZoneIds(...)");
            return availableZoneIds;
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Serializing TimeZone is discouraged, as deserialization can fail depending on the configuration. Please serialize the string id instead.")
        public final KSerializer<TimeZone> serializer() {
            return TimeZoneSerializer.INSTANCE;
        }
    }

    static {
        UtcOffset zero = UtcOffset.INSTANCE.getZERO();
        ZoneId zoneIdOf = ZoneId.of("UTC");
        Intrinsics.checkNotNullExpressionValue(zoneIdOf, "of(...)");
        UTC = new FixedOffsetTimeZone(zero, zoneIdOf);
    }

    public static /* synthetic */ Instant toInstant$default(TimeZone timeZone, LocalDateTime localDateTime, OverloadMarker overloadMarker, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toInstant");
        }
        if ((i & 1) != 0) {
            overloadMarker = OverloadMarker.INSTANCE.getINSTANCE$kotlinx_datetime();
        }
        return timeZone.toInstant(localDateTime, overloadMarker);
    }
}
