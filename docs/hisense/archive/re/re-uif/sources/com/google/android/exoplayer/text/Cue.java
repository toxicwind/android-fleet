package com.google.android.exoplayer.text;

import android.graphics.Bitmap;
import android.text.Layout;

/* JADX INFO: loaded from: classes.dex */
public class Cue {
    public final float line;
    public final float position;
    public CharSequence text;

    public Cue() {
        this((Bitmap) null);
    }

    public Cue(CharSequence charSequence) {
        this(charSequence, null, Float.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public Cue(CharSequence charSequence, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3) {
        this.text = charSequence;
        this.line = f;
        this.position = f2;
    }

    public Cue(Bitmap bitmap) {
        this(bitmap, false);
    }

    public Cue(Bitmap bitmap, boolean z) {
        this.text = null;
        this.line = Float.MIN_VALUE;
        this.position = Float.MIN_VALUE;
    }

    public void setText(String str) {
        this.text = str;
    }
}
