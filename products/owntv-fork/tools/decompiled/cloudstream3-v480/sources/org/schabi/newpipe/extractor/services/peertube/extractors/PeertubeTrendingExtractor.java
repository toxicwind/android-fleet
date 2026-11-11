package org.schabi.newpipe.extractor.services.peertube.extractors;

import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.kiosk.KioskExtractor;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubeTrendingExtractor extends KioskExtractor<StreamInfoItem> {
    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
    }

    public PeertubeTrendingExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler, String str) {
        super(streamingService, listLinkHandler, str);
    }

    @Override // org.schabi.newpipe.extractor.kiosk.KioskExtractor, org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return getId();
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() throws IOException, ExtractionException {
        return getPage(new Page(getUrl() + "&start=0&count=12"));
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<StreamInfoItem> getPage(Page page) throws IOException, ExtractionException {
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
                throw new ParsingException("Could not parse json data for kiosk info", e);
            }
        }
        if (jsonObjectFrom != null) {
            PeertubeParsingHelper.validate(jsonObjectFrom);
            long j = jsonObjectFrom.getLong("total");
            StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
            PeertubeParsingHelper.collectItemsFrom(streamInfoItemsCollector, jsonObjectFrom, getBaseUrl());
            return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, PeertubeParsingHelper.getNextPage(page.getUrl(), j));
        }
        throw new ExtractionException("Unable to get PeerTube kiosk info");
    }
}
