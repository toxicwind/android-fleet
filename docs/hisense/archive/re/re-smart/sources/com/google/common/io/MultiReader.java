package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public class MultiReader extends Reader {
    public Reader current;
    public final Iterator<? extends CharSource> it;

    public MultiReader(Iterator<? extends CharSource> it) {
        this.it = it;
        advance();
    }

    private void advance() {
        close();
        if (this.it.hasNext()) {
            this.current = this.it.next().openStream();
        }
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Reader reader = this.current;
        if (reader != null) {
            try {
                reader.close();
            } finally {
                this.current = null;
            }
        }
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        Reader reader = this.current;
        if (reader == null) {
            return -1;
        }
        int i3 = reader.read(cArr, i, i2);
        if (i3 != -1) {
            return i3;
        }
        advance();
        return read(cArr, i, i2);
    }

    @Override // java.io.Reader
    public boolean ready() {
        Reader reader = this.current;
        return reader != null && reader.ready();
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        Preconditions.checkArgument(j >= 0, "n is negative");
        if (j > 0) {
            while (true) {
                Reader reader = this.current;
                if (reader == null) {
                    break;
                }
                long jSkip = reader.skip(j);
                if (jSkip > 0) {
                    return jSkip;
                }
                advance();
            }
        }
        return 0L;
    }
}
