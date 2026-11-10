package com.ju.lib.datacommunication.network.http.g;

import android.text.TextUtils;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected String f139a;
    private j b;

    public s(String str, String str2, String str3, j jVar) {
        this.b = null;
        this.f139a = str + "/THIRD_ADDRESS.json";
        this.b = jVar;
    }

    private void a(Map map, j jVar) {
        if (!TextUtils.isEmpty(jVar.c())) {
            map.put("appVersion", jVar.c());
        }
        if (!TextUtils.isEmpty(jVar.e())) {
            map.put("refAppKey", jVar.e());
        }
        if (!TextUtils.isEmpty(jVar.d())) {
            map.put("deviceId", jVar.d());
        }
        if (!TextUtils.isEmpty(jVar.f132a)) {
            map.put("version", jVar.f132a);
        }
        map.put("timeStamp", "" + (System.currentTimeMillis() / 1000));
        if (!TextUtils.isEmpty(jVar.b)) {
            map.put("format", jVar.b);
        }
        if (!TextUtils.isEmpty(jVar.c)) {
            map.put("languageId", jVar.c);
        }
        if (TextUtils.isEmpty(jVar.d)) {
            return;
        }
        map.put("sourceType", jVar.d);
    }

    public n b() throws Throwable {
        HashMap map = new HashMap();
        j jVar = this.b;
        n nVarA = null;
        if (jVar == null) {
            com.ju.lib.datacommunication.network.http.i.b.d("ThirdDynamicAddressManager", "mDynamicAddressParam is null");
            return null;
        }
        a(map, jVar);
        com.ju.lib.datacommunication.network.http.core.s sVarB = ((com.ju.lib.datacommunication.network.http.h.a) ((com.ju.lib.datacommunication.network.http.h.a) ((com.ju.lib.datacommunication.network.http.h.a) ((com.ju.lib.datacommunication.network.http.h.a) com.ju.lib.datacommunication.network.http.d.b().b().g("https://bas-dynhost.hismarttv.com/pubdatasvc/getAppServiceListV2")).i(map)).a("appKey", this.b.e())).a("X-sign-For", com.ju.lib.datacommunication.network.http.core.signature.a.b(map, this.b.b()))).b();
        if (sVarB != null && sVarB.d() && sVarB.a() != null) {
            String strA = sVarB.a().a();
            String strB = c.b(strA, "signatureServer");
            String strA2 = com.ju.lib.datacommunication.network.http.core.signature.a.a(c.a(strA, "signatureServer"), this.b.b());
            if (TextUtils.isEmpty(strB) || TextUtils.isEmpty(strA2)) {
                com.ju.lib.datacommunication.network.http.i.b.d("ThirdDynamicAddressManager", "signatureServer is empty or sin is empty");
            } else {
                if (!strB.equals(strA2)) {
                    com.ju.lib.datacommunication.network.http.i.b.d("ThirdDynamicAddressManager", "check sign error");
                    return null;
                }
                nVarA = n.a(strA);
                if (nVarA != null) {
                    try {
                        if (nVarA.f134a == 0) {
                            com.ju.lib.datacommunication.network.http.i.b.d("ThirdDynamicAddressManager", "filepath:" + this.f139a);
                            d(nVarA);
                        }
                    } catch (Exception e) {
                        com.ju.lib.datacommunication.network.http.i.b.d("ThirdDynamicAddressManager", "save file fail:" + e);
                    }
                }
            }
        }
        return nVarA;
    }

    public n c() throws Throwable {
        if (!k.b(this.f139a)) {
            throw new FileNotFoundException("file not found");
        }
        String strC = k.c(this.f139a);
        n nVarA = (strC == null || "".equals(strC)) ? null : n.a(strC);
        com.ju.lib.datacommunication.network.http.i.b.d("ThirdDynamicAddressManager", "read from file success");
        return nVarA;
    }

    public void d(n nVar) throws Throwable {
        k.d(n.b(nVar), this.f139a);
        com.ju.lib.datacommunication.network.http.i.b.d("ThirdDynamicAddressManager", "save file success");
    }
}
