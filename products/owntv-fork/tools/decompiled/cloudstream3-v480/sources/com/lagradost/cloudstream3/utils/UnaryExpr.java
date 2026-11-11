package com.lagradost.cloudstream3.utils;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0001HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/utils/UnaryExpr;", "Lcom/lagradost/cloudstream3/utils/Node;", "op", "", "expr", "prefix", "", "<init>", "(Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/Node;Z)V", "getOp", "()Ljava/lang/String;", "getExpr", "()Lcom/lagradost/cloudstream3/utils/Node;", "getPrefix", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class UnaryExpr extends Node {
    private final Node expr;
    private final String op;
    private final boolean prefix;

    public static /* synthetic */ UnaryExpr copy$default(UnaryExpr unaryExpr, String str, Node node, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = unaryExpr.op;
        }
        if ((i & 2) != 0) {
            node = unaryExpr.expr;
        }
        if ((i & 4) != 0) {
            z = unaryExpr.prefix;
        }
        return unaryExpr.copy(str, node, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOp() {
        return this.op;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Node getExpr() {
        return this.expr;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getPrefix() {
        return this.prefix;
    }

    public final UnaryExpr copy(String op, Node expr, boolean prefix) {
        Intrinsics.checkNotNullParameter(op, "op");
        Intrinsics.checkNotNullParameter(expr, "expr");
        return new UnaryExpr(op, expr, prefix);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UnaryExpr)) {
            return false;
        }
        UnaryExpr unaryExpr = (UnaryExpr) other;
        return Intrinsics.areEqual(this.op, unaryExpr.op) && Intrinsics.areEqual(this.expr, unaryExpr.expr) && this.prefix == unaryExpr.prefix;
    }

    public int hashCode() {
        return (((this.op.hashCode() * 31) + this.expr.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.prefix);
    }

    public String toString() {
        return "UnaryExpr(op=" + this.op + ", expr=" + this.expr + ", prefix=" + this.prefix + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnaryExpr(String op, Node expr, boolean z) {
        super(null);
        Intrinsics.checkNotNullParameter(op, "op");
        Intrinsics.checkNotNullParameter(expr, "expr");
        this.op = op;
        this.expr = expr;
        this.prefix = z;
    }

    public final Node getExpr() {
        return this.expr;
    }

    public final String getOp() {
        return this.op;
    }

    public final boolean getPrefix() {
        return this.prefix;
    }
}
