package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ListCollectionIdsResponse extends GeneratedMessageLite<ListCollectionIdsResponse, Builder> implements ListCollectionIdsResponseOrBuilder {
    public static final int COLLECTION_IDS_FIELD_NUMBER = 1;
    public static final ListCollectionIdsResponse DEFAULT_INSTANCE;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    public static volatile Parser<ListCollectionIdsResponse> PARSER;
    public Internal.ProtobufList<String> collectionIds_ = GeneratedMessageLite.emptyProtobufList();
    public String nextPageToken_ = "";

    /* JADX INFO: renamed from: com.google.firestore.v1.ListCollectionIdsResponse$1, reason: invalid class name */
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

    static {
        ListCollectionIdsResponse listCollectionIdsResponse = new ListCollectionIdsResponse();
        DEFAULT_INSTANCE = listCollectionIdsResponse;
        GeneratedMessageLite.defaultInstanceMap.put(ListCollectionIdsResponse.class, listCollectionIdsResponse);
    }

    public static /* synthetic */ void access$300(ListCollectionIdsResponse listCollectionIdsResponse, Iterable iterable) {
        listCollectionIdsResponse.ensureCollectionIdsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) listCollectionIdsResponse.collectionIds_);
    }

    private void addAllCollectionIds(Iterable<String> iterable) {
        ensureCollectionIdsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.collectionIds_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addCollectionIds(String str) {
        str.getClass();
        ensureCollectionIdsIsMutable();
        this.collectionIds_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addCollectionIdsBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        ensureCollectionIdsIsMutable();
        this.collectionIds_.add(byteString.toStringUtf8());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCollectionIds() {
        this.collectionIds_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNextPageToken() {
        this.nextPageToken_ = DEFAULT_INSTANCE.getNextPageToken();
    }

    private void ensureCollectionIdsIsMutable() {
        Internal.ProtobufList<String> protobufList = this.collectionIds_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.collectionIds_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static ListCollectionIdsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static ListCollectionIdsResponse parseDelimitedFrom(InputStream inputStream) {
        return (ListCollectionIdsResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListCollectionIdsResponse parseFrom(ByteBuffer byteBuffer) {
        return (ListCollectionIdsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<ListCollectionIdsResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCollectionIds(int i, String str) {
        str.getClass();
        ensureCollectionIdsIsMutable();
        this.collectionIds_.set(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNextPageToken(String str) {
        str.getClass();
        this.nextPageToken_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNextPageTokenBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.nextPageToken_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ț\u0002Ȉ", new Object[]{"collectionIds_", "nextPageToken_"});
            case NEW_MUTABLE_INSTANCE:
                return new ListCollectionIdsResponse();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ListCollectionIdsResponse> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ListCollectionIdsResponse.class) {
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

    @Override // com.google.firestore.v1.ListCollectionIdsResponseOrBuilder
    public String getCollectionIds(int i) {
        return this.collectionIds_.get(i);
    }

    @Override // com.google.firestore.v1.ListCollectionIdsResponseOrBuilder
    public ByteString getCollectionIdsBytes(int i) {
        return ByteString.copyFromUtf8(this.collectionIds_.get(i));
    }

    @Override // com.google.firestore.v1.ListCollectionIdsResponseOrBuilder
    public int getCollectionIdsCount() {
        return this.collectionIds_.size();
    }

    @Override // com.google.firestore.v1.ListCollectionIdsResponseOrBuilder
    public List<String> getCollectionIdsList() {
        return this.collectionIds_;
    }

    @Override // com.google.firestore.v1.ListCollectionIdsResponseOrBuilder
    public String getNextPageToken() {
        return this.nextPageToken_;
    }

    @Override // com.google.firestore.v1.ListCollectionIdsResponseOrBuilder
    public ByteString getNextPageTokenBytes() {
        return ByteString.copyFromUtf8(this.nextPageToken_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ListCollectionIdsResponse, Builder> implements ListCollectionIdsResponseOrBuilder {
        public Builder() {
            super(ListCollectionIdsResponse.DEFAULT_INSTANCE);
        }

        public Builder addAllCollectionIds(Iterable<String> iterable) {
            copyOnWrite();
            ListCollectionIdsResponse.access$300((ListCollectionIdsResponse) this.instance, iterable);
            return this;
        }

        public Builder addCollectionIds(String str) {
            copyOnWrite();
            ((ListCollectionIdsResponse) this.instance).addCollectionIds(str);
            return this;
        }

        public Builder addCollectionIdsBytes(ByteString byteString) {
            copyOnWrite();
            ((ListCollectionIdsResponse) this.instance).addCollectionIdsBytes(byteString);
            return this;
        }

        public Builder clearCollectionIds() {
            copyOnWrite();
            ((ListCollectionIdsResponse) this.instance).clearCollectionIds();
            return this;
        }

        public Builder clearNextPageToken() {
            copyOnWrite();
            ((ListCollectionIdsResponse) this.instance).clearNextPageToken();
            return this;
        }

        @Override // com.google.firestore.v1.ListCollectionIdsResponseOrBuilder
        public String getCollectionIds(int i) {
            return ((ListCollectionIdsResponse) this.instance).getCollectionIds(i);
        }

        @Override // com.google.firestore.v1.ListCollectionIdsResponseOrBuilder
        public ByteString getCollectionIdsBytes(int i) {
            return ((ListCollectionIdsResponse) this.instance).getCollectionIdsBytes(i);
        }

        @Override // com.google.firestore.v1.ListCollectionIdsResponseOrBuilder
        public int getCollectionIdsCount() {
            return ((ListCollectionIdsResponse) this.instance).getCollectionIdsCount();
        }

        @Override // com.google.firestore.v1.ListCollectionIdsResponseOrBuilder
        public List<String> getCollectionIdsList() {
            return Collections.unmodifiableList(((ListCollectionIdsResponse) this.instance).getCollectionIdsList());
        }

        @Override // com.google.firestore.v1.ListCollectionIdsResponseOrBuilder
        public String getNextPageToken() {
            return ((ListCollectionIdsResponse) this.instance).getNextPageToken();
        }

        @Override // com.google.firestore.v1.ListCollectionIdsResponseOrBuilder
        public ByteString getNextPageTokenBytes() {
            return ((ListCollectionIdsResponse) this.instance).getNextPageTokenBytes();
        }

        public Builder setCollectionIds(int i, String str) {
            copyOnWrite();
            ((ListCollectionIdsResponse) this.instance).setCollectionIds(i, str);
            return this;
        }

        public Builder setNextPageToken(String str) {
            copyOnWrite();
            ((ListCollectionIdsResponse) this.instance).setNextPageToken(str);
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            copyOnWrite();
            ((ListCollectionIdsResponse) this.instance).setNextPageTokenBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(ListCollectionIdsResponse.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(ListCollectionIdsResponse listCollectionIdsResponse) {
        return DEFAULT_INSTANCE.createBuilder(listCollectionIdsResponse);
    }

    public static ListCollectionIdsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListCollectionIdsResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListCollectionIdsResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (ListCollectionIdsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ListCollectionIdsResponse parseFrom(ByteString byteString) {
        return (ListCollectionIdsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ListCollectionIdsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (ListCollectionIdsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ListCollectionIdsResponse parseFrom(byte[] bArr) {
        return (ListCollectionIdsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ListCollectionIdsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (ListCollectionIdsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ListCollectionIdsResponse parseFrom(InputStream inputStream) {
        return (ListCollectionIdsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListCollectionIdsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListCollectionIdsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListCollectionIdsResponse parseFrom(CodedInputStream codedInputStream) {
        return (ListCollectionIdsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ListCollectionIdsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListCollectionIdsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
