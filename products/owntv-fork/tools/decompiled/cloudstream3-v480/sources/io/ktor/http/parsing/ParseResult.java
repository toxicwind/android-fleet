package io.ktor.http.parsing;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Parser.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00040\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\t\u001a\u0004\u0018\u00010\u00032\u0006\u0010\b\u001a\u00020\u0003H\u0086\u0002¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00042\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\u000e\u0010\u000fR&\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/http/parsing/ParseResult;", "", "", "", "", "mapping", "<init>", "(Ljava/util/Map;)V", "key", "get", "(Ljava/lang/String;)Ljava/lang/String;", "getAll", "(Ljava/lang/String;)Ljava/util/List;", "", "contains", "(Ljava/lang/String;)Z", "Ljava/util/Map;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ParseResult {
    private final Map<String, List<String>> mapping;

    /* JADX WARN: Multi-variable type inference failed */
    public ParseResult(Map<String, ? extends List<String>> mapping) {
        Intrinsics.checkNotNullParameter(mapping, "mapping");
        this.mapping = mapping;
    }

    public final String get(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        List<String> list = this.mapping.get(key);
        if (list != null) {
            return (String) CollectionsKt.firstOrNull((List) list);
        }
        return null;
    }

    public final List<String> getAll(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        List<String> list = this.mapping.get(key);
        return list == null ? CollectionsKt.emptyList() : list;
    }

    public final boolean contains(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.mapping.containsKey(key);
    }
}
