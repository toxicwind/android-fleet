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
public final class Cursor extends GeneratedMessageLite<Cursor, Builder> implements CursorOrBuilder {
    public static final int BEFORE_FIELD_NUMBER = 2;
    public static final Cursor DEFAULT_INSTANCE;
    public static volatile Parser<Cursor> PARSER = null;
    public static final int VALUES_FIELD_NUMBER = 1;
    public boolean before_;
    public Internal.ProtobufList<Value> values_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.firestore.v1.Cursor$1, reason: invalid class name */
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
        Cursor cursor = new Cursor();
        DEFAULT_INSTANCE = cursor;
        GeneratedMessageLite.defaultInstanceMap.put(Cursor.class, cursor);
    }

    public static /* synthetic */ void access$400(Cursor cursor, Iterable iterable) {
        cursor.ensureValuesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) cursor.values_);
    }

    public static /* synthetic */ void access$600(Cursor cursor, int i) {
        cursor.ensureValuesIsMutable();
        cursor.values_.remove(i);
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

    private void clearBefore() {
        this.before_ = false;
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

    public static Cursor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Cursor parseDelimitedFrom(InputStream inputStream) {
        return (Cursor) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Cursor parseFrom(ByteBuffer byteBuffer) {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Cursor> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeValues(int i) {
        ensureValuesIsMutable();
        this.values_.remove(i);
    }

    private void setBefore(boolean z) {
        this.before_ = z;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u0007", new Object[]{"values_", Value.class, "before_"});
            case NEW_MUTABLE_INSTANCE:
                return new Cursor();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Cursor> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Cursor.class) {
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

    @Override // com.google.firestore.v1.CursorOrBuilder
    public boolean getBefore() {
        return this.before_;
    }

    @Override // com.google.firestore.v1.CursorOrBuilder
    public Value getValues(int i) {
        return this.values_.get(i);
    }

    @Override // com.google.firestore.v1.CursorOrBuilder
    public int getValuesCount() {
        return this.values_.size();
    }

    @Override // com.google.firestore.v1.CursorOrBuilder
    public List<Value> getValuesList() {
        return this.values_;
    }

    public ValueOrBuilder getValuesOrBuilder(int i) {
        return this.values_.get(i);
    }

    public List<? extends ValueOrBuilder> getValuesOrBuilderList() {
        return this.values_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Cursor, Builder> implements CursorOrBuilder {
        public Builder() {
            super(Cursor.DEFAULT_INSTANCE);
        }

        public Builder addAllValues(Iterable<? extends Value> iterable) {
            copyOnWrite();
            Cursor.access$400((Cursor) this.instance, iterable);
            return this;
        }

        public Builder addValues(Value value) {
            copyOnWrite();
            ((Cursor) this.instance).addValues(value);
            return this;
        }

        public Builder clearBefore() {
            copyOnWrite();
            ((Cursor) this.instance).before_ = false;
            return this;
        }

        public Builder clearValues() {
            copyOnWrite();
            ((Cursor) this.instance).clearValues();
            return this;
        }

        @Override // com.google.firestore.v1.CursorOrBuilder
        public boolean getBefore() {
            return ((Cursor) this.instance).getBefore();
        }

        @Override // com.google.firestore.v1.CursorOrBuilder
        public Value getValues(int i) {
            return ((Cursor) this.instance).getValues(i);
        }

        @Override // com.google.firestore.v1.CursorOrBuilder
        public int getValuesCount() {
            return ((Cursor) this.instance).getValuesCount();
        }

        @Override // com.google.firestore.v1.CursorOrBuilder
        public List<Value> getValuesList() {
            return Collections.unmodifiableList(((Cursor) this.instance).getValuesList());
        }

        public Builder removeValues(int i) {
            copyOnWrite();
            Cursor.access$600((Cursor) this.instance, i);
            return this;
        }

        public Builder setBefore(boolean z) {
            copyOnWrite();
            ((Cursor) this.instance).before_ = z;
            return this;
        }

        public Builder setValues(int i, Value value) {
            copyOnWrite();
            ((Cursor) this.instance).setValues(i, value);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Cursor.DEFAULT_INSTANCE);
        }

        public Builder addValues(int i, Value value) {
            copyOnWrite();
            ((Cursor) this.instance).addValues(i, value);
            return this;
        }

        public Builder setValues(int i, Value.Builder builder) {
            copyOnWrite();
            ((Cursor) this.instance).setValues(i, builder.build());
            return this;
        }

        public Builder addValues(Value.Builder builder) {
            copyOnWrite();
            ((Cursor) this.instance).addValues(builder.build());
            return this;
        }

        public Builder addValues(int i, Value.Builder builder) {
            copyOnWrite();
            ((Cursor) this.instance).addValues(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Cursor cursor) {
        return DEFAULT_INSTANCE.createBuilder(cursor);
    }

    public static Cursor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Cursor) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Cursor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Cursor parseFrom(ByteString byteString) {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addValues(int i, Value value) {
        value.getClass();
        ensureValuesIsMutable();
        this.values_.add(i, value);
    }

    public static Cursor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Cursor parseFrom(byte[] bArr) {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Cursor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Cursor parseFrom(InputStream inputStream) {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Cursor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Cursor parseFrom(CodedInputStream codedInputStream) {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Cursor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
