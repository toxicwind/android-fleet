package com.ju.lib.datacommunication.network.http.utils;

import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class ReflectUtil {
    public static final void setField(Class<?> cls, Object obj, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException {
        Field declaredField = cls.getDeclaredField(str);
        boolean zIsAccessible = declaredField.isAccessible();
        if (!zIsAccessible) {
            declaredField.setAccessible(true);
        }
        declaredField.set(obj, obj2);
        if (zIsAccessible) {
            return;
        }
        declaredField.setAccessible(zIsAccessible);
    }
}
