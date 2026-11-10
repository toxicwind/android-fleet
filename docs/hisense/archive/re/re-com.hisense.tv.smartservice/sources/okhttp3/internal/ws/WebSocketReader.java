package okhttp3.internal.ws;

import b.a.a.a.a;
import d.g;
import d.i;
import d.j;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class WebSocketReader {
    public boolean closed;
    public final FrameCallback frameCallback;
    public long frameLength;
    public final boolean isClient;
    public boolean isControlFrame;
    public boolean isFinalFrame;
    public final g.a maskCursor;
    public final byte[] maskKey;
    public int opcode;
    public final i source;
    public final g controlFrameBuffer = new g();
    public final g messageFrameBuffer = new g();

    public interface FrameCallback {
        void onReadClose(int i, String str);

        void onReadMessage(j jVar);

        void onReadMessage(String str);

        void onReadPing(j jVar);

        void onReadPong(j jVar);
    }

    public WebSocketReader(boolean z, i iVar, FrameCallback frameCallback) {
        if (iVar == null) {
            throw new NullPointerException("source == null");
        }
        if (frameCallback == null) {
            throw new NullPointerException("frameCallback == null");
        }
        this.isClient = z;
        this.source = iVar;
        this.frameCallback = frameCallback;
        this.maskKey = z ? null : new byte[4];
        this.maskCursor = z ? null : new g.a();
    }

    private void readControlFrame() throws ProtocolException {
        String strO;
        long j = this.frameLength;
        if (j > 0) {
            this.source.a(this.controlFrameBuffer, j);
            if (!this.isClient) {
                this.controlFrameBuffer.a(this.maskCursor);
                this.maskCursor.g(0L);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        switch (this.opcode) {
            case 8:
                short s = 1005;
                g gVar = this.controlFrameBuffer;
                long j2 = gVar.f2102c;
                if (j2 == 1) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (j2 != 0) {
                    s = gVar.readShort();
                    strO = this.controlFrameBuffer.o();
                    String strCloseCodeExceptionMessage = WebSocketProtocol.closeCodeExceptionMessage(s);
                    if (strCloseCodeExceptionMessage != null) {
                        throw new ProtocolException(strCloseCodeExceptionMessage);
                    }
                } else {
                    strO = "";
                }
                this.frameCallback.onReadClose(s, strO);
                this.closed = true;
                return;
            case 9:
                this.frameCallback.onReadPing(this.controlFrameBuffer.n());
                return;
            case 10:
                this.frameCallback.onReadPong(this.controlFrameBuffer.n());
                return;
            default:
                StringBuilder sbA = a.a("Unknown control opcode: ");
                sbA.append(Integer.toHexString(this.opcode));
                throw new ProtocolException(sbA.toString());
        }
    }

    private void readHeader() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        long jTimeoutNanos = this.source.timeout().timeoutNanos();
        this.source.timeout().clearTimeout();
        try {
            int i = this.source.readByte() & 255;
            this.source.timeout().timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
            this.opcode = i & 15;
            this.isFinalFrame = (i & 128) != 0;
            this.isControlFrame = (i & 8) != 0;
            if (this.isControlFrame && !this.isFinalFrame) {
                throw new ProtocolException("Control frames must be final.");
            }
            boolean z = (i & 64) != 0;
            boolean z2 = (i & 32) != 0;
            boolean z3 = (i & 16) != 0;
            if (z || z2 || z3) {
                throw new ProtocolException("Reserved flags are unsupported.");
            }
            int i2 = this.source.readByte() & 255;
            boolean z4 = (i2 & 128) != 0;
            boolean z5 = this.isClient;
            if (z4 == z5) {
                throw new ProtocolException(z5 ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
            }
            this.frameLength = i2 & 127;
            long j = this.frameLength;
            if (j == 126) {
                this.frameLength = ((long) this.source.readShort()) & WebSocketProtocol.PAYLOAD_SHORT_MAX;
            } else if (j == 127) {
                this.frameLength = this.source.readLong();
                if (this.frameLength < 0) {
                    StringBuilder sbA = a.a("Frame length 0x");
                    sbA.append(Long.toHexString(this.frameLength));
                    sbA.append(" > 0x7FFFFFFFFFFFFFFF");
                    throw new ProtocolException(sbA.toString());
                }
            }
            if (this.isControlFrame && this.frameLength > 125) {
                throw new ProtocolException("Control frame must be less than 125B.");
            }
            if (z4) {
                this.source.readFully(this.maskKey);
            }
        } catch (Throwable th) {
            this.source.timeout().timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
            throw th;
        }
    }

    private void readMessage() throws IOException {
        while (!this.closed) {
            long j = this.frameLength;
            if (j > 0) {
                this.source.a(this.messageFrameBuffer, j);
                if (!this.isClient) {
                    this.messageFrameBuffer.a(this.maskCursor);
                    this.maskCursor.g(this.messageFrameBuffer.f2102c - this.frameLength);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            }
            if (this.isFinalFrame) {
                return;
            }
            readUntilNonControlFrame();
            if (this.opcode != 0) {
                StringBuilder sbA = a.a("Expected continuation opcode. Got: ");
                sbA.append(Integer.toHexString(this.opcode));
                throw new ProtocolException(sbA.toString());
            }
        }
        throw new IOException("closed");
    }

    private void readMessageFrame() throws IOException {
        int i = this.opcode;
        if (i != 1 && i != 2) {
            StringBuilder sbA = a.a("Unknown opcode: ");
            sbA.append(Integer.toHexString(i));
            throw new ProtocolException(sbA.toString());
        }
        readMessage();
        if (i == 1) {
            this.frameCallback.onReadMessage(this.messageFrameBuffer.o());
        } else {
            this.frameCallback.onReadMessage(this.messageFrameBuffer.n());
        }
    }

    private void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            readHeader();
            if (!this.isControlFrame) {
                return;
            } else {
                readControlFrame();
            }
        }
    }

    public void processNextFrame() throws IOException {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }
}
