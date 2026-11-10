package com.google.rpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class BadRequest extends GeneratedMessageLite<BadRequest, Builder> implements BadRequestOrBuilder {
    public static final BadRequest DEFAULT_INSTANCE;
    public static final int FIELD_VIOLATIONS_FIELD_NUMBER = 1;
    public static volatile Parser<BadRequest> PARSER;
    public Internal.ProtobufList<FieldViolation> fieldViolations_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.rpc.BadRequest$1, reason: invalid class name */
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

    public static final class FieldViolation extends GeneratedMessageLite<FieldViolation, Builder> implements FieldViolationOrBuilder {
        public static final FieldViolation DEFAULT_INSTANCE;
        public static final int DESCRIPTION_FIELD_NUMBER = 2;
        public static final int FIELD_FIELD_NUMBER = 1;
        public static volatile Parser<FieldViolation> PARSER;
        public String field_ = "";
        public String description_ = "";

        static {
            FieldViolation fieldViolation = new FieldViolation();
            DEFAULT_INSTANCE = fieldViolation;
            GeneratedMessageLite.defaultInstanceMap.put(FieldViolation.class, fieldViolation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDescription() {
            this.description_ = DEFAULT_INSTANCE.getDescription();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearField() {
            this.field_ = DEFAULT_INSTANCE.getField();
        }

        public static FieldViolation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static FieldViolation parseDelimitedFrom(InputStream inputStream) {
            return (FieldViolation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldViolation parseFrom(ByteBuffer byteBuffer) {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<FieldViolation> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDescription(String str) {
            str.getClass();
            this.description_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDescriptionBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.description_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setField(String str) {
            str.getClass();
            this.field_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFieldBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.field_ = byteString.toStringUtf8();
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"field_", "description_"});
                case NEW_MUTABLE_INSTANCE:
                    return new FieldViolation();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<FieldViolation> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (FieldViolation.class) {
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

        @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
        public String getDescription() {
            return this.description_;
        }

        @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
        public ByteString getDescriptionBytes() {
            return ByteString.copyFromUtf8(this.description_);
        }

        @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
        public String getField() {
            return this.field_;
        }

        @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
        public ByteString getFieldBytes() {
            return ByteString.copyFromUtf8(this.field_);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<FieldViolation, Builder> implements FieldViolationOrBuilder {
            public Builder() {
                super(FieldViolation.DEFAULT_INSTANCE);
            }

            public Builder clearDescription() {
                copyOnWrite();
                ((FieldViolation) this.instance).clearDescription();
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((FieldViolation) this.instance).clearField();
                return this;
            }

            @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
            public String getDescription() {
                return ((FieldViolation) this.instance).getDescription();
            }

            @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
            public ByteString getDescriptionBytes() {
                return ((FieldViolation) this.instance).getDescriptionBytes();
            }

            @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
            public String getField() {
                return ((FieldViolation) this.instance).getField();
            }

            @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
            public ByteString getFieldBytes() {
                return ((FieldViolation) this.instance).getFieldBytes();
            }

            public Builder setDescription(String str) {
                copyOnWrite();
                ((FieldViolation) this.instance).setDescription(str);
                return this;
            }

            public Builder setDescriptionBytes(ByteString byteString) {
                copyOnWrite();
                ((FieldViolation) this.instance).setDescriptionBytes(byteString);
                return this;
            }

            public Builder setField(String str) {
                copyOnWrite();
                ((FieldViolation) this.instance).setField(str);
                return this;
            }

            public Builder setFieldBytes(ByteString byteString) {
                copyOnWrite();
                ((FieldViolation) this.instance).setFieldBytes(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(FieldViolation.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(FieldViolation fieldViolation) {
            return DEFAULT_INSTANCE.createBuilder(fieldViolation);
        }

        public static FieldViolation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldViolation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(ByteString byteString) {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FieldViolation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(byte[] bArr) {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FieldViolation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(InputStream inputStream) {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldViolation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(CodedInputStream codedInputStream) {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FieldViolation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface FieldViolationOrBuilder extends MessageLiteOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        String getField();

        ByteString getFieldBytes();
    }

    static {
        BadRequest badRequest = new BadRequest();
        DEFAULT_INSTANCE = badRequest;
        GeneratedMessageLite.defaultInstanceMap.put(BadRequest.class, badRequest);
    }

    public static /* synthetic */ void access$1200(BadRequest badRequest, Iterable iterable) {
        badRequest.ensureFieldViolationsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) badRequest.fieldViolations_);
    }

    public static /* synthetic */ void access$1400(BadRequest badRequest, int i) {
        badRequest.ensureFieldViolationsIsMutable();
        badRequest.fieldViolations_.remove(i);
    }

    private void addAllFieldViolations(Iterable<? extends FieldViolation> iterable) {
        ensureFieldViolationsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.fieldViolations_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFieldViolations(FieldViolation fieldViolation) {
        fieldViolation.getClass();
        ensureFieldViolationsIsMutable();
        this.fieldViolations_.add(fieldViolation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFieldViolations() {
        this.fieldViolations_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureFieldViolationsIsMutable() {
        Internal.ProtobufList<FieldViolation> protobufList = this.fieldViolations_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.fieldViolations_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static BadRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static BadRequest parseDelimitedFrom(InputStream inputStream) {
        return (BadRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BadRequest parseFrom(ByteBuffer byteBuffer) {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<BadRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeFieldViolations(int i) {
        ensureFieldViolationsIsMutable();
        this.fieldViolations_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFieldViolations(int i, FieldViolation fieldViolation) {
        fieldViolation.getClass();
        ensureFieldViolationsIsMutable();
        this.fieldViolations_.set(i, fieldViolation);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"fieldViolations_", FieldViolation.class});
            case NEW_MUTABLE_INSTANCE:
                return new BadRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<BadRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (BadRequest.class) {
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

    @Override // com.google.rpc.BadRequestOrBuilder
    public FieldViolation getFieldViolations(int i) {
        return this.fieldViolations_.get(i);
    }

    @Override // com.google.rpc.BadRequestOrBuilder
    public int getFieldViolationsCount() {
        return this.fieldViolations_.size();
    }

    @Override // com.google.rpc.BadRequestOrBuilder
    public List<FieldViolation> getFieldViolationsList() {
        return this.fieldViolations_;
    }

    public FieldViolationOrBuilder getFieldViolationsOrBuilder(int i) {
        return this.fieldViolations_.get(i);
    }

    public List<? extends FieldViolationOrBuilder> getFieldViolationsOrBuilderList() {
        return this.fieldViolations_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BadRequest, Builder> implements BadRequestOrBuilder {
        public Builder() {
            super(BadRequest.DEFAULT_INSTANCE);
        }

        public Builder addAllFieldViolations(Iterable<? extends FieldViolation> iterable) {
            copyOnWrite();
            BadRequest.access$1200((BadRequest) this.instance, iterable);
            return this;
        }

        public Builder addFieldViolations(FieldViolation fieldViolation) {
            copyOnWrite();
            ((BadRequest) this.instance).addFieldViolations(fieldViolation);
            return this;
        }

        public Builder clearFieldViolations() {
            copyOnWrite();
            ((BadRequest) this.instance).clearFieldViolations();
            return this;
        }

        @Override // com.google.rpc.BadRequestOrBuilder
        public FieldViolation getFieldViolations(int i) {
            return ((BadRequest) this.instance).getFieldViolations(i);
        }

        @Override // com.google.rpc.BadRequestOrBuilder
        public int getFieldViolationsCount() {
            return ((BadRequest) this.instance).getFieldViolationsCount();
        }

        @Override // com.google.rpc.BadRequestOrBuilder
        public List<FieldViolation> getFieldViolationsList() {
            return Collections.unmodifiableList(((BadRequest) this.instance).getFieldViolationsList());
        }

        public Builder removeFieldViolations(int i) {
            copyOnWrite();
            BadRequest.access$1400((BadRequest) this.instance, i);
            return this;
        }

        public Builder setFieldViolations(int i, FieldViolation fieldViolation) {
            copyOnWrite();
            ((BadRequest) this.instance).setFieldViolations(i, fieldViolation);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(BadRequest.DEFAULT_INSTANCE);
        }

        public Builder addFieldViolations(int i, FieldViolation fieldViolation) {
            copyOnWrite();
            ((BadRequest) this.instance).addFieldViolations(i, fieldViolation);
            return this;
        }

        public Builder setFieldViolations(int i, FieldViolation.Builder builder) {
            copyOnWrite();
            ((BadRequest) this.instance).setFieldViolations(i, builder.build());
            return this;
        }

        public Builder addFieldViolations(FieldViolation.Builder builder) {
            copyOnWrite();
            ((BadRequest) this.instance).addFieldViolations(builder.build());
            return this;
        }

        public Builder addFieldViolations(int i, FieldViolation.Builder builder) {
            copyOnWrite();
            ((BadRequest) this.instance).addFieldViolations(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(BadRequest badRequest) {
        return DEFAULT_INSTANCE.createBuilder(badRequest);
    }

    public static BadRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BadRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BadRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BadRequest parseFrom(ByteString byteString) {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFieldViolations(int i, FieldViolation fieldViolation) {
        fieldViolation.getClass();
        ensureFieldViolationsIsMutable();
        this.fieldViolations_.add(i, fieldViolation);
    }

    public static BadRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BadRequest parseFrom(byte[] bArr) {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BadRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BadRequest parseFrom(InputStream inputStream) {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BadRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BadRequest parseFrom(CodedInputStream codedInputStream) {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BadRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
