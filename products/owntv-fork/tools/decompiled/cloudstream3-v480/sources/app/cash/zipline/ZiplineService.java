package app.cash.zipline;

import kotlin.Metadata;

/* JADX INFO: compiled from: ZiplineService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00060\u0001j\u0002`\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005À\u0006\u0003"}, d2 = {"Lapp/cash/zipline/ZiplineService;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "close", "", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ZiplineService extends AutoCloseable {
    @Override // java.lang.AutoCloseable
    void close();

    /* JADX INFO: renamed from: app.cash.zipline.ZiplineService$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ZiplineService.kt */
    public final /* synthetic */ class CC {
        public static void $default$close(ZiplineService _this) {
        }
    }

    /* JADX INFO: compiled from: ZiplineService.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void close(ZiplineService ziplineService) {
            CC.$default$close(ziplineService);
        }
    }
}
