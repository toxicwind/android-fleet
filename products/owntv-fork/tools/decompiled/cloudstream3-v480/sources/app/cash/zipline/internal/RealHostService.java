package app.cash.zipline.internal;

import app.cash.zipline.EventListener;
import app.cash.zipline.Zipline;
import app.cash.zipline.ZiplineService;
import app.cash.zipline.internal.bridge.Endpoint;
import app.cash.zipline.internal.bridge.SerializableZiplineServiceType;
import io.ktor.http.ContentType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RealHostService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0016J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\"\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006 "}, d2 = {"Lapp/cash/zipline/internal/RealHostService;", "Lapp/cash/zipline/internal/HostService;", "endpoint", "Lapp/cash/zipline/internal/bridge/Endpoint;", "zipline", "Lapp/cash/zipline/Zipline;", "eventListener", "Lapp/cash/zipline/EventListener;", "eventLoop", "Lapp/cash/zipline/internal/CoroutineEventLoop;", "<init>", "(Lapp/cash/zipline/internal/bridge/Endpoint;Lapp/cash/zipline/Zipline;Lapp/cash/zipline/EventListener;Lapp/cash/zipline/internal/CoroutineEventLoop;)V", "serviceNames", "", "", "getServiceNames", "()Ljava/util/Set;", "serviceType", "Lapp/cash/zipline/internal/bridge/SerializableZiplineServiceType;", "name", "setTimeout", "", "timeoutId", "", "delayMillis", "clearTimeout", "log", "level", ContentType.Message.TYPE, "throwable", "", "serviceLeaked", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RealHostService implements HostService {
    private final Endpoint endpoint;
    private final EventListener eventListener;
    private final CoroutineEventLoop eventLoop;
    private final Zipline zipline;

    public RealHostService(Endpoint endpoint, Zipline zipline, EventListener eventListener, CoroutineEventLoop eventLoop) {
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(zipline, "zipline");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        Intrinsics.checkNotNullParameter(eventLoop, "eventLoop");
        this.endpoint = endpoint;
        this.zipline = zipline;
        this.eventListener = eventListener;
        this.eventLoop = eventLoop;
    }

    @Override // app.cash.zipline.ZiplineService, java.lang.AutoCloseable
    public /* bridge */ void close() {
        ZiplineService.CC.$default$close(this);
    }

    @Override // app.cash.zipline.internal.EndpointService
    public Set<String> getServiceNames() {
        return this.endpoint.getServiceNames();
    }

    @Override // app.cash.zipline.internal.EndpointService
    public SerializableZiplineServiceType serviceType(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.endpoint.serviceType(name);
    }

    @Override // app.cash.zipline.internal.HostService
    public void setTimeout(int timeoutId, int delayMillis) {
        this.eventLoop.setTimeout(timeoutId, delayMillis);
    }

    @Override // app.cash.zipline.internal.HostService
    public void clearTimeout(int timeoutId) {
        this.eventLoop.clearTimeout(timeoutId);
    }

    @Override // app.cash.zipline.internal.HostService
    public void log(String level, String message, Throwable throwable) {
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(message, "message");
        LogAndroidKt.log(level, message, throwable);
    }

    @Override // app.cash.zipline.internal.HostService
    public void serviceLeaked(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.eventListener.serviceLeaked(this.zipline, name);
    }
}
