package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonObject;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
class YoutubeShowRendererInfoItemExtractor extends YoutubeBaseShowInfoItemExtractor {

    @Nonnull
    private final JsonObject longBylineText;

    @Nonnull
    private final JsonObject shortBylineText;

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return false;
    }

    YoutubeShowRendererInfoItemExtractor(@Nonnull JsonObject jsonObject) {
        super(jsonObject);
        this.shortBylineText = jsonObject.getObject("shortBylineText");
        this.longBylineText = jsonObject.getObject("longBylineText");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        String textFromObject = YoutubeParsingHelper.getTextFromObject(this.longBylineText);
        if (!Utils.isNullOrEmpty(textFromObject)) {
            return textFromObject;
        }
        String textFromObject2 = YoutubeParsingHelper.getTextFromObject(this.shortBylineText);
        if (Utils.isNullOrEmpty(textFromObject2)) {
            throw new ParsingException("Could not get uploader name");
        }
        return textFromObject2;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        String urlFromObject = YoutubeParsingHelper.getUrlFromObject(this.longBylineText);
        if (urlFromObject != null) {
            return urlFromObject;
        }
        String urlFromObject2 = YoutubeParsingHelper.getUrlFromObject(this.shortBylineText);
        if (urlFromObject2 != null) {
            return urlFromObject2;
        }
        throw new ParsingException("Could not get uploader URL");
    }
}
