package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class ListCollectionIdsRequest extends GeneratedMessageLite<ListCollectionIdsRequest, Builder> implements ListCollectionIdsRequestOrBuilder {
    public static final ListCollectionIdsRequest DEFAULT_INSTANCE;
    public static final int PAGE_SIZE_FIELD_NUMBER = 2;
    public static final int PAGE_TOKEN_FIELD_NUMBER = 3;
    public static final int PARENT_FIELD_NUMBER = 1;
    public static volatile Parser<ListCollectionIdsRequest> PARSER;
    public int pageSize_;
    public String parent_ = "";
    public String pageToken_ = "";

    /* JADX INFO: renamed from: com.google.firestore.v1.ListCollectionIdsRequest$1, reason: invalid class name */
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
        ListCollectionIdsRequest listCollectionIdsRequest = new ListCollectionIdsRequest();
        DEFAULT_INSTANCE = listCollectionIdsRequest;
        GeneratedMessageLite.defaultInstanceMap.put(ListCollectionIdsRequest.class, listCollectionIdsRequest);
    }

    private void clearPageSize() {
        this.pageSize_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPageToken() {
        this.pageToken_ = DEFAULT_INSTANCE.getPageToken();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearParent() {
        this.parent_ = DEFAULT_INSTANCE.getParent();
    }

    public static ListCollectionIdsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static ListCollectionIdsRequest parseDelimitedFrom(InputStream inputStream) {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListCollectionIdsRequest parseFrom(ByteBuffer byteBuffer) {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<ListCollectionIdsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setPageSize(int i) {
        this.pageSize_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPageToken(String str) {
        str.getClass();
        this.pageToken_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPageTokenBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.pageToken_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParent(String str) {
        str.getClass();
        this.parent_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParentBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.parent_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\u0004\u0003Ȉ", new Object[]{"parent_", "pageSize_", "pageToken_"});
            case NEW_MUTABLE_INSTANCE:
                return new ListCollectionIdsRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ListCollectionIdsRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ListCollectionIdsRequest.class) {
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

    @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
    public int getPageSize() {
        return this.pageSize_;
    }

    @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
    public String getPageToken() {
        return this.pageToken_;
    }

    @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
    public ByteString getPageTokenBytes() {
        return ByteString.copyFromUtf8(this.pageToken_);
    }

    @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
    public String getParent() {
        return this.parent_;
    }

    @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
    public ByteString getParentBytes() {
        return ByteString.copyFromUtf8(this.parent_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ListCollectionIdsRequest, Builder> implements ListCollectionIdsRequestOrBuilder {
        public Builder() {
            super(ListCollectionIdsRequest.DEFAULT_INSTANCE);
        }

        public Builder clearPageSize() {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).pageSize_ = 0;
            return this;
        }

        public Builder clearPageToken() {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).clearPageToken();
            return this;
        }

        public Builder clearParent() {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).clearParent();
            return this;
        }

        @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
        public int getPageSize() {
            return ((ListCollectionIdsRequest) this.instance).getPageSize();
        }

        @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
        public String getPageToken() {
            return ((ListCollectionIdsRequest) this.instance).getPageToken();
        }

        @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
        public ByteString getPageTokenBytes() {
            return ((ListCollectionIdsRequest) this.instance).getPageTokenBytes();
        }

        @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
        public String getParent() {
            return ((ListCollectionIdsRequest) this.instance).getParent();
        }

        @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
        public ByteString getParentBytes() {
            return ((ListCollectionIdsRequest) this.instance).getParentBytes();
        }

        public Builder setPageSize(int i) {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).pageSize_ = i;
            return this;
        }

        public Builder setPageToken(String str) {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).setPageToken(str);
            return this;
        }

        public Builder setPageTokenBytes(ByteString byteString) {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).setPageTokenBytes(byteString);
            return this;
        }

        public Builder setParent(String str) {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).setParent(str);
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).setParentBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(ListCollectionIdsRequest.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(ListCollectionIdsRequest listCollectionIdsRequest) {
        return DEFAULT_INSTANCE.createBuilder(listCollectionIdsRequest);
    }

    public static ListCollectionIdsRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListCollectionIdsRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ListCollectionIdsRequest parseFrom(ByteString byteString) {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ListCollectionIdsRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ListCollectionIdsRequest parseFrom(byte[] bArr) {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ListCollectionIdsRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ListCollectionIdsRequest parseFrom(InputStream inputStream) {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListCollectionIdsRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListCollectionIdsRequest parseFrom(CodedInputStream codedInputStream) {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ListCollectionIdsRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
