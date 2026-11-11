package com.lagradost.nicehttp;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/* JADX INFO: compiled from: Session.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0006B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/nicehttp/Session;", "Lcom/lagradost/nicehttp/Requests;", "client", "Lokhttp3/OkHttpClient;", "<init>", "(Lokhttp3/OkHttpClient;)V", "CustomCookieJar", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Session extends Requests {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Session(OkHttpClient client) {
        super(null, null, null, null, null, 0, null, 0L, null, 511, null);
        Intrinsics.checkNotNullParameter(client, "client");
        setBaseClient(client.newBuilder().cookieJar(new CustomCookieJar()).build());
    }

    /* JADX INFO: compiled from: Session.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00070\rH\u0016R&\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/lagradost/nicehttp/Session$CustomCookieJar;", "Lokhttp3/CookieJar;", "<init>", "(Lcom/lagradost/nicehttp/Session;)V", "cookies", "", "", "Lokhttp3/Cookie;", "getCookies", "()Ljava/util/Map;", "setCookies", "(Ljava/util/Map;)V", "loadForRequest", "", ImagesContract.URL, "Lokhttp3/HttpUrl;", "saveFromResponse", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public class CustomCookieJar implements CookieJar {
        private Map<String, Cookie> cookies = MapsKt.emptyMap();

        public CustomCookieJar() {
        }

        public final Map<String, Cookie> getCookies() {
            return this.cookies;
        }

        public final void setCookies(Map<String, Cookie> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.cookies = map;
        }

        @Override // okhttp3.CookieJar
        public List<Cookie> loadForRequest(HttpUrl url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return CollectionsKt.toList(this.cookies.values());
        }

        @Override // okhttp3.CookieJar
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(cookies, "cookies");
            Map<String, Cookie> map = this.cookies;
            List<Cookie> list = cookies;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (Cookie cookie : list) {
                arrayList.add(TuplesKt.to(cookie.name(), cookie));
            }
            this.cookies = MapsKt.plus(map, arrayList);
        }
    }
}
