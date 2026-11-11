package app.cash.zipline;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventListener.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0010\b&\u0018\u0000 62\u00020\u0001:\u000256B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J \u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001a\u0010\r\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J*\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0016J\u0018\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\tH\u0016J\"\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0016J$\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0016J4\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0016J0\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\t2\n\u0010\u001e\u001a\u00060\u001fj\u0002` 2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0016J\u001a\u0010!\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\tH\u0016J\"\u0010#\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0016J.\u0010$\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t2\n\u0010\u001e\u001a\u00060\u001fj\u0002` 2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0016J \u0010%\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010&\u001a\u00020'H\u0016J&\u0010(\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010\t2\n\u0010\u001e\u001a\u00060\u001fj\u0002` H\u0016J*\u0010)\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020\tH\u0016J\"\u0010+\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u001a\u0010,\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\tH\u0016J\"\u0010.\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0016J\u001a\u0010/\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\tH\u0016J\"\u00100\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0016J\u001a\u00101\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\tH\u0016J\"\u00102\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0016J\u0010\u00103\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u00104\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u00067"}, d2 = {"Lapp/cash/zipline/EventListener;", "", "<init>", "()V", "bindService", "", "zipline", "Lapp/cash/zipline/Zipline;", "name", "", NotificationCompat.CATEGORY_SERVICE, "Lapp/cash/zipline/ZiplineService;", "takeService", "callStart", NotificationCompat.CATEGORY_CALL, "Lapp/cash/zipline/Call;", "callEnd", "result", "Lapp/cash/zipline/CallResult;", "startValue", "serviceLeaked", "applicationLoadStart", "applicationName", "manifestUrl", "applicationLoadSkipped", "applicationLoadSkippedNotFresh", "applicationLoadSuccess", "manifest", "Lapp/cash/zipline/ZiplineManifest;", "applicationLoadFailed", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "downloadStart", ImagesContract.URL, "downloadEnd", "downloadFailed", "cacheHit", "fileSize", "", "manifestParseFailed", "manifestVerified", "verifiedKey", "manifestReady", "moduleLoadStart", "moduleId", "moduleLoadEnd", "initializerStart", "initializerEnd", "mainFunctionStart", "mainFunctionEnd", "ziplineCreated", "ziplineClosed", "Factory", "Companion", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class EventListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final EventListener NONE = new EventListener() { // from class: app.cash.zipline.EventListener$Companion$NONE$1
    };

    /* JADX INFO: compiled from: EventListener.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lapp/cash/zipline/EventListener$Factory;", "", "create", "Lapp/cash/zipline/EventListener;", "applicationName", "", "manifestUrl", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface Factory {
        EventListener create(String applicationName, String manifestUrl);
    }

    public void applicationLoadFailed(String applicationName, String manifestUrl, Exception exception, Object startValue) {
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        Intrinsics.checkNotNullParameter(exception, "exception");
    }

    public void applicationLoadSkipped(String applicationName, String manifestUrl, Object startValue) {
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        Intrinsics.checkNotNullParameter(manifestUrl, "manifestUrl");
    }

    public void applicationLoadSkippedNotFresh(String applicationName, String manifestUrl, Object startValue) {
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
    }

    public Object applicationLoadStart(String applicationName, String manifestUrl) {
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        return null;
    }

    public void applicationLoadSuccess(String applicationName, String manifestUrl, ZiplineManifest manifest, Zipline zipline, Object startValue) {
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        Intrinsics.checkNotNullParameter(manifest, "manifest");
        Intrinsics.checkNotNullParameter(zipline, "zipline");
    }

    public void bindService(Zipline zipline, String name, ZiplineService service) {
        Intrinsics.checkNotNullParameter(zipline, "zipline");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(service, "service");
    }

    public void cacheHit(String applicationName, String url, long fileSize) {
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        Intrinsics.checkNotNullParameter(url, "url");
    }

    public void callEnd(Zipline zipline, Call call, CallResult result, Object startValue) {
        Intrinsics.checkNotNullParameter(zipline, "zipline");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
    }

    public Object callStart(Zipline zipline, Call call) {
        Intrinsics.checkNotNullParameter(zipline, "zipline");
        Intrinsics.checkNotNullParameter(call, "call");
        return null;
    }

    public void downloadEnd(String applicationName, String url, Object startValue) {
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        Intrinsics.checkNotNullParameter(url, "url");
    }

    public void downloadFailed(String applicationName, String url, Exception exception, Object startValue) {
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(exception, "exception");
    }

    public Object downloadStart(String applicationName, String url) {
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        Intrinsics.checkNotNullParameter(url, "url");
        return null;
    }

    public void initializerEnd(Zipline zipline, String applicationName, Object startValue) {
        Intrinsics.checkNotNullParameter(zipline, "zipline");
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
    }

    public Object initializerStart(Zipline zipline, String applicationName) {
        Intrinsics.checkNotNullParameter(zipline, "zipline");
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        return null;
    }

    public void mainFunctionEnd(Zipline zipline, String applicationName, Object startValue) {
        Intrinsics.checkNotNullParameter(zipline, "zipline");
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
    }

    public Object mainFunctionStart(Zipline zipline, String applicationName) {
        Intrinsics.checkNotNullParameter(zipline, "zipline");
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        return null;
    }

    public void manifestParseFailed(String applicationName, String url, Exception exception) {
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        Intrinsics.checkNotNullParameter(exception, "exception");
    }

    public void manifestReady(String applicationName, String manifestUrl, ZiplineManifest manifest) {
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        Intrinsics.checkNotNullParameter(manifest, "manifest");
    }

    public void manifestVerified(String applicationName, String manifestUrl, ZiplineManifest manifest, String verifiedKey) {
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        Intrinsics.checkNotNullParameter(manifest, "manifest");
        Intrinsics.checkNotNullParameter(verifiedKey, "verifiedKey");
    }

    public void moduleLoadEnd(Zipline zipline, String moduleId, Object startValue) {
        Intrinsics.checkNotNullParameter(zipline, "zipline");
        Intrinsics.checkNotNullParameter(moduleId, "moduleId");
    }

    public Object moduleLoadStart(Zipline zipline, String moduleId) {
        Intrinsics.checkNotNullParameter(zipline, "zipline");
        Intrinsics.checkNotNullParameter(moduleId, "moduleId");
        return null;
    }

    public void serviceLeaked(Zipline zipline, String name) {
        Intrinsics.checkNotNullParameter(zipline, "zipline");
        Intrinsics.checkNotNullParameter(name, "name");
    }

    public void takeService(Zipline zipline, String name, ZiplineService service) {
        Intrinsics.checkNotNullParameter(zipline, "zipline");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(service, "service");
    }

    public void ziplineClosed(Zipline zipline) {
        Intrinsics.checkNotNullParameter(zipline, "zipline");
    }

    public void ziplineCreated(Zipline zipline) {
        Intrinsics.checkNotNullParameter(zipline, "zipline");
    }

    /* JADX INFO: compiled from: EventListener.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lapp/cash/zipline/EventListener$Companion;", "", "<init>", "()V", "NONE", "Lapp/cash/zipline/EventListener;", "getNONE", "()Lapp/cash/zipline/EventListener;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EventListener getNONE() {
            return EventListener.NONE;
        }
    }
}
