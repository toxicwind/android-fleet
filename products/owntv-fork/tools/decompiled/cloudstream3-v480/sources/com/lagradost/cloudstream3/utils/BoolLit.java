package com.lagradost.cloudstream3.utils;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/utils/BoolLit;", "Lcom/lagradost/cloudstream3/utils/Node;", "v", "", "<init>", "(Z)V", "getV", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class BoolLit extends Node {
    private final boolean v;

    public static /* synthetic */ BoolLit copy$default(BoolLit boolLit, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = boolLit.v;
        }
        return boolLit.copy(z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getV() {
        return this.v;
    }

    public final BoolLit copy(boolean v) {
        return new BoolLit(v);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof BoolLit) && this.v == ((BoolLit) other).v;
    }

    public int hashCode() {
        return NextAiring$$ExternalSyntheticBackport0.m(this.v);
    }

    public String toString() {
        return "BoolLit(v=" + this.v + ')';
    }

    public BoolLit(boolean z) {
        super(null);
        this.v = z;
    }

    public final boolean getV() {
        return this.v;
    }
}
