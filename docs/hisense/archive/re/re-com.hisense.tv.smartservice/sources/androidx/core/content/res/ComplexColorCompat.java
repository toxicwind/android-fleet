package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public final class ComplexColorCompat {
    public static final String LOG_TAG = "ComplexColorCompat";
    public int mColor;
    public final ColorStateList mColorStateList;
    public final Shader mShader;

    public ComplexColorCompat(Shader shader, ColorStateList colorStateList, @ColorInt int color) {
        this.mShader = shader;
        this.mColorStateList = colorStateList;
        this.mColor = color;
    }

    @NonNull
    public static ComplexColorCompat createFromXml(@NonNull Resources resources, @ColorRes int resId, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        XmlResourceParser xml = resources.getXml(resId);
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
        do {
            next = xml.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        String name = xml.getName();
        byte b2 = -1;
        int iHashCode = name.hashCode();
        if (iHashCode != 89650992) {
            if (iHashCode == 1191572447 && name.equals("selector")) {
                b2 = 0;
            }
        } else if (name.equals("gradient")) {
            b2 = 1;
        }
        if (b2 == 0) {
            return from(ColorStateListInflaterCompat.createFromXmlInner(resources, xml, attributeSetAsAttributeSet, theme));
        }
        if (b2 == 1) {
            return from(GradientColorInflaterCompat.createFromXmlInner(resources, xml, attributeSetAsAttributeSet, theme));
        }
        throw new XmlPullParserException(xml.getPositionDescription() + ": unsupported complex color tag " + name);
    }

    public static ComplexColorCompat from(@NonNull Shader shader) {
        return new ComplexColorCompat(shader, null, 0);
    }

    @Nullable
    public static ComplexColorCompat inflate(@NonNull Resources resources, @ColorRes int resId, @Nullable Resources.Theme theme) {
        try {
            return createFromXml(resources, resId, theme);
        } catch (Exception e2) {
            Log.e(LOG_TAG, "Failed to inflate ComplexColor.", e2);
            return null;
        }
    }

    @ColorInt
    public int getColor() {
        return this.mColor;
    }

    @Nullable
    public Shader getShader() {
        return this.mShader;
    }

    public boolean isGradient() {
        return this.mShader != null;
    }

    public boolean isStateful() {
        ColorStateList colorStateList;
        return this.mShader == null && (colorStateList = this.mColorStateList) != null && colorStateList.isStateful();
    }

    public boolean onStateChanged(int[] stateSet) {
        if (isStateful()) {
            ColorStateList colorStateList = this.mColorStateList;
            int colorForState = colorStateList.getColorForState(stateSet, colorStateList.getDefaultColor());
            if (colorForState != this.mColor) {
                this.mColor = colorForState;
                return true;
            }
        }
        return false;
    }

    public void setColor(@ColorInt int color) {
        this.mColor = color;
    }

    public boolean willDraw() {
        return isGradient() || this.mColor != 0;
    }

    public static ComplexColorCompat from(@NonNull ColorStateList colorStateList) {
        return new ComplexColorCompat(null, colorStateList, colorStateList.getDefaultColor());
    }

    public static ComplexColorCompat from(@ColorInt int color) {
        return new ComplexColorCompat(null, null, color);
    }
}
