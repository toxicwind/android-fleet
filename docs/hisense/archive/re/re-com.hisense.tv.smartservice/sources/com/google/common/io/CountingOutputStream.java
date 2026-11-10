package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class CountingOutputStream extends FilterOutputStream {
    public long count;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CountingOutputStream(OutputStream outputStream) {
        super(outputStream);
        if (outputStream == null) {
            throw new NullPointerException();
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ((FilterOutputStream) this).out.close();
    }

    public long getCount() {
        return this.count;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        ((FilterOutputStream) this).out.write(bArr, i, i2);
        this.count += (long) i2;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        ((FilterOutputStream) this).out.write(i);
        this.count++;
    }
}
