package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedWriter;
import java.io.Writer;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public abstract class CharSink {
    public Writer openBufferedStream() {
        Writer writerOpenStream = openStream();
        return writerOpenStream instanceof BufferedWriter ? (BufferedWriter) writerOpenStream : new BufferedWriter(writerOpenStream);
    }

    public abstract Writer openStream();

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public void write(CharSequence charSequence) throws X {
        if (charSequence == null) {
            throw new NullPointerException();
        }
        Closer closerCreate = Closer.create();
        try {
            try {
                Writer writer = (Writer) closerCreate.register(openStream());
                writer.append(charSequence);
                writer.flush();
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
    public long writeFrom(Readable readable) throws X {
        if (readable == null) {
            throw new NullPointerException();
        }
        Closer closerCreate = Closer.create();
        try {
            try {
                Writer writer = (Writer) closerCreate.register(openStream());
                long jCopy = CharStreams.copy(readable, writer);
                writer.flush();
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

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public void writeLines(Iterable<? extends CharSequence> iterable) throws X {
        writeLines(iterable, System.getProperty("line.separator"));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public void writeLines(Iterable<? extends CharSequence> iterable, String str) throws X {
        if (iterable == null) {
            throw new NullPointerException();
        }
        if (str == null) {
            throw new NullPointerException();
        }
        Closer closerCreate = Closer.create();
        try {
            try {
                Writer writer = (Writer) closerCreate.register(openBufferedStream());
                Iterator<? extends CharSequence> it = iterable.iterator();
                while (it.hasNext()) {
                    writer.append(it.next()).append((CharSequence) str);
                }
                writer.flush();
                closerCreate.close();
            } catch (Throwable th) {
                throw closerCreate.rethrow(th);
            }
        } catch (Throwable th2) {
            closerCreate.close();
            throw th2;
        }
    }
}
