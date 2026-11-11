package app.cash.zipline.internal.bridge;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineFunction;
import app.cash.zipline.ZiplineService;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.internal.UnitSerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: flows.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\ba\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H¦@¢\u0006\u0002\u0010\u0007¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lapp/cash/zipline/internal/bridge/FlowZiplineService;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "collect", "", "collector", "Lapp/cash/zipline/internal/bridge/FlowZiplineCollector;", "(Lapp/cash/zipline/internal/bridge/FlowZiplineCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface FlowZiplineService<T> extends ZiplineService {
    public static final Companion Companion = Companion.$$INSTANCE;

    Object collect(FlowZiplineCollector<T> flowZiplineCollector, Continuation<? super Unit> continuation);

    /* JADX INFO: compiled from: flows.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* JADX INFO: compiled from: flows.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class Adapter<TX> extends ZiplineServiceAdapter<FlowZiplineService<TX>> implements KSerializer<FlowZiplineService<TX>> {
            private final String serialName;
            private final List<KSerializer<?>> serializers;
            private final String simpleName;

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: compiled from: flows.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            static final class GeneratedOutboundService<TS> implements FlowZiplineService<TS>, OutboundService {
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

                /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                @Override // app.cash.zipline.internal.bridge.FlowZiplineService
                public Object collect(FlowZiplineCollector<TS> flowZiplineCollector, Continuation<? super Unit> continuation) {
                    FlowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1 flowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1;
                    if (continuation instanceof FlowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1) {
                        flowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1 = (FlowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1) continuation;
                        if ((flowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1.label & Integer.MIN_VALUE) != 0) {
                            flowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1.label -= Integer.MIN_VALUE;
                        } else {
                            flowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1 = new FlowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1(this, continuation);
                        }
                    } else {
                        flowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1 = new FlowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1(this, continuation);
                    }
                    Object objCallSuspending = flowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = flowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(objCallSuspending);
                        OutboundCallHandler outboundCallHandler = this.callHandler;
                        flowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1.L$0 = SpillingKt.nullOutSpilledVariable(flowZiplineCollector);
                        flowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1.L$1 = SpillingKt.nullOutSpilledVariable(outboundCallHandler);
                        flowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1.label = 1;
                        objCallSuspending = outboundCallHandler.callSuspending(this, 0, new Object[]{flowZiplineCollector}, flowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1);
                        if (objCallSuspending == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(objCallSuspending);
                    }
                    Intrinsics.checkNotNull(objCallSuspending, "null cannot be cast to non-null type kotlin.Unit");
                    return (Unit) objCallSuspending;
                }

                @Override // app.cash.zipline.internal.bridge.OutboundService
                public final OutboundCallHandler getCallHandler() {
                    return this.callHandler;
                }
            }

            /* JADX INFO: compiled from: flows.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction0<TF> extends SuspendingZiplineFunction<FlowZiplineService<TF>> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction0(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer, KSerializer<?> suspendCallbackSerializer) {
                    super("cdZduhGd", "suspend fun collect(app.cash.zipline.internal.bridge.FlowZiplineCollector<T>): kotlin.Unit", argSerializers, resultSerializer, suspendCallbackSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                    Intrinsics.checkNotNullParameter(suspendCallbackSerializer, "suspendCallbackSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.SuspendingZiplineFunction
                public /* bridge */ /* synthetic */ Object callSuspending(ZiplineService ziplineService, List list, Continuation continuation) {
                    return callSuspending((FlowZiplineService) ziplineService, (List<?>) list, (Continuation<Object>) continuation);
                }

                public Object callSuspending(FlowZiplineService<TF> flowZiplineService, List<?> list, Continuation<Object> continuation) {
                    Object obj = list.get(0);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type app.cash.zipline.internal.bridge.FlowZiplineCollector<TF of app.cash.zipline.internal.bridge.FlowZiplineService.Companion.Adapter.ZiplineFunction0>");
                    Object objCollect = flowZiplineService.collect((FlowZiplineCollector) obj, continuation);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            }

            /* JADX INFO: compiled from: flows.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction1<TF> extends ReturningZiplineFunction<FlowZiplineService<TF>> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction1(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("moYx+T3e", "fun close(): kotlin.Unit", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((FlowZiplineService) ziplineService, (List<?>) list);
                }

                public Object call(FlowZiplineService<TF> service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    service.close();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            public Adapter(List<? extends KSerializer<?>> serializers, String serialName) {
                Intrinsics.checkNotNullParameter(serializers, "serializers");
                Intrinsics.checkNotNullParameter(serialName, "serialName");
                this.serialName = serialName;
                this.simpleName = "FlowZiplineService";
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
            public FlowZiplineService<TX> outboundService(OutboundCallHandler callHandler) {
                Intrinsics.checkNotNullParameter(callHandler, "callHandler");
                return new GeneratedOutboundService(callHandler);
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public List<ZiplineFunction<FlowZiplineService<TX>>> ziplineFunctions(SerializersModule serializersModule) {
                Intrinsics.checkNotNullParameter(serializersModule, "serializersModule");
                List listListOf = CollectionsKt.listOf((Object[]) new KSerializer[]{this.serializers.get(0)});
                FlowZiplineCollector.Companion.Adapter adapter = new FlowZiplineCollector.Companion.Adapter(listListOf, ZiplineServiceAdapterKt.serialName("app.cash.zipline.internal.bridge.FlowZiplineCollector", listListOf));
                UnitSerializer unitSerializer = UnitSerializer.INSTANCE;
                List listListOf2 = CollectionsKt.listOf((Object[]) new KSerializer[]{UnitSerializer.INSTANCE});
                return CollectionsKt.listOf((Object[]) new ZiplineFunction[]{new ZiplineFunction0(CollectionsKt.listOf((Object[]) new KSerializer[]{adapter}), unitSerializer, new SuspendCallback.Companion.Adapter(listListOf2, ZiplineServiceAdapterKt.serialName("app.cash.zipline.internal.bridge.SuspendCallback", listListOf2))), new ZiplineFunction1(CollectionsKt.listOf((Object[]) new KSerializer[0]), unitSerializer)});
            }
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: app.cash.zipline.internal.bridge.FlowZiplineService$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: flows.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = FlowZiplineService.Companion;
        }
    }

    /* JADX INFO: compiled from: flows.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <T> void close(FlowZiplineService<T> flowZiplineService) {
            ZiplineService.CC.$default$close(flowZiplineService);
        }
    }
}
