package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.XmlRes;
import androidx.core.R;
import androidx.core.view.ViewCompat;
import com.google.common.collect.LinkedHashMultimap;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public final class ColorStateListInflaterCompat {
    public static final ThreadLocal<TypedValue> sTempTypedValue = new ThreadLocal<>();

    @NonNull
    public static ColorStateList createFromXml(@NonNull Resources r, @NonNull XmlPullParser parser, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(parser);
        do {
            next = parser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return createFromXmlInner(r, parser, attributeSetAsAttributeSet, theme);
        }
        throw new XmlPullParserException("No start tag found");
    }

    @NonNull
    public static ColorStateList createFromXmlInner(@NonNull Resources r, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) throws XmlPullParserException {
        String name = parser.getName();
        if (name.equals("selector")) {
            return inflate(r, parser, attrs, theme);
        }
        throw new XmlPullParserException(parser.getPositionDescription() + ": invalid color state list tag " + name);
    }

    @NonNull
    public static TypedValue getTypedValue() {
        TypedValue typedValue = sTempTypedValue.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        sTempTypedValue.set(typedValue2);
        return typedValue2;
    }

    @Nullable
    public static ColorStateList inflate(@NonNull Resources resources, @XmlRes int resId, @Nullable Resources.Theme theme) {
        try {
            return createFromXml(resources, resources.getXml(resId), theme);
        } catch (Exception e2) {
            Log.e("CSLCompat", "Failed to inflate ColorStateList.", e2);
            return null;
        }
    }

    public static boolean isColorInt(@NonNull Resources r, @ColorRes int resId) {
        TypedValue typedValue = getTypedValue();
        r.getValue(resId, typedValue, true);
        int i = typedValue.type;
        return i >= 28 && i <= 31;
    }

    @ColorInt
    public static int modulateColorAlpha(@ColorInt int color, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR) float alphaMod) {
        return (color & ViewCompat.MEASURED_SIZE_MASK) | (Math.round(Color.alpha(color) * alphaMod) << 24);
    }

    public static TypedArray obtainAttributes(Resources res, Resources.Theme theme, AttributeSet set, int[] attrs) {
        return theme == null ? res.obtainAttributes(set, attrs) : theme.obtainStyledAttributes(set, attrs, 0, 0);
    }

    public static ColorStateList inflate(@NonNull Resources r, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth;
        int color;
        int i = 1;
        int depth2 = parser.getDepth() + 1;
        int[][] iArr = new int[20][];
        int[] iArrAppend = new int[iArr.length];
        int i2 = 0;
        while (true) {
            int next = parser.next();
            if (next == i || ((depth = parser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2 && parser.getName().equals("item")) {
                TypedArray typedArrayObtainAttributes = obtainAttributes(r, theme, attrs, R.styleable.ColorStateListItem);
                int resourceId = typedArrayObtainAttributes.getResourceId(R.styleable.ColorStateListItem_android_color, -1);
                if (resourceId != -1 && !isColorInt(r, resourceId)) {
                    try {
                        color = createFromXml(r, r.getXml(resourceId), theme).getDefaultColor();
                    } catch (Exception unused) {
                        color = typedArrayObtainAttributes.getColor(R.styleable.ColorStateListItem_android_color, -65281);
                    }
                } else {
                    color = typedArrayObtainAttributes.getColor(R.styleable.ColorStateListItem_android_color, -65281);
                }
                float f2 = 1.0f;
                if (typedArrayObtainAttributes.hasValue(R.styleable.ColorStateListItem_android_alpha)) {
                    f2 = typedArrayObtainAttributes.getFloat(R.styleable.ColorStateListItem_android_alpha, 1.0f);
                } else if (typedArrayObtainAttributes.hasValue(R.styleable.ColorStateListItem_alpha)) {
                    f2 = typedArrayObtainAttributes.getFloat(R.styleable.ColorStateListItem_alpha, 1.0f);
                }
                typedArrayObtainAttributes.recycle();
                int attributeCount = attrs.getAttributeCount();
                int[] iArr2 = new int[attributeCount];
                int i3 = 0;
                for (int i4 = 0; i4 < attributeCount; i4++) {
                    int attributeNameResource = attrs.getAttributeNameResource(i4);
                    if (attributeNameResource != 16843173 && attributeNameResource != 16843551 && attributeNameResource != R.attr.alpha) {
                        int i5 = i3 + 1;
                        if (!attrs.getAttributeBooleanValue(i4, false)) {
                            attributeNameResource = -attributeNameResource;
                        }
                        iArr2[i3] = attributeNameResource;
                        i3 = i5;
                    }
                }
                int[] iArrTrimStateSet = StateSet.trimStateSet(iArr2, i3);
                iArrAppend = GrowingArrayUtils.append(iArrAppend, i2, modulateColorAlpha(color, f2));
                iArr = (int[][]) GrowingArrayUtils.append(iArr, i2, iArrTrimStateSet);
                i2++;
            }
            i = 1;
        }
        int[] iArr3 = new int[i2];
        int[][] iArr4 = new int[i2][];
        System.arraycopy(iArrAppend, 0, iArr3, 0, i2);
        System.arraycopy(iArr, 0, iArr4, 0, i2);
        return new ColorStateList(iArr4, iArr3);
    }
}
