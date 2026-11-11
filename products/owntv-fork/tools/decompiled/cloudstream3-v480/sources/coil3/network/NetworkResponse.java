package coil3.network;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetworkClient.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001BG\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\f\u0010\rJF\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001d\u001a\u00020\u0003H\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006 "}, d2 = {"Lcoil3/network/NetworkResponse;", "", "code", "", "requestMillis", "", "responseMillis", "headers", "Lcoil3/network/NetworkHeaders;", TtmlNode.TAG_BODY, "Lcoil3/network/NetworkResponseBody;", "delegate", "<init>", "(IJJLcoil3/network/NetworkHeaders;Lcoil3/network/NetworkResponseBody;Ljava/lang/Object;)V", "getCode", "()I", "getRequestMillis", "()J", "getResponseMillis", "getHeaders", "()Lcoil3/network/NetworkHeaders;", "getBody", "()Lcoil3/network/NetworkResponseBody;", "getDelegate", "()Ljava/lang/Object;", "copy", "equals", "", "other", "hashCode", "toString", "", "coil-network-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NetworkResponse {
    private final NetworkResponseBody body;
    private final int code;
    private final Object delegate;
    private final NetworkHeaders headers;
    private final long requestMillis;
    private final long responseMillis;

    public NetworkResponse() {
        this(0, 0L, 0L, null, null, null, 63, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NetworkResponse)) {
            return false;
        }
        NetworkResponse networkResponse = (NetworkResponse) other;
        return this.code == networkResponse.code && this.requestMillis == networkResponse.requestMillis && this.responseMillis == networkResponse.responseMillis && Intrinsics.areEqual(this.headers, networkResponse.headers) && Intrinsics.areEqual(this.body, networkResponse.body) && Intrinsics.areEqual(this.delegate, networkResponse.delegate);
    }

    public int hashCode() {
        int iM = ((((((this.code * 31) + UByte$$ExternalSyntheticBackport0.m(this.requestMillis)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.responseMillis)) * 31) + this.headers.hashCode()) * 31;
        NetworkResponseBody networkResponseBody = this.body;
        int iHashCode = (iM + (networkResponseBody == null ? 0 : networkResponseBody.hashCode())) * 31;
        Object obj = this.delegate;
        return iHashCode + (obj != null ? obj.hashCode() : 0);
    }

    public String toString() {
        return "NetworkResponse(code=" + this.code + ", requestMillis=" + this.requestMillis + ", responseMillis=" + this.responseMillis + ", headers=" + this.headers + ", body=" + this.body + ", delegate=" + this.delegate + ')';
    }

    public NetworkResponse(int i, long j, long j2, NetworkHeaders networkHeaders, NetworkResponseBody networkResponseBody, Object obj) {
        this.code = i;
        this.requestMillis = j;
        this.responseMillis = j2;
        this.headers = networkHeaders;
        this.body = networkResponseBody;
        this.delegate = obj;
    }

    public final int getCode() {
        return this.code;
    }

    public final long getRequestMillis() {
        return this.requestMillis;
    }

    public final long getResponseMillis() {
        return this.responseMillis;
    }

    public /* synthetic */ NetworkResponse(int i, long j, long j2, NetworkHeaders networkHeaders, NetworkResponseBody networkResponseBody, Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 200 : i, (i2 & 2) != 0 ? 0L : j, (i2 & 4) != 0 ? 0L : j2, (i2 & 8) != 0 ? NetworkHeaders.EMPTY : networkHeaders, (i2 & 16) != 0 ? null : networkResponseBody, (i2 & 32) != 0 ? null : obj);
    }

    public final NetworkHeaders getHeaders() {
        return this.headers;
    }

    public final NetworkResponseBody getBody() {
        return this.body;
    }

    public final Object getDelegate() {
        return this.delegate;
    }

    public static /* synthetic */ NetworkResponse copy$default(NetworkResponse networkResponse, int i, long j, long j2, NetworkHeaders networkHeaders, NetworkResponseBody networkResponseBody, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = networkResponse.code;
        }
        if ((i2 & 2) != 0) {
            j = networkResponse.requestMillis;
        }
        if ((i2 & 4) != 0) {
            j2 = networkResponse.responseMillis;
        }
        if ((i2 & 8) != 0) {
            networkHeaders = networkResponse.headers;
        }
        if ((i2 & 16) != 0) {
            networkResponseBody = networkResponse.body;
        }
        if ((i2 & 32) != 0) {
            obj = networkResponse.delegate;
        }
        Object obj3 = obj;
        NetworkHeaders networkHeaders2 = networkHeaders;
        long j3 = j2;
        return networkResponse.copy(i, j, j3, networkHeaders2, networkResponseBody, obj3);
    }

    public final NetworkResponse copy(int code, long requestMillis, long responseMillis, NetworkHeaders headers, NetworkResponseBody body, Object delegate) {
        return new NetworkResponse(code, requestMillis, responseMillis, headers, body, delegate);
    }
}
