package org.schabi.newpipe.extractor.services.peertube.extractors;

import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.MultiInfoItemsCollector;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeChannelLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeChannelTabLinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubeChannelTabExtractor extends ChannelTabExtractor {
    private final String baseUrl;

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) {
    }

    public PeertubeChannelTabExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) throws ParsingException {
        super(streamingService, listLinkHandler);
        this.baseUrl = getBaseUrl();
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<InfoItem> getInitialPage() throws IOException, ExtractionException {
        return getPage(new Page(this.baseUrl + PeertubeChannelLinkHandlerFactory.API_ENDPOINT + getId() + PeertubeChannelTabLinkHandlerFactory.getUrlSuffix(getName()) + "?start=0&count=12"));
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<InfoItem> getPage(Page page) throws IOException, ExtractionException {
        JsonObject jsonObjectFrom;
        if (page == null || Utils.isNullOrEmpty(page.getUrl())) {
            throw new IllegalArgumentException("Page doesn't contain an URL");
        }
        Response response = getDownloader().get(page.getUrl());
        if (response == null || Utils.isBlank(response.responseBody())) {
            jsonObjectFrom = null;
        } else {
            try {
                jsonObjectFrom = JsonParser.object().from(response.responseBody());
            } catch (Exception e) {
                throw new ParsingException("Could not parse json data for account info", e);
            }
        }
        if (jsonObjectFrom == null) {
            throw new ExtractionException("Unable to get account channel list");
        }
        PeertubeParsingHelper.validate(jsonObjectFrom);
        MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
        PeertubeParsingHelper.collectItemsFrom(multiInfoItemsCollector, jsonObjectFrom, getBaseUrl());
        return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, PeertubeParsingHelper.getNextPage(page.getUrl(), jsonObjectFrom.getLong("total")));
    }
}
