package me.xdrop.fuzzywuzzy.model;

/* JADX INFO: loaded from: classes5.dex */
public class BoundExtractedResult<T> implements Comparable<BoundExtractedResult<T>> {
    private int index;
    private T referent;
    private int score;
    private String string;

    public BoundExtractedResult(T t, String str, int i, int i2) {
        this.referent = t;
        this.string = str;
        this.score = i;
        this.index = i2;
    }

    public T getReferent() {
        return this.referent;
    }

    public String getString() {
        return this.string;
    }

    public void setString(String str) {
        this.string = str;
    }

    public int getScore() {
        return this.score;
    }

    public int getIndex() {
        return this.index;
    }

    public String toString() {
        return "(string: " + this.string + ", score: " + this.score + ", index: " + this.index + ")";
    }

    @Override // java.lang.Comparable
    public int compareTo(BoundExtractedResult<T> boundExtractedResult) {
        return Integer.compare(getScore(), boundExtractedResult.getScore());
    }
}
