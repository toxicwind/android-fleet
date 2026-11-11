package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonObject;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.playlist.PlaylistInfo;
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
abstract class YoutubeBaseShowInfoItemExtractor implements PlaylistInfoItemExtractor {

    @Nonnull
    protected final JsonObject showRenderer;

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ Description getDescription() {
        return Description.EMPTY_DESCRIPTION;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ PlaylistInfo.PlaylistType getPlaylistType() {
        return PlaylistInfo.PlaylistType.NORMAL;
    }

    YoutubeBaseShowInfoItemExtractor(@Nonnull JsonObject jsonObject) {
        this.showRenderer = jsonObject;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        return this.showRenderer.getString("title");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        return YoutubeParsingHelper.getUrlFromNavigationEndpoint(this.showRenderer.getObject("navigationEndpoint"));
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return YoutubeParsingHelper.getThumbnailsFromInfoItem(this.showRenderer.getObject("thumbnailRenderer").getObject("showCustomThumbnailRenderer"));
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public long getStreamCount() throws ParsingException {
        String textFromObject = YoutubeParsingHelper.getTextFromObject(this.showRenderer.getObject("thumbnailOverlays").getObject("thumbnailOverlayBottomPanelRenderer").getObject("text"));
        if (textFromObject == null) {
            throw new ParsingException("Could not get stream count");
        }
        try {
            return Long.parseLong(Utils.removeNonDigitCharacters(textFromObject));
        } catch (NumberFormatException e) {
            throw new ParsingException("Could not convert stream count to a long", e);
        }
    }
}
