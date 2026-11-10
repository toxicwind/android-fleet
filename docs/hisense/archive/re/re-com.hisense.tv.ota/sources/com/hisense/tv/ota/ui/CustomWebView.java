package com.hisense.tv.ota.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class CustomWebView extends WebView {
    public CustomWebView(Context context) {
        super(a(context));
    }

    public CustomWebView(Context context, AttributeSet attributeSet) {
        super(a(context), attributeSet);
    }

    public CustomWebView(Context context, AttributeSet attributeSet, int i) {
        super(a(context), attributeSet, i);
    }

    public static Context a(Context context) {
        return Build.VERSION.SDK_INT < 23 ? context.createConfigurationContext(new Configuration()) : context;
    }

    public static void b() {
        Method declaredMethod;
        String str;
        int i = Build.VERSION.SDK_INT;
        try {
            Class<?> cls = Class.forName("android.webkit.WebViewFactory");
            Field declaredField = cls.getDeclaredField("sProviderInstance");
            declaredField.setAccessible(true);
            Object objInvoke = declaredField.get(null);
            if (objInvoke != null) {
                Log.i("CustomWebView", "sProviderInstance isn't null");
                return;
            }
            if (i > 22) {
                declaredMethod = cls.getDeclaredMethod("getProviderClass", new Class[0]);
            } else {
                if (i != 22) {
                    Log.i("CustomWebView", "Don't need to Hook WebView");
                    return;
                }
                declaredMethod = cls.getDeclaredMethod("getFactoryClass", new Class[0]);
            }
            declaredMethod.setAccessible(true);
            Class cls2 = (Class) declaredMethod.invoke(cls, new Object[0]);
            Class<?> cls3 = Class.forName("android.webkit.WebViewDelegate");
            Constructor<?> declaredConstructor = cls3.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            if (i < 26) {
                Constructor constructor = cls2.getConstructor(cls3);
                if (constructor != null) {
                    constructor.setAccessible(true);
                    objInvoke = constructor.newInstance(declaredConstructor.newInstance(new Object[0]));
                }
            } else {
                Field declaredField2 = cls.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD");
                declaredField2.setAccessible(true);
                String str2 = (String) declaredField2.get(null);
                if (str2 == null) {
                    str2 = "create";
                }
                Method method = cls2.getMethod(str2, cls3);
                if (method != null) {
                    objInvoke = method.invoke(null, declaredConstructor.newInstance(new Object[0]));
                }
            }
            if (objInvoke != null) {
                declaredField.set("sProviderInstance", objInvoke);
                str = "Hook success!";
            } else {
                str = "Hook failed!";
            }
            Log.i("CustomWebView", str);
        } catch (Throwable th) {
            Log.w("CustomWebView", th);
        }
    }
}
