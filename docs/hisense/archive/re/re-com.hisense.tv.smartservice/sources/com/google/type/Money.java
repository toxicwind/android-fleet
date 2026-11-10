package com.google.type;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class Money extends GeneratedMessageLite<Money, Builder> implements MoneyOrBuilder {
    public static final int CURRENCY_CODE_FIELD_NUMBER = 1;
    public static final Money DEFAULT_INSTANCE;
    public static final int NANOS_FIELD_NUMBER = 3;
    public static volatile Parser<Money> PARSER = null;
    public static final int UNITS_FIELD_NUMBER = 2;
    public String currencyCode_ = "";
    public int nanos_;
    public long units_;

    /* JADX INFO: renamed from: com.google.type.Money$1, reason: invalid class name */
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
        Money money = new Money();
        DEFAULT_INSTANCE = money;
        GeneratedMessageLite.defaultInstanceMap.put(Money.class, money);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCurrencyCode() {
        this.currencyCode_ = DEFAULT_INSTANCE.getCurrencyCode();
    }

    private void clearNanos() {
        this.nanos_ = 0;
    }

    private void clearUnits() {
        this.units_ = 0L;
    }

    public static Money getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Money parseDelimitedFrom(InputStream inputStream) {
        return (Money) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Money parseFrom(ByteBuffer byteBuffer) {
        return (Money) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Money> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrencyCode(String str) {
        str.getClass();
        this.currencyCode_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrencyCodeBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.currencyCode_ = byteString.toStringUtf8();
    }

    private void setNanos(int i) {
        this.nanos_ = i;
    }

    private void setUnits(long j) {
        this.units_ = j;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\u0002\u0003\u0004", new Object[]{"currencyCode_", "units_", "nanos_"});
            case NEW_MUTABLE_INSTANCE:
                return new Money();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Money> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Money.class) {
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

    @Override // com.google.type.MoneyOrBuilder
    public String getCurrencyCode() {
        return this.currencyCode_;
    }

    @Override // com.google.type.MoneyOrBuilder
    public ByteString getCurrencyCodeBytes() {
        return ByteString.copyFromUtf8(this.currencyCode_);
    }

    @Override // com.google.type.MoneyOrBuilder
    public int getNanos() {
        return this.nanos_;
    }

    @Override // com.google.type.MoneyOrBuilder
    public long getUnits() {
        return this.units_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Money, Builder> implements MoneyOrBuilder {
        public Builder() {
            super(Money.DEFAULT_INSTANCE);
        }

        public Builder clearCurrencyCode() {
            copyOnWrite();
            ((Money) this.instance).clearCurrencyCode();
            return this;
        }

        public Builder clearNanos() {
            copyOnWrite();
            ((Money) this.instance).nanos_ = 0;
            return this;
        }

        public Builder clearUnits() {
            copyOnWrite();
            ((Money) this.instance).units_ = 0L;
            return this;
        }

        @Override // com.google.type.MoneyOrBuilder
        public String getCurrencyCode() {
            return ((Money) this.instance).getCurrencyCode();
        }

        @Override // com.google.type.MoneyOrBuilder
        public ByteString getCurrencyCodeBytes() {
            return ((Money) this.instance).getCurrencyCodeBytes();
        }

        @Override // com.google.type.MoneyOrBuilder
        public int getNanos() {
            return ((Money) this.instance).getNanos();
        }

        @Override // com.google.type.MoneyOrBuilder
        public long getUnits() {
            return ((Money) this.instance).getUnits();
        }

        public Builder setCurrencyCode(String str) {
            copyOnWrite();
            ((Money) this.instance).setCurrencyCode(str);
            return this;
        }

        public Builder setCurrencyCodeBytes(ByteString byteString) {
            copyOnWrite();
            ((Money) this.instance).setCurrencyCodeBytes(byteString);
            return this;
        }

        public Builder setNanos(int i) {
            copyOnWrite();
            ((Money) this.instance).nanos_ = i;
            return this;
        }

        public Builder setUnits(long j) {
            copyOnWrite();
            ((Money) this.instance).units_ = j;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Money.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(Money money) {
        return DEFAULT_INSTANCE.createBuilder(money);
    }

    public static Money parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Money) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Money parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Money) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Money parseFrom(ByteString byteString) {
        return (Money) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Money parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Money) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Money parseFrom(byte[] bArr) {
        return (Money) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Money parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Money) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Money parseFrom(InputStream inputStream) {
        return (Money) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Money parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Money) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Money parseFrom(CodedInputStream codedInputStream) {
        return (Money) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Money parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Money) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
