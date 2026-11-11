package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.MultiInfoItemsCollector;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabs;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.services.bandcamp.extractors.streaminfoitem.BandcampDiscographStreamInfoItemExtractor;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampChannelTabExtractor extends ChannelTabExtractor {
    private JsonArray discography;
    private final String filter;

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<InfoItem> getPage(Page page) {
        return null;
    }

    public BandcampChannelTabExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
        String str = listLinkHandler.getContentFilters().get(0);
        str.hashCode();
        if (str.equals(ChannelTabs.ALBUMS)) {
            this.filter = "album";
        } else if (str.equals("tracks")) {
            this.filter = "track";
        } else {
            throw new IllegalArgumentException("Unsupported channel tab: " + str);
        }
    }

    public static BandcampChannelTabExtractor fromDiscography(StreamingService streamingService, ListLinkHandler listLinkHandler, JsonArray jsonArray) {
        BandcampChannelTabExtractor bandcampChannelTabExtractor = new BandcampChannelTabExtractor(streamingService, listLinkHandler);
        bandcampChannelTabExtractor.discography = jsonArray;
        return bandcampChannelTabExtractor;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws ParsingException {
        if (this.discography == null) {
            this.discography = BandcampExtractorHelper.getArtistDetails(getId()).getArray("discography");
        }
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<InfoItem> getInitialPage() throws IOException, ExtractionException {
        MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
        for (Object obj : this.discography) {
            if (obj instanceof JsonObject) {
                JsonObject jsonObject = (JsonObject) obj;
                String string = jsonObject.getString("item_type", "");
                if (string.equals(this.filter)) {
                    string.hashCode();
                    if (string.equals("album")) {
                        multiInfoItemsCollector.commit(new BandcampAlbumInfoItemExtractor(jsonObject, getUrl()));
                    } else if (string.equals("track")) {
                        multiInfoItemsCollector.commit(new BandcampDiscographStreamInfoItemExtractor(jsonObject, getUrl()));
                    }
                }
            }
        }
        return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, null);
    }
}
