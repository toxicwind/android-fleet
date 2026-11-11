package com.lagradost.cloudstream3.utils;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005HÆ\u0003J5\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/utils/FuncExpr;", "Lcom/lagradost/cloudstream3/utils/Node;", "name", "", "params", "", TtmlNode.TAG_BODY, "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getParams", "()Ljava/util/List;", "getBody", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class FuncExpr extends Node {
    private final List<Node> body;
    private final String name;
    private final List<String> params;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FuncExpr copy$default(FuncExpr funcExpr, String str, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = funcExpr.name;
        }
        if ((i & 2) != 0) {
            list = funcExpr.params;
        }
        if ((i & 4) != 0) {
            list2 = funcExpr.body;
        }
        return funcExpr.copy(str, list, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<String> component2() {
        return this.params;
    }

    public final List<Node> component3() {
        return this.body;
    }

    public final FuncExpr copy(String name, List<String> params, List<? extends Node> body) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(body, "body");
        return new FuncExpr(name, params, body);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FuncExpr)) {
            return false;
        }
        FuncExpr funcExpr = (FuncExpr) other;
        return Intrinsics.areEqual(this.name, funcExpr.name) && Intrinsics.areEqual(this.params, funcExpr.params) && Intrinsics.areEqual(this.body, funcExpr.body);
    }

    public int hashCode() {
        String str = this.name;
        return ((((str == null ? 0 : str.hashCode()) * 31) + this.params.hashCode()) * 31) + this.body.hashCode();
    }

    public String toString() {
        return "FuncExpr(name=" + this.name + ", params=" + this.params + ", body=" + this.body + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FuncExpr(String str, List<String> params, List<? extends Node> body) {
        super(null);
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(body, "body");
        this.name = str;
        this.params = params;
        this.body = body;
    }

    public final List<Node> getBody() {
        return this.body;
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getParams() {
        return this.params;
    }
}
