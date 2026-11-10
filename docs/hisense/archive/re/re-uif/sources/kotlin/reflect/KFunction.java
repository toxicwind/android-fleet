package kotlin.reflect;

import kotlin.Function;

/* JADX INFO: compiled from: KFunction.kt */
/* JADX INFO: loaded from: classes.dex */
public interface KFunction<R> extends KCallable<R>, Function<R> {
    boolean isExternal();

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    @Override // kotlin.reflect.KCallable
    boolean isSuspend();
}
