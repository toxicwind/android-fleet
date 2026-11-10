package com.google.common.io;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Absent;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.UnsignedLong;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public abstract class CharSource {

    /* JADX INFO: Access modifiers changed from: private */
    final class AsByteSource extends ByteSource {
        public final Charset charset;

        public AsByteSource(Charset charset) {
            if (charset == null) {
                throw new NullPointerException();
            }
            this.charset = charset;
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            return charset.equals(this.charset) ? CharSource.this : new ByteSource.AsCharSource(charset);
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() {
            return new ReaderInputStream(CharSource.this.openStream(), this.charset, 8192);
        }

        public String toString() {
            String string = CharSource.this.toString();
            String strValueOf = String.valueOf(this.charset);
            return a.b(strValueOf.length() + a.a((Object) string, 15), string, ".asByteSource(", strValueOf, ")");
        }
    }

    private static final class ConcatenatedCharSource extends CharSource {
        public final Iterable<? extends CharSource> sources;

        public ConcatenatedCharSource(Iterable<? extends CharSource> iterable) {
            if (iterable == null) {
                throw new NullPointerException();
            }
            this.sources = iterable;
        }

        @Override // com.google.common.io.CharSource
        public boolean isEmpty() {
            Iterator<? extends CharSource> it = this.sources.iterator();
            while (it.hasNext()) {
                if (!it.next().isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.CharSource
        public long length() {
            Iterator<? extends CharSource> it = this.sources.iterator();
            long length = 0;
            while (it.hasNext()) {
                length += it.next().length();
            }
            return length;
        }

        @Override // com.google.common.io.CharSource
        public Optional<Long> lengthIfKnown() {
            Iterator<? extends CharSource> it = this.sources.iterator();
            long jLongValue = 0;
            while (it.hasNext()) {
                Optional<Long> optionalLengthIfKnown = it.next().lengthIfKnown();
                if (!optionalLengthIfKnown.isPresent()) {
                    return Absent.INSTANCE;
                }
                jLongValue += optionalLengthIfKnown.get().longValue();
            }
            return Optional.of(Long.valueOf(jLongValue));
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() {
            return new MultiReader(this.sources.iterator());
        }

        public String toString() {
            String strValueOf = String.valueOf(this.sources);
            return a.a(strValueOf.length() + 19, "CharSource.concat(", strValueOf, ")");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class EmptyCharSource extends StringCharSource {
        public static final EmptyCharSource INSTANCE = new EmptyCharSource();

        public EmptyCharSource() {
            super("");
        }

        @Override // com.google.common.io.CharSource.CharSequenceCharSource
        public String toString() {
            return "CharSource.empty()";
        }
    }

    public static CharSource concat(Iterable<? extends CharSource> iterable) {
        return new ConcatenatedCharSource(iterable);
    }

    private long countBySkipping(Reader reader) throws IOException {
        long j = 0;
        while (true) {
            long jSkip = reader.skip(UnsignedLong.UNSIGNED_MASK);
            if (jSkip == 0) {
                return j;
            }
            j += jSkip;
        }
    }

    public static CharSource empty() {
        return EmptyCharSource.INSTANCE;
    }

    public static CharSource wrap(CharSequence charSequence) {
        return charSequence instanceof String ? new StringCharSource((String) charSequence) : new CharSequenceCharSource(charSequence);
    }

    @Beta
    public ByteSource asByteSource(Charset charset) {
        return new AsByteSource(charset);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @CanIgnoreReturnValue
    public long copyTo(Appendable appendable) throws X {
        if (appendable == null) {
            throw new NullPointerException();
        }
        Closer closerCreate = Closer.create();
        try {
            try {
                long jCopy = CharStreams.copy((Reader) closerCreate.register(openStream()), appendable);
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
    /* JADX WARN: Bottom block not found for handler: all -> 0x003a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isEmpty() throws X {
        /*
            r6 = this;
            com.google.common.base.Optional r0 = r6.lengthIfKnown()
            boolean r1 = r0.isPresent()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L1f
            java.lang.Object r0 = r0.get()
            java.lang.Long r0 = (java.lang.Long) r0
            long r0 = r0.longValue()
            r4 = 0
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 != 0) goto L1d
            goto L1e
        L1d:
            r2 = r3
        L1e:
            return r2
        L1f:
            com.google.common.io.Closer r0 = com.google.common.io.Closer.create()
            java.io.Reader r1 = r6.openStream()     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L3c
            java.io.Closeable r1 = r0.register(r1)     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L3c
            java.io.Reader r1 = (java.io.Reader) r1     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L3c
            int r1 = r1.read()     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L3c
            r4 = -1
            if (r1 != r4) goto L35
            goto L36
        L35:
            r2 = r3
        L36:
            r0.close()
            return r2
        L3a:
            r1 = move-exception
            goto L42
        L3c:
            r1 = move-exception
            java.lang.RuntimeException r1 = r0.rethrow(r1)     // Catch: java.lang.Throwable -> L3a
            throw r1     // Catch: java.lang.Throwable -> L3a
        L42:
            r0.close()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.CharSource.isEmpty():boolean");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @Beta
    public long length() throws X {
        Optional<Long> optionalLengthIfKnown = lengthIfKnown();
        if (optionalLengthIfKnown.isPresent()) {
            return optionalLengthIfKnown.get().longValue();
        }
        Closer closerCreate = Closer.create();
        try {
            try {
                long jCountBySkipping = countBySkipping((Reader) closerCreate.register(openStream()));
                closerCreate.close();
                return jCountBySkipping;
            } catch (Throwable th) {
                throw closerCreate.rethrow(th);
            }
        } catch (Throwable th2) {
            closerCreate.close();
            throw th2;
        }
    }

    @Beta
    public Optional<Long> lengthIfKnown() {
        return Absent.INSTANCE;
    }

    public BufferedReader openBufferedStream() {
        Reader readerOpenStream = openStream();
        return readerOpenStream instanceof BufferedReader ? (BufferedReader) readerOpenStream : new BufferedReader(readerOpenStream);
    }

    public abstract Reader openStream();

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public String read() throws X {
        Closer closerCreate = Closer.create();
        try {
            try {
                String string = CharStreams.toString((Reader) closerCreate.register(openStream()));
                closerCreate.close();
                return string;
            } catch (Throwable th) {
                throw closerCreate.rethrow(th);
            }
        } catch (Throwable th2) {
            closerCreate.close();
            throw th2;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public String readFirstLine() throws X {
        Closer closerCreate = Closer.create();
        try {
            try {
                String line = ((BufferedReader) closerCreate.register(openBufferedStream())).readLine();
                closerCreate.close();
                return line;
            } catch (Throwable th) {
                throw closerCreate.rethrow(th);
            }
        } catch (Throwable th2) {
            closerCreate.close();
            throw th2;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public ImmutableList<String> readLines() throws X {
        Closer closerCreate = Closer.create();
        try {
            try {
                BufferedReader bufferedReader = (BufferedReader) closerCreate.register(openBufferedStream());
                ArrayList arrayList = new ArrayList();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        ImmutableList<String> immutableListCopyOf = ImmutableList.copyOf((Collection) arrayList);
                        closerCreate.close();
                        return immutableListCopyOf;
                    }
                    arrayList.add(line);
                }
            } catch (Throwable th) {
                throw closerCreate.rethrow(th);
            }
        } catch (Throwable th2) {
            closerCreate.close();
            throw th2;
        }
    }

    private static class CharSequenceCharSource extends CharSource {
        public static final Splitter LINE_SPLITTER = Splitter.onPattern("\r\n|\n|\r");
        public final CharSequence seq;

        /* JADX INFO: renamed from: com.google.common.io.CharSource$CharSequenceCharSource$1, reason: invalid class name */
        public class AnonymousClass1 extends AbstractIterator<String> {
            public Iterator<String> lines;

            public AnonymousClass1() {
                this.lines = CharSequenceCharSource.LINE_SPLITTER.split(CharSequenceCharSource.this.seq).iterator();
            }

            @Override // com.google.common.collect.AbstractIterator
            public String computeNext() {
                if (this.lines.hasNext()) {
                    String next = this.lines.next();
                    if (this.lines.hasNext() || !next.isEmpty()) {
                        return next;
                    }
                }
                return endOfData();
            }
        }

        public CharSequenceCharSource(CharSequence charSequence) {
            if (charSequence == null) {
                throw new NullPointerException();
            }
            this.seq = charSequence;
        }

        private Iterator<String> linesIterator() {
            return new AnonymousClass1();
        }

        @Override // com.google.common.io.CharSource
        public boolean isEmpty() {
            return this.seq.length() == 0;
        }

        @Override // com.google.common.io.CharSource
        public long length() {
            return this.seq.length();
        }

        @Override // com.google.common.io.CharSource
        public Optional<Long> lengthIfKnown() {
            return Optional.of(Long.valueOf(this.seq.length()));
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() {
            return new CharSequenceReader(this.seq);
        }

        @Override // com.google.common.io.CharSource
        public String read() {
            return this.seq.toString();
        }

        @Override // com.google.common.io.CharSource
        public String readFirstLine() {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            if (anonymousClass1.hasNext()) {
                return anonymousClass1.next();
            }
            return null;
        }

        @Override // com.google.common.io.CharSource
        public ImmutableList<String> readLines() {
            return ImmutableList.copyOf(new AnonymousClass1());
        }

        public String toString() {
            String strTruncate = Ascii.truncate(this.seq, 30, "...");
            return a.a(a.a((Object) strTruncate, 17), "CharSource.wrap(", strTruncate, ")");
        }

        @Override // com.google.common.io.CharSource
        public <T> T readLines(LineProcessor<T> lineProcessor) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            while (anonymousClass1.hasNext() && lineProcessor.processLine(anonymousClass1.next())) {
            }
            return lineProcessor.getResult();
        }
    }

    private static class StringCharSource extends CharSequenceCharSource {
        public StringCharSource(String str) {
            super(str);
        }

        @Override // com.google.common.io.CharSource
        public long copyTo(Appendable appendable) throws IOException {
            appendable.append(this.seq);
            return this.seq.length();
        }

        @Override // com.google.common.io.CharSource.CharSequenceCharSource, com.google.common.io.CharSource
        public Reader openStream() {
            return new StringReader((String) this.seq);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: X */
        @Override // com.google.common.io.CharSource
        public long copyTo(CharSink charSink) throws X {
            if (charSink != null) {
                Closer closerCreate = Closer.create();
                try {
                    try {
                        ((Writer) closerCreate.register(charSink.openStream())).write((String) this.seq);
                        long length = this.seq.length();
                        closerCreate.close();
                        return length;
                    } catch (Throwable th) {
                        throw closerCreate.rethrow(th);
                    }
                } catch (Throwable th2) {
                    closerCreate.close();
                    throw th2;
                }
            }
            throw new NullPointerException();
        }
    }

    public static CharSource concat(Iterator<? extends CharSource> it) {
        return new ConcatenatedCharSource(ImmutableList.copyOf(it));
    }

    public static CharSource concat(CharSource... charSourceArr) {
        return new ConcatenatedCharSource(ImmutableList.copyOf(charSourceArr));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @CanIgnoreReturnValue
    public long copyTo(CharSink charSink) throws X {
        if (charSink != null) {
            Closer closerCreate = Closer.create();
            try {
                try {
                    long jCopy = CharStreams.copy((Reader) closerCreate.register(openStream()), (Writer) closerCreate.register(charSink.openStream()));
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
        throw new NullPointerException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @CanIgnoreReturnValue
    @Beta
    public <T> T readLines(LineProcessor<T> lineProcessor) throws X {
        if (lineProcessor != null) {
            Closer closerCreate = Closer.create();
            try {
                try {
                    T t = (T) CharStreams.readLines((Reader) closerCreate.register(openStream()), lineProcessor);
                    closerCreate.close();
                    return t;
                } catch (Throwable th) {
                    throw closerCreate.rethrow(th);
                }
            } catch (Throwable th2) {
                closerCreate.close();
                throw th2;
            }
        }
        throw new NullPointerException();
    }
}
