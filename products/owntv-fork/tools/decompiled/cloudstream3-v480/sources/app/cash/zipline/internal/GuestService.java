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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.internal.UnitSerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: GuestService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b`\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lapp/cash/zipline/internal/GuestService;", "Lapp/cash/zipline/internal/EndpointService;", "Lapp/cash/zipline/ZiplineService;", "runJob", "", "timeoutId", "", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface GuestService extends EndpointService, ZiplineService {
    public static final Companion Companion = Companion.$$INSTANCE;

    void runJob(int timeoutId);

    /* JADX INFO: compiled from: GuestService.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* JADX INFO: compiled from: GuestService.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class Adapter extends ZiplineServiceAdapter<GuestService> implements KSerializer<GuestService> {
            private final String serialName;
            private final List<KSerializer<?>> serializers;
            private final String simpleName;

            /* JADX INFO: compiled from: GuestService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class GeneratedOutboundService implements GuestService, OutboundService {
                private final OutboundCallHandler callHandler;

                public GeneratedOutboundService(OutboundCallHandler callHandler) {
                    Intrinsics.checkNotNullParameter(callHandler, "callHandler");
                    this.callHandler = callHandler;
                }

                @Override // app.cash.zipline.ZiplineService, java.lang.AutoCloseable
                public void close() {
                    Object objCall = this.callHandler.call(this, 2, new Object[0]);
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.Unit");
                }

                @Override // app.cash.zipline.internal.bridge.OutboundService
                public final OutboundCallHandler getCallHandler() {
                    return this.callHandler;
                }

                @Override // app.cash.zipline.internal.EndpointService
                public Set<String> getServiceNames() {
                    Object objCall = this.callHandler.call(this, 3, new Object[0]);
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
                    return (Set) objCall;
                }

                @Override // app.cash.zipline.internal.GuestService
                public void runJob(int i) {
                    Object objCall = this.callHandler.call(this, 0, Integer.valueOf(i));
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.Unit");
                }

                @Override // app.cash.zipline.internal.EndpointService
                public SerializableZiplineServiceType serviceType(String name) {
                    Intrinsics.checkNotNullParameter(name, "name");
                    return (SerializableZiplineServiceType) this.callHandler.call(this, 1, name);
                }
            }

            /* JADX INFO: compiled from: GuestService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction0 extends ReturningZiplineFunction<GuestService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction0(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("UdeailsI", "fun runJob(kotlin.Int): kotlin.Unit", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((GuestService) ziplineService, (List<?>) list);
                }

                public Object call(GuestService service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    Object obj = args.get(0);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
                    service.runJob(((Integer) obj).intValue());
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: compiled from: GuestService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction1 extends ReturningZiplineFunction<GuestService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction1(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("lT3a9OTc", "fun serviceType(kotlin.String): app.cash.zipline.internal.bridge.SerializableZiplineServiceType?", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((GuestService) ziplineService, (List<?>) list);
                }

                public Object call(GuestService service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    Object obj = args.get(0);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                    return service.serviceType((String) obj);
                }
            }

            /* JADX INFO: compiled from: GuestService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction2 extends ReturningZiplineFunction<GuestService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction2(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("moYx+T3e", "fun close(): kotlin.Unit", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((GuestService) ziplineService, (List<?>) list);
                }

                public Object call(GuestService service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    service.close();
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: compiled from: GuestService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction3 extends ReturningZiplineFunction<GuestService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction3(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("YUpf59K9", "val serviceNames: kotlin.collections.Set<kotlin.String>", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((GuestService) ziplineService, (List<?>) list);
                }

                public Object call(GuestService service, List<?> args) {
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
                this.simpleName = "GuestService";
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
            public GuestService outboundService(OutboundCallHandler callHandler) {
                Intrinsics.checkNotNullParameter(callHandler, "callHandler");
                return new GeneratedOutboundService(callHandler);
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public List<ZiplineFunction<GuestService>> ziplineFunctions(SerializersModule serializersModule) {
                Intrinsics.checkNotNullParameter(serializersModule, "serializersModule");
                IntSerializer intSerializer = IntSerializer.INSTANCE;
                UnitSerializer unitSerializer = UnitSerializer.INSTANCE;
                return CollectionsKt.listOf((Object[]) new ZiplineFunction[]{new ZiplineFunction0(CollectionsKt.listOf((Object[]) new KSerializer[]{intSerializer}), unitSerializer), new ZiplineFunction1(CollectionsKt.listOf((Object[]) new KSerializer[]{StringSerializer.INSTANCE}), BuiltinSerializersKt.getNullable(SerializableZiplineServiceType.INSTANCE.serializer())), new ZiplineFunction2(CollectionsKt.listOf((Object[]) new KSerializer[0]), unitSerializer), new ZiplineFunction3(CollectionsKt.listOf((Object[]) new KSerializer[0]), new LinkedHashSetSerializer(StringSerializer.INSTANCE))});
            }
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: app.cash.zipline.internal.GuestService$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: GuestService.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = GuestService.Companion;
        }
    }

    /* JADX INFO: compiled from: GuestService.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void close(GuestService guestService) {
            ZiplineService.CC.$default$close(guestService);
        }
    }
}
