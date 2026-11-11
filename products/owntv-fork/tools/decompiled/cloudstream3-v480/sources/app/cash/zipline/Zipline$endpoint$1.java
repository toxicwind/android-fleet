package app.cash.zipline;

import androidx.core.app.NotificationCompat;
import app.cash.zipline.internal.bridge.CallChannel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: Zipline.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH\u0016R\u001b\u0010\u0002\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\r"}, d2 = {"app/cash/zipline/Zipline$endpoint$1", "Lapp/cash/zipline/internal/bridge/CallChannel;", "jsInboundBridge", "getJsInboundBridge", "()Lapp/cash/zipline/internal/bridge/CallChannel;", "jsInboundBridge$delegate", "Lkotlin/Lazy;", NotificationCompat.CATEGORY_CALL, "", "callJson", "disconnect", "", "instanceName", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Zipline$endpoint$1 implements CallChannel {

    /* JADX INFO: renamed from: jsInboundBridge$delegate, reason: from kotlin metadata */
    private final Lazy jsInboundBridge;
    final /* synthetic */ Zipline this$0;

    Zipline$endpoint$1(final Zipline zipline) {
        this.this$0 = zipline;
        this.jsInboundBridge = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: app.cash.zipline.Zipline$endpoint$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return zipline.getQuickJs().getInboundChannel$zipline_release();
            }
        });
    }

    private final CallChannel getJsInboundBridge() {
        return (CallChannel) this.jsInboundBridge.getValue();
    }

    @Override // app.cash.zipline.internal.bridge.CallChannel
    public String call(String callJson) {
        Intrinsics.checkNotNullParameter(callJson, "callJson");
        if (!CoroutineScopeKt.isActive(this.this$0.scope)) {
            throw new IllegalStateException("Zipline closed".toString());
        }
        return getJsInboundBridge().call(callJson);
    }

    @Override // app.cash.zipline.internal.bridge.CallChannel
    public boolean disconnect(String instanceName) {
        Intrinsics.checkNotNullParameter(instanceName, "instanceName");
        return getJsInboundBridge().disconnect(instanceName);
    }
}
