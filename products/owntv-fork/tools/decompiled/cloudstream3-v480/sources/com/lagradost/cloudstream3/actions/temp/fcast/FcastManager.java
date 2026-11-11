package com.lagradost.cloudstream3.actions.temp.fcast;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Build;
import android.os.ext.SdkExtensions;
import android.util.Log;
import com.lagradost.cloudstream3.CommonActivity$$ExternalSyntheticApiModelOutline0;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.Coroutines;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: FcastManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0002\u0012\u0013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/FcastManager;", "", "<init>", "()V", "nsdManager", "Landroid/net/nsd/NsdManager;", "registrationListenerTcp", "Lcom/lagradost/cloudstream3/actions/temp/fcast/FcastManager$Companion$DefaultRegistrationListener;", "getDeviceName", "", "init", "Lkotlinx/coroutines/Job;", "context", "Landroid/content/Context;", "registerReceiver", "", "stop", "", "DefaultDiscoveryListener", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class FcastManager {
    public static final String APP_PREFIX = "CloudStream";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int TCP_PORT = 46899;
    private static final List<PublicDeviceInfo> _currentDevices;
    private static final List<PublicDeviceInfo> currentDevices;
    private NsdManager nsdManager;
    private final Companion.DefaultRegistrationListener registrationListenerTcp = new Companion.DefaultRegistrationListener();

    /* JADX INFO: Access modifiers changed from: private */
    public final String getDeviceName() {
        return Build.MANUFACTURER + '-' + Build.MODEL;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.actions.temp.fcast.FcastManager$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: FcastManager.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/actions/temp/fcast/FcastManager;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.actions.temp.fcast.FcastManager$init$1", f = "FcastManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, FcastManager, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ boolean $registerReceiver;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, boolean z, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$context = context;
            this.$registerReceiver = z;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, FcastManager fcastManager, Continuation<? super Unit> continuation) {
            return FcastManager.this.new AnonymousClass1(this.$context, this.$registerReceiver, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            FcastManager fcastManager = FcastManager.this;
            Object systemService = this.$context.getSystemService("servicediscovery");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.nsd.NsdManager");
            fcastManager.nsdManager = (NsdManager) systemService;
            if (this.$registerReceiver) {
                String str = "CloudStream-" + FcastManager.this.getDeviceName();
                NsdServiceInfo nsdServiceInfo = new NsdServiceInfo();
                nsdServiceInfo.setServiceName(str);
                nsdServiceInfo.setServiceType("_fcast._tcp");
                nsdServiceInfo.setPort(FcastManager.TCP_PORT);
                NsdManager nsdManager = FcastManager.this.nsdManager;
                if (nsdManager != null) {
                    nsdManager.registerService(nsdServiceInfo, 1, FcastManager.this.registrationListenerTcp);
                }
            }
            NsdManager nsdManager2 = FcastManager.this.nsdManager;
            if (nsdManager2 != null) {
                nsdManager2.discoverServices("_fcast._tcp", 1, FcastManager.this.new DefaultDiscoveryListener());
            }
            return Unit.INSTANCE;
        }
    }

    public final Job init(Context context, boolean registerReceiver) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(context, registerReceiver, null));
    }

    public final void stop() {
        NsdManager nsdManager = this.nsdManager;
        if (nsdManager != null) {
            nsdManager.unregisterService(this.registrationListenerTcp);
        }
    }

    /* JADX INFO: compiled from: FcastManager.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001a\u0010\r\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\u000e\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u000f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/FcastManager$DefaultDiscoveryListener;", "Landroid/net/nsd/NsdManager$DiscoveryListener;", "<init>", "(Lcom/lagradost/cloudstream3/actions/temp/fcast/FcastManager;)V", "tag", "", "getTag", "()Ljava/lang/String;", "onStartDiscoveryFailed", "", "serviceType", "errorCode", "", "onStopDiscoveryFailed", "onDiscoveryStarted", "onDiscoveryStopped", "onServiceFound", "serviceInfo", "Landroid/net/nsd/NsdServiceInfo;", "onServiceLost", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public final class DefaultDiscoveryListener implements NsdManager.DiscoveryListener {
        private final String tag = "DiscoveryListener";

        public DefaultDiscoveryListener() {
        }

        public final String getTag() {
            return this.tag;
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onStartDiscoveryFailed(String serviceType, int errorCode) {
            Log.d(this.tag, "Discovery failed: " + serviceType + ", error code: " + errorCode);
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onStopDiscoveryFailed(String serviceType, int errorCode) {
            Log.d(this.tag, "Stop discovery failed: " + serviceType + ", error code: " + errorCode);
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onDiscoveryStarted(String serviceType) {
            Log.d(this.tag, "Discovery started: " + serviceType);
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onDiscoveryStopped(String serviceType) {
            Log.d(this.tag, "Discovery stopped: " + serviceType);
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onServiceFound(final NsdServiceInfo serviceInfo) {
            final FcastManager fcastManager = FcastManager.this;
            ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.actions.temp.fcast.FcastManager$DefaultDiscoveryListener$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return FcastManager.DefaultDiscoveryListener.onServiceFound$lambda$0(serviceInfo, fcastManager, this);
                }
            });
        }

        static final Unit onServiceFound$lambda$0(NsdServiceInfo nsdServiceInfo, FcastManager fcastManager, final DefaultDiscoveryListener defaultDiscoveryListener) {
            if (nsdServiceInfo == null) {
                return Unit.INSTANCE;
            }
            if (Build.VERSION.SDK_INT < 30 || SdkExtensions.getExtensionVersion(33) < 7) {
                NsdManager nsdManager = fcastManager.nsdManager;
                if (nsdManager != null) {
                    nsdManager.resolveService(nsdServiceInfo, new NsdManager.ResolveListener() { // from class: com.lagradost.cloudstream3.actions.temp.fcast.FcastManager$DefaultDiscoveryListener$onServiceFound$1$3
                        @Override // android.net.nsd.NsdManager.ResolveListener
                        public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
                        }

                        @Override // android.net.nsd.NsdManager.ResolveListener
                        public void onServiceResolved(NsdServiceInfo serviceInfo) {
                            if (serviceInfo == null) {
                                return;
                            }
                            synchronized (FcastManager._currentDevices) {
                                FcastManager._currentDevices.add(new PublicDeviceInfo(serviceInfo));
                            }
                            Log.d(this.this$0.getTag(), "Service found: " + serviceInfo.getServiceName() + ", Net: " + serviceInfo.getHost().getHostAddress());
                        }
                    });
                }
            } else {
                NsdManager nsdManager2 = fcastManager.nsdManager;
                if (nsdManager2 != null) {
                    nsdManager2.registerServiceInfoCallback(nsdServiceInfo, new Executor() { // from class: com.lagradost.cloudstream3.actions.temp.fcast.FcastManager$DefaultDiscoveryListener$$ExternalSyntheticLambda3
                        @Override // java.util.concurrent.Executor
                        public final void execute(Runnable runnable) {
                            runnable.run();
                        }
                    }, CommonActivity$$ExternalSyntheticApiModelOutline0.m8388m((Object) new FcastManager$DefaultDiscoveryListener$onServiceFound$1$2(defaultDiscoveryListener, nsdServiceInfo)));
                }
            }
            return Unit.INSTANCE;
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onServiceLost(final NsdServiceInfo serviceInfo) {
            if (serviceInfo == null) {
                return;
            }
            synchronized (FcastManager._currentDevices) {
                CollectionsKt.removeAll(FcastManager._currentDevices, new Function1() { // from class: com.lagradost.cloudstream3.actions.temp.fcast.FcastManager$DefaultDiscoveryListener$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(FcastManager.DefaultDiscoveryListener.onServiceLost$lambda$0$0(serviceInfo, (PublicDeviceInfo) obj));
                    }
                });
            }
            Log.d(this.tag, "Service lost: " + serviceInfo.getServiceName());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean onServiceLost$lambda$0$0(NsdServiceInfo nsdServiceInfo, PublicDeviceInfo it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Intrinsics.areEqual(it.getRawName(), nsdServiceInfo.getServiceName());
        }
    }

    /* JADX INFO: compiled from: FcastManager.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u000fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/FcastManager$Companion;", "", "<init>", "()V", "APP_PREFIX", "", "_currentDevices", "", "Lcom/lagradost/cloudstream3/actions/temp/fcast/PublicDeviceInfo;", "currentDevices", "", "getCurrentDevices", "()Ljava/util/List;", "TCP_PORT", "", "DefaultRegistrationListener", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<PublicDeviceInfo> getCurrentDevices() {
            return FcastManager.currentDevices;
        }

        /* JADX INFO: compiled from: FcastManager.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/FcastManager$Companion$DefaultRegistrationListener;", "Landroid/net/nsd/NsdManager$RegistrationListener;", "<init>", "()V", "tag", "", "getTag", "()Ljava/lang/String;", "onServiceRegistered", "", "serviceInfo", "Landroid/net/nsd/NsdServiceInfo;", "onRegistrationFailed", "errorCode", "", "onServiceUnregistered", "onUnregistrationFailed", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class DefaultRegistrationListener implements NsdManager.RegistrationListener {
            private final String tag = "DiscoveryService";

            public final String getTag() {
                return this.tag;
            }

            @Override // android.net.nsd.NsdManager.RegistrationListener
            public void onServiceRegistered(NsdServiceInfo serviceInfo) {
                Intrinsics.checkNotNullParameter(serviceInfo, "serviceInfo");
                Log.d(this.tag, "Service registered: " + serviceInfo.getServiceName());
            }

            @Override // android.net.nsd.NsdManager.RegistrationListener
            public void onRegistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
                Intrinsics.checkNotNullParameter(serviceInfo, "serviceInfo");
                Log.e(this.tag, "Service registration failed: errorCode=" + errorCode);
            }

            @Override // android.net.nsd.NsdManager.RegistrationListener
            public void onServiceUnregistered(NsdServiceInfo serviceInfo) {
                Intrinsics.checkNotNullParameter(serviceInfo, "serviceInfo");
                Log.d(this.tag, "Service unregistered: " + serviceInfo.getServiceName());
            }

            @Override // android.net.nsd.NsdManager.RegistrationListener
            public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
                Intrinsics.checkNotNullParameter(serviceInfo, "serviceInfo");
                Log.e(this.tag, "Service unregistration failed: errorCode=" + errorCode);
            }
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        _currentDevices = arrayList;
        currentDevices = arrayList;
    }
}
