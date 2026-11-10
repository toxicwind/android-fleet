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
public final class DocumentMask extends GeneratedMessageLite<DocumentMask, Builder> implements DocumentMaskOrBuilder {
    public static final DocumentMask DEFAULT_INSTANCE;
    public static final int FIELD_PATHS_FIELD_NUMBER = 1;
    public static volatile Parser<DocumentMask> PARSER;
    public Internal.ProtobufList<String> fieldPaths_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.firestore.v1.DocumentMask$1, reason: invalid class name */
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
        DocumentMask documentMask = new DocumentMask();
        DEFAULT_INSTANCE = documentMask;
        GeneratedMessageLite.defaultInstanceMap.put(DocumentMask.class, documentMask);
    }

    public static /* synthetic */ void access$300(DocumentMask documentMask, Iterable iterable) {
        documentMask.ensureFieldPathsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) documentMask.fieldPaths_);
    }

    private void addAllFieldPaths(Iterable<String> iterable) {
        ensureFieldPathsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.fieldPaths_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFieldPaths(String str) {
        str.getClass();
        ensureFieldPathsIsMutable();
        this.fieldPaths_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFieldPathsBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        ensureFieldPathsIsMutable();
        this.fieldPaths_.add(byteString.toStringUtf8());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFieldPaths() {
        this.fieldPaths_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureFieldPathsIsMutable() {
        Internal.ProtobufList<String> protobufList = this.fieldPaths_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.fieldPaths_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static DocumentMask getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static DocumentMask parseDelimitedFrom(InputStream inputStream) {
        return (DocumentMask) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentMask parseFrom(ByteBuffer byteBuffer) {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<DocumentMask> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFieldPaths(int i, String str) {
        str.getClass();
        ensureFieldPathsIsMutable();
        this.fieldPaths_.set(i, str);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001Ț", new Object[]{"fieldPaths_"});
            case NEW_MUTABLE_INSTANCE:
                return new DocumentMask();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<DocumentMask> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (DocumentMask.class) {
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

    @Override // com.google.firestore.v1.DocumentMaskOrBuilder
    public String getFieldPaths(int i) {
        return this.fieldPaths_.get(i);
    }

    @Override // com.google.firestore.v1.DocumentMaskOrBuilder
    public ByteString getFieldPathsBytes(int i) {
        return ByteString.copyFromUtf8(this.fieldPaths_.get(i));
    }

    @Override // com.google.firestore.v1.DocumentMaskOrBuilder
    public int getFieldPathsCount() {
        return this.fieldPaths_.size();
    }

    @Override // com.google.firestore.v1.DocumentMaskOrBuilder
    public List<String> getFieldPathsList() {
        return this.fieldPaths_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DocumentMask, Builder> implements DocumentMaskOrBuilder {
        public Builder() {
            super(DocumentMask.DEFAULT_INSTANCE);
        }

        public Builder addAllFieldPaths(Iterable<String> iterable) {
            copyOnWrite();
            DocumentMask.access$300((DocumentMask) this.instance, iterable);
            return this;
        }

        public Builder addFieldPaths(String str) {
            copyOnWrite();
            ((DocumentMask) this.instance).addFieldPaths(str);
            return this;
        }

        public Builder addFieldPathsBytes(ByteString byteString) {
            copyOnWrite();
            ((DocumentMask) this.instance).addFieldPathsBytes(byteString);
            return this;
        }

        public Builder clearFieldPaths() {
            copyOnWrite();
            ((DocumentMask) this.instance).clearFieldPaths();
            return this;
        }

        @Override // com.google.firestore.v1.DocumentMaskOrBuilder
        public String getFieldPaths(int i) {
            return ((DocumentMask) this.instance).getFieldPaths(i);
        }

        @Override // com.google.firestore.v1.DocumentMaskOrBuilder
        public ByteString getFieldPathsBytes(int i) {
            return ((DocumentMask) this.instance).getFieldPathsBytes(i);
        }

        @Override // com.google.firestore.v1.DocumentMaskOrBuilder
        public int getFieldPathsCount() {
            return ((DocumentMask) this.instance).getFieldPathsCount();
        }

        @Override // com.google.firestore.v1.DocumentMaskOrBuilder
        public List<String> getFieldPathsList() {
            return Collections.unmodifiableList(((DocumentMask) this.instance).getFieldPathsList());
        }

        public Builder setFieldPaths(int i, String str) {
            copyOnWrite();
            ((DocumentMask) this.instance).setFieldPaths(i, str);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(DocumentMask.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(DocumentMask documentMask) {
        return DEFAULT_INSTANCE.createBuilder(documentMask);
    }

    public static DocumentMask parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentMask) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentMask parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DocumentMask parseFrom(ByteString byteString) {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DocumentMask parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DocumentMask parseFrom(byte[] bArr) {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DocumentMask parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DocumentMask parseFrom(InputStream inputStream) {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentMask parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentMask parseFrom(CodedInputStream codedInputStream) {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DocumentMask parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
