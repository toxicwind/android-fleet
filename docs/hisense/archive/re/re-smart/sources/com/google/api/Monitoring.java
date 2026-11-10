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
public final class Monitoring extends GeneratedMessageLite<Monitoring, Builder> implements MonitoringOrBuilder {
    public static final int CONSUMER_DESTINATIONS_FIELD_NUMBER = 2;
    public static final Monitoring DEFAULT_INSTANCE;
    public static volatile Parser<Monitoring> PARSER = null;
    public static final int PRODUCER_DESTINATIONS_FIELD_NUMBER = 1;
    public Internal.ProtobufList<MonitoringDestination> producerDestinations_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<MonitoringDestination> consumerDestinations_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.Monitoring$1, reason: invalid class name */
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

    public static final class MonitoringDestination extends GeneratedMessageLite<MonitoringDestination, Builder> implements MonitoringDestinationOrBuilder {
        public static final MonitoringDestination DEFAULT_INSTANCE;
        public static final int METRICS_FIELD_NUMBER = 2;
        public static final int MONITORED_RESOURCE_FIELD_NUMBER = 1;
        public static volatile Parser<MonitoringDestination> PARSER;
        public String monitoredResource_ = "";
        public Internal.ProtobufList<String> metrics_ = GeneratedMessageLite.emptyProtobufList();

        static {
            MonitoringDestination monitoringDestination = new MonitoringDestination();
            DEFAULT_INSTANCE = monitoringDestination;
            GeneratedMessageLite.defaultInstanceMap.put(MonitoringDestination.class, monitoringDestination);
        }

        public static /* synthetic */ void access$600(MonitoringDestination monitoringDestination, Iterable iterable) {
            monitoringDestination.ensureMetricsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, (List) monitoringDestination.metrics_);
        }

        private void addAllMetrics(Iterable<String> iterable) {
            ensureMetricsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.metrics_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addMetrics(String str) {
            str.getClass();
            ensureMetricsIsMutable();
            this.metrics_.add(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addMetricsBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            ensureMetricsIsMutable();
            this.metrics_.add(byteString.toStringUtf8());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMetrics() {
            this.metrics_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMonitoredResource() {
            this.monitoredResource_ = DEFAULT_INSTANCE.getMonitoredResource();
        }

        private void ensureMetricsIsMutable() {
            Internal.ProtobufList<String> protobufList = this.metrics_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.metrics_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        public static MonitoringDestination getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static MonitoringDestination parseDelimitedFrom(InputStream inputStream) {
            return (MonitoringDestination) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MonitoringDestination parseFrom(ByteBuffer byteBuffer) {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<MonitoringDestination> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMetrics(int i, String str) {
            str.getClass();
            ensureMetricsIsMutable();
            this.metrics_.set(i, str);
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002Ț", new Object[]{"monitoredResource_", "metrics_"});
                case NEW_MUTABLE_INSTANCE:
                    return new MonitoringDestination();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<MonitoringDestination> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (MonitoringDestination.class) {
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

        @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
        public String getMetrics(int i) {
            return this.metrics_.get(i);
        }

        @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
        public ByteString getMetricsBytes(int i) {
            return ByteString.copyFromUtf8(this.metrics_.get(i));
        }

        @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
        public int getMetricsCount() {
            return this.metrics_.size();
        }

        @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
        public List<String> getMetricsList() {
            return this.metrics_;
        }

        @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
        public String getMonitoredResource() {
            return this.monitoredResource_;
        }

        @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
        public ByteString getMonitoredResourceBytes() {
            return ByteString.copyFromUtf8(this.monitoredResource_);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MonitoringDestination, Builder> implements MonitoringDestinationOrBuilder {
            public Builder() {
                super(MonitoringDestination.DEFAULT_INSTANCE);
            }

            public Builder addAllMetrics(Iterable<String> iterable) {
                copyOnWrite();
                MonitoringDestination.access$600((MonitoringDestination) this.instance, iterable);
                return this;
            }

            public Builder addMetrics(String str) {
                copyOnWrite();
                ((MonitoringDestination) this.instance).addMetrics(str);
                return this;
            }

            public Builder addMetricsBytes(ByteString byteString) {
                copyOnWrite();
                ((MonitoringDestination) this.instance).addMetricsBytes(byteString);
                return this;
            }

            public Builder clearMetrics() {
                copyOnWrite();
                ((MonitoringDestination) this.instance).clearMetrics();
                return this;
            }

            public Builder clearMonitoredResource() {
                copyOnWrite();
                ((MonitoringDestination) this.instance).clearMonitoredResource();
                return this;
            }

            @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
            public String getMetrics(int i) {
                return ((MonitoringDestination) this.instance).getMetrics(i);
            }

            @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
            public ByteString getMetricsBytes(int i) {
                return ((MonitoringDestination) this.instance).getMetricsBytes(i);
            }

            @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
            public int getMetricsCount() {
                return ((MonitoringDestination) this.instance).getMetricsCount();
            }

            @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
            public List<String> getMetricsList() {
                return Collections.unmodifiableList(((MonitoringDestination) this.instance).getMetricsList());
            }

            @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
            public String getMonitoredResource() {
                return ((MonitoringDestination) this.instance).getMonitoredResource();
            }

            @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
            public ByteString getMonitoredResourceBytes() {
                return ((MonitoringDestination) this.instance).getMonitoredResourceBytes();
            }

            public Builder setMetrics(int i, String str) {
                copyOnWrite();
                ((MonitoringDestination) this.instance).setMetrics(i, str);
                return this;
            }

            public Builder setMonitoredResource(String str) {
                copyOnWrite();
                ((MonitoringDestination) this.instance).setMonitoredResource(str);
                return this;
            }

            public Builder setMonitoredResourceBytes(ByteString byteString) {
                copyOnWrite();
                ((MonitoringDestination) this.instance).setMonitoredResourceBytes(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(MonitoringDestination.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(MonitoringDestination monitoringDestination) {
            return DEFAULT_INSTANCE.createBuilder(monitoringDestination);
        }

        public static MonitoringDestination parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MonitoringDestination) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static MonitoringDestination parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static MonitoringDestination parseFrom(ByteString byteString) {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static MonitoringDestination parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static MonitoringDestination parseFrom(byte[] bArr) {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static MonitoringDestination parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static MonitoringDestination parseFrom(InputStream inputStream) {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MonitoringDestination parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static MonitoringDestination parseFrom(CodedInputStream codedInputStream) {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static MonitoringDestination parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface MonitoringDestinationOrBuilder extends MessageLiteOrBuilder {
        String getMetrics(int i);

        ByteString getMetricsBytes(int i);

        int getMetricsCount();

        List<String> getMetricsList();

        String getMonitoredResource();

        ByteString getMonitoredResourceBytes();
    }

    static {
        Monitoring monitoring = new Monitoring();
        DEFAULT_INSTANCE = monitoring;
        GeneratedMessageLite.defaultInstanceMap.put(Monitoring.class, monitoring);
    }

    public static /* synthetic */ void access$1400(Monitoring monitoring, Iterable iterable) {
        monitoring.ensureProducerDestinationsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) monitoring.producerDestinations_);
    }

    public static /* synthetic */ void access$1600(Monitoring monitoring, int i) {
        monitoring.ensureProducerDestinationsIsMutable();
        monitoring.producerDestinations_.remove(i);
    }

    public static /* synthetic */ void access$2000(Monitoring monitoring, Iterable iterable) {
        monitoring.ensureConsumerDestinationsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) monitoring.consumerDestinations_);
    }

    public static /* synthetic */ void access$2200(Monitoring monitoring, int i) {
        monitoring.ensureConsumerDestinationsIsMutable();
        monitoring.consumerDestinations_.remove(i);
    }

    private void addAllConsumerDestinations(Iterable<? extends MonitoringDestination> iterable) {
        ensureConsumerDestinationsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.consumerDestinations_);
    }

    private void addAllProducerDestinations(Iterable<? extends MonitoringDestination> iterable) {
        ensureProducerDestinationsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.producerDestinations_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addConsumerDestinations(MonitoringDestination monitoringDestination) {
        monitoringDestination.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.add(monitoringDestination);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProducerDestinations(MonitoringDestination monitoringDestination) {
        monitoringDestination.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.add(monitoringDestination);
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
        Internal.ProtobufList<MonitoringDestination> protobufList = this.consumerDestinations_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.consumerDestinations_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureProducerDestinationsIsMutable() {
        Internal.ProtobufList<MonitoringDestination> protobufList = this.producerDestinations_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.producerDestinations_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static Monitoring getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Monitoring parseDelimitedFrom(InputStream inputStream) {
        return (Monitoring) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Monitoring parseFrom(ByteBuffer byteBuffer) {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Monitoring> parser() {
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
    public void setConsumerDestinations(int i, MonitoringDestination monitoringDestination) {
        monitoringDestination.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.set(i, monitoringDestination);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProducerDestinations(int i, MonitoringDestination monitoringDestination) {
        monitoringDestination.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.set(i, monitoringDestination);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b", new Object[]{"producerDestinations_", MonitoringDestination.class, "consumerDestinations_", MonitoringDestination.class});
            case NEW_MUTABLE_INSTANCE:
                return new Monitoring();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Monitoring> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Monitoring.class) {
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

    @Override // com.google.api.MonitoringOrBuilder
    public MonitoringDestination getConsumerDestinations(int i) {
        return this.consumerDestinations_.get(i);
    }

    @Override // com.google.api.MonitoringOrBuilder
    public int getConsumerDestinationsCount() {
        return this.consumerDestinations_.size();
    }

    @Override // com.google.api.MonitoringOrBuilder
    public List<MonitoringDestination> getConsumerDestinationsList() {
        return this.consumerDestinations_;
    }

    public MonitoringDestinationOrBuilder getConsumerDestinationsOrBuilder(int i) {
        return this.consumerDestinations_.get(i);
    }

    public List<? extends MonitoringDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
        return this.consumerDestinations_;
    }

    @Override // com.google.api.MonitoringOrBuilder
    public MonitoringDestination getProducerDestinations(int i) {
        return this.producerDestinations_.get(i);
    }

    @Override // com.google.api.MonitoringOrBuilder
    public int getProducerDestinationsCount() {
        return this.producerDestinations_.size();
    }

    @Override // com.google.api.MonitoringOrBuilder
    public List<MonitoringDestination> getProducerDestinationsList() {
        return this.producerDestinations_;
    }

    public MonitoringDestinationOrBuilder getProducerDestinationsOrBuilder(int i) {
        return this.producerDestinations_.get(i);
    }

    public List<? extends MonitoringDestinationOrBuilder> getProducerDestinationsOrBuilderList() {
        return this.producerDestinations_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Monitoring, Builder> implements MonitoringOrBuilder {
        public Builder() {
            super(Monitoring.DEFAULT_INSTANCE);
        }

        public Builder addAllConsumerDestinations(Iterable<? extends MonitoringDestination> iterable) {
            copyOnWrite();
            Monitoring.access$2000((Monitoring) this.instance, iterable);
            return this;
        }

        public Builder addAllProducerDestinations(Iterable<? extends MonitoringDestination> iterable) {
            copyOnWrite();
            Monitoring.access$1400((Monitoring) this.instance, iterable);
            return this;
        }

        public Builder addConsumerDestinations(MonitoringDestination monitoringDestination) {
            copyOnWrite();
            ((Monitoring) this.instance).addConsumerDestinations(monitoringDestination);
            return this;
        }

        public Builder addProducerDestinations(MonitoringDestination monitoringDestination) {
            copyOnWrite();
            ((Monitoring) this.instance).addProducerDestinations(monitoringDestination);
            return this;
        }

        public Builder clearConsumerDestinations() {
            copyOnWrite();
            ((Monitoring) this.instance).clearConsumerDestinations();
            return this;
        }

        public Builder clearProducerDestinations() {
            copyOnWrite();
            ((Monitoring) this.instance).clearProducerDestinations();
            return this;
        }

        @Override // com.google.api.MonitoringOrBuilder
        public MonitoringDestination getConsumerDestinations(int i) {
            return ((Monitoring) this.instance).getConsumerDestinations(i);
        }

        @Override // com.google.api.MonitoringOrBuilder
        public int getConsumerDestinationsCount() {
            return ((Monitoring) this.instance).getConsumerDestinationsCount();
        }

        @Override // com.google.api.MonitoringOrBuilder
        public List<MonitoringDestination> getConsumerDestinationsList() {
            return Collections.unmodifiableList(((Monitoring) this.instance).getConsumerDestinationsList());
        }

        @Override // com.google.api.MonitoringOrBuilder
        public MonitoringDestination getProducerDestinations(int i) {
            return ((Monitoring) this.instance).getProducerDestinations(i);
        }

        @Override // com.google.api.MonitoringOrBuilder
        public int getProducerDestinationsCount() {
            return ((Monitoring) this.instance).getProducerDestinationsCount();
        }

        @Override // com.google.api.MonitoringOrBuilder
        public List<MonitoringDestination> getProducerDestinationsList() {
            return Collections.unmodifiableList(((Monitoring) this.instance).getProducerDestinationsList());
        }

        public Builder removeConsumerDestinations(int i) {
            copyOnWrite();
            Monitoring.access$2200((Monitoring) this.instance, i);
            return this;
        }

        public Builder removeProducerDestinations(int i) {
            copyOnWrite();
            Monitoring.access$1600((Monitoring) this.instance, i);
            return this;
        }

        public Builder setConsumerDestinations(int i, MonitoringDestination monitoringDestination) {
            copyOnWrite();
            ((Monitoring) this.instance).setConsumerDestinations(i, monitoringDestination);
            return this;
        }

        public Builder setProducerDestinations(int i, MonitoringDestination monitoringDestination) {
            copyOnWrite();
            ((Monitoring) this.instance).setProducerDestinations(i, monitoringDestination);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Monitoring.DEFAULT_INSTANCE);
        }

        public Builder addConsumerDestinations(int i, MonitoringDestination monitoringDestination) {
            copyOnWrite();
            ((Monitoring) this.instance).addConsumerDestinations(i, monitoringDestination);
            return this;
        }

        public Builder addProducerDestinations(int i, MonitoringDestination monitoringDestination) {
            copyOnWrite();
            ((Monitoring) this.instance).addProducerDestinations(i, monitoringDestination);
            return this;
        }

        public Builder setConsumerDestinations(int i, MonitoringDestination.Builder builder) {
            copyOnWrite();
            ((Monitoring) this.instance).setConsumerDestinations(i, builder.build());
            return this;
        }

        public Builder setProducerDestinations(int i, MonitoringDestination.Builder builder) {
            copyOnWrite();
            ((Monitoring) this.instance).setProducerDestinations(i, builder.build());
            return this;
        }

        public Builder addConsumerDestinations(MonitoringDestination.Builder builder) {
            copyOnWrite();
            ((Monitoring) this.instance).addConsumerDestinations(builder.build());
            return this;
        }

        public Builder addProducerDestinations(MonitoringDestination.Builder builder) {
            copyOnWrite();
            ((Monitoring) this.instance).addProducerDestinations(builder.build());
            return this;
        }

        public Builder addConsumerDestinations(int i, MonitoringDestination.Builder builder) {
            copyOnWrite();
            ((Monitoring) this.instance).addConsumerDestinations(i, builder.build());
            return this;
        }

        public Builder addProducerDestinations(int i, MonitoringDestination.Builder builder) {
            copyOnWrite();
            ((Monitoring) this.instance).addProducerDestinations(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Monitoring monitoring) {
        return DEFAULT_INSTANCE.createBuilder(monitoring);
    }

    public static Monitoring parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Monitoring) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Monitoring parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Monitoring parseFrom(ByteString byteString) {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addConsumerDestinations(int i, MonitoringDestination monitoringDestination) {
        monitoringDestination.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.add(i, monitoringDestination);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProducerDestinations(int i, MonitoringDestination monitoringDestination) {
        monitoringDestination.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.add(i, monitoringDestination);
    }

    public static Monitoring parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Monitoring parseFrom(byte[] bArr) {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Monitoring parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Monitoring parseFrom(InputStream inputStream) {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Monitoring parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Monitoring parseFrom(CodedInputStream codedInputStream) {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Monitoring parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
