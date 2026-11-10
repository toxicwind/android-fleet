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
public final class ConfigChange extends GeneratedMessageLite<ConfigChange, Builder> implements ConfigChangeOrBuilder {
    public static final int ADVICES_FIELD_NUMBER = 5;
    public static final int CHANGE_TYPE_FIELD_NUMBER = 4;
    public static final ConfigChange DEFAULT_INSTANCE;
    public static final int ELEMENT_FIELD_NUMBER = 1;
    public static final int NEW_VALUE_FIELD_NUMBER = 3;
    public static final int OLD_VALUE_FIELD_NUMBER = 2;
    public static volatile Parser<ConfigChange> PARSER;
    public int changeType_;
    public String element_ = "";
    public String oldValue_ = "";
    public String newValue_ = "";
    public Internal.ProtobufList<Advice> advices_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.ConfigChange$1, reason: invalid class name */
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
        ConfigChange configChange = new ConfigChange();
        DEFAULT_INSTANCE = configChange;
        GeneratedMessageLite.defaultInstanceMap.put(ConfigChange.class, configChange);
    }

    public static /* synthetic */ void access$1600(ConfigChange configChange, Iterable iterable) {
        configChange.ensureAdvicesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) configChange.advices_);
    }

    public static /* synthetic */ void access$1800(ConfigChange configChange, int i) {
        configChange.ensureAdvicesIsMutable();
        configChange.advices_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAdvices(Advice advice) {
        advice.getClass();
        ensureAdvicesIsMutable();
        this.advices_.add(advice);
    }

    private void addAllAdvices(Iterable<? extends Advice> iterable) {
        ensureAdvicesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.advices_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAdvices() {
        this.advices_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearChangeType() {
        this.changeType_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearElement() {
        this.element_ = DEFAULT_INSTANCE.getElement();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNewValue() {
        this.newValue_ = DEFAULT_INSTANCE.getNewValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOldValue() {
        this.oldValue_ = DEFAULT_INSTANCE.getOldValue();
    }

    private void ensureAdvicesIsMutable() {
        Internal.ProtobufList<Advice> protobufList = this.advices_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.advices_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static ConfigChange getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static ConfigChange parseDelimitedFrom(InputStream inputStream) {
        return (ConfigChange) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ConfigChange parseFrom(ByteBuffer byteBuffer) {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<ConfigChange> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeAdvices(int i) {
        ensureAdvicesIsMutable();
        this.advices_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdvices(int i, Advice advice) {
        advice.getClass();
        ensureAdvicesIsMutable();
        this.advices_.set(i, advice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChangeType(ChangeType changeType) {
        this.changeType_ = changeType.getNumber();
    }

    private void setChangeTypeValue(int i) {
        this.changeType_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setElement(String str) {
        str.getClass();
        this.element_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setElementBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.element_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNewValue(String str) {
        str.getClass();
        this.newValue_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNewValueBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.newValue_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOldValue(String str) {
        str.getClass();
        this.oldValue_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOldValueBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.oldValue_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004\f\u0005\u001b", new Object[]{"element_", "oldValue_", "newValue_", "changeType_", "advices_", Advice.class});
            case NEW_MUTABLE_INSTANCE:
                return new ConfigChange();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ConfigChange> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ConfigChange.class) {
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

    @Override // com.google.api.ConfigChangeOrBuilder
    public Advice getAdvices(int i) {
        return this.advices_.get(i);
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public int getAdvicesCount() {
        return this.advices_.size();
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public List<Advice> getAdvicesList() {
        return this.advices_;
    }

    public AdviceOrBuilder getAdvicesOrBuilder(int i) {
        return this.advices_.get(i);
    }

    public List<? extends AdviceOrBuilder> getAdvicesOrBuilderList() {
        return this.advices_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ChangeType getChangeType() {
        ChangeType changeTypeForNumber = ChangeType.forNumber(this.changeType_);
        return changeTypeForNumber == null ? ChangeType.UNRECOGNIZED : changeTypeForNumber;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public int getChangeTypeValue() {
        return this.changeType_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public String getElement() {
        return this.element_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ByteString getElementBytes() {
        return ByteString.copyFromUtf8(this.element_);
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public String getNewValue() {
        return this.newValue_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ByteString getNewValueBytes() {
        return ByteString.copyFromUtf8(this.newValue_);
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public String getOldValue() {
        return this.oldValue_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ByteString getOldValueBytes() {
        return ByteString.copyFromUtf8(this.oldValue_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ConfigChange, Builder> implements ConfigChangeOrBuilder {
        public Builder() {
            super(ConfigChange.DEFAULT_INSTANCE);
        }

        public Builder addAdvices(Advice advice) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(advice);
            return this;
        }

        public Builder addAllAdvices(Iterable<? extends Advice> iterable) {
            copyOnWrite();
            ConfigChange.access$1600((ConfigChange) this.instance, iterable);
            return this;
        }

        public Builder clearAdvices() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearAdvices();
            return this;
        }

        public Builder clearChangeType() {
            copyOnWrite();
            ((ConfigChange) this.instance).changeType_ = 0;
            return this;
        }

        public Builder clearElement() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearElement();
            return this;
        }

        public Builder clearNewValue() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearNewValue();
            return this;
        }

        public Builder clearOldValue() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearOldValue();
            return this;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public Advice getAdvices(int i) {
            return ((ConfigChange) this.instance).getAdvices(i);
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public int getAdvicesCount() {
            return ((ConfigChange) this.instance).getAdvicesCount();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public List<Advice> getAdvicesList() {
            return Collections.unmodifiableList(((ConfigChange) this.instance).getAdvicesList());
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ChangeType getChangeType() {
            return ((ConfigChange) this.instance).getChangeType();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public int getChangeTypeValue() {
            return ((ConfigChange) this.instance).getChangeTypeValue();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public String getElement() {
            return ((ConfigChange) this.instance).getElement();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ByteString getElementBytes() {
            return ((ConfigChange) this.instance).getElementBytes();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public String getNewValue() {
            return ((ConfigChange) this.instance).getNewValue();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ByteString getNewValueBytes() {
            return ((ConfigChange) this.instance).getNewValueBytes();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public String getOldValue() {
            return ((ConfigChange) this.instance).getOldValue();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ByteString getOldValueBytes() {
            return ((ConfigChange) this.instance).getOldValueBytes();
        }

        public Builder removeAdvices(int i) {
            copyOnWrite();
            ConfigChange.access$1800((ConfigChange) this.instance, i);
            return this;
        }

        public Builder setAdvices(int i, Advice advice) {
            copyOnWrite();
            ((ConfigChange) this.instance).setAdvices(i, advice);
            return this;
        }

        public Builder setChangeType(ChangeType changeType) {
            copyOnWrite();
            ((ConfigChange) this.instance).setChangeType(changeType);
            return this;
        }

        public Builder setChangeTypeValue(int i) {
            copyOnWrite();
            ((ConfigChange) this.instance).changeType_ = i;
            return this;
        }

        public Builder setElement(String str) {
            copyOnWrite();
            ((ConfigChange) this.instance).setElement(str);
            return this;
        }

        public Builder setElementBytes(ByteString byteString) {
            copyOnWrite();
            ((ConfigChange) this.instance).setElementBytes(byteString);
            return this;
        }

        public Builder setNewValue(String str) {
            copyOnWrite();
            ((ConfigChange) this.instance).setNewValue(str);
            return this;
        }

        public Builder setNewValueBytes(ByteString byteString) {
            copyOnWrite();
            ((ConfigChange) this.instance).setNewValueBytes(byteString);
            return this;
        }

        public Builder setOldValue(String str) {
            copyOnWrite();
            ((ConfigChange) this.instance).setOldValue(str);
            return this;
        }

        public Builder setOldValueBytes(ByteString byteString) {
            copyOnWrite();
            ((ConfigChange) this.instance).setOldValueBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(ConfigChange.DEFAULT_INSTANCE);
        }

        public Builder addAdvices(int i, Advice advice) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(i, advice);
            return this;
        }

        public Builder setAdvices(int i, Advice.Builder builder) {
            copyOnWrite();
            ((ConfigChange) this.instance).setAdvices(i, builder.build());
            return this;
        }

        public Builder addAdvices(Advice.Builder builder) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(builder.build());
            return this;
        }

        public Builder addAdvices(int i, Advice.Builder builder) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(ConfigChange configChange) {
        return DEFAULT_INSTANCE.createBuilder(configChange);
    }

    public static ConfigChange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ConfigChange) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(ByteString byteString) {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAdvices(int i, Advice advice) {
        advice.getClass();
        ensureAdvicesIsMutable();
        this.advices_.add(i, advice);
    }

    public static ConfigChange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(byte[] bArr) {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ConfigChange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(InputStream inputStream) {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ConfigChange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(CodedInputStream codedInputStream) {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ConfigChange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
