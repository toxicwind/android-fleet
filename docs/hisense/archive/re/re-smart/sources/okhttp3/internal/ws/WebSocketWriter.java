package okhttp3.internal.ws;

import d.B;
import d.E;
import d.g;
import d.h;
import d.j;
import java.io.IOException;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public final class WebSocketWriter {
    public boolean activeWriter;
    public final g buffer = new g();
    public final FrameSink frameSink = new FrameSink();
    public final boolean isClient;
    public final g.a maskCursor;
    public final byte[] maskKey;
    public final Random random;
    public final h sink;
    public final g sinkBuffer;
    public boolean writerClosed;

    final class FrameSink implements B {
        public boolean closed;
        public long contentLength;
        public int formatOpcode;
        public boolean isFirstFrame;

        public FrameSink() {
        }

        @Override // d.B, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter webSocketWriter = WebSocketWriter.this;
            webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.f2102c, this.isFirstFrame, true);
            this.closed = true;
            WebSocketWriter.this.activeWriter = false;
        }

        @Override // d.B, java.io.Flushable
        public void flush() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter webSocketWriter = WebSocketWriter.this;
            webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.f2102c, this.isFirstFrame, false);
            this.isFirstFrame = false;
        }

        @Override // d.B
        public E timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        /* JADX WARN: Code duplicated, block: B:11:0x0027  */
        @Override // d.B
        public void write(g gVar, long j) throws IOException {
            boolean z;
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.buffer.write(gVar, j);
            if (this.isFirstFrame) {
                long j2 = this.contentLength;
                if (j2 == -1 || WebSocketWriter.this.buffer.f2102c <= j2 - 8192) {
                    z = false;
                } else {
                    z = true;
                }
            } else {
                z = false;
            }
            long jM = WebSocketWriter.this.buffer.m();
            if (jM <= 0 || z) {
                return;
            }
            WebSocketWriter.this.writeMessageFrame(this.formatOpcode, jM, this.isFirstFrame, false);
            this.isFirstFrame = false;
        }
    }

    public WebSocketWriter(boolean z, h hVar, Random random) {
        if (hVar == null) {
            throw new NullPointerException("sink == null");
        }
        if (random == null) {
            throw new NullPointerException("random == null");
        }
        this.isClient = z;
        this.sink = hVar;
        this.sinkBuffer = hVar.a();
        this.random = random;
        this.maskKey = z ? new byte[4] : null;
        this.maskCursor = z ? new g.a() : null;
    }

    private void writeControlFrame(int i, j jVar) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        int iG = jVar.g();
        if (iG > 125) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        this.sinkBuffer.writeByte(i | 128);
        if (this.isClient) {
            this.sinkBuffer.writeByte(iG | 128);
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.write(this.maskKey);
            if (iG > 0) {
                g gVar = this.sinkBuffer;
                long j = gVar.f2102c;
                gVar.a(jVar);
                this.sinkBuffer.a(this.maskCursor);
                this.maskCursor.g(j);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        } else {
            this.sinkBuffer.writeByte(iG);
            this.sinkBuffer.a(jVar);
        }
        this.sink.flush();
    }

    public B newMessageSink(int i, long j) {
        if (this.activeWriter) {
            throw new IllegalStateException("Another message writer is active. Did you call close()?");
        }
        this.activeWriter = true;
        FrameSink frameSink = this.frameSink;
        frameSink.formatOpcode = i;
        frameSink.contentLength = j;
        frameSink.isFirstFrame = true;
        frameSink.closed = false;
        return frameSink;
    }

    public void writeClose(int i, j jVar) {
        j jVarN = j.f2110b;
        if (i != 0 || jVar != null) {
            if (i != 0) {
                WebSocketProtocol.validateCloseCode(i);
            }
            g gVar = new g();
            gVar.writeShort(i);
            if (jVar != null) {
                gVar.a(jVar);
            }
            jVarN = gVar.n();
        }
        try {
            writeControlFrame(8, jVarN);
        } finally {
            this.writerClosed = true;
        }
    }

    public void writeMessageFrame(int i, long j, boolean z, boolean z2) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        if (!z) {
            i = 0;
        }
        if (z2) {
            i |= 128;
        }
        this.sinkBuffer.writeByte(i);
        int i2 = this.isClient ? 128 : 0;
        if (j <= 125) {
            this.sinkBuffer.writeByte(((int) j) | i2);
        } else if (j <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            this.sinkBuffer.writeByte(i2 | 126);
            this.sinkBuffer.writeShort((int) j);
        } else {
            this.sinkBuffer.writeByte(i2 | 127);
            this.sinkBuffer.j(j);
        }
        if (this.isClient) {
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.write(this.maskKey);
            if (j > 0) {
                g gVar = this.sinkBuffer;
                long j2 = gVar.f2102c;
                gVar.write(this.buffer, j);
                this.sinkBuffer.a(this.maskCursor);
                this.maskCursor.g(j2);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        } else {
            this.sinkBuffer.write(this.buffer, j);
        }
        this.sink.j();
    }

    public void writePing(j jVar) throws IOException {
        writeControlFrame(9, jVar);
    }

    public void writePong(j jVar) throws IOException {
        writeControlFrame(10, jVar);
    }
}
