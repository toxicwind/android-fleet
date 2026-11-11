package app.cash.zipline.internal.bridge;

import androidx.core.app.NotificationCompat;
import app.cash.zipline.ZiplineService;
import j$.util.DesugarCollections;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: leakCanaryJni.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\b\u0010\n\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\"2\u0010\f\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e\u0018\u00010\u00100\rX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"trackLeaks", "", "endpoint", "Lapp/cash/zipline/internal/bridge/Endpoint;", "serviceName", "", "callHandler", "Lapp/cash/zipline/internal/bridge/OutboundCallHandler;", NotificationCompat.CATEGORY_SERVICE, "Lapp/cash/zipline/ZiplineService;", "detectLeaks", "stopTrackingLeaks", "allReferencesSet", "", "Lapp/cash/zipline/internal/bridge/ZiplineServiceReference;", "kotlin.jvm.PlatformType", "", "allReferencesQueue", "Ljava/lang/ref/ReferenceQueue;", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LeakCanaryJniKt {
    private static final Set<ZiplineServiceReference> allReferencesSet = DesugarCollections.synchronizedSet(new LinkedHashSet());
    private static final ReferenceQueue<ZiplineService> allReferencesQueue = new ReferenceQueue<>();

    public static final void trackLeaks(Endpoint endpoint, String serviceName, OutboundCallHandler callHandler, ZiplineService service) {
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        Intrinsics.checkNotNullParameter(callHandler, "callHandler");
        Intrinsics.checkNotNullParameter(service, "service");
        Set<ZiplineServiceReference> allReferencesSet2 = allReferencesSet;
        Intrinsics.checkNotNullExpressionValue(allReferencesSet2, "allReferencesSet");
        allReferencesSet2.add(new ZiplineServiceReference(endpoint, serviceName, callHandler, service));
    }

    public static final void detectLeaks() {
        while (true) {
            Reference<? extends ZiplineService> referencePoll = allReferencesQueue.poll();
            if (referencePoll == null) {
                return;
            } else {
                ((ZiplineServiceReference) referencePoll).afterGc();
            }
        }
    }

    public static final void stopTrackingLeaks(final Endpoint endpoint) {
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Set<ZiplineServiceReference> allReferencesSet2 = allReferencesSet;
        Intrinsics.checkNotNullExpressionValue(allReferencesSet2, "allReferencesSet");
        CollectionsKt.removeAll(allReferencesSet2, new Function1() { // from class: app.cash.zipline.internal.bridge.LeakCanaryJniKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(Intrinsics.areEqual(((ZiplineServiceReference) obj).getEndpoint(), endpoint));
            }
        });
    }
}
