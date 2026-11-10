package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.w;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VoiceBoxApiService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1999a;

    public VoiceBoxApiService(IHttpApi iHttpApi) {
        this.f1999a = iHttpApi;
    }

    public static VoiceBoxApiService getService(IHttpApi iHttpApi) {
        return w.a(iHttpApi);
    }

    public abstract String activityResources(String str, boolean z, HashMap<String, String> map);

    public abstract String concatUrl(String str, boolean z, HashMap<String, String> map);

    public abstract String conductPlayUrl(String str, boolean z, HashMap<String, String> map);

    public abstract String fileRequrl(String str, boolean z, HashMap<String, String> map);

    public abstract String getAbilityList(String str, boolean z, HashMap<String, String> map);

    public abstract String getColumns(String str, boolean z, HashMap<String, String> map);

    public abstract String getConcatUrl(String str, boolean z, HashMap<String, String> map);

    public abstract String getFavoriteContent(String str, boolean z, HashMap<String, String> map);

    public abstract String getFirstResult(String str, boolean z, HashMap<String, String> map);

    public abstract String getItemDetail(String str, boolean z, HashMap<String, String> map);

    public abstract String getItemLayoutDetail(String str, boolean z, HashMap<String, String> map);

    public abstract String getMainPage(String str, boolean z, HashMap<String, String> map);

    public abstract String getSideBar(String str, boolean z, HashMap<String, String> map);

    public abstract String getTabResult(String str, boolean z, HashMap<String, String> map);

    public abstract String getTopicDetail(String str, boolean z, HashMap<String, String> map);

    public abstract String gethotword(String str, boolean z, HashMap<String, String> map);

    public abstract String getlauncherWord(String str, boolean z, HashMap<String, String> map);

    public abstract String secondParams(String str, boolean z, HashMap<String, String> map);

    public abstract String specInfo(String str, boolean z, HashMap<String, String> map);
}
