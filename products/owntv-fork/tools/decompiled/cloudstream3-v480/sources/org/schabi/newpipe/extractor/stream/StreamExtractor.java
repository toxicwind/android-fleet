package org.schabi.newpipe.extractor.stream;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Extractor;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.InfoItemExtractor;
import org.schabi.newpipe.extractor.InfoItemsCollector;
import org.schabi.newpipe.extractor.MediaFormat;
import org.schabi.newpipe.extractor.MetaInfo;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandler;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.utils.Parser;

/* JADX INFO: loaded from: classes8.dex */
public abstract class StreamExtractor extends Extractor {
    public static final int NO_AGE_LIMIT = 0;
    public static final long UNKNOWN_SUBSCRIBER_COUNT = -1;

    public enum Privacy {
        PUBLIC,
        UNLISTED,
        PRIVATE,
        INTERNAL,
        OTHER
    }

    public int getAgeLimit() throws ParsingException {
        return 0;
    }

    public abstract List<AudioStream> getAudioStreams() throws IOException, ExtractionException;

    public long getDislikeCount() throws ParsingException {
        return -1L;
    }

    public String getErrorMessage() {
        return null;
    }

    @Nullable
    public Locale getLanguageInfo() throws ParsingException {
        return null;
    }

    public long getLength() throws ParsingException {
        return 0L;
    }

    public long getLikeCount() throws ParsingException {
        return -1L;
    }

    @Nullable
    public InfoItemsCollector<? extends InfoItem, ? extends InfoItemExtractor> getRelatedItems() throws IOException, ExtractionException {
        return null;
    }

    public abstract StreamType getStreamType() throws ParsingException;

    @Nullable
    public String getTextualUploadDate() throws ParsingException {
        return null;
    }

    @Nonnull
    public abstract List<Image> getThumbnails() throws ParsingException;

    public long getTimeStamp() throws ParsingException {
        return 0L;
    }

    @Nullable
    public DateWrapper getUploadDate() throws ParsingException {
        return null;
    }

    @Nonnull
    public abstract String getUploaderName() throws ParsingException;

    public long getUploaderSubscriberCount() throws ParsingException {
        return -1L;
    }

    @Nonnull
    public abstract String getUploaderUrl() throws ParsingException;

    public abstract List<VideoStream> getVideoOnlyStreams() throws IOException, ExtractionException;

    public abstract List<VideoStream> getVideoStreams() throws IOException, ExtractionException;

    public long getViewCount() throws ParsingException {
        return -1L;
    }

    public boolean isShortFormContent() throws ParsingException {
        return false;
    }

    public boolean isUploaderVerified() throws ParsingException {
        return false;
    }

    public StreamExtractor(StreamingService streamingService, LinkHandler linkHandler) {
        super(streamingService, linkHandler);
    }

    @Nonnull
    public Description getDescription() throws ParsingException {
        return Description.EMPTY_DESCRIPTION;
    }

    @Nonnull
    public List<Image> getUploaderAvatars() throws ParsingException {
        return Collections.EMPTY_LIST;
    }

    @Nonnull
    public String getSubChannelUrl() throws ParsingException {
        return "";
    }

    @Nonnull
    public String getSubChannelName() throws ParsingException {
        return "";
    }

    @Nonnull
    public List<Image> getSubChannelAvatars() throws ParsingException {
        return Collections.EMPTY_LIST;
    }

    @Nonnull
    public String getDashMpdUrl() throws ParsingException {
        return "";
    }

    @Nonnull
    public String getHlsUrl() throws ParsingException {
        return "";
    }

    @Nonnull
    public List<SubtitlesStream> getSubtitlesDefault() throws IOException, ExtractionException {
        return Collections.EMPTY_LIST;
    }

    @Nonnull
    public List<SubtitlesStream> getSubtitles(MediaFormat mediaFormat) throws IOException, ExtractionException {
        return Collections.EMPTY_LIST;
    }

    @Nullable
    @Deprecated
    public StreamInfoItemsCollector getRelatedStreams() throws IOException, ExtractionException {
        InfoItemsCollector<? extends InfoItem, ? extends InfoItemExtractor> relatedItems = getRelatedItems();
        if (relatedItems instanceof StreamInfoItemsCollector) {
            return (StreamInfoItemsCollector) relatedItems;
        }
        return null;
    }

    @Nonnull
    public List<Frameset> getFrames() throws ExtractionException {
        return Collections.EMPTY_LIST;
    }

    protected long getTimestampSeconds(String str) throws ParsingException {
        String strMatchGroup1;
        String strMatchGroup2;
        String strMatchGroup3 = "";
        try {
            String strMatchGroup4 = Parser.matchGroup1(str, getOriginalUrl());
            try {
                if (strMatchGroup4.isEmpty()) {
                    return 0L;
                }
                try {
                    strMatchGroup1 = Parser.matchGroup1("(\\d+)s", strMatchGroup4);
                    try {
                        strMatchGroup2 = Parser.matchGroup1("(\\d+)m", strMatchGroup4);
                        try {
                            strMatchGroup3 = Parser.matchGroup1("(\\d+)h", strMatchGroup4);
                        } catch (Exception unused) {
                            if (strMatchGroup1.isEmpty() && strMatchGroup2.isEmpty()) {
                                strMatchGroup1 = Parser.matchGroup1("t=(\\d+)", strMatchGroup4);
                            }
                        }
                    } catch (Exception unused2) {
                        strMatchGroup2 = "";
                    }
                } catch (Exception unused3) {
                    strMatchGroup1 = "";
                    strMatchGroup2 = strMatchGroup1;
                }
                int i = 0;
                int i2 = strMatchGroup1.isEmpty() ? 0 : Integer.parseInt(strMatchGroup1);
                int i3 = strMatchGroup2.isEmpty() ? 0 : Integer.parseInt(strMatchGroup2);
                if (!strMatchGroup3.isEmpty()) {
                    i = Integer.parseInt(strMatchGroup3);
                }
                return ((long) i2) + (((long) i3) * 60) + (((long) i) * 3600);
            } catch (ParsingException e) {
                throw new ParsingException("Could not get timestamp.", e);
            }
        } catch (Parser.RegexException unused4) {
            return -2L;
        }
    }

    @Nonnull
    public String getHost() throws ParsingException {
        return "";
    }

    public Privacy getPrivacy() throws ParsingException {
        return Privacy.PUBLIC;
    }

    @Nonnull
    public String getCategory() throws ParsingException {
        return "";
    }

    @Nonnull
    public String getLicence() throws ParsingException {
        return "";
    }

    @Nonnull
    public List<String> getTags() throws ParsingException {
        return Collections.EMPTY_LIST;
    }

    @Nonnull
    public String getSupportInfo() throws ParsingException {
        return "";
    }

    @Nonnull
    public List<StreamSegment> getStreamSegments() throws ParsingException {
        return Collections.EMPTY_LIST;
    }

    @Nonnull
    public List<MetaInfo> getMetaInfo() throws ParsingException {
        return Collections.EMPTY_LIST;
    }

    @Nonnull
    public ContentAvailability getContentAvailability() throws ParsingException {
        return ContentAvailability.UNKNOWN;
    }
}
