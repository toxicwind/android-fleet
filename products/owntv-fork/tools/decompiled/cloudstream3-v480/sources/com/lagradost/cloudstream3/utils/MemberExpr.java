package com.lagradost.cloudstream3.utils;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\r\u001a\u00020\u0001HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0001HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/utils/MemberExpr;", "Lcom/lagradost/cloudstream3/utils/Node;", "obj", "prop", "computed", "", "<init>", "(Lcom/lagradost/cloudstream3/utils/Node;Lcom/lagradost/cloudstream3/utils/Node;Z)V", "getObj", "()Lcom/lagradost/cloudstream3/utils/Node;", "getProp", "getComputed", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class MemberExpr extends Node {
    private final boolean computed;
    private final Node obj;
    private final Node prop;

    public static /* synthetic */ MemberExpr copy$default(MemberExpr memberExpr, Node node, Node node2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            node = memberExpr.obj;
        }
        if ((i & 2) != 0) {
            node2 = memberExpr.prop;
        }
        if ((i & 4) != 0) {
            z = memberExpr.computed;
        }
        return memberExpr.copy(node, node2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Node getObj() {
        return this.obj;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Node getProp() {
        return this.prop;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getComputed() {
        return this.computed;
    }

    public final MemberExpr copy(Node obj, Node prop, boolean computed) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        Intrinsics.checkNotNullParameter(prop, "prop");
        return new MemberExpr(obj, prop, computed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MemberExpr)) {
            return false;
        }
        MemberExpr memberExpr = (MemberExpr) other;
        return Intrinsics.areEqual(this.obj, memberExpr.obj) && Intrinsics.areEqual(this.prop, memberExpr.prop) && this.computed == memberExpr.computed;
    }

    public int hashCode() {
        return (((this.obj.hashCode() * 31) + this.prop.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.computed);
    }

    public String toString() {
        return "MemberExpr(obj=" + this.obj + ", prop=" + this.prop + ", computed=" + this.computed + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MemberExpr(Node obj, Node prop, boolean z) {
        super(null);
        Intrinsics.checkNotNullParameter(obj, "obj");
        Intrinsics.checkNotNullParameter(prop, "prop");
        this.obj = obj;
        this.prop = prop;
        this.computed = z;
    }

    public final boolean getComputed() {
        return this.computed;
    }

    public final Node getObj() {
        return this.obj;
    }

    public final Node getProp() {
        return this.prop;
    }
}
