package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class Precondition extends GeneratedMessageLite<Precondition, Builder> implements PreconditionOrBuilder {
    public static final Precondition DEFAULT_INSTANCE;
    public static final int EXISTS_FIELD_NUMBER = 1;
    public static volatile Parser<Precondition> PARSER = null;
    public static final int UPDATE_TIME_FIELD_NUMBER = 2;
    public int conditionTypeCase_ = 0;
    public Object conditionType_;

    /* JADX INFO: renamed from: com.google.firestore.v1.Precondition$1, reason: invalid class name */
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

    public enum ConditionTypeCase {
        EXISTS(1),
        UPDATE_TIME(2),
        CONDITIONTYPE_NOT_SET(0);

        public final int value;

        ConditionTypeCase(int i) {
            this.value = i;
        }

        public static ConditionTypeCase forNumber(int i) {
            if (i == 0) {
                return CONDITIONTYPE_NOT_SET;
            }
            if (i == 1) {
                return EXISTS;
            }
            if (i != 2) {
                return null;
            }
            return UPDATE_TIME;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ConditionTypeCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        Precondition precondition = new Precondition();
        DEFAULT_INSTANCE = precondition;
        GeneratedMessageLite.defaultInstanceMap.put(Precondition.class, precondition);
    }

    public static /* synthetic */ void access$200(Precondition precondition, boolean z) {
        precondition.conditionTypeCase_ = 1;
        precondition.conditionType_ = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConditionType() {
        this.conditionTypeCase_ = 0;
        this.conditionType_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearExists() {
        if (this.conditionTypeCase_ == 1) {
            this.conditionTypeCase_ = 0;
            this.conditionType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUpdateTime() {
        if (this.conditionTypeCase_ == 2) {
            this.conditionTypeCase_ = 0;
            this.conditionType_ = null;
        }
    }

    public static Precondition getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUpdateTime(Timestamp timestamp) {
        Object obj;
        timestamp.getClass();
        if (this.conditionTypeCase_ != 2 || (obj = this.conditionType_) == Timestamp.DEFAULT_INSTANCE) {
            this.conditionType_ = timestamp;
        } else {
            this.conditionType_ = Timestamp.newBuilder((Timestamp) obj).mergeFrom(timestamp).buildPartial();
        }
        this.conditionTypeCase_ = 2;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Precondition parseDelimitedFrom(InputStream inputStream) {
        return (Precondition) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Precondition parseFrom(ByteBuffer byteBuffer) {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Precondition> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setExists(boolean z) {
        this.conditionTypeCase_ = 1;
        this.conditionType_ = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpdateTime(Timestamp timestamp) {
        timestamp.getClass();
        this.conditionType_ = timestamp;
        this.conditionTypeCase_ = 2;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001:\u0000\u0002<\u0000", new Object[]{"conditionType_", "conditionTypeCase_", Timestamp.class});
            case NEW_MUTABLE_INSTANCE:
                return new Precondition();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Precondition> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Precondition.class) {
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

    @Override // com.google.firestore.v1.PreconditionOrBuilder
    public ConditionTypeCase getConditionTypeCase() {
        return ConditionTypeCase.forNumber(this.conditionTypeCase_);
    }

    @Override // com.google.firestore.v1.PreconditionOrBuilder
    public boolean getExists() {
        if (this.conditionTypeCase_ == 1) {
            return ((Boolean) this.conditionType_).booleanValue();
        }
        return false;
    }

    @Override // com.google.firestore.v1.PreconditionOrBuilder
    public Timestamp getUpdateTime() {
        return this.conditionTypeCase_ == 2 ? (Timestamp) this.conditionType_ : Timestamp.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.PreconditionOrBuilder
    public boolean hasUpdateTime() {
        return this.conditionTypeCase_ == 2;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Precondition, Builder> implements PreconditionOrBuilder {
        public Builder() {
            super(Precondition.DEFAULT_INSTANCE);
        }

        public Builder clearConditionType() {
            copyOnWrite();
            ((Precondition) this.instance).clearConditionType();
            return this;
        }

        public Builder clearExists() {
            copyOnWrite();
            ((Precondition) this.instance).clearExists();
            return this;
        }

        public Builder clearUpdateTime() {
            copyOnWrite();
            ((Precondition) this.instance).clearUpdateTime();
            return this;
        }

        @Override // com.google.firestore.v1.PreconditionOrBuilder
        public ConditionTypeCase getConditionTypeCase() {
            return ((Precondition) this.instance).getConditionTypeCase();
        }

        @Override // com.google.firestore.v1.PreconditionOrBuilder
        public boolean getExists() {
            return ((Precondition) this.instance).getExists();
        }

        @Override // com.google.firestore.v1.PreconditionOrBuilder
        public Timestamp getUpdateTime() {
            return ((Precondition) this.instance).getUpdateTime();
        }

        @Override // com.google.firestore.v1.PreconditionOrBuilder
        public boolean hasUpdateTime() {
            return ((Precondition) this.instance).hasUpdateTime();
        }

        public Builder mergeUpdateTime(Timestamp timestamp) {
            copyOnWrite();
            ((Precondition) this.instance).mergeUpdateTime(timestamp);
            return this;
        }

        public Builder setExists(boolean z) {
            copyOnWrite();
            Precondition.access$200((Precondition) this.instance, z);
            return this;
        }

        public Builder setUpdateTime(Timestamp timestamp) {
            copyOnWrite();
            ((Precondition) this.instance).setUpdateTime(timestamp);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Precondition.DEFAULT_INSTANCE);
        }

        public Builder setUpdateTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((Precondition) this.instance).setUpdateTime(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Precondition precondition) {
        return DEFAULT_INSTANCE.createBuilder(precondition);
    }

    public static Precondition parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Precondition) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Precondition parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Precondition parseFrom(ByteString byteString) {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Precondition parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Precondition parseFrom(byte[] bArr) {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Precondition parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Precondition parseFrom(InputStream inputStream) {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Precondition parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Precondition parseFrom(CodedInputStream codedInputStream) {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Precondition parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
