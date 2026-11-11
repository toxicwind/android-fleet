package app.cash.zipline.internal.bridge;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.Call;
import app.cash.zipline.CallResult;
import app.cash.zipline.ZiplineScope;
import app.cash.zipline.ZiplineService;
import app.cash.zipline.ZiplineServiceType;
import app.cash.zipline.internal.EndpointService;
import app.cash.zipline.internal.PlatformEngineKt;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuilder;

/* JADX INFO: compiled from: Endpoint.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001VB7\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ%\u0010=\u001a\u00020>\"\b\b\u0000\u0010?*\u00020@2\u0006\u0010A\u001a\u00020\u001a2\u0006\u0010B\u001a\u0002H?¢\u0006\u0002\u0010CJ5\u0010=\u001a\u00020>\"\b\b\u0000\u0010?*\u00020@2\u0006\u0010A\u001a\u00020\u001a2\u0006\u0010D\u001a\u0002H?2\f\u0010E\u001a\b\u0012\u0004\u0012\u0002H?0FH\u0001¢\u0006\u0002\u0010GJ'\u0010H\u001a\u0002H?\"\b\b\u0000\u0010?*\u00020@2\u0006\u0010A\u001a\u00020\u001a2\b\b\u0002\u0010\u0002\u001a\u00020)¢\u0006\u0002\u0010IJ7\u0010H\u001a\u0002H?\"\b\b\u0000\u0010?*\u00020@2\u0006\u0010A\u001a\u00020\u001a2\b\b\u0002\u0010\u0002\u001a\u00020)2\f\u0010E\u001a\b\u0012\u0004\u0012\u0002H?0FH\u0001¢\u0006\u0002\u0010JJ\f\u0010K\u001a\u00020L*\u00020@H\u0002J+\u0010M\u001a\u0002H?\"\u0004\b\u0000\u0010?2\u0006\u0010\u0002\u001a\u00020)2\f\u0010N\u001a\b\u0012\u0004\u0012\u0002H?0\u000bH\u0000¢\u0006\u0004\bO\u0010PJ\u0016\u0010Q\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001b2\u0006\u0010A\u001a\u00020\u001aH\u0001J\r\u0010R\u001a\u00020\u001aH\u0000¢\u0006\u0002\bSJ\u0012\u0010T\u001a\u0004\u0018\u00010U2\u0006\u0010A\u001a\u00020\u001aH\u0016J&\u0010T\u001a\b\u0012\u0004\u0012\u0002H?09\"\b\b\u0000\u0010?*\u00020@2\f\u0010E\u001a\b\u0012\u0004\u0012\u0002H?0FH\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001b0\u0019X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010 \u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"0!X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001a0&8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010$R\u001c\u0010(\u001a\u0004\u0018\u00010)X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020/X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0014\u00102\u001a\u000203X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u00106\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0015R\u001e\u00108\u001a\u0012\u0012\u0004\u0012\u00020\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u0003090\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010:\u001a\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<¨\u0006W"}, d2 = {"Lapp/cash/zipline/internal/bridge/Endpoint;", "Lapp/cash/zipline/internal/EndpointService;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "userSerializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "eventListener", "Lapp/cash/zipline/internal/bridge/Endpoint$EventListener;", "outboundChannel", "Lapp/cash/zipline/internal/bridge/CallChannel;", "oppositeProvider", "Lkotlin/Function0;", "<init>", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/serialization/modules/SerializersModule;Lapp/cash/zipline/internal/bridge/Endpoint$EventListener;Lapp/cash/zipline/internal/bridge/CallChannel;Lkotlin/jvm/functions/Function0;)V", "getScope$zipline_release", "()Lkotlinx/coroutines/CoroutineScope;", "getUserSerializersModule$zipline_release", "()Lkotlinx/serialization/modules/SerializersModule;", "getEventListener$zipline_release", "()Lapp/cash/zipline/internal/bridge/Endpoint$EventListener;", "getOutboundChannel$zipline_release", "()Lapp/cash/zipline/internal/bridge/CallChannel;", "getOppositeProvider$zipline_release", "()Lkotlin/jvm/functions/Function0;", "inboundServices", "", "", "Lapp/cash/zipline/internal/bridge/InboundService;", "getInboundServices$zipline_release", "()Ljava/util/Map;", "nextId", "", "incompleteContinuations", "", "Lkotlin/coroutines/Continuation;", "getIncompleteContinuations$zipline_release", "()Ljava/util/Set;", "serviceNames", "", "getServiceNames", "takeScope", "Lapp/cash/zipline/ZiplineScope;", "getTakeScope$zipline_release", "()Lapp/cash/zipline/ZiplineScope;", "setTakeScope$zipline_release", "(Lapp/cash/zipline/ZiplineScope;)V", "json", "Lkotlinx/serialization/json/Json;", "getJson$zipline_release", "()Lkotlinx/serialization/json/Json;", "callCodec", "Lapp/cash/zipline/internal/bridge/CallCodec;", "getCallCodec$zipline_release", "()Lapp/cash/zipline/internal/bridge/CallCodec;", "inboundChannel", "getInboundChannel", "serviceTypeCache", "Lapp/cash/zipline/internal/bridge/RealZiplineServiceType;", "opposite", "getOpposite$zipline_release", "()Lapp/cash/zipline/internal/EndpointService;", "bind", "", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "name", "instance", "(Ljava/lang/String;Lapp/cash/zipline/ZiplineService;)V", NotificationCompat.CATEGORY_SERVICE, "adapter", "Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;", "(Ljava/lang/String;Lapp/cash/zipline/ZiplineService;Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;)V", "take", "(Ljava/lang/String;Lapp/cash/zipline/ZiplineScope;)Lapp/cash/zipline/ZiplineService;", "(Ljava/lang/String;Lapp/cash/zipline/ZiplineScope;Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;)Lapp/cash/zipline/ZiplineService;", "usesScope", "", "withTakeScope", "block", "withTakeScope$zipline_release", "(Lapp/cash/zipline/ZiplineScope;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "remove", "generatePassByReferenceName", "generatePassByReferenceName$zipline_release", "serviceType", "Lapp/cash/zipline/internal/bridge/SerializableZiplineServiceType;", "EventListener", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Endpoint implements EndpointService {
    private final CallCodec callCodec;
    private final EventListener eventListener;
    private final CallChannel inboundChannel;
    private final Map<String, InboundService<?>> inboundServices;
    private final Set<Continuation<?>> incompleteContinuations;
    private final Json json;
    private int nextId;
    private final Function0<EndpointService> oppositeProvider;
    private final CallChannel outboundChannel;
    private final CoroutineScope scope;
    private final Map<String, RealZiplineServiceType<?>> serviceTypeCache;
    private ZiplineScope takeScope;
    private final SerializersModule userSerializersModule;

    /* JADX INFO: compiled from: Endpoint.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0016J\"\u0010\u000f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0016¨\u0006\u0013"}, d2 = {"Lapp/cash/zipline/internal/bridge/Endpoint$EventListener;", "", "<init>", "()V", "bindService", "", "name", "", NotificationCompat.CATEGORY_SERVICE, "Lapp/cash/zipline/ZiplineService;", "takeService", "serviceLeaked", "callStart", NotificationCompat.CATEGORY_CALL, "Lapp/cash/zipline/Call;", "callEnd", "result", "Lapp/cash/zipline/CallResult;", "startValue", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static class EventListener {
        public void bindService(String name, ZiplineService service) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(service, "service");
        }

        public void callEnd(Call call, CallResult result, Object startValue) {
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(result, "result");
        }

        public Object callStart(Call call) {
            Intrinsics.checkNotNullParameter(call, "call");
            return null;
        }

        public void serviceLeaked(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
        }

        public void takeService(String name, ZiplineService service) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(service, "service");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Endpoint(CoroutineScope scope, SerializersModule userSerializersModule, EventListener eventListener, CallChannel outboundChannel, Function0<? extends EndpointService> oppositeProvider) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(userSerializersModule, "userSerializersModule");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        Intrinsics.checkNotNullParameter(outboundChannel, "outboundChannel");
        Intrinsics.checkNotNullParameter(oppositeProvider, "oppositeProvider");
        this.scope = scope;
        this.userSerializersModule = userSerializersModule;
        this.eventListener = eventListener;
        this.outboundChannel = outboundChannel;
        this.oppositeProvider = oppositeProvider;
        this.inboundServices = new LinkedHashMap();
        this.nextId = 1;
        this.incompleteContinuations = new LinkedHashSet();
        this.json = JsonKt.Json$default(null, new Function1() { // from class: app.cash.zipline.internal.bridge.Endpoint$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Endpoint.json$lambda$0(this.f$0, (JsonBuilder) obj);
            }
        }, 1, null);
        this.callCodec = new CallCodec(this);
        this.inboundChannel = new CallChannel() { // from class: app.cash.zipline.internal.bridge.Endpoint$inboundChannel$1
            @Override // app.cash.zipline.internal.bridge.CallChannel
            public String call(String callJson) {
                Intrinsics.checkNotNullParameter(callJson, "callJson");
                InternalCall internalCallDecodeCall$zipline_release = this.this$0.getCallCodec().decodeCall$zipline_release(callJson);
                InboundService<?> inboundService = internalCallDecodeCall$zipline_release.getInboundService();
                Intrinsics.checkNotNull(inboundService);
                Call lastInboundCall = this.this$0.getCallCodec().getLastInboundCall();
                Intrinsics.checkNotNull(lastInboundCall);
                if (internalCallDecodeCall$zipline_release.getSuspendCallback() != null) {
                    return inboundService.callSuspending(internalCallDecodeCall$zipline_release, lastInboundCall, internalCallDecodeCall$zipline_release.getSuspendCallback());
                }
                return inboundService.call(internalCallDecodeCall$zipline_release, lastInboundCall);
            }

            @Override // app.cash.zipline.internal.bridge.CallChannel
            public boolean disconnect(String instanceName) {
                Intrinsics.checkNotNullParameter(instanceName, "instanceName");
                return this.this$0.remove(instanceName) != null;
            }
        };
        this.serviceTypeCache = new LinkedHashMap();
    }

    @Override // app.cash.zipline.ZiplineService, java.lang.AutoCloseable
    public /* bridge */ void close() {
        ZiplineService.CC.$default$close(this);
    }

    /* JADX INFO: renamed from: getScope$zipline_release, reason: from getter */
    public final CoroutineScope getScope() {
        return this.scope;
    }

    /* JADX INFO: renamed from: getUserSerializersModule$zipline_release, reason: from getter */
    public final SerializersModule getUserSerializersModule() {
        return this.userSerializersModule;
    }

    /* JADX INFO: renamed from: getEventListener$zipline_release, reason: from getter */
    public final EventListener getEventListener() {
        return this.eventListener;
    }

    /* JADX INFO: renamed from: getOutboundChannel$zipline_release, reason: from getter */
    public final CallChannel getOutboundChannel() {
        return this.outboundChannel;
    }

    public final Function0<EndpointService> getOppositeProvider$zipline_release() {
        return this.oppositeProvider;
    }

    public final Map<String, InboundService<?>> getInboundServices$zipline_release() {
        return this.inboundServices;
    }

    public final Set<Continuation<?>> getIncompleteContinuations$zipline_release() {
        return this.incompleteContinuations;
    }

    @Override // app.cash.zipline.internal.EndpointService
    public Set<String> getServiceNames() {
        return CollectionsKt.toSet(this.inboundServices.keySet());
    }

    /* JADX INFO: renamed from: getTakeScope$zipline_release, reason: from getter */
    public final ZiplineScope getTakeScope() {
        return this.takeScope;
    }

    public final void setTakeScope$zipline_release(ZiplineScope ziplineScope) {
        this.takeScope = ziplineScope;
    }

    /* JADX INFO: renamed from: getJson$zipline_release, reason: from getter */
    public final Json getJson() {
        return this.json;
    }

    static final Unit json$lambda$0(Endpoint endpoint, JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setUseArrayPolymorphism(true);
        Json.setIgnoreUnknownKeys(true);
        Json.setEncodeDefaults(true);
        Json.setAllowStructuredMapKeys(true);
        SerializersModuleBuilder serializersModuleBuilder = new SerializersModuleBuilder();
        serializersModuleBuilder.contextual(Reflection.getOrCreateKotlinClass(PassByReference.class), new PassByReferenceSerializer(endpoint));
        serializersModuleBuilder.contextual(Reflection.getOrCreateKotlinClass(Throwable.class), ThrowableSerializer.INSTANCE);
        serializersModuleBuilder.contextual(Reflection.getOrCreateKotlinClass(Long.TYPE), LongSerializer.INSTANCE);
        serializersModuleBuilder.contextual(Reflection.getOrCreateKotlinClass(Flow.class), new Function1() { // from class: app.cash.zipline.internal.bridge.Endpoint$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Endpoint.json$lambda$0$0$0((List) obj);
            }
        });
        serializersModuleBuilder.contextual(Reflection.getOrCreateKotlinClass(StateFlow.class), new Function1() { // from class: app.cash.zipline.internal.bridge.Endpoint$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Endpoint.json$lambda$0$0$1((List) obj);
            }
        });
        serializersModuleBuilder.include(endpoint.userSerializersModule);
        Json.setSerializersModule(serializersModuleBuilder.build());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KSerializer json$lambda$0$0$0(List serializers) {
        Intrinsics.checkNotNullParameter(serializers, "serializers");
        return new FlowSerializer(new FlowZiplineService.Companion.Adapter(serializers, ZiplineServiceAdapterKt.serialName("app.cash.zipline.internal.bridge.FlowZiplineService", serializers)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KSerializer json$lambda$0$0$1(List serializers) {
        Intrinsics.checkNotNullParameter(serializers, "serializers");
        return new StateFlowSerializer(new StateFlowZiplineService.Companion.Adapter(serializers, ZiplineServiceAdapterKt.serialName("app.cash.zipline.internal.bridge.StateFlowZiplineService", serializers)));
    }

    /* JADX INFO: renamed from: getCallCodec$zipline_release, reason: from getter */
    public final CallCodec getCallCodec() {
        return this.callCodec;
    }

    public final CallChannel getInboundChannel() {
        return this.inboundChannel;
    }

    public final EndpointService getOpposite$zipline_release() {
        return this.oppositeProvider.invoke();
    }

    public final <T extends ZiplineService> void bind(String name, T instance) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(instance, "instance");
        throw new IllegalStateException("unexpected call to Endpoint.bind: is the Zipline plugin configured?".toString());
    }

    public final <T extends ZiplineService> void bind(String name, T service, ZiplineServiceAdapter<T> adapter) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        this.eventListener.bindService(name, service);
        this.inboundServices.put(name, new InboundService<>(serviceType(adapter), service, this));
    }

    public static /* synthetic */ ZiplineService take$default(Endpoint endpoint, String str, ZiplineScope ziplineScope, int i, Object obj) {
        if ((i & 2) != 0) {
            ziplineScope = new ZiplineScope();
        }
        return endpoint.take(str, ziplineScope);
    }

    public final <T extends ZiplineService> T take(String name, ZiplineScope scope) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(scope, "scope");
        throw new IllegalStateException("unexpected call to Endpoint.take: is the Zipline plugin configured?".toString());
    }

    public static /* synthetic */ ZiplineService take$default(Endpoint endpoint, String str, ZiplineScope ziplineScope, ZiplineServiceAdapter ziplineServiceAdapter, int i, Object obj) {
        if ((i & 2) != 0) {
            ziplineScope = new ZiplineScope();
        }
        return endpoint.take(str, ziplineScope, ziplineServiceAdapter);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final <T extends ZiplineService> T take(String name, ZiplineScope scope, ZiplineServiceAdapter<T> adapter) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        LeakCanaryJniKt.detectLeaks();
        OutboundCallHandler outboundCallHandler = new OutboundCallHandler(serviceType(adapter), name, this, adapter, scope, null, 32, null);
        T t = (T) outboundCallHandler.outboundService();
        if (usesScope(t)) {
            scope.add$zipline_release(outboundCallHandler);
        }
        this.eventListener.takeService(name, t);
        LeakCanaryJniKt.trackLeaks(this, name, outboundCallHandler, t);
        return t;
    }

    private final boolean usesScope(ZiplineService ziplineService) {
        return ((ziplineService instanceof SuspendCallback) || (ziplineService instanceof CancelCallback)) ? false : true;
    }

    public final <T> T withTakeScope$zipline_release(ZiplineScope scope, Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(block, "block");
        ZiplineScope ziplineScope = this.takeScope;
        this.takeScope = scope;
        try {
            return block.invoke();
        } finally {
            this.takeScope = ziplineScope;
        }
    }

    public final InboundService<?> remove(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.inboundServices.remove(name);
    }

    public final String generatePassByReferenceName$zipline_release() {
        String passByReferencePrefix = PlatformEngineKt.getPassByReferencePrefix();
        int i = this.nextId;
        this.nextId = i + 1;
        return passByReferencePrefix + i;
    }

    @Override // app.cash.zipline.internal.EndpointService
    public SerializableZiplineServiceType serviceType(String name) {
        ZiplineServiceType type$zipline_release;
        Intrinsics.checkNotNullParameter(name, "name");
        InboundService<?> inboundService = this.inboundServices.get(name);
        if (inboundService == null || (type$zipline_release = inboundService.getType$zipline_release()) == null) {
            return null;
        }
        return new SerializableZiplineServiceType(type$zipline_release);
    }

    private final <T extends ZiplineService> RealZiplineServiceType<T> serviceType(ZiplineServiceAdapter<T> adapter) {
        Map<String, RealZiplineServiceType<?>> map = this.serviceTypeCache;
        String serialName = adapter.getSerialName();
        ZiplineServiceType realZiplineServiceType = map.get(serialName);
        if (realZiplineServiceType == null) {
            realZiplineServiceType = new RealZiplineServiceType(adapter.getSerialName(), adapter.ziplineFunctions(this.json.getSerializersModule()));
            map.put(serialName, (RealZiplineServiceType<?>) realZiplineServiceType);
        }
        return (RealZiplineServiceType) realZiplineServiceType;
    }
}
