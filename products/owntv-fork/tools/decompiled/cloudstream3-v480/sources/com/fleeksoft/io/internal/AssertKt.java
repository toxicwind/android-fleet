package com.fleeksoft.io.internal;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Assert.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0006¨\u0006\t"}, d2 = {"assert", "", ES6Iterator.VALUE_PROPERTY, "", "lazyMessage", "Lkotlin/Function0;", "", "condition", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "io-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AssertKt {
    /* JADX INFO: renamed from: assert, reason: not valid java name */
    public static final void m8175assert(boolean z, Function0<String> function0) {
    }

    public static /* synthetic */ void assert$default(boolean z, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        m8175assert(z, (Function0<String>) function0);
    }

    /* JADX INFO: renamed from: assert, reason: not valid java name */
    public static final void m8174assert(boolean z, String str) throws Exception {
        if (z) {
            return;
        }
        if (str == null) {
            str = "Assert error!";
        }
        throw new Exception(str);
    }
}
