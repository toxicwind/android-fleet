package org.mozilla.javascript.regexp;

/* JADX INFO: compiled from: NativeRegExp.java */
/* JADX INFO: loaded from: classes8.dex */
class REGlobalData {
    REBackTrackData backTrackStackTop;
    int cp;
    boolean multiline;
    long[] parens;
    RECompiled regexp;
    int skipped;
    REProgState stateStackTop;

    REGlobalData() {
    }

    int parensIndex(int i) {
        return (int) this.parens[i];
    }

    int parensLength(int i) {
        return (int) (this.parens[i] >>> 32);
    }

    void setParens(int i, int i2, int i3) {
        REBackTrackData rEBackTrackData = this.backTrackStackTop;
        if (rEBackTrackData != null) {
            long[] jArr = rEBackTrackData.parens;
            long[] jArr2 = this.parens;
            if (jArr == jArr2) {
                this.parens = (long[]) jArr2.clone();
            }
        }
        this.parens[i] = (((long) i3) << 32) | (((long) i2) & 4294967295L);
    }
}
