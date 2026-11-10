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
public final class ContextRule extends GeneratedMessageLite<ContextRule, Builder> implements ContextRuleOrBuilder {
    public static final int ALLOWED_REQUEST_EXTENSIONS_FIELD_NUMBER = 4;
    public static final int ALLOWED_RESPONSE_EXTENSIONS_FIELD_NUMBER = 5;
    public static final ContextRule DEFAULT_INSTANCE;
    public static volatile Parser<ContextRule> PARSER = null;
    public static final int PROVIDED_FIELD_NUMBER = 3;
    public static final int REQUESTED_FIELD_NUMBER = 2;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    public String selector_ = "";
    public Internal.ProtobufList<String> requested_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<String> provided_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<String> allowedRequestExtensions_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<String> allowedResponseExtensions_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.ContextRule$1, reason: invalid class name */
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
        ContextRule contextRule = new ContextRule();
        DEFAULT_INSTANCE = contextRule;
        GeneratedMessageLite.defaultInstanceMap.put(ContextRule.class, contextRule);
    }

    public static /* synthetic */ void access$1100(ContextRule contextRule, Iterable iterable) {
        contextRule.ensureProvidedIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) contextRule.provided_);
    }

    public static /* synthetic */ void access$1600(ContextRule contextRule, Iterable iterable) {
        contextRule.ensureAllowedRequestExtensionsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) contextRule.allowedRequestExtensions_);
    }

    public static /* synthetic */ void access$2100(ContextRule contextRule, Iterable iterable) {
        contextRule.ensureAllowedResponseExtensionsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) contextRule.allowedResponseExtensions_);
    }

    public static /* synthetic */ void access$600(ContextRule contextRule, Iterable iterable) {
        contextRule.ensureRequestedIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) contextRule.requested_);
    }

    private void addAllAllowedRequestExtensions(Iterable<String> iterable) {
        ensureAllowedRequestExtensionsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.allowedRequestExtensions_);
    }

    private void addAllAllowedResponseExtensions(Iterable<String> iterable) {
        ensureAllowedResponseExtensionsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.allowedResponseExtensions_);
    }

    private void addAllProvided(Iterable<String> iterable) {
        ensureProvidedIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.provided_);
    }

    private void addAllRequested(Iterable<String> iterable) {
        ensureRequestedIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.requested_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllowedRequestExtensions(String str) {
        str.getClass();
        ensureAllowedRequestExtensionsIsMutable();
        this.allowedRequestExtensions_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllowedRequestExtensionsBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        ensureAllowedRequestExtensionsIsMutable();
        this.allowedRequestExtensions_.add(byteString.toStringUtf8());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllowedResponseExtensions(String str) {
        str.getClass();
        ensureAllowedResponseExtensionsIsMutable();
        this.allowedResponseExtensions_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllowedResponseExtensionsBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        ensureAllowedResponseExtensionsIsMutable();
        this.allowedResponseExtensions_.add(byteString.toStringUtf8());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProvided(String str) {
        str.getClass();
        ensureProvidedIsMutable();
        this.provided_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProvidedBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        ensureProvidedIsMutable();
        this.provided_.add(byteString.toStringUtf8());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRequested(String str) {
        str.getClass();
        ensureRequestedIsMutable();
        this.requested_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRequestedBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        ensureRequestedIsMutable();
        this.requested_.add(byteString.toStringUtf8());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAllowedRequestExtensions() {
        this.allowedRequestExtensions_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAllowedResponseExtensions() {
        this.allowedResponseExtensions_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearProvided() {
        this.provided_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRequested() {
        this.requested_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSelector() {
        this.selector_ = DEFAULT_INSTANCE.getSelector();
    }

    private void ensureAllowedRequestExtensionsIsMutable() {
        Internal.ProtobufList<String> protobufList = this.allowedRequestExtensions_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.allowedRequestExtensions_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureAllowedResponseExtensionsIsMutable() {
        Internal.ProtobufList<String> protobufList = this.allowedResponseExtensions_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.allowedResponseExtensions_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureProvidedIsMutable() {
        Internal.ProtobufList<String> protobufList = this.provided_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.provided_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureRequestedIsMutable() {
        Internal.ProtobufList<String> protobufList = this.requested_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.requested_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static ContextRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static ContextRule parseDelimitedFrom(InputStream inputStream) {
        return (ContextRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ContextRule parseFrom(ByteBuffer byteBuffer) {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<ContextRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAllowedRequestExtensions(int i, String str) {
        str.getClass();
        ensureAllowedRequestExtensionsIsMutable();
        this.allowedRequestExtensions_.set(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAllowedResponseExtensions(int i, String str) {
        str.getClass();
        ensureAllowedResponseExtensionsIsMutable();
        this.allowedResponseExtensions_.set(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProvided(int i, String str) {
        str.getClass();
        ensureProvidedIsMutable();
        this.provided_.set(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequested(int i, String str) {
        str.getClass();
        ensureRequestedIsMutable();
        this.requested_.set(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelector(String str) {
        str.getClass();
        this.selector_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectorBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.selector_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0004\u0000\u0001Ȉ\u0002Ț\u0003Ț\u0004Ț\u0005Ț", new Object[]{"selector_", "requested_", "provided_", "allowedRequestExtensions_", "allowedResponseExtensions_"});
            case NEW_MUTABLE_INSTANCE:
                return new ContextRule();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ContextRule> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ContextRule.class) {
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

    @Override // com.google.api.ContextRuleOrBuilder
    public String getAllowedRequestExtensions(int i) {
        return this.allowedRequestExtensions_.get(i);
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public ByteString getAllowedRequestExtensionsBytes(int i) {
        return ByteString.copyFromUtf8(this.allowedRequestExtensions_.get(i));
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public int getAllowedRequestExtensionsCount() {
        return this.allowedRequestExtensions_.size();
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public List<String> getAllowedRequestExtensionsList() {
        return this.allowedRequestExtensions_;
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public String getAllowedResponseExtensions(int i) {
        return this.allowedResponseExtensions_.get(i);
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public ByteString getAllowedResponseExtensionsBytes(int i) {
        return ByteString.copyFromUtf8(this.allowedResponseExtensions_.get(i));
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public int getAllowedResponseExtensionsCount() {
        return this.allowedResponseExtensions_.size();
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public List<String> getAllowedResponseExtensionsList() {
        return this.allowedResponseExtensions_;
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public String getProvided(int i) {
        return this.provided_.get(i);
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public ByteString getProvidedBytes(int i) {
        return ByteString.copyFromUtf8(this.provided_.get(i));
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public int getProvidedCount() {
        return this.provided_.size();
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public List<String> getProvidedList() {
        return this.provided_;
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public String getRequested(int i) {
        return this.requested_.get(i);
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public ByteString getRequestedBytes(int i) {
        return ByteString.copyFromUtf8(this.requested_.get(i));
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public int getRequestedCount() {
        return this.requested_.size();
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public List<String> getRequestedList() {
        return this.requested_;
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public String getSelector() {
        return this.selector_;
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public ByteString getSelectorBytes() {
        return ByteString.copyFromUtf8(this.selector_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ContextRule, Builder> implements ContextRuleOrBuilder {
        public Builder() {
            super(ContextRule.DEFAULT_INSTANCE);
        }

        public Builder addAllAllowedRequestExtensions(Iterable<String> iterable) {
            copyOnWrite();
            ContextRule.access$1600((ContextRule) this.instance, iterable);
            return this;
        }

        public Builder addAllAllowedResponseExtensions(Iterable<String> iterable) {
            copyOnWrite();
            ContextRule.access$2100((ContextRule) this.instance, iterable);
            return this;
        }

        public Builder addAllProvided(Iterable<String> iterable) {
            copyOnWrite();
            ContextRule.access$1100((ContextRule) this.instance, iterable);
            return this;
        }

        public Builder addAllRequested(Iterable<String> iterable) {
            copyOnWrite();
            ContextRule.access$600((ContextRule) this.instance, iterable);
            return this;
        }

        public Builder addAllowedRequestExtensions(String str) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllowedRequestExtensions(str);
            return this;
        }

        public Builder addAllowedRequestExtensionsBytes(ByteString byteString) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllowedRequestExtensionsBytes(byteString);
            return this;
        }

        public Builder addAllowedResponseExtensions(String str) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllowedResponseExtensions(str);
            return this;
        }

        public Builder addAllowedResponseExtensionsBytes(ByteString byteString) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllowedResponseExtensionsBytes(byteString);
            return this;
        }

        public Builder addProvided(String str) {
            copyOnWrite();
            ((ContextRule) this.instance).addProvided(str);
            return this;
        }

        public Builder addProvidedBytes(ByteString byteString) {
            copyOnWrite();
            ((ContextRule) this.instance).addProvidedBytes(byteString);
            return this;
        }

        public Builder addRequested(String str) {
            copyOnWrite();
            ((ContextRule) this.instance).addRequested(str);
            return this;
        }

        public Builder addRequestedBytes(ByteString byteString) {
            copyOnWrite();
            ((ContextRule) this.instance).addRequestedBytes(byteString);
            return this;
        }

        public Builder clearAllowedRequestExtensions() {
            copyOnWrite();
            ((ContextRule) this.instance).clearAllowedRequestExtensions();
            return this;
        }

        public Builder clearAllowedResponseExtensions() {
            copyOnWrite();
            ((ContextRule) this.instance).clearAllowedResponseExtensions();
            return this;
        }

        public Builder clearProvided() {
            copyOnWrite();
            ((ContextRule) this.instance).clearProvided();
            return this;
        }

        public Builder clearRequested() {
            copyOnWrite();
            ((ContextRule) this.instance).clearRequested();
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((ContextRule) this.instance).clearSelector();
            return this;
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public String getAllowedRequestExtensions(int i) {
            return ((ContextRule) this.instance).getAllowedRequestExtensions(i);
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public ByteString getAllowedRequestExtensionsBytes(int i) {
            return ((ContextRule) this.instance).getAllowedRequestExtensionsBytes(i);
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public int getAllowedRequestExtensionsCount() {
            return ((ContextRule) this.instance).getAllowedRequestExtensionsCount();
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public List<String> getAllowedRequestExtensionsList() {
            return Collections.unmodifiableList(((ContextRule) this.instance).getAllowedRequestExtensionsList());
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public String getAllowedResponseExtensions(int i) {
            return ((ContextRule) this.instance).getAllowedResponseExtensions(i);
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public ByteString getAllowedResponseExtensionsBytes(int i) {
            return ((ContextRule) this.instance).getAllowedResponseExtensionsBytes(i);
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public int getAllowedResponseExtensionsCount() {
            return ((ContextRule) this.instance).getAllowedResponseExtensionsCount();
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public List<String> getAllowedResponseExtensionsList() {
            return Collections.unmodifiableList(((ContextRule) this.instance).getAllowedResponseExtensionsList());
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public String getProvided(int i) {
            return ((ContextRule) this.instance).getProvided(i);
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public ByteString getProvidedBytes(int i) {
            return ((ContextRule) this.instance).getProvidedBytes(i);
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public int getProvidedCount() {
            return ((ContextRule) this.instance).getProvidedCount();
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public List<String> getProvidedList() {
            return Collections.unmodifiableList(((ContextRule) this.instance).getProvidedList());
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public String getRequested(int i) {
            return ((ContextRule) this.instance).getRequested(i);
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public ByteString getRequestedBytes(int i) {
            return ((ContextRule) this.instance).getRequestedBytes(i);
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public int getRequestedCount() {
            return ((ContextRule) this.instance).getRequestedCount();
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public List<String> getRequestedList() {
            return Collections.unmodifiableList(((ContextRule) this.instance).getRequestedList());
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public String getSelector() {
            return ((ContextRule) this.instance).getSelector();
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public ByteString getSelectorBytes() {
            return ((ContextRule) this.instance).getSelectorBytes();
        }

        public Builder setAllowedRequestExtensions(int i, String str) {
            copyOnWrite();
            ((ContextRule) this.instance).setAllowedRequestExtensions(i, str);
            return this;
        }

        public Builder setAllowedResponseExtensions(int i, String str) {
            copyOnWrite();
            ((ContextRule) this.instance).setAllowedResponseExtensions(i, str);
            return this;
        }

        public Builder setProvided(int i, String str) {
            copyOnWrite();
            ((ContextRule) this.instance).setProvided(i, str);
            return this;
        }

        public Builder setRequested(int i, String str) {
            copyOnWrite();
            ((ContextRule) this.instance).setRequested(i, str);
            return this;
        }

        public Builder setSelector(String str) {
            copyOnWrite();
            ((ContextRule) this.instance).setSelector(str);
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            copyOnWrite();
            ((ContextRule) this.instance).setSelectorBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(ContextRule.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(ContextRule contextRule) {
        return DEFAULT_INSTANCE.createBuilder(contextRule);
    }

    public static ContextRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ContextRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ContextRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ContextRule parseFrom(ByteString byteString) {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ContextRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ContextRule parseFrom(byte[] bArr) {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ContextRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ContextRule parseFrom(InputStream inputStream) {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ContextRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ContextRule parseFrom(CodedInputStream codedInputStream) {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ContextRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
