package app.cash.zipline.internal.bridge;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineService;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: flows.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0002¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0080\u0004J\u0018\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005*\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0002J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004J\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003*\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0002J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0096\u0082\u0004J\n\u0010\u001a\u001a\u00020\u001bH\u0096\u0080\u0004R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\b\u001a\u00020\tX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001c"}, d2 = {"Lapp/cash/zipline/internal/bridge/StateFlowSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/serialization/KSerializer;", "Lkotlinx/coroutines/flow/StateFlow;", "delegateSerializer", "Lapp/cash/zipline/internal/bridge/StateFlowZiplineService;", "<init>", "(Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "toZiplineService", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "toStateFlow", "equals", "", "other", "", "hashCode", "", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class StateFlowSerializer<T> implements KSerializer<StateFlow<? extends T>> {
    private final KSerializer<StateFlowZiplineService<T>> delegateSerializer;
    private final SerialDescriptor descriptor;

    public StateFlowSerializer(KSerializer<StateFlowZiplineService<T>> delegateSerializer) {
        Intrinsics.checkNotNullParameter(delegateSerializer, "delegateSerializer");
        this.delegateSerializer = delegateSerializer;
        this.descriptor = delegateSerializer.getDescriptor();
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, StateFlow<? extends T> value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        encoder.encodeSerializableValue(this.delegateSerializer, toZiplineService(value));
    }

    /* JADX INFO: renamed from: app.cash.zipline.internal.bridge.StateFlowSerializer$toZiplineService$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: flows.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@¢\u0006\u0002\u0010\u0006J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004R\u0014\u0010\u0007\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"app/cash/zipline/internal/bridge/StateFlowSerializer$toZiplineService$1", "Lapp/cash/zipline/internal/bridge/StateFlowZiplineService;", "collect", "", "collector", "Lapp/cash/zipline/internal/bridge/FlowZiplineCollector;", "(Lapp/cash/zipline/internal/bridge/FlowZiplineCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", ES6Iterator.VALUE_PROPERTY, "getValue", "()Ljava/lang/Object;", "toString", "", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class C06731 implements StateFlowZiplineService<T> {
        final /* synthetic */ StateFlow<T> $this_toZiplineService;

        /* JADX WARN: Multi-variable type inference failed */
        C06731(StateFlow<? extends T> stateFlow) {
            this.$this_toZiplineService = stateFlow;
        }

        @Override // app.cash.zipline.ZiplineService, java.lang.AutoCloseable
        public /* bridge */ void close() {
            ZiplineService.CC.$default$close(this);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @Override // app.cash.zipline.internal.bridge.FlowZiplineService
        public Object collect(FlowZiplineCollector<T> flowZiplineCollector, Continuation<? super Unit> continuation) {
            StateFlowSerializer$toZiplineService$1$collect$1 stateFlowSerializer$toZiplineService$1$collect$1;
            if (continuation instanceof StateFlowSerializer$toZiplineService$1$collect$1) {
                stateFlowSerializer$toZiplineService$1$collect$1 = (StateFlowSerializer$toZiplineService$1$collect$1) continuation;
                if ((stateFlowSerializer$toZiplineService$1$collect$1.label & Integer.MIN_VALUE) != 0) {
                    stateFlowSerializer$toZiplineService$1$collect$1.label -= Integer.MIN_VALUE;
                } else {
                    stateFlowSerializer$toZiplineService$1$collect$1 = new StateFlowSerializer$toZiplineService$1$collect$1(this, continuation);
                }
            } else {
                stateFlowSerializer$toZiplineService$1$collect$1 = new StateFlowSerializer$toZiplineService$1$collect$1(this, continuation);
            }
            Object obj = stateFlowSerializer$toZiplineService$1$collect$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = stateFlowSerializer$toZiplineService$1$collect$1.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow<T> stateFlow = this.$this_toZiplineService;
                    StateFlowSerializer$toZiplineService$1$collect$2 stateFlowSerializer$toZiplineService$1$collect$2 = new StateFlowSerializer$toZiplineService$1$collect$2(flowZiplineCollector);
                    stateFlowSerializer$toZiplineService$1$collect$1.L$0 = flowZiplineCollector;
                    stateFlowSerializer$toZiplineService$1$collect$1.label = 1;
                    if (stateFlow.collect(stateFlowSerializer$toZiplineService$1$collect$2, stateFlowSerializer$toZiplineService$1$collect$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    flowZiplineCollector = (FlowZiplineCollector) stateFlowSerializer$toZiplineService$1$collect$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            } catch (Throwable th) {
                flowZiplineCollector.close();
                throw th;
            }
        }

        @Override // app.cash.zipline.internal.bridge.StateFlowZiplineService
        public T getValue() {
            return this.$this_toZiplineService.getValue();
        }

        public String toString() {
            return this.$this_toZiplineService.toString();
        }
    }

    private final StateFlowZiplineService<T> toZiplineService(StateFlow<? extends T> stateFlow) {
        return new C06731(stateFlow);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public StateFlow<T> deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return toStateFlow((StateFlowZiplineService) decoder.decodeSerializableValue(this.delegateSerializer));
    }

    /* JADX INFO: renamed from: app.cash.zipline.internal.bridge.StateFlowSerializer$toStateFlow$1, reason: invalid class name */
    /* JADX INFO: compiled from: flows.kt */
    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0007\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"app/cash/zipline/internal/bridge/StateFlowSerializer$toStateFlow$1", "Lkotlinx/coroutines/flow/StateFlow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", ES6Iterator.VALUE_PROPERTY, "getValue", "()Ljava/lang/Object;", "replayCache", "", "getReplayCache", "()Ljava/util/List;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AnonymousClass1 implements StateFlow<T> {
        final /* synthetic */ StateFlowZiplineService<T> $this_toStateFlow;

        AnonymousClass1(StateFlowZiplineService<T> stateFlowZiplineService) {
            this.$this_toStateFlow = stateFlowZiplineService;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
        public Object collect(FlowCollector<? super T> flowCollector, Continuation<?> continuation) {
            StateFlowSerializer$toStateFlow$1$collect$1 stateFlowSerializer$toStateFlow$1$collect$1;
            if (continuation instanceof StateFlowSerializer$toStateFlow$1$collect$1) {
                stateFlowSerializer$toStateFlow$1$collect$1 = (StateFlowSerializer$toStateFlow$1$collect$1) continuation;
                if ((stateFlowSerializer$toStateFlow$1$collect$1.label & Integer.MIN_VALUE) != 0) {
                    stateFlowSerializer$toStateFlow$1$collect$1.label -= Integer.MIN_VALUE;
                } else {
                    stateFlowSerializer$toStateFlow$1$collect$1 = new StateFlowSerializer$toStateFlow$1$collect$1(this, continuation);
                }
            } else {
                stateFlowSerializer$toStateFlow$1$collect$1 = new StateFlowSerializer$toStateFlow$1$collect$1(this, continuation);
            }
            Object obj = stateFlowSerializer$toStateFlow$1$collect$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = stateFlowSerializer$toStateFlow$1$collect$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowChannelFlow = FlowKt.channelFlow(new StateFlowSerializer$toStateFlow$1$collect$2(this.$this_toStateFlow, null));
                stateFlowSerializer$toStateFlow$1$collect$1.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
                stateFlowSerializer$toStateFlow$1$collect$1.label = 1;
                if (flowChannelFlow.collect(flowCollector, stateFlowSerializer$toStateFlow$1$collect$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new AssertionError();
        }

        @Override // kotlinx.coroutines.flow.StateFlow
        public T getValue() {
            return this.$this_toStateFlow.getValue();
        }

        @Override // kotlinx.coroutines.flow.SharedFlow
        public List<T> getReplayCache() {
            return CollectionsKt.listOf(getValue());
        }
    }

    private final StateFlow<T> toStateFlow(StateFlowZiplineService<T> stateFlowZiplineService) {
        return new AnonymousClass1(stateFlowZiplineService);
    }

    public boolean equals(Object other) {
        return (other instanceof StateFlowSerializer) && Intrinsics.areEqual(((StateFlowSerializer) other).delegateSerializer, this.delegateSerializer);
    }

    public int hashCode() {
        return this.delegateSerializer.hashCode();
    }
}
