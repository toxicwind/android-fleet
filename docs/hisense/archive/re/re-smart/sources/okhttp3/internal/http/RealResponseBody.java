package okhttp3.internal.http;

import d.i;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes2.dex */
public final class RealResponseBody extends ResponseBody {
    public final long contentLength;
    public final String contentTypeString;
    public final i source;

    public RealResponseBody(String str, long j, i iVar) {
        this.contentTypeString = str;
        this.contentLength = j;
        this.source = iVar;
    }

    @Override // okhttp3.ResponseBody
    public long contentLength() {
        return this.contentLength;
    }

    @Override // okhttp3.ResponseBody
    public MediaType contentType() {
        String str = this.contentTypeString;
        if (str != null) {
            return MediaType.parse(str);
        }
        return null;
    }

    @Override // okhttp3.ResponseBody
    public i source() {
        return this.source;
    }
}
