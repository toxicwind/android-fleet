package androidx.compose.ui.text.platform.style;

import android.graphics.Paint;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import kotlin.Metadata;

/* JADX INFO: compiled from: DrawStyleSpan.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0013\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\u0000¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"toAndroidJoin", "Landroid/graphics/Paint$Join;", "Landroidx/compose/ui/graphics/StrokeJoin;", "toAndroidJoin-Ww9F2mQ", "(I)Landroid/graphics/Paint$Join;", "toAndroidCap", "Landroid/graphics/Paint$Cap;", "Landroidx/compose/ui/graphics/StrokeCap;", "toAndroidCap-BeK7IIE", "(I)Landroid/graphics/Paint$Cap;", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DrawStyleSpan_androidKt {
    /* JADX INFO: renamed from: toAndroidJoin-Ww9F2mQ, reason: not valid java name */
    public static final Paint.Join m6633toAndroidJoinWw9F2mQ(int i) {
        if (StrokeJoin.m4537equalsimpl0(i, StrokeJoin.INSTANCE.m4542getMiterLxFBmk8())) {
            return Paint.Join.MITER;
        }
        if (StrokeJoin.m4537equalsimpl0(i, StrokeJoin.INSTANCE.m4543getRoundLxFBmk8())) {
            return Paint.Join.ROUND;
        }
        return StrokeJoin.m4537equalsimpl0(i, StrokeJoin.INSTANCE.m4541getBevelLxFBmk8()) ? Paint.Join.BEVEL : Paint.Join.MITER;
    }

    /* JADX INFO: renamed from: toAndroidCap-BeK7IIE, reason: not valid java name */
    public static final Paint.Cap m6632toAndroidCapBeK7IIE(int i) {
        if (StrokeCap.m4527equalsimpl0(i, StrokeCap.INSTANCE.m4531getButtKaPHkGw())) {
            return Paint.Cap.BUTT;
        }
        if (StrokeCap.m4527equalsimpl0(i, StrokeCap.INSTANCE.m4532getRoundKaPHkGw())) {
            return Paint.Cap.ROUND;
        }
        return StrokeCap.m4527equalsimpl0(i, StrokeCap.INSTANCE.m4533getSquareKaPHkGw()) ? Paint.Cap.SQUARE : Paint.Cap.BUTT;
    }
}
