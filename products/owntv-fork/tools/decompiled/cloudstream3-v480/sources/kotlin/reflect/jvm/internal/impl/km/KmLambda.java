package kotlin.reflect.jvm.internal.impl.km;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Nodes.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class KmLambda {
    public KmFunction function;

    public final void setFunction(KmFunction kmFunction) {
        Intrinsics.checkNotNullParameter(kmFunction, "<set-?>");
        this.function = kmFunction;
    }
}
