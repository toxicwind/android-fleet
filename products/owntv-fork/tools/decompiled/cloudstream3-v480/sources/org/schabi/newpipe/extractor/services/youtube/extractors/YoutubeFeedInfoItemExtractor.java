package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.jsoup.nodes.Element;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.stream.ContentAvailability;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamType;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeFeedInfoItemExtractor implements StreamInfoItemExtractor {
    private final Element entryElement;

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ ContentAvailability getContentAvailability() {
        return ContentAvailability.UNKNOWN;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getDuration() {
        return -1L;
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

    public YoutubeFeedInfoItemExtractor(Element element) {
        this.entryElement = element;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public StreamType getStreamType() {
        return StreamType.VIDEO_STREAM;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getViewCount() {
        return Long.parseLong(this.entryElement.getElementsByTag("media:statistics").first().attr("views"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderName() {
        return this.entryElement.select("author > name").first().text();
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderUrl() {
        return this.entryElement.select("author > uri").first().text();
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nullable
    public String getTextualUploadDate() {
        return this.entryElement.getElementsByTag("published").first().text();
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nullable
    public DateWrapper getUploadDate() throws ParsingException {
        return DateWrapper.fromOffsetDateTime(getTextualUploadDate());
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() {
        return this.entryElement.getElementsByTag("title").first().text();
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() {
        return this.entryElement.getElementsByTag("link").first().attr("href");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() {
        int i;
        int i2;
        Element elementFirst = this.entryElement.getElementsByTag("media:thumbnail").first();
        if (elementFirst == null) {
            return Collections.EMPTY_LIST;
        }
        String strAttr = elementFirst.attr(ImagesContract.URL);
        if (strAttr.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        String strReplace = strAttr.replace("hqdefault", "mqdefault");
        if (strReplace.equals(strAttr)) {
            i2 = -1;
            try {
                i = Integer.parseInt(elementFirst.attr("height"));
            } catch (NumberFormatException unused) {
                i = -1;
            }
            try {
                i2 = Integer.parseInt(elementFirst.attr("width"));
            } catch (NumberFormatException unused2) {
            }
        } else {
            i = 320;
            i2 = 180;
        }
        return UByte$$ExternalSyntheticBackport0.m(new Object[]{new Image(strReplace, i, i2, Image.ResolutionLevel.fromHeight(i))});
    }
}
