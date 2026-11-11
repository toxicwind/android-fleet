package io.ktor.http;

import io.ktor.http.header.UtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Headers.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\r\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u001d\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0001\u0010\u0006\u001a#\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\u0001\u0010\t\u001aE\u0010\u0001\u001a\u00020\u000026\u0010\f\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00070\u000b0\n\"\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00070\u000b¢\u0006\u0004\b\u0001\u0010\r\u001a&\u0010\u0013\u001a\u00020\u00002\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011¢\u0006\u0004\b\u0013\u0010\u0014\u001a5\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007*\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lio/ktor/http/Headers;", "headersOf", "()Lio/ktor/http/Headers;", "", "name", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/String;Ljava/lang/String;)Lio/ktor/http/Headers;", "", "values", "(Ljava/lang/String;Ljava/util/List;)Lio/ktor/http/Headers;", "", "Lkotlin/Pair;", "pairs", "([Lkotlin/Pair;)Lio/ktor/http/Headers;", "Lkotlin/Function1;", "Lio/ktor/http/HeadersBuilder;", "", "Lkotlin/ExtensionFunctionType;", "builder", "headers", "(Lkotlin/jvm/functions/Function1;)Lio/ktor/http/Headers;", "", "separator", "", "splitInsideQuotes", "getSplitValues", "(Lio/ktor/http/Headers;Ljava/lang/String;CZ)Ljava/util/List;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class HeadersKt {
    public static final Headers headersOf() {
        return Headers.INSTANCE.getEmpty();
    }

    public static final Headers headersOf(String name, String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        return new HeadersSingleImpl(name, CollectionsKt.listOf(value));
    }

    public static final Headers headersOf(String name, List<String> values) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(values, "values");
        return new HeadersSingleImpl(name, values);
    }

    public static final Headers headersOf(Pair<String, ? extends List<String>>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        return new HeadersImpl(MapsKt.toMap(ArraysKt.asList(pairs)));
    }

    public static final Headers headers(Function1<? super HeadersBuilder, Unit> builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Headers.Companion companion = Headers.INSTANCE;
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
        builder.invoke(headersBuilder);
        return headersBuilder.build();
    }

    public static /* synthetic */ List getSplitValues$default(Headers headers, String str, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            c = AbstractJsonLexerKt.COMMA;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return getSplitValues(headers, str, c, z);
    }

    public static final List<String> getSplitValues(Headers headers, String name, char c, boolean z) {
        Intrinsics.checkNotNullParameter(headers, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        List<String> all = headers.getAll(name);
        if (all == null) {
            return null;
        }
        return UtilsKt.splitHeaderValues(all, c, z);
    }
}
