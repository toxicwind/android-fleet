package com.fleeksoft.ksoup.ported;

import kotlin.Metadata;

/* JADX INFO: compiled from: CodePoint.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\f\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0003¨\u0006\u0004"}, d2 = {"toCodePoint", "Lcom/fleeksoft/ksoup/ported/CodePoint;", "", "", "ksoup_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CodePointKt {
    public static final CodePoint toCodePoint(int i) {
        return new CodePoint(i);
    }

    public static final CodePoint toCodePoint(char c) {
        return new CodePoint(c);
    }
}
