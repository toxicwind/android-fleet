package app.cash.zipline.internal;

import androidx.core.app.NotificationCompat;
import app.cash.zipline.Call;
import app.cash.zipline.CallResult;
import app.cash.zipline.EventListener;
import app.cash.zipline.Zipline;
import app.cash.zipline.ZiplineService;
import app.cash.zipline.internal.bridge.Endpoint;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventListenerAdapter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\"\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0015H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001c"}, d2 = {"Lapp/cash/zipline/internal/EventListenerAdapter;", "Lapp/cash/zipline/internal/bridge/Endpoint$EventListener;", "delegate", "Lapp/cash/zipline/EventListener;", "zipline", "Lapp/cash/zipline/Zipline;", "<init>", "(Lapp/cash/zipline/EventListener;Lapp/cash/zipline/Zipline;)V", "getDelegate", "()Lapp/cash/zipline/EventListener;", "getZipline", "()Lapp/cash/zipline/Zipline;", "bindService", "", "name", "", NotificationCompat.CATEGORY_SERVICE, "Lapp/cash/zipline/ZiplineService;", "takeService", "serviceLeaked", "callStart", "", NotificationCompat.CATEGORY_CALL, "Lapp/cash/zipline/Call;", "callEnd", "result", "Lapp/cash/zipline/CallResult;", "startValue", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class EventListenerAdapter extends Endpoint.EventListener {
    private final EventListener delegate;
    private final Zipline zipline;

    public EventListenerAdapter(EventListener delegate, Zipline zipline) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(zipline, "zipline");
        this.delegate = delegate;
        this.zipline = zipline;
    }

    public final EventListener getDelegate() {
        return this.delegate;
    }

    public final Zipline getZipline() {
        return this.zipline;
    }

    @Override // app.cash.zipline.internal.bridge.Endpoint.EventListener
    public void bindService(String name, ZiplineService service) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(service, "service");
        this.delegate.bindService(this.zipline, name, service);
    }

    @Override // app.cash.zipline.internal.bridge.Endpoint.EventListener
    public void takeService(String name, ZiplineService service) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(service, "service");
        this.delegate.takeService(this.zipline, name, service);
    }

    @Override // app.cash.zipline.internal.bridge.Endpoint.EventListener
    public void serviceLeaked(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.delegate.serviceLeaked(this.zipline, name);
    }

    @Override // app.cash.zipline.internal.bridge.Endpoint.EventListener
    public Object callStart(Call call) {
        Intrinsics.checkNotNullParameter(call, "call");
        return this.delegate.callStart(this.zipline, call);
    }

    @Override // app.cash.zipline.internal.bridge.Endpoint.EventListener
    public void callEnd(Call call, CallResult result, Object startValue) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        this.delegate.callEnd(this.zipline, call, result, startValue);
    }
}
