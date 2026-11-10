package org.java_websocket.client;

import com.mediatek.twoworlds.tv.MtkTvScanCeBase;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import org.java_websocket.AbstractWebSocket;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;

/* JADX INFO: loaded from: classes.dex */
public abstract class WebSocketClient extends AbstractWebSocket implements Runnable, WebSocket {
    private CountDownLatch closeLatch;
    private CountDownLatch connectLatch;
    private Thread connectReadThread;
    private int connectTimeout;
    private DnsResolver dnsResolver;
    private WebSocketImpl engine;
    private Map<String, String> headers;
    private OutputStream ostream;
    private Proxy proxy;
    private Socket socket;
    private SocketFactory socketFactory;
    protected URI uri;
    private Thread writeThread;

    public abstract void onClose(int i, String str, boolean z);

    public void onCloseInitiated(int i, String str) {
    }

    public void onClosing(int i, String str, boolean z) {
    }

    public abstract void onError(Exception exc);

    public abstract void onMessage(String str);

    public void onMessage(ByteBuffer byteBuffer) {
    }

    public abstract void onOpen(ServerHandshake serverHandshake);

    @Override // org.java_websocket.WebSocketListener
    public final void onWriteDemand(WebSocket webSocket) {
    }

    public WebSocketClient(URI uri, Draft draft, Map<String, String> map) {
        this(uri, draft, map, 0);
    }

    public WebSocketClient(URI uri, Draft draft, Map<String, String> map, int i) {
        this.uri = null;
        this.engine = null;
        this.socket = null;
        this.socketFactory = null;
        this.proxy = Proxy.NO_PROXY;
        this.connectLatch = new CountDownLatch(1);
        this.closeLatch = new CountDownLatch(1);
        this.connectTimeout = 0;
        this.dnsResolver = null;
        if (uri == null) {
            throw new IllegalArgumentException();
        }
        if (draft == null) {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        }
        this.uri = uri;
        this.dnsResolver = new DnsResolver(this) { // from class: org.java_websocket.client.WebSocketClient.1
            @Override // org.java_websocket.client.DnsResolver
            public InetAddress resolve(URI uri2) throws UnknownHostException {
                return InetAddress.getByName(uri2.getHost());
            }
        };
        if (map != null) {
            TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            this.headers = treeMap;
            treeMap.putAll(map);
        }
        this.connectTimeout = i;
        setTcpNoDelay(false);
        setReuseAddr(false);
        this.engine = new WebSocketImpl(this, draft);
    }

    public void connect() {
        if (this.connectReadThread != null) {
            throw new IllegalStateException("WebSocketClient objects are not reuseable");
        }
        Thread thread = new Thread(this);
        this.connectReadThread = thread;
        thread.setName("WebSocketConnectReadThread-" + this.connectReadThread.getId());
        this.connectReadThread.start();
    }

    public void send(String str) {
        this.engine.send(str);
    }

    @Override // org.java_websocket.AbstractWebSocket
    protected Collection<WebSocket> getConnections() {
        return Collections.singletonList(this.engine);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0042 A[Catch: InternalError -> 0x0101, Exception -> 0x0130, TryCatch #4 {Exception -> 0x0130, InternalError -> 0x0101, blocks: (B:3:0x0001, B:5:0x0007, B:12:0x0028, B:14:0x0042, B:17:0x005d, B:19:0x006b, B:20:0x008a, B:22:0x0090, B:23:0x009e, B:6:0x0010, B:8:0x0014, B:9:0x001f, B:40:0x00fb, B:41:0x0100), top: B:53:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:22:0x0090 A[Catch: InternalError -> 0x0101, Exception -> 0x0130, TryCatch #4 {Exception -> 0x0130, InternalError -> 0x0101, blocks: (B:3:0x0001, B:5:0x0007, B:12:0x0028, B:14:0x0042, B:17:0x005d, B:19:0x006b, B:20:0x008a, B:22:0x0090, B:23:0x009e, B:6:0x0010, B:8:0x0014, B:9:0x001f, B:40:0x00fb, B:41:0x0100), top: B:53:0x0001 }] */
    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        byte[] bArr;
        int i;
        try {
            if (this.socketFactory != null) {
                this.socket = this.socketFactory.createSocket();
            } else {
                if (this.socket == null) {
                    this.socket = new Socket(this.proxy);
                    z = true;
                } else if (this.socket.isClosed()) {
                    throw new IOException();
                }
                this.socket.setTcpNoDelay(isTcpNoDelay());
                this.socket.setReuseAddress(isReuseAddr());
                if (!this.socket.isConnected()) {
                    this.socket.connect(new InetSocketAddress(this.dnsResolver.resolve(this.uri), getPort()), this.connectTimeout);
                }
                if (z && "wss".equals(this.uri.getScheme())) {
                    SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
                    sSLContext.init(null, null, null);
                    this.socket = sSLContext.getSocketFactory().createSocket(this.socket, this.uri.getHost(), getPort(), true);
                }
                if (this.socket instanceof SSLSocket) {
                    SSLSocket sSLSocket = (SSLSocket) this.socket;
                    SSLParameters sSLParameters = sSLSocket.getSSLParameters();
                    onSetSSLParameters(sSLParameters);
                    sSLSocket.setSSLParameters(sSLParameters);
                }
                InputStream inputStream = this.socket.getInputStream();
                this.ostream = this.socket.getOutputStream();
                sendHandshake();
                Thread thread = new Thread(new WebsocketWriteThread(this));
                this.writeThread = thread;
                thread.start();
                bArr = new byte[16384];
                while (!isClosing() && !isClosed() && (i = inputStream.read(bArr)) != -1) {
                    try {
                        this.engine.decode(ByteBuffer.wrap(bArr, 0, i));
                    } catch (IOException e) {
                        handleIOException(e);
                    } catch (RuntimeException e2) {
                        onError(e2);
                        this.engine.closeConnection(MtkTvScanCeBase.OPERATOR_WUHAN_SHIWANG, e2.getMessage());
                    }
                }
                this.engine.eot();
                this.connectReadThread = null;
            }
            z = false;
            this.socket.setTcpNoDelay(isTcpNoDelay());
            this.socket.setReuseAddress(isReuseAddr());
            if (!this.socket.isConnected()) {
                this.socket.connect(new InetSocketAddress(this.dnsResolver.resolve(this.uri), getPort()), this.connectTimeout);
            }
            if (z) {
                SSLContext sSLContext2 = SSLContext.getInstance("TLSv1.2");
                sSLContext2.init(null, null, null);
                this.socket = sSLContext2.getSocketFactory().createSocket(this.socket, this.uri.getHost(), getPort(), true);
            }
            if (this.socket instanceof SSLSocket) {
                SSLSocket sSLSocket2 = (SSLSocket) this.socket;
                SSLParameters sSLParameters2 = sSLSocket2.getSSLParameters();
                onSetSSLParameters(sSLParameters2);
                sSLSocket2.setSSLParameters(sSLParameters2);
            }
            InputStream inputStream2 = this.socket.getInputStream();
            this.ostream = this.socket.getOutputStream();
            sendHandshake();
            Thread thread2 = new Thread(new WebsocketWriteThread(this));
            this.writeThread = thread2;
            thread2.start();
            bArr = new byte[16384];
            while (!isClosing()) {
                this.engine.decode(ByteBuffer.wrap(bArr, 0, i));
            }
            this.engine.eot();
            this.connectReadThread = null;
        } catch (Exception e3) {
            onWebsocketError(this.engine, e3);
            this.engine.closeConnection(-1, e3.getMessage());
        } catch (InternalError e4) {
            if ((e4.getCause() instanceof InvocationTargetException) && (e4.getCause().getCause() instanceof IOException)) {
                IOException iOException = (IOException) e4.getCause().getCause();
                onWebsocketError(this.engine, iOException);
                this.engine.closeConnection(-1, iOException.getMessage());
                return;
            }
            throw e4;
        }
    }

    protected void onSetSSLParameters(SSLParameters sSLParameters) {
        sSLParameters.setEndpointIdentificationAlgorithm("HTTPS");
    }

    private int getPort() {
        int port = this.uri.getPort();
        if (port != -1) {
            return port;
        }
        String scheme = this.uri.getScheme();
        if ("wss".equals(scheme)) {
            return 443;
        }
        if ("ws".equals(scheme)) {
            return 80;
        }
        throw new IllegalArgumentException("unknown scheme: " + scheme);
    }

    private void sendHandshake() throws InvalidHandshakeException {
        String rawPath = this.uri.getRawPath();
        String rawQuery = this.uri.getRawQuery();
        if (rawPath == null || rawPath.length() == 0) {
            rawPath = "/";
        }
        if (rawQuery != null) {
            rawPath = rawPath + '?' + rawQuery;
        }
        int port = getPort();
        StringBuilder sb = new StringBuilder();
        sb.append(this.uri.getHost());
        sb.append((port == 80 || port == 443) ? "" : ":" + port);
        String string = sb.toString();
        HandshakeImpl1Client handshakeImpl1Client = new HandshakeImpl1Client();
        handshakeImpl1Client.setResourceDescriptor(rawPath);
        handshakeImpl1Client.put("Host", string);
        Map<String, String> map = this.headers;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                handshakeImpl1Client.put(entry.getKey(), entry.getValue());
            }
        }
        this.engine.startHandshake(handshakeImpl1Client);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketMessage(WebSocket webSocket, String str) {
        onMessage(str);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        onMessage(byteBuffer);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketOpen(WebSocket webSocket, Handshakedata handshakedata) {
        startConnectionLostTimer();
        onOpen((ServerHandshake) handshakedata);
        this.connectLatch.countDown();
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketClose(WebSocket webSocket, int i, String str, boolean z) {
        stopConnectionLostTimer();
        Thread thread = this.writeThread;
        if (thread != null) {
            thread.interrupt();
        }
        onClose(i, str, z);
        this.connectLatch.countDown();
        this.closeLatch.countDown();
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketError(WebSocket webSocket, Exception exc) {
        onError(exc);
    }

    @Override // org.java_websocket.WebSocketListener
    public void onWebsocketCloseInitiated(WebSocket webSocket, int i, String str) {
        onCloseInitiated(i, str);
    }

    @Override // org.java_websocket.WebSocketListener
    public void onWebsocketClosing(WebSocket webSocket, int i, String str, boolean z) {
        onClosing(i, str, z);
    }

    private class WebsocketWriteThread implements Runnable {
        private final WebSocketClient webSocketClient;

        WebsocketWriteThread(WebSocketClient webSocketClient) {
            this.webSocketClient = webSocketClient;
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("WebSocketWriteThread-" + Thread.currentThread().getId());
            try {
                try {
                    runWriteData();
                } catch (IOException e) {
                    WebSocketClient.this.handleIOException(e);
                }
            } finally {
                closeSocket();
                WebSocketClient.this.writeThread = null;
            }
        }

        private void runWriteData() throws IOException {
            while (!Thread.interrupted()) {
                try {
                    ByteBuffer byteBufferTake = WebSocketClient.this.engine.outQueue.take();
                    WebSocketClient.this.ostream.write(byteBufferTake.array(), 0, byteBufferTake.limit());
                    WebSocketClient.this.ostream.flush();
                } catch (InterruptedException unused) {
                    for (ByteBuffer byteBuffer : WebSocketClient.this.engine.outQueue) {
                        WebSocketClient.this.ostream.write(byteBuffer.array(), 0, byteBuffer.limit());
                        WebSocketClient.this.ostream.flush();
                    }
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        private void closeSocket() {
            try {
                if (WebSocketClient.this.socket != null) {
                    WebSocketClient.this.socket.close();
                }
            } catch (IOException e) {
                WebSocketClient.this.onWebsocketError(this.webSocketClient, e);
            }
        }
    }

    public boolean isClosed() {
        return this.engine.isClosed();
    }

    public boolean isClosing() {
        return this.engine.isClosing();
    }

    @Override // org.java_websocket.WebSocket
    public void sendFrame(Framedata framedata) {
        this.engine.sendFrame(framedata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleIOException(IOException iOException) {
        if (iOException instanceof SSLException) {
            onError(iOException);
        }
        this.engine.eot();
    }
}
