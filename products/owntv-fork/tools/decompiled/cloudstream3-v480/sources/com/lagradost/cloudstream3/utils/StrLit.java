package com.lagradost.cloudstream3.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/utils/StrLit;", "Lcom/lagradost/cloudstream3/utils/Node;", "v", "", "<init>", "(Ljava/lang/String;)V", "getV", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class StrLit extends Node {
    private final String v;

    public static /* synthetic */ StrLit copy$default(StrLit strLit, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = strLit.v;
        }
        return strLit.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getV() {
        return this.v;
    }

    public final StrLit copy(String v) {
        Intrinsics.checkNotNullParameter(v, "v");
        return new StrLit(v);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof StrLit) && Intrinsics.areEqual(this.v, ((StrLit) other).v);
    }

    public int hashCode() {
        return this.v.hashCode();
    }

    public String toString() {
        return "StrLit(v=" + this.v + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StrLit(String v) {
        super(null);
        Intrinsics.checkNotNullParameter(v, "v");
        this.v = v;
    }

    public final String getV() {
        return this.v;
    }
}
