package app.cash.zipline;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.internal.bridge.ZiplineServiceAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: serializers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\u001a6\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0012\b\u0002\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u0007\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0001¨\u0006\n"}, d2 = {"ziplineServiceSerializer", "Lkotlinx/serialization/KSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "kClass", "Lkotlin/reflect/KClass;", "typeArgumentsSerializers", "", "ziplineServiceAdapter", "Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SerializersKt {
    public static final <T extends ZiplineService> KSerializer<T> ziplineServiceSerializer() {
        throw new IllegalStateException("unexpected call to ziplineServiceSerializer(): is the Zipline plugin configured?".toString());
    }

    public static /* synthetic */ KSerializer ziplineServiceSerializer$default(KClass kClass, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        return ziplineServiceSerializer(kClass, list);
    }

    public static final <T extends ZiplineService> KSerializer<T> ziplineServiceSerializer(KClass<?> kClass, List<? extends KSerializer<?>> typeArgumentsSerializers) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(typeArgumentsSerializers, "typeArgumentsSerializers");
        throw new IllegalStateException("unexpected call to ziplineServiceSerializer(): is the Zipline plugin configured?".toString());
    }

    public static final <T extends ZiplineService> KSerializer<T> ziplineServiceSerializer(ZiplineServiceAdapter<T> ziplineServiceAdapter) {
        Intrinsics.checkNotNullParameter(ziplineServiceAdapter, "ziplineServiceAdapter");
        return ziplineServiceAdapter;
    }
}
