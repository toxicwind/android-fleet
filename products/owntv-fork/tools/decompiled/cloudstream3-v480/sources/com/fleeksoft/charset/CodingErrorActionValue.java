package com.fleeksoft.charset;

import java.nio.charset.CodingErrorAction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CodingErrorAction.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\t\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0015\u0010\u000b\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lcom/fleeksoft/charset/CodingErrorActionValue;", "", "<init>", "()V", "IGNORE", "Ljava/nio/charset/CodingErrorAction;", "Lcom/fleeksoft/charset/CodingErrorAction;", "getIGNORE", "()Ljava/nio/charset/CodingErrorAction;", "REPLACE", "getREPLACE", "REPORT", "getREPORT", "charset_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CodingErrorActionValue {
    private static final CodingErrorAction IGNORE;
    public static final CodingErrorActionValue INSTANCE = new CodingErrorActionValue();
    private static final CodingErrorAction REPLACE;
    private static final CodingErrorAction REPORT;

    private CodingErrorActionValue() {
    }

    static {
        CodingErrorAction IGNORE2 = CodingErrorAction.IGNORE;
        Intrinsics.checkNotNullExpressionValue(IGNORE2, "IGNORE");
        IGNORE = IGNORE2;
        CodingErrorAction REPLACE2 = CodingErrorAction.REPLACE;
        Intrinsics.checkNotNullExpressionValue(REPLACE2, "REPLACE");
        REPLACE = REPLACE2;
        CodingErrorAction REPORT2 = CodingErrorAction.REPORT;
        Intrinsics.checkNotNullExpressionValue(REPORT2, "REPORT");
        REPORT = REPORT2;
    }

    public final CodingErrorAction getIGNORE() {
        return IGNORE;
    }

    public final CodingErrorAction getREPLACE() {
        return REPLACE;
    }

    public final CodingErrorAction getREPORT() {
        return REPORT;
    }
}
