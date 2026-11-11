package coil3.network;

import androidx.media3.extractor.text.ttml.TtmlNode;
import coil3.Extras;
import coil3.network.internal.UtilsKt;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetworkClient.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fB1\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u000b\u0010\rJ:\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nJ2\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001e"}, d2 = {"Lcoil3/network/NetworkRequest;", "", ImagesContract.URL, "", "method", "headers", "Lcoil3/network/NetworkHeaders;", TtmlNode.TAG_BODY, "Lcoil3/network/NetworkRequestBody;", "extras", "Lcoil3/Extras;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcoil3/network/NetworkHeaders;Lcoil3/network/NetworkRequestBody;Lcoil3/Extras;)V", "(Ljava/lang/String;Ljava/lang/String;Lcoil3/network/NetworkHeaders;Lcoil3/network/NetworkRequestBody;)V", "getUrl", "()Ljava/lang/String;", "getMethod", "getHeaders", "()Lcoil3/network/NetworkHeaders;", "getBody", "()Lcoil3/network/NetworkRequestBody;", "getExtras", "()Lcoil3/Extras;", "copy", "equals", "", "other", "hashCode", "", "toString", "coil-network-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NetworkRequest {
    private final NetworkRequestBody body;
    private final Extras extras;
    private final NetworkHeaders headers;
    private final String method;
    private final String url;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NetworkRequest)) {
            return false;
        }
        NetworkRequest networkRequest = (NetworkRequest) other;
        return Intrinsics.areEqual(this.url, networkRequest.url) && Intrinsics.areEqual(this.method, networkRequest.method) && Intrinsics.areEqual(this.headers, networkRequest.headers) && Intrinsics.areEqual(this.body, networkRequest.body) && Intrinsics.areEqual(this.extras, networkRequest.extras);
    }

    public int hashCode() {
        int iHashCode = ((((this.url.hashCode() * 31) + this.method.hashCode()) * 31) + this.headers.hashCode()) * 31;
        NetworkRequestBody networkRequestBody = this.body;
        return ((iHashCode + (networkRequestBody == null ? 0 : networkRequestBody.hashCode())) * 31) + this.extras.hashCode();
    }

    public String toString() {
        return "NetworkRequest(url=" + this.url + ", method=" + this.method + ", headers=" + this.headers + ", body=" + this.body + ", extras=" + this.extras + ')';
    }

    public NetworkRequest(String str, String str2, NetworkHeaders networkHeaders, NetworkRequestBody networkRequestBody, Extras extras) {
        this.url = str;
        this.method = str2;
        this.headers = networkHeaders;
        this.body = networkRequestBody;
        this.extras = extras;
    }

    public final String getUrl() {
        return this.url;
    }

    public /* synthetic */ NetworkRequest(String str, String str2, NetworkHeaders networkHeaders, NetworkRequestBody networkRequestBody, Extras extras, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? UtilsKt.HTTP_METHOD_GET : str2, (i & 4) != 0 ? NetworkHeaders.EMPTY : networkHeaders, (i & 8) != 0 ? null : networkRequestBody, (i & 16) != 0 ? Extras.EMPTY : extras);
    }

    public final String getMethod() {
        return this.method;
    }

    public final NetworkHeaders getHeaders() {
        return this.headers;
    }

    public final NetworkRequestBody getBody() {
        return this.body;
    }

    public final Extras getExtras() {
        return this.extras;
    }

    public static /* synthetic */ NetworkRequest copy$default(NetworkRequest networkRequest, String str, String str2, NetworkHeaders networkHeaders, NetworkRequestBody networkRequestBody, Extras extras, int i, Object obj) {
        if ((i & 1) != 0) {
            str = networkRequest.url;
        }
        if ((i & 2) != 0) {
            str2 = networkRequest.method;
        }
        if ((i & 4) != 0) {
            networkHeaders = networkRequest.headers;
        }
        if ((i & 8) != 0) {
            networkRequestBody = networkRequest.body;
        }
        if ((i & 16) != 0) {
            extras = networkRequest.extras;
        }
        Extras extras2 = extras;
        NetworkHeaders networkHeaders2 = networkHeaders;
        return networkRequest.copy(str, str2, networkHeaders2, networkRequestBody, extras2);
    }

    public final NetworkRequest copy(String url, String method, NetworkHeaders headers, NetworkRequestBody body, Extras extras) {
        return new NetworkRequest(url, method, headers, body, extras);
    }

    public /* synthetic */ NetworkRequest(String str, String str2, NetworkHeaders networkHeaders, NetworkRequestBody networkRequestBody, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? UtilsKt.HTTP_METHOD_GET : str2, (i & 4) != 0 ? NetworkHeaders.EMPTY : networkHeaders, (i & 8) != 0 ? null : networkRequestBody);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    public /* synthetic */ NetworkRequest(String str, String str2, NetworkHeaders networkHeaders, NetworkRequestBody networkRequestBody) {
        this(str, str2, networkHeaders, networkRequestBody, null, 16, null);
    }

    public static /* synthetic */ NetworkRequest copy$default(NetworkRequest networkRequest, String str, String str2, NetworkHeaders networkHeaders, NetworkRequestBody networkRequestBody, int i, Object obj) {
        if ((i & 1) != 0) {
            str = networkRequest.url;
        }
        if ((i & 2) != 0) {
            str2 = networkRequest.method;
        }
        if ((i & 4) != 0) {
            networkHeaders = networkRequest.headers;
        }
        if ((i & 8) != 0) {
            networkRequestBody = networkRequest.body;
        }
        return networkRequest.copy(str, str2, networkHeaders, networkRequestBody);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    public final /* synthetic */ NetworkRequest copy(String url, String method, NetworkHeaders headers, NetworkRequestBody body) {
        return new NetworkRequest(url, method, headers, body, this.extras);
    }
}
