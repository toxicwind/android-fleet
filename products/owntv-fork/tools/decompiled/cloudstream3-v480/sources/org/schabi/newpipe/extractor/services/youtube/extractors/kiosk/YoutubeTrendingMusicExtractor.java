package org.schabi.newpipe.extractor.services.youtube.extractors.kiosk;

import java.io.IOException;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.UnsupportedContentInCountryException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeTrendingMusicExtractor extends YoutubeChartsBaseKioskExtractor {
    @Override // org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeChartsBaseKioskExtractor, org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public /* bridge */ /* synthetic */ ListExtractor.InfoItemsPage getInitialPage() throws IOException, ExtractionException {
        return super.getInitialPage();
    }

    @Override // org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeChartsBaseKioskExtractor, org.schabi.newpipe.extractor.ListExtractor
    public /* bridge */ /* synthetic */ ListExtractor.InfoItemsPage getPage(Page page) {
        return super.getPage(page);
    }

    public YoutubeTrendingMusicExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler, String str) {
        super(streamingService, listLinkHandler, str, "TRENDING_VIDEOS");
    }

    @Override // org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeChartsBaseKioskExtractor, org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        if (!YT_CHARTS_SUPPORTED_COUNTRY_CODES.contains(getExtractorContentCountry().getCountryCode())) {
            throw new UnsupportedContentInCountryException("YouTube Charts doesn't support this country for trending music videos charts");
        }
        super.onFetchPage(downloader);
    }

    @Override // org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeChartsBaseKioskExtractor, org.schabi.newpipe.extractor.kiosk.KioskExtractor, org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return "Trending Music Videos";
    }
}
