package androidx.activity.compose;

import androidx.activity.BackEventCompat;
import androidx.activity.compose.internal.BackHandlerCompat;
import androidx.core.app.NotificationCompat;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: PredictiveBackHandler.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010$\u001a\u00020\u0012H\u0002J\u0010\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\rH\u0016J\u0010\u0010'\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\rH\u0016J\b\u0010(\u001a\u00020\u0012H\u0016J\b\u0010)\u001a\u00020\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tRM\u0010\n\u001a3\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\r0\f¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u000bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Landroidx/activity/compose/ComposePredictiveBackHandler;", "Landroidx/activity/compose/internal/BackHandlerCompat;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "info", "Landroidx/activity/compose/PredictiveBackHandlerInfo;", "<init>", "(Lkotlinx/coroutines/CoroutineScope;Landroidx/activity/compose/PredictiveBackHandlerInfo;)V", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "currentOnBack", "Lkotlin/Function2;", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/activity/BackEventCompat;", "Lkotlin/ParameterName;", "name", "progress", "Lkotlin/coroutines/Continuation;", "", "", "getCurrentOnBack", "()Lkotlin/jvm/functions/Function2;", "setCurrentOnBack", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", ES6Iterator.VALUE_PROPERTY, "", "isBackEnabled", "()Z", "setBackEnabled", "(Z)V", "activeChannel", "Lkotlinx/coroutines/channels/Channel;", "activeJob", "Lkotlinx/coroutines/Job;", "isPredictiveBack", "launchNewGesture", "onBackStarted", NotificationCompat.CATEGORY_EVENT, "onBackProgressed", "onBackCompleted", "onBackCancelled", "activity-compose"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class ComposePredictiveBackHandler extends BackHandlerCompat {
    private Channel<BackEventCompat> activeChannel;
    private Job activeJob;
    private Function2<? super Flow<BackEventCompat>, ? super Continuation<? super Unit>, ? extends Object> currentOnBack;
    private boolean isPredictiveBack;
    private final CoroutineScope scope;

    public final CoroutineScope getScope() {
        return this.scope;
    }

    public ComposePredictiveBackHandler(CoroutineScope coroutineScope, PredictiveBackHandlerInfo predictiveBackHandlerInfo) {
        super(predictiveBackHandlerInfo);
        this.scope = coroutineScope;
        this.currentOnBack = new ComposePredictiveBackHandler$currentOnBack$1(null);
    }

    public final Function2<Flow<BackEventCompat>, Continuation<? super Unit>, Object> getCurrentOnBack() {
        return this.currentOnBack;
    }

    public final void setCurrentOnBack(Function2<? super Flow<BackEventCompat>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.currentOnBack = function2;
    }

    @Override // androidx.activity.compose.internal.BackHandlerCompat
    public boolean isBackEnabled() {
        return super.isBackEnabled();
    }

    @Override // androidx.activity.compose.internal.BackHandlerCompat
    public void setBackEnabled(boolean z) {
        Job job;
        if (!z && super.isBackEnabled() && (job = this.activeJob) != null && !job.isActive()) {
            onBackCancelled();
        }
        super.setBackEnabled(z);
    }

    private final void launchNewGesture() {
        this.activeChannel = ChannelKt.Channel$default(-2, BufferOverflow.SUSPEND, null, 4, null);
        this.activeJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.activity.compose.ComposePredictiveBackHandler$launchNewGesture$1, reason: invalid class name */
    /* JADX INFO: compiled from: PredictiveBackHandler.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.activity.compose.ComposePredictiveBackHandler$launchNewGesture$1", f = "PredictiveBackHandler.kt", i = {0}, l = {231}, m = "invokeSuspend", n = {"completed"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ComposePredictiveBackHandler.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.BooleanRef booleanRef;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (ComposePredictiveBackHandler.this.isBackEnabled()) {
                    Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                    Function2<Flow<BackEventCompat>, Continuation<? super Unit>, Object> currentOnBack = ComposePredictiveBackHandler.this.getCurrentOnBack();
                    Channel channel = ComposePredictiveBackHandler.this.activeChannel;
                    Intrinsics.checkNotNull(channel);
                    Flow<BackEventCompat> flowOnCompletion = FlowKt.onCompletion(FlowKt.consumeAsFlow(channel), new C00001(booleanRef2, null));
                    this.L$0 = booleanRef2;
                    this.label = 1;
                    if (currentOnBack.invoke(flowOnCompletion, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    booleanRef = booleanRef2;
                }
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            booleanRef = (Ref.BooleanRef) this.L$0;
            ResultKt.throwOnFailure(obj);
            if (!booleanRef.element) {
                throw new IllegalStateException("You must collect the progress flow".toString());
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: androidx.activity.compose.ComposePredictiveBackHandler$launchNewGesture$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: PredictiveBackHandler.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Landroidx/activity/BackEventCompat;", "it", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.activity.compose.ComposePredictiveBackHandler$launchNewGesture$1$1", f = "PredictiveBackHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class C00001 extends SuspendLambda implements Function3<FlowCollector<? super BackEventCompat>, Throwable, Continuation<? super Unit>, Object> {
            final /* synthetic */ Ref.BooleanRef $completed;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00001(Ref.BooleanRef booleanRef, Continuation<? super C00001> continuation) {
                super(3, continuation);
                this.$completed = booleanRef;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(FlowCollector<? super BackEventCompat> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
                return new C00001(this.$completed, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$completed.element = true;
                return Unit.INSTANCE;
            }
        }
    }

    @Override // androidx.activity.compose.internal.BackHandlerCompat
    public void onBackStarted(BackEventCompat event) {
        onBackCancelled();
        if (isBackEnabled()) {
            this.isPredictiveBack = true;
            launchNewGesture();
        }
    }

    @Override // androidx.activity.compose.internal.BackHandlerCompat
    public void onBackProgressed(BackEventCompat event) {
        Channel<BackEventCompat> channel = this.activeChannel;
        if (channel != null) {
            ChannelResult.m10495boximpl(channel.mo10481trySendJP2dKIU(event));
        }
    }

    @Override // androidx.activity.compose.internal.BackHandlerCompat
    public void onBackCompleted() {
        if (this.activeChannel != null && !this.isPredictiveBack) {
            onBackCancelled();
        }
        if (this.activeChannel == null) {
            this.isPredictiveBack = false;
            launchNewGesture();
        }
        Channel<BackEventCompat> channel = this.activeChannel;
        if (channel != null) {
            SendChannel.CC.close$default(channel, null, 1, null);
        }
        this.isPredictiveBack = false;
    }

    @Override // androidx.activity.compose.internal.BackHandlerCompat
    public void onBackCancelled() {
        Channel<BackEventCompat> channel = this.activeChannel;
        if (channel != null) {
            channel.cancel(new CancellationException("onBack cancelled"));
        }
        Job job = this.activeJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.activeChannel = null;
        this.activeJob = null;
        this.isPredictiveBack = false;
    }
}
