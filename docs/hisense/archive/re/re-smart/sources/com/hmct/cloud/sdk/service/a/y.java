package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.bean.wallpaper.MobilebrowserLableReplay;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeBaseReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeCommentReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeDownloadReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeHotWordReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeOrderCommitReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeOrderReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeRankReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeRecommendReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeResourceDetailReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeResourceReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeScoreReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeSearchReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeTopicReply;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.WallPaperService;
import com.hmct.cloud.sdk.utils.Constants;
import com.hmct.cloud.sdk.utils.Params;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class y extends WallPaperService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile WallPaperService f2069b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2070c = "{\"resultCode\":1,\"errorCode\":\"000000\",\"errorDesc\":\"networkError\"}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2071d = "networkError";

    public y(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static WallPaperService a(IHttpApi iHttpApi) {
        if (f2069b == null) {
            synchronized (y.class) {
                if (f2069b == null) {
                    f2069b = new y(iHttpApi);
                }
            }
        }
        return f2069b;
    }

    private String b(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("WallPaperService", "domainName is null");
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
        return HiCloudService.b(((WallPaperService) this).f2001a, HiCloudService.a(str, str2, z), map2, JsonSignature.createJsonSignature(), f2070c, f2071d, map3);
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeOrderCommitReply commitOrder(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.x(b(str, z, "theme/commitorder", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeHotWordReply getThemeHotWord(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.r(a(str, z, "theme/hotWord", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeOrderReply getThemeOrder(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.t(a(str, z, "theme/order", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeRankReply getThemeRank(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.o(a(str, z, "theme/rank", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeRecommendReply getThemeRecommend(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.n(a(str, z, "theme/recommend", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeResourceReply getThemeResource(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.p(a(str, z, "theme/resource", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeResourceDetailReply getThemeResourceDetail(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.q(a(str, z, "theme/resourceDetail", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeTopicReply getThemeTopic(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.z(a(str, z, "theme/topic", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public String globalSearchThemeSearch(String str, boolean z, HashMap<String, String> map) {
        b(map);
        return a(str, z, "theme/globalsearch/themesearch", map);
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public MobilebrowserLableReplay mobilebrowserLable(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.y(a(str, z, "mobilebrowser/lable", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeBaseReply mobilebrowserRecommendlog(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.A(b(str, z, "mobilebrowser/recommendlog", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeDownloadReply themeDownload(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.u(a(str, z, "theme/download", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeCommentReply themeGetComments(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.w(a(str, z, "theme/getComments", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeSearchReply themeSearch(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.s(a(str, z, "theme/search", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeBaseReply uploadThemeComments(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.v(b(str, z, "theme/comments", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.WallPaperService
    public ThemeScoreReply uploadThemeScore(String str, boolean z, HashMap<String, String> map) {
        b(map);
        try {
            return com.hmct.cloud.sdk.a.a.v(b(str, z, "theme/score", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("WallPaperService", "domainName is null");
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
        return HiCloudService.a(((WallPaperService) this).f2001a, HiCloudService.a(str, str2, z), map2, JsonSignature.createJsonSignature(), f2070c, f2071d, map3);
    }

    public static HashMap<String, String> b(HashMap<String, String> map) {
        map.put("version", Constants.DEFAULTAPIVERSION);
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000);
        map.put("timeStamp", sb.toString());
        map.put(Params.FORMAT, "0");
        map.put(Params.LANGUAGEID, "0");
        map.put(Params.INVOKESOURCE, "1");
        return map;
    }
}
