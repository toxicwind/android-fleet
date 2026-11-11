package com.lagradost.cloudstream3.actions.temp.fcast;

import android.net.nsd.NsdManager$ServiceInfoCallback;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;
import j$.util.Collection;
import j$.util.function.Predicate$CC;
import java.net.InetAddress;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: FcastManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016¨\u0006\u000b"}, d2 = {"com/lagradost/cloudstream3/actions/temp/fcast/FcastManager$DefaultDiscoveryListener$onServiceFound$1$2", "Landroid/net/nsd/NsdManager$ServiceInfoCallback;", "onServiceInfoCallbackRegistrationFailed", "", "errorCode", "", "onServiceUpdated", "serviceInfo", "Landroid/net/nsd/NsdServiceInfo;", "onServiceLost", "onServiceInfoCallbackUnregistered", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class FcastManager$DefaultDiscoveryListener$onServiceFound$1$2 implements NsdManager$ServiceInfoCallback {
    final /* synthetic */ NsdServiceInfo $serviceInfo;
    final /* synthetic */ FcastManager.DefaultDiscoveryListener this$0;

    public void onServiceInfoCallbackUnregistered() {
    }

    FcastManager$DefaultDiscoveryListener$onServiceFound$1$2(FcastManager.DefaultDiscoveryListener defaultDiscoveryListener, NsdServiceInfo nsdServiceInfo) {
        this.this$0 = defaultDiscoveryListener;
        this.$serviceInfo = nsdServiceInfo;
    }

    public void onServiceInfoCallbackRegistrationFailed(int errorCode) {
        Log.e(this.this$0.getTag(), "Service registration failed: " + errorCode);
    }

    public void onServiceUpdated(final NsdServiceInfo serviceInfo) {
        Intrinsics.checkNotNullParameter(serviceInfo, "serviceInfo");
        String tag = this.this$0.getTag();
        StringBuilder sb = new StringBuilder("Service updated: ");
        sb.append(serviceInfo.getServiceName());
        sb.append(",Net: ");
        List hostAddresses = serviceInfo.getHostAddresses();
        Intrinsics.checkNotNullExpressionValue(hostAddresses, "getHostAddresses(...)");
        InetAddress inetAddress = (InetAddress) CollectionsKt.firstOrNull(hostAddresses);
        sb.append(inetAddress != null ? inetAddress.getHostAddress() : null);
        Log.d(tag, sb.toString());
        synchronized (FcastManager._currentDevices) {
            List list = FcastManager._currentDevices;
            final Function1 function1 = new Function1() { // from class: com.lagradost.cloudstream3.actions.temp.fcast.FcastManager$DefaultDiscoveryListener$onServiceFound$1$2$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(FcastManager$DefaultDiscoveryListener$onServiceFound$1$2.onServiceUpdated$lambda$0$0(serviceInfo, (PublicDeviceInfo) obj));
                }
            };
            Collection.EL.removeIf(list, new Predicate() { // from class: com.lagradost.cloudstream3.actions.temp.fcast.FcastManager$DefaultDiscoveryListener$onServiceFound$1$2$$ExternalSyntheticLambda4
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate$CC.$default$and(this, predicate);
                }

                public /* synthetic */ Predicate negate() {
                    return Predicate$CC.$default$negate(this);
                }

                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate$CC.$default$or(this, predicate);
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return FcastManager$DefaultDiscoveryListener$onServiceFound$1$2.onServiceUpdated$lambda$0$1(function1, obj);
                }
            });
            FcastManager._currentDevices.add(new PublicDeviceInfo(serviceInfo));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onServiceUpdated$lambda$0$0(NsdServiceInfo nsdServiceInfo, PublicDeviceInfo it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Intrinsics.areEqual(it.getRawName(), nsdServiceInfo.getServiceName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onServiceUpdated$lambda$0$1(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    public void onServiceLost() {
        Log.d(this.this$0.getTag(), "Service lost: " + this.$serviceInfo.getServiceName() + AbstractJsonLexerKt.COMMA);
        List list = FcastManager._currentDevices;
        final NsdServiceInfo nsdServiceInfo = this.$serviceInfo;
        synchronized (list) {
            List list2 = FcastManager._currentDevices;
            final Function1 function1 = new Function1() { // from class: com.lagradost.cloudstream3.actions.temp.fcast.FcastManager$DefaultDiscoveryListener$onServiceFound$1$2$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(FcastManager$DefaultDiscoveryListener$onServiceFound$1$2.onServiceLost$lambda$1$0(nsdServiceInfo, (PublicDeviceInfo) obj));
                }
            };
            Collection.EL.removeIf(list2, new Predicate() { // from class: com.lagradost.cloudstream3.actions.temp.fcast.FcastManager$DefaultDiscoveryListener$onServiceFound$1$2$$ExternalSyntheticLambda2
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate$CC.$default$and(this, predicate);
                }

                public /* synthetic */ Predicate negate() {
                    return Predicate$CC.$default$negate(this);
                }

                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate$CC.$default$or(this, predicate);
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return FcastManager$DefaultDiscoveryListener$onServiceFound$1$2.onServiceLost$lambda$1$1(function1, obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onServiceLost$lambda$1$0(NsdServiceInfo nsdServiceInfo, PublicDeviceInfo it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Intrinsics.areEqual(it.getRawName(), nsdServiceInfo.getServiceName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onServiceLost$lambda$1$1(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }
}
