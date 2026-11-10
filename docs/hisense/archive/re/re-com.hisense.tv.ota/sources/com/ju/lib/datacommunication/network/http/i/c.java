package com.ju.lib.datacommunication.network.http.i;

import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static final void a(Class cls, Object obj, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException {
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
