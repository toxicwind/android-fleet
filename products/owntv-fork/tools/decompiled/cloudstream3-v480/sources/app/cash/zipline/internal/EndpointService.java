package app.cash.zipline.internal;

import app.cash.zipline.ZiplineFunction;
import app.cash.zipline.ZiplineService;
import app.cash.zipline.internal.bridge.OutboundCallHandler;
import app.cash.zipline.internal.bridge.OutboundService;
import app.cash.zipline.internal.bridge.ReturningZiplineFunction;
import app.cash.zipline.internal.bridge.SerializableZiplineServiceType;
import app.cash.zipline.internal.bridge.ZiplineServiceAdapter;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.internal.UnitSerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: EndpointService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u0004H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lapp/cash/zipline/internal/EndpointService;", "Lapp/cash/zipline/ZiplineService;", "serviceNames", "", "", "getServiceNames", "()Ljava/util/Set;", "serviceType", "Lapp/cash/zipline/internal/bridge/SerializableZiplineServiceType;", "name", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface EndpointService extends ZiplineService {
    public static final Companion Companion = Companion.$$INSTANCE;

    Set<String> getServiceNames();

    SerializableZiplineServiceType serviceType(String name);

    /* JADX INFO: compiled from: EndpointService.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* JADX INFO: compiled from: EndpointService.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class Adapter extends ZiplineServiceAdapter<EndpointService> implements KSerializer<EndpointService> {
            private final String serialName;
            private final List<KSerializer<?>> serializers;
            private final String simpleName;

            /* JADX INFO: compiled from: EndpointService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class GeneratedOutboundService implements EndpointService, OutboundService {
                private final OutboundCallHandler callHandler;

                public GeneratedOutboundService(OutboundCallHandler callHandler) {
                    Intrinsics.checkNotNullParameter(callHandler, "callHandler");
                    this.callHandler = callHandler;
                }

                @Override // app.cash.zipline.ZiplineService, java.lang.AutoCloseable
                public void close() {
                    Object objCall = this.callHandler.call(this, 1, new Object[0]);
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.Unit");
                }

                @Override // app.cash.zipline.internal.bridge.OutboundService
                public final OutboundCallHandler getCallHandler() {
                    return this.callHandler;
                }

                @Override // app.cash.zipline.internal.EndpointService
                public Set<String> getServiceNames() {
                    Object objCall = this.callHandler.call(this, 2, new Object[0]);
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
                    return (Set) objCall;
                }

                @Override // app.cash.zipline.internal.EndpointService
                public SerializableZiplineServiceType serviceType(String name) {
                    Intrinsics.checkNotNullParameter(name, "name");
                    return (SerializableZiplineServiceType) this.callHandler.call(this, 0, name);
                }
            }

            /* JADX INFO: compiled from: EndpointService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction0 extends ReturningZiplineFunction<EndpointService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction0(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("lT3a9OTc", "fun serviceType(kotlin.String): app.cash.zipline.internal.bridge.SerializableZiplineServiceType?", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((EndpointService) ziplineService, (List<?>) list);
                }

                public Object call(EndpointService service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    Object obj = args.get(0);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                    return service.serviceType((String) obj);
                }
            }

            /* JADX INFO: compiled from: EndpointService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction1 extends ReturningZiplineFunction<EndpointService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction1(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("moYx+T3e", "fun close(): kotlin.Unit", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((EndpointService) ziplineService, (List<?>) list);
                }

                public Object call(EndpointService service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    service.close();
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: compiled from: EndpointService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction2 extends ReturningZiplineFunction<EndpointService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction2(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("YUpf59K9", "val serviceNames: kotlin.collections.Set<kotlin.String>", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((EndpointService) ziplineService, (List<?>) list);
                }

                public Object call(EndpointService service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    return service.getServiceNames();
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            public Adapter(List<? extends KSerializer<?>> serializers, String serialName) {
                Intrinsics.checkNotNullParameter(serializers, "serializers");
                Intrinsics.checkNotNullParameter(serialName, "serialName");
                this.serialName = serialName;
                this.simpleName = "EndpointService";
                this.serializers = serializers;
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public final String getSerialName() {
                return this.serialName;
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public final List<KSerializer<?>> getSerializers() {
                return this.serializers;
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public final String getSimpleName() {
                return this.simpleName;
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public EndpointService outboundService(OutboundCallHandler callHandler) {
                Intrinsics.checkNotNullParameter(callHandler, "callHandler");
                return new GeneratedOutboundService(callHandler);
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public List<ZiplineFunction<EndpointService>> ziplineFunctions(SerializersModule serializersModule) {
                Intrinsics.checkNotNullParameter(serializersModule, "serializersModule");
                return CollectionsKt.listOf((Object[]) new ZiplineFunction[]{new ZiplineFunction0(CollectionsKt.listOf((Object[]) new KSerializer[]{StringSerializer.INSTANCE}), BuiltinSerializersKt.getNullable(SerializableZiplineServiceType.INSTANCE.serializer())), new ZiplineFunction1(CollectionsKt.listOf((Object[]) new KSerializer[0]), UnitSerializer.INSTANCE), new ZiplineFunction2(CollectionsKt.listOf((Object[]) new KSerializer[0]), new LinkedHashSetSerializer(StringSerializer.INSTANCE))});
            }
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: app.cash.zipline.internal.EndpointService$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: EndpointService.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = EndpointService.Companion;
        }
    }

    /* JADX INFO: compiled from: EndpointService.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void close(EndpointService endpointService) {
            ZiplineService.CC.$default$close(endpointService);
        }
    }
}
