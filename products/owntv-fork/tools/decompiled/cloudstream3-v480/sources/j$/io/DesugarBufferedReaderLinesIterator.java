package j$.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes3.dex */
class DesugarBufferedReaderLinesIterator implements Iterator {
    private final BufferedReader bufferedReader;
    String nextLine = null;

    DesugarBufferedReaderLinesIterator(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.nextLine != null) {
            return true;
        }
        try {
            String line = this.bufferedReader.readLine();
            this.nextLine = line;
            return line != null;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override // java.util.Iterator
    public String next() {
        if (this.nextLine != null || hasNext()) {
            String str = this.nextLine;
            this.nextLine = null;
            return str;
        }
        throw new NoSuchElementException();
    }
}
