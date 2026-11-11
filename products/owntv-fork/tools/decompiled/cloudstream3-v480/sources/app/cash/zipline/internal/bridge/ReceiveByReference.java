package app.cash.zipline.internal.bridge;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineScope;
import app.cash.zipline.ZiplineService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: passByReference.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J#\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0014¢\u0006\u0002\u0010\u0015R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lapp/cash/zipline/internal/bridge/ReceiveByReference;", "Lapp/cash/zipline/internal/bridge/PassByReference;", "name", "", "endpoint", "Lapp/cash/zipline/internal/bridge/Endpoint;", "<init>", "(Ljava/lang/String;Lapp/cash/zipline/internal/bridge/Endpoint;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getEndpoint", "()Lapp/cash/zipline/internal/bridge/Endpoint;", "setEndpoint", "(Lapp/cash/zipline/internal/bridge/Endpoint;)V", "take", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "adapter", "Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;", "(Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;)Lapp/cash/zipline/ZiplineService;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ReceiveByReference implements PassByReference {
    private Endpoint endpoint;
    private String name;

    public ReceiveByReference(String name, Endpoint endpoint) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        this.name = name;
        this.endpoint = endpoint;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final Endpoint getEndpoint() {
        return this.endpoint;
    }

    public final void setEndpoint(Endpoint endpoint) {
        Intrinsics.checkNotNullParameter(endpoint, "<set-?>");
        this.endpoint = endpoint;
    }

    public final <T extends ZiplineService> T take(ZiplineServiceAdapter<T> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        ZiplineScope takeScope$zipline_release = this.endpoint.getTakeScope();
        if (takeScope$zipline_release == null) {
            takeScope$zipline_release = new ZiplineScope();
        }
        return (T) this.endpoint.take(this.name, takeScope$zipline_release, adapter);
    }
}
