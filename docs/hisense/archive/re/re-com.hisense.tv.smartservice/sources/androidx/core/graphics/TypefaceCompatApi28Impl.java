package androidx.core.graphics;

import android.graphics.Typeface;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(28)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class TypefaceCompatApi28Impl extends TypefaceCompatApi26Impl {
    public static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    public static final String DEFAULT_FAMILY = "sans-serif";
    public static final int RESOLVE_BY_FONT_TABLE = -1;

    @Override // androidx.core.graphics.TypefaceCompatApi26Impl
    public Typeface createFromFamiliesWithDefault(Object family) {
        try {
            Object objNewInstance = Array.newInstance(this.mFontFamily, 1);
            Array.set(objNewInstance, 0, family);
            return (Typeface) this.mCreateFromFamiliesWithDefault.invoke(null, objNewInstance, DEFAULT_FAMILY, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatApi26Impl
    public Method obtainCreateFromFamiliesWithDefaultMethod(Class<?> fontFamily) throws NoSuchMethodException {
        Class cls = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(fontFamily, 1).getClass(), String.class, cls, cls);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
