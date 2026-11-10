package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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
public final class HttpBody extends GeneratedMessageLite<HttpBody, Builder> implements HttpBodyOrBuilder {
    public static final int CONTENT_TYPE_FIELD_NUMBER = 1;
    public static final int DATA_FIELD_NUMBER = 2;
    public static final HttpBody DEFAULT_INSTANCE;
    public static final int EXTENSIONS_FIELD_NUMBER = 3;
    public static volatile Parser<HttpBody> PARSER;
    public String contentType_ = "";
    public ByteString data_ = ByteString.EMPTY;
    public Internal.ProtobufList<Any> extensions_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.HttpBody$1, reason: invalid class name */
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
        HttpBody httpBody = new HttpBody();
        DEFAULT_INSTANCE = httpBody;
        GeneratedMessageLite.defaultInstanceMap.put(HttpBody.class, httpBody);
    }

    public static /* synthetic */ void access$1100(HttpBody httpBody, int i) {
        httpBody.ensureExtensionsIsMutable();
        httpBody.extensions_.remove(i);
    }

    public static /* synthetic */ void access$900(HttpBody httpBody, Iterable iterable) {
        httpBody.ensureExtensionsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) httpBody.extensions_);
    }

    private void addAllExtensions(Iterable<? extends Any> iterable) {
        ensureExtensionsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.extensions_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addExtensions(Any any) {
        any.getClass();
        ensureExtensionsIsMutable();
        this.extensions_.add(any);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearContentType() {
        this.contentType_ = DEFAULT_INSTANCE.getContentType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearData() {
        this.data_ = DEFAULT_INSTANCE.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearExtensions() {
        this.extensions_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureExtensionsIsMutable() {
        Internal.ProtobufList<Any> protobufList = this.extensions_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.extensions_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static HttpBody getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static HttpBody parseDelimitedFrom(InputStream inputStream) {
        return (HttpBody) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HttpBody parseFrom(ByteBuffer byteBuffer) {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<HttpBody> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeExtensions(int i) {
        ensureExtensionsIsMutable();
        this.extensions_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContentType(String str) {
        str.getClass();
        this.contentType_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContentTypeBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.contentType_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setData(ByteString byteString) {
        byteString.getClass();
        this.data_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExtensions(int i, Any any) {
        any.getClass();
        ensureExtensionsIsMutable();
        this.extensions_.set(i, any);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\n\u0003\u001b", new Object[]{"contentType_", "data_", "extensions_", Any.class});
            case NEW_MUTABLE_INSTANCE:
                return new HttpBody();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<HttpBody> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (HttpBody.class) {
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

    @Override // com.google.api.HttpBodyOrBuilder
    public String getContentType() {
        return this.contentType_;
    }

    @Override // com.google.api.HttpBodyOrBuilder
    public ByteString getContentTypeBytes() {
        return ByteString.copyFromUtf8(this.contentType_);
    }

    @Override // com.google.api.HttpBodyOrBuilder
    public ByteString getData() {
        return this.data_;
    }

    @Override // com.google.api.HttpBodyOrBuilder
    public Any getExtensions(int i) {
        return this.extensions_.get(i);
    }

    @Override // com.google.api.HttpBodyOrBuilder
    public int getExtensionsCount() {
        return this.extensions_.size();
    }

    @Override // com.google.api.HttpBodyOrBuilder
    public List<Any> getExtensionsList() {
        return this.extensions_;
    }

    public AnyOrBuilder getExtensionsOrBuilder(int i) {
        return this.extensions_.get(i);
    }

    public List<? extends AnyOrBuilder> getExtensionsOrBuilderList() {
        return this.extensions_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HttpBody, Builder> implements HttpBodyOrBuilder {
        public Builder() {
            super(HttpBody.DEFAULT_INSTANCE);
        }

        public Builder addAllExtensions(Iterable<? extends Any> iterable) {
            copyOnWrite();
            HttpBody.access$900((HttpBody) this.instance, iterable);
            return this;
        }

        public Builder addExtensions(Any any) {
            copyOnWrite();
            ((HttpBody) this.instance).addExtensions(any);
            return this;
        }

        public Builder clearContentType() {
            copyOnWrite();
            ((HttpBody) this.instance).clearContentType();
            return this;
        }

        public Builder clearData() {
            copyOnWrite();
            ((HttpBody) this.instance).clearData();
            return this;
        }

        public Builder clearExtensions() {
            copyOnWrite();
            ((HttpBody) this.instance).clearExtensions();
            return this;
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public String getContentType() {
            return ((HttpBody) this.instance).getContentType();
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public ByteString getContentTypeBytes() {
            return ((HttpBody) this.instance).getContentTypeBytes();
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public ByteString getData() {
            return ((HttpBody) this.instance).getData();
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public Any getExtensions(int i) {
            return ((HttpBody) this.instance).getExtensions(i);
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public int getExtensionsCount() {
            return ((HttpBody) this.instance).getExtensionsCount();
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public List<Any> getExtensionsList() {
            return Collections.unmodifiableList(((HttpBody) this.instance).getExtensionsList());
        }

        public Builder removeExtensions(int i) {
            copyOnWrite();
            HttpBody.access$1100((HttpBody) this.instance, i);
            return this;
        }

        public Builder setContentType(String str) {
            copyOnWrite();
            ((HttpBody) this.instance).setContentType(str);
            return this;
        }

        public Builder setContentTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpBody) this.instance).setContentTypeBytes(byteString);
            return this;
        }

        public Builder setData(ByteString byteString) {
            copyOnWrite();
            ((HttpBody) this.instance).setData(byteString);
            return this;
        }

        public Builder setExtensions(int i, Any any) {
            copyOnWrite();
            ((HttpBody) this.instance).setExtensions(i, any);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(HttpBody.DEFAULT_INSTANCE);
        }

        public Builder addExtensions(int i, Any any) {
            copyOnWrite();
            ((HttpBody) this.instance).addExtensions(i, any);
            return this;
        }

        public Builder setExtensions(int i, Any.Builder builder) {
            copyOnWrite();
            ((HttpBody) this.instance).setExtensions(i, builder.build());
            return this;
        }

        public Builder addExtensions(Any.Builder builder) {
            copyOnWrite();
            ((HttpBody) this.instance).addExtensions(builder.build());
            return this;
        }

        public Builder addExtensions(int i, Any.Builder builder) {
            copyOnWrite();
            ((HttpBody) this.instance).addExtensions(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(HttpBody httpBody) {
        return DEFAULT_INSTANCE.createBuilder(httpBody);
    }

    public static HttpBody parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpBody) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HttpBody parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static HttpBody parseFrom(ByteString byteString) {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addExtensions(int i, Any any) {
        any.getClass();
        ensureExtensionsIsMutable();
        this.extensions_.add(i, any);
    }

    public static HttpBody parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static HttpBody parseFrom(byte[] bArr) {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static HttpBody parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static HttpBody parseFrom(InputStream inputStream) {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HttpBody parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HttpBody parseFrom(CodedInputStream codedInputStream) {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static HttpBody parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
