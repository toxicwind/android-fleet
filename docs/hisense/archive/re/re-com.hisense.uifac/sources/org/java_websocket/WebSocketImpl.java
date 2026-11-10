package org.java_websocket;

import com.mediatek.twoworlds.tv.MtkTvScanCeBase;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.java_websocket.drafts.Draft;
import org.java_websocket.enums.CloseHandshakeType;
import org.java_websocket.enums.HandshakeState;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.enums.Role;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.LimitExceededException;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.PingFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.util.Charsetfunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class WebSocketImpl implements WebSocket {
    private ByteChannel channel;
    private Draft draft;
    private SelectionKey key;
    private List<Draft> knownDrafts;
    public final BlockingQueue<ByteBuffer> outQueue;
    private Role role;
    private final WebSocketListener wsl;
    private final Logger log = LoggerFactory.getLogger((Class<?>) WebSocketImpl.class);
    private boolean flushandclosestate = false;
    private volatile ReadyState readyState = ReadyState.NOT_YET_CONNECTED;
    private ByteBuffer tmpHandshakeBytes = ByteBuffer.allocate(0);
    private ClientHandshake handshakerequest = null;
    private String closemessage = null;
    private Integer closecode = null;
    private Boolean closedremotely = null;
    private String resourceDescriptor = null;
    private long lastPong = System.nanoTime();
    private final Object synchronizeWriteObject = new Object();

    public WebSocketImpl(WebSocketListener webSocketListener, Draft draft) {
        this.draft = null;
        if (webSocketListener == null || (draft == null && this.role == Role.SERVER)) {
            throw new IllegalArgumentException("parameters must not be null");
        }
        this.outQueue = new LinkedBlockingQueue();
        new LinkedBlockingQueue();
        this.wsl = webSocketListener;
        this.role = Role.CLIENT;
        if (draft != null) {
            this.draft = draft.copyInstance();
        }
    }

    public void decode(ByteBuffer byteBuffer) {
        this.log.trace("process({}): ({})", Integer.valueOf(byteBuffer.remaining()), byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining()));
        if (this.readyState != ReadyState.NOT_YET_CONNECTED) {
            if (this.readyState == ReadyState.OPEN) {
                decodeFrames(byteBuffer);
            }
        } else {
            if (!decodeHandshake(byteBuffer) || isClosing() || isClosed()) {
                return;
            }
            if (byteBuffer.hasRemaining()) {
                decodeFrames(byteBuffer);
            } else if (this.tmpHandshakeBytes.hasRemaining()) {
                decodeFrames(this.tmpHandshakeBytes);
            }
        }
    }

    private boolean decodeHandshake(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2;
        if (this.tmpHandshakeBytes.capacity() == 0) {
            byteBuffer2 = byteBuffer;
        } else {
            if (this.tmpHandshakeBytes.remaining() < byteBuffer.remaining()) {
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(this.tmpHandshakeBytes.capacity() + byteBuffer.remaining());
                this.tmpHandshakeBytes.flip();
                byteBufferAllocate.put(this.tmpHandshakeBytes);
                this.tmpHandshakeBytes = byteBufferAllocate;
            }
            this.tmpHandshakeBytes.put(byteBuffer);
            this.tmpHandshakeBytes.flip();
            byteBuffer2 = this.tmpHandshakeBytes;
        }
        byteBuffer2.mark();
        try {
            try {
                if (this.role == Role.SERVER) {
                    if (this.draft == null) {
                        Iterator<Draft> it = this.knownDrafts.iterator();
                        while (it.hasNext()) {
                            Draft draftCopyInstance = it.next().copyInstance();
                            try {
                                draftCopyInstance.setParseMode(this.role);
                                byteBuffer2.reset();
                                Handshakedata handshakedataTranslateHandshake = draftCopyInstance.translateHandshake(byteBuffer2);
                                if (!(handshakedataTranslateHandshake instanceof ClientHandshake)) {
                                    this.log.trace("Closing due to wrong handshake");
                                    closeConnectionDueToWrongHandshake(new InvalidDataException(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG, "wrong http function"));
                                    return false;
                                }
                                ClientHandshake clientHandshake = (ClientHandshake) handshakedataTranslateHandshake;
                                if (draftCopyInstance.acceptHandshakeAsServer(clientHandshake) == HandshakeState.MATCHED) {
                                    this.resourceDescriptor = clientHandshake.getResourceDescriptor();
                                    try {
                                        ServerHandshakeBuilder serverHandshakeBuilderOnWebsocketHandshakeReceivedAsServer = this.wsl.onWebsocketHandshakeReceivedAsServer(this, draftCopyInstance, clientHandshake);
                                        draftCopyInstance.postProcessHandshakeResponseAsServer(clientHandshake, serverHandshakeBuilderOnWebsocketHandshakeReceivedAsServer);
                                        write(draftCopyInstance.createHandshake(serverHandshakeBuilderOnWebsocketHandshakeReceivedAsServer));
                                        this.draft = draftCopyInstance;
                                        open(clientHandshake);
                                        return true;
                                    } catch (RuntimeException e) {
                                        this.log.error("Closing due to internal server error", e);
                                        this.wsl.onWebsocketError(this, e);
                                        closeConnectionDueToInternalServerError(e);
                                        return false;
                                    } catch (InvalidDataException e2) {
                                        this.log.trace("Closing due to wrong handshake. Possible handshake rejection", (Throwable) e2);
                                        closeConnectionDueToWrongHandshake(e2);
                                        return false;
                                    }
                                }
                            } catch (InvalidHandshakeException unused) {
                            }
                        }
                        if (this.draft == null) {
                            this.log.trace("Closing due to protocol error: no draft matches");
                            closeConnectionDueToWrongHandshake(new InvalidDataException(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG, "no draft matches"));
                        }
                        return false;
                    }
                    Handshakedata handshakedataTranslateHandshake2 = this.draft.translateHandshake(byteBuffer2);
                    if (!(handshakedataTranslateHandshake2 instanceof ClientHandshake)) {
                        this.log.trace("Closing due to protocol error: wrong http function");
                        flushAndClose(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG, "wrong http function", false);
                        return false;
                    }
                    ClientHandshake clientHandshake2 = (ClientHandshake) handshakedataTranslateHandshake2;
                    if (this.draft.acceptHandshakeAsServer(clientHandshake2) == HandshakeState.MATCHED) {
                        open(clientHandshake2);
                        return true;
                    }
                    this.log.trace("Closing due to protocol error: the handshake did finally not match");
                    close(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG, "the handshake did finally not match");
                    return false;
                }
                if (this.role == Role.CLIENT) {
                    this.draft.setParseMode(this.role);
                    Handshakedata handshakedataTranslateHandshake3 = this.draft.translateHandshake(byteBuffer2);
                    if (!(handshakedataTranslateHandshake3 instanceof ServerHandshake)) {
                        this.log.trace("Closing due to protocol error: wrong http function");
                        flushAndClose(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG, "wrong http function", false);
                        return false;
                    }
                    ServerHandshake serverHandshake = (ServerHandshake) handshakedataTranslateHandshake3;
                    if (this.draft.acceptHandshakeAsClient(this.handshakerequest, serverHandshake) == HandshakeState.MATCHED) {
                        try {
                            this.wsl.onWebsocketHandshakeReceivedAsClient(this, this.handshakerequest, serverHandshake);
                            open(serverHandshake);
                            return true;
                        } catch (RuntimeException e3) {
                            this.log.error("Closing since client was never connected", e3);
                            this.wsl.onWebsocketError(this, e3);
                            flushAndClose(-1, e3.getMessage(), false);
                            return false;
                        } catch (InvalidDataException e4) {
                            this.log.trace("Closing due to invalid data exception. Possible handshake rejection", (Throwable) e4);
                            flushAndClose(e4.getCloseCode(), e4.getMessage(), false);
                            return false;
                        }
                    }
                    this.log.trace("Closing due to protocol error: draft {} refuses handshake", this.draft);
                    close(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG, "draft " + this.draft + " refuses handshake");
                }
                return false;
            } catch (InvalidHandshakeException e5) {
                this.log.trace("Closing due to invalid handshake", (Throwable) e5);
                close(e5);
            }
        } catch (IncompleteHandshakeException e6) {
            if (this.tmpHandshakeBytes.capacity() == 0) {
                byteBuffer2.reset();
                int preferredSize = e6.getPreferredSize();
                if (preferredSize == 0) {
                    preferredSize = byteBuffer2.capacity() + 16;
                }
                ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(preferredSize);
                this.tmpHandshakeBytes = byteBufferAllocate2;
                byteBufferAllocate2.put(byteBuffer);
            } else {
                ByteBuffer byteBuffer3 = this.tmpHandshakeBytes;
                byteBuffer3.position(byteBuffer3.limit());
                ByteBuffer byteBuffer4 = this.tmpHandshakeBytes;
                byteBuffer4.limit(byteBuffer4.capacity());
            }
        }
    }

    private void decodeFrames(ByteBuffer byteBuffer) {
        try {
            for (Framedata framedata : this.draft.translateFrame(byteBuffer)) {
                this.log.trace("matched frame: {}", framedata);
                this.draft.processFrame(this, framedata);
            }
        } catch (LimitExceededException e) {
            if (e.getLimit() == Integer.MAX_VALUE) {
                this.log.error("Closing due to invalid size of frame", e);
                this.wsl.onWebsocketError(this, e);
            }
            close(e);
        } catch (InvalidDataException e2) {
            this.log.error("Closing due to invalid data in frame", e2);
            this.wsl.onWebsocketError(this, e2);
            close(e2);
        }
    }

    private void closeConnectionDueToWrongHandshake(InvalidDataException invalidDataException) {
        write(generateHttpResponseDueToError(404));
        flushAndClose(invalidDataException.getCloseCode(), invalidDataException.getMessage(), false);
    }

    private void closeConnectionDueToInternalServerError(RuntimeException runtimeException) {
        write(generateHttpResponseDueToError(500));
        flushAndClose(-1, runtimeException.getMessage(), false);
    }

    private ByteBuffer generateHttpResponseDueToError(int i) {
        String str = i != 404 ? "500 Internal Server Error" : "404 WebSocket Upgrade Failure";
        return ByteBuffer.wrap(Charsetfunctions.asciiBytes("HTTP/1.1 " + str + "\r\nContent-Type: text/html\nServer: TooTallNate Java-WebSocket\r\nContent-Length: " + (str.length() + 48) + "\r\n\r\n<html><head></head><body><h1>" + str + "</h1></body></html>"));
    }

    public synchronized void close(int i, String str, boolean z) {
        if (this.readyState == ReadyState.CLOSING || this.readyState == ReadyState.CLOSED) {
            return;
        }
        if (this.readyState == ReadyState.OPEN) {
            if (i == 1006) {
                this.readyState = ReadyState.CLOSING;
                flushAndClose(i, str, false);
                return;
            }
            this.draft.getCloseHandshakeType();
            CloseHandshakeType closeHandshakeType = CloseHandshakeType.NONE;
            try {
                if (!z) {
                    try {
                        this.wsl.onWebsocketCloseInitiated(this, i, str);
                    } catch (RuntimeException e) {
                        this.wsl.onWebsocketError(this, e);
                    }
                }
                if (isOpen()) {
                    CloseFrame closeFrame = new CloseFrame();
                    closeFrame.setReason(str);
                    closeFrame.setCode(i);
                    closeFrame.isValid();
                    sendFrame(closeFrame);
                }
            } catch (InvalidDataException e2) {
                this.log.error("generated frame is invalid", e2);
                this.wsl.onWebsocketError(this, e2);
                flushAndClose(MtkTvScanCeBase.OPERATOR_WUHAN_SHIWANG, "generated frame is invalid", false);
            }
            flushAndClose(i, str, z);
        } else if (i == -3) {
            flushAndClose(-3, str, true);
        } else if (i == 1002) {
            flushAndClose(i, str, z);
        } else {
            flushAndClose(-1, str, false);
        }
        this.readyState = ReadyState.CLOSING;
        this.tmpHandshakeBytes = null;
    }

    public void close(int i, String str) {
        close(i, str, false);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0061 A[Catch: all -> 0x006f, TryCatch #2 {, blocks: (B:3:0x0001, B:7:0x0009, B:11:0x0013, B:12:0x0017, B:14:0x001b, B:15:0x0020, B:17:0x0024, B:26:0x0051, B:30:0x005d, B:32:0x0061, B:33:0x0066, B:29:0x0058, B:20:0x002b, B:22:0x0031, B:24:0x003d, B:25:0x0045), top: B:43:0x0001, inners: #0, #1 }] */
    public synchronized void closeConnection(int i, String str, boolean z) {
        if (this.readyState == ReadyState.CLOSED) {
            return;
        }
        if (this.readyState == ReadyState.OPEN && i == 1006) {
            this.readyState = ReadyState.CLOSING;
        }
        if (this.key != null) {
            this.key.cancel();
        }
        if (this.channel != null) {
            try {
                this.channel.close();
            } catch (IOException e) {
                if (e.getMessage() != null && e.getMessage().equals("Broken pipe")) {
                    this.log.trace("Caught IOException: Broken pipe during closeConnection()", (Throwable) e);
                } else {
                    this.log.error("Exception during channel.close()", e);
                    this.wsl.onWebsocketError(this, e);
                }
            }
            try {
                this.wsl.onWebsocketClose(this, i, str, z);
            } catch (RuntimeException e2) {
                this.wsl.onWebsocketError(this, e2);
            }
            if (this.draft != null) {
                this.draft.reset();
            }
            this.handshakerequest = null;
            this.readyState = ReadyState.CLOSED;
            return;
        }
        this.wsl.onWebsocketClose(this, i, str, z);
        if (this.draft != null) {
            this.draft.reset();
        }
        this.handshakerequest = null;
        this.readyState = ReadyState.CLOSED;
        return;
        throw th;
    }

    protected void closeConnection(int i, boolean z) {
        closeConnection(i, "", z);
    }

    public void closeConnection(int i, String str) {
        closeConnection(i, str, false);
    }

    public synchronized void flushAndClose(int i, String str, boolean z) {
        if (this.flushandclosestate) {
            return;
        }
        this.closecode = Integer.valueOf(i);
        this.closemessage = str;
        this.closedremotely = Boolean.valueOf(z);
        this.flushandclosestate = true;
        this.wsl.onWriteDemand(this);
        try {
            this.wsl.onWebsocketClosing(this, i, str, z);
        } catch (RuntimeException e) {
            this.log.error("Exception in onWebsocketClosing", e);
            this.wsl.onWebsocketError(this, e);
        }
        if (this.draft != null) {
            this.draft.reset();
        }
        this.handshakerequest = null;
    }

    public void eot() {
        if (this.readyState == ReadyState.NOT_YET_CONNECTED) {
            closeConnection(-1, true);
            return;
        }
        if (this.flushandclosestate) {
            closeConnection(this.closecode.intValue(), this.closemessage, this.closedremotely.booleanValue());
            return;
        }
        this.draft.getCloseHandshakeType();
        CloseHandshakeType closeHandshakeType = CloseHandshakeType.NONE;
        this.draft.getCloseHandshakeType();
        CloseHandshakeType closeHandshakeType2 = CloseHandshakeType.ONEWAY;
        closeConnection(MtkTvScanCeBase.OPERATOR_WUHAN_SHIWANG, true);
    }

    public void close(InvalidDataException invalidDataException) {
        close(invalidDataException.getCloseCode(), invalidDataException.getMessage(), false);
    }

    public void send(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        send(this.draft.createFrames(str, this.role == Role.CLIENT));
    }

    private void send(Collection<Framedata> collection) {
        if (!isOpen()) {
            throw new WebsocketNotConnectedException();
        }
        if (collection == null) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList();
        for (Framedata framedata : collection) {
            this.log.trace("send frame: {}", framedata);
            arrayList.add(this.draft.createBinaryFrame(framedata));
        }
        write(arrayList);
    }

    @Override // org.java_websocket.WebSocket
    public void sendFrame(Framedata framedata) {
        send(Collections.singletonList(framedata));
    }

    public void sendPing() throws NullPointerException {
        PingFrame pingFrameOnPreparePing = this.wsl.onPreparePing(this);
        if (pingFrameOnPreparePing == null) {
            throw new NullPointerException("onPreparePing(WebSocket) returned null. PingFrame to sent can't be null.");
        }
        sendFrame(pingFrameOnPreparePing);
    }

    public void startHandshake(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException {
        this.draft.postProcessHandshakeRequestAsClient(clientHandshakeBuilder);
        this.handshakerequest = clientHandshakeBuilder;
        this.resourceDescriptor = clientHandshakeBuilder.getResourceDescriptor();
        try {
            this.wsl.onWebsocketHandshakeSentAsClient(this, this.handshakerequest);
            write(this.draft.createHandshake(this.handshakerequest));
        } catch (RuntimeException e) {
            this.log.error("Exception in startHandshake", e);
            this.wsl.onWebsocketError(this, e);
            throw new InvalidHandshakeException("rejected because of " + e);
        } catch (InvalidDataException unused) {
            throw new InvalidHandshakeException("Handshake data rejected by client.");
        }
    }

    private void write(ByteBuffer byteBuffer) {
        this.log.trace("write({}): {}", Integer.valueOf(byteBuffer.remaining()), byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array()));
        this.outQueue.add(byteBuffer);
        this.wsl.onWriteDemand(this);
    }

    private void write(List<ByteBuffer> list) {
        synchronized (this.synchronizeWriteObject) {
            Iterator<ByteBuffer> it = list.iterator();
            while (it.hasNext()) {
                write(it.next());
            }
        }
    }

    private void open(Handshakedata handshakedata) {
        this.log.trace("open using draft: {}", this.draft);
        this.readyState = ReadyState.OPEN;
        try {
            this.wsl.onWebsocketOpen(this, handshakedata);
        } catch (RuntimeException e) {
            this.wsl.onWebsocketError(this, e);
        }
    }

    public boolean isOpen() {
        return this.readyState == ReadyState.OPEN;
    }

    public boolean isClosing() {
        return this.readyState == ReadyState.CLOSING;
    }

    public boolean isClosed() {
        return this.readyState == ReadyState.CLOSED;
    }

    public ReadyState getReadyState() {
        return this.readyState;
    }

    public String toString() {
        return super.toString();
    }

    long getLastPong() {
        return this.lastPong;
    }

    public void updateLastPong() {
        this.lastPong = System.nanoTime();
    }

    public WebSocketListener getWebSocketListener() {
        return this.wsl;
    }
}
