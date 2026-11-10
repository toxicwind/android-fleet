package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.bean.basp.AreaWeatherForecastInfoReply;
import com.hmct.cloud.sdk.bean.basp.AreaWeatherInfoReplay;
import com.hmct.cloud.sdk.bean.basp.CallCenterReplyInfo;
import com.hmct.cloud.sdk.bean.basp.DomainListReplyInfo;
import com.hmct.cloud.sdk.bean.basp.LocalAreaInfoReply;
import com.hmct.cloud.sdk.service.BaspService;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.utils.Constants;
import com.hmct.cloud.sdk.utils.Params;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.XmlSignature;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class a extends BaspService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile BaspService f2003b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2004c = "networkError";

    public a(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static BaspService a(IHttpApi iHttpApi) {
        if (f2003b == null) {
            synchronized (a.class) {
                if (f2003b == null) {
                    f2003b = new a(iHttpApi);
                }
            }
        }
        return f2003b;
    }

    @Override // com.hmct.cloud.sdk.service.BaspService
    public DomainListReplyInfo getAppServiceDomainListWithoutToken(String str, boolean z, HashMap<String, String> map) {
        try {
            return com.hmct.cloud.sdk.a.a.f(a(str, z, "pds/getAppServiceList", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.BaspService
    public String getAreaListByLevel(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "pds/getAreaListByLevel", map);
    }

    @Override // com.hmct.cloud.sdk.service.BaspService
    public String getAreaListByParentId(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "pds/getAreaListByParentId", map);
    }

    @Override // com.hmct.cloud.sdk.service.BaspService
    public AreaWeatherForecastInfoReply getAreaWeatherForecastInfo(String str, String str2, String str3, String str4) {
        HashMap<String, String> map = new HashMap<>();
        map.put("city", str2);
        map.put("province", str3);
        map.put("areaId", str4);
        try {
            return com.hmct.cloud.sdk.a.a.h(a(str, false, "pds/getAreaWeatherForecastInfo", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.BaspService
    public AreaWeatherInfoReplay getAreaWeatherInfo(String str, String str2, String str3, String str4) {
        HashMap<String, String> map = new HashMap<>();
        map.put("city", str2);
        map.put("province", str3);
        map.put("areaId", str4);
        try {
            return com.hmct.cloud.sdk.a.a.g(a(str, false, "pds/getAreaWeatherInfo", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.BaspService
    public CallCenterReplyInfo getCallCenter(String str, boolean z, HashMap<String, String> map) {
        try {
            return com.hmct.cloud.sdk.a.a.e(a(str, z, "pds/getCallCenter", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.BaspService
    public LocalAreaInfoReply getLocalAreaInfo(String str, boolean z, HashMap<String, String> map) {
        try {
            return com.hmct.cloud.sdk.a.a.i(a(str, z, "pds/getLocalAreaInfo", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            str = Constants.DEFAULT_DOMAINNAME;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        map2.put("version", Constants.DEFAULTAPIVERSION);
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000);
        map2.put("timeStamp", sb.toString());
        map2.put(Params.FORMAT, "0");
        map2.put(Params.LANGUAGEID, "0");
        a(map2);
        return a(((BaspService) this).f1976a, HiCloudService.a(str, str2, z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), new XmlSignature(), "{\"response\":{\"resultCode\":1,\"errorCode\":\"000000\",\"errorDesc\":\"networkError\",\"resultcode\":1,\"errorcode\":\"000000\",\"errordesc\":\"networkError\"}}", f2004c);
    }
}
