package com.lagradost.cloudstream3.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001HÆ\u0001J\u0014\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ReturnStmt;", "Lcom/lagradost/cloudstream3/utils/Node;", "expr", "<init>", "(Lcom/lagradost/cloudstream3/utils/Node;)V", "getExpr", "()Lcom/lagradost/cloudstream3/utils/Node;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class ReturnStmt extends Node {
    private final Node expr;

    public static /* synthetic */ ReturnStmt copy$default(ReturnStmt returnStmt, Node node, int i, Object obj) {
        if ((i & 1) != 0) {
            node = returnStmt.expr;
        }
        return returnStmt.copy(node);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Node getExpr() {
        return this.expr;
    }

    public final ReturnStmt copy(Node expr) {
        return new ReturnStmt(expr);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ReturnStmt) && Intrinsics.areEqual(this.expr, ((ReturnStmt) other).expr);
    }

    public int hashCode() {
        Node node = this.expr;
        if (node == null) {
            return 0;
        }
        return node.hashCode();
    }

    public String toString() {
        return "ReturnStmt(expr=" + this.expr + ')';
    }

    public ReturnStmt(Node node) {
        super(null);
        this.expr = node;
    }

    public final Node getExpr() {
        return this.expr;
    }
}
