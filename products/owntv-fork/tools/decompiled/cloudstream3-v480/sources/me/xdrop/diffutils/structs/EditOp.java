package me.xdrop.diffutils.structs;

/* JADX INFO: loaded from: classes5.dex */
public final class EditOp {
    public int dpos;
    public int spos;
    public EditType type;

    public String toString() {
        return this.type.name() + "(" + this.spos + "," + this.dpos + ")";
    }
}
