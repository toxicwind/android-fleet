package app.cash.zipline.internal;

import app.cash.zipline.ZiplineFunction;
import app.cash.zipline.ZiplineService;
import app.cash.zipline.internal.bridge.OutboundCallHandler;
import app.cash.zipline.internal.bridge.OutboundService;
import app.cash.zipline.internal.bridge.ReturningZiplineFunction;
import app.cash.zipline.internal.bridge.SerializableZiplineServiceType;
import app.cash.zipline.internal.bridge.ZiplineServiceAdapter;
import io.ktor.http.ContentType;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.internal.UnitSerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: HostService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\b`\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\"\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000bH&¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lapp/cash/zipline/internal/HostService;", "Lapp/cash/zipline/internal/EndpointService;", "Lapp/cash/zipline/ZiplineService;", "setTimeout", "", "timeoutId", "", "delayMillis", "clearTimeout", "log", "level", "", ContentType.Message.TYPE, "throwable", "", "serviceLeaked", "name", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface HostService extends EndpointService, ZiplineService {
    public static final Companion Companion = Companion.$$INSTANCE;

    void clearTimeout(int timeoutId);

    void log(String level, String message, Throwable throwable);

    void serviceLeaked(String name);

    void setTimeout(int timeoutId, int delayMillis);

    /* JADX INFO: compiled from: HostService.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* JADX INFO: compiled from: HostService.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class Adapter extends ZiplineServiceAdapter<HostService> implements KSerializer<HostService> {
            private final String serialName;
            private final List<KSerializer<?>> serializers;
            private final String simpleName;

            /* JADX INFO: compiled from: HostService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class GeneratedOutboundService implements HostService, OutboundService {
                private final OutboundCallHandler callHandler;

                public GeneratedOutboundService(OutboundCallHandler callHandler) {
                    Intrinsics.checkNotNullParameter(callHandler, "callHandler");
                    this.callHandler = callHandler;
                }

                @Override // app.cash.zipline.internal.HostService
                public void clearTimeout(int i) {
                    Object objCall = this.callHandler.call(this, 1, Integer.valueOf(i));
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.Unit");
                }

                @Override // app.cash.zipline.ZiplineService, java.lang.AutoCloseable
                public void close() {
                    Object objCall = this.callHandler.call(this, 5, new Object[0]);
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.Unit");
                }

                @Override // app.cash.zipline.internal.bridge.OutboundService
                public final OutboundCallHandler getCallHandler() {
                    return this.callHandler;
                }

                @Override // app.cash.zipline.internal.EndpointService
                public Set<String> getServiceNames() {
                    Object objCall = this.callHandler.call(this, 6, new Object[0]);
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
                    return (Set) objCall;
                }

                @Override // app.cash.zipline.internal.HostService
                public void log(String level, String message, Throwable th) {
                    Intrinsics.checkNotNullParameter(level, "level");
                    Intrinsics.checkNotNullParameter(message, "message");
                    Object objCall = this.callHandler.call(this, 2, level, message, th);
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.Unit");
                }

                @Override // app.cash.zipline.internal.HostService
                public void serviceLeaked(String name) {
                    Intrinsics.checkNotNullParameter(name, "name");
                    Object objCall = this.callHandler.call(this, 3, name);
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.Unit");
                }

                @Override // app.cash.zipline.internal.EndpointService
                public SerializableZiplineServiceType serviceType(String name) {
                    Intrinsics.checkNotNullParameter(name, "name");
                    return (SerializableZiplineServiceType) this.callHandler.call(this, 4, name);
                }

                @Override // app.cash.zipline.internal.HostService
                public void setTimeout(int i, int i2) {
                    Object objCall = this.callHandler.call(this, 0, Integer.valueOf(i), Integer.valueOf(i2));
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.Unit");
                }
            }

            /* JADX INFO: compiled from: HostService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction0 extends ReturningZiplineFunction<HostService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction0(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("aj52KUj/", "fun setTimeout(kotlin.Int, kotlin.Int): kotlin.Unit", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((HostService) ziplineService, (List<?>) list);
                }

                public Object call(HostService service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    Object obj = args.get(0);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
                    int iIntValue = ((Integer) obj).intValue();
                    Object obj2 = args.get(1);
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Int");
                    service.setTimeout(iIntValue, ((Integer) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: compiled from: HostService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction1 extends ReturningZiplineFunction<HostService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction1(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("oO0TiwLK", "fun clearTimeout(kotlin.Int): kotlin.Unit", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((HostService) ziplineService, (List<?>) list);
                }

                public Object call(HostService service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    Object obj = args.get(0);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
                    service.clearTimeout(((Integer) obj).intValue());
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: compiled from: HostService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction2 extends ReturningZiplineFunction<HostService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction2(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("Lu6/DTqp", "fun log(kotlin.String, kotlin.String, kotlin.Throwable?): kotlin.Unit", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((HostService) ziplineService, (List<?>) list);
                }

                public Object call(HostService service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    Object obj = args.get(0);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                    Object obj2 = args.get(1);
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
                    service.log((String) obj, (String) obj2, (Throwable) args.get(2));
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: compiled from: HostService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction3 extends ReturningZiplineFunction<HostService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction3(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("Mx/W/Edd", "fun serviceLeaked(kotlin.String): kotlin.Unit", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((HostService) ziplineService, (List<?>) list);
                }

                public Object call(HostService service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    Object obj = args.get(0);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                    service.serviceLeaked((String) obj);
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: compiled from: HostService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction4 extends ReturningZiplineFunction<HostService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction4(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("lT3a9OTc", "fun serviceType(kotlin.String): app.cash.zipline.internal.bridge.SerializableZiplineServiceType?", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((HostService) ziplineService, (List<?>) list);
                }

                public Object call(HostService service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    Object obj = args.get(0);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                    return service.serviceType((String) obj);
                }
            }

            /* JADX INFO: compiled from: HostService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction5 extends ReturningZiplineFunction<HostService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction5(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("moYx+T3e", "fun close(): kotlin.Unit", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((HostService) ziplineService, (List<?>) list);
                }

                public Object call(HostService service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    service.close();
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: compiled from: HostService.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction6 extends ReturningZiplineFunction<HostService> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction6(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("YUpf59K9", "val serviceNames: kotlin.collections.Set<kotlin.String>", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((HostService) ziplineService, (List<?>) list);
                }

                public Object call(HostService service, List<?> args) {
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
                this.simpleName = "HostService";
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
            public HostService outboundService(OutboundCallHandler callHandler) {
                Intrinsics.checkNotNullParameter(callHandler, "callHandler");
                return new GeneratedOutboundService(callHandler);
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public List<ZiplineFunction<HostService>> ziplineFunctions(SerializersModule serializersModule) {
                Intrinsics.checkNotNullParameter(serializersModule, "serializersModule");
                IntSerializer intSerializer = IntSerializer.INSTANCE;
                UnitSerializer unitSerializer = UnitSerializer.INSTANCE;
                StringSerializer stringSerializer = StringSerializer.INSTANCE;
                return CollectionsKt.listOf((Object[]) new ZiplineFunction[]{new ZiplineFunction0(CollectionsKt.listOf((Object[]) new KSerializer[]{intSerializer, intSerializer}), unitSerializer), new ZiplineFunction1(CollectionsKt.listOf((Object[]) new KSerializer[]{intSerializer}), unitSerializer), new ZiplineFunction2(CollectionsKt.listOf((Object[]) new KSerializer[]{stringSerializer, stringSerializer, BuiltinSerializersKt.getNullable(kotlinx.serialization.SerializersKt.noCompiledSerializer(serializersModule, Reflection.getOrCreateKotlinClass(Throwable.class)))}), unitSerializer), new ZiplineFunction3(CollectionsKt.listOf((Object[]) new KSerializer[]{stringSerializer}), unitSerializer), new ZiplineFunction4(CollectionsKt.listOf((Object[]) new KSerializer[]{stringSerializer}), BuiltinSerializersKt.getNullable(SerializableZiplineServiceType.INSTANCE.serializer())), new ZiplineFunction5(CollectionsKt.listOf((Object[]) new KSerializer[0]), unitSerializer), new ZiplineFunction6(CollectionsKt.listOf((Object[]) new KSerializer[0]), new LinkedHashSetSerializer(StringSerializer.INSTANCE))});
            }
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: app.cash.zipline.internal.HostService$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: HostService.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = HostService.Companion;
        }
    }

    /* JADX INFO: compiled from: HostService.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void close(HostService hostService) {
            ZiplineService.CC.$default$close(hostService);
        }
    }
}
