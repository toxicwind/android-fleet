package com.lagradost.cloudstream3.utils;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0001HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0001HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0001HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ForInStmt;", "Lcom/lagradost/cloudstream3/utils/Node;", "decl", "", "obj", TtmlNode.TAG_BODY, "<init>", "(Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/Node;Lcom/lagradost/cloudstream3/utils/Node;)V", "getDecl", "()Ljava/lang/String;", "getObj", "()Lcom/lagradost/cloudstream3/utils/Node;", "getBody", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class ForInStmt extends Node {
    private final Node body;
    private final String decl;
    private final Node obj;

    public static /* synthetic */ ForInStmt copy$default(ForInStmt forInStmt, String str, Node node, Node node2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = forInStmt.decl;
        }
        if ((i & 2) != 0) {
            node = forInStmt.obj;
        }
        if ((i & 4) != 0) {
            node2 = forInStmt.body;
        }
        return forInStmt.copy(str, node, node2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDecl() {
        return this.decl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Node getObj() {
        return this.obj;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Node getBody() {
        return this.body;
    }

    public final ForInStmt copy(String decl, Node obj, Node body) {
        Intrinsics.checkNotNullParameter(decl, "decl");
        Intrinsics.checkNotNullParameter(obj, "obj");
        Intrinsics.checkNotNullParameter(body, "body");
        return new ForInStmt(decl, obj, body);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ForInStmt)) {
            return false;
        }
        ForInStmt forInStmt = (ForInStmt) other;
        return Intrinsics.areEqual(this.decl, forInStmt.decl) && Intrinsics.areEqual(this.obj, forInStmt.obj) && Intrinsics.areEqual(this.body, forInStmt.body);
    }

    public int hashCode() {
        return (((this.decl.hashCode() * 31) + this.obj.hashCode()) * 31) + this.body.hashCode();
    }

    public String toString() {
        return "ForInStmt(decl=" + this.decl + ", obj=" + this.obj + ", body=" + this.body + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ForInStmt(String decl, Node obj, Node body) {
        super(null);
        Intrinsics.checkNotNullParameter(decl, "decl");
        Intrinsics.checkNotNullParameter(obj, "obj");
        Intrinsics.checkNotNullParameter(body, "body");
        this.decl = decl;
        this.obj = obj;
        this.body = body;
    }

    public final Node getBody() {
        return this.body;
    }

    public final String getDecl() {
        return this.decl;
    }

    public final Node getObj() {
        return this.obj;
    }
}
