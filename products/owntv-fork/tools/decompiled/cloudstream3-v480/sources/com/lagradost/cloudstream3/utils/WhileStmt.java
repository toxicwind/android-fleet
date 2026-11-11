package com.lagradost.cloudstream3.utils;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\t\u001a\u00020\u0001HÆ\u0003J\t\u0010\n\u001a\u00020\u0001HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001HÆ\u0001J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/utils/WhileStmt;", "Lcom/lagradost/cloudstream3/utils/Node;", "test", TtmlNode.TAG_BODY, "<init>", "(Lcom/lagradost/cloudstream3/utils/Node;Lcom/lagradost/cloudstream3/utils/Node;)V", "getTest", "()Lcom/lagradost/cloudstream3/utils/Node;", "getBody", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class WhileStmt extends Node {
    private final Node body;
    private final Node test;

    public static /* synthetic */ WhileStmt copy$default(WhileStmt whileStmt, Node node, Node node2, int i, Object obj) {
        if ((i & 1) != 0) {
            node = whileStmt.test;
        }
        if ((i & 2) != 0) {
            node2 = whileStmt.body;
        }
        return whileStmt.copy(node, node2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Node getTest() {
        return this.test;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Node getBody() {
        return this.body;
    }

    public final WhileStmt copy(Node test, Node body) {
        Intrinsics.checkNotNullParameter(test, "test");
        Intrinsics.checkNotNullParameter(body, "body");
        return new WhileStmt(test, body);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WhileStmt)) {
            return false;
        }
        WhileStmt whileStmt = (WhileStmt) other;
        return Intrinsics.areEqual(this.test, whileStmt.test) && Intrinsics.areEqual(this.body, whileStmt.body);
    }

    public int hashCode() {
        return (this.test.hashCode() * 31) + this.body.hashCode();
    }

    public String toString() {
        return "WhileStmt(test=" + this.test + ", body=" + this.body + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WhileStmt(Node test, Node body) {
        super(null);
        Intrinsics.checkNotNullParameter(test, "test");
        Intrinsics.checkNotNullParameter(body, "body");
        this.test = test;
        this.body = body;
    }

    public final Node getBody() {
        return this.body;
    }

    public final Node getTest() {
        return this.test;
    }
}
