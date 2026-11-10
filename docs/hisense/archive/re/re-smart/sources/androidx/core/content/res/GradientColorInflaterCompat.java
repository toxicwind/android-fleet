package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public final class GradientColorInflaterCompat {
    public static final int TILE_MODE_CLAMP = 0;
    public static final int TILE_MODE_MIRROR = 2;
    public static final int TILE_MODE_REPEAT = 1;

    public static ColorStops checkColors(@Nullable ColorStops colorItems, @ColorInt int startColor, @ColorInt int endColor, boolean hasCenterColor, @ColorInt int centerColor) {
        if (colorItems != null) {
            return colorItems;
        }
        return hasCenterColor ? new ColorStops(startColor, centerColor, endColor) : new ColorStops(startColor, endColor);
    }

    public static Shader createFromXml(@NonNull Resources resources, @NonNull XmlPullParser parser, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(parser);
        do {
            next = parser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return createFromXmlInner(resources, parser, attributeSetAsAttributeSet, theme);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static Shader createFromXmlInner(@NonNull Resources resources, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) throws XmlPullParserException {
        String name = parser.getName();
        if (!name.equals("gradient")) {
            throw new XmlPullParserException(parser.getPositionDescription() + ": invalid gradient color tag " + name);
        }
        TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attrs, R.styleable.GradientColor);
        float namedFloat = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, parser, "startX", R.styleable.GradientColor_android_startX, 0.0f);
        float namedFloat2 = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, parser, "startY", R.styleable.GradientColor_android_startY, 0.0f);
        float namedFloat3 = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, parser, "endX", R.styleable.GradientColor_android_endX, 0.0f);
        float namedFloat4 = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, parser, "endY", R.styleable.GradientColor_android_endY, 0.0f);
        float namedFloat5 = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, parser, "centerX", R.styleable.GradientColor_android_centerX, 0.0f);
        float namedFloat6 = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, parser, "centerY", R.styleable.GradientColor_android_centerY, 0.0f);
        int namedInt = TypedArrayUtils.getNamedInt(typedArrayObtainAttributes, parser, "type", R.styleable.GradientColor_android_type, 0);
        int namedColor = TypedArrayUtils.getNamedColor(typedArrayObtainAttributes, parser, "startColor", R.styleable.GradientColor_android_startColor, 0);
        boolean zHasAttribute = TypedArrayUtils.hasAttribute(parser, "centerColor");
        int namedColor2 = TypedArrayUtils.getNamedColor(typedArrayObtainAttributes, parser, "centerColor", R.styleable.GradientColor_android_centerColor, 0);
        int namedColor3 = TypedArrayUtils.getNamedColor(typedArrayObtainAttributes, parser, "endColor", R.styleable.GradientColor_android_endColor, 0);
        int namedInt2 = TypedArrayUtils.getNamedInt(typedArrayObtainAttributes, parser, "tileMode", R.styleable.GradientColor_android_tileMode, 0);
        float namedFloat7 = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, parser, "gradientRadius", R.styleable.GradientColor_android_gradientRadius, 0.0f);
        typedArrayObtainAttributes.recycle();
        ColorStops colorStopsCheckColors = checkColors(inflateChildElements(resources, parser, attrs, theme), namedColor, namedColor3, zHasAttribute, namedColor2);
        if (namedInt != 1) {
            return namedInt != 2 ? new LinearGradient(namedFloat, namedFloat2, namedFloat3, namedFloat4, colorStopsCheckColors.mColors, colorStopsCheckColors.mOffsets, parseTileMode(namedInt2)) : new SweepGradient(namedFloat5, namedFloat6, colorStopsCheckColors.mColors, colorStopsCheckColors.mOffsets);
        }
        if (namedFloat7 > 0.0f) {
            return new RadialGradient(namedFloat5, namedFloat6, namedFloat7, colorStopsCheckColors.mColors, colorStopsCheckColors.mOffsets, parseTileMode(namedInt2));
        }
        throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
    }

    public static ColorStops inflateChildElements(@NonNull Resources resources, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth;
        int depth2 = parser.getDepth() + 1;
        ArrayList arrayList = new ArrayList(20);
        ArrayList arrayList2 = new ArrayList(20);
        while (true) {
            int next = parser.next();
            if (next == 1 || ((depth = parser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2 && parser.getName().equals("item")) {
                TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attrs, R.styleable.GradientColorItem);
                boolean zHasValue = typedArrayObtainAttributes.hasValue(R.styleable.GradientColorItem_android_color);
                boolean zHasValue2 = typedArrayObtainAttributes.hasValue(R.styleable.GradientColorItem_android_offset);
                if (!zHasValue || !zHasValue2) {
                    throw new XmlPullParserException(parser.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' attribute!");
                }
                int color = typedArrayObtainAttributes.getColor(R.styleable.GradientColorItem_android_color, 0);
                float f2 = typedArrayObtainAttributes.getFloat(R.styleable.GradientColorItem_android_offset, 0.0f);
                typedArrayObtainAttributes.recycle();
                arrayList2.add(Integer.valueOf(color));
                arrayList.add(Float.valueOf(f2));
            }
        }
        if (arrayList2.size() > 0) {
            return new ColorStops(arrayList2, arrayList);
        }
        return null;
    }

    public static Shader.TileMode parseTileMode(int tileMode) {
        if (tileMode != 1) {
            return tileMode != 2 ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR;
        }
        return Shader.TileMode.REPEAT;
    }

    static final class ColorStops {
        public final int[] mColors;
        public final float[] mOffsets;

        public ColorStops(@NonNull List<Integer> colorsList, @NonNull List<Float> offsetsList) {
            int size = colorsList.size();
            this.mColors = new int[size];
            this.mOffsets = new float[size];
            for (int i = 0; i < size; i++) {
                this.mColors[i] = colorsList.get(i).intValue();
                this.mOffsets[i] = offsetsList.get(i).floatValue();
            }
        }

        public ColorStops(@ColorInt int startColor, @ColorInt int endColor) {
            this.mColors = new int[]{startColor, endColor};
            this.mOffsets = new float[]{0.0f, 1.0f};
        }

        public ColorStops(@ColorInt int startColor, @ColorInt int centerColor, @ColorInt int endColor) {
            this.mColors = new int[]{startColor, centerColor, endColor};
            this.mOffsets = new float[]{0.0f, 0.5f, 1.0f};
        }
    }
}
