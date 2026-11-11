package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: Caching.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004JE\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t0\u000b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0014\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t0\bH\u0086\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\rR4\u0010\u0011\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u0005\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t0\u000b0\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"Lkotlinx/serialization/internal/ParametrizedCacheEntry;", ExifInterface.GPS_DIRECTION_TRUE, "", "<init>", "()V", "", "Lkotlin/reflect/KType;", "types", "Lkotlin/Function0;", "Lkotlinx/serialization/KSerializer;", "producer", "Lkotlin/Result;", "computeIfAbsent-gIAlu-s", "(Ljava/util/List;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "computeIfAbsent", "j$/util/concurrent/ConcurrentHashMap", "Lkotlinx/serialization/internal/KTypeWrapper;", "serializers", "Lj$/util/concurrent/ConcurrentHashMap;", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class ParametrizedCacheEntry<T> {
    private final ConcurrentHashMap<List<KTypeWrapper>, Result<KSerializer<T>>> serializers = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: computeIfAbsent-gIAlu-s, reason: not valid java name */
    public final Object m10615computeIfAbsentgIAlus(List<? extends KType> types, Function0<? extends KSerializer<T>> producer) {
        Object objM8936constructorimpl;
        Intrinsics.checkNotNullParameter(types, "types");
        Intrinsics.checkNotNullParameter(producer, "producer");
        List<? extends KType> list = types;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new KTypeWrapper((KType) it.next()));
        }
        ArrayList arrayList2 = arrayList;
        ConcurrentHashMap concurrentHashMap = this.serializers;
        Object objM8935boximpl = concurrentHashMap.get(arrayList2);
        if (objM8935boximpl == null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(producer.invoke());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            objM8935boximpl = Result.m8935boximpl(objM8936constructorimpl);
            Object objPutIfAbsent = concurrentHashMap.putIfAbsent(arrayList2, objM8935boximpl);
            if (objPutIfAbsent != null) {
                objM8935boximpl = objPutIfAbsent;
            }
        }
        Intrinsics.checkNotNullExpressionValue(objM8935boximpl, "getOrPut(...)");
        return ((Result) objM8935boximpl).getValue();
    }
}
