package com.hisense.tv.ota.W;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.hmct.cloud.sdk.bean.account.SignonReplyInfo;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
class c {
    public static String a(Context context) {
        String str;
        if (k.z(context)) {
            com.ju.lib.datacommunication.network.http.c cVarC = com.ju.lib.datacommunication.network.http.d.c();
            b bVar = b.e;
            cVarC.E(bVar, bVar.a());
            com.ju.lib.datacommunication.network.http.d.e(cVarC);
            String strA = d.a(1, context);
            String strH = a.b.a.a.d.b.h(context);
            if (TextUtils.isEmpty(strH)) {
                str = "can't get token, device ID is empty";
            } else {
                HashMap map = new HashMap();
                map.put("deviceId", strH);
                map.put("devSerial", strH.length() >= 32 ? strH.substring(strH.length() - 32) : "");
                map.put("appKey", "1231567061");
                map.put("appSecret", "58n7n268a6ziyzn2u7c8c3tdi74r3m76");
                map.put("loginName", "");
                map.put("password", "");
                a.b.a.a.d.c.d();
                SignonReplyInfo signonReplyInfoF = a.b.a.a.b.a.a().f(strA, true, map);
                if (signonReplyInfoF != null) {
                    String strF = signonReplyInfoF.f();
                    Log.i("HiCloudUtils", "token : " + strF);
                    return strF;
                }
                str = "can't get token, result is null";
            }
        } else {
            str = "can't get token, network is not ready";
        }
        Log.e("HiCloudUtils", str);
        return null;
    }
}
