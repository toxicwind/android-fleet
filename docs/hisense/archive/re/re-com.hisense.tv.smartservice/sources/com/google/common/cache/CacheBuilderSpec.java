package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class CacheBuilderSpec {
    public static final Splitter KEYS_SPLITTER = Splitter.on(',').trimResults();
    public static final Splitter KEY_VALUE_SPLITTER = Splitter.on('=').trimResults();
    public static final ImmutableMap<String, ValueParser> VALUE_PARSERS = ImmutableMap.builder().put("initialCapacity", new InitialCapacityParser()).put("maximumSize", new MaximumSizeParser()).put("maximumWeight", new MaximumWeightParser()).put("concurrencyLevel", new ConcurrencyLevelParser()).put("weakKeys", new KeyStrengthParser(LocalCache.Strength.WEAK)).put("softValues", new ValueStrengthParser(LocalCache.Strength.SOFT)).put("weakValues", new ValueStrengthParser(LocalCache.Strength.WEAK)).put("recordStats", new RecordStatsParser()).put("expireAfterAccess", new AccessDurationParser()).put("expireAfterWrite", new WriteDurationParser()).put("refreshAfterWrite", new RefreshDurationParser()).put("refreshInterval", new RefreshDurationParser()).build();

    @VisibleForTesting
    public long accessExpirationDuration;

    @VisibleForTesting
    public TimeUnit accessExpirationTimeUnit;

    @VisibleForTesting
    public Integer concurrencyLevel;

    @VisibleForTesting
    public Integer initialCapacity;

    @VisibleForTesting
    public LocalCache.Strength keyStrength;

    @VisibleForTesting
    public Long maximumSize;

    @VisibleForTesting
    public Long maximumWeight;

    @VisibleForTesting
    public Boolean recordStats;

    @VisibleForTesting
    public long refreshDuration;

    @VisibleForTesting
    public TimeUnit refreshTimeUnit;
    public final String specification;

    @VisibleForTesting
    public LocalCache.Strength valueStrength;

    @VisibleForTesting
    public long writeExpirationDuration;

    @VisibleForTesting
    public TimeUnit writeExpirationTimeUnit;

    /* JADX INFO: renamed from: com.google.common.cache.CacheBuilderSpec$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$common$cache$LocalCache$Strength = new int[LocalCache.Strength.values().length];

        static {
            try {
                $SwitchMap$com$google$common$cache$LocalCache$Strength[LocalCache.Strength.WEAK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$cache$LocalCache$Strength[LocalCache.Strength.SOFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static class AccessDurationParser extends DurationParser {
        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        public void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.accessExpirationTimeUnit == null, "expireAfterAccess already set");
            cacheBuilderSpec.accessExpirationDuration = j;
            cacheBuilderSpec.accessExpirationTimeUnit = timeUnit;
        }
    }

    static class ConcurrencyLevelParser extends IntegerParser {
        @Override // com.google.common.cache.CacheBuilderSpec.IntegerParser
        public void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i) {
            Preconditions.checkArgument(cacheBuilderSpec.concurrencyLevel == null, "concurrency level was already set to ", cacheBuilderSpec.concurrencyLevel);
            cacheBuilderSpec.concurrencyLevel = Integer.valueOf(i);
        }
    }

    static abstract class DurationParser implements ValueParser {
        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            TimeUnit timeUnit;
            Preconditions.checkArgument((str2 == null || str2.isEmpty()) ? false : true, "value of key %s omitted", str);
            try {
                char cCharAt = str2.charAt(str2.length() - 1);
                if (cCharAt == 'd') {
                    timeUnit = TimeUnit.DAYS;
                } else if (cCharAt == 'h') {
                    timeUnit = TimeUnit.HOURS;
                } else if (cCharAt == 'm') {
                    timeUnit = TimeUnit.MINUTES;
                } else {
                    if (cCharAt != 's') {
                        throw new IllegalArgumentException(CacheBuilderSpec.format("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", str, str2));
                    }
                    timeUnit = TimeUnit.SECONDS;
                }
                parseDuration(cacheBuilderSpec, Long.parseLong(str2.substring(0, str2.length() - 1)), timeUnit);
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2));
            }
        }

        public abstract void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit);
    }

    static class InitialCapacityParser extends IntegerParser {
        @Override // com.google.common.cache.CacheBuilderSpec.IntegerParser
        public void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i) {
            Preconditions.checkArgument(cacheBuilderSpec.initialCapacity == null, "initial capacity was already set to ", cacheBuilderSpec.initialCapacity);
            cacheBuilderSpec.initialCapacity = Integer.valueOf(i);
        }
    }

    static abstract class IntegerParser implements ValueParser {
        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument((str2 == null || str2.isEmpty()) ? false : true, "value of key %s omitted", str);
            try {
                parseInteger(cacheBuilderSpec, Integer.parseInt(str2));
            } catch (NumberFormatException e2) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2), e2);
            }
        }

        public abstract void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i);
    }

    static class KeyStrengthParser implements ValueParser {
        public final LocalCache.Strength strength;

        public KeyStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument(str2 == null, "key %s does not take values", str);
            Preconditions.checkArgument(cacheBuilderSpec.keyStrength == null, "%s was already set to %s", str, cacheBuilderSpec.keyStrength);
            cacheBuilderSpec.keyStrength = this.strength;
        }
    }

    static abstract class LongParser implements ValueParser {
        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument((str2 == null || str2.isEmpty()) ? false : true, "value of key %s omitted", str);
            try {
                parseLong(cacheBuilderSpec, Long.parseLong(str2));
            } catch (NumberFormatException e2) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2), e2);
            }
        }

        public abstract void parseLong(CacheBuilderSpec cacheBuilderSpec, long j);
    }

    static class MaximumSizeParser extends LongParser {
        @Override // com.google.common.cache.CacheBuilderSpec.LongParser
        public void parseLong(CacheBuilderSpec cacheBuilderSpec, long j) {
            Preconditions.checkArgument(cacheBuilderSpec.maximumSize == null, "maximum size was already set to ", cacheBuilderSpec.maximumSize);
            Preconditions.checkArgument(cacheBuilderSpec.maximumWeight == null, "maximum weight was already set to ", cacheBuilderSpec.maximumWeight);
            cacheBuilderSpec.maximumSize = Long.valueOf(j);
        }
    }

    static class MaximumWeightParser extends LongParser {
        @Override // com.google.common.cache.CacheBuilderSpec.LongParser
        public void parseLong(CacheBuilderSpec cacheBuilderSpec, long j) {
            Preconditions.checkArgument(cacheBuilderSpec.maximumWeight == null, "maximum weight was already set to ", cacheBuilderSpec.maximumWeight);
            Preconditions.checkArgument(cacheBuilderSpec.maximumSize == null, "maximum size was already set to ", cacheBuilderSpec.maximumSize);
            cacheBuilderSpec.maximumWeight = Long.valueOf(j);
        }
    }

    static class RecordStatsParser implements ValueParser {
        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument(str2 == null, "recordStats does not take values");
            Preconditions.checkArgument(cacheBuilderSpec.recordStats == null, "recordStats already set");
            cacheBuilderSpec.recordStats = true;
        }
    }

    static class RefreshDurationParser extends DurationParser {
        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        public void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.refreshTimeUnit == null, "refreshAfterWrite already set");
            cacheBuilderSpec.refreshDuration = j;
            cacheBuilderSpec.refreshTimeUnit = timeUnit;
        }
    }

    private interface ValueParser {
        void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2);
    }

    static class ValueStrengthParser implements ValueParser {
        public final LocalCache.Strength strength;

        public ValueStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument(str2 == null, "key %s does not take values", str);
            Preconditions.checkArgument(cacheBuilderSpec.valueStrength == null, "%s was already set to %s", str, cacheBuilderSpec.valueStrength);
            cacheBuilderSpec.valueStrength = this.strength;
        }
    }

    static class WriteDurationParser extends DurationParser {
        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        public void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.writeExpirationTimeUnit == null, "expireAfterWrite already set");
            cacheBuilderSpec.writeExpirationDuration = j;
            cacheBuilderSpec.writeExpirationTimeUnit = timeUnit;
        }
    }

    public CacheBuilderSpec(String str) {
        this.specification = str;
    }

    public static CacheBuilderSpec disableCaching() {
        return parse("maximumSize=0");
    }

    public static Long durationInNanos(long j, TimeUnit timeUnit) {
        if (timeUnit == null) {
            return null;
        }
        return Long.valueOf(timeUnit.toNanos(j));
    }

    public static String format(String str, Object... objArr) {
        return String.format(Locale.ROOT, str, objArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static CacheBuilderSpec parse(String str) {
        CacheBuilderSpec cacheBuilderSpec = new CacheBuilderSpec(str);
        if (!str.isEmpty()) {
            for (String str2 : KEYS_SPLITTER.split(str)) {
                ImmutableList immutableListCopyOf = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(str2));
                Preconditions.checkArgument(!immutableListCopyOf.isEmpty(), "blank key-value pair");
                Preconditions.checkArgument(immutableListCopyOf.size() <= 2, "key-value pair %s with more than one equals sign", str2);
                String str3 = (String) immutableListCopyOf.get(0);
                ValueParser valueParser = VALUE_PARSERS.get(str3);
                Preconditions.checkArgument(valueParser != null, "unknown key %s", str3);
                valueParser.parse(cacheBuilderSpec, str3, immutableListCopyOf.size() == 1 ? null : (String) immutableListCopyOf.get(1));
            }
        }
        return cacheBuilderSpec;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CacheBuilderSpec)) {
            return false;
        }
        CacheBuilderSpec cacheBuilderSpec = (CacheBuilderSpec) obj;
        return Objects.equal(this.initialCapacity, cacheBuilderSpec.initialCapacity) && Objects.equal(this.maximumSize, cacheBuilderSpec.maximumSize) && Objects.equal(this.maximumWeight, cacheBuilderSpec.maximumWeight) && Objects.equal(this.concurrencyLevel, cacheBuilderSpec.concurrencyLevel) && Objects.equal(this.keyStrength, cacheBuilderSpec.keyStrength) && Objects.equal(this.valueStrength, cacheBuilderSpec.valueStrength) && Objects.equal(this.recordStats, cacheBuilderSpec.recordStats) && Objects.equal(durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(cacheBuilderSpec.writeExpirationDuration, cacheBuilderSpec.writeExpirationTimeUnit)) && Objects.equal(durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(cacheBuilderSpec.accessExpirationDuration, cacheBuilderSpec.accessExpirationTimeUnit)) && Objects.equal(durationInNanos(this.refreshDuration, this.refreshTimeUnit), durationInNanos(cacheBuilderSpec.refreshDuration, cacheBuilderSpec.refreshTimeUnit));
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.initialCapacity, this.maximumSize, this.maximumWeight, this.concurrencyLevel, this.keyStrength, this.valueStrength, this.recordStats, durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(this.refreshDuration, this.refreshTimeUnit)});
    }

    public CacheBuilder<Object, Object> toCacheBuilder() {
        CacheBuilder<Object, Object> cacheBuilder = new CacheBuilder<>();
        Integer num = this.initialCapacity;
        if (num != null) {
            cacheBuilder.initialCapacity(num.intValue());
        }
        Long l = this.maximumSize;
        if (l != null) {
            cacheBuilder.maximumSize(l.longValue());
        }
        Long l2 = this.maximumWeight;
        if (l2 != null) {
            cacheBuilder.maximumWeight(l2.longValue());
        }
        Integer num2 = this.concurrencyLevel;
        if (num2 != null) {
            cacheBuilder.concurrencyLevel(num2.intValue());
        }
        LocalCache.Strength strength = this.keyStrength;
        if (strength != null) {
            if (strength.ordinal() != 2) {
                throw new AssertionError();
            }
            cacheBuilder.weakKeys();
        }
        LocalCache.Strength strength2 = this.valueStrength;
        if (strength2 != null) {
            int iOrdinal = strength2.ordinal();
            if (iOrdinal == 1) {
                cacheBuilder.softValues();
            } else {
                if (iOrdinal != 2) {
                    throw new AssertionError();
                }
                cacheBuilder.weakValues();
            }
        }
        Boolean bool = this.recordStats;
        if (bool != null && bool.booleanValue()) {
            cacheBuilder.recordStats();
        }
        TimeUnit timeUnit = this.writeExpirationTimeUnit;
        if (timeUnit != null) {
            cacheBuilder.expireAfterWrite(this.writeExpirationDuration, timeUnit);
        }
        TimeUnit timeUnit2 = this.accessExpirationTimeUnit;
        if (timeUnit2 != null) {
            cacheBuilder.expireAfterAccess(this.accessExpirationDuration, timeUnit2);
        }
        TimeUnit timeUnit3 = this.refreshTimeUnit;
        if (timeUnit3 != null) {
            cacheBuilder.refreshAfterWrite(this.refreshDuration, timeUnit3);
        }
        return cacheBuilder;
    }

    public String toParsableString() {
        return this.specification;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(toParsableString()).toString();
    }
}
