package okhttp3.internal.ws;

import b.a.a.a.a;
import com.google.common.net.HttpHeaders;
import com.google.firebase.storage.network.NetworkRequest;
import d.h;
import d.i;
import d.j;
import d.t;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;

/* JADX INFO: loaded from: classes2.dex */
public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    public static final long MAX_QUEUE_SIZE = 16777216;
    public static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
    public boolean awaitingPong;
    public Call call;
    public ScheduledFuture<?> cancelFuture;
    public boolean enqueuedClose;
    public ScheduledExecutorService executor;
    public boolean failed;
    public final String key;
    public final WebSocketListener listener;
    public final Request originalRequest;
    public final long pingIntervalMillis;
    public long queueSize;
    public final Random random;
    public WebSocketReader reader;
    public String receivedCloseReason;
    public int receivedPingCount;
    public int receivedPongCount;
    public int sentPingCount;
    public Streams streams;
    public WebSocketWriter writer;
    public final Runnable writerRunnable;
    public final ArrayDeque<j> pongQueue = new ArrayDeque<>();
    public final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque<>();
    public int receivedCloseCode = -1;

    final class CancelRunnable implements Runnable {
        public CancelRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RealWebSocket.this.cancel();
        }
    }

    static final class Close {
        public final long cancelAfterCloseMillis;
        public final int code;
        public final j reason;

        public Close(int i, j jVar, long j) {
            this.code = i;
            this.reason = jVar;
            this.cancelAfterCloseMillis = j;
        }
    }

    static final class Message {
        public final j data;
        public final int formatOpcode;

        public Message(int i, j jVar) {
            this.formatOpcode = i;
            this.data = jVar;
        }
    }

    private final class PingRunnable implements Runnable {
        public PingRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RealWebSocket.this.writePingFrame();
        }
    }

    public static abstract class Streams implements Closeable {
        public final boolean client;
        public final h sink;
        public final i source;

        public Streams(boolean z, i iVar, h hVar) {
            this.client = z;
            this.source = iVar;
            this.sink = hVar;
        }
    }

    public RealWebSocket(Request request, WebSocketListener webSocketListener, Random random, long j) {
        if (!NetworkRequest.GET.equals(request.method())) {
            StringBuilder sbA = a.a("Request must be GET: ");
            sbA.append(request.method());
            throw new IllegalArgumentException(sbA.toString());
        }
        this.originalRequest = request;
        this.listener = webSocketListener;
        this.random = random;
        this.pingIntervalMillis = j;
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        this.key = j.a(bArr).a();
        this.writerRunnable = new Runnable() { // from class: okhttp3.internal.ws.RealWebSocket.1
            @Override // java.lang.Runnable
            public void run() {
                do {
                    try {
                    } catch (IOException e2) {
                        RealWebSocket.this.failWebSocket(e2, null);
                        return;
                    }
                } while (RealWebSocket.this.writeOneFrame());
            }
        };
    }

    private void runWriter() {
        ScheduledExecutorService scheduledExecutorService = this.executor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.execute(this.writerRunnable);
        }
    }

    public void awaitTermination(int i, TimeUnit timeUnit) {
        this.executor.awaitTermination(i, timeUnit);
    }

    @Override // okhttp3.WebSocket
    public void cancel() {
        this.call.cancel();
    }

    public void checkResponse(Response response) throws ProtocolException {
        if (response.code() != 101) {
            StringBuilder sbA = a.a("Expected HTTP 101 response but was '");
            sbA.append(response.code());
            sbA.append(" ");
            sbA.append(response.message());
            sbA.append("'");
            throw new ProtocolException(sbA.toString());
        }
        String strHeader = response.header(HttpHeaders.CONNECTION);
        if (!HttpHeaders.UPGRADE.equalsIgnoreCase(strHeader)) {
            throw new ProtocolException(a.a("Expected 'Connection' header value 'Upgrade' but was '", strHeader, "'"));
        }
        String strHeader2 = response.header(HttpHeaders.UPGRADE);
        if (!"websocket".equalsIgnoreCase(strHeader2)) {
            throw new ProtocolException(a.a("Expected 'Upgrade' header value 'websocket' but was '", strHeader2, "'"));
        }
        String strHeader3 = response.header(HttpHeaders.SEC_WEBSOCKET_ACCEPT);
        String strA = j.d(this.key + WebSocketProtocol.ACCEPT_MAGIC).e().a();
        if (strA.equals(strHeader3)) {
            return;
        }
        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + strA + "' but was '" + strHeader3 + "'");
    }

    @Override // okhttp3.WebSocket
    public boolean close(int i, String str) {
        return close(i, str, 60000L);
    }

    public void connect(OkHttpClient okHttpClient) {
        OkHttpClient okHttpClientBuild = okHttpClient.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        final Request requestBuild = this.originalRequest.newBuilder().header(HttpHeaders.UPGRADE, "websocket").header(HttpHeaders.CONNECTION, HttpHeaders.UPGRADE).header(HttpHeaders.SEC_WEBSOCKET_KEY, this.key).header(HttpHeaders.SEC_WEBSOCKET_VERSION, "13").build();
        this.call = Internal.instance.newWebSocketCall(okHttpClientBuild, requestBuild);
        this.call.timeout().clearTimeout();
        this.call.enqueue(new Callback() { // from class: okhttp3.internal.ws.RealWebSocket.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                RealWebSocket.this.failWebSocket(iOException, null);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                try {
                    RealWebSocket.this.checkResponse(response);
                    StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
                    streamAllocation.noNewStreams();
                    Streams streamsNewWebSocketStreams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
                    try {
                        RealWebSocket.this.listener.onOpen(RealWebSocket.this, response);
                        RealWebSocket.this.initReaderAndWriter("OkHttp WebSocket " + requestBuild.url().redact(), streamsNewWebSocketStreams);
                        streamAllocation.connection().socket().setSoTimeout(0);
                        RealWebSocket.this.loopReader();
                    } catch (Exception e2) {
                        RealWebSocket.this.failWebSocket(e2, null);
                    }
                } catch (ProtocolException e3) {
                    RealWebSocket.this.failWebSocket(e3, response);
                    Util.closeQuietly(response);
                }
            }
        });
    }

    public void failWebSocket(Exception exc, Response response) {
        synchronized (this) {
            if (this.failed) {
                return;
            }
            this.failed = true;
            Streams streams = this.streams;
            this.streams = null;
            if (this.cancelFuture != null) {
                this.cancelFuture.cancel(false);
            }
            if (this.executor != null) {
                this.executor.shutdown();
            }
            try {
                this.listener.onFailure(this, exc, response);
            } finally {
                Util.closeQuietly(streams);
            }
        }
    }

    public void initReaderAndWriter(String str, Streams streams) {
        synchronized (this) {
            this.streams = streams;
            this.writer = new WebSocketWriter(streams.client, streams.sink, this.random);
            this.executor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(str, false));
            if (this.pingIntervalMillis != 0) {
                this.executor.scheduleAtFixedRate(new PingRunnable(), this.pingIntervalMillis, this.pingIntervalMillis, TimeUnit.MILLISECONDS);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                runWriter();
            }
        }
        this.reader = new WebSocketReader(streams.client, streams.source, this);
    }

    public void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            this.reader.processNextFrame();
        }
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadClose(int i, String str) {
        Streams streams;
        if (i == -1) {
            throw new IllegalArgumentException();
        }
        synchronized (this) {
            if (this.receivedCloseCode != -1) {
                throw new IllegalStateException("already closed");
            }
            this.receivedCloseCode = i;
            this.receivedCloseReason = str;
            if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
                streams = this.streams;
                this.streams = null;
                if (this.cancelFuture != null) {
                    this.cancelFuture.cancel(false);
                }
                this.executor.shutdown();
            } else {
                streams = null;
            }
        }
        try {
            this.listener.onClosing(this, i, str);
            if (streams != null) {
                this.listener.onClosed(this, i, str);
            }
        } finally {
            Util.closeQuietly(streams);
        }
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(String str) {
        this.listener.onMessage(this, str);
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPing(j jVar) {
        if (!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
            this.pongQueue.add(jVar);
            runWriter();
            this.receivedPingCount++;
        }
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPong(j jVar) {
        this.receivedPongCount++;
        this.awaitingPong = false;
    }

    public synchronized boolean pong(j jVar) {
        if (!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
            this.pongQueue.add(jVar);
            runWriter();
            return true;
        }
        return false;
    }

    public boolean processNextFrame() {
        try {
            this.reader.processNextFrame();
            return this.receivedCloseCode == -1;
        } catch (Exception e2) {
            failWebSocket(e2, null);
            return false;
        }
    }

    @Override // okhttp3.WebSocket
    public synchronized long queueSize() {
        return this.queueSize;
    }

    public synchronized int receivedPingCount() {
        return this.receivedPingCount;
    }

    public synchronized int receivedPongCount() {
        return this.receivedPongCount;
    }

    @Override // okhttp3.WebSocket
    public Request request() {
        return this.originalRequest;
    }

    @Override // okhttp3.WebSocket
    public boolean send(String str) {
        if (str != null) {
            return send(j.d(str), 1);
        }
        throw new NullPointerException("text == null");
    }

    public synchronized int sentPingCount() {
        return this.sentPingCount;
    }

    public void tearDown() {
        ScheduledFuture<?> scheduledFuture = this.cancelFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.executor.shutdown();
        this.executor.awaitTermination(10L, TimeUnit.SECONDS);
    }

    public boolean writeOneFrame() {
        int i;
        Object objPoll;
        String str;
        synchronized (this) {
            if (this.failed) {
                return false;
            }
            WebSocketWriter webSocketWriter = this.writer;
            j jVarPoll = this.pongQueue.poll();
            Streams streams = null;
            if (jVarPoll == null) {
                objPoll = this.messageAndCloseQueue.poll();
                if (objPoll instanceof Close) {
                    i = this.receivedCloseCode;
                    str = this.receivedCloseReason;
                    if (i != -1) {
                        Streams streams2 = this.streams;
                        this.streams = null;
                        this.executor.shutdown();
                        streams = streams2;
                    } else {
                        this.cancelFuture = this.executor.schedule(new CancelRunnable(), ((Close) objPoll).cancelAfterCloseMillis, TimeUnit.MILLISECONDS);
                    }
                } else {
                    if (objPoll == null) {
                        return false;
                    }
                    i = -1;
                    str = null;
                }
            } else {
                i = -1;
                objPoll = null;
                str = null;
            }
            try {
                if (jVarPoll != null) {
                    webSocketWriter.writePong(jVarPoll);
                } else if (objPoll instanceof Message) {
                    j jVar = ((Message) objPoll).data;
                    h hVarA = t.a(webSocketWriter.newMessageSink(((Message) objPoll).formatOpcode, jVar.g()));
                    hVarA.a(jVar);
                    hVarA.close();
                    synchronized (this) {
                        this.queueSize -= (long) jVar.g();
                    }
                } else {
                    if (!(objPoll instanceof Close)) {
                        throw new AssertionError();
                    }
                    Close close = (Close) objPoll;
                    webSocketWriter.writeClose(close.code, close.reason);
                    if (streams != null) {
                        this.listener.onClosed(this, i, str);
                    }
                }
                Util.closeQuietly(streams);
                return true;
            } catch (Throwable th) {
                Util.closeQuietly(streams);
                throw th;
            }
        }
    }

    public void writePingFrame() {
        synchronized (this) {
            if (this.failed) {
                return;
            }
            WebSocketWriter webSocketWriter = this.writer;
            int i = this.awaitingPong ? this.sentPingCount : -1;
            this.sentPingCount++;
            this.awaitingPong = true;
            if (i == -1) {
                try {
                    webSocketWriter.writePing(j.f2110b);
                    return;
                } catch (IOException e2) {
                    failWebSocket(e2, null);
                    return;
                }
            }
            StringBuilder sbA = a.a("sent ping but didn't receive pong within ");
            sbA.append(this.pingIntervalMillis);
            sbA.append("ms (after ");
            sbA.append(i - 1);
            sbA.append(" successful ping/pongs)");
            failWebSocket(new SocketTimeoutException(sbA.toString()), null);
        }
    }

    public synchronized boolean close(int i, String str, long j) {
        WebSocketProtocol.validateCloseCode(i);
        j jVarD = null;
        if (str != null) {
            jVarD = j.d(str);
            if (jVarD.g() > 123) {
                throw new IllegalArgumentException("reason.size() > 123: " + str);
            }
        }
        if (!this.failed && !this.enqueuedClose) {
            this.enqueuedClose = true;
            this.messageAndCloseQueue.add(new Close(i, jVarD, j));
            runWriter();
            return true;
        }
        return false;
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(j jVar) {
        this.listener.onMessage(this, jVar);
    }

    @Override // okhttp3.WebSocket
    public boolean send(j jVar) {
        if (jVar != null) {
            return send(jVar, 2);
        }
        throw new NullPointerException("bytes == null");
    }

    private synchronized boolean send(j jVar, int i) {
        if (!this.failed && !this.enqueuedClose) {
            if (this.queueSize + ((long) jVar.g()) > MAX_QUEUE_SIZE) {
                close(1001, null);
                return false;
            }
            this.queueSize += (long) jVar.g();
            this.messageAndCloseQueue.add(new Message(i, jVar));
            runWriter();
            return true;
        }
        return false;
    }
}
