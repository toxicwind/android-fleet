package com.regza.cesi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.CharsKt;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.protocols.Protocol;
import org.jdeferred2.Deferred;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: CesiConnection.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CesiConnection extends BaseCesiConnection {
    private WS _ws;
    private final Logger logger;
    private Deferred<JsonNode, Error, JsonNode> openPromise;

    public CesiConnection(@Nullable Settings settings) {
        super(settings);
        this.logger = Logger.getLogger("CesiConnection");
    }

    @Override // com.regza.cesi.BaseCesiConnection
    public Logger getLogger$cesi() {
        return this.logger;
    }

    @NotNull
    public final Map<String, String> getHeaders() {
        HashMap map = new HashMap();
        map.put("Origin", "app://com.regza");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: CesiConnection.kt */
    static final class WS extends WebSocketClient {

        @NotNull
        private final CesiConnection _connection;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WS(@NotNull URI uri, @NotNull CesiConnection connection, @NotNull Map<String, String> headers) {
            super(uri, new Draft_6455(Collections.emptyList(), Collections.singletonList(new Protocol("cesi"))), headers);
            Intrinsics.checkParameterIsNotNull(uri, "uri");
            Intrinsics.checkParameterIsNotNull(connection, "connection");
            Intrinsics.checkParameterIsNotNull(headers, "headers");
            this._connection = connection;
            setConnectionLostTimeout(0);
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onOpen(@Nullable ServerHandshake serverHandshake) {
            this._connection.onOpen(serverHandshake);
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onMessage(@Nullable String str) throws JsonProcessingException {
            this._connection.onMessage(str);
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onError(@Nullable Exception exc) {
            this._connection.onError(exc);
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onClose(int i, @Nullable String str, boolean z) {
            this._connection.onClose(i, str, z);
        }
    }

    public boolean isOpen() {
        return this._ws != null;
    }

    @NotNull
    public Deferred<JsonNode, Error, JsonNode> open(@NotNull final String host, final int i) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        logi$cesi("open: CESI library version " + getLIB_VERSION());
        _clear$cesi();
        this.openPromise = createDeferred();
        this._ws = new WS(new URI("ws://" + host + ":" + i), this, getHeaders());
        StringBuilder sb = new StringBuilder();
        sb.append("open: connectionLostTimeout ");
        WS ws = this._ws;
        if (ws == null) {
            Intrinsics.throwNpe();
        }
        sb.append(String.valueOf(ws.getConnectionLostTimeout()));
        logv$cesi(sb.toString());
        new Thread(new Runnable() { // from class: com.regza.cesi.CesiConnection$open$t$1
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                JsonNode constants;
                try {
                    Settings settings = this.this$0.get_settings$cesi();
                    if ((settings != null ? settings.getConstants() : null) == null) {
                        this.this$0._GetConstants(host, i);
                    } else {
                        CesiConnection cesiConnection = this.this$0;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("constants from settings: ");
                        Settings settings2 = this.this$0.get_settings$cesi();
                        sb2.append((settings2 == null || (constants = settings2.getConstants()) == null) ? null : constants.toString());
                        cesiConnection.logv$cesi(sb2.toString());
                        CesiConnection cesiConnection2 = this.this$0;
                        Settings settings3 = this.this$0.get_settings$cesi();
                        JsonNode constants2 = settings3 != null ? settings3.getConstants() : null;
                        if (constants2 == null) {
                            Intrinsics.throwNpe();
                        }
                        cesiConnection2._StoreConstants$cesi(constants2);
                    }
                    CesiConnection.WS ws2 = this.this$0._ws;
                    if (ws2 == null) {
                        Intrinsics.throwNpe();
                    }
                    ws2.connect();
                } catch (Exception e) {
                    this.this$0.getLogger$cesi().warning("failed to connect " + e.toString());
                    Deferred deferred = this.this$0.openPromise;
                    if (deferred == null) {
                        Intrinsics.throwNpe();
                    }
                    deferred.reject(new Error(0L, 0L, Long.valueOf(BaseCesiConnection.CoreStatus.INITIALIZATION_ERROR.getValue()), 0L));
                }
            }
        }).start();
        Deferred<JsonNode, Error, JsonNode> deferred = this.openPromise;
        if (deferred == null) {
            Intrinsics.throwNpe();
        }
        return deferred;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:66:0x0163  */
    /* JADX WARN: Code duplicated, block: B:76:0x0154 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:82:0x015e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x0159 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:98:? A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17, types: [java.io.InputStreamReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    public final void _GetConstants(String str, int i) throws Throwable {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        ?? inputStreamReader;
        ?? bufferedReader;
        ?? r2;
        logv$cesi("_GetConstants");
        StringBuffer stringBuffer = new StringBuffer("");
        HttpURLConnection httpURLConnection2 = null;
         = 0;
         = 0;
        ?? r1 = 0;
        ?? r3 = 0;
        try {
            URL url = new URL("http://" + str + ":" + i + "/CESIConstants.js");
            StringBuilder sb = new StringBuilder();
            sb.append("_GetConstants: try to connect ");
            sb.append(url.toString());
            logv$cesi(sb.toString());
            URLConnection uRLConnectionOpenConnection = url.openConnection();
            if (uRLConnectionOpenConnection == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.net.HttpURLConnection");
            }
            httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
            try {
                httpURLConnection.setConnectTimeout(30000);
                httpURLConnection.setReadTimeout(30000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                logv$cesi("_GetConstants: responseCode " + String.valueOf(responseCode));
                if (responseCode == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader);
                            while (true) {
                                try {
                                    String line = bufferedReader.readLine();
                                    if (line == null) {
                                        break;
                                    } else {
                                        stringBuffer.append(line);
                                    }
                                } catch (IOException e) {
                                    e = e;
                                    httpURLConnection2 = httpURLConnection;
                                    inputStreamReader = inputStreamReader;
                                    bufferedReader = bufferedReader;
                                    try {
                                        logv$cesi("_GetConstants error" + e.toString());
                                        throw e;
                                    } catch (Throwable th) {
                                        th = th;
                                        httpURLConnection = httpURLConnection2;
                                        r3 = bufferedReader;
                                        if (r3 != 0) {
                                            try {
                                                r3.close();
                                            } catch (IOException unused) {
                                            }
                                        }
                                        if (inputStreamReader != 0) {
                                            try {
                                                inputStreamReader.close();
                                            } catch (IOException unused2) {
                                            }
                                        }
                                        if (inputStream != null) {
                                            try {
                                                inputStream.close();
                                            } catch (IOException unused3) {
                                            }
                                        }
                                        if (httpURLConnection != null) {
                                            throw th;
                                        }
                                        httpURLConnection.disconnect();
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    r3 = bufferedReader;
                                    if (r3 != 0) {
                                        r3.close();
                                    }
                                    if (inputStreamReader != 0) {
                                        inputStreamReader.close();
                                    }
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    if (httpURLConnection != null) {
                                        throw th;
                                    }
                                    httpURLConnection.disconnect();
                                    throw th;
                                }
                            }
                            r1 = bufferedReader;
                            r2 = inputStreamReader;
                        } catch (IOException e2) {
                            e = e2;
                            bufferedReader = 0;
                        } catch (Throwable th3) {
                            th = th3;
                            if (r3 != 0) {
                                r3.close();
                            }
                            if (inputStreamReader != 0) {
                                inputStreamReader.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (httpURLConnection != null) {
                                throw th;
                            }
                            httpURLConnection.disconnect();
                            throw th;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        inputStreamReader = 0;
                        bufferedReader = inputStreamReader;
                        httpURLConnection2 = httpURLConnection;
                        inputStreamReader = inputStreamReader;
                        bufferedReader = bufferedReader;
                        logv$cesi("_GetConstants error" + e.toString());
                        throw e;
                    } catch (Throwable th4) {
                        th = th4;
                        inputStreamReader = 0;
                    }
                } else {
                    logv$cesi("_GetConstants invalid response " + String.valueOf(responseCode));
                    inputStream = null;
                    r2 = 0;
                }
                if (r1 != 0) {
                    try {
                        r1.close();
                    } catch (IOException unused4) {
                    }
                }
                if (r2 != 0) {
                    try {
                        r2.close();
                    } catch (IOException unused5) {
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused6) {
                    }
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                String string = stringBuffer.toString();
                Intrinsics.checkExpressionValueIsNotNull(string, "sb.toString()");
                JsonNode tree = new ObjectMapper().readTree(string);
                Intrinsics.checkExpressionValueIsNotNull(tree, "mapper.readTree(response)");
                logv$cesi("constants" + tree.toString());
                _StoreConstants$cesi(tree);
            } catch (IOException e4) {
                e = e4;
                inputStream = null;
                inputStreamReader = 0;
            } catch (Throwable th5) {
                th = th5;
                inputStream = null;
                inputStreamReader = inputStream;
                if (r3 != 0) {
                    r3.close();
                }
                if (inputStreamReader != 0) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (httpURLConnection != null) {
                    throw th;
                }
                httpURLConnection.disconnect();
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            inputStream = null;
            inputStreamReader = 0;
            bufferedReader = 0;
        } catch (Throwable th6) {
            th = th6;
            httpURLConnection = null;
            inputStream = null;
        }
    }

    private final void onNotify(String str, BaseCesiConnection.Header header) throws JsonProcessingException {
        get_subscription$cesi();
        if (header.getCategory() == BaseCesiConnection.category.INSTANCE.getERROR()) {
            logv$cesi("notify ERROR");
            if (str != null) {
                String strSubstring = str.substring(16, 24);
                Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                CharsKt.checkRadix(16);
                Long.parseLong(strSubstring, 16);
                if (str != null) {
                    String strSubstring2 = str.substring(24, 32);
                    Intrinsics.checkExpressionValueIsNotNull(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    CharsKt.checkRadix(16);
                    long j = Long.parseLong(strSubstring2, 16);
                    Long messageType = header.getMessageType();
                    if (messageType == null) {
                        Intrinsics.throwNpe();
                    }
                    _notifyError$cesi(messageType.longValue(), j, header.getMessageID());
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        if (header.getCategory() == BaseCesiConnection.category.INSTANCE.getMESSAGE()) {
            logv$cesi("notify MESSAGE");
            ObjectMapper objectMapper = new ObjectMapper();
            if (str != null) {
                String strSubstring3 = str.substring(16);
                Intrinsics.checkExpressionValueIsNotNull(strSubstring3, "(this as java.lang.String).substring(startIndex)");
                JsonNode tree = objectMapper.readTree(strSubstring3);
                Intrinsics.checkExpressionValueIsNotNull(tree, "mapper.readTree(message.substring(16))");
                Long messageType2 = header.getMessageType();
                if (messageType2 == null) {
                    Intrinsics.throwNpe();
                }
                _notify$cesi(messageType2.longValue(), tree);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        getLogger$cesi().warning("Invalid category: " + header.getCategory());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onMessage(String str) throws JsonProcessingException {
        logv$cesi("onMessage: data: " + str);
        if (str == null) {
            logv$cesi("onMessage: message is empty");
            return;
        }
        BaseCesiConnection.Header header = readHeader(str);
        if (header == null) {
            return;
        }
        logv$cesi("onMessage: header: " + header.toString());
        long category = header.getCategory();
        if (category == BaseCesiConnection.category.INSTANCE.getPING()) {
            try {
                logv$cesi("onMessage: PING");
                WS ws = this._ws;
                if (ws == null) {
                    Intrinsics.throwNpe();
                }
                ws.send(makeHeaderPong());
                return;
            } catch (Exception unused) {
                getLogger$cesi().warning("There is an error sending PONG.");
                return;
            }
        }
        Long messageID = header.getMessageID();
        if (messageID == null) {
            Intrinsics.throwNpe();
        }
        long jLongValue = messageID.longValue();
        if (jLongValue == getNOTIFICATION_MESSAGEID$cesi()) {
            onNotify(str, header);
            return;
        }
        Deferred<JsonNode, Error, JsonNode> deferred = get_promises$cesi().get(Long.valueOf(jLongValue));
        if (deferred == null) {
            return;
        }
        get_promises$cesi().remove(Long.valueOf(jLongValue));
        set_numProcessing$cesi(get_numProcessing$cesi() - 1);
        if (category == BaseCesiConnection.category.INSTANCE.getERROR()) {
            logv$cesi("onMessage: category: ERROR");
            String strSubstring = str.substring(16, 24);
            Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            CharsKt.checkRadix(16);
            long j = Long.parseLong(strSubstring, 16);
            String strSubstring2 = str.substring(24, 32);
            Intrinsics.checkExpressionValueIsNotNull(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            CharsKt.checkRadix(16);
            deferred.reject(new Error(Long.valueOf(jLongValue), header.getMessageType(), Long.valueOf(j), Long.valueOf(Long.parseLong(strSubstring2, 16))));
        } else if (category == BaseCesiConnection.category.INSTANCE.getMESSAGE()) {
            try {
                logv$cesi("onMessage: category: MESSAGE");
                ObjectMapper objectMapper = new ObjectMapper();
                String strSubstring3 = str.substring(16);
                Intrinsics.checkExpressionValueIsNotNull(strSubstring3, "(this as java.lang.String).substring(startIndex)");
                JsonNode tree = objectMapper.readTree(strSubstring3);
                Intrinsics.checkExpressionValueIsNotNull(tree, "mapper.readTree(message.substring(16))");
                deferred.resolve(tree);
            } catch (Exception unused2) {
                loge$cesi("onMessage: parse error");
                deferred.reject(new Error(Long.valueOf(jLongValue), header.getMessageType(), getStatus().get("USER_EXCEPTION"), 0L));
            }
        } else if (category == BaseCesiConnection.category.INSTANCE.getINITIALIZE()) {
            try {
                logv$cesi("onMessage: category: INITIALIZE");
                deferred.resolve(createJson());
                Deferred<JsonNode, Error, JsonNode> deferred2 = this.openPromise;
                if (deferred2 == null) {
                    Intrinsics.throwNpe();
                }
                deferred2.resolve(createJson());
            } catch (Exception unused3) {
                loge$cesi("onMessage: parse error");
                deferred.reject(new Error(Long.valueOf(jLongValue), header.getMessageType(), getStatus().get("USER_EXCEPTION"), 0L));
            }
        } else if (category == BaseCesiConnection.category.INSTANCE.getSUBSCRIBE()) {
            try {
                logv$cesi("onMessage: category: SUBSCRIBE");
                deferred.resolve(createJson());
            } catch (Exception unused4) {
                loge$cesi("onMessage: parse error");
                deferred.reject(new Error(Long.valueOf(jLongValue), header.getMessageType(), getStatus().get("USER_EXCEPTION"), 0L));
            }
        } else {
            getLogger$cesi().warning("onMessage: Unknown Category Error: header.category = " + category);
            deferred.reject(new Error(Long.valueOf(jLongValue), header.getMessageType(), getStatus().get("UNSPECIFIED"), 0L));
        }
        popAndSendDelayedMessage$cesi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onError(Exception exc) {
        loge$cesi("onError: " + String.valueOf(exc));
        clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onOpen(ServerHandshake serverHandshake) {
        logv$cesi("onOpen");
        long j_issueMessageID$cesi = _issueMessageID$cesi();
        _send$cesi(j_issueMessageID$cesi, makeHeaderInitialize(BaseCesiConnection.category.INSTANCE.getINITIALIZE(), getVersion(), j_issueMessageID$cesi), "", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClose(int i, String str, boolean z) {
        if (str != null) {
            loge$cesi("onClose code " + i + " reason " + str + " remote " + z);
        } else {
            loge$cesi("onClose code " + i + " remote " + z);
        }
        clear();
    }

    @Override // com.regza.cesi.BaseCesiConnection
    public void sendToWebSocket(@NotNull String header, @NotNull String body) {
        Deferred<JsonNode, Error, JsonNode> deferred;
        Intrinsics.checkParameterIsNotNull(header, "header");
        Intrinsics.checkParameterIsNotNull(body, "body");
        try {
            WS ws = this._ws;
            if (ws == null) {
                Intrinsics.throwNpe();
            }
            ws.send(header + body);
        } catch (Exception e) {
            BaseCesiConnection.Header header2 = readHeader(header);
            StringBuilder sb = new StringBuilder();
            sb.append("sendToWebSocket: exception ");
            sb.append(e.toString());
            sb.append(" messageId ");
            sb.append(header2 != null ? header2.getMessageID() : null);
            loge$cesi(sb.toString());
            if (header2 == null || (deferred = get_promises$cesi().get(header2.getMessageID())) == null) {
                return;
            }
            deferred.reject(new Error(header2.getMessageID(), header2.getMessageType(), Long.valueOf(BaseCesiConnection.CoreStatus.WEBSOCKET_CONNECTION_ERROR.getValue()), 0L));
            Map<Long, Deferred<JsonNode, Error, JsonNode>> map = get_promises$cesi();
            Long messageID = header2.getMessageID();
            if (map == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
            }
            TypeIntrinsics.asMutableMap(map).remove(messageID);
            set_numProcessing$cesi(get_numProcessing$cesi() - 1);
        }
    }

    private final String makeHeaderInitialize(long j, long j2, long j3) {
        return toHexString(j, 4) + toHexString(j2, 4) + toHexString(j3, 8);
    }

    private final String makeHeaderMessage(long j, long j2, long j3) {
        return toHexString(j, 4) + toHexString(j2, 4) + toHexString(j3, 8);
    }

    private final String makeHeaderPong() {
        return toHexString(BaseCesiConnection.category.INSTANCE.getPONG(), 4);
    }

    private final String toHexString(long j, int i) {
        int i2 = 1;
        List listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Character[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'});
        String str = "";
        if (1 <= i) {
            while (true) {
                str = String.valueOf(((Character) listListOf.get((int) (j % ((long) 16)))).charValue()) + str;
                j = (long) Math.floor(j / ((double) 16));
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        return str;
    }

    @NotNull
    public Deferred<JsonNode, Error, JsonNode> sendMessage(@NotNull String message, @NotNull JsonNode param) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(param, "param");
        Long l = getCommands().get(message);
        long j_issueMessageID$cesi = _issueMessageID$cesi();
        if (l == null) {
            logv$cesi("sendMessage " + message + " not found");
            Deferred<JsonNode, Error, JsonNode> deferredReject = createDeferred().reject(new Error(Long.valueOf(j_issueMessageID$cesi), l, Long.valueOf(BaseCesiConnection.CoreStatus.UNSUPPORTED_OPERATION.getValue()), 0L));
            Intrinsics.checkExpressionValueIsNotNull(deferredReject, "createDeferred().reject(…RTED_OPERATION.value, 0))");
            return deferredReject;
        }
        if (this._ws == null) {
            loge$cesi("the connection is not established");
            Deferred<JsonNode, Error, JsonNode> deferredReject2 = createDeferred().reject(new Error(Long.valueOf(j_issueMessageID$cesi), l, Long.valueOf(BaseCesiConnection.CoreStatus.WEBSOCKET_CONNECTION_ERROR.getValue()), 0L));
            Intrinsics.checkExpressionValueIsNotNull(deferredReject2, "createDeferred().reject(…NNECTION_ERROR.value, 0))");
            return deferredReject2;
        }
        String string = param.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "param.toString()");
        logv$cesi("sendMessage " + message + " " + string);
        return BaseCesiConnection._send$cesi$default(this, j_issueMessageID$cesi, makeHeaderMessage(BaseCesiConnection.category.INSTANCE.getMESSAGE(), l.longValue(), j_issueMessageID$cesi), string, false, 8, null);
    }

    private final BaseCesiConnection.Header readHeader(String str) {
        BaseCesiConnection.Header header;
        if (str != null) {
            String strSubstring = str.substring(0, 4);
            Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            CharsKt.checkRadix(16);
            long j = Long.parseLong(strSubstring, 16);
            if (j == BaseCesiConnection.category.INSTANCE.getINITIALIZE()) {
                if (str == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring2 = str.substring(8, 16);
                Intrinsics.checkExpressionValueIsNotNull(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                CharsKt.checkRadix(16);
                return new BaseCesiConnection.Header(j, Long.valueOf(Long.parseLong(strSubstring2, 16)), null, 4, null);
            }
            if (j == BaseCesiConnection.category.INSTANCE.getMESSAGE()) {
                if (str == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring3 = str.substring(8, 16);
                Intrinsics.checkExpressionValueIsNotNull(strSubstring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                CharsKt.checkRadix(16);
                Long lValueOf = Long.valueOf(Long.parseLong(strSubstring3, 16));
                if (str == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring4 = str.substring(4, 8);
                Intrinsics.checkExpressionValueIsNotNull(strSubstring4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                CharsKt.checkRadix(16);
                header = new BaseCesiConnection.Header(j, lValueOf, Long.valueOf(Long.parseLong(strSubstring4, 16)));
            } else {
                if (j != BaseCesiConnection.category.INSTANCE.getERROR()) {
                    if (j == BaseCesiConnection.category.INSTANCE.getSUBSCRIBE()) {
                        if (str == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        String strSubstring5 = str.substring(4, 12);
                        Intrinsics.checkExpressionValueIsNotNull(strSubstring5, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        CharsKt.checkRadix(16);
                        return new BaseCesiConnection.Header(j, Long.valueOf(Long.parseLong(strSubstring5, 16)), null, 4, null);
                    }
                    if (j != BaseCesiConnection.category.INSTANCE.getUNSUBSCRIBE()) {
                        if (j == BaseCesiConnection.category.INSTANCE.getPING()) {
                            return new BaseCesiConnection.Header(j, null, null, 6, null);
                        }
                        return null;
                    }
                    if (str == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring6 = str.substring(4, 12);
                    Intrinsics.checkExpressionValueIsNotNull(strSubstring6, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    CharsKt.checkRadix(16);
                    return new BaseCesiConnection.Header(j, Long.valueOf(Long.parseLong(strSubstring6, 16)), null, 4, null);
                }
                if (str == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring7 = str.substring(8, 16);
                Intrinsics.checkExpressionValueIsNotNull(strSubstring7, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                CharsKt.checkRadix(16);
                Long lValueOf2 = Long.valueOf(Long.parseLong(strSubstring7, 16));
                if (str == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring8 = str.substring(4, 8);
                Intrinsics.checkExpressionValueIsNotNull(strSubstring8, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                CharsKt.checkRadix(16);
                header = new BaseCesiConnection.Header(j, lValueOf2, Long.valueOf(Long.parseLong(strSubstring8, 16)));
            }
            return header;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private final void clear() {
        Deferred<JsonNode, Error, JsonNode> deferred = this.openPromise;
        if (deferred != null) {
            if (deferred == null) {
                Intrinsics.throwNpe();
            }
            if (deferred.isPending()) {
                try {
                    Deferred<JsonNode, Error, JsonNode> deferred2 = this.openPromise;
                    if (deferred2 == null) {
                        Intrinsics.throwNpe();
                    }
                    deferred2.reject(new Error(0L, 0L, Long.valueOf(BaseCesiConnection.CoreStatus.WEBSOCKET_CONNECTION_ERROR.getValue()), 0L));
                } catch (Exception unused) {
                }
            }
        }
        _rejectPromises$cesi();
        _clear$cesi();
    }

    @Override // com.regza.cesi.BaseCesiConnection
    public void _clear$cesi() {
        super._clear$cesi();
        this._ws = null;
    }
}
