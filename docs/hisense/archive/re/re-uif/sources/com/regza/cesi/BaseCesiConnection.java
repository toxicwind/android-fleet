package com.regza.cesi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jdeferred2.Deferred;
import org.jdeferred2.impl.DeferredObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: CesiConnection.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BaseCesiConnection {

    @NotNull
    private String LIB_VERSION;

    @NotNull
    private List<DelayedMessage> _delayedMessages;
    private long _messageID;
    private long _numProcessing;

    @Nullable
    private final Settings _settings;

    @Nullable
    private JsonNode constants;
    private final long version;

    @NotNull
    private Map<Long, List<Subscription>> _subscription = new LinkedHashMap();

    @NotNull
    private final Map<String, Long> commands = new LinkedHashMap();

    @NotNull
    private final Map<String, Long> status = new LinkedHashMap();
    private final long NOTIFICATION_MESSAGEID = 4294967295L;

    @NotNull
    private Map<Long, Deferred<JsonNode, Error, JsonNode>> _promises = new LinkedHashMap();

    @NotNull
    public abstract Logger getLogger$cesi();

    public abstract void sendToWebSocket(@NotNull String str, @NotNull String str2);

    public BaseCesiConnection(@Nullable Settings settings) {
        DeferredObject deferredObject = new DeferredObject();
        deferredObject.resolve(null);
        Intrinsics.checkExpressionValueIsNotNull(deferredObject, "DeferredObject<Any, Any, Any>().resolve(null)");
        this.version = 256L;
        this._settings = settings;
        this._delayedMessages = new ArrayList();
        this.LIB_VERSION = "0.3.1";
    }

    public final long get_numProcessing$cesi() {
        return this._numProcessing;
    }

    public final void set_numProcessing$cesi(long j) {
        this._numProcessing = j;
    }

    @NotNull
    public final Map<Long, List<Subscription>> get_subscription$cesi() {
        return this._subscription;
    }

    @NotNull
    public final Map<String, Long> getCommands() {
        return this.commands;
    }

    @NotNull
    public final Map<String, Long> getStatus() {
        return this.status;
    }

    @Nullable
    public final JsonNode getConstants() {
        return this.constants;
    }

    public final long getNOTIFICATION_MESSAGEID$cesi() {
        return this.NOTIFICATION_MESSAGEID;
    }

    @NotNull
    public final Map<Long, Deferred<JsonNode, Error, JsonNode>> get_promises$cesi() {
        return this._promises;
    }

    public final long getVersion() {
        return this.version;
    }

    @Nullable
    public final Settings get_settings$cesi() {
        return this._settings;
    }

    /* JADX INFO: compiled from: CesiConnection.kt */
    public static final class Subscription {

        @Nullable
        private final NotifyErrorListener errorListener;

        @Nullable
        private final Integer id;

        @Nullable
        private final NotifyListener listener;

        @Nullable
        private final NotifyMessageListener messageListener;

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Subscription)) {
                return false;
            }
            Subscription subscription = (Subscription) obj;
            return Intrinsics.areEqual(this.id, subscription.id) && Intrinsics.areEqual(this.listener, subscription.listener) && Intrinsics.areEqual(this.messageListener, subscription.messageListener) && Intrinsics.areEqual(this.errorListener, subscription.errorListener);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num != null ? num.hashCode() : 0) * 31;
            NotifyListener notifyListener = this.listener;
            int iHashCode2 = (iHashCode + (notifyListener != null ? notifyListener.hashCode() : 0)) * 31;
            NotifyMessageListener notifyMessageListener = this.messageListener;
            int iHashCode3 = (iHashCode2 + (notifyMessageListener != null ? notifyMessageListener.hashCode() : 0)) * 31;
            NotifyErrorListener notifyErrorListener = this.errorListener;
            return iHashCode3 + (notifyErrorListener != null ? notifyErrorListener.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Subscription(id=" + this.id + ", listener=" + this.listener + ", messageListener=" + this.messageListener + ", errorListener=" + this.errorListener + ")";
        }

        @Nullable
        public final NotifyErrorListener getErrorListener() {
            return this.errorListener;
        }

        @Nullable
        public final NotifyListener getListener() {
            return this.listener;
        }

        @Nullable
        public final NotifyMessageListener getMessageListener() {
            return this.messageListener;
        }
    }

    /* JADX INFO: compiled from: CesiConnection.kt */
    public static final class Header {
        private final long category;

        @Nullable
        private final Long messageID;

        @Nullable
        private final Long messageType;

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Header)) {
                return false;
            }
            Header header = (Header) obj;
            return this.category == header.category && Intrinsics.areEqual(this.messageID, header.messageID) && Intrinsics.areEqual(this.messageType, header.messageType);
        }

        public int hashCode() {
            long j = this.category;
            int i = ((int) (j ^ (j >>> 32))) * 31;
            Long l = this.messageID;
            int iHashCode = (i + (l != null ? l.hashCode() : 0)) * 31;
            Long l2 = this.messageType;
            return iHashCode + (l2 != null ? l2.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Header(category=" + this.category + ", messageID=" + this.messageID + ", messageType=" + this.messageType + ")";
        }

        public Header(long j, @Nullable Long l, @Nullable Long l2) {
            this.category = j;
            this.messageID = l;
            this.messageType = l2;
        }

        public /* synthetic */ Header(long j, Long l, Long l2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, (i & 2) != 0 ? 0L : l, (i & 4) != 0 ? 0L : l2);
        }

        public final long getCategory() {
            return this.category;
        }

        @Nullable
        public final Long getMessageID() {
            return this.messageID;
        }

        @Nullable
        public final Long getMessageType() {
            return this.messageType;
        }
    }

    /* JADX INFO: compiled from: CesiConnection.kt */
    public static final class DelayedMessage {

        @NotNull
        private String body;

        @NotNull
        private String header;
        private long id;

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DelayedMessage)) {
                return false;
            }
            DelayedMessage delayedMessage = (DelayedMessage) obj;
            return this.id == delayedMessage.id && Intrinsics.areEqual(this.header, delayedMessage.header) && Intrinsics.areEqual(this.body, delayedMessage.body);
        }

        public int hashCode() {
            long j = this.id;
            int i = ((int) (j ^ (j >>> 32))) * 31;
            String str = this.header;
            int iHashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.body;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "DelayedMessage(id=" + this.id + ", header=" + this.header + ", body=" + this.body + ")";
        }

        public DelayedMessage(long j, @NotNull String header, @NotNull String body) {
            Intrinsics.checkParameterIsNotNull(header, "header");
            Intrinsics.checkParameterIsNotNull(body, "body");
            this.id = j;
            this.header = header;
            this.body = body;
        }

        @NotNull
        public final String getBody() {
            return this.body;
        }

        @NotNull
        public final String getHeader() {
            return this.header;
        }

        public final long getId() {
            return this.id;
        }
    }

    @NotNull
    public final String getLIB_VERSION() {
        return this.LIB_VERSION;
    }

    /* JADX INFO: compiled from: CesiConnection.kt */
    public static final class category {
        private static final long ERROR = 13;
        private static final long INITIALIZE = 10;
        public static final category INSTANCE = new category();
        private static final long MESSAGE = 11;
        private static final long PING = 19;
        private static final long PONG = 20;
        private static final long SUBSCRIBE = 14;
        private static final long UNSUBSCRIBE = 15;

        private category() {
        }

        public final long getINITIALIZE() {
            return INITIALIZE;
        }

        public final long getMESSAGE() {
            return MESSAGE;
        }

        public final long getERROR() {
            return ERROR;
        }

        public final long getSUBSCRIBE() {
            return SUBSCRIBE;
        }

        public final long getUNSUBSCRIBE() {
            return UNSUBSCRIBE;
        }

        public final long getPING() {
            return PING;
        }

        public final long getPONG() {
            return PONG;
        }
    }

    /* JADX INFO: compiled from: CesiConnection.kt */
    public enum CoreStatus {
        /* JADX INFO: Fake field, exist only in values array */
        NO_ERROR(0),
        /* JADX INFO: Fake field, exist only in values array */
        UNSPECIFIED(1),
        /* JADX INFO: Fake field, exist only in values array */
        INVALID_MESSAGE_TYPE(2),
        /* JADX INFO: Fake field, exist only in values array */
        INVALID_PARAMETER(3),
        /* JADX INFO: Fake field, exist only in values array */
        NO_MEMORY(4),
        /* JADX INFO: Fake field, exist only in values array */
        CESI_SECURITY_ERROR(5),
        UNSUPPORTED_OPERATION(6),
        /* JADX INFO: Fake field, exist only in values array */
        DISABLED(7),
        /* JADX INFO: Fake field, exist only in values array */
        NOTIFICATION_ERROR(8),
        WEBSOCKET_CONNECTION_ERROR(9),
        INITIALIZATION_ERROR(10),
        /* JADX INFO: Fake field, exist only in values array */
        USER_EXCEPTION(11);

        private final long value;

        CoreStatus(long j) {
            this.value = j;
        }

        public final long getValue() {
            return this.value;
        }
    }

    public final void _StoreConstants$cesi(@NotNull JsonNode c) {
        Intrinsics.checkParameterIsNotNull(c, "c");
        Iterator<Map.Entry<String, JsonNode>> itFields = c.get("MessageType").fields();
        Intrinsics.checkExpressionValueIsNotNull(itFields, "mt.fields()");
        while (itFields.hasNext()) {
            Map.Entry<String, JsonNode> next = itFields.next();
            Map<String, Long> map = this.commands;
            String key = next.getKey();
            Intrinsics.checkExpressionValueIsNotNull(key, "f.key");
            map.put(key, Long.valueOf(next.getValue().asLong()));
        }
        Iterator<Map.Entry<String, JsonNode>> itFields2 = c.get("Status").fields();
        Intrinsics.checkExpressionValueIsNotNull(itFields2, "st.fields()");
        while (itFields2.hasNext()) {
            Map.Entry<String, JsonNode> next2 = itFields2.next();
            Map<String, Long> map2 = this.status;
            String key2 = next2.getKey();
            Intrinsics.checkExpressionValueIsNotNull(key2, "f.key");
            map2.put(key2, Long.valueOf(next2.getValue().asLong()));
        }
        logv$cesi("commands " + this.commands.toString());
        logv$cesi("status " + this.status.toString());
        this.constants = c;
    }

    public final long _issueMessageID$cesi() {
        long j = this._messageID;
        if (0 == j || this.NOTIFICATION_MESSAGEID == j) {
            this._messageID = 1L;
        }
        long j2 = this._messageID;
        this._messageID = 1 + j2;
        return j2;
    }

    @NotNull
    public final Deferred<JsonNode, Error, JsonNode> createDeferred() {
        return new DeferredObject();
    }

    @NotNull
    public final ObjectNode createJson() {
        ObjectNode objectNodeCreateObjectNode = new ObjectMapper().createObjectNode();
        Intrinsics.checkExpressionValueIsNotNull(objectNodeCreateObjectNode, "ObjectMapper().createObjectNode()");
        return objectNodeCreateObjectNode;
    }

    public final void _rejectPromises$cesi() {
        Iterator<Long> it = this._promises.keySet().iterator();
        while (it.hasNext()) {
            long jLongValue = it.next().longValue();
            try {
                Deferred<JsonNode, Error, JsonNode> deferred = this._promises.get(Long.valueOf(jLongValue));
                if (deferred != null) {
                    deferred.reject(new Error(Long.valueOf(jLongValue), 0L, Long.valueOf(CoreStatus.WEBSOCKET_CONNECTION_ERROR.getValue()), 0L));
                }
            } catch (Exception unused) {
            }
        }
    }

    public void _clear$cesi() {
        this._promises.clear();
        this.commands.clear();
        this.status.clear();
        for (CoreStatus coreStatus : CoreStatus.values()) {
            this.status.put(coreStatus.name(), Long.valueOf(coreStatus.getValue()));
        }
        this.constants = null;
    }

    public final void _notifyError$cesi(long j, long j2, @Nullable Long l) {
        Map<Long, List<Subscription>> map = this._subscription;
        if (map.containsKey(Long.valueOf(j))) {
            List<Subscription> list = map.get(Long.valueOf(j));
            if (list == null) {
                Intrinsics.throwNpe();
            }
            for (Subscription subscription : list) {
                NotifyListener listener = subscription.getListener();
                if (listener != null) {
                    listener.onError(new Error(l, Long.valueOf(j), Long.valueOf(j2), 0L));
                }
                NotifyErrorListener errorListener = subscription.getErrorListener();
                if (errorListener != null) {
                    errorListener.onError(j, new Error(l, Long.valueOf(j), Long.valueOf(j2), 0L));
                }
            }
        }
    }

    public final void _notify$cesi(long j, @NotNull JsonNode body) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        Map<Long, List<Subscription>> map = this._subscription;
        if (map.containsKey(Long.valueOf(j))) {
            List<Subscription> list = map.get(Long.valueOf(j));
            if (list == null) {
                Intrinsics.throwNpe();
            }
            for (Subscription subscription : list) {
                NotifyListener listener = subscription.getListener();
                if (listener != null) {
                    listener.onNotify(body);
                }
                NotifyMessageListener messageListener = subscription.getMessageListener();
                if (messageListener != null) {
                    messageListener.onNotify(j, body);
                }
            }
        }
    }

    public final void logv$cesi(@NotNull String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        Settings settings = this._settings;
        if (settings == null || !settings.getVerbose()) {
            return;
        }
        getLogger$cesi().info(string);
    }

    public final void loge$cesi(@NotNull String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        getLogger$cesi().warning(string);
    }

    public final void logi$cesi(@NotNull String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        getLogger$cesi().info(string);
    }

    public static /* synthetic */ Deferred _send$cesi$default(BaseCesiConnection baseCesiConnection, long j, String str, String str2, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: _send");
        }
        if ((i & 8) != 0) {
            z = false;
        }
        return baseCesiConnection._send$cesi(j, str, str2, z);
    }

    @NotNull
    public Deferred<JsonNode, Error, JsonNode> _send$cesi(long j, @NotNull String header, @NotNull String body, boolean z) {
        Intrinsics.checkParameterIsNotNull(header, "header");
        Intrinsics.checkParameterIsNotNull(body, "body");
        Settings settings = this._settings;
        long parallelisum = settings != null ? settings.getParallelisum() : 10L;
        StringBuilder sb = new StringBuilder();
        sb.append("_send: ");
        sb.append((header + body).toString());
        logv$cesi(sb.toString());
        Deferred<JsonNode, Error, JsonNode> deferredCreateDeferred = createDeferred();
        if (z) {
            sendToWebSocket(header, body);
            deferredCreateDeferred.resolve(createJson());
        } else if (this._numProcessing < parallelisum) {
            if (!this._promises.containsKey(Long.valueOf(j))) {
                this._promises.put(Long.valueOf(j), deferredCreateDeferred);
            }
            this._numProcessing++;
            sendToWebSocket(header, body);
        } else {
            if (!this._promises.containsKey(Long.valueOf(j))) {
                this._promises.put(Long.valueOf(j), deferredCreateDeferred);
            }
            this._delayedMessages.add(new DelayedMessage(j, header, body));
            logv$cesi("_send add delayed messages: _numProcessing = " + String.valueOf(this._numProcessing) + ", _delayedMessages = " + String.valueOf(this._delayedMessages.size()));
        }
        return deferredCreateDeferred;
    }

    public final void popAndSendDelayedMessage$cesi() {
        if (this._delayedMessages.size() > 0) {
            long j = this._numProcessing;
            int size = this._delayedMessages.size();
            DelayedMessage delayedMessage = this._delayedMessages.get(0);
            this._delayedMessages.remove(0);
            _send$cesi$default(this, delayedMessage.getId(), delayedMessage.getHeader(), delayedMessage.getBody(), false, 8, null);
            logv$cesi("popAndSendDelayedMessage: _numProcessing " + String.valueOf(j) + "->" + String.valueOf(this._numProcessing) + ", _delayedMessages = " + String.valueOf(size) + "->" + String.valueOf(this._delayedMessages.size()));
        }
    }
}
