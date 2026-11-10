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
public final class LogDescriptor extends GeneratedMessageLite<LogDescriptor, Builder> implements LogDescriptorOrBuilder {
    public static final LogDescriptor DEFAULT_INSTANCE;
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 4;
    public static final int LABELS_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<LogDescriptor> PARSER;
    public String name_ = "";
    public Internal.ProtobufList<LabelDescriptor> labels_ = GeneratedMessageLite.emptyProtobufList();
    public String description_ = "";
    public String displayName_ = "";

    /* JADX INFO: renamed from: com.google.api.LogDescriptor$1, reason: invalid class name */
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
        LogDescriptor logDescriptor = new LogDescriptor();
        DEFAULT_INSTANCE = logDescriptor;
        GeneratedMessageLite.defaultInstanceMap.put(LogDescriptor.class, logDescriptor);
    }

    public static /* synthetic */ void access$700(LogDescriptor logDescriptor, Iterable iterable) {
        logDescriptor.ensureLabelsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) logDescriptor.labels_);
    }

    public static /* synthetic */ void access$900(LogDescriptor logDescriptor, int i) {
        logDescriptor.ensureLabelsIsMutable();
        logDescriptor.labels_.remove(i);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    private void ensureLabelsIsMutable() {
        Internal.ProtobufList<LabelDescriptor> protobufList = this.labels_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.labels_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static LogDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static LogDescriptor parseDelimitedFrom(InputStream inputStream) {
        return (LogDescriptor) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static LogDescriptor parseFrom(ByteBuffer byteBuffer) {
        return (LogDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<LogDescriptor> parser() {
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
    public void setName(String str) {
        str.getClass();
        this.name_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.name_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001Ȉ\u0002\u001b\u0003Ȉ\u0004Ȉ", new Object[]{"name_", "labels_", LabelDescriptor.class, "description_", "displayName_"});
            case NEW_MUTABLE_INSTANCE:
                return new LogDescriptor();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<LogDescriptor> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (LogDescriptor.class) {
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

    @Override // com.google.api.LogDescriptorOrBuilder
    public String getDescription() {
        return this.description_;
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public ByteString getDescriptionBytes() {
        return ByteString.copyFromUtf8(this.description_);
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public String getDisplayName() {
        return this.displayName_;
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public ByteString getDisplayNameBytes() {
        return ByteString.copyFromUtf8(this.displayName_);
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public LabelDescriptor getLabels(int i) {
        return this.labels_.get(i);
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public int getLabelsCount() {
        return this.labels_.size();
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public List<LabelDescriptor> getLabelsList() {
        return this.labels_;
    }

    public LabelDescriptorOrBuilder getLabelsOrBuilder(int i) {
        return this.labels_.get(i);
    }

    public List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList() {
        return this.labels_;
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<LogDescriptor, Builder> implements LogDescriptorOrBuilder {
        public Builder() {
            super(LogDescriptor.DEFAULT_INSTANCE);
        }

        public Builder addAllLabels(Iterable<? extends LabelDescriptor> iterable) {
            copyOnWrite();
            LogDescriptor.access$700((LogDescriptor) this.instance, iterable);
            return this;
        }

        public Builder addLabels(LabelDescriptor labelDescriptor) {
            copyOnWrite();
            ((LogDescriptor) this.instance).addLabels(labelDescriptor);
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((LogDescriptor) this.instance).clearDescription();
            return this;
        }

        public Builder clearDisplayName() {
            copyOnWrite();
            ((LogDescriptor) this.instance).clearDisplayName();
            return this;
        }

        public Builder clearLabels() {
            copyOnWrite();
            ((LogDescriptor) this.instance).clearLabels();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((LogDescriptor) this.instance).clearName();
            return this;
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public String getDescription() {
            return ((LogDescriptor) this.instance).getDescription();
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public ByteString getDescriptionBytes() {
            return ((LogDescriptor) this.instance).getDescriptionBytes();
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public String getDisplayName() {
            return ((LogDescriptor) this.instance).getDisplayName();
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public ByteString getDisplayNameBytes() {
            return ((LogDescriptor) this.instance).getDisplayNameBytes();
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public LabelDescriptor getLabels(int i) {
            return ((LogDescriptor) this.instance).getLabels(i);
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public int getLabelsCount() {
            return ((LogDescriptor) this.instance).getLabelsCount();
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public List<LabelDescriptor> getLabelsList() {
            return Collections.unmodifiableList(((LogDescriptor) this.instance).getLabelsList());
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public String getName() {
            return ((LogDescriptor) this.instance).getName();
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public ByteString getNameBytes() {
            return ((LogDescriptor) this.instance).getNameBytes();
        }

        public Builder removeLabels(int i) {
            copyOnWrite();
            LogDescriptor.access$900((LogDescriptor) this.instance, i);
            return this;
        }

        public Builder setDescription(String str) {
            copyOnWrite();
            ((LogDescriptor) this.instance).setDescription(str);
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            copyOnWrite();
            ((LogDescriptor) this.instance).setDescriptionBytes(byteString);
            return this;
        }

        public Builder setDisplayName(String str) {
            copyOnWrite();
            ((LogDescriptor) this.instance).setDisplayName(str);
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            copyOnWrite();
            ((LogDescriptor) this.instance).setDisplayNameBytes(byteString);
            return this;
        }

        public Builder setLabels(int i, LabelDescriptor labelDescriptor) {
            copyOnWrite();
            ((LogDescriptor) this.instance).setLabels(i, labelDescriptor);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((LogDescriptor) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((LogDescriptor) this.instance).setNameBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(LogDescriptor.DEFAULT_INSTANCE);
        }

        public Builder addLabels(int i, LabelDescriptor labelDescriptor) {
            copyOnWrite();
            ((LogDescriptor) this.instance).addLabels(i, labelDescriptor);
            return this;
        }

        public Builder setLabels(int i, LabelDescriptor.Builder builder) {
            copyOnWrite();
            ((LogDescriptor) this.instance).setLabels(i, builder.build());
            return this;
        }

        public Builder addLabels(LabelDescriptor.Builder builder) {
            copyOnWrite();
            ((LogDescriptor) this.instance).addLabels(builder.build());
            return this;
        }

        public Builder addLabels(int i, LabelDescriptor.Builder builder) {
            copyOnWrite();
            ((LogDescriptor) this.instance).addLabels(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(LogDescriptor logDescriptor) {
        return DEFAULT_INSTANCE.createBuilder(logDescriptor);
    }

    public static LogDescriptor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (LogDescriptor) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static LogDescriptor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (LogDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static LogDescriptor parseFrom(ByteString byteString) {
        return (LogDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLabels(int i, LabelDescriptor labelDescriptor) {
        labelDescriptor.getClass();
        ensureLabelsIsMutable();
        this.labels_.add(i, labelDescriptor);
    }

    public static LogDescriptor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (LogDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static LogDescriptor parseFrom(byte[] bArr) {
        return (LogDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static LogDescriptor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (LogDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static LogDescriptor parseFrom(InputStream inputStream) {
        return (LogDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static LogDescriptor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (LogDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static LogDescriptor parseFrom(CodedInputStream codedInputStream) {
        return (LogDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static LogDescriptor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (LogDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
