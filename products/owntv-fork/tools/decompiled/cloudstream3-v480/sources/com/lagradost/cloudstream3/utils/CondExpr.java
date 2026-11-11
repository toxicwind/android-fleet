package com.lagradost.cloudstream3.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0001HÆ\u0003J\t\u0010\f\u001a\u00020\u0001HÆ\u0003J\t\u0010\r\u001a\u00020\u0001HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0001HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/utils/CondExpr;", "Lcom/lagradost/cloudstream3/utils/Node;", "test", "cons", "alt", "<init>", "(Lcom/lagradost/cloudstream3/utils/Node;Lcom/lagradost/cloudstream3/utils/Node;Lcom/lagradost/cloudstream3/utils/Node;)V", "getTest", "()Lcom/lagradost/cloudstream3/utils/Node;", "getCons", "getAlt", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class CondExpr extends Node {
    private final Node alt;
    private final Node cons;
    private final Node test;

    public static /* synthetic */ CondExpr copy$default(CondExpr condExpr, Node node, Node node2, Node node3, int i, Object obj) {
        if ((i & 1) != 0) {
            node = condExpr.test;
        }
        if ((i & 2) != 0) {
            node2 = condExpr.cons;
        }
        if ((i & 4) != 0) {
            node3 = condExpr.alt;
        }
        return condExpr.copy(node, node2, node3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Node getTest() {
        return this.test;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Node getCons() {
        return this.cons;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Node getAlt() {
        return this.alt;
    }

    public final CondExpr copy(Node test, Node cons, Node alt) {
        Intrinsics.checkNotNullParameter(test, "test");
        Intrinsics.checkNotNullParameter(cons, "cons");
        Intrinsics.checkNotNullParameter(alt, "alt");
        return new CondExpr(test, cons, alt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CondExpr)) {
            return false;
        }
        CondExpr condExpr = (CondExpr) other;
        return Intrinsics.areEqual(this.test, condExpr.test) && Intrinsics.areEqual(this.cons, condExpr.cons) && Intrinsics.areEqual(this.alt, condExpr.alt);
    }

    public int hashCode() {
        return (((this.test.hashCode() * 31) + this.cons.hashCode()) * 31) + this.alt.hashCode();
    }

    public String toString() {
        return "CondExpr(test=" + this.test + ", cons=" + this.cons + ", alt=" + this.alt + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CondExpr(Node test, Node cons, Node alt) {
        super(null);
        Intrinsics.checkNotNullParameter(test, "test");
        Intrinsics.checkNotNullParameter(cons, "cons");
        Intrinsics.checkNotNullParameter(alt, "alt");
        this.test = test;
        this.cons = cons;
        this.alt = alt;
    }

    public final Node getAlt() {
        return this.alt;
    }

    public final Node getCons() {
        return this.cons;
    }

    public final Node getTest() {
        return this.test;
    }
}
