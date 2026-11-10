package androidx.core.view.animation;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public class PathInterpolatorApi14 implements Interpolator {
    public static final float PRECISION = 0.002f;
    public final float[] mX;
    public final float[] mY;

    public PathInterpolatorApi14(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int i = ((int) (length / 0.002f)) + 1;
        this.mX = new float[i];
        this.mY = new float[i];
        float[] fArr = new float[2];
        for (int i2 = 0; i2 < i; i2++) {
            pathMeasure.getPosTan((i2 * length) / (i - 1), fArr, null);
            this.mX[i2] = fArr[0];
            this.mY[i2] = fArr[1];
        }
    }

    public static Path createCubic(float controlX1, float controlY1, float controlX2, float controlY2) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(controlX1, controlY1, controlX2, controlY2, 1.0f, 1.0f);
        return path;
    }

    public static Path createQuad(float controlX, float controlY) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(controlX, controlY, 1.0f, 1.0f);
        return path;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float t) {
        if (t <= 0.0f) {
            return 0.0f;
        }
        if (t >= 1.0f) {
            return 1.0f;
        }
        int i = 0;
        int length = this.mX.length - 1;
        while (length - i > 1) {
            int i2 = (i + length) / 2;
            if (t < this.mX[i2]) {
                length = i2;
            } else {
                i = i2;
            }
        }
        float[] fArr = this.mX;
        float f2 = fArr[length] - fArr[i];
        if (f2 == 0.0f) {
            return this.mY[i];
        }
        float f3 = (t - fArr[i]) / f2;
        float[] fArr2 = this.mY;
        float f4 = fArr2[i];
        return ((fArr2[length] - f4) * f3) + f4;
    }

    public PathInterpolatorApi14(float controlX, float controlY) {
        this(createQuad(controlX, controlY));
    }

    public PathInterpolatorApi14(float controlX1, float controlY1, float controlX2, float controlY2) {
        this(createCubic(controlX1, controlY1, controlX2, controlY2));
    }
}
