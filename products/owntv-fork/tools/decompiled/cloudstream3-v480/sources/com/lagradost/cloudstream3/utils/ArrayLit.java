package com.lagradost.cloudstream3.utils;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ArrayLit;", "Lcom/lagradost/cloudstream3/utils/Node;", "elems", "", "<init>", "(Ljava/util/List;)V", "getElems", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class ArrayLit extends Node {
    private final List<Node> elems;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ArrayLit copy$default(ArrayLit arrayLit, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = arrayLit.elems;
        }
        return arrayLit.copy(list);
    }

    public final List<Node> component1() {
        return this.elems;
    }

    public final ArrayLit copy(List<? extends Node> elems) {
        Intrinsics.checkNotNullParameter(elems, "elems");
        return new ArrayLit(elems);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ArrayLit) && Intrinsics.areEqual(this.elems, ((ArrayLit) other).elems);
    }

    public int hashCode() {
        return this.elems.hashCode();
    }

    public String toString() {
        return "ArrayLit(elems=" + this.elems + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ArrayLit(List<? extends Node> elems) {
        super(null);
        Intrinsics.checkNotNullParameter(elems, "elems");
        this.elems = elems;
    }

    public final List<Node> getElems() {
        return this.elems;
    }
}
