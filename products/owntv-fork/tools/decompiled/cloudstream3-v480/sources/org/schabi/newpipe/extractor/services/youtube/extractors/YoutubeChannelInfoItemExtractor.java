package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonObject;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeChannelLinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeChannelInfoItemExtractor implements ChannelInfoItemExtractor {
    private final JsonObject channelInfoItem;
    private final boolean withHandle;

    public YoutubeChannelInfoItemExtractor(JsonObject jsonObject) {
        this.channelInfoItem = jsonObject;
        String textFromObject = YoutubeParsingHelper.getTextFromObject(jsonObject.getObject("subscriberCountText"));
        this.withHandle = textFromObject != null ? textFromObject.startsWith("@") : false;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        try {
            return YoutubeParsingHelper.getThumbnailsFromInfoItem(this.channelInfoItem);
        } catch (Exception e) {
            throw new ParsingException("Could not get thumbnails", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        try {
            return YoutubeParsingHelper.getTextFromObject(this.channelInfoItem.getObject("title"));
        } catch (Exception e) {
            throw new ParsingException("Could not get name", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        try {
            return YoutubeChannelLinkHandlerFactory.getInstance().getUrl("channel/" + this.channelInfoItem.getString("channelId"));
        } catch (Exception e) {
            throw new ParsingException("Could not get url", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public long getSubscriberCount() throws ParsingException {
        try {
            if (!this.channelInfoItem.has("subscriberCountText")) {
                return -1L;
            }
            boolean z = this.withHandle;
            JsonObject jsonObject = this.channelInfoItem;
            if (z) {
                if (jsonObject.has("videoCountText")) {
                    return Utils.mixedNumberWordToLong(YoutubeParsingHelper.getTextFromObject(this.channelInfoItem.getObject("videoCountText")));
                }
                return -1L;
            }
            return Utils.mixedNumberWordToLong(YoutubeParsingHelper.getTextFromObject(jsonObject.getObject("subscriberCountText")));
        } catch (Exception e) {
            throw new ParsingException("Could not get subscriber count", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public long getStreamCount() throws ParsingException {
        try {
            if (!this.withHandle && this.channelInfoItem.has("videoCountText")) {
                return Long.parseLong(Utils.removeNonDigitCharacters(YoutubeParsingHelper.getTextFromObject(this.channelInfoItem.getObject("videoCountText"))));
            }
            return -1L;
        } catch (Exception e) {
            throw new ParsingException("Could not get stream count", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public boolean isVerified() throws ParsingException {
        return YoutubeParsingHelper.isVerified(this.channelInfoItem.getArray("ownerBadges"));
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public String getDescription() throws ParsingException {
        try {
            if (this.channelInfoItem.has("descriptionSnippet")) {
                return YoutubeParsingHelper.getTextFromObject(this.channelInfoItem.getObject("descriptionSnippet"));
            }
            return null;
        } catch (Exception e) {
            throw new ParsingException("Could not get description", e);
        }
    }
}
