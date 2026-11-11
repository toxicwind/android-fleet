package org.schabi.newpipe.extractor.services.youtube.extractors;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabs;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ContentNotAvailableException;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.playlist.PlaylistExtractor;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubePlaylistLinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeChannelTabPlaylistExtractor extends ChannelTabExtractor {
    private boolean playlistExisting;
    private final PlaylistExtractor playlistExtractorInstance;

    public YoutubeChannelTabPlaylistExtractor(@Nonnull StreamingService streamingService, @Nonnull ListLinkHandler listLinkHandler) throws SystemPlaylistUrlCreationException, IllegalArgumentException {
        super(streamingService, listLinkHandler);
        this.playlistExtractorInstance = new YoutubePlaylistExtractor(streamingService, getPlaylistLinkHandler(listLinkHandler));
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        try {
            this.playlistExtractorInstance.onFetchPage(downloader);
            if (this.playlistExisting) {
                return;
            }
            this.playlistExisting = true;
        } catch (ContentNotAvailableException unused) {
        }
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage getInitialPage() throws IOException, ExtractionException {
        if (!this.playlistExisting) {
            return ListExtractor.InfoItemsPage.emptyPage();
        }
        return this.playlistExtractorInstance.getInitialPage();
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage getPage(Page page) throws IOException, ExtractionException {
        if (!this.playlistExisting) {
            return ListExtractor.InfoItemsPage.emptyPage();
        }
        return this.playlistExtractorInstance.getPage(page);
    }

    @Nonnull
    private ListLinkHandler getPlaylistLinkHandler(@Nonnull ListLinkHandler listLinkHandler) throws SystemPlaylistUrlCreationException, IllegalArgumentException {
        String str;
        List<String> contentFilters = listLinkHandler.getContentFilters();
        if (contentFilters.isEmpty()) {
            throw new IllegalArgumentException("A content filter is required");
        }
        String id = listLinkHandler.getId();
        if (Utils.isNullOrEmpty(id) || !id.startsWith("UC")) {
            throw new IllegalArgumentException("Invalid channel ID");
        }
        byte b = 2;
        String strSubstring = id.substring(2);
        String str2 = contentFilters.get(0);
        str2.hashCode();
        switch (str2.hashCode()) {
            case -903148681:
                b = !str2.equals(ChannelTabs.SHORTS) ? (byte) -1 : (byte) 0;
                break;
            case -816678056:
                b = !str2.equals("videos") ? (byte) -1 : (byte) 1;
                break;
            case -439267705:
                if (!str2.equals(ChannelTabs.LIVESTREAMS)) {
                    b = -1;
                }
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
                str = "UUSH" + strSubstring;
                break;
            case 1:
                str = "UULF" + strSubstring;
                break;
            case 2:
                str = "UULV" + strSubstring;
                break;
            default:
                throw new IllegalArgumentException("Only Videos, Shorts and Livestreams tabs can extracted as playlists");
        }
        String str3 = str;
        try {
            String url = YoutubePlaylistLinkHandlerFactory.getInstance().getUrl(str3);
            return new ListLinkHandler(url, url, str3, Collections.EMPTY_LIST, "");
        } catch (ParsingException e) {
            throw new SystemPlaylistUrlCreationException("Could not create a YouTube playlist from a valid playlist ID", e);
        }
    }

    public static final class SystemPlaylistUrlCreationException extends RuntimeException {
        SystemPlaylistUrlCreationException(String str, Throwable th) {
            super(str, th);
        }
    }
}
