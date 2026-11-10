package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.bean.basp.AreaWeatherForecastInfoReply;
import com.hmct.cloud.sdk.bean.basp.AreaWeatherInfoReplay;
import com.hmct.cloud.sdk.bean.basp.CallCenterReplyInfo;
import com.hmct.cloud.sdk.bean.basp.DomainListReplyInfo;
import com.hmct.cloud.sdk.bean.basp.LocalAreaInfoReply;
import com.hmct.cloud.sdk.service.a.a;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaspService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1976a;

    public BaspService(IHttpApi iHttpApi) {
        this.f1976a = iHttpApi;
    }

    public static BaspService getService(IHttpApi iHttpApi) {
        return a.a(iHttpApi);
    }

    public abstract DomainListReplyInfo getAppServiceDomainListWithoutToken(String str, boolean z, HashMap<String, String> map);

    public abstract String getAreaListByLevel(String str, boolean z, HashMap<String, String> map);

    public abstract String getAreaListByParentId(String str, boolean z, HashMap<String, String> map);

    public abstract AreaWeatherForecastInfoReply getAreaWeatherForecastInfo(String str, String str2, String str3, String str4);

    public abstract AreaWeatherInfoReplay getAreaWeatherInfo(String str, String str2, String str3, String str4);

    public abstract CallCenterReplyInfo getCallCenter(String str, boolean z, HashMap<String, String> map);

    public abstract LocalAreaInfoReply getLocalAreaInfo(String str, boolean z, HashMap<String, String> map);
}
