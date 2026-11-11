package io.ktor.http.content;

import kotlin.Function;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: Multipart.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final class MultipartKt$sam$kotlinx_coroutines_flow_FlowCollector$0 implements FlowCollector, FunctionAdapter {
    private final /* synthetic */ Function2<P1, Continuation<? super R>, Object> function;

    /* JADX WARN: Multi-variable type inference failed */
    MultipartKt$sam$kotlinx_coroutines_flow_FlowCollector$0(Function2<? super P1, ? super Continuation<? super R>, ? extends Object> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        this.function = function;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // kotlinx.coroutines.flow.FlowCollector
    public final /* synthetic */ Object emit(Object obj, Continuation continuation) {
        return this.function.invoke((P1) obj, (Continuation<? super R>) continuation);
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
            return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
        }
        return false;
    }

    @Override // kotlin.jvm.internal.FunctionAdapter
    public final Function<?> getFunctionDelegate() {
        return this.function;
    }

    public final int hashCode() {
        return getFunctionDelegate().hashCode();
    }
}
