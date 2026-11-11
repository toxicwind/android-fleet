package org.schabi.newpipe.extractor.services.media_ccc.extractors.infoItems;

import com.grack.nanojson.JsonObject;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCParsingHelper;
import org.schabi.newpipe.extractor.stream.ContentAvailability;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamType;

/* JADX INFO: loaded from: classes8.dex */
public class MediaCCCStreamInfoItemExtractor implements StreamInfoItemExtractor {
    private final JsonObject event;

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ ContentAvailability getContentAvailability() {
        return ContentAvailability.UNKNOWN;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ String getShortDescription() {
        return StreamInfoItemExtractor.CC.$default$getShortDescription(this);
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
    public boolean isUploaderVerified() throws ParsingException {
        return false;
    }

    public MediaCCCStreamInfoItemExtractor(JsonObject jsonObject) {
        this.event = jsonObject;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public StreamType getStreamType() {
        return StreamType.VIDEO_STREAM;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getDuration() {
        return this.event.getInt("length");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getViewCount() {
        return this.event.getInt("view_count");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderName() {
        return this.event.getString("conference_title");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderUrl() {
        return this.event.getString("conference_url");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nullable
    public String getTextualUploadDate() {
        return this.event.getString("release_date");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nullable
    public DateWrapper getUploadDate() throws ParsingException {
        return DateWrapper.fromOffsetDateTime(getTextualUploadDate());
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        return this.event.getString("title");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        return "https://media.ccc.de/public/events/" + this.event.getString("guid");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() {
        return MediaCCCParsingHelper.getThumbnailsFromStreamItem(this.event);
    }
}
