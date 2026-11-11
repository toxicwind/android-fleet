package com.lagradost.cloudstream3.utils;

import io.ktor.http.CodecsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StringUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0005H\u0007J\f\u0010\u0006\u001a\u00020\u0005*\u00020\u0005H\u0007J\n\u0010\u0007\u001a\u00020\u0005*\u00020\u0005J\n\u0010\b\u001a\u00020\u0005*\u00020\u0005¨\u0006\t"}, d2 = {"Lcom/lagradost/cloudstream3/utils/StringUtils;", "", "<init>", "()V", "decodeUrl", "", "encodeUrl", "encodeUri", "decodeUri", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class StringUtils {
    public static final StringUtils INSTANCE = new StringUtils();

    private StringUtils() {
    }

    public final String decodeUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return CodecsKt.decodeURLQueryComponent$default(str, 0, 0, false, null, 15, null);
    }

    public final String encodeUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return CodecsKt.encodeURLParameter$default(str, false, 1, null);
    }

    public final String encodeUri(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return encodeUrl(str);
    }

    public final String decodeUri(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return decodeUrl(str);
    }
}
