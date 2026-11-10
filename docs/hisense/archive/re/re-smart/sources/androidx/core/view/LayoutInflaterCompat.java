package androidx.core.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.firebase.installations.local.IidStore;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutInflaterCompat {
    public static final String TAG = "LayoutInflaterCompatHC";
    public static boolean sCheckedField;
    public static Field sLayoutInflaterFactory2Field;

    static class Factory2Wrapper implements LayoutInflater.Factory2 {
        public final LayoutInflaterFactory mDelegateFactory;

        public Factory2Wrapper(LayoutInflaterFactory delegateFactory) {
            this.mDelegateFactory = delegateFactory;
        }

        @Override // android.view.LayoutInflater.Factory
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return this.mDelegateFactory.onCreateView(null, name, context, attrs);
        }

        @NonNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Factory2Wrapper.class.getName());
            sb.append(IidStore.JSON_ENCODED_PREFIX);
            return a.a(sb, this.mDelegateFactory, "}");
        }

        @Override // android.view.LayoutInflater.Factory2
        public View onCreateView(View parent, String name, Context context, AttributeSet attributeSet) {
            return this.mDelegateFactory.onCreateView(parent, name, context, attributeSet);
        }
    }

    public static void forceSetFactory2(LayoutInflater inflater, LayoutInflater.Factory2 factory) {
        if (!sCheckedField) {
            try {
                sLayoutInflaterFactory2Field = LayoutInflater.class.getDeclaredField("mFactory2");
                sLayoutInflaterFactory2Field.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                StringBuilder sbA = a.a("forceSetFactory2 Could not find field 'mFactory2' on class ");
                sbA.append(LayoutInflater.class.getName());
                sbA.append("; inflation may have unexpected results.");
                Log.e(TAG, sbA.toString(), e2);
            }
            sCheckedField = true;
        }
        Field field = sLayoutInflaterFactory2Field;
        if (field != null) {
            try {
                field.set(inflater, factory);
            } catch (IllegalAccessException e3) {
                Log.e(TAG, "forceSetFactory2 could not set the Factory2 on LayoutInflater " + inflater + "; inflation may have unexpected results.", e3);
            }
        }
    }

    @Deprecated
    public static LayoutInflaterFactory getFactory(LayoutInflater inflater) {
        LayoutInflater.Factory factory = inflater.getFactory();
        if (factory instanceof Factory2Wrapper) {
            return ((Factory2Wrapper) factory).mDelegateFactory;
        }
        return null;
    }

    @Deprecated
    public static void setFactory(@NonNull LayoutInflater inflater, @NonNull LayoutInflaterFactory factory) {
        int i = Build.VERSION.SDK_INT;
        inflater.setFactory2(factory != null ? new Factory2Wrapper(factory) : null);
    }

    public static void setFactory2(@NonNull LayoutInflater inflater, @NonNull LayoutInflater.Factory2 factory) {
        inflater.setFactory2(factory);
        int i = Build.VERSION.SDK_INT;
    }
}
