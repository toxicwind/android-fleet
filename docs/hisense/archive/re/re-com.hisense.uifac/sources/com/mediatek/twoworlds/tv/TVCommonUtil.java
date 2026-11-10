package com.mediatek.twoworlds.tv;

import android.os.IBinder;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class TVCommonUtil {
    private static final String TAG = "TVCommonUtil";

    private static IBinder getService(String str) {
        Method declaredMethod;
        try {
            try {
                declaredMethod = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                declaredMethod = null;
            }
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        if (declaredMethod == null) {
            return null;
        }
        try {
            return (IBinder) declaredMethod.invoke(null, str);
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
