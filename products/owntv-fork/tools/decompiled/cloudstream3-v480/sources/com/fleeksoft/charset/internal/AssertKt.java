package com.fleeksoft.charset.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Assert.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¨\u0006\u0007"}, d2 = {"assert", "", ES6Iterator.VALUE_PROPERTY, "", "lazyMessage", "Lkotlin/Function0;", "", "charset_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AssertKt {
    /* JADX INFO: renamed from: assert, reason: not valid java name */
    public static final void m8165assert(boolean z, Function0<String> function0) {
    }

    public static /* synthetic */ void assert$default(boolean z, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        m8165assert(z, function0);
    }
}
