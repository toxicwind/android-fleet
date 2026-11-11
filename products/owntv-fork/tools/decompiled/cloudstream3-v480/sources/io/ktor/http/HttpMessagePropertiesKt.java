package io.ktor.http;

import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: HttpMessageProperties.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0019\u0010\b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\t\u001a\u0019\u0010\f\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\r\u001a\u0019\u0010\u000f\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\r\u001a\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0010\u001a\u0019\u0010\u0013\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012*\u00020\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0013\u0010\u0015\u001a\u0004\u0018\u00010\n*\u00020\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0019\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001a*\u00020\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u00020\u001d¢\u0006\u0004\b\u0004\u0010\u001e\u001a\u0019\u0010\u0013\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012*\u00020\u001d¢\u0006\u0004\b\u0013\u0010\u001f\u001a\u0013\u0010\u0015\u001a\u0004\u0018\u00010\n*\u00020\u001d¢\u0006\u0004\b\u0015\u0010 \u001a\u0019\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u001d¢\u0006\u0004\b\u0018\u0010!\u001a\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001a*\u00020\u001d¢\u0006\u0004\b\u001b\u0010\"\u001a\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\u0017*\u00020\u001d¢\u0006\u0004\b$\u0010!\u001a\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020#0\u0017*\u00020\u0000¢\u0006\u0004\b%\u0010\u0019\u001a\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020&0\u0017*\u00020\u001d¢\u0006\u0004\b'\u0010!\u001a\u0019\u0010(\u001a\b\u0012\u0004\u0012\u00020\n0\u0017*\u00020\nH\u0000¢\u0006\u0004\b(\u0010)¨\u0006*"}, d2 = {"Lio/ktor/http/HttpMessageBuilder;", "Lio/ktor/http/ContentType;", "type", "", "contentType", "(Lio/ktor/http/HttpMessageBuilder;Lio/ktor/http/ContentType;)V", "", "seconds", "maxAge", "(Lio/ktor/http/HttpMessageBuilder;I)V", "", ES6Iterator.VALUE_PROPERTY, "ifNoneMatch", "(Lio/ktor/http/HttpMessageBuilder;Ljava/lang/String;)V", "content", "userAgent", "(Lio/ktor/http/HttpMessageBuilder;)Lio/ktor/http/ContentType;", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "(Lio/ktor/http/HttpMessageBuilder;)Ljava/nio/charset/Charset;", "etag", "(Lio/ktor/http/HttpMessageBuilder;)Ljava/lang/String;", "", "vary", "(Lio/ktor/http/HttpMessageBuilder;)Ljava/util/List;", "", "contentLength", "(Lio/ktor/http/HttpMessageBuilder;)Ljava/lang/Long;", "Lio/ktor/http/HttpMessage;", "(Lio/ktor/http/HttpMessage;)Lio/ktor/http/ContentType;", "(Lio/ktor/http/HttpMessage;)Ljava/nio/charset/Charset;", "(Lio/ktor/http/HttpMessage;)Ljava/lang/String;", "(Lio/ktor/http/HttpMessage;)Ljava/util/List;", "(Lio/ktor/http/HttpMessage;)Ljava/lang/Long;", "Lio/ktor/http/Cookie;", "setCookie", "cookies", "Lio/ktor/http/HeaderValue;", "cacheControl", "splitSetCookieHeader", "(Ljava/lang/String;)Ljava/util/List;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class HttpMessagePropertiesKt {
    public static final void contentType(HttpMessageBuilder httpMessageBuilder, ContentType type) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        httpMessageBuilder.getHeaders().set("Content-Type", type.toString());
    }

    public static final void maxAge(HttpMessageBuilder httpMessageBuilder, int i) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        httpMessageBuilder.getHeaders().append("Cache-Control", "max-age=" + i);
    }

    public static final void ifNoneMatch(HttpMessageBuilder httpMessageBuilder, String value) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        httpMessageBuilder.getHeaders().set("If-None-Match", value);
    }

    public static final void userAgent(HttpMessageBuilder httpMessageBuilder, String content) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        Intrinsics.checkNotNullParameter(content, "content");
        httpMessageBuilder.getHeaders().set("User-Agent", content);
    }

    public static final ContentType contentType(HttpMessageBuilder httpMessageBuilder) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        String str = httpMessageBuilder.getHeaders().get("Content-Type");
        if (str != null) {
            return ContentType.INSTANCE.parse(str);
        }
        return null;
    }

    public static final Charset charset(HttpMessageBuilder httpMessageBuilder) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        ContentType contentType = contentType(httpMessageBuilder);
        if (contentType != null) {
            return ContentTypesKt.charset(contentType);
        }
        return null;
    }

    public static final String etag(HttpMessageBuilder httpMessageBuilder) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        return httpMessageBuilder.getHeaders().get("ETag");
    }

    public static final List<String> vary(HttpMessageBuilder httpMessageBuilder) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        List<String> all = httpMessageBuilder.getHeaders().getAll("Vary");
        if (all == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = all.iterator();
        while (it.hasNext()) {
            List listSplit$default = StringsKt.split$default((CharSequence) it.next(), new String[]{","}, false, 0, 6, (Object) null);
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
            Iterator it2 = listSplit$default.iterator();
            while (it2.hasNext()) {
                arrayList2.add(StringsKt.trim((CharSequence) it2.next()).toString());
            }
            CollectionsKt.addAll(arrayList, arrayList2);
        }
        return arrayList;
    }

    public static final Long contentLength(HttpMessageBuilder httpMessageBuilder) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        String str = httpMessageBuilder.getHeaders().get("Content-Length");
        if (str != null) {
            return StringsKt.toLongOrNull(str);
        }
        return null;
    }

    public static final ContentType contentType(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        String str = httpMessage.getHeaders().get("Content-Type");
        if (str != null) {
            return ContentType.INSTANCE.parse(str);
        }
        return null;
    }

    public static final Charset charset(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        ContentType contentType = contentType(httpMessage);
        if (contentType != null) {
            return ContentTypesKt.charset(contentType);
        }
        return null;
    }

    public static final String etag(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        return httpMessage.getHeaders().get("ETag");
    }

    public static final List<String> vary(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        List<String> all = httpMessage.getHeaders().getAll("Vary");
        if (all == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = all.iterator();
        while (it.hasNext()) {
            List listSplit$default = StringsKt.split$default((CharSequence) it.next(), new String[]{","}, false, 0, 6, (Object) null);
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
            Iterator it2 = listSplit$default.iterator();
            while (it2.hasNext()) {
                arrayList2.add(StringsKt.trim((CharSequence) it2.next()).toString());
            }
            CollectionsKt.addAll(arrayList, arrayList2);
        }
        return arrayList;
    }

    public static final Long contentLength(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        String str = httpMessage.getHeaders().get("Content-Length");
        if (str != null) {
            return StringsKt.toLongOrNull(str);
        }
        return null;
    }

    public static final List<Cookie> setCookie(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        List<String> all = httpMessage.getHeaders().getAll("Set-Cookie");
        if (all == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = all.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, splitSetCookieHeader((String) it.next()));
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(CookieKt.parseServerSetCookieHeader((String) it2.next()));
        }
        return arrayList3;
    }

    public static final List<Cookie> cookies(HttpMessageBuilder httpMessageBuilder) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        List<String> all = httpMessageBuilder.getHeaders().getAll("Set-Cookie");
        if (all == null) {
            return CollectionsKt.emptyList();
        }
        List<String> list = all;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(CookieKt.parseServerSetCookieHeader((String) it.next()));
        }
        return arrayList;
    }

    public static final List<HeaderValue> cacheControl(HttpMessage httpMessage) {
        List<HeaderValue> headerValue;
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        String str = httpMessage.getHeaders().get("Cache-Control");
        return (str == null || (headerValue = HttpHeaderValueParserKt.parseHeaderValue(str)) == null) ? CollectionsKt.emptyList() : headerValue;
    }

    public static final List<String> splitSetCookieHeader(String str) {
        int i;
        Intrinsics.checkNotNullParameter(str, "<this>");
        String str2 = str;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str2, AbstractJsonLexerKt.COMMA, 0, false, 6, (Object) null);
        if (iIndexOf$default == -1) {
            return CollectionsKt.listOf(str);
        }
        ArrayList arrayList = new ArrayList();
        int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str2, '=', iIndexOf$default, false, 4, (Object) null);
        int iIndexOf$default3 = StringsKt.indexOf$default((CharSequence) str2, ';', iIndexOf$default, false, 4, (Object) null);
        int iIndexOf$default4 = iIndexOf$default2;
        int i2 = 0;
        while (i2 < str.length() && iIndexOf$default > 0) {
            if (iIndexOf$default4 < iIndexOf$default) {
                iIndexOf$default4 = StringsKt.indexOf$default((CharSequence) str2, '=', iIndexOf$default, false, 4, (Object) null);
            }
            int i3 = iIndexOf$default;
            int i4 = iIndexOf$default4;
            int iIndexOf$default5 = StringsKt.indexOf$default((CharSequence) str2, AbstractJsonLexerKt.COMMA, i3 + 1, false, 4, (Object) null);
            while (true) {
                i = i3;
                i3 = iIndexOf$default5;
                if (i3 < 0 || i3 >= i4) {
                    break;
                }
                iIndexOf$default5 = StringsKt.indexOf$default((CharSequence) str2, AbstractJsonLexerKt.COMMA, i3 + 1, false, 4, (Object) null);
            }
            if (iIndexOf$default3 < i) {
                iIndexOf$default3 = StringsKt.indexOf$default((CharSequence) str2, ';', i, false, 4, (Object) null);
            }
            if (i4 < 0) {
                String strSubstring = str.substring(i2);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                arrayList.add(strSubstring);
                return arrayList;
            }
            if (iIndexOf$default3 == -1 || iIndexOf$default3 > i4) {
                String strSubstring2 = str.substring(i2, i);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                arrayList.add(strSubstring2);
                i2 = i + 1;
            }
            iIndexOf$default = i3;
            iIndexOf$default4 = i4;
        }
        if (i2 < str.length()) {
            String strSubstring3 = str.substring(i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
            arrayList.add(strSubstring3);
        }
        return arrayList;
    }
}
