package org.schabi.newpipe.extractor.services.peertube.extractors;

import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.ChannelExtractor;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeChannelLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeChannelTabLinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.JsonUtils;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubeChannelExtractor extends ChannelExtractor {
    private final String baseUrl;
    private JsonObject json;

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public boolean isVerified() throws ParsingException {
        return false;
    }

    public PeertubeChannelExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) throws ParsingException {
        super(streamingService, listLinkHandler);
        this.baseUrl = getBaseUrl();
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getAvatars() {
        return PeertubeParsingHelper.getAvatarsFromOwnerAccountOrVideoChannelObject(this.baseUrl, this.json);
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getBanners() {
        return PeertubeParsingHelper.getBannersFromAccountOrVideoChannelObject(this.baseUrl, this.json);
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getFeedUrl() throws ParsingException {
        return getBaseUrl() + "/feeds/videos.xml?videoChannelId=" + this.json.get("id");
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public long getSubscriberCount() {
        return this.json.getLong("followersCount");
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nullable
    public String getDescription() {
        return this.json.getString("description");
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getParentChannelName() throws ParsingException {
        return JsonUtils.getString(this.json, "ownerAccount.name");
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getParentChannelUrl() throws ParsingException {
        return JsonUtils.getString(this.json, "ownerAccount.url");
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getParentChannelAvatars() {
        return PeertubeParsingHelper.getAvatarsFromOwnerAccountOrVideoChannelObject(this.baseUrl, this.json.getObject("ownerAccount"));
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<ListLinkHandler> getTabs() throws ParsingException {
        return UByte$$ExternalSyntheticBackport0.m(new Object[]{PeertubeChannelTabLinkHandlerFactory.getInstance().fromQuery(getId(), UByte$$ExternalSyntheticBackport0.m(new Object[]{"videos"}), "", getBaseUrl()), PeertubeChannelTabLinkHandlerFactory.getInstance().fromQuery(getId(), UByte$$ExternalSyntheticBackport0.m(new Object[]{"playlists"}), "", getBaseUrl())});
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        Response response = downloader.get(this.baseUrl + PeertubeChannelLinkHandlerFactory.API_ENDPOINT + getId());
        if (response != null) {
            setInitialData(response.responseBody());
            return;
        }
        throw new ExtractionException("Unable to extract PeerTube channel data");
    }

    private void setInitialData(String str) throws ExtractionException {
        try {
            JsonObject jsonObjectFrom = JsonParser.object().from(str);
            this.json = jsonObjectFrom;
            if (jsonObjectFrom == null) {
                throw new ExtractionException("Unable to extract PeerTube channel data");
            }
        } catch (JsonParserException e) {
            throw new ExtractionException("Unable to extract PeerTube channel data", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return JsonUtils.getString(this.json, "displayName");
    }
}
