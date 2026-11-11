package com.lagradost.cloudstream3.utils;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B?\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003HÆ\u0003JI\u0010\u0014\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0005HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/utils/TryCatch;", "Lcom/lagradost/cloudstream3/utils/Node;", TtmlNode.TAG_BODY, "", "catchParam", "", "catchBody", "finallyBody", "<init>", "(Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getBody", "()Ljava/util/List;", "getCatchParam", "()Ljava/lang/String;", "getCatchBody", "getFinallyBody", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class TryCatch extends Node {
    private final List<Node> body;
    private final List<Node> catchBody;
    private final String catchParam;
    private final List<Node> finallyBody;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TryCatch copy$default(TryCatch tryCatch, List list, String str, List list2, List list3, int i, Object obj) {
        if ((i & 1) != 0) {
            list = tryCatch.body;
        }
        if ((i & 2) != 0) {
            str = tryCatch.catchParam;
        }
        if ((i & 4) != 0) {
            list2 = tryCatch.catchBody;
        }
        if ((i & 8) != 0) {
            list3 = tryCatch.finallyBody;
        }
        return tryCatch.copy(list, str, list2, list3);
    }

    public final List<Node> component1() {
        return this.body;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCatchParam() {
        return this.catchParam;
    }

    public final List<Node> component3() {
        return this.catchBody;
    }

    public final List<Node> component4() {
        return this.finallyBody;
    }

    public final TryCatch copy(List<? extends Node> body, String catchParam, List<? extends Node> catchBody, List<? extends Node> finallyBody) {
        Intrinsics.checkNotNullParameter(body, "body");
        return new TryCatch(body, catchParam, catchBody, finallyBody);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TryCatch)) {
            return false;
        }
        TryCatch tryCatch = (TryCatch) other;
        return Intrinsics.areEqual(this.body, tryCatch.body) && Intrinsics.areEqual(this.catchParam, tryCatch.catchParam) && Intrinsics.areEqual(this.catchBody, tryCatch.catchBody) && Intrinsics.areEqual(this.finallyBody, tryCatch.finallyBody);
    }

    public int hashCode() {
        int iHashCode = this.body.hashCode() * 31;
        String str = this.catchParam;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        List<Node> list = this.catchBody;
        int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        List<Node> list2 = this.finallyBody;
        return iHashCode3 + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "TryCatch(body=" + this.body + ", catchParam=" + this.catchParam + ", catchBody=" + this.catchBody + ", finallyBody=" + this.finallyBody + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TryCatch(List<? extends Node> body, String str, List<? extends Node> list, List<? extends Node> list2) {
        super(null);
        Intrinsics.checkNotNullParameter(body, "body");
        this.body = body;
        this.catchParam = str;
        this.catchBody = list;
        this.finallyBody = list2;
    }

    public final List<Node> getBody() {
        return this.body;
    }

    public final List<Node> getCatchBody() {
        return this.catchBody;
    }

    public final String getCatchParam() {
        return this.catchParam;
    }

    public final List<Node> getFinallyBody() {
        return this.finallyBody;
    }
}
