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
public final class ArrayValue extends GeneratedMessageLite<ArrayValue, Builder> implements ArrayValueOrBuilder {
    public static final ArrayValue DEFAULT_INSTANCE;
    public static volatile Parser<ArrayValue> PARSER = null;
    public static final int VALUES_FIELD_NUMBER = 1;
    public Internal.ProtobufList<Value> values_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.firestore.v1.ArrayValue$1, reason: invalid class name */
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
        ArrayValue arrayValue = new ArrayValue();
        DEFAULT_INSTANCE = arrayValue;
        GeneratedMessageLite.defaultInstanceMap.put(ArrayValue.class, arrayValue);
    }

    public static /* synthetic */ void access$400(ArrayValue arrayValue, Iterable iterable) {
        arrayValue.ensureValuesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) arrayValue.values_);
    }

    public static /* synthetic */ void access$600(ArrayValue arrayValue, int i) {
        arrayValue.ensureValuesIsMutable();
        arrayValue.values_.remove(i);
    }

    private void addAllValues(Iterable<? extends Value> iterable) {
        ensureValuesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.values_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addValues(Value value) {
        value.getClass();
        ensureValuesIsMutable();
        this.values_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearValues() {
        this.values_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureValuesIsMutable() {
        Internal.ProtobufList<Value> protobufList = this.values_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.values_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static ArrayValue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static ArrayValue parseDelimitedFrom(InputStream inputStream) {
        return (ArrayValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ArrayValue parseFrom(ByteBuffer byteBuffer) {
        return (ArrayValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<ArrayValue> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeValues(int i) {
        ensureValuesIsMutable();
        this.values_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setValues(int i, Value value) {
        value.getClass();
        ensureValuesIsMutable();
        this.values_.set(i, value);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"values_", Value.class});
            case NEW_MUTABLE_INSTANCE:
                return new ArrayValue();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ArrayValue> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ArrayValue.class) {
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

    @Override // com.google.firestore.v1.ArrayValueOrBuilder
    public Value getValues(int i) {
        return this.values_.get(i);
    }

    @Override // com.google.firestore.v1.ArrayValueOrBuilder
    public int getValuesCount() {
        return this.values_.size();
    }

    @Override // com.google.firestore.v1.ArrayValueOrBuilder
    public List<Value> getValuesList() {
        return this.values_;
    }

    public ValueOrBuilder getValuesOrBuilder(int i) {
        return this.values_.get(i);
    }

    public List<? extends ValueOrBuilder> getValuesOrBuilderList() {
        return this.values_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ArrayValue, Builder> implements ArrayValueOrBuilder {
        public Builder() {
            super(ArrayValue.DEFAULT_INSTANCE);
        }

        public Builder addAllValues(Iterable<? extends Value> iterable) {
            copyOnWrite();
            ArrayValue.access$400((ArrayValue) this.instance, iterable);
            return this;
        }

        public Builder addValues(Value value) {
            copyOnWrite();
            ((ArrayValue) this.instance).addValues(value);
            return this;
        }

        public Builder clearValues() {
            copyOnWrite();
            ((ArrayValue) this.instance).clearValues();
            return this;
        }

        @Override // com.google.firestore.v1.ArrayValueOrBuilder
        public Value getValues(int i) {
            return ((ArrayValue) this.instance).getValues(i);
        }

        @Override // com.google.firestore.v1.ArrayValueOrBuilder
        public int getValuesCount() {
            return ((ArrayValue) this.instance).getValuesCount();
        }

        @Override // com.google.firestore.v1.ArrayValueOrBuilder
        public List<Value> getValuesList() {
            return Collections.unmodifiableList(((ArrayValue) this.instance).getValuesList());
        }

        public Builder removeValues(int i) {
            copyOnWrite();
            ArrayValue.access$600((ArrayValue) this.instance, i);
            return this;
        }

        public Builder setValues(int i, Value value) {
            copyOnWrite();
            ((ArrayValue) this.instance).setValues(i, value);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(ArrayValue.DEFAULT_INSTANCE);
        }

        public Builder addValues(int i, Value value) {
            copyOnWrite();
            ((ArrayValue) this.instance).addValues(i, value);
            return this;
        }

        public Builder setValues(int i, Value.Builder builder) {
            copyOnWrite();
            ((ArrayValue) this.instance).setValues(i, builder.build());
            return this;
        }

        public Builder addValues(Value.Builder builder) {
            copyOnWrite();
            ((ArrayValue) this.instance).addValues(builder.build());
            return this;
        }

        public Builder addValues(int i, Value.Builder builder) {
            copyOnWrite();
            ((ArrayValue) this.instance).addValues(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(ArrayValue arrayValue) {
        return DEFAULT_INSTANCE.createBuilder(arrayValue);
    }

    public static ArrayValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ArrayValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ArrayValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (ArrayValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ArrayValue parseFrom(ByteString byteString) {
        return (ArrayValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addValues(int i, Value value) {
        value.getClass();
        ensureValuesIsMutable();
        this.values_.add(i, value);
    }

    public static ArrayValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (ArrayValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ArrayValue parseFrom(byte[] bArr) {
        return (ArrayValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ArrayValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (ArrayValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ArrayValue parseFrom(InputStream inputStream) {
        return (ArrayValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ArrayValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ArrayValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ArrayValue parseFrom(CodedInputStream codedInputStream) {
        return (ArrayValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ArrayValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ArrayValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
