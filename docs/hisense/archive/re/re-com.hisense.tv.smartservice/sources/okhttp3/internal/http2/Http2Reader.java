package okhttp3.internal.http2;

import com.ju.lib.datalayer.database.asist.SQLStatement;
import d.C;
import d.E;
import d.g;
import d.i;
import d.j;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes2.dex */
public final class Http2Reader implements Closeable {
    public static final Logger logger = Logger.getLogger(Http2.class.getName());
    public final boolean client;
    public final ContinuationSource continuation;
    public final Hpack.Reader hpackReader;
    public final i source;

    static final class ContinuationSource implements C {
        public byte flags;
        public int left;
        public int length;
        public short padding;
        public final i source;
        public int streamId;

        public ContinuationSource(i iVar) {
            this.source = iVar;
        }

        private void readContinuationHeader() throws IOException {
            int i = this.streamId;
            int medium = Http2Reader.readMedium(this.source);
            this.left = medium;
            this.length = medium;
            byte b2 = (byte) (this.source.readByte() & 255);
            this.flags = (byte) (this.source.readByte() & 255);
            if (Http2Reader.logger.isLoggable(Level.FINE)) {
                Http2Reader.logger.fine(Http2.frameLog(true, this.streamId, this.length, b2, this.flags));
            }
            this.streamId = this.source.readInt() & Integer.MAX_VALUE;
            if (b2 != 9) {
                Http2.ioException("%s != TYPE_CONTINUATION", Byte.valueOf(b2));
                throw null;
            }
            if (this.streamId == i) {
                return;
            }
            Http2.ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
            throw null;
        }

        @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // d.C
        public long read(g gVar, long j) throws IOException {
            while (true) {
                int i = this.left;
                if (i != 0) {
                    long j2 = this.source.read(gVar, Math.min(j, i));
                    if (j2 == -1) {
                        return -1L;
                    }
                    this.left = (int) (((long) this.left) - j2);
                    return j2;
                }
                this.source.skip(this.padding);
                this.padding = (short) 0;
                if ((this.flags & 4) != 0) {
                    return -1L;
                }
                readContinuationHeader();
            }
        }

        @Override // d.C
        public E timeout() {
            return this.source.timeout();
        }
    }

    interface Handler {
        void ackSettings();

        void alternateService(int i, String str, j jVar, String str2, int i2, long j);

        void data(boolean z, int i, i iVar, int i2);

        void goAway(int i, ErrorCode errorCode, j jVar);

        void headers(boolean z, int i, int i2, List<Header> list);

        void ping(boolean z, int i, int i2);

        void priority(int i, int i2, int i3, boolean z);

        void pushPromise(int i, int i2, List<Header> list);

        void rstStream(int i, ErrorCode errorCode);

        void settings(boolean z, Settings settings);

        void windowUpdate(int i, long j);
    }

    public Http2Reader(i iVar, boolean z) {
        this.source = iVar;
        this.client = z;
        this.continuation = new ContinuationSource(this.source);
        this.hpackReader = new Hpack.Reader(4096, 4096, this.continuation);
    }

    public static int lengthWithoutPadding(int i, byte b2, short s) throws IOException {
        if ((b2 & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        Http2.ioException("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw null;
    }

    private void readData(Handler handler, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            Http2.ioException("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
            throw null;
        }
        boolean z = (b2 & 1) != 0;
        if ((b2 & 32) != 0) {
            Http2.ioException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
            throw null;
        }
        short s = (b2 & 8) != 0 ? (short) (this.source.readByte() & 255) : (short) 0;
        handler.data(z, i2, this.source, lengthWithoutPadding(i, b2, s));
        this.source.skip(s);
    }

    private void readGoAway(Handler handler, int i, byte b2, int i2) throws IOException {
        if (i < 8) {
            Http2.ioException("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            Http2.ioException("TYPE_GOAWAY streamId != 0", new Object[0]);
            throw null;
        }
        int i3 = this.source.readInt();
        int i4 = this.source.readInt();
        int i5 = i - 8;
        ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(i4);
        if (errorCodeFromHttp2 == null) {
            Http2.ioException("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(i4));
            throw null;
        }
        j jVarD = j.f2110b;
        if (i5 > 0) {
            jVarD = this.source.d(i5);
        }
        handler.goAway(i3, errorCodeFromHttp2, jVarD);
    }

    private List<Header> readHeaderBlock(int i, short s, byte b2, int i2) throws IOException {
        ContinuationSource continuationSource = this.continuation;
        continuationSource.left = i;
        continuationSource.length = i;
        continuationSource.padding = s;
        continuationSource.flags = b2;
        continuationSource.streamId = i2;
        this.hpackReader.readHeaders();
        return this.hpackReader.getAndResetHeaderList();
    }

    private void readHeaders(Handler handler, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            Http2.ioException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            throw null;
        }
        boolean z = (b2 & 1) != 0;
        short s = (b2 & 8) != 0 ? (short) (this.source.readByte() & 255) : (short) 0;
        if ((b2 & 32) != 0) {
            readPriority(handler, i2);
            i -= 5;
        }
        handler.headers(z, i2, -1, readHeaderBlock(lengthWithoutPadding(i, b2, s), s, b2, i2));
    }

    public static int readMedium(i iVar) {
        return (iVar.readByte() & 255) | ((iVar.readByte() & 255) << 16) | ((iVar.readByte() & 255) << 8);
    }

    private void readPing(Handler handler, int i, byte b2, int i2) throws IOException {
        if (i != 8) {
            Http2.ioException("TYPE_PING length != 8: %s", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            Http2.ioException("TYPE_PING streamId != 0", new Object[0]);
            throw null;
        }
        handler.ping((b2 & 1) != 0, this.source.readInt(), this.source.readInt());
    }

    private void readPriority(Handler handler, int i, byte b2, int i2) throws IOException {
        if (i != 5) {
            Http2.ioException("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            readPriority(handler, i2);
        } else {
            Http2.ioException("TYPE_PRIORITY streamId == 0", new Object[0]);
            throw null;
        }
    }

    private void readPushPromise(Handler handler, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            Http2.ioException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            throw null;
        }
        short s = (b2 & 8) != 0 ? (short) (this.source.readByte() & 255) : (short) 0;
        handler.pushPromise(i2, this.source.readInt() & Integer.MAX_VALUE, readHeaderBlock(lengthWithoutPadding(i - 4, b2, s), s, b2, i2));
    }

    private void readRstStream(Handler handler, int i, byte b2, int i2) throws IOException {
        if (i != 4) {
            Http2.ioException("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
            throw null;
        }
        if (i2 == 0) {
            Http2.ioException("TYPE_RST_STREAM streamId == 0", new Object[0]);
            throw null;
        }
        int i3 = this.source.readInt();
        ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(i3);
        if (errorCodeFromHttp2 != null) {
            handler.rstStream(i2, errorCodeFromHttp2);
        } else {
            Http2.ioException("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(i3));
            throw null;
        }
    }

    private void readSettings(Handler handler, int i, byte b2, int i2) throws IOException {
        if (i2 != 0) {
            Http2.ioException("TYPE_SETTINGS streamId != 0", new Object[0]);
            throw null;
        }
        if ((b2 & 1) != 0) {
            if (i == 0) {
                handler.ackSettings();
                return;
            } else {
                Http2.ioException("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                throw null;
            }
        }
        if (i % 6 != 0) {
            Http2.ioException("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i));
            throw null;
        }
        Settings settings = new Settings();
        for (int i3 = 0; i3 < i; i3 += 6) {
            int i4 = this.source.readShort() & SQLStatement.NONE;
            int i5 = this.source.readInt();
            switch (i4) {
                case 2:
                    if (i5 != 0 && i5 != 1) {
                        Http2.ioException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                        throw null;
                    }
                    break;
                    break;
                case 3:
                    i4 = 4;
                    break;
                case 4:
                    i4 = 7;
                    if (i5 < 0) {
                        Http2.ioException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                        throw null;
                    }
                    break;
                    break;
                case 5:
                    if (i5 < 16384 || i5 > 16777215) {
                        Http2.ioException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(i5));
                        throw null;
                    }
                    break;
                    break;
            }
            settings.set(i4, i5);
        }
        handler.settings(false, settings);
    }

    private void readWindowUpdate(Handler handler, int i, byte b2, int i2) throws IOException {
        if (i != 4) {
            Http2.ioException("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
            throw null;
        }
        long j = ((long) this.source.readInt()) & 2147483647L;
        if (j != 0) {
            handler.windowUpdate(i2, j);
        } else {
            Http2.ioException("windowSizeIncrement was 0", Long.valueOf(j));
            throw null;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.source.close();
    }

    public boolean nextFrame(boolean z, Handler handler) throws IOException {
        try {
            this.source.c(9L);
            int medium = readMedium(this.source);
            if (medium < 0 || medium > 16384) {
                Http2.ioException("FRAME_SIZE_ERROR: %s", Integer.valueOf(medium));
                throw null;
            }
            byte b2 = (byte) (this.source.readByte() & 255);
            if (z && b2 != 4) {
                Http2.ioException("Expected a SETTINGS frame but was %s", Byte.valueOf(b2));
                throw null;
            }
            byte b3 = (byte) (this.source.readByte() & 255);
            int i = this.source.readInt() & Integer.MAX_VALUE;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(Http2.frameLog(true, i, medium, b2, b3));
            }
            switch (b2) {
                case 0:
                    readData(handler, medium, b3, i);
                    return true;
                case 1:
                    readHeaders(handler, medium, b3, i);
                    return true;
                case 2:
                    readPriority(handler, medium, b3, i);
                    return true;
                case 3:
                    readRstStream(handler, medium, b3, i);
                    return true;
                case 4:
                    readSettings(handler, medium, b3, i);
                    return true;
                case 5:
                    readPushPromise(handler, medium, b3, i);
                    return true;
                case 6:
                    readPing(handler, medium, b3, i);
                    return true;
                case 7:
                    readGoAway(handler, medium, b3, i);
                    return true;
                case 8:
                    readWindowUpdate(handler, medium, b3, i);
                    return true;
                default:
                    this.source.skip(medium);
                    return true;
            }
        } catch (IOException unused) {
            return false;
        }
    }

    public void readConnectionPreface(Handler handler) throws IOException {
        if (this.client) {
            if (nextFrame(true, handler)) {
                return;
            }
            Http2.ioException("Required SETTINGS preface not received", new Object[0]);
            throw null;
        }
        j jVarD = this.source.d(Http2.CONNECTION_PREFACE.g());
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Util.format("<< CONNECTION %s", jVarD.b()));
        }
        if (Http2.CONNECTION_PREFACE.equals(jVarD)) {
            return;
        }
        Http2.ioException("Expected a connection header but was %s", jVarD.j());
        throw null;
    }

    private void readPriority(Handler handler, int i) {
        int i2 = this.source.readInt();
        handler.priority(i, i2 & Integer.MAX_VALUE, (this.source.readByte() & 255) + 1, (Integer.MIN_VALUE & i2) != 0);
    }
}
