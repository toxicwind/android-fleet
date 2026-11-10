package com.google.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class Date extends GeneratedMessageLite<Date, Builder> implements DateOrBuilder {
    public static final int DAY_FIELD_NUMBER = 3;
    public static final Date DEFAULT_INSTANCE;
    public static final int MONTH_FIELD_NUMBER = 2;
    public static volatile Parser<Date> PARSER = null;
    public static final int YEAR_FIELD_NUMBER = 1;
    public int day_;
    public int month_;
    public int year_;

    /* JADX INFO: renamed from: com.google.type.Date$1, reason: invalid class name */
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
        Date date = new Date();
        DEFAULT_INSTANCE = date;
        GeneratedMessageLite.defaultInstanceMap.put(Date.class, date);
    }

    private void clearDay() {
        this.day_ = 0;
    }

    private void clearMonth() {
        this.month_ = 0;
    }

    private void clearYear() {
        this.year_ = 0;
    }

    public static Date getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Date parseDelimitedFrom(InputStream inputStream) {
        return (Date) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Date parseFrom(ByteBuffer byteBuffer) {
        return (Date) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Date> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setDay(int i) {
        this.day_ = i;
    }

    private void setMonth(int i) {
        this.month_ = i;
    }

    private void setYear(int i) {
        this.year_ = i;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0002\u0004\u0003\u0004", new Object[]{"year_", "month_", "day_"});
            case NEW_MUTABLE_INSTANCE:
                return new Date();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Date> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Date.class) {
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

    @Override // com.google.type.DateOrBuilder
    public int getDay() {
        return this.day_;
    }

    @Override // com.google.type.DateOrBuilder
    public int getMonth() {
        return this.month_;
    }

    @Override // com.google.type.DateOrBuilder
    public int getYear() {
        return this.year_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Date, Builder> implements DateOrBuilder {
        public Builder() {
            super(Date.DEFAULT_INSTANCE);
        }

        public Builder clearDay() {
            copyOnWrite();
            ((Date) this.instance).day_ = 0;
            return this;
        }

        public Builder clearMonth() {
            copyOnWrite();
            ((Date) this.instance).month_ = 0;
            return this;
        }

        public Builder clearYear() {
            copyOnWrite();
            ((Date) this.instance).year_ = 0;
            return this;
        }

        @Override // com.google.type.DateOrBuilder
        public int getDay() {
            return ((Date) this.instance).getDay();
        }

        @Override // com.google.type.DateOrBuilder
        public int getMonth() {
            return ((Date) this.instance).getMonth();
        }

        @Override // com.google.type.DateOrBuilder
        public int getYear() {
            return ((Date) this.instance).getYear();
        }

        public Builder setDay(int i) {
            copyOnWrite();
            ((Date) this.instance).day_ = i;
            return this;
        }

        public Builder setMonth(int i) {
            copyOnWrite();
            ((Date) this.instance).month_ = i;
            return this;
        }

        public Builder setYear(int i) {
            copyOnWrite();
            ((Date) this.instance).year_ = i;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Date.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(Date date) {
        return DEFAULT_INSTANCE.createBuilder(date);
    }

    public static Date parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Date) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Date parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Date) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Date parseFrom(ByteString byteString) {
        return (Date) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Date parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Date) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Date parseFrom(byte[] bArr) {
        return (Date) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Date parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Date) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Date parseFrom(InputStream inputStream) {
        return (Date) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Date parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Date) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Date parseFrom(CodedInputStream codedInputStream) {
        return (Date) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Date parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Date) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
