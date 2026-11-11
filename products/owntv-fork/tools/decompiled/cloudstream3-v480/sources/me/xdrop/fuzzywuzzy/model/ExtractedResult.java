package me.xdrop.fuzzywuzzy.model;

/* JADX INFO: loaded from: classes5.dex */
public class ExtractedResult implements Comparable<ExtractedResult> {
    private int index;
    private int score;
    private String string;

    public ExtractedResult(String str, int i, int i2) {
        this.string = str;
        this.score = i;
        this.index = i2;
    }

    @Override // java.lang.Comparable
    public int compareTo(ExtractedResult extractedResult) {
        return Integer.compare(getScore(), extractedResult.getScore());
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
}
