package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonObject;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeStreamLinkHandlerFactory;
import org.schabi.newpipe.extractor.stream.ContentAvailability;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamType;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
class YoutubeShortsLockupInfoItemExtractor implements StreamInfoItemExtractor {

    @Nonnull
    private final JsonObject shortsLockupViewModel;

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ ContentAvailability getContentAvailability() {
        return ContentAvailability.UNKNOWN;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getDuration() throws ParsingException {
        return -1L;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ String getShortDescription() {
        return StreamInfoItemExtractor.CC.$default$getShortDescription(this);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nullable
    public String getTextualUploadDate() throws ParsingException {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nullable
    public DateWrapper getUploadDate() throws ParsingException {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ List getUploaderAvatars() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public boolean isAd() throws ParsingException {
        return false;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public boolean isShortFormContent() {
        return true;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return false;
    }

    YoutubeShortsLockupInfoItemExtractor(@Nonnull JsonObject jsonObject) {
        this.shortsLockupViewModel = jsonObject;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        return this.shortsLockupViewModel.getObject("overlayMetadata").getObject("primaryText").getString("content");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        String string = this.shortsLockupViewModel.getObject("onTap").getObject("innertubeCommand").getObject("reelWatchEndpoint").getString(YoutubeParsingHelper.VIDEO_ID);
        if (Utils.isNullOrEmpty(string)) {
            string = this.shortsLockupViewModel.getObject("inlinePlayerData").getObject("onVisible").getObject("innertubeCommand").getObject("watchEndpoint").getString(YoutubeParsingHelper.VIDEO_ID);
        }
        if (Utils.isNullOrEmpty(string)) {
            throw new ParsingException("Could not get video ID");
        }
        try {
            return YoutubeStreamLinkHandlerFactory.getInstance().getUrl(string);
        } catch (Exception e) {
            throw new ParsingException("Could not get URL", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        boolean zHas = this.shortsLockupViewModel.has("thumbnail");
        JsonObject jsonObject = this.shortsLockupViewModel;
        if (zHas) {
            return YoutubeParsingHelper.getImagesFromThumbnailsArray(jsonObject.getObject("thumbnail").getArray("sources"));
        }
        return YoutubeParsingHelper.getImagesFromThumbnailsArray(jsonObject.getObject("thumbnailViewModel").getObject("thumbnailViewModel").getObject("image").getArray("sources"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public StreamType getStreamType() throws ParsingException {
        return StreamType.VIDEO_STREAM;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getViewCount() throws ParsingException {
        String string = this.shortsLockupViewModel.getObject("overlayMetadata").getObject("secondaryText").getString("content");
        if (!Utils.isNullOrEmpty(string)) {
            if (string.contains("✪")) {
                return -1L;
            }
            if (string.toLowerCase().contains("no views")) {
                return 0L;
            }
            return Utils.mixedNumberWordToLong(string);
        }
        throw new ParsingException("Could not get short view count");
    }
}
