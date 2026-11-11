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
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: flows.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\ba\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H¦@¢\u0006\u0002\u0010\u0006¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lapp/cash/zipline/internal/bridge/FlowZiplineCollector;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "emit", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface FlowZiplineCollector<T> extends ZiplineService {
    public static final Companion Companion = Companion.$$INSTANCE;

    Object emit(T t, Continuation<? super Unit> continuation);

    /* JADX INFO: compiled from: flows.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* JADX INFO: compiled from: flows.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class Adapter<TX> extends ZiplineServiceAdapter<FlowZiplineCollector<TX>> implements KSerializer<FlowZiplineCollector<TX>> {
            private final String serialName;
            private final List<KSerializer<?>> serializers;
            private final String simpleName;

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: compiled from: flows.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            static final class GeneratedOutboundService<TS> implements FlowZiplineCollector<TS>, OutboundService {
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
                @Override // app.cash.zipline.internal.bridge.FlowZiplineCollector
                public Object emit(TS ts, Continuation<? super Unit> continuation) {
                    FlowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1 flowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1;
                    if (continuation instanceof FlowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1) {
                        flowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1 = (FlowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1) continuation;
                        if ((flowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1.label & Integer.MIN_VALUE) != 0) {
                            flowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1.label -= Integer.MIN_VALUE;
                        } else {
                            flowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1 = new FlowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1(this, continuation);
                        }
                    } else {
                        flowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1 = new FlowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1(this, continuation);
                    }
                    Object objCallSuspending = flowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = flowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(objCallSuspending);
                        OutboundCallHandler outboundCallHandler = this.callHandler;
                        flowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1.L$0 = SpillingKt.nullOutSpilledVariable(ts);
                        flowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1.L$1 = SpillingKt.nullOutSpilledVariable(outboundCallHandler);
                        flowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1.label = 1;
                        objCallSuspending = outboundCallHandler.callSuspending(this, 0, new Object[]{ts}, flowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1);
                        if (objCallSuspending == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        Object obj = flowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1.L$0;
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
            private static final class ZiplineFunction0<TF> extends SuspendingZiplineFunction<FlowZiplineCollector<TF>> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction0(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer, KSerializer<?> suspendCallbackSerializer) {
                    super("onkYO9kU", "suspend fun emit(T): kotlin.Unit", argSerializers, resultSerializer, suspendCallbackSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                    Intrinsics.checkNotNullParameter(suspendCallbackSerializer, "suspendCallbackSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.SuspendingZiplineFunction
                public /* bridge */ /* synthetic */ Object callSuspending(ZiplineService ziplineService, List list, Continuation continuation) {
                    return callSuspending((FlowZiplineCollector) ziplineService, (List<?>) list, (Continuation<Object>) continuation);
                }

                /* JADX WARN: Multi-variable type inference failed */
                public Object callSuspending(FlowZiplineCollector<TF> flowZiplineCollector, List<?> list, Continuation<Object> continuation) {
                    Object objEmit = flowZiplineCollector.emit(list.get(0), continuation);
                    return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
                }
            }

            /* JADX INFO: compiled from: flows.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction1<TF> extends ReturningZiplineFunction<FlowZiplineCollector<TF>> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction1(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("moYx+T3e", "fun close(): kotlin.Unit", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((FlowZiplineCollector) ziplineService, (List<?>) list);
                }

                public Object call(FlowZiplineCollector<TF> service, List<?> args) {
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
                this.simpleName = "FlowZiplineCollector";
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
            public FlowZiplineCollector<TX> outboundService(OutboundCallHandler callHandler) {
                Intrinsics.checkNotNullParameter(callHandler, "callHandler");
                return new GeneratedOutboundService(callHandler);
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public List<ZiplineFunction<FlowZiplineCollector<TX>>> ziplineFunctions(SerializersModule serializersModule) {
                Intrinsics.checkNotNullParameter(serializersModule, "serializersModule");
                KSerializer<?> kSerializer = this.serializers.get(0);
                UnitSerializer unitSerializer = UnitSerializer.INSTANCE;
                List listListOf = CollectionsKt.listOf((Object[]) new KSerializer[]{UnitSerializer.INSTANCE});
                return CollectionsKt.listOf((Object[]) new ZiplineFunction[]{new ZiplineFunction0(CollectionsKt.listOf((Object[]) new KSerializer[]{kSerializer}), unitSerializer, new SuspendCallback.Companion.Adapter(listListOf, ZiplineServiceAdapterKt.serialName("app.cash.zipline.internal.bridge.SuspendCallback", listListOf))), new ZiplineFunction1(CollectionsKt.listOf((Object[]) new KSerializer[0]), unitSerializer)});
            }
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: app.cash.zipline.internal.bridge.FlowZiplineCollector$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: flows.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = FlowZiplineCollector.Companion;
        }
    }

    /* JADX INFO: compiled from: flows.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <T> void close(FlowZiplineCollector<T> flowZiplineCollector) {
            ZiplineService.CC.$default$close(flowZiplineCollector);
        }
    }
}
