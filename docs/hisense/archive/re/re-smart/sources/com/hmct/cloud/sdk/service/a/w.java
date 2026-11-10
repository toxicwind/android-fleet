package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.VoiceBoxApiService;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class w extends VoiceBoxApiService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile VoiceBoxApiService f2061b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2062c = "{\"resultCode\":1,\"errorCode\":\"000000\",\"errorMsg\":\"networkError\"}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2063d = "networkError";

    public w(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static VoiceBoxApiService a(IHttpApi iHttpApi) {
        if (f2061b == null) {
            synchronized (w.class) {
                if (f2061b == null) {
                    f2061b = new w(iHttpApi);
                }
            }
        }
        return f2061b;
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String activityResources(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "api/activityResources", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String concatUrl(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "detailApi/concatUrl", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String conductPlayUrl(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "detailApi/conductPlayUrl", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String fileRequrl(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "api/file/requrl", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String getAbilityList(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "detailApi/abilityList", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String getColumns(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "api/columns", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String getConcatUrl(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "detailApi/concatUrl", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String getFavoriteContent(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "vipUserApi/favoriteContent", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String getFirstResult(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "searchApi/mamSearch/firstResult", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String getItemDetail(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "detailApi/itemDetail", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String getItemLayoutDetail(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "detailApi/itemLayoutDetail", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String getMainPage(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "api/mainPage", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String getSideBar(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "api/sideBar", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String getTabResult(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "searchApi/mamSearch/tabResult", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String getTopicDetail(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "api/topicDetail", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String gethotword(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "searchApi/mamSearch/hotword", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String getlauncherWord(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "searchApi/mamSearch/launcherWord", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String secondParams(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "api/v1.1.0/layoutApi/secondParams", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceBoxApiService
    public String specInfo(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "detailApi/specInfo", map);
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("VoiceBoxApiService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        String strEncyptMapMD5 = HiCloudKey.encyptMapMD5(map2);
        HashMap map3 = new HashMap();
        map3.put("X-Sign-For", strEncyptMapMD5);
        return HiCloudService.a(((VoiceBoxApiService) this).f1999a, HiCloudService.a(str, str2, z), map2, JsonSignature.createJsonSignature(), f2062c, f2063d, map3);
    }
}
