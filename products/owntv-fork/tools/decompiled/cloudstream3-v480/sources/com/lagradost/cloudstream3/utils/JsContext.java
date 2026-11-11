package com.lagradost.cloudstream3.utils;

import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B%\b\u0000\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001c\u001a\u00020\u0019H\u0086\u0002J\u001b\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u0086\u0002R\u001c\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/utils/JsContext;", "", "maxExecutionTime", "Lkotlin/time/Duration;", "maxInstructions", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "<init>", "(JJLkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getMaxExecutionTime-UwyO8pc", "()J", "setMaxExecutionTime-LRDsOJo", "(J)V", "J", "getMaxInstructions", "setMaxInstructions", "interpreter", "Lcom/lagradost/cloudstream3/utils/JsInterpreter;", "getInterpreter", "()Lcom/lagradost/cloudstream3/utils/JsInterpreter;", "interpreter$delegate", "Lkotlin/Lazy;", "eval", "code", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "get", "name", "set", "", ES6Iterator.VALUE_PROPERTY, "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JsContext {

    /* JADX INFO: renamed from: interpreter$delegate, reason: from kotlin metadata */
    private final Lazy interpreter;
    private long maxExecutionTime;
    private long maxInstructions;
    private final CoroutineScope scope;

    public /* synthetic */ JsContext(long j, long j2, CoroutineScope coroutineScope, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, coroutineScope);
    }

    private JsContext(long j, long j2, CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.maxExecutionTime = j;
        this.maxInstructions = j2;
        this.scope = scope;
        this.interpreter = LazyKt.lazy(new Function0() { // from class: com.lagradost.cloudstream3.utils.JsContext$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsContext.interpreter_delegate$lambda$0(this.f$0);
            }
        });
    }

    public /* synthetic */ JsContext(long j, long j2, CoroutineScope coroutineScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? JsInterpreterKt.JS_DEFAULT_MAX_EXECUTION_TIME : j, (i & 2) != 0 ? 50000000L : j2, coroutineScope, null);
    }

    /* JADX INFO: renamed from: getMaxExecutionTime-UwyO8pc, reason: not valid java name and from getter */
    public final long getMaxExecutionTime() {
        return this.maxExecutionTime;
    }

    /* JADX INFO: renamed from: setMaxExecutionTime-LRDsOJo, reason: not valid java name */
    public final void m8640setMaxExecutionTimeLRDsOJo(long j) {
        this.maxExecutionTime = j;
    }

    public final long getMaxInstructions() {
        return this.maxInstructions;
    }

    public final void setMaxInstructions(long j) {
        this.maxInstructions = j;
    }

    private final JsInterpreter getInterpreter() {
        return (JsInterpreter) this.interpreter.getValue();
    }

    static final JsInterpreter interpreter_delegate$lambda$0(JsContext jsContext) {
        return new JsInterpreter(jsContext.maxExecutionTime, jsContext.maxInstructions, jsContext.scope, null);
    }

    public final Object eval(String str, Continuation<Object> continuation) throws CancellationException {
        return getInterpreter().eval(str);
    }

    public final Object get(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getInterpreter().getVar(name);
    }

    public final void set(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        getInterpreter().setVar(name, value);
    }
}
