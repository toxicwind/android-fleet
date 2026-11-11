package io.ktor.util.reflect;

import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: ServiceLoader.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\u001a\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0004\u001a\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001e\u0010\b\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000H\u0087\b¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/sequences/Sequence;", "loadServicesAsSequence", "()Lkotlin/sequences/Sequence;", "", "loadServices", "()Ljava/util/List;", "loadServiceOrNull", "()Ljava/lang/Object;", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ServiceLoaderKt {
    public static final /* synthetic */ <T> Sequence<T> loadServicesAsSequence() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Iterator it = ServiceLoader.load(Object.class, Object.class.getClassLoader()).iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        return SequencesKt.asSequence(it);
    }

    public static final /* synthetic */ <T> List<T> loadServices() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Iterator it = ServiceLoader.load(Object.class, Object.class.getClassLoader()).iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        return SequencesKt.toList(SequencesKt.asSequence(it));
    }

    public static final /* synthetic */ <T> T loadServiceOrNull() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Iterator it = ServiceLoader.load(Object.class, Object.class.getClassLoader()).iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        return (T) SequencesKt.firstOrNull(SequencesKt.asSequence(it));
    }
}
