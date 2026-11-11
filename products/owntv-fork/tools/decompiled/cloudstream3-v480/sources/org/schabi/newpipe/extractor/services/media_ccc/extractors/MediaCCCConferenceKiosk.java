package org.schabi.newpipe.extractor.services.media_ccc.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.ChannelInfoItem;
import org.schabi.newpipe.extractor.channel.ChannelInfoItemsCollector;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.kiosk.KioskExtractor;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.services.media_ccc.extractors.infoItems.MediaCCCConferenceInfoItemExtractor;

/* JADX INFO: loaded from: classes8.dex */
public class MediaCCCConferenceKiosk extends KioskExtractor<ChannelInfoItem> {
    public static final String KIOSK_ID = "conferences";
    private JsonObject doc;

    public MediaCCCConferenceKiosk(StreamingService streamingService, ListLinkHandler listLinkHandler, String str) {
        super(streamingService, listLinkHandler, str);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<ChannelInfoItem> getInitialPage() {
        JsonArray array = this.doc.getArray("conferences");
        ChannelInfoItemsCollector channelInfoItemsCollector = new ChannelInfoItemsCollector(getServiceId());
        for (int i = 0; i < array.size(); i++) {
            channelInfoItemsCollector.commit(new MediaCCCConferenceInfoItemExtractor(array.getObject(i)));
        }
        return new ListExtractor.InfoItemsPage<>(channelInfoItemsCollector, null);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<ChannelInfoItem> getPage(Page page) {
        return ListExtractor.InfoItemsPage.emptyPage();
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        try {
            this.doc = JsonParser.object().from(downloader.get(getLinkHandler().getUrl(), getExtractorLocalization()).responseBody());
        } catch (JsonParserException e) {
            throw new ExtractionException("Could not parse json.", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.kiosk.KioskExtractor, org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return this.doc.getString("Conferences");
    }
}
