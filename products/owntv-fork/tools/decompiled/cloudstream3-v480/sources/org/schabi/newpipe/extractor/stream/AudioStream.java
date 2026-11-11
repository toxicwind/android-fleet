package org.schabi.newpipe.extractor.stream;

import j$.util.Objects;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.MediaFormat;
import org.schabi.newpipe.extractor.services.youtube.ItagItem;

/* JADX INFO: loaded from: classes8.dex */
public final class AudioStream extends Stream {
    public static final int UNKNOWN_BITRATE = -1;

    @Nullable
    private final Locale audioLocale;

    @Nullable
    private final String audioTrackId;

    @Nullable
    private final String audioTrackName;

    @Nullable
    private final AudioTrackType audioTrackType;
    private final int averageBitrate;
    private int bitrate;
    private String codec;
    private int indexEnd;
    private int indexStart;
    private int initEnd;
    private int initStart;
    private int itag;

    @Nullable
    private ItagItem itagItem;
    private String quality;

    public static final class Builder {

        @Nullable
        private Locale audioLocale;

        @Nullable
        private String audioTrackId;

        @Nullable
        private String audioTrackName;

        @Nullable
        private AudioTrackType audioTrackType;
        private String content;
        private String id;
        private boolean isUrl;

        @Nullable
        private ItagItem itagItem;

        @Nullable
        private String manifestUrl;

        @Nullable
        private MediaFormat mediaFormat;
        private DeliveryMethod deliveryMethod = DeliveryMethod.PROGRESSIVE_HTTP;
        private int averageBitrate = -1;

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

        public Builder setAverageBitrate(int i) {
            this.averageBitrate = i;
            return this;
        }

        public Builder setAudioTrackId(@Nullable String str) {
            this.audioTrackId = str;
            return this;
        }

        public Builder setAudioTrackName(@Nullable String str) {
            this.audioTrackName = str;
            return this;
        }

        public Builder setAudioTrackType(AudioTrackType audioTrackType) {
            this.audioTrackType = audioTrackType;
            return this;
        }

        public Builder setAudioLocale(@Nullable Locale locale) {
            this.audioLocale = locale;
            return this;
        }

        public Builder setItagItem(@Nullable ItagItem itagItem) {
            this.itagItem = itagItem;
            return this;
        }

        @Nonnull
        public AudioStream build() {
            validateBuild();
            return new AudioStream(this);
        }

        void validateBuild() {
            if (this.id == null) {
                throw new IllegalStateException("The identifier of the audio stream has been not set or is null. If you are not able to get an identifier, use the static constant ID_UNKNOWN of the Stream class.");
            }
            if (this.content == null) {
                throw new IllegalStateException("The content of the audio stream has been not set or is null. Please specify a non-null one with setContent.");
            }
            if (this.deliveryMethod == null) {
                throw new IllegalStateException("The delivery method of the audio stream has been set as null, which is not allowed. Pass a valid one instead with setDeliveryMethod.");
            }
        }
    }

    AudioStream(Builder builder) {
        super(builder.id, builder.content, builder.isUrl, builder.mediaFormat, builder.deliveryMethod, builder.manifestUrl);
        this.itag = -1;
        if (builder.itagItem != null) {
            this.itagItem = builder.itagItem;
            this.itag = builder.itagItem.id;
            this.quality = builder.itagItem.getQuality();
            this.bitrate = builder.itagItem.getBitrate();
            this.initStart = builder.itagItem.getInitStart();
            this.initEnd = builder.itagItem.getInitEnd();
            this.indexStart = builder.itagItem.getIndexStart();
            this.indexEnd = builder.itagItem.getIndexEnd();
            this.codec = builder.itagItem.getCodec();
        }
        this.averageBitrate = builder.averageBitrate;
        this.audioTrackId = builder.audioTrackId;
        this.audioTrackName = builder.audioTrackName;
        this.audioLocale = builder.audioLocale;
        this.audioTrackType = builder.audioTrackType;
    }

    @Override // org.schabi.newpipe.extractor.stream.Stream
    public boolean equalStats(Stream stream) {
        if (!super.equalStats(stream) || !(stream instanceof AudioStream)) {
            return false;
        }
        AudioStream audioStream = (AudioStream) stream;
        return this.averageBitrate == audioStream.averageBitrate && Objects.equals(this.audioTrackId, audioStream.audioTrackId) && this.audioTrackType == audioStream.audioTrackType && Objects.equals(this.audioLocale, audioStream.audioLocale);
    }

    public int getAverageBitrate() {
        return this.averageBitrate;
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

    public String getQuality() {
        return this.quality;
    }

    public String getCodec() {
        return this.codec;
    }

    @Nullable
    public String getAudioTrackId() {
        return this.audioTrackId;
    }

    @Nullable
    public String getAudioTrackName() {
        return this.audioTrackName;
    }

    @Nullable
    public Locale getAudioLocale() {
        return this.audioLocale;
    }

    @Nullable
    public AudioTrackType getAudioTrackType() {
        return this.audioTrackType;
    }

    @Override // org.schabi.newpipe.extractor.stream.Stream
    @Nullable
    public ItagItem getItagItem() {
        return this.itagItem;
    }
}
