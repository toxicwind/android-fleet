package com.fleeksoft.ksoup.ported;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import kotlin.Metadata;

/* JADX INFO: compiled from: Assert.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0000¨\u0006\u0006"}, d2 = {"assert", "", "condition", "", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "", "ksoup_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class AssertKt {
    public static /* synthetic */ void assert$default(boolean z, String str, int i, Object obj) throws Exception {
        if ((i & 2) != 0) {
            str = null;
        }
        m8181assert(z, str);
    }

    /* JADX INFO: renamed from: assert, reason: not valid java name */
    public static final void m8181assert(boolean z, String str) throws Exception {
        if (z) {
            return;
        }
        if (str == null) {
            str = "Assert error!";
        }
        throw new Exception(str);
    }
}
