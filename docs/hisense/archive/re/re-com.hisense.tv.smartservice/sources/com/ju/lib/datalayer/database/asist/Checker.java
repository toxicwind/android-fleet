package com.ju.lib.datalayer.database.asist;

import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class Checker {
    public static boolean isEmpty(CharSequence charSequence) {
        return isNull(charSequence) || charSequence.length() == 0;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isEmpty(Object[] objArr) {
        return isNull(objArr) || objArr.length == 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return isNull(collection) || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }
}
