package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: SharingStarted.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
final class StartedLazily$command$1$1<T> implements FlowCollector {
    final /* synthetic */ Ref.BooleanRef $started;
    final /* synthetic */ FlowCollector<SharingCommand> $this_unsafeFlow;

    /* JADX WARN: Multi-variable type inference failed */
    StartedLazily$command$1$1(Ref.BooleanRef booleanRef, FlowCollector<? super SharingCommand> flowCollector) {
        this.$started = booleanRef;
        this.$this_unsafeFlow = flowCollector;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object emit(int i, Continuation<? super Unit> continuation) {
        StartedLazily$command$1$1$emit$1 startedLazily$command$1$1$emit$1;
        if (continuation instanceof StartedLazily$command$1$1$emit$1) {
            startedLazily$command$1$1$emit$1 = (StartedLazily$command$1$1$emit$1) continuation;
            if ((startedLazily$command$1$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                startedLazily$command$1$1$emit$1.label -= Integer.MIN_VALUE;
            } else {
                startedLazily$command$1$1$emit$1 = new StartedLazily$command$1$1$emit$1(this, continuation);
            }
        } else {
            startedLazily$command$1$1$emit$1 = new StartedLazily$command$1$1$emit$1(this, continuation);
        }
        Object obj = startedLazily$command$1$1$emit$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = startedLazily$command$1$1$emit$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            if (i <= 0 || this.$started.element) {
                return Unit.INSTANCE;
            }
            this.$started.element = true;
            FlowCollector<SharingCommand> flowCollector = this.$this_unsafeFlow;
            SharingCommand sharingCommand = SharingCommand.START;
            startedLazily$command$1$1$emit$1.I$0 = i;
            startedLazily$command$1$1$emit$1.label = 1;
            if (flowCollector.emit(sharingCommand, startedLazily$command$1$1$emit$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = startedLazily$command$1$1$emit$1.I$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
        return emit(((Number) obj).intValue(), (Continuation<? super Unit>) continuation);
    }
}
