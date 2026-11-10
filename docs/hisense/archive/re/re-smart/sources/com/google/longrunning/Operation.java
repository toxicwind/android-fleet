package com.google.longrunning;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.rpc.Status;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class Operation extends GeneratedMessageLite<Operation, Builder> implements OperationOrBuilder {
    public static final Operation DEFAULT_INSTANCE;
    public static final int DONE_FIELD_NUMBER = 3;
    public static final int ERROR_FIELD_NUMBER = 4;
    public static final int METADATA_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<Operation> PARSER = null;
    public static final int RESPONSE_FIELD_NUMBER = 5;
    public boolean done_;
    public Any metadata_;
    public Object result_;
    public int resultCase_ = 0;
    public String name_ = "";

    /* JADX INFO: renamed from: com.google.longrunning.Operation$1, reason: invalid class name */
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

    public enum ResultCase {
        ERROR(4),
        RESPONSE(5),
        RESULT_NOT_SET(0);

        public final int value;

        ResultCase(int i) {
            this.value = i;
        }

        public static ResultCase forNumber(int i) {
            if (i == 0) {
                return RESULT_NOT_SET;
            }
            if (i == 4) {
                return ERROR;
            }
            if (i != 5) {
                return null;
            }
            return RESPONSE;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ResultCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        Operation operation = new Operation();
        DEFAULT_INSTANCE = operation;
        GeneratedMessageLite.defaultInstanceMap.put(Operation.class, operation);
    }

    private void clearDone() {
        this.done_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearError() {
        if (this.resultCase_ == 4) {
            this.resultCase_ = 0;
            this.result_ = null;
        }
    }

    private void clearMetadata() {
        this.metadata_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResponse() {
        if (this.resultCase_ == 5) {
            this.resultCase_ = 0;
            this.result_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResult() {
        this.resultCase_ = 0;
        this.result_ = null;
    }

    public static Operation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeError(Status status) {
        Object obj;
        status.getClass();
        if (this.resultCase_ != 4 || (obj = this.result_) == Status.DEFAULT_INSTANCE) {
            this.result_ = status;
        } else {
            this.result_ = Status.newBuilder((Status) obj).mergeFrom(status).buildPartial();
        }
        this.resultCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeMetadata(Any any) {
        any.getClass();
        Any any2 = this.metadata_;
        if (any2 == null || any2 == Any.DEFAULT_INSTANCE) {
            this.metadata_ = any;
        } else {
            this.metadata_ = Any.newBuilder(any2).mergeFrom(any).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeResponse(Any any) {
        Object obj;
        any.getClass();
        if (this.resultCase_ != 5 || (obj = this.result_) == Any.DEFAULT_INSTANCE) {
            this.result_ = any;
        } else {
            this.result_ = Any.newBuilder((Any) obj).mergeFrom(any).buildPartial();
        }
        this.resultCase_ = 5;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Operation parseDelimitedFrom(InputStream inputStream) {
        return (Operation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Operation parseFrom(ByteBuffer byteBuffer) {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Operation> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setDone(boolean z) {
        this.done_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setError(Status status) {
        status.getClass();
        this.result_ = status;
        this.resultCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetadata(Any any) {
        any.getClass();
        this.metadata_ = any;
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
    public void setResponse(Any any) {
        any.getClass();
        this.result_ = any;
        this.resultCase_ = 5;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0001\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002\t\u0003\u0007\u0004<\u0000\u0005<\u0000", new Object[]{"result_", "resultCase_", "name_", "metadata_", "done_", Status.class, Any.class});
            case NEW_MUTABLE_INSTANCE:
                return new Operation();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Operation> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Operation.class) {
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

    @Override // com.google.longrunning.OperationOrBuilder
    public boolean getDone() {
        return this.done_;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public Status getError() {
        return this.resultCase_ == 4 ? (Status) this.result_ : Status.DEFAULT_INSTANCE;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public Any getMetadata() {
        Any any = this.metadata_;
        return any == null ? Any.DEFAULT_INSTANCE : any;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public Any getResponse() {
        return this.resultCase_ == 5 ? (Any) this.result_ : Any.DEFAULT_INSTANCE;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public ResultCase getResultCase() {
        return ResultCase.forNumber(this.resultCase_);
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public boolean hasError() {
        return this.resultCase_ == 4;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public boolean hasResponse() {
        return this.resultCase_ == 5;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Operation, Builder> implements OperationOrBuilder {
        public Builder() {
            super(Operation.DEFAULT_INSTANCE);
        }

        public Builder clearDone() {
            copyOnWrite();
            ((Operation) this.instance).done_ = false;
            return this;
        }

        public Builder clearError() {
            copyOnWrite();
            ((Operation) this.instance).clearError();
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((Operation) this.instance).metadata_ = null;
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Operation) this.instance).clearName();
            return this;
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((Operation) this.instance).clearResponse();
            return this;
        }

        public Builder clearResult() {
            copyOnWrite();
            ((Operation) this.instance).clearResult();
            return this;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public boolean getDone() {
            return ((Operation) this.instance).getDone();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public Status getError() {
            return ((Operation) this.instance).getError();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public Any getMetadata() {
            return ((Operation) this.instance).getMetadata();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public String getName() {
            return ((Operation) this.instance).getName();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public ByteString getNameBytes() {
            return ((Operation) this.instance).getNameBytes();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public Any getResponse() {
            return ((Operation) this.instance).getResponse();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public ResultCase getResultCase() {
            return ((Operation) this.instance).getResultCase();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public boolean hasError() {
            return ((Operation) this.instance).hasError();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public boolean hasMetadata() {
            return ((Operation) this.instance).hasMetadata();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public boolean hasResponse() {
            return ((Operation) this.instance).hasResponse();
        }

        public Builder mergeError(Status status) {
            copyOnWrite();
            ((Operation) this.instance).mergeError(status);
            return this;
        }

        public Builder mergeMetadata(Any any) {
            copyOnWrite();
            ((Operation) this.instance).mergeMetadata(any);
            return this;
        }

        public Builder mergeResponse(Any any) {
            copyOnWrite();
            ((Operation) this.instance).mergeResponse(any);
            return this;
        }

        public Builder setDone(boolean z) {
            copyOnWrite();
            ((Operation) this.instance).done_ = z;
            return this;
        }

        public Builder setError(Status status) {
            copyOnWrite();
            ((Operation) this.instance).setError(status);
            return this;
        }

        public Builder setMetadata(Any any) {
            copyOnWrite();
            ((Operation) this.instance).setMetadata(any);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((Operation) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((Operation) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setResponse(Any any) {
            copyOnWrite();
            ((Operation) this.instance).setResponse(any);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Operation.DEFAULT_INSTANCE);
        }

        public Builder setError(Status.Builder builder) {
            copyOnWrite();
            ((Operation) this.instance).setError(builder.build());
            return this;
        }

        public Builder setMetadata(Any.Builder builder) {
            copyOnWrite();
            ((Operation) this.instance).setMetadata(builder.build());
            return this;
        }

        public Builder setResponse(Any.Builder builder) {
            copyOnWrite();
            ((Operation) this.instance).setResponse(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Operation operation) {
        return DEFAULT_INSTANCE.createBuilder(operation);
    }

    public static Operation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Operation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Operation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Operation parseFrom(ByteString byteString) {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Operation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Operation parseFrom(byte[] bArr) {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Operation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Operation parseFrom(InputStream inputStream) {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Operation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Operation parseFrom(CodedInputStream codedInputStream) {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Operation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
