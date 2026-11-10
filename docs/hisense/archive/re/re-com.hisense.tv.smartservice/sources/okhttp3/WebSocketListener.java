package okhttp3;

import d.j;

/* JADX INFO: loaded from: classes2.dex */
public abstract class WebSocketListener {
    public void onClosed(WebSocket webSocket, int i, String str) {
    }

    public void onClosing(WebSocket webSocket, int i, String str) {
    }

    public void onFailure(WebSocket webSocket, Throwable th, Response response) {
    }

    public void onMessage(WebSocket webSocket, j jVar) {
    }

    public void onMessage(WebSocket webSocket, String str) {
    }

    public void onOpen(WebSocket webSocket, Response response) {
    }
}
