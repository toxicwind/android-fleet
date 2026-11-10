package com.ju.lib.utils.jsonxml;

import android.text.TextUtils;
import e.b.a;
import e.b.b;
import e.b.d;

/* JADX INFO: loaded from: classes2.dex */
public class ParseSuperAppParamUtil {
    public static String getSuperAppParam(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            a aVarK = new d(str).k("startupUrl");
            for (int i = 0; i < aVarK.a(); i++) {
                d dVarA = aVarK.a(i);
                if ("superAppParam".equals(dVarA.a("key", ""))) {
                    return dVarA.a("value", "");
                }
            }
        } catch (b e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
}
