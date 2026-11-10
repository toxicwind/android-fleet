package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class LineReader {
    public final Readable readable;
    public final Reader reader;
    public final CharBuffer cbuf = CharStreams.createBuffer();
    public final char[] buf = this.cbuf.array();
    public final Queue<String> lines = new LinkedList();
    public final LineBuffer lineBuf = new LineBuffer() { // from class: com.google.common.io.LineReader.1
        @Override // com.google.common.io.LineBuffer
        public void handleLine(String str, String str2) {
            LineReader.this.lines.add(str);
        }
    };

    public LineReader(Readable readable) {
        if (readable == null) {
            throw new NullPointerException();
        }
        this.readable = readable;
        this.reader = readable instanceof Reader ? (Reader) readable : null;
    }

    @CanIgnoreReturnValue
    public String readLine() throws IOException {
        int i;
        while (this.lines.peek() == null) {
            this.cbuf.clear();
            Reader reader = this.reader;
            if (reader != null) {
                char[] cArr = this.buf;
                i = reader.read(cArr, 0, cArr.length);
            } else {
                i = this.readable.read(this.cbuf);
            }
            if (i == -1) {
                this.lineBuf.finish();
                break;
            }
            this.lineBuf.add(this.buf, 0, i);
        }
        return this.lines.poll();
    }
}
