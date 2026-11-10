package com.google.android.exoplayer;

import com.google.android.exoplayer.nbfss.NonBlockingFrameworkSampleSource;
import com.google.android.exoplayer.util.MimeTypes;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class SampleSourceTrackRenderer extends TrackRenderer {
    private long durationUs;
    private SampleSource.SampleSourceReader enabledSource;
    private int enabledSourceTrackIndex;
    private int[] handledSourceIndices;
    private int[] handledSourceTrackIndices;
    protected boolean onlyPushIFrame;
    private SampleSource.SampleSourceReader[] sources;
    protected int speed = 3;
    protected long lastSeekPositionUs = -1;
    private boolean isVideoOnly = false;
    protected int playState = 1;
    protected int isSeeking = 0;

    protected abstract void doSomeWork(long j, long j2, boolean z) throws ExoPlaybackException;

    protected abstract boolean handlesTrack(MediaFormat mediaFormat) throws MediaCodecUtil.DecoderQueryException;

    protected abstract void onDiscontinuity(long j) throws ExoPlaybackException;

    protected long shiftInputPosition(long j) {
        return j;
    }

    public SampleSourceTrackRenderer(SampleSource... sampleSourceArr) {
        this.sources = new SampleSource.SampleSourceReader[sampleSourceArr.length];
        for (int i = 0; i < sampleSourceArr.length; i++) {
            this.sources[i] = sampleSourceArr[i].register();
        }
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected final boolean doPrepare(long j) throws ExoPlaybackException {
        SampleSource.SampleSourceReader[] sampleSourceReaderArr;
        boolean z;
        int i;
        int i2 = 0;
        int i3 = 0;
        boolean zPrepare = true;
        while (true) {
            SampleSource.SampleSourceReader[] sampleSourceReaderArr2 = this.sources;
            if (i3 >= sampleSourceReaderArr2.length) {
                break;
            }
            zPrepare &= sampleSourceReaderArr2[i3].prepare(j);
            i3++;
        }
        if (!zPrepare) {
            return false;
        }
        int i4 = 0;
        int trackCount = 0;
        while (true) {
            sampleSourceReaderArr = this.sources;
            if (i4 >= sampleSourceReaderArr.length) {
                break;
            }
            trackCount += sampleSourceReaderArr[i4].getTrackCount();
            i4++;
        }
        long jMax = 0;
        int[] iArr = new int[trackCount];
        int[] iArr2 = new int[trackCount];
        int length = sampleSourceReaderArr.length;
        int i5 = 0;
        int i6 = 0;
        boolean z2 = false;
        boolean z3 = false;
        int i7 = 0;
        int i8 = 0;
        while (i5 < length) {
            SampleSource.SampleSourceReader sampleSourceReader = this.sources[i5];
            int trackCount2 = sampleSourceReader.getTrackCount();
            while (i2 < trackCount2) {
                MediaFormat format = sampleSourceReader.getFormat(i2);
                int i9 = length;
                boolean z4 = z2;
                if (format.mimeType.contains("video")) {
                    i7++;
                }
                if (format.mimeType.contains("audio")) {
                    i8++;
                }
                boolean z5 = format.mimeType.contains("realvideo") ? true : z4;
                if (format.mimeType.contains("divx")) {
                    z3 = true;
                }
                try {
                    if (handlesTrack(format)) {
                        iArr[i6] = i5;
                        iArr2[i6] = i2;
                        if (!format.mimeType.equals("video/x-unknown") && !format.mimeType.equals("audio/x-unknown")) {
                            i6++;
                        }
                        if (jMax == -1) {
                            i = i6;
                        } else {
                            i = i6;
                            long j2 = format.durationUs;
                            if (j2 == -1) {
                                i6 = i;
                                jMax = -1;
                            } else if (j2 != -2) {
                                jMax = Math.max(jMax, j2);
                            }
                        }
                        i6 = i;
                    }
                    i2++;
                    length = i9;
                    z2 = z5;
                } catch (MediaCodecUtil.DecoderQueryException e) {
                    throw new ExoPlaybackException(e);
                }
            }
            i5++;
            i2 = 0;
        }
        if ((this instanceof MediaCodecVideoTrackRenderer) && i6 == 0) {
            if (z2) {
                throw new ExoPlaybackException(new Exception("Realvideo not support."), 0);
            }
            if (z3) {
                throw new ExoPlaybackException(new Exception("Divx not support."), 2);
            }
            if (i7 == 1 && i8 > 0) {
                throw new ExoPlaybackException(new Exception("no available video track."), 0);
            }
        }
        if (this instanceof MediaCodecAudioTrackRenderer) {
            z = true;
            if (i8 == 1 && i7 > 0 && i6 == 0) {
                throw new ExoPlaybackException(new Exception("no available audio track"), 1);
            }
        } else {
            z = true;
        }
        this.durationUs = jMax;
        this.handledSourceIndices = Arrays.copyOf(iArr, i6);
        this.handledSourceTrackIndices = Arrays.copyOf(iArr2, i6);
        return z;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void onEnabled(int i, long j, boolean z) throws ExoPlaybackException {
        shiftInputPosition(j);
        SampleSource.SampleSourceReader sampleSourceReader = this.sources[this.handledSourceIndices[i]];
        this.enabledSource = sampleSourceReader;
        int i2 = this.handledSourceTrackIndices[i];
        this.enabledSourceTrackIndex = i2;
        sampleSourceReader.enable(i2, j);
        if (!(this.enabledSource instanceof NonBlockingFrameworkSampleSource) || MimeTypes.isVideo(getFormat(i).mimeType)) {
            this.lastSeekPositionUs = j;
        }
        onDiscontinuity(j);
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void seekTo(long j) throws ExoPlaybackException {
        this.lastSeekPositionUs = this.speed > 3 ? -1L : j;
        shiftInputPosition(j);
        this.enabledSource.seekToUs(j);
        checkForDiscontinuity(j);
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected final void doSomeWork(long j, long j2) throws ExoPlaybackException {
        shiftInputPosition(j);
        doSomeWork(checkForDiscontinuity(j), j2, this.enabledSource.continueBuffering(this.enabledSourceTrackIndex, j));
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected long getBufferedPositionUs() {
        return this.enabledSource.getBufferedPositionUs();
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected long getDurationUs() {
        return this.durationUs;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void maybeThrowError() throws ExoPlaybackException {
        SampleSource.SampleSourceReader sampleSourceReader = this.enabledSource;
        if (sampleSourceReader != null) {
            maybeThrowError(sampleSourceReader);
            return;
        }
        int length = this.sources.length;
        for (int i = 0; i < length; i++) {
            maybeThrowError(this.sources[i]);
        }
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void onDisabled(long j) throws ExoPlaybackException {
        SampleSource.SampleSourceReader sampleSourceReader = this.enabledSource;
        if (sampleSourceReader instanceof NonBlockingFrameworkSampleSource) {
            ((NonBlockingFrameworkSampleSource) sampleSourceReader).disable(this.enabledSourceTrackIndex, j);
        } else {
            sampleSourceReader.disable(this.enabledSourceTrackIndex);
        }
        this.enabledSource = null;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void onReleased() throws ExoPlaybackException {
        int length = this.sources.length;
        for (int i = 0; i < length; i++) {
            this.sources[i].release();
        }
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected final int getTrackCount() {
        return this.handledSourceTrackIndices.length;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected final MediaFormat getFormat(int i) {
        return this.sources[this.handledSourceIndices[i]].getFormat(this.handledSourceTrackIndices[i]);
    }

    protected final int readSource(long j, MediaFormatHolder mediaFormatHolder, SampleHolder sampleHolder) {
        return this.enabledSource.readData(this.enabledSourceTrackIndex, j, mediaFormatHolder, sampleHolder);
    }

    private long checkForDiscontinuity(long j) throws ExoPlaybackException {
        long discontinuity = this.enabledSource.readDiscontinuity(this.enabledSourceTrackIndex);
        if (discontinuity == Long.MIN_VALUE) {
            return j;
        }
        if (this.enabledSource instanceof NonBlockingFrameworkSampleSource) {
            if (this.speed > 3) {
                this.lastSeekPositionUs = -1L;
            } else {
                this.lastSeekPositionUs = j;
            }
            onDiscontinuity(j);
        } else {
            if (this.speed > 3) {
                j = -1;
            }
            this.lastSeekPositionUs = j;
            onDiscontinuity(discontinuity);
        }
        return discontinuity;
    }

    private void maybeThrowError(SampleSource.SampleSourceReader sampleSourceReader) throws ExoPlaybackException {
        try {
            sampleSourceReader.maybeThrowError();
        } catch (IOException e) {
            throw new ExoPlaybackException(e);
        }
    }

    @Override // com.google.android.exoplayer.TrackRenderer, com.google.android.exoplayer.ExoPlayer.ExoPlayerComponent
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1002) {
            Map map = (Map) obj;
            Boolean bool = (Boolean) map.get("enableIFrameMode");
            if (map.get("rendererSpeed") != null) {
                this.speed = ((Integer) map.get("rendererSpeed")).intValue();
            }
            if (bool != null) {
                if (bool.booleanValue()) {
                    this.enabledSource.setTrickMode(true);
                    this.onlyPushIFrame = true;
                    return;
                } else {
                    this.enabledSource.setTrickMode(false);
                    this.onlyPushIFrame = false;
                    return;
                }
            }
            return;
        }
        if (i == 1004) {
            if (obj != null) {
                SampleSource.SampleSourceReader[] sampleSourceReaderArr = this.sources;
                SampleSource.SampleSourceReader[] sampleSourceReaderArr2 = new SampleSource.SampleSourceReader[sampleSourceReaderArr.length + 1];
                System.arraycopy(sampleSourceReaderArr, 0, sampleSourceReaderArr2, 0, sampleSourceReaderArr.length);
                sampleSourceReaderArr2[this.sources.length] = ((SampleSource) obj).register();
                this.sources = sampleSourceReaderArr2;
                doPrepare(0L);
                return;
            }
            return;
        }
        if (i == 1006) {
            if (obj != null) {
                this.isVideoOnly = ((Boolean) obj).booleanValue();
            }
        } else if (i == 1007) {
            this.playState = ((Integer) obj).intValue();
        } else {
            super.handleMessage(i, obj);
        }
    }

    protected boolean isVideoOnly() {
        return this.isVideoOnly;
    }

    public void setLastSeekPositionUs(long j) {
        this.lastSeekPositionUs = j;
    }
}
