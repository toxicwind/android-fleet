package com.google.android.exoplayer.text.pgs;

import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: classes.dex */
public class FileBuffer {
    private BufferedInputStream bis;
    private byte[] buf;
    private FileChannel fc;
    private FileInputStream fi;
    private String filename;
    private boolean isByte;
    private long length;
    private long offset;
    private long offsetEnd;

    public FileBuffer(InputStream inputStream) throws Exception {
        this.filename = null;
        this.isByte = false;
        this.length = 1048576L;
        this.buf = new byte[1048576];
        try {
            this.bis = new BufferedInputStream(inputStream);
            this.offset = 0L;
            readBuffer(0L);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("BufferedInputStream exception ~");
        }
    }

    public FileBuffer(byte[] bArr) throws Exception {
        this.filename = null;
        this.length = bArr.length;
        this.isByte = true;
        this.buf = bArr;
        this.offset = 0L;
        readBuffer(0L);
    }

    private void readBuffer(long j) throws Exception {
        int i;
        if (this.filename != null) {
            try {
                this.offset = j;
                this.fc.position(j);
                long j2 = this.length - j;
                if (j2 < 0) {
                    throw new Exception("Offset " + j + " out of bounds for file " + this.filename);
                }
                if (j2 < this.buf.length) {
                    i = this.fi.read(this.buf, 0, (int) j2);
                } else {
                    i = this.fi.read(this.buf, 0, this.buf.length);
                }
                this.offsetEnd = (((long) i) + j) - 1;
                MtkDebugLogUtil.log("FileBuffer", "numRead = " + i + "offset = " + j, 16);
                return;
            } catch (IOException unused) {
                throw new Exception("IO error at offset +" + j + " of file '" + this.filename + "'");
            } catch (IllegalArgumentException unused2) {
                throw new Exception("IO error at offset +" + j + " of file '" + this.filename + "'");
            }
        }
        BufferedInputStream bufferedInputStream = this.bis;
        if (bufferedInputStream != null) {
            try {
                this.offset = j;
                try {
                    bufferedInputStream.mark(((int) j) + 1048576);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                long jSkip = this.bis.skip(j);
                if (jSkip != j) {
                    throw new Exception("Skip " + j + " skip fail, actual skip " + jSkip);
                }
                int i2 = this.bis.read(this.buf, 0, 1048576);
                if (i2 < 1048576) {
                    this.length = ((long) i2) + j;
                } else {
                    this.length = ((long) (i2 * 2)) + j;
                }
                try {
                    this.bis.reset();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                this.offsetEnd = (((long) i2) + j) - 1;
                MtkDebugLogUtil.log("FileBuffer", "numRead = " + i2 + "offset = " + j + " offsetEnd = " + this.offsetEnd + "length = " + this.length, 16);
                return;
            } catch (IOException e3) {
                e3.printStackTrace();
                throw new Exception("IO error at offset +" + j + " of file '" + this.filename + "'");
            } catch (IllegalArgumentException e4) {
                e4.printStackTrace();
                throw new Exception("IO error at offset +" + j + " of file '" + this.filename + "'");
            }
        }
        if (this.isByte) {
            try {
                this.offset = j;
                if (this.length - j < 0) {
                    throw new Exception("Offset " + j + " out of bounds for file " + this.filename);
                }
                this.offsetEnd = this.length - 1;
                MtkDebugLogUtil.log("FileBuffer", "offset = " + j + " length = " + this.length, 16);
            } catch (IOException unused3) {
                throw new Exception("IO error at offset +" + j + " of file '" + this.filename + "'");
            } catch (IllegalArgumentException unused4) {
                throw new Exception("IO error at offset +" + j + " of file '" + this.filename + "'");
            }
        }
    }

    public int getByte(long j) throws Exception {
        if (j < this.offset || j > this.offsetEnd) {
            readBuffer(j);
        }
        return this.buf[(int) (j - this.offset)] & 255;
    }

    public int getWord(long j) throws Exception {
        if (j < this.offset || 1 + j > this.offsetEnd) {
            readBuffer(j);
        }
        int i = (int) (j - this.offset);
        byte[] bArr = this.buf;
        return ((bArr[i] & 255) << 8) | (bArr[i + 1] & 255);
    }

    public int getDWord(long j) throws Exception {
        if (j < this.offset || 3 + j > this.offsetEnd) {
            readBuffer(j);
        }
        int i = (int) (j - this.offset);
        byte[] bArr = this.buf;
        return ((bArr[i] & 255) << 24) | (bArr[i + 3] & 255) | ((bArr[i + 2] & 255) << 8) | ((bArr[i + 1] & 255) << 16);
    }

    public long getSize() {
        return this.length;
    }

    public void finalize() throws Throwable {
        BufferedInputStream bufferedInputStream = this.bis;
        if (bufferedInputStream != null) {
            bufferedInputStream.close();
        }
        FileChannel fileChannel = this.fc;
        if (fileChannel != null) {
            fileChannel.close();
        }
        FileInputStream fileInputStream = this.fi;
        if (fileInputStream != null) {
            fileInputStream.close();
        }
        super.finalize();
    }
}
