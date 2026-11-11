package app.cash.zipline;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineService;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ZiplineServiceType.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n0\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lapp/cash/zipline/ZiplineServiceType;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "", "name", "", "getName", "()Ljava/lang/String;", "functions", "", "Lapp/cash/zipline/ZiplineFunction;", "getFunctions", "()Ljava/util/List;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ZiplineServiceType<T extends ZiplineService> {
    List<ZiplineFunction<T>> getFunctions();

    String getName();
}
