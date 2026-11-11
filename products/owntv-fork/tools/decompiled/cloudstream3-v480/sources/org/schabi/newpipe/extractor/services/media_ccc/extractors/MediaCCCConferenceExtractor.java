package org.schabi.newpipe.extractor.services.media_ccc.extractors;

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
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.linkhandler.ReadyChannelTabListLinkHandler;
import org.schabi.newpipe.extractor.services.media_ccc.linkHandler.MediaCCCConferenceLinkHandlerFactory;

/* JADX INFO: loaded from: classes8.dex */
public class MediaCCCConferenceExtractor extends ChannelExtractor {
    private JsonObject conferenceData;

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getDescription() {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getFeedUrl() {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public long getSubscriberCount() {
        return -1L;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public boolean isVerified() {
        return false;
    }

    public MediaCCCConferenceExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    static JsonObject fetchConferenceData(@Nonnull Downloader downloader, @Nonnull String str) throws IOException, ExtractionException {
        String str2 = MediaCCCConferenceLinkHandlerFactory.CONFERENCE_API_ENDPOINT + str;
        try {
            return JsonParser.object().from(downloader.get(str2).responseBody());
        } catch (JsonParserException unused) {
            throw new ExtractionException("Could not parse json returned by URL: " + str2);
        }
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getAvatars() {
        return MediaCCCParsingHelper.getImageListFromLogoImageUrl(this.conferenceData.getString("logo_url"));
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getBanners() {
        return Collections.EMPTY_LIST;
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
    @Nonnull
    public List<ListLinkHandler> getTabs() throws ParsingException {
        return UByte$$ExternalSyntheticBackport0.m(new Object[]{new ReadyChannelTabListLinkHandler(getUrl(), getId(), "videos", new MediaCCCConferenceExtractor$$ExternalSyntheticLambda0(this.conferenceData))});
    }

    static /* synthetic */ ChannelTabExtractor lambda$getTabs$91397d47$1(JsonObject jsonObject, StreamingService streamingService, ListLinkHandler listLinkHandler) {
        return new MediaCCCChannelTabExtractor(streamingService, listLinkHandler, jsonObject);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        this.conferenceData = fetchConferenceData(downloader, getId());
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return this.conferenceData.getString("title");
    }
}
