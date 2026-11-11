package app.cash.zipline.internal.bridge;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineService;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: flows.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0002¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0080\u0004J\u0018\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005*\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0002J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004J\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003*\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0002J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0096\u0082\u0004J\n\u0010\u001a\u001a\u00020\u001bH\u0096\u0080\u0004R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\b\u001a\u00020\tX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001c"}, d2 = {"Lapp/cash/zipline/internal/bridge/FlowSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/serialization/KSerializer;", "Lkotlinx/coroutines/flow/Flow;", "delegateSerializer", "Lapp/cash/zipline/internal/bridge/FlowZiplineService;", "<init>", "(Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "toZiplineService", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "toFlow", "equals", "", "other", "", "hashCode", "", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class FlowSerializer<T> implements KSerializer<Flow<? extends T>> {
    private final KSerializer<FlowZiplineService<T>> delegateSerializer;
    private final SerialDescriptor descriptor;

    public FlowSerializer(KSerializer<FlowZiplineService<T>> delegateSerializer) {
        Intrinsics.checkNotNullParameter(delegateSerializer, "delegateSerializer");
        this.delegateSerializer = delegateSerializer;
        this.descriptor = delegateSerializer.getDescriptor();
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Flow<? extends T> value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        encoder.encodeSerializableValue(this.delegateSerializer, toZiplineService(value));
    }

    /* JADX INFO: renamed from: app.cash.zipline.internal.bridge.FlowSerializer$toZiplineService$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: flows.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@¢\u0006\u0002\u0010\u0006J\n\u0010\u0007\u001a\u00020\bH\u0096\u0080\u0004¨\u0006\t"}, d2 = {"app/cash/zipline/internal/bridge/FlowSerializer$toZiplineService$1", "Lapp/cash/zipline/internal/bridge/FlowZiplineService;", "collect", "", "collector", "Lapp/cash/zipline/internal/bridge/FlowZiplineCollector;", "(Lapp/cash/zipline/internal/bridge/FlowZiplineCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toString", "", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class C06711 implements FlowZiplineService<T> {
        final /* synthetic */ Flow<T> $this_toZiplineService;

        /* JADX WARN: Multi-variable type inference failed */
        C06711(Flow<? extends T> flow) {
            this.$this_toZiplineService = flow;
        }

        @Override // app.cash.zipline.ZiplineService, java.lang.AutoCloseable
        public /* bridge */ void close() {
            ZiplineService.CC.$default$close(this);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @Override // app.cash.zipline.internal.bridge.FlowZiplineService
        public Object collect(FlowZiplineCollector<T> flowZiplineCollector, Continuation<? super Unit> continuation) {
            FlowSerializer$toZiplineService$1$collect$1 flowSerializer$toZiplineService$1$collect$1;
            if (continuation instanceof FlowSerializer$toZiplineService$1$collect$1) {
                flowSerializer$toZiplineService$1$collect$1 = (FlowSerializer$toZiplineService$1$collect$1) continuation;
                if ((flowSerializer$toZiplineService$1$collect$1.label & Integer.MIN_VALUE) != 0) {
                    flowSerializer$toZiplineService$1$collect$1.label -= Integer.MIN_VALUE;
                } else {
                    flowSerializer$toZiplineService$1$collect$1 = new FlowSerializer$toZiplineService$1$collect$1(this, continuation);
                }
            } else {
                flowSerializer$toZiplineService$1$collect$1 = new FlowSerializer$toZiplineService$1$collect$1(this, continuation);
            }
            Object obj = flowSerializer$toZiplineService$1$collect$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = flowSerializer$toZiplineService$1$collect$1.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow<T> flow = this.$this_toZiplineService;
                    FlowSerializer$toZiplineService$1$collect$2 flowSerializer$toZiplineService$1$collect$2 = new FlowSerializer$toZiplineService$1$collect$2(flowZiplineCollector);
                    flowSerializer$toZiplineService$1$collect$1.L$0 = flowZiplineCollector;
                    flowSerializer$toZiplineService$1$collect$1.label = 1;
                    if (flow.collect(flowSerializer$toZiplineService$1$collect$2, flowSerializer$toZiplineService$1$collect$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    flowZiplineCollector = (FlowZiplineCollector) flowSerializer$toZiplineService$1$collect$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                flowZiplineCollector.close();
                return Unit.INSTANCE;
            } catch (Throwable th) {
                flowZiplineCollector.close();
                throw th;
            }
        }

        public String toString() {
            return this.$this_toZiplineService.toString();
        }
    }

    private final FlowZiplineService<T> toZiplineService(Flow<? extends T> flow) {
        return new C06711(flow);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Flow<T> deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return toFlow((FlowZiplineService) decoder.decodeSerializableValue(this.delegateSerializer));
    }

    /* JADX INFO: renamed from: app.cash.zipline.internal.bridge.FlowSerializer$toFlow$1, reason: invalid class name */
    /* JADX INFO: compiled from: flows.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "app.cash.zipline.internal.bridge.FlowSerializer$toFlow$1", f = "flows.kt", i = {0}, l = {81}, m = "invokeSuspend", n = {"$this$channelFlow"}, nl = {86}, s = {"L$0"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ FlowZiplineService<T> $this_toFlow;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(FlowZiplineService<T> flowZiplineService, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_toFlow = flowZiplineService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_toFlow, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super T> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
                this.label = 1;
                if (this.$this_toFlow.collect(new FlowZiplineCollector<T>() { // from class: app.cash.zipline.internal.bridge.FlowSerializer.toFlow.1.1
                    @Override // app.cash.zipline.ZiplineService, java.lang.AutoCloseable
                    public /* bridge */ void close() {
                        ZiplineService.CC.$default$close(this);
                    }

                    @Override // app.cash.zipline.internal.bridge.FlowZiplineCollector
                    public Object emit(T t, Continuation<? super Unit> continuation) {
                        Object objSend = producerScope.send(t, continuation);
                        return objSend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSend : Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final Flow<T> toFlow(FlowZiplineService<T> flowZiplineService) {
        return FlowKt.channelFlow(new AnonymousClass1(flowZiplineService, null));
    }

    public boolean equals(Object other) {
        return (other instanceof FlowSerializer) && Intrinsics.areEqual(((FlowSerializer) other).delegateSerializer, this.delegateSerializer);
    }

    public int hashCode() {
        return this.delegateSerializer.hashCode();
    }
}
