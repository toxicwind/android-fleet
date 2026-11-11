package com.lagradost.cloudstream3.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: Levenshtein.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/utils/MatchingBlock;", "", "spos", "", "dpos", "length", "<init>", "(III)V", "getSpos", "()I", "getDpos", "getLength", "toString", "", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class MatchingBlock {
    private final int dpos;
    private final int length;
    private final int spos;

    public MatchingBlock() {
        this(0, 0, 0, 7, null);
    }

    public static /* synthetic */ MatchingBlock copy$default(MatchingBlock matchingBlock, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = matchingBlock.spos;
        }
        if ((i4 & 2) != 0) {
            i2 = matchingBlock.dpos;
        }
        if ((i4 & 4) != 0) {
            i3 = matchingBlock.length;
        }
        return matchingBlock.copy(i, i2, i3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getSpos() {
        return this.spos;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getDpos() {
        return this.dpos;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getLength() {
        return this.length;
    }

    public final MatchingBlock copy(int spos, int dpos, int length) {
        return new MatchingBlock(spos, dpos, length);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MatchingBlock)) {
            return false;
        }
        MatchingBlock matchingBlock = (MatchingBlock) other;
        return this.spos == matchingBlock.spos && this.dpos == matchingBlock.dpos && this.length == matchingBlock.length;
    }

    public int hashCode() {
        return (((this.spos * 31) + this.dpos) * 31) + this.length;
    }

    public MatchingBlock(int i, int i2, int i3) {
        this.spos = i;
        this.dpos = i2;
        this.length = i3;
    }

    public /* synthetic */ MatchingBlock(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0 : i3);
    }

    public final int getSpos() {
        return this.spos;
    }

    public final int getDpos() {
        return this.dpos;
    }

    public final int getLength() {
        return this.length;
    }

    public String toString() {
        return "(" + this.spos + AbstractJsonLexerKt.COMMA + this.dpos + AbstractJsonLexerKt.COMMA + this.length + ')';
    }
}
