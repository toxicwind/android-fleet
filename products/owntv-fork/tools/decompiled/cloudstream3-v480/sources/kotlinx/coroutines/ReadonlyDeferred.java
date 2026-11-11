package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectClause1;

/* JADX INFO: compiled from: CompletableDeferred.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0097\u0001J\u000e\u0010\u000f\u001a\u00028\u0000H\u0096A¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0097\u0001J\t\u0010\u0011\u001a\u00020\u0015H\u0097\u0001J\u001e\u0010\u0011\u001a\u00020\u00152\u000e\u0010\u0013\u001a\n\u0018\u00010\u0016j\u0004\u0018\u0001`\u0017H\u0096\u0001¢\u0006\u0002\u0010\u0018J<\u0010\u0019\u001a\u0002H\u001a\"\n\b\u0001\u0010\u001a*\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u0002H\u001a2\u0018\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u0002H\u001a0\u001eH\u0096\u0001¢\u0006\u0002\u0010 J(\u0010!\u001a\u0004\u0018\u0001H\"\"\b\b\u0001\u0010\"*\u00020\u001f2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\"0$H\u0096\u0003¢\u0006\u0002\u0010%J\u0012\u0010&\u001a\u00060\u0016j\u0002`\u0017H\u0097\u0001¢\u0006\u0002\u0010'J\u000e\u0010(\u001a\u00028\u0000H\u0097\u0001¢\u0006\u0002\u0010)J\u000b\u0010*\u001a\u0004\u0018\u00010\u0014H\u0097\u0001J2\u0010+\u001a\u00020,2'\u0010-\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0014¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00150.j\u0002`1H\u0096\u0001JB\u0010+\u001a\u00020,2\u0006\u00102\u001a\u00020\u00122\u0006\u00103\u001a\u00020\u00122'\u0010-\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0014¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00150.j\u0002`1H\u0097\u0001J\u000e\u00104\u001a\u00020\u0015H\u0096A¢\u0006\u0002\u0010\u0010J\u0015\u00105\u001a\u0002062\n\u0010#\u001a\u0006\u0012\u0002\b\u00030$H\u0096\u0001J\u0011\u00107\u001a\u0002062\u0006\u00108\u001a\u000206H\u0096\u0003J\u0011\u00107\u001a\u0002092\u0006\u0010:\u001a\u000209H\u0097\u0003J\t\u0010;\u001a\u00020\u0012H\u0096\u0001R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010<\u001a\b\u0012\u0004\u0012\u0002090=X\u0096\u0005¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0012\u0010@\u001a\u00020\u0012X\u0096\u0005¢\u0006\u0006\u001a\u0004\b@\u0010AR\u0012\u0010B\u001a\u00020\u0012X\u0096\u0005¢\u0006\u0006\u001a\u0004\bB\u0010AR\u0012\u0010C\u001a\u00020\u0012X\u0096\u0005¢\u0006\u0006\u001a\u0004\bC\u0010AR\u0016\u0010#\u001a\u0006\u0012\u0002\b\u00030$X\u0096\u0005¢\u0006\u0006\u001a\u0004\bD\u0010ER\u0018\u0010F\u001a\b\u0012\u0004\u0012\u00028\u00000GX\u0096\u0005¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0012\u0010J\u001a\u00020KX\u0096\u0005¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0016\u0010N\u001a\u0004\u0018\u0001098\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\bO\u0010P¨\u0006Q"}, d2 = {"Lkotlinx/coroutines/ReadonlyDeferred;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/Deferred;", "deferred", "Lkotlinx/coroutines/CompletableDeferred;", "<init>", "(Lkotlinx/coroutines/CompletableDeferred;)V", "getDeferred", "()Lkotlinx/coroutines/CompletableDeferred;", "toString", "", "attachChild", "Lkotlinx/coroutines/ChildHandle;", "child", "Lkotlinx/coroutines/ChildJob;", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancel", "", "cause", "", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "fold", "R", "", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", ExifInterface.LONGITUDE_EAST, "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "getCancellationException", "()Ljava/util/concurrent/CancellationException;", "getCompleted", "()Ljava/lang/Object;", "getCompletionExceptionOrNull", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "onCancelling", "invokeImmediately", "join", "minusKey", "Lkotlin/coroutines/CoroutineContext;", "plus", "context", "Lkotlinx/coroutines/Job;", "other", "start", "children", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "isActive", "()Z", "isCancelled", "isCompleted", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "onAwait", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "onJoin", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "parent", "getParent", "()Lkotlinx/coroutines/Job;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class ReadonlyDeferred<T> implements Deferred<T> {
    private final CompletableDeferred<T> deferred;

    @Override // kotlinx.coroutines.Job
    public ChildHandle attachChild(ChildJob child) {
        return this.deferred.attachChild(child);
    }

    @Override // kotlinx.coroutines.Deferred
    public Object await(Continuation<? super T> continuation) {
        return this.deferred.await(continuation);
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        this.deferred.cancel();
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(CancellationException cause) {
        this.deferred.cancel(cause);
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ boolean cancel(Throwable cause) {
        return this.deferred.cancel(cause);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <R> R fold(R initial, Function2<? super R, ? super CoroutineContext.Element, ? extends R> operation) {
        return (R) this.deferred.fold(initial, operation);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return (E) this.deferred.get(key);
    }

    @Override // kotlinx.coroutines.Job
    public CancellationException getCancellationException() {
        return this.deferred.getCancellationException();
    }

    @Override // kotlinx.coroutines.Job
    public Sequence<Job> getChildren() {
        return this.deferred.getChildren();
    }

    @Override // kotlinx.coroutines.Deferred
    public T getCompleted() {
        return this.deferred.getCompleted();
    }

    @Override // kotlinx.coroutines.Deferred
    public Throwable getCompletionExceptionOrNull() {
        return this.deferred.getCompletionExceptionOrNull();
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public CoroutineContext.Key<?> getKey() {
        return this.deferred.getKey();
    }

    @Override // kotlinx.coroutines.Deferred
    public SelectClause1<T> getOnAwait() {
        return this.deferred.getOnAwait();
    }

    @Override // kotlinx.coroutines.Job
    public SelectClause0 getOnJoin() {
        return this.deferred.getOnJoin();
    }

    @Override // kotlinx.coroutines.Job
    public Job getParent() {
        return this.deferred.getParent();
    }

    @Override // kotlinx.coroutines.Job
    public DisposableHandle invokeOnCompletion(Function1<? super Throwable, Unit> handler) {
        return this.deferred.invokeOnCompletion(handler);
    }

    @Override // kotlinx.coroutines.Job
    public DisposableHandle invokeOnCompletion(boolean onCancelling, boolean invokeImmediately, Function1<? super Throwable, Unit> handler) {
        return this.deferred.invokeOnCompletion(onCancelling, invokeImmediately, handler);
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        return this.deferred.isActive();
    }

    @Override // kotlinx.coroutines.Job
    public boolean isCancelled() {
        return this.deferred.isCancelled();
    }

    @Override // kotlinx.coroutines.Job
    public boolean isCompleted() {
        return this.deferred.isCompleted();
    }

    @Override // kotlinx.coroutines.Job
    public Object join(Continuation<? super Unit> continuation) {
        return this.deferred.join(continuation);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return this.deferred.minusKey(key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext context) {
        return this.deferred.plus(context);
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    public Job plus(Job other) {
        return this.deferred.plus(other);
    }

    @Override // kotlinx.coroutines.Job
    public boolean start() {
        return this.deferred.start();
    }

    public ReadonlyDeferred(CompletableDeferred<T> completableDeferred) {
        this.deferred = completableDeferred;
    }

    public final CompletableDeferred<T> getDeferred() {
        return this.deferred;
    }

    public String toString() {
        return "ReadonlyDeferred(" + this.deferred + ')';
    }
}
