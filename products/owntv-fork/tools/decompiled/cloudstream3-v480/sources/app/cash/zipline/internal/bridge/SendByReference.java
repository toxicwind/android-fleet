package app.cash.zipline.internal.bridge;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: passByReference.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Lapp/cash/zipline/internal/bridge/SendByReference;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "Lapp/cash/zipline/internal/bridge/PassByReference;", NotificationCompat.CATEGORY_SERVICE, "adapter", "Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;", "<init>", "(Lapp/cash/zipline/ZiplineService;Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;)V", "getService", "()Lapp/cash/zipline/ZiplineService;", "Lapp/cash/zipline/ZiplineService;", "getAdapter", "()Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;", "bind", "", "endpoint", "Lapp/cash/zipline/internal/bridge/Endpoint;", "name", "", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SendByReference<T extends ZiplineService> implements PassByReference {
    private final ZiplineServiceAdapter<T> adapter;
    private final T service;

    public SendByReference(T service, ZiplineServiceAdapter<T> adapter) {
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        this.service = service;
        this.adapter = adapter;
    }

    public final T getService() {
        return this.service;
    }

    public final ZiplineServiceAdapter<T> getAdapter() {
        return this.adapter;
    }

    public final void bind(Endpoint endpoint, String name) {
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(name, "name");
        endpoint.bind(name, this.service, this.adapter);
    }
}
