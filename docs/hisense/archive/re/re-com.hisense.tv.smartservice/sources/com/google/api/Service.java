package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Api;
import com.google.protobuf.ApiOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Enum;
import com.google.protobuf.EnumOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import com.google.protobuf.Type;
import com.google.protobuf.TypeOrBuilder;
import com.google.protobuf.UInt32Value;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Service extends GeneratedMessageLite<Service, Builder> implements ServiceOrBuilder {
    public static final int APIS_FIELD_NUMBER = 3;
    public static final int AUTHENTICATION_FIELD_NUMBER = 11;
    public static final int BACKEND_FIELD_NUMBER = 8;
    public static final int BILLING_FIELD_NUMBER = 26;
    public static final int CONFIG_VERSION_FIELD_NUMBER = 20;
    public static final int CONTEXT_FIELD_NUMBER = 12;
    public static final int CONTROL_FIELD_NUMBER = 21;
    public static final Service DEFAULT_INSTANCE;
    public static final int DOCUMENTATION_FIELD_NUMBER = 6;
    public static final int ENDPOINTS_FIELD_NUMBER = 18;
    public static final int ENUMS_FIELD_NUMBER = 5;
    public static final int HTTP_FIELD_NUMBER = 9;
    public static final int ID_FIELD_NUMBER = 33;
    public static final int LOGGING_FIELD_NUMBER = 27;
    public static final int LOGS_FIELD_NUMBER = 23;
    public static final int METRICS_FIELD_NUMBER = 24;
    public static final int MONITORED_RESOURCES_FIELD_NUMBER = 25;
    public static final int MONITORING_FIELD_NUMBER = 28;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<Service> PARSER = null;
    public static final int PRODUCER_PROJECT_ID_FIELD_NUMBER = 22;
    public static final int QUOTA_FIELD_NUMBER = 10;
    public static final int SOURCE_INFO_FIELD_NUMBER = 37;
    public static final int SYSTEM_PARAMETERS_FIELD_NUMBER = 29;
    public static final int TITLE_FIELD_NUMBER = 2;
    public static final int TYPES_FIELD_NUMBER = 4;
    public static final int USAGE_FIELD_NUMBER = 15;
    public Authentication authentication_;
    public Backend backend_;
    public Billing billing_;
    public UInt32Value configVersion_;
    public Context context_;
    public Control control_;
    public Documentation documentation_;
    public Http http_;
    public Logging logging_;
    public Monitoring monitoring_;
    public Quota quota_;
    public SourceInfo sourceInfo_;
    public SystemParameters systemParameters_;
    public Usage usage_;
    public String name_ = "";
    public String id_ = "";
    public String title_ = "";
    public String producerProjectId_ = "";
    public Internal.ProtobufList<Api> apis_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<Type> types_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<Enum> enums_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<Endpoint> endpoints_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<LogDescriptor> logs_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<MetricDescriptor> metrics_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<MonitoredResourceDescriptor> monitoredResources_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.Service$1, reason: invalid class name */
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
        Service service = new Service();
        DEFAULT_INSTANCE = service;
        GeneratedMessageLite.defaultInstanceMap.put(Service.class, service);
    }

    public static /* synthetic */ void access$1900(Service service, Iterable iterable) {
        service.ensureApisIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) service.apis_);
    }

    public static /* synthetic */ void access$2100(Service service, int i) {
        service.ensureApisIsMutable();
        service.apis_.remove(i);
    }

    public static /* synthetic */ void access$2500(Service service, Iterable iterable) {
        service.ensureTypesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) service.types_);
    }

    public static /* synthetic */ void access$2700(Service service, int i) {
        service.ensureTypesIsMutable();
        service.types_.remove(i);
    }

    public static /* synthetic */ void access$3100(Service service, Iterable iterable) {
        service.ensureEnumsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) service.enums_);
    }

    public static /* synthetic */ void access$3300(Service service, int i) {
        service.ensureEnumsIsMutable();
        service.enums_.remove(i);
    }

    public static /* synthetic */ void access$5800(Service service, Iterable iterable) {
        service.ensureEndpointsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) service.endpoints_);
    }

    public static /* synthetic */ void access$6000(Service service, int i) {
        service.ensureEndpointsIsMutable();
        service.endpoints_.remove(i);
    }

    public static /* synthetic */ void access$6700(Service service, Iterable iterable) {
        service.ensureLogsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) service.logs_);
    }

    public static /* synthetic */ void access$6900(Service service, int i) {
        service.ensureLogsIsMutable();
        service.logs_.remove(i);
    }

    public static /* synthetic */ void access$7300(Service service, Iterable iterable) {
        service.ensureMetricsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) service.metrics_);
    }

    public static /* synthetic */ void access$7500(Service service, int i) {
        service.ensureMetricsIsMutable();
        service.metrics_.remove(i);
    }

    public static /* synthetic */ void access$7900(Service service, Iterable iterable) {
        service.ensureMonitoredResourcesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) service.monitoredResources_);
    }

    public static /* synthetic */ void access$8100(Service service, int i) {
        service.ensureMonitoredResourcesIsMutable();
        service.monitoredResources_.remove(i);
    }

    private void addAllApis(Iterable<? extends Api> iterable) {
        ensureApisIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.apis_);
    }

    private void addAllEndpoints(Iterable<? extends Endpoint> iterable) {
        ensureEndpointsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.endpoints_);
    }

    private void addAllEnums(Iterable<? extends Enum> iterable) {
        ensureEnumsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.enums_);
    }

    private void addAllLogs(Iterable<? extends LogDescriptor> iterable) {
        ensureLogsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.logs_);
    }

    private void addAllMetrics(Iterable<? extends MetricDescriptor> iterable) {
        ensureMetricsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.metrics_);
    }

    private void addAllMonitoredResources(Iterable<? extends MonitoredResourceDescriptor> iterable) {
        ensureMonitoredResourcesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.monitoredResources_);
    }

    private void addAllTypes(Iterable<? extends Type> iterable) {
        ensureTypesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.types_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addApis(Api api) {
        api.getClass();
        ensureApisIsMutable();
        this.apis_.add(api);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addEndpoints(Endpoint endpoint) {
        endpoint.getClass();
        ensureEndpointsIsMutable();
        this.endpoints_.add(endpoint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addEnums(Enum r2) {
        r2.getClass();
        ensureEnumsIsMutable();
        this.enums_.add(r2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLogs(LogDescriptor logDescriptor) {
        logDescriptor.getClass();
        ensureLogsIsMutable();
        this.logs_.add(logDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMetrics(MetricDescriptor metricDescriptor) {
        metricDescriptor.getClass();
        ensureMetricsIsMutable();
        this.metrics_.add(metricDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMonitoredResources(MonitoredResourceDescriptor monitoredResourceDescriptor) {
        monitoredResourceDescriptor.getClass();
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.add(monitoredResourceDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTypes(Type type) {
        type.getClass();
        ensureTypesIsMutable();
        this.types_.add(type);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearApis() {
        this.apis_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearAuthentication() {
        this.authentication_ = null;
    }

    private void clearBackend() {
        this.backend_ = null;
    }

    private void clearBilling() {
        this.billing_ = null;
    }

    private void clearConfigVersion() {
        this.configVersion_ = null;
    }

    private void clearContext() {
        this.context_ = null;
    }

    private void clearControl() {
        this.control_ = null;
    }

    private void clearDocumentation() {
        this.documentation_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearEndpoints() {
        this.endpoints_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearEnums() {
        this.enums_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearHttp() {
        this.http_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearId() {
        this.id_ = DEFAULT_INSTANCE.getId();
    }

    private void clearLogging() {
        this.logging_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLogs() {
        this.logs_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMetrics() {
        this.metrics_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMonitoredResources() {
        this.monitoredResources_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearMonitoring() {
        this.monitoring_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearProducerProjectId() {
        this.producerProjectId_ = DEFAULT_INSTANCE.getProducerProjectId();
    }

    private void clearQuota() {
        this.quota_ = null;
    }

    private void clearSourceInfo() {
        this.sourceInfo_ = null;
    }

    private void clearSystemParameters() {
        this.systemParameters_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTitle() {
        this.title_ = DEFAULT_INSTANCE.getTitle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTypes() {
        this.types_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearUsage() {
        this.usage_ = null;
    }

    private void ensureApisIsMutable() {
        Internal.ProtobufList<Api> protobufList = this.apis_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.apis_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureEndpointsIsMutable() {
        Internal.ProtobufList<Endpoint> protobufList = this.endpoints_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.endpoints_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureEnumsIsMutable() {
        Internal.ProtobufList<Enum> protobufList = this.enums_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.enums_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureLogsIsMutable() {
        Internal.ProtobufList<LogDescriptor> protobufList = this.logs_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.logs_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureMetricsIsMutable() {
        Internal.ProtobufList<MetricDescriptor> protobufList = this.metrics_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.metrics_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureMonitoredResourcesIsMutable() {
        Internal.ProtobufList<MonitoredResourceDescriptor> protobufList = this.monitoredResources_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.monitoredResources_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureTypesIsMutable() {
        Internal.ProtobufList<Type> protobufList = this.types_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.types_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static Service getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeAuthentication(Authentication authentication) {
        authentication.getClass();
        Authentication authentication2 = this.authentication_;
        if (authentication2 == null || authentication2 == Authentication.DEFAULT_INSTANCE) {
            this.authentication_ = authentication;
        } else {
            this.authentication_ = Authentication.newBuilder(authentication2).mergeFrom(authentication).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeBackend(Backend backend) {
        backend.getClass();
        Backend backend2 = this.backend_;
        if (backend2 == null || backend2 == Backend.DEFAULT_INSTANCE) {
            this.backend_ = backend;
        } else {
            this.backend_ = Backend.newBuilder(backend2).mergeFrom(backend).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeBilling(Billing billing) {
        billing.getClass();
        Billing billing2 = this.billing_;
        if (billing2 == null || billing2 == Billing.DEFAULT_INSTANCE) {
            this.billing_ = billing;
        } else {
            this.billing_ = Billing.newBuilder(billing2).mergeFrom(billing).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeConfigVersion(UInt32Value uInt32Value) {
        uInt32Value.getClass();
        UInt32Value uInt32Value2 = this.configVersion_;
        if (uInt32Value2 == null || uInt32Value2 == UInt32Value.DEFAULT_INSTANCE) {
            this.configVersion_ = uInt32Value;
        } else {
            this.configVersion_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeContext(Context context) {
        context.getClass();
        Context context2 = this.context_;
        if (context2 == null || context2 == Context.DEFAULT_INSTANCE) {
            this.context_ = context;
        } else {
            this.context_ = Context.newBuilder(context2).mergeFrom(context).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeControl(Control control) {
        control.getClass();
        Control control2 = this.control_;
        if (control2 == null || control2 == Control.DEFAULT_INSTANCE) {
            this.control_ = control;
        } else {
            this.control_ = Control.newBuilder(control2).mergeFrom(control).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentation(Documentation documentation) {
        documentation.getClass();
        Documentation documentation2 = this.documentation_;
        if (documentation2 == null || documentation2 == Documentation.DEFAULT_INSTANCE) {
            this.documentation_ = documentation;
        } else {
            this.documentation_ = Documentation.newBuilder(documentation2).mergeFrom(documentation).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeHttp(Http http) {
        http.getClass();
        Http http2 = this.http_;
        if (http2 == null || http2 == Http.DEFAULT_INSTANCE) {
            this.http_ = http;
        } else {
            this.http_ = Http.newBuilder(http2).mergeFrom(http).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLogging(Logging logging) {
        logging.getClass();
        Logging logging2 = this.logging_;
        if (logging2 == null || logging2 == Logging.DEFAULT_INSTANCE) {
            this.logging_ = logging;
        } else {
            this.logging_ = Logging.newBuilder(logging2).mergeFrom(logging).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeMonitoring(Monitoring monitoring) {
        monitoring.getClass();
        Monitoring monitoring2 = this.monitoring_;
        if (monitoring2 == null || monitoring2 == Monitoring.DEFAULT_INSTANCE) {
            this.monitoring_ = monitoring;
        } else {
            this.monitoring_ = Monitoring.newBuilder(monitoring2).mergeFrom(monitoring).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeQuota(Quota quota) {
        quota.getClass();
        Quota quota2 = this.quota_;
        if (quota2 == null || quota2 == Quota.DEFAULT_INSTANCE) {
            this.quota_ = quota;
        } else {
            this.quota_ = Quota.newBuilder(quota2).mergeFrom(quota).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeSourceInfo(SourceInfo sourceInfo) {
        sourceInfo.getClass();
        SourceInfo sourceInfo2 = this.sourceInfo_;
        if (sourceInfo2 == null || sourceInfo2 == SourceInfo.DEFAULT_INSTANCE) {
            this.sourceInfo_ = sourceInfo;
        } else {
            this.sourceInfo_ = SourceInfo.newBuilder(sourceInfo2).mergeFrom(sourceInfo).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeSystemParameters(SystemParameters systemParameters) {
        systemParameters.getClass();
        SystemParameters systemParameters2 = this.systemParameters_;
        if (systemParameters2 == null || systemParameters2 == SystemParameters.DEFAULT_INSTANCE) {
            this.systemParameters_ = systemParameters;
        } else {
            this.systemParameters_ = SystemParameters.newBuilder(systemParameters2).mergeFrom(systemParameters).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUsage(Usage usage) {
        usage.getClass();
        Usage usage2 = this.usage_;
        if (usage2 == null || usage2 == Usage.DEFAULT_INSTANCE) {
            this.usage_ = usage;
        } else {
            this.usage_ = Usage.newBuilder(usage2).mergeFrom(usage).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Service parseDelimitedFrom(InputStream inputStream) {
        return (Service) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Service parseFrom(ByteBuffer byteBuffer) {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Service> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeApis(int i) {
        ensureApisIsMutable();
        this.apis_.remove(i);
    }

    private void removeEndpoints(int i) {
        ensureEndpointsIsMutable();
        this.endpoints_.remove(i);
    }

    private void removeEnums(int i) {
        ensureEnumsIsMutable();
        this.enums_.remove(i);
    }

    private void removeLogs(int i) {
        ensureLogsIsMutable();
        this.logs_.remove(i);
    }

    private void removeMetrics(int i) {
        ensureMetricsIsMutable();
        this.metrics_.remove(i);
    }

    private void removeMonitoredResources(int i) {
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.remove(i);
    }

    private void removeTypes(int i) {
        ensureTypesIsMutable();
        this.types_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setApis(int i, Api api) {
        api.getClass();
        ensureApisIsMutable();
        this.apis_.set(i, api);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAuthentication(Authentication authentication) {
        authentication.getClass();
        this.authentication_ = authentication;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBackend(Backend backend) {
        backend.getClass();
        this.backend_ = backend;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBilling(Billing billing) {
        billing.getClass();
        this.billing_ = billing;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConfigVersion(UInt32Value uInt32Value) {
        uInt32Value.getClass();
        this.configVersion_ = uInt32Value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContext(Context context) {
        context.getClass();
        this.context_ = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setControl(Control control) {
        control.getClass();
        this.control_ = control;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentation(Documentation documentation) {
        documentation.getClass();
        this.documentation_ = documentation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEndpoints(int i, Endpoint endpoint) {
        endpoint.getClass();
        ensureEndpointsIsMutable();
        this.endpoints_.set(i, endpoint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEnums(int i, Enum r3) {
        r3.getClass();
        ensureEnumsIsMutable();
        this.enums_.set(i, r3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHttp(Http http) {
        http.getClass();
        this.http_ = http;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setId(String str) {
        str.getClass();
        this.id_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIdBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.id_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLogging(Logging logging) {
        logging.getClass();
        this.logging_ = logging;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLogs(int i, LogDescriptor logDescriptor) {
        logDescriptor.getClass();
        ensureLogsIsMutable();
        this.logs_.set(i, logDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetrics(int i, MetricDescriptor metricDescriptor) {
        metricDescriptor.getClass();
        ensureMetricsIsMutable();
        this.metrics_.set(i, metricDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMonitoredResources(int i, MonitoredResourceDescriptor monitoredResourceDescriptor) {
        monitoredResourceDescriptor.getClass();
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.set(i, monitoredResourceDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMonitoring(Monitoring monitoring) {
        monitoring.getClass();
        this.monitoring_ = monitoring;
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
    public void setProducerProjectId(String str) {
        str.getClass();
        this.producerProjectId_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProducerProjectIdBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.producerProjectId_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQuota(Quota quota) {
        quota.getClass();
        this.quota_ = quota;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSourceInfo(SourceInfo sourceInfo) {
        sourceInfo.getClass();
        this.sourceInfo_ = sourceInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemParameters(SystemParameters systemParameters) {
        systemParameters.getClass();
        this.systemParameters_ = systemParameters;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTitle(String str) {
        str.getClass();
        this.title_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTitleBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.title_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTypes(int i, Type type) {
        type.getClass();
        ensureTypesIsMutable();
        this.types_.set(i, type);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUsage(Usage usage) {
        usage.getClass();
        this.usage_ = usage;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0019\u0000\u0000\u0001%\u0019\u0000\u0007\u0000\u0001Ȉ\u0002Ȉ\u0003\u001b\u0004\u001b\u0005\u001b\u0006\t\b\t\t\t\n\t\u000b\t\f\t\u000f\t\u0012\u001b\u0014\t\u0015\t\u0016Ȉ\u0017\u001b\u0018\u001b\u0019\u001b\u001a\t\u001b\t\u001c\t\u001d\t!Ȉ%\t", new Object[]{"name_", "title_", "apis_", Api.class, "types_", Type.class, "enums_", Enum.class, "documentation_", "backend_", "http_", "quota_", "authentication_", "context_", "usage_", "endpoints_", Endpoint.class, "configVersion_", "control_", "producerProjectId_", "logs_", LogDescriptor.class, "metrics_", MetricDescriptor.class, "monitoredResources_", MonitoredResourceDescriptor.class, "billing_", "logging_", "monitoring_", "systemParameters_", "id_", "sourceInfo_"});
            case NEW_MUTABLE_INSTANCE:
                return new Service();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Service> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Service.class) {
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

    @Override // com.google.api.ServiceOrBuilder
    public Api getApis(int i) {
        return this.apis_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getApisCount() {
        return this.apis_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<Api> getApisList() {
        return this.apis_;
    }

    public ApiOrBuilder getApisOrBuilder(int i) {
        return this.apis_.get(i);
    }

    public List<? extends ApiOrBuilder> getApisOrBuilderList() {
        return this.apis_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Authentication getAuthentication() {
        Authentication authentication = this.authentication_;
        return authentication == null ? Authentication.DEFAULT_INSTANCE : authentication;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Backend getBackend() {
        Backend backend = this.backend_;
        return backend == null ? Backend.DEFAULT_INSTANCE : backend;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Billing getBilling() {
        Billing billing = this.billing_;
        return billing == null ? Billing.DEFAULT_INSTANCE : billing;
    }

    @Override // com.google.api.ServiceOrBuilder
    public UInt32Value getConfigVersion() {
        UInt32Value uInt32Value = this.configVersion_;
        return uInt32Value == null ? UInt32Value.DEFAULT_INSTANCE : uInt32Value;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Context getContext() {
        Context context = this.context_;
        return context == null ? Context.DEFAULT_INSTANCE : context;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Control getControl() {
        Control control = this.control_;
        return control == null ? Control.DEFAULT_INSTANCE : control;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Documentation getDocumentation() {
        Documentation documentation = this.documentation_;
        return documentation == null ? Documentation.DEFAULT_INSTANCE : documentation;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Endpoint getEndpoints(int i) {
        return this.endpoints_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getEndpointsCount() {
        return this.endpoints_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<Endpoint> getEndpointsList() {
        return this.endpoints_;
    }

    public EndpointOrBuilder getEndpointsOrBuilder(int i) {
        return this.endpoints_.get(i);
    }

    public List<? extends EndpointOrBuilder> getEndpointsOrBuilderList() {
        return this.endpoints_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Enum getEnums(int i) {
        return this.enums_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getEnumsCount() {
        return this.enums_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<Enum> getEnumsList() {
        return this.enums_;
    }

    public EnumOrBuilder getEnumsOrBuilder(int i) {
        return this.enums_.get(i);
    }

    public List<? extends EnumOrBuilder> getEnumsOrBuilderList() {
        return this.enums_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Http getHttp() {
        Http http = this.http_;
        return http == null ? Http.DEFAULT_INSTANCE : http;
    }

    @Override // com.google.api.ServiceOrBuilder
    public String getId() {
        return this.id_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public ByteString getIdBytes() {
        return ByteString.copyFromUtf8(this.id_);
    }

    @Override // com.google.api.ServiceOrBuilder
    public Logging getLogging() {
        Logging logging = this.logging_;
        return logging == null ? Logging.DEFAULT_INSTANCE : logging;
    }

    @Override // com.google.api.ServiceOrBuilder
    public LogDescriptor getLogs(int i) {
        return this.logs_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getLogsCount() {
        return this.logs_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<LogDescriptor> getLogsList() {
        return this.logs_;
    }

    public LogDescriptorOrBuilder getLogsOrBuilder(int i) {
        return this.logs_.get(i);
    }

    public List<? extends LogDescriptorOrBuilder> getLogsOrBuilderList() {
        return this.logs_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public MetricDescriptor getMetrics(int i) {
        return this.metrics_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getMetricsCount() {
        return this.metrics_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<MetricDescriptor> getMetricsList() {
        return this.metrics_;
    }

    public MetricDescriptorOrBuilder getMetricsOrBuilder(int i) {
        return this.metrics_.get(i);
    }

    public List<? extends MetricDescriptorOrBuilder> getMetricsOrBuilderList() {
        return this.metrics_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public MonitoredResourceDescriptor getMonitoredResources(int i) {
        return this.monitoredResources_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getMonitoredResourcesCount() {
        return this.monitoredResources_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
        return this.monitoredResources_;
    }

    public MonitoredResourceDescriptorOrBuilder getMonitoredResourcesOrBuilder(int i) {
        return this.monitoredResources_.get(i);
    }

    public List<? extends MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesOrBuilderList() {
        return this.monitoredResources_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Monitoring getMonitoring() {
        Monitoring monitoring = this.monitoring_;
        return monitoring == null ? Monitoring.DEFAULT_INSTANCE : monitoring;
    }

    @Override // com.google.api.ServiceOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.api.ServiceOrBuilder
    public String getProducerProjectId() {
        return this.producerProjectId_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public ByteString getProducerProjectIdBytes() {
        return ByteString.copyFromUtf8(this.producerProjectId_);
    }

    @Override // com.google.api.ServiceOrBuilder
    public Quota getQuota() {
        Quota quota = this.quota_;
        return quota == null ? Quota.DEFAULT_INSTANCE : quota;
    }

    @Override // com.google.api.ServiceOrBuilder
    public SourceInfo getSourceInfo() {
        SourceInfo sourceInfo = this.sourceInfo_;
        return sourceInfo == null ? SourceInfo.DEFAULT_INSTANCE : sourceInfo;
    }

    @Override // com.google.api.ServiceOrBuilder
    public SystemParameters getSystemParameters() {
        SystemParameters systemParameters = this.systemParameters_;
        return systemParameters == null ? SystemParameters.DEFAULT_INSTANCE : systemParameters;
    }

    @Override // com.google.api.ServiceOrBuilder
    public String getTitle() {
        return this.title_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public ByteString getTitleBytes() {
        return ByteString.copyFromUtf8(this.title_);
    }

    @Override // com.google.api.ServiceOrBuilder
    public Type getTypes(int i) {
        return this.types_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getTypesCount() {
        return this.types_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<Type> getTypesList() {
        return this.types_;
    }

    public TypeOrBuilder getTypesOrBuilder(int i) {
        return this.types_.get(i);
    }

    public List<? extends TypeOrBuilder> getTypesOrBuilderList() {
        return this.types_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Usage getUsage() {
        Usage usage = this.usage_;
        return usage == null ? Usage.DEFAULT_INSTANCE : usage;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasAuthentication() {
        return this.authentication_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasBackend() {
        return this.backend_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasBilling() {
        return this.billing_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasConfigVersion() {
        return this.configVersion_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasContext() {
        return this.context_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasControl() {
        return this.control_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasDocumentation() {
        return this.documentation_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasHttp() {
        return this.http_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasLogging() {
        return this.logging_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasMonitoring() {
        return this.monitoring_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasQuota() {
        return this.quota_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasSourceInfo() {
        return this.sourceInfo_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasSystemParameters() {
        return this.systemParameters_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasUsage() {
        return this.usage_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Service, Builder> implements ServiceOrBuilder {
        public Builder() {
            super(Service.DEFAULT_INSTANCE);
        }

        public Builder addAllApis(Iterable<? extends Api> iterable) {
            copyOnWrite();
            Service.access$1900((Service) this.instance, iterable);
            return this;
        }

        public Builder addAllEndpoints(Iterable<? extends Endpoint> iterable) {
            copyOnWrite();
            Service.access$5800((Service) this.instance, iterable);
            return this;
        }

        public Builder addAllEnums(Iterable<? extends Enum> iterable) {
            copyOnWrite();
            Service.access$3100((Service) this.instance, iterable);
            return this;
        }

        public Builder addAllLogs(Iterable<? extends LogDescriptor> iterable) {
            copyOnWrite();
            Service.access$6700((Service) this.instance, iterable);
            return this;
        }

        public Builder addAllMetrics(Iterable<? extends MetricDescriptor> iterable) {
            copyOnWrite();
            Service.access$7300((Service) this.instance, iterable);
            return this;
        }

        public Builder addAllMonitoredResources(Iterable<? extends MonitoredResourceDescriptor> iterable) {
            copyOnWrite();
            Service.access$7900((Service) this.instance, iterable);
            return this;
        }

        public Builder addAllTypes(Iterable<? extends Type> iterable) {
            copyOnWrite();
            Service.access$2500((Service) this.instance, iterable);
            return this;
        }

        public Builder addApis(Api api) {
            copyOnWrite();
            ((Service) this.instance).addApis(api);
            return this;
        }

        public Builder addEndpoints(Endpoint endpoint) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(endpoint);
            return this;
        }

        public Builder addEnums(Enum r2) {
            copyOnWrite();
            ((Service) this.instance).addEnums(r2);
            return this;
        }

        public Builder addLogs(LogDescriptor logDescriptor) {
            copyOnWrite();
            ((Service) this.instance).addLogs(logDescriptor);
            return this;
        }

        public Builder addMetrics(MetricDescriptor metricDescriptor) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(metricDescriptor);
            return this;
        }

        public Builder addMonitoredResources(MonitoredResourceDescriptor monitoredResourceDescriptor) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(monitoredResourceDescriptor);
            return this;
        }

        public Builder addTypes(Type type) {
            copyOnWrite();
            ((Service) this.instance).addTypes(type);
            return this;
        }

        public Builder clearApis() {
            copyOnWrite();
            ((Service) this.instance).clearApis();
            return this;
        }

        public Builder clearAuthentication() {
            copyOnWrite();
            ((Service) this.instance).authentication_ = null;
            return this;
        }

        public Builder clearBackend() {
            copyOnWrite();
            ((Service) this.instance).backend_ = null;
            return this;
        }

        public Builder clearBilling() {
            copyOnWrite();
            ((Service) this.instance).billing_ = null;
            return this;
        }

        public Builder clearConfigVersion() {
            copyOnWrite();
            ((Service) this.instance).configVersion_ = null;
            return this;
        }

        public Builder clearContext() {
            copyOnWrite();
            ((Service) this.instance).context_ = null;
            return this;
        }

        public Builder clearControl() {
            copyOnWrite();
            ((Service) this.instance).control_ = null;
            return this;
        }

        public Builder clearDocumentation() {
            copyOnWrite();
            ((Service) this.instance).documentation_ = null;
            return this;
        }

        public Builder clearEndpoints() {
            copyOnWrite();
            ((Service) this.instance).clearEndpoints();
            return this;
        }

        public Builder clearEnums() {
            copyOnWrite();
            ((Service) this.instance).clearEnums();
            return this;
        }

        public Builder clearHttp() {
            copyOnWrite();
            ((Service) this.instance).http_ = null;
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((Service) this.instance).clearId();
            return this;
        }

        public Builder clearLogging() {
            copyOnWrite();
            ((Service) this.instance).logging_ = null;
            return this;
        }

        public Builder clearLogs() {
            copyOnWrite();
            ((Service) this.instance).clearLogs();
            return this;
        }

        public Builder clearMetrics() {
            copyOnWrite();
            ((Service) this.instance).clearMetrics();
            return this;
        }

        public Builder clearMonitoredResources() {
            copyOnWrite();
            ((Service) this.instance).clearMonitoredResources();
            return this;
        }

        public Builder clearMonitoring() {
            copyOnWrite();
            ((Service) this.instance).monitoring_ = null;
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Service) this.instance).clearName();
            return this;
        }

        public Builder clearProducerProjectId() {
            copyOnWrite();
            ((Service) this.instance).clearProducerProjectId();
            return this;
        }

        public Builder clearQuota() {
            copyOnWrite();
            ((Service) this.instance).quota_ = null;
            return this;
        }

        public Builder clearSourceInfo() {
            copyOnWrite();
            ((Service) this.instance).sourceInfo_ = null;
            return this;
        }

        public Builder clearSystemParameters() {
            copyOnWrite();
            ((Service) this.instance).systemParameters_ = null;
            return this;
        }

        public Builder clearTitle() {
            copyOnWrite();
            ((Service) this.instance).clearTitle();
            return this;
        }

        public Builder clearTypes() {
            copyOnWrite();
            ((Service) this.instance).clearTypes();
            return this;
        }

        public Builder clearUsage() {
            copyOnWrite();
            ((Service) this.instance).usage_ = null;
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public Api getApis(int i) {
            return ((Service) this.instance).getApis(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getApisCount() {
            return ((Service) this.instance).getApisCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<Api> getApisList() {
            return Collections.unmodifiableList(((Service) this.instance).getApisList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public Authentication getAuthentication() {
            return ((Service) this.instance).getAuthentication();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Backend getBackend() {
            return ((Service) this.instance).getBackend();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Billing getBilling() {
            return ((Service) this.instance).getBilling();
        }

        @Override // com.google.api.ServiceOrBuilder
        public UInt32Value getConfigVersion() {
            return ((Service) this.instance).getConfigVersion();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Context getContext() {
            return ((Service) this.instance).getContext();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Control getControl() {
            return ((Service) this.instance).getControl();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Documentation getDocumentation() {
            return ((Service) this.instance).getDocumentation();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Endpoint getEndpoints(int i) {
            return ((Service) this.instance).getEndpoints(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getEndpointsCount() {
            return ((Service) this.instance).getEndpointsCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<Endpoint> getEndpointsList() {
            return Collections.unmodifiableList(((Service) this.instance).getEndpointsList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public Enum getEnums(int i) {
            return ((Service) this.instance).getEnums(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getEnumsCount() {
            return ((Service) this.instance).getEnumsCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<Enum> getEnumsList() {
            return Collections.unmodifiableList(((Service) this.instance).getEnumsList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public Http getHttp() {
            return ((Service) this.instance).getHttp();
        }

        @Override // com.google.api.ServiceOrBuilder
        public String getId() {
            return ((Service) this.instance).getId();
        }

        @Override // com.google.api.ServiceOrBuilder
        public ByteString getIdBytes() {
            return ((Service) this.instance).getIdBytes();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Logging getLogging() {
            return ((Service) this.instance).getLogging();
        }

        @Override // com.google.api.ServiceOrBuilder
        public LogDescriptor getLogs(int i) {
            return ((Service) this.instance).getLogs(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getLogsCount() {
            return ((Service) this.instance).getLogsCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<LogDescriptor> getLogsList() {
            return Collections.unmodifiableList(((Service) this.instance).getLogsList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public MetricDescriptor getMetrics(int i) {
            return ((Service) this.instance).getMetrics(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getMetricsCount() {
            return ((Service) this.instance).getMetricsCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<MetricDescriptor> getMetricsList() {
            return Collections.unmodifiableList(((Service) this.instance).getMetricsList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public MonitoredResourceDescriptor getMonitoredResources(int i) {
            return ((Service) this.instance).getMonitoredResources(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getMonitoredResourcesCount() {
            return ((Service) this.instance).getMonitoredResourcesCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
            return Collections.unmodifiableList(((Service) this.instance).getMonitoredResourcesList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public Monitoring getMonitoring() {
            return ((Service) this.instance).getMonitoring();
        }

        @Override // com.google.api.ServiceOrBuilder
        public String getName() {
            return ((Service) this.instance).getName();
        }

        @Override // com.google.api.ServiceOrBuilder
        public ByteString getNameBytes() {
            return ((Service) this.instance).getNameBytes();
        }

        @Override // com.google.api.ServiceOrBuilder
        public String getProducerProjectId() {
            return ((Service) this.instance).getProducerProjectId();
        }

        @Override // com.google.api.ServiceOrBuilder
        public ByteString getProducerProjectIdBytes() {
            return ((Service) this.instance).getProducerProjectIdBytes();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Quota getQuota() {
            return ((Service) this.instance).getQuota();
        }

        @Override // com.google.api.ServiceOrBuilder
        public SourceInfo getSourceInfo() {
            return ((Service) this.instance).getSourceInfo();
        }

        @Override // com.google.api.ServiceOrBuilder
        public SystemParameters getSystemParameters() {
            return ((Service) this.instance).getSystemParameters();
        }

        @Override // com.google.api.ServiceOrBuilder
        public String getTitle() {
            return ((Service) this.instance).getTitle();
        }

        @Override // com.google.api.ServiceOrBuilder
        public ByteString getTitleBytes() {
            return ((Service) this.instance).getTitleBytes();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Type getTypes(int i) {
            return ((Service) this.instance).getTypes(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getTypesCount() {
            return ((Service) this.instance).getTypesCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<Type> getTypesList() {
            return Collections.unmodifiableList(((Service) this.instance).getTypesList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public Usage getUsage() {
            return ((Service) this.instance).getUsage();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasAuthentication() {
            return ((Service) this.instance).hasAuthentication();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasBackend() {
            return ((Service) this.instance).hasBackend();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasBilling() {
            return ((Service) this.instance).hasBilling();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasConfigVersion() {
            return ((Service) this.instance).hasConfigVersion();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasContext() {
            return ((Service) this.instance).hasContext();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasControl() {
            return ((Service) this.instance).hasControl();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasDocumentation() {
            return ((Service) this.instance).hasDocumentation();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasHttp() {
            return ((Service) this.instance).hasHttp();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasLogging() {
            return ((Service) this.instance).hasLogging();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasMonitoring() {
            return ((Service) this.instance).hasMonitoring();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasQuota() {
            return ((Service) this.instance).hasQuota();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasSourceInfo() {
            return ((Service) this.instance).hasSourceInfo();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasSystemParameters() {
            return ((Service) this.instance).hasSystemParameters();
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasUsage() {
            return ((Service) this.instance).hasUsage();
        }

        public Builder mergeAuthentication(Authentication authentication) {
            copyOnWrite();
            ((Service) this.instance).mergeAuthentication(authentication);
            return this;
        }

        public Builder mergeBackend(Backend backend) {
            copyOnWrite();
            ((Service) this.instance).mergeBackend(backend);
            return this;
        }

        public Builder mergeBilling(Billing billing) {
            copyOnWrite();
            ((Service) this.instance).mergeBilling(billing);
            return this;
        }

        public Builder mergeConfigVersion(UInt32Value uInt32Value) {
            copyOnWrite();
            ((Service) this.instance).mergeConfigVersion(uInt32Value);
            return this;
        }

        public Builder mergeContext(Context context) {
            copyOnWrite();
            ((Service) this.instance).mergeContext(context);
            return this;
        }

        public Builder mergeControl(Control control) {
            copyOnWrite();
            ((Service) this.instance).mergeControl(control);
            return this;
        }

        public Builder mergeDocumentation(Documentation documentation) {
            copyOnWrite();
            ((Service) this.instance).mergeDocumentation(documentation);
            return this;
        }

        public Builder mergeHttp(Http http) {
            copyOnWrite();
            ((Service) this.instance).mergeHttp(http);
            return this;
        }

        public Builder mergeLogging(Logging logging) {
            copyOnWrite();
            ((Service) this.instance).mergeLogging(logging);
            return this;
        }

        public Builder mergeMonitoring(Monitoring monitoring) {
            copyOnWrite();
            ((Service) this.instance).mergeMonitoring(monitoring);
            return this;
        }

        public Builder mergeQuota(Quota quota) {
            copyOnWrite();
            ((Service) this.instance).mergeQuota(quota);
            return this;
        }

        public Builder mergeSourceInfo(SourceInfo sourceInfo) {
            copyOnWrite();
            ((Service) this.instance).mergeSourceInfo(sourceInfo);
            return this;
        }

        public Builder mergeSystemParameters(SystemParameters systemParameters) {
            copyOnWrite();
            ((Service) this.instance).mergeSystemParameters(systemParameters);
            return this;
        }

        public Builder mergeUsage(Usage usage) {
            copyOnWrite();
            ((Service) this.instance).mergeUsage(usage);
            return this;
        }

        public Builder removeApis(int i) {
            copyOnWrite();
            Service.access$2100((Service) this.instance, i);
            return this;
        }

        public Builder removeEndpoints(int i) {
            copyOnWrite();
            Service.access$6000((Service) this.instance, i);
            return this;
        }

        public Builder removeEnums(int i) {
            copyOnWrite();
            Service.access$3300((Service) this.instance, i);
            return this;
        }

        public Builder removeLogs(int i) {
            copyOnWrite();
            Service.access$6900((Service) this.instance, i);
            return this;
        }

        public Builder removeMetrics(int i) {
            copyOnWrite();
            Service.access$7500((Service) this.instance, i);
            return this;
        }

        public Builder removeMonitoredResources(int i) {
            copyOnWrite();
            Service.access$8100((Service) this.instance, i);
            return this;
        }

        public Builder removeTypes(int i) {
            copyOnWrite();
            Service.access$2700((Service) this.instance, i);
            return this;
        }

        public Builder setApis(int i, Api api) {
            copyOnWrite();
            ((Service) this.instance).setApis(i, api);
            return this;
        }

        public Builder setAuthentication(Authentication authentication) {
            copyOnWrite();
            ((Service) this.instance).setAuthentication(authentication);
            return this;
        }

        public Builder setBackend(Backend backend) {
            copyOnWrite();
            ((Service) this.instance).setBackend(backend);
            return this;
        }

        public Builder setBilling(Billing billing) {
            copyOnWrite();
            ((Service) this.instance).setBilling(billing);
            return this;
        }

        public Builder setConfigVersion(UInt32Value uInt32Value) {
            copyOnWrite();
            ((Service) this.instance).setConfigVersion(uInt32Value);
            return this;
        }

        public Builder setContext(Context context) {
            copyOnWrite();
            ((Service) this.instance).setContext(context);
            return this;
        }

        public Builder setControl(Control control) {
            copyOnWrite();
            ((Service) this.instance).setControl(control);
            return this;
        }

        public Builder setDocumentation(Documentation documentation) {
            copyOnWrite();
            ((Service) this.instance).setDocumentation(documentation);
            return this;
        }

        public Builder setEndpoints(int i, Endpoint endpoint) {
            copyOnWrite();
            ((Service) this.instance).setEndpoints(i, endpoint);
            return this;
        }

        public Builder setEnums(int i, Enum r3) {
            copyOnWrite();
            ((Service) this.instance).setEnums(i, r3);
            return this;
        }

        public Builder setHttp(Http http) {
            copyOnWrite();
            ((Service) this.instance).setHttp(http);
            return this;
        }

        public Builder setId(String str) {
            copyOnWrite();
            ((Service) this.instance).setId(str);
            return this;
        }

        public Builder setIdBytes(ByteString byteString) {
            copyOnWrite();
            ((Service) this.instance).setIdBytes(byteString);
            return this;
        }

        public Builder setLogging(Logging logging) {
            copyOnWrite();
            ((Service) this.instance).setLogging(logging);
            return this;
        }

        public Builder setLogs(int i, LogDescriptor logDescriptor) {
            copyOnWrite();
            ((Service) this.instance).setLogs(i, logDescriptor);
            return this;
        }

        public Builder setMetrics(int i, MetricDescriptor metricDescriptor) {
            copyOnWrite();
            ((Service) this.instance).setMetrics(i, metricDescriptor);
            return this;
        }

        public Builder setMonitoredResources(int i, MonitoredResourceDescriptor monitoredResourceDescriptor) {
            copyOnWrite();
            ((Service) this.instance).setMonitoredResources(i, monitoredResourceDescriptor);
            return this;
        }

        public Builder setMonitoring(Monitoring monitoring) {
            copyOnWrite();
            ((Service) this.instance).setMonitoring(monitoring);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((Service) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((Service) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setProducerProjectId(String str) {
            copyOnWrite();
            ((Service) this.instance).setProducerProjectId(str);
            return this;
        }

        public Builder setProducerProjectIdBytes(ByteString byteString) {
            copyOnWrite();
            ((Service) this.instance).setProducerProjectIdBytes(byteString);
            return this;
        }

        public Builder setQuota(Quota quota) {
            copyOnWrite();
            ((Service) this.instance).setQuota(quota);
            return this;
        }

        public Builder setSourceInfo(SourceInfo sourceInfo) {
            copyOnWrite();
            ((Service) this.instance).setSourceInfo(sourceInfo);
            return this;
        }

        public Builder setSystemParameters(SystemParameters systemParameters) {
            copyOnWrite();
            ((Service) this.instance).setSystemParameters(systemParameters);
            return this;
        }

        public Builder setTitle(String str) {
            copyOnWrite();
            ((Service) this.instance).setTitle(str);
            return this;
        }

        public Builder setTitleBytes(ByteString byteString) {
            copyOnWrite();
            ((Service) this.instance).setTitleBytes(byteString);
            return this;
        }

        public Builder setTypes(int i, Type type) {
            copyOnWrite();
            ((Service) this.instance).setTypes(i, type);
            return this;
        }

        public Builder setUsage(Usage usage) {
            copyOnWrite();
            ((Service) this.instance).setUsage(usage);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Service.DEFAULT_INSTANCE);
        }

        public Builder addApis(int i, Api api) {
            copyOnWrite();
            ((Service) this.instance).addApis(i, api);
            return this;
        }

        public Builder addEndpoints(int i, Endpoint endpoint) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(i, endpoint);
            return this;
        }

        public Builder addEnums(int i, Enum r3) {
            copyOnWrite();
            ((Service) this.instance).addEnums(i, r3);
            return this;
        }

        public Builder addLogs(int i, LogDescriptor logDescriptor) {
            copyOnWrite();
            ((Service) this.instance).addLogs(i, logDescriptor);
            return this;
        }

        public Builder addMetrics(int i, MetricDescriptor metricDescriptor) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(i, metricDescriptor);
            return this;
        }

        public Builder addMonitoredResources(int i, MonitoredResourceDescriptor monitoredResourceDescriptor) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(i, monitoredResourceDescriptor);
            return this;
        }

        public Builder addTypes(int i, Type type) {
            copyOnWrite();
            ((Service) this.instance).addTypes(i, type);
            return this;
        }

        public Builder setApis(int i, Api.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setApis(i, builder.build());
            return this;
        }

        public Builder setAuthentication(Authentication.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setAuthentication(builder.build());
            return this;
        }

        public Builder setBackend(Backend.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setBackend(builder.build());
            return this;
        }

        public Builder setBilling(Billing.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setBilling(builder.build());
            return this;
        }

        public Builder setConfigVersion(UInt32Value.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setConfigVersion(builder.build());
            return this;
        }

        public Builder setContext(Context.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setContext(builder.build());
            return this;
        }

        public Builder setControl(Control.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setControl(builder.build());
            return this;
        }

        public Builder setDocumentation(Documentation.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setDocumentation(builder.build());
            return this;
        }

        public Builder setEndpoints(int i, Endpoint.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setEndpoints(i, builder.build());
            return this;
        }

        public Builder setEnums(int i, Enum.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setEnums(i, builder.build());
            return this;
        }

        public Builder setHttp(Http.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setHttp(builder.build());
            return this;
        }

        public Builder setLogging(Logging.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setLogging(builder.build());
            return this;
        }

        public Builder setLogs(int i, LogDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setLogs(i, builder.build());
            return this;
        }

        public Builder setMetrics(int i, MetricDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setMetrics(i, builder.build());
            return this;
        }

        public Builder setMonitoredResources(int i, MonitoredResourceDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setMonitoredResources(i, builder.build());
            return this;
        }

        public Builder setMonitoring(Monitoring.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setMonitoring(builder.build());
            return this;
        }

        public Builder setQuota(Quota.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setQuota(builder.build());
            return this;
        }

        public Builder setSourceInfo(SourceInfo.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setSourceInfo(builder.build());
            return this;
        }

        public Builder setSystemParameters(SystemParameters.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setSystemParameters(builder.build());
            return this;
        }

        public Builder setTypes(int i, Type.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setTypes(i, builder.build());
            return this;
        }

        public Builder setUsage(Usage.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setUsage(builder.build());
            return this;
        }

        public Builder addApis(Api.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addApis(builder.build());
            return this;
        }

        public Builder addEndpoints(Endpoint.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(builder.build());
            return this;
        }

        public Builder addEnums(Enum.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addEnums(builder.build());
            return this;
        }

        public Builder addLogs(LogDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addLogs(builder.build());
            return this;
        }

        public Builder addMetrics(MetricDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(builder.build());
            return this;
        }

        public Builder addMonitoredResources(MonitoredResourceDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(builder.build());
            return this;
        }

        public Builder addTypes(Type.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addTypes(builder.build());
            return this;
        }

        public Builder addApis(int i, Api.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addApis(i, builder.build());
            return this;
        }

        public Builder addEndpoints(int i, Endpoint.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(i, builder.build());
            return this;
        }

        public Builder addEnums(int i, Enum.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addEnums(i, builder.build());
            return this;
        }

        public Builder addLogs(int i, LogDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addLogs(i, builder.build());
            return this;
        }

        public Builder addMetrics(int i, MetricDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(i, builder.build());
            return this;
        }

        public Builder addMonitoredResources(int i, MonitoredResourceDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(i, builder.build());
            return this;
        }

        public Builder addTypes(int i, Type.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addTypes(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Service service) {
        return DEFAULT_INSTANCE.createBuilder(service);
    }

    public static Service parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Service) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Service parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Service parseFrom(ByteString byteString) {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addApis(int i, Api api) {
        api.getClass();
        ensureApisIsMutable();
        this.apis_.add(i, api);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addEndpoints(int i, Endpoint endpoint) {
        endpoint.getClass();
        ensureEndpointsIsMutable();
        this.endpoints_.add(i, endpoint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addEnums(int i, Enum r3) {
        r3.getClass();
        ensureEnumsIsMutable();
        this.enums_.add(i, r3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLogs(int i, LogDescriptor logDescriptor) {
        logDescriptor.getClass();
        ensureLogsIsMutable();
        this.logs_.add(i, logDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMetrics(int i, MetricDescriptor metricDescriptor) {
        metricDescriptor.getClass();
        ensureMetricsIsMutable();
        this.metrics_.add(i, metricDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMonitoredResources(int i, MonitoredResourceDescriptor monitoredResourceDescriptor) {
        monitoredResourceDescriptor.getClass();
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.add(i, monitoredResourceDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTypes(int i, Type type) {
        type.getClass();
        ensureTypesIsMutable();
        this.types_.add(i, type);
    }

    public static Service parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Service parseFrom(byte[] bArr) {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Service parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Service parseFrom(InputStream inputStream) {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Service parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Service parseFrom(CodedInputStream codedInputStream) {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Service parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
