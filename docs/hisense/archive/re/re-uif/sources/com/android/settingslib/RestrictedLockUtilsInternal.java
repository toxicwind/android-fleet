package com.android.settingslib;

import android.content.Context;
import android.os.UserHandle;
import android.os.UserManager;

/* JADX INFO: loaded from: classes.dex */
public class RestrictedLockUtilsInternal extends RestrictedLockUtils {
    static Proxy sProxy = new Proxy();

    public static boolean hasBaseUserRestriction(Context context, String str, int i) {
        return ((UserManager) context.getSystemService("user")).hasBaseUserRestriction(str, UserHandle.of(i));
    }

    static class Proxy {
        Proxy() {
        }
    }
}
