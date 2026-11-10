package com.hmct.cloud.sdk.service;

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
import com.hmct.cloud.sdk.service.a.y;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class WallPaperService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f2001a;

    public WallPaperService(IHttpApi iHttpApi) {
        this.f2001a = iHttpApi;
    }

    public static WallPaperService getService(IHttpApi iHttpApi) {
        return y.a(iHttpApi);
    }

    public abstract ThemeOrderCommitReply commitOrder(String str, boolean z, HashMap<String, String> map);

    public abstract ThemeHotWordReply getThemeHotWord(String str, boolean z, HashMap<String, String> map);

    public abstract ThemeOrderReply getThemeOrder(String str, boolean z, HashMap<String, String> map);

    public abstract ThemeRankReply getThemeRank(String str, boolean z, HashMap<String, String> map);

    public abstract ThemeRecommendReply getThemeRecommend(String str, boolean z, HashMap<String, String> map);

    public abstract ThemeResourceReply getThemeResource(String str, boolean z, HashMap<String, String> map);

    public abstract ThemeResourceDetailReply getThemeResourceDetail(String str, boolean z, HashMap<String, String> map);

    public abstract ThemeTopicReply getThemeTopic(String str, boolean z, HashMap<String, String> map);

    public abstract String globalSearchThemeSearch(String str, boolean z, HashMap<String, String> map);

    public abstract MobilebrowserLableReplay mobilebrowserLable(String str, boolean z, HashMap<String, String> map);

    public abstract ThemeBaseReply mobilebrowserRecommendlog(String str, boolean z, HashMap<String, String> map);

    public abstract ThemeDownloadReply themeDownload(String str, boolean z, HashMap<String, String> map);

    public abstract ThemeCommentReply themeGetComments(String str, boolean z, HashMap<String, String> map);

    public abstract ThemeSearchReply themeSearch(String str, boolean z, HashMap<String, String> map);

    public abstract ThemeBaseReply uploadThemeComments(String str, boolean z, HashMap<String, String> map);

    public abstract ThemeScoreReply uploadThemeScore(String str, boolean z, HashMap<String, String> map);
}
