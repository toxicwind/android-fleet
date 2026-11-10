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
public final class MonitoredResource extends GeneratedMessageLite<MonitoredResource, Builder> implements MonitoredResourceOrBuilder {
    public static final MonitoredResource DEFAULT_INSTANCE;
    public static final int LABELS_FIELD_NUMBER = 2;
    public static volatile Parser<MonitoredResource> PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 1;
    public MapFieldLite<String, String> labels_ = MapFieldLite.EMPTY_MAP_FIELD;
    public String type_ = "";

    /* JADX INFO: renamed from: com.google.api.MonitoredResource$1, reason: invalid class name */
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
        MonitoredResource monitoredResource = new MonitoredResource();
        DEFAULT_INSTANCE = monitoredResource;
        GeneratedMessageLite.defaultInstanceMap.put(MonitoredResource.class, monitoredResource);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearType() {
        this.type_ = DEFAULT_INSTANCE.getType();
    }

    public static MonitoredResource getDefaultInstance() {
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

    public static MonitoredResource parseDelimitedFrom(InputStream inputStream) {
        return (MonitoredResource) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MonitoredResource parseFrom(ByteBuffer byteBuffer) {
        return (MonitoredResource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<MonitoredResource> parser() {
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

    @Override // com.google.api.MonitoredResourceOrBuilder
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0001\u0000\u0000\u0001Ȉ\u00022", new Object[]{"type_", "labels_", LabelsDefaultEntryHolder.defaultEntry});
            case NEW_MUTABLE_INSTANCE:
                return new MonitoredResource();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<MonitoredResource> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (MonitoredResource.class) {
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

    @Override // com.google.api.MonitoredResourceOrBuilder
    @Deprecated
    public Map<String, String> getLabels() {
        return getLabelsMap();
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    public int getLabelsCount() {
        return this.labels_.size();
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    public Map<String, String> getLabelsMap() {
        return Collections.unmodifiableMap(this.labels_);
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    public String getLabelsOrDefault(String str, String str2) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLite = this.labels_;
        return mapFieldLite.containsKey(str) ? mapFieldLite.get(str) : str2;
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    public String getLabelsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLite = this.labels_;
        if (mapFieldLite.containsKey(str)) {
            return mapFieldLite.get(str);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    public String getType() {
        return this.type_;
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    public ByteString getTypeBytes() {
        return ByteString.copyFromUtf8(this.type_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MonitoredResource, Builder> implements MonitoredResourceOrBuilder {
        public Builder() {
            super(MonitoredResource.DEFAULT_INSTANCE);
        }

        public Builder clearLabels() {
            copyOnWrite();
            ((MonitoredResource) this.instance).internalGetMutableLabels().clear();
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((MonitoredResource) this.instance).clearType();
            return this;
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public boolean containsLabels(String str) {
            str.getClass();
            return ((MonitoredResource) this.instance).getLabelsMap().containsKey(str);
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public int getLabelsCount() {
            return ((MonitoredResource) this.instance).getLabelsMap().size();
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public Map<String, String> getLabelsMap() {
            return Collections.unmodifiableMap(((MonitoredResource) this.instance).getLabelsMap());
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public String getLabelsOrDefault(String str, String str2) {
            str.getClass();
            Map<String, String> labelsMap = ((MonitoredResource) this.instance).getLabelsMap();
            return labelsMap.containsKey(str) ? labelsMap.get(str) : str2;
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public String getLabelsOrThrow(String str) {
            str.getClass();
            Map<String, String> labelsMap = ((MonitoredResource) this.instance).getLabelsMap();
            if (labelsMap.containsKey(str)) {
                return labelsMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public String getType() {
            return ((MonitoredResource) this.instance).getType();
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public ByteString getTypeBytes() {
            return ((MonitoredResource) this.instance).getTypeBytes();
        }

        public Builder putAllLabels(Map<String, String> map) {
            copyOnWrite();
            ((MonitoredResource) this.instance).internalGetMutableLabels().putAll(map);
            return this;
        }

        public Builder putLabels(String str, String str2) {
            str.getClass();
            str2.getClass();
            copyOnWrite();
            ((MonitoredResource) this.instance).internalGetMutableLabels().put(str, str2);
            return this;
        }

        public Builder removeLabels(String str) {
            str.getClass();
            copyOnWrite();
            ((MonitoredResource) this.instance).internalGetMutableLabels().remove(str);
            return this;
        }

        public Builder setType(String str) {
            copyOnWrite();
            ((MonitoredResource) this.instance).setType(str);
            return this;
        }

        public Builder setTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((MonitoredResource) this.instance).setTypeBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(MonitoredResource.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(MonitoredResource monitoredResource) {
        return DEFAULT_INSTANCE.createBuilder(monitoredResource);
    }

    public static MonitoredResource parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResource) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MonitoredResource parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static MonitoredResource parseFrom(ByteString byteString) {
        return (MonitoredResource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static MonitoredResource parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MonitoredResource parseFrom(byte[] bArr) {
        return (MonitoredResource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MonitoredResource parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MonitoredResource parseFrom(InputStream inputStream) {
        return (MonitoredResource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MonitoredResource parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MonitoredResource parseFrom(CodedInputStream codedInputStream) {
        return (MonitoredResource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MonitoredResource parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
