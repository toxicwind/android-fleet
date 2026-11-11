package androidx.core.view;

import android.graphics.Matrix;
import android.graphics.Path;
import android.view.DisplayShape;
import androidx.core.graphics.PathParser;
import j$.util.Objects;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes4.dex */
public final class DisplayShapeCompat {
    static final DisplayShapeCompat EMPTY = new DisplayShapeCompat("", 0, 0, 1.0f, 0, 0, 0, 1.0f);
    private static final String TAG = "DisplayShapeCompat";
    private final Impl mImpl;

    private interface Impl {
        Path getPath();

        DisplayShape getPlatformDisplayShape();
    }

    private DisplayShapeCompat(DisplayShape displayShape) {
        this.mImpl = new Impl34(displayShape);
    }

    private DisplayShapeCompat(String str, int i, int i2, float f, int i3, int i4, int i5, float f2) {
        this.mImpl = new ImplBase(str, i, i2, f, i3, i4, i5, f2);
    }

    static DisplayShapeCompat toDisplayShapeCompat(DisplayShape displayShape) {
        if (displayShape == null) {
            return null;
        }
        return new DisplayShapeCompat(displayShape);
    }

    static DisplayShape toPlatformDisplayShape(DisplayShapeCompat displayShapeCompat) {
        if (displayShapeCompat == null) {
            return null;
        }
        return displayShapeCompat.mImpl.getPlatformDisplayShape();
    }

    public static DisplayShapeCompat create(String str, float f, int i, int i2) {
        return new DisplayShapeCompat(str, i, i2, f, 0, 0, 0, 1.0f);
    }

    public static DisplayShapeCompat create(int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        return new DisplayShapeCompat(createSpecString(i, i2, z, i3, i4, i5, i6), i, i2, 1.0f, 0, 0, 0, 1.0f);
    }

    private static String createSpecString(int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        if (z) {
            int i7 = i / 2;
            int i8 = i2 / 2;
            return "M0," + i8 + " A" + i7 + "," + i8 + " 0 1,1 " + i + "," + i8 + " A" + i7 + "," + i8 + " 0 1,1 0," + i8 + " Z";
        }
        StringBuilder sb = new StringBuilder("M ");
        int iMin = Math.min(i / 2, i2 / 2);
        int iMin2 = Math.min(iMin, i3);
        int iMin3 = Math.min(iMin, i4);
        int iMin4 = Math.min(iMin, i5);
        int iMin5 = Math.min(iMin, i6);
        sb.append(iMin2);
        sb.append(",0 L ");
        sb.append(i - iMin3);
        sb.append(",0");
        if (iMin3 > 0) {
            sb.append(" A ");
            sb.append(iMin3);
            sb.append(",");
            sb.append(iMin3);
            sb.append(" 0 0,1 ");
            sb.append(i);
            sb.append(",");
            sb.append(iMin3);
        }
        sb.append(" L ");
        sb.append(i);
        sb.append(",");
        sb.append(i2 - iMin4);
        if (iMin4 > 0) {
            sb.append(" A ");
            sb.append(iMin4);
            sb.append(",");
            sb.append(iMin4);
            sb.append(" 0 0,1 ");
            sb.append(i - iMin4);
            sb.append(",");
            sb.append(i2);
        }
        sb.append(" L ");
        sb.append(iMin5);
        sb.append(",");
        sb.append(i2);
        if (iMin5 > 0) {
            sb.append(" A ");
            sb.append(iMin5);
            sb.append(",");
            sb.append(iMin5);
            sb.append(" 0 0,1 0,");
            sb.append(i2 - iMin5);
        }
        if (iMin2 > 0) {
            sb.append(" L 0,");
            sb.append(iMin2);
            sb.append(" A ");
            sb.append(iMin2);
            sb.append(",");
            sb.append(iMin2);
            sb.append(" 0 0,1 ");
            sb.append(iMin2);
            sb.append(",0");
        }
        sb.append(" Z");
        return sb.toString();
    }

    public Path getPath() {
        return this.mImpl.getPath();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DisplayShapeCompat) {
            return Objects.equals(this.mImpl, ((DisplayShapeCompat) obj).mImpl);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.mImpl);
    }

    public String toString() {
        return this.mImpl.toString();
    }

    private static class ImplBase implements Impl {
        private Path mCachedPath;
        private final int mDisplayHeight;
        private final String mDisplayShapeSpec;
        private final int mDisplayWidth;
        private final int mOffsetX;
        private final int mOffsetY;
        private final float mPhysicalPixelDisplaySizeRatio;
        private final int mRotation;
        private final float mScale;

        @Override // androidx.core.view.DisplayShapeCompat.Impl
        public DisplayShape getPlatformDisplayShape() {
            return null;
        }

        ImplBase(String str, int i, int i2, float f, int i3, int i4, int i5, float f2) {
            this.mDisplayShapeSpec = str;
            this.mDisplayWidth = i;
            this.mDisplayHeight = i2;
            this.mPhysicalPixelDisplaySizeRatio = f;
            this.mRotation = i3;
            this.mOffsetX = i4;
            this.mOffsetY = i5;
            this.mScale = f2;
        }

        @Override // androidx.core.view.DisplayShapeCompat.Impl
        public Path getPath() {
            float f;
            float f2;
            float f3;
            float f4;
            Path path = this.mCachedPath;
            if (path != null) {
                return path;
            }
            String str = this.mDisplayShapeSpec;
            if (str == null || str.isEmpty()) {
                return new Path();
            }
            try {
                Path pathCreatePathFromPathData = PathParser.createPathFromPathData(this.mDisplayShapeSpec);
                if (!pathCreatePathFromPathData.isEmpty()) {
                    Matrix matrix = new Matrix();
                    int i = this.mRotation;
                    if (i != 0) {
                        float f5 = 0.0f;
                        if (i != 1) {
                            if (i == 2) {
                                f5 = this.mDisplayWidth;
                                f3 = this.mDisplayHeight;
                                f4 = 180.0f;
                            } else if (i != 3) {
                                f = 0.0f;
                                f2 = 0.0f;
                            } else {
                                f3 = this.mDisplayHeight;
                                f4 = 270.0f;
                            }
                            float f6 = f4;
                            f2 = f3;
                            f = f5;
                            f5 = f6;
                        } else {
                            f = this.mDisplayWidth;
                            f5 = 90.0f;
                            f2 = 0.0f;
                        }
                        matrix.preRotate(f5, f, f2);
                    }
                    float f7 = this.mPhysicalPixelDisplaySizeRatio;
                    if (f7 != 1.0f) {
                        matrix.preScale(f7, f7);
                    }
                    int i2 = this.mOffsetX;
                    if (i2 != 0 || this.mOffsetY != 0) {
                        matrix.postTranslate(i2, this.mOffsetY);
                    }
                    float f8 = this.mScale;
                    if (f8 != 1.0f) {
                        matrix.postScale(f8, f8);
                    }
                    pathCreatePathFromPathData.transform(matrix);
                }
                this.mCachedPath = pathCreatePathFromPathData;
                return pathCreatePathFromPathData;
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Failed to parse DisplayShapeCompat path data: " + this.mDisplayShapeSpec, e);
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ImplBase)) {
                return false;
            }
            ImplBase implBase = (ImplBase) obj;
            return Objects.equals(this.mDisplayShapeSpec, implBase.mDisplayShapeSpec) && this.mDisplayWidth == implBase.mDisplayWidth && this.mDisplayHeight == implBase.mDisplayHeight && this.mPhysicalPixelDisplaySizeRatio == implBase.mPhysicalPixelDisplaySizeRatio && this.mRotation == implBase.mRotation && this.mOffsetX == implBase.mOffsetX && this.mOffsetY == implBase.mOffsetY && this.mScale == implBase.mScale;
        }

        public int hashCode() {
            return Objects.hash(this.mDisplayShapeSpec, Integer.valueOf(this.mDisplayWidth), Integer.valueOf(this.mDisplayHeight), Float.valueOf(this.mPhysicalPixelDisplaySizeRatio), Integer.valueOf(this.mRotation), Integer.valueOf(this.mOffsetX), Integer.valueOf(this.mOffsetY), Float.valueOf(this.mScale));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("DisplayShapeCompat{ spec=");
            String str = this.mDisplayShapeSpec;
            sb.append(str != null ? Integer.valueOf(str.hashCode()) : AbstractJsonLexerKt.NULL);
            sb.append(" displayWidth=");
            sb.append(this.mDisplayWidth);
            sb.append(" displayHeight=");
            sb.append(this.mDisplayHeight);
            sb.append(" physicalPixelDisplaySizeRatio=");
            sb.append(this.mPhysicalPixelDisplaySizeRatio);
            sb.append(" rotation=");
            sb.append(this.mRotation);
            sb.append(" offsetX=");
            sb.append(this.mOffsetX);
            sb.append(" offsetY=");
            sb.append(this.mOffsetY);
            sb.append(" scale=");
            sb.append(this.mScale);
            sb.append("}");
            return sb.toString();
        }
    }

    private static class Impl34 implements Impl {
        private final DisplayShape mPlatformDisplayShape;

        Impl34(DisplayShape displayShape) {
            this.mPlatformDisplayShape = displayShape;
        }

        @Override // androidx.core.view.DisplayShapeCompat.Impl
        public Path getPath() {
            return this.mPlatformDisplayShape.getPath();
        }

        @Override // androidx.core.view.DisplayShapeCompat.Impl
        public DisplayShape getPlatformDisplayShape() {
            return this.mPlatformDisplayShape;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Impl34) {
                return Objects.equals(this.mPlatformDisplayShape, ((Impl34) obj).mPlatformDisplayShape);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.mPlatformDisplayShape);
        }

        public String toString() {
            return "DisplayShapeCompat{mPlatformDisplayShape=" + this.mPlatformDisplayShape + AbstractJsonLexerKt.END_OBJ;
        }
    }
}
