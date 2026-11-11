package com.lagradost.cloudstream3.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0001HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001HÆ\u0001J\u0014\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/utils/TypeofExpr;", "Lcom/lagradost/cloudstream3/utils/Node;", "expr", "<init>", "(Lcom/lagradost/cloudstream3/utils/Node;)V", "getExpr", "()Lcom/lagradost/cloudstream3/utils/Node;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class TypeofExpr extends Node {
    private final Node expr;

    public static /* synthetic */ TypeofExpr copy$default(TypeofExpr typeofExpr, Node node, int i, Object obj) {
        if ((i & 1) != 0) {
            node = typeofExpr.expr;
        }
        return typeofExpr.copy(node);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Node getExpr() {
        return this.expr;
    }

    public final TypeofExpr copy(Node expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        return new TypeofExpr(expr);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TypeofExpr) && Intrinsics.areEqual(this.expr, ((TypeofExpr) other).expr);
    }

    public int hashCode() {
        return this.expr.hashCode();
    }

    public String toString() {
        return "TypeofExpr(expr=" + this.expr + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TypeofExpr(Node expr) {
        super(null);
        Intrinsics.checkNotNullParameter(expr, "expr");
        this.expr = expr;
    }

    public final Node getExpr() {
        return this.expr;
    }
}
