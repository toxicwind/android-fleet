package coil3.disk;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import okio.FileSystem;

/* JADX INFO: compiled from: utils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\n\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0000\"\u001b\u0010\u0000\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"instance", "Lcoil3/disk/DiskCache;", "getInstance", "()Lcoil3/disk/DiskCache;", "instance$delegate", "Lkotlin/Lazy;", "singletonDiskCache", "coil-core_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class UtilsKt {
    private static final Lazy instance$delegate = LazyKt.lazy(new Function0() { // from class: coil3.disk.UtilsKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new DiskCache.Builder().directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY.resolve("coil3_disk_cache")).build();
        }
    });

    private static final DiskCache getInstance() {
        return (DiskCache) instance$delegate.getValue();
    }

    public static final DiskCache singletonDiskCache() {
        return getInstance();
    }
}
