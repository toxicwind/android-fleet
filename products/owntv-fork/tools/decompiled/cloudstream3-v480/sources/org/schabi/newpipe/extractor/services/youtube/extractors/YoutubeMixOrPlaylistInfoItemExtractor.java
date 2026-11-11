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
public class YoutubeMixOrPlaylistInfoItemExtractor implements PlaylistInfoItemExtractor {
    private final JsonObject mixInfoItem;

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ Description getDescription() {
        return Description.EMPTY_DESCRIPTION;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return false;
    }

    public YoutubeMixOrPlaylistInfoItemExtractor(JsonObject jsonObject) {
        this.mixInfoItem = jsonObject;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        String textFromObject = YoutubeParsingHelper.getTextFromObject(this.mixInfoItem.getObject("title"));
        if (Utils.isNullOrEmpty(textFromObject)) {
            throw new ParsingException("Could not get name");
        }
        return textFromObject;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        String string = this.mixInfoItem.getString("shareUrl");
        if (Utils.isNullOrEmpty(string)) {
            throw new ParsingException("Could not get url");
        }
        return string;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return YoutubeParsingHelper.getThumbnailsFromInfoItem(this.mixInfoItem);
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        return YoutubeParsingHelper.getTextFromObject(this.mixInfoItem.getObject("longBylineText"));
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public long getStreamCount() throws ParsingException {
        String textFromObject = YoutubeParsingHelper.getTextFromObject(this.mixInfoItem.getObject("videoCountShortText"));
        if (textFromObject == null) {
            throw new ParsingException("Could not extract item count for playlist/mix info item");
        }
        try {
            return Integer.parseInt(textFromObject);
        } catch (NumberFormatException unused) {
            return -2L;
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    @Nonnull
    public PlaylistInfo.PlaylistType getPlaylistType() throws ParsingException {
        return YoutubeParsingHelper.extractPlaylistTypeFromPlaylistUrl(getUrl());
    }
}
