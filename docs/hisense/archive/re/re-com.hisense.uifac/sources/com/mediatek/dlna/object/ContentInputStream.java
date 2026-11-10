package com.mediatek.dlna.object;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class ContentInputStream extends InputStream {
    private Content content;
    private long current;
    private long handle;

    private native int nativeDmpAbort(long j);

    private native long nativeDmpByteSkip(long j, long j2);

    private native int nativeDmpClose(long j);

    private native int nativeDmpGetSeekType(long j);

    private native long nativeDmpOpen(String str, String str2, String str3, String str4, long j, int i, int i2, int i3, byte[] bArr);

    private native int nativeDmpRead(long j, byte[] bArr, int i, int i2);

    private native boolean nativeDmpStallingSupported(long j);

    private native int nativeDmpTimeSkip(long j, long j2, long j3);

    private native int nativeDmrAbort(long j);

    private native long nativeDmrByteSkip(long j, long j2);

    private native int nativeDmrClose(long j);

    private native int nativeDmrGetSeekType(long j);

    private native long nativeDmrOpen(String str, String str2);

    private native int nativeDmrRead(long j, byte[] bArr, int i, int i2);

    private native boolean nativeDmrStallingSupported(long j);

    private native boolean nativeDmrStart();

    private native int nativeDmrTimeSkip(long j, long j2, long j3);

    private boolean retry() {
        long jNativeDmrByteSkip;
        if (this.handle != 0) {
            if (!nativeDmrStart()) {
                nativeDmpClose(this.handle);
            } else {
                nativeDmrClose(this.handle);
            }
        }
        this.handle = 0L;
        if (!nativeDmrStart()) {
            this.handle = nativeDmpOpen(this.content.getResUri(), this.content.getMimeType(), this.content.getDtcpInfo(), this.content.getProtocolInfo(), this.content.getSize(), this.content.getFlag(), this.content.getMediaType(), this.content.getDrmType(), this.content.getSessionId());
        } else {
            this.handle = nativeDmrOpen(this.content.getResUri(), this.content.getProtocolInfo());
        }
        if (this.handle == 0) {
            Log.i("ContentInputStream", "error, handle is null");
            return false;
        }
        if (this.current == 0) {
            return true;
        }
        if (!nativeDmrStart()) {
            jNativeDmrByteSkip = nativeDmpByteSkip(this.handle, this.current);
        } else {
            jNativeDmrByteSkip = nativeDmrByteSkip(this.handle, this.current);
        }
        return jNativeDmrByteSkip == this.current;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        throw new IOException("Not Supported");
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        int iNativeDmrRead;
        if (this.handle == 0) {
            Log.i("ContentInputStream", "error, handle is null");
            throw new FileNotFoundException();
        }
        if (!nativeDmrStart()) {
            iNativeDmrRead = nativeDmpRead(this.handle, bArr, 0, bArr.length);
        } else {
            iNativeDmrRead = nativeDmrRead(this.handle, bArr, 0, bArr.length);
        }
        if (iNativeDmrRead > 0) {
            this.current += (long) iNativeDmrRead;
        } else if (iNativeDmrRead == -2) {
            int i = 0;
            while (i < 2) {
                if (retry()) {
                    if (!nativeDmrStart()) {
                        iNativeDmrRead = nativeDmpRead(this.handle, bArr, 0, bArr.length);
                    } else {
                        iNativeDmrRead = nativeDmrRead(this.handle, bArr, 0, bArr.length);
                    }
                    if (iNativeDmrRead > 0) {
                        this.current += (long) iNativeDmrRead;
                        break;
                    }
                }
                i++;
                try {
                    Thread.sleep(i * MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR);
                } catch (InterruptedException unused) {
                    Log.e("ContentInputStreaming", "sleep error!");
                }
            }
        }
        return iNativeDmrRead;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int iNativeDmrRead;
        if (this.handle == 0) {
            Log.i("ContentInputStream", "error, handle is null");
            throw new FileNotFoundException();
        }
        Log.i("ContentInputStream", "Read In");
        if (!nativeDmrStart()) {
            iNativeDmrRead = nativeDmpRead(this.handle, bArr, i, i2);
        } else {
            iNativeDmrRead = nativeDmrRead(this.handle, bArr, i, i2);
        }
        if (iNativeDmrRead > 0) {
            this.current += (long) iNativeDmrRead;
        } else if (iNativeDmrRead == -2) {
            int i3 = 0;
            while (i3 < 2) {
                if (retry()) {
                    if (!nativeDmrStart()) {
                        iNativeDmrRead = nativeDmpRead(this.handle, bArr, i, i2);
                    } else {
                        iNativeDmrRead = nativeDmrRead(this.handle, bArr, i, i2);
                    }
                    if (iNativeDmrRead > 0) {
                        this.current += (long) iNativeDmrRead;
                        break;
                    }
                }
                i3++;
                try {
                    Thread.sleep(i3 * MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR);
                } catch (InterruptedException unused) {
                    Log.e("ContentInputStreaming", "sleep error!");
                }
            }
        }
        Log.i("ContentInputStream", "Read Out");
        return iNativeDmrRead;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long jNativeDmrByteSkip;
        Log.i("skip", "come in");
        if (this.handle == 0) {
            Log.i("ContentInputStream", "error, handle is null");
            throw new FileNotFoundException();
        }
        if (!nativeDmrStart()) {
            jNativeDmrByteSkip = nativeDmpByteSkip(this.handle, j);
        } else {
            jNativeDmrByteSkip = nativeDmrByteSkip(this.handle, j);
        }
        if (jNativeDmrByteSkip <= 0) {
            return jNativeDmrByteSkip;
        }
        long j2 = jNativeDmrByteSkip - this.current;
        this.current = jNativeDmrByteSkip;
        return j2;
    }

    public int timeskip(long j, long j2) throws IOException {
        int iNativeDmrTimeSkip;
        if (this.handle == 0) {
            Log.i("ContentInputStream", "error, handle is null");
            throw new FileNotFoundException();
        }
        Log.i("ContentInputStream", "time seek");
        if (!nativeDmrStart()) {
            iNativeDmrTimeSkip = nativeDmpTimeSkip(this.handle, j, j2);
        } else {
            iNativeDmrTimeSkip = nativeDmrTimeSkip(this.handle, j, j2);
        }
        if (iNativeDmrTimeSkip != 0) {
            Log.e("ContentInputStream", "Time seek error");
        }
        return iNativeDmrTimeSkip;
    }

    public boolean getstallingsupport() throws IOException {
        if (this.handle == 0) {
            Log.i("ContentInputStream", "error, handle is null");
            throw new FileNotFoundException();
        }
        if (!nativeDmrStart()) {
            return nativeDmpStallingSupported(this.handle);
        }
        return nativeDmrStallingSupported(this.handle);
    }

    public int getseektype() throws IOException {
        Log.i("getseektype", "come in");
        if (this.handle == 0) {
            Log.i("ContentInputStream", "error, handle is null");
            throw new FileNotFoundException();
        }
        if (!nativeDmrStart()) {
            return nativeDmpGetSeekType(this.handle);
        }
        return nativeDmrGetSeekType(this.handle);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.content.getSize() != 0) {
            return (int) (this.content.getSize() - this.current);
        }
        return 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.handle == 0) {
            Log.i("ContentInputStream", "error, handle is null");
            throw new FileNotFoundException();
        }
        if (!nativeDmrStart()) {
            nativeDmpClose(this.handle);
        } else {
            nativeDmrClose(this.handle);
        }
        this.handle = 0L;
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        if (this.handle != 0) {
            if (!nativeDmrStart()) {
                nativeDmpClose(this.handle);
            } else {
                nativeDmrClose(this.handle);
            }
            this.handle = 0L;
        }
        if (!nativeDmrStart()) {
            this.handle = nativeDmpOpen(this.content.getResUri(), this.content.getMimeType(), this.content.getDtcpInfo(), this.content.getProtocolInfo(), this.content.getSize(), this.content.getFlag(), this.content.getMediaType(), this.content.getDrmType(), this.content.getSessionId());
        } else {
            this.handle = nativeDmrOpen(this.content.getResUri(), this.content.getProtocolInfo());
        }
        this.current = 0L;
        if (this.handle == 0) {
            throw new IOException("Not Supported");
        }
    }

    protected void finalize() throws Throwable {
        if (this.handle != 0) {
            if (!nativeDmrStart()) {
                nativeDmpClose(this.handle);
            } else {
                nativeDmrClose(this.handle);
            }
            this.handle = 0L;
        }
        super.finalize();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        super.mark(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return super.markSupported();
    }

    public void abort() throws IOException {
        if (this.handle == 0) {
            Log.i("ContentInputStream", "error, handle is null");
            return;
        }
        Log.i("ContentInputStream", "Abort In");
        if (!nativeDmrStart()) {
            nativeDmpAbort(this.handle);
        } else {
            nativeDmrAbort(this.handle);
        }
        Log.i("ContentInputStream", "Abort Out");
    }

    public class MediaInfo {
        private String bitrate;
        private String bitspersample;
        private String channels;
        private String colorDepth;
        private String duration;
        private String resolution;
        private String samplingfrequency;
        private long size;

        public MediaInfo() {
            this.size = ContentInputStream.this.content.getSize();
            this.samplingfrequency = ContentInputStream.this.content.getSampleFrequency();
            this.bitspersample = ContentInputStream.this.content.getBitsPerSample();
            this.channels = ContentInputStream.this.content.getNrAudioChannels();
            this.bitrate = ContentInputStream.this.content.getBitrate();
            this.resolution = ContentInputStream.this.content.getResolution();
            this.colorDepth = ContentInputStream.this.content.getColorDepth();
            String resDuration = ContentInputStream.this.content.getResDuration();
            this.duration = resDuration;
            if (resDuration != null) {
                this.duration = resDuration.replace(".", ":");
            }
            Log.i("MediaInfo", "MediaInfo as follows:");
            Log.i("MediaInfo", "size is " + Long.toString(this.size));
            Log.i("MediaInfo", "samplingfrequency is " + this.samplingfrequency);
            Log.i("MediaInfo", "bitspersample is " + this.bitspersample);
            Log.i("MediaInfo", "channels is " + this.channels);
            Log.i("MediaInfo", "bitrate is " + this.bitrate);
            Log.i("MediaInfo", "resolution is " + this.resolution);
            Log.i("MediaInfo", "colorDepth is " + this.colorDepth);
            Log.i("MediaInfo", "duration is " + this.duration);
        }

        public String getDuration() {
            return this.duration;
        }
    }
}
