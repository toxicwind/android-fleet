package org.schabi.newpipe.extractor.services.youtube.extractors.kiosk;

import java.io.IOException;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeTrendingPodcastsEpisodesExtractor extends YoutubeDesktopBaseKioskExtractor {
    @Override // org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeDesktopBaseKioskExtractor, org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public /* bridge */ /* synthetic */ ListExtractor.InfoItemsPage getInitialPage() throws IOException, ExtractionException {
        return super.getInitialPage();
    }

    @Override // org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeDesktopBaseKioskExtractor, org.schabi.newpipe.extractor.kiosk.KioskExtractor, org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public /* bridge */ /* synthetic */ String getName() throws ParsingException {
        return super.getName();
    }

    @Override // org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeDesktopBaseKioskExtractor, org.schabi.newpipe.extractor.ListExtractor
    public /* bridge */ /* synthetic */ ListExtractor.InfoItemsPage getPage(Page page) throws IOException, ExtractionException {
        return super.getPage(page);
    }

    @Override // org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeDesktopBaseKioskExtractor, org.schabi.newpipe.extractor.Extractor
    public /* bridge */ /* synthetic */ void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        super.onFetchPage(downloader);
    }

    public YoutubeTrendingPodcastsEpisodesExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler, String str) {
        super(streamingService, listLinkHandler, str, "FEpodcasts_destination", "qgcCCAM%3D");
    }
}
