package com.google.rpc;

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

/* JADX INFO: loaded from: classes2.dex */
public final class Status extends GeneratedMessageLite<Status, Builder> implements StatusOrBuilder {
    public static final int CODE_FIELD_NUMBER = 1;
    public static final Status DEFAULT_INSTANCE;
    public static final int DETAILS_FIELD_NUMBER = 3;
    public static final int MESSAGE_FIELD_NUMBER = 2;
    public static volatile Parser<Status> PARSER;
    public int code_;
    public String message_ = "";
    public Internal.ProtobufList<Any> details_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.rpc.Status$1, reason: invalid class name */
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
        Status status = new Status();
        DEFAULT_INSTANCE = status;
        GeneratedMessageLite.defaultInstanceMap.put(Status.class, status);
    }

    public static /* synthetic */ void access$1100(Status status, int i) {
        status.ensureDetailsIsMutable();
        status.details_.remove(i);
    }

    public static /* synthetic */ void access$900(Status status, Iterable iterable) {
        status.ensureDetailsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) status.details_);
    }

    private void addAllDetails(Iterable<? extends Any> iterable) {
        ensureDetailsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.details_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDetails(Any any) {
        any.getClass();
        ensureDetailsIsMutable();
        this.details_.add(any);
    }

    private void clearCode() {
        this.code_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDetails() {
        this.details_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMessage() {
        this.message_ = DEFAULT_INSTANCE.getMessage();
    }

    private void ensureDetailsIsMutable() {
        Internal.ProtobufList<Any> protobufList = this.details_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.details_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static Status getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Status parseDelimitedFrom(InputStream inputStream) {
        return (Status) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Status parseFrom(ByteBuffer byteBuffer) {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Status> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeDetails(int i) {
        ensureDetailsIsMutable();
        this.details_.remove(i);
    }

    private void setCode(int i) {
        this.code_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDetails(int i, Any any) {
        any.getClass();
        ensureDetailsIsMutable();
        this.details_.set(i, any);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMessage(String str) {
        str.getClass();
        this.message_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMessageBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.message_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u0004\u0002Ȉ\u0003\u001b", new Object[]{"code_", "message_", "details_", Any.class});
            case NEW_MUTABLE_INSTANCE:
                return new Status();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Status> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Status.class) {
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

    @Override // com.google.rpc.StatusOrBuilder
    public int getCode() {
        return this.code_;
    }

    @Override // com.google.rpc.StatusOrBuilder
    public Any getDetails(int i) {
        return this.details_.get(i);
    }

    @Override // com.google.rpc.StatusOrBuilder
    public int getDetailsCount() {
        return this.details_.size();
    }

    @Override // com.google.rpc.StatusOrBuilder
    public List<Any> getDetailsList() {
        return this.details_;
    }

    public AnyOrBuilder getDetailsOrBuilder(int i) {
        return this.details_.get(i);
    }

    public List<? extends AnyOrBuilder> getDetailsOrBuilderList() {
        return this.details_;
    }

    @Override // com.google.rpc.StatusOrBuilder
    public String getMessage() {
        return this.message_;
    }

    @Override // com.google.rpc.StatusOrBuilder
    public ByteString getMessageBytes() {
        return ByteString.copyFromUtf8(this.message_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Status, Builder> implements StatusOrBuilder {
        public Builder() {
            super(Status.DEFAULT_INSTANCE);
        }

        public Builder addAllDetails(Iterable<? extends Any> iterable) {
            copyOnWrite();
            Status.access$900((Status) this.instance, iterable);
            return this;
        }

        public Builder addDetails(Any any) {
            copyOnWrite();
            ((Status) this.instance).addDetails(any);
            return this;
        }

        public Builder clearCode() {
            copyOnWrite();
            ((Status) this.instance).code_ = 0;
            return this;
        }

        public Builder clearDetails() {
            copyOnWrite();
            ((Status) this.instance).clearDetails();
            return this;
        }

        public Builder clearMessage() {
            copyOnWrite();
            ((Status) this.instance).clearMessage();
            return this;
        }

        @Override // com.google.rpc.StatusOrBuilder
        public int getCode() {
            return ((Status) this.instance).getCode();
        }

        @Override // com.google.rpc.StatusOrBuilder
        public Any getDetails(int i) {
            return ((Status) this.instance).getDetails(i);
        }

        @Override // com.google.rpc.StatusOrBuilder
        public int getDetailsCount() {
            return ((Status) this.instance).getDetailsCount();
        }

        @Override // com.google.rpc.StatusOrBuilder
        public List<Any> getDetailsList() {
            return Collections.unmodifiableList(((Status) this.instance).getDetailsList());
        }

        @Override // com.google.rpc.StatusOrBuilder
        public String getMessage() {
            return ((Status) this.instance).getMessage();
        }

        @Override // com.google.rpc.StatusOrBuilder
        public ByteString getMessageBytes() {
            return ((Status) this.instance).getMessageBytes();
        }

        public Builder removeDetails(int i) {
            copyOnWrite();
            Status.access$1100((Status) this.instance, i);
            return this;
        }

        public Builder setCode(int i) {
            copyOnWrite();
            ((Status) this.instance).code_ = i;
            return this;
        }

        public Builder setDetails(int i, Any any) {
            copyOnWrite();
            ((Status) this.instance).setDetails(i, any);
            return this;
        }

        public Builder setMessage(String str) {
            copyOnWrite();
            ((Status) this.instance).setMessage(str);
            return this;
        }

        public Builder setMessageBytes(ByteString byteString) {
            copyOnWrite();
            ((Status) this.instance).setMessageBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Status.DEFAULT_INSTANCE);
        }

        public Builder addDetails(int i, Any any) {
            copyOnWrite();
            ((Status) this.instance).addDetails(i, any);
            return this;
        }

        public Builder setDetails(int i, Any.Builder builder) {
            copyOnWrite();
            ((Status) this.instance).setDetails(i, builder.build());
            return this;
        }

        public Builder addDetails(Any.Builder builder) {
            copyOnWrite();
            ((Status) this.instance).addDetails(builder.build());
            return this;
        }

        public Builder addDetails(int i, Any.Builder builder) {
            copyOnWrite();
            ((Status) this.instance).addDetails(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Status status) {
        return DEFAULT_INSTANCE.createBuilder(status);
    }

    public static Status parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Status) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Status parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Status parseFrom(ByteString byteString) {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDetails(int i, Any any) {
        any.getClass();
        ensureDetailsIsMutable();
        this.details_.add(i, any);
    }

    public static Status parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Status parseFrom(byte[] bArr) {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Status parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Status parseFrom(InputStream inputStream) {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Status parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Status parseFrom(CodedInputStream codedInputStream) {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Status parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
