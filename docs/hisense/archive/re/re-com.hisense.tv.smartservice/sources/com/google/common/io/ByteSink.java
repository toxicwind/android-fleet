package com.google.common.io;

import b.a.a.a.a;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public abstract class ByteSink {

    private final class AsCharSink extends CharSink {
        public final Charset charset;

        @Override // com.google.common.io.CharSink
        public Writer openStream() {
            return new OutputStreamWriter(ByteSink.this.openStream(), this.charset);
        }

        public String toString() {
            String string = ByteSink.this.toString();
            String strValueOf = String.valueOf(this.charset);
            return a.b(strValueOf.length() + a.a((Object) string, 13), string, ".asCharSink(", strValueOf, ")");
        }

        public AsCharSink(Charset charset) {
            if (charset == null) {
                throw new NullPointerException();
            }
            this.charset = charset;
        }
    }

    public CharSink asCharSink(Charset charset) {
        return new AsCharSink(charset);
    }

    public OutputStream openBufferedStream() {
        OutputStream outputStreamOpenStream = openStream();
        return outputStreamOpenStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStreamOpenStream : new BufferedOutputStream(outputStreamOpenStream);
    }

    public abstract OutputStream openStream();

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public void write(byte[] bArr) throws X {
        if (bArr == null) {
            throw new NullPointerException();
        }
        Closer closerCreate = Closer.create();
        try {
            try {
                OutputStream outputStream = (OutputStream) closerCreate.register(openStream());
                outputStream.write(bArr);
                outputStream.flush();
                closerCreate.close();
            } catch (Throwable th) {
                throw closerCreate.rethrow(th);
            }
        } catch (Throwable th2) {
            closerCreate.close();
            throw th2;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @CanIgnoreReturnValue
    public long writeFrom(InputStream inputStream) throws X {
        if (inputStream == null) {
            throw new NullPointerException();
        }
        Closer closerCreate = Closer.create();
        try {
            try {
                OutputStream outputStream = (OutputStream) closerCreate.register(openStream());
                long jCopy = ByteStreams.copy(inputStream, outputStream);
                outputStream.flush();
                closerCreate.close();
                return jCopy;
            } catch (Throwable th) {
                throw closerCreate.rethrow(th);
            }
        } catch (Throwable th2) {
            closerCreate.close();
            throw th2;
        }
    }
}
