package io.ktor.util.pipeline;

import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;

/* JADX INFO: compiled from: SuspendFunctionGun.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004Bh\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00028\u0001\u0012O\u0010\r\u001aK\u0012G\u0012E\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u000b¢\u0006\u0002\b\f0\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00028\u0000H\u0096@¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0000H\u0096@¢\u0006\u0004\b\u0015\u0010\u0016J\u0018\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00028\u0000H\u0090@¢\u0006\u0004\b\u0017\u0010\u0016J\u0017\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u001d\u0010\u001f\u001a\u00020\n2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\nH\u0002¢\u0006\u0004\b!\u0010\u0011J\u001d\u0010%\u001a\u00020\n2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0000¢\u0006\u0004\b#\u0010$R]\u0010\r\u001aK\u0012G\u0012E\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u000b¢\u0006\u0002\b\f0\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010&R\u0014\u0010*\u001a\u00020'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R \u0010\"\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\"\u0010+\u001a\u0004\b,\u0010-R\"\u0010\u0014\u001a\u00028\u00008\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u0010 R\"\u00103\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0016\u00106\u001a\u0002058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0016\u00108\u001a\u0002058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b8\u00107¨\u00069"}, d2 = {"Lio/ktor/util/pipeline/SuspendFunctionGun;", "", "TSubject", "TContext", "Lio/ktor/util/pipeline/PipelineContext;", "initial", "context", "", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "", "Lio/ktor/util/pipeline/PipelineInterceptor;", "Lkotlin/ExtensionFunctionType;", "interceptors", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/List;)V", "finish", "()V", "proceed", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "subject", "proceedWith", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "execute$ktor_utils", "execute", "", "direct", "loop", "(Z)Z", "Lkotlin/Result;", "result", "resumeRootWith", "(Ljava/lang/Object;)V", "discardLastRootContinuation", "continuation", "addContinuation$ktor_utils", "(Lkotlin/coroutines/Continuation;)V", "addContinuation", "Ljava/util/List;", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "Lkotlin/coroutines/Continuation;", "getContinuation$ktor_utils", "()Lkotlin/coroutines/Continuation;", "Ljava/lang/Object;", "getSubject", "()Ljava/lang/Object;", "setSubject", "", "suspensions", "[Lkotlin/coroutines/Continuation;", "", "lastSuspensionIndex", "I", "index", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SuspendFunctionGun<TSubject, TContext> extends PipelineContext<TSubject, TContext> {
    private final Continuation<Unit> continuation;
    private int index;
    private final List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> interceptors;
    private int lastSuspensionIndex;
    private TSubject subject;
    private final Continuation<TSubject>[] suspensions;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SuspendFunctionGun(TSubject initial, TContext context, List<? extends Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object>> interceptors) {
        super(context);
        Intrinsics.checkNotNullParameter(initial, "initial");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(interceptors, "interceptors");
        this.interceptors = interceptors;
        this.continuation = new SuspendFunctionGun$continuation$1(this);
        this.subject = initial;
        this.suspensions = new Continuation[interceptors.size()];
        this.lastSuspensionIndex = -1;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.continuation.get$context();
    }

    public final Continuation<Unit> getContinuation$ktor_utils() {
        return this.continuation;
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public TSubject getSubject() {
        return this.subject;
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public void setSubject(TSubject tsubject) {
        Intrinsics.checkNotNullParameter(tsubject, "<set-?>");
        this.subject = tsubject;
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public void finish() {
        this.index = this.interceptors.size();
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object proceed(Continuation<? super TSubject> continuation) {
        Object coroutine_suspended;
        if (this.index == this.interceptors.size()) {
            coroutine_suspended = getSubject();
        } else {
            addContinuation$ktor_utils(continuation);
            if (loop(true)) {
                discardLastRootContinuation();
                coroutine_suspended = getSubject();
            } else {
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
        }
        if (coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return coroutine_suspended;
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object proceedWith(TSubject tsubject, Continuation<? super TSubject> continuation) {
        setSubject(tsubject);
        return proceed(continuation);
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object execute$ktor_utils(TSubject tsubject, Continuation<? super TSubject> continuation) {
        this.index = 0;
        if (this.interceptors.size() == 0) {
            return tsubject;
        }
        setSubject(tsubject);
        if (this.lastSuspensionIndex >= 0) {
            throw new IllegalStateException("Already started".toString());
        }
        return proceed(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean loop(boolean direct) {
        int i;
        do {
            i = this.index;
            if (i == this.interceptors.size()) {
                if (direct) {
                    return true;
                }
                Result.Companion companion = Result.INSTANCE;
                resumeRootWith(Result.m8936constructorimpl(getSubject()));
                return false;
            }
            this.index = i + 1;
            try {
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                resumeRootWith(Result.m8936constructorimpl(ResultKt.createFailure(th)));
                return false;
            }
        } while (PipelineJvmKt.pipelineStartCoroutineUninterceptedOrReturn(this.interceptors.get(i), this, getSubject(), this.continuation) != IntrinsicsKt.getCOROUTINE_SUSPENDED());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeRootWith(Object result) {
        int i = this.lastSuspensionIndex;
        if (i < 0) {
            throw new IllegalStateException("No more continuations to resume".toString());
        }
        Continuation<TSubject> continuation = this.suspensions[i];
        Intrinsics.checkNotNull(continuation);
        Continuation<TSubject>[] continuationArr = this.suspensions;
        int i2 = this.lastSuspensionIndex;
        this.lastSuspensionIndex = i2 - 1;
        continuationArr[i2] = null;
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) continuation.get$context().get(ContinuationInterceptor.INSTANCE);
        Continuation<TSubject> continuationIntercepted = (!(continuationInterceptor instanceof CoroutineDispatcher) || ((CoroutineDispatcher) continuationInterceptor).isDispatchNeeded(continuation.get$context())) ? IntrinsicsKt.intercepted(continuation) : continuation;
        Throwable thM8939exceptionOrNullimpl = Result.m8939exceptionOrNullimpl(result);
        if (thM8939exceptionOrNullimpl == null) {
            continuationIntercepted.resumeWith(result);
            return;
        }
        Throwable thRecoverStackTraceBridge = StackTraceRecoverKt.recoverStackTraceBridge(thM8939exceptionOrNullimpl, continuation);
        Result.Companion companion = Result.INSTANCE;
        continuationIntercepted.resumeWith(Result.m8936constructorimpl(ResultKt.createFailure(thRecoverStackTraceBridge)));
    }

    private final void discardLastRootContinuation() {
        int i = this.lastSuspensionIndex;
        if (i < 0) {
            throw new IllegalStateException("No more continuations to resume".toString());
        }
        Continuation<TSubject>[] continuationArr = this.suspensions;
        this.lastSuspensionIndex = i - 1;
        continuationArr[i] = null;
    }

    public final void addContinuation$ktor_utils(Continuation<? super TSubject> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        Continuation<TSubject>[] continuationArr = this.suspensions;
        int i = this.lastSuspensionIndex + 1;
        this.lastSuspensionIndex = i;
        continuationArr[i] = continuation;
    }
}
