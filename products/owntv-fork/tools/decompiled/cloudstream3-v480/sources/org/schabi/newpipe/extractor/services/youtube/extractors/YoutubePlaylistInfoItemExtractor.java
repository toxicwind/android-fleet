package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.playlist.PlaylistInfo;
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubePlaylistLinkHandlerFactory;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubePlaylistInfoItemExtractor implements PlaylistInfoItemExtractor {
    private final JsonObject playlistInfoItem;

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ Description getDescription() {
        return Description.EMPTY_DESCRIPTION;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ PlaylistInfo.PlaylistType getPlaylistType() {
        return PlaylistInfo.PlaylistType.NORMAL;
    }

    public YoutubePlaylistInfoItemExtractor(JsonObject jsonObject) {
        this.playlistInfoItem = jsonObject;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        try {
            JsonArray array = this.playlistInfoItem.getArray("thumbnails").getObject(0).getArray("thumbnails");
            if (array.isEmpty()) {
                array = this.playlistInfoItem.getObject("thumbnail").getArray("thumbnails");
            }
            return YoutubeParsingHelper.getImagesFromThumbnailsArray(array);
        } catch (Exception e) {
            throw new ParsingException("Could not get thumbnails", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        try {
            return YoutubeParsingHelper.getTextFromObject(this.playlistInfoItem.getObject("title"));
        } catch (Exception e) {
            throw new ParsingException("Could not get name", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        try {
            return YoutubePlaylistLinkHandlerFactory.getInstance().getUrl(this.playlistInfoItem.getString("playlistId"));
        } catch (Exception e) {
            throw new ParsingException("Could not get url", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        try {
            return YoutubeParsingHelper.getTextFromObject(this.playlistInfoItem.getObject("longBylineText"));
        } catch (Exception e) {
            throw new ParsingException("Could not get uploader name", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        try {
            return YoutubeParsingHelper.getUrlFromObject(this.playlistInfoItem.getObject("longBylineText"));
        } catch (Exception e) {
            throw new ParsingException("Could not get uploader url", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        try {
            return YoutubeParsingHelper.isVerified(this.playlistInfoItem.getArray("ownerBadges"));
        } catch (Exception e) {
            throw new ParsingException("Could not get uploader verification info", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public long getStreamCount() throws ParsingException {
        String string = this.playlistInfoItem.getString("videoCount");
        if (string == null) {
            string = YoutubeParsingHelper.getTextFromObject(this.playlistInfoItem.getObject("videoCountText"));
        }
        if (string == null) {
            string = YoutubeParsingHelper.getTextFromObject(this.playlistInfoItem.getObject("videoCountShortText"));
        }
        if (string == null) {
            throw new ParsingException("Could not get stream count");
        }
        try {
            return Long.parseLong(Utils.removeNonDigitCharacters(string));
        } catch (Exception e) {
            throw new ParsingException("Could not get stream count", e);
        }
    }
}
