package androidx.media3.datasource.cronet;

import android.content.Context;
import org.chromium.net.CronetEngine;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class CronetEngineWrapper {
    private final CronetEngine cronetEngine;

    public CronetEngineWrapper(Context context) {
        this(context, null, false);
    }

    public CronetEngineWrapper(Context context, String str, boolean z) {
        this.cronetEngine = CronetUtil.buildCronetEngine(context, str, z);
    }

    public CronetEngineWrapper(CronetEngine cronetEngine) {
        this.cronetEngine = cronetEngine;
    }

    CronetEngine getCronetEngine() {
        return this.cronetEngine;
    }
}
