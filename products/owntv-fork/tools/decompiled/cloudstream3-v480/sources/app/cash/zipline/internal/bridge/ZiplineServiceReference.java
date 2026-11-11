package app.cash.zipline.internal.bridge;

import androidx.core.app.NotificationCompat;
import app.cash.zipline.ZiplineService;
import java.lang.ref.PhantomReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: leakCanaryJni.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010\u000e\u001a\u00020\u000fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lapp/cash/zipline/internal/bridge/ZiplineServiceReference;", "Ljava/lang/ref/PhantomReference;", "Lapp/cash/zipline/ZiplineService;", "endpoint", "Lapp/cash/zipline/internal/bridge/Endpoint;", "serviceName", "", "callHandler", "Lapp/cash/zipline/internal/bridge/OutboundCallHandler;", NotificationCompat.CATEGORY_SERVICE, "<init>", "(Lapp/cash/zipline/internal/bridge/Endpoint;Ljava/lang/String;Lapp/cash/zipline/internal/bridge/OutboundCallHandler;Lapp/cash/zipline/ZiplineService;)V", "getEndpoint", "()Lapp/cash/zipline/internal/bridge/Endpoint;", "afterGc", "", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class ZiplineServiceReference extends PhantomReference<ZiplineService> {
    private final OutboundCallHandler callHandler;
    private final Endpoint endpoint;
    private final String serviceName;

    public final Endpoint getEndpoint() {
        return this.endpoint;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ZiplineServiceReference(Endpoint endpoint, String serviceName, OutboundCallHandler callHandler, ZiplineService service) {
        super(service, LeakCanaryJniKt.allReferencesQueue);
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        Intrinsics.checkNotNullParameter(callHandler, "callHandler");
        Intrinsics.checkNotNullParameter(service, "service");
        this.endpoint = endpoint;
        this.serviceName = serviceName;
        this.callHandler = callHandler;
    }

    public final void afterGc() {
        LeakCanaryJniKt.allReferencesSet.remove(this);
        if (this.callHandler.getServiceState().getClosed()) {
            return;
        }
        this.endpoint.getEventListener().serviceLeaked(this.serviceName);
    }
}
