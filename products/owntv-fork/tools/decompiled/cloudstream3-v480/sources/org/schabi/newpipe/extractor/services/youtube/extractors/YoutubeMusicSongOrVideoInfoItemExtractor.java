package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeSearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.stream.ContentAvailability;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamType;
import org.schabi.newpipe.extractor.utils.Parser;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeMusicSongOrVideoInfoItemExtractor implements StreamInfoItemExtractor {
    private final JsonArray descriptionElements;
    private final String searchType;
    private final JsonObject songOrVideoInfoItem;

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ ContentAvailability getContentAvailability() {
        return ContentAvailability.UNKNOWN;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ String getShortDescription() {
        return StreamInfoItemExtractor.CC.$default$getShortDescription(this);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getTextualUploadDate() {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public DateWrapper getUploadDate() {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ List getUploaderAvatars() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public boolean isAd() {
        return false;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ boolean isShortFormContent() {
        return StreamInfoItemExtractor.CC.$default$isShortFormContent(this);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public boolean isUploaderVerified() {
        return false;
    }

    public YoutubeMusicSongOrVideoInfoItemExtractor(JsonObject jsonObject, JsonArray jsonArray, String str) {
        this.songOrVideoInfoItem = jsonObject;
        this.descriptionElements = jsonArray;
        this.searchType = str;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        String string = this.songOrVideoInfoItem.getObject("playlistItemData").getString(YoutubeParsingHelper.VIDEO_ID);
        if (!Utils.isNullOrEmpty(string)) {
            return "https://music.youtube.com/watch?v=" + string;
        }
        throw new ParsingException("Could not get URL");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        String textFromObject = YoutubeParsingHelper.getTextFromObject(this.songOrVideoInfoItem.getArray("flexColumns").getObject(0).getObject("musicResponsiveListItemFlexColumnRenderer").getObject("text"));
        if (Utils.isNullOrEmpty(textFromObject)) {
            throw new ParsingException("Could not get name");
        }
        return textFromObject;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public StreamType getStreamType() {
        return StreamType.VIDEO_STREAM;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getDuration() throws ParsingException {
        JsonArray jsonArray = this.descriptionElements;
        String string = jsonArray.getObject(jsonArray.size() - 1).getString("text");
        if (!Utils.isNullOrEmpty(string)) {
            return YoutubeParsingHelper.parseDurationString(string);
        }
        throw new ParsingException("Could not get duration");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        String string = this.descriptionElements.getObject(0).getString("text");
        if (Utils.isNullOrEmpty(string)) {
            throw new ParsingException("Could not get uploader name");
        }
        return string;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        boolean zEquals = this.searchType.equals(YoutubeSearchQueryHandlerFactory.MUSIC_VIDEOS);
        JsonObject jsonObject = this.songOrVideoInfoItem;
        if (zEquals) {
            Iterator<Object> it = jsonObject.getObject("menu").getObject("menuRenderer").getArray("items").iterator();
            while (it.hasNext()) {
                JsonObject object = ((JsonObject) it.next()).getObject("menuNavigationItemRenderer");
                if (object.getObject("icon").getString("iconType", "").equals("ARTIST")) {
                    return YoutubeParsingHelper.getUrlFromNavigationEndpoint(object.getObject("navigationEndpoint"));
                }
            }
            return null;
        }
        JsonObject object2 = jsonObject.getArray("flexColumns").getObject(1).getObject("musicResponsiveListItemFlexColumnRenderer").getObject("text").getArray("runs").getObject(0);
        if (!object2.has("navigationEndpoint")) {
            return null;
        }
        String urlFromNavigationEndpoint = YoutubeParsingHelper.getUrlFromNavigationEndpoint(object2.getObject("navigationEndpoint"));
        if (Utils.isNullOrEmpty(urlFromNavigationEndpoint)) {
            throw new ParsingException("Could not get uploader URL");
        }
        return urlFromNavigationEndpoint;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getViewCount() throws ParsingException {
        if (this.searchType.equals(YoutubeSearchQueryHandlerFactory.MUSIC_SONGS)) {
            return -1L;
        }
        JsonArray jsonArray = this.descriptionElements;
        String string = jsonArray.getObject(jsonArray.size() - 3).getString("text");
        if (!Utils.isNullOrEmpty(string)) {
            try {
                return Utils.mixedNumberWordToLong(string);
            } catch (Parser.RegexException unused) {
                return 0L;
            }
        }
        throw new ParsingException("Could not get view count");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        try {
            return YoutubeParsingHelper.getImagesFromThumbnailsArray(this.songOrVideoInfoItem.getObject("thumbnail").getObject("musicThumbnailRenderer").getObject("thumbnail").getArray("thumbnails"));
        } catch (Exception e) {
            throw new ParsingException("Could not get thumbnails", e);
        }
    }
}
