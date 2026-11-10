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
public final class SourceInfo extends GeneratedMessageLite<SourceInfo, Builder> implements SourceInfoOrBuilder {
    public static final SourceInfo DEFAULT_INSTANCE;
    public static volatile Parser<SourceInfo> PARSER = null;
    public static final int SOURCE_FILES_FIELD_NUMBER = 1;
    public Internal.ProtobufList<Any> sourceFiles_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.SourceInfo$1, reason: invalid class name */
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
        SourceInfo sourceInfo = new SourceInfo();
        DEFAULT_INSTANCE = sourceInfo;
        GeneratedMessageLite.defaultInstanceMap.put(SourceInfo.class, sourceInfo);
    }

    public static /* synthetic */ void access$400(SourceInfo sourceInfo, Iterable iterable) {
        sourceInfo.ensureSourceFilesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) sourceInfo.sourceFiles_);
    }

    public static /* synthetic */ void access$600(SourceInfo sourceInfo, int i) {
        sourceInfo.ensureSourceFilesIsMutable();
        sourceInfo.sourceFiles_.remove(i);
    }

    private void addAllSourceFiles(Iterable<? extends Any> iterable) {
        ensureSourceFilesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.sourceFiles_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addSourceFiles(Any any) {
        any.getClass();
        ensureSourceFilesIsMutable();
        this.sourceFiles_.add(any);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSourceFiles() {
        this.sourceFiles_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureSourceFilesIsMutable() {
        Internal.ProtobufList<Any> protobufList = this.sourceFiles_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.sourceFiles_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static SourceInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static SourceInfo parseDelimitedFrom(InputStream inputStream) {
        return (SourceInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SourceInfo parseFrom(ByteBuffer byteBuffer) {
        return (SourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<SourceInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeSourceFiles(int i) {
        ensureSourceFilesIsMutable();
        this.sourceFiles_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSourceFiles(int i, Any any) {
        any.getClass();
        ensureSourceFilesIsMutable();
        this.sourceFiles_.set(i, any);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"sourceFiles_", Any.class});
            case NEW_MUTABLE_INSTANCE:
                return new SourceInfo();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<SourceInfo> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (SourceInfo.class) {
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

    @Override // com.google.api.SourceInfoOrBuilder
    public Any getSourceFiles(int i) {
        return this.sourceFiles_.get(i);
    }

    @Override // com.google.api.SourceInfoOrBuilder
    public int getSourceFilesCount() {
        return this.sourceFiles_.size();
    }

    @Override // com.google.api.SourceInfoOrBuilder
    public List<Any> getSourceFilesList() {
        return this.sourceFiles_;
    }

    public AnyOrBuilder getSourceFilesOrBuilder(int i) {
        return this.sourceFiles_.get(i);
    }

    public List<? extends AnyOrBuilder> getSourceFilesOrBuilderList() {
        return this.sourceFiles_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SourceInfo, Builder> implements SourceInfoOrBuilder {
        public Builder() {
            super(SourceInfo.DEFAULT_INSTANCE);
        }

        public Builder addAllSourceFiles(Iterable<? extends Any> iterable) {
            copyOnWrite();
            SourceInfo.access$400((SourceInfo) this.instance, iterable);
            return this;
        }

        public Builder addSourceFiles(Any any) {
            copyOnWrite();
            ((SourceInfo) this.instance).addSourceFiles(any);
            return this;
        }

        public Builder clearSourceFiles() {
            copyOnWrite();
            ((SourceInfo) this.instance).clearSourceFiles();
            return this;
        }

        @Override // com.google.api.SourceInfoOrBuilder
        public Any getSourceFiles(int i) {
            return ((SourceInfo) this.instance).getSourceFiles(i);
        }

        @Override // com.google.api.SourceInfoOrBuilder
        public int getSourceFilesCount() {
            return ((SourceInfo) this.instance).getSourceFilesCount();
        }

        @Override // com.google.api.SourceInfoOrBuilder
        public List<Any> getSourceFilesList() {
            return Collections.unmodifiableList(((SourceInfo) this.instance).getSourceFilesList());
        }

        public Builder removeSourceFiles(int i) {
            copyOnWrite();
            SourceInfo.access$600((SourceInfo) this.instance, i);
            return this;
        }

        public Builder setSourceFiles(int i, Any any) {
            copyOnWrite();
            ((SourceInfo) this.instance).setSourceFiles(i, any);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(SourceInfo.DEFAULT_INSTANCE);
        }

        public Builder addSourceFiles(int i, Any any) {
            copyOnWrite();
            ((SourceInfo) this.instance).addSourceFiles(i, any);
            return this;
        }

        public Builder setSourceFiles(int i, Any.Builder builder) {
            copyOnWrite();
            ((SourceInfo) this.instance).setSourceFiles(i, builder.build());
            return this;
        }

        public Builder addSourceFiles(Any.Builder builder) {
            copyOnWrite();
            ((SourceInfo) this.instance).addSourceFiles(builder.build());
            return this;
        }

        public Builder addSourceFiles(int i, Any.Builder builder) {
            copyOnWrite();
            ((SourceInfo) this.instance).addSourceFiles(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(SourceInfo sourceInfo) {
        return DEFAULT_INSTANCE.createBuilder(sourceInfo);
    }

    public static SourceInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (SourceInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (SourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(ByteString byteString) {
        return (SourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addSourceFiles(int i, Any any) {
        any.getClass();
        ensureSourceFilesIsMutable();
        this.sourceFiles_.add(i, any);
    }

    public static SourceInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (SourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(byte[] bArr) {
        return (SourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static SourceInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (SourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(InputStream inputStream) {
        return (SourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SourceInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (SourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(CodedInputStream codedInputStream) {
        return (SourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static SourceInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (SourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
