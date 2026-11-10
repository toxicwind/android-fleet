package com.mediatek.twoworlds.tv;

import android.os.IBinder;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class TVCommonUtil {
    public static final String TAG = "TVCommonUtil";

    public static IBinder getService(String str) {
        Method declaredMethod;
        try {
            try {
                declaredMethod = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class);
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
                declaredMethod = null;
            }
        } catch (ClassNotFoundException e3) {
            e3.printStackTrace();
        }
        if (declaredMethod != null) {
            try {
                return (IBinder) declaredMethod.invoke(null, str);
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
        return null;
    }
}
