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
public final class Metric extends GeneratedMessageLite<Metric, Builder> implements MetricOrBuilder {
    public static final Metric DEFAULT_INSTANCE;
    public static final int LABELS_FIELD_NUMBER = 2;
    public static volatile Parser<Metric> PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 3;
    public MapFieldLite<String, String> labels_ = MapFieldLite.EMPTY_MAP_FIELD;
    public String type_ = "";

    /* JADX INFO: renamed from: com.google.api.Metric$1, reason: invalid class name */
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

    private static final class LabelsDefaultEntryHolder {
        public static final MapEntryLite<String, String> defaultEntry;

        static {
            WireFormat.FieldType fieldType = WireFormat.FieldType.STRING;
            defaultEntry = new MapEntryLite<>(fieldType, "", fieldType, "");
        }
    }

    static {
        Metric metric = new Metric();
        DEFAULT_INSTANCE = metric;
        GeneratedMessageLite.defaultInstanceMap.put(Metric.class, metric);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearType() {
        this.type_ = DEFAULT_INSTANCE.getType();
    }

    public static Metric getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private Map<String, String> getMutableLabelsMap() {
        return internalGetMutableLabels();
    }

    private MapFieldLite<String, String> internalGetLabels() {
        return this.labels_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapFieldLite<String, String> internalGetMutableLabels() {
        if (!this.labels_.isMutable()) {
            this.labels_ = this.labels_.mutableCopy();
        }
        return this.labels_;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Metric parseDelimitedFrom(InputStream inputStream) {
        return (Metric) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Metric parseFrom(ByteBuffer byteBuffer) {
        return (Metric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Metric> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setType(String str) {
        str.getClass();
        this.type_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTypeBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.type_ = byteString.toStringUtf8();
    }

    @Override // com.google.api.MetricOrBuilder
    public boolean containsLabels(String str) {
        str.getClass();
        return this.labels_.containsKey(str);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0001\u0000\u0000\u00022\u0003Ȉ", new Object[]{"labels_", LabelsDefaultEntryHolder.defaultEntry, "type_"});
            case NEW_MUTABLE_INSTANCE:
                return new Metric();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Metric> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Metric.class) {
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

    @Override // com.google.api.MetricOrBuilder
    @Deprecated
    public Map<String, String> getLabels() {
        return getLabelsMap();
    }

    @Override // com.google.api.MetricOrBuilder
    public int getLabelsCount() {
        return this.labels_.size();
    }

    @Override // com.google.api.MetricOrBuilder
    public Map<String, String> getLabelsMap() {
        return Collections.unmodifiableMap(this.labels_);
    }

    @Override // com.google.api.MetricOrBuilder
    public String getLabelsOrDefault(String str, String str2) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLite = this.labels_;
        return mapFieldLite.containsKey(str) ? mapFieldLite.get(str) : str2;
    }

    @Override // com.google.api.MetricOrBuilder
    public String getLabelsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLite = this.labels_;
        if (mapFieldLite.containsKey(str)) {
            return mapFieldLite.get(str);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.api.MetricOrBuilder
    public String getType() {
        return this.type_;
    }

    @Override // com.google.api.MetricOrBuilder
    public ByteString getTypeBytes() {
        return ByteString.copyFromUtf8(this.type_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Metric, Builder> implements MetricOrBuilder {
        public Builder() {
            super(Metric.DEFAULT_INSTANCE);
        }

        public Builder clearLabels() {
            copyOnWrite();
            ((Metric) this.instance).internalGetMutableLabels().clear();
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((Metric) this.instance).clearType();
            return this;
        }

        @Override // com.google.api.MetricOrBuilder
        public boolean containsLabels(String str) {
            str.getClass();
            return ((Metric) this.instance).getLabelsMap().containsKey(str);
        }

        @Override // com.google.api.MetricOrBuilder
        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        @Override // com.google.api.MetricOrBuilder
        public int getLabelsCount() {
            return ((Metric) this.instance).getLabelsMap().size();
        }

        @Override // com.google.api.MetricOrBuilder
        public Map<String, String> getLabelsMap() {
            return Collections.unmodifiableMap(((Metric) this.instance).getLabelsMap());
        }

        @Override // com.google.api.MetricOrBuilder
        public String getLabelsOrDefault(String str, String str2) {
            str.getClass();
            Map<String, String> labelsMap = ((Metric) this.instance).getLabelsMap();
            return labelsMap.containsKey(str) ? labelsMap.get(str) : str2;
        }

        @Override // com.google.api.MetricOrBuilder
        public String getLabelsOrThrow(String str) {
            str.getClass();
            Map<String, String> labelsMap = ((Metric) this.instance).getLabelsMap();
            if (labelsMap.containsKey(str)) {
                return labelsMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.api.MetricOrBuilder
        public String getType() {
            return ((Metric) this.instance).getType();
        }

        @Override // com.google.api.MetricOrBuilder
        public ByteString getTypeBytes() {
            return ((Metric) this.instance).getTypeBytes();
        }

        public Builder putAllLabels(Map<String, String> map) {
            copyOnWrite();
            ((Metric) this.instance).internalGetMutableLabels().putAll(map);
            return this;
        }

        public Builder putLabels(String str, String str2) {
            str.getClass();
            str2.getClass();
            copyOnWrite();
            ((Metric) this.instance).internalGetMutableLabels().put(str, str2);
            return this;
        }

        public Builder removeLabels(String str) {
            str.getClass();
            copyOnWrite();
            ((Metric) this.instance).internalGetMutableLabels().remove(str);
            return this;
        }

        public Builder setType(String str) {
            copyOnWrite();
            ((Metric) this.instance).setType(str);
            return this;
        }

        public Builder setTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((Metric) this.instance).setTypeBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Metric.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(Metric metric) {
        return DEFAULT_INSTANCE.createBuilder(metric);
    }

    public static Metric parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Metric) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Metric parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Metric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Metric parseFrom(ByteString byteString) {
        return (Metric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Metric parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Metric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Metric parseFrom(byte[] bArr) {
        return (Metric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Metric parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Metric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Metric parseFrom(InputStream inputStream) {
        return (Metric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Metric parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Metric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Metric parseFrom(CodedInputStream codedInputStream) {
        return (Metric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Metric parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Metric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
