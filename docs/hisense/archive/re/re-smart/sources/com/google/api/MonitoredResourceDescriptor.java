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
public final class MonitoredResourceDescriptor extends GeneratedMessageLite<MonitoredResourceDescriptor, Builder> implements MonitoredResourceDescriptorOrBuilder {
    public static final MonitoredResourceDescriptor DEFAULT_INSTANCE;
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 2;
    public static final int LABELS_FIELD_NUMBER = 4;
    public static final int LAUNCH_STAGE_FIELD_NUMBER = 7;
    public static final int NAME_FIELD_NUMBER = 5;
    public static volatile Parser<MonitoredResourceDescriptor> PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 1;
    public int launchStage_;
    public String name_ = "";
    public String type_ = "";
    public String displayName_ = "";
    public String description_ = "";
    public Internal.ProtobufList<LabelDescriptor> labels_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.MonitoredResourceDescriptor$1, reason: invalid class name */
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
        MonitoredResourceDescriptor monitoredResourceDescriptor = new MonitoredResourceDescriptor();
        DEFAULT_INSTANCE = monitoredResourceDescriptor;
        GeneratedMessageLite.defaultInstanceMap.put(MonitoredResourceDescriptor.class, monitoredResourceDescriptor);
    }

    public static /* synthetic */ void access$1600(MonitoredResourceDescriptor monitoredResourceDescriptor, Iterable iterable) {
        monitoredResourceDescriptor.ensureLabelsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) monitoredResourceDescriptor.labels_);
    }

    public static /* synthetic */ void access$1800(MonitoredResourceDescriptor monitoredResourceDescriptor, int i) {
        monitoredResourceDescriptor.ensureLabelsIsMutable();
        monitoredResourceDescriptor.labels_.remove(i);
    }

    private void addAllLabels(Iterable<? extends LabelDescriptor> iterable) {
        ensureLabelsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.labels_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLabels(LabelDescriptor labelDescriptor) {
        labelDescriptor.getClass();
        ensureLabelsIsMutable();
        this.labels_.add(labelDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDescription() {
        this.description_ = DEFAULT_INSTANCE.getDescription();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDisplayName() {
        this.displayName_ = DEFAULT_INSTANCE.getDisplayName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLabels() {
        this.labels_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearLaunchStage() {
        this.launchStage_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearType() {
        this.type_ = DEFAULT_INSTANCE.getType();
    }

    private void ensureLabelsIsMutable() {
        Internal.ProtobufList<LabelDescriptor> protobufList = this.labels_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.labels_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static MonitoredResourceDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static MonitoredResourceDescriptor parseDelimitedFrom(InputStream inputStream) {
        return (MonitoredResourceDescriptor) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MonitoredResourceDescriptor parseFrom(ByteBuffer byteBuffer) {
        return (MonitoredResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<MonitoredResourceDescriptor> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeLabels(int i) {
        ensureLabelsIsMutable();
        this.labels_.remove(i);
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
    public void setDisplayName(String str) {
        str.getClass();
        this.displayName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDisplayNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.displayName_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLabels(int i, LabelDescriptor labelDescriptor) {
        labelDescriptor.getClass();
        ensureLabelsIsMutable();
        this.labels_.set(i, labelDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLaunchStage(LaunchStage launchStage) {
        this.launchStage_ = launchStage.getNumber();
    }

    private void setLaunchStageValue(int i) {
        this.launchStage_ = i;
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
    public void setType(String str) {
        str.getClass();
        this.type_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTypeBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.type_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0000\u0001\u0007\u0006\u0000\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004\u001b\u0005Ȉ\u0007\f", new Object[]{"type_", "displayName_", "description_", "labels_", LabelDescriptor.class, "name_", "launchStage_"});
            case NEW_MUTABLE_INSTANCE:
                return new MonitoredResourceDescriptor();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<MonitoredResourceDescriptor> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (MonitoredResourceDescriptor.class) {
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

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public String getDescription() {
        return this.description_;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public ByteString getDescriptionBytes() {
        return ByteString.copyFromUtf8(this.description_);
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public String getDisplayName() {
        return this.displayName_;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public ByteString getDisplayNameBytes() {
        return ByteString.copyFromUtf8(this.displayName_);
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public LabelDescriptor getLabels(int i) {
        return this.labels_.get(i);
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public int getLabelsCount() {
        return this.labels_.size();
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public List<LabelDescriptor> getLabelsList() {
        return this.labels_;
    }

    public LabelDescriptorOrBuilder getLabelsOrBuilder(int i) {
        return this.labels_.get(i);
    }

    public List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList() {
        return this.labels_;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public LaunchStage getLaunchStage() {
        LaunchStage launchStageForNumber = LaunchStage.forNumber(this.launchStage_);
        return launchStageForNumber == null ? LaunchStage.UNRECOGNIZED : launchStageForNumber;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public int getLaunchStageValue() {
        return this.launchStage_;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public String getType() {
        return this.type_;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public ByteString getTypeBytes() {
        return ByteString.copyFromUtf8(this.type_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MonitoredResourceDescriptor, Builder> implements MonitoredResourceDescriptorOrBuilder {
        public Builder() {
            super(MonitoredResourceDescriptor.DEFAULT_INSTANCE);
        }

        public Builder addAllLabels(Iterable<? extends LabelDescriptor> iterable) {
            copyOnWrite();
            MonitoredResourceDescriptor.access$1600((MonitoredResourceDescriptor) this.instance, iterable);
            return this;
        }

        public Builder addLabels(LabelDescriptor labelDescriptor) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).addLabels(labelDescriptor);
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).clearDescription();
            return this;
        }

        public Builder clearDisplayName() {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).clearDisplayName();
            return this;
        }

        public Builder clearLabels() {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).clearLabels();
            return this;
        }

        public Builder clearLaunchStage() {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).launchStage_ = 0;
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).clearName();
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).clearType();
            return this;
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public String getDescription() {
            return ((MonitoredResourceDescriptor) this.instance).getDescription();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public ByteString getDescriptionBytes() {
            return ((MonitoredResourceDescriptor) this.instance).getDescriptionBytes();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public String getDisplayName() {
            return ((MonitoredResourceDescriptor) this.instance).getDisplayName();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public ByteString getDisplayNameBytes() {
            return ((MonitoredResourceDescriptor) this.instance).getDisplayNameBytes();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public LabelDescriptor getLabels(int i) {
            return ((MonitoredResourceDescriptor) this.instance).getLabels(i);
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public int getLabelsCount() {
            return ((MonitoredResourceDescriptor) this.instance).getLabelsCount();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public List<LabelDescriptor> getLabelsList() {
            return Collections.unmodifiableList(((MonitoredResourceDescriptor) this.instance).getLabelsList());
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public LaunchStage getLaunchStage() {
            return ((MonitoredResourceDescriptor) this.instance).getLaunchStage();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public int getLaunchStageValue() {
            return ((MonitoredResourceDescriptor) this.instance).getLaunchStageValue();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public String getName() {
            return ((MonitoredResourceDescriptor) this.instance).getName();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public ByteString getNameBytes() {
            return ((MonitoredResourceDescriptor) this.instance).getNameBytes();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public String getType() {
            return ((MonitoredResourceDescriptor) this.instance).getType();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public ByteString getTypeBytes() {
            return ((MonitoredResourceDescriptor) this.instance).getTypeBytes();
        }

        public Builder removeLabels(int i) {
            copyOnWrite();
            MonitoredResourceDescriptor.access$1800((MonitoredResourceDescriptor) this.instance, i);
            return this;
        }

        public Builder setDescription(String str) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).setDescription(str);
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).setDescriptionBytes(byteString);
            return this;
        }

        public Builder setDisplayName(String str) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).setDisplayName(str);
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).setDisplayNameBytes(byteString);
            return this;
        }

        public Builder setLabels(int i, LabelDescriptor labelDescriptor) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).setLabels(i, labelDescriptor);
            return this;
        }

        public Builder setLaunchStage(LaunchStage launchStage) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).setLaunchStage(launchStage);
            return this;
        }

        public Builder setLaunchStageValue(int i) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).launchStage_ = i;
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setType(String str) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).setType(str);
            return this;
        }

        public Builder setTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).setTypeBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(MonitoredResourceDescriptor.DEFAULT_INSTANCE);
        }

        public Builder addLabels(int i, LabelDescriptor labelDescriptor) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).addLabels(i, labelDescriptor);
            return this;
        }

        public Builder setLabels(int i, LabelDescriptor.Builder builder) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).setLabels(i, builder.build());
            return this;
        }

        public Builder addLabels(LabelDescriptor.Builder builder) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).addLabels(builder.build());
            return this;
        }

        public Builder addLabels(int i, LabelDescriptor.Builder builder) {
            copyOnWrite();
            ((MonitoredResourceDescriptor) this.instance).addLabels(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(MonitoredResourceDescriptor monitoredResourceDescriptor) {
        return DEFAULT_INSTANCE.createBuilder(monitoredResourceDescriptor);
    }

    public static MonitoredResourceDescriptor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResourceDescriptor) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseFrom(ByteString byteString) {
        return (MonitoredResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLabels(int i, LabelDescriptor labelDescriptor) {
        labelDescriptor.getClass();
        ensureLabelsIsMutable();
        this.labels_.add(i, labelDescriptor);
    }

    public static MonitoredResourceDescriptor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseFrom(byte[] bArr) {
        return (MonitoredResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MonitoredResourceDescriptor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseFrom(InputStream inputStream) {
        return (MonitoredResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MonitoredResourceDescriptor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseFrom(CodedInputStream codedInputStream) {
        return (MonitoredResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MonitoredResourceDescriptor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MonitoredResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
