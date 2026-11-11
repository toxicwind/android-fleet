package j$.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.net.HttpHeaders;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalQueries;
import j$.time.zone.ZoneRules;
import j$.time.zone.ZoneRulesException;
import j$.time.zone.ZoneRulesProvider;
import j$.util.DesugarTimeZone;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ZoneId implements Serializable {
    public static final Map SHORT_IDS = AnonymousClass2.m(new Map.Entry[]{AnonymousClass0.m("ACT", "Australia/Darwin"), AnonymousClass0.m("AET", "Australia/Sydney"), AnonymousClass0.m("AGT", "America/Argentina/Buenos_Aires"), AnonymousClass0.m("ART", "Africa/Cairo"), AnonymousClass0.m("AST", "America/Anchorage"), AnonymousClass0.m("BET", "America/Sao_Paulo"), AnonymousClass0.m("BST", "Asia/Dhaka"), AnonymousClass0.m("CAT", "Africa/Harare"), AnonymousClass0.m("CNT", "America/St_Johns"), AnonymousClass0.m("CST", "America/Chicago"), AnonymousClass0.m("CTT", "Asia/Shanghai"), AnonymousClass0.m("EAT", "Africa/Addis_Ababa"), AnonymousClass0.m(HttpHeaders.ECT, "Europe/Paris"), AnonymousClass0.m("IET", "America/Indiana/Indianapolis"), AnonymousClass0.m("IST", "Asia/Kolkata"), AnonymousClass0.m("JST", "Asia/Tokyo"), AnonymousClass0.m("MIT", "Pacific/Apia"), AnonymousClass0.m("NET", "Asia/Yerevan"), AnonymousClass0.m("NST", "Pacific/Auckland"), AnonymousClass0.m("PLT", "Asia/Karachi"), AnonymousClass0.m("PNT", "America/Phoenix"), AnonymousClass0.m("PRT", "America/Puerto_Rico"), AnonymousClass0.m("PST", "America/Los_Angeles"), AnonymousClass0.m("SST", "Pacific/Guadalcanal"), AnonymousClass0.m("VST", "Asia/Ho_Chi_Minh"), AnonymousClass0.m("EST", "-05:00"), AnonymousClass0.m("MST", "-07:00"), AnonymousClass0.m("HST", "-10:00")});
    private static final long serialVersionUID = 8352817235686L;

    /* JADX INFO: renamed from: j$.time.ZoneId$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ Map.Entry m(Object obj, Object obj2) {
            return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(obj), Objects.requireNonNull(obj2));
        }
    }

    /* JADX INFO: renamed from: j$.time.ZoneId$2, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass2 {

        /* JADX INFO: renamed from: j$.time.ZoneId$2$0, reason: invalid class name */
        public final /* synthetic */ class AnonymousClass0 {
            public static /* synthetic */ void m(String str, Object obj) {
                throw new IllegalArgumentException(str + obj);
            }
        }

        public static /* synthetic */ Map m(Map.Entry[] entryArr) {
            HashMap map = new HashMap(entryArr.length);
            for (Map.Entry entry : entryArr) {
                Object objRequireNonNull = Objects.requireNonNull(entry.getKey());
                if (map.put(objRequireNonNull, Objects.requireNonNull(entry.getValue())) != null) {
                    AnonymousClass0.m("duplicate key: ", objRequireNonNull);
                    return null;
                }
            }
            return Collections.unmodifiableMap(map);
        }
    }

    /* JADX INFO: renamed from: j$.time.ZoneId$3, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass3 {
        public static /* synthetic */ void m(String str, Object obj, Object obj2, Object obj3) {
            throw new DateTimeException(str + obj + obj2 + obj3);
        }
    }

    /* JADX INFO: renamed from: j$.time.ZoneId$4, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass4 {
        public static /* synthetic */ void m(String str) {
            throw new IllegalArgumentException(str);
        }
    }

    public abstract String getId();

    public abstract ZoneRules getRules();

    abstract void write(DataOutput dataOutput);

    public static ZoneId systemDefault() {
        return DesugarTimeZone.toZoneId(TimeZone.getDefault());
    }

    public static Set<String> getAvailableZoneIds() {
        return new HashSet(ZoneRulesProvider.getAvailableZoneIds());
    }

    public static ZoneId of(String str, Map map) {
        Objects.requireNonNull(str, "zoneId");
        Objects.requireNonNull(map, "aliasMap");
        return of((String) Objects.requireNonNullElse((String) map.get(str), str));
    }

    public static ZoneId of(String str) {
        return of(str, true);
    }

    public static ZoneId ofOffset(String str, ZoneOffset zoneOffset) {
        Objects.requireNonNull(str, "prefix");
        Objects.requireNonNull(zoneOffset, TypedValues.CycleType.S_WAVE_OFFSET);
        if (str.isEmpty()) {
            return zoneOffset;
        }
        if (!str.equals("GMT") && !str.equals("UTC") && !str.equals("UT")) {
            AnonymousClass4.m("prefix should be GMT, UTC or UT, is: ".concat(str));
            return null;
        }
        if (zoneOffset.getTotalSeconds() != 0) {
            str = str.concat(zoneOffset.getId());
        }
        return new ZoneRegion(str, zoneOffset.getRules());
    }

    static ZoneId of(String str, boolean z) {
        Objects.requireNonNull(str, "zoneId");
        if (str.length() <= 1 || str.startsWith(Marker.ANY_NON_NULL_MARKER) || str.startsWith("-")) {
            return ZoneOffset.of(str);
        }
        if (str.startsWith("UTC") || str.startsWith("GMT")) {
            return ofWithPrefix(str, 3, z);
        }
        if (str.startsWith("UT")) {
            return ofWithPrefix(str, 2, z);
        }
        return ZoneRegion.ofId(str, z);
    }

    private static ZoneId ofWithPrefix(String str, int i, boolean z) {
        String strSubstring = str.substring(0, i);
        if (str.length() == i) {
            return ofOffset(strSubstring, ZoneOffset.UTC);
        }
        if (str.charAt(i) != '+' && str.charAt(i) != '-') {
            return ZoneRegion.ofId(str, z);
        }
        try {
            ZoneOffset zoneOffsetOf = ZoneOffset.of(str.substring(i));
            if (zoneOffsetOf == ZoneOffset.UTC) {
                return ofOffset(strSubstring, zoneOffsetOf);
            }
            return ofOffset(strSubstring, zoneOffsetOf);
        } catch (DateTimeException e) {
            throw new DateTimeException("Invalid ID for offset-based ZoneId: ".concat(str), e);
        }
    }

    public static ZoneId from(TemporalAccessor temporalAccessor) {
        ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.zone());
        if (zoneId != null) {
            return zoneId;
        }
        AnonymousClass3.m("Unable to obtain ZoneId from TemporalAccessor: ", temporalAccessor, " of type ", temporalAccessor.getClass().getName());
        return null;
    }

    ZoneId() {
        if (getClass() != ZoneOffset.class && getClass() != ZoneRegion.class) {
            throw new AssertionError("Invalid subclass");
        }
    }

    public ZoneId normalized() {
        try {
            ZoneRules rules = getRules();
            return rules.isFixedOffset() ? rules.getOffset(Instant.EPOCH) : this;
        } catch (ZoneRulesException unused) {
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ZoneId) {
            return getId().equals(((ZoneId) obj).getId());
        }
        return false;
    }

    public int hashCode() {
        return getId().hashCode();
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public String toString() {
        return getId();
    }

    private Object writeReplace() {
        return new Ser((byte) 7, this);
    }
}
