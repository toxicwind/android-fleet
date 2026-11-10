package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class CharStreams {
    public static final int DEFAULT_BUF_SIZE = 2048;

    private static final class NullWriter extends Writer {
        public static final NullWriter INSTANCE = new NullWriter();

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(char c2) {
            return this;
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence charSequence) {
            return this;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        public String toString() {
            return "CharStreams.nullWriter()";
        }

        @Override // java.io.Writer
        public void write(int i) {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2 + i, cArr.length);
        }

        @Override // java.io.Writer
        public void write(String str, int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2 + i, str.length());
        }

        @Override // java.io.Writer
        public void write(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence charSequence, int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, charSequence == null ? 4 : charSequence.length());
            return this;
        }

        @Override // java.io.Writer
        public void write(char[] cArr) {
            if (cArr == null) {
                throw new NullPointerException();
            }
        }
    }

    @Beta
    public static Writer asWriter(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new AppendableWriter(appendable);
    }

    @CanIgnoreReturnValue
    public static long copy(Readable readable, Appendable appendable) throws IOException {
        if (readable instanceof Reader) {
            return appendable instanceof StringBuilder ? copyReaderToBuilder((Reader) readable, (StringBuilder) appendable) : copyReaderToWriter((Reader) readable, asWriter(appendable));
        }
        if (readable == null) {
            throw new NullPointerException();
        }
        if (appendable == null) {
            throw new NullPointerException();
        }
        long jRemaining = 0;
        CharBuffer charBufferCreateBuffer = createBuffer();
        while (readable.read(charBufferCreateBuffer) != -1) {
            charBufferCreateBuffer.flip();
            appendable.append(charBufferCreateBuffer);
            jRemaining += (long) charBufferCreateBuffer.remaining();
            charBufferCreateBuffer.clear();
        }
        return jRemaining;
    }

    @CanIgnoreReturnValue
    public static long copyReaderToBuilder(Reader reader, StringBuilder sb) throws IOException {
        if (reader == null) {
            throw new NullPointerException();
        }
        if (sb == null) {
            throw new NullPointerException();
        }
        char[] cArr = new char[2048];
        long j = 0;
        while (true) {
            int i = reader.read(cArr);
            if (i == -1) {
                return j;
            }
            sb.append(cArr, 0, i);
            j += (long) i;
        }
    }

    @CanIgnoreReturnValue
    public static long copyReaderToWriter(Reader reader, Writer writer) throws IOException {
        if (reader == null) {
            throw new NullPointerException();
        }
        if (writer == null) {
            throw new NullPointerException();
        }
        char[] cArr = new char[2048];
        long j = 0;
        while (true) {
            int i = reader.read(cArr);
            if (i == -1) {
                return j;
            }
            writer.write(cArr, 0, i);
            j += (long) i;
        }
    }

    public static CharBuffer createBuffer() {
        return CharBuffer.allocate(2048);
    }

    @CanIgnoreReturnValue
    @Beta
    public static long exhaust(Readable readable) {
        CharBuffer charBufferCreateBuffer = createBuffer();
        long j = 0;
        while (true) {
            long j2 = readable.read(charBufferCreateBuffer);
            if (j2 == -1) {
                return j;
            }
            j += j2;
            charBufferCreateBuffer.clear();
        }
    }

    @Beta
    public static Writer nullWriter() {
        return NullWriter.INSTANCE;
    }

    @Beta
    public static List<String> readLines(Readable readable) throws IOException {
        ArrayList arrayList = new ArrayList();
        LineReader lineReader = new LineReader(readable);
        while (true) {
            String line = lineReader.readLine();
            if (line == null) {
                return arrayList;
            }
            arrayList.add(line);
        }
    }

    @Beta
    public static void skipFully(Reader reader, long j) throws IOException {
        if (reader == null) {
            throw new NullPointerException();
        }
        while (j > 0) {
            long jSkip = reader.skip(j);
            if (jSkip == 0) {
                throw new EOFException();
            }
            j -= jSkip;
        }
    }

    public static String toString(Readable readable) {
        return toStringBuilder(readable).toString();
    }

    public static StringBuilder toStringBuilder(Readable readable) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (readable instanceof Reader) {
            copyReaderToBuilder((Reader) readable, sb);
        } else {
            copy(readable, sb);
        }
        return sb;
    }

    @CanIgnoreReturnValue
    @Beta
    public static <T> T readLines(Readable readable, LineProcessor<T> lineProcessor) throws IOException {
        String line;
        if (readable == null) {
            throw new NullPointerException();
        }
        if (lineProcessor != null) {
            LineReader lineReader = new LineReader(readable);
            do {
                line = lineReader.readLine();
                if (line == null) {
                    break;
                }
            } while (lineProcessor.processLine(line));
            return lineProcessor.getResult();
        }
        throw new NullPointerException();
    }
}
