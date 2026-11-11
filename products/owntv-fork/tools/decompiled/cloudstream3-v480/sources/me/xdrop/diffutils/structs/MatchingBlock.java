package me.xdrop.diffutils.structs;

/* JADX INFO: loaded from: classes5.dex */
public final class MatchingBlock {
    public int dpos;
    public int length;
    public int spos;

    public String toString() {
        return "(" + this.spos + "," + this.dpos + "," + this.length + ")";
    }
}
