package org.schabi.newpipe.extractor.services.youtube.extractors;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.playlist.PlaylistInfo;
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeSearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeMusicAlbumOrPlaylistInfoItemExtractor implements PlaylistInfoItemExtractor {
    private final JsonObject albumOrPlaylistInfoItem;
    private final JsonObject descriptionElementUploader;

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ Description getDescription() {
        return Description.EMPTY_DESCRIPTION;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ PlaylistInfo.PlaylistType getPlaylistType() {
        return PlaylistInfo.PlaylistType.NORMAL;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public long getStreamCount() throws ParsingException {
        return -1L;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return false;
    }

    public YoutubeMusicAlbumOrPlaylistInfoItemExtractor(JsonObject jsonObject, JsonArray jsonArray, String str) {
        this.albumOrPlaylistInfoItem = jsonObject;
        this.descriptionElementUploader = jsonArray.getObject(YoutubeSearchQueryHandlerFactory.MUSIC_ALBUMS.equals(str) ? 2 : 0);
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        try {
            return YoutubeParsingHelper.getImagesFromThumbnailsArray(this.albumOrPlaylistInfoItem.getObject("thumbnail").getObject("musicThumbnailRenderer").getObject("thumbnail").getArray("thumbnails"));
        } catch (Exception e) {
            throw new ParsingException("Could not get thumbnails", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        String textFromObject = YoutubeParsingHelper.getTextFromObject(this.albumOrPlaylistInfoItem.getArray("flexColumns").getObject(0).getObject("musicResponsiveListItemFlexColumnRenderer").getObject("text"));
        if (Utils.isNullOrEmpty(textFromObject)) {
            throw new ParsingException("Could not get name");
        }
        return textFromObject;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        String string = this.albumOrPlaylistInfoItem.getObject("menu").getObject("menuRenderer").getArray("items").getObject(4).getObject("toggleMenuServiceItemRenderer").getObject("toggledServiceEndpoint").getObject("likeEndpoint").getObject(TypedValues.AttributesType.S_TARGET).getString("playlistId");
        if (Utils.isNullOrEmpty(string)) {
            string = this.albumOrPlaylistInfoItem.getObject("overlay").getObject("musicItemThumbnailOverlayRenderer").getObject("content").getObject("musicPlayButtonRenderer").getObject("playNavigationEndpoint").getObject("watchPlaylistEndpoint").getString("playlistId");
        }
        if (!Utils.isNullOrEmpty(string)) {
            return "https://music.youtube.com/playlist?list=" + string;
        }
        throw new ParsingException("Could not get URL");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        String string = this.descriptionElementUploader.getString("text");
        if (Utils.isNullOrEmpty(string)) {
            throw new ParsingException("Could not get uploader name");
        }
        return string;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    @Nullable
    public String getUploaderUrl() throws ParsingException {
        Iterator<Object> it = this.albumOrPlaylistInfoItem.getObject("menu").getObject("menuRenderer").getArray("items").iterator();
        while (it.hasNext()) {
            JsonObject object = ((JsonObject) it.next()).getObject("menuNavigationItemRenderer");
            if (object.getObject("icon").getString("iconType", "").equals("ARTIST")) {
                return YoutubeParsingHelper.getUrlFromNavigationEndpoint(object.getObject("navigationEndpoint"));
            }
        }
        if (this.descriptionElementUploader.has("navigationEndpoint")) {
            return YoutubeParsingHelper.getUrlFromNavigationEndpoint(this.descriptionElementUploader.getObject("navigationEndpoint"));
        }
        return null;
    }
}
