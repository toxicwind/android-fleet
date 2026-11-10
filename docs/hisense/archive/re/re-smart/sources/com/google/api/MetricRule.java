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
public final class MetricRule extends GeneratedMessageLite<MetricRule, Builder> implements MetricRuleOrBuilder {
    public static final MetricRule DEFAULT_INSTANCE;
    public static final int METRIC_COSTS_FIELD_NUMBER = 2;
    public static volatile Parser<MetricRule> PARSER = null;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    public MapFieldLite<String, Long> metricCosts_ = MapFieldLite.EMPTY_MAP_FIELD;
    public String selector_ = "";

    /* JADX INFO: renamed from: com.google.api.MetricRule$1, reason: invalid class name */
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

    private static final class MetricCostsDefaultEntryHolder {
        public static final MapEntryLite<String, Long> defaultEntry = new MapEntryLite<>(WireFormat.FieldType.STRING, "", WireFormat.FieldType.INT64, 0L);
    }

    static {
        MetricRule metricRule = new MetricRule();
        DEFAULT_INSTANCE = metricRule;
        GeneratedMessageLite.defaultInstanceMap.put(MetricRule.class, metricRule);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSelector() {
        this.selector_ = DEFAULT_INSTANCE.getSelector();
    }

    public static MetricRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private Map<String, Long> getMutableMetricCostsMap() {
        return internalGetMutableMetricCosts();
    }

    private MapFieldLite<String, Long> internalGetMetricCosts() {
        return this.metricCosts_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapFieldLite<String, Long> internalGetMutableMetricCosts() {
        if (!this.metricCosts_.isMutable()) {
            this.metricCosts_ = this.metricCosts_.mutableCopy();
        }
        return this.metricCosts_;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static MetricRule parseDelimitedFrom(InputStream inputStream) {
        return (MetricRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MetricRule parseFrom(ByteBuffer byteBuffer) {
        return (MetricRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<MetricRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelector(String str) {
        str.getClass();
        this.selector_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectorBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.selector_ = byteString.toStringUtf8();
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public boolean containsMetricCosts(String str) {
        str.getClass();
        return this.metricCosts_.containsKey(str);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0001\u0000\u0000\u0001Ȉ\u00022", new Object[]{"selector_", "metricCosts_", MetricCostsDefaultEntryHolder.defaultEntry});
            case NEW_MUTABLE_INSTANCE:
                return new MetricRule();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<MetricRule> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (MetricRule.class) {
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

    @Override // com.google.api.MetricRuleOrBuilder
    @Deprecated
    public Map<String, Long> getMetricCosts() {
        return getMetricCostsMap();
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public int getMetricCostsCount() {
        return this.metricCosts_.size();
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public Map<String, Long> getMetricCostsMap() {
        return Collections.unmodifiableMap(this.metricCosts_);
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public long getMetricCostsOrDefault(String str, long j) {
        str.getClass();
        MapFieldLite<String, Long> mapFieldLite = this.metricCosts_;
        return mapFieldLite.containsKey(str) ? mapFieldLite.get(str).longValue() : j;
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public long getMetricCostsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, Long> mapFieldLite = this.metricCosts_;
        if (mapFieldLite.containsKey(str)) {
            return mapFieldLite.get(str).longValue();
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public String getSelector() {
        return this.selector_;
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public ByteString getSelectorBytes() {
        return ByteString.copyFromUtf8(this.selector_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MetricRule, Builder> implements MetricRuleOrBuilder {
        public Builder() {
            super(MetricRule.DEFAULT_INSTANCE);
        }

        public Builder clearMetricCosts() {
            copyOnWrite();
            ((MetricRule) this.instance).internalGetMutableMetricCosts().clear();
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((MetricRule) this.instance).clearSelector();
            return this;
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public boolean containsMetricCosts(String str) {
            str.getClass();
            return ((MetricRule) this.instance).getMetricCostsMap().containsKey(str);
        }

        @Override // com.google.api.MetricRuleOrBuilder
        @Deprecated
        public Map<String, Long> getMetricCosts() {
            return getMetricCostsMap();
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public int getMetricCostsCount() {
            return ((MetricRule) this.instance).getMetricCostsMap().size();
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public Map<String, Long> getMetricCostsMap() {
            return Collections.unmodifiableMap(((MetricRule) this.instance).getMetricCostsMap());
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public long getMetricCostsOrDefault(String str, long j) {
            str.getClass();
            Map<String, Long> metricCostsMap = ((MetricRule) this.instance).getMetricCostsMap();
            return metricCostsMap.containsKey(str) ? metricCostsMap.get(str).longValue() : j;
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public long getMetricCostsOrThrow(String str) {
            str.getClass();
            Map<String, Long> metricCostsMap = ((MetricRule) this.instance).getMetricCostsMap();
            if (metricCostsMap.containsKey(str)) {
                return metricCostsMap.get(str).longValue();
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public String getSelector() {
            return ((MetricRule) this.instance).getSelector();
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public ByteString getSelectorBytes() {
            return ((MetricRule) this.instance).getSelectorBytes();
        }

        public Builder putAllMetricCosts(Map<String, Long> map) {
            copyOnWrite();
            ((MetricRule) this.instance).internalGetMutableMetricCosts().putAll(map);
            return this;
        }

        public Builder putMetricCosts(String str, long j) {
            str.getClass();
            copyOnWrite();
            ((MetricRule) this.instance).internalGetMutableMetricCosts().put(str, Long.valueOf(j));
            return this;
        }

        public Builder removeMetricCosts(String str) {
            str.getClass();
            copyOnWrite();
            ((MetricRule) this.instance).internalGetMutableMetricCosts().remove(str);
            return this;
        }

        public Builder setSelector(String str) {
            copyOnWrite();
            ((MetricRule) this.instance).setSelector(str);
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            copyOnWrite();
            ((MetricRule) this.instance).setSelectorBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(MetricRule.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(MetricRule metricRule) {
        return DEFAULT_INSTANCE.createBuilder(metricRule);
    }

    public static MetricRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MetricRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MetricRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (MetricRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static MetricRule parseFrom(ByteString byteString) {
        return (MetricRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static MetricRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (MetricRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MetricRule parseFrom(byte[] bArr) {
        return (MetricRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MetricRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (MetricRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MetricRule parseFrom(InputStream inputStream) {
        return (MetricRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MetricRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MetricRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MetricRule parseFrom(CodedInputStream codedInputStream) {
        return (MetricRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MetricRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MetricRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
