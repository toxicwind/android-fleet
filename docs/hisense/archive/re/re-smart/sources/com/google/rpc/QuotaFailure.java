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
public final class QuotaFailure extends GeneratedMessageLite<QuotaFailure, Builder> implements QuotaFailureOrBuilder {
    public static final QuotaFailure DEFAULT_INSTANCE;
    public static volatile Parser<QuotaFailure> PARSER = null;
    public static final int VIOLATIONS_FIELD_NUMBER = 1;
    public Internal.ProtobufList<Violation> violations_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.rpc.QuotaFailure$1, reason: invalid class name */
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

    public static final class Violation extends GeneratedMessageLite<Violation, Builder> implements ViolationOrBuilder {
        public static final Violation DEFAULT_INSTANCE;
        public static final int DESCRIPTION_FIELD_NUMBER = 2;
        public static volatile Parser<Violation> PARSER = null;
        public static final int SUBJECT_FIELD_NUMBER = 1;
        public String subject_ = "";
        public String description_ = "";

        static {
            Violation violation = new Violation();
            DEFAULT_INSTANCE = violation;
            GeneratedMessageLite.defaultInstanceMap.put(Violation.class, violation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDescription() {
            this.description_ = DEFAULT_INSTANCE.getDescription();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSubject() {
            this.subject_ = DEFAULT_INSTANCE.getSubject();
        }

        public static Violation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Violation parseDelimitedFrom(InputStream inputStream) {
            return (Violation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Violation parseFrom(ByteBuffer byteBuffer) {
            return (Violation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Violation> parser() {
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
        public void setSubject(String str) {
            str.getClass();
            this.subject_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSubjectBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.subject_ = byteString.toStringUtf8();
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"subject_", "description_"});
                case NEW_MUTABLE_INSTANCE:
                    return new Violation();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<Violation> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Violation.class) {
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

        @Override // com.google.rpc.QuotaFailure.ViolationOrBuilder
        public String getDescription() {
            return this.description_;
        }

        @Override // com.google.rpc.QuotaFailure.ViolationOrBuilder
        public ByteString getDescriptionBytes() {
            return ByteString.copyFromUtf8(this.description_);
        }

        @Override // com.google.rpc.QuotaFailure.ViolationOrBuilder
        public String getSubject() {
            return this.subject_;
        }

        @Override // com.google.rpc.QuotaFailure.ViolationOrBuilder
        public ByteString getSubjectBytes() {
            return ByteString.copyFromUtf8(this.subject_);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Violation, Builder> implements ViolationOrBuilder {
            public Builder() {
                super(Violation.DEFAULT_INSTANCE);
            }

            public Builder clearDescription() {
                copyOnWrite();
                ((Violation) this.instance).clearDescription();
                return this;
            }

            public Builder clearSubject() {
                copyOnWrite();
                ((Violation) this.instance).clearSubject();
                return this;
            }

            @Override // com.google.rpc.QuotaFailure.ViolationOrBuilder
            public String getDescription() {
                return ((Violation) this.instance).getDescription();
            }

            @Override // com.google.rpc.QuotaFailure.ViolationOrBuilder
            public ByteString getDescriptionBytes() {
                return ((Violation) this.instance).getDescriptionBytes();
            }

            @Override // com.google.rpc.QuotaFailure.ViolationOrBuilder
            public String getSubject() {
                return ((Violation) this.instance).getSubject();
            }

            @Override // com.google.rpc.QuotaFailure.ViolationOrBuilder
            public ByteString getSubjectBytes() {
                return ((Violation) this.instance).getSubjectBytes();
            }

            public Builder setDescription(String str) {
                copyOnWrite();
                ((Violation) this.instance).setDescription(str);
                return this;
            }

            public Builder setDescriptionBytes(ByteString byteString) {
                copyOnWrite();
                ((Violation) this.instance).setDescriptionBytes(byteString);
                return this;
            }

            public Builder setSubject(String str) {
                copyOnWrite();
                ((Violation) this.instance).setSubject(str);
                return this;
            }

            public Builder setSubjectBytes(ByteString byteString) {
                copyOnWrite();
                ((Violation) this.instance).setSubjectBytes(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(Violation.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(Violation violation) {
            return DEFAULT_INSTANCE.createBuilder(violation);
        }

        public static Violation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Violation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Violation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Violation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Violation parseFrom(ByteString byteString) {
            return (Violation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Violation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Violation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Violation parseFrom(byte[] bArr) {
            return (Violation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Violation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Violation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Violation parseFrom(InputStream inputStream) {
            return (Violation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Violation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Violation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Violation parseFrom(CodedInputStream codedInputStream) {
            return (Violation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Violation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Violation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ViolationOrBuilder extends MessageLiteOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        String getSubject();

        ByteString getSubjectBytes();
    }

    static {
        QuotaFailure quotaFailure = new QuotaFailure();
        DEFAULT_INSTANCE = quotaFailure;
        GeneratedMessageLite.defaultInstanceMap.put(QuotaFailure.class, quotaFailure);
    }

    public static /* synthetic */ void access$1200(QuotaFailure quotaFailure, Iterable iterable) {
        quotaFailure.ensureViolationsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) quotaFailure.violations_);
    }

    public static /* synthetic */ void access$1400(QuotaFailure quotaFailure, int i) {
        quotaFailure.ensureViolationsIsMutable();
        quotaFailure.violations_.remove(i);
    }

    private void addAllViolations(Iterable<? extends Violation> iterable) {
        ensureViolationsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.violations_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addViolations(Violation violation) {
        violation.getClass();
        ensureViolationsIsMutable();
        this.violations_.add(violation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearViolations() {
        this.violations_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureViolationsIsMutable() {
        Internal.ProtobufList<Violation> protobufList = this.violations_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.violations_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static QuotaFailure getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static QuotaFailure parseDelimitedFrom(InputStream inputStream) {
        return (QuotaFailure) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static QuotaFailure parseFrom(ByteBuffer byteBuffer) {
        return (QuotaFailure) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<QuotaFailure> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeViolations(int i) {
        ensureViolationsIsMutable();
        this.violations_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setViolations(int i, Violation violation) {
        violation.getClass();
        ensureViolationsIsMutable();
        this.violations_.set(i, violation);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"violations_", Violation.class});
            case NEW_MUTABLE_INSTANCE:
                return new QuotaFailure();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<QuotaFailure> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (QuotaFailure.class) {
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

    @Override // com.google.rpc.QuotaFailureOrBuilder
    public Violation getViolations(int i) {
        return this.violations_.get(i);
    }

    @Override // com.google.rpc.QuotaFailureOrBuilder
    public int getViolationsCount() {
        return this.violations_.size();
    }

    @Override // com.google.rpc.QuotaFailureOrBuilder
    public List<Violation> getViolationsList() {
        return this.violations_;
    }

    public ViolationOrBuilder getViolationsOrBuilder(int i) {
        return this.violations_.get(i);
    }

    public List<? extends ViolationOrBuilder> getViolationsOrBuilderList() {
        return this.violations_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<QuotaFailure, Builder> implements QuotaFailureOrBuilder {
        public Builder() {
            super(QuotaFailure.DEFAULT_INSTANCE);
        }

        public Builder addAllViolations(Iterable<? extends Violation> iterable) {
            copyOnWrite();
            QuotaFailure.access$1200((QuotaFailure) this.instance, iterable);
            return this;
        }

        public Builder addViolations(Violation violation) {
            copyOnWrite();
            ((QuotaFailure) this.instance).addViolations(violation);
            return this;
        }

        public Builder clearViolations() {
            copyOnWrite();
            ((QuotaFailure) this.instance).clearViolations();
            return this;
        }

        @Override // com.google.rpc.QuotaFailureOrBuilder
        public Violation getViolations(int i) {
            return ((QuotaFailure) this.instance).getViolations(i);
        }

        @Override // com.google.rpc.QuotaFailureOrBuilder
        public int getViolationsCount() {
            return ((QuotaFailure) this.instance).getViolationsCount();
        }

        @Override // com.google.rpc.QuotaFailureOrBuilder
        public List<Violation> getViolationsList() {
            return Collections.unmodifiableList(((QuotaFailure) this.instance).getViolationsList());
        }

        public Builder removeViolations(int i) {
            copyOnWrite();
            QuotaFailure.access$1400((QuotaFailure) this.instance, i);
            return this;
        }

        public Builder setViolations(int i, Violation violation) {
            copyOnWrite();
            ((QuotaFailure) this.instance).setViolations(i, violation);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(QuotaFailure.DEFAULT_INSTANCE);
        }

        public Builder addViolations(int i, Violation violation) {
            copyOnWrite();
            ((QuotaFailure) this.instance).addViolations(i, violation);
            return this;
        }

        public Builder setViolations(int i, Violation.Builder builder) {
            copyOnWrite();
            ((QuotaFailure) this.instance).setViolations(i, builder.build());
            return this;
        }

        public Builder addViolations(Violation.Builder builder) {
            copyOnWrite();
            ((QuotaFailure) this.instance).addViolations(builder.build());
            return this;
        }

        public Builder addViolations(int i, Violation.Builder builder) {
            copyOnWrite();
            ((QuotaFailure) this.instance).addViolations(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(QuotaFailure quotaFailure) {
        return DEFAULT_INSTANCE.createBuilder(quotaFailure);
    }

    public static QuotaFailure parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (QuotaFailure) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static QuotaFailure parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (QuotaFailure) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static QuotaFailure parseFrom(ByteString byteString) {
        return (QuotaFailure) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addViolations(int i, Violation violation) {
        violation.getClass();
        ensureViolationsIsMutable();
        this.violations_.add(i, violation);
    }

    public static QuotaFailure parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (QuotaFailure) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static QuotaFailure parseFrom(byte[] bArr) {
        return (QuotaFailure) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static QuotaFailure parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (QuotaFailure) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static QuotaFailure parseFrom(InputStream inputStream) {
        return (QuotaFailure) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static QuotaFailure parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (QuotaFailure) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static QuotaFailure parseFrom(CodedInputStream codedInputStream) {
        return (QuotaFailure) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static QuotaFailure parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (QuotaFailure) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
