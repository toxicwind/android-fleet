package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import java.io.IOException;
import java.util.HashSet;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.MultiInfoItemsCollector;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudChannelTabExtractor extends ChannelTabExtractor {
    private static final int MAX_EMPTY_PAGES = 3;
    private static final String USERS_ENDPOINT = "https://api-v2.soundcloud.com/users/";
    private final String userId;

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) {
    }

    public SoundcloudChannelTabExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
        this.userId = getLinkHandler().getId();
    }

    @Nonnull
    private String getEndpoint() throws ParsingException {
        String name = getName();
        name.hashCode();
        switch (name) {
            case "playlists":
                return "/playlists_without_albums";
            case "albums":
                return "/albums";
            case "tracks":
                return "/tracks";
            case "likes":
                return "/likes";
            default:
                throw new ParsingException("Unsupported tab: " + getName());
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getId() {
        return this.userId;
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<InfoItem> getInitialPage() throws IOException, ExtractionException {
        return getPage(new Page(USERS_ENDPOINT + this.userId + getEndpoint() + "?client_id=" + SoundcloudParsingHelper.clientId() + "&limit=20&linked_partitioning=1"));
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<InfoItem> getPage(Page page) throws IOException, ExtractionException {
        String str;
        if (page == null || Utils.isNullOrEmpty(page.getUrl())) {
            throw new IllegalArgumentException("Page doesn't contain an URL");
        }
        MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
        HashSet hashSet = new HashSet();
        String url = page.getUrl();
        int i = 0;
        do {
            str = "";
            if (!Utils.isNullOrEmpty(url) && hashSet.add(url)) {
                int size = multiInfoItemsCollector.getItems().size();
                url = SoundcloudParsingHelper.getInfoItemsFromApi(multiInfoItemsCollector, url);
                if (multiInfoItemsCollector.getItems().size() <= size) {
                    i++;
                    if (i >= 3) {
                        break;
                    }
                } else {
                    str = url;
                    break;
                }
            } else {
                break;
            }
        } while (!Utils.isNullOrEmpty(url));
        return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, Utils.isNullOrEmpty(str) ? null : new Page(str));
    }
}
