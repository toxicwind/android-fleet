package com.lagradost.cloudstream3;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0016H&J\u0018\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0018H&R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00010\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR \u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX¦\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001cÀ\u0006\u0003"}, d2 = {"Lcom/lagradost/cloudstream3/EpisodeResponse;", "", "showStatus", "Lcom/lagradost/cloudstream3/ShowStatus;", "getShowStatus", "()Lcom/lagradost/cloudstream3/ShowStatus;", "setShowStatus", "(Lcom/lagradost/cloudstream3/ShowStatus;)V", "nextAiring", "Lcom/lagradost/cloudstream3/NextAiring;", "getNextAiring", "()Lcom/lagradost/cloudstream3/NextAiring;", "setNextAiring", "(Lcom/lagradost/cloudstream3/NextAiring;)V", "seasonNames", "", "Lcom/lagradost/cloudstream3/SeasonData;", "getSeasonNames", "()Ljava/util/List;", "setSeasonNames", "(Ljava/util/List;)V", "getLatestEpisodes", "", "Lcom/lagradost/cloudstream3/DubStatus;", "", "getTotalEpisodeIndex", "episode", "season", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface EpisodeResponse {
    Map<DubStatus, Integer> getLatestEpisodes();

    NextAiring getNextAiring();

    List<SeasonData> getSeasonNames();

    ShowStatus getShowStatus();

    int getTotalEpisodeIndex(int episode, int season);

    void setNextAiring(NextAiring nextAiring);

    void setSeasonNames(List<SeasonData> list);

    void setShowStatus(ShowStatus showStatus);
}
