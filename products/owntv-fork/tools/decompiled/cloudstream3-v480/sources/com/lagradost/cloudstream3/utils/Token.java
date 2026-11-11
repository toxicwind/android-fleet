package com.lagradost.cloudstream3.utils;

import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/utils/Token;", "", "type", "Lcom/lagradost/cloudstream3/utils/TT;", "raw", "", CloudStreamPackage.POSITION_EXTRA, "", "<init>", "(Lcom/lagradost/cloudstream3/utils/TT;Ljava/lang/String;I)V", "getType", "()Lcom/lagradost/cloudstream3/utils/TT;", "getRaw", "()Ljava/lang/String;", "getPos", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class Token {
    private final int pos;
    private final String raw;
    private final TT type;

    public static /* synthetic */ Token copy$default(Token token, TT tt, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            tt = token.type;
        }
        if ((i2 & 2) != 0) {
            str = token.raw;
        }
        if ((i2 & 4) != 0) {
            i = token.pos;
        }
        return token.copy(tt, str, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final TT getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRaw() {
        return this.raw;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getPos() {
        return this.pos;
    }

    public final Token copy(TT type, String raw, int pos) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(raw, "raw");
        return new Token(type, raw, pos);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Token)) {
            return false;
        }
        Token token = (Token) other;
        return this.type == token.type && Intrinsics.areEqual(this.raw, token.raw) && this.pos == token.pos;
    }

    public int hashCode() {
        return (((this.type.hashCode() * 31) + this.raw.hashCode()) * 31) + this.pos;
    }

    public String toString() {
        return "Token(type=" + this.type + ", raw=" + this.raw + ", pos=" + this.pos + ')';
    }

    public Token(TT type, String raw, int i) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(raw, "raw");
        this.type = type;
        this.raw = raw;
        this.pos = i;
    }

    public final int getPos() {
        return this.pos;
    }

    public final String getRaw() {
        return this.raw;
    }

    public final TT getType() {
        return this.type;
    }
}
