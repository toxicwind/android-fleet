package com.lagradost.cloudstream3.ui.result;

import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SeasonData;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResultFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0005\u001a\n\u0010\u0006\u001a\u00020\u0004*\u00020\u0005\u001aÏ\u0001\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u00012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f¢\u0006\u0002\u0010 \u001a\n\u0010!\u001a\u00020\"*\u00020\u0005\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"START_ACTION_RESUME_LATEST", "", "START_ACTION_LOAD_EP", "getRealPosition", "", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "getDisplayPosition", "buildResultEpisode", "headerName", "", "name", "poster", "episode", "seasonIndex", "season", "data", "apiName", "id", "index", "rating", "Lcom/lagradost/cloudstream3/Score;", "description", "isFiller", "", "tvType", "Lcom/lagradost/cloudstream3/TvType;", "parentId", "totalEpisodeIndex", "airDate", "runTime", "seasonData", "Lcom/lagradost/cloudstream3/SeasonData;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;IILcom/lagradost/cloudstream3/Score;Ljava/lang/String;Ljava/lang/Boolean;Lcom/lagradost/cloudstream3/TvType;ILjava/lang/Integer;Ljava/lang/Long;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SeasonData;)Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "getWatchProgress", "", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ResultFragmentKt {
    public static final int START_ACTION_LOAD_EP = 2;
    public static final int START_ACTION_RESUME_LATEST = 1;

    public static final long getRealPosition(ResultEpisode resultEpisode) {
        Intrinsics.checkNotNullParameter(resultEpisode, "<this>");
        if (resultEpisode.getDuration() <= 0) {
            return 0L;
        }
        long position = (resultEpisode.getPosition() * 100) / resultEpisode.getDuration();
        if (position <= 5 || position >= 95) {
            return 0L;
        }
        return resultEpisode.getPosition();
    }

    public static final long getDisplayPosition(ResultEpisode resultEpisode) {
        Intrinsics.checkNotNullParameter(resultEpisode, "<this>");
        if (resultEpisode.getDuration() <= 0) {
            return 0L;
        }
        long position = (resultEpisode.getPosition() * 100) / resultEpisode.getDuration();
        if (position <= 1) {
            return 0L;
        }
        if (position <= 5) {
            return (5 * resultEpisode.getDuration()) / 100;
        }
        if (position >= 95) {
            return resultEpisode.getDuration();
        }
        return resultEpisode.getPosition();
    }

    public static final ResultEpisode buildResultEpisode(String headerName, String str, String str2, int i, Integer num, Integer num2, String data, String apiName, int i2, int i3, Score score, String str3, Boolean bool, TvType tvType, int i4, Integer num3, Long l, Integer num4, SeasonData seasonData) {
        Intrinsics.checkNotNullParameter(headerName, "headerName");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(tvType, "tvType");
        DataStoreHelper.PosDur viewPos = DataStoreHelper.INSTANCE.getViewPos(Integer.valueOf(i2));
        VideoWatchState videoWatchState = DataStoreHelper.INSTANCE.getVideoWatchState(Integer.valueOf(i2));
        if (videoWatchState == null) {
            videoWatchState = VideoWatchState.None;
        }
        return new ResultEpisode(headerName, str, str2, i, num, num2, data, apiName, i2, i3, viewPos != null ? viewPos.getPosition() : 0L, viewPos != null ? viewPos.getDuration() : 0L, score, str3, bool, tvType, i4, videoWatchState, num3, l, num4, seasonData);
    }

    public static final float getWatchProgress(ResultEpisode resultEpisode) {
        Intrinsics.checkNotNullParameter(resultEpisode, "<this>");
        return (getDisplayPosition(resultEpisode) / 1000) / (resultEpisode.getDuration() / 1000);
    }
}
