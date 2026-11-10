package okhttp3;

import d.j;

/* JADX INFO: loaded from: classes2.dex */
public interface WebSocket {

    public interface Factory {
        WebSocket newWebSocket(Request request, WebSocketListener webSocketListener);
    }

    void cancel();

    boolean close(int i, String str);

    long queueSize();

    Request request();

    boolean send(j jVar);

    boolean send(String str);
}
