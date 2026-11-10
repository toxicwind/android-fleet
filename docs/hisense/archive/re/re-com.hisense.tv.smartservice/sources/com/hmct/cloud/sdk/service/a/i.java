package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.IotService;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class i extends IotService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile IotService f2025b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2026c = "{\"payload\":{\"status\":\"ERROR\",\"errorCode\":\"000000\",\"errorDesc\":\"networkError\"}}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2027d = "networkError";

    public i(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static IotService a(IHttpApi iHttpApi) {
        if (f2025b == null) {
            synchronized (i.class) {
                if (f2025b == null) {
                    f2025b = new i(iHttpApi);
                }
            }
        }
        return f2025b;
    }

    @Override // com.hmct.cloud.sdk.service.IotService
    public String devicesDelete(String str, boolean z, String str2) {
        return a(str, z, "1.0/iot/devices/delete", str2);
    }

    @Override // com.hmct.cloud.sdk.service.IotService
    public String devicesDetail(String str, boolean z, String str2) {
        return a(str, z, "1.0/iot/devices/detail", str2);
    }

    @Override // com.hmct.cloud.sdk.service.IotService
    public String devicesDiscovery(String str, boolean z, String str2) {
        return a(str, z, "1.0/iot/devices/discovery", str2);
    }

    @Override // com.hmct.cloud.sdk.service.IotService
    public String devicesExecute(String str, boolean z, String str2) {
        return a(str, z, "1.0/iot/devices/execute", str2);
    }

    @Override // com.hmct.cloud.sdk.service.IotService
    public String devicesList(String str, boolean z, String str2) {
        return a(str, z, "1.0/iot/devices/list", str2);
    }

    @Override // com.hmct.cloud.sdk.service.IotService
    public String devicesQuery(String str, boolean z, String str2) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("IotService", "domainName is null");
            return null;
        }
        String strEncyptStringMD5 = HiCloudKey.encyptStringMD5(str2);
        HashMap map = new HashMap();
        map.put("X-Sign-For", strEncyptStringMD5);
        return HiCloudService.a(((IotService) this).f1985a, HiCloudService.a(str, "1.0/iot/devices/query", z), str2, JsonSignature.createJsonSignature(), f2026c, f2027d, map);
    }

    @Override // com.hmct.cloud.sdk.service.IotService
    public String devicesUnlink(String str, boolean z, String str2) {
        return a(str, z, "1.0/iot/devices/unlink", str2);
    }

    @Override // com.hmct.cloud.sdk.service.IotService
    public String devicesUpdateName(String str, boolean z, String str2) {
        return a(str, z, "1.0/iot/devices/update/name", str2);
    }

    @Override // com.hmct.cloud.sdk.service.IotService
    public String partnersDetail(String str, boolean z, String str2) {
        return a(str, z, "1.0/iot/partners/detail", str2);
    }

    @Override // com.hmct.cloud.sdk.service.IotService
    public String partnersList(String str, boolean z, String str2) {
        return a(str, z, "1.0/iot/partners/list", str2);
    }

    @Override // com.hmct.cloud.sdk.service.IotService
    public String roomsList(String str, boolean z, String str2) {
        return a(str, z, "1.0/iot/rooms/list", str2);
    }

    private String a(String str, boolean z, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("IotService", "domainName is null");
            return null;
        }
        String strEncyptStringMD5 = HiCloudKey.encyptStringMD5(str3);
        HashMap map = new HashMap();
        map.put("X-Sign-For", strEncyptStringMD5);
        return HiCloudService.a(((IotService) this).f1985a, HiCloudService.a(str, str2, z), str3, JsonSignature.createJsonSignature(), f2026c, f2027d, map, "application/json");
    }
}
