package io.ktor.util.pipeline;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;

/* JADX INFO: compiled from: SuspendFunctionGun.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00009\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0017\u0010\u0007\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\r\u001a\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bH\u0096\u0080\u0004¢\u0006\u0004\b\r\u0010\u000eR\u001c\u0010\u0011\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0015\u0010\u001c\u001a\u00020\u00198VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, d2 = {"io/ktor/util/pipeline/SuspendFunctionGun$continuation$1", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lio/ktor/util/CoroutineStackFrame;", "Ljava/lang/StackTraceElement;", "Lio/ktor/util/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "peekContinuation", "()Lkotlin/coroutines/Continuation;", "Lkotlin/Result;", "result", "resumeWith", "(Ljava/lang/Object;)V", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "", "currentIndex", "I", "getCurrentIndex", "()I", "setCurrentIndex", "(I)V", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "context", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SuspendFunctionGun$continuation$1 implements Continuation<Unit>, CoroutineStackFrame {
    private int currentIndex = Integer.MIN_VALUE;
    final /* synthetic */ SuspendFunctionGun<TSubject, TContext> this$0;

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    SuspendFunctionGun$continuation$1(SuspendFunctionGun<TSubject, TContext> suspendFunctionGun) {
        this.this$0 = suspendFunctionGun;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<?> continuationPeekContinuation = peekContinuation();
        if (continuationPeekContinuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuationPeekContinuation;
        }
        return null;
    }

    public final int getCurrentIndex() {
        return this.currentIndex;
    }

    public final void setCurrentIndex(int i) {
        this.currentIndex = i;
    }

    private final Continuation<?> peekContinuation() {
        if (this.currentIndex == Integer.MIN_VALUE) {
            this.currentIndex = ((SuspendFunctionGun) this.this$0).lastSuspensionIndex;
        }
        if (this.currentIndex >= 0) {
            try {
                Continuation<?>[] continuationArr = ((SuspendFunctionGun) this.this$0).suspensions;
                int i = this.currentIndex;
                Continuation<?> continuation = continuationArr[i];
                if (continuation == null) {
                    return StackWalkingFailedFrame.INSTANCE;
                }
                this.currentIndex = i - 1;
                return continuation;
            } catch (Throwable unused) {
                return StackWalkingFailedFrame.INSTANCE;
            }
        }
        this.currentIndex = Integer.MIN_VALUE;
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    /* JADX INFO: renamed from: getContext */
    public CoroutineContext get$context() {
        for (int i = ((SuspendFunctionGun) this.this$0).lastSuspensionIndex; -1 < i; i--) {
            Continuation continuation = ((SuspendFunctionGun) this.this$0).suspensions[i];
            if (continuation != this && continuation != null) {
                return continuation.get$context();
            }
        }
        throw new IllegalStateException("Not started".toString());
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object result) {
        SuspendFunctionGun<TSubject, TContext> suspendFunctionGun = this.this$0;
        Throwable thM8939exceptionOrNullimpl = Result.m8939exceptionOrNullimpl(result);
        if (thM8939exceptionOrNullimpl == null) {
            this.this$0.loop(false);
        } else {
            Result.Companion companion = Result.INSTANCE;
            suspendFunctionGun.resumeRootWith(Result.m8936constructorimpl(ResultKt.createFailure(thM8939exceptionOrNullimpl)));
        }
    }
}
