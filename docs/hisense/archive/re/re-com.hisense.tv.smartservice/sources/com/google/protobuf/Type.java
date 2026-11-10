package com.google.protobuf;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class Type extends GeneratedMessageLite<Type, Builder> implements TypeOrBuilder {
    public static final Type DEFAULT_INSTANCE;
    public static final int FIELDS_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int ONEOFS_FIELD_NUMBER = 3;
    public static final int OPTIONS_FIELD_NUMBER = 4;
    public static volatile Parser<Type> PARSER = null;
    public static final int SOURCE_CONTEXT_FIELD_NUMBER = 5;
    public static final int SYNTAX_FIELD_NUMBER = 6;
    public SourceContext sourceContext_;
    public int syntax_;
    public String name_ = "";
    public Internal.ProtobufList<Field> fields_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<String> oneofs_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<Option> options_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.protobuf.Type$1, reason: invalid class name */
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
        Type type = new Type();
        DEFAULT_INSTANCE = type;
        GeneratedMessageLite.defaultInstanceMap.put(Type.class, type);
    }

    public static /* synthetic */ void access$1200(Type type, Iterable iterable) {
        type.ensureOneofsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) type.oneofs_);
    }

    public static /* synthetic */ void access$1800(Type type, Iterable iterable) {
        type.ensureOptionsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) type.options_);
    }

    public static /* synthetic */ void access$2000(Type type, int i) {
        type.ensureOptionsIsMutable();
        type.options_.remove(i);
    }

    public static /* synthetic */ void access$700(Type type, Iterable iterable) {
        type.ensureFieldsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) type.fields_);
    }

    public static /* synthetic */ void access$900(Type type, int i) {
        type.ensureFieldsIsMutable();
        type.fields_.remove(i);
    }

    private void addAllFields(Iterable<? extends Field> iterable) {
        ensureFieldsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.fields_);
    }

    private void addAllOneofs(Iterable<String> iterable) {
        ensureOneofsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.oneofs_);
    }

    private void addAllOptions(Iterable<? extends Option> iterable) {
        ensureOptionsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.options_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFields(Field field) {
        field.getClass();
        ensureFieldsIsMutable();
        this.fields_.add(field);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addOneofs(String str) {
        str.getClass();
        ensureOneofsIsMutable();
        this.oneofs_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addOneofsBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        ensureOneofsIsMutable();
        this.oneofs_.add(byteString.toStringUtf8());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addOptions(Option option) {
        option.getClass();
        ensureOptionsIsMutable();
        this.options_.add(option);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFields() {
        this.fields_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOneofs() {
        this.oneofs_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOptions() {
        this.options_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearSourceContext() {
        this.sourceContext_ = null;
    }

    private void clearSyntax() {
        this.syntax_ = 0;
    }

    private void ensureFieldsIsMutable() {
        Internal.ProtobufList<Field> protobufList = this.fields_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.fields_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureOneofsIsMutable() {
        Internal.ProtobufList<String> protobufList = this.oneofs_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.oneofs_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureOptionsIsMutable() {
        Internal.ProtobufList<Option> protobufList = this.options_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.options_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static Type getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeSourceContext(SourceContext sourceContext) {
        sourceContext.getClass();
        SourceContext sourceContext2 = this.sourceContext_;
        if (sourceContext2 == null || sourceContext2 == SourceContext.DEFAULT_INSTANCE) {
            this.sourceContext_ = sourceContext;
        } else {
            this.sourceContext_ = SourceContext.newBuilder(sourceContext2).mergeFrom(sourceContext).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Type parseDelimitedFrom(InputStream inputStream) {
        return (Type) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Type parseFrom(ByteBuffer byteBuffer) {
        return (Type) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Type> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeFields(int i) {
        ensureFieldsIsMutable();
        this.fields_.remove(i);
    }

    private void removeOptions(int i) {
        ensureOptionsIsMutable();
        this.options_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFields(int i, Field field) {
        field.getClass();
        ensureFieldsIsMutable();
        this.fields_.set(i, field);
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
    public void setOneofs(int i, String str) {
        str.getClass();
        ensureOneofsIsMutable();
        this.oneofs_.set(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOptions(int i, Option option) {
        option.getClass();
        ensureOptionsIsMutable();
        this.options_.set(i, option);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSourceContext(SourceContext sourceContext) {
        sourceContext.getClass();
        this.sourceContext_ = sourceContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSyntax(Syntax syntax) {
        this.syntax_ = syntax.getNumber();
    }

    private void setSyntaxValue(int i) {
        this.syntax_ = i;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0003\u0000\u0001Ȉ\u0002\u001b\u0003Ț\u0004\u001b\u0005\t\u0006\f", new Object[]{"name_", "fields_", Field.class, "oneofs_", "options_", Option.class, "sourceContext_", "syntax_"});
            case NEW_MUTABLE_INSTANCE:
                return new Type();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Type> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Type.class) {
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

    @Override // com.google.protobuf.TypeOrBuilder
    public Field getFields(int i) {
        return this.fields_.get(i);
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public int getFieldsCount() {
        return this.fields_.size();
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public List<Field> getFieldsList() {
        return this.fields_;
    }

    public FieldOrBuilder getFieldsOrBuilder(int i) {
        return this.fields_.get(i);
    }

    public List<? extends FieldOrBuilder> getFieldsOrBuilderList() {
        return this.fields_;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public String getOneofs(int i) {
        return this.oneofs_.get(i);
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public ByteString getOneofsBytes(int i) {
        return ByteString.copyFromUtf8(this.oneofs_.get(i));
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public int getOneofsCount() {
        return this.oneofs_.size();
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public List<String> getOneofsList() {
        return this.oneofs_;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public Option getOptions(int i) {
        return this.options_.get(i);
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public int getOptionsCount() {
        return this.options_.size();
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public List<Option> getOptionsList() {
        return this.options_;
    }

    public OptionOrBuilder getOptionsOrBuilder(int i) {
        return this.options_.get(i);
    }

    public List<? extends OptionOrBuilder> getOptionsOrBuilderList() {
        return this.options_;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public SourceContext getSourceContext() {
        SourceContext sourceContext = this.sourceContext_;
        return sourceContext == null ? SourceContext.DEFAULT_INSTANCE : sourceContext;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public Syntax getSyntax() {
        Syntax syntaxForNumber = Syntax.forNumber(this.syntax_);
        return syntaxForNumber == null ? Syntax.UNRECOGNIZED : syntaxForNumber;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public int getSyntaxValue() {
        return this.syntax_;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public boolean hasSourceContext() {
        return this.sourceContext_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Type, Builder> implements TypeOrBuilder {
        public Builder() {
            super(Type.DEFAULT_INSTANCE);
        }

        public Builder addAllFields(Iterable<? extends Field> iterable) {
            copyOnWrite();
            Type.access$700((Type) this.instance, iterable);
            return this;
        }

        public Builder addAllOneofs(Iterable<String> iterable) {
            copyOnWrite();
            Type.access$1200((Type) this.instance, iterable);
            return this;
        }

        public Builder addAllOptions(Iterable<? extends Option> iterable) {
            copyOnWrite();
            Type.access$1800((Type) this.instance, iterable);
            return this;
        }

        public Builder addFields(Field field) {
            copyOnWrite();
            ((Type) this.instance).addFields(field);
            return this;
        }

        public Builder addOneofs(String str) {
            copyOnWrite();
            ((Type) this.instance).addOneofs(str);
            return this;
        }

        public Builder addOneofsBytes(ByteString byteString) {
            copyOnWrite();
            ((Type) this.instance).addOneofsBytes(byteString);
            return this;
        }

        public Builder addOptions(Option option) {
            copyOnWrite();
            ((Type) this.instance).addOptions(option);
            return this;
        }

        public Builder clearFields() {
            copyOnWrite();
            ((Type) this.instance).clearFields();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Type) this.instance).clearName();
            return this;
        }

        public Builder clearOneofs() {
            copyOnWrite();
            ((Type) this.instance).clearOneofs();
            return this;
        }

        public Builder clearOptions() {
            copyOnWrite();
            ((Type) this.instance).clearOptions();
            return this;
        }

        public Builder clearSourceContext() {
            copyOnWrite();
            ((Type) this.instance).sourceContext_ = null;
            return this;
        }

        public Builder clearSyntax() {
            copyOnWrite();
            ((Type) this.instance).syntax_ = 0;
            return this;
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public Field getFields(int i) {
            return ((Type) this.instance).getFields(i);
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public int getFieldsCount() {
            return ((Type) this.instance).getFieldsCount();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public List<Field> getFieldsList() {
            return Collections.unmodifiableList(((Type) this.instance).getFieldsList());
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public String getName() {
            return ((Type) this.instance).getName();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public ByteString getNameBytes() {
            return ((Type) this.instance).getNameBytes();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public String getOneofs(int i) {
            return ((Type) this.instance).getOneofs(i);
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public ByteString getOneofsBytes(int i) {
            return ((Type) this.instance).getOneofsBytes(i);
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public int getOneofsCount() {
            return ((Type) this.instance).getOneofsCount();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public List<String> getOneofsList() {
            return Collections.unmodifiableList(((Type) this.instance).getOneofsList());
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public Option getOptions(int i) {
            return ((Type) this.instance).getOptions(i);
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public int getOptionsCount() {
            return ((Type) this.instance).getOptionsCount();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public List<Option> getOptionsList() {
            return Collections.unmodifiableList(((Type) this.instance).getOptionsList());
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public SourceContext getSourceContext() {
            return ((Type) this.instance).getSourceContext();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public Syntax getSyntax() {
            return ((Type) this.instance).getSyntax();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public int getSyntaxValue() {
            return ((Type) this.instance).getSyntaxValue();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public boolean hasSourceContext() {
            return ((Type) this.instance).hasSourceContext();
        }

        public Builder mergeSourceContext(SourceContext sourceContext) {
            copyOnWrite();
            ((Type) this.instance).mergeSourceContext(sourceContext);
            return this;
        }

        public Builder removeFields(int i) {
            copyOnWrite();
            Type.access$900((Type) this.instance, i);
            return this;
        }

        public Builder removeOptions(int i) {
            copyOnWrite();
            Type.access$2000((Type) this.instance, i);
            return this;
        }

        public Builder setFields(int i, Field field) {
            copyOnWrite();
            ((Type) this.instance).setFields(i, field);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((Type) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((Type) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setOneofs(int i, String str) {
            copyOnWrite();
            ((Type) this.instance).setOneofs(i, str);
            return this;
        }

        public Builder setOptions(int i, Option option) {
            copyOnWrite();
            ((Type) this.instance).setOptions(i, option);
            return this;
        }

        public Builder setSourceContext(SourceContext sourceContext) {
            copyOnWrite();
            ((Type) this.instance).setSourceContext(sourceContext);
            return this;
        }

        public Builder setSyntax(Syntax syntax) {
            copyOnWrite();
            ((Type) this.instance).setSyntax(syntax);
            return this;
        }

        public Builder setSyntaxValue(int i) {
            copyOnWrite();
            ((Type) this.instance).syntax_ = i;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Type.DEFAULT_INSTANCE);
        }

        public Builder addFields(int i, Field field) {
            copyOnWrite();
            ((Type) this.instance).addFields(i, field);
            return this;
        }

        public Builder addOptions(int i, Option option) {
            copyOnWrite();
            ((Type) this.instance).addOptions(i, option);
            return this;
        }

        public Builder setFields(int i, Field.Builder builder) {
            copyOnWrite();
            ((Type) this.instance).setFields(i, builder.build());
            return this;
        }

        public Builder setOptions(int i, Option.Builder builder) {
            copyOnWrite();
            ((Type) this.instance).setOptions(i, builder.build());
            return this;
        }

        public Builder setSourceContext(SourceContext.Builder builder) {
            copyOnWrite();
            ((Type) this.instance).setSourceContext(builder.build());
            return this;
        }

        public Builder addFields(Field.Builder builder) {
            copyOnWrite();
            ((Type) this.instance).addFields(builder.build());
            return this;
        }

        public Builder addOptions(Option.Builder builder) {
            copyOnWrite();
            ((Type) this.instance).addOptions(builder.build());
            return this;
        }

        public Builder addFields(int i, Field.Builder builder) {
            copyOnWrite();
            ((Type) this.instance).addFields(i, builder.build());
            return this;
        }

        public Builder addOptions(int i, Option.Builder builder) {
            copyOnWrite();
            ((Type) this.instance).addOptions(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Type type) {
        return DEFAULT_INSTANCE.createBuilder(type);
    }

    public static Type parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Type) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Type parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Type) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Type parseFrom(ByteString byteString) {
        return (Type) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFields(int i, Field field) {
        field.getClass();
        ensureFieldsIsMutable();
        this.fields_.add(i, field);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addOptions(int i, Option option) {
        option.getClass();
        ensureOptionsIsMutable();
        this.options_.add(i, option);
    }

    public static Type parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Type) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Type parseFrom(byte[] bArr) {
        return (Type) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Type parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Type) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Type parseFrom(InputStream inputStream) {
        return (Type) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Type parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Type) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Type parseFrom(CodedInputStream codedInputStream) {
        return (Type) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Type parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Type) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
