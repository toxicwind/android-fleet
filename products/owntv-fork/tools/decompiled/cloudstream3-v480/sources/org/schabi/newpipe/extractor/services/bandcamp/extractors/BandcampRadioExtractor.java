package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.kiosk.KioskExtractor;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampRadioExtractor extends KioskExtractor<StreamInfoItem> {
    public static final String KIOSK_RADIO = "Radio";
    public static final String RADIO_API_URL = "https://bandcamp.com/api/bcweekly/3/list";
    private JsonObject json;

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<StreamInfoItem> getPage(Page page) {
        return null;
    }

    public BandcampRadioExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler, String str) {
        super(streamingService, listLinkHandler, str);
        this.json = null;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        try {
            this.json = JsonParser.object().from(getDownloader().get(RADIO_API_URL).responseBody());
        } catch (JsonParserException e) {
            throw new ExtractionException("Could not parse Bandcamp Radio API response", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.kiosk.KioskExtractor, org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return KIOSK_RADIO;
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() {
        StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        JsonArray array = this.json.getArray("results");
        for (int i = 0; i < array.size(); i++) {
            streamInfoItemsCollector.commit((StreamInfoItemExtractor) new BandcampRadioInfoItemExtractor(array.getObject(i)));
        }
        return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, null);
    }
}
