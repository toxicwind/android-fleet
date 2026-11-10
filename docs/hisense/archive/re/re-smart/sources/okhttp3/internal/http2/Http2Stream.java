package okhttp3.internal.http2;

import b.a.a.a.a;
import com.ju.lib.datacommunication.network.http.dns.Tools;
import d.B;
import d.C;
import d.C0244c;
import d.E;
import d.g;
import d.i;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import okhttp3.Headers;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes2.dex */
public final class Http2Stream {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public long bytesLeftInWriteWindow;
    public final Http2Connection connection;
    public boolean hasResponseHeaders;
    public Header.Listener headersListener;
    public final int id;
    public final FramingSink sink;
    public final FramingSource source;
    public long unacknowledgedBytesRead = 0;
    public final Deque<Headers> headersQueue = new ArrayDeque();
    public final StreamTimeout readTimeout = new StreamTimeout();
    public final StreamTimeout writeTimeout = new StreamTimeout();
    public ErrorCode errorCode = null;

    final class FramingSink implements B {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public static final long EMIT_BUFFER_SIZE = 16384;
        public boolean closed;
        public boolean finished;
        public final g sendBuffer = new g();

        public FramingSink() {
        }

        private void emitFrame(boolean z) throws IOException {
            long jMin;
            synchronized (Http2Stream.this) {
                Http2Stream.this.writeTimeout.enter();
                while (Http2Stream.this.bytesLeftInWriteWindow <= 0 && !this.finished && !this.closed && Http2Stream.this.errorCode == null) {
                    try {
                        Http2Stream.this.waitForIo();
                    } catch (Throwable th) {
                        Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
                        throw th;
                    }
                }
                Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
                Http2Stream.this.checkOutNotClosed();
                jMin = Math.min(Http2Stream.this.bytesLeftInWriteWindow, this.sendBuffer.f2102c);
                Http2Stream.this.bytesLeftInWriteWindow -= jMin;
            }
            Http2Stream.this.writeTimeout.enter();
            try {
                Http2Stream.this.connection.writeData(Http2Stream.this.id, z && jMin == this.sendBuffer.f2102c, this.sendBuffer, jMin);
            } finally {
                Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
            }
        }

        @Override // d.B, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (Http2Stream.this) {
                if (this.closed) {
                    return;
                }
                Http2Stream http2Stream = Http2Stream.this;
                if (!http2Stream.sink.finished) {
                    if (this.sendBuffer.f2102c > 0) {
                        while (this.sendBuffer.f2102c > 0) {
                            emitFrame(true);
                        }
                    } else {
                        http2Stream.connection.writeData(http2Stream.id, true, null, 0L);
                    }
                }
                synchronized (Http2Stream.this) {
                    this.closed = true;
                }
                Http2Stream.this.connection.flush();
                Http2Stream.this.cancelStreamIfNecessary();
            }
        }

        @Override // d.B, java.io.Flushable
        public void flush() throws IOException {
            synchronized (Http2Stream.this) {
                Http2Stream.this.checkOutNotClosed();
            }
            while (this.sendBuffer.f2102c > 0) {
                emitFrame(false);
                Http2Stream.this.connection.flush();
            }
        }

        @Override // d.B
        public E timeout() {
            return Http2Stream.this.writeTimeout;
        }

        @Override // d.B
        public void write(g gVar, long j) throws IOException {
            this.sendBuffer.write(gVar, j);
            while (this.sendBuffer.f2102c >= EMIT_BUFFER_SIZE) {
                emitFrame(false);
            }
        }
    }

    private final class FramingSource implements C {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public boolean closed;
        public boolean finished;
        public final long maxByteCount;
        public final g receiveBuffer = new g();
        public final g readBuffer = new g();

        public FramingSource(long j) {
            this.maxByteCount = j;
        }

        private void updateConnectionFlowControl(long j) {
            Http2Stream.this.connection.updateConnectionFlowControl(j);
        }

        @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            long j;
            ArrayList arrayList;
            Header.Listener listener;
            synchronized (Http2Stream.this) {
                this.closed = true;
                j = this.readBuffer.f2102c;
                this.readBuffer.clear();
                arrayList = null;
                if (Http2Stream.this.headersQueue.isEmpty() || Http2Stream.this.headersListener == null) {
                    listener = null;
                } else {
                    arrayList = new ArrayList(Http2Stream.this.headersQueue);
                    Http2Stream.this.headersQueue.clear();
                    listener = Http2Stream.this.headersListener;
                }
                Http2Stream.this.notifyAll();
            }
            if (j > 0) {
                updateConnectionFlowControl(j);
            }
            Http2Stream.this.cancelStreamIfNecessary();
            if (listener != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    listener.onHeaders((Headers) it.next());
                }
            }
        }

        @Override // d.C
        public long read(g gVar, long j) throws StreamResetException {
            long j2;
            Headers headersRemoveFirst;
            Header.Listener listener;
            if (j < 0) {
                throw new IllegalArgumentException(a.a("byteCount < 0: ", j));
            }
            while (true) {
                synchronized (Http2Stream.this) {
                    Http2Stream.this.readTimeout.enter();
                    try {
                        ErrorCode errorCode = Http2Stream.this.errorCode != null ? Http2Stream.this.errorCode : null;
                        if (this.closed) {
                            throw new IOException("stream closed");
                        }
                        if (Http2Stream.this.headersQueue.isEmpty() || Http2Stream.this.headersListener == null) {
                            if (this.readBuffer.f2102c > 0) {
                                long j3 = this.readBuffer.read(gVar, Math.min(j, this.readBuffer.f2102c));
                                Http2Stream.this.unacknowledgedBytesRead += j3;
                                if (errorCode == null && Http2Stream.this.unacknowledgedBytesRead >= Http2Stream.this.connection.okHttpSettings.getInitialWindowSize() / 2) {
                                    Http2Stream.this.connection.writeWindowUpdateLater(Http2Stream.this.id, Http2Stream.this.unacknowledgedBytesRead);
                                    Http2Stream.this.unacknowledgedBytesRead = 0L;
                                }
                                j2 = j3;
                            } else if (this.finished || errorCode != null) {
                                j2 = -1;
                            } else {
                                Http2Stream.this.waitForIo();
                                Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
                            }
                            headersRemoveFirst = null;
                            listener = null;
                        } else {
                            headersRemoveFirst = Http2Stream.this.headersQueue.removeFirst();
                            listener = Http2Stream.this.headersListener;
                            j2 = -1;
                        }
                        Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
                        if (headersRemoveFirst == null || listener == null) {
                            if (j2 != -1) {
                                updateConnectionFlowControl(j2);
                                return j2;
                            }
                            if (errorCode == null) {
                                return -1L;
                            }
                            throw new StreamResetException(errorCode);
                        }
                        listener.onHeaders(headersRemoveFirst);
                    } catch (Throwable th) {
                        Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
                        throw th;
                    }
                }
            }
        }

        public void receive(i iVar, long j) throws EOFException {
            boolean z;
            boolean z2;
            boolean z3;
            long j2;
            while (j > 0) {
                synchronized (Http2Stream.this) {
                    z = this.finished;
                    z2 = true;
                    z3 = this.readBuffer.f2102c + j > this.maxByteCount;
                }
                if (z3) {
                    iVar.skip(j);
                    Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z) {
                    iVar.skip(j);
                    return;
                }
                long j3 = iVar.read(this.receiveBuffer, j);
                if (j3 == -1) {
                    throw new EOFException();
                }
                j -= j3;
                synchronized (Http2Stream.this) {
                    if (this.closed) {
                        j2 = this.receiveBuffer.f2102c;
                        this.receiveBuffer.clear();
                    } else {
                        if (this.readBuffer.f2102c != 0) {
                            z2 = false;
                        }
                        this.readBuffer.a(this.receiveBuffer);
                        if (z2) {
                            Http2Stream.this.notifyAll();
                        }
                        j2 = 0;
                    }
                }
                if (j2 > 0) {
                    updateConnectionFlowControl(j2);
                }
            }
        }

        @Override // d.C
        public E timeout() {
            return Http2Stream.this.readTimeout;
        }
    }

    class StreamTimeout extends C0244c {
        public StreamTimeout() {
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }

        @Override // d.C0244c
        public IOException newTimeoutException(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException(Tools.KEY_TIMEOUT);
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // d.C0244c
        public void timedOut() {
            Http2Stream.this.closeLater(ErrorCode.CANCEL);
            Http2Stream.this.connection.sendDegradedPingLater();
        }
    }

    public Http2Stream(int i, Http2Connection http2Connection, boolean z, boolean z2, Headers headers) {
        if (http2Connection == null) {
            throw new NullPointerException("connection == null");
        }
        this.id = i;
        this.connection = http2Connection;
        this.bytesLeftInWriteWindow = http2Connection.peerSettings.getInitialWindowSize();
        this.source = new FramingSource(http2Connection.okHttpSettings.getInitialWindowSize());
        this.sink = new FramingSink();
        this.source.finished = z2;
        this.sink.finished = z;
        if (headers != null) {
            this.headersQueue.add(headers);
        }
        if (isLocallyInitiated() && headers != null) {
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
        }
        if (!isLocallyInitiated() && headers == null) {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }

    private boolean closeInternal(ErrorCode errorCode) {
        synchronized (this) {
            if (this.errorCode != null) {
                return false;
            }
            if (this.source.finished && this.sink.finished) {
                return false;
            }
            this.errorCode = errorCode;
            notifyAll();
            this.connection.removeStream(this.id);
            return true;
        }
    }

    public void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    public void cancelStreamIfNecessary() {
        boolean z;
        boolean zIsOpen;
        synchronized (this) {
            z = !this.source.finished && this.source.closed && (this.sink.finished || this.sink.closed);
            zIsOpen = isOpen();
        }
        if (z) {
            close(ErrorCode.CANCEL);
        } else {
            if (zIsOpen) {
                return;
            }
            this.connection.removeStream(this.id);
        }
    }

    public void checkOutNotClosed() throws IOException {
        FramingSink framingSink = this.sink;
        if (framingSink.closed) {
            throw new IOException("stream closed");
        }
        if (framingSink.finished) {
            throw new IOException("stream finished");
        }
        ErrorCode errorCode = this.errorCode;
        if (errorCode != null) {
            throw new StreamResetException(errorCode);
        }
    }

    public void close(ErrorCode errorCode) {
        if (closeInternal(errorCode)) {
            this.connection.writeSynReset(this.id, errorCode);
        }
    }

    public void closeLater(ErrorCode errorCode) {
        if (closeInternal(errorCode)) {
            this.connection.writeSynResetLater(this.id, errorCode);
        }
    }

    public Http2Connection getConnection() {
        return this.connection;
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public int getId() {
        return this.id;
    }

    public B getSink() {
        synchronized (this) {
            if (!this.hasResponseHeaders && !isLocallyInitiated()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.sink;
    }

    public C getSource() {
        return this.source;
    }

    public boolean isLocallyInitiated() {
        return this.connection.client == ((this.id & 1) == 1);
    }

    public synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        return ((this.source.finished || this.source.closed) && (this.sink.finished || this.sink.closed) && this.hasResponseHeaders) ? false : true;
    }

    public E readTimeout() {
        return this.readTimeout;
    }

    public void receiveData(i iVar, int i) throws EOFException {
        this.source.receive(iVar, i);
    }

    public void receiveFin() {
        boolean zIsOpen;
        synchronized (this) {
            this.source.finished = true;
            zIsOpen = isOpen();
            notifyAll();
        }
        if (zIsOpen) {
            return;
        }
        this.connection.removeStream(this.id);
    }

    public void receiveHeaders(List<Header> list) {
        boolean zIsOpen;
        synchronized (this) {
            this.hasResponseHeaders = true;
            this.headersQueue.add(Util.toHeaders(list));
            zIsOpen = isOpen();
            notifyAll();
        }
        if (zIsOpen) {
            return;
        }
        this.connection.removeStream(this.id);
    }

    public synchronized void receiveRstStream(ErrorCode errorCode) {
        if (this.errorCode == null) {
            this.errorCode = errorCode;
            notifyAll();
        }
    }

    public synchronized void setHeadersListener(Header.Listener listener) {
        this.headersListener = listener;
        if (!this.headersQueue.isEmpty() && listener != null) {
            notifyAll();
        }
    }

    public synchronized Headers takeHeaders() {
        this.readTimeout.enter();
        while (this.headersQueue.isEmpty() && this.errorCode == null) {
            try {
                waitForIo();
            } catch (Throwable th) {
                this.readTimeout.exitAndThrowIfTimedOut();
                throw th;
            }
        }
        this.readTimeout.exitAndThrowIfTimedOut();
        if (this.headersQueue.isEmpty()) {
            throw new StreamResetException(this.errorCode);
        }
        return this.headersQueue.removeFirst();
    }

    public void waitForIo() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    public void writeHeaders(List<Header> list, boolean z) {
        boolean z2;
        boolean z3;
        if (list == null) {
            throw new NullPointerException("headers == null");
        }
        synchronized (this) {
            this.hasResponseHeaders = true;
            if (z) {
                z2 = false;
            } else {
                this.sink.finished = true;
                z2 = true;
            }
            z3 = z2;
        }
        if (!z2) {
            synchronized (this.connection) {
                z2 = this.connection.bytesLeftInWriteWindow == 0;
            }
        }
        this.connection.writeSynReply(this.id, z3, list);
        if (z2) {
            this.connection.flush();
        }
    }

    public E writeTimeout() {
        return this.writeTimeout;
    }
}
