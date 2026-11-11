package org.schabi.newpipe.extractor.stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.MediaFormat;
import org.schabi.newpipe.extractor.services.youtube.ItagItem;

/* JADX INFO: loaded from: classes8.dex */
public final class VideoStream extends Stream {
    public static final String RESOLUTION_UNKNOWN = "";
    private int bitrate;
    private String codec;
    private int fps;
    private int height;
    private int indexEnd;
    private int indexStart;
    private int initEnd;
    private int initStart;

    @Deprecated
    public final boolean isVideoOnly;
    private int itag;

    @Nullable
    private ItagItem itagItem;
    private String quality;

    @Deprecated
    public final String resolution;
    private int width;

    public static final class Builder {
        private String content;
        private DeliveryMethod deliveryMethod = DeliveryMethod.PROGRESSIVE_HTTP;
        private String id;
        private boolean isUrl;
        private Boolean isVideoOnly;

        @Nullable
        private ItagItem itagItem;

        @Nullable
        private String manifestUrl;

        @Nullable
        private MediaFormat mediaFormat;
        private String resolution;

        public Builder setId(@Nonnull String str) {
            this.id = str;
            return this;
        }

        public Builder setContent(@Nonnull String str, boolean z) {
            this.content = str;
            this.isUrl = z;
            return this;
        }

        public Builder setMediaFormat(@Nullable MediaFormat mediaFormat) {
            this.mediaFormat = mediaFormat;
            return this;
        }

        public Builder setDeliveryMethod(@Nonnull DeliveryMethod deliveryMethod) {
            this.deliveryMethod = deliveryMethod;
            return this;
        }

        public Builder setManifestUrl(@Nullable String str) {
            this.manifestUrl = str;
            return this;
        }

        public Builder setIsVideoOnly(boolean z) {
            this.isVideoOnly = Boolean.valueOf(z);
            return this;
        }

        public Builder setResolution(@Nonnull String str) {
            this.resolution = str;
            return this;
        }

        public Builder setItagItem(@Nullable ItagItem itagItem) {
            this.itagItem = itagItem;
            return this;
        }

        @Nonnull
        public VideoStream build() {
            if (this.id == null) {
                throw new IllegalStateException("The identifier of the video stream has been not set or is null. If you are not able to get an identifier, use the static constant ID_UNKNOWN of the Stream class.");
            }
            if (this.content == null) {
                throw new IllegalStateException("The content of the video stream has been not set or is null. Please specify a non-null one with setContent.");
            }
            if (this.deliveryMethod == null) {
                throw new IllegalStateException("The delivery method of the video stream has been set as null, which is not allowed. Pass a valid one instead with setDeliveryMethod.");
            }
            if (this.isVideoOnly == null) {
                throw new IllegalStateException("The video stream has been not set as a video-only stream or as a video stream with embedded audio. Please specify this information with setIsVideoOnly.");
            }
            if (this.resolution == null) {
                throw new IllegalStateException("The resolution of the video stream has been not set. Please specify it with setResolution (use an empty string if you are not able to get it).");
            }
            return new VideoStream(this.id, this.content, this.isUrl, this.mediaFormat, this.deliveryMethod, this.resolution, this.isVideoOnly.booleanValue(), this.manifestUrl, this.itagItem);
        }
    }

    private VideoStream(@Nonnull String str, @Nonnull String str2, boolean z, @Nullable MediaFormat mediaFormat, @Nonnull DeliveryMethod deliveryMethod, @Nonnull String str3, boolean z2, @Nullable String str4, @Nullable ItagItem itagItem) {
        super(str, str2, z, mediaFormat, deliveryMethod, str4);
        this.itag = -1;
        if (itagItem != null) {
            this.itagItem = itagItem;
            this.itag = itagItem.id;
            this.bitrate = itagItem.getBitrate();
            this.initStart = itagItem.getInitStart();
            this.initEnd = itagItem.getInitEnd();
            this.indexStart = itagItem.getIndexStart();
            this.indexEnd = itagItem.getIndexEnd();
            this.codec = itagItem.getCodec();
            this.height = itagItem.getHeight();
            this.width = itagItem.getWidth();
            this.quality = itagItem.getQuality();
            this.fps = itagItem.getFps();
        }
        this.resolution = str3;
        this.isVideoOnly = z2;
    }

    @Override // org.schabi.newpipe.extractor.stream.Stream
    public boolean equalStats(Stream stream) {
        if (!super.equalStats(stream) || !(stream instanceof VideoStream)) {
            return false;
        }
        VideoStream videoStream = (VideoStream) stream;
        return this.resolution.equals(videoStream.resolution) && this.isVideoOnly == videoStream.isVideoOnly;
    }

    @Nonnull
    public String getResolution() {
        return this.resolution;
    }

    public boolean isVideoOnly() {
        return this.isVideoOnly;
    }

    public int getItag() {
        return this.itag;
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public int getInitStart() {
        return this.initStart;
    }

    public int getInitEnd() {
        return this.initEnd;
    }

    public int getIndexStart() {
        return this.indexStart;
    }

    public int getIndexEnd() {
        return this.indexEnd;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getFps() {
        return this.fps;
    }

    public String getQuality() {
        return this.quality;
    }

    public String getCodec() {
        return this.codec;
    }

    @Override // org.schabi.newpipe.extractor.stream.Stream
    @Nullable
    public ItagItem getItagItem() {
        return this.itagItem;
    }
}
