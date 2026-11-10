package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.f;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FKNowService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1981a;

    public FKNowService(IHttpApi iHttpApi) {
        this.f1981a = iHttpApi;
    }

    public static FKNowService getService(IHttpApi iHttpApi) {
        return f.a(iHttpApi);
    }

    public abstract String applicationFoxxumFilterWord(String str, boolean z, HashMap<String, String> map);

    public abstract String applicationRegiste(String str, boolean z, HashMap<String, String> map);

    public abstract String homepageApps(String str, boolean z, HashMap<String, String> map);

    public abstract String homepageCategoryDetail(String str, boolean z, HashMap<String, String> map);

    public abstract String homepagePattern(String str, boolean z, HashMap<String, String> map);

    public abstract String homepageVideos(String str, boolean z, HashMap<String, String> map);

    public abstract String mediaDetail(String str, boolean z, HashMap<String, String> map);

    public abstract String mediaOnlineCheck(String str, boolean z, HashMap<String, String> map);

    public abstract String recommendationPersonalizedMedias(String str, boolean z, HashMap<String, String> map);

    public abstract String recommendationRelatedMedias(String str, boolean z, HashMap<String, String> map);

    public abstract String searchCcontents(String str, boolean z, HashMap<String, String> map);

    public abstract String searchDefaultHotWords(String str, boolean z, HashMap<String, String> map);

    public abstract String searchHotWords(String str, boolean z, HashMap<String, String> map);

    public abstract String supportInfo(String str, boolean z, HashMap<String, String> map);
}
