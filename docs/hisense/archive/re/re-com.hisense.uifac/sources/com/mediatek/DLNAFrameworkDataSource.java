package com.mediatek;

import android.os.Looper;
import android.util.Log;
import com.google.android.exoplayer.upstream.FrameworkDataSource;
import com.google.android.exoplayer.upstream.Loader;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.mediatek.dlna.object.ContentInputStream;
import com.mediatek.dlna.object.ContentInputStream.MediaInfo;
import com.mediatek.mmp.util.PcmMediaInfo;
import com.mediatek.twoworlds.tv.MtkTvTimeFormatBase;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class DLNAFrameworkDataSource extends FrameworkDataSource implements Loader.Callback {
    private volatile int currentStatus;
    private long fileSizeMax;
    private boolean isCompleted;
    private boolean isDlna;
    private final boolean isPcmAudio;
    private boolean isStallingSupport;
    private volatile long lastPositionUs;
    private DLNAStreamLoadable loadable;
    private Loader loader;
    private final Lock loaderLock;
    private final FrameworkDataSource.StreamDataSource mDataSource;
    private int mDuration;
    private long mFileSize;
    private InputStream mInputStream;
    private int mSeekType;
    private final Looper myLooper;
    private byte[] wavHeaderForPcm;

    private byte[] convLongToByteArray(long j) {
        return new byte[]{(byte) (j >>> 56), (byte) (j >>> 48), (byte) (j >>> 40), (byte) (j >>> 32), (byte) (j >>> 24), (byte) (j >>> 16), (byte) (j >>> 8), (byte) j};
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadError(Loader.Loadable loadable, IOException iOException) {
    }

    public DLNAFrameworkDataSource(FrameworkDataSource.StreamDataSource streamDataSource, PcmMediaInfo pcmMediaInfo) {
        super(streamDataSource);
        this.fileSizeMax = 2147483647L;
        this.mSeekType = 3;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.loaderLock = reentrantLock;
        reentrantLock.newCondition();
        this.mDataSource = streamDataSource;
        this.isCompleted = false;
        this.isPcmAudio = pcmMediaInfo != null;
        this.currentStatus = 0;
        Log.i("DLNAFrameworkDataSource", "isPcmAudio = " + this.isPcmAudio);
        if (this.isPcmAudio) {
            genWavHeaderForPcmAudio(pcmMediaInfo);
        }
        boolean z = this.isPcmAudio;
        long size = streamDataSource.getSize();
        this.mFileSize = z ? size + 44 : size;
        Log.i("DLNAFrameworkDataSource", "fileSize = " + this.mFileSize);
        if (this.mFileSize == 0) {
            this.mFileSize = this.fileSizeMax;
            Log.i("DLNAFrameworkDataSource", "fileSize Change to MAX: " + this.mFileSize);
        }
        this.loader = new Loader("Loader: Input Stream");
        this.myLooper = Looper.myLooper();
        InputStream inputStreamNewInputStream = streamDataSource.newInputStream();
        this.mInputStream = inputStreamNewInputStream;
        if (inputStreamNewInputStream instanceof ContentInputStream) {
            try {
                Log.i("DLNAFrameworkDataSource", "it is DLNA case");
                this.isDlna = true;
                this.isStallingSupport = ((ContentInputStream) this.mInputStream).getstallingsupport();
                Log.i("DLNAFrameworkDataSource", "isStallingSupport = " + this.isStallingSupport);
                ContentInputStream contentInputStream = (ContentInputStream) this.mInputStream;
                Objects.requireNonNull(contentInputStream);
                ContentInputStream.MediaInfo mediaInfo = contentInputStream.new MediaInfo();
                if (mediaInfo.getDuration() != null) {
                    String[] strArrSplit = mediaInfo.getDuration().split(":");
                    Log.i("DLNAFrameworkDataSource", "getDuration(string mode) = " + mediaInfo.getDuration());
                    this.mDuration = (Integer.parseInt(strArrSplit[0]) * MtkTvTimeFormatBase.SECONDS_PER_HOUR) + (Integer.parseInt(strArrSplit[1]) * 60) + Integer.parseInt(strArrSplit[2]);
                    Log.i("DLNAFrameworkDataSource", "duration(s) = " + this.mDuration);
                } else {
                    this.mDuration = 0;
                    Log.w("DLNAFrameworkDataSource", "getDuration(string mode) return null, duration = 0");
                }
                int i = ((ContentInputStream) this.mInputStream).getseektype();
                if (i == 0) {
                    this.mSeekType = 0;
                    Log.d("DLNAFrameworkDataSource", "seekType is DLNA_SEEK_TYPE_NONSUPPORTED");
                } else if (i == 1) {
                    this.mSeekType = 1;
                    Log.d("DLNAFrameworkDataSource", "seekType is DLNA_SEEK_TYPE_BYTE_ONLY");
                } else if (i == 2) {
                    this.mSeekType = 2;
                    Log.d("DLNAFrameworkDataSource", "seekType is DLNA_SEEK_TYPE_TIME_ONLY");
                } else if (i == 3) {
                    this.mSeekType = 3;
                    Log.d("DLNAFrameworkDataSource", "seekType is DLNA_SEEK_TYPE_BYTE_AND_TIME");
                } else if (i != 4 && i != 8 && i != 12 && i == 13) {
                    this.mSeekType = 13;
                    Log.d("DLNAFrameworkDataSource", "seekType is DLNA_SEEK_TYPE_UNKNOWN");
                } else {
                    this.mSeekType = 0;
                    Log.d("DLNAFrameworkDataSource", "seekType is DLNA_SEEK_TYPE_NONSUPPORTED");
                }
                Log.d("DLNAFrameworkDataSource", "seekType = " + this.mSeekType);
                return;
            } catch (Exception e) {
                Log.e("DLNAFrameworkDataSource", "getSeekType(): call mInputStream.getstallingsupport(), exception happened!" + e.toString());
                return;
            }
        }
        Log.i("DLNAFrameworkDataSource", "it is default case");
        this.isStallingSupport = true;
        this.isDlna = false;
    }

    @Override // com.google.android.exoplayer.upstream.FrameworkDataSource
    protected int readAtV22(long j, byte[] bArr, int i) {
        synchronized (this) {
            if (this.currentStatus == -1) {
                MtkDebugLogUtil.log("DLNAFrameworkDataSource", "readAtV22, return -1, currentStatus = " + this.currentStatus, 64);
                return -1;
            }
            if (j == this.mFileSize - 1 && i >= 16) {
                Log.d("DLNAFrameworkDataSource", "offset == fileSize - 1 && size >= 16, get duration");
                if (!this.isDlna) {
                    return 0;
                }
                System.arraycopy(new byte[]{100, 117, 114, 97, 116, 105, 111, 110}, 0, bArr, 0, 8);
                System.arraycopy(convLongToByteArray(((long) this.mDuration) * 1000), 0, bArr, 8, 8);
                return 16;
            }
            return readAtInternal(j, bArr, i);
        }
    }

    @Override // com.google.android.exoplayer.upstream.FrameworkDataSource
    protected int readAtV23(long j, byte[] bArr, int i, int i2) {
        int i3;
        synchronized (this) {
            if (this.currentStatus == -1) {
                MtkDebugLogUtil.log("DLNAFrameworkDataSource", "readAtV23, return -1, currentStatus = " + this.currentStatus, 64);
                return -1;
            }
            if (j == this.mFileSize - 1 && i2 >= 16) {
                Log.d("DLNAFrameworkDataSource", "position == fileSize - 1 && size >= 16, get duration");
                if (!this.isDlna) {
                    return 0;
                }
                if (this.mDuration == 0 && ((i3 = this.mSeekType) == 1 || i3 == 3)) {
                    return 0;
                }
                System.arraycopy(new byte[]{100, 117, 114, 97, 116, 105, 111, 110}, 0, bArr, 0, 8);
                System.arraycopy(convLongToByteArray(((long) this.mDuration) * 1000), 0, bArr, 8, 8);
                return 16;
            }
            byte[] bArr2 = new byte[i2];
            long jCurrentTimeMillis = System.currentTimeMillis();
            MtkDebugLogUtil.log("DLNAFrameworkDataSource", "+readAtV23, position=" + j + ", offset=" + i + ", size=" + i2, 64);
            int atInternal = readAtInternal(j, bArr2, i2);
            MtkDebugLogUtil.log("DLNAFrameworkDataSource", "-readAtV23, position=" + j + ", offset=" + i + ", size=" + i2 + ", cost(ms)=" + (System.currentTimeMillis() - jCurrentTimeMillis), 64);
            if (atInternal > 0) {
                System.arraycopy(bArr2, 0, bArr, i, atInternal);
            }
            return atInternal;
        }
    }

    private int readAtInternal(long j, byte[] bArr, int i) {
        if (this.mInputStream == null) {
            InputStream inputStreamNewInputStream = this.mDataSource.newInputStream();
            this.mInputStream = inputStreamNewInputStream;
            Assertions.checkArgument(inputStreamNewInputStream != null, "newInputStream fail!");
        }
        if (this.loader == null) {
            this.loader = new Loader("Loader: Input Stream");
        }
        if (this.loadable == null) {
            this.loadable = new DLNAStreamLoadable(this.mInputStream, this.mDataSource, this.mFileSize, this.lastPositionUs);
            this.lastPositionUs = 0L;
            this.loader.startLoading(this.myLooper, this.loadable, this);
        }
        if (j >= this.mFileSize) {
            Log.i("DLNAFrameworkDataSource", "offset:" + j);
            Log.i("DLNAFrameworkDataSource", "fileSize:" + this.mFileSize);
            Log.i("DLNAFrameworkDataSource", "offset >= mFileSize return 001");
            return -1;
        }
        if (this.isCompleted) {
            InputStream inputStream = this.mInputStream;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.mInputStream = this.mDataSource.newInputStream();
            if (this.loadable != null) {
                this.loadable = new DLNAStreamLoadable(this.mInputStream, this.mDataSource, this.mFileSize, this.lastPositionUs);
                this.lastPositionUs = 0L;
            }
            this.loader.startLoading(this.myLooper, this.loadable, this);
            this.isCompleted = false;
        }
        if (!this.isPcmAudio) {
            return this.loadable.readAt(j, bArr, i);
        }
        if (j >= 44) {
            return this.loadable.readAt(j - 44, bArr, i);
        }
        if (i <= 44 - j) {
            System.arraycopy(this.wavHeaderForPcm, (int) j, bArr, 0, i);
            return i;
        }
        int i2 = (int) j;
        int i3 = 44 - i2;
        int i4 = i - i3;
        byte[] bArr2 = new byte[i4];
        int at = this.loadable.readAt(0L, bArr2, i4);
        if (at < 0) {
            return at;
        }
        System.arraycopy(this.wavHeaderForPcm, i2, bArr, 0, i3);
        System.arraycopy(bArr2, 0, bArr, i3, at);
        return i3 + at;
    }

    @Override // com.google.android.exoplayer.upstream.FrameworkDataSource
    protected long getSize() {
        Log.i("DLNAFrameworkDataSource", "call getSize(), size = " + this.mFileSize);
        if (this.mFileSize == 0) {
            this.mFileSize = this.fileSizeMax;
            Log.i("DLNAFrameworkDataSource", "fileSize Change to MAX: " + this.mFileSize);
        }
        return this.mFileSize;
    }

    @Override // com.google.android.exoplayer.upstream.FrameworkDataSource
    protected void close() {
        Log.i("DLNAFrameworkDataSource", "close");
        closeInternal();
    }

    private void closeInternal() {
        Loader loader = this.loader;
        if (loader != null && loader.isLoading() && this.loadable != null) {
            this.loader.cancelLoading();
            this.loadable = null;
            this.loader = null;
        }
        InputStream inputStream = this.mInputStream;
        if (inputStream != null) {
            try {
                inputStream.close();
                this.mInputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void pauseInternal() {
        Loader loader = this.loader;
        if (loader != null) {
            loader.isLoading();
        }
    }

    protected void resumeInternal() {
        Loader loader = this.loader;
        if (loader != null) {
            loader.isLoading();
        }
    }

    @Override // com.google.android.exoplayer.upstream.FrameworkDataSource
    public void seekTo(long j) {
        Log.i("DLNAFrameworkDataSource", "seekTo: " + j + "(Us) for DLNA");
        if (this.mInputStream == null) {
            InputStream inputStreamNewInputStream = this.mDataSource.newInputStream();
            this.mInputStream = inputStreamNewInputStream;
            Assertions.checkArgument(inputStreamNewInputStream != null, "newInputStream fail!");
        }
        if (this.loader == null) {
            this.loader = new Loader("Loader: Input Stream");
        }
        if (this.loadable == null) {
            DLNAStreamLoadable dLNAStreamLoadable = new DLNAStreamLoadable(this.mInputStream, this.mDataSource, this.mFileSize, j);
            this.loadable = dLNAStreamLoadable;
            this.loader.startLoading(this.myLooper, dLNAStreamLoadable, this);
        }
        this.currentStatus = 0;
        this.loadable.seekTo(j);
    }

    @Override // com.google.android.exoplayer.upstream.FrameworkDataSource
    public void tryDisconnect() {
        Log.i("DLNAFrameworkDataSource", "begin call tryDisconnect");
        if (this.isStallingSupport) {
            pauseInternal();
        } else {
            this.currentStatus = -1;
            closeInternal();
        }
        Log.i("DLNAFrameworkDataSource", "end call tryDisconnect, currentStatus = " + this.currentStatus);
    }

    @Override // com.google.android.exoplayer.upstream.FrameworkDataSource
    public void tryResume(long j) {
        Log.i("DLNAFrameworkDataSource", "call tryResume, positionUs = " + j);
        this.lastPositionUs = j;
        if (this.mInputStream == null) {
            InputStream inputStreamNewInputStream = this.mDataSource.newInputStream();
            this.mInputStream = inputStreamNewInputStream;
            Assertions.checkArgument(inputStreamNewInputStream != null, "newInputStream fail!");
        } else {
            resumeInternal();
        }
        if (this.loader == null) {
            this.loader = new Loader("Loader: Input Stream");
        }
        if (this.loadable == null) {
            this.loadable = new DLNAStreamLoadable(this.mInputStream, this.mDataSource, this.mFileSize, this.lastPositionUs);
            this.lastPositionUs = 0L;
            this.loader.startLoading(this.myLooper, this.loadable, this);
        }
        this.currentStatus = 0;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadCanceled(Loader.Loadable loadable) {
        Log.i("DLNAFrameworkDataSource", "onLoadCanceled");
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadCompleted(Loader.Loadable loadable) {
        Log.i("DLNAFrameworkDataSource", "onLoadCompleted");
        this.isCompleted = true;
    }

    private void genWavHeaderForPcmAudio(PcmMediaInfo pcmMediaInfo) {
        this.wavHeaderForPcm = new byte[44];
        PcmMediaInfo.AudioPcmInfo audioPcmInfo = pcmMediaInfo.getAudioPcmInfo();
        System.arraycopy(new byte[]{82, 73, 70, 70}, 0, this.wavHeaderForPcm, 0, 4);
        int i = ((int) pcmMediaInfo.size) + 36;
        System.arraycopy(new byte[]{(byte) (i & 255), (byte) ((i & 65280) >>> 8), (byte) ((i & 16711680) >>> 16), (byte) (i >>> 24)}, 0, this.wavHeaderForPcm, 4, 4);
        System.arraycopy(new byte[]{87, 65, 86, 69}, 0, this.wavHeaderForPcm, 8, 4);
        System.arraycopy(new byte[]{102, 109, 116, 32}, 0, this.wavHeaderForPcm, 12, 4);
        System.arraycopy(new byte[]{16, 0, 0, 0}, 0, this.wavHeaderForPcm, 16, 4);
        System.arraycopy(new byte[]{0, 4}, 0, this.wavHeaderForPcm, 20, 2);
        int i2 = audioPcmInfo.channelNumber;
        System.arraycopy(new byte[]{(byte) i2, 0}, 0, this.wavHeaderForPcm, 22, 2);
        int iConvertSampleRateToValue = convertSampleRateToValue(audioPcmInfo.sampleRate);
        System.arraycopy(new byte[]{(byte) (iConvertSampleRateToValue & 255), (byte) ((iConvertSampleRateToValue & 65280) >>> 8), (byte) ((iConvertSampleRateToValue & 16711680) >>> 16), (byte) (iConvertSampleRateToValue >>> 24)}, 0, this.wavHeaderForPcm, 24, 4);
        int iConvertBitsPerSampleToValue = convertBitsPerSampleToValue(audioPcmInfo.bitsPerSample);
        int i3 = ((iConvertSampleRateToValue * i2) * iConvertBitsPerSampleToValue) / 8;
        System.arraycopy(new byte[]{(byte) (i3 & 255), (byte) ((i3 & 65280) >>> 8), (byte) ((16711680 & i3) >>> 16), (byte) (i3 >>> 24)}, 0, this.wavHeaderForPcm, 28, 4);
        short s = audioPcmInfo.blockAlign;
        System.arraycopy(new byte[]{(byte) (s & 255), (byte) ((s & 65280) >>> 8)}, 0, this.wavHeaderForPcm, 32, 2);
        System.arraycopy(new byte[]{(byte) (iConvertBitsPerSampleToValue & 255), (byte) ((iConvertBitsPerSampleToValue & 65280) >>> 8)}, 0, this.wavHeaderForPcm, 34, 2);
        System.arraycopy(new byte[]{100, 97, 116, 97}, 0, this.wavHeaderForPcm, 36, 4);
        long j = pcmMediaInfo.size;
        System.arraycopy(new byte[]{(byte) (255 & j), (byte) ((65280 & j) >>> 8), (byte) ((16711680 & j) >>> 16), (byte) (j >>> 24)}, 0, this.wavHeaderForPcm, 40, 4);
    }

    private int convertSampleRateToValue(int i) {
        switch (i) {
            case 1:
                return 8000;
            case 2:
                return 16000;
            case 3:
                return 32000;
            case 4:
                return 11025;
            case 5:
                return 22050;
            case 6:
                return 44100;
            case 7:
                return 12000;
            case 8:
                return 24000;
            case 9:
                return 48000;
            case 10:
                return 96000;
            case 11:
                return 192000;
            default:
                Log.e("DLNAFrameworkDataSource", "SampleRate is error! sampleRate = " + i);
                return 48000;
        }
    }

    private int convertBitsPerSampleToValue(int i) {
        if (i == 1) {
            return 8;
        }
        if (i == 2) {
            return 16;
        }
        if (i == 3) {
            return 20;
        }
        if (i == 4) {
            return 24;
        }
        Log.e("DLNAFrameworkDataSource", "bitsPerSample is error! bitsPerSample = " + i);
        return 16;
    }
}
