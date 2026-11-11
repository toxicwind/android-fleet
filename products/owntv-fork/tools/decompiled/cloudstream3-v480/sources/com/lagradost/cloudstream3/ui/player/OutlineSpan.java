package com.lagradost.cloudstream3.ui.player;

import android.text.TextPaint;
import android.text.style.CharacterStyle;
import kotlin.Metadata;

/* JADX INFO: compiled from: OutlineSpan.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/OutlineSpan;", "Landroid/text/style/CharacterStyle;", "outlineWidth", "", "<init>", "(F)V", "getOutlineWidth", "()F", "updateDrawState", "", "tp", "Landroid/text/TextPaint;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class OutlineSpan extends CharacterStyle {
    private final float outlineWidth;

    public OutlineSpan(float f) {
        this.outlineWidth = f;
    }

    public final float getOutlineWidth() {
        return this.outlineWidth;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint tp) {
        if (tp != null) {
            tp.setStrokeWidth(this.outlineWidth);
        }
    }
}
