package org.schabi.newpipe.extractor.services.media_ccc.extractors;

import com.grack.nanojson.JsonObject;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.services.media_ccc.linkHandler.MediaCCCConferenceLinkHandlerFactory;
import org.schabi.newpipe.extractor.stream.ContentAvailability;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamType;

/* JADX INFO: loaded from: classes8.dex */
public class MediaCCCLiveStreamKioskExtractor implements StreamInfoItemExtractor {
    private final JsonObject conferenceInfo;
    private final String group;
    private final JsonObject roomInfo;

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ ContentAvailability getContentAvailability() {
        return ContentAvailability.UNKNOWN;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getDuration() throws ParsingException {
        return 0L;
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
    public long getViewCount() throws ParsingException {
        return -1L;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public boolean isAd() throws ParsingException {
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

    public MediaCCCLiveStreamKioskExtractor(JsonObject jsonObject, String str, JsonObject jsonObject2) {
        this.conferenceInfo = jsonObject;
        this.group = str;
        this.roomInfo = jsonObject2;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        return this.roomInfo.getObject("talks").getObject("current").getString("title");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        return this.roomInfo.getString("link");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return MediaCCCParsingHelper.getThumbnailsFromLiveStreamItem(this.roomInfo);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public StreamType getStreamType() throws ParsingException {
        Iterator<Object> it = this.roomInfo.getArray("streams").iterator();
        while (it.hasNext()) {
            if ("video".equals(((JsonObject) it.next()).getString("type"))) {
                return StreamType.LIVE_STREAM;
            }
        }
        return StreamType.AUDIO_LIVE_STREAM;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        return this.conferenceInfo.getString("conference") + " - " + this.group + " - " + this.roomInfo.getString("display");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        return MediaCCCConferenceLinkHandlerFactory.CONFERENCE_PATH + this.conferenceInfo.getString("slug");
    }
}
