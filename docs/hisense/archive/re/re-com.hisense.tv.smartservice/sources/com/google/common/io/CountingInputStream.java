package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class CountingInputStream extends FilterInputStream {
    public long count;
    public long mark;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CountingInputStream(InputStream inputStream) {
        super(inputStream);
        if (inputStream == null) {
            throw new NullPointerException();
        }
        this.mark = -1L;
    }

    public long getCount() {
        return this.count;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        ((FilterInputStream) this).in.mark(i);
        this.mark = this.count;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i = ((FilterInputStream) this).in.read();
        if (i != -1) {
            this.count++;
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        if (!((FilterInputStream) this).in.markSupported()) {
            throw new IOException("Mark not supported");
        }
        if (this.mark == -1) {
            throw new IOException("Mark not set");
        }
        ((FilterInputStream) this).in.reset();
        this.count = this.mark;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long jSkip = ((FilterInputStream) this).in.skip(j);
        this.count += jSkip;
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = ((FilterInputStream) this).in.read(bArr, i, i2);
        if (i3 != -1) {
            this.count += (long) i3;
        }
        return i3;
    }
}
