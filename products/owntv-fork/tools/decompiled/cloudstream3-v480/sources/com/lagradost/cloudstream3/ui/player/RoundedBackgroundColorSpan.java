package com.lagradost.cloudstream3.ui.player;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.LineBackgroundSpan;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoundedBackgroundColorSpan.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\r\n\u0002\b\u0004\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ`\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/RoundedBackgroundColorSpan;", "Landroid/text/style/LineBackgroundSpan;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "", "alignment", "Landroid/text/Layout$Alignment;", "padding", "", "radius", "<init>", "(ILandroid/text/Layout$Alignment;FF)V", "paint", "Landroid/graphics/Paint;", "drawBackground", "", "c", "Landroid/graphics/Canvas;", TtmlNode.TAG_P, TtmlNode.LEFT, TtmlNode.RIGHT, "top", "baseline", "bottom", "text", "", "start", TtmlNode.END, "lineNumber", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RoundedBackgroundColorSpan implements LineBackgroundSpan {
    private final Layout.Alignment alignment;
    private final int backgroundColor;
    private final float padding;
    private final Paint paint;
    private final float radius;

    /* JADX INFO: compiled from: RoundedBackgroundColorSpan.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Layout.Alignment.values().length];
            try {
                iArr[Layout.Alignment.ALIGN_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Layout.Alignment.ALIGN_OPPOSITE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Layout.Alignment.ALIGN_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public RoundedBackgroundColorSpan(int i, Layout.Alignment alignment, float f, float f2) {
        Intrinsics.checkNotNullParameter(alignment, "alignment");
        this.backgroundColor = i;
        this.alignment = alignment;
        this.padding = f;
        this.radius = f2;
        Paint paint = new Paint();
        paint.setColor(i);
        paint.setAntiAlias(true);
        this.paint = paint;
    }

    @Override // android.text.style.LineBackgroundSpan
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lineNumber) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(p, "p");
        Intrinsics.checkNotNullParameter(text, "text");
        if (Color.alpha(this.backgroundColor) <= 0) {
            return;
        }
        float fMeasureText = p.measureText(text, start, end);
        float f = 0.0f;
        StaticLayout staticLayoutBuild = StaticLayout.Builder.obtain(text, 0, text.length(), new TextPaint(p), (int) fMeasureText).setAlignment(this.alignment).setLineSpacing(0.0f, 1.0f).setIncludePad(true).build();
        Intrinsics.checkNotNull(staticLayoutBuild);
        float f2 = (left + right) * 0.5f;
        int i = WhenMappings.$EnumSwitchMapping$0[this.alignment.ordinal()];
        if (i != 1) {
            if (i == 2) {
                f = right - fMeasureText;
            } else {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                f = f2 - (0.5f * fMeasureText);
            }
        }
        float lineTop = staticLayoutBuild.getLineTop(lineNumber);
        float lineBottom = staticLayoutBuild.getLineBottom(lineNumber);
        float f3 = this.padding;
        float f4 = f - f3;
        float f5 = f + fMeasureText + f3;
        float f6 = this.radius;
        c.drawRoundRect(f4, lineTop, f5, lineBottom, f6, f6, this.paint);
    }
}
