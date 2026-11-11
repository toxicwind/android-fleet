package org.jsoup.internal;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Supplier;
import org.jsoup.helper.Validate;

/* JADX INFO: loaded from: classes8.dex */
class SimpleBufferedInput extends FilterInputStream implements InputStreamRetargetInterface {
    static final SoftPool<byte[]> BufferPool = new SoftPool<>(new Supplier() { // from class: org.jsoup.internal.SimpleBufferedInput$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            return SimpleBufferedInput.lambda$static$0();
        }
    });
    static final int BufferSize = 8192;
    private int bufLength;
    private int bufMark;
    private int bufPos;
    private byte[] byteBuf;
    private int capRemaining;
    private boolean inReadFully;

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public /* synthetic */ long transferTo(OutputStream outputStream) {
        return DesugarInputStream.transferTo(this, outputStream);
    }

    static /* synthetic */ byte[] lambda$static$0() {
        return new byte[8192];
    }

    SimpleBufferedInput(InputStream inputStream) {
        super(inputStream);
        this.capRemaining = Integer.MAX_VALUE;
        this.bufMark = -1;
        this.inReadFully = false;
        if (inputStream == null) {
            this.inReadFully = true;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.bufPos >= this.bufLength) {
            fill();
            if (this.bufPos >= this.bufLength) {
                return -1;
            }
        }
        byte[] buf = getBuf();
        int i = this.bufPos;
        this.bufPos = i + 1;
        return buf[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        Validate.notNull(bArr);
        if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        int i3 = this.bufLength - this.bufPos;
        if (i3 <= 0) {
            fill();
            i3 = this.bufLength - this.bufPos;
        }
        int iMin = Math.min(i3, i2);
        if (iMin <= 0) {
            return -1;
        }
        System.arraycopy(getBuf(), this.bufPos, bArr, i, iMin);
        this.bufPos += iMin;
        return iMin;
    }

    private void fill() throws IOException {
        int iMin;
        if (this.inReadFully) {
            return;
        }
        if (this.byteBuf == null) {
            this.byteBuf = BufferPool.borrow();
        }
        compact();
        int i = this.bufPos;
        this.bufLength = i;
        int iMin2 = Math.min(this.byteBuf.length - i, this.capRemaining);
        if (iMin2 <= 0) {
            return;
        }
        int i2 = this.in.read(this.byteBuf, this.bufPos, iMin2);
        if (i2 > 0) {
            this.bufLength = this.bufPos + i2;
            this.capRemaining -= i2;
            while (this.byteBuf.length - this.bufLength > 0 && this.capRemaining > 0) {
                try {
                    if (this.in.available() < 1 || (iMin = Math.min(this.byteBuf.length - this.bufLength, this.capRemaining)) <= 0 || (i2 = this.in.read(this.byteBuf, this.bufLength, iMin)) <= 0) {
                        break;
                    }
                    this.bufLength += i2;
                    this.capRemaining -= i2;
                } catch (IOException unused) {
                }
            }
        }
        if (i2 == -1) {
            this.inReadFully = true;
        }
    }

    byte[] getBuf() {
        Validate.notNull(this.byteBuf);
        return this.byteBuf;
    }

    boolean baseReadFully() {
        return this.inReadFully;
    }

    void resetFullyRead() {
        if (this.in != null) {
            this.inReadFully = false;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        int i = this.byteBuf != null ? this.bufLength - this.bufPos : 0;
        if (i > 0) {
            return i;
        }
        if (this.inReadFully) {
            return 0;
        }
        return this.in.available();
    }

    void capRemaining(int i) {
        this.capRemaining = Math.max(0, i);
    }

    void setMark() {
        this.bufMark = this.bufPos;
    }

    void rewindToMark() throws IOException {
        int i = this.bufMark;
        if (i < 0) {
            throw new IOException("Resetting to invalid mark");
        }
        this.bufPos = i;
    }

    void clearMark() {
        this.bufMark = -1;
    }

    private void compact() {
        int i;
        byte[] bArr = this.byteBuf;
        if (bArr == null || (i = this.bufPos) == 0) {
            return;
        }
        int i2 = this.bufMark;
        if (i2 >= 0) {
            i = i2;
        }
        if (i <= 0) {
            return;
        }
        int i3 = this.bufLength - i;
        if (i3 > 0) {
            System.arraycopy(bArr, i, bArr, 0, i3);
        }
        this.bufLength = i3;
        this.bufPos -= i;
        int i4 = this.bufMark;
        if (i4 >= 0) {
            this.bufMark = i4 - i;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.in != null) {
            super.close();
        }
        byte[] bArr = this.byteBuf;
        if (bArr == null) {
            return;
        }
        BufferPool.release(bArr);
        this.byteBuf = null;
    }
}
