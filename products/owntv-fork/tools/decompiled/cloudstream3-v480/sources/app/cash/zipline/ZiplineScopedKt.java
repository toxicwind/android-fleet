package app.cash.zipline;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.internal.bridge.OutboundService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ZiplineScoped.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u00012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"withScope", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "scope", "Lapp/cash/zipline/ZiplineScope;", "(Lapp/cash/zipline/ZiplineService;Lapp/cash/zipline/ZiplineScope;)Lapp/cash/zipline/ZiplineService;", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ZiplineScopedKt {
    public static final <T extends ZiplineService> T withScope(T t, ZiplineScope scope) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(scope, "scope");
        if (!(t instanceof OutboundService)) {
            throw new IllegalArgumentException(("cannot scope " + t + "; it isn't an outbound service").toString());
        }
        return (T) ((OutboundService) t).getCallHandler().withScope(scope).outboundService();
    }
}
