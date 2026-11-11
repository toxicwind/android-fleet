package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.utils.Parser;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeMusicArtistInfoItemExtractor implements ChannelInfoItemExtractor {
    private final JsonObject artistInfoItem;

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    @Nullable
    public String getDescription() {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public long getStreamCount() {
        return -1L;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public boolean isVerified() {
        return true;
    }

    public YoutubeMusicArtistInfoItemExtractor(JsonObject jsonObject) {
        this.artistInfoItem = jsonObject;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        try {
            return YoutubeParsingHelper.getImagesFromThumbnailsArray(this.artistInfoItem.getObject("thumbnail").getObject("musicThumbnailRenderer").getObject("thumbnail").getArray("thumbnails"));
        } catch (Exception e) {
            throw new ParsingException("Could not get thumbnails", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        String textFromObject = YoutubeParsingHelper.getTextFromObject(this.artistInfoItem.getArray("flexColumns").getObject(0).getObject("musicResponsiveListItemFlexColumnRenderer").getObject("text"));
        if (Utils.isNullOrEmpty(textFromObject)) {
            throw new ParsingException("Could not get name");
        }
        return textFromObject;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        String urlFromNavigationEndpoint = YoutubeParsingHelper.getUrlFromNavigationEndpoint(this.artistInfoItem.getObject("navigationEndpoint"));
        if (Utils.isNullOrEmpty(urlFromNavigationEndpoint)) {
            throw new ParsingException("Could not get URL");
        }
        return urlFromNavigationEndpoint;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public long getSubscriberCount() throws ParsingException {
        JsonArray array = this.artistInfoItem.getArray("flexColumns");
        JsonArray array2 = array.getObject(array.size() - 1).getObject("musicResponsiveListItemFlexColumnRenderer").getObject("text").getArray("runs");
        String string = array2.getObject(array2.size() - 1).getString("text");
        if (!Utils.isNullOrEmpty(string)) {
            try {
                return Utils.mixedNumberWordToLong(string);
            } catch (Parser.RegexException unused) {
                return 0L;
            }
        }
        throw new ParsingException("Could not get subscriber count");
    }
}
