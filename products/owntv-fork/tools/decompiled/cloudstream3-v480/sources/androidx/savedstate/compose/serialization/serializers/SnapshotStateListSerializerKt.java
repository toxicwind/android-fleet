package androidx.savedstate.compose.serialization.serializers;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.reflect.KType;
import kotlinx.serialization.SerializersKt;

/* JADX INFO: compiled from: SnapshotStateListSerializer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001H\u0086\b¨\u0006\u0003"}, d2 = {"SnapshotStateListSerializer", "Landroidx/savedstate/compose/serialization/serializers/SnapshotStateListSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "savedstate-compose"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SnapshotStateListSerializerKt {
    public static final /* synthetic */ <T> SnapshotStateListSerializer<T> SnapshotStateListSerializer() {
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
        return new SnapshotStateListSerializer<>(SerializersKt.serializer((KType) null));
    }
}
