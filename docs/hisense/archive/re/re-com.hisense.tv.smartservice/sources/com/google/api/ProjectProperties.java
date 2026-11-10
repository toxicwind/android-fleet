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
public final class ProjectProperties extends GeneratedMessageLite<ProjectProperties, Builder> implements ProjectPropertiesOrBuilder {
    public static final ProjectProperties DEFAULT_INSTANCE;
    public static volatile Parser<ProjectProperties> PARSER = null;
    public static final int PROPERTIES_FIELD_NUMBER = 1;
    public Internal.ProtobufList<Property> properties_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.ProjectProperties$1, reason: invalid class name */
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
        ProjectProperties projectProperties = new ProjectProperties();
        DEFAULT_INSTANCE = projectProperties;
        GeneratedMessageLite.defaultInstanceMap.put(ProjectProperties.class, projectProperties);
    }

    public static /* synthetic */ void access$400(ProjectProperties projectProperties, Iterable iterable) {
        projectProperties.ensurePropertiesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) projectProperties.properties_);
    }

    public static /* synthetic */ void access$600(ProjectProperties projectProperties, int i) {
        projectProperties.ensurePropertiesIsMutable();
        projectProperties.properties_.remove(i);
    }

    private void addAllProperties(Iterable<? extends Property> iterable) {
        ensurePropertiesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.properties_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProperties(Property property) {
        property.getClass();
        ensurePropertiesIsMutable();
        this.properties_.add(property);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearProperties() {
        this.properties_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensurePropertiesIsMutable() {
        Internal.ProtobufList<Property> protobufList = this.properties_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.properties_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static ProjectProperties getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static ProjectProperties parseDelimitedFrom(InputStream inputStream) {
        return (ProjectProperties) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ProjectProperties parseFrom(ByteBuffer byteBuffer) {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<ProjectProperties> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeProperties(int i) {
        ensurePropertiesIsMutable();
        this.properties_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProperties(int i, Property property) {
        property.getClass();
        ensurePropertiesIsMutable();
        this.properties_.set(i, property);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"properties_", Property.class});
            case NEW_MUTABLE_INSTANCE:
                return new ProjectProperties();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ProjectProperties> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ProjectProperties.class) {
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

    @Override // com.google.api.ProjectPropertiesOrBuilder
    public Property getProperties(int i) {
        return this.properties_.get(i);
    }

    @Override // com.google.api.ProjectPropertiesOrBuilder
    public int getPropertiesCount() {
        return this.properties_.size();
    }

    @Override // com.google.api.ProjectPropertiesOrBuilder
    public List<Property> getPropertiesList() {
        return this.properties_;
    }

    public PropertyOrBuilder getPropertiesOrBuilder(int i) {
        return this.properties_.get(i);
    }

    public List<? extends PropertyOrBuilder> getPropertiesOrBuilderList() {
        return this.properties_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProjectProperties, Builder> implements ProjectPropertiesOrBuilder {
        public Builder() {
            super(ProjectProperties.DEFAULT_INSTANCE);
        }

        public Builder addAllProperties(Iterable<? extends Property> iterable) {
            copyOnWrite();
            ProjectProperties.access$400((ProjectProperties) this.instance, iterable);
            return this;
        }

        public Builder addProperties(Property property) {
            copyOnWrite();
            ((ProjectProperties) this.instance).addProperties(property);
            return this;
        }

        public Builder clearProperties() {
            copyOnWrite();
            ((ProjectProperties) this.instance).clearProperties();
            return this;
        }

        @Override // com.google.api.ProjectPropertiesOrBuilder
        public Property getProperties(int i) {
            return ((ProjectProperties) this.instance).getProperties(i);
        }

        @Override // com.google.api.ProjectPropertiesOrBuilder
        public int getPropertiesCount() {
            return ((ProjectProperties) this.instance).getPropertiesCount();
        }

        @Override // com.google.api.ProjectPropertiesOrBuilder
        public List<Property> getPropertiesList() {
            return Collections.unmodifiableList(((ProjectProperties) this.instance).getPropertiesList());
        }

        public Builder removeProperties(int i) {
            copyOnWrite();
            ProjectProperties.access$600((ProjectProperties) this.instance, i);
            return this;
        }

        public Builder setProperties(int i, Property property) {
            copyOnWrite();
            ((ProjectProperties) this.instance).setProperties(i, property);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(ProjectProperties.DEFAULT_INSTANCE);
        }

        public Builder addProperties(int i, Property property) {
            copyOnWrite();
            ((ProjectProperties) this.instance).addProperties(i, property);
            return this;
        }

        public Builder setProperties(int i, Property.Builder builder) {
            copyOnWrite();
            ((ProjectProperties) this.instance).setProperties(i, builder.build());
            return this;
        }

        public Builder addProperties(Property.Builder builder) {
            copyOnWrite();
            ((ProjectProperties) this.instance).addProperties(builder.build());
            return this;
        }

        public Builder addProperties(int i, Property.Builder builder) {
            copyOnWrite();
            ((ProjectProperties) this.instance).addProperties(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(ProjectProperties projectProperties) {
        return DEFAULT_INSTANCE.createBuilder(projectProperties);
    }

    public static ProjectProperties parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ProjectProperties) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ProjectProperties parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ProjectProperties parseFrom(ByteString byteString) {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProperties(int i, Property property) {
        property.getClass();
        ensurePropertiesIsMutable();
        this.properties_.add(i, property);
    }

    public static ProjectProperties parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ProjectProperties parseFrom(byte[] bArr) {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ProjectProperties parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ProjectProperties parseFrom(InputStream inputStream) {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ProjectProperties parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ProjectProperties parseFrom(CodedInputStream codedInputStream) {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ProjectProperties parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ProjectProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
