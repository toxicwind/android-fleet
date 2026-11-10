package com.google.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class TimeOfDay extends GeneratedMessageLite<TimeOfDay, Builder> implements TimeOfDayOrBuilder {
    public static final TimeOfDay DEFAULT_INSTANCE;
    public static final int HOURS_FIELD_NUMBER = 1;
    public static final int MINUTES_FIELD_NUMBER = 2;
    public static final int NANOS_FIELD_NUMBER = 4;
    public static volatile Parser<TimeOfDay> PARSER = null;
    public static final int SECONDS_FIELD_NUMBER = 3;
    public int hours_;
    public int minutes_;
    public int nanos_;
    public int seconds_;

    /* JADX INFO: renamed from: com.google.type.TimeOfDay$1, reason: invalid class name */
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
        TimeOfDay timeOfDay = new TimeOfDay();
        DEFAULT_INSTANCE = timeOfDay;
        GeneratedMessageLite.defaultInstanceMap.put(TimeOfDay.class, timeOfDay);
    }

    private void clearHours() {
        this.hours_ = 0;
    }

    private void clearMinutes() {
        this.minutes_ = 0;
    }

    private void clearNanos() {
        this.nanos_ = 0;
    }

    private void clearSeconds() {
        this.seconds_ = 0;
    }

    public static TimeOfDay getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static TimeOfDay parseDelimitedFrom(InputStream inputStream) {
        return (TimeOfDay) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TimeOfDay parseFrom(ByteBuffer byteBuffer) {
        return (TimeOfDay) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<TimeOfDay> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setHours(int i) {
        this.hours_ = i;
    }

    private void setMinutes(int i) {
        this.minutes_ = i;
    }

    private void setNanos(int i) {
        this.nanos_ = i;
    }

    private void setSeconds(int i) {
        this.seconds_ = i;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0004\u0002\u0004\u0003\u0004\u0004\u0004", new Object[]{"hours_", "minutes_", "seconds_", "nanos_"});
            case NEW_MUTABLE_INSTANCE:
                return new TimeOfDay();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<TimeOfDay> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (TimeOfDay.class) {
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

    @Override // com.google.type.TimeOfDayOrBuilder
    public int getHours() {
        return this.hours_;
    }

    @Override // com.google.type.TimeOfDayOrBuilder
    public int getMinutes() {
        return this.minutes_;
    }

    @Override // com.google.type.TimeOfDayOrBuilder
    public int getNanos() {
        return this.nanos_;
    }

    @Override // com.google.type.TimeOfDayOrBuilder
    public int getSeconds() {
        return this.seconds_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TimeOfDay, Builder> implements TimeOfDayOrBuilder {
        public Builder() {
            super(TimeOfDay.DEFAULT_INSTANCE);
        }

        public Builder clearHours() {
            copyOnWrite();
            ((TimeOfDay) this.instance).hours_ = 0;
            return this;
        }

        public Builder clearMinutes() {
            copyOnWrite();
            ((TimeOfDay) this.instance).minutes_ = 0;
            return this;
        }

        public Builder clearNanos() {
            copyOnWrite();
            ((TimeOfDay) this.instance).nanos_ = 0;
            return this;
        }

        public Builder clearSeconds() {
            copyOnWrite();
            ((TimeOfDay) this.instance).seconds_ = 0;
            return this;
        }

        @Override // com.google.type.TimeOfDayOrBuilder
        public int getHours() {
            return ((TimeOfDay) this.instance).getHours();
        }

        @Override // com.google.type.TimeOfDayOrBuilder
        public int getMinutes() {
            return ((TimeOfDay) this.instance).getMinutes();
        }

        @Override // com.google.type.TimeOfDayOrBuilder
        public int getNanos() {
            return ((TimeOfDay) this.instance).getNanos();
        }

        @Override // com.google.type.TimeOfDayOrBuilder
        public int getSeconds() {
            return ((TimeOfDay) this.instance).getSeconds();
        }

        public Builder setHours(int i) {
            copyOnWrite();
            ((TimeOfDay) this.instance).hours_ = i;
            return this;
        }

        public Builder setMinutes(int i) {
            copyOnWrite();
            ((TimeOfDay) this.instance).minutes_ = i;
            return this;
        }

        public Builder setNanos(int i) {
            copyOnWrite();
            ((TimeOfDay) this.instance).nanos_ = i;
            return this;
        }

        public Builder setSeconds(int i) {
            copyOnWrite();
            ((TimeOfDay) this.instance).seconds_ = i;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(TimeOfDay.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(TimeOfDay timeOfDay) {
        return DEFAULT_INSTANCE.createBuilder(timeOfDay);
    }

    public static TimeOfDay parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TimeOfDay) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TimeOfDay parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (TimeOfDay) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static TimeOfDay parseFrom(ByteString byteString) {
        return (TimeOfDay) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TimeOfDay parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (TimeOfDay) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TimeOfDay parseFrom(byte[] bArr) {
        return (TimeOfDay) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TimeOfDay parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (TimeOfDay) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TimeOfDay parseFrom(InputStream inputStream) {
        return (TimeOfDay) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TimeOfDay parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TimeOfDay) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TimeOfDay parseFrom(CodedInputStream codedInputStream) {
        return (TimeOfDay) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TimeOfDay parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TimeOfDay) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
