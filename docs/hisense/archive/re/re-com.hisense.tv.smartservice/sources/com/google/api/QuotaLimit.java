package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class QuotaLimit extends GeneratedMessageLite<QuotaLimit, Builder> implements QuotaLimitOrBuilder {
    public static final QuotaLimit DEFAULT_INSTANCE;
    public static final int DEFAULT_LIMIT_FIELD_NUMBER = 3;
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 12;
    public static final int DURATION_FIELD_NUMBER = 5;
    public static final int FREE_TIER_FIELD_NUMBER = 7;
    public static final int MAX_LIMIT_FIELD_NUMBER = 4;
    public static final int METRIC_FIELD_NUMBER = 8;
    public static final int NAME_FIELD_NUMBER = 6;
    public static volatile Parser<QuotaLimit> PARSER = null;
    public static final int UNIT_FIELD_NUMBER = 9;
    public static final int VALUES_FIELD_NUMBER = 10;
    public long defaultLimit_;
    public long freeTier_;
    public long maxLimit_;
    public MapFieldLite<String, Long> values_ = MapFieldLite.EMPTY_MAP_FIELD;
    public String name_ = "";
    public String description_ = "";
    public String duration_ = "";
    public String metric_ = "";
    public String unit_ = "";
    public String displayName_ = "";

    /* JADX INFO: renamed from: com.google.api.QuotaLimit$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private static final class ValuesDefaultEntryHolder {
        public static final MapEntryLite<String, Long> defaultEntry = new MapEntryLite<>(WireFormat.FieldType.STRING, "", WireFormat.FieldType.INT64, 0L);
    }

    static {
        QuotaLimit quotaLimit = new QuotaLimit();
        DEFAULT_INSTANCE = quotaLimit;
        GeneratedMessageLite.defaultInstanceMap.put(QuotaLimit.class, quotaLimit);
    }

    private void clearDefaultLimit() {
        this.defaultLimit_ = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDescription() {
        this.description_ = DEFAULT_INSTANCE.getDescription();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDisplayName() {
        this.displayName_ = DEFAULT_INSTANCE.getDisplayName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDuration() {
        this.duration_ = DEFAULT_INSTANCE.getDuration();
    }

    private void clearFreeTier() {
        this.freeTier_ = 0L;
    }

    private void clearMaxLimit() {
        this.maxLimit_ = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMetric() {
        this.metric_ = DEFAULT_INSTANCE.getMetric();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUnit() {
        this.unit_ = DEFAULT_INSTANCE.getUnit();
    }

    public static QuotaLimit getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private Map<String, Long> getMutableValuesMap() {
        return internalGetMutableValues();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapFieldLite<String, Long> internalGetMutableValues() {
        if (!this.values_.isMutable()) {
            this.values_ = this.values_.mutableCopy();
        }
        return this.values_;
    }

    private MapFieldLite<String, Long> internalGetValues() {
        return this.values_;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static QuotaLimit parseDelimitedFrom(InputStream inputStream) {
        return (QuotaLimit) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static QuotaLimit parseFrom(ByteBuffer byteBuffer) {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<QuotaLimit> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setDefaultLimit(long j) {
        this.defaultLimit_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDescription(String str) {
        str.getClass();
        this.description_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDescriptionBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.description_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDisplayName(String str) {
        str.getClass();
        this.displayName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDisplayNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.displayName_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDuration(String str) {
        str.getClass();
        this.duration_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDurationBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.duration_ = byteString.toStringUtf8();
    }

    private void setFreeTier(long j) {
        this.freeTier_ = j;
    }

    private void setMaxLimit(long j) {
        this.maxLimit_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetric(String str) {
        str.getClass();
        this.metric_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetricBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.metric_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.name_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.name_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUnit(String str) {
        str.getClass();
        this.unit_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUnitBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.unit_ = byteString.toStringUtf8();
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public boolean containsValues(String str) {
        str.getClass();
        return this.values_.containsKey(str);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\n\u0000\u0000\u0002\f\n\u0001\u0000\u0000\u0002Ȉ\u0003\u0002\u0004\u0002\u0005Ȉ\u0006Ȉ\u0007\u0002\bȈ\tȈ\n2\fȈ", new Object[]{"description_", "defaultLimit_", "maxLimit_", "duration_", "name_", "freeTier_", "metric_", "unit_", "values_", ValuesDefaultEntryHolder.defaultEntry, "displayName_"});
            case NEW_MUTABLE_INSTANCE:
                return new QuotaLimit();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<QuotaLimit> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (QuotaLimit.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                }
                return defaultInstanceBasedParser;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public long getDefaultLimit() {
        return this.defaultLimit_;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public String getDescription() {
        return this.description_;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public ByteString getDescriptionBytes() {
        return ByteString.copyFromUtf8(this.description_);
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public String getDisplayName() {
        return this.displayName_;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public ByteString getDisplayNameBytes() {
        return ByteString.copyFromUtf8(this.displayName_);
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public String getDuration() {
        return this.duration_;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public ByteString getDurationBytes() {
        return ByteString.copyFromUtf8(this.duration_);
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public long getFreeTier() {
        return this.freeTier_;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public long getMaxLimit() {
        return this.maxLimit_;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public String getMetric() {
        return this.metric_;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public ByteString getMetricBytes() {
        return ByteString.copyFromUtf8(this.metric_);
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public String getUnit() {
        return this.unit_;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public ByteString getUnitBytes() {
        return ByteString.copyFromUtf8(this.unit_);
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    @Deprecated
    public Map<String, Long> getValues() {
        return getValuesMap();
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public int getValuesCount() {
        return this.values_.size();
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public Map<String, Long> getValuesMap() {
        return Collections.unmodifiableMap(this.values_);
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public long getValuesOrDefault(String str, long j) {
        str.getClass();
        MapFieldLite<String, Long> mapFieldLite = this.values_;
        return mapFieldLite.containsKey(str) ? mapFieldLite.get(str).longValue() : j;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public long getValuesOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, Long> mapFieldLite = this.values_;
        if (mapFieldLite.containsKey(str)) {
            return mapFieldLite.get(str).longValue();
        }
        throw new IllegalArgumentException();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<QuotaLimit, Builder> implements QuotaLimitOrBuilder {
        public Builder() {
            super(QuotaLimit.DEFAULT_INSTANCE);
        }

        public Builder clearDefaultLimit() {
            copyOnWrite();
            ((QuotaLimit) this.instance).defaultLimit_ = 0L;
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearDescription();
            return this;
        }

        public Builder clearDisplayName() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearDisplayName();
            return this;
        }

        public Builder clearDuration() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearDuration();
            return this;
        }

        public Builder clearFreeTier() {
            copyOnWrite();
            ((QuotaLimit) this.instance).freeTier_ = 0L;
            return this;
        }

        public Builder clearMaxLimit() {
            copyOnWrite();
            ((QuotaLimit) this.instance).maxLimit_ = 0L;
            return this;
        }

        public Builder clearMetric() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearMetric();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearName();
            return this;
        }

        public Builder clearUnit() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearUnit();
            return this;
        }

        public Builder clearValues() {
            copyOnWrite();
            ((QuotaLimit) this.instance).internalGetMutableValues().clear();
            return this;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public boolean containsValues(String str) {
            str.getClass();
            return ((QuotaLimit) this.instance).getValuesMap().containsKey(str);
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public long getDefaultLimit() {
            return ((QuotaLimit) this.instance).getDefaultLimit();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public String getDescription() {
            return ((QuotaLimit) this.instance).getDescription();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public ByteString getDescriptionBytes() {
            return ((QuotaLimit) this.instance).getDescriptionBytes();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public String getDisplayName() {
            return ((QuotaLimit) this.instance).getDisplayName();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public ByteString getDisplayNameBytes() {
            return ((QuotaLimit) this.instance).getDisplayNameBytes();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public String getDuration() {
            return ((QuotaLimit) this.instance).getDuration();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public ByteString getDurationBytes() {
            return ((QuotaLimit) this.instance).getDurationBytes();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public long getFreeTier() {
            return ((QuotaLimit) this.instance).getFreeTier();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public long getMaxLimit() {
            return ((QuotaLimit) this.instance).getMaxLimit();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public String getMetric() {
            return ((QuotaLimit) this.instance).getMetric();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public ByteString getMetricBytes() {
            return ((QuotaLimit) this.instance).getMetricBytes();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public String getName() {
            return ((QuotaLimit) this.instance).getName();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public ByteString getNameBytes() {
            return ((QuotaLimit) this.instance).getNameBytes();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public String getUnit() {
            return ((QuotaLimit) this.instance).getUnit();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public ByteString getUnitBytes() {
            return ((QuotaLimit) this.instance).getUnitBytes();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        @Deprecated
        public Map<String, Long> getValues() {
            return getValuesMap();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public int getValuesCount() {
            return ((QuotaLimit) this.instance).getValuesMap().size();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public Map<String, Long> getValuesMap() {
            return Collections.unmodifiableMap(((QuotaLimit) this.instance).getValuesMap());
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public long getValuesOrDefault(String str, long j) {
            str.getClass();
            Map<String, Long> valuesMap = ((QuotaLimit) this.instance).getValuesMap();
            return valuesMap.containsKey(str) ? valuesMap.get(str).longValue() : j;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public long getValuesOrThrow(String str) {
            str.getClass();
            Map<String, Long> valuesMap = ((QuotaLimit) this.instance).getValuesMap();
            if (valuesMap.containsKey(str)) {
                return valuesMap.get(str).longValue();
            }
            throw new IllegalArgumentException();
        }

        public Builder putAllValues(Map<String, Long> map) {
            copyOnWrite();
            ((QuotaLimit) this.instance).internalGetMutableValues().putAll(map);
            return this;
        }

        public Builder putValues(String str, long j) {
            str.getClass();
            copyOnWrite();
            ((QuotaLimit) this.instance).internalGetMutableValues().put(str, Long.valueOf(j));
            return this;
        }

        public Builder removeValues(String str) {
            str.getClass();
            copyOnWrite();
            ((QuotaLimit) this.instance).internalGetMutableValues().remove(str);
            return this;
        }

        public Builder setDefaultLimit(long j) {
            copyOnWrite();
            ((QuotaLimit) this.instance).defaultLimit_ = j;
            return this;
        }

        public Builder setDescription(String str) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setDescription(str);
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setDescriptionBytes(byteString);
            return this;
        }

        public Builder setDisplayName(String str) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setDisplayName(str);
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setDisplayNameBytes(byteString);
            return this;
        }

        public Builder setDuration(String str) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setDuration(str);
            return this;
        }

        public Builder setDurationBytes(ByteString byteString) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setDurationBytes(byteString);
            return this;
        }

        public Builder setFreeTier(long j) {
            copyOnWrite();
            ((QuotaLimit) this.instance).freeTier_ = j;
            return this;
        }

        public Builder setMaxLimit(long j) {
            copyOnWrite();
            ((QuotaLimit) this.instance).maxLimit_ = j;
            return this;
        }

        public Builder setMetric(String str) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setMetric(str);
            return this;
        }

        public Builder setMetricBytes(ByteString byteString) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setMetricBytes(byteString);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setUnit(String str) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setUnit(str);
            return this;
        }

        public Builder setUnitBytes(ByteString byteString) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setUnitBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(QuotaLimit.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(QuotaLimit quotaLimit) {
        return DEFAULT_INSTANCE.createBuilder(quotaLimit);
    }

    public static QuotaLimit parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (QuotaLimit) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(ByteString byteString) {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static QuotaLimit parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(byte[] bArr) {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static QuotaLimit parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(InputStream inputStream) {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static QuotaLimit parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(CodedInputStream codedInputStream) {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static QuotaLimit parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
