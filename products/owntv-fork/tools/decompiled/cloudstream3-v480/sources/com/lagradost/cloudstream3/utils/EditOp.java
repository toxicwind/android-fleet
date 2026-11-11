package com.lagradost.cloudstream3.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: Levenshtein.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J)\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/utils/EditOp;", "", "type", "Lcom/lagradost/cloudstream3/utils/EditType;", "spos", "", "dpos", "<init>", "(Lcom/lagradost/cloudstream3/utils/EditType;II)V", "getType", "()Lcom/lagradost/cloudstream3/utils/EditType;", "setType", "(Lcom/lagradost/cloudstream3/utils/EditType;)V", "getSpos", "()I", "setSpos", "(I)V", "getDpos", "setDpos", "toString", "", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class EditOp {
    private int dpos;
    private int spos;
    private EditType type;

    public EditOp() {
        this(null, 0, 0, 7, null);
    }

    public static /* synthetic */ EditOp copy$default(EditOp editOp, EditType editType, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            editType = editOp.type;
        }
        if ((i3 & 2) != 0) {
            i = editOp.spos;
        }
        if ((i3 & 4) != 0) {
            i2 = editOp.dpos;
        }
        return editOp.copy(editType, i, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final EditType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getSpos() {
        return this.spos;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getDpos() {
        return this.dpos;
    }

    public final EditOp copy(EditType type, int spos, int dpos) {
        return new EditOp(type, spos, dpos);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EditOp)) {
            return false;
        }
        EditOp editOp = (EditOp) other;
        return this.type == editOp.type && this.spos == editOp.spos && this.dpos == editOp.dpos;
    }

    public int hashCode() {
        EditType editType = this.type;
        return ((((editType == null ? 0 : editType.hashCode()) * 31) + this.spos) * 31) + this.dpos;
    }

    public EditOp(EditType editType, int i, int i2) {
        this.type = editType;
        this.spos = i;
        this.dpos = i2;
    }

    public /* synthetic */ EditOp(EditType editType, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : editType, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 0 : i2);
    }

    public final EditType getType() {
        return this.type;
    }

    public final void setType(EditType editType) {
        this.type = editType;
    }

    public final int getSpos() {
        return this.spos;
    }

    public final void setSpos(int i) {
        this.spos = i;
    }

    public final int getDpos() {
        return this.dpos;
    }

    public final void setDpos(int i) {
        this.dpos = i;
    }

    public String toString() {
        String strName;
        StringBuilder sb = new StringBuilder();
        EditType editType = this.type;
        if (editType == null || (strName = editType.name()) == null) {
            strName = AbstractJsonLexerKt.NULL;
        }
        sb.append(strName);
        sb.append('(');
        sb.append(this.spos);
        sb.append(AbstractJsonLexerKt.COMMA);
        sb.append(this.dpos);
        sb.append(')');
        return sb.toString();
    }
}
