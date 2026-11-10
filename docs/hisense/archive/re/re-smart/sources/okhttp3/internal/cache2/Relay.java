package okhttp3.internal.cache2;

import d.C;
import d.E;
import d.g;
import d.j;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes2.dex */
public final class Relay {
    public static final long FILE_HEADER_SIZE = 32;
    public static final j PREFIX_CLEAN = j.d("OkHttp cache v1\n");
    public static final j PREFIX_DIRTY = j.d("OkHttp DIRTY :(\n");
    public static final int SOURCE_FILE = 2;
    public static final int SOURCE_UPSTREAM = 1;
    public final long bufferMaxSize;
    public boolean complete;
    public RandomAccessFile file;
    public final j metadata;
    public int sourceCount;
    public C upstream;
    public long upstreamPos;
    public Thread upstreamReader;
    public final g upstreamBuffer = new g();
    public final g buffer = new g();

    class RelaySource implements C {
        public FileOperator fileOperator;
        public long sourcePos;
        public final E timeout = new E();

        public RelaySource() {
            this.fileOperator = new FileOperator(Relay.this.file.getChannel());
        }

        @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.fileOperator == null) {
                return;
            }
            RandomAccessFile randomAccessFile = null;
            this.fileOperator = null;
            synchronized (Relay.this) {
                Relay.this.sourceCount--;
                if (Relay.this.sourceCount == 0) {
                    RandomAccessFile randomAccessFile2 = Relay.this.file;
                    Relay.this.file = null;
                    randomAccessFile = randomAccessFile2;
                }
            }
            if (randomAccessFile != null) {
                Util.closeQuietly(randomAccessFile);
            }
        }

        @Override // d.C
        public long read(g gVar, long j) throws IOException {
            long j2;
            char c2;
            if (this.fileOperator == null) {
                throw new IllegalStateException("closed");
            }
            synchronized (Relay.this) {
                while (true) {
                    long j3 = this.sourcePos;
                    j2 = Relay.this.upstreamPos;
                    if (j3 != j2) {
                        long j4 = j2 - Relay.this.buffer.f2102c;
                        if (this.sourcePos < j4) {
                            c2 = 2;
                            break;
                        }
                        long jMin = Math.min(j, j2 - this.sourcePos);
                        Relay.this.buffer.a(gVar, this.sourcePos - j4, jMin);
                        this.sourcePos += jMin;
                        return jMin;
                    }
                    if (!Relay.this.complete) {
                        if (Relay.this.upstreamReader == null) {
                            Relay.this.upstreamReader = Thread.currentThread();
                            c2 = 1;
                            break;
                        }
                        this.timeout.waitUntilNotified(Relay.this);
                    } else {
                        return -1L;
                    }
                }
                if (c2 == 2) {
                    long jMin2 = Math.min(j, j2 - this.sourcePos);
                    this.fileOperator.read(this.sourcePos + 32, gVar, jMin2);
                    this.sourcePos += jMin2;
                    return jMin2;
                }
                try {
                    long j5 = Relay.this.upstream.read(Relay.this.upstreamBuffer, Relay.this.bufferMaxSize);
                    if (j5 == -1) {
                        Relay.this.commit(j2);
                        synchronized (Relay.this) {
                            Relay.this.upstreamReader = null;
                            Relay.this.notifyAll();
                        }
                        return -1L;
                    }
                    long jMin3 = Math.min(j5, j);
                    Relay.this.upstreamBuffer.a(gVar, 0L, jMin3);
                    this.sourcePos += jMin3;
                    this.fileOperator.write(j2 + 32, Relay.this.upstreamBuffer.m8clone(), j5);
                    synchronized (Relay.this) {
                        Relay.this.buffer.write(Relay.this.upstreamBuffer, j5);
                        if (Relay.this.buffer.f2102c > Relay.this.bufferMaxSize) {
                            Relay.this.buffer.skip(Relay.this.buffer.f2102c - Relay.this.bufferMaxSize);
                        }
                        Relay.this.upstreamPos += j5;
                    }
                    synchronized (Relay.this) {
                        Relay.this.upstreamReader = null;
                        Relay.this.notifyAll();
                    }
                    return jMin3;
                } catch (Throwable th) {
                    synchronized (Relay.this) {
                        Relay.this.upstreamReader = null;
                        Relay.this.notifyAll();
                        throw th;
                    }
                }
            }
        }

        @Override // d.C
        public E timeout() {
            return this.timeout;
        }
    }

    public Relay(RandomAccessFile randomAccessFile, C c2, long j, j jVar, long j2) {
        this.file = randomAccessFile;
        this.upstream = c2;
        this.complete = c2 == null;
        this.upstreamPos = j;
        this.metadata = jVar;
        this.bufferMaxSize = j2;
    }

    public static Relay edit(File file, C c2, j jVar, long j) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        Relay relay = new Relay(randomAccessFile, c2, 0L, jVar, j);
        randomAccessFile.setLength(0L);
        relay.writeHeader(PREFIX_DIRTY, -1L, -1L);
        return relay;
    }

    public static Relay read(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileChannel channel = randomAccessFile.getChannel();
        g gVar = new g();
        if (32 < 0) {
            throw new IndexOutOfBoundsException();
        }
        long j = 32;
        long j2 = 0;
        while (j > 0) {
            long jTransferTo = channel.transferTo(j2, j, gVar);
            j2 += jTransferTo;
            j -= jTransferTo;
        }
        if (!gVar.d(PREFIX_CLEAN.g()).equals(PREFIX_CLEAN)) {
            throw new IOException("unreadable cache file");
        }
        long j3 = gVar.readLong();
        long j4 = gVar.readLong();
        g gVar2 = new g();
        long j5 = 32 + j3;
        if (j4 < 0) {
            throw new IndexOutOfBoundsException();
        }
        long j6 = j5;
        long j7 = j4;
        while (j7 > 0) {
            long jTransferTo2 = channel.transferTo(j6, j7, gVar2);
            j6 += jTransferTo2;
            j7 -= jTransferTo2;
        }
        return new Relay(randomAccessFile, null, j3, gVar2.n(), 0L);
    }

    private void writeHeader(j jVar, long j, long j2) throws IOException {
        g gVar = new g();
        gVar.a(jVar);
        gVar.j(j);
        gVar.j(j2);
        if (gVar.f2102c != 32) {
            throw new IllegalArgumentException();
        }
        FileChannel channel = this.file.getChannel();
        if (32 < 0 || 32 > gVar.f2102c) {
            throw new IndexOutOfBoundsException();
        }
        long j3 = 32;
        long j4 = 0;
        while (j3 > 0) {
            long jTransferFrom = channel.transferFrom(gVar, j4, j3);
            j4 += jTransferFrom;
            j3 -= jTransferFrom;
        }
    }

    private void writeMetadata(long j) throws IOException {
        g gVar = new g();
        gVar.a(this.metadata);
        FileChannel channel = this.file.getChannel();
        long j2 = j + 32;
        long jG = this.metadata.g();
        if (jG < 0 || jG > gVar.f2102c) {
            throw new IndexOutOfBoundsException();
        }
        long j3 = j2;
        long j4 = jG;
        while (j4 > 0) {
            long jTransferFrom = channel.transferFrom(gVar, j3, j4);
            j3 += jTransferFrom;
            j4 -= jTransferFrom;
        }
    }

    public void commit(long j) throws IOException {
        writeMetadata(j);
        this.file.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, j, this.metadata.g());
        this.file.getChannel().force(false);
        synchronized (this) {
            this.complete = true;
        }
        Util.closeQuietly(this.upstream);
        this.upstream = null;
    }

    public boolean isClosed() {
        return this.file == null;
    }

    public j metadata() {
        return this.metadata;
    }

    public C newSource() {
        synchronized (this) {
            if (this.file == null) {
                return null;
            }
            this.sourceCount++;
            return new RelaySource();
        }
    }
}
