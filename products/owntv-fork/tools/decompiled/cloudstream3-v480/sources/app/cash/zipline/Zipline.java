package app.cash.zipline;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.internal.CoroutineEventLoop;
import app.cash.zipline.internal.EventListenerAdapter;
import app.cash.zipline.internal.GuestService;
import app.cash.zipline.internal.HostService;
import app.cash.zipline.internal.PlatformKt;
import app.cash.zipline.internal.QuickJsExtensionsKt;
import app.cash.zipline.internal.RealHostService;
import app.cash.zipline.internal.bridge.Endpoint;
import app.cash.zipline.internal.bridge.InboundService;
import app.cash.zipline.internal.bridge.LeakCanaryJniKt;
import app.cash.zipline.internal.bridge.ThrowablesKt;
import app.cash.zipline.internal.bridge.ZiplineServiceAdapter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClasses;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuildersKt;

/* JADX INFO: compiled from: Zipline.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 D2\u00060\u0001j\u0002`\u0002:\u0001DB1\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ%\u0010*\u001a\u00020+\"\b\b\u0000\u0010,*\u00020-2\u0006\u0010.\u001a\u00020\u001f2\u0006\u0010/\u001a\u0002H,¢\u0006\u0002\u00100J5\u0010*\u001a\u00020+\"\b\b\u0000\u0010,*\u00020-2\u0006\u0010.\u001a\u00020\u001f2\u0006\u00101\u001a\u0002H,2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H,03H\u0001¢\u0006\u0002\u00104J'\u00105\u001a\u0002H,\"\b\b\u0000\u0010,*\u00020-2\u0006\u0010.\u001a\u00020\u001f2\b\b\u0002\u0010\t\u001a\u000206¢\u0006\u0002\u00107J7\u00105\u001a\u0002H,\"\b\b\u0000\u0010,*\u00020-2\u0006\u0010.\u001a\u00020\u001f2\b\b\u0002\u0010\t\u001a\u0002062\f\u00102\u001a\b\u0012\u0004\u0012\u0002H,03H\u0001¢\u0006\u0002\u00108J\b\u00109\u001a\u00020+H\u0016J\u0016\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u00020\u001f2\u0006\u0010<\u001a\u00020\u001fJ\u0016\u0010:\u001a\u00020+2\u0006\u0010=\u001a\u00020>2\u0006\u0010<\u001a\u00020\u001fJ1\u0010?\u001a\u0002H,\"\b\b\u0000\u0010,*\u00020)2\f\u0010@\u001a\b\u0012\u0004\u0012\u0002H,0(2\f\u0010A\u001a\b\u0012\u0004\u0012\u0002H,0B¢\u0006\u0002\u0010CR\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0019\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b#\u0010!R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010&\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030(\u0012\u0004\u0012\u00020)0'X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Lapp/cash/zipline/Zipline;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "quickJs", "Lapp/cash/zipline/QuickJs;", "userSerializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "eventListener", "Lapp/cash/zipline/EventListener;", "<init>", "(Lapp/cash/zipline/QuickJs;Lkotlinx/serialization/modules/SerializersModule;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineScope;Lapp/cash/zipline/EventListener;)V", "getQuickJs$annotations", "()V", "getQuickJs", "()Lapp/cash/zipline/QuickJs;", "getEventListener", "()Lapp/cash/zipline/EventListener;", "endpoint", "Lapp/cash/zipline/internal/bridge/Endpoint;", "guest", "Lapp/cash/zipline/internal/GuestService;", "json", "Lkotlinx/serialization/json/Json;", "getJson", "()Lkotlinx/serialization/json/Json;", "serviceNames", "", "", "getServiceNames$zipline_release", "()Ljava/util/Set;", "clientNames", "getClientNames$zipline_release", "closed", "", "attachments", "", "Lkotlin/reflect/KClass;", "", "bind", "", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "name", "instance", "(Ljava/lang/String;Lapp/cash/zipline/ZiplineService;)V", NotificationCompat.CATEGORY_SERVICE, "adapter", "Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;", "(Ljava/lang/String;Lapp/cash/zipline/ZiplineService;Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;)V", "take", "Lapp/cash/zipline/ZiplineScope;", "(Ljava/lang/String;Lapp/cash/zipline/ZiplineScope;)Lapp/cash/zipline/ZiplineService;", "(Ljava/lang/String;Lapp/cash/zipline/ZiplineScope;Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;)Lapp/cash/zipline/ZiplineService;", "close", "loadJsModule", "script", "id", "bytecode", "", "getOrPutAttachment", "key", "compute", "Lkotlin/Function0;", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Companion", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Zipline implements AutoCloseable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<KClass<?>, Object> attachments;
    private boolean closed;
    private final Endpoint endpoint;
    private final EventListener eventListener;
    private final GuestService guest;
    private final QuickJs quickJs;
    private final CoroutineScope scope;

    public /* synthetic */ Zipline(QuickJs quickJs, SerializersModule serializersModule, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope, EventListener eventListener, DefaultConstructorMarker defaultConstructorMarker) {
        this(quickJs, serializersModule, coroutineDispatcher, coroutineScope, eventListener);
    }

    @EngineApi
    public static /* synthetic */ void getQuickJs$annotations() {
    }

    public static /* synthetic */ ZiplineService take$default(Zipline zipline, String str, ZiplineScope ziplineScope, int i, Object obj) {
        if ((i & 2) != 0) {
            ziplineScope = new ZiplineScope();
        }
        return zipline.take(str, ziplineScope);
    }

    private Zipline(QuickJs quickJs, SerializersModule serializersModule, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope, EventListener eventListener) {
        this.quickJs = quickJs;
        this.scope = coroutineScope;
        this.eventListener = eventListener;
        Endpoint endpoint = new Endpoint(coroutineScope, serializersModule, new EventListenerAdapter(eventListener, this), new Zipline$endpoint$1(this), new Function0() { // from class: app.cash.zipline.Zipline$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.guest;
            }
        });
        this.endpoint = endpoint;
        GuestService guestService = (GuestService) Endpoint.take$default(endpoint, PlatformKt.ZIPLINE_GUEST_NAME, null, new GuestService.Companion.Adapter(CollectionsKt.listOf((Object[]) new KSerializer[0]), "app.cash.zipline.internal.GuestService"), 2, null);
        this.guest = guestService;
        this.attachments = new LinkedHashMap();
        quickJs.initOutboundChannel$zipline_release(endpoint.getInboundChannel());
        endpoint.bind(PlatformKt.ZIPLINE_HOST_NAME, new RealHostService(endpoint, this, eventListener, new CoroutineEventLoop(coroutineDispatcher, coroutineScope, guestService)), new HostService.Companion.Adapter(CollectionsKt.listOf((Object[]) new KSerializer[0]), "app.cash.zipline.internal.HostService"));
    }

    public final QuickJs getQuickJs() {
        return this.quickJs;
    }

    public final EventListener getEventListener() {
        return this.eventListener;
    }

    public final Json getJson() {
        return this.endpoint.getJson();
    }

    public final Set<String> getServiceNames$zipline_release() {
        return this.endpoint.getServiceNames();
    }

    public final Set<String> getClientNames$zipline_release() {
        return this.guest.getServiceNames();
    }

    public final <T extends ZiplineService> void bind(String name, T instance) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(instance, "instance");
        throw new IllegalStateException("unexpected call to Zipline.bind: is the Zipline plugin configured?".toString());
    }

    public final <T extends ZiplineService> void bind(String name, T service, ZiplineServiceAdapter<T> adapter) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        if (!CoroutineScopeKt.isActive(this.scope)) {
            throw new IllegalStateException("closed".toString());
        }
        this.endpoint.bind(name, service, adapter);
    }

    public final <T extends ZiplineService> T take(String name, ZiplineScope scope) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(scope, "scope");
        throw new IllegalStateException("unexpected call to Zipline.take: is the Zipline plugin configured?".toString());
    }

    public static /* synthetic */ ZiplineService take$default(Zipline zipline, String str, ZiplineScope ziplineScope, ZiplineServiceAdapter ziplineServiceAdapter, int i, Object obj) {
        if ((i & 2) != 0) {
            ziplineScope = new ZiplineScope();
        }
        return zipline.take(str, ziplineScope, ziplineServiceAdapter);
    }

    public final <T extends ZiplineService> T take(String name, ZiplineScope scope, ZiplineServiceAdapter<T> adapter) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        if (!CoroutineScopeKt.isActive(this.scope)) {
            throw new IllegalStateException("closed".toString());
        }
        return (T) this.endpoint.take(name, scope, adapter);
    }

    @Override // java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.closed) {
            return;
        }
        this.closed = true;
        CoroutineScopeKt.cancel(this.scope, ThrowablesKt.getTheOnlyCancellationException());
        InboundService[] inboundServiceArr = (InboundService[]) this.endpoint.getInboundServices$zipline_release().values().toArray(new InboundService[0]);
        this.endpoint.getInboundServices$zipline_release().clear();
        Throwable th = null;
        for (InboundService inboundService : inboundServiceArr) {
            try {
                inboundService.getService$zipline_release().close();
            } catch (Throwable th2) {
                if (th != null) {
                    th = th2;
                }
            }
        }
        this.quickJs.close();
        for (Continuation<?> continuation : this.endpoint.getIncompleteContinuations$zipline_release()) {
            Result.Companion companion = Result.INSTANCE;
            continuation.resumeWith(Result.m8936constructorimpl(ResultKt.createFailure(new CancellationException("Zipline closed"))));
        }
        this.endpoint.getIncompleteContinuations$zipline_release().clear();
        LeakCanaryJniKt.stopTrackingLeaks(this.endpoint);
        this.eventListener.ziplineClosed(this);
        if (th != null) {
            throw th;
        }
    }

    public final void loadJsModule(String script, String id) {
        Intrinsics.checkNotNullParameter(script, "script");
        Intrinsics.checkNotNullParameter(id, "id");
        QuickJsExtensionsKt.loadJsModule(this.quickJs, script, id);
    }

    public final void loadJsModule(byte[] bytecode, String id) {
        Intrinsics.checkNotNullParameter(bytecode, "bytecode");
        Intrinsics.checkNotNullParameter(id, "id");
        QuickJsExtensionsKt.loadJsModule(this.quickJs, id, bytecode);
    }

    public final <T> T getOrPutAttachment(KClass<T> key, Function0<? extends T> compute) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(compute, "compute");
        Map<KClass<?>, Object> map = this.attachments;
        Object objInvoke = map.get(key);
        if (objInvoke == null) {
            objInvoke = compute.invoke();
            map.put(key, objInvoke);
        }
        return (T) KClasses.cast(key, objInvoke);
    }

    /* JADX INFO: compiled from: Zipline.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lapp/cash/zipline/Zipline$Companion;", "", "<init>", "()V", "create", "Lapp/cash/zipline/Zipline;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "eventListener", "Lapp/cash/zipline/EventListener;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ Zipline create$default(Companion companion, CoroutineDispatcher coroutineDispatcher, SerializersModule serializersModule, EventListener eventListener, int i, Object obj) {
            if ((i & 2) != 0) {
                serializersModule = SerializersModuleBuildersKt.EmptySerializersModule();
            }
            if ((i & 4) != 0) {
                eventListener = EventListener.INSTANCE.getNONE();
            }
            return companion.create(coroutineDispatcher, serializersModule, eventListener);
        }

        public final Zipline create(CoroutineDispatcher dispatcher, SerializersModule serializersModule, EventListener eventListener) {
            Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
            Intrinsics.checkNotNullParameter(serializersModule, "serializersModule");
            Intrinsics.checkNotNullParameter(eventListener, "eventListener");
            QuickJs quickJsCreate = QuickJs.INSTANCE.create();
            quickJsCreate.setMaxStackSize(6291456L);
            QuickJsExtensionsKt.initModuleLoader(quickJsCreate);
            Zipline zipline = new Zipline(quickJsCreate, serializersModule, dispatcher, CoroutineScopeKt.CoroutineScope(dispatcher), eventListener, null);
            eventListener.ziplineCreated(zipline);
            return zipline;
        }
    }
}
