package org.schabi.newpipe.extractor.services.youtube.extractors;

import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ContentNotAvailableException;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.feed.FeedExtractor;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeFeedExtractor extends FeedExtractor {
    private static final String WEBSITE_CHANNEL_BASE_URL = "https://www.youtube.com/channel/";
    private Document document;

    public YoutubeFeedExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        Response response = downloader.get(YoutubeParsingHelper.getFeedUrlFrom(getLinkHandler().getId()));
        if (response.responseCode() == 404) {
            throw new ContentNotAvailableException("Could not get feed: 404 - not found");
        }
        this.document = Jsoup.parse(response.responseBody());
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() {
        Elements elementsSelect = this.document.select("feed > entry");
        StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        Iterator it = elementsSelect.iterator();
        while (it.hasNext()) {
            streamInfoItemsCollector.commit((StreamInfoItemExtractor) new YoutubeFeedInfoItemExtractor((Element) it.next()));
        }
        return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, null);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getId() {
        return getUrl().replace(WEBSITE_CHANNEL_BASE_URL, "");
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getUrl() {
        Element elementFirst = this.document.select("feed > author > uri").first();
        if (elementFirst != null) {
            String strText = elementFirst.text();
            if (!strText.equals("")) {
                return strText;
            }
        }
        Element elementFirst2 = this.document.select("feed > link[rel*=alternate]").first();
        if (elementFirst2 == null) {
            return "";
        }
        return elementFirst2.attr("href");
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() {
        Element elementFirst = this.document.select("feed > author > name").first();
        if (elementFirst == null) {
            return "";
        }
        return elementFirst.text();
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<StreamInfoItem> getPage(Page page) {
        return ListExtractor.InfoItemsPage.emptyPage();
    }
}
