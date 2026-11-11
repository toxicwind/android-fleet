package app.cash.zipline;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineService;
import kotlin.Metadata;

/* JADX INFO: compiled from: ZiplineFunction.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0012\u0010\r\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\f¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lapp/cash/zipline/ZiplineFunction;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "", "id", "", "getId", "()Ljava/lang/String;", "signature", "getSignature", "isSuspending", "", "()Z", "isClose", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ZiplineFunction<T extends ZiplineService> {
    String getId();

    String getSignature();

    boolean isClose();

    boolean isSuspending();
}
