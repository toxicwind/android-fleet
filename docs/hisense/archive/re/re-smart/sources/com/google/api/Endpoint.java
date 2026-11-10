package com.google.api;

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
public final class Endpoint extends GeneratedMessageLite<Endpoint, Builder> implements EndpointOrBuilder {
    public static final int ALIASES_FIELD_NUMBER = 2;
    public static final int ALLOW_CORS_FIELD_NUMBER = 5;
    public static final Endpoint DEFAULT_INSTANCE;
    public static final int FEATURES_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<Endpoint> PARSER = null;
    public static final int TARGET_FIELD_NUMBER = 101;
    public boolean allowCors_;
    public String name_ = "";
    public Internal.ProtobufList<String> aliases_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<String> features_ = GeneratedMessageLite.emptyProtobufList();
    public String target_ = "";

    /* JADX INFO: renamed from: com.google.api.Endpoint$1, reason: invalid class name */
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
        Endpoint endpoint = new Endpoint();
        DEFAULT_INSTANCE = endpoint;
        GeneratedMessageLite.defaultInstanceMap.put(Endpoint.class, endpoint);
    }

    public static /* synthetic */ void access$1100(Endpoint endpoint, Iterable iterable) {
        endpoint.ensureFeaturesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) endpoint.features_);
    }

    public static /* synthetic */ void access$600(Endpoint endpoint, Iterable iterable) {
        endpoint.ensureAliasesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) endpoint.aliases_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAliases(String str) {
        str.getClass();
        ensureAliasesIsMutable();
        this.aliases_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAliasesBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        ensureAliasesIsMutable();
        this.aliases_.add(byteString.toStringUtf8());
    }

    private void addAllAliases(Iterable<String> iterable) {
        ensureAliasesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.aliases_);
    }

    private void addAllFeatures(Iterable<String> iterable) {
        ensureFeaturesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.features_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFeatures(String str) {
        str.getClass();
        ensureFeaturesIsMutable();
        this.features_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFeaturesBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        ensureFeaturesIsMutable();
        this.features_.add(byteString.toStringUtf8());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAliases() {
        this.aliases_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearAllowCors() {
        this.allowCors_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFeatures() {
        this.features_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTarget() {
        this.target_ = DEFAULT_INSTANCE.getTarget();
    }

    private void ensureAliasesIsMutable() {
        Internal.ProtobufList<String> protobufList = this.aliases_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.aliases_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureFeaturesIsMutable() {
        Internal.ProtobufList<String> protobufList = this.features_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.features_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static Endpoint getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Endpoint parseDelimitedFrom(InputStream inputStream) {
        return (Endpoint) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Endpoint parseFrom(ByteBuffer byteBuffer) {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Endpoint> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAliases(int i, String str) {
        str.getClass();
        ensureAliasesIsMutable();
        this.aliases_.set(i, str);
    }

    private void setAllowCors(boolean z) {
        this.allowCors_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFeatures(int i, String str) {
        str.getClass();
        ensureFeaturesIsMutable();
        this.features_.set(i, str);
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
    public void setTarget(String str) {
        str.getClass();
        this.target_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.target_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001e\u0005\u0000\u0002\u0000\u0001Ȉ\u0002Ț\u0004Ț\u0005\u0007eȈ", new Object[]{"name_", "aliases_", "features_", "allowCors_", "target_"});
            case NEW_MUTABLE_INSTANCE:
                return new Endpoint();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Endpoint> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Endpoint.class) {
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

    @Override // com.google.api.EndpointOrBuilder
    @Deprecated
    public String getAliases(int i) {
        return this.aliases_.get(i);
    }

    @Override // com.google.api.EndpointOrBuilder
    @Deprecated
    public ByteString getAliasesBytes(int i) {
        return ByteString.copyFromUtf8(this.aliases_.get(i));
    }

    @Override // com.google.api.EndpointOrBuilder
    @Deprecated
    public int getAliasesCount() {
        return this.aliases_.size();
    }

    @Override // com.google.api.EndpointOrBuilder
    @Deprecated
    public List<String> getAliasesList() {
        return this.aliases_;
    }

    @Override // com.google.api.EndpointOrBuilder
    public boolean getAllowCors() {
        return this.allowCors_;
    }

    @Override // com.google.api.EndpointOrBuilder
    public String getFeatures(int i) {
        return this.features_.get(i);
    }

    @Override // com.google.api.EndpointOrBuilder
    public ByteString getFeaturesBytes(int i) {
        return ByteString.copyFromUtf8(this.features_.get(i));
    }

    @Override // com.google.api.EndpointOrBuilder
    public int getFeaturesCount() {
        return this.features_.size();
    }

    @Override // com.google.api.EndpointOrBuilder
    public List<String> getFeaturesList() {
        return this.features_;
    }

    @Override // com.google.api.EndpointOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.api.EndpointOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.api.EndpointOrBuilder
    public String getTarget() {
        return this.target_;
    }

    @Override // com.google.api.EndpointOrBuilder
    public ByteString getTargetBytes() {
        return ByteString.copyFromUtf8(this.target_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Endpoint, Builder> implements EndpointOrBuilder {
        public Builder() {
            super(Endpoint.DEFAULT_INSTANCE);
        }

        @Deprecated
        public Builder addAliases(String str) {
            copyOnWrite();
            ((Endpoint) this.instance).addAliases(str);
            return this;
        }

        @Deprecated
        public Builder addAliasesBytes(ByteString byteString) {
            copyOnWrite();
            ((Endpoint) this.instance).addAliasesBytes(byteString);
            return this;
        }

        @Deprecated
        public Builder addAllAliases(Iterable<String> iterable) {
            copyOnWrite();
            Endpoint.access$600((Endpoint) this.instance, iterable);
            return this;
        }

        public Builder addAllFeatures(Iterable<String> iterable) {
            copyOnWrite();
            Endpoint.access$1100((Endpoint) this.instance, iterable);
            return this;
        }

        public Builder addFeatures(String str) {
            copyOnWrite();
            ((Endpoint) this.instance).addFeatures(str);
            return this;
        }

        public Builder addFeaturesBytes(ByteString byteString) {
            copyOnWrite();
            ((Endpoint) this.instance).addFeaturesBytes(byteString);
            return this;
        }

        @Deprecated
        public Builder clearAliases() {
            copyOnWrite();
            ((Endpoint) this.instance).clearAliases();
            return this;
        }

        public Builder clearAllowCors() {
            copyOnWrite();
            ((Endpoint) this.instance).allowCors_ = false;
            return this;
        }

        public Builder clearFeatures() {
            copyOnWrite();
            ((Endpoint) this.instance).clearFeatures();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Endpoint) this.instance).clearName();
            return this;
        }

        public Builder clearTarget() {
            copyOnWrite();
            ((Endpoint) this.instance).clearTarget();
            return this;
        }

        @Override // com.google.api.EndpointOrBuilder
        @Deprecated
        public String getAliases(int i) {
            return ((Endpoint) this.instance).getAliases(i);
        }

        @Override // com.google.api.EndpointOrBuilder
        @Deprecated
        public ByteString getAliasesBytes(int i) {
            return ((Endpoint) this.instance).getAliasesBytes(i);
        }

        @Override // com.google.api.EndpointOrBuilder
        @Deprecated
        public int getAliasesCount() {
            return ((Endpoint) this.instance).getAliasesCount();
        }

        @Override // com.google.api.EndpointOrBuilder
        @Deprecated
        public List<String> getAliasesList() {
            return Collections.unmodifiableList(((Endpoint) this.instance).getAliasesList());
        }

        @Override // com.google.api.EndpointOrBuilder
        public boolean getAllowCors() {
            return ((Endpoint) this.instance).getAllowCors();
        }

        @Override // com.google.api.EndpointOrBuilder
        public String getFeatures(int i) {
            return ((Endpoint) this.instance).getFeatures(i);
        }

        @Override // com.google.api.EndpointOrBuilder
        public ByteString getFeaturesBytes(int i) {
            return ((Endpoint) this.instance).getFeaturesBytes(i);
        }

        @Override // com.google.api.EndpointOrBuilder
        public int getFeaturesCount() {
            return ((Endpoint) this.instance).getFeaturesCount();
        }

        @Override // com.google.api.EndpointOrBuilder
        public List<String> getFeaturesList() {
            return Collections.unmodifiableList(((Endpoint) this.instance).getFeaturesList());
        }

        @Override // com.google.api.EndpointOrBuilder
        public String getName() {
            return ((Endpoint) this.instance).getName();
        }

        @Override // com.google.api.EndpointOrBuilder
        public ByteString getNameBytes() {
            return ((Endpoint) this.instance).getNameBytes();
        }

        @Override // com.google.api.EndpointOrBuilder
        public String getTarget() {
            return ((Endpoint) this.instance).getTarget();
        }

        @Override // com.google.api.EndpointOrBuilder
        public ByteString getTargetBytes() {
            return ((Endpoint) this.instance).getTargetBytes();
        }

        @Deprecated
        public Builder setAliases(int i, String str) {
            copyOnWrite();
            ((Endpoint) this.instance).setAliases(i, str);
            return this;
        }

        public Builder setAllowCors(boolean z) {
            copyOnWrite();
            ((Endpoint) this.instance).allowCors_ = z;
            return this;
        }

        public Builder setFeatures(int i, String str) {
            copyOnWrite();
            ((Endpoint) this.instance).setFeatures(i, str);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((Endpoint) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((Endpoint) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setTarget(String str) {
            copyOnWrite();
            ((Endpoint) this.instance).setTarget(str);
            return this;
        }

        public Builder setTargetBytes(ByteString byteString) {
            copyOnWrite();
            ((Endpoint) this.instance).setTargetBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Endpoint.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(Endpoint endpoint) {
        return DEFAULT_INSTANCE.createBuilder(endpoint);
    }

    public static Endpoint parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Endpoint) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Endpoint parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Endpoint parseFrom(ByteString byteString) {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Endpoint parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Endpoint parseFrom(byte[] bArr) {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Endpoint parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Endpoint parseFrom(InputStream inputStream) {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Endpoint parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Endpoint parseFrom(CodedInputStream codedInputStream) {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Endpoint parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
