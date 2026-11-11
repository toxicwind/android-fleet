package okhttp3;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.http.HttpHeaders;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/* JADX INFO: compiled from: CompressionInterceptor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0018B\u001b\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0015\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0014J\u0017\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0016\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\u0017R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lokhttp3/CompressionInterceptor;", "Lokhttp3/Interceptor;", "algorithms", "", "Lokhttp3/CompressionInterceptor$DecompressionAlgorithm;", "<init>", "([Lokhttp3/CompressionInterceptor$DecompressionAlgorithm;)V", "getAlgorithms", "()[Lokhttp3/CompressionInterceptor$DecompressionAlgorithm;", "[Lokhttp3/CompressionInterceptor$DecompressionAlgorithm;", "acceptEncoding", "", "getAcceptEncoding$okhttp", "()Ljava/lang/String;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "decompress", "response", "decompress$okhttp", "lookupDecompressor", "encoding", "lookupDecompressor$okhttp", "DecompressionAlgorithm", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class CompressionInterceptor implements Interceptor {
    private final String acceptEncoding;
    private final DecompressionAlgorithm[] algorithms;

    /* JADX INFO: compiled from: CompressionInterceptor.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lokhttp3/CompressionInterceptor$DecompressionAlgorithm;", "", "encoding", "", "getEncoding", "()Ljava/lang/String;", "decompress", "Lokio/Source;", "compressedSource", "Lokio/BufferedSource;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface DecompressionAlgorithm {
        Source decompress(BufferedSource compressedSource);

        String getEncoding();
    }

    public CompressionInterceptor(DecompressionAlgorithm... algorithms) {
        Intrinsics.checkNotNullParameter(algorithms, "algorithms");
        this.algorithms = algorithms;
        ArrayList arrayList = new ArrayList(algorithms.length);
        for (DecompressionAlgorithm decompressionAlgorithm : algorithms) {
            arrayList.add(decompressionAlgorithm.getEncoding());
        }
        this.acceptEncoding = CollectionsKt.joinToString$default(arrayList, ", ", null, null, 0, null, null, 62, null);
    }

    public final DecompressionAlgorithm[] getAlgorithms() {
        return this.algorithms;
    }

    /* JADX INFO: renamed from: getAcceptEncoding$okhttp, reason: from getter */
    public final String getAcceptEncoding() {
        return this.acceptEncoding;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        if (!(this.algorithms.length == 0) && chain.request().header("Accept-Encoding") == null) {
            return decompress$okhttp(chain.proceed(chain.request().newBuilder().header("Accept-Encoding", this.acceptEncoding).build()));
        }
        return chain.proceed(chain.request());
    }

    public final Response decompress$okhttp(Response response) {
        DecompressionAlgorithm decompressionAlgorithmLookupDecompressor$okhttp;
        Intrinsics.checkNotNullParameter(response, "response");
        if (HttpHeaders.promisesBody(response)) {
            ResponseBody responseBodyBody = response.body();
            String strHeader$default = Response.header$default(response, "Content-Encoding", null, 2, null);
            if (strHeader$default != null && (decompressionAlgorithmLookupDecompressor$okhttp = lookupDecompressor$okhttp(strHeader$default)) != null) {
                return response.newBuilder().removeHeader("Content-Encoding").removeHeader("Content-Length").body(ResponseBody.INSTANCE.create(Okio.buffer(decompressionAlgorithmLookupDecompressor$okhttp.decompress(responseBodyBody.get$this_asResponseBody())), responseBodyBody.get$contentType(), -1L)).build();
            }
        }
        return response;
    }

    public final DecompressionAlgorithm lookupDecompressor$okhttp(String encoding) {
        Intrinsics.checkNotNullParameter(encoding, "encoding");
        for (DecompressionAlgorithm decompressionAlgorithm : this.algorithms) {
            if (StringsKt.equals(decompressionAlgorithm.getEncoding(), encoding, true)) {
                return decompressionAlgorithm;
            }
        }
        return null;
    }
}
