package com.lagradost.cloudstream3.utils;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0001HÆ\u0003J7\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0001HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ForStmt;", "Lcom/lagradost/cloudstream3/utils/Node;", "init", "test", "update", TtmlNode.TAG_BODY, "<init>", "(Lcom/lagradost/cloudstream3/utils/Node;Lcom/lagradost/cloudstream3/utils/Node;Lcom/lagradost/cloudstream3/utils/Node;Lcom/lagradost/cloudstream3/utils/Node;)V", "getInit", "()Lcom/lagradost/cloudstream3/utils/Node;", "getTest", "getUpdate", "getBody", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class ForStmt extends Node {
    private final Node body;
    private final Node init;
    private final Node test;
    private final Node update;

    public static /* synthetic */ ForStmt copy$default(ForStmt forStmt, Node node, Node node2, Node node3, Node node4, int i, Object obj) {
        if ((i & 1) != 0) {
            node = forStmt.init;
        }
        if ((i & 2) != 0) {
            node2 = forStmt.test;
        }
        if ((i & 4) != 0) {
            node3 = forStmt.update;
        }
        if ((i & 8) != 0) {
            node4 = forStmt.body;
        }
        return forStmt.copy(node, node2, node3, node4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Node getInit() {
        return this.init;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Node getTest() {
        return this.test;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Node getUpdate() {
        return this.update;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Node getBody() {
        return this.body;
    }

    public final ForStmt copy(Node init, Node test, Node update, Node body) {
        Intrinsics.checkNotNullParameter(body, "body");
        return new ForStmt(init, test, update, body);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ForStmt)) {
            return false;
        }
        ForStmt forStmt = (ForStmt) other;
        return Intrinsics.areEqual(this.init, forStmt.init) && Intrinsics.areEqual(this.test, forStmt.test) && Intrinsics.areEqual(this.update, forStmt.update) && Intrinsics.areEqual(this.body, forStmt.body);
    }

    public int hashCode() {
        Node node = this.init;
        int iHashCode = (node == null ? 0 : node.hashCode()) * 31;
        Node node2 = this.test;
        int iHashCode2 = (iHashCode + (node2 == null ? 0 : node2.hashCode())) * 31;
        Node node3 = this.update;
        return ((iHashCode2 + (node3 != null ? node3.hashCode() : 0)) * 31) + this.body.hashCode();
    }

    public String toString() {
        return "ForStmt(init=" + this.init + ", test=" + this.test + ", update=" + this.update + ", body=" + this.body + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ForStmt(Node node, Node node2, Node node3, Node body) {
        super(null);
        Intrinsics.checkNotNullParameter(body, "body");
        this.init = node;
        this.test = node2;
        this.update = node3;
        this.body = body;
    }

    public final Node getBody() {
        return this.body;
    }

    public final Node getInit() {
        return this.init;
    }

    public final Node getTest() {
        return this.test;
    }

    public final Node getUpdate() {
        return this.update;
    }
}
