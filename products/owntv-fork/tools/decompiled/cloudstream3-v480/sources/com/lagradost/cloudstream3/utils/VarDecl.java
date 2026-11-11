package com.lagradost.cloudstream3.utils;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B#\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\n\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00040\u0003HÆ\u0003J'\u0010\u000b\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00040\u0003HÆ\u0001J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0005HÖ\u0081\u0004R%\u0010\u0002\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/utils/VarDecl;", "Lcom/lagradost/cloudstream3/utils/Node;", "decls", "", "Lkotlin/Pair;", "", "<init>", "(Ljava/util/List;)V", "getDecls", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class VarDecl extends Node {
    private final List<Pair<String, Node>> decls;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VarDecl copy$default(VarDecl varDecl, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = varDecl.decls;
        }
        return varDecl.copy(list);
    }

    public final List<Pair<String, Node>> component1() {
        return this.decls;
    }

    public final VarDecl copy(List<? extends Pair<String, ? extends Node>> decls) {
        Intrinsics.checkNotNullParameter(decls, "decls");
        return new VarDecl(decls);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof VarDecl) && Intrinsics.areEqual(this.decls, ((VarDecl) other).decls);
    }

    public int hashCode() {
        return this.decls.hashCode();
    }

    public String toString() {
        return "VarDecl(decls=" + this.decls + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public VarDecl(List<? extends Pair<String, ? extends Node>> decls) {
        super(null);
        Intrinsics.checkNotNullParameter(decls, "decls");
        this.decls = decls;
    }

    public final List<Pair<String, Node>> getDecls() {
        return this.decls;
    }
}
