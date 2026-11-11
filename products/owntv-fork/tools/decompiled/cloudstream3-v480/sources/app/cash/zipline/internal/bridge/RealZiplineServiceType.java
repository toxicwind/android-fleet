package app.cash.zipline.internal.bridge;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineFunction;
import app.cash.zipline.ZiplineService;
import app.cash.zipline.ZiplineServiceType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: RealZiplineServiceType.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0003\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0007¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR \u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR#\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lapp/cash/zipline/internal/bridge/RealZiplineServiceType;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "Lapp/cash/zipline/ZiplineServiceType;", "name", "", "functions", "", "Lapp/cash/zipline/ZiplineFunction;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getFunctions", "()Ljava/util/List;", "functionsById", "", "getFunctionsById", "()Ljava/util/Map;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RealZiplineServiceType<T extends ZiplineService> implements ZiplineServiceType<T> {
    private final List<ZiplineFunction<T>> functions;
    private final Map<String, ZiplineFunction<T>> functionsById;
    private final String name;

    /* JADX WARN: Multi-variable type inference failed */
    public RealZiplineServiceType(String name, List<? extends ZiplineFunction<T>> functions) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(functions, "functions");
        this.name = name;
        this.functions = functions;
        List<ZiplineFunction<T>> functions2 = getFunctions();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(functions2, 10)), 16));
        for (Object obj : functions2) {
            linkedHashMap.put(((ZiplineFunction) obj).getId(), obj);
        }
        this.functionsById = linkedHashMap;
    }

    @Override // app.cash.zipline.ZiplineServiceType
    public String getName() {
        return this.name;
    }

    @Override // app.cash.zipline.ZiplineServiceType
    public List<ZiplineFunction<T>> getFunctions() {
        return this.functions;
    }

    public final Map<String, ZiplineFunction<T>> getFunctionsById() {
        return this.functionsById;
    }
}
