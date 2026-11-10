package okhttp3.internal.http;

import com.google.firebase.storage.network.NetworkRequest;

/* JADX INFO: loaded from: classes2.dex */
public final class HttpMethod {
    public static boolean invalidatesCache(String str) {
        return str.equals(NetworkRequest.POST) || str.equals(NetworkRequest.PATCH) || str.equals(NetworkRequest.PUT) || str.equals(NetworkRequest.DELETE) || str.equals("MOVE");
    }

    public static boolean permitsRequestBody(String str) {
        return (str.equals(NetworkRequest.GET) || str.equals("HEAD")) ? false : true;
    }

    public static boolean redirectsToGet(String str) {
        return !str.equals("PROPFIND");
    }

    public static boolean redirectsWithBody(String str) {
        return str.equals("PROPFIND");
    }

    public static boolean requiresRequestBody(String str) {
        return str.equals(NetworkRequest.POST) || str.equals(NetworkRequest.PUT) || str.equals(NetworkRequest.PATCH) || str.equals("PROPPATCH") || str.equals("REPORT");
    }
}
