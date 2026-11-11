package org.json;

import java.io.IOException;
import java.io.Writer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes8.dex */
public class StringBuilderWriter extends Writer {
    private final StringBuilder builder;

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
    }

    public StringBuilderWriter() {
        StringBuilder sb = new StringBuilder();
        this.builder = sb;
        this.lock = sb;
    }

    public StringBuilderWriter(int i) {
        StringBuilder sb = new StringBuilder(i);
        this.builder = sb;
        this.lock = sb;
    }

    @Override // java.io.Writer
    public void write(int i) {
        this.builder.append((char) i);
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        int i3;
        if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return;
        }
        this.builder.append(cArr, i, i2);
    }

    @Override // java.io.Writer
    public void write(String str) {
        this.builder.append(str);
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) {
        this.builder.append((CharSequence) str, i, i2 + i);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public StringBuilderWriter append(CharSequence charSequence) {
        write(String.valueOf(charSequence));
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public StringBuilderWriter append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = AbstractJsonLexerKt.NULL;
        }
        return append(charSequence.subSequence(i, i2));
    }

    @Override // java.io.Writer, java.lang.Appendable
    public StringBuilderWriter append(char c) {
        write(c);
        return this;
    }

    public String toString() {
        return this.builder.toString();
    }
}
