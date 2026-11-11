package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ContentDisposition.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u001f\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"", "key", ES6Iterator.VALUE_PROPERTY, "encodeContentDispositionAttribute", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ContentDispositionKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String encodeContentDispositionAttribute(String str, String str2) {
        if (!Intrinsics.areEqual(str, ContentDisposition.Parameters.FileNameAsterisk) || StringsKt.startsWith(str2, "utf-8''", true)) {
            return str2;
        }
        String str3 = str2;
        for (int i = 0; i < str3.length(); i++) {
            if (!CodecsKt.getATTRIBUTE_CHARACTERS().contains(Character.valueOf(str3.charAt(i)))) {
                return "utf-8''" + CodecsKt.percentEncode(str2, CodecsKt.getATTRIBUTE_CHARACTERS());
            }
        }
        return str2;
    }
}
