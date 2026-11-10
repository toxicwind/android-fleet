package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class WriteRequest extends GeneratedMessageLite<WriteRequest, Builder> implements WriteRequestOrBuilder {
    public static final int DATABASE_FIELD_NUMBER = 1;
    public static final WriteRequest DEFAULT_INSTANCE;
    public static final int LABELS_FIELD_NUMBER = 5;
    public static volatile Parser<WriteRequest> PARSER = null;
    public static final int STREAM_ID_FIELD_NUMBER = 2;
    public static final int STREAM_TOKEN_FIELD_NUMBER = 4;
    public static final int WRITES_FIELD_NUMBER = 3;
    public MapFieldLite<String, String> labels_ = MapFieldLite.EMPTY_MAP_FIELD;
    public String database_ = "";
    public String streamId_ = "";
    public Internal.ProtobufList<Write> writes_ = GeneratedMessageLite.emptyProtobufList();
    public ByteString streamToken_ = ByteString.EMPTY;

    /* JADX INFO: renamed from: com.google.firestore.v1.WriteRequest$1, reason: invalid class name */
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
        WriteRequest writeRequest = new WriteRequest();
        DEFAULT_INSTANCE = writeRequest;
        GeneratedMessageLite.defaultInstanceMap.put(WriteRequest.class, writeRequest);
    }

    public static /* synthetic */ void access$1000(WriteRequest writeRequest, Iterable iterable) {
        writeRequest.ensureWritesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) writeRequest.writes_);
    }

    public static /* synthetic */ void access$1200(WriteRequest writeRequest, int i) {
        writeRequest.ensureWritesIsMutable();
        writeRequest.writes_.remove(i);
    }

    private void addAllWrites(Iterable<? extends Write> iterable) {
        ensureWritesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.writes_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWrites(Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.add(write);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDatabase() {
        this.database_ = DEFAULT_INSTANCE.getDatabase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStreamId() {
        this.streamId_ = DEFAULT_INSTANCE.getStreamId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStreamToken() {
        this.streamToken_ = DEFAULT_INSTANCE.getStreamToken();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearWrites() {
        this.writes_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureWritesIsMutable() {
        Internal.ProtobufList<Write> protobufList = this.writes_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.writes_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static WriteRequest getDefaultInstance() {
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

    public static WriteRequest parseDelimitedFrom(InputStream inputStream) {
        return (WriteRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteRequest parseFrom(ByteBuffer byteBuffer) {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<WriteRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeWrites(int i) {
        ensureWritesIsMutable();
        this.writes_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabase(String str) {
        str.getClass();
        this.database_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabaseBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.database_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStreamId(String str) {
        str.getClass();
        this.streamId_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStreamIdBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.streamId_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStreamToken(ByteString byteString) {
        byteString.getClass();
        this.streamToken_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWrites(int i, Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.set(i, write);
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0001\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003\u001b\u0004\n\u00052", new Object[]{"database_", "streamId_", "writes_", Write.class, "streamToken_", "labels_", LabelsDefaultEntryHolder.defaultEntry});
            case NEW_MUTABLE_INSTANCE:
                return new WriteRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<WriteRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (WriteRequest.class) {
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

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public String getDatabase() {
        return this.database_;
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public ByteString getDatabaseBytes() {
        return ByteString.copyFromUtf8(this.database_);
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    @Deprecated
    public Map<String, String> getLabels() {
        return getLabelsMap();
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public int getLabelsCount() {
        return this.labels_.size();
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public Map<String, String> getLabelsMap() {
        return Collections.unmodifiableMap(this.labels_);
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public String getLabelsOrDefault(String str, String str2) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLite = this.labels_;
        return mapFieldLite.containsKey(str) ? mapFieldLite.get(str) : str2;
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public String getLabelsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLite = this.labels_;
        if (mapFieldLite.containsKey(str)) {
            return mapFieldLite.get(str);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public String getStreamId() {
        return this.streamId_;
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public ByteString getStreamIdBytes() {
        return ByteString.copyFromUtf8(this.streamId_);
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public ByteString getStreamToken() {
        return this.streamToken_;
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public Write getWrites(int i) {
        return this.writes_.get(i);
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public int getWritesCount() {
        return this.writes_.size();
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public List<Write> getWritesList() {
        return this.writes_;
    }

    public WriteOrBuilder getWritesOrBuilder(int i) {
        return this.writes_.get(i);
    }

    public List<? extends WriteOrBuilder> getWritesOrBuilderList() {
        return this.writes_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WriteRequest, Builder> implements WriteRequestOrBuilder {
        public Builder() {
            super(WriteRequest.DEFAULT_INSTANCE);
        }

        public Builder addAllWrites(Iterable<? extends Write> iterable) {
            copyOnWrite();
            WriteRequest.access$1000((WriteRequest) this.instance, iterable);
            return this;
        }

        public Builder addWrites(Write write) {
            copyOnWrite();
            ((WriteRequest) this.instance).addWrites(write);
            return this;
        }

        public Builder clearDatabase() {
            copyOnWrite();
            ((WriteRequest) this.instance).clearDatabase();
            return this;
        }

        public Builder clearLabels() {
            copyOnWrite();
            ((WriteRequest) this.instance).internalGetMutableLabels().clear();
            return this;
        }

        public Builder clearStreamId() {
            copyOnWrite();
            ((WriteRequest) this.instance).clearStreamId();
            return this;
        }

        public Builder clearStreamToken() {
            copyOnWrite();
            ((WriteRequest) this.instance).clearStreamToken();
            return this;
        }

        public Builder clearWrites() {
            copyOnWrite();
            ((WriteRequest) this.instance).clearWrites();
            return this;
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public boolean containsLabels(String str) {
            str.getClass();
            return ((WriteRequest) this.instance).getLabelsMap().containsKey(str);
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public String getDatabase() {
            return ((WriteRequest) this.instance).getDatabase();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public ByteString getDatabaseBytes() {
            return ((WriteRequest) this.instance).getDatabaseBytes();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public int getLabelsCount() {
            return ((WriteRequest) this.instance).getLabelsMap().size();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public Map<String, String> getLabelsMap() {
            return Collections.unmodifiableMap(((WriteRequest) this.instance).getLabelsMap());
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public String getLabelsOrDefault(String str, String str2) {
            str.getClass();
            Map<String, String> labelsMap = ((WriteRequest) this.instance).getLabelsMap();
            return labelsMap.containsKey(str) ? labelsMap.get(str) : str2;
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public String getLabelsOrThrow(String str) {
            str.getClass();
            Map<String, String> labelsMap = ((WriteRequest) this.instance).getLabelsMap();
            if (labelsMap.containsKey(str)) {
                return labelsMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public String getStreamId() {
            return ((WriteRequest) this.instance).getStreamId();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public ByteString getStreamIdBytes() {
            return ((WriteRequest) this.instance).getStreamIdBytes();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public ByteString getStreamToken() {
            return ((WriteRequest) this.instance).getStreamToken();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public Write getWrites(int i) {
            return ((WriteRequest) this.instance).getWrites(i);
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public int getWritesCount() {
            return ((WriteRequest) this.instance).getWritesCount();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public List<Write> getWritesList() {
            return Collections.unmodifiableList(((WriteRequest) this.instance).getWritesList());
        }

        public Builder putAllLabels(Map<String, String> map) {
            copyOnWrite();
            ((WriteRequest) this.instance).internalGetMutableLabels().putAll(map);
            return this;
        }

        public Builder putLabels(String str, String str2) {
            str.getClass();
            str2.getClass();
            copyOnWrite();
            ((WriteRequest) this.instance).internalGetMutableLabels().put(str, str2);
            return this;
        }

        public Builder removeLabels(String str) {
            str.getClass();
            copyOnWrite();
            ((WriteRequest) this.instance).internalGetMutableLabels().remove(str);
            return this;
        }

        public Builder removeWrites(int i) {
            copyOnWrite();
            WriteRequest.access$1200((WriteRequest) this.instance, i);
            return this;
        }

        public Builder setDatabase(String str) {
            copyOnWrite();
            ((WriteRequest) this.instance).setDatabase(str);
            return this;
        }

        public Builder setDatabaseBytes(ByteString byteString) {
            copyOnWrite();
            ((WriteRequest) this.instance).setDatabaseBytes(byteString);
            return this;
        }

        public Builder setStreamId(String str) {
            copyOnWrite();
            ((WriteRequest) this.instance).setStreamId(str);
            return this;
        }

        public Builder setStreamIdBytes(ByteString byteString) {
            copyOnWrite();
            ((WriteRequest) this.instance).setStreamIdBytes(byteString);
            return this;
        }

        public Builder setStreamToken(ByteString byteString) {
            copyOnWrite();
            ((WriteRequest) this.instance).setStreamToken(byteString);
            return this;
        }

        public Builder setWrites(int i, Write write) {
            copyOnWrite();
            ((WriteRequest) this.instance).setWrites(i, write);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(WriteRequest.DEFAULT_INSTANCE);
        }

        public Builder addWrites(int i, Write write) {
            copyOnWrite();
            ((WriteRequest) this.instance).addWrites(i, write);
            return this;
        }

        public Builder setWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteRequest) this.instance).setWrites(i, builder.build());
            return this;
        }

        public Builder addWrites(Write.Builder builder) {
            copyOnWrite();
            ((WriteRequest) this.instance).addWrites(builder.build());
            return this;
        }

        public Builder addWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteRequest) this.instance).addWrites(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(WriteRequest writeRequest) {
        return DEFAULT_INSTANCE.createBuilder(writeRequest);
    }

    public static WriteRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static WriteRequest parseFrom(ByteString byteString) {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWrites(int i, Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.add(i, write);
    }

    public static WriteRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static WriteRequest parseFrom(byte[] bArr) {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static WriteRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static WriteRequest parseFrom(InputStream inputStream) {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteRequest parseFrom(CodedInputStream codedInputStream) {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static WriteRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
