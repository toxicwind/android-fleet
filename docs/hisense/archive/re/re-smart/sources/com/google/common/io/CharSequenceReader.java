package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class CharSequenceReader extends Reader {
    public int mark;
    public int pos;
    public CharSequence seq;

    public CharSequenceReader(CharSequence charSequence) {
        if (charSequence == null) {
            throw new NullPointerException();
        }
        this.seq = charSequence;
    }

    private void checkOpen() throws IOException {
        if (this.seq == null) {
            throw new IOException("reader closed");
        }
    }

    private boolean hasRemaining() {
        return remaining() > 0;
    }

    private int remaining() {
        return this.seq.length() - this.pos;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.seq = null;
    }

    @Override // java.io.Reader
    public synchronized void mark(int i) {
        Preconditions.checkArgument(i >= 0, "readAheadLimit (%s) may not be negative", i);
        checkOpen();
        this.mark = this.pos;
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.Reader, java.lang.Readable
    public synchronized int read(CharBuffer charBuffer) {
        try {
            if (charBuffer == null) {
                throw new NullPointerException();
            }
            checkOpen();
            if (!hasRemaining()) {
                return -1;
            }
            int iMin = Math.min(charBuffer.remaining(), remaining());
            for (int i = 0; i < iMin; i++) {
                CharSequence charSequence = this.seq;
                int i2 = this.pos;
                this.pos = i2 + 1;
                charBuffer.put(charSequence.charAt(i2));
            }
            return iMin;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.Reader
    public synchronized boolean ready() {
        checkOpen();
        return true;
    }

    @Override // java.io.Reader
    public synchronized void reset() {
        checkOpen();
        this.pos = this.mark;
    }

    @Override // java.io.Reader
    public synchronized long skip(long j) {
        int iMin;
        Preconditions.checkArgument(j >= 0, "n (%s) may not be negative", j);
        checkOpen();
        iMin = (int) Math.min(remaining(), j);
        this.pos += iMin;
        return iMin;
    }

    @Override // java.io.Reader
    public synchronized int read() {
        int iCharAt;
        checkOpen();
        if (hasRemaining()) {
            CharSequence charSequence = this.seq;
            int i = this.pos;
            this.pos = i + 1;
            iCharAt = charSequence.charAt(i);
        } else {
            iCharAt = -1;
        }
        return iCharAt;
    }

    @Override // java.io.Reader
    public synchronized int read(char[] cArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, cArr.length);
        checkOpen();
        if (!hasRemaining()) {
            return -1;
        }
        int iMin = Math.min(i2, remaining());
        for (int i3 = 0; i3 < iMin; i3++) {
            CharSequence charSequence = this.seq;
            int i4 = this.pos;
            this.pos = i4 + 1;
            cArr[i + i3] = charSequence.charAt(i4);
        }
        return iMin;
    }
}
