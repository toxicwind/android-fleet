package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.FKNowService;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class f extends FKNowService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile FKNowService f2014b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2015c = "{\"result\":1,\"error_code\":\"000000\",\"error_desc\":\"networkError\"}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2016d = "networkError";

    public f(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static FKNowService a(IHttpApi iHttpApi) {
        if (f2014b == null) {
            synchronized (f.class) {
                if (f2014b == null) {
                    f2014b = new f(iHttpApi);
                }
            }
        }
        return f2014b;
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String applicationFoxxumFilterWord(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "application/foxxum_filter_word", map);
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String applicationRegiste(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "application/registe", map);
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String homepageApps(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "homepage/apps", map);
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String homepageCategoryDetail(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "homepage/category/detail", map);
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String homepagePattern(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "homepage/pattern", map);
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String homepageVideos(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "homepage/videos", map);
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String mediaDetail(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "media/detail", map);
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String mediaOnlineCheck(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "media/online_check", map);
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String recommendationPersonalizedMedias(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "recommendation/personalized_medias", map);
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String recommendationRelatedMedias(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "recommendation/related_medias", map);
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String searchCcontents(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "search/contents", map);
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String searchDefaultHotWords(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "search/default_hot_words", map);
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String searchHotWords(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "search/hot_words", map);
    }

    @Override // com.hmct.cloud.sdk.service.FKNowService
    public String supportInfo(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "application/support_info", map);
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("FKNowService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        return a(((FKNowService) this).f1981a, HiCloudService.a(str, str2, z), map2, null, f2015c, f2016d);
    }
}
