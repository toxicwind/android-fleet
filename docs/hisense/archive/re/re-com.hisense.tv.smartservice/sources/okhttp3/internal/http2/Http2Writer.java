package okhttp3.internal.http2;

import d.g;
import d.h;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes2.dex */
public final class Http2Writer implements Closeable {
    public static final Logger logger = Logger.getLogger(Http2.class.getName());
    public final boolean client;
    public boolean closed;
    public final g hpackBuffer = new g();
    public final Hpack.Writer hpackWriter = new Hpack.Writer(this.hpackBuffer);
    public int maxFrameSize = 16384;
    public final h sink;

    public Http2Writer(h hVar, boolean z) {
        this.sink = hVar;
        this.client = z;
    }

    private void writeContinuationFrames(int i, long j) {
        while (j > 0) {
            int iMin = (int) Math.min(this.maxFrameSize, j);
            long j2 = iMin;
            j -= j2;
            frameHeader(i, iMin, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.sink.write(this.hpackBuffer, j2);
        }
    }

    public static void writeMedium(h hVar, int i) {
        hVar.writeByte((i >>> 16) & 255);
        hVar.writeByte((i >>> 8) & 255);
        hVar.writeByte(i & 255);
    }

    public synchronized void applyAndAckSettings(Settings settings) {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.maxFrameSize = settings.getMaxFrameSize(this.maxFrameSize);
        if (settings.getHeaderTableSize() != -1) {
            this.hpackWriter.setHeaderTableSizeSetting(settings.getHeaderTableSize());
        }
        frameHeader(0, 0, (byte) 4, (byte) 1);
        this.sink.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.closed = true;
        this.sink.close();
    }

    public synchronized void connectionPreface() {
        if (this.closed) {
            throw new IOException("closed");
        }
        if (this.client) {
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(Util.format(">> CONNECTION %s", Http2.CONNECTION_PREFACE.b()));
            }
            this.sink.write(Http2.CONNECTION_PREFACE.i());
            this.sink.flush();
        }
    }

    public synchronized void data(boolean z, int i, g gVar, int i2) {
        if (this.closed) {
            throw new IOException("closed");
        }
        dataFrame(i, z ? (byte) 1 : (byte) 0, gVar, i2);
    }

    public void dataFrame(int i, byte b2, g gVar, int i2) {
        frameHeader(i, i2, (byte) 0, b2);
        if (i2 > 0) {
            this.sink.write(gVar, i2);
        }
    }

    public synchronized void flush() {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.sink.flush();
    }

    public void frameHeader(int i, int i2, byte b2, byte b3) {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Http2.frameLog(false, i, i2, b2, b3));
        }
        int i3 = this.maxFrameSize;
        if (i2 > i3) {
            Http2.illegalArgument("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i3), Integer.valueOf(i2));
            throw null;
        }
        if ((Integer.MIN_VALUE & i) != 0) {
            Http2.illegalArgument("reserved bit set: %s", Integer.valueOf(i));
            throw null;
        }
        writeMedium(this.sink, i2);
        this.sink.writeByte(b2 & 255);
        this.sink.writeByte(b3 & 255);
        this.sink.writeInt(i & Integer.MAX_VALUE);
    }

    public synchronized void goAway(int i, ErrorCode errorCode, byte[] bArr) {
        if (this.closed) {
            throw new IOException("closed");
        }
        if (errorCode.httpCode == -1) {
            Http2.illegalArgument("errorCode.httpCode == -1", new Object[0]);
            throw null;
        }
        frameHeader(0, bArr.length + 8, (byte) 7, (byte) 0);
        this.sink.writeInt(i);
        this.sink.writeInt(errorCode.httpCode);
        if (bArr.length > 0) {
            this.sink.write(bArr);
        }
        this.sink.flush();
    }

    public synchronized void headers(int i, List<Header> list) {
        if (this.closed) {
            throw new IOException("closed");
        }
        headers(false, i, list);
    }

    public int maxDataLength() {
        return this.maxFrameSize;
    }

    public synchronized void ping(boolean z, int i, int i2) {
        if (this.closed) {
            throw new IOException("closed");
        }
        frameHeader(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
        this.sink.writeInt(i);
        this.sink.writeInt(i2);
        this.sink.flush();
    }

    public synchronized void pushPromise(int i, int i2, List<Header> list) {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.hpackWriter.writeHeaders(list);
        long j = this.hpackBuffer.f2102c;
        int iMin = (int) Math.min(this.maxFrameSize - 4, j);
        long j2 = iMin;
        frameHeader(i, iMin + 4, (byte) 5, j == j2 ? (byte) 4 : (byte) 0);
        this.sink.writeInt(i2 & Integer.MAX_VALUE);
        this.sink.write(this.hpackBuffer, j2);
        if (j > j2) {
            writeContinuationFrames(i, j - j2);
        }
    }

    public synchronized void rstStream(int i, ErrorCode errorCode) {
        if (this.closed) {
            throw new IOException("closed");
        }
        if (errorCode.httpCode == -1) {
            throw new IllegalArgumentException();
        }
        frameHeader(i, 4, (byte) 3, (byte) 0);
        this.sink.writeInt(errorCode.httpCode);
        this.sink.flush();
    }

    public synchronized void settings(Settings settings) {
        int i;
        if (this.closed) {
            throw new IOException("closed");
        }
        int i2 = 0;
        frameHeader(0, settings.size() * 6, (byte) 4, (byte) 0);
        while (i2 < 10) {
            if (settings.isSet(i2)) {
                if (i2 == 4) {
                    i = 3;
                } else {
                    i = i2 == 7 ? 4 : i2;
                }
                this.sink.writeShort(i);
                this.sink.writeInt(settings.get(i2));
            }
            i2++;
        }
        this.sink.flush();
    }

    public synchronized void synReply(boolean z, int i, List<Header> list) {
        if (this.closed) {
            throw new IOException("closed");
        }
        headers(z, i, list);
    }

    public synchronized void synStream(boolean z, int i, int i2, List<Header> list) {
        if (this.closed) {
            throw new IOException("closed");
        }
        headers(z, i, list);
    }

    public synchronized void windowUpdate(int i, long j) {
        if (this.closed) {
            throw new IOException("closed");
        }
        if (j == 0 || j > 2147483647L) {
            Http2.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
            throw null;
        }
        frameHeader(i, 4, (byte) 8, (byte) 0);
        this.sink.writeInt((int) j);
        this.sink.flush();
    }

    public void headers(boolean z, int i, List<Header> list) throws IOException {
        if (!this.closed) {
            this.hpackWriter.writeHeaders(list);
            long j = this.hpackBuffer.f2102c;
            int iMin = (int) Math.min(this.maxFrameSize, j);
            long j2 = iMin;
            byte b2 = j == j2 ? (byte) 4 : (byte) 0;
            if (z) {
                b2 = (byte) (b2 | 1);
            }
            frameHeader(i, iMin, (byte) 1, b2);
            this.sink.write(this.hpackBuffer, j2);
            if (j > j2) {
                writeContinuationFrames(i, j - j2);
                return;
            }
            return;
        }
        throw new IOException("closed");
    }
}
