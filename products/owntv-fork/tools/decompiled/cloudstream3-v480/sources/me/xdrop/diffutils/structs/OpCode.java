package me.xdrop.diffutils.structs;

/* JADX INFO: loaded from: classes5.dex */
public final class OpCode {
    public int dbeg;
    public int dend;
    public int sbeg;
    public int send;
    public EditType type;

    public String toString() {
        return this.type.name() + "(" + this.sbeg + "," + this.send + "," + this.dbeg + "," + this.dend + ")";
    }
}
