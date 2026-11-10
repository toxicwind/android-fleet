package com.google.common.io;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Absent;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.primitives.UnsignedLong;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public abstract class ByteSource {

    class AsCharSource extends CharSource {
        public final Charset charset;

        public AsCharSource(Charset charset) {
            if (charset == null) {
                throw new NullPointerException();
            }
            this.charset = charset;
        }

        @Override // com.google.common.io.CharSource
        public ByteSource asByteSource(Charset charset) {
            return charset.equals(this.charset) ? ByteSource.this : new CharSource.AsByteSource(charset);
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() {
            return new InputStreamReader(ByteSource.this.openStream(), this.charset);
        }

        @Override // com.google.common.io.CharSource
        public String read() {
            return new String(ByteSource.this.read(), this.charset);
        }

        public String toString() {
            String string = ByteSource.this.toString();
            String strValueOf = String.valueOf(this.charset);
            return a.b(strValueOf.length() + a.a((Object) string, 15), string, ".asCharSource(", strValueOf, ")");
        }
    }

    private static class ByteArrayByteSource extends ByteSource {
        public final byte[] bytes;
        public final int length;
        public final int offset;

        public ByteArrayByteSource(byte[] bArr) {
            int length = bArr.length;
            this.bytes = bArr;
            this.offset = 0;
            this.length = length;
        }

        @Override // com.google.common.io.ByteSource
        public long copyTo(OutputStream outputStream) throws IOException {
            outputStream.write(this.bytes, this.offset, this.length);
            return this.length;
        }

        @Override // com.google.common.io.ByteSource
        public HashCode hash(HashFunction hashFunction) {
            return hashFunction.hashBytes(this.bytes, this.offset, this.length);
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() {
            return this.length == 0;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openBufferedStream() {
            return openStream();
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() {
            return new ByteArrayInputStream(this.bytes, this.offset, this.length);
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() {
            byte[] bArr = this.bytes;
            int i = this.offset;
            return Arrays.copyOfRange(bArr, i, this.length + i);
        }

        @Override // com.google.common.io.ByteSource
        public long size() {
            return this.length;
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            return Optional.of(Long.valueOf(this.length));
        }

        @Override // com.google.common.io.ByteSource
        public ByteSource slice(long j, long j2) {
            Preconditions.checkArgument(j >= 0, "offset (%s) may not be negative", j);
            Preconditions.checkArgument(j2 >= 0, "length (%s) may not be negative", j2);
            long jMin = Math.min(j, this.length);
            return new ByteArrayByteSource(this.bytes, this.offset + ((int) jMin), (int) Math.min(j2, ((long) this.length) - jMin));
        }

        public String toString() {
            String strTruncate = Ascii.truncate(BaseEncoding.BASE16.encode(this.bytes, this.offset, this.length), 30, "...");
            return a.a(a.a((Object) strTruncate, 17), "ByteSource.wrap(", strTruncate, ")");
        }

        @Override // com.google.common.io.ByteSource
        public <T> T read(ByteProcessor<T> byteProcessor) {
            byteProcessor.processBytes(this.bytes, this.offset, this.length);
            return byteProcessor.getResult();
        }

        public ByteArrayByteSource(byte[] bArr, int i, int i2) {
            this.bytes = bArr;
            this.offset = i;
            this.length = i2;
        }
    }

    private static final class ConcatenatedByteSource extends ByteSource {
        public final Iterable<? extends ByteSource> sources;

        public ConcatenatedByteSource(Iterable<? extends ByteSource> iterable) {
            if (iterable == null) {
                throw new NullPointerException();
            }
            this.sources = iterable;
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() {
            Iterator<? extends ByteSource> it = this.sources.iterator();
            while (it.hasNext()) {
                if (!it.next().isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() {
            return new MultiInputStream(this.sources.iterator());
        }

        @Override // com.google.common.io.ByteSource
        public long size() {
            Iterator<? extends ByteSource> it = this.sources.iterator();
            long size = 0;
            while (it.hasNext()) {
                size += it.next().size();
                if (size < 0) {
                    return UnsignedLong.UNSIGNED_MASK;
                }
            }
            return size;
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            Iterable<? extends ByteSource> iterable = this.sources;
            if (!(iterable instanceof Collection)) {
                return Absent.INSTANCE;
            }
            Iterator<? extends ByteSource> it = iterable.iterator();
            long jLongValue = 0;
            while (it.hasNext()) {
                Optional<Long> optionalSizeIfKnown = it.next().sizeIfKnown();
                if (!optionalSizeIfKnown.isPresent()) {
                    return Absent.INSTANCE;
                }
                jLongValue += optionalSizeIfKnown.get().longValue();
                if (jLongValue < 0) {
                    return Optional.of(Long.valueOf(UnsignedLong.UNSIGNED_MASK));
                }
            }
            return Optional.of(Long.valueOf(jLongValue));
        }

        public String toString() {
            String strValueOf = String.valueOf(this.sources);
            return a.a(strValueOf.length() + 19, "ByteSource.concat(", strValueOf, ")");
        }
    }

    private static final class EmptyByteSource extends ByteArrayByteSource {
        public static final EmptyByteSource INSTANCE = new EmptyByteSource();

        public EmptyByteSource() {
            super(new byte[0]);
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            if (charset != null) {
                return CharSource.EmptyCharSource.INSTANCE;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.io.ByteSource.ByteArrayByteSource, com.google.common.io.ByteSource
        public byte[] read() {
            return this.bytes;
        }

        @Override // com.google.common.io.ByteSource.ByteArrayByteSource
        public String toString() {
            return "ByteSource.empty()";
        }
    }

    private final class SlicedByteSource extends ByteSource {
        public final long length;
        public final long offset;

        public SlicedByteSource(long j, long j2) {
            Preconditions.checkArgument(j >= 0, "offset (%s) may not be negative", j);
            Preconditions.checkArgument(j2 >= 0, "length (%s) may not be negative", j2);
            this.offset = j;
            this.length = j2;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: X */
        private InputStream sliceStream(InputStream inputStream) throws X, IOException {
            long j = this.offset;
            if (j > 0) {
                try {
                    if (ByteStreams.skipUpTo(inputStream, j) < this.offset) {
                        inputStream.close();
                        return new ByteArrayInputStream(new byte[0]);
                    }
                } catch (Throwable th) {
                    Closer closerCreate = Closer.create();
                    closerCreate.register(inputStream);
                    try {
                        throw closerCreate.rethrow(th);
                    } catch (Throwable th2) {
                        closerCreate.close();
                        throw th2;
                    }
                }
            }
            return ByteStreams.limit(inputStream, this.length);
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() {
            return this.length == 0 || super.isEmpty();
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openBufferedStream() {
            return sliceStream(ByteSource.this.openBufferedStream());
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() {
            return sliceStream(ByteSource.this.openStream());
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            Optional<Long> optionalSizeIfKnown = ByteSource.this.sizeIfKnown();
            if (!optionalSizeIfKnown.isPresent()) {
                return Absent.INSTANCE;
            }
            long jLongValue = optionalSizeIfKnown.get().longValue();
            return Optional.of(Long.valueOf(Math.min(this.length, jLongValue - Math.min(this.offset, jLongValue))));
        }

        @Override // com.google.common.io.ByteSource
        public ByteSource slice(long j, long j2) {
            Preconditions.checkArgument(j >= 0, "offset (%s) may not be negative", j);
            Preconditions.checkArgument(j2 >= 0, "length (%s) may not be negative", j2);
            long j3 = this.length - j;
            return j3 <= 0 ? EmptyByteSource.INSTANCE : ByteSource.this.slice(this.offset + j, Math.min(j2, j3));
        }

        public String toString() {
            String string = ByteSource.this.toString();
            long j = this.offset;
            long j2 = this.length;
            StringBuilder sb = new StringBuilder(a.a((Object) string, 50));
            sb.append(string);
            sb.append(".slice(");
            sb.append(j);
            sb.append(", ");
            sb.append(j2);
            sb.append(")");
            return sb.toString();
        }
    }

    public static ByteSource concat(Iterable<? extends ByteSource> iterable) {
        return new ConcatenatedByteSource(iterable);
    }

    private long countBySkipping(InputStream inputStream) throws IOException {
        long j = 0;
        while (true) {
            long jSkipUpTo = ByteStreams.skipUpTo(inputStream, 2147483647L);
            if (jSkipUpTo <= 0) {
                return j;
            }
            j += jSkipUpTo;
        }
    }

    public static ByteSource empty() {
        return EmptyByteSource.INSTANCE;
    }

    public static ByteSource wrap(byte[] bArr) {
        return new ByteArrayByteSource(bArr);
    }

    public CharSource asCharSource(Charset charset) {
        return new AsCharSource(charset);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public boolean contentEquals(ByteSource byteSource) throws X {
        int i;
        if (byteSource == null) {
            throw new NullPointerException();
        }
        byte[] bArrCreateBuffer = ByteStreams.createBuffer();
        byte[] bArrCreateBuffer2 = ByteStreams.createBuffer();
        Closer closerCreate = Closer.create();
        try {
            try {
                InputStream inputStream = (InputStream) closerCreate.register(openStream());
                InputStream inputStream2 = (InputStream) closerCreate.register(byteSource.openStream());
                do {
                    i = ByteStreams.read(inputStream, bArrCreateBuffer, 0, bArrCreateBuffer.length);
                    if (i == ByteStreams.read(inputStream2, bArrCreateBuffer2, 0, bArrCreateBuffer2.length) && Arrays.equals(bArrCreateBuffer, bArrCreateBuffer2)) {
                    }
                    closerCreate.close();
                    return false;
                } while (i == bArrCreateBuffer.length);
                closerCreate.close();
                return true;
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
    public long copyTo(OutputStream outputStream) throws X {
        if (outputStream == null) {
            throw new NullPointerException();
        }
        Closer closerCreate = Closer.create();
        try {
            try {
                long jCopy = ByteStreams.copy((InputStream) closerCreate.register(openStream()), outputStream);
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
    public HashCode hash(HashFunction hashFunction) throws X {
        Hasher hasherNewHasher = hashFunction.newHasher();
        copyTo(new Funnels.SinkAsStream(hasherNewHasher));
        return hasherNewHasher.hash();
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
            com.google.common.base.Optional r0 = r6.sizeIfKnown()
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
            java.io.InputStream r1 = r6.openStream()     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L3c
            java.io.Closeable r1 = r0.register(r1)     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L3c
            java.io.InputStream r1 = (java.io.InputStream) r1     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L3c
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.ByteSource.isEmpty():boolean");
    }

    public InputStream openBufferedStream() {
        InputStream inputStreamOpenStream = openStream();
        return inputStreamOpenStream instanceof BufferedInputStream ? (BufferedInputStream) inputStreamOpenStream : new BufferedInputStream(inputStreamOpenStream);
    }

    public abstract InputStream openStream();

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public byte[] read() throws X {
        Closer closerCreate = Closer.create();
        try {
            try {
                InputStream inputStream = (InputStream) closerCreate.register(openStream());
                Optional<Long> optionalSizeIfKnown = sizeIfKnown();
                byte[] byteArray = optionalSizeIfKnown.isPresent() ? ByteStreams.toByteArray(inputStream, optionalSizeIfKnown.get().longValue()) : ByteStreams.toByteArray(inputStream);
                closerCreate.close();
                return byteArray;
            } catch (Throwable th) {
                throw closerCreate.rethrow(th);
            }
        } catch (Throwable th2) {
            closerCreate.close();
            throw th2;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public long size() throws X {
        Optional<Long> optionalSizeIfKnown = sizeIfKnown();
        if (optionalSizeIfKnown.isPresent()) {
            return optionalSizeIfKnown.get().longValue();
        }
        Closer closerCreate = Closer.create();
        try {
            long jCountBySkipping = countBySkipping((InputStream) closerCreate.register(openStream()));
            closerCreate.close();
            return jCountBySkipping;
        } catch (IOException unused) {
            closerCreate.close();
            Closer closerCreate2 = Closer.create();
            try {
                try {
                    long jExhaust = ByteStreams.exhaust((InputStream) closerCreate2.register(openStream()));
                    closerCreate2.close();
                    return jExhaust;
                } catch (Throwable th) {
                    throw closerCreate2.rethrow(th);
                }
            } catch (Throwable th2) {
                closerCreate2.close();
                throw th2;
            }
        } catch (Throwable th3) {
            closerCreate.close();
            throw th3;
        }
    }

    @Beta
    public Optional<Long> sizeIfKnown() {
        return Absent.INSTANCE;
    }

    public ByteSource slice(long j, long j2) {
        return new SlicedByteSource(j, j2);
    }

    public static ByteSource concat(Iterator<? extends ByteSource> it) {
        return new ConcatenatedByteSource(ImmutableList.copyOf(it));
    }

    public static ByteSource concat(ByteSource... byteSourceArr) {
        return new ConcatenatedByteSource(ImmutableList.copyOf(byteSourceArr));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @CanIgnoreReturnValue
    public long copyTo(ByteSink byteSink) throws X {
        if (byteSink != null) {
            Closer closerCreate = Closer.create();
            try {
                try {
                    long jCopy = ByteStreams.copy((InputStream) closerCreate.register(openStream()), (OutputStream) closerCreate.register(byteSink.openStream()));
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
    public <T> T read(ByteProcessor<T> byteProcessor) throws X {
        if (byteProcessor != null) {
            Closer closerCreate = Closer.create();
            try {
                try {
                    T t = (T) ByteStreams.readBytes((InputStream) closerCreate.register(openStream()), byteProcessor);
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
