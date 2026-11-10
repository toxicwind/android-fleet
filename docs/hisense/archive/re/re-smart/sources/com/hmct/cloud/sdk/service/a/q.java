package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.ShoppingGuiderApiService;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class q extends ShoppingGuiderApiService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile ShoppingGuiderApiService f2047b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2048c = "{\"response\": {\"resultCode\":1,\"errorCode\": \"000000\",\"errorDesc\": \"networkError\"}}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2049d = "networkError";

    public q(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static ShoppingGuiderApiService a(IHttpApi iHttpApi) {
        if (f2047b == null) {
            synchronized (q.class) {
                if (f2047b == null) {
                    f2047b = new q(iHttpApi);
                }
            }
        }
        return f2047b;
    }

    @Override // com.hmct.cloud.sdk.service.ShoppingGuiderApiService
    public String checkstatus(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "ShoppingGuiderSystemOnlineApi/checkstatus", map);
    }

    @Override // com.hmct.cloud.sdk.service.ShoppingGuiderApiService
    public String getAllGoodsList(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "ShoppingGuiderSystemOnlineApi/getallgoodslist", map);
    }

    @Override // com.hmct.cloud.sdk.service.ShoppingGuiderApiService
    public String getGoodsDetail(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "ShoppingGuiderSystemOnlineApi/getgoodsdetail", map);
    }

    @Override // com.hmct.cloud.sdk.service.ShoppingGuiderApiService
    public String getGuideGoodsList(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "ShoppingGuiderSystemOnlineApi/getguidegoodslist", map);
    }

    @Override // com.hmct.cloud.sdk.service.ShoppingGuiderApiService
    public String getLayoutGoodsList(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "ShoppingGuiderSystemOnlineApi/getlayoutgoodslist", map);
    }

    @Override // com.hmct.cloud.sdk.service.ShoppingGuiderApiService
    public String getModuleInfos(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "ShoppingGuiderSystemOnlineApi/getmoduleinfos", map);
    }

    @Override // com.hmct.cloud.sdk.service.ShoppingGuiderApiService
    public String getbrowserhistory(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "ShoppingGuiderSystemOnlineApi/getbrowserhistory", map);
    }

    @Override // com.hmct.cloud.sdk.service.ShoppingGuiderApiService
    public String getlabletemplatelist(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "ShoppingGuiderSystemOnlineApi/getlabletemplatelist", map);
    }

    @Override // com.hmct.cloud.sdk.service.ShoppingGuiderApiService
    public String initBasicConfig(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "ShoppingGuiderSystemOnlineApi/initbasicconfig", map);
    }

    @Override // com.hmct.cloud.sdk.service.ShoppingGuiderApiService
    public String initbasicconfig(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "ShoppingGuiderSystemOnlineApi/initbasicconfig", map);
    }

    @Override // com.hmct.cloud.sdk.service.ShoppingGuiderApiService
    public String postBrowserHistory(String str, boolean z, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UpgradeService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        return b(((ShoppingGuiderApiService) this).f1993a, HiCloudService.a(str, "ShoppingGuiderSystemOnlineApi/postbrowserhistory", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), f2048c, f2049d);
    }

    @Override // com.hmct.cloud.sdk.service.ShoppingGuiderApiService
    public String testTest(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "ShoppingGuiderSystemOnlineApi/test/test", map);
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UpgradeService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        return a(((ShoppingGuiderApiService) this).f1993a, HiCloudService.a(str, str2, z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), f2048c, f2049d);
    }
}
