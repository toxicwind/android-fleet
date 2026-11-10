package com.google.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Duration;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class DateTime extends GeneratedMessageLite<DateTime, Builder> implements DateTimeOrBuilder {
    public static final int DAY_FIELD_NUMBER = 3;
    public static final DateTime DEFAULT_INSTANCE;
    public static final int HOURS_FIELD_NUMBER = 4;
    public static final int MINUTES_FIELD_NUMBER = 5;
    public static final int MONTH_FIELD_NUMBER = 2;
    public static final int NANOS_FIELD_NUMBER = 7;
    public static volatile Parser<DateTime> PARSER = null;
    public static final int SECONDS_FIELD_NUMBER = 6;
    public static final int TIME_ZONE_FIELD_NUMBER = 9;
    public static final int UTC_OFFSET_FIELD_NUMBER = 8;
    public static final int YEAR_FIELD_NUMBER = 1;
    public int day_;
    public int hours_;
    public int minutes_;
    public int month_;
    public int nanos_;
    public int seconds_;
    public int timeOffsetCase_ = 0;
    public Object timeOffset_;
    public int year_;

    /* JADX INFO: renamed from: com.google.type.DateTime$1, reason: invalid class name */
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

    public enum TimeOffsetCase {
        UTC_OFFSET(8),
        TIME_ZONE(9),
        TIMEOFFSET_NOT_SET(0);

        public final int value;

        TimeOffsetCase(int i) {
            this.value = i;
        }

        public static TimeOffsetCase forNumber(int i) {
            if (i == 0) {
                return TIMEOFFSET_NOT_SET;
            }
            if (i == 8) {
                return UTC_OFFSET;
            }
            if (i != 9) {
                return null;
            }
            return TIME_ZONE;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static TimeOffsetCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        DateTime dateTime = new DateTime();
        DEFAULT_INSTANCE = dateTime;
        GeneratedMessageLite.defaultInstanceMap.put(DateTime.class, dateTime);
    }

    private void clearDay() {
        this.day_ = 0;
    }

    private void clearHours() {
        this.hours_ = 0;
    }

    private void clearMinutes() {
        this.minutes_ = 0;
    }

    private void clearMonth() {
        this.month_ = 0;
    }

    private void clearNanos() {
        this.nanos_ = 0;
    }

    private void clearSeconds() {
        this.seconds_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTimeOffset() {
        this.timeOffsetCase_ = 0;
        this.timeOffset_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTimeZone() {
        if (this.timeOffsetCase_ == 9) {
            this.timeOffsetCase_ = 0;
            this.timeOffset_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUtcOffset() {
        if (this.timeOffsetCase_ == 8) {
            this.timeOffsetCase_ = 0;
            this.timeOffset_ = null;
        }
    }

    private void clearYear() {
        this.year_ = 0;
    }

    public static DateTime getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeTimeZone(TimeZone timeZone) {
        Object obj;
        timeZone.getClass();
        if (this.timeOffsetCase_ != 9 || (obj = this.timeOffset_) == TimeZone.DEFAULT_INSTANCE) {
            this.timeOffset_ = timeZone;
        } else {
            this.timeOffset_ = TimeZone.newBuilder((TimeZone) obj).mergeFrom(timeZone).buildPartial();
        }
        this.timeOffsetCase_ = 9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUtcOffset(Duration duration) {
        Object obj;
        duration.getClass();
        if (this.timeOffsetCase_ != 8 || (obj = this.timeOffset_) == Duration.DEFAULT_INSTANCE) {
            this.timeOffset_ = duration;
        } else {
            this.timeOffset_ = Duration.newBuilder((Duration) obj).mergeFrom(duration).buildPartial();
        }
        this.timeOffsetCase_ = 8;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static DateTime parseDelimitedFrom(InputStream inputStream) {
        return (DateTime) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DateTime parseFrom(ByteBuffer byteBuffer) {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<DateTime> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setDay(int i) {
        this.day_ = i;
    }

    private void setHours(int i) {
        this.hours_ = i;
    }

    private void setMinutes(int i) {
        this.minutes_ = i;
    }

    private void setMonth(int i) {
        this.month_ = i;
    }

    private void setNanos(int i) {
        this.nanos_ = i;
    }

    private void setSeconds(int i) {
        this.seconds_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTimeZone(TimeZone timeZone) {
        timeZone.getClass();
        this.timeOffset_ = timeZone;
        this.timeOffsetCase_ = 9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUtcOffset(Duration duration) {
        duration.getClass();
        this.timeOffset_ = duration;
        this.timeOffsetCase_ = 8;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\t\u0001\u0000\u0001\t\t\u0000\u0000\u0000\u0001\u0004\u0002\u0004\u0003\u0004\u0004\u0004\u0005\u0004\u0006\u0004\u0007\u0004\b<\u0000\t<\u0000", new Object[]{"timeOffset_", "timeOffsetCase_", "year_", "month_", "day_", "hours_", "minutes_", "seconds_", "nanos_", Duration.class, TimeZone.class});
            case NEW_MUTABLE_INSTANCE:
                return new DateTime();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<DateTime> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (DateTime.class) {
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

    @Override // com.google.type.DateTimeOrBuilder
    public int getDay() {
        return this.day_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public int getHours() {
        return this.hours_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public int getMinutes() {
        return this.minutes_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public int getMonth() {
        return this.month_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public int getNanos() {
        return this.nanos_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public int getSeconds() {
        return this.seconds_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public TimeOffsetCase getTimeOffsetCase() {
        return TimeOffsetCase.forNumber(this.timeOffsetCase_);
    }

    @Override // com.google.type.DateTimeOrBuilder
    public TimeZone getTimeZone() {
        return this.timeOffsetCase_ == 9 ? (TimeZone) this.timeOffset_ : TimeZone.DEFAULT_INSTANCE;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public Duration getUtcOffset() {
        return this.timeOffsetCase_ == 8 ? (Duration) this.timeOffset_ : Duration.DEFAULT_INSTANCE;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public int getYear() {
        return this.year_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public boolean hasTimeZone() {
        return this.timeOffsetCase_ == 9;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public boolean hasUtcOffset() {
        return this.timeOffsetCase_ == 8;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DateTime, Builder> implements DateTimeOrBuilder {
        public Builder() {
            super(DateTime.DEFAULT_INSTANCE);
        }

        public Builder clearDay() {
            copyOnWrite();
            ((DateTime) this.instance).day_ = 0;
            return this;
        }

        public Builder clearHours() {
            copyOnWrite();
            ((DateTime) this.instance).hours_ = 0;
            return this;
        }

        public Builder clearMinutes() {
            copyOnWrite();
            ((DateTime) this.instance).minutes_ = 0;
            return this;
        }

        public Builder clearMonth() {
            copyOnWrite();
            ((DateTime) this.instance).month_ = 0;
            return this;
        }

        public Builder clearNanos() {
            copyOnWrite();
            ((DateTime) this.instance).nanos_ = 0;
            return this;
        }

        public Builder clearSeconds() {
            copyOnWrite();
            ((DateTime) this.instance).seconds_ = 0;
            return this;
        }

        public Builder clearTimeOffset() {
            copyOnWrite();
            ((DateTime) this.instance).clearTimeOffset();
            return this;
        }

        public Builder clearTimeZone() {
            copyOnWrite();
            ((DateTime) this.instance).clearTimeZone();
            return this;
        }

        public Builder clearUtcOffset() {
            copyOnWrite();
            ((DateTime) this.instance).clearUtcOffset();
            return this;
        }

        public Builder clearYear() {
            copyOnWrite();
            ((DateTime) this.instance).year_ = 0;
            return this;
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getDay() {
            return ((DateTime) this.instance).getDay();
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getHours() {
            return ((DateTime) this.instance).getHours();
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getMinutes() {
            return ((DateTime) this.instance).getMinutes();
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getMonth() {
            return ((DateTime) this.instance).getMonth();
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getNanos() {
            return ((DateTime) this.instance).getNanos();
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getSeconds() {
            return ((DateTime) this.instance).getSeconds();
        }

        @Override // com.google.type.DateTimeOrBuilder
        public TimeOffsetCase getTimeOffsetCase() {
            return ((DateTime) this.instance).getTimeOffsetCase();
        }

        @Override // com.google.type.DateTimeOrBuilder
        public TimeZone getTimeZone() {
            return ((DateTime) this.instance).getTimeZone();
        }

        @Override // com.google.type.DateTimeOrBuilder
        public Duration getUtcOffset() {
            return ((DateTime) this.instance).getUtcOffset();
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getYear() {
            return ((DateTime) this.instance).getYear();
        }

        @Override // com.google.type.DateTimeOrBuilder
        public boolean hasTimeZone() {
            return ((DateTime) this.instance).hasTimeZone();
        }

        @Override // com.google.type.DateTimeOrBuilder
        public boolean hasUtcOffset() {
            return ((DateTime) this.instance).hasUtcOffset();
        }

        public Builder mergeTimeZone(TimeZone timeZone) {
            copyOnWrite();
            ((DateTime) this.instance).mergeTimeZone(timeZone);
            return this;
        }

        public Builder mergeUtcOffset(Duration duration) {
            copyOnWrite();
            ((DateTime) this.instance).mergeUtcOffset(duration);
            return this;
        }

        public Builder setDay(int i) {
            copyOnWrite();
            ((DateTime) this.instance).day_ = i;
            return this;
        }

        public Builder setHours(int i) {
            copyOnWrite();
            ((DateTime) this.instance).hours_ = i;
            return this;
        }

        public Builder setMinutes(int i) {
            copyOnWrite();
            ((DateTime) this.instance).minutes_ = i;
            return this;
        }

        public Builder setMonth(int i) {
            copyOnWrite();
            ((DateTime) this.instance).month_ = i;
            return this;
        }

        public Builder setNanos(int i) {
            copyOnWrite();
            ((DateTime) this.instance).nanos_ = i;
            return this;
        }

        public Builder setSeconds(int i) {
            copyOnWrite();
            ((DateTime) this.instance).seconds_ = i;
            return this;
        }

        public Builder setTimeZone(TimeZone timeZone) {
            copyOnWrite();
            ((DateTime) this.instance).setTimeZone(timeZone);
            return this;
        }

        public Builder setUtcOffset(Duration duration) {
            copyOnWrite();
            ((DateTime) this.instance).setUtcOffset(duration);
            return this;
        }

        public Builder setYear(int i) {
            copyOnWrite();
            ((DateTime) this.instance).year_ = i;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(DateTime.DEFAULT_INSTANCE);
        }

        public Builder setTimeZone(TimeZone.Builder builder) {
            copyOnWrite();
            ((DateTime) this.instance).setTimeZone(builder.build());
            return this;
        }

        public Builder setUtcOffset(Duration.Builder builder) {
            copyOnWrite();
            ((DateTime) this.instance).setUtcOffset(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(DateTime dateTime) {
        return DEFAULT_INSTANCE.createBuilder(dateTime);
    }

    public static DateTime parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DateTime) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DateTime parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DateTime parseFrom(ByteString byteString) {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DateTime parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DateTime parseFrom(byte[] bArr) {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DateTime parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DateTime parseFrom(InputStream inputStream) {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DateTime parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DateTime parseFrom(CodedInputStream codedInputStream) {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DateTime parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DateTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
