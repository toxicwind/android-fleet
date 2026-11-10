package com.google.android.exoplayer.audio;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTimestamp;
import android.media.MediaFormat;
import android.media.PlaybackParams;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.exoplayer.C;
import com.google.android.exoplayer.util.AacUtil;
import com.google.android.exoplayer.util.Ac3Util;
import com.google.android.exoplayer.util.Ac4Util;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Downmixer;
import com.google.android.exoplayer.util.DtsUtil;
import com.google.android.exoplayer.util.MpeghUtil;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public final class AudioTrack {
    public static boolean enablePreV21AudioSessionWorkaround = false;
    public static boolean failOnSpuriousAudioTimestamp = false;
    private int ac3Bitrate;
    private ByteBuffer ac3Buffer;
    private byte[] ac3Header;
    private int ac3HeaderPointer;
    private boolean ac3Initialized;
    private int ac3ProcessingStatus;
    private int ac3SyncframeCount;
    private int ac3SyncframeRemain;
    private int ac3SyncframeSize;
    private int ac4FramerateIndex;
    private final AudioCapabilities audioCapabilities;
    private boolean audioTimestampSet;
    private android.media.AudioTrack audioTrack;
    private final AudioTrackUtil audioTrackUtil;
    private int bufferBytesRemaining;
    private int bufferSize;
    private long bufferSizeUs;
    private ByteBuffer bufferWithRemaining;
    private int channelConfig;
    private Method getLatencyMethod;
    private android.media.AudioTrack keepSessionIdAudioTrack;
    private long lastPlayheadSampleTimeUs;
    private long lastReleaseTimeMs;
    private long lastTimestampSampleTimeUs;
    private long latencyUs;
    private String mimeType;
    private int nextPlayheadOffsetIndex;
    private boolean passthrough;
    private int pcmFrameSize;
    private int playheadOffsetCount;
    private final long[] playheadOffsets;
    private byte[] remainingBytesArray;
    private ByteBuffer resampledBuffer;
    private long resumeSystemTimeUs;
    private int sampleRate;
    private int skipBytesCounter;
    private int skipFrameCounter;
    private long smoothedPlayheadOffsetUs;
    private int sourceChannelCount;
    private int sourceEncoding;
    private int startMediaTimeState;
    private long startMediaTimeUs;
    private int streamType;
    private long submittedEncodedFrames;
    private long submittedPcmBytes;
    private int targetEncoding;
    private byte[] temporaryBuffer;
    private int temporaryBufferOffset;
    private int tunnelingAudioSessionId;
    private ByteBuffer tunnelingBuffer;
    private boolean useResampledBuffer;
    private float volume;
    private static final int[] AC3_SYNCFRAME_SIZE_TABLE = {64, 64, 80, 80, 96, 96, 112, 112, 128, 128, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_NOT_SUPPORTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_NOT_SUPPORTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN, 256, 256, 320, 320, 384, 384, 448, 448, 512, 512, 640, 640, 768, 768, 896, 896, 1024, 1024, 1152, 1152, 1280, 1280, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69, 70, 87, 88, 104, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DRM_NOT_SUPPORT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VIDEO_PRESENT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VS_BIT_RATE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_CLEAR_EOF, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_AUDIO_SWITCH_DONE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_STREAM_CTR_NOT_SUPPORT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_AUDIO_STREAM_CTR_NOT_SUPPORT, 243, 244, 278, 279, 348, 349, 417, 418, 487, 488, 557, 558, 696, 697, 835, 836, 975, 976, 1114, 1115, 1253, 1254, 1393, 1394, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 96, 96, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RANGE_AB_DONE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RANGE_AB_DONE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_OFF, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_OFF, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONNECTION_ANA_FREQ, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONNECTION_ANA_FREQ, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED, 240, 240, 288, 288, 336, 336, 384, 384, 480, 480, 576, 576, 672, 672, 768, 768, 960, 960, 1152, 1152, 1344, 1344, 1536, 1536, 1728, 1728, 1920, 1920, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[] AC3_BITRATE_TABLE = {32, 40, 48, 56, 64, 80, 96, 112, 128, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_NOT_SUPPORTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN, 256, 320, 384, 448, 512, 576, 640};
    private static final int[] AC3_FSCOD_SAMPLE_RATE = {48000, 44100, 32000};
    private static final int[] AC3_NUMBER_OF_BLOCK = {1, 2, 3, 6};
    private int framesPerEncodedSample = 0;
    private boolean useBufferWithRemaining = false;
    private long lastPresentationTimeUs = -1;
    private boolean dumpOutput = false;
    private int aacProfile = -1;
    private long lastAppendedPresentTimeUs = -1;
    private int lastAppendedSize = 0;
    private int encodedFrameCountInBuffer = 0;
    private boolean needDownMix = false;
    private final ConditionVariable releasingConditionVariable = new ConditionVariable(true);

    public static final class InitializationException extends Exception {
        public final int audioTrackState;

        public InitializationException(int i, int i2, int i3, int i4) {
            super("AudioTrack init failed: " + i + ", Config(" + i2 + ", " + i3 + ", " + i4 + ")");
            this.audioTrackState = i;
        }
    }

    public static final class WriteException extends Exception {
        public final int errorCode;

        public WriteException(int i) {
            super("AudioTrack write failed: " + i);
            this.errorCode = i;
        }
    }

    public static final class InvalidAudioTrackTimestampException extends RuntimeException {
        public InvalidAudioTrackTimestampException(String str) {
            super(str);
        }
    }

    public AudioTrack(AudioCapabilities audioCapabilities, int i) {
        this.audioCapabilities = audioCapabilities;
        if (Util.SDK_INT >= 18) {
            try {
                this.getLatencyMethod = android.media.AudioTrack.class.getMethod("getLatency", null);
            } catch (NoSuchMethodException unused) {
            }
        }
        int i2 = Util.SDK_INT;
        if (i2 >= 23) {
            this.audioTrackUtil = new AudioTrackUtilV23();
        } else if (i2 >= 19) {
            this.audioTrackUtil = new AudioTrackUtilV19();
        } else {
            this.audioTrackUtil = new AudioTrackUtil();
        }
        this.playheadOffsets = new long[10];
        this.streamType = i;
        this.volume = 1.0f;
        this.startMediaTimeState = 0;
        this.tunnelingAudioSessionId = -1;
    }

    public boolean isPassthroughSupported(String str) {
        AudioCapabilities audioCapabilities = this.audioCapabilities;
        return audioCapabilities != null && audioCapabilities.supportsEncoding(getEncodingForMimeType(str, -1));
    }

    public boolean isInitialized() {
        return this.audioTrack != null;
    }

    public long getCurrentPositionUs(boolean z) {
        long playbackHeadPositionUs;
        long j;
        if (!hasCurrentPositionUs()) {
            return Long.MIN_VALUE;
        }
        if (this.audioTrack.getPlayState() == 3) {
            maybeSampleSyncParams();
        }
        long jNanoTime = System.nanoTime() / 1000;
        if (this.audioTimestampSet) {
            return framesToDurationUs(this.audioTrackUtil.getTimestampFramePosition() + durationUsToFrames((long) ((jNanoTime - (this.audioTrackUtil.getTimestampNanoTime() / 1000)) * this.audioTrackUtil.getPlaybackSpeed()))) + this.startMediaTimeUs;
        }
        if (this.playheadOffsetCount == 0) {
            playbackHeadPositionUs = this.audioTrackUtil.getPlaybackHeadPositionUs();
            j = this.startMediaTimeUs;
        } else {
            playbackHeadPositionUs = jNanoTime + this.smoothedPlayheadOffsetUs;
            j = this.startMediaTimeUs;
        }
        long j2 = playbackHeadPositionUs + j;
        return !z ? j2 - this.latencyUs : j2;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x016b  */
    /* JADX WARN: Code duplicated, block: B:102:0x0172  */
    /* JADX WARN: Code duplicated, block: B:103:0x0174  */
    /* JADX WARN: Code duplicated, block: B:106:0x0198  */
    /* JADX WARN: Code duplicated, block: B:107:0x019a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:108:0x019c  */
    /* JADX WARN: Code duplicated, block: B:109:0x019e  */
    /* JADX WARN: Code duplicated, block: B:112:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:115:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:116:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:25:0x0068  */
    /* JADX WARN: Code duplicated, block: B:76:0x0128  */
    /* JADX WARN: Code duplicated, block: B:79:0x0132  */
    /* JADX WARN: Code duplicated, block: B:80:0x0136 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:81:0x0138  */
    /* JADX WARN: Code duplicated, block: B:99:0x0167  */
    public void configure(String str, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int minBufferSize;
        boolean z;
        int i7;
        int iDurationUsToFrames;
        int iMax;
        int i8;
        long jFramesToDurationUs;
        int i9 = i;
        int encodingForMimeType = i3;
        boolean z2 = !"audio/raw".equals(str);
        this.sourceChannelCount = i9;
        if (this.tunnelingAudioSessionId != -1 && !z2 && i9 == 6) {
            this.needDownMix = true;
            i9 = 2;
        } else {
            this.needDownMix = false;
        }
        if (i9 != 12) {
            switch (i9) {
                case 1:
                    i5 = 4;
                    break;
                case 2:
                    i5 = 12;
                    break;
                case 3:
                    i5 = 28;
                    break;
                case 4:
                    i5 = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CAD_ACQUISITION_ERROR;
                    break;
                case 5:
                    if (!str.equals("audio/ac3") && !str.equals("audio/eac3")) {
                        i5 = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CAD_INVALID;
                        break;
                    }
                case 6:
                    i5 = 252;
                    break;
                case 7:
                    i5 = 1276;
                    break;
                case 8:
                    i5 = C.CHANNEL_OUT_7POINT1_SURROUND;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported channel count: " + i9);
            }
        } else {
            i5 = C.CHANNEL_OUT_7POINT1_SURROUND;
        }
        if (Util.SDK_INT <= 23 && "foster".equals(Util.DEVICE) && "NVIDIA".equals(Util.MANUFACTURER)) {
            if (i9 == 3 || i9 == 5) {
                i5 = 252;
            } else if (i9 == 7) {
                i5 = C.CHANNEL_OUT_7POINT1_SURROUND;
            }
        }
        if (Util.SDK_INT <= 25 && "fugu".equals(Util.DEVICE) && z2 && i9 == 1) {
            i5 = 12;
        }
        if (z2) {
            if ("audio/mp4a-latm".equals(str)) {
                encodingForMimeType = getEncodingForMimeType(str, this.aacProfile);
                if (encodingForMimeType == 11 || encodingForMimeType == 12) {
                    i6 = i2 * 2;
                    Log.i("AudioTrack", "Double the sample rate[" + i6 + "] for HEAAC!");
                }
                if (!isInitialized() && this.sourceEncoding == encodingForMimeType && this.sampleRate == i6 && this.channelConfig == i5) {
                    return;
                }
                reset();
                this.sourceEncoding = encodingForMimeType;
                this.passthrough = z2;
                this.sampleRate = i6;
                this.channelConfig = i5;
                if (!z2) {
                    encodingForMimeType = 2;
                }
                this.targetEncoding = encodingForMimeType;
                this.pcmFrameSize = i9 * 2;
                this.mimeType = str;
                if (i4 != 0) {
                    this.bufferSize = i4;
                } else if (z2) {
                    minBufferSize = android.media.AudioTrack.getMinBufferSize(i6, i5, encodingForMimeType);
                    if (minBufferSize != -2) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Assertions.checkState(z);
                    i7 = minBufferSize * 2;
                    iDurationUsToFrames = ((int) durationUsToFrames(250000L)) * this.pcmFrameSize;
                    iMax = (int) Math.max(minBufferSize, durationUsToFrames(750000L) * ((long) this.pcmFrameSize));
                    if (i7 < iDurationUsToFrames) {
                        i8 = iDurationUsToFrames;
                    } else if (i7 > iMax) {
                        i8 = iMax;
                    } else {
                        i8 = i7;
                    }
                    this.bufferSize = i8;
                    if (i8 < 12288) {
                        this.bufferSize = 12288;
                    }
                    MtkDebugLogUtil.log("AudioTrack", "configure: sampleRate=" + i6 + ", channelConfig=" + i5 + ", targetEncoding=" + this.targetEncoding + ", minBufferSize=" + minBufferSize + ", multipliedBufferSize=" + i7 + ", minAppBufferSize=" + iDurationUsToFrames + ", maxAppBufferSize=" + iMax + ", bufferSize=" + this.bufferSize, 2);
                } else if (encodingForMimeType != 5 || encodingForMimeType == 6) {
                    this.bufferSize = 32768;
                } else if (encodingForMimeType == 7 || encodingForMimeType == 8) {
                    this.bufferSize = MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN;
                } else if (encodingForMimeType == 10) {
                    this.bufferSize = 28672;
                } else if (encodingForMimeType == 11 || encodingForMimeType == 12) {
                    this.bufferSize = 8192;
                } else {
                    this.bufferSize = 32768;
                }
                if (z2) {
                    jFramesToDurationUs = -1;
                } else {
                    jFramesToDurationUs = framesToDurationUs(pcmBytesToFrames(this.bufferSize));
                }
                this.bufferSizeUs = jFramesToDurationUs;
            }
            encodingForMimeType = getEncodingForMimeType(str, -1);
        } else if (encodingForMimeType != 3 && encodingForMimeType != 2 && encodingForMimeType != Integer.MIN_VALUE && encodingForMimeType != 1073741824) {
            throw new IllegalArgumentException("Unsupported PCM encoding: " + encodingForMimeType);
        }
        i6 = i2;
        if (!isInitialized()) {
        }
        reset();
        this.sourceEncoding = encodingForMimeType;
        this.passthrough = z2;
        this.sampleRate = i6;
        this.channelConfig = i5;
        if (!z2) {
            encodingForMimeType = 2;
        }
        this.targetEncoding = encodingForMimeType;
        this.pcmFrameSize = i9 * 2;
        this.mimeType = str;
        if (i4 != 0) {
            this.bufferSize = i4;
        } else if (z2) {
            minBufferSize = android.media.AudioTrack.getMinBufferSize(i6, i5, encodingForMimeType);
            if (minBufferSize != -2) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
            i7 = minBufferSize * 2;
            iDurationUsToFrames = ((int) durationUsToFrames(250000L)) * this.pcmFrameSize;
            iMax = (int) Math.max(minBufferSize, durationUsToFrames(750000L) * ((long) this.pcmFrameSize));
            if (i7 < iDurationUsToFrames) {
                i8 = iDurationUsToFrames;
            } else if (i7 > iMax) {
                i8 = iMax;
            } else {
                i8 = i7;
            }
            this.bufferSize = i8;
            if (i8 < 12288) {
                this.bufferSize = 12288;
            }
            MtkDebugLogUtil.log("AudioTrack", "configure: sampleRate=" + i6 + ", channelConfig=" + i5 + ", targetEncoding=" + this.targetEncoding + ", minBufferSize=" + minBufferSize + ", multipliedBufferSize=" + i7 + ", minAppBufferSize=" + iDurationUsToFrames + ", maxAppBufferSize=" + iMax + ", bufferSize=" + this.bufferSize, 2);
        } else if (encodingForMimeType != 5) {
            this.bufferSize = 32768;
        } else {
            this.bufferSize = 32768;
        }
        if (z2) {
            jFramesToDurationUs = -1;
        } else {
            jFramesToDurationUs = framesToDurationUs(pcmBytesToFrames(this.bufferSize));
        }
        this.bufferSizeUs = jFramesToDurationUs;
    }

    public int initialize() throws InitializationException {
        return initialize(0);
    }

    public int initialize(int i) throws InitializationException {
        this.releasingConditionVariable.block();
        long jCurrentTimeMillis = System.currentTimeMillis() - this.lastReleaseTimeMs;
        if (jCurrentTimeMillis < 150) {
            try {
                Thread.sleep(150 - jCurrentTimeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.tunnelingAudioSessionId != -1) {
            setupTunnelingAudioTrack();
        } else if (i == 0) {
            this.audioTrack = new android.media.AudioTrack(this.streamType, this.sampleRate, this.channelConfig, this.targetEncoding, this.bufferSize, 1);
        } else {
            this.audioTrack = new android.media.AudioTrack(this.streamType, this.sampleRate, this.channelConfig, this.targetEncoding, this.bufferSize, 1, i);
        }
        checkAudioTrackInitialized();
        int audioSessionId = this.audioTrack.getAudioSessionId();
        if (enablePreV21AudioSessionWorkaround && Util.SDK_INT < 21) {
            android.media.AudioTrack audioTrack = this.keepSessionIdAudioTrack;
            if (audioTrack != null && audioSessionId != audioTrack.getAudioSessionId()) {
                releaseKeepSessionIdAudioTrack();
            }
            if (this.keepSessionIdAudioTrack == null) {
                this.keepSessionIdAudioTrack = new android.media.AudioTrack(this.streamType, 4000, 4, 2, 2, 0, audioSessionId);
            }
        }
        this.audioTrackUtil.reconfigure(this.audioTrack, needsPassthroughWorkarounds());
        setAudioTrackVolume();
        if ("1".equals(Util.getProp("mtk.exoplayer.dump.audio.output", "0"))) {
            this.dumpOutput = true;
        } else {
            this.dumpOutput = false;
        }
        return audioSessionId;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public long getBufferSizeUs() {
        return this.bufferSizeUs;
    }

    public void play() {
        MtkDebugLogUtil.log("AudioTrack", "play()", 2);
        if (isInitialized()) {
            this.resumeSystemTimeUs = System.nanoTime() / 1000;
            MtkDebugLogUtil.log("AudioTrack", "audioTrackUtil.play()", 2);
            this.audioTrackUtil.play();
        }
    }

    public void handleDiscontinuity() {
        if (this.startMediaTimeState == 1) {
            this.startMediaTimeState = 2;
        }
    }

    public int handleBuffer(ByteBuffer byteBuffer, int i, int i2, long j) throws WriteException {
        ByteBuffer byteBufferResampleTo16BitPcm;
        int iPosition;
        int iLimit;
        int i3;
        int i4;
        int i5;
        String str;
        int i6;
        int i7;
        char c;
        ByteBuffer byteBuffer2;
        int i8;
        ByteBuffer byteBuffer3;
        ByteBuffer byteBuffer4;
        int iWriteNonBlockingV21;
        int iWrite;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        long j2 = j;
        if (needsPassthroughWorkarounds()) {
            if (this.audioTrack.getPlayState() == 2) {
                return 0;
            }
            if (this.audioTrack.getPlayState() == 1 && this.audioTrackUtil.getPlaybackHeadPosition() != 0) {
                return 0;
            }
        }
        if (this.passthrough && ((i12 = this.targetEncoding) == 5 || i12 == 6 || i12 == 18)) {
            if (!isAc3Initialized()) {
                initializeAc3();
            }
            try {
                if (ac3BufferProcess(byteBuffer, i2) == -1) {
                    return 2;
                }
                int iPosition2 = byteBuffer.position();
                ByteBuffer byteBuffer5 = this.ac3Buffer;
                iLimit = this.ac3SyncframeSize;
                iPosition = 0;
                i4 = i2;
                i3 = iPosition2;
                byteBufferResampleTo16BitPcm = byteBuffer5;
            } catch (WriteException e) {
                if (this.submittedEncodedFrames > 0 && (i13 = this.skipFrameCounter) <= 20) {
                    this.skipFrameCounter = i13 + 1;
                    this.skipBytesCounter = 0;
                    this.bufferBytesRemaining = 0;
                    this.ac3ProcessingStatus = 4;
                    MtkDebugLogUtil.log("AudioTrack", "skip non-standard frame = " + this.skipFrameCounter, 2);
                    return 2;
                }
                throw e;
            }
        } else {
            byteBufferResampleTo16BitPcm = byteBuffer;
            iPosition = i;
            iLimit = i2;
            i3 = 0;
            i4 = 0;
        }
        if (this.bufferBytesRemaining != 0) {
            i5 = i3;
            str = "AudioTrack";
            i6 = i4;
            i7 = 6;
            c = 5;
            byteBuffer2 = byteBufferResampleTo16BitPcm;
            i8 = 0;
        } else {
            if (iLimit == 0) {
                return 2;
            }
            boolean z = this.targetEncoding != this.sourceEncoding;
            this.useResampledBuffer = z;
            if (z) {
                Assertions.checkState(this.targetEncoding == 2);
                i5 = i3;
                str = "AudioTrack";
                i6 = i4;
                byteBufferResampleTo16BitPcm = resampleTo16BitPcm(byteBufferResampleTo16BitPcm, iPosition, iLimit, this.sourceEncoding, this.resampledBuffer);
                this.resampledBuffer = byteBufferResampleTo16BitPcm;
                iPosition = byteBufferResampleTo16BitPcm.position();
                iLimit = this.resampledBuffer.limit();
            } else {
                i5 = i3;
                str = "AudioTrack";
                i6 = i4;
                if (this.tunnelingAudioSessionId == -1) {
                    if (this.remainingBytesArray != null) {
                        ByteBuffer byteBuffer6 = this.bufferWithRemaining;
                        if (byteBuffer6 == null || byteBuffer6.capacity() < iLimit + this.remainingBytesArray.length) {
                            this.bufferWithRemaining = ByteBuffer.allocateDirect(iLimit + this.remainingBytesArray.length);
                        }
                        this.bufferWithRemaining.position(0);
                        this.bufferWithRemaining.limit(iLimit + this.remainingBytesArray.length);
                        this.bufferWithRemaining.put(this.remainingBytesArray);
                        this.bufferWithRemaining.put(byteBufferResampleTo16BitPcm);
                        this.bufferWithRemaining.position(0);
                        byteBufferResampleTo16BitPcm = this.bufferWithRemaining;
                        iPosition = byteBufferResampleTo16BitPcm.position();
                        iLimit = this.bufferWithRemaining.limit();
                        this.remainingBytesArray = null;
                        this.useBufferWithRemaining = true;
                    } else {
                        this.useBufferWithRemaining = false;
                    }
                }
            }
            int i14 = iLimit;
            this.bufferBytesRemaining = i14;
            byteBufferResampleTo16BitPcm.position(iPosition);
            if (this.passthrough && this.framesPerEncodedSample == 0) {
                int i15 = this.targetEncoding;
                if (i15 == 10 || i15 == 11 || i15 == 12) {
                    this.framesPerEncodedSample = AacUtil.getAacSyncframeAudioSampleCount(this.aacProfile);
                } else if (i15 == 7 || i15 == 8) {
                    int iPosition3 = byteBufferResampleTo16BitPcm.position();
                    if (byteBufferResampleTo16BitPcm.get(iPosition3) == 100 && byteBufferResampleTo16BitPcm.get(iPosition3 + 1) == 88 && byteBufferResampleTo16BitPcm.get(iPosition3 + 2) == 32 && byteBufferResampleTo16BitPcm.get(iPosition3 + 3) == 37) {
                        this.framesPerEncodedSample = DtsUtil.parseDtsExtensionAudioSampleCount(byteBufferResampleTo16BitPcm, this.sampleRate);
                    } else {
                        int dtsAudioSampleCount = DtsUtil.parseDtsAudioSampleCount(byteBufferResampleTo16BitPcm);
                        if (dtsAudioSampleCount > 0) {
                            this.framesPerEncodedSample = dtsAudioSampleCount;
                        }
                    }
                } else if (i15 == 19) {
                    this.framesPerEncodedSample = MpeghUtil.getMpeghSyncframeAudioSampleCount(this.sampleRate);
                } else {
                    this.framesPerEncodedSample = getFramesPerEncodedSample(i15, byteBufferResampleTo16BitPcm);
                }
                if (this.targetEncoding == C.ENCODING_AC4) {
                    int ac4FramerateIndex = getAc4FramerateIndex(byteBufferResampleTo16BitPcm);
                    this.ac4FramerateIndex = ac4FramerateIndex;
                    this.audioTrackUtil.setAc4FramerateIndex(ac4FramerateIndex);
                }
            }
            if (this.startMediaTimeState == 0) {
                this.startMediaTimeUs = Math.max(0L, j2);
                this.startMediaTimeState = 1;
                Log.d(str, "[Performance]handleBuffer: presentationTimeUs=" + j2 + ", submittedEncodedFrames=" + this.submittedEncodedFrames + ", startMediaTimeUs=" + this.startMediaTimeUs);
                i10 = 0;
                c = 5;
                i7 = 6;
            } else {
                long jFramesToDurationUs = this.startMediaTimeUs + framesToDurationUs(getSubmittedFrames());
                MtkDebugLogUtil.log(str, "handleBuffer: presentationTimeUs=" + j2 + ", submittedEncodedFrames=" + this.submittedEncodedFrames + ", expectedBufferStartTime=" + jFramesToDurationUs + ", startMediaTimeUs=" + this.startMediaTimeUs, 2);
                int i16 = this.targetEncoding;
                c = 5;
                i7 = 6;
                if ((i16 == 5 || i16 == 6) && j2 != jFramesToDurationUs) {
                    MtkDebugLogUtil.log(str, "Change PTS to expected time:" + jFramesToDurationUs, 2);
                    j2 = jFramesToDurationUs;
                }
                if (this.startMediaTimeState != 1 || Math.abs(jFramesToDurationUs - j2) <= 256000 || this.lastPresentationTimeUs == j2) {
                    i9 = 2;
                } else {
                    Log.e(str, "Discontinuity detected [expected " + jFramesToDurationUs + ", got " + j2 + "]");
                    i9 = 2;
                    this.startMediaTimeState = 2;
                }
                if (this.startMediaTimeState == i9) {
                    this.startMediaTimeUs += j2 - jFramesToDurationUs;
                    this.startMediaTimeState = 1;
                    i10 = 1;
                } else {
                    i10 = 0;
                }
            }
            if (Util.SDK_INT < 21) {
                byte[] bArr = this.temporaryBuffer;
                if (bArr == null || bArr.length < i14) {
                    this.temporaryBuffer = new byte[i14];
                }
                byteBufferResampleTo16BitPcm.get(this.temporaryBuffer, 0, i14);
                this.temporaryBufferOffset = 0;
            }
            if (this.passthrough && ((i11 = this.targetEncoding) == 7 || i11 == 8)) {
                for (int iPosition4 = byteBufferResampleTo16BitPcm.position(); iPosition4 + 4 < i14; iPosition4++) {
                    if (byteBufferResampleTo16BitPcm.get(iPosition4) == 127 && (byteBufferResampleTo16BitPcm.get(iPosition4 + 1) & 255) == 254 && (byteBufferResampleTo16BitPcm.get(iPosition4 + 2) & 255) == 128 && byteBufferResampleTo16BitPcm.get(iPosition4 + 3) == 1) {
                        this.encodedFrameCountInBuffer++;
                    }
                }
            }
            if (this.tunnelingAudioSessionId != -1) {
                appendTunnelingHeader(byteBufferResampleTo16BitPcm, j2);
                if (!this.passthrough) {
                    this.submittedPcmBytes -= 16;
                }
            }
            this.lastPresentationTimeUs = j2;
            i8 = i10;
            byteBuffer2 = byteBufferResampleTo16BitPcm;
        }
        if (Util.SDK_INT < 21) {
            int playbackHeadPosition = this.bufferSize - ((int) (this.submittedPcmBytes - (this.audioTrackUtil.getPlaybackHeadPosition() * ((long) this.pcmFrameSize))));
            if (playbackHeadPosition > 0) {
                iWrite = this.audioTrack.write(this.temporaryBuffer, this.temporaryBufferOffset, Math.min(this.bufferBytesRemaining, playbackHeadPosition));
                if (iWrite >= 0) {
                    this.temporaryBufferOffset += iWrite;
                }
            } else {
                iWrite = 0;
            }
        } else {
            if (this.tunnelingAudioSessionId != -1) {
                iWriteNonBlockingV21 = writeNonBlockingV21(this.audioTrack, this.tunnelingBuffer, this.bufferBytesRemaining);
                byteBuffer3 = this.tunnelingBuffer;
            } else {
                if (this.useResampledBuffer) {
                    byteBuffer4 = this.resampledBuffer;
                } else {
                    if (this.useBufferWithRemaining) {
                        byteBuffer4 = this.bufferWithRemaining;
                    } else {
                        byteBuffer3 = byteBuffer2;
                    }
                    iWriteNonBlockingV21 = writeNonBlockingV21(this.audioTrack, byteBuffer3, this.bufferBytesRemaining);
                }
                byteBuffer3 = byteBuffer4;
                iWriteNonBlockingV21 = writeNonBlockingV21(this.audioTrack, byteBuffer3, this.bufferBytesRemaining);
            }
            ByteBuffer byteBuffer7 = byteBuffer3;
            int i17 = iWriteNonBlockingV21;
            if (this.dumpOutput && i17 > 0 && byteBuffer7 != null) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File("/data/exo_dump_audio_output_" + Thread.currentThread().getId() + ".bin"), true);
                    int iPosition5 = byteBuffer7.position();
                    byteBuffer7.position(iPosition5 - i17);
                    byte[] bArr2 = new byte[i17];
                    byteBuffer7.get(bArr2, 0, i17);
                    byteBuffer7.position(iPosition5);
                    fileOutputStream.write(bArr2);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            iWrite = i17;
        }
        if (iWrite < 0) {
            if ((iWrite == -32 || iWrite == -6) && (this.passthrough || this.tunnelingAudioSessionId != -1)) {
                Log.d(str, "Receive dead object error using direct out thread. Re-create AudioTrack.");
                reset();
                return i8;
            }
            throw new WriteException(iWrite);
        }
        this.bufferBytesRemaining -= iWrite;
        MtkDebugLogUtil.log(str, "bytesWritten = " + iWrite + ", bufferBytesRemaining = " + this.bufferBytesRemaining + ", submittedPcmBytes = " + this.submittedPcmBytes + ", submittedEncodedFrames = " + this.submittedEncodedFrames, 2);
        if (!this.passthrough) {
            this.submittedPcmBytes += (long) iWrite;
        }
        int i18 = this.bufferBytesRemaining;
        if (i18 == 0) {
            if (this.passthrough) {
                int i19 = this.targetEncoding;
                if (i19 == 7 || i19 == 8) {
                    long j3 = this.submittedEncodedFrames;
                    int i20 = this.encodedFrameCountInBuffer;
                    this.submittedEncodedFrames = j3 + (i20 > 0 ? ((long) this.framesPerEncodedSample) * ((long) i20) : this.framesPerEncodedSample);
                    this.encodedFrameCountInBuffer = 0;
                } else if (i19 == i7) {
                    byte[] bArr3 = this.ac3Header;
                    int i21 = (bArr3[2] & 192) >> 6;
                    int i22 = (bArr3[c] & 255) >> 3;
                    if (i22 <= 8 || (i22 >= 11 && i22 <= 16 && i21 != 1)) {
                        this.submittedEncodedFrames += (long) this.framesPerEncodedSample;
                    }
                } else {
                    this.submittedEncodedFrames += (long) this.framesPerEncodedSample;
                }
                this.ac3ProcessingStatus = 4;
                this.skipFrameCounter = 0;
                if (i5 != i6) {
                    return i8;
                }
            }
        } else {
            if (this.passthrough || i18 >= this.pcmFrameSize) {
                return i8;
            }
            byte[] bArr4 = new byte[i18];
            this.remainingBytesArray = bArr4;
            if (this.tunnelingAudioSessionId != -1) {
                this.tunnelingBuffer.get(bArr4, 0, i18);
            } else if (this.useResampledBuffer) {
                this.resampledBuffer.get(bArr4, 0, i18);
            } else if (this.useBufferWithRemaining) {
                this.bufferWithRemaining.get(bArr4, 0, i18);
            } else {
                byteBuffer2.get(bArr4, 0, i18);
            }
            this.bufferBytesRemaining = 0;
        }
        return i8 | 2;
    }

    public void handleEndOfStream() {
        if (isInitialized()) {
            this.audioTrackUtil.handleEndOfStream(getSubmittedFrames());
        }
    }

    public boolean hasPendingData() {
        return isInitialized() && (getSubmittedFrames() > this.audioTrackUtil.getPlaybackHeadPosition() || overrideHasPendingData());
    }

    public void setPlaybackParams(PlaybackParams playbackParams) {
        this.audioTrackUtil.setPlaybackParameters(playbackParams);
    }

    public boolean setStreamType(int i) {
        if (this.streamType == i) {
            return false;
        }
        this.streamType = i;
        reset();
        return true;
    }

    public void setVolume(float f) {
        if (this.volume != f) {
            this.volume = f;
            setAudioTrackVolume();
        }
    }

    private void setAudioTrackVolume() {
        if (isInitialized()) {
            if (Util.SDK_INT >= 21) {
                setAudioTrackVolumeV21(this.audioTrack, this.volume);
            } else {
                setAudioTrackVolumeV3(this.audioTrack, this.volume);
            }
        }
    }

    public void pause() {
        MtkDebugLogUtil.log("AudioTrack", "pause()", 2);
        if (isInitialized()) {
            resetSyncParams();
            this.audioTrackUtil.pause();
        }
    }

    public void reset() {
        MtkDebugLogUtil.log("AudioTrack", "reset()", 2);
        if (isInitialized()) {
            this.submittedPcmBytes = 0L;
            this.submittedEncodedFrames = 0L;
            int i = this.targetEncoding;
            if (i != 7 && i != 8) {
                this.framesPerEncodedSample = 0;
            }
            this.bufferBytesRemaining = 0;
            this.startMediaTimeState = 0;
            this.latencyUs = 0L;
            resetSyncParams();
            resetAc3();
            if (this.audioTrack.getPlayState() == 3) {
                this.audioTrack.pause();
                MtkDebugLogUtil.log("AudioTrack", "audioTrack.pause()", 2);
            }
            final android.media.AudioTrack audioTrack = this.audioTrack;
            this.audioTrack = null;
            this.audioTrackUtil.reconfigure(null, false);
            this.releasingConditionVariable.close();
            new Thread() { // from class: com.google.android.exoplayer.audio.AudioTrack.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        audioTrack.flush();
                        AudioTrack.this.lastReleaseTimeMs = System.currentTimeMillis();
                        audioTrack.release();
                    } finally {
                        AudioTrack.this.releasingConditionVariable.open();
                    }
                }
            }.start();
        }
    }

    public void release() {
        MtkDebugLogUtil.log("AudioTrack", "release()", 2);
        reset();
        releaseKeepSessionIdAudioTrack();
    }

    private void releaseKeepSessionIdAudioTrack() {
        final android.media.AudioTrack audioTrack = this.keepSessionIdAudioTrack;
        if (audioTrack == null) {
            return;
        }
        this.keepSessionIdAudioTrack = null;
        new Thread(this) { // from class: com.google.android.exoplayer.audio.AudioTrack.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                audioTrack.release();
            }
        }.start();
    }

    private boolean hasCurrentPositionUs() {
        return isInitialized() && this.startMediaTimeState != 0;
    }

    private void maybeSampleSyncParams() {
        long playbackHeadPositionUs = this.audioTrackUtil.getPlaybackHeadPositionUs();
        if (playbackHeadPositionUs == 0) {
            return;
        }
        long jNanoTime = System.nanoTime() / 1000;
        if (jNanoTime - this.lastPlayheadSampleTimeUs >= 30000) {
            long[] jArr = this.playheadOffsets;
            int i = this.nextPlayheadOffsetIndex;
            jArr[i] = playbackHeadPositionUs - jNanoTime;
            this.nextPlayheadOffsetIndex = (i + 1) % 10;
            int i2 = this.playheadOffsetCount;
            if (i2 < 10) {
                this.playheadOffsetCount = i2 + 1;
            }
            this.lastPlayheadSampleTimeUs = jNanoTime;
            this.smoothedPlayheadOffsetUs = 0L;
            int i3 = 0;
            while (true) {
                int i4 = this.playheadOffsetCount;
                if (i3 >= i4) {
                    break;
                }
                this.smoothedPlayheadOffsetUs += this.playheadOffsets[i3] / ((long) i4);
                i3++;
            }
        }
        if (!needsPassthroughWorkarounds() && jNanoTime - this.lastTimestampSampleTimeUs >= 500000) {
            boolean zUpdateTimestamp = this.audioTrackUtil.updateTimestamp();
            this.audioTimestampSet = zUpdateTimestamp;
            if (zUpdateTimestamp) {
                long timestampNanoTime = this.audioTrackUtil.getTimestampNanoTime() / 1000;
                long timestampFramePosition = this.audioTrackUtil.getTimestampFramePosition();
                if (timestampNanoTime < this.resumeSystemTimeUs) {
                    this.audioTimestampSet = false;
                } else if (Math.abs(timestampNanoTime - jNanoTime) > 5000000) {
                    String str = "Spurious audio timestamp (system clock mismatch): " + timestampFramePosition + ", " + timestampNanoTime + ", " + jNanoTime + ", " + playbackHeadPositionUs;
                    if (failOnSpuriousAudioTimestamp) {
                        throw new InvalidAudioTrackTimestampException(str);
                    }
                    Log.w("AudioTrack", str);
                    this.audioTimestampSet = false;
                } else if (Math.abs(framesToDurationUs(timestampFramePosition) - playbackHeadPositionUs) > 5000000) {
                    String str2 = "Spurious audio timestamp (frame position mismatch): " + timestampFramePosition + ", " + timestampNanoTime + ", " + jNanoTime + ", " + playbackHeadPositionUs;
                    if (failOnSpuriousAudioTimestamp) {
                        throw new InvalidAudioTrackTimestampException(str2);
                    }
                    Log.w("AudioTrack", str2);
                    this.audioTimestampSet = false;
                }
            }
            Method method = this.getLatencyMethod;
            if (method != null && !this.passthrough) {
                try {
                    long jIntValue = (((long) ((Integer) method.invoke(this.audioTrack, null)).intValue()) * 1000) - this.bufferSizeUs;
                    this.latencyUs = jIntValue;
                    long jMax = Math.max(jIntValue, 0L);
                    this.latencyUs = jMax;
                    if (jMax > 5000000) {
                        Log.w("AudioTrack", "Ignoring impossibly large audio latency: " + this.latencyUs);
                        this.latencyUs = 0L;
                    }
                } catch (Exception unused) {
                    this.getLatencyMethod = null;
                }
            }
            this.lastTimestampSampleTimeUs = jNanoTime;
        }
    }

    private void checkAudioTrackInitialized() throws InitializationException {
        int state = this.audioTrack.getState();
        if (state == 1) {
            Log.d("AudioTrack", "[Performance]AudioTrack init success: , Config(" + this.sampleRate + ", " + this.channelConfig + ", " + this.bufferSize + ")");
            return;
        }
        try {
            this.audioTrack.release();
            MtkDebugLogUtil.log("AudioTrack", "audioTrack.release()", 2);
        } catch (Exception unused) {
        } catch (Throwable th) {
            this.audioTrack = null;
            throw th;
        }
        this.audioTrack = null;
        throw new InitializationException(state, this.sampleRate, this.channelConfig, this.bufferSize);
    }

    private long pcmBytesToFrames(long j) {
        return j / ((long) this.pcmFrameSize);
    }

    private long framesToDurationUs(long j) {
        return (j * 1000000) / ((long) this.sampleRate);
    }

    private long durationUsToFrames(long j) {
        return (j * ((long) this.sampleRate)) / 1000000;
    }

    private long getSubmittedFrames() {
        return this.passthrough ? this.submittedEncodedFrames : pcmBytesToFrames(this.submittedPcmBytes);
    }

    private void resetSyncParams() {
        this.smoothedPlayheadOffsetUs = 0L;
        this.playheadOffsetCount = 0;
        this.nextPlayheadOffsetIndex = 0;
        this.lastPlayheadSampleTimeUs = 0L;
        this.audioTimestampSet = false;
        this.lastTimestampSampleTimeUs = 0L;
        this.lastAppendedPresentTimeUs = -1L;
        this.lastAppendedSize = 0;
        this.encodedFrameCountInBuffer = 0;
    }

    private boolean needsPassthroughWorkarounds() {
        int i;
        return Util.SDK_INT < 23 && ((i = this.targetEncoding) == 5 || i == 6);
    }

    private boolean overrideHasPendingData() {
        return needsPassthroughWorkarounds() && this.audioTrack.getPlayState() == 2 && this.audioTrack.getPlaybackHeadPosition() == 0;
    }

    private ByteBuffer resampleTo16BitPcm(ByteBuffer byteBuffer, int i, int i2, int i3, ByteBuffer byteBuffer2) {
        int length;
        if (i3 == Integer.MIN_VALUE) {
            length = (i2 / 3) * 2;
        } else if (i3 == 3) {
            length = i2 * 2;
        } else if (i3 == 1073741824) {
            length = i2 / 2;
        } else {
            throw new IllegalStateException();
        }
        byte[] bArr = this.remainingBytesArray;
        if (bArr != null) {
            length += bArr.length;
        }
        if (byteBuffer2 == null || byteBuffer2.capacity() < length) {
            byteBuffer2 = ByteBuffer.allocateDirect(length);
        }
        byteBuffer2.position(0);
        byteBuffer2.limit(length);
        byte[] bArr2 = this.remainingBytesArray;
        if (bArr2 != null) {
            byteBuffer2.put(bArr2);
            this.remainingBytesArray = null;
        }
        int i4 = i2 + i;
        if (i3 == Integer.MIN_VALUE) {
            while (i < i4) {
                byteBuffer2.put(byteBuffer.get(i + 1));
                byteBuffer2.put(byteBuffer.get(i + 2));
                i += 3;
            }
        } else if (i3 == 3) {
            while (i < i4) {
                byteBuffer2.put((byte) 0);
                byteBuffer2.put((byte) ((byteBuffer.get(i) & 255) - 128));
                i++;
            }
        } else {
            if (i3 != 1073741824) {
                throw new IllegalStateException();
            }
            while (i < i4) {
                byteBuffer2.put(byteBuffer.get(i + 2));
                byteBuffer2.put(byteBuffer.get(i + 3));
                i += 4;
            }
        }
        byteBuffer2.position(0);
        return byteBuffer2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:35:0x0075  */
    static int getEncodingForMimeType(String str, int i) {
        switch (str) {
            case "audio/ac3":
                return 5;
            case "audio/eac3":
                return 6;
            case "audio/vnd.dts":
                return 7;
            case "audio/vnd.dts.hd":
            case "audio/vnd.dts.hd;profile=lbr":
                return 8;
            case "audio/ac4":
            case "audio/ac4-joc":
                return C.ENCODING_AC4;
            case "audio/mp4a-latm":
                if (i == 5) {
                    return 11;
                }
                return i == 29 ? 12 : 10;
            case "audio/eac3-joc":
                return 6;
            case "audio/mpeg-h":
                return 19;
            default:
                return 0;
        }
    }

    private static int getFramesPerEncodedSample(int i, ByteBuffer byteBuffer) {
        if (i == 5) {
            return Ac3Util.getAc3SyncframeAudioSampleCount();
        }
        if (i == 6 || i == 18) {
            return Ac3Util.parseEAc3SyncframeAudioSampleCount(byteBuffer);
        }
        if (i == C.ENCODING_AC4) {
            byte[] bArr = new byte[byteBuffer.limit()];
            byteBuffer.duplicate().get(bArr);
            return Ac4Util.getAc4SyncframeAudioSampleCount(bArr);
        }
        throw new IllegalStateException("Unexpected audio encoding: " + i);
    }

    private static int getAc4FramerateIndex(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.limit()];
        byteBuffer.duplicate().get(bArr);
        return Ac4Util.getAc4FramerateIndex(bArr);
    }

    @TargetApi(21)
    private static int writeNonBlockingV21(android.media.AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
        return audioTrack.write(byteBuffer, i, 1);
    }

    @TargetApi(21)
    private static void setAudioTrackVolumeV21(android.media.AudioTrack audioTrack, float f) {
        audioTrack.setVolume(f);
    }

    private static void setAudioTrackVolumeV3(android.media.AudioTrack audioTrack, float f) {
        audioTrack.setStereoVolume(f, f);
    }

    private static class AudioTrackUtil {
        protected android.media.AudioTrack audioTrack;
        private long elapsedTimeSincePauseUs;
        private long endPlaybackHeadPosition;
        private long lastRawPlaybackHeadPosition;
        private Lock mAudioTrackLock;
        private boolean needsPassthroughWorkaround;
        private long passthroughWorkaroundPauseOffset;
        private long pauseTimestampUs;
        private long playTimestampUs;
        private long rawPlaybackHeadWrapCount;
        private int sampleRate;
        private long stopPlaybackHeadPosition;
        private long stopTimestampUs;

        public float getPlaybackSpeed() {
            return 1.0f;
        }

        public void setAc4FramerateIndex(int i) {
        }

        public boolean updateTimestamp() {
            return false;
        }

        private AudioTrackUtil() {
            this.pauseTimestampUs = 0L;
            this.playTimestampUs = 0L;
            this.elapsedTimeSincePauseUs = 0L;
            this.mAudioTrackLock = new ReentrantLock();
        }

        public void reconfigure(android.media.AudioTrack audioTrack, boolean z) {
            this.mAudioTrackLock.lock();
            this.audioTrack = audioTrack;
            this.needsPassthroughWorkaround = z;
            this.stopTimestampUs = -1L;
            this.lastRawPlaybackHeadPosition = 0L;
            this.rawPlaybackHeadWrapCount = 0L;
            this.passthroughWorkaroundPauseOffset = 0L;
            this.pauseTimestampUs = 0L;
            this.playTimestampUs = 0L;
            this.elapsedTimeSincePauseUs = 0L;
            if (audioTrack != null) {
                this.sampleRate = audioTrack.getSampleRate();
            }
            this.mAudioTrackLock.unlock();
        }

        public void handleEndOfStream(long j) {
            this.stopPlaybackHeadPosition = getPlaybackHeadPosition();
            this.stopTimestampUs = SystemClock.elapsedRealtime() * 1000;
            this.endPlaybackHeadPosition = j;
            this.audioTrack.stop();
            MtkDebugLogUtil.log("AudioTrack", "audioTrack.stop()", 2);
        }

        public void pause() {
            if (this.stopTimestampUs != -1) {
                this.pauseTimestampUs = SystemClock.elapsedRealtime() * 1000;
                Log.d("AudioTrack", "play track if have received EOS, pauseTimestampUs:" + this.pauseTimestampUs);
                this.audioTrack.play();
            }
            this.audioTrack.pause();
            MtkDebugLogUtil.log("AudioTrack", "audioTrack.pause()", 2);
        }

        public void play() {
            this.audioTrack.play();
            if (this.stopTimestampUs != -1) {
                long jElapsedRealtime = SystemClock.elapsedRealtime() * 1000;
                this.playTimestampUs = jElapsedRealtime;
                this.elapsedTimeSincePauseUs = jElapsedRealtime - this.pauseTimestampUs;
                Log.d("AudioTrack", "stop audiotrack if have received EOS, playTimestampUs:" + this.playTimestampUs + ", pauseTimestampUs" + this.pauseTimestampUs + ", elapsedTimeSincePauseUs" + this.elapsedTimeSincePauseUs);
                this.audioTrack.stop();
            }
        }

        public long getPlaybackHeadPosition() {
            long jElapsedRealtime;
            this.mAudioTrackLock.lock();
            android.media.AudioTrack audioTrack = this.audioTrack;
            if (audioTrack == null) {
                Log.d("AudioTrack", "getPlaybackHeadPosition invalid audiotrack!");
                this.mAudioTrackLock.unlock();
                return 0L;
            }
            int playState = audioTrack.getPlayState();
            long j = this.stopTimestampUs;
            if (j != -1) {
                if (playState == 2) {
                    jElapsedRealtime = this.pauseTimestampUs - j;
                } else {
                    jElapsedRealtime = ((SystemClock.elapsedRealtime() * 1000) - this.elapsedTimeSincePauseUs) - this.stopTimestampUs;
                }
                long j2 = (jElapsedRealtime * ((long) this.sampleRate)) / 1000000;
                this.mAudioTrackLock.unlock();
                return Math.min(this.endPlaybackHeadPosition, this.stopPlaybackHeadPosition + j2);
            }
            if (playState == 1) {
                this.mAudioTrackLock.unlock();
                return 0L;
            }
            long playbackHeadPosition = ((long) this.audioTrack.getPlaybackHeadPosition()) & 4294967295L;
            if (this.needsPassthroughWorkaround) {
                if (playState == 2 && playbackHeadPosition == 0) {
                    this.passthroughWorkaroundPauseOffset = this.lastRawPlaybackHeadPosition;
                }
                playbackHeadPosition += this.passthroughWorkaroundPauseOffset;
            }
            if (this.lastRawPlaybackHeadPosition > playbackHeadPosition) {
                if (playbackHeadPosition == 0 && this.audioTrack.getPlaybackHeadPosition() == 0 && this.lastRawPlaybackHeadPosition + 1098383360 < 4294967295L) {
                    Log.w("AudioTrack", "AudioTrack is very likely dead. Do not hanle this position over flow");
                } else {
                    this.rawPlaybackHeadWrapCount++;
                }
            }
            this.lastRawPlaybackHeadPosition = playbackHeadPosition;
            this.mAudioTrackLock.unlock();
            return playbackHeadPosition + (this.rawPlaybackHeadWrapCount << 32);
        }

        public long getPlaybackHeadPositionUs() {
            return (getPlaybackHeadPosition() * 1000000) / ((long) this.sampleRate);
        }

        public long getTimestampNanoTime() {
            throw new UnsupportedOperationException();
        }

        public long getTimestampFramePosition() {
            throw new UnsupportedOperationException();
        }

        public void setPlaybackParameters(PlaybackParams playbackParams) {
            throw new UnsupportedOperationException();
        }
    }

    @TargetApi(19)
    private static class AudioTrackUtilV19 extends AudioTrackUtil {
        private final AudioTimestamp audioTimestamp;
        private long lastRawTimestampFramePosition;
        private long lastTimestampFramePosition;
        private long rawTimestampFramePositionWrapCount;

        public AudioTrackUtilV19() {
            super();
            this.audioTimestamp = new AudioTimestamp();
        }

        @Override // com.google.android.exoplayer.audio.AudioTrack.AudioTrackUtil
        public void reconfigure(android.media.AudioTrack audioTrack, boolean z) {
            super.reconfigure(audioTrack, z);
            this.rawTimestampFramePositionWrapCount = 0L;
            this.lastRawTimestampFramePosition = 0L;
            this.lastTimestampFramePosition = 0L;
        }

        @Override // com.google.android.exoplayer.audio.AudioTrack.AudioTrackUtil
        public boolean updateTimestamp() {
            boolean timestamp = this.audioTrack.getTimestamp(this.audioTimestamp);
            if (timestamp) {
                long j = this.audioTimestamp.framePosition;
                if (this.lastRawTimestampFramePosition > j) {
                    this.rawTimestampFramePositionWrapCount++;
                }
                this.lastRawTimestampFramePosition = j;
                this.lastTimestampFramePosition = j + (this.rawTimestampFramePositionWrapCount << 32);
            }
            return timestamp;
        }

        @Override // com.google.android.exoplayer.audio.AudioTrack.AudioTrackUtil
        public long getTimestampNanoTime() {
            return this.audioTimestamp.nanoTime;
        }

        @Override // com.google.android.exoplayer.audio.AudioTrack.AudioTrackUtil
        public long getTimestampFramePosition() {
            return this.lastTimestampFramePosition;
        }
    }

    @TargetApi(23)
    private static class AudioTrackUtilV23 extends AudioTrackUtilV19 {
        private PlaybackParams playbackParams;
        private float playbackSpeed = 1.0f;

        @Override // com.google.android.exoplayer.audio.AudioTrack.AudioTrackUtilV19, com.google.android.exoplayer.audio.AudioTrack.AudioTrackUtil
        public void reconfigure(android.media.AudioTrack audioTrack, boolean z) {
            super.reconfigure(audioTrack, z);
            maybeApplyPlaybackParams();
        }

        @Override // com.google.android.exoplayer.audio.AudioTrack.AudioTrackUtil
        public void setPlaybackParameters(PlaybackParams playbackParams) {
            if (playbackParams == null) {
                playbackParams = new PlaybackParams();
            }
            PlaybackParams playbackParamsAllowDefaults = playbackParams.allowDefaults();
            this.playbackParams = playbackParamsAllowDefaults;
            this.playbackSpeed = playbackParamsAllowDefaults.getSpeed();
            maybeApplyPlaybackParams();
        }

        @Override // com.google.android.exoplayer.audio.AudioTrack.AudioTrackUtil
        public float getPlaybackSpeed() {
            return this.playbackSpeed;
        }

        private void maybeApplyPlaybackParams() {
            PlaybackParams playbackParams;
            android.media.AudioTrack audioTrack = this.audioTrack;
            if (audioTrack == null || (playbackParams = this.playbackParams) == null) {
                return;
            }
            audioTrack.setPlaybackParams(playbackParams);
        }
    }

    public void setTunnelingAudioSessionId(int i) {
        this.tunnelingAudioSessionId = i;
    }

    @TargetApi(21)
    private void setupTunnelingAudioTrack() {
        Log.d("AudioTrack", "setup media tunneling, tunnelingAudioSessionId = " + this.tunnelingAudioSessionId);
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setFlags(16);
        builder.setUsage(1);
        builder.setContentType(3);
        AudioFormat.Builder builder2 = new AudioFormat.Builder();
        builder2.setChannelMask(this.channelConfig);
        builder2.setEncoding(this.targetEncoding);
        builder2.setSampleRate(this.sampleRate);
        this.audioTrack = new android.media.AudioTrack(builder.build(), builder2.build(), this.bufferSize, 1, this.tunnelingAudioSessionId);
    }

    private void appendTunnelingHeader(ByteBuffer byteBuffer, long j) {
        int i = this.bufferBytesRemaining;
        if (this.needDownMix) {
            this.bufferBytesRemaining = (i * 2) / this.sourceChannelCount;
        }
        int i2 = this.bufferBytesRemaining + 16;
        this.bufferBytesRemaining = i2;
        byte[] bArr = this.remainingBytesArray;
        if (bArr != null && bArr.length > 0) {
            this.bufferBytesRemaining = i2 + bArr.length;
        }
        if (this.tunnelingBuffer == null) {
            this.tunnelingBuffer = ByteBuffer.allocate(this.bufferBytesRemaining);
        }
        int iLimit = this.tunnelingBuffer.limit();
        int i3 = this.bufferBytesRemaining;
        if (iLimit < i3) {
            this.tunnelingBuffer = ByteBuffer.allocate(i3);
        }
        this.tunnelingBuffer.clear();
        byte[] bArr2 = this.remainingBytesArray;
        if (bArr2 != null && bArr2.length > 0) {
            this.tunnelingBuffer.put(bArr2);
            this.remainingBytesArray = null;
        }
        int i4 = this.needDownMix ? (i * 2) / this.sourceChannelCount : i;
        if (!this.passthrough) {
            long j2 = this.lastAppendedPresentTimeUs;
            if (j2 != -1) {
                long jFramesToDurationUs = j2 + framesToDurationUs(pcmBytesToFrames(this.lastAppendedSize));
                if (j == this.lastPresentationTimeUs || jFramesToDurationUs >= j || j - jFramesToDurationUs < 50000) {
                    MtkDebugLogUtil.log("AudioTrack", "appendTunnelingHeader: adjustedTimeUs = " + jFramesToDurationUs, 2);
                    j = jFramesToDurationUs;
                }
            }
        }
        this.lastAppendedSize = i4;
        this.lastAppendedPresentTimeUs = j;
        this.tunnelingBuffer.put((byte) 85);
        this.tunnelingBuffer.put((byte) 85);
        this.tunnelingBuffer.put((byte) 0);
        this.tunnelingBuffer.put((byte) 1);
        for (int i5 = 0; i5 < 4; i5++) {
            this.tunnelingBuffer.put((byte) (((-16777216) & i4) >> 24));
            i4 <<= 8;
        }
        long j3 = j * 1000;
        for (int i6 = 0; i6 < 8; i6++) {
            this.tunnelingBuffer.put((byte) (((-72057594037927936L) & j3) >> 56));
            j3 <<= 8;
        }
        if (this.needDownMix) {
            Downmixer.downmix5point1ToStereo(byteBuffer, this.tunnelingBuffer, i / (this.sourceChannelCount * 2));
        } else {
            this.tunnelingBuffer.put(byteBuffer);
        }
        this.tunnelingBuffer.position(0);
    }

    private void resetAc3() {
        this.ac3Initialized = false;
        Log.d("AudioTrack", "resetAc3");
    }

    private void initializeAc3() {
        if (this.ac3Initialized) {
            return;
        }
        MtkDebugLogUtil.log("AudioTrack", "initAc3", 2);
        this.ac3Header = new byte[6];
        this.ac3HeaderPointer = 0;
        this.ac3SyncframeSize = 0;
        this.ac3ProcessingStatus = 0;
        this.ac3Buffer = ByteBuffer.allocate(0);
        this.ac3Initialized = true;
        this.ac3SyncframeCount = 0;
        this.skipBytesCounter = 0;
        this.skipFrameCounter = 0;
    }

    private boolean isAc3Initialized() {
        return this.ac3Initialized;
    }

    /* JADX WARN: Code duplicated, block: B:106:0x0311  */
    /* JADX WARN: Code duplicated, block: B:39:0x00c2  */
    private int ac3BufferProcess(ByteBuffer byteBuffer, int i) throws WriteException {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int[] iArr = AC3_FSCOD_SAMPLE_RATE;
        MtkDebugLogUtil.log("AudioTrack", "ac3BufferProcess buffder.position=" + byteBuffer.position() + ", buffer.limit=" + byteBuffer.limit() + ", size=" + i, 2);
        if (this.ac3ProcessingStatus == 4) {
            this.ac3HeaderPointer = 0;
            this.ac3SyncframeSize = 0;
            this.ac3Buffer.clear();
            this.ac3ProcessingStatus = 0;
        }
        char c = 'w';
        int i7 = 8;
        int i8 = 3;
        if (this.ac3ProcessingStatus == 0) {
            while (byteBuffer.position() < i && (i5 = this.ac3HeaderPointer) < 6) {
                this.ac3Header[i5] = byteBuffer.get();
                int i9 = this.ac3HeaderPointer;
                if (i9 == 0 && (this.ac3Header[i9] & 255) == 11) {
                    this.ac3HeaderPointer = 1;
                } else {
                    int i10 = this.ac3HeaderPointer;
                    if (i10 == 1) {
                        if ((this.ac3Header[i10] & 255) == c) {
                            this.ac3HeaderPointer = 2;
                        } else {
                            this.ac3HeaderPointer = 0;
                        }
                    } else if (i10 > 1) {
                        if (i10 == 5) {
                            int i11 = (this.ac3Header[i10] & 255) >> 3;
                            if (!"audio/ac3".equals(this.mimeType) || i11 > i7) {
                                if (!"audio/eac3".equals(this.mimeType) || i11 < 6) {
                                    i6 = 16;
                                } else {
                                    i6 = 16;
                                    if (i11 <= 16) {
                                        this.ac3HeaderPointer++;
                                    }
                                }
                                if (!"audio/eac3-joc".equals(this.mimeType) || i11 < 11 || i11 > i6) {
                                    this.ac3HeaderPointer = 0;
                                } else {
                                    this.ac3HeaderPointer++;
                                }
                            } else {
                                this.ac3HeaderPointer++;
                            }
                        } else {
                            this.ac3HeaderPointer = i10 + 1;
                        }
                    }
                }
                if (this.ac3HeaderPointer == 0) {
                    int i12 = this.skipBytesCounter + 1;
                    this.skipBytesCounter = i12;
                    if (i12 % 100 == 0) {
                        MtkDebugLogUtil.log("AudioTrack", "skipBytesCounter = " + this.skipBytesCounter, 2);
                    }
                    if (this.skipBytesCounter == 49152) {
                        throw new WriteException(-1001);
                    }
                }
                c = 'w';
                i7 = 8;
            }
            if (this.ac3HeaderPointer == 6) {
                this.ac3ProcessingStatus = 1;
                this.skipBytesCounter = 0;
            }
        }
        if (this.ac3ProcessingStatus == 1) {
            this.ac3SyncframeCount++;
            int i13 = (this.ac3Header[5] & 255) >> 3;
            MtkDebugLogUtil.log("AudioTrack", "bsid = " + i13, 2);
            try {
                if (i13 <= 8) {
                    int i14 = this.ac3Header[4] & 255;
                    int i15 = AC3_SYNCFRAME_SIZE_TABLE[i14] * 2;
                    this.ac3SyncframeSize = i15;
                    this.ac3Bitrate = AC3_BITRATE_TABLE[(i14 & 63) >> 1];
                    this.ac3SyncframeRemain = i15 - 6;
                    this.framesPerEncodedSample = Ac3Util.getAc3SyncframeAudioSampleCount();
                    MtkDebugLogUtil.log("AudioTrack", " ac-3: ac3SyncframeSize = " + this.ac3SyncframeSize + ", ac3Bitrate = " + this.ac3Bitrate + ", framesPerEncodedSample = " + this.framesPerEncodedSample + ", ac3SyncframeCount = " + this.ac3SyncframeCount, 2);
                } else if (i13 >= 11 && i13 <= 16) {
                    int i16 = (this.ac3Header[2] & 192) >> 6;
                    int i17 = (this.ac3Header[2] & 56) >> 3;
                    int i18 = ((this.ac3Header[2] & 7) << 8) + (this.ac3Header[3] & 255) + 1;
                    int i19 = (this.ac3Header[4] & 192) >> 6;
                    if (i19 == 3) {
                        this.sampleRate = iArr[(this.ac3Header[4] & 48) >> 4] / 2;
                    } else {
                        i8 = (this.ac3Header[4] & 48) >> 4;
                        this.sampleRate = iArr[i19];
                    }
                    int i20 = AC3_NUMBER_OF_BLOCK[i8] * 256;
                    int i21 = i18 * 2;
                    this.ac3SyncframeSize = i21;
                    this.ac3Bitrate = (((i18 * this.sampleRate) * 16) / i20) / 1000;
                    this.ac3SyncframeRemain = i21 - 6;
                    if (byteBuffer.position() + this.ac3SyncframeRemain + 6 < i) {
                        int iPosition = byteBuffer.position() + this.ac3SyncframeRemain;
                        if ((byteBuffer.get(iPosition) & 255) == 11 && (byteBuffer.get(iPosition + 1) & 255) == 119) {
                            int i22 = iPosition + 2;
                            if (((byteBuffer.get(i22) & 192) >> 6) == 1) {
                                int i23 = (((byteBuffer.get(i22) & 7) << 8) + (byteBuffer.get(iPosition + 3) & 255) + 1) * 2;
                                this.ac3SyncframeRemain += i23;
                                this.ac3SyncframeSize += i23;
                                MtkDebugLogUtil.log("AudioTrack", "Dependent substreams detected, startPos = " + iPosition + ", frameSize=" + i23, 2);
                            }
                        }
                    }
                    this.framesPerEncodedSample = Ac3Util.parseEAc3SyncframeAudioSampleCount(this.ac3Header);
                    MtkDebugLogUtil.log("AudioTrack", " e-ac-3: ac3SyncframeSize = " + this.ac3SyncframeSize + ", ac3Bitrate = " + this.ac3Bitrate + ", framesPerEncodedSample = " + this.framesPerEncodedSample + ", ac3SyncframeCount = " + this.ac3SyncframeCount + ", strmType=" + i16 + ", subStreamId=" + i17, 2);
                } else {
                    i4 = -1001;
                    try {
                        throw new WriteException(-1001);
                    } catch (ArrayIndexOutOfBoundsException unused) {
                        throw new WriteException(i4);
                    }
                }
                if (this.ac3SyncframeSize < this.ac3Header.length) {
                    this.ac3ProcessingStatus = 4;
                    return -1;
                }
                int iLimit = this.ac3Buffer.limit();
                int i24 = this.ac3SyncframeSize;
                if (iLimit < i24) {
                    this.ac3Buffer = ByteBuffer.allocate(i24);
                }
                if (this.ac3Buffer.position() == 0) {
                    this.ac3Buffer.put(this.ac3Header);
                }
                this.ac3ProcessingStatus = 2;
            } catch (ArrayIndexOutOfBoundsException unused2) {
                i4 = -1001;
            }
        }
        if (this.ac3ProcessingStatus == 2) {
            while (byteBuffer.position() < i && this.ac3SyncframeRemain > 0) {
                this.ac3Buffer.put(byteBuffer.get());
                this.ac3SyncframeRemain--;
            }
            if (this.ac3SyncframeRemain == 0) {
                i2 = 3;
                this.ac3ProcessingStatus = 3;
                i3 = 0;
                this.ac3Buffer.position(0);
            } else {
                i2 = 3;
                i3 = 0;
            }
        } else {
            i2 = 3;
            i3 = 0;
        }
        if (this.ac3ProcessingStatus == i2) {
            return i3;
        }
        return -1;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0091  */
    public void configure(String str, MediaFormat mediaFormat, int i, int i2) {
        int integer;
        if (mediaFormat.containsKey("aac-profile")) {
            this.aacProfile = mediaFormat.getInteger("aac-profile");
        }
        int integer2 = mediaFormat.getInteger("channel-count");
        int integer3 = mediaFormat.getInteger("sample-rate");
        this.tunnelingAudioSessionId = i2;
        if ("audio/mpeg-h".equals(str)) {
            integer2 = 2;
        }
        int i3 = integer2;
        if ("audio/ac3".equals(str) || "audio/eac3".equals(str) || "audio/eac3-joc".equals(str) || "audio/vnd.dts.hd;profile=lbr".equals(str) || "audio/vnd.dts".equals(str) || "audio/vnd.dts.hd".equals(str)) {
            Log.i("AudioTrack", "mimeType[" + str + "] need to re-calculate audiotrack buffer size");
            if (mediaFormat.containsKey("bitrate")) {
                integer = (int) (((((long) ((mediaFormat.getInteger("bitrate") / 8192) + 32)) * 250000) * 1024) / 1000000);
            } else {
                integer = 0;
            }
        } else {
            integer = 0;
        }
        Log.d("AudioTrack", "configure mimeType:" + str + ", channelCount:" + i3 + ", sampleRate:" + integer3 + ", pcmEncoding:" + i + ", specifiedBufferSize:" + integer + ", tunnelingAudioSessionId:" + i2);
        configure(str, i3, integer3, i, integer);
    }

    static int mapChannelCountToMask(int i, String str) {
        switch (i) {
            case 1:
                return 4;
            case 2:
                return 12;
            case 3:
                return 28;
            case 4:
                return MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CAD_ACQUISITION_ERROR;
            case 5:
                if (str.equals("audio/ac3") || str.equals("audio/eac3")) {
                    return 252;
                }
                return MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CAD_INVALID;
            case 6:
                return 252;
            case 7:
                return 1276;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                return C.CHANNEL_OUT_7POINT1_SURROUND;
            default:
                throw new IllegalArgumentException("Unsupported channel count: " + i);
        }
    }
}
