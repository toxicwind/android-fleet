package com.google.android.exoplayer;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.TraceUtil;
import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public abstract class MediaCodecTrackRenderer extends SampleSourceTrackRenderer {
    private ByteBuffer aacBuffer;
    private LinkedBlockingQueue<Integer> availableOutputBufferIds;
    private LinkedBlockingQueue<MediaCodec.BufferInfo> availableOutputBufferInfos;
    private MediaCodec codec;
    public final CodecCounters codecCounters;
    private long codecHotswapTimeMs;
    private boolean codecIsAdaptive;
    private boolean codecNeedsAdaptationWorkaround;
    private boolean codecNeedsAdaptationWorkaroundBuffer;
    private boolean codecNeedsDiscardToSpsWorkaround;
    private boolean codecNeedsEosFlushWorkaround;
    private boolean codecNeedsEosOutputExceptionWorkaround;
    private boolean codecNeedsEosPropagationWorkaround;
    private boolean codecNeedsFlushWorkaround;
    private boolean codecNeedsMonoChannelCountWorkaround;
    private boolean codecReceivedBuffers;
    private boolean codecReceivedEos;
    private int codecReconfigurationState;
    private boolean codecReconfigured;
    private int codecReinitializationState;
    private final List<Long> decodeOnlyPresentationTimestamps;
    private final boolean deviceNeedsAutoFrcWorkaround;
    private boolean drainedFirstOutputBuffer;
    private DrmInitData drmInitData;
    private DrmSessionManager<FrameworkMediaCrypto> drmSessionManager;
    protected final Handler eventHandler;
    private final EventListener eventListener;
    private boolean feededFirstInputBuffer;
    protected MediaFormat format;
    private final MediaFormatHolder formatHolder;
    private boolean[] inbandDataToDecoderForTrick;
    private ByteBuffer[] inputBuffers;
    private int inputIndex;
    private boolean inputStreamEnded;
    private ExoPlayerImplInternal internalPlayer;
    private boolean isTunnelingVideo;
    private boolean justResubmitCsdData;
    private MediaCodecCallback mediaCodecCallback;
    private final MediaCodecSelector mediaCodecSelector;
    protected boolean needResubmitCsdData;
    private boolean openedDrmSession;
    private ByteBuffer outputBuffer;
    private MediaCodec.BufferInfo outputBufferInfo;
    private ByteBuffer[] outputBuffers;
    protected boolean outputFormatChanged;
    private int outputIndex;
    private boolean outputStreamEnded;
    private final boolean playClearSamplesWithoutKeys;
    private final SampleHolder sampleHolder;
    private int seekFlag;
    private boolean shouldSkipAdaptationWorkaroundOutputBuffer;
    private int sourceState;
    private int tunnelingAudioSessionId;
    private boolean waitingForFirstSyncFrame;
    private boolean waitingForKeys;
    private static final byte[] ADAPTATION_WORKAROUND_BUFFER = Util.getBytesFromHexString("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    private static int AAC_FAKE_HEADER_SIZE = 7;

    public interface EventListener {
        void onCryptoError(MediaCodec.CryptoException cryptoException);

        void onDecoderInitializationError(DecoderInitializationException decoderInitializationException);

        void onDecoderInitialized(String str, long j, long j2);
    }

    protected boolean canReconfigureCodec(MediaCodec mediaCodec, boolean z, MediaFormat mediaFormat, MediaFormat mediaFormat2) {
        return false;
    }

    protected abstract void configureCodec(MediaCodec mediaCodec, boolean z, android.media.MediaFormat mediaFormat, MediaCrypto mediaCrypto);

    protected long getDequeueOutputBufferTimeoutUs() {
        return 0L;
    }

    protected abstract boolean handlesTrack(MediaCodecSelector mediaCodecSelector, MediaFormat mediaFormat) throws MediaCodecUtil.DecoderQueryException;

    protected abstract boolean isTunneledSupported(MediaFormat mediaFormat);

    protected void onOutputFormatChanged(MediaCodec mediaCodec, android.media.MediaFormat mediaFormat) throws ExoPlaybackException {
    }

    protected void onOutputStreamEnded() {
    }

    protected void onProcessedOutputBuffer(long j) {
    }

    protected void onQueuedInputBuffer(long j, ByteBuffer byteBuffer, int i, boolean z) {
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void onStarted() {
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void onStopped() {
    }

    protected abstract boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo, int i, boolean z) throws ExoPlaybackException;

    public static class DecoderInitializationException extends Exception {
        public final String decoderName;
        public final String diagnosticInfo;
        public final String mimeType;
        public final boolean secureDecoderRequired;

        public DecoderInitializationException(MediaFormat mediaFormat, Throwable th, boolean z, int i) {
            super("Decoder init failed: [" + i + "], " + mediaFormat, th);
            this.mimeType = mediaFormat.mimeType;
            this.secureDecoderRequired = z;
            this.decoderName = null;
            this.diagnosticInfo = buildCustomDiagnosticInfo(i);
        }

        public DecoderInitializationException(MediaFormat mediaFormat, Throwable th, boolean z, String str) {
            super("Decoder init failed: " + str + ", " + mediaFormat, th);
            this.mimeType = mediaFormat.mimeType;
            this.secureDecoderRequired = z;
            this.decoderName = str;
            this.diagnosticInfo = Util.SDK_INT >= 21 ? getDiagnosticInfoV21(th) : null;
        }

        @TargetApi(21)
        private static String getDiagnosticInfoV21(Throwable th) {
            if (th instanceof MediaCodec.CodecException) {
                return ((MediaCodec.CodecException) th).getDiagnosticInfo();
            }
            return null;
        }

        private static String buildCustomDiagnosticInfo(int i) {
            return "com.google.android.exoplayer.MediaCodecTrackRenderer_" + (i < 0 ? "neg_" : "") + Math.abs(i);
        }
    }

    public MediaCodecTrackRenderer(SampleSource sampleSource, MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, Handler handler, EventListener eventListener) {
        this(new SampleSource[]{sampleSource}, mediaCodecSelector, drmSessionManager, z, handler, eventListener);
    }

    public MediaCodecTrackRenderer(SampleSource[] sampleSourceArr, MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, Handler handler, EventListener eventListener) {
        super(sampleSourceArr);
        this.justResubmitCsdData = false;
        this.outputFormatChanged = false;
        this.needResubmitCsdData = false;
        this.inbandDataToDecoderForTrick = new boolean[]{false, false};
        Assertions.checkState(Util.SDK_INT >= 16);
        Assertions.checkNotNull(mediaCodecSelector);
        this.mediaCodecSelector = mediaCodecSelector;
        this.drmSessionManager = drmSessionManager;
        this.playClearSamplesWithoutKeys = z;
        this.eventHandler = handler;
        this.eventListener = eventListener;
        this.deviceNeedsAutoFrcWorkaround = deviceNeedsAutoFrcWorkaround();
        this.codecCounters = new CodecCounters();
        this.sampleHolder = new SampleHolder(0);
        this.formatHolder = new MediaFormatHolder();
        this.decodeOnlyPresentationTimestamps = new ArrayList();
        this.outputBufferInfo = new MediaCodec.BufferInfo();
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
        this.tunnelingAudioSessionId = -1;
        this.seekFlag = 0;
        this.availableOutputBufferIds = new LinkedBlockingQueue<>();
        this.availableOutputBufferInfos = new LinkedBlockingQueue<>();
        this.outputBuffer = null;
        this.aacBuffer = ByteBuffer.allocate(0);
        this.feededFirstInputBuffer = false;
        this.drainedFirstOutputBuffer = false;
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected final boolean handlesTrack(MediaFormat mediaFormat) throws MediaCodecUtil.DecoderQueryException {
        return handlesTrack(this.mediaCodecSelector, mediaFormat);
    }

    protected DecoderInfo getDecoderInfo(MediaCodecSelector mediaCodecSelector, String str, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return mediaCodecSelector.getDecoderInfo(str, z);
    }

    protected final void maybeInitCodec() throws ExoPlaybackException {
        boolean zRequiresSecureDecoderComponent;
        MediaCrypto wrappedMediaCrypto;
        DecoderInfo decoderInfo;
        int i;
        if (shouldInitCodec()) {
            String str = this.format.mimeType;
            DrmInitData drmInitData = this.drmInitData;
            if (drmInitData != null) {
                DrmSessionManager<FrameworkMediaCrypto> drmSessionManager = this.drmSessionManager;
                if (drmSessionManager == null) {
                    throw new ExoPlaybackException("Media requires a DrmSessionManager");
                }
                if (!this.openedDrmSession) {
                    drmSessionManager.open(drmInitData);
                    this.openedDrmSession = true;
                }
                int state = this.drmSessionManager.getState();
                if (state == 0) {
                    throw new ExoPlaybackException(this.drmSessionManager.getError());
                }
                if (state != 3 && state != 4) {
                    return;
                }
                wrappedMediaCrypto = ((FrameworkMediaCrypto) this.drmSessionManager.getMediaCrypto()).getWrappedMediaCrypto();
                zRequiresSecureDecoderComponent = this.drmSessionManager.requiresSecureDecoderComponent(str);
            } else {
                zRequiresSecureDecoderComponent = false;
                wrappedMediaCrypto = null;
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            try {
                if ((this instanceof MediaCodecAudioTrackRenderer) && "audio/mp4a-latm".equals(str)) {
                    boolean z = !this.format.getFrameworkMediaFormatV16().containsKey("is-adts") || this.format.getFrameworkMediaFormatV16().getInteger("is-adts") == 1;
                    decoderInfo = getDecoderInfo(this.mediaCodecSelector, str, zRequiresSecureDecoderComponent, !z);
                    Log.d("MediaCodecTrackRenderer", "maybeInitCodec: mimeType:" + str + " isADTS:" + z);
                } else {
                    if ("video/dolby-vision".equals(str)) {
                        if (Util.SDK_INT >= 24) {
                            String strFindDecoderForFormat = MediaCodecUtil.findDecoderForFormat(this.format.getFrameworkMediaFormatV16(), zRequiresSecureDecoderComponent);
                            if (strFindDecoderForFormat != null) {
                                str = str + "#" + strFindDecoderForFormat;
                            } else {
                                notifyAndThrowDecoderInitError(new DecoderInitializationException(this.format, (Throwable) null, zRequiresSecureDecoderComponent, -49999));
                                throw null;
                            }
                        } else {
                            notifyAndThrowDecoderInitError(new DecoderInitializationException(this.format, (Throwable) null, zRequiresSecureDecoderComponent, -49999));
                            throw null;
                        }
                    }
                    decoderInfo = getDecoderInfo(this.mediaCodecSelector, str, zRequiresSecureDecoderComponent);
                }
                String str2 = str;
                Log.d("MediaCodecTrackRenderer", "[Performance]getDecoderInfo() costTimeMs=" + (SystemClock.elapsedRealtime() - jElapsedRealtime));
                if (decoderInfo == null) {
                    notifyAndThrowDecoderInitError(new DecoderInitializationException(this.format, (Throwable) null, zRequiresSecureDecoderComponent, -49999));
                    throw null;
                }
                boolean z2 = this instanceof MediaCodecVideoTrackRenderer;
                if (z2) {
                    if ("video/avc".equals(this.format.mimeType) || "video/hevc".equals(this.format.mimeType) || "video/x-vnd.on2.vp9".equals(this.format.mimeType) || "video/av01".equals(this.format.mimeType)) {
                        MediaCodecInfo.VideoCapabilities videoCapabilities = decoderInfo.capabilities.getVideoCapabilities();
                        if (!videoCapabilities.getSupportedWidths().contains(Integer.valueOf(this.format.width)) || !videoCapabilities.getSupportedHeights().contains(Integer.valueOf(this.format.height))) {
                            Log.w("MediaCodecTrackRenderer", "Mainstream video resolution (" + this.format.width + "x" + this.format.height + ") is beyond the capbilities[" + decoderInfo.name + "]: w" + videoCapabilities.getSupportedWidths().toString() + ", h" + videoCapabilities.getSupportedHeights().toString());
                            throw new ExoPlaybackException(new Exception("vdec beyond capabilities").getCause(), 0);
                        }
                    } else {
                        MediaFormat mediaFormat = this.format;
                        int i2 = mediaFormat.width;
                        if (i2 > 4096 || i2 < 64 || (i = mediaFormat.height) > 2176 || i < 64) {
                            Log.w("MediaCodecTrackRenderer", "Niche video resolution (" + this.format.width + "x" + this.format.height + ") is beyond the capbilities[" + decoderInfo.name + "]: w[64, 4096], h[64, 2176]");
                            throw new ExoPlaybackException(new Exception("vdec beyond capabilities").getCause(), 0);
                        }
                    }
                }
                String str3 = decoderInfo.name;
                this.codecIsAdaptive = decoderInfo.adaptive;
                this.codecNeedsDiscardToSpsWorkaround = codecNeedsDiscardToSpsWorkaround(str3, this.format);
                this.codecNeedsFlushWorkaround = codecNeedsFlushWorkaround(str3);
                this.codecNeedsAdaptationWorkaround = codecNeedsAdaptationWorkaround(str3);
                this.codecNeedsEosPropagationWorkaround = codecNeedsEosPropagationWorkaround(str3);
                this.codecNeedsEosFlushWorkaround = codecNeedsEosFlushWorkaround(str3);
                this.codecNeedsEosOutputExceptionWorkaround = codecNeedsEosOutputExceptionWorkaround(str3);
                this.codecNeedsMonoChannelCountWorkaround = codecNeedsMonoChannelCountWorkaround(str3, this.format);
                try {
                    long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                    TraceUtil.beginSection("createByCodecName(" + str3 + ")");
                    this.codec = MediaCodec.createByCodecName(str3);
                    if (isMediaCodecAsyncMode()) {
                        MediaCodecCallback mediaCodecCallback = new MediaCodecCallback(this);
                        this.mediaCodecCallback = mediaCodecCallback;
                        this.codec.setCallback(mediaCodecCallback, mediaCodecCallback.getHandler());
                    }
                    TraceUtil.endSection();
                    TraceUtil.beginSection("configureCodec");
                    configureCodec(this.codec, decoderInfo.adaptive, getFrameworkMediaFormat(this.format), wrappedMediaCrypto);
                    TraceUtil.endSection();
                    TraceUtil.beginSection("codec.start()");
                    this.codec.start();
                    TraceUtil.endSection();
                    if (!isMediaCodecAsyncMode()) {
                        this.inputBuffers = this.codec.getInputBuffers();
                        this.outputBuffers = this.codec.getOutputBuffers();
                    }
                    long jElapsedRealtime3 = SystemClock.elapsedRealtime();
                    long j = jElapsedRealtime3 - jElapsedRealtime2;
                    notifyDecoderInitialized(str3, jElapsedRealtime3, j);
                    Log.i("MediaCodecTrackRenderer", "[Performance]InitCodec, mimeType[" + str2 + "], codecName[" + str3 + "] costTimeMs=" + j);
                    this.codecHotswapTimeMs = getState() == 3 ? SystemClock.elapsedRealtime() : -1L;
                    if (!isMediaCodecAsyncMode() || z2) {
                        this.inputIndex = -1;
                        this.outputIndex = -1;
                    }
                    this.waitingForFirstSyncFrame = true;
                    this.outputFormatChanged = false;
                    this.needResubmitCsdData = false;
                    this.codecCounters.codecInitCount++;
                } catch (Exception e) {
                    notifyAndThrowDecoderInitError(new DecoderInitializationException(this.format, e, zRequiresSecureDecoderComponent, str3));
                    throw null;
                }
            } catch (MediaCodecUtil.DecoderQueryException e2) {
                notifyAndThrowDecoderInitError(new DecoderInitializationException(this.format, e2, zRequiresSecureDecoderComponent, -49998));
                throw null;
            }
        }
    }

    private void notifyAndThrowDecoderInitError(DecoderInitializationException decoderInitializationException) throws ExoPlaybackException {
        notifyDecoderInitializationError(decoderInitializationException);
        throw new ExoPlaybackException(decoderInitializationException, this instanceof MediaCodecAudioTrackRenderer ? 1 : 0);
    }

    protected boolean shouldInitCodec() {
        return this.codec == null && this.format != null;
    }

    protected final boolean codecInitialized() {
        return this.codec != null;
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onDisabled(long j) throws ExoPlaybackException {
        this.format = null;
        this.drmInitData = null;
        this.isSeeking = 0;
        this.aacBuffer = null;
        if (this instanceof MediaCodecAudioTrackRenderer) {
            this.lastSeekPositionUs = -1L;
        }
        try {
            releaseCodec();
            try {
                if (this.openedDrmSession) {
                    this.drmSessionManager.close();
                    this.openedDrmSession = false;
                }
            } finally {
                super.onDisabled(j);
            }
        } catch (Throwable th) {
            try {
                if (this.openedDrmSession) {
                    this.drmSessionManager.close();
                    this.openedDrmSession = false;
                }
                throw th;
            } finally {
                super.onDisabled(j);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void releaseCodec() {
        if (this.codec != null) {
            this.codecHotswapTimeMs = -1L;
            this.waitingForKeys = false;
            this.decodeOnlyPresentationTimestamps.clear();
            this.inputBuffers = null;
            this.outputBuffers = null;
            this.codecReconfigured = false;
            this.codecReceivedBuffers = false;
            this.justResubmitCsdData = false;
            this.codecIsAdaptive = false;
            this.codecNeedsDiscardToSpsWorkaround = false;
            this.codecNeedsFlushWorkaround = false;
            this.codecNeedsAdaptationWorkaround = false;
            this.codecNeedsEosPropagationWorkaround = false;
            this.codecNeedsEosFlushWorkaround = false;
            this.codecNeedsMonoChannelCountWorkaround = false;
            this.codecNeedsAdaptationWorkaroundBuffer = false;
            this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
            this.codecReceivedEos = false;
            this.codecReconfigurationState = 0;
            this.codecReinitializationState = 0;
            this.codecCounters.codecReleaseCount++;
            try {
                if (this.mediaCodecCallback != null) {
                    this.mediaCodecCallback.release();
                    this.availableOutputBufferIds.clear();
                    this.availableOutputBufferInfos.clear();
                }
                this.outputBuffer = null;
                this.inputIndex = -1;
                this.outputIndex = -1;
                this.codec.stop();
                try {
                    this.codec.release();
                } finally {
                    this.codec = null;
                    this.mediaCodecCallback = null;
                }
            } catch (Throwable th) {
                try {
                    this.codec.release();
                    throw th;
                } finally {
                    this.codec = null;
                    this.mediaCodecCallback = null;
                }
            }
        }
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void onDiscontinuity(long j) throws ExoPlaybackException {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        this.sourceState = 0;
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.aacBuffer = null;
        if (this.codec != null) {
            if (isMediaCodecAsyncMode()) {
                this.mediaCodecCallback.flush();
                this.availableOutputBufferIds.clear();
                this.availableOutputBufferInfos.clear();
                this.sampleHolder.data = null;
                this.outputBuffer = null;
            }
            flushCodec();
            if (isMediaCodecAsyncMode()) {
                boolean z = this instanceof MediaCodecAudioTrackRenderer;
                this.mediaCodecCallback.flushDone();
                Log.d("MediaCodecTrackRenderer", "+codec.start(), isAudio[" + z + "]");
                this.codec.start();
                Log.d("MediaCodecTrackRenderer", "-codec.start(), isAudio[" + z + "]");
            }
        }
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void doSomeWork(long j, long j2, boolean z) throws ExoPlaybackException {
        int i;
        if (z) {
            i = this.sourceState;
            if (i == 0) {
                i = 1;
            }
        } else {
            i = 0;
        }
        this.sourceState = i;
        if (this.format == null) {
            readFormat(j);
        }
        maybeInitCodec();
        if (this.codec != null) {
            TraceUtil.beginSection("drainAndFeed");
            while (drainOutputBuffer(j, j2)) {
            }
            if (!isMediaCodecAsyncMode() && feedInputBuffer(j, true)) {
                while (feedInputBuffer(j, false)) {
                }
            }
            TraceUtil.endSection();
        }
        this.codecCounters.ensureUpdated();
    }

    private void readFormat(long j) throws ExoPlaybackException {
        if (readSource(j, this.formatHolder, null) == -4) {
            onInputFormatChanged(this.formatHolder);
        }
    }

    protected void flushCodec() throws ExoPlaybackException {
        this.codecHotswapTimeMs = -1L;
        this.inputIndex = -1;
        this.outputIndex = -1;
        this.waitingForFirstSyncFrame = true;
        this.waitingForKeys = false;
        this.decodeOnlyPresentationTimestamps.clear();
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        if (this.codecNeedsFlushWorkaround || ((this.codecNeedsEosFlushWorkaround && this.codecReceivedEos) || this.codecReinitializationState != 0)) {
            releaseCodec();
            maybeInitCodec();
        } else {
            this.codec.flush();
            this.codecReceivedBuffers = false;
            this.justResubmitCsdData = false;
        }
        if (!this.codecReconfigured || this.format == null) {
            return;
        }
        this.codecReconfigurationState = 1;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0273 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:102:0x0275  */
    /* JADX WARN: Code duplicated, block: B:104:0x0279  */
    /* JADX WARN: Code duplicated, block: B:114:0x02a0  */
    /* JADX WARN: Code duplicated, block: B:116:0x02a4  */
    /* JADX WARN: Code duplicated, block: B:117:0x02ec  */
    /* JADX WARN: Code duplicated, block: B:119:0x02ef  */
    /* JADX WARN: Code duplicated, block: B:149:0x0387  */
    /* JADX WARN: Code duplicated, block: B:151:0x038c  */
    /* JADX WARN: Code duplicated, block: B:154:0x039e  */
    /* JADX WARN: Code duplicated, block: B:156:0x03a1  */
    /* JADX WARN: Code duplicated, block: B:158:0x03a6  */
    /* JADX WARN: Code duplicated, block: B:180:0x03f9  */
    /* JADX WARN: Code duplicated, block: B:182:0x03fd  */
    /* JADX WARN: Code duplicated, block: B:184:0x0405  */
    /* JADX WARN: Code duplicated, block: B:186:0x040f  */
    /* JADX WARN: Code duplicated, block: B:189:0x0417  */
    /* JADX WARN: Code duplicated, block: B:190:0x041b  */
    /* JADX WARN: Code duplicated, block: B:193:0x042a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:194:0x042b  */
    /* JADX WARN: Code duplicated, block: B:199:0x0442  */
    /* JADX WARN: Code duplicated, block: B:201:0x0448  */
    /* JADX WARN: Code duplicated, block: B:204:0x046c A[Catch: CryptoException -> 0x04ff, TRY_LEAVE, TryCatch #0 {CryptoException -> 0x04ff, blocks: (B:202:0x044b, B:204:0x046c, B:207:0x0495, B:209:0x049b, B:210:0x04a2, B:206:0x0484, B:214:0x04b7, B:216:0x04c2, B:218:0x04c6, B:220:0x04cf, B:221:0x04d4, B:222:0x04d5, B:223:0x04da, B:224:0x04db, B:225:0x04e4, B:229:0x04e8, B:230:0x04fe), top: B:239:0x044b, inners: #1, #6 }] */
    /* JADX WARN: Code duplicated, block: B:209:0x049b A[Catch: CryptoException -> 0x04ff, TryCatch #0 {CryptoException -> 0x04ff, blocks: (B:202:0x044b, B:204:0x046c, B:207:0x0495, B:209:0x049b, B:210:0x04a2, B:206:0x0484, B:214:0x04b7, B:216:0x04c2, B:218:0x04c6, B:220:0x04cf, B:221:0x04d4, B:222:0x04d5, B:223:0x04da, B:224:0x04db, B:225:0x04e4, B:229:0x04e8, B:230:0x04fe), top: B:239:0x044b, inners: #1, #6 }] */
    /* JADX WARN: Code duplicated, block: B:240:0x0484 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:116:0x02a4, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:119:0x02ef, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    private boolean feedInputBuffer(long j, boolean z) throws ExoPlaybackException {
        int source;
        int i;
        boolean z2;
        boolean zIsEncrypted;
        boolean zShouldWaitForKeys;
        int iPosition;
        int i2;
        long j2;
        boolean z3;
        int i3;
        int i4;
        MediaFormat mediaFormat;
        List<byte[]> list;
        Object[] objArr;
        if (this.inputStreamEnded || this.codecReinitializationState == 2) {
            return false;
        }
        if (isMediaCodecAsyncMode()) {
            ByteBuffer byteBuffer = this.sampleHolder.data;
            if (byteBuffer == null || byteBuffer.position() == 0) {
                try {
                    this.sampleHolder.data = this.codec.getInputBuffer(this.inputIndex);
                    this.sampleHolder.clearData();
                } catch (IllegalStateException unused) {
                    Log.w("MediaCodecTrackRenderer", "Catch codec.getInputBuffer IllegalStateException!");
                    return false;
                }
            }
        } else if (this.inputIndex < 0) {
            try {
                int iDequeueInputBuffer = this.codec.dequeueInputBuffer(0L);
                this.inputIndex = iDequeueInputBuffer;
                if (iDequeueInputBuffer < 0) {
                    return false;
                }
                SampleHolder sampleHolder = this.sampleHolder;
                sampleHolder.data = this.inputBuffers[iDequeueInputBuffer];
                sampleHolder.clearData();
            } catch (MediaCodec.CodecException e) {
                Log.e("MediaCodecTrackRenderer", "Catch MediaCodec.CodecException, error:" + e.getErrorCode());
                throw e;
            } catch (IllegalStateException e2) {
                Log.w("MediaCodecTrackRenderer", "Catch codec.dequeueInputBuffer IllegalStateException!");
                if (this instanceof MediaCodecVideoTrackRenderer) {
                    throw new ExoPlaybackException(e2, 0);
                }
                throw new ExoPlaybackException(e2, 1);
            }
        }
        if (this.codecReinitializationState == 1) {
            if (this.codecNeedsEosPropagationWorkaround) {
                objArr = false;
            } else {
                this.codecReceivedEos = true;
                this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0L, 4);
                this.inputIndex = -1;
                objArr = true;
            }
            this.codecReinitializationState = 2;
            return isMediaCodecAsyncMode() && objArr == true;
        }
        if (this.codecNeedsAdaptationWorkaroundBuffer) {
            this.codecNeedsAdaptationWorkaroundBuffer = false;
            this.sampleHolder.data.put(ADAPTATION_WORKAROUND_BUFFER);
            this.codec.queueInputBuffer(this.inputIndex, 0, ADAPTATION_WORKAROUND_BUFFER.length, 0L, 0);
            this.inputIndex = -1;
            this.codecReceivedBuffers = true;
            return true;
        }
        boolean z4 = this instanceof MediaCodecAudioTrackRenderer;
        if (!z4 && this.needResubmitCsdData && (mediaFormat = this.format) != null && (list = mediaFormat.initializationData) != null && list.size() > 0) {
            int length = 0;
            for (byte[] bArr : this.format.initializationData) {
                this.sampleHolder.data.put(bArr);
                length += bArr.length;
            }
            this.codec.queueInputBuffer(this.inputIndex, 0, length, 0L, 2);
            MtkDebugLogUtil.log("MediaCodecTrackRenderer", "Resubmit the csd data as flush before outputFormatChanged", 1);
            if (isMediaCodecAsyncMode()) {
                this.sampleHolder.data.clear();
            }
            this.inputIndex = -1;
            this.codecReceivedBuffers = true;
            this.codecReconfigurationState = 0;
            this.codecCounters.inputBufferCount++;
            this.needResubmitCsdData = false;
            this.justResubmitCsdData = true;
            return true;
        }
        if (!this.waitingForKeys) {
            if (this.codecReconfigurationState == 1) {
                if (this.format != null) {
                    for (int i5 = 0; i5 < this.format.initializationData.size(); i5++) {
                        this.sampleHolder.data.put(this.format.initializationData.get(i5));
                    }
                } else {
                    Log.e("MediaCodecTrackRenderer", "format is null!");
                }
                this.codecReconfigurationState = 2;
            }
            MtkDebugLogUtil.log("MediaCodecTrackRenderer", "+readData, isAudio[" + z4 + "], positionUs[" + j + "]", 1);
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                source = readSource(j, this.formatHolder, this.sampleHolder);
                if (!this.feededFirstInputBuffer) {
                    Log.d("MediaCodecTrackRenderer", "[Performance]-readData, isAudio[" + z4 + "], result[" + source + "], timeUs[" + this.sampleHolder.timeUs + "], size[" + this.sampleHolder.size + "], speedTimeMs[" + (System.currentTimeMillis() - jCurrentTimeMillis) + "], flags[" + this.sampleHolder.flags + "]");
                    this.feededFirstInputBuffer = true;
                } else {
                    MtkDebugLogUtil.log("MediaCodecTrackRenderer", "-readData, isAudio[" + z4 + "], result[" + source + "], timeUs[" + this.sampleHolder.timeUs + "], size[" + this.sampleHolder.size + "], speedTimeMs[" + (System.currentTimeMillis() - jCurrentTimeMillis) + "], flags[" + this.sampleHolder.flags + "]", 1);
                }
                if (source == -1 && !z4) {
                    Log.d("MediaCodecTrackRenderer", "[AutoTest]reach EOS, the last video sample pts = " + this.sampleHolder.timeUs);
                }
                if (z && this.sourceState == 1) {
                    i = -2;
                    if (source == -2 && this.playState != 3) {
                        this.sourceState = 2;
                    }
                }
                if (source == i) {
                    return false;
                }
                if (source != -1) {
                    this.justResubmitCsdData = false;
                }
                if ((this.sampleHolder.flags & 67108864) != 0 && this.isTunnelingVideo) {
                    if (this.speed <= 4 && this.isSeeking == 3) {
                        Log.d("MediaCodecTrackRenderer", "tunnel trick seekflag");
                        this.seekFlag = 1;
                        this.isSeeking = 0;
                    } else {
                        i4 = this.isSeeking;
                        if (i4 == 1) {
                            MtkDebugLogUtil.log("MediaCodecTrackRenderer", "lzl setvendorparam for pause seek", 1);
                            Bundle bundle = new Bundle();
                            Log.d("MediaCodecTrackRenderer", "tunnel pause step=" + this.lastSeekPositionUs);
                            StringBuilder sb = new StringBuilder();
                            sb.append("step=");
                            sb.append(this.lastSeekPositionUs);
                            bundle.putString("vendor.vdecomx.trick", sb.toString());
                            this.codec.setParameters(bundle);
                            this.isSeeking = 0;
                            boolean[] zArr = this.inbandDataToDecoderForTrick;
                            zArr[1] = true;
                            zArr[0] = false;
                        } else if (i4 == 2) {
                            MtkDebugLogUtil.log("MediaCodecTrackRenderer", "lzl setvendorparam for play seek", 1);
                            Bundle bundle2 = new Bundle();
                            MtkDebugLogUtil.log("MediaCodecTrackRenderer", "play seek=" + this.lastSeekPositionUs, 1);
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("seek=");
                            sb2.append(this.lastSeekPositionUs);
                            bundle2.putString("vendor.vdecomx.trick", sb2.toString());
                            this.codec.setParameters(bundle2);
                            this.isSeeking = 0;
                        }
                    }
                    this.lastSeekPositionUs = this.sampleHolder.timeUs;
                }
                if (!z4 && this.isTunnelingVideo && (((i3 = this.speed) == 5 || i3 == 6 || i3 == 7 || i3 == 8 || i3 == 9 || i3 == 10 || i3 == 11 || i3 == 12 || i3 == 13) && this.isTunnelingVideo && this.seekFlag == 1)) {
                    MtkDebugLogUtil.log("MediaCodecTrackRenderer", "lzl setvendorparam FF seek trick step", 1);
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("vendor.vdecomx.trick", "step");
                    this.codec.setParameters(bundle3);
                    this.seekFlag = 0;
                    boolean[] zArr2 = this.inbandDataToDecoderForTrick;
                    zArr2[1] = true;
                    zArr2[0] = false;
                }
                if (source == -4) {
                    if (this.codecReconfigurationState == 2) {
                        this.sampleHolder.clearData();
                        this.codecReconfigurationState = 1;
                    }
                    onInputFormatChanged(this.formatHolder);
                    return !isMediaCodecAsyncMode();
                }
                if (source == -1) {
                    if (this.codecReconfigurationState == 2) {
                        this.sampleHolder.clearData();
                        this.codecReconfigurationState = 1;
                    }
                    this.inputStreamEnded = true;
                    if (this.codecReceivedBuffers || this.justResubmitCsdData) {
                        Log.d("MediaCodecTrackRenderer", "processEndOfStream");
                        this.justResubmitCsdData = false;
                        processEndOfStream();
                        return false;
                    }
                    try {
                        if (this.codecNeedsEosPropagationWorkaround) {
                            z3 = false;
                        } else {
                            this.codecReceivedEos = true;
                            this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0L, 4);
                            this.inputIndex = -1;
                            z3 = true;
                        }
                        return isMediaCodecAsyncMode() && z3;
                    } catch (MediaCodec.CryptoException e3) {
                        notifyCryptoError(e3);
                        throw new ExoPlaybackException(e3);
                    }
                }
                if (this.waitingForFirstSyncFrame) {
                    z2 = false;
                } else {
                    if (!this.sampleHolder.isSyncFrame()) {
                        this.sampleHolder.clearData();
                        if (this.codecReconfigurationState == 2) {
                            this.codecReconfigurationState = 1;
                        }
                        return !isMediaCodecAsyncMode();
                    }
                    z2 = false;
                    this.waitingForFirstSyncFrame = false;
                }
                zIsEncrypted = this.sampleHolder.isEncrypted();
                zShouldWaitForKeys = shouldWaitForKeys(zIsEncrypted);
                this.waitingForKeys = zShouldWaitForKeys;
                if (zShouldWaitForKeys) {
                    return z2;
                }
                if (this.codecNeedsDiscardToSpsWorkaround && !zIsEncrypted) {
                    NalUnitUtil.discardToSps(this.sampleHolder.data);
                    if (this.sampleHolder.data.position() == 0) {
                        return !isMediaCodecAsyncMode();
                    }
                    this.codecNeedsDiscardToSpsWorkaround = false;
                }
                try {
                    iPosition = this.sampleHolder.data.position();
                    i2 = iPosition - this.sampleHolder.size;
                    j2 = this.sampleHolder.timeUs;
                    onQueuedInputBuffer(j2, this.sampleHolder.data, iPosition, zIsEncrypted);
                    if (zIsEncrypted) {
                        this.codec.queueSecureInputBuffer(this.inputIndex, 0, getFrameworkCryptoInfo(this.sampleHolder, i2), j2, 0);
                    } else {
                        try {
                            try {
                                this.codec.queueInputBuffer(this.inputIndex, 0, iPosition, j2, 0);
                            } catch (IllegalStateException e4) {
                                Log.w("MediaCodecTrackRenderer", "Catch codec.queueInputBuffer IllegalStateException!");
                                if (isMediaCodecAsyncMode()) {
                                    return false;
                                }
                                if (this instanceof MediaCodecVideoTrackRenderer) {
                                    throw new ExoPlaybackException(e4, 0);
                                }
                                if (!((MediaCodecAudioTrackRenderer) this).hasPendingData()) {
                                    throw new ExoPlaybackException(e4, 1);
                                }
                                throw new ExoPlaybackException(e4);
                            }
                        } catch (MediaCodec.CodecException e5) {
                            Log.e("MediaCodecTrackRenderer", "Catch MediaCodec.CodecException, error:" + e5.getErrorCode());
                            throw e5;
                        }
                    }
                    if (isMediaCodecAsyncMode()) {
                        this.sampleHolder.data.clear();
                    }
                    this.inputIndex = -1;
                    this.codecReceivedBuffers = true;
                    this.codecReconfigurationState = 0;
                    this.codecCounters.inputBufferCount++;
                    isMediaCodecAsyncMode();
                    return true;
                } catch (MediaCodec.CryptoException e6) {
                    notifyCryptoError(e6);
                    throw new ExoPlaybackException(e6);
                }
            } catch (IllegalStateException e7) {
                throw new ExoPlaybackException(e7, z4 ? 1 : 0);
            }
        }
        source = -3;
        i = -2;
        if (source == i) {
            return false;
        }
        if (source != -1) {
            this.justResubmitCsdData = false;
        }
        if ((this.sampleHolder.flags & 67108864) != 0) {
            if (this.speed <= 4) {
                i4 = this.isSeeking;
                if (i4 == 1) {
                    MtkDebugLogUtil.log("MediaCodecTrackRenderer", "lzl setvendorparam for pause seek", 1);
                    Bundle bundle4 = new Bundle();
                    Log.d("MediaCodecTrackRenderer", "tunnel pause step=" + this.lastSeekPositionUs);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("step=");
                    sb3.append(this.lastSeekPositionUs);
                    bundle4.putString("vendor.vdecomx.trick", sb3.toString());
                    this.codec.setParameters(bundle4);
                    this.isSeeking = 0;
                    boolean[] zArr3 = this.inbandDataToDecoderForTrick;
                    zArr3[1] = true;
                    zArr3[0] = false;
                } else if (i4 == 2) {
                    MtkDebugLogUtil.log("MediaCodecTrackRenderer", "lzl setvendorparam for play seek", 1);
                    Bundle bundle5 = new Bundle();
                    MtkDebugLogUtil.log("MediaCodecTrackRenderer", "play seek=" + this.lastSeekPositionUs, 1);
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("seek=");
                    sb4.append(this.lastSeekPositionUs);
                    bundle5.putString("vendor.vdecomx.trick", sb4.toString());
                    this.codec.setParameters(bundle5);
                    this.isSeeking = 0;
                }
            } else {
                i4 = this.isSeeking;
                if (i4 == 1) {
                    MtkDebugLogUtil.log("MediaCodecTrackRenderer", "lzl setvendorparam for pause seek", 1);
                    Bundle bundle6 = new Bundle();
                    Log.d("MediaCodecTrackRenderer", "tunnel pause step=" + this.lastSeekPositionUs);
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("step=");
                    sb5.append(this.lastSeekPositionUs);
                    bundle6.putString("vendor.vdecomx.trick", sb5.toString());
                    this.codec.setParameters(bundle6);
                    this.isSeeking = 0;
                    boolean[] zArr4 = this.inbandDataToDecoderForTrick;
                    zArr4[1] = true;
                    zArr4[0] = false;
                } else if (i4 == 2) {
                    MtkDebugLogUtil.log("MediaCodecTrackRenderer", "lzl setvendorparam for play seek", 1);
                    Bundle bundle7 = new Bundle();
                    MtkDebugLogUtil.log("MediaCodecTrackRenderer", "play seek=" + this.lastSeekPositionUs, 1);
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("seek=");
                    sb6.append(this.lastSeekPositionUs);
                    bundle7.putString("vendor.vdecomx.trick", sb6.toString());
                    this.codec.setParameters(bundle7);
                    this.isSeeking = 0;
                }
            }
            this.lastSeekPositionUs = this.sampleHolder.timeUs;
        }
        if (!z4) {
            MtkDebugLogUtil.log("MediaCodecTrackRenderer", "lzl setvendorparam FF seek trick step", 1);
            Bundle bundle8 = new Bundle();
            bundle8.putString("vendor.vdecomx.trick", "step");
            this.codec.setParameters(bundle8);
            this.seekFlag = 0;
            boolean[] zArr5 = this.inbandDataToDecoderForTrick;
            zArr5[1] = true;
            zArr5[0] = false;
        }
        if (source == -4) {
            if (this.codecReconfigurationState == 2) {
                this.sampleHolder.clearData();
                this.codecReconfigurationState = 1;
            }
            onInputFormatChanged(this.formatHolder);
            return !isMediaCodecAsyncMode();
        }
        if (source == -1) {
            if (this.codecReconfigurationState == 2) {
                this.sampleHolder.clearData();
                this.codecReconfigurationState = 1;
            }
            this.inputStreamEnded = true;
            if (this.codecReceivedBuffers) {
            }
            Log.d("MediaCodecTrackRenderer", "processEndOfStream");
            this.justResubmitCsdData = false;
            processEndOfStream();
            return false;
        }
        if (this.waitingForFirstSyncFrame) {
            z2 = false;
        } else {
            if (!this.sampleHolder.isSyncFrame()) {
                this.sampleHolder.clearData();
                if (this.codecReconfigurationState == 2) {
                    this.codecReconfigurationState = 1;
                }
                return !isMediaCodecAsyncMode();
            }
            z2 = false;
            this.waitingForFirstSyncFrame = false;
        }
        zIsEncrypted = this.sampleHolder.isEncrypted();
        zShouldWaitForKeys = shouldWaitForKeys(zIsEncrypted);
        this.waitingForKeys = zShouldWaitForKeys;
        if (zShouldWaitForKeys) {
            return z2;
        }
        if (this.codecNeedsDiscardToSpsWorkaround) {
            NalUnitUtil.discardToSps(this.sampleHolder.data);
            if (this.sampleHolder.data.position() == 0) {
                return !isMediaCodecAsyncMode();
            }
            this.codecNeedsDiscardToSpsWorkaround = false;
        }
        iPosition = this.sampleHolder.data.position();
        i2 = iPosition - this.sampleHolder.size;
        j2 = this.sampleHolder.timeUs;
        onQueuedInputBuffer(j2, this.sampleHolder.data, iPosition, zIsEncrypted);
        if (zIsEncrypted) {
            this.codec.queueSecureInputBuffer(this.inputIndex, 0, getFrameworkCryptoInfo(this.sampleHolder, i2), j2, 0);
        } else {
            this.codec.queueInputBuffer(this.inputIndex, 0, iPosition, j2, 0);
        }
        if (isMediaCodecAsyncMode()) {
            this.sampleHolder.data.clear();
        }
        this.inputIndex = -1;
        this.codecReceivedBuffers = true;
        this.codecReconfigurationState = 0;
        this.codecCounters.inputBufferCount++;
        isMediaCodecAsyncMode();
        return true;
    }

    private static MediaCodec.CryptoInfo getFrameworkCryptoInfo(SampleHolder sampleHolder, int i) {
        MediaCodec.CryptoInfo frameworkCryptoInfoV16 = sampleHolder.cryptoInfo.getFrameworkCryptoInfoV16();
        if (i == 0) {
            return frameworkCryptoInfoV16;
        }
        if (frameworkCryptoInfoV16.numBytesOfClearData == null) {
            frameworkCryptoInfoV16.numBytesOfClearData = new int[1];
        }
        int[] iArr = frameworkCryptoInfoV16.numBytesOfClearData;
        iArr[0] = iArr[0] + i;
        return frameworkCryptoInfoV16;
    }

    private android.media.MediaFormat getFrameworkMediaFormat(MediaFormat mediaFormat) {
        android.media.MediaFormat frameworkMediaFormatV16 = mediaFormat.getFrameworkMediaFormatV16();
        if (this.deviceNeedsAutoFrcWorkaround) {
            frameworkMediaFormatV16.setInteger("auto-frc", 0);
        }
        return frameworkMediaFormatV16;
    }

    private boolean shouldWaitForKeys(boolean z) throws ExoPlaybackException {
        if (!this.openedDrmSession) {
            return false;
        }
        int state = this.drmSessionManager.getState();
        if (state != 0) {
            return state != 4 && (z || !this.playClearSamplesWithoutKeys);
        }
        throw new ExoPlaybackException(this.drmSessionManager.getError());
    }

    protected void onInputFormatChanged(MediaFormatHolder mediaFormatHolder) throws ExoPlaybackException {
        MediaFormat mediaFormat = this.format;
        this.format = mediaFormatHolder.format;
        DrmInitData drmInitData = mediaFormatHolder.drmInitData;
        this.drmInitData = drmInitData;
        boolean z = false;
        boolean z2 = (drmInitData == null || this.openedDrmSession) ? false : true;
        if (!Util.areEqual(this.format, mediaFormat) || z2) {
            MediaCodec mediaCodec = this.codec;
            if (mediaCodec != null && !z2 && canReconfigureCodec(mediaCodec, this.codecIsAdaptive, mediaFormat, this.format)) {
                this.codecReconfigured = true;
                this.codecReconfigurationState = 1;
                if (this.codecNeedsAdaptationWorkaround) {
                    MediaFormat mediaFormat2 = this.format;
                    if (mediaFormat2.width == mediaFormat.width && mediaFormat2.height == mediaFormat.height) {
                        z = true;
                    }
                }
                this.codecNeedsAdaptationWorkaroundBuffer = z;
                return;
            }
            if (this.codecReceivedBuffers) {
                this.codecReinitializationState = 1;
            } else {
                releaseCodec();
                maybeInitCodec();
            }
        }
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isReady() {
        return (this.format == null || this.waitingForKeys || (this.sourceState == 0 && this.outputIndex < 0 && !isWithinHotswapPeriod())) ? false : true;
    }

    protected final int getSourceState() {
        return this.sourceState;
    }

    private boolean isWithinHotswapPeriod() {
        return SystemClock.elapsedRealtime() < this.codecHotswapTimeMs + 1000;
    }

    private boolean drainOutputBuffer(long j, long j2) throws ExoPlaybackException {
        ByteBuffer outputBuffer;
        boolean zProcessOutputBuffer;
        if (this.isTunnelingVideo) {
            try {
                onOutputFormatChanged(this.codec, this.codec.getOutputFormat());
                MediaCodecVideoTrackRenderer mediaCodecVideoTrackRenderer = (MediaCodecVideoTrackRenderer) this;
                if (!mediaCodecVideoTrackRenderer.haveRenderedFirstFrame()) {
                    mediaCodecVideoTrackRenderer.setupVideoMediaTunnelingOutput();
                }
                if (this.inputStreamEnded && mediaCodecVideoTrackRenderer.getTunnelEos()) {
                    this.outputStreamEnded = true;
                }
                return false;
            } catch (MediaCodec.CodecException e) {
                Log.e("MediaCodecTrackRenderer", "Catch MediaCodec.CodecException, error:" + e.getErrorCode());
                throw e;
            } catch (IllegalStateException e2) {
                if (isMediaCodecAsyncMode()) {
                    return false;
                }
                throw new ExoPlaybackException(e2.getCause(), 0);
            }
        }
        if (this.outputStreamEnded) {
            return false;
        }
        if (isMediaCodecAsyncMode()) {
            if (this.availableOutputBufferIds.isEmpty() || this.availableOutputBufferInfos.isEmpty()) {
                return false;
            }
            this.outputIndex = this.availableOutputBufferIds.peek().intValue();
            this.outputBufferInfo = this.availableOutputBufferInfos.peek();
        } else if (this.outputIndex < 0) {
            if (this.codecNeedsEosOutputExceptionWorkaround && this.codecReceivedEos) {
                try {
                    this.outputIndex = this.codec.dequeueOutputBuffer(this.outputBufferInfo, getDequeueOutputBufferTimeoutUs());
                } catch (IllegalStateException unused) {
                    processEndOfStream();
                    if (this.outputStreamEnded) {
                        releaseCodec();
                    }
                    return false;
                }
            } else {
                try {
                    this.outputIndex = this.codec.dequeueOutputBuffer(this.outputBufferInfo, getDequeueOutputBufferTimeoutUs());
                } catch (MediaCodec.CodecException e3) {
                    Log.e("MediaCodecTrackRenderer", "Catch MediaCodec.CodecException, error:" + e3.getErrorCode());
                    throw e3;
                } catch (IllegalStateException e4) {
                    Log.w("MediaCodecTrackRenderer", "Catch codec.dequeueOutputBuffer IllegalStateException!");
                    if (this instanceof MediaCodecVideoTrackRenderer) {
                        throw new ExoPlaybackException(e4, 0);
                    }
                    throw new ExoPlaybackException(e4, 1);
                }
            }
        }
        int i = this.outputIndex;
        if (i == -2) {
            processOutputFormat();
            return true;
        }
        if (i == -3) {
            this.outputBuffers = this.codec.getOutputBuffers();
            this.codecCounters.outputBuffersChangedCount++;
            return true;
        }
        if (i < 0) {
            if (!this.codecNeedsEosPropagationWorkaround || (!this.inputStreamEnded && this.codecReinitializationState != 2)) {
                return false;
            }
            processEndOfStream();
            return true;
        }
        if (this.shouldSkipAdaptationWorkaroundOutputBuffer) {
            this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
            this.codec.releaseOutputBuffer(i, false);
            if (isMediaCodecAsyncMode()) {
                this.availableOutputBufferIds.remove();
                this.availableOutputBufferInfos.remove();
            }
            this.outputIndex = -1;
            return true;
        }
        if ((this.outputBufferInfo.flags & 4) != 0) {
            processEndOfStream();
            return false;
        }
        if (isMediaCodecAsyncMode()) {
            outputBuffer = this.outputBuffer;
            if (outputBuffer == null) {
                outputBuffer = this.codec.getOutputBuffer(this.outputIndex);
            }
        } else {
            outputBuffer = this.outputBuffers[this.outputIndex];
        }
        this.outputBuffer = outputBuffer;
        if (needApendAacHeader(outputBuffer)) {
            ByteBuffer byteBuffer = this.aacBuffer;
            if (byteBuffer == null) {
                this.outputBuffer = getNewAacBuffer(this.outputBuffer, this.outputBufferInfo.size);
            } else {
                this.outputBuffer = byteBuffer;
            }
            MediaCodec.BufferInfo bufferInfo = this.outputBufferInfo;
            bufferInfo.size += AAC_FAKE_HEADER_SIZE;
            bufferInfo.offset = this.outputBuffer.position();
        }
        boolean z = this.seekMode == 1;
        if (!this.drainedFirstOutputBuffer) {
            Log.d("MediaCodecTrackRenderer", "[Performance]speed=" + this.speed + ", isPrecisionSeekMode=" + z + ", presentationTimeUs=" + this.outputBufferInfo.presentationTimeUs + ", lastSeekPositionUs=" + this.lastSeekPositionUs);
            this.drainedFirstOutputBuffer = true;
        } else {
            MtkDebugLogUtil.log("MediaCodecTrackRenderer", "speed=" + this.speed + ", isPrecisionSeekMode=" + z + ", presentationTimeUs=" + this.outputBufferInfo.presentationTimeUs + ", lastSeekPositionUs=" + this.lastSeekPositionUs, 1);
        }
        boolean z2 = this.speed <= 3 && z && this.outputBufferInfo.presentationTimeUs < this.lastSeekPositionUs;
        if (this.codecNeedsEosOutputExceptionWorkaround && this.codecReceivedEos) {
            try {
                zProcessOutputBuffer = processOutputBuffer(j, j2, this.codec, this.outputBuffer, this.outputBufferInfo, this.outputIndex, z2);
            } catch (IllegalStateException unused2) {
                processEndOfStream();
                if (this.outputStreamEnded) {
                    releaseCodec();
                }
                return false;
            }
        } else {
            zProcessOutputBuffer = processOutputBuffer(j, j2, this.codec, this.outputBuffer, this.outputBufferInfo, this.outputIndex, z2);
        }
        if (!zProcessOutputBuffer) {
            return false;
        }
        onProcessedOutputBuffer(this.outputBufferInfo.presentationTimeUs);
        if (isMediaCodecAsyncMode()) {
            this.availableOutputBufferIds.remove();
            this.availableOutputBufferInfos.remove();
        }
        this.outputIndex = -1;
        this.outputBuffer = null;
        this.aacBuffer = null;
        return true;
    }

    private void processOutputFormat() throws ExoPlaybackException {
        processOutputFormat(this.codec.getOutputFormat());
    }

    private void processOutputFormat(android.media.MediaFormat mediaFormat) throws ExoPlaybackException {
        if (this.codecNeedsAdaptationWorkaround && mediaFormat.getInteger("width") == 32 && mediaFormat.getInteger("height") == 32) {
            this.shouldSkipAdaptationWorkaroundOutputBuffer = true;
            return;
        }
        if (this.codecNeedsMonoChannelCountWorkaround) {
            mediaFormat.setInteger("channel-count", 1);
        }
        onOutputFormatChanged(this.codec, mediaFormat);
        this.codecCounters.outputFormatChangedCount++;
        this.outputFormatChanged = true;
    }

    private void processEndOfStream() throws ExoPlaybackException {
        if (this.codecReinitializationState == 2) {
            releaseCodec();
            maybeInitCodec();
        } else {
            this.outputStreamEnded = true;
            onOutputStreamEnded();
        }
    }

    private void notifyDecoderInitializationError(final DecoderInitializationException decoderInitializationException) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecTrackRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                MediaCodecTrackRenderer.this.eventListener.onDecoderInitializationError(decoderInitializationException);
            }
        });
    }

    private void notifyCryptoError(final MediaCodec.CryptoException cryptoException) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecTrackRenderer.2
            @Override // java.lang.Runnable
            public void run() {
                MediaCodecTrackRenderer.this.eventListener.onCryptoError(cryptoException);
            }
        });
    }

    private void notifyDecoderInitialized(final String str, final long j, final long j2) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecTrackRenderer.3
            @Override // java.lang.Runnable
            public void run() {
                MediaCodecTrackRenderer.this.eventListener.onDecoderInitialized(str, j, j2);
            }
        });
    }

    private static boolean codecNeedsFlushWorkaround(String str) {
        int i = Util.SDK_INT;
        return i < 18 || (i == 18 && ("OMX.SEC.avc.dec".equals(str) || "OMX.SEC.avc.dec.secure".equals(str))) || (Util.SDK_INT == 19 && Util.MODEL.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(str) || "OMX.Exynos.avc.dec.secure".equals(str)));
    }

    private static boolean codecNeedsAdaptationWorkaround(String str) {
        return Util.SDK_INT < 24 && ("OMX.Nvidia.h264.decode".equals(str) || "OMX.Nvidia.h264.decode.secure".equals(str)) && (Util.DEVICE.equals("flounder") || Util.DEVICE.equals("flounder_lte") || Util.DEVICE.equals("grouper") || Util.DEVICE.equals("tilapia"));
    }

    private static boolean codecNeedsDiscardToSpsWorkaround(String str, MediaFormat mediaFormat) {
        return Util.SDK_INT < 21 && mediaFormat.initializationData.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str);
    }

    private static boolean codecNeedsEosPropagationWorkaround(String str) {
        return Util.SDK_INT <= 17 && ("OMX.rk.video_decoder.avc".equals(str) || "OMX.allwinner.video.decoder.avc".equals(str));
    }

    private static boolean codecNeedsEosFlushWorkaround(String str) {
        return (Util.SDK_INT <= 23 && "OMX.google.vorbis.decoder".equals(str)) || (Util.SDK_INT <= 19 && "hb2000".equals(Util.DEVICE) && ("OMX.amlogic.avc.decoder.awesome".equals(str) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str)));
    }

    private static boolean codecNeedsEosOutputExceptionWorkaround(String str) {
        return Util.SDK_INT == 21 && "OMX.google.aac.decoder".equals(str);
    }

    private static boolean codecNeedsMonoChannelCountWorkaround(String str, MediaFormat mediaFormat) {
        return Util.SDK_INT <= 18 && mediaFormat.channelCount == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(str);
    }

    private static boolean deviceNeedsAutoFrcWorkaround() {
        return Util.SDK_INT <= 22 && "foster".equals(Util.DEVICE) && "NVIDIA".equals(Util.MANUFACTURER);
    }

    public void setTunnelingAudioSessionId(int i) throws IllegalArgumentException {
        this.tunnelingAudioSessionId = i;
    }

    protected int getTunnelingAudioSessionId() {
        return this.tunnelingAudioSessionId;
    }

    protected void setIsTunnelingVideo(boolean z) {
        this.isTunnelingVideo = z;
    }

    public void setDrmSessionManager(DrmSessionManager drmSessionManager) {
        this.drmSessionManager = drmSessionManager;
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void maybeThrowError() throws ExoPlaybackException {
        super.maybeThrowError();
    }

    protected DecoderInfo getDecoderInfo(MediaCodecSelector mediaCodecSelector, String str, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException {
        return mediaCodecSelector.getDecoderInfo(str, z, z2);
    }

    private boolean isMediaCodecAsyncMode() {
        return Util.SDK_INT >= 21 && this.tunnelingAudioSessionId != -1;
    }

    public void setInternalPlayer(ExoPlayerImplInternal exoPlayerImplInternal) {
        this.internalPlayer = exoPlayerImplInternal;
    }

    public boolean onInputBufferAvailable(int i) {
        this.inputIndex = i;
        ExoPlayerImplInternal exoPlayerImplInternal = this.internalPlayer;
        long currentPosition = exoPlayerImplInternal != null ? exoPlayerImplInternal.getCurrentPosition() * 1000 : 0L;
        try {
            if (this.sourceState != 0) {
                return feedInputBuffer(currentPosition, true);
            }
            return false;
        } catch (ExoPlaybackException e) {
            ExoPlayerImplInternal exoPlayerImplInternal2 = this.internalPlayer;
            if (exoPlayerImplInternal2 != null) {
                exoPlayerImplInternal2.throwMediaCodecTrackRendererException(e);
            }
            return true;
        }
    }

    public void onOutputBufferAvailable(int i, MediaCodec.BufferInfo bufferInfo) {
        if (this.isTunnelingVideo && (this instanceof MediaCodecVideoTrackRenderer)) {
            return;
        }
        try {
            this.availableOutputBufferIds.put(Integer.valueOf(i));
            this.availableOutputBufferInfos.put(bufferInfo);
        } catch (InterruptedException unused) {
            Log.e("MediaCodecTrackRenderer", "onOutputBufferAvailable, catch InterruptedException");
        }
    }

    public void onOutputFormatChangedFromCallback(android.media.MediaFormat mediaFormat) {
        try {
            processOutputFormat(mediaFormat);
        } catch (ExoPlaybackException e) {
            this.internalPlayer.throwMediaCodecTrackRendererException(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [int] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    public void onError(MediaCodec.CodecException codecException, boolean z) {
        ?? r4;
        if (codecException.getErrorCode() == -1879048184) {
            r4 = 2;
        } else if (codecException.getErrorCode() == -2147479533) {
            r4 = z;
            r4 = 3;
            Log.e("MediaCodecTrackRenderer", "onError, catch ERROR_RESOURCE_INTERRUPT");
        }
        r4 = z;
        this.internalPlayer.throwMediaCodecTrackRendererException(new ExoPlaybackException(codecException, (int) r4));
    }

    private boolean needApendAacHeader(ByteBuffer byteBuffer) {
        if (!(this instanceof MediaCodecAudioTrackRenderer) || !this.format.mimeType.equals("audio/mp4a-latm") || !((MediaCodecAudioTrackRenderer) this).allowPassthrough(this.format.mimeType) || byteBuffer.limit() < 2) {
            return false;
        }
        byte b = byteBuffer.get(0);
        byte b2 = byteBuffer.get(1);
        int i = b & 255;
        return ((i == 255 && (b2 & 240) == 240) || (i == 86 && (b2 & 224) == 224)) ? false : true;
    }

    private byte[] genAacHeader(int i) {
        byte[] bArr = new byte[AAC_FAKE_HEADER_SIZE];
        int i2 = 0;
        bArr[0] = -1;
        bArr[1] = -7;
        MediaFormat mediaFormat = this.format;
        int i3 = mediaFormat.sampleRate;
        if (mediaFormat.getFrameworkMediaFormatV16().containsKey("aac-profile")) {
            MtkDebugLogUtil.log("MediaCodecTrackRenderer", "appendAacHeader aacProfile:" + this.format.getFrameworkMediaFormatV16().getInteger("aac-profile"), 1);
        }
        MtkDebugLogUtil.log("MediaCodecTrackRenderer", "appendAacHeader sampleRate=" + this.format.sampleRate + ", channelCount=" + this.format.channelCount + ", bufferSize=" + i, 1);
        switch (i3) {
            case 8000:
                i2 = 11;
                break;
            case 11025:
                i2 = 10;
                break;
            case 12000:
                i2 = 9;
                break;
            case 16000:
                i2 = 8;
                break;
            case 22050:
                i2 = 7;
                break;
            case 24000:
                i2 = 6;
                break;
            case 32000:
                i2 = 5;
                break;
            case 44100:
            default:
                i2 = 4;
                break;
            case 48000:
                i2 = 3;
                break;
            case 64000:
                i2 = 2;
                break;
            case 88200:
                i2 = 1;
                break;
            case 96000:
                break;
        }
        int i4 = this.format.channelCount;
        int i5 = i4 <= 7 ? i4 : 7;
        bArr[2] = (byte) (((i2 << 2) & 60) | 64 | ((i5 >> 2) & 1));
        int i6 = AAC_FAKE_HEADER_SIZE;
        bArr[3] = (byte) (((i5 & 3) << 6) | (((i + i6) >> 11) & 3));
        bArr[4] = (byte) (((i + i6) >> 3) & 255);
        bArr[5] = (byte) ((((i + i6) << 5) & MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN) | 31);
        bArr[6] = -4;
        return bArr;
    }

    private ByteBuffer getNewAacBuffer(ByteBuffer byteBuffer, int i) {
        ByteBuffer byteBuffer2 = this.aacBuffer;
        if (byteBuffer2 == null || byteBuffer2.limit() < AAC_FAKE_HEADER_SIZE + i) {
            this.aacBuffer = ByteBuffer.allocate(AAC_FAKE_HEADER_SIZE + i);
        }
        this.aacBuffer.clear();
        this.aacBuffer.put(genAacHeader(i));
        int iPosition = byteBuffer.position();
        this.aacBuffer.put(byteBuffer);
        byteBuffer.position(iPosition);
        this.aacBuffer.position(0);
        return this.aacBuffer;
    }

    public void setvendorparam(int i, boolean z) {
        boolean z2 = this instanceof MediaCodecAudioTrackRenderer;
        if (this.codec == null || z2) {
            return;
        }
        Bundle bundle = new Bundle();
        if (z) {
            if (i == 3) {
                bundle.putString("vendor.vdecomx.trick", "resume");
                this.codec.setParameters(bundle);
                MtkDebugLogUtil.log("MediaCodecVideoTrackRenderer", "lzl setvendorparam VIDEO_ONLY_RESUME", 1);
                return;
            } else {
                if (i == 1) {
                    bundle.putString("vendor.vdecomx.trick", "pause");
                    this.codec.setParameters(bundle);
                    MtkDebugLogUtil.log("MediaCodecVideoTrackRenderer", "lzl setvendorparam VIDEO_ONLY_PAUSE", 1);
                    return;
                }
                return;
            }
        }
        if (i == 4) {
            if (this.inbandDataToDecoderForTrick[0]) {
                return;
            }
            if (this.tunnelingAudioSessionId == 0) {
                bundle.putString("vendor.vdecomx.trick", "resume");
                this.codec.setParameters(bundle);
            }
            bundle.putString("vendor.vdecomx.trick", "dropframe");
            this.codec.setParameters(bundle);
            boolean[] zArr = this.inbandDataToDecoderForTrick;
            zArr[0] = true;
            zArr[1] = false;
            MtkDebugLogUtil.log("MediaCodecTrackRenderer", "lzl setvendorparam FF 2x trick dropframe", 1);
            return;
        }
        if (i == 3 || i == 1 || i == 2) {
            if (i == 1) {
                bundle.putString("vendor.vdecomx.trick", "pause");
            } else {
                bundle.putString("vendor.vdecomx.trick", "tricktonormal");
            }
            this.codec.setParameters(bundle);
            boolean[] zArr2 = this.inbandDataToDecoderForTrick;
            zArr2[0] = false;
            zArr2[1] = false;
            MtkDebugLogUtil.log("MediaCodecTrackRenderer", "setvendorparam speed = " + i, 1);
        }
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer, com.google.android.exoplayer.ExoPlayer.ExoPlayerComponent
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1010) {
            if (this.codec != null) {
                Bundle bundle = new Bundle();
                bundle.putString("vendor.vdecomx.trick", "step");
                this.codec.setParameters(bundle);
                MtkDebugLogUtil.log("MediaCodecTrackRenderer", "lzl setvendorparam pause seek step", 1);
                return;
            }
            return;
        }
        if (i == 1011) {
            if (this.codec != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("vendor.vdecomx.trick", "tricktonormal");
                this.codec.setParameters(bundle2);
                MtkDebugLogUtil.log("MediaCodecTrackRenderer", "lzl setvendorparam pause seek resume", 1);
                return;
            }
            return;
        }
        if (i == 1012) {
            if (this.codec != null) {
                Bundle bundle3 = new Bundle();
                StringBuilder sb = new StringBuilder();
                sb.append("audioend=");
                Long l = (Long) obj;
                sb.append(l.longValue());
                bundle3.putString("vendor.vdecomx.trick", sb.toString());
                this.codec.setParameters(bundle3);
                MtkDebugLogUtil.log("MediaCodecTrackRenderer", "setParameter audioend=" + l.longValue(), 1);
                return;
            }
            return;
        }
        super.handleMessage(i, obj);
    }
}
