package com.hmct.cloud.sdk.factory;

import com.hmct.cloud.sdk.service.BaspService;
import com.hmct.cloud.sdk.service.ChcaService;
import com.hmct.cloud.sdk.service.DeviceRemoteCtlService;
import com.hmct.cloud.sdk.service.EsnService;
import com.hmct.cloud.sdk.service.FKNowService;
import com.hmct.cloud.sdk.service.HiCloudAccountService;
import com.hmct.cloud.sdk.service.HiVipEcifService;
import com.hmct.cloud.sdk.service.IotService;
import com.hmct.cloud.sdk.service.LauncherService;
import com.hmct.cloud.sdk.service.LiveApiService;
import com.hmct.cloud.sdk.service.LiveTVService;
import com.hmct.cloud.sdk.service.MamService;
import com.hmct.cloud.sdk.service.MessageService;
import com.hmct.cloud.sdk.service.NetCheckService;
import com.hmct.cloud.sdk.service.ProfessionApiService;
import com.hmct.cloud.sdk.service.ShoppingGuiderApiService;
import com.hmct.cloud.sdk.service.TVService;
import com.hmct.cloud.sdk.service.UniversalService;
import com.hmct.cloud.sdk.service.UpgradeService;
import com.hmct.cloud.sdk.service.UpgradeServiceSignMd5;
import com.hmct.cloud.sdk.service.VIPCloudService;
import com.hmct.cloud.sdk.service.VoiceBoxApiService;
import com.hmct.cloud.sdk.service.VoiceService;
import com.hmct.cloud.sdk.service.WallPaperService;
import com.hmct.cloud.sdk.service.WebAppStoreApiService;
import com.hmct.cloud.sdk.service.a.a;
import com.hmct.cloud.sdk.service.a.b;
import com.hmct.cloud.sdk.service.a.c;
import com.hmct.cloud.sdk.service.a.e;
import com.hmct.cloud.sdk.service.a.f;
import com.hmct.cloud.sdk.service.a.g;
import com.hmct.cloud.sdk.service.a.h;
import com.hmct.cloud.sdk.service.a.i;
import com.hmct.cloud.sdk.service.a.j;
import com.hmct.cloud.sdk.service.a.k;
import com.hmct.cloud.sdk.service.a.l;
import com.hmct.cloud.sdk.service.a.m;
import com.hmct.cloud.sdk.service.a.n;
import com.hmct.cloud.sdk.service.a.o;
import com.hmct.cloud.sdk.service.a.p;
import com.hmct.cloud.sdk.service.a.q;
import com.hmct.cloud.sdk.service.a.r;
import com.hmct.cloud.sdk.service.a.s;
import com.hmct.cloud.sdk.service.a.t;
import com.hmct.cloud.sdk.service.a.u;
import com.hmct.cloud.sdk.service.a.v;
import com.hmct.cloud.sdk.service.a.w;
import com.hmct.cloud.sdk.service.a.x;
import com.hmct.cloud.sdk.service.a.y;
import com.hmct.cloud.sdk.service.a.z;
import com.ju.lib.datacommunication.network.http.HttpManager;
import com.ju.lib.datacommunication.network.http.IHttpApi;

/* JADX INFO: loaded from: classes2.dex */
public class HiCloudServiceFactory {
    public static IHttpApi iHttpApi = HttpManager.setupHttpApi(HttpManager.obtainHttpConfig().setConnectTimeOut(10000).setReadTimeOut(10000).setHttpDns(false, null, null).setEnableLog(true));

    public static BaspService getBaspService() {
        return a.a(iHttpApi);
    }

    public static ChcaService getChcaService() {
        return b.a(iHttpApi);
    }

    public static DeviceRemoteCtlService getDeviceRemoteCtlService() {
        return c.a(iHttpApi);
    }

    public static EsnService getEsnService() {
        return e.a(iHttpApi);
    }

    public static FKNowService getFKNowService() {
        return f.a(iHttpApi);
    }

    public static HiCloudAccountService getHiCloudAccountService() {
        return g.a(iHttpApi);
    }

    public static HiVipEcifService getHiVipEcifService() {
        return h.a(iHttpApi);
    }

    public static IotService getIotService() {
        return i.a(iHttpApi);
    }

    public static LauncherService getLauncherService() {
        return j.a(iHttpApi);
    }

    public static LiveApiService getLiveApiService() {
        return k.a(iHttpApi);
    }

    public static LiveTVService getLiveTVService() {
        return l.a(iHttpApi);
    }

    public static MamService getMamService() {
        return m.a(iHttpApi);
    }

    public static MessageService getMessageService() {
        return n.a(iHttpApi);
    }

    public static NetCheckService getNetCheckService() {
        return o.a(iHttpApi);
    }

    public static ProfessionApiService getProfessionApiService() {
        return p.a(iHttpApi);
    }

    public static ShoppingGuiderApiService getShoppingGuiderApiService() {
        return q.a(iHttpApi);
    }

    public static TVService getTVService() {
        return r.a(iHttpApi);
    }

    public static UniversalService getUniversalService() {
        return s.a(iHttpApi);
    }

    public static UpgradeService getUpgradeService() {
        return t.a(iHttpApi);
    }

    public static UpgradeServiceSignMd5 getUpgradeServiceSignMd5() {
        return u.a(iHttpApi);
    }

    public static VIPCloudService getVIPCloudService() {
        return v.a(iHttpApi);
    }

    public static VoiceBoxApiService getVoiceBoxApiService() {
        return w.a(iHttpApi);
    }

    public static VoiceService getVoiceService() {
        return x.a(iHttpApi);
    }

    public static WallPaperService getWallPaperService() {
        return y.a(iHttpApi);
    }

    public static WebAppStoreApiService getWebAppStoreApiService() {
        return z.a(iHttpApi);
    }
}
