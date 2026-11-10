package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Logging extends GeneratedMessageLite<Logging, Builder> implements LoggingOrBuilder {
    public static final int CONSUMER_DESTINATIONS_FIELD_NUMBER = 2;
    public static final Logging DEFAULT_INSTANCE;
    public static volatile Parser<Logging> PARSER = null;
    public static final int PRODUCER_DESTINATIONS_FIELD_NUMBER = 1;
    public Internal.ProtobufList<LoggingDestination> producerDestinations_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<LoggingDestination> consumerDestinations_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.Logging$1, reason: invalid class name */
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

    public static final class LoggingDestination extends GeneratedMessageLite<LoggingDestination, Builder> implements LoggingDestinationOrBuilder {
        public static final LoggingDestination DEFAULT_INSTANCE;
        public static final int LOGS_FIELD_NUMBER = 1;
        public static final int MONITORED_RESOURCE_FIELD_NUMBER = 3;
        public static volatile Parser<LoggingDestination> PARSER;
        public String monitoredResource_ = "";
        public Internal.ProtobufList<String> logs_ = GeneratedMessageLite.emptyProtobufList();

        static {
            LoggingDestination loggingDestination = new LoggingDestination();
            DEFAULT_INSTANCE = loggingDestination;
            GeneratedMessageLite.defaultInstanceMap.put(LoggingDestination.class, loggingDestination);
        }

        public static /* synthetic */ void access$600(LoggingDestination loggingDestination, Iterable iterable) {
            loggingDestination.ensureLogsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, (List) loggingDestination.logs_);
        }

        private void addAllLogs(Iterable<String> iterable) {
            ensureLogsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.logs_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addLogs(String str) {
            str.getClass();
            ensureLogsIsMutable();
            this.logs_.add(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addLogsBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            ensureLogsIsMutable();
            this.logs_.add(byteString.toStringUtf8());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLogs() {
            this.logs_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMonitoredResource() {
            this.monitoredResource_ = DEFAULT_INSTANCE.getMonitoredResource();
        }

        private void ensureLogsIsMutable() {
            Internal.ProtobufList<String> protobufList = this.logs_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.logs_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        public static LoggingDestination getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static LoggingDestination parseDelimitedFrom(InputStream inputStream) {
            return (LoggingDestination) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LoggingDestination parseFrom(ByteBuffer byteBuffer) {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<LoggingDestination> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLogs(int i, String str) {
            str.getClass();
            ensureLogsIsMutable();
            this.logs_.set(i, str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMonitoredResource(String str) {
            str.getClass();
            this.monitoredResource_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMonitoredResourceBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.monitoredResource_ = byteString.toStringUtf8();
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0001\u0000\u0001Ț\u0003Ȉ", new Object[]{"logs_", "monitoredResource_"});
                case NEW_MUTABLE_INSTANCE:
                    return new LoggingDestination();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<LoggingDestination> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (LoggingDestination.class) {
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

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public String getLogs(int i) {
            return this.logs_.get(i);
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public ByteString getLogsBytes(int i) {
            return ByteString.copyFromUtf8(this.logs_.get(i));
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public int getLogsCount() {
            return this.logs_.size();
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public List<String> getLogsList() {
            return this.logs_;
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public String getMonitoredResource() {
            return this.monitoredResource_;
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public ByteString getMonitoredResourceBytes() {
            return ByteString.copyFromUtf8(this.monitoredResource_);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<LoggingDestination, Builder> implements LoggingDestinationOrBuilder {
            public Builder() {
                super(LoggingDestination.DEFAULT_INSTANCE);
            }

            public Builder addAllLogs(Iterable<String> iterable) {
                copyOnWrite();
                LoggingDestination.access$600((LoggingDestination) this.instance, iterable);
                return this;
            }

            public Builder addLogs(String str) {
                copyOnWrite();
                ((LoggingDestination) this.instance).addLogs(str);
                return this;
            }

            public Builder addLogsBytes(ByteString byteString) {
                copyOnWrite();
                ((LoggingDestination) this.instance).addLogsBytes(byteString);
                return this;
            }

            public Builder clearLogs() {
                copyOnWrite();
                ((LoggingDestination) this.instance).clearLogs();
                return this;
            }

            public Builder clearMonitoredResource() {
                copyOnWrite();
                ((LoggingDestination) this.instance).clearMonitoredResource();
                return this;
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public String getLogs(int i) {
                return ((LoggingDestination) this.instance).getLogs(i);
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public ByteString getLogsBytes(int i) {
                return ((LoggingDestination) this.instance).getLogsBytes(i);
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public int getLogsCount() {
                return ((LoggingDestination) this.instance).getLogsCount();
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public List<String> getLogsList() {
                return Collections.unmodifiableList(((LoggingDestination) this.instance).getLogsList());
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public String getMonitoredResource() {
                return ((LoggingDestination) this.instance).getMonitoredResource();
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public ByteString getMonitoredResourceBytes() {
                return ((LoggingDestination) this.instance).getMonitoredResourceBytes();
            }

            public Builder setLogs(int i, String str) {
                copyOnWrite();
                ((LoggingDestination) this.instance).setLogs(i, str);
                return this;
            }

            public Builder setMonitoredResource(String str) {
                copyOnWrite();
                ((LoggingDestination) this.instance).setMonitoredResource(str);
                return this;
            }

            public Builder setMonitoredResourceBytes(ByteString byteString) {
                copyOnWrite();
                ((LoggingDestination) this.instance).setMonitoredResourceBytes(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(LoggingDestination.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(LoggingDestination loggingDestination) {
            return DEFAULT_INSTANCE.createBuilder(loggingDestination);
        }

        public static LoggingDestination parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (LoggingDestination) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static LoggingDestination parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static LoggingDestination parseFrom(ByteString byteString) {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static LoggingDestination parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static LoggingDestination parseFrom(byte[] bArr) {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static LoggingDestination parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static LoggingDestination parseFrom(InputStream inputStream) {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LoggingDestination parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static LoggingDestination parseFrom(CodedInputStream codedInputStream) {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static LoggingDestination parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface LoggingDestinationOrBuilder extends MessageLiteOrBuilder {
        String getLogs(int i);

        ByteString getLogsBytes(int i);

        int getLogsCount();

        List<String> getLogsList();

        String getMonitoredResource();

        ByteString getMonitoredResourceBytes();
    }

    static {
        Logging logging = new Logging();
        DEFAULT_INSTANCE = logging;
        GeneratedMessageLite.defaultInstanceMap.put(Logging.class, logging);
    }

    public static /* synthetic */ void access$1400(Logging logging, Iterable iterable) {
        logging.ensureProducerDestinationsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) logging.producerDestinations_);
    }

    public static /* synthetic */ void access$1600(Logging logging, int i) {
        logging.ensureProducerDestinationsIsMutable();
        logging.producerDestinations_.remove(i);
    }

    public static /* synthetic */ void access$2000(Logging logging, Iterable iterable) {
        logging.ensureConsumerDestinationsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) logging.consumerDestinations_);
    }

    public static /* synthetic */ void access$2200(Logging logging, int i) {
        logging.ensureConsumerDestinationsIsMutable();
        logging.consumerDestinations_.remove(i);
    }

    private void addAllConsumerDestinations(Iterable<? extends LoggingDestination> iterable) {
        ensureConsumerDestinationsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.consumerDestinations_);
    }

    private void addAllProducerDestinations(Iterable<? extends LoggingDestination> iterable) {
        ensureProducerDestinationsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.producerDestinations_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addConsumerDestinations(LoggingDestination loggingDestination) {
        loggingDestination.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.add(loggingDestination);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProducerDestinations(LoggingDestination loggingDestination) {
        loggingDestination.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.add(loggingDestination);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConsumerDestinations() {
        this.consumerDestinations_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearProducerDestinations() {
        this.producerDestinations_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureConsumerDestinationsIsMutable() {
        Internal.ProtobufList<LoggingDestination> protobufList = this.consumerDestinations_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.consumerDestinations_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureProducerDestinationsIsMutable() {
        Internal.ProtobufList<LoggingDestination> protobufList = this.producerDestinations_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.producerDestinations_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static Logging getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Logging parseDelimitedFrom(InputStream inputStream) {
        return (Logging) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Logging parseFrom(ByteBuffer byteBuffer) {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Logging> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeConsumerDestinations(int i) {
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.remove(i);
    }

    private void removeProducerDestinations(int i) {
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConsumerDestinations(int i, LoggingDestination loggingDestination) {
        loggingDestination.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.set(i, loggingDestination);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProducerDestinations(int i, LoggingDestination loggingDestination) {
        loggingDestination.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.set(i, loggingDestination);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b", new Object[]{"producerDestinations_", LoggingDestination.class, "consumerDestinations_", LoggingDestination.class});
            case NEW_MUTABLE_INSTANCE:
                return new Logging();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Logging> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Logging.class) {
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

    @Override // com.google.api.LoggingOrBuilder
    public LoggingDestination getConsumerDestinations(int i) {
        return this.consumerDestinations_.get(i);
    }

    @Override // com.google.api.LoggingOrBuilder
    public int getConsumerDestinationsCount() {
        return this.consumerDestinations_.size();
    }

    @Override // com.google.api.LoggingOrBuilder
    public List<LoggingDestination> getConsumerDestinationsList() {
        return this.consumerDestinations_;
    }

    public LoggingDestinationOrBuilder getConsumerDestinationsOrBuilder(int i) {
        return this.consumerDestinations_.get(i);
    }

    public List<? extends LoggingDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
        return this.consumerDestinations_;
    }

    @Override // com.google.api.LoggingOrBuilder
    public LoggingDestination getProducerDestinations(int i) {
        return this.producerDestinations_.get(i);
    }

    @Override // com.google.api.LoggingOrBuilder
    public int getProducerDestinationsCount() {
        return this.producerDestinations_.size();
    }

    @Override // com.google.api.LoggingOrBuilder
    public List<LoggingDestination> getProducerDestinationsList() {
        return this.producerDestinations_;
    }

    public LoggingDestinationOrBuilder getProducerDestinationsOrBuilder(int i) {
        return this.producerDestinations_.get(i);
    }

    public List<? extends LoggingDestinationOrBuilder> getProducerDestinationsOrBuilderList() {
        return this.producerDestinations_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Logging, Builder> implements LoggingOrBuilder {
        public Builder() {
            super(Logging.DEFAULT_INSTANCE);
        }

        public Builder addAllConsumerDestinations(Iterable<? extends LoggingDestination> iterable) {
            copyOnWrite();
            Logging.access$2000((Logging) this.instance, iterable);
            return this;
        }

        public Builder addAllProducerDestinations(Iterable<? extends LoggingDestination> iterable) {
            copyOnWrite();
            Logging.access$1400((Logging) this.instance, iterable);
            return this;
        }

        public Builder addConsumerDestinations(LoggingDestination loggingDestination) {
            copyOnWrite();
            ((Logging) this.instance).addConsumerDestinations(loggingDestination);
            return this;
        }

        public Builder addProducerDestinations(LoggingDestination loggingDestination) {
            copyOnWrite();
            ((Logging) this.instance).addProducerDestinations(loggingDestination);
            return this;
        }

        public Builder clearConsumerDestinations() {
            copyOnWrite();
            ((Logging) this.instance).clearConsumerDestinations();
            return this;
        }

        public Builder clearProducerDestinations() {
            copyOnWrite();
            ((Logging) this.instance).clearProducerDestinations();
            return this;
        }

        @Override // com.google.api.LoggingOrBuilder
        public LoggingDestination getConsumerDestinations(int i) {
            return ((Logging) this.instance).getConsumerDestinations(i);
        }

        @Override // com.google.api.LoggingOrBuilder
        public int getConsumerDestinationsCount() {
            return ((Logging) this.instance).getConsumerDestinationsCount();
        }

        @Override // com.google.api.LoggingOrBuilder
        public List<LoggingDestination> getConsumerDestinationsList() {
            return Collections.unmodifiableList(((Logging) this.instance).getConsumerDestinationsList());
        }

        @Override // com.google.api.LoggingOrBuilder
        public LoggingDestination getProducerDestinations(int i) {
            return ((Logging) this.instance).getProducerDestinations(i);
        }

        @Override // com.google.api.LoggingOrBuilder
        public int getProducerDestinationsCount() {
            return ((Logging) this.instance).getProducerDestinationsCount();
        }

        @Override // com.google.api.LoggingOrBuilder
        public List<LoggingDestination> getProducerDestinationsList() {
            return Collections.unmodifiableList(((Logging) this.instance).getProducerDestinationsList());
        }

        public Builder removeConsumerDestinations(int i) {
            copyOnWrite();
            Logging.access$2200((Logging) this.instance, i);
            return this;
        }

        public Builder removeProducerDestinations(int i) {
            copyOnWrite();
            Logging.access$1600((Logging) this.instance, i);
            return this;
        }

        public Builder setConsumerDestinations(int i, LoggingDestination loggingDestination) {
            copyOnWrite();
            ((Logging) this.instance).setConsumerDestinations(i, loggingDestination);
            return this;
        }

        public Builder setProducerDestinations(int i, LoggingDestination loggingDestination) {
            copyOnWrite();
            ((Logging) this.instance).setProducerDestinations(i, loggingDestination);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Logging.DEFAULT_INSTANCE);
        }

        public Builder addConsumerDestinations(int i, LoggingDestination loggingDestination) {
            copyOnWrite();
            ((Logging) this.instance).addConsumerDestinations(i, loggingDestination);
            return this;
        }

        public Builder addProducerDestinations(int i, LoggingDestination loggingDestination) {
            copyOnWrite();
            ((Logging) this.instance).addProducerDestinations(i, loggingDestination);
            return this;
        }

        public Builder setConsumerDestinations(int i, LoggingDestination.Builder builder) {
            copyOnWrite();
            ((Logging) this.instance).setConsumerDestinations(i, builder.build());
            return this;
        }

        public Builder setProducerDestinations(int i, LoggingDestination.Builder builder) {
            copyOnWrite();
            ((Logging) this.instance).setProducerDestinations(i, builder.build());
            return this;
        }

        public Builder addConsumerDestinations(LoggingDestination.Builder builder) {
            copyOnWrite();
            ((Logging) this.instance).addConsumerDestinations(builder.build());
            return this;
        }

        public Builder addProducerDestinations(LoggingDestination.Builder builder) {
            copyOnWrite();
            ((Logging) this.instance).addProducerDestinations(builder.build());
            return this;
        }

        public Builder addConsumerDestinations(int i, LoggingDestination.Builder builder) {
            copyOnWrite();
            ((Logging) this.instance).addConsumerDestinations(i, builder.build());
            return this;
        }

        public Builder addProducerDestinations(int i, LoggingDestination.Builder builder) {
            copyOnWrite();
            ((Logging) this.instance).addProducerDestinations(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Logging logging) {
        return DEFAULT_INSTANCE.createBuilder(logging);
    }

    public static Logging parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Logging) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Logging parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Logging parseFrom(ByteString byteString) {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addConsumerDestinations(int i, LoggingDestination loggingDestination) {
        loggingDestination.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.add(i, loggingDestination);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProducerDestinations(int i, LoggingDestination loggingDestination) {
        loggingDestination.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.add(i, loggingDestination);
    }

    public static Logging parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Logging parseFrom(byte[] bArr) {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Logging parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Logging parseFrom(InputStream inputStream) {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Logging parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Logging parseFrom(CodedInputStream codedInputStream) {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Logging parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
