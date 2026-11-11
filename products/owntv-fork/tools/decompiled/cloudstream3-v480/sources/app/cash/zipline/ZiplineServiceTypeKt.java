package app.cash.zipline;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.internal.bridge.OutboundService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ZiplineServiceType.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"'\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"'\u0010\u0006\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005¨\u0006\b"}, d2 = {"targetType", "Lapp/cash/zipline/ZiplineServiceType;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "getTargetType", "(Lapp/cash/zipline/ZiplineService;)Lapp/cash/zipline/ZiplineServiceType;", "sourceType", "getSourceType", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ZiplineServiceTypeKt {
    public static final <T extends ZiplineService> ZiplineServiceType<T> getTargetType(T t) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        if (t instanceof OutboundService) {
            return ((OutboundService) t).getCallHandler().getTargetType();
        }
        return null;
    }

    public static final <T extends ZiplineService> ZiplineServiceType<T> getSourceType(T t) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        if (t instanceof OutboundService) {
            return (ZiplineServiceType<T>) ((OutboundService) t).getCallHandler().getSourceType();
        }
        return null;
    }
}
