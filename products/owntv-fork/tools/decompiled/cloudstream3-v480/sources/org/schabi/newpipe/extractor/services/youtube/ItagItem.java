package org.schabi.newpipe.extractor.services.youtube;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.mediarouter.media.GlobalMediaRouter;
import java.io.Serializable;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.mozilla.universalchardet.prober.HebrewProber;
import org.schabi.newpipe.extractor.MediaFormat;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.stream.AudioTrackType;

/* JADX INFO: loaded from: classes8.dex */
public class ItagItem implements Serializable {
    public static final long APPROX_DURATION_MS_UNKNOWN = -1;
    public static final int AUDIO_CHANNELS_NOT_APPLICABLE_OR_UNKNOWN = -1;
    public static final int AVERAGE_BITRATE_UNKNOWN = -1;
    public static final long CONTENT_LENGTH_UNKNOWN = -1;
    public static final int FPS_NOT_APPLICABLE_OR_UNKNOWN = -1;
    private static final ItagItem[] ITAG_LIST = {new ItagItem(17, ItagType.VIDEO, MediaFormat.v3GPP, "144p"), new ItagItem(36, ItagType.VIDEO, MediaFormat.v3GPP, "240p"), new ItagItem(18, ItagType.VIDEO, MediaFormat.MPEG_4, "360p"), new ItagItem(34, ItagType.VIDEO, MediaFormat.MPEG_4, "360p"), new ItagItem(35, ItagType.VIDEO, MediaFormat.MPEG_4, "480p"), new ItagItem(59, ItagType.VIDEO, MediaFormat.MPEG_4, "480p"), new ItagItem(78, ItagType.VIDEO, MediaFormat.MPEG_4, "480p"), new ItagItem(22, ItagType.VIDEO, MediaFormat.MPEG_4, "720p"), new ItagItem(37, ItagType.VIDEO, MediaFormat.MPEG_4, "1080p"), new ItagItem(38, ItagType.VIDEO, MediaFormat.MPEG_4, "1080p"), new ItagItem(43, ItagType.VIDEO, MediaFormat.WEBM, "360p"), new ItagItem(44, ItagType.VIDEO, MediaFormat.WEBM, "480p"), new ItagItem(45, ItagType.VIDEO, MediaFormat.WEBM, "720p"), new ItagItem(46, ItagType.VIDEO, MediaFormat.WEBM, "1080p"), new ItagItem(171, ItagType.AUDIO, MediaFormat.WEBMA, 128), new ItagItem(172, ItagType.AUDIO, MediaFormat.WEBMA, 256), new ItagItem(599, ItagType.AUDIO, MediaFormat.M4A, 32), new ItagItem(139, ItagType.AUDIO, MediaFormat.M4A, 48), new ItagItem(140, ItagType.AUDIO, MediaFormat.M4A, 128), new ItagItem(141, ItagType.AUDIO, MediaFormat.M4A, 256), new ItagItem(600, ItagType.AUDIO, MediaFormat.WEBMA_OPUS, 35), new ItagItem(249, ItagType.AUDIO, MediaFormat.WEBMA_OPUS, 50), new ItagItem(250, ItagType.AUDIO, MediaFormat.WEBMA_OPUS, 70), new ItagItem(251, ItagType.AUDIO, MediaFormat.WEBMA_OPUS, 160), new ItagItem(160, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "144p"), new ItagItem(394, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "144p"), new ItagItem(133, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "240p"), new ItagItem(395, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "240p"), new ItagItem(134, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "360p"), new ItagItem(396, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "360p"), new ItagItem(135, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "480p"), new ItagItem(212, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "480p"), new ItagItem(397, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "480p"), new ItagItem(136, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "720p"), new ItagItem(398, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "720p"), new ItagItem(298, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "720p60", 60), new ItagItem(137, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "1080p"), new ItagItem(399, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "1080p"), new ItagItem(299, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "1080p60", 60), new ItagItem(400, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "1440p"), new ItagItem(GlobalMediaRouter.CallbackHandler.MSG_ROUTE_DISCONNECTED, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "2160p"), new ItagItem(TypedValues.CycleType.TYPE_CURVE_FIT, ItagType.VIDEO_ONLY, MediaFormat.MPEG_4, "2160p"), new ItagItem(278, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "144p"), new ItagItem(242, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "240p"), new ItagItem(243, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "360p"), new ItagItem(HebrewProber.NORMAL_PE, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "480p"), new ItagItem(HebrewProber.FINAL_TSADI, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "480p"), new ItagItem(HebrewProber.NORMAL_TSADI, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "480p"), new ItagItem(247, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "720p"), new ItagItem(248, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "1080p"), new ItagItem(271, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "1440p"), new ItagItem(272, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "2160p"), new ItagItem(302, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "720p60", 60), new ItagItem(303, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "1080p60", 60), new ItagItem(308, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "1440p60", 60), new ItagItem(313, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "2160p"), new ItagItem(315, ItagType.VIDEO_ONLY, MediaFormat.WEBM, "2160p60", 60)};
    public static final long LAST_MODIFIED_UNKOWN = -1;
    public static final int SAMPLE_RATE_UNKNOWN = -1;
    public static final int TARGET_DURATION_SEC_UNKNOWN = -1;
    private long approxDurationMs;
    private int audioChannels;

    @Nullable
    private Locale audioLocale;
    private String audioTrackId;
    private String audioTrackName;

    @Nullable
    private AudioTrackType audioTrackType;

    @Deprecated
    public int avgBitrate;
    private int bitrate;
    private String codec;
    private long contentLength;

    @Deprecated
    public int fps;
    private int height;
    public final int id;
    private int indexEnd;
    private int indexStart;
    private int initEnd;
    private int initStart;
    private boolean isDrc;
    public final ItagType itagType;
    private long lastModified;
    private final MediaFormat mediaFormat;
    private String quality;

    @Deprecated
    public String resolutionString;
    private int sampleRate;
    private int targetDurationSec;
    private int width;
    private String xtags;

    public enum ItagType {
        AUDIO,
        VIDEO,
        VIDEO_ONLY
    }

    public static boolean isSupported(int i) {
        for (ItagItem itagItem : ITAG_LIST) {
            if (i == itagItem.id) {
                return true;
            }
        }
        return false;
    }

    @Nonnull
    public static ItagItem getItag(int i) throws ParsingException {
        for (ItagItem itagItem : ITAG_LIST) {
            if (i == itagItem.id) {
                return new ItagItem(itagItem);
            }
        }
        throw new ParsingException("itag " + i + " is not supported");
    }

    public ItagItem(int i, ItagType itagType, MediaFormat mediaFormat, String str) {
        this.avgBitrate = -1;
        this.sampleRate = -1;
        this.audioChannels = -1;
        this.targetDurationSec = -1;
        this.approxDurationMs = -1L;
        this.contentLength = -1L;
        this.id = i;
        this.itagType = itagType;
        this.mediaFormat = mediaFormat;
        this.resolutionString = str;
        this.fps = 30;
    }

    public ItagItem(int i, ItagType itagType, MediaFormat mediaFormat, String str, int i2) {
        this.avgBitrate = -1;
        this.sampleRate = -1;
        this.audioChannels = -1;
        this.targetDurationSec = -1;
        this.approxDurationMs = -1L;
        this.contentLength = -1L;
        this.id = i;
        this.itagType = itagType;
        this.mediaFormat = mediaFormat;
        this.resolutionString = str;
        this.fps = i2;
    }

    public ItagItem(int i, ItagType itagType, MediaFormat mediaFormat, int i2) {
        this.sampleRate = -1;
        this.audioChannels = -1;
        this.fps = -1;
        this.targetDurationSec = -1;
        this.approxDurationMs = -1L;
        this.contentLength = -1L;
        this.id = i;
        this.itagType = itagType;
        this.mediaFormat = mediaFormat;
        this.avgBitrate = i2;
    }

    public ItagItem(@Nonnull ItagItem itagItem) {
        this.avgBitrate = -1;
        this.sampleRate = -1;
        this.audioChannels = -1;
        this.fps = -1;
        this.targetDurationSec = -1;
        this.approxDurationMs = -1L;
        this.contentLength = -1L;
        this.mediaFormat = itagItem.mediaFormat;
        this.id = itagItem.id;
        this.itagType = itagItem.itagType;
        this.avgBitrate = itagItem.avgBitrate;
        this.sampleRate = itagItem.sampleRate;
        this.audioChannels = itagItem.audioChannels;
        this.resolutionString = itagItem.resolutionString;
        this.fps = itagItem.fps;
        this.bitrate = itagItem.bitrate;
        this.width = itagItem.width;
        this.height = itagItem.height;
        this.initStart = itagItem.initStart;
        this.initEnd = itagItem.initEnd;
        this.indexStart = itagItem.indexStart;
        this.indexEnd = itagItem.indexEnd;
        this.quality = itagItem.quality;
        this.codec = itagItem.codec;
        this.targetDurationSec = itagItem.targetDurationSec;
        this.approxDurationMs = itagItem.approxDurationMs;
        this.contentLength = itagItem.contentLength;
        this.audioTrackId = itagItem.audioTrackId;
        this.audioTrackName = itagItem.audioTrackName;
        this.audioTrackType = itagItem.audioTrackType;
        this.audioLocale = itagItem.audioLocale;
    }

    public MediaFormat getMediaFormat() {
        return this.mediaFormat;
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public void setBitrate(int i) {
        this.bitrate = i;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getFps() {
        return this.fps;
    }

    public void setFps(int i) {
        if (i <= 0) {
            i = -1;
        }
        this.fps = i;
    }

    public int getInitStart() {
        return this.initStart;
    }

    public void setInitStart(int i) {
        this.initStart = i;
    }

    public int getInitEnd() {
        return this.initEnd;
    }

    public void setInitEnd(int i) {
        this.initEnd = i;
    }

    public int getIndexStart() {
        return this.indexStart;
    }

    public void setIndexStart(int i) {
        this.indexStart = i;
    }

    public int getIndexEnd() {
        return this.indexEnd;
    }

    public void setIndexEnd(int i) {
        this.indexEnd = i;
    }

    public String getQuality() {
        return this.quality;
    }

    public void setQuality(String str) {
        this.quality = str;
    }

    @Nullable
    public String getResolutionString() {
        return this.resolutionString;
    }

    public String getCodec() {
        return this.codec;
    }

    public void setCodec(String str) {
        this.codec = str;
    }

    public int getAverageBitrate() {
        return this.avgBitrate;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public void setSampleRate(int i) {
        if (i <= 0) {
            i = -1;
        }
        this.sampleRate = i;
    }

    public int getAudioChannels() {
        return this.audioChannels;
    }

    public void setAudioChannels(int i) {
        if (i <= 0) {
            i = -1;
        }
        this.audioChannels = i;
    }

    public int getTargetDurationSec() {
        return this.targetDurationSec;
    }

    public void setTargetDurationSec(int i) {
        if (i <= 0) {
            i = -1;
        }
        this.targetDurationSec = i;
    }

    public long getApproxDurationMs() {
        return this.approxDurationMs;
    }

    public void setApproxDurationMs(long j) {
        if (j <= 0) {
            j = -1;
        }
        this.approxDurationMs = j;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public void setContentLength(long j) {
        if (j <= 0) {
            j = -1;
        }
        this.contentLength = j;
    }

    @Nullable
    public String getAudioTrackId() {
        return this.audioTrackId;
    }

    public void setAudioTrackId(@Nullable String str) {
        this.audioTrackId = str;
    }

    @Nullable
    public String getAudioTrackName() {
        return this.audioTrackName;
    }

    public void setAudioTrackName(@Nullable String str) {
        this.audioTrackName = str;
    }

    @Nullable
    public AudioTrackType getAudioTrackType() {
        return this.audioTrackType;
    }

    public void setAudioTrackType(@Nullable AudioTrackType audioTrackType) {
        this.audioTrackType = audioTrackType;
    }

    @Nullable
    public Locale getAudioLocale() {
        return this.audioLocale;
    }

    public void setAudioLocale(@Nullable Locale locale) {
        this.audioLocale = locale;
    }

    public Boolean isDrc() {
        return Boolean.valueOf(this.isDrc);
    }

    public void setIsDrc(Boolean bool) {
        this.isDrc = bool.booleanValue();
    }

    public long getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(long j) {
        this.lastModified = j;
    }

    public String getXtags() {
        return this.xtags;
    }

    public void setXtags(String str) {
        this.xtags = str;
    }
}
