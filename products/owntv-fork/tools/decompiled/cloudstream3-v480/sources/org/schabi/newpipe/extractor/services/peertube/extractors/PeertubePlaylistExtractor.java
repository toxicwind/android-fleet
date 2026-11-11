package org.schabi.newpipe.extractor.services.peertube.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.playlist.PlaylistExtractor;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubePlaylistExtractor extends PlaylistExtractor {
    private JsonObject playlistInfo;

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return false;
    }

    public PeertubePlaylistExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return PeertubeParsingHelper.getThumbnailsFromPlaylistOrVideoItem(getBaseUrl(), this.playlistInfo);
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public String getUploaderUrl() {
        return this.playlistInfo.getObject("ownerAccount").getString(ImagesContract.URL);
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public String getUploaderName() {
        return this.playlistInfo.getObject("ownerAccount").getString("displayName");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() throws ParsingException {
        return PeertubeParsingHelper.getAvatarsFromOwnerAccountOrVideoChannelObject(getBaseUrl(), this.playlistInfo.getObject("ownerAccount"));
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public long getStreamCount() {
        return this.playlistInfo.getLong("videosLength");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public Description getDescription() throws ParsingException {
        String string = this.playlistInfo.getString("description");
        if (Utils.isNullOrEmpty(string)) {
            return Description.EMPTY_DESCRIPTION;
        }
        return new Description(string, 3);
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public String getSubChannelName() {
        return this.playlistInfo.getObject("videoChannel").getString("displayName");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public String getSubChannelUrl() {
        return this.playlistInfo.getObject("videoChannel").getString(ImagesContract.URL);
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public List<Image> getSubChannelAvatars() throws ParsingException {
        return PeertubeParsingHelper.getAvatarsFromOwnerAccountOrVideoChannelObject(getBaseUrl(), this.playlistInfo.getObject("videoChannel"));
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() throws IOException, ExtractionException {
        return getPage(new Page(getUrl() + "/videos?start=0&count=12"));
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
                throw new ParsingException("Could not parse json data for playlist info", e);
            }
        }
        if (jsonObjectFrom != null) {
            PeertubeParsingHelper.validate(jsonObjectFrom);
            long j = jsonObjectFrom.getLong("total");
            StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
            PeertubeParsingHelper.collectItemsFrom(streamInfoItemsCollector, jsonObjectFrom, getBaseUrl());
            return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, PeertubeParsingHelper.getNextPage(page.getUrl(), j));
        }
        throw new ExtractionException("Unable to get PeerTube playlist info");
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        try {
            JsonObject jsonObjectFrom = JsonParser.object().from(downloader.get(getUrl()).responseBody());
            this.playlistInfo = jsonObjectFrom;
            PeertubeParsingHelper.validate(jsonObjectFrom);
        } catch (JsonParserException e) {
            throw new ExtractionException("Could not parse json", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return this.playlistInfo.getString("displayName");
    }
}
