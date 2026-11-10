package okhttp3.internal.http;

import d.B;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes2.dex */
public interface HttpCodec {
    public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

    void cancel();

    B createRequestBody(Request request, long j);

    void finishRequest();

    void flushRequest();

    ResponseBody openResponseBody(Response response);

    Response.Builder readResponseHeaders(boolean z);

    void writeRequestHeaders(Request request);
}
