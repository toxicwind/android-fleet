package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import androidx.autofill.HintConstants;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.ChannelExtractor;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabs;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;
import org.schabi.newpipe.extractor.services.soundcloud.linkHandler.SoundcloudChannelTabLinkHandlerFactory;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudChannelExtractor extends ChannelExtractor {
    private static final String USERS_ENDPOINT = "https://api-v2.soundcloud.com/users/";
    private JsonObject user;
    private String userId;

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getFeedUrl() {
        return null;
    }

    public SoundcloudChannelExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        String id = getLinkHandler().getId();
        this.userId = id;
        try {
            this.user = JsonParser.object().from(downloader.get(USERS_ENDPOINT + id + "?client_id=" + SoundcloudParsingHelper.clientId(), getExtractorLocalization()).responseBody());
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse json response", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getId() {
        return this.userId;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() {
        return this.user.getString(HintConstants.AUTOFILL_HINT_USERNAME);
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getAvatars() {
        return SoundcloudParsingHelper.getAllImagesFromArtworkOrAvatarUrl(this.user.getString("avatar_url"));
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getBanners() {
        return SoundcloudParsingHelper.getAllImagesFromVisualUrl(this.user.getObject("visuals").getArray("visuals").getObject(0).getString("visual_url"));
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public long getSubscriberCount() {
        return this.user.getLong("followers_count", 0L);
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getDescription() {
        return this.user.getString("description", "");
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getParentChannelName() {
        return "";
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getParentChannelUrl() {
        return "";
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getParentChannelAvatars() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public boolean isVerified() throws ParsingException {
        return this.user.getBoolean("verified");
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<ListLinkHandler> getTabs() throws ParsingException {
        String url = getUrl();
        String str = url + SoundcloudChannelTabLinkHandlerFactory.getUrlSuffix("tracks");
        String str2 = url + SoundcloudChannelTabLinkHandlerFactory.getUrlSuffix("playlists");
        String str3 = url + SoundcloudChannelTabLinkHandlerFactory.getUrlSuffix(ChannelTabs.ALBUMS);
        String str4 = url + SoundcloudChannelTabLinkHandlerFactory.getUrlSuffix(ChannelTabs.LIKES);
        String id = getId();
        return UByte$$ExternalSyntheticBackport0.m(new Object[]{new ListLinkHandler(str, str, id, UByte$$ExternalSyntheticBackport0.m(new Object[]{"tracks"}), ""), new ListLinkHandler(str2, str2, id, UByte$$ExternalSyntheticBackport0.m(new Object[]{"playlists"}), ""), new ListLinkHandler(str3, str3, id, UByte$$ExternalSyntheticBackport0.m(new Object[]{ChannelTabs.ALBUMS}), ""), new ListLinkHandler(str4, str4, id, UByte$$ExternalSyntheticBackport0.m(new Object[]{ChannelTabs.LIKES}), "")});
    }
}
