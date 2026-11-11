package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.grack.nanojson.JsonObject;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;
import org.schabi.newpipe.extractor.stream.ContentAvailability;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamType;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudStreamInfoItemExtractor implements StreamInfoItemExtractor {
    private final JsonObject itemObject;

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ ContentAvailability getContentAvailability() {
        return ContentAvailability.UNKNOWN;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ String getShortDescription() {
        return StreamInfoItemExtractor.CC.$default$getShortDescription(this);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public boolean isAd() {
        return false;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ boolean isShortFormContent() {
        return StreamInfoItemExtractor.CC.$default$isShortFormContent(this);
    }

    public SoundcloudStreamInfoItemExtractor(JsonObject jsonObject) {
        this.itemObject = jsonObject;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() {
        return Utils.replaceHttpWithHttps(this.itemObject.getString("permalink_url"));
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() {
        return this.itemObject.getString("title");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getDuration() {
        return this.itemObject.getLong(TypedValues.TransitionType.S_DURATION) / 1000;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderName() {
        return this.itemObject.getObject("user").getString(HintConstants.AUTOFILL_HINT_USERNAME);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderUrl() {
        return Utils.replaceHttpWithHttps(this.itemObject.getObject("user").getString("permalink_url"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() {
        return SoundcloudParsingHelper.getAllImagesFromArtworkOrAvatarUrl(this.itemObject.getObject("user").getString("avatar_url"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return this.itemObject.getObject("user").getBoolean("verified");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getTextualUploadDate() {
        return this.itemObject.getString("created_at");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public DateWrapper getUploadDate() throws ParsingException {
        return SoundcloudParsingHelper.parseDate(getTextualUploadDate());
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getViewCount() {
        return this.itemObject.getLong("playback_count");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return SoundcloudParsingHelper.getAllImagesFromTrackObject(this.itemObject);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public StreamType getStreamType() {
        return StreamType.AUDIO_STREAM;
    }
}
