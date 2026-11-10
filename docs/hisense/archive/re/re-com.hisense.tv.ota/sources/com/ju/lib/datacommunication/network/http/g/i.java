package com.ju.lib.datacommunication.network.http.g;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f131a;
    private String b;
    private String c;

    public i(String str, String str2, String str3) {
        this.f131a = str + "/ADDRESS.json";
        this.b = str2;
        this.c = str3;
    }

    private void c(b bVar) throws Throwable {
        k.d(b.b(bVar), this.f131a);
        com.ju.lib.datacommunication.network.http.i.b.d("DynamicAddressManager", "save file success");
    }

    private void d(Map map) {
        map.put("appVersion", "" + this.b);
        map.put("refAppKey", "1175952793");
        map.put("version", "7.4");
        map.put("timeStamp", "" + (System.currentTimeMillis() / 1000));
        map.put("format", "0");
        map.put("languageId", "0");
        map.put("sourceType", "1");
        map.put("deviceId", this.c);
    }

    public b a(String str) throws Throwable {
        HashMap map = new HashMap();
        map.put("domainVersion", str);
        d(map);
        com.ju.lib.datacommunication.network.http.core.s sVarB = ((com.ju.lib.datacommunication.network.http.h.a) ((com.ju.lib.datacommunication.network.http.h.a) ((com.ju.lib.datacommunication.network.http.h.a) ((com.ju.lib.datacommunication.network.http.h.a) com.ju.lib.datacommunication.network.http.d.b().b().g("http://api-dynhost.hismarttv.com/pds/getAppPlatformDomainList")).i(map)).h("sign", com.ju.lib.datacommunication.network.http.core.signature.a.c(map, com.ju.lib.datacommunication.network.http.core.signature.a.g()))).f(new com.ju.lib.datacommunication.network.http.core.signature.c())).b();
        if (sVarB == null || !sVarB.d() || sVarB.a() == null) {
            return null;
        }
        b bVarC = b.c(sVarB.a().a());
        if (bVarC == null) {
            return bVarC;
        }
        try {
            if (bVarC.f126a != 0) {
                return bVarC;
            }
            com.ju.lib.datacommunication.network.http.i.b.d("DynamicAddressManager", "filepath:" + this.f131a);
            c(bVarC);
            return bVarC;
        } catch (Exception e) {
            com.ju.lib.datacommunication.network.http.i.b.d("DynamicAddressManager", "save file fail:" + e);
            return bVarC;
        }
    }

    public b b() throws Throwable {
        if (!k.b(this.f131a)) {
            throw new FileNotFoundException("file not found");
        }
        String strC = k.c(this.f131a);
        b bVarA = (strC == null || "".equals(strC)) ? null : b.a(strC);
        com.ju.lib.datacommunication.network.http.i.b.d("DynamicAddressManager", "read from file success");
        return bVarA;
    }
}
