package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.Struct;
import com.google.protobuf.WireFormat;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class MonitoredResourceMetadata extends GeneratedMessageLite<MonitoredResourceMetadata, Builder> implements MonitoredResourceMetadataOrBuilder {
    public static final MonitoredResourceMetadata DEFAULT_INSTANCE;
    public static volatile Parser<MonitoredResourceMetadata> PARSER = null;
    public static final int SYSTEM_LABELS_FIELD_NUMBER = 1;
    public static final int USER_LABELS_FIELD_NUMBER = 2;
    public Struct systemLabels_;
    public MapFieldLite<String, String> userLabels_ = MapFieldLite.EMPTY_MAP_FIELD;

    /* JADX INFO: renamed from: com.google.api.MonitoredResourceMetadata$1, reason: invalid class name */
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

    private static final class UserLabelsDefaultEntryHolder {
        public static final MapEntryLite<String, String> defaultEntry;

        static {
            WireFormat.FieldType fieldType = WireFormat.FieldType.STRING;
            defaultEntry = new MapEntryLite<>(fieldType, "", fieldType, "");
        }
    }

    static {
        MonitoredResourceMetadata monitoredResourceMetadata = new MonitoredResourceMetadata();
        DEFAULT_INSTANCE = monitoredResourceMetadata;
        GeneratedMessageLite.defaultInstanceMap.put(MonitoredResourceMetadata.class, monitoredResourceMetadata);
    }

    private void clearSystemLabels() {
        this.systemLabels_ = null;
    }

    public static MonitoredResourceMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private Map<String, String> getMutableUserLabelsMap() {
        return internalGetMutableUserLabels();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapFieldLite<String, String> internalGetMutableUserLabels() {
        if (!this.userLabels_.isMutable()) {
            this.userLabels_ = this.userLabels_.mutableCopy();
        }
        return this.userLabels_;
    }

    private MapFieldLite<String, String> internalGetUserLabels() {
        return this.userLabels_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeSystemLabels(Struct struct) {
        struct.getClass();
        Struct struct2 = this.systemLabels_;
        if (struct2 == null || struct2 == Struct.DEFAULT_INSTANCE) {
            this.systemLabels_ = struct;
        } else {
            this.systemLabels_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static MonitoredResourceMetadata parseDelimitedFrom(InputStream inputStream) {
        return (MonitoredResourceMetadata) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MonitoredResourceMetadata parseFrom(ByteBuffer byteBuffer) {
        return (MonitoredResourceMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<MonitoredResourceMetadata> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemLabels(Struct struct) {
        struct.getClass();
        this.systemLabels_ = struct;
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public boolean containsUserLabels(String str) {
        str.getClass();
        return this.userLabels_.containsKey(str);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0001\u0000\u0000\u0001\t\u00022", new Object[]{"systemLabels_", "userLabels_", UserLabelsDefaultEntryHolder.defaultEntry});
            case NEW_MUTABLE_INSTANCE:
                return new MonitoredResourceMetadata();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<MonitoredResourceMetadata> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (MonitoredResourceMetadata.class) {
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

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public Struct getSystemLabels() {
        Struct struct = this.systemLabels_;
        return struct == null ? Struct.DEFAULT_INSTANCE : struct;
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    @Deprecated
    public Map<String, String> getUserLabels() {
        return getUserLabelsMap();
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public int getUserLabelsCount() {
        return this.userLabels_.size();
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public Map<String, String> getUserLabelsMap() {
        return Collections.unmodifiableMap(this.userLabels_);
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public String getUserLabelsOrDefault(String str, String str2) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLite = this.userLabels_;
        return mapFieldLite.containsKey(str) ? mapFieldLite.get(str) : str2;
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public String getUserLabelsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLite = this.userLabels_;
        if (mapFieldLite.containsKey(str)) {
            return mapFieldLite.get(str);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public boolean hasSystemLabels() {
        return this.systemLabels_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MonitoredResourceMetadata, Builder> implements MonitoredResourceMetadataOrBuilder {
        public Builder() {
            super(MonitoredResourceMetadata.DEFAULT_INSTANCE);
        }

        public Builder clearSystemLabels() {
            copyOnWrite();
            ((MonitoredResourceMetadata) this.instance).systemLabels_ = null;
            return this;
        }

        public Builder clearUserLabels() {
            copyOnWrite();
            ((MonitoredResourceMetadata) this.instance).internalGetMutableUserLabels().clear();
            return this;
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public boolean containsUserLabels(String str) {
            str.getClass();
            return ((MonitoredResourceMetadata) this.instance).getUserLabelsMap().containsKey(str);
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public Struct getSystemLabels() {
            return ((MonitoredResourceMetadata) this.instance).getSystemLabels();
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        @Deprecated
        public Map<String, String> getUserLabels() {
            return getUserLabelsMap();
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public int getUserLabelsCount() {
            return ((MonitoredResourceMetadata) this.instance).getUserLabelsMap().size();
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public Map<String, String> getUserLabelsMap() {
            return Collections.unmodifiableMap(((MonitoredResourceMetadata) this.instance).getUserLabelsMap());
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public String getUserLabelsOrDefault(String str, String str2) {
            str.getClass();
            Map<String, String> userLabelsMap = ((MonitoredResourceMetadata) this.instance).getUserLabelsMap();
            return userLabelsMap.containsKey(str) ? userLabelsMap.get(str) : str2;
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public String getUserLabelsOrThrow(String str) {
            str.getClass();
            Map<String, String> userLabelsMap = ((MonitoredResourceMetadata) this.instance).getUserLabelsMap();
            if (userLabelsMap.containsKey(str)) {
                return userLabelsMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public boolean hasSystemLabels() {
            return ((MonitoredResourceMetadata) this.instance).hasSystemLabels();
        }

        public Builder mergeSystemLabels(Struct struct) {
            copyOnWrite();
            ((MonitoredResourceMetadata) this.instance).mergeSystemLabels(struct);
            return this;
        }

        public Builder putAllUserLabels(Map<String, String> map) {
            copyOnWrite();
            ((MonitoredResourceMetadata) this.instance).internalGetMutableUserLabels().putAll(map);
            return this;
        }

        public Builder putUserLabels(String str, String str2) {
            str.getClass();
            str2.getClass();
            copyOnWrite();
            ((MonitoredResourceMetadata) this.instance).internalGetMutableUserLabels().put(str, str2);
            return this;
        }

        public Builder removeUserLabels(String str) {
            str.getClass();
            copyOnWrite();
            ((MonitoredResourceMetadata) this.instance).internalGetMutableUserLabels().remove(str);
            return this;
        }

        public Builder setSystemLabels(Struct struct) {
            copyOnWrite();
            ((MonitoredResourceMetadata) this.instance).setSystemLabels(struct);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(MonitoredResourceMetadata.DEFAULT_INSTANCE);
        }

        public Builder setSystemLabels(Struct.Builder builder) {
            copyOnWrite();
            ((MonitoredResourceMetadata) this.instance).setSystemLabels(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(MonitoredResourceMetadata monitoredResourceMetadata) {
        return DEFAULT_INSTANCE.createBuilder(monitoredResourceMetadata);
    }

    public static MonitoredResourceMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResourceMetadata) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResourceMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(ByteString byteString) {
        return (MonitoredResourceMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static MonitoredResourceMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResourceMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(byte[] bArr) {
        return (MonitoredResourceMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MonitoredResourceMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResourceMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(InputStream inputStream) {
        return (MonitoredResourceMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MonitoredResourceMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResourceMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(CodedInputStream codedInputStream) {
        return (MonitoredResourceMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MonitoredResourceMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResourceMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
