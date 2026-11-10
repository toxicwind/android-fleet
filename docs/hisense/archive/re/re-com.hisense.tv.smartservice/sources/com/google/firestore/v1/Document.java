package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import com.google.protobuf.WireFormat;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class Document extends GeneratedMessageLite<Document, Builder> implements DocumentOrBuilder {
    public static final int CREATE_TIME_FIELD_NUMBER = 3;
    public static final Document DEFAULT_INSTANCE;
    public static final int FIELDS_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<Document> PARSER = null;
    public static final int UPDATE_TIME_FIELD_NUMBER = 4;
    public Timestamp createTime_;
    public MapFieldLite<String, Value> fields_ = MapFieldLite.EMPTY_MAP_FIELD;
    public String name_ = "";
    public Timestamp updateTime_;

    /* JADX INFO: renamed from: com.google.firestore.v1.Document$1, reason: invalid class name */
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
        Document document = new Document();
        DEFAULT_INSTANCE = document;
        GeneratedMessageLite.defaultInstanceMap.put(Document.class, document);
    }

    private void clearCreateTime() {
        this.createTime_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    private void clearUpdateTime() {
        this.updateTime_ = null;
    }

    public static Document getDefaultInstance() {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCreateTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.createTime_;
        if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
            this.createTime_ = timestamp;
        } else {
            this.createTime_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUpdateTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.updateTime_;
        if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
            this.updateTime_ = timestamp;
        } else {
            this.updateTime_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Document parseDelimitedFrom(InputStream inputStream) {
        return (Document) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Document parseFrom(ByteBuffer byteBuffer) {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Document> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCreateTime(Timestamp timestamp) {
        timestamp.getClass();
        this.createTime_ = timestamp;
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
    public void setUpdateTime(Timestamp timestamp) {
        timestamp.getClass();
        this.updateTime_ = timestamp;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0001\u0000\u0000\u0001Ȉ\u00022\u0003\t\u0004\t", new Object[]{"name_", "fields_", FieldsDefaultEntryHolder.defaultEntry, "createTime_", "updateTime_"});
            case NEW_MUTABLE_INSTANCE:
                return new Document();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Document> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Document.class) {
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

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Timestamp getCreateTime() {
        Timestamp timestamp = this.createTime_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    @Deprecated
    public Map<String, Value> getFields() {
        return getFieldsMap();
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public int getFieldsCount() {
        return this.fields_.size();
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Map<String, Value> getFieldsMap() {
        return Collections.unmodifiableMap(this.fields_);
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Value getFieldsOrDefault(String str, Value value) {
        str.getClass();
        MapFieldLite<String, Value> mapFieldLite = this.fields_;
        return mapFieldLite.containsKey(str) ? mapFieldLite.get(str) : value;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Value getFieldsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, Value> mapFieldLite = this.fields_;
        if (mapFieldLite.containsKey(str)) {
            return mapFieldLite.get(str);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Timestamp getUpdateTime() {
        Timestamp timestamp = this.updateTime_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public boolean hasCreateTime() {
        return this.createTime_ != null;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public boolean hasUpdateTime() {
        return this.updateTime_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Document, Builder> implements DocumentOrBuilder {
        public Builder() {
            super(Document.DEFAULT_INSTANCE);
        }

        public Builder clearCreateTime() {
            copyOnWrite();
            ((Document) this.instance).createTime_ = null;
            return this;
        }

        public Builder clearFields() {
            copyOnWrite();
            ((Document) this.instance).internalGetMutableFields().clear();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Document) this.instance).clearName();
            return this;
        }

        public Builder clearUpdateTime() {
            copyOnWrite();
            ((Document) this.instance).updateTime_ = null;
            return this;
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public boolean containsFields(String str) {
            str.getClass();
            return ((Document) this.instance).getFieldsMap().containsKey(str);
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Timestamp getCreateTime() {
            return ((Document) this.instance).getCreateTime();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        @Deprecated
        public Map<String, Value> getFields() {
            return getFieldsMap();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public int getFieldsCount() {
            return ((Document) this.instance).getFieldsMap().size();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Map<String, Value> getFieldsMap() {
            return Collections.unmodifiableMap(((Document) this.instance).getFieldsMap());
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Value getFieldsOrDefault(String str, Value value) {
            str.getClass();
            Map<String, Value> fieldsMap = ((Document) this.instance).getFieldsMap();
            return fieldsMap.containsKey(str) ? fieldsMap.get(str) : value;
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Value getFieldsOrThrow(String str) {
            str.getClass();
            Map<String, Value> fieldsMap = ((Document) this.instance).getFieldsMap();
            if (fieldsMap.containsKey(str)) {
                return fieldsMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public String getName() {
            return ((Document) this.instance).getName();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public ByteString getNameBytes() {
            return ((Document) this.instance).getNameBytes();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Timestamp getUpdateTime() {
            return ((Document) this.instance).getUpdateTime();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public boolean hasCreateTime() {
            return ((Document) this.instance).hasCreateTime();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public boolean hasUpdateTime() {
            return ((Document) this.instance).hasUpdateTime();
        }

        public Builder mergeCreateTime(Timestamp timestamp) {
            copyOnWrite();
            ((Document) this.instance).mergeCreateTime(timestamp);
            return this;
        }

        public Builder mergeUpdateTime(Timestamp timestamp) {
            copyOnWrite();
            ((Document) this.instance).mergeUpdateTime(timestamp);
            return this;
        }

        public Builder putAllFields(Map<String, Value> map) {
            copyOnWrite();
            ((Document) this.instance).internalGetMutableFields().putAll(map);
            return this;
        }

        public Builder putFields(String str, Value value) {
            str.getClass();
            value.getClass();
            copyOnWrite();
            ((Document) this.instance).internalGetMutableFields().put(str, value);
            return this;
        }

        public Builder removeFields(String str) {
            str.getClass();
            copyOnWrite();
            ((Document) this.instance).internalGetMutableFields().remove(str);
            return this;
        }

        public Builder setCreateTime(Timestamp timestamp) {
            copyOnWrite();
            ((Document) this.instance).setCreateTime(timestamp);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((Document) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((Document) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setUpdateTime(Timestamp timestamp) {
            copyOnWrite();
            ((Document) this.instance).setUpdateTime(timestamp);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Document.DEFAULT_INSTANCE);
        }

        public Builder setCreateTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((Document) this.instance).setCreateTime(builder.build());
            return this;
        }

        public Builder setUpdateTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((Document) this.instance).setUpdateTime(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Document document) {
        return DEFAULT_INSTANCE.createBuilder(document);
    }

    public static Document parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Document) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Document parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Document parseFrom(ByteString byteString) {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Document parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Document parseFrom(byte[] bArr) {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Document parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Document parseFrom(InputStream inputStream) {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Document parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Document parseFrom(CodedInputStream codedInputStream) {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Document parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
