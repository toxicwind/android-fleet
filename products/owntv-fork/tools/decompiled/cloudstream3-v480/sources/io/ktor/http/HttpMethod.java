package io.ktor.http;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import coil3.network.internal.UtilsKt;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: HttpMethod.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0006\u001a\u00020\u0002H\u0096\u0080\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\u0007J\u001a\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u0010\u001a\u00020\u000fHÖ\u0081\u0004¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0007¨\u0006\u0015"}, d2 = {"Lio/ktor/http/HttpMethod;", "", "", ES6Iterator.VALUE_PROPERTY, "<init>", "(Ljava/lang/String;)V", "toString", "()Ljava/lang/String;", "component1", "copy", "(Ljava/lang/String;)Lio/ktor/http/HttpMethod;", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "Ljava/lang/String;", "getValue", "Companion", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HttpMethod {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final List<HttpMethod> DefaultMethods;
    public static final HttpMethod Delete;
    public static final HttpMethod Get;
    public static final HttpMethod Head;
    public static final HttpMethod Options;
    public static final HttpMethod Patch;
    public static final HttpMethod Post;
    public static final HttpMethod Put;
    public static final HttpMethod Query;
    public static final HttpMethod Trace;
    private final String value;

    public static /* synthetic */ HttpMethod copy$default(HttpMethod httpMethod, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = httpMethod.value;
        }
        return httpMethod.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    public final HttpMethod copy(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new HttpMethod(value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof HttpMethod) && Intrinsics.areEqual(this.value, ((HttpMethod) other).value);
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public HttpMethod(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
    }

    public final String getValue() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }

    /* JADX INFO: compiled from: HttpMethod.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u000b\u0010\nJ\u000f\u0010\f\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\f\u0010\nJ\u000f\u0010\r\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\r\u0010\nJ\u000f\u0010\u000e\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u000e\u0010\nJ\u000f\u0010\u000f\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u000f\u0010\nJ\u000f\u0010\u0010\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0010\u0010\nJ\u000f\u0010\u0011\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0011\u0010\nJ\u0015\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012H\u0007¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0016R\u0014\u0010\u001a\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u0016R\u0014\u0010\u001b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u0016R\u0014\u0010\u001c\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u0016R\u0014\u0010\u001d\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u0016R\u0014\u0010\u001e\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u0016R\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00128\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lio/ktor/http/HttpMethod$Companion;", "", "<init>", "()V", "", "method", "Lio/ktor/http/HttpMethod;", "parse", "(Ljava/lang/String;)Lio/ktor/http/HttpMethod;", "getGet", "()Lio/ktor/http/HttpMethod;", "getPost", "getPut", "getPatch", "getDelete", "getHead", "getOptions", "getTrace", "", "getDefaultMethods", "()Ljava/util/List;", "Get", "Lio/ktor/http/HttpMethod;", "Post", "Put", "Patch", "Delete", "Head", "Options", "Trace", "Query", "DefaultMethods", "Ljava/util/List;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HttpMethod parse(String method) {
            Intrinsics.checkNotNullParameter(method, "method");
            if (Intrinsics.areEqual(method, HttpMethod.Get.getValue())) {
                return HttpMethod.Get;
            }
            if (Intrinsics.areEqual(method, HttpMethod.Post.getValue())) {
                return HttpMethod.Post;
            }
            if (Intrinsics.areEqual(method, HttpMethod.Put.getValue())) {
                return HttpMethod.Put;
            }
            if (Intrinsics.areEqual(method, HttpMethod.Patch.getValue())) {
                return HttpMethod.Patch;
            }
            if (Intrinsics.areEqual(method, HttpMethod.Trace.getValue())) {
                return HttpMethod.Trace;
            }
            if (Intrinsics.areEqual(method, HttpMethod.Delete.getValue())) {
                return HttpMethod.Delete;
            }
            if (Intrinsics.areEqual(method, HttpMethod.Head.getValue())) {
                return HttpMethod.Head;
            }
            if (Intrinsics.areEqual(method, HttpMethod.Query.getValue())) {
                return HttpMethod.Query;
            }
            return Intrinsics.areEqual(method, HttpMethod.Options.getValue()) ? HttpMethod.Options : new HttpMethod(method);
        }

        @Deprecated(message = "Use Get const instead", replaceWith = @ReplaceWith(expression = "HttpMethod.Get", imports = {}))
        public final HttpMethod getGet() {
            return HttpMethod.Get;
        }

        @Deprecated(message = "Use Post const instead", replaceWith = @ReplaceWith(expression = "HttpMethod.Post", imports = {}))
        public final HttpMethod getPost() {
            return HttpMethod.Post;
        }

        @Deprecated(message = "Use Put const instead", replaceWith = @ReplaceWith(expression = "HttpMethod.Put", imports = {}))
        public final HttpMethod getPut() {
            return HttpMethod.Put;
        }

        @Deprecated(message = "Use Patch const instead", replaceWith = @ReplaceWith(expression = "HttpMethod.Patch", imports = {}))
        public final HttpMethod getPatch() {
            return HttpMethod.Patch;
        }

        @Deprecated(message = "Use Delete const instead", replaceWith = @ReplaceWith(expression = "HttpMethod.Delete", imports = {}))
        public final HttpMethod getDelete() {
            return HttpMethod.Delete;
        }

        @Deprecated(message = "Use Head const instead", replaceWith = @ReplaceWith(expression = "HttpMethod.Head", imports = {}))
        public final HttpMethod getHead() {
            return HttpMethod.Head;
        }

        @Deprecated(message = "Use Options const instead", replaceWith = @ReplaceWith(expression = "HttpMethod.Options", imports = {}))
        public final HttpMethod getOptions() {
            return HttpMethod.Options;
        }

        @Deprecated(message = "Use Trace const instead", replaceWith = @ReplaceWith(expression = "HttpMethod.Trace", imports = {}))
        public final HttpMethod getTrace() {
            return HttpMethod.Trace;
        }

        @Deprecated(message = "Use DefaultMethods const instead", replaceWith = @ReplaceWith(expression = "HttpMethod.DefaultMethods", imports = {}))
        public final List<HttpMethod> getDefaultMethods() {
            return HttpMethod.DefaultMethods;
        }
    }

    static {
        HttpMethod httpMethod = new HttpMethod(UtilsKt.HTTP_METHOD_GET);
        Get = httpMethod;
        HttpMethod httpMethod2 = new HttpMethod(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST);
        Post = httpMethod2;
        HttpMethod httpMethod3 = new HttpMethod("PUT");
        Put = httpMethod3;
        HttpMethod httpMethod4 = new HttpMethod("PATCH");
        Patch = httpMethod4;
        HttpMethod httpMethod5 = new HttpMethod("DELETE");
        Delete = httpMethod5;
        HttpMethod httpMethod6 = new HttpMethod("HEAD");
        Head = httpMethod6;
        HttpMethod httpMethod7 = new HttpMethod("OPTIONS");
        Options = httpMethod7;
        Trace = new HttpMethod("TRACE");
        Query = new HttpMethod("QUERY");
        DefaultMethods = CollectionsKt.listOf((Object[]) new HttpMethod[]{httpMethod, httpMethod2, httpMethod3, httpMethod4, httpMethod5, httpMethod6, httpMethod7});
    }
}
