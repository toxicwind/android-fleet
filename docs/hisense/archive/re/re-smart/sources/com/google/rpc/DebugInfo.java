package com.google.rpc;

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

/* JADX INFO: loaded from: classes2.dex */
public final class DebugInfo extends GeneratedMessageLite<DebugInfo, Builder> implements DebugInfoOrBuilder {
    public static final DebugInfo DEFAULT_INSTANCE;
    public static final int DETAIL_FIELD_NUMBER = 2;
    public static volatile Parser<DebugInfo> PARSER = null;
    public static final int STACK_ENTRIES_FIELD_NUMBER = 1;
    public Internal.ProtobufList<String> stackEntries_ = GeneratedMessageLite.emptyProtobufList();
    public String detail_ = "";

    /* JADX INFO: renamed from: com.google.rpc.DebugInfo$1, reason: invalid class name */
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
        DebugInfo debugInfo = new DebugInfo();
        DEFAULT_INSTANCE = debugInfo;
        GeneratedMessageLite.defaultInstanceMap.put(DebugInfo.class, debugInfo);
    }

    public static /* synthetic */ void access$300(DebugInfo debugInfo, Iterable iterable) {
        debugInfo.ensureStackEntriesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) debugInfo.stackEntries_);
    }

    private void addAllStackEntries(Iterable<String> iterable) {
        ensureStackEntriesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.stackEntries_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addStackEntries(String str) {
        str.getClass();
        ensureStackEntriesIsMutable();
        this.stackEntries_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addStackEntriesBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        ensureStackEntriesIsMutable();
        this.stackEntries_.add(byteString.toStringUtf8());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDetail() {
        this.detail_ = DEFAULT_INSTANCE.getDetail();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStackEntries() {
        this.stackEntries_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureStackEntriesIsMutable() {
        Internal.ProtobufList<String> protobufList = this.stackEntries_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.stackEntries_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static DebugInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static DebugInfo parseDelimitedFrom(InputStream inputStream) {
        return (DebugInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DebugInfo parseFrom(ByteBuffer byteBuffer) {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<DebugInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDetail(String str) {
        str.getClass();
        this.detail_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDetailBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.detail_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStackEntries(int i, String str) {
        str.getClass();
        ensureStackEntriesIsMutable();
        this.stackEntries_.set(i, str);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ț\u0002Ȉ", new Object[]{"stackEntries_", "detail_"});
            case NEW_MUTABLE_INSTANCE:
                return new DebugInfo();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<DebugInfo> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (DebugInfo.class) {
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

    @Override // com.google.rpc.DebugInfoOrBuilder
    public String getDetail() {
        return this.detail_;
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public ByteString getDetailBytes() {
        return ByteString.copyFromUtf8(this.detail_);
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public String getStackEntries(int i) {
        return this.stackEntries_.get(i);
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public ByteString getStackEntriesBytes(int i) {
        return ByteString.copyFromUtf8(this.stackEntries_.get(i));
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public int getStackEntriesCount() {
        return this.stackEntries_.size();
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public List<String> getStackEntriesList() {
        return this.stackEntries_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DebugInfo, Builder> implements DebugInfoOrBuilder {
        public Builder() {
            super(DebugInfo.DEFAULT_INSTANCE);
        }

        public Builder addAllStackEntries(Iterable<String> iterable) {
            copyOnWrite();
            DebugInfo.access$300((DebugInfo) this.instance, iterable);
            return this;
        }

        public Builder addStackEntries(String str) {
            copyOnWrite();
            ((DebugInfo) this.instance).addStackEntries(str);
            return this;
        }

        public Builder addStackEntriesBytes(ByteString byteString) {
            copyOnWrite();
            ((DebugInfo) this.instance).addStackEntriesBytes(byteString);
            return this;
        }

        public Builder clearDetail() {
            copyOnWrite();
            ((DebugInfo) this.instance).clearDetail();
            return this;
        }

        public Builder clearStackEntries() {
            copyOnWrite();
            ((DebugInfo) this.instance).clearStackEntries();
            return this;
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public String getDetail() {
            return ((DebugInfo) this.instance).getDetail();
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public ByteString getDetailBytes() {
            return ((DebugInfo) this.instance).getDetailBytes();
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public String getStackEntries(int i) {
            return ((DebugInfo) this.instance).getStackEntries(i);
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public ByteString getStackEntriesBytes(int i) {
            return ((DebugInfo) this.instance).getStackEntriesBytes(i);
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public int getStackEntriesCount() {
            return ((DebugInfo) this.instance).getStackEntriesCount();
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public List<String> getStackEntriesList() {
            return Collections.unmodifiableList(((DebugInfo) this.instance).getStackEntriesList());
        }

        public Builder setDetail(String str) {
            copyOnWrite();
            ((DebugInfo) this.instance).setDetail(str);
            return this;
        }

        public Builder setDetailBytes(ByteString byteString) {
            copyOnWrite();
            ((DebugInfo) this.instance).setDetailBytes(byteString);
            return this;
        }

        public Builder setStackEntries(int i, String str) {
            copyOnWrite();
            ((DebugInfo) this.instance).setStackEntries(i, str);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(DebugInfo.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(DebugInfo debugInfo) {
        return DEFAULT_INSTANCE.createBuilder(debugInfo);
    }

    public static DebugInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DebugInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(ByteString byteString) {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DebugInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(byte[] bArr) {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DebugInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(InputStream inputStream) {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DebugInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(CodedInputStream codedInputStream) {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DebugInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
