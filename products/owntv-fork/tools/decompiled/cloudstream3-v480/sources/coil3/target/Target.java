package coil3.target;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import coil3.Image;
import kotlin.Metadata;

/* JADX INFO: compiled from: Target.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lcoil3/target/Target;", "", "onStart", "", "placeholder", "Lcoil3/Image;", "onError", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "onSuccess", "result", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface Target {

    /* JADX INFO: renamed from: coil3.target.Target$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Target.kt */
    public final /* synthetic */ class CC {
        public static void $default$onError(Target _this, Image image) {
        }

        public static void $default$onStart(Target _this, Image image) {
        }

        public static void $default$onSuccess(Target _this, Image image) {
        }
    }

    void onError(Image error);

    void onStart(Image placeholder);

    void onSuccess(Image result);
}
