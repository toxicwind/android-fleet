package com.lagradost.cloudstream3.utils;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0001HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004HÆ\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/utils/NewExpr;", "Lcom/lagradost/cloudstream3/utils/Node;", "callee", "args", "", "<init>", "(Lcom/lagradost/cloudstream3/utils/Node;Ljava/util/List;)V", "getCallee", "()Lcom/lagradost/cloudstream3/utils/Node;", "getArgs", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class NewExpr extends Node {
    private final List<Node> args;
    private final Node callee;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NewExpr copy$default(NewExpr newExpr, Node node, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            node = newExpr.callee;
        }
        if ((i & 2) != 0) {
            list = newExpr.args;
        }
        return newExpr.copy(node, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Node getCallee() {
        return this.callee;
    }

    public final List<Node> component2() {
        return this.args;
    }

    public final NewExpr copy(Node callee, List<? extends Node> args) {
        Intrinsics.checkNotNullParameter(callee, "callee");
        Intrinsics.checkNotNullParameter(args, "args");
        return new NewExpr(callee, args);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NewExpr)) {
            return false;
        }
        NewExpr newExpr = (NewExpr) other;
        return Intrinsics.areEqual(this.callee, newExpr.callee) && Intrinsics.areEqual(this.args, newExpr.args);
    }

    public int hashCode() {
        return (this.callee.hashCode() * 31) + this.args.hashCode();
    }

    public String toString() {
        return "NewExpr(callee=" + this.callee + ", args=" + this.args + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public NewExpr(Node callee, List<? extends Node> args) {
        super(null);
        Intrinsics.checkNotNullParameter(callee, "callee");
        Intrinsics.checkNotNullParameter(args, "args");
        this.callee = callee;
        this.args = args;
    }

    public final List<Node> getArgs() {
        return this.args;
    }

    public final Node getCallee() {
        return this.callee;
    }
}
