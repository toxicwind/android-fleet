package org.schabi.newpipe.extractor.stream;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.Info;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.MetaInfo;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ContentNotAvailableException;
import org.schabi.newpipe.extractor.exceptions.ContentNotSupportedException;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.utils.ExtractorHelper;
import org.schabi.newpipe.extractor.utils.ExtractorLogger;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class StreamInfo extends Info {
    private static final String TAG = "StreamInfo";
    private int ageLimit;
    private List<AudioStream> audioStreams;
    private String category;

    @Nonnull
    private ContentAvailability contentAvailability;
    private String dashMpdUrl;
    private Description description;
    private long dislikeCount;
    private long duration;
    private String hlsUrl;
    private String host;
    private Locale language;
    private String licence;
    private long likeCount;
    private List<MetaInfo> metaInfo;
    private List<Frameset> previewFrames;
    private StreamExtractor.Privacy privacy;
    private List<InfoItem> relatedItems;
    private boolean shortFormContent;
    private long startPosition;
    private List<StreamSegment> streamSegments;
    private StreamType streamType;

    @Nonnull
    private List<Image> subChannelAvatars;
    private String subChannelName;
    private String subChannelUrl;
    private List<SubtitlesStream> subtitles;
    private String supportInfo;
    private List<String> tags;
    private String textualUploadDate;

    @Nonnull
    private List<Image> thumbnails;
    private DateWrapper uploadDate;

    @Nonnull
    private List<Image> uploaderAvatars;
    private String uploaderName;
    private long uploaderSubscriberCount;
    private String uploaderUrl;
    private boolean uploaderVerified;
    private List<VideoStream> videoOnlyStreams;
    private List<VideoStream> videoStreams;
    private long viewCount;

    public static class StreamExtractException extends ExtractionException {
        StreamExtractException(String str) {
            super(str);
        }
    }

    public StreamInfo(int i, String str, String str2, StreamType streamType, String str3, String str4, int i2) {
        super(i, str3, str, str2, str4);
        this.thumbnails = Collections.EMPTY_LIST;
        this.duration = -1L;
        this.viewCount = -1L;
        this.likeCount = -1L;
        this.dislikeCount = -1L;
        this.uploaderName = "";
        this.uploaderUrl = "";
        this.uploaderAvatars = Collections.EMPTY_LIST;
        this.uploaderVerified = false;
        this.uploaderSubscriberCount = -1L;
        this.subChannelName = "";
        this.subChannelUrl = "";
        this.subChannelAvatars = Collections.EMPTY_LIST;
        this.videoStreams = Collections.EMPTY_LIST;
        this.audioStreams = Collections.EMPTY_LIST;
        this.videoOnlyStreams = Collections.EMPTY_LIST;
        this.dashMpdUrl = "";
        this.hlsUrl = "";
        this.relatedItems = Collections.EMPTY_LIST;
        this.startPosition = 0L;
        this.subtitles = Collections.EMPTY_LIST;
        this.host = "";
        this.category = "";
        this.licence = "";
        this.supportInfo = "";
        this.language = null;
        this.tags = Collections.EMPTY_LIST;
        this.streamSegments = Collections.EMPTY_LIST;
        this.metaInfo = Collections.EMPTY_LIST;
        this.shortFormContent = false;
        this.contentAvailability = ContentAvailability.AVAILABLE;
        this.previewFrames = Collections.EMPTY_LIST;
        this.streamType = streamType;
        this.ageLimit = i2;
        ExtractorLogger.d(TAG, "Created {}", this);
    }

    @Override // org.schabi.newpipe.extractor.Info
    public String toString() {
        return TAG + "[serviceId=" + getServiceId() + ", url='" + getUrl() + "', originalUrl='" + getOriginalUrl() + "', id='" + getId() + "', name='" + getName() + "', streamType=" + this.streamType + ", ageLimit=" + this.ageLimit + "]";
    }

    public static StreamInfo getInfo(String str) throws IOException, ExtractionException {
        ExtractorLogger.d(TAG, "getInfo({url})", str);
        return getInfo(NewPipe.getServiceByUrl(str), str);
    }

    public static StreamInfo getInfo(@Nonnull StreamingService streamingService, String str) throws IOException, ExtractionException {
        ExtractorLogger.d(TAG, "getInfo({service},{url})", streamingService, str);
        return getInfo(streamingService.getStreamExtractor(str));
    }

    public static StreamInfo getInfo(@Nonnull StreamExtractor streamExtractor) throws IOException, ExtractionException {
        ExtractorLogger.d(TAG, "getInfo({extractor})", streamExtractor);
        streamExtractor.fetchPage();
        try {
            StreamInfo streamInfoExtractImportantData = extractImportantData(streamExtractor);
            extractStreams(streamInfoExtractImportantData, streamExtractor);
            extractOptionalData(streamInfoExtractImportantData, streamExtractor);
            return streamInfoExtractImportantData;
        } catch (ExtractionException e) {
            String errorMessage = streamExtractor.getErrorMessage();
            if (Utils.isNullOrEmpty(errorMessage)) {
                throw e;
            }
            throw new ContentNotAvailableException(errorMessage, e);
        }
    }

    @Nonnull
    private static StreamInfo extractImportantData(@Nonnull StreamExtractor streamExtractor) throws ExtractionException {
        String url = streamExtractor.getUrl();
        StreamType streamType = streamExtractor.getStreamType();
        String id = streamExtractor.getId();
        String name = streamExtractor.getName();
        int ageLimit = streamExtractor.getAgeLimit();
        if (streamType == StreamType.NONE || Utils.isNullOrEmpty(url) || Utils.isNullOrEmpty(id) || name == null || ageLimit == -1) {
            throw new ExtractionException("Some important stream information was not given.");
        }
        return new StreamInfo(streamExtractor.getServiceId(), url, streamExtractor.getOriginalUrl(), streamType, id, name, ageLimit);
    }

    private static void extractStreams(StreamInfo streamInfo, StreamExtractor streamExtractor) throws ExtractionException {
        try {
            streamInfo.setDashMpdUrl(streamExtractor.getDashMpdUrl());
        } catch (Exception e) {
            streamInfo.addError(new ExtractionException("Couldn't get DASH manifest", e));
        }
        try {
            streamInfo.setHlsUrl(streamExtractor.getHlsUrl());
        } catch (Exception e2) {
            streamInfo.addError(new ExtractionException("Couldn't get HLS manifest", e2));
        }
        try {
            streamInfo.setAudioStreams(streamExtractor.getAudioStreams());
        } catch (ContentNotSupportedException e3) {
            throw e3;
        } catch (Exception e4) {
            streamInfo.addError(new ExtractionException("Couldn't get audio streams", e4));
        }
        try {
            streamInfo.setVideoStreams(streamExtractor.getVideoStreams());
        } catch (Exception e5) {
            streamInfo.addError(new ExtractionException("Couldn't get video streams", e5));
        }
        try {
            streamInfo.setVideoOnlyStreams(streamExtractor.getVideoOnlyStreams());
        } catch (Exception e6) {
            streamInfo.addError(new ExtractionException("Couldn't get video only streams", e6));
        }
        if (streamInfo.videoStreams.isEmpty() && streamInfo.audioStreams.isEmpty() && Utils.isNullOrEmpty(streamInfo.dashMpdUrl) && Utils.isNullOrEmpty(streamInfo.hlsUrl)) {
            throw new StreamExtractException("Could not get any stream. See error variable to get further details.");
        }
    }

    private static void extractOptionalData(StreamInfo streamInfo, StreamExtractor streamExtractor) {
        try {
            streamInfo.setThumbnails(streamExtractor.getThumbnails());
        } catch (Exception e) {
            streamInfo.addError(e);
        }
        try {
            streamInfo.setDuration(streamExtractor.getLength());
        } catch (Exception e2) {
            streamInfo.addError(e2);
        }
        try {
            streamInfo.setUploaderName(streamExtractor.getUploaderName());
        } catch (Exception e3) {
            streamInfo.addError(e3);
        }
        try {
            streamInfo.setUploaderUrl(streamExtractor.getUploaderUrl());
        } catch (Exception e4) {
            streamInfo.addError(e4);
        }
        try {
            streamInfo.setUploaderAvatars(streamExtractor.getUploaderAvatars());
        } catch (Exception e5) {
            streamInfo.addError(e5);
        }
        try {
            streamInfo.setUploaderVerified(streamExtractor.isUploaderVerified());
        } catch (Exception e6) {
            streamInfo.addError(e6);
        }
        try {
            streamInfo.setUploaderSubscriberCount(streamExtractor.getUploaderSubscriberCount());
        } catch (Exception e7) {
            streamInfo.addError(e7);
        }
        try {
            streamInfo.setSubChannelName(streamExtractor.getSubChannelName());
        } catch (Exception e8) {
            streamInfo.addError(e8);
        }
        try {
            streamInfo.setSubChannelUrl(streamExtractor.getSubChannelUrl());
        } catch (Exception e9) {
            streamInfo.addError(e9);
        }
        try {
            streamInfo.setSubChannelAvatars(streamExtractor.getSubChannelAvatars());
        } catch (Exception e10) {
            streamInfo.addError(e10);
        }
        try {
            streamInfo.setDescription(streamExtractor.getDescription());
        } catch (Exception e11) {
            streamInfo.addError(e11);
        }
        try {
            streamInfo.setViewCount(streamExtractor.getViewCount());
        } catch (Exception e12) {
            streamInfo.addError(e12);
        }
        try {
            streamInfo.setTextualUploadDate(streamExtractor.getTextualUploadDate());
        } catch (Exception e13) {
            streamInfo.addError(e13);
        }
        try {
            streamInfo.setUploadDate(streamExtractor.getUploadDate());
        } catch (Exception e14) {
            streamInfo.addError(e14);
        }
        try {
            streamInfo.setStartPosition(streamExtractor.getTimeStamp());
        } catch (Exception e15) {
            streamInfo.addError(e15);
        }
        try {
            streamInfo.setLikeCount(streamExtractor.getLikeCount());
        } catch (Exception e16) {
            streamInfo.addError(e16);
        }
        try {
            streamInfo.setDislikeCount(streamExtractor.getDislikeCount());
        } catch (Exception e17) {
            streamInfo.addError(e17);
        }
        try {
            streamInfo.setSubtitles(streamExtractor.getSubtitlesDefault());
        } catch (Exception e18) {
            streamInfo.addError(e18);
        }
        try {
            streamInfo.setHost(streamExtractor.getHost());
        } catch (Exception e19) {
            streamInfo.addError(e19);
        }
        try {
            streamInfo.setPrivacy(streamExtractor.getPrivacy());
        } catch (Exception e20) {
            streamInfo.addError(e20);
        }
        try {
            streamInfo.setCategory(streamExtractor.getCategory());
        } catch (Exception e21) {
            streamInfo.addError(e21);
        }
        try {
            streamInfo.setLicence(streamExtractor.getLicence());
        } catch (Exception e22) {
            streamInfo.addError(e22);
        }
        try {
            streamInfo.setLanguageInfo(streamExtractor.getLanguageInfo());
        } catch (Exception e23) {
            streamInfo.addError(e23);
        }
        try {
            streamInfo.setTags(streamExtractor.getTags());
        } catch (Exception e24) {
            streamInfo.addError(e24);
        }
        try {
            streamInfo.setSupportInfo(streamExtractor.getSupportInfo());
        } catch (Exception e25) {
            streamInfo.addError(e25);
        }
        try {
            streamInfo.setStreamSegments(streamExtractor.getStreamSegments());
        } catch (Exception e26) {
            streamInfo.addError(e26);
        }
        try {
            streamInfo.setMetaInfo(streamExtractor.getMetaInfo());
        } catch (Exception e27) {
            streamInfo.addError(e27);
        }
        try {
            streamInfo.setPreviewFrames(streamExtractor.getFrames());
        } catch (Exception e28) {
            streamInfo.addError(e28);
        }
        try {
            streamInfo.setShortFormContent(streamExtractor.isShortFormContent());
        } catch (Exception e29) {
            streamInfo.addError(e29);
        }
        try {
            streamInfo.setContentAvailability(streamExtractor.getContentAvailability());
        } catch (Exception e30) {
            streamInfo.addError(e30);
        }
        streamInfo.setRelatedItems(ExtractorHelper.getRelatedItemsOrLogError(streamInfo, streamExtractor));
    }

    public StreamType getStreamType() {
        return this.streamType;
    }

    public void setStreamType(StreamType streamType) {
        this.streamType = streamType;
    }

    @Nonnull
    public List<Image> getThumbnails() {
        return this.thumbnails;
    }

    public void setThumbnails(@Nonnull List<Image> list) {
        this.thumbnails = list;
    }

    public String getTextualUploadDate() {
        return this.textualUploadDate;
    }

    public void setTextualUploadDate(String str) {
        this.textualUploadDate = str;
    }

    public DateWrapper getUploadDate() {
        return this.uploadDate;
    }

    public void setUploadDate(DateWrapper dateWrapper) {
        this.uploadDate = dateWrapper;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public int getAgeLimit() {
        return this.ageLimit;
    }

    public void setAgeLimit(int i) {
        this.ageLimit = i;
    }

    public Description getDescription() {
        return this.description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public long getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(long j) {
        this.viewCount = j;
    }

    public long getLikeCount() {
        return this.likeCount;
    }

    public void setLikeCount(long j) {
        this.likeCount = j;
    }

    public long getDislikeCount() {
        return this.dislikeCount;
    }

    public void setDislikeCount(long j) {
        this.dislikeCount = j;
    }

    public String getUploaderName() {
        return this.uploaderName;
    }

    public void setUploaderName(String str) {
        this.uploaderName = str;
    }

    public String getUploaderUrl() {
        return this.uploaderUrl;
    }

    public void setUploaderUrl(String str) {
        this.uploaderUrl = str;
    }

    @Nonnull
    public List<Image> getUploaderAvatars() {
        return this.uploaderAvatars;
    }

    public void setUploaderAvatars(@Nonnull List<Image> list) {
        this.uploaderAvatars = list;
    }

    public boolean isUploaderVerified() {
        return this.uploaderVerified;
    }

    public void setUploaderVerified(boolean z) {
        this.uploaderVerified = z;
    }

    public long getUploaderSubscriberCount() {
        return this.uploaderSubscriberCount;
    }

    public void setUploaderSubscriberCount(long j) {
        this.uploaderSubscriberCount = j;
    }

    public String getSubChannelName() {
        return this.subChannelName;
    }

    public void setSubChannelName(String str) {
        this.subChannelName = str;
    }

    public String getSubChannelUrl() {
        return this.subChannelUrl;
    }

    public void setSubChannelUrl(String str) {
        this.subChannelUrl = str;
    }

    @Nonnull
    public List<Image> getSubChannelAvatars() {
        return this.subChannelAvatars;
    }

    public void setSubChannelAvatars(@Nonnull List<Image> list) {
        this.subChannelAvatars = list;
    }

    public List<VideoStream> getVideoStreams() {
        return this.videoStreams;
    }

    public void setVideoStreams(List<VideoStream> list) {
        this.videoStreams = list;
    }

    public List<AudioStream> getAudioStreams() {
        return this.audioStreams;
    }

    public void setAudioStreams(List<AudioStream> list) {
        this.audioStreams = list;
    }

    public List<VideoStream> getVideoOnlyStreams() {
        return this.videoOnlyStreams;
    }

    public void setVideoOnlyStreams(List<VideoStream> list) {
        this.videoOnlyStreams = list;
    }

    public String getDashMpdUrl() {
        return this.dashMpdUrl;
    }

    public void setDashMpdUrl(String str) {
        this.dashMpdUrl = str;
    }

    public String getHlsUrl() {
        return this.hlsUrl;
    }

    public void setHlsUrl(String str) {
        this.hlsUrl = str;
    }

    public List<InfoItem> getRelatedItems() {
        return this.relatedItems;
    }

    @Deprecated
    public List<InfoItem> getRelatedStreams() {
        return getRelatedItems();
    }

    public void setRelatedItems(List<InfoItem> list) {
        this.relatedItems = list;
    }

    @Deprecated
    public void setRelatedStreams(List<InfoItem> list) {
        setRelatedItems(list);
    }

    public long getStartPosition() {
        return this.startPosition;
    }

    public void setStartPosition(long j) {
        this.startPosition = j;
    }

    public List<SubtitlesStream> getSubtitles() {
        return this.subtitles;
    }

    public void setSubtitles(List<SubtitlesStream> list) {
        this.subtitles = list;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public StreamExtractor.Privacy getPrivacy() {
        return this.privacy;
    }

    public void setPrivacy(StreamExtractor.Privacy privacy) {
        this.privacy = privacy;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public String getLicence() {
        return this.licence;
    }

    public void setLicence(String str) {
        this.licence = str;
    }

    public Locale getLanguageInfo() {
        return this.language;
    }

    public void setLanguageInfo(Locale locale) {
        this.language = locale;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> list) {
        this.tags = list;
    }

    public void setSupportInfo(String str) {
        this.supportInfo = str;
    }

    public String getSupportInfo() {
        return this.supportInfo;
    }

    public List<StreamSegment> getStreamSegments() {
        return this.streamSegments;
    }

    public void setStreamSegments(List<StreamSegment> list) {
        this.streamSegments = list;
    }

    public void setMetaInfo(List<MetaInfo> list) {
        this.metaInfo = list;
    }

    public List<Frameset> getPreviewFrames() {
        return this.previewFrames;
    }

    public void setPreviewFrames(List<Frameset> list) {
        this.previewFrames = list;
    }

    @Nonnull
    public List<MetaInfo> getMetaInfo() {
        return this.metaInfo;
    }

    public boolean isShortFormContent() {
        return this.shortFormContent;
    }

    public void setShortFormContent(boolean z) {
        this.shortFormContent = z;
    }

    @Nonnull
    public ContentAvailability getContentAvailability() {
        return this.contentAvailability;
    }

    public void setContentAvailability(@Nonnull ContentAvailability contentAvailability) {
        this.contentAvailability = contentAvailability;
    }
}
