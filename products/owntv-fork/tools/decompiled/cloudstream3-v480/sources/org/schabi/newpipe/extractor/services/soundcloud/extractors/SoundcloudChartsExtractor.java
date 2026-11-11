package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import java.io.IOException;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.ServiceList;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.kiosk.KioskExtractor;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.localization.ContentCountry;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudChartsExtractor extends KioskExtractor<StreamInfoItem> {
    private StreamInfoItemsCollector initialFetchCollector;
    private String initialFetchNextPageUrl;

    public SoundcloudChartsExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler, String str) {
        super(streamingService, listLinkHandler, str);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        String str;
        if (this.initialFetchNextPageUrl != null) {
            return;
        }
        this.initialFetchCollector = new StreamInfoItemsCollector(getServiceId());
        String str2 = "https://api-v2.soundcloud.com/charts?genre=soundcloud:genres:all-music&client_id=" + SoundcloudParsingHelper.clientId() + "&kind=trending";
        ContentCountry contentCountry = ServiceList.SoundCloud.getContentCountry();
        if (getService().getSupportedCountries().contains(contentCountry)) {
            str = str2 + "&region=soundcloud:regions:" + contentCountry.getCountryCode();
        } else {
            str = null;
        }
        try {
            StreamInfoItemsCollector streamInfoItemsCollector = this.initialFetchCollector;
            if (str == null) {
                str = str2;
            }
            this.initialFetchNextPageUrl = SoundcloudParsingHelper.getStreamsFromApi(streamInfoItemsCollector, str, true);
        } catch (IOException unused) {
            this.initialFetchNextPageUrl = SoundcloudParsingHelper.getStreamsFromApi(this.initialFetchCollector, str2, true);
        }
    }

    @Override // org.schabi.newpipe.extractor.kiosk.KioskExtractor, org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() {
        return getId();
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<StreamInfoItem> getPage(Page page) throws IOException, ExtractionException {
        if (page == null || Utils.isNullOrEmpty(page.getUrl())) {
            throw new IllegalArgumentException("Page doesn't contain an URL");
        }
        StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, new Page(SoundcloudParsingHelper.getStreamsFromApi(streamInfoItemsCollector, page.getUrl(), true)));
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() throws IOException, ExtractionException {
        return new ListExtractor.InfoItemsPage<>(this.initialFetchCollector, new Page(this.initialFetchNextPageUrl));
    }
}
