package com.lagradost.cloudstream3.ui.search;

import android.app.Activity;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.ui.download.DownloadButtonSetup;
import com.lagradost.cloudstream3.ui.download.DownloadClickEvent;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchHelper;", "", "<init>", "()V", "handleSearchClickCallback", "", "callback", "Lcom/lagradost/cloudstream3/ui/search/SearchClickCallback;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SearchHelper {
    public static final SearchHelper INSTANCE = new SearchHelper();

    private SearchHelper() {
    }

    public final void handleSearchClickCallback(SearchClickCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        SearchResponse card = callback.getCard();
        int action = callback.getAction();
        if (action == 0) {
            AppContextUtils.loadSearchResult$default(AppContextUtils.INSTANCE, card, 0, null, 6, null);
            return;
        }
        if (action == 1) {
            Activity activity = CommonActivity.INSTANCE.getActivity();
            MainActivity mainActivity = activity instanceof MainActivity ? (MainActivity) activity : null;
            if (mainActivity != null) {
                MainActivity.loadPopup$default(mainActivity, callback.getCard(), false, 2, null);
                return;
            } else {
                CommonActivity.INSTANCE.showToast(callback.getCard().getName(), (Integer) 0);
                return;
            }
        }
        if (action != 2) {
            return;
        }
        if (card instanceof DataStoreHelper.ResumeWatchingResult) {
            DataStoreHelper.ResumeWatchingResult resumeWatchingResult = (DataStoreHelper.ResumeWatchingResult) card;
            Integer id = resumeWatchingResult.getId();
            if (id == null) {
                CommonActivity.INSTANCE.showToast(R.string.error_invalid_id, (Integer) 0);
                return;
            }
            if (resumeWatchingResult.isFromDownload()) {
                DownloadButtonSetup downloadButtonSetup = DownloadButtonSetup.INSTANCE;
                String name = resumeWatchingResult.getName();
                String posterUrl = resumeWatchingResult.getPosterUrl();
                Integer episode = resumeWatchingResult.getEpisode();
                int iIntValue = episode != null ? episode.intValue() : 0;
                Integer season = resumeWatchingResult.getSeason();
                int iIntValue2 = id.intValue();
                Integer parentId = resumeWatchingResult.getParentId();
                if (parentId != null) {
                    downloadButtonSetup.handleDownloadClick(new DownloadClickEvent(0, new DownloadObjects.DownloadEpisodeCached(name, posterUrl, iIntValue, season, parentId.intValue(), null, null, System.currentTimeMillis(), iIntValue2)));
                    return;
                }
                return;
            }
            AppContextUtils.INSTANCE.loadSearchResult(card, 2, id);
            return;
        }
        handleSearchClickCallback(new SearchClickCallback(0, callback.getView(), -1, callback.getCard()));
    }
}
