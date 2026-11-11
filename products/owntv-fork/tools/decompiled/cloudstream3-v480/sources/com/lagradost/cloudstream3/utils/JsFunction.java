package com.lagradost.cloudstream3.utils;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B5\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/utils/JsFunction;", "", "name", "", "params", "", TtmlNode.TAG_BODY, "Lcom/lagradost/cloudstream3/utils/Node;", "closure", "Lcom/lagradost/cloudstream3/utils/Scope;", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/lagradost/cloudstream3/utils/Scope;)V", "getName", "()Ljava/lang/String;", "getParams", "()Ljava/util/List;", "getBody", "getClosure", "()Lcom/lagradost/cloudstream3/utils/Scope;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class JsFunction {
    private final List<Node> body;
    private final Scope closure;
    private final String name;
    private final List<String> params;

    /* JADX WARN: Multi-variable type inference failed */
    public JsFunction(String str, List<String> params, List<? extends Node> body, Scope closure) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(closure, "closure");
        this.name = str;
        this.params = params;
        this.body = body;
        this.closure = closure;
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getParams() {
        return this.params;
    }

    public final List<Node> getBody() {
        return this.body;
    }

    public final Scope getClosure() {
        return this.closure;
    }
}
