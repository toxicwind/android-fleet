package com.google.firestore.v1;

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
public final class MapValue extends GeneratedMessageLite<MapValue, Builder> implements MapValueOrBuilder {
    public static final MapValue DEFAULT_INSTANCE;
    public static final int FIELDS_FIELD_NUMBER = 1;
    public static volatile Parser<MapValue> PARSER;
    public MapFieldLite<String, Value> fields_ = MapFieldLite.EMPTY_MAP_FIELD;

    /* JADX INFO: renamed from: com.google.firestore.v1.MapValue$1, reason: invalid class name */
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

    private static final class FieldsDefaultEntryHolder {
        public static final MapEntryLite<String, Value> defaultEntry = new MapEntryLite<>(WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Value.DEFAULT_INSTANCE);
    }

    static {
        MapValue mapValue = new MapValue();
        DEFAULT_INSTANCE = mapValue;
        GeneratedMessageLite.defaultInstanceMap.put(MapValue.class, mapValue);
    }

    public static MapValue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private Map<String, Value> getMutableFieldsMap() {
        return internalGetMutableFields();
    }

    private MapFieldLite<String, Value> internalGetFields() {
        return this.fields_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapFieldLite<String, Value> internalGetMutableFields() {
        if (!this.fields_.isMutable()) {
            this.fields_ = this.fields_.mutableCopy();
        }
        return this.fields_;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static MapValue parseDelimitedFrom(InputStream inputStream) {
        return (MapValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MapValue parseFrom(ByteBuffer byteBuffer) {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<MapValue> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.firestore.v1.MapValueOrBuilder
    public boolean containsFields(String str) {
        str.getClass();
        return this.fields_.containsKey(str);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"fields_", FieldsDefaultEntryHolder.defaultEntry});
            case NEW_MUTABLE_INSTANCE:
                return new MapValue();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<MapValue> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (MapValue.class) {
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

    @Override // com.google.firestore.v1.MapValueOrBuilder
    @Deprecated
    public Map<String, Value> getFields() {
        return getFieldsMap();
    }

    @Override // com.google.firestore.v1.MapValueOrBuilder
    public int getFieldsCount() {
        return this.fields_.size();
    }

    @Override // com.google.firestore.v1.MapValueOrBuilder
    public Map<String, Value> getFieldsMap() {
        return Collections.unmodifiableMap(this.fields_);
    }

    @Override // com.google.firestore.v1.MapValueOrBuilder
    public Value getFieldsOrDefault(String str, Value value) {
        str.getClass();
        MapFieldLite<String, Value> mapFieldLite = this.fields_;
        return mapFieldLite.containsKey(str) ? mapFieldLite.get(str) : value;
    }

    @Override // com.google.firestore.v1.MapValueOrBuilder
    public Value getFieldsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, Value> mapFieldLite = this.fields_;
        if (mapFieldLite.containsKey(str)) {
            return mapFieldLite.get(str);
        }
        throw new IllegalArgumentException();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MapValue, Builder> implements MapValueOrBuilder {
        public Builder() {
            super(MapValue.DEFAULT_INSTANCE);
        }

        public Builder clearFields() {
            copyOnWrite();
            ((MapValue) this.instance).internalGetMutableFields().clear();
            return this;
        }

        @Override // com.google.firestore.v1.MapValueOrBuilder
        public boolean containsFields(String str) {
            str.getClass();
            return ((MapValue) this.instance).getFieldsMap().containsKey(str);
        }

        @Override // com.google.firestore.v1.MapValueOrBuilder
        @Deprecated
        public Map<String, Value> getFields() {
            return getFieldsMap();
        }

        @Override // com.google.firestore.v1.MapValueOrBuilder
        public int getFieldsCount() {
            return ((MapValue) this.instance).getFieldsMap().size();
        }

        @Override // com.google.firestore.v1.MapValueOrBuilder
        public Map<String, Value> getFieldsMap() {
            return Collections.unmodifiableMap(((MapValue) this.instance).getFieldsMap());
        }

        @Override // com.google.firestore.v1.MapValueOrBuilder
        public Value getFieldsOrDefault(String str, Value value) {
            str.getClass();
            Map<String, Value> fieldsMap = ((MapValue) this.instance).getFieldsMap();
            return fieldsMap.containsKey(str) ? fieldsMap.get(str) : value;
        }

        @Override // com.google.firestore.v1.MapValueOrBuilder
        public Value getFieldsOrThrow(String str) {
            str.getClass();
            Map<String, Value> fieldsMap = ((MapValue) this.instance).getFieldsMap();
            if (fieldsMap.containsKey(str)) {
                return fieldsMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        public Builder putAllFields(Map<String, Value> map) {
            copyOnWrite();
            ((MapValue) this.instance).internalGetMutableFields().putAll(map);
            return this;
        }

        public Builder putFields(String str, Value value) {
            str.getClass();
            value.getClass();
            copyOnWrite();
            ((MapValue) this.instance).internalGetMutableFields().put(str, value);
            return this;
        }

        public Builder removeFields(String str) {
            str.getClass();
            copyOnWrite();
            ((MapValue) this.instance).internalGetMutableFields().remove(str);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(MapValue.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(MapValue mapValue) {
        return DEFAULT_INSTANCE.createBuilder(mapValue);
    }

    public static MapValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MapValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MapValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static MapValue parseFrom(ByteString byteString) {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static MapValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MapValue parseFrom(byte[] bArr) {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MapValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MapValue parseFrom(InputStream inputStream) {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MapValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MapValue parseFrom(CodedInputStream codedInputStream) {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MapValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
