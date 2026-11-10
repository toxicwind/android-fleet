package com.google.android.exoplayer.text.ttml;

/* JADX INFO: loaded from: classes.dex */
final class TtmlRegion {
    public final float line;
    public final int lineType;
    public final float position;
    public final float width;

    public TtmlRegion() {
        this(Float.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public TtmlRegion(float f, float f2, int i, float f3) {
        this.position = f;
        this.line = f2;
        this.lineType = i;
        this.width = f3;
    }
}
